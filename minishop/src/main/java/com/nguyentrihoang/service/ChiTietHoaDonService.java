package com.nguyentrihoang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentrihoang.Imp.ChiTietHoaDonImp;
import com.nguyentrihoang.dao.ChiTietHoaDonDao;
import com.nguyentrihoang.entity.ChiTietHoaDon;

@Service
public class ChiTietHoaDonService implements ChiTietHoaDonImp{
	
	@Autowired
	ChiTietHoaDonDao chiTietHoaDonDao;
	
	@Override
	public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		
		return chiTietHoaDonDao.ThemChiTietHoaDon(chiTietHoaDon);
	}

}
