package com.nguyentrihoang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentrihoang.Imp.XuatHoaDonImp;
import com.nguyentrihoang.dao.XuatHoaDonDao;
import com.nguyentrihoang.entity.HoaDon;
@Service
public class XuatHoaDonService implements XuatHoaDonImp{
	@Autowired
	XuatHoaDonDao xuatHoaDonDao;

	@Override
	public List<HoaDon> XuatHoaDon() {
		return xuatHoaDonDao.XuatHoaDon();
	}

}
