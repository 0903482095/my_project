package com.nguyentrihoang.Imp;

import java.util.List;

import com.nguyentrihoang.entity.SanPham;

public interface SanPhamImp {
    List<SanPham> LayDanhSachSanPhamLimit(int spbatdau);
    SanPham LayDanhSachChiTietSanPhamTheoMa(int masp);
    List<SanPham> LayDanhSachSanPhamTheoMaDanhMuc(int madanhmuc);
    void XoaSanPhamTheoMaSanPham(int masanpham);
    void ThemSanPham(SanPham sanPham);
    void SuaSanPham(SanPham sanPham);
}
