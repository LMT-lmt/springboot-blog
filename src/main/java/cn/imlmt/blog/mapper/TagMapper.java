package cn.imlmt.blog.mapper;

import cn.imlmt.blog.entities.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {
//************** t_tag ***************************************
    int saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTags();

    int updateTag(Tag tag);

    void removeTag(Long id);

    List<Tag> getHotTag(Integer size);

//************** t_blog_tags ***************************************************************
    void insertBlogTag(@Param("blogId") Long blogId,@Param("tagId") Long tagId);

    void removeBlogTag(@Param("blogId") Long blogId,@Param("tagId") Long tagId);

    void removeBlogTagByBlogId(Long id);
}
