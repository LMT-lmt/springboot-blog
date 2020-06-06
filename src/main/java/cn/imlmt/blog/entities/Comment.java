package cn.imlmt.blog.entities;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;
    private String replyName;   //回复对象的名称

    private boolean adminComment;   //是否是博主的评论

    //多对一
    private Blog blog;

    //评论自关联
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
}
