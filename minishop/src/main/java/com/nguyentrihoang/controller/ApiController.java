package com.nguyentrihoang.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyentrihoang.entity.ChiTietSanPham;
import com.nguyentrihoang.entity.DanhMucSanPham;
import com.nguyentrihoang.entity.GioHang;
import com.nguyentrihoang.entity.JSON_SanPham;
import com.nguyentrihoang.entity.MauSanPham;
import com.nguyentrihoang.entity.SanPham;
import com.nguyentrihoang.entity.SizeSanPham;
import com.nguyentrihoang.service.NhanVienService;
import com.nguyentrihoang.service.SanPhamService;

@Controller
@RequestMapping("/api")
@SessionAttributes({ "email", "giohang" })
public class ApiController {
	@Autowired
	NhanVienService nhanVienService;

	@Autowired
	SanPhamService sanPhamService;

	@GetMapping("KiemTraDangNhap")
	@ResponseBody
	public String KiemTraDangNhap(@RequestParam String email, @RequestParam String matkhau, ModelMap modelMap) {
		boolean ktra = nhanVienService.kiemTraDangNhap(email, matkhau);

		modelMap.addAttribute("email", email);

		return "" + ktra;
	}

	List<GioHang> gioHangs = new ArrayList<>();

	@GetMapping("/CapNhatGioHang")
	@ResponseBody
	public void CapNhatGioHang(HttpSession httpSession, @RequestParam int soluong, @RequestParam int masp,
			@RequestParam int mamau, @RequestParam int masize, @RequestParam String tongtien) {
		if (null != httpSession.getAttribute("giohang")) {
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = KiemTraSanPhamDaTonTaiGioHang(listGioHangs, httpSession, masp, masize, mamau);
			listGioHangs.get(vitri).setSoluong(soluong);
			listGioHangs.get(vitri).setGiatien(tongtien);
		}
	}

	@GetMapping("/XoaGioHang")
	@ResponseBody
	public String XoaGioHang(HttpSession httpSession, @RequestParam int masp, @RequestParam int mamau,
			@RequestParam int masize) {
		if (null != httpSession.getAttribute("giohang")) {
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = KiemTraSanPhamDaTonTaiGioHang(listGioHangs, httpSession, masp, masize, mamau);
			listGioHangs.remove(vitri);
			return listGioHangs.size() + "";
		}
		return null;

	}

	@GetMapping("/ThemGioHang")
	@ResponseBody
	public String ThemGioHang(@RequestParam int masp, @RequestParam int mamau, @RequestParam int masize,
			@RequestParam String tensp, @RequestParam String tenmau, @RequestParam String tensize,
			@RequestParam String giatien, @RequestParam int soluong, @RequestParam int machitiet,
			HttpSession httpSession) {
		if (null == httpSession.getAttribute("giohang")) {

			GioHang gioHang = new GioHang();
			gioHang.setMasp(masp);
			gioHang.setMamau(mamau);
			gioHang.setMasize(masize);
			gioHang.setTensp(tensp);
			gioHang.setTenmau(tenmau);
			gioHang.setTensize(tensize);
			gioHang.setGiatien(giatien);
			gioHang.setSoluong(1);
			gioHang.setMachitiet(machitiet);

			gioHangs.add(gioHang);

			httpSession.setAttribute("giohang", gioHangs);
			return gioHangs.size() + "";

		} else {
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = KiemTraSanPhamDaTonTaiGioHang(listGioHangs, httpSession, masp, masize, mamau);
			if (vitri == -1) {
				GioHang gioHang = new GioHang();
				gioHang.setMasp(masp);
				gioHang.setMamau(mamau);
				gioHang.setMasize(masize);
				gioHang.setTensp(tensp);
				gioHang.setTenmau(tenmau);
				gioHang.setTensize(tensize);
				gioHang.setGiatien(giatien);
				gioHang.setSoluong(1);
				gioHang.setMachitiet(machitiet);

				listGioHangs.add(gioHang);
			} else {
				int soluongmoi = listGioHangs.get(vitri).getSoluong() + 1;
				listGioHangs.get(vitri).setSoluong(soluongmoi);
			}
			return listGioHangs.size() + "";
		}

	}

