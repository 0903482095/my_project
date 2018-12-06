package com.nguyentrihoang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyentrihoang.Imp.XuatHoaDonImp;
import com.nguyentrihoang.entity.HoaDon;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class XuatHoaDonDao implements XuatHoaDonImp{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<HoaDon> XuatHoaDon() {
		Session session=sessionFactory.getCurrentSession();
		List<HoaDon> listHoaDon=session.createQuery("from HOADON").getResultList();
		return listHoaDon;
	}

}
