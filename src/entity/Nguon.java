package entity;
public class Nguon extends LinhKien {
    private int congSuat;
    private String chungNhan;

    public Nguon() {}

    public Nguon(String maSP, String tenSP, String hangSX, double giaBan, int soLuongTon, int congSuat, String chungNhan) {
        super(maSP, tenSP, hangSX, 0, giaBan, soLuongTon, 12, "Nguồn", "");
        this.congSuat = congSuat;
        this.chungNhan = chungNhan;
    }

    @Override
    public double tinhGiaBan() {
        return giaBan;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Nguồn: " + tenSP + " | Công suất: " + congSuat + "W | Chứng nhận: " + chungNhan + " | Giá: " + giaBan);
    }
}
