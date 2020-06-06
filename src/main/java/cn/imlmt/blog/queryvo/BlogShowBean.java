package cn.imlmt.blog.queryvo;

import cn.imlmt.blog.entities.Type;
import cn.imlmt.blog.entities.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BlogShowBean {
    private Long id;
    private Integer views;
    private String description;
    private String title;
    private String firstPicture;
   // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Long userId;
    private Long typeId;

    private User user;
    private Type type;
}
