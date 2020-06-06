package cn.imlmt.blog.service.Impl;

import cn.imlmt.blog.entities.Comment;
import cn.imlmt.blog.mapper.CommentMapper;
import cn.imlmt.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    //获取所有留言
    @Transactional
    @Override
    public List<Comment> getCommentListByBlogId(Long id) {
        //获取顶级评论
        List<Comment> comments = commentMapper.getCommentListByBlogIdParentId(id, 0L);

        //获取子评论
        for (Comment comment : comments) {
            comment.setReplyComments(commentMapper.getCommentListByBlogIdParentId(id, comment.getId()));
        }
        return comments;
    }

    @Transactional
    @Override
    public void saveComment(Comment comment) {
        Long parentCommentId = comment.getParentCommentId();
        if(parentCommentId != 0){   //如果是子评论或更深层次的评论，统一放在第二级
            //父级
            Comment c = commentMapper.getCommentById(parentCommentId);
            if (c.getParentCommentId() != null){    //如果父级不是顶级
                comment.setParentCommentId(c.getParentCommentId());
            } else {    //如果父级就是顶级
                comment.setParentCommentId(c.getId());
            }

        }else { //顶级评论
            comment.setParentCommentId(null);
        }
        if ("".equals(comment.getReplyName())){
            comment.setReplyName(null);
        }
        comment.setCreateTime(new Date());
        commentMapper.saveComment(comment);
    }
}
