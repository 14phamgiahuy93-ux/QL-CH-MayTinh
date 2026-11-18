
package entity;

public class NhaCungCap {

    private String maNCC;
    private String tenNCC;
    private String soDienThoai;
    private String diaChi;
    private String email;

    // Constructor mac dinh
    public NhaCungCap() {
    }

    // Constructor co tham so
    public NhaCungCap(String maNCC, String tenNCC,
                      String soDienThoai, String diaChi, String email) {

        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
    }

    // Getters va Setters
    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Hien thi thong tin nha cung cap
    public void hienThiThongTin() {

        System.out.println("-----------------------------------");
        System.out.println("Ma NCC: " + maNCC);
        System.out.println("Ten NCC: " + tenNCC);
        System.out.println("So dien thoai: " + soDienThoai);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Email: " + email);
        System.out.println("-----------------------------------");
    }
}
