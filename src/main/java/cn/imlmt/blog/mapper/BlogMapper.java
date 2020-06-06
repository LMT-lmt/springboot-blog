package cn.imlmt.blog.mapper;

import cn.imlmt.blog.entities.Blog;
import cn.imlmt.blog.queryvo.BlogListBean;
import cn.imlmt.blog.queryvo.BlogQuery;
import cn.imlmt.blog.queryvo.BlogShowBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface BlogMapper {

    Blog getBlogById(Long id);
    //更新博客
    void updateBlog(Blog blog);

    void deleteBlogById(Long id);
    //保存博客
    Long saveBlog(Blog blog);
    //博客列表(无条件)
    List<BlogListBean> getBlogList();
    //博客列表(有条件)
    List<BlogListBean> getBlogListWithReq(BlogQuery blogQuery);

    //获取首页推荐博客列表id,title
    List<BlogListBean> getRecommendBlogList(Integer size);

    //获取首页最新博客列表
    List<BlogShowBean> getLatestBlogList();

    //获取搜索博客列表
    List<BlogShowBean> getSearchBlogList(String query);

    //访客+1
    void addBlogViews(Long id);
}
