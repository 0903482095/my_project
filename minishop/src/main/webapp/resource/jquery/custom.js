$(document).ready(function(){
	var masanpham;
	$("#btnDangNhap").click(function(){
		
		var email = $("#email").val();
		var password=$("#matkhau").val();	
				
		$.ajax({
			url:"/api/KiemTraDangNhap",
			type:"GET",			
			data:{
				email:email,
				matkhau:password
			},	
			success:function(value){
				if(value=="true"){
					duongdanhientai=window.location.href;
					duongdan=duongdanhientai.replace("http://localhost:8080/dangnhap","http://localhost:8080/");
					window.location=duongdan;
				}
				else {
					$("#ketqua").text("dang nhap that bai !");
				}
			}
		})
	});
	
	$("#dangnhap").click(function(){
		$(this).addClass("actived");
		$("#dangki").removeClass("actived");
		$(".container-login-form").show();
		$(".container-signup-form").css("display","none");
		
	});
	$("#dangki").click(function(){
		
		$(this).addClass("actived");
		$("#dangnhap").removeClass("actived");
		$(".container-login-form").hide();
		$(".container-signup-form").show();
	});
	
	$(".btn-giohang").click(function(){
		var machitiet=$(this).attr("data-machitiet");
		var mamau=$(this).closest("tr").find(".mau").attr("data-mamau");
		var masize=$(this).closest("tr").find(".size").attr("data-masize");
		var tenmau=$(this).closest("tr").find(".mau").text();
		var tensize=$(this).closest("tr").find(".size").text();
		var tensp=$("#tensp").text();
		var masp=$("#tensp").attr("data-masp");
		var giatien=$("#giatien").attr("data-value");
		var soluong=$(this).closest("tr").find(".soluong").text();
		$.ajax({
			url:"/api/ThemGioHang",
			type:"GET",			
			data:{
				masp:masp,
				masize:masize,
				mamau:mamau,
				tensp:tensp,
				giatien:giatien,
				tenmau:tenmau,
				tensize:tensize,
				soluong:soluong,
				machitiet:machitiet
			},	
			success:function(value){
				$("#giohang").find("div").addClass("circle-giohang");
				$("#giohang").find("div").html("<span>"+value+"</span>")
			}
		})
	});
	GanTongTienGioHang();
	function GanTongTienGioHang(isEventChange){
		var tongtiensanpham=0;
		$(".giatien").each(function(){
			var giatien=$(this).attr("data-value");
			var soluong=$(this).closest("tr").find(".soluong-giohang").val();
			var tongtien=soluong * parseInt(giatien);
			
			var format=parseFloat(tongtien).toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
			
			
			
			if(!isEventChange){
				$(this).html(format);
			}
			tongtiensanpham=tongtiensanpham+tongtien;
			var formattongtien=tongtiensanpham.toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();	
			$("#tongtien").html(formattongtien);
			
		})
		
		
	}
			
	$(".soluong-giohang").change(function(){
		var soluong=$(this).val();
		var giatien=$(this).closest("tr").find(".giatien").attr("data-value");
		var tongtien=soluong * parseInt(giatien);
		var format=tongtien.toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
		$(this).closest("tr").find(".giatien").html(format);
		GanTongTienGioHang(true);
		
		var mamau=$(this).closest("tr").find(".mau").attr("data-mamau");
		var masize=$(this).closest("tr").find(".size").attr("data-masize");
		var masp=$(this).closest("tr").find(".tensp").attr("data-masp");
		$.ajax({
			url:"/api/CapNhatGioHang",
			type:"GET",
			data:{
				masp:masp,
				masize:masize,
				mamau:mamau,
				soluong:soluong,
				tongtien:format
			},
			success:function(value){
				
			}
		})
		
	})
	
	$(".xoa-giohang").click(function(){
		var self=$(this);
		var mamau=$(this).closest("tr").find(".mau").attr("data-mamau");
		var masize=$(this).closest("tr").find(".size").attr("data-masize");
		var masp=$(this).closest("tr").find(".tensp").attr("data-masp");
		$.ajax({
			url:"/api/XoaGioHang",
			type:"GET",
			data:{
				masp:masp,
				masize:masize,
				mamau:mamau,
				
			},
			success:function(value){
				self.closest("tr").remove();
				GanTongTienGioHang(true);
				$("#giohang").find("div").html("<span>"+value+"</span>");
			}
		})
	})
	$("body").on("click",".paging-item",function(){
		$(".paging-item").removeClass("active");
		$(this).addClass("active");
		var sotrang = $(this).text();
		var spbatdau = (sotrang - 1) * 5;
		$.ajax({
			url:"/api/LaySanPhamLimit",
			type:"GET",
			data:{
				spbatdau:spbatdau,
			},
			success:function(value){
				var tbodysanpham = $("#table-sanpham").find("tbody");
				tbodysanpham.empty();
				tbodysanpham.append(value);
			}
		})
	})
	$("#checkall").change(function(){
		if(this.checked){
			$("#table-sanpham input").each(function(){
				$(this).attr("checked",true);
			})
		}
		else{
			$("#table-sanpham input").each(function(){
				$(this).attr("checked",false);
			})
		}
	})
	$("#xoa-sanpham").click(function(){
		$("#table-sanpham > tbody input:checked").each(function(){
			var masanpham=$(this).val();
			var This=$(this);
			
			$.ajax({
				url:"/api/XoaSanPham",
				type:"GET",
				data:{
					masanpham:masanpham,
				},
				success:function(){
					This.closest("tr").remove();
				}
			})
		})
		
	})
	
	var files=[];
	var tenhinh="";
	$("#hinhanh").change(function(event){
		files = event.target.files;
		tenhinh= files[0].name;
		forms = new FormData();
		forms.append("file",files[0]);
		
		$.ajax({
			url : "/api/UploadFile",
			type: "POST",
			data:forms,
			contentType:false,
			processData:false,
			enctype:"multipart/form-data",
			success:function(){
				
			}
		})
	})
	
	$("body").on("click",".btn-chitiet",function(){
		$(this).remove();
		var chitietclone = $("#chitietsanpham").clone().removeAttr("id");
		$("#containerchitietsanpham").append(chitietclone);
	})
	
	$("#btnthemsanpham").click(function(event){
		event.preventDefault();
		var formdata=$("#form-sanpham").serializeArray();
		json={};
		arrayChitiet=[];
		$.each(formdata,function(i,field){
			json[field.name]=field.value;
	
		});
		
		
		$("#containerchitietsanpham > .chitietsanpham").each(function(){
			objectChitiet={};
			var mausanpham=$(this).find("#mausanpham").val();
			var sizesanpham=$(this).find("#sizesanpham").val();
			var soluong=$(this).find("#soluong").val();
			
			objectChitiet["mausanpham"]=mausanpham;
			objectChitiet["sizesanpham"]=sizesanpham;
			objectChitiet["soluong"]=soluong;
			
			arrayChitiet.push(objectChitiet);
		})
		
		json["chitietsanpham"]=arrayChitiet;
		json["hinhsanpham"]=tenhinh;
		
		console.log(json);
		
		$.ajax({
			url : "/api/ThemSanPham",
			type : "POST",
			data:{
				dataJson : JSON.stringify(json)
			},
			success : function(value){
				
			}
		})
	})
	$("body").on("click","#sua-sanpham",function(){
		masanpham=$(this).attr("data-masanpham");
		$("#btnthemsanpham").addClass("hidden");
		$("#btnupdatesanpham").removeClass("hidden");
		$("#btnthoat").removeClass("hidden");
		$.ajax({
			url:"/minishop/api/LaySanPhamTheoMa",
			type:"GET",
			data:{
				masanpham:masanpham,
			},
			success:function(value){
				$("#tensanpham").val(value.tensanpham);
				$("#giatien").val(value.giatien);
				
				if(value.gianhcho==="nam"){
					$("#nam").attr("checked",true);
				}
				else{
					$("#nu").attr("checked",true);
				}
				$("#danhmucsanpham").val(value.danhmucsanpham.madanhmuc);
				$("#mota").val(value.mota);
				var sochitiet=value.chitietsanpham.length;
				$("#containerchitietsanpham").html("");
				for(i=0;i<sochitiet;i++){
					var chitietclone=$("#chitietsanpham").clone().removeAttr("id");
					
					if(i<sochitiet-1){
						chitietclone.find(".btn-chitiet").remove();
					}
					
					chitietclone.find("#mausanpham").val(value.chitietsanpham[i].mausanpham.mamau);
					chitietclone.find("#sizesanpham").val(value.chitietsanpham[i].sizesanpham.masize);
					chitietclone.find("#soluong").val(value.chitietsanpham[i].soluong);
					$("#containerchitietsanpham").append(chitietclone);
				}
				hinhsanpham=value.hinhsanpham;
			}
		})
		
	})
	$("#btnupdatesanpham").click(function(event){
		event.preventDefault();
		var formdata=$("#form-sanpham").serializeArray();
		json={};
		arrayChitiet=[];
		$.each(formdata,function(i,field){
			json[field.name]=field.value;
	
		});
		
		
		$("#containerchitietsanpham > .chitietsanpham").each(function(){
			objectChitiet={};
			var mausanpham=$(this).find("#mausanpham").val();
			var sizesanpham=$(this).find("#sizesanpham").val();
			var soluong=$(this).find("#soluong").val();
			
			objectChitiet["mausanpham"]=mausanpham;
			objectChitiet["sizesanpham"]=sizesanpham;
			objectChitiet["soluong"]=soluong;
			
			arrayChitiet.push(objectChitiet);
		})
		
		json["masanpham"]=masanpham;
		json["chitietsanpham"]=arrayChitiet;
		json["hinhsanpham"]=hinhsanpham;
		
		console.log(json);
		
		$.ajax({
			url : "/api/SuaSanPham",
			type : "POST",
			data:{
				dataJson : JSON.stringify(json)
			},
			success : function(value){
				
			}
		})
	})
});
