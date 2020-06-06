package cn.imlmt.blog.entities;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Serializable {

    private Long id;
    private String name;
    private Integer amount;

    //多对多
    private List<Blog> blogs = new ArrayList<>();
}
