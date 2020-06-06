package cn.imlmt.blog.queryvo;

import cn.imlmt.blog.entities.Type;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 博客列表展示用的
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogListBean {
    private Long id;
    private String title;
    private Long typeId;
    private boolean recommend;
    private boolean published;
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Type type;
}
