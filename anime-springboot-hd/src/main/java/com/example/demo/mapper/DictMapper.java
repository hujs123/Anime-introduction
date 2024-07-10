package com.example.demo.mapper;


import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.example.demo.entity.DictEntity;
import com.example.demo.entity.PersonInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface DictMapper {

    List<DictEntity> getDictList(String dictType);

    List<DictEntity> getDictListByName(String name);

}
