package cn.imlmt.blog.service;

import cn.imlmt.blog.entities.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentListByBlogId(Long id);

    void saveComment(Comment comment);
}
