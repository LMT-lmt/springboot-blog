package cn.imlmt.blog.service;

import cn.imlmt.blog.entities.Type;

import java.util.List;

public interface TypeService {

    int saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> getAllTypes();

    int updateType(Long id, Type type);

    void removeType(Long id);

    void incrTypeAmount(Long id);

    void decrTypeAmount(Long id);

    List<Type> getHotType(Integer size);
}
