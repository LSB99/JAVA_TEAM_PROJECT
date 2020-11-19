package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.skhu.dto.Order;

	@Mapper
	public interface OrderMapper {
	    @Select("SELECT * FROM order WHERE orderId = #{orderId}")
	    Order findOne(int orderId);

	    @Select("SELECT * FROM order WHERE foodName = #{foodName}")
	    Order findByName(String foodName);

	    @Select("SELECT * FROM order")
	    List<Order> findAll();

	    @Insert("INSERT order (foodName, amount, price) VALUES (#{foodName}, #{amount}, #{price})")
	    @Options(useGeneratedKeys = true, keyProperty = "orderId")
	    void insert(Order order);

	    @Update("UPDATE order SET foodName = #{foodName}, amount = #{amount}, price = #{price} WHERE orderId = #{orderId}")
	    void update(Order order);

	    @Delete("DELETE FROM order WHERE orderId = #{orderId}")
	    void delete(int orderId);
	}

