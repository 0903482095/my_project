package com.nguyentrihoang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentrihoang.Imp.DanhMucImp;
import com.nguyentrihoang.dao.DanhMucDao;
import com.nguyentrihoang.entity.DanhMucSanPham;

@Service
public class DanhMucService implements DanhMucImp{

	@Autowired
	DanhMucDao danhMucDao;
	
	@Override
	public List<DanhMucSanPham> LayDanhMuc() {
		
		return danhMucDao.LayDanhMuc();
	}

}
