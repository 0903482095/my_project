package com.nguyentrihoang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nguyentrihoang.entity.ChiTietHoaDon;
import com.nguyentrihoang.entity.HoaDon;
import com.nguyentrihoang.service.XuatHoaDonService;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {
	@Autowired
	XuatHoaDonService xuatHoaDonService;
	
	@GetMapping
	public String Default(ModelMap modelMap) {
		List<HoaDon> listHoaDons=xuatHoaDonService.XuatHoaDon();
		modelMap.addAttribute("listhoadon", listHoaDons);
		return "hoadon";
	}
}
