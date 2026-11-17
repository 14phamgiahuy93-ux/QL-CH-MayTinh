package manager;
import entity.PhieuNhap;
import entity.ChiTietPhieuNhap;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.SanPham;

import interfaces.IFileIO;
import utils.FileUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QuanLyPhieuNhap implements IFileIO {

    private PhieuNhap[] danhSach;
    private int soLuong;

    private QuanLySanPham qlSP;
    private QuanLyNhanVien qlNV;

    // Constructor
    public QuanLyPhieuNhap() {
        danhSach = new PhieuNhap[100];
        soLuong = 0;
    }

    public QuanLyPhieuNhap(QuanLySanPham qlSP, QuanLyNhanVien qlNV) {
        this();
        this.qlSP = qlSP;
        this.qlNV = qlNV;
    }

    // 1. Them phieu nhap
    public void themPhieuNhap(PhieuNhap pn) {

        if (pn == null) {
            System.out.println("Phieu nhap khong hop le!");
            return;
        }

        if (layPhieuNhapNoiBo(pn.getMaPN()) != null) {
            System.out.println("Ma phieu nhap da ton tai!");
            return;
        }

        if (soLuong >= danhSach.length) {
            danhSach = Arrays.copyOf(
                danhSach, danhSach.length * 2
            );
        }

        danhSach[soLuong++] = pn;

        System.out.println("Them phieu nhap thanh cong!");
    }

    // 2. Xoa phieu nhap theo ma
    public void xoaPhieuNhap(String maPN) {

        int index = -1;

        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPN().equalsIgnoreCase(maPN)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Khong tim thay phieu nhap!");
            return;
        }

        for (int i = index; i < soLuong - 1; i++) {
            danhSach[i] = danhSach[i + 1];
        }

        danhSach[--soLuong] = null;

        System.out.println("Xoa phieu nhap thanh cong!");
    }

    // 3. Tim phieu nhap theo ma
    public void timPhieuNhap(String maPN) {

        PhieuNhap pn = layPhieuNhapNoiBo(maPN);

        if (pn != null) {
            System.out.println("Da tim thay phieu nhap:");
            pn.hienThi();
        } else {
            System.out.println("Khong tim thay phieu nhap!");
        }
    }

    // 4. Tim phieu nhap theo nhieu tieu chi
    public void timPhieuNhapNhieuTieuChi(String tuKhoa) {

        boolean timThay = false;

        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            System.out.println("Tu khoa khong hop le!");
            return;
        }

        tuKhoa = tuKhoa.trim();

        // Tim chinh xac
        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            if (pn.getMaPN().equalsIgnoreCase(tuKhoa)
                    || pn.getNgayNhap().equalsIgnoreCase(tuKhoa)
                    || pn.getNhaCungCap().getTenNCC().equalsIgnoreCase(tuKhoa)
                    || pn.getNhanVien().getHoTen().equalsIgnoreCase(tuKhoa)) {

                System.out.println("Tim thay chinh xac:");
                pn.hienThi();

                timThay = true;
            }
        }

        if (timThay) {
            return;
        }

        // Tim gan dung
        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            if (pn.getMaPN().toLowerCase().contains(tuKhoa.toLowerCase())
                    || pn.getNgayNhap().toLowerCase().contains(tuKhoa.toLowerCase())
                    || pn.getNhaCungCap().getTenNCC().toLowerCase().contains(tuKhoa.toLowerCase())
                    || pn.getNhanVien().getHoTen().toLowerCase().contains(tuKhoa.toLowerCase())) {

                System.out.println("Tim thay gan dung:");
                pn.hienThi();

                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay phieu nhap!");
        }
    }

    // 5. Hien thi danh sach phieu nhap
    public void hienThiDanhSach() {

        if (soLuong == 0) {
            System.out.println("Danh sach phieu nhap trong!");
            return;
        }

        System.out.println("\n===== DANH SACH PHIEU NHAP =====");

        for (int i = 0; i < soLuong; i++) {
            danhSach[i].hienThi();
        }
    }

    // 6. Thong ke tong tien nhap
    public void thongKeTienNhap() {

        double tongChiPhi = 0;

        for (int i = 0; i < soLuong; i++) {
            tongChiPhi += danhSach[i].tinhTongTienNhap();
        }

        System.out.println("\n===== THONG KE NHAP HANG =====");
        System.out.println("Tong so phieu nhap: " + soLuong);

        System.out.printf(
            "Tong chi phi nhap hang: %.2f VND%n",
            tongChiPhi
        );
    }

    // 7. Thong ke theo nha cung cap
    public void thongKeTheoNhaCungCap() {

        if (soLuong == 0) {
            System.out.println("Danh sach phieu nhap rong!");
            return;
        }

        Map<String, double[]> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            String khoa = pn.getNhaCungCap().getMaNCC()
                    + " - "
                    + pn.getNhaCungCap().getTenNCC();

            double[] soLieu = thongKe.get(khoa);

            if (soLieu == null) {
                soLieu = new double[2];
                thongKe.put(khoa, soLieu);
            }

            soLieu[0]++;
            soLieu[1] += pn.tinhTongTienNhap();
        }

        System.out.println("\n===== THONG KE THEO NHA CUNG CAP =====");

        for (Map.Entry<String, double[]> entry : thongKe.entrySet()) {

            double[] soLieu = entry.getValue();

            System.out.println("\nNha cung cap: " + entry.getKey());
            System.out.println("So phieu nhap: " + (int) soLieu[0]);

            System.out.printf(
                "Tong tien nhap: %.2f VND%n",
                soLieu[1]
            );
        }
    }

    // 8. Thong ke theo nhan vien
    public void thongKeTheoNhanVien() {

        if (soLuong == 0) {
            System.out.println("Danh sach phieu nhap rong!");
            return;
        }

        Map<String, double[]> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            String khoa = pn.getNhanVien().getMaNV()
                    + " - "
                    + pn.getNhanVien().getHoTen();

            double[] soLieu = thongKe.get(khoa);

            if (soLieu == null) {
                soLieu = new double[2];
                thongKe.put(khoa, soLieu);
            }

            soLieu[0]++;
            soLieu[1] += pn.tinhTongTienNhap();
        }

        System.out.println("\n===== THONG KE THEO NHAN VIEN =====");

        for (Map.Entry<String, double[]> entry : thongKe.entrySet()) {

            double[] soLieu = entry.getValue();

            System.out.println("\nNhan vien: " + entry.getKey());
            System.out.println("So phieu nhap: " + (int) soLieu[0]);

            System.out.printf(
                "Tong tien nhap: %.2f VND%n",
                soLieu[1]
            );
        }
    }

    // 9. Cap nhat ton kho theo phieu nhap
    public boolean capNhatTonKhoTheoPhieuNhap(String maPN) {

        if (qlSP == null) {
            System.out.println("Chua khoi tao QuanLySanPham!");
            return false;
        }

        PhieuNhap pn = layPhieuNhapNoiBo(maPN);

        if (pn == null) {
            System.out.println("Khong tim thay phieu nhap!");
            return false;
        }

        // Kiem tra chi tiet truoc khi cap nhat
        for (int i = 0; i < pn.getSoLuongChiTiet(); i++) {

            ChiTietPhieuNhap ct = pn.getDanhSachChiTiet()[i];

            if (ct == null || ct.getSanPham() == null
                    || ct.getSoLuong() <= 0) {

                System.out.println("Chi tiet phieu nhap khong hop le!");
                return false;
            }

            SanPham sp = qlSP.laySanPham(
                ct.getSanPham().getMaSP()
            );

            if (sp == null) {
                System.out.println("San pham khong ton tai!");
                return false;
            }
        }

        // Cap nhat ton kho
        for (int i = 0; i < pn.getSoLuongChiTiet(); i++) {

            ChiTietPhieuNhap ct = pn.getDanhSachChiTiet()[i];

            SanPham sp = qlSP.laySanPham(
                ct.getSanPham().getMaSP()
            );

            sp.setSoLuongTon(
                sp.getSoLuongTon() + ct.getSoLuong()
            );
        }

        System.out.println("Cap nhat ton kho thanh cong!");
        return true;
    }

    // 10. Ghi danh sach phieu nhap vao file
    @Override
    public void ghiFile(String tenFile) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            NhaCungCap ncc = pn.getNhaCungCap();
            NhanVien nv = pn.getNhanVien();

            sb.append("P;")
              .append(pn.getMaPN()).append(";")
              .append(pn.getNgayNhap()).append(";")
              .append(ncc.getMaNCC()).append(";")
              .append(ncc.getTenNCC()).append(";")
              .append(ncc.getSoDienThoai()).append(";")
              .append(ncc.getDiaChi()).append(";")
              .append(ncc.getEmail()).append(";")
              .append(nv.getMaNV()).append("\n");

            for (int j = 0; j < pn.getSoLuongChiTiet(); j++) {

                ChiTietPhieuNhap ct = pn.getDanhSachChiTiet()[j];

                sb.append("C;")
                  .append(pn.getMaPN()).append(";")
                  .append(ct.getSanPham().getMaSP()).append(";")
                  .append(ct.getSoLuong()).append(";")
                  .append(ct.getDonGiaNhap()).append("\n");
            }
        }

        FileUtil.ghiChuoi(tenFile, sb.toString());

        System.out.println("Ghi file phieu nhap thanh cong!");
    }

    // 11. Doc danh sach phieu nhap tu file
    @Override
    public void docFile(String tenFile) {

        if (qlSP == null || qlNV == null) {
            System.out.println(
                "Chua khoi tao QuanLySanPham hoac QuanLyNhanVien!"
            );
            return;
        }

        danhSach = new PhieuNhap[100];
        soLuong = 0;

        String noiDung = FileUtil.docChuoi(tenFile);

        if (noiDung == null || noiDung.trim().isEmpty()) {
            System.out.println("File trong hoac khong doc duoc!");
            return;
        }

        String[] cacDong = noiDung.split("\\R");

        for (String line : cacDong) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] data = line.split(";", -1);

            try {

                // Doc thong tin phieu nhap
                if (data.length == 9 && data[0].equals("P")) {

                    NhaCungCap ncc = new NhaCungCap(
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7]
                    );

                    NhanVien nv = qlNV.layNhanVien(data[8]);

                    if (nv == null) {
                        System.out.println(
                            "Khong tim thay nhan vien: " + data[8]
                        );
                        continue;
                    }

                    PhieuNhap pn = new PhieuNhap(
                        data[1],
                        data[2],
                        ncc,
                        nv
                    );

                    themPhieuNhap(pn);
                }

                // Doc chi tiet phieu nhap
                else if (data.length == 5 && data[0].equals("C")) {

                    String maPN = data[1];
                    String maSP = data[2];

                    int soLuongNhap = Integer.parseInt(data[3]);
                    double donGiaNhap = Double.parseDouble(data[4]);

                    PhieuNhap pn = layPhieuNhapNoiBo(maPN);
                    SanPham sp = qlSP.laySanPham(maSP);

                    if (pn == null || sp == null) {
                        System.out.println(
                            "Khong tim thay phieu nhap hoac san pham!"
                        );
                        continue;
                    }

                    ChiTietPhieuNhap ct = new ChiTietPhieuNhap(
                        sp,
                        soLuongNhap,
                        donGiaNhap
                    );

                    pn.themChiTiet(ct);
                }

                else {
                    System.out.println("Dong du lieu khong hop le: " + line);
                }

            } catch (NumberFormatException e) {
                System.out.println("Du lieu so khong hop le: " + line);
            }
        }

        System.out.println("Doc file phieu nhap hoan tat!");
    }

    // 12. Lay phieu nhap theo ma
    private PhieuNhap layPhieuNhapNoiBo(String maPN) {

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i].getMaPN().equalsIgnoreCase(maPN)) {
                return danhSach[i];
            }
        }

        return null;
    }

    // 13. Getters
    public PhieuNhap[] getDanhSach() {
        return danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }
}
