package cn.imlmt.blog.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {

    private Long id;                  //id
    private String title;               //标题
    private String content;             //内容
    private String firstPicture;          //首图
    private String flag;                //标记转载等
    private Integer views;              //浏览次数
    private boolean appreciation;       //赞赏开启
    private boolean shareStatement;     //版权开启
    private boolean commentabled;       //评论开启
    private boolean published;          //发布
    private boolean recommend;          //推荐
    private String description;         //描述
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;            //创建时间
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;            //更新时间

    private Long typeId;
    private Long userId;

    private String tagIds;

    //分类，多对一
    private Type type;
    //标签，多对多
    private List<Tag> tags = new ArrayList<>();
    //用户，多对一
    private User user;
    //评论，一对多
    private List<Comment> comments = new ArrayList<>();
}
