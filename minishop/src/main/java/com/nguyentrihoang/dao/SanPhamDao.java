package com.nguyentrihoang.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyentrihoang.Imp.SanPhamImp;
import com.nguyentrihoang.entity.ChiTietSanPham;
import com.nguyentrihoang.entity.SanPham;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SanPhamDao implements SanPhamImp{
	
    @Autowired 
    SessionFactory sessionFactory;
    
	@Override
	@Transactional
	public List<SanPham> LayDanhSachSanPhamLimit(int spbatdau) {
		Session session=sessionFactory.getCurrentSession();
		List<SanPham> listSanPhams = new ArrayList<>();
		if(spbatdau<0) {
			listSanPhams=session.createQuery("from SANPHAM").getResultList();
		}
		else {
			listSanPhams=session.createQuery("from SANPHAM").setFirstResult(spbatdau).setMaxResults(5).getResultList();
		}
		
		return listSanPhams;
	}

	@Override
	@Transactional
	public SanPham LayDanhSachChiTietSanPhamTheoMa(int masp) {
		Session session=sessionFactory.getCurrentSession();
		SanPham sanPham=(SanPham) session.createQuery("from SANPHAM sp where sp.masanpham="+masp).getSingleResult();
		return sanPham;
	}

	@Override
	@Transactional
	public List<SanPham> LayDanhSachSanPhamTheoMaDanhMuc(int madanhmuc) {
		Session session=sessionFactory.getCurrentSession();
		String query="from SANPHAM sp where sp.danhmucsanpham.madanhmuc="+madanhmuc;
		List<SanPham> listSanPhams=session.createQuery(query).getResultList();
		return listSanPhams;
	}

	@Override
	@Transactional
	public void XoaSanPhamTheoMaSanPham(int masanpham) {
		Session session=sessionFactory.getCurrentSession();
		SanPham sanPham=session.get(SanPham.class, masanpham);
		
		Set<ChiTietSanPham> chiTietSanPhams=sanPham.getChitietsanpham();
		for (ChiTietSanPham chiTietSanPham : chiTietSanPhams) {
			session.createQuery("delete CHITIETHOADON WHERE machitietsanpham="+chiTietSanPham.getMachitietsanpham()).executeUpdate();
		}
		
		session.createQuery("delete CHITIETSANPHAM WHERE masanpham="+masanpham).executeUpdate();
		session.createQuery("delete SANPHAM WHERE masanpham="+masanpham).executeUpdate();
	}

	@Override
	@Transactional
	public void ThemSanPham(SanPham sanPham) {
		Session session=sessionFactory.getCurrentSession();
		session.save(sanPham);
		
	}

	@Override
	@Transactional
	public void SuaSanPham(SanPham sanPham) {
		Session session=sessionFactory.getCurrentSession();
		session.update(sanPham);
		
	}
	

}
