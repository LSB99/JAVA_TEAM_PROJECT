package net.skhu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.skhu.dto.Client;

@Mapper
public interface ClientMapper {

	@Select("select *  "
			+"from client "
			+ "where clientId = #{clientId} ")

	Client findByclientId(String clientId);


    @Update("UPDATE client "
    		+ "SET time = #{time}, money = #{money}  "
    		+ "WHERE clientId = #{clientId}")
    void update(Client client);

}
