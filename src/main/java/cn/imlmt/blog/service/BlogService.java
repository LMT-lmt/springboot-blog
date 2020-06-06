package cn.imlmt.blog.service;

import cn.imlmt.blog.entities.Blog;
import cn.imlmt.blog.queryvo.BlogListBean;
import cn.imlmt.blog.queryvo.BlogQuery;
import cn.imlmt.blog.queryvo.BlogShowBean;

import java.util.List;

public interface BlogService {

    Blog getBlogById(Long id);

    Blog getBlogByIdChangeContent(Long id);

    //保存博客
    void saveBlog(Blog blog);

    void deleteBlogById(Long id);

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
}
