package cn.imlmt.blog.controller.admin;

import cn.imlmt.blog.entities.Tag;
import cn.imlmt.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //获取分页后的分类
    @GetMapping("/tags")
    public String types(Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Tag> list = tagService.getAllTags();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    //新增分类页面跳转
    @GetMapping("tags/insert")
    public String tags(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }

    //新增分类
    @PostMapping("tags/insert")
    public String save(Tag tag, RedirectAttributes attributes){
        //System.out.println(type.getName());
        Tag t = tagService.getTagByName(tag.getName());
        if(t != null){
            attributes.addFlashAttribute("message", "该分类已存在！");
            return "redirect:/admin/tags/insert";
        } else {
            tagService.saveTag(tag);
            attributes.addFlashAttribute("message", "操作成功！");
            return "redirect:/admin/tags";
        }
    }

    //跳转到修改页面
    @GetMapping("/tags/{id}")
    public String toEditPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tag-input";
    }

    //修改分类
    @PostMapping("/tags/{id}")
    public String edit(Tag tag, RedirectAttributes attributes){
        //System.out.println(type);
        Tag t = tagService.getTagByName(tag.getName());
        if(t != null) {
            attributes.addFlashAttribute("message", "该分类已存在！");
            return "redirect:/admin/tags/"+t.getId();
        }
        tagService.updateTag(tag.getId(), tag);
        attributes.addFlashAttribute("message", "操作成功！");
        return "redirect:/admin/tags";
    }

    //删除分类
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        tagService.removeTag(id);
        attributes.addFlashAttribute("message", "操作成功！");
        return "redirect:/admin/tags";
    }

}
