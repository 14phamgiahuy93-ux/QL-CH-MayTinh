package entity;
public abstract class LinhKien extends SanPham {

    protected String loaiLinhKien;
    protected String thongSoKyThuat;

    // Constructor mac dinh
    public LinhKien() {
        super();
    }

    // Constructor co tham so
    public LinhKien(String maSP, String tenSP, String hangSX,
                    double giaNhap, double giaBan,
                    int soLuongTon, int baoHanh,
                    String loaiLinhKien, String thongSoKyThuat) {

        super(maSP, tenSP, hangSX, giaNhap,
              giaBan, soLuongTon, baoHanh);

        setLoaiLinhKien(loaiLinhKien);
        setThongSoKyThuat(thongSoKyThuat);
    }

    // Getter va Setter loai linh kien
    public String getLoaiLinhKien() {
        return loaiLinhKien;
    }

    public void setLoaiLinhKien(String loaiLinhKien) {
        this.loaiLinhKien = loaiLinhKien;
    }

    // Getter va Setter thong so ky thuat
    public String getThongSoKyThuat() {
        return thongSoKyThuat;
    }

    public void setThongSoKyThuat(String thongSoKyThuat) {
        this.thongSoKyThuat = thongSoKyThuat;
    }
}
