package entity;

public class KhachHang extends Nguoi {

    private String maKH;
    private String diaChi;

    public KhachHang() {
        super();
    }

    public KhachHang(String hoTen, String ngaySinh, String soDienThoai, String email, String maKH, String diaChi) {
        super(hoTen, ngaySinh, soDienThoai, email);
        this.maKH = maKH;
        this.diaChi = diaChi;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Họ tên: " + getHoTen());
        System.out.println("Ngày sinh: " + getNgaySinh());
        System.out.println("Số điện thoại: " + getSoDienThoai());
        System.out.println("Email: " + getEmail());
        System.out.println("Mã khách hàng: " + maKH);
        System.out.println("Địa chỉ: " + diaChi);
    }

}