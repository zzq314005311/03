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

	// ʹ��if��̬��ѯ������Ϣ
	@Test
	public void if1() {

		SqlSession sqlSession = null;
		List<Bill> list = new ArrayList<Bill>();
		String productName = "��";
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
			System.out.println("����id" + u.getId() + "��������" + u.getBillCode() + "��Ʒ����" + u.getProductName() + "��Ӧ��id"
					+ u.getProviderId() + "��Ӧ������" + u.getProName() + "�������" + u.getTotalPrice() + "�Ƿ񸶿�"
					+ u.getIsPayment() + "����ʱ��" + u.getCreationDate());
		}
	}

	// ʹ��if-where��ѯ������Ϣ
	@Test
	public void ifelse() {
		SqlSession sqlSession = null;
		List<provider> list = new ArrayList<provider>();
		String proCode = "";
		String proName = "��";
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

	// ʹ��if-set�޸�����
	@Test
	public void update() {
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MybatisUtil.creSqlSession();
			Bill bill = new Bill();
			bill.setBillCode("12345");
			bill.setProductName("����2");
			// bill.setProductDesc("����2");
			// bill.setProductUnit("����2");
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

	// ʹ��if-trimʵ���޸�����
	@Test
	public void update1() {

		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MybatisUtil.creSqlSession();
			Bill bill = new Bill();
			bill.setBillCode("222");
			bill.setProductName("����");
			// bill.setProductDesc("����2");
			// bill.setProductUnit("����2");
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

	// ʹ��foreadch��ȡ��Ӧ������Ķ���
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

	// ʹ��chiise��ѯ��Ӧ��
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

	// ������Ӧ�̷�ҳ
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
