package com.nguyentrihoang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyentrihoang.Imp.DanhMucImp;
import com.nguyentrihoang.entity.DanhMucSanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class DanhMucDao implements DanhMucImp{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<DanhMucSanPham> LayDanhMuc() {
		Session session=sessionFactory.getCurrentSession();
		List<DanhMucSanPham> danhMucSanPhams=session.createQuery("from DANHMUCSANPHAM").getResultList();
		return danhMucSanPhams;
	}

}
