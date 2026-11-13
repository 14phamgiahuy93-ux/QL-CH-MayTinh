package entity;

public class RAM extends LinhKien {
    private int dungLuong;
    private String loaiRAM;
    private int tocDoBus;

    // Constructor
    public RAM(){
        super();
    }

    public RAM(String maSP, String tenSP, String hangSX, double giaNhap, double giaBan, int soLuongTon, int baoHanh, String loaiLinhKien, String thongSoKyThuat, int dungLuong, String loaiRAM, int tocDoBus){
        super(maSP, tenSP, hangSX, giaNhap, giaBan, soLuongTon, baoHanh, loaiLinhKien, thongSoKyThuat);

        this.dungLuong = dungLuong;
        this.loaiRAM = loaiRAM;
        this.tocDoBus = tocDoBus;
    }

    // Getter va Setter
    public int getDungLuong(){
        return dungLuong;
    }

    public void setDungLuong(int dungLuong){
        this.dungLuong = dungLuong;
    }

    public String getLoaiRAM(){
        return loaiRAM;
    }

    public void setLoaiRAM(String loaiRAM){
        this.loaiRAM = loaiRAM;
    }

    public int getTocDoBus(){
        return tocDoBus;
    }

    public void setTocDoBus(int tocDoBus){
        this.tocDoBus = tocDoBus;
    }

    // Ghi de phuong thuc
    @Override
    public double tinhGiaBan(){
        return getGiaBan();
    }

    @Override
    public void hienThiThongTin(){
        System.out.println("=== RAM ===");
        System.out.println("Ma SP: " + getMaSP());
        System.out.println("Ten SP: " + getTenSP());
        System.out.println("Hang SX: " + getHangSX());
        System.out.println("Gia nhap: " + getGiaNhap());
        System.out.println("Gia ban: " + tinhGiaBan());
        System.out.println("So luong ton: " + getSoLuongTon());
        System.out.println("Bao hanh: " + getBaoHanh());
        System.out.println("Loai linh kien: " + getLoaiLinhKien());
        System.out.println("Thong so: " + getThongSoKyThuat());
        System.out.println("Dung luong: " + dungLuong + " GB");
        System.out.println("Loai RAM: " + loaiRAM);
        System.out.println("Toc do Bus: " + tocDoBus + " MHz");
    }
}
