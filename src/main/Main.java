package main;

import manager.QuanLySanPham;
import manager.QuanLyNhanVien;
import manager.QuanLyKhachHang;
import manager.QuanLyHoaDon;
import manager.QuanLyPhieuNhap;

import entity.SanPham;
import entity.MayTinh;
import entity.ChiTietSanPham;
import entity.CPU;
import entity.RAM;
import entity.CardDoHoa;
import entity.OCung;
import entity.Nguon;

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

    private static final String FILE_SAN_PHAM = "data/sanpham.txt";
    private static final String FILE_NHAN_VIEN = "data/nhanvien.txt";
    private static final String FILE_KHACH_HANG = "data/khachhang.txt";
    private static final String FILE_HOA_DON = "data/hoadon.txt";
    private static final String FILE_PHIEU_NHAP = "data/phieunhap.txt";

    private static QuanLySanPham qlsp;
    private static QuanLyNhanVien qlnv;
    private static QuanLyKhachHang qlkh;
    private static QuanLyHoaDon qlhd;
    private static QuanLyPhieuNhap qlpn;

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

    // ================= KHOI TAO VA FILE =================

    private static void khoiTaoQuanLy() {
        qlsp = new QuanLySanPham();
        qlnv = new QuanLyNhanVien();
        qlkh = new QuanLyKhachHang();

        qlhd = new QuanLyHoaDon(qlsp, qlnv, qlkh);
        qlpn = new QuanLyPhieuNhap(qlsp, qlnv);
    }

    private static void docDuLieu() {
        System.out.println("\n========== DOC DU LIEU TU FILE ==========");

        qlsp.docFile(FILE_SAN_PHAM);
        qlnv.docFile(FILE_NHAN_VIEN);
        qlkh.docFile(FILE_KHACH_HANG);
        qlhd.docFile(FILE_HOA_DON);
        qlpn.docFile(FILE_PHIEU_NHAP);

        System.out.println("Da doc xong du lieu!");
    }

    private static void ghiDuLieu() {
        System.out.println("\n========== LUU DU LIEU VAO FILE ==========");

        qlsp.ghiFile(FILE_SAN_PHAM);
        qlnv.ghiFile(FILE_NHAN_VIEN);
        qlkh.ghiFile(FILE_KHACH_HANG);
        qlhd.ghiFile(FILE_HOA_DON);
        qlpn.ghiFile(FILE_PHIEU_NHAP);

        System.out.println("Da luu xong du lieu!");
    }

    // ================= SAN PHAM =================

    private static void menuSanPham() {

        int luaChon;

        do {
            System.out.println("\n========== QUAN LY SAN PHAM ==========");
            System.out.println("1. Them may tinh");
            System.out.println("2. Them CPU");
            System.out.println("3. Them RAM");
            System.out.println("4. Them card do hoa");
            System.out.println("5. Them o cung");
            System.out.println("6. Them nguon");
            System.out.println("7. Hien thi danh sach san pham");
            System.out.println("8. Tim san pham theo ma");
            System.out.println("9. Sua san pham");
            System.out.println("10. Xoa san pham");
            System.out.println("11. Sap xep san pham theo gia");
            System.out.println("12. Kiem tra ton kho");
            System.out.println("13. Thong ke san pham");
            System.out.println("14. Them chi tiet san pham");
            System.out.println("15. Dem tong so doi tuong san pham");
            System.out.println("16. Tim kiem san pham theo nhieu tieu chi");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 16);

            switch (luaChon) {
                case 1:
                    themMayTinh();
                    break;
                case 2:
                    themCPU();
                    break;
                case 3:
                    themRAM();
                    break;
                case 4:
                    themCardDoHoa();
                    break;
                case 5:
                    themOCung();
                    break;
                case 6:
                    themNguon();
                    break;
                case 7:
                    qlsp.hienThiDanhSach();
                    break;
                case 8: {
                    String maSP = Validator.readString("Nhap ma san pham: ");
                    qlsp.timSanPham(maSP);
                    break;
                }
                case 9: {
                    String maSP = Validator.readString("Nhap ma san pham can sua: ");
                    qlsp.suaSanPham(maSP);
                    break;
                }
                case 10: {
                    String maSP = Validator.readString("Nhap ma san pham can xoa: ");
                    qlsp.xoaSanPham(maSP);
                    break;
                }
                case 11:
                    qlsp.sapXepTheoGia();
                    qlsp.hienThiDanhSach();
                    break;
                case 12:
                    qlsp.kiemTraTonKho();
                    break;
                case 13:
                    qlsp.thongKeTheoNhieuKhoa();
                    break;
                case 14:
                    themChiTietSanPham();
                    break;
                case 15:
                    System.out.println("\n========== DEM SAN PHAM ==========");
                    System.out.println("Tong so doi tuong san pham da khoi tao: "
                            + SanPham.getDemSanPham());
                    break;
                case 16: {
                    String tuKhoa = Validator.readString(
                            "Nhap tu khoa tim kiem san pham: "
                    );
                    qlsp.timSanPhamNhieuTieuChi(tuKhoa);
                    break;
                }
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
                maSP, tenSP, hangSX,
                giaNhap, giaBan, soLuongTon, baoHanh,
                loaiMay, CPU, RAM, dungLuongSSD
        );

        qlsp.themSanPham(mt);
    }

    private static void themCPU() {

        System.out.println("\n========== THEM CPU ==========");

        String maSP = Validator.readString("Ma san pham: ");
        String tenSP = Validator.readString("Ten CPU: ");
        String hangSX = Validator.readString("Hang san xuat: ");

        double giaNhap = Validator.readDouble("Gia nhap: ", 0);
        double giaBan = Validator.readDouble("Gia ban: ", 0);

        int soLuongTon = Validator.readInt("So luong ton: ", 0, Integer.MAX_VALUE);
        int baoHanh = Validator.readInt("Bao hanh (thang): ", 0, 120);

        String loai = Validator.readString("Loai linh kien: ");
        String thongSo = Validator.readString("Thong so ky thuat: ");
        String socket = Validator.readString("Socket: ");

        int soNhan = Validator.readInt("So nhan: ", 1, 128);
        double xungNhip = Validator.readDouble("Xung nhip (GHz): ", 0);

        CPU cpu = new CPU(
                maSP, tenSP, hangSX,
                giaNhap, giaBan, soLuongTon, baoHanh,
                loai, thongSo, socket, soNhan, xungNhip
        );

        qlsp.themSanPham(cpu);
    }

    private static void themRAM() {

        System.out.println("\n========== THEM RAM ==========");

        String maSP = Validator.readString("Ma san pham: ");
        String tenSP = Validator.readString("Ten RAM: ");
        String hangSX = Validator.readString("Hang san xuat: ");

        double giaNhap = Validator.readDouble("Gia nhap: ", 0);
        double giaBan = Validator.readDouble("Gia ban: ", 0);

        int soLuongTon = Validator.readInt("So luong ton: ", 0, Integer.MAX_VALUE);
        int baoHanh = Validator.readInt("Bao hanh (thang): ", 0, 120);

        String loai = Validator.readString("Loai linh kien: ");
        String thongSo = Validator.readString("Thong so ky thuat: ");

        int dungLuong = Validator.readInt("Dung luong RAM (GB): ", 1, 1024);
        String loaiRAM = Validator.readString("Loai RAM (DDR4/DDR5): ");
        int tocDoBus = Validator.readInt("Toc do Bus (MHz): ", 1, 100000);

        RAM ram = new RAM(
                maSP, tenSP, hangSX,
                giaNhap, giaBan, soLuongTon, baoHanh,
                loai, thongSo, dungLuong, loaiRAM, tocDoBus
        );

        qlsp.themSanPham(ram);
    }

    private static void themCardDoHoa() {

        System.out.println("\n========== THEM CARD DO HOA ==========");

        String maSP = Validator.readString("Ma san pham: ");
        String tenSP = Validator.readString("Ten card: ");
        String hangSX = Validator.readString("Hang san xuat: ");

        double giaNhap = Validator.readDouble("Gia nhap: ", 0);
        double giaBan = Validator.readDouble("Gia ban: ", 0);

        int soLuongTon = Validator.readInt("So luong ton: ", 0, Integer.MAX_VALUE);
        int baoHanh = Validator.readInt("Bao hanh (thang): ", 0, 120);

        String loai = Validator.readString("Loai linh kien: ");
        String thongSo = Validator.readString("Thong so ky thuat: ");

        int VRAM = Validator.readInt("Dung luong VRAM (GB): ", 1, 100);
        String chip = Validator.readString("Chip do hoa: ");

        CardDoHoa card = new CardDoHoa(
                maSP, tenSP, hangSX,
                giaNhap, giaBan, soLuongTon, baoHanh,
                loai, thongSo, VRAM, chip
        );

        qlsp.themSanPham(card);
    }

    private static void themOCung() {

        System.out.println("\n========== THEM O CUNG ==========");

        String maSP = Validator.readString("Ma san pham: ");
        String tenSP = Validator.readString("Ten o cung: ");
        String hangSX = Validator.readString("Hang san xuat: ");

        double giaNhap = Validator.readDouble("Gia nhap: ", 0);
        double giaBan = Validator.readDouble("Gia ban: ", 0);

        int soLuongTon = Validator.readInt("So luong ton: ", 0, Integer.MAX_VALUE);
        int baoHanh = Validator.readInt("Bao hanh (thang): ", 0, 120);

        String loai = Validator.readString("Loai linh kien: ");
        String thongSo = Validator.readString("Thong so ky thuat: ");

        int dungLuong = Validator.readInt("Dung luong (GB): ", 1, 1000000);
        String loaiOCung = Validator.readString("Loai o cung (SSD/HDD): ");

        OCung oc = new OCung(
                maSP, tenSP, hangSX,
                giaNhap, giaBan, soLuongTon, baoHanh,
                loai, thongSo, dungLuong, loaiOCung
        );

        qlsp.themSanPham(oc);
    }

    private static void themNguon() {

        System.out.println("\n========== THEM NGUON ==========");

        String maSP = Validator.readString("Ma san pham: ");
        String tenSP = Validator.readString("Ten nguon: ");
        String hangSX = Validator.readString("Hang san xuat: ");

        double giaNhap = Validator.readDouble("Gia nhap: ", 0);
        double giaBan = Validator.readDouble("Gia ban: ", 0);

        int soLuongTon = Validator.readInt("So luong ton: ", 0, Integer.MAX_VALUE);
        int baoHanh = Validator.readInt("Bao hanh (thang): ", 0, 120);

        String loai = Validator.readString("Loai linh kien: ");
        String thongSo = Validator.readString("Thong so ky thuat: ");

        int congSuat = Validator.readInt("Cong suat (W): ", 1, 10000);
        String chungNhan = Validator.readString("Chung nhan (80 Plus): ");

        Nguon nguon = new Nguon(
                maSP, tenSP, hangSX,
                giaBan, soLuongTon, congSuat, chungNhan
        );

        nguon.setGiaNhap(giaNhap);
        nguon.setBaoHanh(baoHanh);
        nguon.setLoaiLinhKien(loai);
        nguon.setThongSoKyThuat(thongSo);

        qlsp.themSanPham(nguon);
    }

    private static void themChiTietSanPham() {

        System.out.println("\n========== THEM CHI TIET SAN PHAM ==========");

        String maSP = Validator.readString("Nhap ma san pham: ");
        String tenSP = Validator.readString("Nhap ten san pham: ");
        String hangSX = Validator.readString("Nhap hang san xuat: ");

        double giaNhap = Validator.readDouble("Nhap gia nhap: ", 0);
        double giaBan = Validator.readDouble("Nhap gia ban: ", 0);

        int soLuongTon = Validator.readInt(
                "Nhap so luong ton: ", 0, Integer.MAX_VALUE
        );

        int baoHanh = Validator.readInt(
                "Nhap bao hanh (thang): ", 0, 120
        );

        String soSeri = Validator.readString("Nhap so seri: ");
        String maSPChiTiet = Validator.readString("Nhap ma san pham chi tiet: ");
        String trangThai = Validator.readString("Nhap trang thai san pham: ");

        ChiTietSanPham sp = new ChiTietSanPham(
                maSP, tenSP, hangSX,
                giaNhap, giaBan, soLuongTon, baoHanh,
                soSeri, maSPChiTiet, trangThai
        );

        qlsp.themSanPham(sp);
    }

    // ================= NHAN VIEN =================

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
            System.out.println("7. Tim kiem nhan vien theo nhieu tieu chi");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 7);

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
                    int luaChonSua = Validator.readInt("Nhap muc can sua (1-8): ", 1, 8);
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
                case 7: {
                    String tuKhoa = Validator.readString(
                            "Nhap tu khoa tim kiem nhan vien: "
                    );
                    qlnv.timNhanVienNhieuTieuChi(tuKhoa);
                    break;
                }
            }

        } while (luaChon != 0);
    }

    private static void themNhanVienQuanLy() {

        System.out.println("\n========== THEM NHAN VIEN QUAN LY ==========");

        String hoTen = Validator.readString("Nhap ho ten: ");
        String ngaySinh = Validator.readDate("Nhap ngay sinh (yyyy-MM-dd): ");
        String soDienThoai = Validator.readPhone("Nhap so dien thoai: ");
        String email = Validator.readEmail("Nhap email: ");
        String maNV = Validator.readString("Nhap ma nhan vien: ");

        double luongCoBan = Validator.readDouble("Nhap luong co ban: ", 0);
        double phuCap = Validator.readDouble("Nhap phu cap: ", 0);
        double tienThuong = Validator.readDouble("Nhap tien thuong: ", 0);
        double phuCapChucVu = Validator.readDouble("Nhap phu cap chuc vu: ", 0);

        QuanLy nv = new QuanLy(
                hoTen, ngaySinh, soDienThoai, email, maNV,
                luongCoBan, phuCap, tienThuong, phuCapChucVu
        );

        if (qlnv.themNhanVien(nv)) {
            System.out.println("Them nhan vien thanh cong!");
        } else {
            System.out.println("Them nhan vien that bai! Ma nhan vien co the da ton tai.");
        }
    }

    // ================= KHACH HANG =================

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
            System.out.println("7. Tim kiem khach hang theo nhieu tieu chi");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 7);

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
                case 7: {
                    String tuKhoa = Validator.readString(
                            "Nhap tu khoa tim kiem khach hang: "
                    );
                    qlkh.timKhachHangNhieuTieuChi(tuKhoa);
                    break;
                }
            }

        } while (luaChon != 0);
    }

    private static void themKhachHang() {

        System.out.println("\n========== THEM KHACH HANG ==========");

        String hoTen = Validator.readString("Nhap ho ten: ");
        String ngaySinh = Validator.readDate("Nhap ngay sinh (yyyy-MM-dd): ");
        String soDienThoai = Validator.readPhone("Nhap so dien thoai: ");
        String email = Validator.readEmail("Nhap email: ");
        String maKH = Validator.readString("Nhap ma khach hang: ");
        String diaChi = Validator.readString("Nhap dia chi: ");

        KhachHang kh = new KhachHang(
                hoTen, ngaySinh, soDienThoai, email, maKH, diaChi
        );

        qlkh.themKhachHang(kh);
    }

    // ================= HOA DON =================

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
            System.out.println("8. Tim kiem hoa don theo nhieu tieu chi");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 8);

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
                case 8: {
                    String tuKhoa = Validator.readString(
                            "Nhap tu khoa tim kiem hoa don: "
                    );
                    qlhd.timHoaDonNhieuTieuChi(tuKhoa);
                    break;
                }
            }

        } while (luaChon != 0);
    }

    private static void lapHoaDon() {

        System.out.println("\n========== LAP HOA DON ==========");

        String maHD = Validator.readString("Nhap ma hoa don: ");
        String ngayLap = Validator.readDate("Nhap ngay lap (yyyy-MM-dd): ");

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

        int soChiTiet = Validator.readInt(
                "Nhap so loai san pham trong hoa don: ", 1, 100
        );

        for (int i = 0; i < soChiTiet; i++) {

            System.out.println("\n--- San pham thu " + (i + 1) + " ---");

            String maSP = Validator.readString("Nhap ma san pham: ");
            SanPham sp = qlsp.laySanPham(maSP);

            if (sp == null) {
                System.out.println("Khong tim thay san pham. Bo qua chi tiet nay.");
                continue;
            }

            int soLuong = Validator.readInt(
                    "Nhap so luong mua: ", 1, 100000
            );

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

    // ================= PHIEU NHAP =================

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
            System.out.println("9. Tim kiem phieu nhap theo nhieu tieu chi");
            System.out.println("0. Quay lai");

            luaChon = Validator.readInt("Nhap lua chon: ", 0, 9);

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
                    String ngayMoi = Validator.readDate(
                            "Nhap ngay nhap moi (yyyy-MM-dd): "
                    );
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
                case 9: {
                    String tuKhoa = Validator.readString(
                            "Nhap tu khoa tim kiem phieu nhap: "
                    );
                    qlpn.timPhieuNhapNhieuTieuChi(tuKhoa);
                    break;
                }
            }

        } while (luaChon != 0);
    }

    private static void lapPhieuNhap() {

        System.out.println("\n========== LAP PHIEU NHAP ==========");

        String maPN = Validator.readString("Nhap ma phieu nhap: ");
        String ngayNhap = Validator.readDate("Nhap ngay nhap (yyyy-MM-dd): ");

        String maNV = Validator.readString("Nhap ma nhan vien lap phieu: ");
        NhanVien nv = qlnv.layNhanVien(maNV);

        if (nv == null) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }

        System.out.println("\n--- THONG TIN NHA CUNG CAP ---");

        String maNCC = Validator.readString("Nhap ma nha cung cap: ");
        String tenNCC = Validator.readString("Nhap ten nha cung cap: ");
        String soDienThoai = Validator.readPhone("Nhap so dien thoai: ");
        String diaChi = Validator.readString("Nhap dia chi: ");
        String email = Validator.readEmail("Nhap email: ");

        NhaCungCap ncc = new NhaCungCap(
                maNCC, tenNCC, soDienThoai, diaChi, email
        );

        PhieuNhap pn = new PhieuNhap(maPN, ngayNhap, ncc, nv);

        int soChiTiet = Validator.readInt(
                "Nhap so loai san pham nhap: ", 1, 100
        );

        for (int i = 0; i < soChiTiet; i++) {

            System.out.println("\n--- San pham nhap thu " + (i + 1) + " ---");

            String maSP = Validator.readString("Nhap ma san pham: ");
            SanPham sp = qlsp.laySanPham(maSP);

            if (sp == null) {
                System.out.println("Khong tim thay san pham. Bo qua chi tiet nay.");
                continue;
            }

            int soLuong = Validator.readInt(
                    "Nhap so luong nhap: ", 1, 100000
            );

            double donGiaNhap = Validator.readDouble(
                    "Nhap don gia nhap: ", 0
            );

            ChiTietPhieuNhap ct = new ChiTietPhieuNhap(
                    sp, soLuong, donGiaNhap
            );

            pn.themChiTiet(ct);
        }

        if (pn.getSoLuongChiTiet() == 0) {
            System.out.println("Phieu nhap khong co chi tiet hop le!");
            return;
        }

        qlpn.themPhieuNhap(pn);
        qlpn.capNhatTonKhoTheoPhieuNhap(maPN);

        System.out.println("Lap phieu nhap thanh cong!");
        System.out.println("Tong tien nhap: " + pn.tinhTongTienNhap());
    }
}
