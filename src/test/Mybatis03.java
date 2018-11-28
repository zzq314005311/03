package test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLXML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import dao.billMapper;
import dao.providerMapper;
import pojo.Bill;
import pojo.provider;
import util.MybatisUtil;

public class Mybatis03 {

	// 使用if动态查询订单信息
	@Test
	public void if1() {

		SqlSession sqlSession = null;
		List<Bill> list = new ArrayList<Bill>();
		String productName = "油";
		Integer providerId = null;
		Integer isPayment = null;
		try {
			sqlSession = MybatisUtil.creSqlSession();
			list = sqlSession.getMapper(billMapper.class).selectAll(productName, providerId, isPayment);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closesqlSession(sqlSession);
		}
		for (Bill u : list) {
			System.out.println("订单id" + u.getId() + "订单编码" + u.getBillCode() + "商品名称" + u.getProductName() + "供应商id"
					+ u.getProviderId() + "供应商名称" + u.getProName() + "订单金额" + u.getTotalPrice() + "是否付款"
					+ u.getIsPayment() + "创建时间" + u.getCreationDate());
		}
	}

	// 使用if-where查询订单信息
	@Test
	public void ifelse() {
		SqlSession sqlSession = null;
		List<provider> list = new ArrayList<provider>();
		String proCode = "";
		String proName = "北";
		try {
			sqlSession = MybatisUtil.creSqlSession();
			list = sqlSession.getMapper(providerMapper.class).select1(proCode, proName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closesqlSession(sqlSession);
		}
		for (provider u : list) {
			System.out.println(u);
		}
	}

	// 使用if-set修改数据
	@Test
	public void update() {
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MybatisUtil.creSqlSession();
			Bill bill = new Bill();
			bill.setBillCode("12345");
			bill.setProductName("测试2");
			// bill.setProductDesc("测试2");
			// bill.setProductUnit("测试2");
			// bill.setProductCount(new BigDecimal(400.00));
			// bill.setTotalPrice(new BigDecimal(450.25));
			// bill.setIsPayment(2);
			// bill.setCreatedBy(2);
			// bill.setModifyBy(3);
			// bill.setProviderId(3);
			bill.setId(36);
			count = sqlSession.getMapper(billMapper.class).update(bill);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closesqlSession(sqlSession);
		}
		System.out.println(count);
	}

	// 使用if-trim实现修改数据
	@Test
	public void update1() {

		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MybatisUtil.creSqlSession();
			Bill bill = new Bill();
			bill.setBillCode("222");
			bill.setProductName("测试");
			// bill.setProductDesc("测试2");
			// bill.setProductUnit("测试2");
			// bill.setProductCount(new BigDecimal(400.00));
			// bill.setTotalPrice(new BigDecimal(450.25));
			// bill.setIsPayment(2);
			// bill.setCreatedBy(2);
			// bill.setModifyBy(3);
			// bill.setProviderId(3);
			bill.setId(36);
			count = sqlSession.getMapper(billMapper.class).update1(bill);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closesqlSession(sqlSession);
		}
		System.out.println(count);

	}

	// 使用foreadch获取供应商下面的订单
	@Test
	public void s() {
		SqlSession sqlSession = null;
		List<provider> list = new ArrayList<provider>();
		Integer[] id = { 1, 3 };
		try {
			sqlSession = MybatisUtil.creSqlSession();
			list = sqlSession.getMapper(providerMapper.class).select2(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closesqlSession(sqlSession);
		}
		for (provider u : list) {
			System.out.println(u);
		}
	}

	// 使用chiise查询供应商
	@Test
	public void ifselect() {
		SqlSession sqlSession = null;
		List<provider> list = new ArrayList<provider>();
		String proCode = "2";
		String proName = "";
		String proContact = "";
		Date creationDate = null;
		try {
			sqlSession = MybatisUtil.creSqlSession();
			list = sqlSession.getMapper(providerMapper.class).ifselect(proCode, proName, proContact, creationDate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closesqlSession(sqlSession);
		}
		for (provider u : list) {
			System.out.println(u);
		}
	}

	// 订单供应商分页
	@Test
	public void select3() {
		SqlSession sqlSession = null;
		List<provider> list = new ArrayList<provider>();
		int from = 0;
		int pageSize = 5;
		try {
			sqlSession = MybatisUtil.creSqlSession();
			list = sqlSession.getMapper(providerMapper.class).fenye(from, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closesqlSession(sqlSession);
		}
		for (provider u : list) {
			System.out.println(u);
		}
	}

	@Test
	public void ss() {
		SqlSession sqlSession = null;
		List<Bill> list2 = new ArrayList<Bill>();
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		try {
			sqlSession = MybatisUtil.creSqlSession();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("billCode", "2");
			map.put("billmap", list);
			list2 = sqlSession.getMapper(billMapper.class).select(map);
			MybatisUtil.closesqlSession(sqlSession);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closesqlSession(sqlSession);
		}
		for (Bill u : list2) {
			System.out.println(u);
		}
	}
}
