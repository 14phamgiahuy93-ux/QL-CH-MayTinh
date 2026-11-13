package entity;
public class ChiTietHoaDon {
    private SanPham sanPham;
    private int soLuong;
    private double donGia;

    // Constructor
    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(SanPham sanPham, int soLuong, double donGia) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
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

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    // Tính thành tiền
    public double tinhThanhTien() {
        return soLuong * donGia;
    }
}
