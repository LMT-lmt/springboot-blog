package cn.imlmt.blog.mapper;

import cn.imlmt.blog.entities.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {

    int saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> getAllTypes();

    int updateType(Type type);

    void removeType(Long id);

    List<Type> getHotType(Integer size);
}
