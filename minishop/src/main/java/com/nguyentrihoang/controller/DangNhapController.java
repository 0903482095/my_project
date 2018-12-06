package com.nguyentrihoang.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nguyentrihoang.entity.NhanVien;
import com.nguyentrihoang.service.NhanVienService;

@Controller
@SessionAttributes("email")
@RequestMapping("/dangnhap")
public class DangNhapController {
	
	@Autowired 
	NhanVienService nhanVienService;
	
	@GetMapping
	public String Default() {
		return "dangnhap";
	}
	
	@PostMapping
	public String Dangky(@RequestParam String email, @RequestParam String matkhau, @RequestParam String nhaplaimatkhau,ModelMap modelMap) {
		boolean ktra=validate(email);
		if(ktra) {
			if(matkhau.equals(nhaplaimatkhau)) {
				NhanVien nhanVien=new NhanVien();
				nhanVien.setEmail(email);
				nhanVien.setTendangnhap(email);
				nhanVien.setMatkhau(matkhau);
				Boolean ktthem=nhanVienService.themNhanVien(nhanVien,email);
				if(ktthem) {
					modelMap.addAttribute("ktradangnhap", "Tao tai khoan thanh cong !");
				}
				else {
					modelMap.addAttribute("ktradangnhap", "Tao tai khoan that bai !");
				}
			}
			else {
				modelMap.addAttribute("ktradangnhap", "Mat khau khong trung khop !");
			}
		}
		else {
			modelMap.addAttribute("ktradangnhap", "Vui long nhap lai email !");
		}
		return "dangnhap";
	}
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	        return matcher.find();
	}
}
