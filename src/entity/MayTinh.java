package entity;
public class MayTinh extends SanPham {
    private String loaiMay;
    private String CPU;
    private int RAM;
    private int dungLuongSSD;

    // Constructor mac dinh
    public MayTinh() {
        super();
    }

    // Constructor co tham so
    public MayTinh(String maSP, String tenSP, String hangSX,
                   double giaNhap, double giaBan,
                   int soLuongTon, int baoHanh,
                   String loaiMay, String CPU,
                   int RAM, int dungLuongSSD) {

        super(maSP, tenSP, hangSX, giaNhap, giaBan,
              soLuongTon, baoHanh);

        setLoaiMay(loaiMay);
        setCPU(CPU);
        setRAM(RAM);
        setDungLuongSSD(dungLuongSSD);
    }

    // Getter va Setter loai may
    public String getLoaiMay() {
        return loaiMay;
    }

    public void setLoaiMay(String loaiMay) {
        this.loaiMay = loaiMay;
    }

    // Getter va Setter CPU
    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    // Getter va Setter RAM
    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        if (RAM >= 0) {
            this.RAM = RAM;
        } else {
            System.out.println("RAM khong duoc am!");
        }
    }

    // Getter va Setter SSD
    public int getDungLuongSSD() {
        return dungLuongSSD;
    }

    public void setDungLuongSSD(int dungLuongSSD) {
        if (dungLuongSSD >= 0) {
            this.dungLuongSSD = dungLuongSSD;
        } else {
            System.out.println("Dung luong SSD khong duoc am!");
        }
    }

    // Ghi de phuong thuc tinh gia ban
    @Override
    public double tinhGiaBan() {
        return getGiaBan();
    }

    // Ghi de phuong thuc hien thi thong tin
    @Override
    public void hienThiThongTin() {
        System.out.println("=== MAY TINH ===");
        System.out.println("Ma SP: " + getMaSP());
        System.out.println("Ten SP: " + getTenSP());
        System.out.println("Hang SX: " + getHangSX());
        System.out.println("Gia nhap: " + getGiaNhap());
        System.out.println("Gia ban: " + tinhGiaBan());
        System.out.println("So luong ton: " + getSoLuongTon());
        System.out.println("Bao hanh: " + getBaoHanh());
        System.out.println("Loai may: " + loaiMay);
        System.out.println("CPU: " + CPU);
        System.out.println("RAM: " + RAM + " GB");
        System.out.println("SSD: " + dungLuongSSD + " GB");
    }
}
