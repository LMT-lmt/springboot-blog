package cn.imlmt.blog.controller;

import cn.imlmt.blog.entities.Blog;
import cn.imlmt.blog.entities.Tag;
import cn.imlmt.blog.entities.Type;
import cn.imlmt.blog.exception.NotFoundException;
import cn.imlmt.blog.queryvo.BlogListBean;
import cn.imlmt.blog.queryvo.BlogShowBean;
import cn.imlmt.blog.service.BlogService;
import cn.imlmt.blog.service.TagService;
import cn.imlmt.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogShowBean> latestBlogList = blogService.getLatestBlogList();
        PageInfo<BlogShowBean> pageInfo = new PageInfo<BlogShowBean>(latestBlogList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("totalBlog", latestBlogList.size());

        List<BlogListBean> recommendBlogList = blogService.getRecommendBlogList(8);
        List<Type> hotType = typeService.getHotType(6);
        List<Tag> hotTag = tagService.getHotTag(8);

        model.addAttribute("recommendBlogList", recommendBlogList);
        model.addAttribute("hotType", hotType);
        model.addAttribute("hotTag", hotTag);

        return "index";
    }

    @PostMapping("/search")
    public String search(Model model,@RequestParam(value = "query") String query,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogShowBean> searchBlogList = blogService.getSearchBlogList(query);
        PageInfo<BlogShowBean> pageInfo = new PageInfo<BlogShowBean>(searchBlogList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("totalBlog", searchBlogList.size());
        model.addAttribute("query", query);
        return "search";
    }

    //博客详情
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id, Model model){
        Blog blog = blogService.getBlogByIdChangeContent(id);
        model.addAttribute("blog", blog);
        return "blog";
    }

    @GetMapping("/types")
    public String types(){

        return "types";
    }

}
