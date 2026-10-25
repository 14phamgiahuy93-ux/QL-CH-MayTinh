package entity;

public class MayTinh extends SanPham{
    private String loaiMay;
    private String CPU;
    private int RAM;
    private int dungLuongSSD;

    // Constructor
    public MayTinh() {
        super();
    }

    public MayTinh(String maSP, String tenSP, String hangSX, double giaNhap, double giaBan, int soLuongTon, int baoHanh, String loaiMay, String CPU, int RAM, int dungLuongSSD) {
        super(maSP, tenSP, hangSX, giaNhap, giaBan, soLuongTon, baoHanh);

        this.loaiMay = loaiMay;
        this.CPU = CPU;
        this.RAM = RAM;
        this.dungLuongSSD = dungLuongSSD;
    }

    // Getter va Setter
    public String getLoaiMay(){
        return loaiMay;
    }

    public void setLoaiMay(String loaiMay){
        this.loaiMay = loaiMay;
    }

    public String getCPU(){
        return CPU;
    }

    public void setCPU(String CPU){
        this.CPU = CPU;
    }

    public int getRAM(){
        return RAM;
    }

    public void setRAM(int RAM){
        this.RAM = RAM;
    }

    public int getDungLuongSSD(){
        return dungLuongSSD;
    }

    public void setDungLuongSSD(int dungLuongSSD){
        this.dungLuongSSD = dungLuongSSD;
    }

    // Ghi de phuong thuc
    @Override
    public double tinhGiaBan(){
        return getGiaBan();
    }

    @Override
    public void hienThiThongTin(){
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
