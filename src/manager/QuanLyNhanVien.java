package manager;
import entity.NhanVien;
import entity.QuanLy;
import interfaces.IFileIO;
import utils.FileUtil;

import java.util.Arrays;
import java.util.Objects;

public class QuanLyNhanVien implements IFileIO {

    private NhanVien[] danhSach;
    private int soLuong;

    public QuanLyNhanVien() {
        danhSach = new NhanVien[10];
        soLuong = 0;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public NhanVien[] getDanhSach() {
        return danhSach;
    }

    // 1. Them nhan vien
    public boolean themNhanVien(NhanVien nv) {

        if (nv == null || nv.getMaNV() == null
                || nv.getMaNV().trim().isEmpty()) {
            return false;
        }

        // Kiem tra trung ma truoc
        if (layNhanVien(nv.getMaNV()) != null) {
            System.out.println("Ma nhan vien da ton tai!");
            return false;
        }

        // Mo rong mang neu day
        if (soLuong == danhSach.length) {
            danhSach = Arrays.copyOf(
                danhSach, danhSach.length * 2
            );
        }

        danhSach[soLuong++] = nv;
        return true;
    }

    // 2. Them nhieu nhan vien
    public void themNhieuNhanVien(NhanVien[] ds, int k) {

        if (ds == null || k <= 0 || k > ds.length) {
            System.out.println("Danh sach khong hop le!");
            return;
        }

        int soThem = 0;

        for (int i = 0; i < k; i++) {

            if (themNhanVien(ds[i])) {
                soThem++;
                System.out.println("Them nhan vien thu "
                        + (i + 1) + " thanh cong!");
            } else {
                System.out.println("Khong the them nhan vien thu "
                        + (i + 1));
            }
        }

        System.out.println("Tong so nhan vien da them: " + soThem);
    }

    // 3. Sua thong tin nhan vien
    public boolean suaThongTinNhanVien(
            String maNV, int luaChon, String giaTriMoi) {

        if (maNV == null || giaTriMoi == null) {
            return false;
        }

        NhanVien nv = layNhanVien(maNV);

        if (nv == null) {
            System.out.println("Khong tim thay nhan vien!");
            return false;
        }

        try {
            switch (luaChon) {

                case 1:
                    nv.setHoTen(giaTriMoi);
                    break;

                case 2:
                    nv.setNgaySinh(giaTriMoi);
                    break;

                case 3:
                    nv.setSoDienThoai(giaTriMoi);
                    break;

                case 4:
                    nv.setEmail(giaTriMoi);
                    break;

                case 5:
                    double luong = Double.parseDouble(giaTriMoi);
                    if (luong < 0) return false;
                    nv.setLuongCoBan(luong);
                    break;

                case 6:
                    double phuCap = Double.parseDouble(giaTriMoi);
                    if (phuCap < 0) return false;
                    nv.setPhuCap(phuCap);
                    break;

                case 7:
                    double tienThuong = Double.parseDouble(giaTriMoi);
                    if (tienThuong < 0) return false;
                    nv.setTienThuong(tienThuong);
                    break;

                case 8:
                    if (!(nv instanceof QuanLy)) {
                        return false;
                    }

                    double phuCapChucVu =
                            Double.parseDouble(giaTriMoi);

                    if (phuCapChucVu < 0) return false;

                    ((QuanLy) nv).setPhuCapChucVu(phuCapChucVu);
                    break;

                default:
                    return false;
            }

            System.out.println("Sua thong tin thanh cong!");
            return true;

        } catch (NumberFormatException e) {
            System.out.println("Gia tri nhap khong hop le!");
            return false;
        }
    }

    // 4. Xoa nhan vien
    public boolean xoaNhanVien(String maNV) {

        if (maNV == null) {
            return false;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i].getMaNV().equalsIgnoreCase(maNV)) {

                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }

                danhSach[--soLuong] = null;

                System.out.println("Xoa nhan vien thanh cong!");
                return true;
            }
        }

        System.out.println("Khong tim thay nhan vien!");
        return false;
    }

    // 5. Tim nhan vien theo ma
    public boolean timNhanVien(String maNV) {

        NhanVien nv = layNhanVien(maNV);

        if (nv != null) {
            System.out.println("Tim thay nhan vien!");
            nv.hienThiThongTin();
            return true;
        }

        System.out.println("Khong tim thay nhan vien!");
        return false;
    }

    // 6. Tim nhan vien theo nhieu tieu chi
    public void timNhanVienNhieuTieuChi(String tuKhoa) {

        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            System.out.println("Tu khoa khong hop le!");
            return;
        }

        tuKhoa = tuKhoa.trim();
        boolean timThay = false;

        // Tim chinh xac truoc
        for (int i = 0; i < soLuong; i++) {

            NhanVien nv = danhSach[i];

            String ma = nv.getMaNV() == null ? "" : nv.getMaNV();
            String ten = nv.getHoTen() == null ? "" : nv.getHoTen();
            String sdt = nv.getSoDienThoai() == null
                    ? "" : nv.getSoDienThoai();
            String email = nv.getEmail() == null
                    ? "" : nv.getEmail();

            if (ma.equalsIgnoreCase(tuKhoa)
                    || ten.equalsIgnoreCase(tuKhoa)
                    || sdt.equalsIgnoreCase(tuKhoa)
                    || email.equalsIgnoreCase(tuKhoa)) {

                System.out.println("Tim thay chinh xac:");
                nv.hienThiThongTin();
                timThay = true;
            }
        }

        if (timThay) {
            return;
        }

        // Tim gan dung
        String tuKhoaLower = tuKhoa.toLowerCase();

        for (int i = 0; i < soLuong; i++) {

            NhanVien nv = danhSach[i];

            String ma = nv.getMaNV() == null ? "" : nv.getMaNV();
            String ten = nv.getHoTen() == null ? "" : nv.getHoTen();
            String sdt = nv.getSoDienThoai() == null
                    ? "" : nv.getSoDienThoai();
            String email = nv.getEmail() == null
                    ? "" : nv.getEmail();

            if (ma.toLowerCase().contains(tuKhoaLower)
                    || ten.toLowerCase().contains(tuKhoaLower)
                    || sdt.contains(tuKhoa)
                    || email.toLowerCase().contains(tuKhoaLower)) {

                System.out.println("Tim thay gan dung:");
                nv.hienThiThongTin();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay nhan vien!");
        }
    }

    // 7. Hien thi danh sach
    public void hienThiDanhSach() {

        if (soLuong == 0) {
            System.out.println("Danh sach nhan vien rong!");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            System.out.println("\nNhan vien thu " + (i + 1));
            danhSach[i].hienThiThongTin();
        }
    }

    // 8. Thong ke theo loai nhan vien
    public void thongKeTheoLoaiNhanVien() {

        int soQuanLy = 0;
        int soNhanVienKhac = 0;

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] instanceof QuanLy) {
                soQuanLy++;
            } else {
                soNhanVienKhac++;
            }
        }

        System.out.println("\n===== THONG KE NHAN VIEN =====");
        System.out.println("So luong quan ly: " + soQuanLy);
        System.out.println("So luong nhan vien khac: "
                + soNhanVienKhac);
        System.out.println("Tong so nhan vien: " + soLuong);
    }

    // 9. Ghi file
    @Override
    public void ghiFile(String tenFile) {

        StringBuilder noiDung = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {

            NhanVien nv = danhSach[i];

            noiDung.append(nv.getHoTen()).append(";")
                   .append(nv.getNgaySinh()).append(";")
                   .append(nv.getSoDienThoai()).append(";")
                   .append(nv.getEmail()).append(";")
                   .append(nv.getMaNV()).append(";")
                   .append(nv.getLuongCoBan()).append(";")
                   .append(nv.getPhuCap()).append(";")
                   .append(nv.getTienThuong()).append(";");

            if (nv instanceof QuanLy) {
                QuanLy ql = (QuanLy) nv;
                noiDung.append(ql.getPhuCapChucVu());
            }

            if (i < soLuong - 1) {
                noiDung.append("\n");
            }
        }

        FileUtil.ghiChuoi(tenFile, noiDung.toString());

        System.out.println("Da ghi danh sach nhan vien vao file!");
    }

    // 10. Doc file
    @Override
    public void docFile(String tenFile) {

        String noiDung = FileUtil.docChuoi(tenFile);

        if (noiDung == null || noiDung.trim().isEmpty()) {
            System.out.println("File nhan vien rong!");
            return;
        }

        danhSach = new NhanVien[10];
        soLuong = 0;

        String[] cacDong = noiDung.split("\\R");

        for (String dong : cacDong) {

            if (dong.trim().isEmpty()) {
                continue;
            }

            String[] data = dong.split(";", -1);

            if (data.length != 9 || data[8].isEmpty()) {
                continue;
            }

            try {

                NhanVien nv = new QuanLy(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    data[4],
                    Double.parseDouble(data[5]),
                    Double.parseDouble(data[6]),
                    Double.parseDouble(data[7]),
                    Double.parseDouble(data[8])
                );

                themNhanVien(nv);

            } catch (NumberFormatException e) {
                System.out.println("Loi du lieu nhan vien: " + dong);
            }
        }

        System.out.println("Da doc danh sach nhan vien tu file!");
    }

    // 11. Lay nhan vien theo ma
    public NhanVien layNhanVien(String maNV) {

        if (maNV == null) {
            return null;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i].getMaNV() != null
                    && danhSach[i].getMaNV().equalsIgnoreCase(maNV)) {
                return danhSach[i];
            }
        }

        return null;
    }
}
