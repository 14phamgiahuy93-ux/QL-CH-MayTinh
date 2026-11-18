package entity;
public class ChiTietPhieuNhap {

    private SanPham sanPham;
    private int soLuong;
    private double donGiaNhap;

    // Constructor mac dinh
    public ChiTietPhieuNhap() {
    }

    // Constructor co tham so
    public ChiTietPhieuNhap(SanPham sanPham, int soLuong, double donGiaNhap) {
        this.sanPham = sanPham;
        setSoLuong(soLuong);
        setDonGiaNhap(donGiaNhap);
    }

    // Tinh thanh tien
    public double tinhThanhTien() {
        return soLuong * donGiaNhap;
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
        } else {
            System.out.println("So luong phai lon hon 0!");
        }
    }

    public double getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(double donGiaNhap) {
        if (donGiaNhap >= 0 && Double.isFinite(donGiaNhap)) {
            this.donGiaNhap = donGiaNhap;
        } else {
            System.out.println("Don gia nhap khong hop le!");
        }
    }

    // Hien thi chi tiet phieu nhap
    public void hienThi() {

        String tenSP = sanPham != null
                ? sanPham.getTenSP() : "N/A";

        System.out.printf(
            "  - San pham: %s | So luong: %d | Don gia: %.2f | Thanh tien: %.2f%n",
            tenSP,
            soLuong,
            donGiaNhap,
            tinhThanhTien()
        );
    }
}
