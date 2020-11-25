package net.skhu.mapper;

import net.skhu.dto.Client;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClientMapper {
    @Select("SELECT * FROM client WHERE id = #{id}")
    Client findOne(int id);

    @Select("SELECT * FROM client WHERE name = #{name}")
    List<Client> findByName(String name);

    @Select("SELECT * FROM client")
    List<Client> findAll();

    @Select("SELECT * FROM client WHERE name = #{clientId}")
    List<Client> findById(String clientId);

    @Insert("INSERT client (name, age, phoneNumber, address) VALUES (#{name}, #{age}, #{phoneNumber}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Client client);

    @Update("UPDATE client SET name = #{name}, age = #{age}, phoneNumber = #{phoneNumber}, address = #{address} WHERE id = #{id}")
    void update(Client client);

    @Delete("DELETE FROM client WHERE id = #{id}")
    void delete(int id);
}
