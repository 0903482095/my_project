package com.nguyentrihoang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyentrihoang.Imp.SizeSanPhamImp;
import com.nguyentrihoang.entity.SizeSanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SizeSanPhamDao implements SizeSanPhamImp{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<SizeSanPham> LayDanhSachSize() {
		Session session=sessionFactory.getCurrentSession();
		List<SizeSanPham> listSizeSanPham=session.createQuery("from SIZESANPHAM").getResultList();
		return listSizeSanPham;
	}

}
