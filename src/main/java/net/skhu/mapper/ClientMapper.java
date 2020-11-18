package net.skhu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import net.skhu.dto.Client;

@Mapper
public interface ClientMapper {


    @Update("UPDATE client "
    		+ "SET time = #{time}, money = #{money}  "
    		+ "WHERE name = #{name}")
    void update(Client client);

}
