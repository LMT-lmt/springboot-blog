package cn.imlmt.blog.service;

import cn.imlmt.blog.entities.Tag;

import java.util.List;

public interface TagService {
    int saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTags();

    int updateTag(Long id, Tag tag);

    void removeTag(Long id);

    void incrTagAmount(Long id);

    void decrTagAmount(Long id);

    List<Tag> getHotTag(Integer size);

    //************** t_blog_tags ***************************************************************
    void insertBlogTag(Long blogId, Long tagId);

    void removeBlogTag(Long blogId, Long tagId);

    void removeBlogTagByBlogId(Long id);
}
