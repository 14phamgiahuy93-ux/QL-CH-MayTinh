package entity;

public class ChiTietPhieuNhap {
    private SanPham sanPham;
    private int soLuong;
    private double donGiaNhap;

    // Constructor mặc định
    public ChiTietPhieuNhap() {
    }

    // Constructor có tham số
    public ChiTietPhieuNhap(SanPham sanPham, int soLuong, double donGiaNhap) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donGiaNhap = donGiaNhap;
    }

    // Phương thức tính thành tiền
    public double tinhThanhTien() {
        return this.soLuong * this.donGiaNhap;
    }

    // Getter và Setter
    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(double donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

    public void hienThi() {
        String tenSP = (sanPham != null) ? sanPham.getTenSP() : "N/A";
        System.out.printf("  - Sản phẩm: %s | Số lượng: %d | Đơn giá: %.2f | Thành tiền: %.2f\n", 
                tenSP, soLuong, donGiaNhap, tinhThanhTien());
    }
}