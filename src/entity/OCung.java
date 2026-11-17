package entity;
public class OCung extends LinhKien {
    private int dungLuong;
    private String loaiOCung;

    public OCung() {}

    public OCung(String maSP, String tenSP, String hangSX, double giaNhap, double giaBan, int soLuongTon, int baoHanh, String loaiLinhKien, String thongSoKyThuat, int dungLuong, String loaiOCung) {
        super(maSP, tenSP, hangSX, giaNhap, giaBan, soLuongTon, baoHanh, loaiLinhKien, thongSoKyThuat);
        this.dungLuong = dungLuong;
        this.loaiOCung = loaiOCung;
    }

    public int getDungLuong() { return dungLuong; }
    public void setDungLuong(int dungLuong) { this.dungLuong = dungLuong; }
    public String getLoaiOCung() { return loaiOCung; }
    public void setLoaiOCung(String loaiOCung) { this.loaiOCung = loaiOCung; }

    @Override
    public double tinhGiaBan() {
        return giaBan;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("=== O Cung ===");
        System.out.println("Ổ Cứng: " + tenSP + " | Loại: " + loaiOCung + " | Dung lượng: " + dungLuong + "GB | Giá: " + giaBan);
    }
}
