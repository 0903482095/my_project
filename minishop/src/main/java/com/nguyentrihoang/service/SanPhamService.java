package com.nguyentrihoang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentrihoang.Imp.SanPhamImp;
import com.nguyentrihoang.dao.SanPhamDao;
import com.nguyentrihoang.entity.SanPham;

@Service
public class SanPhamService implements SanPhamImp{
	@Autowired
	SanPhamDao sanPhamDao;

	@Override
	public List<SanPham> LayDanhSachSanPhamLimit(int spbatdau) {
		
		return sanPhamDao.LayDanhSachSanPhamLimit(spbatdau);
	}

	@Override
	public SanPham LayDanhSachChiTietSanPhamTheoMa(int masp) {
		
		return sanPhamDao.LayDanhSachChiTietSanPhamTheoMa(masp);
	}

	@Override
	public List<SanPham> LayDanhSachSanPhamTheoMaDanhMuc(int madanhmuc) {
		
		return sanPhamDao.LayDanhSachSanPhamTheoMaDanhMuc(madanhmuc);
	}

	@Override
	public void XoaSanPhamTheoMaSanPham(int masanpham) {
		sanPhamDao.XoaSanPhamTheoMaSanPham(masanpham);
		
	}

	@Override
	public void ThemSanPham(SanPham sanPham) {
		sanPhamDao.ThemSanPham(sanPham);
		
	}

	@Override
	public void SuaSanPham(SanPham sanPham) {
		sanPhamDao.SuaSanPham(sanPham);
		
	}

}
