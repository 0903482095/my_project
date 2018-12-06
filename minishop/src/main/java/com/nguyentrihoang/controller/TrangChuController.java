package com.nguyentrihoang.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nguyentrihoang.entity.DanhMucSanPham;
import com.nguyentrihoang.entity.SanPham;
import com.nguyentrihoang.service.DanhMucService;
import com.nguyentrihoang.service.SanPhamService;



@Controller
@RequestMapping("/")
public class TrangChuController {
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	DanhMucService danhMucService;
	
	@GetMapping
	@Transactional
	public String Default(ModelMap modelMap, HttpSession httpSession) {
		
		List<DanhMucSanPham> danhMucSanPhams=danhMucService.LayDanhMuc();
		modelMap.addAttribute("danhmuc", danhMucSanPhams);
		
		
		if(httpSession.getAttribute("email") != null) {
			String dangnhap=(String) httpSession.getAttribute("email");
			modelMap.addAttribute("as", dangnhap.substring(0, 1));
		}
		List<SanPham> listSanPham=sanPhamService.LayDanhSachSanPhamLimit(0);
		modelMap.addAttribute("listSanPham", listSanPham);
		return "trangchu";  
	}
	
	
}
