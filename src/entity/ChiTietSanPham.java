package entity;
public class ChiTietSanPham extends SanPham {
    private String soSeri;
    private String maSPChiTiet;
    private String trangThai;

    public ChiTietSanPham() {}

    public ChiTietSanPham(String maSP, String tenSP, String hangSX, double giaNhap, double giaBan, int soLuongTon, int baoHanh, String soSeri, String maSPChiTiet, String trangThai) {
        super(maSP, tenSP, hangSX, giaNhap, giaBan, soLuongTon, baoHanh);
        this.soSeri = soSeri;
        this.maSPChiTiet = maSPChiTiet;
        this.trangThai = trangThai;
    }

    public String getSoSeri() { return soSeri; }
    public void setSoSeri(String soSeri) { this.soSeri = soSeri; }
    public String getMaSPChiTiet() { return maSPChiTiet; }
    public void setMaSPChiTiet(String maSPChiTiet) { this.maSPChiTiet = maSPChiTiet; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public double tinhGiaBan() {
        return giaBan;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Chi tiết SP - Seri: " + soSeri + " | Trạng thái: " + trangThai);
    }
}
