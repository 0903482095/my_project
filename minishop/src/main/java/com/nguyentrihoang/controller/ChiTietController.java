package com.nguyentrihoang.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nguyentrihoang.entity.DanhMucSanPham;
import com.nguyentrihoang.entity.GioHang;
import com.nguyentrihoang.entity.SanPham;
import com.nguyentrihoang.service.DanhMucService;
import com.nguyentrihoang.service.SanPhamService;

@Controller
@RequestMapping("/chitiets")
@SessionAttributes("giohang")
public class ChiTietController {
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	DanhMucService danhMucService;
	
	@GetMapping("/{masanpham}")
	public String Default(@PathVariable int masanpham,ModelMap modelMap,HttpSession httpSession) {
		SanPham sanPham=sanPhamService.LayDanhSachChiTietSanPhamTheoMa(masanpham);
		List<DanhMucSanPham> danhMucSanPhams=danhMucService.LayDanhMuc();
		modelMap.addAttribute("SanPham", sanPham);
		modelMap.addAttribute("danhmuc", danhMucSanPhams);
		
		if(null != httpSession.getAttribute("giohang") ) {
			List<GioHang> listGioHangs=(List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluongsanphamgiohang", listGioHangs.size());
		}
		return "chitiet";
	}
	
}
