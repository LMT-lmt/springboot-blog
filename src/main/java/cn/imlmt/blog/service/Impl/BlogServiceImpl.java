package cn.imlmt.blog.service.Impl;

import cn.imlmt.blog.entities.Blog;
import cn.imlmt.blog.entities.Tag;
import cn.imlmt.blog.entities.Type;
import cn.imlmt.blog.mapper.BlogMapper;
import cn.imlmt.blog.queryvo.BlogListBean;
import cn.imlmt.blog.queryvo.BlogQuery;
import cn.imlmt.blog.queryvo.BlogShowBean;
import cn.imlmt.blog.service.BlogService;
import cn.imlmt.blog.service.TagService;
import cn.imlmt.blog.service.TypeService;
import cn.imlmt.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;

    @Transactional
    @Override
    public Blog getBlogById(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        String tagIds = blog.getTagIds();
        if(tagIds != null){
            for (String tagId : tagIds.split(",")) {
                Tag tag = tagService.getTagById(Long.valueOf(tagId));
                blog.getTags().add(tag);
            }
        }
        return blog;
    }

    @Transactional
    @Override
    public Blog getBlogByIdChangeContent(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        blogMapper.addBlogViews(id);
        String tagIds = blog.getTagIds();
        if(tagIds != null){
            for (String tagId : tagIds.split(",")) {
                Tag tag = tagService.getTagById(Long.valueOf(tagId));
                blog.getTags().add(tag);
            }
        }
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    @Transactional
    @Override
    public void saveBlog(Blog blog) {
        String tags = blog.getTagIds();
        Long typeId = blog.getTypeId();
        Long blogId = blog.getId();

        if(blogId == null){   //保存新博客
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blogMapper.saveBlog(blog);
            blogId = blog.getId();

            if(tags != null){   //更新标签
                String[] tagIds = tags.split(",");
                for (String tagId : tagIds) {
                    tagService.incrTagAmount(Long.parseLong(tagId));
                    //维护多对多关系
                    tagService.insertBlogTag(blogId, Long.parseLong(tagId));
                }
            }

            typeService.incrTypeAmount(typeId); //更新分类

        } else {        //更新旧博客
            Blog oldBlog = blogMapper.getBlogById(blog.getId());
            blog.setUpdateTime(new Date());

            String oldTags = oldBlog.getTagIds();

            if(oldTags != null && !oldTags.equals(tags)){   //标签更新
                tagService.removeBlogTagByBlogId(oldBlog.getId());
                for (String s : oldTags.split(",")) {
                    tagService.decrTagAmount(Long.parseLong(s));
                }

                if(tags != null){   //更新标签
                    String[] tagIds = tags.split(",");
                    for (String tagId : tagIds) {
                        tagService.incrTagAmount(Long.parseLong(tagId));
                        //维护多对多关系
                        tagService.insertBlogTag(blogId, Long.parseLong(tagId));
                    }
                }
            }

            if(!typeId.equals(oldBlog.getTypeId())){
                typeService.incrTypeAmount(typeId);
                typeService.decrTypeAmount(oldBlog.getTypeId());
            }

            blogMapper.updateBlog(blog);
        }

    }

    @Transactional
    @Override
    public void deleteBlogById(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        typeService.decrTypeAmount(blog.getTypeId());
        String tagIds = blog.getTagIds();
        if(tagIds != null){
            for (String tag : tagIds.split(",")) {
                tagService.decrTagAmount(Long.valueOf(tag));
            }
            tagService.removeBlogTagByBlogId(id);
        }
        blogMapper.deleteBlogById(id);
    }

    @Transactional
    @Override
    public List<BlogListBean> getBlogList() {
        return blogMapper.getBlogList();
    }

    @Transactional
    @Override
    public List<BlogListBean> getBlogListWithReq(BlogQuery blogQuery) {
        return blogMapper.getBlogListWithReq(blogQuery);
    }

    @Transactional
    @Override
    public List<BlogListBean> getRecommendBlogList(Integer size) {
        return blogMapper.getRecommendBlogList(size);
    }

    @Transactional
    @Override
    public List<BlogShowBean> getLatestBlogList() {
        return blogMapper.getLatestBlogList();
    }

    @Override
    public List<BlogShowBean> getSearchBlogList(String query) {
        return blogMapper.getSearchBlogList(query);
    }
}
