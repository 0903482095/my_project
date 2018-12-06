package com.nguyentrihoang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentrihoang.Imp.NhanVienImp;
import com.nguyentrihoang.dao.NhanVienDao;
import com.nguyentrihoang.entity.NhanVien;

@Service
public class NhanVienService implements NhanVienImp{
	@Autowired
	NhanVienDao nhanVienDao;
	
	@Override
	public boolean kiemTraDangNhap(String email, String matkhau) {
		boolean ktra= nhanVienDao.kiemTraDangNhap(email, matkhau);
		return ktra;
	}

	@Override
	public boolean themNhanVien(NhanVien nhanvien,String email) {
		boolean ktrathem=nhanVienDao.themNhanVien(nhanvien,email);
		return ktrathem;
	}

}
