package entity;

import java.util.Arrays;

public class PhieuNhap {
    private String maPN;
    private String ngayNhap;
    private NhaCungCap nhaCungCap;
    private NhanVien nhanVien;
    private ChiTietPhieuNhap[] danhSachChiTiet;
    private int soLuongChiTiet;

    // Constructor mặc định
    public PhieuNhap() {
        this.danhSachChiTiet = new ChiTietPhieuNhap[10]; // Khởi tạo mảng ban đầu dung lượng 10
        this.soLuongChiTiet = 0;
    }

    // Constructor có tham số
    public PhieuNhap(String maPN, String ngayNhap, NhaCungCap nhaCungCap, NhanVien nhanVien) {
        this.maPN = maPN;
        this.ngayNhap = ngayNhap;
        this.nhaCungCap = nhaCungCap;
        this.nhanVien = nhanVien;
        this.danhSachChiTiet = new ChiTietPhieuNhap[10];
        this.soLuongChiTiet = 0;
    }

    // Phương thức thêm chi tiết phiếu nhập (tự động mở rộng mảng thủ công)
    public void themChiTiet(ChiTietPhieuNhap ct) {
        if (soLuongChiTiet >= danhSachChiTiet.length) {
            danhSachChiTiet = Arrays.copyOf(danhSachChiTiet, danhSachChiTiet.length * 2);
        }
        danhSachChiTiet[soLuongChiTiet++] = ct;
    }

    // Phương thức tính tổng tiền nhập của phiếu
    public double tinhTongTienNhap() {
        double tongTien = 0;
        for (int i = 0; i < soLuongChiTiet; i++) {
            tongTien += danhSachChiTiet[i].tinhThanhTien();
        }
        return tongTien;
    }

    // Phương thức hiển thị thông tin phiếu nhập
    public void hienThi() {
        System.out.println("--------------------------------------------------");
        System.out.println("Mã phiếu nhập: " + maPN + " | Ngày nhập: " + ngayNhap);
        String tenNCC = (nhaCungCap != null) ? nhaCungCap.getTenNCC() : "N/A";
        String tenNV = (nhanVien != null) ? nhanVien.getHoTen() : "N/A";
        System.out.println("Nhà cung cấp: " + tenNCC + " | Nhân viên lập: " + tenNV);
        System.out.println("Danh sách chi tiết sản phẩm nhập:");
        for (int i = 0; i < soLuongChiTiet; i++) {
            danhSachChiTiet[i].hienThi();
        }
        System.out.printf("=> Tổng tiền nhập: %.2f\n", tinhTongTienNhap());
        System.out.println("--------------------------------------------------");
    }

    // Getters và Setters
    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ChiTietPhieuNhap[] getDanhSachChiTiet() {
        return danhSachChiTiet;
    }

    public int getSoLuongChiTiet() {
        return soLuongChiTiet;
    }
}