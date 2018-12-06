package com.nguyentrihoang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyentrihoang.Imp.MauSanPhamImp;
import com.nguyentrihoang.entity.MauSanPham;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MauSanPhamDao implements MauSanPhamImp{
	
	@Autowired
	SessionFactory SessionFactory;

	@Override
	@Transactional
	public List<MauSanPham> LayDanhSachMau() {
		Session session=SessionFactory.getCurrentSession();
		List<MauSanPham> listMauSanPham=session.createQuery("from MAUSANPHAM").getResultList();
		return listMauSanPham;
	}

}
