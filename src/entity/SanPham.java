package entity;

public abstract class SanPham{
    protected String maSP;
    protected String tenSP;
    protected String hangSX;
    protected double giaNhap;
    protected double giaBan;
    protected int soLuongTon;
    protected int baoHanh;
    protected static int demSanPham = 0;

    // Constructor
    public SanPham(){
        demSanPham++;
    }

    public SanPham(String maSP, String tenSP, String hangSX, double giaNhap, double giaBan, int soLuongTon, int baoHanh){
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.hangSX = hangSX;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.baoHanh = baoHanh;
        demSanPham++;
    }

    // Getter và Setter
    public String getMaSP(){
        return maSP;
    }

    public void setMaSP(String maSP){
        this.maSP = maSP;
    }

    public String getTenSP(){
        return tenSP;
    }

    public void setTenSP(String tenSP){
        this.tenSP = tenSP;
    }

    public String getHangSX(){
        return hangSX;
    }

    public void setHangSX(String hangSX){
        this.hangSX = hangSX;
    }

    public double getGiaNhap(){
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap){
        this.giaNhap = giaNhap;
    }

    public double getGiaBan(){
        return giaBan;
    }

    public void setGiaBan(double giaBan){
        this.giaBan = giaBan;
    }

    public int getSoLuongTon(){
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon){
        this.soLuongTon = soLuongTon;
    }

    public int getBaoHanh(){
        return baoHanh;
    }

    public void setBaoHanh(int baoHanh){
        this.baoHanh = baoHanh;
    }

    public static int getDemSanPham(){
        return demSanPham;
    }

    public static void setDemSanPham(int soLuong){
        demSanPham = soLuong;
    }

    // Phuong thuc truu tuong
    public abstract void hienThiThongTin();

    public abstract double tinhGiaBan();

    // Phuong thuc static
    public static int demSanPham(){
        return demSanPham;
    }

    // Cap nhat so luong ton
    public void capNhatSoLuong(int soLuong){
        this.soLuongTon = soLuong;
    }

    // Tinh gia tri ton kho
    public double tinhGiaTriTonKho(){
        return giaNhap * soLuongTon;
    }
}
