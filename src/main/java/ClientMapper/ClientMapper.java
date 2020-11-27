package ClientMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ClientDTO.Client;

@Mapper
public interface ClientMapper {


	@Select("select *  "
			+ "from client ")

	List<Client> findAll();

	@Select("select *  "
			+"from client "
			+ "where clientId = #{clientId} ")

	Client findByclientId(String clientId);


    @Update("UPDATE client "
    		+ "SET time = #{time}, money = #{money} , startDate = #{startDate} , endDate = #{endDate}  "
    		+ "WHERE clientId = #{clientId}")
    void update(Client client);

}
