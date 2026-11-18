package entity;
public class QuanLy extends NhanVien{
    private double phuCapChucVu;

    public QuanLy(){
        super();
    }
    public QuanLy(String hoTen, String ngaySinh, String soDienThoai, String email, String maNV, double luongCoBan, double phuCap, double tienThuong, double phuCapChucVu){
        super(hoTen, ngaySinh, soDienThoai, email, maNV, luongCoBan, phuCap, tienThuong);
        this.phuCapChucVu = phuCapChucVu;
    }
    public double getPhuCapChucVu(){
        return phuCapChucVu;
    }
    public void setPhuCapChucVu(double phuCapChucVu){
        this.phuCapChucVu = phuCapChucVu;
    }

    @Override 
    public double tinhLuong(){
        return super.tinhLuong() + phuCapChucVu;
    }

    @Override 
    public void hienThiThongTin(){
        super.hienThiThongTin();
        System.out.println("Phụ cấp chức vụ: "+phuCapChucVu+"đ");
        System.out.println("Tổng tiền lương: "+tinhLuong());
    }
}