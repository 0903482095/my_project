package com.nguyentrihoang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentrihoang.Imp.SizeSanPhamImp;
import com.nguyentrihoang.dao.SizeSanPhamDao;
import com.nguyentrihoang.entity.SizeSanPham;
@Service
public class SizeSanPhamService implements SizeSanPhamImp{
	
	@Autowired
	SizeSanPhamDao sizeSanPhamDao;

	@Override
	public List<SizeSanPham> LayDanhSachSize() {
		
		return sizeSanPhamDao.LayDanhSachSize();
	}

}
