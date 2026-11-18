package entity;
import java.util.Arrays;
public class PhieuNhap {

    private String maPN;
    private String ngayNhap;
    private NhaCungCap nhaCungCap;
    private NhanVien nhanVien;
    private ChiTietPhieuNhap[] danhSachChiTiet;
    private int soLuongChiTiet;

    // Constructor mac dinh
    public PhieuNhap() {
        danhSachChiTiet = new ChiTietPhieuNhap[10];
        soLuongChiTiet = 0;
    }

    // Constructor co tham so
    public PhieuNhap(String maPN, String ngayNhap,
                     NhaCungCap nhaCungCap, NhanVien nhanVien) {

        this.maPN = maPN;
        this.ngayNhap = ngayNhap;
        this.nhaCungCap = nhaCungCap;
        this.nhanVien = nhanVien;

        danhSachChiTiet = new ChiTietPhieuNhap[10];
        soLuongChiTiet = 0;
    }

    // Them chi tiet phieu nhap
    public void themChiTiet(ChiTietPhieuNhap ct) {

        if (ct == null || ct.getSanPham() == null
                || ct.getSoLuong() <= 0
                || ct.getDonGiaNhap() < 0) {

            System.out.println("Chi tiet phieu nhap khong hop le!");
            return;
        }

        // Tu dong mo rong mang khi day
        if (soLuongChiTiet >= danhSachChiTiet.length) {

            danhSachChiTiet = Arrays.copyOf(
                danhSachChiTiet,
                danhSachChiTiet.length * 2
            );
        }

        danhSachChiTiet[soLuongChiTiet++] = ct;

        System.out.println("Them chi tiet phieu nhap thanh cong!");
    }

    // Tinh tong tien nhap
    public double tinhTongTienNhap() {

        double tongTien = 0;

        for (int i = 0; i < soLuongChiTiet; i++) {

            if (danhSachChiTiet[i] != null) {
                tongTien += danhSachChiTiet[i].tinhThanhTien();
            }
        }

        return tongTien;
    }

    // Hien thi thong tin phieu nhap
    public void hienThi() {

        System.out.println("--------------------------------------------------");

        System.out.println("Ma phieu nhap: " + maPN);
        System.out.println("Ngay nhap: " + ngayNhap);

        String tenNCC = nhaCungCap != null
                ? nhaCungCap.getTenNCC() : "N/A";

        String tenNV = nhanVien != null
                ? nhanVien.getHoTen() : "N/A";

        System.out.println("Nha cung cap: " + tenNCC);
        System.out.println("Nhan vien lap: " + tenNV);

        System.out.println("Danh sach chi tiet san pham nhap:");

        if (soLuongChiTiet == 0) {
            System.out.println("Phieu nhap chua co chi tiet!");
        }

        for (int i = 0; i < soLuongChiTiet; i++) {

            if (danhSachChiTiet[i] != null) {
                danhSachChiTiet[i].hienThi();
            }
        }

        System.out.printf(
            "Tong tien nhap: %.2f VND%n",
            tinhTongTienNhap()
        );

        System.out.println("--------------------------------------------------");
    }

    // Getters va Setters
    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ChiTietPhieuNhap[] getDanhSachChiTiet() {
        return danhSachChiTiet;
    }

    public void setDanhSachChiTiet(ChiTietPhieuNhap[] danhSachChiTiet) {

        if (danhSachChiTiet == null) {
            this.danhSachChiTiet = new ChiTietPhieuNhap[10];
            this.soLuongChiTiet = 0;
            return;
        }

        int dem = 0;

        for (ChiTietPhieuNhap ct : danhSachChiTiet) {
            if (ct != null) {
                dem++;
            }
        }

        this.danhSachChiTiet = new ChiTietPhieuNhap[
            Math.max(10, dem)
        ];

        this.soLuongChiTiet = 0;

        for (ChiTietPhieuNhap ct : danhSachChiTiet) {
            if (ct != null) {
                this.danhSachChiTiet[soLuongChiTiet++] = ct;
            }
        }
    }

    public int getSoLuongChiTiet() {
        return soLuongChiTiet;
    }
}
