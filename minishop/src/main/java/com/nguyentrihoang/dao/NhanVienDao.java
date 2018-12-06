package com.nguyentrihoang.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyentrihoang.Imp.NhanVienImp;
import com.nguyentrihoang.entity.NhanVien;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class NhanVienDao implements NhanVienImp{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public boolean kiemTraDangNhap(String email, String matkhau) {
		 Session session=sessionFactory.getCurrentSession();
		 try {
			 NhanVien nhanVien=(NhanVien) session.createQuery("from NHANVIEN where email='"+email+"' AND matkhau='"+matkhau+"'").getSingleResult();
			 if(nhanVien!=null) {
				 return true;
			 }
			 else {
				 return false;
			 }
		} catch (Exception e) 	{
		
		}
		 
		 
		return false;
	}

	@Override
	@Transactional
	public boolean themNhanVien(NhanVien nhanvien,String email) {
		Session session=sessionFactory.getCurrentSession();
		try {
			NhanVien nhanvien1=(NhanVien) session.createQuery("from NHANVIEN where email='"+email+"'").getSingleResult();
			
			if(nhanvien1 !=null) {
				return false;
			}
			else {
				
			}
		} catch (Exception e) 	{
			
		}
		int manhanvien=(int) session.save(nhanvien);
		if(manhanvien>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
