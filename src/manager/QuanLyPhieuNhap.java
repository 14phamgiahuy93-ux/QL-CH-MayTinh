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
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class QuanLyPhieuNhap implements IFileIO {

    private PhieuNhap[] danhSach;
    private int soLuong;

    private QuanLySanPham qlSP;
    private QuanLyNhanVien qlNV;

    // Luu ma phieu da cap nhat ton kho
    private Set<String> phieuDaCapNhatTonKho;

    // Constructor mac dinh
    public QuanLyPhieuNhap() {
        danhSach = new PhieuNhap[100];
        soLuong = 0;
        phieuDaCapNhatTonKho = new HashSet<>();
    }

    // Constructor co tham so
    public QuanLyPhieuNhap(QuanLySanPham qlSP, QuanLyNhanVien qlNV) {
        this();
        this.qlSP = qlSP;
        this.qlNV = qlNV;
    }

    // Chuan hoa ma phieu
    private String chuanHoaMa(String maPN) {

        if (maPN == null) {
            return "";
        }

        return maPN.trim().toLowerCase(Locale.ROOT);
    }

    // 1. Lay phieu nhap theo ma
    private PhieuNhap layPhieuNhapNoiBo(String maPN) {

        if (maPN == null || maPN.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null
                    && danhSach[i].getMaPN() != null
                    && danhSach[i].getMaPN().trim()
                            .equalsIgnoreCase(maPN.trim())) {

                return danhSach[i];
            }
        }

        return null;
    }

    // 2. Them mot phieu nhap
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
            danhSach = Arrays.copyOf(
                    danhSach, danhSach.length * 2
            );
        }

        danhSach[soLuong++] = pn;

        System.out.println("Them phieu nhap thanh cong!");
    }

    // 3. Them nhieu phieu nhap
    public void themNhieuPhieuNhap(PhieuNhap[] ds, int k) {

        if (ds == null || k <= 0 || k > ds.length) {
            System.out.println(
                    "Danh sach hoac so luong khong hop le!"
            );
            return;
        }

        int soThem = 0;

        for (int i = 0; i < k; i++) {

            int truocDo = soLuong;

            themPhieuNhap(ds[i]);

            if (soLuong > truocDo) {
                soThem++;
            }
        }

        System.out.println("Da them " + soThem + " phieu nhap!");
    }

    // 4. Xoa phieu nhap
    public void xoaPhieuNhap(String maPN) {

        PhieuNhap pn = layPhieuNhapNoiBo(maPN);

        if (pn == null) {
            System.out.println("Khong tim thay phieu nhap!");
            return;
        }

        String maChuanHoa = chuanHoaMa(pn.getMaPN());

        // Neu da cap nhat ton kho thi hoan tac truoc khi xoa
        if (phieuDaCapNhatTonKho.contains(maChuanHoa)) {

            if (!hoanTacTonKho(pn)) {
                System.out.println(
                        "Khong the xoa vi khong the hoan tac ton kho!"
                );
                return;
            }

            phieuDaCapNhatTonKho.remove(maChuanHoa);
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] == pn) {

                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }

                danhSach[--soLuong] = null;

                System.out.println("Xoa phieu nhap thanh cong!");
                return;
            }
        }
    }

    // 5. Tim phieu nhap
    public void timPhieuNhap(String maPN) {

        PhieuNhap pn = layPhieuNhapNoiBo(maPN);

        if (pn == null) {
            System.out.println("Khong tim thay phieu nhap!");
        } else {
            pn.hienThi();
        }
    }

    // 6. Tim kiem nhieu tieu chi
    public void timPhieuNhapNhieuTieuChi(String tuKhoa) {

        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            System.out.println("Tu khoa khong hop le!");
            return;
        }

        String tuKhoaChuan =
                tuKhoa.trim().toLowerCase(Locale.ROOT);

        boolean timThay = false;

        // Tim chinh xac truoc
        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            if (pn == null) {
                continue;
            }

            boolean khopMa = pn.getMaPN() != null
                    && pn.getMaPN().equalsIgnoreCase(tuKhoa.trim());

            boolean khopNgay = pn.getNgayNhap() != null
                    && pn.getNgayNhap().equalsIgnoreCase(tuKhoa.trim());

            boolean khopNCC = pn.getNhaCungCap() != null
                    && pn.getNhaCungCap().getTenNCC() != null
                    && pn.getNhaCungCap().getTenNCC()
                            .equalsIgnoreCase(tuKhoa.trim());

            boolean khopNV = pn.getNhanVien() != null
                    && pn.getNhanVien().getHoTen() != null
                    && pn.getNhanVien().getHoTen()
                            .equalsIgnoreCase(tuKhoa.trim());

            if (khopMa || khopNgay || khopNCC || khopNV) {
                pn.hienThi();
                timThay = true;
            }
        }

        // Neu khong co ket qua chinh xac thi tim gan dung
        if (!timThay) {

            for (int i = 0; i < soLuong; i++) {

                PhieuNhap pn = danhSach[i];

                if (pn == null) {
                    continue;
                }

                boolean khopMa = pn.getMaPN() != null
                        && pn.getMaPN().toLowerCase(Locale.ROOT)
                                .contains(tuKhoaChuan);

                boolean khopNgay = pn.getNgayNhap() != null
                        && pn.getNgayNhap().toLowerCase(Locale.ROOT)
                                .contains(tuKhoaChuan);

                boolean khopNCC = pn.getNhaCungCap() != null
                        && pn.getNhaCungCap().getTenNCC() != null
                        && pn.getNhaCungCap().getTenNCC()
                                .toLowerCase(Locale.ROOT)
                                .contains(tuKhoaChuan);

                boolean khopNV = pn.getNhanVien() != null
                        && pn.getNhanVien().getHoTen() != null
                        && pn.getNhanVien().getHoTen()
                                .toLowerCase(Locale.ROOT)
                                .contains(tuKhoaChuan);

                if (khopMa || khopNgay || khopNCC || khopNV) {
                    pn.hienThi();
                    timThay = true;
                }
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay phieu nhap!");
        }
    }

    // 7. Sua ngay nhap
    public void suaNgayNhap(String maPN, String ngayNhapMoi) {

        if (ngayNhapMoi == null
                || ngayNhapMoi.trim().isEmpty()) {

            System.out.println("Ngay nhap khong hop le!");
            return;
        }

        PhieuNhap pn = layPhieuNhapNoiBo(maPN);

        if (pn == null) {
            System.out.println("Khong tim thay phieu nhap!");
            return;
        }

        pn.setNgayNhap(ngayNhapMoi.trim());

        System.out.println("Cap nhat ngay nhap thanh cong!");
    }

    // 8. Hien thi danh sach
    public void hienThiDanhSach() {

        if (soLuong == 0) {
            System.out.println("Danh sach phieu nhap rong!");
            return;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null) {
                danhSach[i].hienThi();
            }
        }
    }

    // 9. Thong ke tong tien nhap
    public void thongKeTienNhap() {

        double tongTien = 0;

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null) {
                tongTien += danhSach[i].tinhTongTienNhap();
            }
        }

        System.out.printf(
                "Tong tien nhap: %.2f VND%n", tongTien
        );
    }

    // 10. Thong ke theo nha cung cap
    public void thongKeTheoNhaCungCap() {

        Map<String, Double> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            if (pn == null || pn.getNhaCungCap() == null) {
                continue;
            }

            String tenNCC = pn.getNhaCungCap().getTenNCC();

            if (tenNCC == null) {
                continue;
            }

            double tien = pn.tinhTongTienNhap();

            thongKe.put(
                    tenNCC,
                    thongKe.getOrDefault(tenNCC, 0.0) + tien
            );
        }

        System.out.println("THONG KE THEO NHA CUNG CAP:");

        for (Map.Entry<String, Double> entry
                : thongKe.entrySet()) {

            System.out.printf(
                    "%s: %.2f VND%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }

    // 11. Thong ke theo nhan vien
    public void thongKeTheoNhanVien() {

        Map<String, Double> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            if (pn == null || pn.getNhanVien() == null) {
                continue;
            }

            String tenNV = pn.getNhanVien().getHoTen();

            if (tenNV == null) {
                continue;
            }

            double tien = pn.tinhTongTienNhap();

            thongKe.put(
                    tenNV,
                    thongKe.getOrDefault(tenNV, 0.0) + tien
            );
        }

        System.out.println("THONG KE THEO NHAN VIEN:");

        for (Map.Entry<String, Double> entry
                : thongKe.entrySet()) {

            System.out.printf(
                    "%s: %.2f VND%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }

    // 12. Cap nhat ton kho theo phieu nhap
    public void capNhatTonKhoTheoPhieuNhap(String maPN) {

        if (qlSP == null) {
            System.out.println(
                    "Chua khoi tao quan ly san pham!"
            );
            return;
        }

        PhieuNhap pn = layPhieuNhapNoiBo(maPN);

        if (pn == null) {
            System.out.println("Khong tim thay phieu nhap!");
            return;
        }

        String maChuanHoa = chuanHoaMa(pn.getMaPN());

        if (phieuDaCapNhatTonKho.contains(maChuanHoa)) {
            System.out.println(
                    "Phieu nhap nay da duoc cap nhat ton kho!"
            );
            return;
        }

        ChiTietPhieuNhap[] dsCT = pn.getDanhSachChiTiet();
        int n = pn.getSoLuongChiTiet();

        if (dsCT == null || n <= 0 || n > dsCT.length) {
            System.out.println(
                    "Phieu nhap chua co chi tiet hop le!"
            );
            return;
        }

        // Gom so luong theo ma san pham
        Map<String, Integer> tongSoLuong = new HashMap<>();

        for (int i = 0; i < n; i++) {

            ChiTietPhieuNhap ct = dsCT[i];

            if (ct == null || ct.getSanPham() == null
                    || ct.getSanPham().getMaSP() == null
                    || ct.getSoLuong() <= 0) {

                System.out.println(
                        "Chi tiet phieu nhap khong hop le!"
                );
                return;
            }

            String maSP = ct.getSanPham().getMaSP();

            int cu = tongSoLuong.getOrDefault(maSP, 0);

            if (cu > Integer.MAX_VALUE - ct.getSoLuong()) {
                System.out.println("So luong vuot gioi han!");
                return;
            }

            tongSoLuong.put(maSP, cu + ct.getSoLuong());
        }

        // Kiem tra toan bo san pham truoc khi cap nhat
        for (Map.Entry<String, Integer> entry
                : tongSoLuong.entrySet()) {

            SanPham sp = qlSP.laySanPham(entry.getKey());

            if (sp == null) {
                System.out.println(
                        "San pham khong ton tai trong kho!"
                );
                return;
            }

            if (sp.getSoLuongTon() < 0
                    || sp.getSoLuongTon()
                    > Integer.MAX_VALUE - entry.getValue()) {

                System.out.println(
                        "So luong ton kho khong hop le!"
                );
                return;
            }
        }

        // Cap nhat ton kho
        for (Map.Entry<String, Integer> entry
                : tongSoLuong.entrySet()) {

            SanPham sp = qlSP.laySanPham(entry.getKey());

            sp.setSoLuongTon(
                    sp.getSoLuongTon() + entry.getValue()
            );
        }

        phieuDaCapNhatTonKho.add(maChuanHoa);

        System.out.println("Cap nhat ton kho thanh cong!");
    }

    // 13. Hoan tac ton kho
    private boolean hoanTacTonKho(PhieuNhap pn) {

        if (pn == null || qlSP == null) {
            return false;
        }

        ChiTietPhieuNhap[] dsCT = pn.getDanhSachChiTiet();
        int n = pn.getSoLuongChiTiet();

        if (dsCT == null || n < 0 || n > dsCT.length) {
            return false;
        }

        Map<String, Integer> tongSoLuong = new HashMap<>();

        // Gom so luong theo ma san pham
        for (int i = 0; i < n; i++) {

            ChiTietPhieuNhap ct = dsCT[i];

            if (ct == null || ct.getSanPham() == null
                    || ct.getSanPham().getMaSP() == null
                    || ct.getSoLuong() <= 0) {

                return false;
            }

            String maSP = ct.getSanPham().getMaSP();

            int cu = tongSoLuong.getOrDefault(maSP, 0);

            if (cu > Integer.MAX_VALUE - ct.getSoLuong()) {
                return false;
            }

            tongSoLuong.put(maSP, cu + ct.getSoLuong());
        }

        // Kiem tra ton kho truoc khi hoan tac
        for (Map.Entry<String, Integer> entry
                : tongSoLuong.entrySet()) {

            SanPham sp = qlSP.laySanPham(entry.getKey());

            if (sp == null
                    || sp.getSoLuongTon() < entry.getValue()) {

                System.out.println(
                        "Khong du ton kho de hoan tac!"
                );
                return false;
            }
        }

        // Tru ton kho
        for (Map.Entry<String, Integer> entry
                : tongSoLuong.entrySet()) {

            SanPham sp = qlSP.laySanPham(entry.getKey());

            sp.setSoLuongTon(
                    sp.getSoLuongTon() - entry.getValue()
            );
        }

        return true;
    }

    // 14. Ghi file
    @Override
    public void ghiFile(String tenFile) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {

            PhieuNhap pn = danhSach[i];

            if (pn == null || pn.getMaPN() == null) {
                continue;
            }

            String maPN = pn.getMaPN();

            String ngayNhap = pn.getNgayNhap() == null
                    ? "" : pn.getNgayNhap();

            String maNCC = pn.getNhaCungCap() == null
                    || pn.getNhaCungCap().getMaNCC() == null
                    ? "" : pn.getNhaCungCap().getMaNCC();

            String maNV = pn.getNhanVien() == null
                    || pn.getNhanVien().getMaNV() == null
                    ? "" : pn.getNhanVien().getMaNV();

            boolean daCapNhat = phieuDaCapNhatTonKho.contains(
                    chuanHoaMa(maPN)
            );

            sb.append("P|")
                    .append(maPN).append("|")
                    .append(ngayNhap).append("|")
                    .append(maNCC).append("|")
                    .append(maNV).append("|")
                    .append(daCapNhat)
                    .append("\n");

            ChiTietPhieuNhap[] dsCT = pn.getDanhSachChiTiet();

            if (dsCT == null) {
                continue;
            }

            int n = Math.min(
                    pn.getSoLuongChiTiet(),
                    dsCT.length
            );

            for (int j = 0; j < n; j++) {

                ChiTietPhieuNhap ct = dsCT[j];

                if (ct == null || ct.getSanPham() == null
                        || ct.getSanPham().getMaSP() == null) {
                    continue;
                }

                sb.append("C|")
                        .append(maPN).append("|")
                        .append(ct.getSanPham().getMaSP()).append("|")
                        .append(ct.getSoLuong()).append("|")
                        .append(ct.getDonGiaNhap())
                        .append("\n");
            }
        }

        FileUtil.ghiChuoi(tenFile, sb.toString());

        System.out.println("Ghi file phieu nhap thanh cong!");
    }

    // 15. Doc file
    @Override
    public void docFile(String tenFile) {

        if (qlSP == null || qlNV == null) {
            System.out.println(
                    "Can khoi tao quan ly san pham va nhan vien truoc!"
            );
            return;
        }

        String noiDung = FileUtil.docChuoi(tenFile);

        if (noiDung == null || noiDung.trim().isEmpty()) {
            System.out.println(
                    "File rong hoac khong doc duoc!"
            );
            return;
        }

        // Luu danh sach cu
        PhieuNhap[] danhSachCu = danhSach;
        int soLuongCu = soLuong;

        Set<String> trangThaiCu =
                new HashSet<>(phieuDaCapNhatTonKho);

        // Tao danh sach moi
        danhSach = new PhieuNhap[100];
        soLuong = 0;
        phieuDaCapNhatTonKho.clear();

        String[] cacDong = noiDung.split("\\R");

        // Buoc 1: Doc thong tin phieu nhap
        for (String line : cacDong) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] p = line.split("\\|", -1);

            if (p.length >= 5 && p[0].equals("P")) {

                try {

                    String maPN = p[1];
                    String ngayNhap = p[2];
                    String maNCC = p[3];
                    String maNV = p[4];

                    if (maPN.trim().isEmpty()
                            || ngayNhap.trim().isEmpty()) {
                        continue;
                    }

                    NhanVien nv = qlNV.layNhanVien(maNV);

                    if (nv == null) {
                        System.out.println(
                                "Khong tim thay nhan vien: " + maNV
                        );
                        continue;
                    }

                    NhaCungCap ncc = null;

                    if (!maNCC.trim().isEmpty()) {
                        ncc = new NhaCungCap();
                        ncc.setMaNCC(maNCC);
                    }

                    PhieuNhap pn = new PhieuNhap(
                            maPN, ngayNhap, ncc, nv
                    );

                    int truocDo = soLuong;

                    themPhieuNhap(pn);

                    // Luu trang thai cap nhat ton kho
                    if (soLuong > truocDo
                            && p.length >= 6
                            && Boolean.parseBoolean(p[5])) {

                        phieuDaCapNhatTonKho.add(
                                chuanHoaMa(maPN)
                        );
                    }

                } catch (Exception e) {
                    System.out.println(
                            "Loi doc phieu nhap: " + line
                    );
                }
            }
        }

        // Buoc 2: Doc chi tiet phieu nhap
        for (String line : cacDong) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] p = line.split("\\|", -1);

            if (p.length == 5 && p[0].equals("C")) {

                try {

                    String maPN = p[1];
                    String maSP = p[2];

                    int soLuongNhap =
                            Integer.parseInt(p[3]);

                    double donGiaNhap =
                            Double.parseDouble(p[4]);

                    if (soLuongNhap <= 0 || donGiaNhap < 0) {
                        System.out.println(
                                "Chi tiet phieu nhap khong hop le!"
                        );
                        continue;
                    }

                    PhieuNhap pn = layPhieuNhapNoiBo(maPN);
                    SanPham sp = qlSP.laySanPham(maSP);

                    if (pn == null || sp == null) {
                        System.out.println(
                                "Khong tim thay phieu nhap hoac san pham!"
                        );
                        continue;
                    }

                    ChiTietPhieuNhap ct = new ChiTietPhieuNhap(
                            sp, soLuongNhap, donGiaNhap
                    );

                    pn.themChiTiet(ct);

                } catch (Exception e) {
                    System.out.println(
                            "Loi doc chi tiet phieu nhap: " + line
                    );
                }
            }
        }

        // Neu khong doc duoc phieu nhap nao thi khoi phuc du lieu cu
        if (soLuong == 0 && soLuongCu > 0) {

            danhSach = danhSachCu;
            soLuong = soLuongCu;

            phieuDaCapNhatTonKho.clear();
            phieuDaCapNhatTonKho.addAll(trangThaiCu);

            System.out.println(
                    "Khong doc duoc phieu nhap hop le. Giu danh sach cu."
            );
            return;
        }

        System.out.println("Doc file phieu nhap hoan tat!");
    }

    // Getters va Setters
    public PhieuNhap[] getDanhSach() {
        return danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setQlSP(QuanLySanPham qlSP) {
        this.qlSP = qlSP;
    }

    public void setQlNV(QuanLyNhanVien qlNV) {
        this.qlNV = qlNV;
    }
}
