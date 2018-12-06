package com.nguyentrihoang.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyentrihoang.entity.ChiTietHoaDon;
import com.nguyentrihoang.entity.ChiTietHoaDonId;
import com.nguyentrihoang.entity.GioHang;
import com.nguyentrihoang.entity.HoaDon;
import com.nguyentrihoang.service.ChiTietHoaDonService;
import com.nguyentrihoang.service.HoaDonService;

@Controller
@RequestMapping("/giohang")
public class GioHangController {
	
	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	ChiTietHoaDonService chiTietHoaDonService;
	
	@GetMapping
	public String Default(HttpSession httpSession,ModelMap modelMap) {
		if(null!=httpSession.getAttribute("giohang")) {
			List<GioHang> gioHangs=(List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluongsanphamgiohang",gioHangs.size());
			modelMap.addAttribute("giohang",gioHangs);
			
		}
		return "giohang";
	}
	
	@PostMapping
	public String ThemHoaDon(HttpSession httpSession, @RequestParam String tenkhachhang, @RequestParam String sodt, @RequestParam String diachigiaohang, @RequestParam String hinhthucgiaohang, @RequestParam String ghichu) {
		if(null!= httpSession.getAttribute("giohang")){
			List<GioHang> giohangs=(List<GioHang>) httpSession.getAttribute("giohang");
			
			HoaDon hoaDon=new HoaDon();
			
			hoaDon.setTenkhachhang(tenkhachhang);
			hoaDon.setSodt(sodt);
			hoaDon.setDiachigiaohang(diachigiaohang);
			hoaDon.setHinhthucgiaohang(hinhthucgiaohang);
			hoaDon.setGhichu(ghichu);
			
			int idhoadon=hoaDonService.ThemHoaDon(hoaDon);
			if(idhoadon>0) {
				Set<ChiTietHoaDon> listChiTietHoaDons=new HashSet<ChiTietHoaDon>();
				for(GioHang gioHang: giohangs) {
					ChiTietHoaDonId chiTietHoaDonId=new ChiTietHoaDonId();
					chiTietHoaDonId.setMachitietsanpham(gioHang.getMachitiet());
					chiTietHoaDonId.setMahoadon(hoaDon.getMahoadon());
					
					ChiTietHoaDon chiTietHoaDon=new ChiTietHoaDon();
					chiTietHoaDon.setChiTietHoaDonId(chiTietHoaDonId);
					chiTietHoaDon.setGiatien(gioHang.getGiatien());
					chiTietHoaDon.setSoluong(gioHang.getSoluong());
					
					chiTietHoaDonService.ThemChiTietHoaDon(chiTietHoaDon);
				}
				
			}
			else {
				
			}
			
			
		}
		return "giohang";
	}
}
