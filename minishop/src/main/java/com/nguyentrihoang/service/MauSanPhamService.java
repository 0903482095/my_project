package com.nguyentrihoang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentrihoang.Imp.MauSanPhamImp;
import com.nguyentrihoang.dao.MauSanPhamDao;
import com.nguyentrihoang.entity.MauSanPham;
@Service
public class MauSanPhamService implements MauSanPhamImp{

	@Autowired
	MauSanPhamDao mauSanPhamDao;
	
	@Override
	public List<MauSanPham> LayDanhSachMau() {
		
		return mauSanPhamDao.LayDanhSachMau();
	}

}
