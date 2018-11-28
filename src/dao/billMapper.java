package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.Bill;

public interface billMapper {

	List<Bill> selectAll(@Param("productName") String productName, @Param("providerId") Integer providerId,
			@Param("isPayment") Integer isPayment);

	public int update(Bill bill);

	public int update1(Bill bill);

	List<Bill> select(Map<String, Object> map);

}