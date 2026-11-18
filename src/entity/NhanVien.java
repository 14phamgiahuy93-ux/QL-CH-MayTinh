package entity;

public abstract class NhanVien extends Nguoi{

    protected String maNV;
    protected double luongCoBan;
    protected double phuCap;
    protected double tienThuong;


    public NhanVien(){
        super();
    }

    public NhanVien(String hoTen, String ngaySinh, String soDienThoai, String email, String maNV, double luongCoBan, double phuCap, double tienThuong){
        super(hoTen, ngaySinh, soDienThoai, email);
        this.maNV = maNV;
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
        this.tienThuong = tienThuong;
    }


    public String getMaNV(){
        return maNV;
    }
    public void setMaNV(String maNV){
        this.maNV = maNV;
    }

    public double getLuongCoBan(){
        return luongCoBan;
    }
    public void setLuongCoBan(double luongCoBan){
        this.luongCoBan = luongCoBan;
    }

    public double getPhuCap(){
        return phuCap; 
    }
    public void setPhuCap(double phuCap){
        this.phuCap = phuCap;
    }

    public double getTienThuong(){
        return tienThuong;
    }
    public void setTienThuong(double tienThuong){
        this.tienThuong = tienThuong;
    }

    public double tinhLuong(){
        return luongCoBan + phuCap + tienThuong;
    }

    @Override 
    public void hienThiThongTin(){
        System.out.println("Họ tên: "+getHoTen());
        System.out.println("Ngày sinh: "+getNgaySinh());
        System.out.println("Số điện thoại"+getSoDienThoai());
        System.out.println("Email: "+getEmail());
        System.out.println("Mã nhân viên: "+maNV);
        System.out.println("Lương cơ bản: "+luongCoBan+"đ");
        System.out.println("Phụ cấp: "+phuCap+"đ");
        System.out.println("Tiền thưởng: "+tienThuong+"đ");
        System.out.println("Tổng tiền lương: "+tinhLuong()+"đ");
    }

}