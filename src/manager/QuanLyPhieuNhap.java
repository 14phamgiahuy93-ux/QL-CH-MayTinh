
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QuanLyPhieuNhap implements IFileIO {

    private PhieuNhap[] danhSach;
    private int soLuong;

    private QuanLySanPham qlSP;
    private QuanLyNhanVien qlNV;

    // Luu ma phieu nhap da cap nhat ton kho
    private Set<String> phieuDaCapNhatTonKho;

    // Constructor
    public QuanLyPhieuNhap() {
        danhSach = new PhieuNhap[100];
        soLuong = 0;
        phieuDaCapNhatTonKho = new HashSet<>();
    }

    public QuanLyPhieuNhap(QuanLySanPham qlSP, QuanLyNhanVien qlNV) {
        this();
        this.qlSP = qlSP;
        this.qlNV = qlNV;
    }

    // 1. Them phieu nhap
    public void themPhieuNhap(PhieuNhap pn) {

        if (pn == null || pn.getMaPN() == null
                || pn.getMaPN().trim().isEmpty()) {
            System.out.println("Phieu nhap khong hop le!");
            return;
        }

        if (layPhieuNhapNoiBo(pn.getMaPN()) != null) {
            System.out.println("Ma phieu nhap da ton tai!");
            return;
        }

        if (soLuong >= danhSach.length) {
            danhSach = Arrays.copyOf(danhSach, danhSach.length * 2);
        }

        danhSach[soLuong++] = pn;

        System.out.println("Them phieu nhap thanh cong!");
    }

    // 2. Them nhieu phieu nhap
    public void themNhieuPhieuNhap(PhieuNhap[] ds, int k) {

        if (ds == null || k <= 0) {
            System.out.println("Du lieu khong hop le!");
            return;
        }

        int soThem = 0;

        for (int i = 0; i < k && i < ds.length; i++) {

            int truocDo = soLuong;

            themPhieuNhap(ds[i]);

            if (soLuong > truocDo) {
                soThem++;
            }
        }

        System.out.println("Da them " + soThem + " phieu nhap!");
    }

    // 3. Xoa phieu nhap theo ma
    public void xoaPhieuNhap(String maPN) {

        PhieuNhap pn = layPhieuNhapNoiBo(maPN);

        if (pn == null) {
            System.out.println("Khong tim thay phieu nhap!");
            return;
        }

        // Neu da cap nhat ton kho thi phai hoan tac truoc
        if (phieuDaCapNhatTonKho.contains(maPN.toLowerCase())) {

            if (!hoanTacTonKho(pn)) {
                System.out.println(
                    "Khong the xoa phieu nhap vi khong the hoan tac ton kho!"
                );
                return;
            }

            phieuDaCapNhatTonKho.remove(maPN.toLowerCase());
        }

        int index = -1;

        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] == pn) {
                index = i;
                break;
            }
        }

        for (int i = index; i < soLuong - 1; i++) {
            danhSach[i] = danhSach[i + 1];
        }

        danhSach[--soLuong] = null;

        System.out.println("Xoa phieu nhap thanh cong!");
    }

    // 4. Tim phieu nhap theo ma
    public void timPhieuNhap(String maPN) {

        PhieuNhap pn = layPhieuNhapNoiBo(maPN);

        if (pn != null) {
            System.out.println("Da tim thay phieu nhap:");
            pn.hienThi();
        } else {
            System.out.println("Khong tim thay phieu nhap!");
        }
    }

    // 5. Tim phieu nhap theo nhieu tieu chi
    public void timPhieuNhapNhieuTieuChi(String tuKhoa) {

        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            System.out.println("Tu khoa khong hop le!");
            return;
        }

        tuKhoa = tuKhoa.trim();
        boolean timThay = false;

        // Tim chinh xac truoc
        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            String ma = pn.getMaPN() == null ? "" : pn.getMaPN();
            String ngay = pn.getNgayNhap() == null ? "" : pn.getNgayNhap();

            String tenNCC = pn.getNhaCungCap() != null
                    && pn.getNhaCungCap().getTenNCC() != null
                    ? pn.getNhaCungCap().getTenNCC() : "";

            String tenNV = pn.getNhanVien() != null
                    && pn.getNhanVien().getHoTen() != null
                    ? pn.getNhanVien().getHoTen() : "";

            if (ma.equalsIgnoreCase(tuKhoa)
                    || ngay.equalsIgnoreCase(tuKhoa)
                    || tenNCC.equalsIgnoreCase(tuKhoa)
                    || tenNV.equalsIgnoreCase(tuKhoa)) {

                System.out.println("Tim thay chinh xac:");
                pn.hienThi();
                timThay = true;
            }
        }

        if (timThay) {
            return;
        }

        // Neu khong co ket qua chinh xac thi tim gan dung
        String tuKhoaLower = tuKhoa.toLowerCase();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            String ma = pn.getMaPN() == null ? "" : pn.getMaPN();
            String ngay = pn.getNgayNhap() == null ? "" : pn.getNgayNhap();

            String tenNCC = pn.getNhaCungCap() != null
                    && pn.getNhaCungCap().getTenNCC() != null
                    ? pn.getNhaCungCap().getTenNCC() : "";

            String tenNV = pn.getNhanVien() != null
                    && pn.getNhanVien().getHoTen() != null
                    ? pn.getNhanVien().getHoTen() : "";

            if (ma.toLowerCase().contains(tuKhoaLower)
                    || ngay.toLowerCase().contains(tuKhoaLower)
                    || tenNCC.toLowerCase().contains(tuKhoaLower)
                    || tenNV.toLowerCase().contains(tuKhoaLower)) {

                System.out.println("Tim thay gan dung:");
                pn.hienThi();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay phieu nhap!");
        }
    }

    // 6. Sua ngay nhap
    public boolean suaNgayNhap(String maPN, String ngayNhapMoi) {

        if (ngayNhapMoi == null || ngayNhapMoi.trim().isEmpty()) {
            System.out.println("Ngay nhap moi khong hop le!");
            return false;
        }

        PhieuNhap pn = layPhieuNhapNoiBo(maPN);

        if (pn == null) {
            System.out.println("Khong tim thay phieu nhap!");
            return false;
        }

        pn.setNgayNhap(ngayNhapMoi.trim());

        System.out.println("Cap nhat ngay nhap thanh cong!");
        return true;
    }

    // 7. Hien thi danh sach phieu nhap
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

    // 8. Thong ke tong tien nhap
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

    // 9. Thong ke theo nha cung cap
    public void thongKeTheoNhaCungCap() {

        if (soLuong == 0) {
            System.out.println("Danh sach phieu nhap rong!");
            return;
        }

        Map<String, double[]> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            if (pn.getNhaCungCap() == null) {
                continue;
            }

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

    // 10. Thong ke theo nhan vien
    public void thongKeTheoNhanVien() {

        if (soLuong == 0) {
            System.out.println("Danh sach phieu nhap rong!");
            return;
        }

        Map<String, double[]> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            if (pn.getNhanVien() == null) {
                continue;
            }

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

    // 11. Cap nhat ton kho theo phieu nhap
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

        String khoa = maPN.toLowerCase();

        // Kiem tra phieu nhap da cap nhat chua
        if (phieuDaCapNhatTonKho.contains(khoa)) {
            System.out.println("Phieu nhap nay da cap nhat ton kho!");
            return false;
        }

        if (pn.getSoLuongChiTiet() <= 0) {
            System.out.println("Phieu nhap chua co chi tiet san pham!");
            return false;
        }

        // Kiem tra tat ca chi tiet truoc khi cap nhat
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

        // Tien hanh cap nhat ton kho
        for (int i = 0; i < pn.getSoLuongChiTiet(); i++) {

            ChiTietPhieuNhap ct = pn.getDanhSachChiTiet()[i];

            SanPham sp = qlSP.laySanPham(
                ct.getSanPham().getMaSP()
            );

            sp.setSoLuongTon(
                sp.getSoLuongTon() + ct.getSoLuong()
            );
        }

        phieuDaCapNhatTonKho.add(khoa);

        System.out.println("Cap nhat ton kho thanh cong!");
        return true;
    }

    // 12. Hoan tac ton kho khi xoa phieu nhap
    private boolean hoanTacTonKho(PhieuNhap pn) {

        if (qlSP == null) {
            return false;
        }

        // Kiem tra truoc khi hoan tac
        for (int i = 0; i < pn.getSoLuongChiTiet(); i++) {

            ChiTietPhieuNhap ct = pn.getDanhSachChiTiet()[i];

            if (ct == null || ct.getSanPham() == null) {
                return false;
            }

            SanPham sp = qlSP.laySanPham(
                ct.getSanPham().getMaSP()
            );

            if (sp == null || sp.getSoLuongTon() < ct.getSoLuong()) {
                return false;
            }
        }

        // Tru lai so luong da nhap
        for (int i = 0; i < pn.getSoLuongChiTiet(); i++) {

            ChiTietPhieuNhap ct = pn.getDanhSachChiTiet()[i];

            SanPham sp = qlSP.laySanPham(
                ct.getSanPham().getMaSP()
            );

            sp.setSoLuongTon(
                sp.getSoLuongTon() - ct.getSoLuong()
            );
        }

        return true;
    }

    // 13. Ghi danh sach phieu nhap vao file
    @Override
    public void ghiFile(String tenFile) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            NhaCungCap ncc = pn.getNhaCungCap();
            NhanVien nv = pn.getNhanVien();

            if (ncc == null || nv == null) {
                System.out.println("Bo qua phieu nhap khong hop le!");
                continue;
            }

            String khoa = pn.getMaPN().toLowerCase();

            boolean daCapNhat = phieuDaCapNhatTonKho.contains(khoa);

            sb.append("P;")
              .append(pn.getMaPN()).append(";")
              .append(pn.getNgayNhap()).append(";")
              .append(ncc.getMaNCC()).append(";")
              .append(ncc.getTenNCC()).append(";")
              .append(ncc.getSoDienThoai()).append(";")
              .append(ncc.getDiaChi()).append(";")
              .append(ncc.getEmail()).append(";")
              .append(nv.getMaNV()).append(";")
              .append(daCapNhat).append("\n");

            for (int j = 0; j < pn.getSoLuongChiTiet(); j++) {

                ChiTietPhieuNhap ct = pn.getDanhSachChiTiet()[j];

                if (ct == null || ct.getSanPham() == null) {
                    continue;
                }

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

    // 14. Doc danh sach phieu nhap tu file
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
        phieuDaCapNhatTonKho.clear();

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
                if (data[0].equals("P")
                        && (data.length == 9 || data.length == 10)) {

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

                    int truocDo = soLuong;

                    themPhieuNhap(pn);

                    if (soLuong > truocDo && data.length == 10
                            && Boolean.parseBoolean(data[9])) {

                        phieuDaCapNhatTonKho.add(
                            data[1].toLowerCase()
                        );
                    }
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
                    System.out.println(
                        "Dong du lieu khong hop le: " + line
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Du lieu so khong hop le: " + line
                );
            }
        }

        System.out.println("Doc file phieu nhap hoan tat!");
    }

    // 15. Lay phieu nhap theo ma
    private PhieuNhap layPhieuNhapNoiBo(String maPN) {

        if (maPN == null) {
            return null;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i].getMaPN() != null
                    && danhSach[i].getMaPN().equalsIgnoreCase(maPN)) {
                return danhSach[i];
            }
        }

        return null;
    }

    // 16. Getters
    public PhieuNhap[] getDanhSach() {
        return danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }
}
