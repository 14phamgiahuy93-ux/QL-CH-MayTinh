package manager;
import entity.KhachHang;
import interfaces.IFileIO;
import utils.FileUtil;
import utils.Validator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QuanLyKhachHang implements IFileIO {

    private KhachHang[] danhSach;
    private int soLuong;

    // Constructor
    public QuanLyKhachHang() {
        danhSach = new KhachHang[10];
        soLuong = 0;
    }

    // 1. Them khach hang
    public void themKhachHang(KhachHang kh) {

        if (kh == null || kh.getMaKH() == null
                || kh.getMaKH().trim().isEmpty()) {

            System.out.println("Thong tin khach hang khong hop le!");
            return;
        }

        if (layKhachHang(kh.getMaKH()) != null) {
            System.out.println("Ma khach hang da ton tai!");
            return;
        }

        if (soLuong >= danhSach.length) {
            danhSach = Arrays.copyOf(
                danhSach, danhSach.length * 2
            );
        }

        danhSach[soLuong++] = kh;

        System.out.println("Them khach hang thanh cong!");
    }

    // 2. Them nhieu khach hang
    public void themNhieuKhachHang(KhachHang[] ds, int k) {

        if (ds == null || k <= 0 || k > ds.length) {
            System.out.println("Danh sach khong hop le!");
            return;
        }

        int soThem = 0;

        for (int i = 0; i < k; i++) {

            int truocDo = soLuong;

            themKhachHang(ds[i]);

            if (soLuong > truocDo) {
                soThem++;
            }
        }

        System.out.println("Da them " + soThem + " khach hang!");
    }

    // 3. Sua khach hang
    public void suaKhachHang(String maKH) {

        KhachHang kh = layKhachHang(maKH);

        if (kh == null) {
            System.out.println("Khong tim thay khach hang!");
            return;
        }

        System.out.println("\n===== SUA KHACH HANG =====");
        System.out.println("1. Ho ten");
        System.out.println("2. Ngay sinh");
        System.out.println("3. So dien thoai");
        System.out.println("4. Email");
        System.out.println("5. Dia chi");

        int luaChon = Validator.readInt(
            "Lua chon: ", 1, 5
        );

        String giaTriMoi = Validator.readString(
            "Nhap gia tri moi: "
        );

        switch (luaChon) {

            case 1:
                kh.setHoTen(giaTriMoi);
                break;

            case 2:
                kh.setNgaySinh(giaTriMoi);
                break;

            case 3:
                kh.setSoDienThoai(giaTriMoi);
                break;

            case 4:
                kh.setEmail(giaTriMoi);
                break;

            case 5:
                kh.setDiaChi(giaTriMoi);
                break;
        }

        System.out.println("Cap nhat khach hang thanh cong!");
    }

    // 4. Xoa khach hang
    public void xoaKhachHang(String maKH) {

        if (maKH == null || maKH.trim().isEmpty()) {
            System.out.println("Ma khach hang khong hop le!");
            return;
        }

        int index = -1;

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null
                    && Objects.equals(
                        danhSach[i].getMaKH(),
                        maKH.trim()
                    )) {

                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Khong tim thay khach hang!");
            return;
        }

        for (int i = index; i < soLuong - 1; i++) {
            danhSach[i] = danhSach[i + 1];
        }

        danhSach[--soLuong] = null;

        System.out.println("Xoa khach hang thanh cong!");
    }

    // 5. Tim khach hang theo ma
    public void timKhachHang(String maKH) {

        KhachHang kh = layKhachHang(maKH);

        if (kh != null) {
            System.out.println("Tim thay khach hang!");
            kh.hienThiThongTin();
        } else {
            System.out.println("Khong tim thay khach hang!");
        }
    }

    // 6. Tim khach hang theo nhieu tieu chi
    public void timKhachHangNhieuTieuChi(String tuKhoa) {

        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            System.out.println("Tu khoa khong hop le!");
            return;
        }

        tuKhoa = tuKhoa.trim();
        String tuKhoaLower = tuKhoa.toLowerCase();

        boolean timThay = false;

        // Tim chinh xac truoc
        for (int i = 0; i < soLuong; i++) {

            KhachHang kh = danhSach[i];

            if (kh == null) {
                continue;
            }

            String ma = kh.getMaKH() == null
                    ? "" : kh.getMaKH();

            String ten = kh.getHoTen() == null
                    ? "" : kh.getHoTen();

            String sdt = kh.getSoDienThoai() == null
                    ? "" : kh.getSoDienThoai();

            String email = kh.getEmail() == null
                    ? "" : kh.getEmail();

            String diaChi = kh.getDiaChi() == null
                    ? "" : kh.getDiaChi();

            if (ma.equalsIgnoreCase(tuKhoa)
                    || ten.equalsIgnoreCase(tuKhoa)
                    || sdt.equalsIgnoreCase(tuKhoa)
                    || email.equalsIgnoreCase(tuKhoa)
                    || diaChi.equalsIgnoreCase(tuKhoa)) {

                System.out.println("Tim thay chinh xac:");
                kh.hienThiThongTin();
                timThay = true;
            }
        }

        if (timThay) {
            return;
        }

        // Tim gan dung
        for (int i = 0; i < soLuong; i++) {

            KhachHang kh = danhSach[i];

            if (kh == null) {
                continue;
            }

            String ma = kh.getMaKH() == null
                    ? "" : kh.getMaKH();

            String ten = kh.getHoTen() == null
                    ? "" : kh.getHoTen();

            String sdt = kh.getSoDienThoai() == null
                    ? "" : kh.getSoDienThoai();

            String email = kh.getEmail() == null
                    ? "" : kh.getEmail();

            String diaChi = kh.getDiaChi() == null
                    ? "" : kh.getDiaChi();

            if (ma.toLowerCase().contains(tuKhoaLower)
                    || ten.toLowerCase().contains(tuKhoaLower)
                    || sdt.contains(tuKhoa)
                    || email.toLowerCase().contains(tuKhoaLower)
                    || diaChi.toLowerCase().contains(tuKhoaLower)) {

                System.out.println("Tim thay gan dung:");
                kh.hienThiThongTin();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay khach hang!");
        }
    }

    // 7. Hien thi danh sach
    public void hienThiDanhSach() {

        if (soLuong == 0) {
            System.out.println("Danh sach khach hang dang trong!");
            return;
        }

        System.out.println("\n===== DANH SACH KHACH HANG =====");

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null) {

                System.out.println(
                    "Khach hang thu " + (i + 1) + ":"
                );

                danhSach[i].hienThiThongTin();

                System.out.println("--------------------");
            }
        }
    }

    // 8. Thong ke theo dia chi
    public void thongKeTheoDiaChi() {

        if (soLuong == 0) {
            System.out.println("Danh sach khach hang rong!");
            return;
        }

        Map<String, Integer> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            KhachHang kh = danhSach[i];

            if (kh == null) {
                continue;
            }

            String diaChi = kh.getDiaChi();

            if (diaChi == null || diaChi.trim().isEmpty()) {
                diaChi = "Chua cap nhat";
            }

            thongKe.put(
                diaChi,
                thongKe.getOrDefault(diaChi, 0) + 1
            );
        }

        System.out.println(
            "\n===== THONG KE KHACH HANG THEO DIA CHI ====="
        );

        for (Map.Entry<String, Integer> entry : thongKe.entrySet()) {

            System.out.println("Dia chi: " + entry.getKey());
            System.out.println("So khach hang: " + entry.getValue());
            System.out.println("--------------------");
        }
    }

    // 9. Ghi file
    @Override
    public void ghiFile(String tenFile) {

        StringBuilder noiDung = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {

            KhachHang kh = danhSach[i];

            if (kh == null) {
                continue;
            }

            noiDung.append(kh.getHoTen()).append(";")
                   .append(kh.getNgaySinh()).append(";")
                   .append(kh.getSoDienThoai()).append(";")
                   .append(kh.getEmail()).append(";")
                   .append(kh.getMaKH()).append(";")
                   .append(kh.getDiaChi())
                   .append("\n");
        }

        FileUtil.ghiChuoi(tenFile, noiDung.toString());

        System.out.println("Ghi file khach hang thanh cong!");
    }

    // 10. Doc file
    @Override
    public void docFile(String tenFile) {

        String noiDung = FileUtil.docChuoi(tenFile);

        if (noiDung == null || noiDung.trim().isEmpty()) {
            System.out.println("File khach hang rong!");
            return;
        }

        KhachHang[] danhSachCu = danhSach;
        int soLuongCu = soLuong;

        danhSach = new KhachHang[10];
        soLuong = 0;

        String[] cacDong = noiDung.split("\\R");

        for (String dong : cacDong) {

            if (dong.trim().isEmpty()) {
                continue;
            }

            String[] thongTin = dong.split(";", -1);

            if (thongTin.length != 6) {
                System.out.println(
                    "Dong du lieu khong hop le: " + dong
                );
                continue;
            }

            try {

                KhachHang kh = new KhachHang(
                    thongTin[0],
                    thongTin[1],
                    thongTin[2],
                    thongTin[3],
                    thongTin[4],
                    thongTin[5]
                );

                themKhachHang(kh);

            } catch (Exception e) {

                System.out.println(
                    "Khong the doc dong: " + dong
                );
            }
        }

        if (soLuong == 0) {

            danhSach = danhSachCu;
            soLuong = soLuongCu;

            System.out.println(
                "Khong doc duoc khach hang, giu danh sach cu!"
            );

            return;
        }

        System.out.println("Doc file khach hang hoan tat!");
    }

    // 11. Lay khach hang theo ma
    public KhachHang layKhachHang(String maKH) {

        if (maKH == null || maKH.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null
                    && danhSach[i].getMaKH() != null
                    && danhSach[i].getMaKH()
                    .equalsIgnoreCase(maKH.trim())) {

                return danhSach[i];
            }
        }

        return null;
    }

    // 12. Getters
    public int getSoLuong() {
        return soLuong;
    }

    public KhachHang[] getDanhSach() {
        return danhSach;
    }
}
