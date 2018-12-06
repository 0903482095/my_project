package com.nguyentrihoang.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.nguyentrihoang.Imp.ChiTietHoaDonImp;
import com.nguyentrihoang.entity.ChiTietHoaDon;
import com.nguyentrihoang.entity.ChiTietHoaDonId;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ChiTietHoaDonDao implements ChiTietHoaDonImp{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		Session session=sessionFactory.getCurrentSession();
		ChiTietHoaDonId id=(ChiTietHoaDonId) session.save(chiTietHoaDon);
		if(null!= id) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
