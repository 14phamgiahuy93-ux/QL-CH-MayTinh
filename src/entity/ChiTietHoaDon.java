package entity;
public class ChiTietHoaDon {

    private SanPham sanPham;
    private int soLuong;
    private double donGia;

    // Constructor
    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(SanPham sanPham, int soLuong, double donGia) {
        setSanPham(sanPham);
        setSoLuong(soLuong);
        setDonGia(donGia);
    }

    // Getter va Setter
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
        if (soLuong > 0) {
            this.soLuong = soLuong;
        }
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia >= 0) {
            this.donGia = donGia;
        }
    }

    // Tinh thanh tien
    public double tinhThanhTien() {
        return soLuong * donGia;
    }
}
