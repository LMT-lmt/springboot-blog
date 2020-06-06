package cn.imlmt.blog.controller.admin;

import cn.imlmt.blog.entities.Blog;
import cn.imlmt.blog.entities.User;
import cn.imlmt.blog.queryvo.BlogListBean;
import cn.imlmt.blog.queryvo.BlogQuery;
import cn.imlmt.blog.service.BlogService;
import cn.imlmt.blog.service.TagService;
import cn.imlmt.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台博客管理
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogListBean> list = blogService.getBlogList();
        PageInfo<BlogListBean> pageInfo = new PageInfo<BlogListBean>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types", typeService.getAllTypes());
        return "admin/blogs";
    }

    //搜索
    @PostMapping("/blogs/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                         BlogQuery blogQuery){
        //System.out.println(blogQuery);
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogListBean> list = blogService.getBlogListWithReq(blogQuery);
        PageInfo<BlogListBean> pageInfo = new PageInfo<BlogListBean>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs :: blogList";
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());
    }

    //跳转到博客发布
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog", new Blog());
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    //跳转到博客编辑
    @GetMapping("/blogs/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String edit(Blog blog, RedirectAttributes attributes, HttpSession session){
        if(blog.getContent().trim().equals("")){
            attributes.addFlashAttribute("message1", "请输入博客内容！");
        } else {
            User user = (User) session.getAttribute("loginUser");
            blog.setUserId(user.getId());

            blogService.saveBlog(blog);
            attributes.addFlashAttribute("message2", "操作成功！");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        blogService.deleteBlogById(id);
        attributes.addFlashAttribute("message2", "操作成功！");
        return "redirect:/admin/blogs";
    }
}
