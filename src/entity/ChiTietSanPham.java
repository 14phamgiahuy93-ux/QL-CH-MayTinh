package entity;
public class ChiTietSanPham extends SanPham {

    private String soSeri;
    private String maSPChiTiet;
    private String trangThai;

    // Constructor mac dinh
    public ChiTietSanPham() {
        super();
    }

    // Constructor co tham so
    public ChiTietSanPham(String maSP, String tenSP, String hangSX,
                          double giaNhap, double giaBan,
                          int soLuongTon, int baoHanh,
                          String soSeri, String maSPChiTiet,
                          String trangThai) {

        super(maSP, tenSP, hangSX, giaNhap, giaBan,
              soLuongTon, baoHanh);

        setSoSeri(soSeri);
        setMaSPChiTiet(maSPChiTiet);
        setTrangThai(trangThai);
    }

    // Getter va Setter so seri
    public String getSoSeri() {
        return soSeri;
    }

    public void setSoSeri(String soSeri) {
        this.soSeri = soSeri;
    }

    // Getter va Setter ma san pham chi tiet
    public String getMaSPChiTiet() {
        return maSPChiTiet;
    }

    public void setMaSPChiTiet(String maSPChiTiet) {
        this.maSPChiTiet = maSPChiTiet;
    }

    // Getter va Setter trang thai
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Ghi de phuong thuc tinh gia ban
    @Override
    public double tinhGiaBan() {
        return getGiaBan();
    }

    // Ghi de phuong thuc hien thi thong tin
    @Override
    public void hienThiThongTin() {
        System.out.println("=== CHI TIET SAN PHAM ===");
        System.out.println("Ma SP: " + getMaSP());
        System.out.println("Ten SP: " + getTenSP());
        System.out.println("Hang SX: " + getHangSX());
        System.out.println("Gia nhap: " + getGiaNhap());
        System.out.println("Gia ban: " + tinhGiaBan());
        System.out.println("So luong ton: " + getSoLuongTon());
        System.out.println("Bao hanh: " + getBaoHanh());
        System.out.println("So seri: " + soSeri);
        System.out.println("Ma SP chi tiet: " + maSPChiTiet);
        System.out.println("Trang thai: " + trangThai);
    }
}
