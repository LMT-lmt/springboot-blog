package cn.imlmt.blog.mapper;

import cn.imlmt.blog.entities.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentMapper {


    Comment getCommentById(Long id);

    //获取顶层评论或子评论
    List<Comment> getCommentListByBlogIdParentId(@Param("blogId") Long blogId,
                                                 @Param("parentId") Long parentId);

    //获取父级评论的父级id
    Long getParentCommentId(Long id);

    int saveComment(Comment comment);
}
