package com.nguyentrihoang.Imp;

import com.nguyentrihoang.entity.NhanVien;

public interface NhanVienImp {
	public boolean kiemTraDangNhap(String email,String matkhau);
	public boolean themNhanVien(NhanVien nhanvien,String email);
}
