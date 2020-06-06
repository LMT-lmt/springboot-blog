package cn.imlmt.blog.service.Impl;

import cn.imlmt.blog.entities.Tag;
import cn.imlmt.blog.exception.NotFoundException;
import cn.imlmt.blog.mapper.TagMapper;
import cn.imlmt.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        if (tag.getAmount() == null){
            tag.setAmount(0);
        }
        return tagMapper.saveTag(tag);
    }

    @Transactional
    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Transactional
    @Override
    public List<Tag> getAllTags() {
        return tagMapper.getAllTags();
    }

    @Transactional
    @Override
    public int updateTag(Long id, Tag tag) {
        Tag t = tagMapper.getTagById(id);
        if (t == null){
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag, t);
        return tagMapper.updateTag(t);
    }

    @Transactional
    @Override
    public void removeTag(Long id) {
        tagMapper.removeTag(id);
    }

    @Transactional
    @Override
    public void incrTagAmount(Long id) {
        Tag tag = tagMapper.getTagById(id);
        tag.setAmount(tag.getAmount()+1);
        tagMapper.updateTag(tag);
    }

    @Transactional
    @Override
    public void decrTagAmount(Long id) {
        Tag tag = tagMapper.getTagById(id);
        tag.setAmount(tag.getAmount()-1);
        tagMapper.updateTag(tag);
    }

    @Override
    public List<Tag> getHotTag(Integer size) {
        return tagMapper.getHotTag(size);
    }

    //************** t_blog_tags ***************************************************************
    @Transactional
    @Override
    public void insertBlogTag(Long blogId, Long tagId) {
        tagMapper.insertBlogTag(blogId, tagId);
    }

    @Transactional
    @Override
    public void removeBlogTag(Long blogId, Long tagId) {
        tagMapper.removeBlogTag(blogId, tagId);
    }

    @Override
    public void removeBlogTagByBlogId(Long id) {
        tagMapper.removeBlogTagByBlogId(id);
    }
}
