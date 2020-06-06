package cn.imlmt.blog.controller;

import cn.imlmt.blog.entities.Comment;
import cn.imlmt.blog.entities.User;
import cn.imlmt.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Value("${comment.avatar}")
    private String avatar;

    //获取blog的评论
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId") Long id, Model model){
        List<Comment> commentList = commentService.getCommentListByBlogId(id);
        model.addAttribute("commentList", commentList);
        return "blog :: commentList";
    }

    //评论
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        //System.out.println(comment);
        User user = (User) session.getAttribute("loginUser");
        if (user != null){
            comment.setAdminComment(true);
            comment.setAvatar(user.getAvatar());
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
        } else {
            comment.setAdminComment(false);
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlogId();
    }

}
