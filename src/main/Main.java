package main;
import manager.QuanLySanPham;
import manager.QuanLyNhanVien;
import manager.QuanLyKhachHang;
import manager.QuanLyHoaDon;
import manager.QuanLyPhieuNhap;
import entity.SanPham;
import entity.MayTinh;
import entity.NhanVien;
import entity.QuanLy;
import entity.KhachHang;
import entity.HoaDon;
import entity.ChiTietHoaDon;
import entity.PhieuNhap;
import entity.ChiTietPhieuNhap;
import entity.NhaCungCap;

import utils.Validator;

public class Main {

    // ================= FILE DU LIEU =================

    private static final String FILE_SAN_PHAM = "sanpham.txt";
    private static final String FILE_NHAN_VIEN = "nhanvien.txt";
    private static final String FILE_KHACH_HANG = "khachhang.txt";
    private static final String FILE_HOA_DON = "hoadon.txt";
    private static final String FILE_PHIEU_NHAP = "phieunhap.txt";

    // ================= CAC DOI TUONG QUAN LY =================

    private static QuanLySanPham qlsp;
    private static QuanLyNhanVien qlnv;
    private static QuanLyKhachHang qlkh;
    private static QuanLyHoaDon qlhd;
    private static QuanLyPhieuNhap qlpn;

    // ================= MAIN =================

