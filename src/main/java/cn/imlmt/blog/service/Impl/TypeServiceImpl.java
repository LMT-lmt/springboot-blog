package cn.imlmt.blog.service.Impl;

import cn.imlmt.blog.entities.Type;
import cn.imlmt.blog.exception.NotFoundException;
import cn.imlmt.blog.mapper.TypeMapper;
import cn.imlmt.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
        if (type.getAmount() == null){
            type.setAmount(0);
        }
        return typeMapper.saveType(type);
    }

    @Transactional
    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Transactional
    @Override
    public List<Type> getAllTypes() {
        return typeMapper.getAllTypes();
    }

    @Transactional
    @Override
    public int updateType(Long id, Type type) {
        Type t = typeMapper.getTypeById(id);
        if (t == null){
            throw new NotFoundException("不存在该分类");
        }
        BeanUtils.copyProperties(type, t);
        return typeMapper.updateType(t);
    }

    @Transactional
    @Override
    public void removeType(Long id) {
        Type t = typeMapper.getTypeById(id);
        if(t.getAmount() != 0){
            throw new NotFoundException("该分类下还有相关博客，无法删除");
        }
        typeMapper.removeType(id);
    }

    @Override
    public void incrTypeAmount(Long id) {
        Type type = typeMapper.getTypeById(id);
        type.setAmount(type.getAmount()+1);
        typeMapper.updateType(type);
    }

    @Override
    public void decrTypeAmount(Long id) {
        Type type = typeMapper.getTypeById(id);
        type.setAmount(type.getAmount()-1);
        typeMapper.updateType(type);
    }

    @Override
    public List<Type> getHotType(Integer size) {
        return typeMapper.getHotType(size);
    }
}
