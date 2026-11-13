package entity;
public abstract class LinhKien extends SanPham{
    protected String loaiLinhKien;
    protected String thongSoKyThuat;

    // Constructor
    public LinhKien(){
        super();
    }

    public LinhKien(String maSP, String tenSP, String hangSX, double giaNhap, double giaBan, int soLuongTon, int baoHanh, String loaiLinhKien, String thongSoKyThuat){
        super(maSP, tenSP, hangSX, giaNhap,
              giaBan, soLuongTon, baoHanh);

        this.loaiLinhKien = loaiLinhKien;
        this.thongSoKyThuat = thongSoKyThuat;
    }

    // Getter va Setter
    public String getLoaiLinhKien(){
        return loaiLinhKien;
    }

    public void setLoaiLinhKien(String loaiLinhKien){
        this.loaiLinhKien = loaiLinhKien;
    }

    public String getThongSoKyThuat(){
        return thongSoKyThuat;
    }

    public void setThongSoKyThuat(String thongSoKyThuat){
        this.thongSoKyThuat = thongSoKyThuat;
    }

    // Khong can ghi de cac phuong thuc abstract
    // Vi LinhKien van la lop truu tuong
}