    public static void main(String[] args) {

        khoiTaoQuanLy();
        docDuLieu();

        int luaChon;

        do {
            System.out.println("\n========== CHUONG TRINH QUAN LY CUA HANG MAY TINH ==========");
            System.out.println("1. Quan ly san pham");
            System.out.println("2. Quan ly nhan vien");
            System.out.println("3. Quan ly khach hang");
            System.out.println("4. Quan ly hoa don");
            System.out.println("5. Quan ly phieu nhap");
            System.out.println("0. Luu du lieu va thoat");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 5);

            switch (luaChon) {
                case 1:
                    menuSanPham();
                    break;

                case 2:
                    menuNhanVien();
                    break;

                case 3:
                    menuKhachHang();
                    break;

                case 4:
                    menuHoaDon();
                    break;

                case 5:
                    menuPhieuNhap();
                    break;

                case 0:
                    ghiDuLieu();
                    System.out.println("Da luu du lieu. Tam biet!");
                    break;
            }

        } while (luaChon != 0);
    }

    // ================= KHOI TAO =================

    private static void khoiTaoQuanLy() {

        qlsp = new QuanLySanPham();
        qlnv = new QuanLyNhanVien();
        qlkh = new QuanLyKhachHang();

        // Hoa don can san pham, nhan vien va khach hang
        qlhd = new QuanLyHoaDon(qlsp, qlnv, qlkh);

        // Phieu nhap can san pham va nhan vien
        qlpn = new QuanLyPhieuNhap(qlsp, qlnv);
    }

    // ================= DOC DU LIEU =================

    private static void docDuLieu() {

        System.out.println("\n========== DOC DU LIEU TU FILE ==========");

        // Phai doc cac du lieu goc truoc
        qlsp.docFile(FILE_SAN_PHAM);
        qlnv.docFile(FILE_NHAN_VIEN);
        qlkh.docFile(FILE_KHACH_HANG);

        // Sau do moi doc hoa don va phieu nhap
        qlhd.docFile(FILE_HOA_DON);
        qlpn.docFile(FILE_PHIEU_NHAP);

        System.out.println("Da doc xong du lieu!");
    }

    // ================= GHI DU LIEU =================

    private static void ghiDuLieu() {

        System.out.println("\n========== LUU DU LIEU VAO FILE ==========");

        qlsp.ghiFile(FILE_SAN_PHAM);
        qlnv.ghiFile(FILE_NHAN_VIEN);
        qlkh.ghiFile(FILE_KHACH_HANG);
        qlhd.ghiFile(FILE_HOA_DON);
        qlpn.ghiFile(FILE_PHIEU_NHAP);

        System.out.println("Da luu xong du lieu!");
    }

    // =========================================================
    //                      SAN PHAM
    // =========================================================

    private static void menuSanPham() {

        int luaChon;

        do {
            System.out.println("\n========== QUAN LY SAN PHAM ==========");
            System.out.println("1. Them may tinh");
            System.out.println("2. Hien thi danh sach san pham");
            System.out.println("3. Tim san pham theo ma");
            System.out.println("4. Sua san pham");
            System.out.println("5. Xoa san pham");
            System.out.println("6. Sap xep san pham theo gia");
            System.out.println("7. Kiem tra ton kho");
            System.out.println("8. Thong ke san pham");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 8);

            switch (luaChon) {

                case 1:
                    themMayTinh();
                    break;

                case 2:
                    qlsp.hienThiDanhSach();
                    break;

                case 3: {
                    String maSP = Validator.readString("Nhap ma san pham: ");
                    qlsp.timSanPham(maSP);
                    break;
                }

                case 4: {
                    String maSP = Validator.readString("Nhap ma san pham can sua: ");
                    qlsp.suaSanPham(maSP);
                    break;
                }

                case 5: {
                    String maSP = Validator.readString("Nhap ma san pham can xoa: ");
                    qlsp.xoaSanPham(maSP);
                    break;
                }

                case 6:
                    qlsp.sapXepTheoGia();
                    qlsp.hienThiDanhSach();
                    break;

                case 7:
                    qlsp.kiemTraTonKho();
                    break;

                case 8:
                    qlsp.thongKeTheoNhieuKhoa();
                    break;
            }

        } while (luaChon != 0);
    }

    private static void themMayTinh() {

        System.out.println("\n========== THEM MAY TINH ==========");

        String maSP = Validator.readString("Nhap ma san pham: ");
        String tenSP = Validator.readString("Nhap ten san pham: ");
        String hangSX = Validator.readString("Nhap hang san xuat: ");

        double giaNhap = Validator.readDouble("Nhap gia nhap: ", 0);
        double giaBan = Validator.readDouble("Nhap gia ban: ", 0);

        int soLuongTon = Validator.readInt("Nhap so luong ton: ", 0, Integer.MAX_VALUE);
        int baoHanh = Validator.readInt("Nhap thoi gian bao hanh (thang): ", 0, 120);

        String loaiMay = Validator.readString("Nhap loai may: ");
        String CPU = Validator.readString("Nhap CPU: ");
        int RAM = Validator.readInt("Nhap dung luong RAM: ", 1, 1024);
        int dungLuongSSD = Validator.readInt("Nhap dung luong SSD: ", 1, 100000);

        MayTinh mt = new MayTinh(
                maSP,
                tenSP,
                hangSX,
                giaNhap,
                giaBan,
                soLuongTon,
                baoHanh,
                loaiMay,
                CPU,
                RAM,
                dungLuongSSD
        );

        qlsp.themSanPham(mt);

        System.out.println("Da them may tinh!");
    }

    // =========================================================
    //                      NHAN VIEN
    // =========================================================

    private static void menuNhanVien() {

        int luaChon;

        do {
            System.out.println("\n========== QUAN LY NHAN VIEN ==========");
            System.out.println("1. Them nhan vien quan ly");
            System.out.println("2. Hien thi danh sach nhan vien");
            System.out.println("3. Tim nhan vien theo ma");
            System.out.println("4. Sua thong tin nhan vien");
            System.out.println("5. Xoa nhan vien");
            System.out.println("6. Thong ke nhan vien");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 6);

            switch (luaChon) {

                case 1:
                    themNhanVienQuanLy();
                    break;

                case 2:
                    qlnv.hienThiDanhSach();
                    break;

                case 3: {
                    String maNV = Validator.readString("Nhap ma nhan vien: ");
                    qlnv.timNhanVien(maNV);
                    break;
                }

                case 4: {
                    String maNV = Validator.readString("Nhap ma nhan vien can sua: ");
                    int luaChonSua = Validator.readInt(
                            "Nhap muc can sua (1-8): ", 1, 8
                    );
                    String giaTriMoi = Validator.readString("Nhap gia tri moi: ");

                    qlnv.suaThongTinNhanVien(maNV, luaChonSua, giaTriMoi);
                    break;
                }

                case 5: {
                    String maNV = Validator.readString("Nhap ma nhan vien can xoa: ");
                    qlnv.xoaNhanVien(maNV);
                    break;
                }

                case 6:
                    qlnv.thongKeTheoLoaiNhanVien();
                    break;
            }

        } while (luaChon != 0);
    }

    private static void themNhanVienQuanLy() {

        System.out.println("\n========== THEM NHAN VIEN QUAN LY ==========");

        String hoTen = Validator.readString("Nhap ho ten: ");
        String ngaySinh = Validator.readString("Nhap ngay sinh: ");
        String soDienThoai = Validator.readString("Nhap so dien thoai: ");
        String email = Validator.readString("Nhap email: ");
        String maNV = Validator.readString("Nhap ma nhan vien: ");

        double luongCoBan = Validator.readDouble("Nhap luong co ban: ", 0);
        double phuCap = Validator.readDouble("Nhap phu cap: ", 0);
        double tienThuong = Validator.readDouble("Nhap tien thuong: ", 0);
        double phuCapChucVu = Validator.readDouble("Nhap phu cap chuc vu: ", 0);

        QuanLy nv = new QuanLy(
                hoTen,
                ngaySinh,
                soDienThoai,
                email,
                maNV,
                luongCoBan,
                phuCap,
                tienThuong,
                phuCapChucVu
        );

        if (qlnv.themNhanVien(nv)) {
            System.out.println("Them nhan vien thanh cong!");
        } else {
            System.out.println("Them nhan vien that bai!");
        }
    }

    // =========================================================
    //                      KHACH HANG
    // =========================================================

    private static void menuKhachHang() {

        int luaChon;

        do {
            System.out.println("\n========== QUAN LY KHACH HANG ==========");
            System.out.println("1. Them khach hang");
            System.out.println("2. Hien thi danh sach khach hang");
            System.out.println("3. Tim khach hang theo ma");
            System.out.println("4. Sua khach hang");
            System.out.println("5. Xoa khach hang");
            System.out.println("6. Thong ke khach hang theo dia chi");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 6);

            switch (luaChon) {

                case 1:
                    themKhachHang();
                    break;

                case 2:
                    qlkh.hienThiDanhSach();
                    break;

                case 3: {
                    String maKH = Validator.readString("Nhap ma khach hang: ");
                    qlkh.timKhachHang(maKH);
                    break;
                }

                case 4: {
                    String maKH = Validator.readString("Nhap ma khach hang can sua: ");
                    qlkh.suaKhachHang(maKH);
                    break;
                }

                case 5: {
                    String maKH = Validator.readString("Nhap ma khach hang can xoa: ");
                    qlkh.xoaKhachHang(maKH);
                    break;
                }

                case 6:
                    qlkh.thongKeTheoDiaChi();
                    break;
            }

        } while (luaChon != 0);
    }

    private static void themKhachHang() {

        System.out.println("\n========== THEM KHACH HANG ==========");

        String hoTen = Validator.readString("Nhap ho ten: ");
        String ngaySinh = Validator.readString("Nhap ngay sinh: ");
        String soDienThoai = Validator.readString("Nhap so dien thoai: ");
        String email = Validator.readString("Nhap email: ");
        String maKH = Validator.readString("Nhap ma khach hang: ");
        String diaChi = Validator.readString("Nhap dia chi: ");

        KhachHang kh = new KhachHang(
                hoTen,
                ngaySinh,
                soDienThoai,
                email,
                maKH,
                diaChi
        );

        qlkh.themKhachHang(kh);

        System.out.println("Da them khach hang!");
    }

    // =========================================================
    //                       HOA DON
    // =========================================================

    private static void menuHoaDon() {

        int luaChon;

        do {
            System.out.println("\n========== QUAN LY HOA DON ==========");
            System.out.println("1. Lap hoa don");
            System.out.println("2. Hien thi danh sach hoa don");
            System.out.println("3. Tim hoa don theo ma");
            System.out.println("4. Xoa hoa don");
            System.out.println("5. Thong ke doanh thu");
            System.out.println("6. Thong ke theo nhan vien");
            System.out.println("7. Thong ke theo khach hang");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 7);

            switch (luaChon) {

                case 1:
                    lapHoaDon();
                    break;

                case 2:
                    qlhd.hienThiDanhSach();
                    break;

                case 3: {
                    String maHD = Validator.readString("Nhap ma hoa don: ");
                    qlhd.timHoaDon(maHD);
                    break;
                }

                case 4: {
                    String maHD = Validator.readString("Nhap ma hoa don can xoa: ");
                    qlhd.xoaHoaDon(maHD);
                    break;
                }

                case 5:
                    qlhd.thongKeDoanhThu();
                    break;

                case 6:
                    qlhd.thongKeTheoNhanVien();
                    break;

                case 7:
                    qlhd.thongKeTheoKhachHang();
                    break;
            }

        } while (luaChon != 0);
    }

    private static void lapHoaDon() {

        System.out.println("\n========== LAP HOA DON ==========");

        String maHD = Validator.readString("Nhap ma hoa don: ");
        String ngayLap = Validator.readString("Nhap ngay lap: ");

        String maNV = Validator.readString("Nhap ma nhan vien lap hoa don: ");
        NhanVien nv = qlnv.layNhanVien(maNV);

        if (nv == null) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }

        String maKH = Validator.readString("Nhap ma khach hang: ");
        KhachHang kh = qlkh.layKhachHang(maKH);

        if (kh == null) {
            System.out.println("Khong tim thay khach hang!");
            return;
        }

        HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh);

        int soChiTiet = Validator.readInt("Nhap so loai san pham trong hoa don: ", 1, 100);

        for (int i = 0; i < soChiTiet; i++) {

            System.out.println("\n--- San pham thu " + (i + 1) + " ---");

            String maSP = Validator.readString("Nhap ma san pham: ");
            SanPham sp = qlsp.laySanPham(maSP);

            if (sp == null) {
                System.out.println("Khong tim thay san pham. Bo qua chi tiet nay.");
                continue;
            }

            int soLuong = Validator.readInt("Nhap so luong mua: ", 1, 100000);

            if (soLuong > sp.getSoLuongTon()) {
                System.out.println("So luong ton kho khong du. Bo qua chi tiet nay.");
                continue;
            }

            double donGia = sp.getGiaBan();

            ChiTietHoaDon ct = new ChiTietHoaDon(sp, soLuong, donGia);
            hd.themChiTiet(ct);
        }

        if (hd.getSoLuongChiTiet() == 0) {
            System.out.println("Hoa don khong co chi tiet hop le!");
            return;
        }

        qlhd.themHoaDon(hd);

        System.out.println("Lap hoa don thanh cong!");
        System.out.println("Tong tien: " + hd.tinhTongTien());
    }

    // =========================================================
    //                      PHIEU NHAP
    // =========================================================

    private static void menuPhieuNhap() {

        int luaChon;

        do {
            System.out.println("\n========== QUAN LY PHIEU NHAP ==========");
            System.out.println("1. Lap phieu nhap");
            System.out.println("2. Hien thi danh sach phieu nhap");
            System.out.println("3. Tim phieu nhap theo ma");
            System.out.println("4. Xoa phieu nhap");
            System.out.println("5. Sua ngay nhap");
            System.out.println("6. Thong ke tien nhap");
            System.out.println("7. Thong ke theo nha cung cap");
            System.out.println("8. Thong ke theo nhan vien");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 8);

            switch (luaChon) {

                case 1:
                    lapPhieuNhap();
                    break;

                case 2:
                    qlpn.hienThiDanhSach();
                    break;

                case 3: {
                    String maPN = Validator.readString("Nhap ma phieu nhap: ");
                    qlpn.timPhieuNhap(maPN);
                    break;
                }

                case 4: {
                    String maPN = Validator.readString("Nhap ma phieu nhap can xoa: ");
                    qlpn.xoaPhieuNhap(maPN);
                    break;
                }

                case 5: {
                    String maPN = Validator.readString("Nhap ma phieu nhap: ");
                    String ngayMoi = Validator.readString("Nhap ngay nhap moi: ");
                    qlpn.suaNgayNhap(maPN, ngayMoi);
                    break;
                }

                case 6:
                    qlpn.thongKeTienNhap();
                    break;

                case 7:
                    qlpn.thongKeTheoNhaCungCap();
                    break;

                case 8:
                    qlpn.thongKeTheoNhanVien();
                    break;
            }

        } while (luaChon != 0);
    }

    private static void lapPhieuNhap() {

        System.out.println("\n========== LAP PHIEU NHAP ==========");

        String maPN = Validator.readString("Nhap ma phieu nhap: ");
        String ngayNhap = Validator.readString("Nhap ngay nhap: ");

        String maNV = Validator.readString("Nhap ma nhan vien lap phieu: ");
        NhanVien nv = qlnv.layNhanVien(maNV);

        if (nv == null) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }

        System.out.println("\n--- THONG TIN NHA CUNG CAP ---");

        String maNCC = Validator.readString("Nhap ma nha cung cap: ");
        String tenNCC = Validator.readString("Nhap ten nha cung cap: ");
        String soDienThoai = Validator.readString("Nhap so dien thoai: ");
        String diaChi = Validator.readString("Nhap dia chi: ");
        String email = Validator.readString("Nhap email: ");

        NhaCungCap ncc = new NhaCungCap(
                maNCC,
                tenNCC,
                soDienThoai,
                diaChi,
                email
        );

        PhieuNhap pn = new PhieuNhap(maPN, ngayNhap, ncc, nv);

        int soChiTiet = Validator.readInt("Nhap so loai san pham nhap: ", 1, 100);

        for (int i = 0; i < soChiTiet; i++) {

            System.out.println("\n--- San pham nhap thu " + (i + 1) + " ---");

            String maSP = Validator.readString("Nhap ma san pham: ");
            SanPham sp = qlsp.laySanPham(maSP);

            if (sp == null) {
                System.out.println("Khong tim thay san pham. Bo qua chi tiet nay.");
                continue;
            }

            int soLuong = Validator.readInt("Nhap so luong nhap: ", 1, 100000);
            double donGiaNhap = Validator.readDouble("Nhap don gia nhap: ", 0);

            ChiTietPhieuNhap ct = new ChiTietPhieuNhap(sp, soLuong, donGiaNhap);
            pn.themChiTiet(ct);
        }

        if (pn.getSoLuongChiTiet() == 0) {
            System.out.println("Phieu nhap khong co chi tiet hop le!");
            return;
        }

        qlpn.themPhieuNhap(pn);

        // Cap nhat ton kho theo phieu nhap vua lap
        qlpn.capNhatTonKhoTheoPhieuNhap(maPN);

        System.out.println("Lap phieu nhap thanh cong!");
        System.out.println("Tong tien nhap: " + pn.tinhTongTienNhap());
    }
}
