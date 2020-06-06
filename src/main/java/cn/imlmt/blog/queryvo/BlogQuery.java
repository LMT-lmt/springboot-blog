package cn.imlmt.blog.queryvo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {

    private String title;
    private Long typeId;
    private Boolean recommend;
    private Boolean published;
}
