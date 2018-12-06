package com.nguyentrihoang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentrihoang.Imp.HoaDonImp;
import com.nguyentrihoang.dao.HoaDonDao;
import com.nguyentrihoang.entity.HoaDon;

@Service
public class HoaDonService implements HoaDonImp{

	@Autowired
	HoaDonDao hoaDonDao;

	@Override
	public int ThemHoaDon(HoaDon hoaDon) {
		return hoaDonDao.ThemHoaDon(hoaDon);
	}
	
	
	
}
