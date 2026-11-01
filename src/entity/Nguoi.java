package entity;
public abstract class Nguoi {

    protected String hoTen;
    protected String ngaySinh;
    protected String soDienThoai;
    protected String email;

    public Nguoi(){}

    public Nguoi(String hoTen, String ngaySinh, String soDienThoai, String email){
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public String getHoTen(){
        return hoTen;
    }
    public void setHoTen(String hoTen){
        this.hoTen = hoTen;
    }

    public String getNgaySinh(){
        return ngaySinh;
    }
    public void setNgaySinh(String ngaySinh){
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai(){
        return soDienThoai;
    }
    public void setSoDienThoai(String soDienThoai){
        this.soDienThoai = soDienThoai;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    


    public abstract void hienThiThongTin();


} 