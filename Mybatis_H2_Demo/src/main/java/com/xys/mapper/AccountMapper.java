package com.xys.mapper;

import com.xys.model.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xiongyongshun
 * @version 1.0
 * @created 16/8/19 17:23
 */
@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO account (name, age) VALUES ( #{name}, #{age} )")
    int insert();

    @Select("SELECT * FROM account where name = #{name}")
    Account select(@Param("name") String name);
}
