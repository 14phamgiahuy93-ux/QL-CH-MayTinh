package entity;
public class HoaDon {
    private String maHD;
    private String ngayLap;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private ChiTietHoaDon[] danhSachChiTiet;
    private int soLuongChiTiet;

    // Constructor
    public HoaDon() {
        danhSachChiTiet = new ChiTietHoaDon[100];
        soLuongChiTiet = 0;
    }

    public HoaDon(String maHD, String ngayLap,
                  NhanVien nhanVien, KhachHang khachHang) {

        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;

        danhSachChiTiet = new ChiTietHoaDon[100];
        soLuongChiTiet = 0;
    }

    // Getter và Setter
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public ChiTietHoaDon[] getDanhSachChiTiet() {
        return danhSachChiTiet;
    }

    public void setDanhSachChiTiet(ChiTietHoaDon[] danhSachChiTiet) {
        this.danhSachChiTiet = danhSachChiTiet;
    }

    public int getSoLuongChiTiet() {
        return soLuongChiTiet;
    }

    public void setSoLuongChiTiet(int soLuongChiTiet) {
        this.soLuongChiTiet = soLuongChiTiet;
    }

    // Thêm chi tiết hóa đơn
    public void themChiTiet(ChiTietHoaDon ct) {
        if (ct == null) {
            System.out.println("Chi tiet hoa don khong hop le!");
            return;
        }

        if (soLuongChiTiet >= danhSachChiTiet.length) {
            System.out.println("Danh sach chi tiet da day!");
            return;
        }

        danhSachChiTiet[soLuongChiTiet] = ct;
        soLuongChiTiet++;

        System.out.println("Them chi tiet hoa don thanh cong!");
    }

    // Tính tổng tiền hóa đơn
    public double tinhTongTien() {
        double tongTien = 0;

        for (int i = 0; i < soLuongChiTiet; i++) {
            tongTien += danhSachChiTiet[i].tinhThanhTien();
        }

        return tongTien;
    }

    // Hiển thị hóa đơn
    public void hienThi() {
        System.out.println("========== HOA DON ==========");
        System.out.println("Ma hoa don: " + maHD);
        System.out.println("Ngay lap: " + ngayLap);

        System.out.println("Nhan vien: "
                + (nhanVien != null ? nhanVien.getHoTen() : "Chua co"));

        System.out.println("Khach hang: "
                + (khachHang != null ? khachHang.getHoTen() : "Chua co"));

        System.out.println("Danh sach chi tiet:");

        for (int i = 0; i < soLuongChiTiet; i++) {
            ChiTietHoaDon ct = danhSachChiTiet[i];

            System.out.println("San pham: "
                    + ct.getSanPham().getTenSP());

            System.out.println("So luong: " + ct.getSoLuong());

            System.out.println("Don gia: " + ct.getDonGia());

            System.out.println("Thanh tien: " + ct.tinhThanhTien());
        }

        System.out.println("Tong tien: " + tinhTongTien());
        System.out.println("=============================");
    }
}
