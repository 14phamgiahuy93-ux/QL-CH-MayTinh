package manager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import entity.*;
import interfaces.IFileIO;
import utils.FileUtil;
import utils.Validator;

public class QuanLyHoaDon implements IFileIO {

    private HoaDon[] danhSach;
    private int soLuong;

    private QuanLySanPham qlsp;
    private QuanLyNhanVien qlnv;
    private QuanLyKhachHang qlkh;

    // Constructor
    public QuanLyHoaDon() {
        danhSach = new HoaDon[10];
        soLuong = 0;
    }

    public QuanLyHoaDon(
            QuanLySanPham qlsp,
            QuanLyNhanVien qlnv,
            QuanLyKhachHang qlkh) {

        this();
        this.qlsp = qlsp;
        this.qlnv = qlnv;
        this.qlkh = qlkh;
    }

    // Getters va setters
    public HoaDon[] getDanhSach() {
        return danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setDanhSach(HoaDon[] danhSach) {

        if (danhSach == null) {
            this.danhSach = new HoaDon[10];
            this.soLuong = 0;
            return;
        }

        int dem = 0;

        for (HoaDon hd : danhSach) {
            if (hd != null) {
                dem++;
            }
        }

        this.danhSach = new HoaDon[Math.max(10, dem)];
        this.soLuong = 0;

        for (HoaDon hd : danhSach) {
            if (hd != null) {
                this.danhSach[soLuong++] = hd;
            }
        }
    }

    // 1. Lay hoa don theo ma
    public HoaDon layHoaDon(String maHD) {

        if (maHD == null || maHD.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null
                    && danhSach[i].getMaHD() != null
                    && danhSach[i].getMaHD()
                            .equalsIgnoreCase(maHD.trim())) {

                return danhSach[i];
            }
        }

        return null;
    }

    // 2. Them hoa don
    public void themHoaDon(HoaDon hd) {

        if (hd == null || hd.getMaHD() == null
                || hd.getMaHD().trim().isEmpty()) {

            System.out.println("Hoa don khong hop le!");
            return;
        }

        if (layHoaDon(hd.getMaHD()) != null) {
            System.out.println("Ma hoa don da ton tai!");
            return;
        }

        if (soLuong == danhSach.length) {
            danhSach = Arrays.copyOf(
                    danhSach, danhSach.length * 2);
        }

        danhSach[soLuong++] = hd;

        System.out.println("Them hoa don thanh cong!");
    }

    // 3. Them nhieu hoa don
    public void themNhieuHoaDon(HoaDon[] ds, int k) {

        if (ds == null || k <= 0 || k > ds.length) {
            System.out.println("Danh sach khong hop le!");
            return;
        }

        int soThem = 0;

        for (int i = 0; i < k; i++) {

            int truocDo = soLuong;

            themHoaDon(ds[i]);

            if (soLuong > truocDo) {
                soThem++;
            }
        }

        System.out.println("Da them " + soThem + " hoa don!");
    }