	private int KiemTraSanPhamDaTonTaiGioHang(List<GioHang> ListGioHangs, HttpSession httpSession, int masp, int masize,
			int mamau) {
		for (int i = 0; i < ListGioHangs.size(); i++) {
			if (ListGioHangs.get(i).getMasp() == masp && ListGioHangs.get(i).getMamau() == mamau
					&& ListGioHangs.get(i).getMasize() == masize) {
				return i;
			}
		}
		return -1;
	}

	@GetMapping(path = "/LaySanPhamLimit", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String LaySanPhamLimit(@RequestParam int spbatdau) {
		String html = "";
		List<SanPham> listSanPhams = sanPhamService.LayDanhSachSanPhamLimit(spbatdau);
		for (SanPham sanPham : listSanPhams) {
			html += "<tr>";
			html += "<td><div class='checkbox'><label><input class='checkboxsanpham' type='checkbox' value='"
					+ sanPham.getMasanpham() + "'/></label></div></td>";
			html += "<td class='tensp'>" + sanPham.getTensanpham() + "</td>";
			html += "<td class='giatien'>" + sanPham.getGiatien() + "</td>";
			html += "<td class='gianhcho'>" + sanPham.getGianhcho() + "</td>";
			html += "<td class='btn btn-info' id='sua-sanpham' data-masanpham='" + sanPham.getMasanpham()
					+ "'>Sửa</td>";
			html += "</tr>";
		}

		return html;
	}

	@GetMapping("/XoaSanPham")
	@ResponseBody
	public void XoaSanPhamTheoMaSanPham(@RequestParam int masanpham) {
		sanPhamService.XoaSanPhamTheoMaSanPham(masanpham);
	}

	@Autowired
	ServletContext context;

	@PostMapping("UploadFile")
	@ResponseBody
	public void UploadFile(MultipartHttpServletRequest request) {
		String path_save_file = context.getRealPath("/resource/Image/sanpham");
		Iterator<String> listNames = request.getFileNames();
		MultipartFile mpf = request.getFile(listNames.next());

		File file_save = new File(path_save_file + mpf.getOriginalFilename());
		try {
			mpf.transferTo(file_save);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@PostMapping("ThemSanPham")
	@ResponseBody
	public void ThemSanPham(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonObject;
		try {
			jsonObject = objectMapper.readTree(dataJson);

			JsonNode jsonChitiet = jsonObject.get("chitietsanpham");
			Set<ChiTietSanPham> listChiTiet = new HashSet<ChiTietSanPham>();
			for (JsonNode objectChitiet : jsonChitiet) {
				ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

				MauSanPham mauSanPham = new MauSanPham();
				mauSanPham.setMamau(objectChitiet.get("mausanpham").asInt());

				SizeSanPham sizeSanPham = new SizeSanPham();
				sizeSanPham.setMasize(objectChitiet.get("sizesanpham").asInt());

				chiTietSanPham.setMausanpham(mauSanPham);
				chiTietSanPham.setSizesanpham(sizeSanPham);
				chiTietSanPham.setSoluong(objectChitiet.get("soluong").asInt());

				listChiTiet.add(chiTietSanPham);
			}

			DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
			danhMucSanPham.setMadanhmuc(jsonObject.get("danhmucsanpham").asInt());

			SanPham sanPham = new SanPham();

			sanPham.setTensanpham(jsonObject.get("tensanpham").asText());
			sanPham.setGiatien(jsonObject.get("giatien").asText());
			sanPham.setGianhcho(jsonObject.get("gianhcho").asText());
			sanPham.setMota(jsonObject.get("mota").asText());
			sanPham.setHinhsanpham(jsonObject.get("hinhsanpham").asText());
			sanPham.setChitietsanpham(listChiTiet);
			sanPham.setDanhmucsanpham(danhMucSanPham);

			sanPhamService.ThemSanPham(sanPham);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@GetMapping(path = "/LaySanPhamTheoMa", produces = "application/json ; charset=utf-8")
	@ResponseBody
	public JSON_SanPham LaySanPhamTheoMa(@RequestParam int masanpham) {
		SanPham sanPham = new SanPham();
		sanPham = sanPhamService.LayDanhSachChiTietSanPhamTheoMa(masanpham);
		JSON_SanPham json_SanPham = new JSON_SanPham();
		json_SanPham.setMasanpham(sanPham.getMasanpham());
		json_SanPham.setTensanpham(sanPham.getTensanpham());
		json_SanPham.setGianhcho(sanPham.getGianhcho());
		json_SanPham.setGiatien(sanPham.getGiatien());
		json_SanPham.setMota(sanPham.getMota());
		json_SanPham.setHinhsanpham(sanPham.getHinhsanpham());

		DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
		danhMucSanPham.setMadanhmuc(sanPham.getDanhmucsanpham().getMadanhmuc());
		danhMucSanPham.setTendanhmuc(sanPham.getDanhmucsanpham().getTendanhmuc());

		json_SanPham.setDanhmucsanpham(danhMucSanPham);

		Set<ChiTietSanPham> chiTietSanPhams = new HashSet<ChiTietSanPham>();
		for (ChiTietSanPham value : sanPham.getChitietsanpham()) {

			ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
			chiTietSanPham.setMachitietsanpham(value.getMachitietsanpham());

			MauSanPham mauSanPham = new MauSanPham();
			mauSanPham.setMamau(value.getMausanpham().getMamau());
			mauSanPham.setTenmau(value.getMausanpham().getTenmau());
			chiTietSanPham.setMausanpham(mauSanPham);

			SizeSanPham sizeSanPham = new SizeSanPham();
			sizeSanPham.setMasize(value.getSizesanpham().getMasize());
			sizeSanPham.setSize(value.getSizesanpham().getSize());
			chiTietSanPham.setSizesanpham(sizeSanPham);

			chiTietSanPham.setSoluong(value.getSoluong());

			chiTietSanPhams.add(chiTietSanPham);
		}
		json_SanPham.setChitietsanpham(chiTietSanPhams);

		return json_SanPham;
	}

	@PostMapping("SuaSanPham")
	@ResponseBody
	public void SuaSanPham(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonObject;
		try {
			jsonObject = objectMapper.readTree(dataJson);

			JsonNode jsonChitiet = jsonObject.get("chitietsanpham");
			Set<ChiTietSanPham> listChiTiet = new HashSet<ChiTietSanPham>();
			for (JsonNode objectChitiet : jsonChitiet) {
				ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

				MauSanPham mauSanPham = new MauSanPham();
				mauSanPham.setMamau(objectChitiet.get("mausanpham").asInt());

				SizeSanPham sizeSanPham = new SizeSanPham();
				sizeSanPham.setMasize(objectChitiet.get("sizesanpham").asInt());

				chiTietSanPham.setMausanpham(mauSanPham);
				chiTietSanPham.setSizesanpham(sizeSanPham);
				chiTietSanPham.setSoluong(objectChitiet.get("soluong").asInt());

				listChiTiet.add(chiTietSanPham);
			}

			DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
			danhMucSanPham.setMadanhmuc(jsonObject.get("danhmucsanpham").asInt());

			SanPham sanPham = new SanPham();

			sanPham.setTensanpham(jsonObject.get("tensanpham").asText());
			sanPham.setGiatien(jsonObject.get("giatien").asText());
			sanPham.setGianhcho(jsonObject.get("gianhcho").asText());
			sanPham.setMota(jsonObject.get("mota").asText());
			sanPham.setHinhsanpham(jsonObject.get("hinhsanpham").asText());
			sanPham.setChitietsanpham(listChiTiet);
			sanPham.setDanhmucsanpham(danhMucSanPham);
			sanPham.setMasanpham(jsonObject.get("masanpham").asInt());

			sanPhamService.SuaSanPham(sanPham);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
