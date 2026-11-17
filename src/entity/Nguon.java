package entity;
public class Nguon extends LinhKien {

    private int congSuat;
    private String chungNhan;

    // Constructor mac dinh
    public Nguon() {
        super();
    }

    // Constructor co tham so
    public Nguon(String maSP, String tenSP, String hangSX,
                 double giaBan, int soLuongTon,
                 int congSuat, String chungNhan) {

        super(maSP, tenSP, hangSX, 0, giaBan,
              soLuongTon, 12, "Nguồn", "");

        setCongSuat(congSuat);
        setChungNhan(chungNhan);
    }

    // Getter va Setter congSuat
    public int getCongSuat() {
        return congSuat;
    }

    public void setCongSuat(int congSuat) {
        if (congSuat >= 0) {
            this.congSuat = congSuat;
        }
    }

    // Getter va Setter chungNhan
    public String getChungNhan() {
        return chungNhan;
    }

    public void setChungNhan(String chungNhan) {
        this.chungNhan = chungNhan;
    }

    // Tinh gia ban
    @Override
    public double tinhGiaBan() {
        return giaBan;
    }

    // Hien thi thong tin
    @Override
    public void hienThiThongTin() {

        System.out.println("===== THONG TIN NGUON =====");

        System.out.println("Ma SP: " + maSP);
        System.out.println("Ten SP: " + tenSP);
        System.out.println("Hang SX: " + hangSX);
        System.out.println("Gia nhap: " + giaNhap);
        System.out.println("Gia ban: " + giaBan);
        System.out.println("So luong ton: " + soLuongTon);
        System.out.println("Bao hanh: " + baoHanh);
        System.out.println("Loai linh kien: " + loaiLinhKien);
        System.out.println("Thong so ky thuat: " + thongSoKyThuat);
        System.out.println("Cong suat: " + congSuat + "W");
        System.out.println("Chung nhan: " + chungNhan);
    }
}
