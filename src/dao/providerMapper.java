package dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.provider;

public interface providerMapper {

	List<provider> select1(@Param("proCode") String proCode, @Param("proName") String proName);

	List<provider> select2(Integer[] id);

	List<provider> ifselect(@Param("proCode") String proCode, @Param("proName") String proName,
			@Param("proContact") String proContact, @Param("creationDate") Date creationDate);

	public int count();

	List<provider> fenye(@Param("from") int from, @Param("pageSize") int pageSize);

}
