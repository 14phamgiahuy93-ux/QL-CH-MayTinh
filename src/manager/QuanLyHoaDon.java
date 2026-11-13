
package manager;

import java.util.Arrays;
import entity.*;
import interfaces.IFileIO;
import utils.FileUtil;

public class QuanLyHoaDon implements IFileIO {

    private HoaDon[] danhSach;
    private int soLuong;

    // Cac lop quan ly lien quan
    private QuanLySanPham qlsp;
    private QuanLyNhanVien qlnv;
    private QuanLyKhachHang qlkh;

    // Constructor
    public QuanLyHoaDon() {
        danhSach = new HoaDon[0];
        soLuong = 0;
    }

    // Constructor co cac doi tuong quan ly
    public QuanLyHoaDon(QuanLySanPham qlsp,
                        QuanLyNhanVien qlnv,
                        QuanLyKhachHang qlkh) {
        this();
        this.qlsp = qlsp;
        this.qlnv = qlnv;
        this.qlkh = qlkh;
    }

    // Getter va Setter
    public HoaDon[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(HoaDon[] danhSach) {
        this.danhSach = danhSach;
        this.soLuong = danhSach.length;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    // 1. Them hoa don
    public void themHoaDon(HoaDon hd) {
        if (hd == null) {
            System.out.println("Hoa don khong hop le!");
            return;
        }

        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong] = hd;
        soLuong++;

        System.out.println("Them hoa don thanh cong!");
    }

    // 2. Xoa hoa don
    public void xoaHoaDon(String maHD) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHD().equals(maHD)) {

                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }

                soLuong--;
                danhSach = Arrays.copyOf(danhSach, soLuong);

                System.out.println("Xoa hoa don thanh cong!");
                return;
            }
        }

        System.out.println("Khong tim thay hoa don!");
    }

    // 3. Tim hoa don
    public void timHoaDon(String maHD) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHD().equals(maHD)) {
                System.out.println("Tim thay hoa don!");
                danhSach[i].hienThi();
                return;
            }
        }

        System.out.println("Khong tim thay hoa don!");
    }

    // Ham lay hoa don theo ma
    public HoaDon layHoaDon(String maHD) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaHD().equalsIgnoreCase(maHD)) {
                return danhSach[i];
            }
        }
        return null;
    }

    // 4. Hien thi danh sach
    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sach hoa don rong!");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            danhSach[i].hienThi();
        }
    }

    // 5. Thong ke doanh thu
    public void thongKeDoanhThu() {
        double doanhThu = 0;

        for (int i = 0; i < soLuong; i++) {
            doanhThu += danhSach[i].tinhTongTien();
        }

        System.out.println("Tong doanh thu: " + doanhThu);
    }

    // 6. GHI FILE
    @Override
    public void ghiFile(String tenFile) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {

            HoaDon hd = danhSach[i];

            // Ghi thong tin hoa don
            sb.append("H|")
              .append(hd.getMaHD()).append("|")
              .append(hd.getNgayLap()).append("|")
              .append(hd.getNhanVien().getMaNV()).append("|")
              .append(hd.getKhachHang().getMaKH())
              .append("\n");

            // Ghi chi tiet hoa don
            ChiTietHoaDon[] dsCT = hd.getDanhSachChiTiet();

            for (int j = 0; j < hd.getSoLuongChiTiet(); j++) {

                ChiTietHoaDon ct = dsCT[j];

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

    // 7. DOC FILE
    @Override
    public void docFile(String tenFile) {

        if (qlsp == null || qlnv == null || qlkh == null) {
            System.out.println("Chua khoi tao cac lop quan ly lien quan!");
            return;
        }

        String noiDung = FileUtil.docChuoi(tenFile);

        if (noiDung.isEmpty()) {
            System.out.println("File hoa don rong!");
            return;
        }

        // Xoa danh sach cu
        danhSach = new HoaDon[0];
        soLuong = 0;

        String[] cacDong = noiDung.split("\\R");

        for (String dong : cacDong) {

            if (dong.trim().isEmpty()) {
                continue;
            }

            String[] data = dong.split("\\|", -1);

            try {

                // Doc thong tin hoa don
                if (data[0].equals("H")) {

                    if (data.length < 5) {
                        continue;
                    }

                    String maHD = data[1];
                    String ngayLap = data[2];
                    String maNV = data[3];
                    String maKH = data[4];

                    NhanVien nv = qlnv.layNhanVien(maNV);
                    KhachHang kh = qlkh.layKhachHang(maKH);

                    if (nv == null || kh == null) {
                        System.out.println("Khong tim thay nhan vien hoac khach hang!");
                        continue;
                    }

                    HoaDon hd = new HoaDon();

                    hd.setMaHD(maHD);
                    hd.setNgayLap(ngayLap);
                    hd.setNhanVien(nv);
                    hd.setKhachHang(kh);

                    // Them hoa don vao mang
                    danhSach = Arrays.copyOf(danhSach, soLuong + 1);
                    danhSach[soLuong++] = hd;
                }

                // Doc chi tiet hoa don
                else if (data[0].equals("D")) {

                    if (data.length < 5) {
                        continue;
                    }

                    String maHD = data[1];
                    String maSP = data[2];

                    int soLuongMua = Integer.parseInt(data[3]);
                    double donGia = Double.parseDouble(data[4]);

                    HoaDon hd = layHoaDon(maHD);
                    SanPham sp = qlsp.laySanPham(maSP);

                    if (hd == null || sp == null) {
                        System.out.println("Khong tim thay hoa don hoac san pham!");
                        continue;
                    }

                    ChiTietHoaDon ct = new ChiTietHoaDon();

                    ct.setSanPham(sp);
                    ct.setSoLuong(soLuongMua);
                    ct.setDonGia(donGia);

                    hd.themChiTiet(ct);
                }

            } catch (Exception e) {
                System.out.println("Loi doc dong: " + dong);
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Doc file hoa don thanh cong!");
    }
}

