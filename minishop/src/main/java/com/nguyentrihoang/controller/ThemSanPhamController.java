package com.nguyentrihoang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nguyentrihoang.entity.DanhMucSanPham;
import com.nguyentrihoang.entity.MauSanPham;
import com.nguyentrihoang.entity.SanPham;
import com.nguyentrihoang.entity.SizeSanPham;
import com.nguyentrihoang.service.DanhMucService;
import com.nguyentrihoang.service.MauSanPhamService;
import com.nguyentrihoang.service.SanPhamService;
import com.nguyentrihoang.service.SizeSanPhamService;

@Controller
@RequestMapping("/themsanpham")
public class ThemSanPhamController {
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	DanhMucService danhMucService;
	
	@Autowired
	MauSanPhamService mauSanPhamService;
	
	@Autowired
	SizeSanPhamService sizeSanPhamService;
	
	@GetMapping
	public String Default(ModelMap modelMap) {
		List<SanPham> listSanPhams = sanPhamService.LayDanhSachSanPhamLimit(0);
		List<SanPham> allSanPham = sanPhamService.LayDanhSachSanPhamLimit(-1);
		Math.ceil(allSanPham.size()/5);
		double tongsopage=Math.ceil((double)allSanPham.size()/5);
		List<DanhMucSanPham> danhMucSanPhams=danhMucService.LayDanhMuc();
		List<MauSanPham> listMauSanPham=mauSanPhamService.LayDanhSachMau();
		List<SizeSanPham> listSizeSanPham=sizeSanPhamService.LayDanhSachSize();
		
		modelMap.addAttribute("listSanPhams", listSanPhams);
		modelMap.addAttribute("allSanPham", allSanPham);
		modelMap.addAttribute("tongsopage", tongsopage);
		modelMap.addAttribute("danhmuc", danhMucSanPhams);
		modelMap.addAttribute("listMauSanPham",listMauSanPham);
		modelMap.addAttribute("listSizeSanPham", listSizeSanPham);
		
		
		return "themsanpham";
	}
}
