package cn.imlmt.blog.controller.admin;

import cn.imlmt.blog.entities.Type;
import cn.imlmt.blog.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    //获取分页后的分类
    @GetMapping("/types")
    public String types(Model model,
                    @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Type> list = typeService.getAllTypes();
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    //新增分类页面跳转
    @GetMapping("types/insert")
    public String types(Model model){
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    //新增分类
    @PostMapping("types/insert")
    public String save(Type type, RedirectAttributes attributes){
        //System.out.println(type.getName());
        Type t = typeService.getTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute("message", "该分类已存在！");
            return "redirect:/admin/types/insert";
        } else {
            typeService.saveType(type);
            attributes.addFlashAttribute("message", "操作成功！");
            return "redirect:/admin/types";
        }
    }

    //跳转到修改页面
    @GetMapping("/types/{id}")
    public String toEditPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/type-input";
    }

    //修改分类
    @PostMapping("/types/{id}")
    public String edit(Type type, RedirectAttributes attributes){
        //System.out.println(type);
        Type t = typeService.getTypeByName(type.getName());
        if(t != null) {
            attributes.addFlashAttribute("message", "该分类已存在！");
            return "redirect:/admin/types/"+t.getId();
        }
        typeService.updateType(type.getId(), type);
        attributes.addFlashAttribute("message", "操作成功！");
        return "redirect:/admin/types";
    }

    //删除分类
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        typeService.removeType(id);
        attributes.addFlashAttribute("message", "操作成功！");
        return "redirect:/admin/types";
    }
}
