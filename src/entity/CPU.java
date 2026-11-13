package entity;

public class CPU extends LinhKien{
    private String socket;
    private int soNhan;
    private double xungNhip;

    // Constructor
    public CPU(){
        super();
    }

    public CPU(String maSP, String tenSP, String hangSX, double giaNhap, double giaBan, int soLuongTon, int baoHanh, String loaiLinhKien, String thongSoKyThuat, String socket, int soNhan, double xungNhip){
        super(maSP, tenSP, hangSX, giaNhap, giaBan, soLuongTon, baoHanh, loaiLinhKien, thongSoKyThuat);

        this.socket = socket;
        this.soNhan = soNhan;
        this.xungNhip = xungNhip;
    }

    // Getter va Setter
    public String getSocket(){
        return socket;
    }

    public void setSocket(String socket){
        this.socket = socket;
    }

    public int getSoNhan(){
        return soNhan;
    }

    public void setSoNhan(int soNhan){
        this.soNhan = soNhan;
    }

    public double getXungNhip(){
        return xungNhip;
    }

    public void setXungNhip(double xungNhip){
        this.xungNhip = xungNhip;
    }

    // Ghi de phuong thuc
    @Override
    public double tinhGiaBan(){
        return getGiaBan();
    }

    @Override
    public void hienThiThongTin(){
        System.out.println("=== CPU ===");
        System.out.println("Ma SP: " + getMaSP());
        System.out.println("Ten SP: " + getTenSP());
        System.out.println("Hang SX: " + getHangSX());
        System.out.println("Gia nhap: " + getGiaNhap());
        System.out.println("Gia ban: " + tinhGiaBan());
        System.out.println("So luong ton: " + getSoLuongTon());
        System.out.println("Bao hanh: " + getBaoHanh());
        System.out.println("Loai linh kien: " + getLoaiLinhKien());
        System.out.println("Thong so: " + getThongSoKyThuat());
        System.out.println("Socket: " + socket);
        System.out.println("So nhan: " + soNhan);
        System.out.println("Xung nhip: " + xungNhip + " GHz");
    }
}