    // 4. Xoa hoa don
    public void xoaHoaDon(String maHD) {

        if (maHD == null || maHD.trim().isEmpty()) {
            System.out.println("Ma hoa don khong hop le!");
            return;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null
                    && danhSach[i].getMaHD() != null
                    && danhSach[i].getMaHD()
                            .equalsIgnoreCase(maHD.trim())) {

                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }

                danhSach[--soLuong] = null;

                System.out.println("Xoa hoa don thanh cong!");
                return;
            }
        }

        System.out.println("Khong tim thay hoa don!");
    }

    // 5. Tim hoa don theo ma
    public void timHoaDon(String maHD) {

        HoaDon hd = layHoaDon(maHD);

        if (hd != null) {
            System.out.println("Tim thay hoa don!");
            hd.hienThi();
        } else {
            System.out.println("Khong tim thay hoa don!");
        }
    }

    // 6. Sua hoa don
    public void suaHoaDon(String maHD) {

        HoaDon hd = layHoaDon(maHD);

        if (hd == null) {
            System.out.println("Khong tim thay hoa don!");
            return;
        }

        if (qlnv == null || qlkh == null) {
            System.out.println("Chua khoi tao cac lop quan ly!");
            return;
        }

        String ngayLapMoi =
                Validator.readString("Nhap ngay lap moi: ");

        String maNVMoi =
                Validator.readString("Nhap ma nhan vien moi: ");

        String maKHMoi =
                Validator.readString("Nhap ma khach hang moi: ");

        NhanVien nvMoi = qlnv.layNhanVien(maNVMoi);
        KhachHang khMoi = qlkh.layKhachHang(maKHMoi);

        if (nvMoi == null) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }

        if (khMoi == null) {
            System.out.println("Khong tim thay khach hang!");
            return;
        }

        if (ngayLapMoi == null || ngayLapMoi.trim().isEmpty()) {
            System.out.println("Ngay lap khong hop le!");
            return;
        }

        hd.setNgayLap(ngayLapMoi);
        hd.setNhanVien(nvMoi);
        hd.setKhachHang(khMoi);

        System.out.println("Sua hoa don thanh cong!");
    }

    // 7. Tim hoa don theo nhieu tieu chi
    public void timHoaDonNhieuTieuChi(String tuKhoa) {

        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            System.out.println("Tu khoa khong hop le!");
            return;
        }

        tuKhoa = tuKhoa.trim();
        boolean timThay = false;

        // Tim chinh xac
        for (int i = 0; i < soLuong; i++) {

            HoaDon hd = danhSach[i];

            if (hd == null) {
                continue;
            }

            String ma = hd.getMaHD() == null
                    ? "" : hd.getMaHD();

            String ngay = hd.getNgayLap() == null
                    ? "" : hd.getNgayLap();

            String tenNV = hd.getNhanVien() == null
                    || hd.getNhanVien().getHoTen() == null
                    ? "" : hd.getNhanVien().getHoTen();

            String tenKH = hd.getKhachHang() == null
                    || hd.getKhachHang().getHoTen() == null
                    ? "" : hd.getKhachHang().getHoTen();

            if (ma.equalsIgnoreCase(tuKhoa)
                    || ngay.equalsIgnoreCase(tuKhoa)
                    || tenNV.equalsIgnoreCase(tuKhoa)
                    || tenKH.equalsIgnoreCase(tuKhoa)) {

                System.out.println("Tim thay chinh xac:");
                hd.hienThi();
                timThay = true;
            }
        }

        if (timThay) {
            return;
        }

        // Tim gan dung
        String tuKhoaLower = tuKhoa.toLowerCase();

        for (int i = 0; i < soLuong; i++) {

            HoaDon hd = danhSach[i];

            if (hd == null) {
                continue;
            }

            String ma = hd.getMaHD() == null
                    ? "" : hd.getMaHD();

            String ngay = hd.getNgayLap() == null
                    ? "" : hd.getNgayLap();

            String tenNV = hd.getNhanVien() == null
                    || hd.getNhanVien().getHoTen() == null
                    ? "" : hd.getNhanVien().getHoTen();

            String tenKH = hd.getKhachHang() == null
                    || hd.getKhachHang().getHoTen() == null
                    ? "" : hd.getKhachHang().getHoTen();

            if (ma.toLowerCase().contains(tuKhoaLower)
                    || ngay.toLowerCase().contains(tuKhoaLower)
                    || tenNV.toLowerCase().contains(tuKhoaLower)
                    || tenKH.toLowerCase().contains(tuKhoaLower)) {

                System.out.println("Tim thay gan dung:");
                hd.hienThi();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay hoa don!");
        }
    }

    // 8. Hien thi danh sach
    public void hienThiDanhSach() {

        if (soLuong == 0) {
            System.out.println("Danh sach hoa don rong!");
            return;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null) {
                danhSach[i].hienThi();
            }
        }
    }

    // 9. Thong ke doanh thu
    public void thongKeDoanhThu() {

        double doanhThu = 0;

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null) {
                doanhThu += danhSach[i].tinhTongTien();
            }
        }

        System.out.println("\n===== THONG KE DOANH THU =====");
        System.out.println("Tong so hoa don: " + soLuong);
        System.out.printf(
                "Tong doanh thu: %.2f VND%n", doanhThu);
    }

    // 10. Thong ke theo nhan vien
    public void thongKeTheoNhanVien() {

        if (soLuong == 0) {
            System.out.println("Danh sach hoa don rong!");
            return;
        }

        Map<String, double[]> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            HoaDon hd = danhSach[i];

            if (hd == null || hd.getNhanVien() == null) {
                continue;
            }

            String khoa = hd.getNhanVien().getMaNV()
                    + " - " + hd.getNhanVien().getHoTen();

            double[] soLieu = thongKe.get(khoa);

            if (soLieu == null) {
                soLieu = new double[2];
                thongKe.put(khoa, soLieu);
            }

            soLieu[0]++;
            soLieu[1] += hd.tinhTongTien();
        }

        System.out.println(
                "\n===== THONG KE THEO NHAN VIEN =====");

        for (Map.Entry<String, double[]> entry
                : thongKe.entrySet()) {

            double[] soLieu = entry.getValue();

            System.out.println("\nNhan vien: " + entry.getKey());
            System.out.println("So hoa don: " + (int) soLieu[0]);

            System.out.printf(
                    "Tong doanh thu: %.2f VND%n", soLieu[1]);
        }
    }

    // 11. Thong ke theo khach hang
    public void thongKeTheoKhachHang() {

        if (soLuong == 0) {
            System.out.println("Danh sach hoa don rong!");
            return;
        }

        Map<String, double[]> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            HoaDon hd = danhSach[i];

            if (hd == null || hd.getKhachHang() == null) {
                continue;
            }

            String khoa = hd.getKhachHang().getMaKH()
                    + " - " + hd.getKhachHang().getHoTen();

            double[] soLieu = thongKe.get(khoa);

            if (soLieu == null) {
                soLieu = new double[2];
                thongKe.put(khoa, soLieu);
            }

            soLieu[0]++;
            soLieu[1] += hd.tinhTongTien();
        }

        System.out.println(
                "\n===== THONG KE THEO KHACH HANG =====");

        for (Map.Entry<String, double[]> entry
                : thongKe.entrySet()) {

            double[] soLieu = entry.getValue();

            System.out.println("\nKhach hang: " + entry.getKey());
            System.out.println("So hoa don: " + (int) soLieu[0]);

            System.out.printf(
                    "Tong tien mua: %.2f VND%n", soLieu[1]);
        }
    }

    // 12. Ghi file
    @Override
    public void ghiFile(String tenFile) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {

            HoaDon hd = danhSach[i];

            if (hd == null
                    || hd.getMaHD() == null
                    || hd.getNhanVien() == null
                    || hd.getKhachHang() == null) {

                System.out.println("Bo qua hoa don khong hop le!");
                continue;
            }

            sb.append("H|")
                    .append(hd.getMaHD()).append("|")
                    .append(hd.getNgayLap()).append("|")
                    .append(hd.getNhanVien().getMaNV()).append("|")
                    .append(hd.getKhachHang().getMaKH())
                    .append("\n");

            ChiTietHoaDon[] dsCT = hd.getDanhSachChiTiet();

            if (dsCT == null) {
                continue;
            }

            for (int j = 0;
                    j < hd.getSoLuongChiTiet() && j < dsCT.length;
                    j++) {

                ChiTietHoaDon ct = dsCT[j];

                if (ct == null || ct.getSanPham() == null) {
                    continue;
                }

                sb.append("D|")
                        .append(hd.getMaHD()).append("|")
                        .append(ct.getSanPham().getMaSP()).append("|")
                        .append(ct.getSoLuong()).append("|")
                        .append(ct.getDonGia())
                        .append("\n");
            }
        }

        FileUtil.ghiChuoi(tenFile, sb.toString());

        System.out.println("Ghi file hoa don thanh cong!");
    }

    // 13. Doc file
    @Override
    public void docFile(String tenFile) {

        if (qlsp == null || qlnv == null || qlkh == null) {
            System.out.println(
                    "Chua khoi tao cac lop quan ly lien quan!");
            return;
        }

        String noiDung = FileUtil.docChuoi(tenFile);

        if (noiDung == null || noiDung.trim().isEmpty()) {
            System.out.println("File hoa don rong!");
            return;
        }

        HoaDon[] danhSachCu = danhSach;
        int soLuongCu = soLuong;

        danhSach = new HoaDon[10];
        soLuong = 0;

        String[] cacDong = noiDung.split("\\R");

        // Doc thong tin hoa don truoc
        for (String dong : cacDong) {

            if (dong.trim().isEmpty()) {
                continue;
            }

            String[] data = dong.split("\\|", -1);

            if (data.length == 5 && data[0].equals("H")) {

                try {

                    String maHD = data[1];
                    String ngayLap = data[2];
                    String maNV = data[3];
                    String maKH = data[4];

                    if (maHD.trim().isEmpty()
                            || ngayLap.trim().isEmpty()) {
                        continue;
                    }

                    NhanVien nv = qlnv.layNhanVien(maNV);
                    KhachHang kh = qlkh.layKhachHang(maKH);

                    if (nv == null || kh == null) {
                        System.out.println(
                                "Khong tim thay nhan vien hoac khach hang!");
                        continue;
                    }

                    HoaDon hd = new HoaDon();

                    hd.setMaHD(maHD);
                    hd.setNgayLap(ngayLap);
                    hd.setNhanVien(nv);
                    hd.setKhachHang(kh);

                    themHoaDon(hd);

                } catch (Exception e) {
                    System.out.println(
                            "Loi doc hoa don: " + dong);
                }
            }
        }

        // Doc chi tiet hoa don sau
        for (String dong : cacDong) {

            if (dong.trim().isEmpty()) {
                continue;
            }

            String[] data = dong.split("\\|", -1);

            if (data.length == 5 && data[0].equals("D")) {

                try {

                    String maHD = data[1];
                    String maSP = data[2];

                    int soLuongMua =
                            Integer.parseInt(data[3]);

                    double donGia =
                            Double.parseDouble(data[4]);

                    if (soLuongMua <= 0 || donGia < 0) {
                        System.out.println(
                                "Chi tiet hoa don khong hop le!");
                        continue;
                    }

                    HoaDon hd = layHoaDon(maHD);
                    SanPham sp = qlsp.laySanPham(maSP);

                    if (hd == null || sp == null) {
                        System.out.println(
                                "Khong tim thay hoa don hoac san pham!");
                        continue;
                    }

                    ChiTietHoaDon ct = new ChiTietHoaDon();

                    ct.setSanPham(sp);
                    ct.setSoLuong(soLuongMua);
                    ct.setDonGia(donGia);

                    hd.themChiTiet(ct);

                } catch (Exception e) {
                    System.out.println(
                            "Loi doc chi tiet hoa don: " + dong);
                }
            }
        }

        // Neu khong doc duoc hoa don nao thi giu danh sach cu
        if (soLuong == 0 && soLuongCu > 0) {
            danhSach = danhSachCu;
            soLuong = soLuongCu;

            System.out.println(
                    "Khong doc duoc hoa don hop le. Giu danh sach cu.");
            return;
        }

        System.out.println("Doc file hoa don hoan tat!");
    }
}
