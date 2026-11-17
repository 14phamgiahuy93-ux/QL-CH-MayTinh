package manager;
import entity.NhanVien;
import entity.QuanLy;
import interfaces.IFileIO;
import utils.FileUtil;
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

    // 1. Them nhan vien
    public boolean themNhanVien(NhanVien nv) {

        if (nv == null) {
            return false;
        }

        if (soLuong == danhSach.length) {
            danhSach = java.util.Arrays.copyOf(danhSach, danhSach.length * 2);
            }

        // Kiem tra trung ma nhan vien
        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaNV(), nv.getMaNV())) {
                return false;
            }
        }

        danhSach[soLuong++] = nv;
        return true;
    }

    // 2. Sua thong tin nhan vien
    public boolean suaThongTinNhanVien(String maNV, int luaChon, String giaTriMoi) {

        for (int i = 0; i < soLuong; i++) {

            NhanVien nv = danhSach[i];

            if (Objects.equals(nv.getMaNV(), maNV)) {

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
                            nv.setLuongCoBan(Double.parseDouble(giaTriMoi));
                            break;

                        case 6:
                            nv.setPhuCap(Double.parseDouble(giaTriMoi));
                            break;

                        case 7:
                            nv.setTienThuong(Double.parseDouble(giaTriMoi));
                            break;

                        case 8:
                            if (nv instanceof QuanLy) {
                                ((QuanLy) nv).setPhuCapChucVu(
                                    Double.parseDouble(giaTriMoi)
                                );
                            } else {
                                return false;
                            }
                            break;

                        default:
                            return false;
                    }

                    return true;

                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }

        return false;
    }

    // 3. Xoa nhan vien
    public boolean xoaNhanVien(String maNV) {

        for (int i = 0; i < soLuong; i++) {

            if (Objects.equals(danhSach[i].getMaNV(), maNV)) {

                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }

                danhSach[--soLuong] = null;
                return true;
            }
        }

        return false;
    }

    // 4. Tim nhan vien
    public boolean timNhanVien(String maNV) {

        for (int i = 0; i < soLuong; i++) {

            if (Objects.equals(danhSach[i].getMaNV(), maNV)) {

                System.out.println("Tim thay nhan vien!");
                danhSach[i].hienThiThongTin();

                return true;
            }
        }

        System.out.println("Khong tim thay nhan vien!");
        return false;
    }

    // 5. Hien thi danh sach
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

    // 6. Ghi file
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

    // 7. Doc file
    @Override
    public void docFile(String tenFile) {

        String noiDung = FileUtil.docChuoi(tenFile);

        soLuong = 0;
        danhSach = new NhanVien[10];

        if (noiDung == null || noiDung.trim().isEmpty()) {
            return;
        }

        String[] cacDong = noiDung.split("\\r?\\n");

        for (String dong : cacDong) {

            String[] data = dong.split(";", -1);

            if (data.length != 9) {
                continue;
            }

            // Chi doc nhan vien QuanLy
            if (data[8].isEmpty()) {
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

    // 8. Lay nhan vien theo ma
    public NhanVien layNhanVien(String maNV) {
            if (maNV == null) {
                return null;
            }
        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i].getMaNV().equalsIgnoreCase(maNV)) {
                return danhSach[i];
            }
        }

        return null;
    }
    public void themNhieuNhanVien(NhanVien[] ds, int k) {

    if (ds == null || k < 0 || k > ds.length) {
        System.out.println("Danh sach khong hop le!");
        return;
    }

    for (int i = 0; i < k; i++) {
        if (themNhanVien(ds[i])) {
            System.out.println("Them nhan vien thanh cong!");
        } else {
            System.out.println("Khong the them nhan vien thu " + (i + 1));
        }
    }
}
    public void timNhanVienNhieuTieuChi(String tuKhoa) {

    if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
        System.out.println("Tu khoa khong hop le!");
        return;
    }

    tuKhoa = tuKhoa.trim();
    boolean timThay = false;

    // Tim chinh xac
    for (int i = 0; i < soLuong; i++) {
        NhanVien nv = danhSach[i];

        if (nv.getMaNV().equalsIgnoreCase(tuKhoa)
                || nv.getHoTen().equalsIgnoreCase(tuKhoa)
                || nv.getSoDienThoai().equalsIgnoreCase(tuKhoa)
                || nv.getEmail().equalsIgnoreCase(tuKhoa)) {

            nv.hienThiThongTin();
            timThay = true;
        }
    }

    // Tim gan dung neu khong co ket qua chinh xac
    if (!timThay) {
        for (int i = 0; i < soLuong; i++) {
            NhanVien nv = danhSach[i];

            if (nv.getMaNV().toLowerCase().contains(tuKhoa.toLowerCase())
                    || nv.getHoTen().toLowerCase().contains(tuKhoa.toLowerCase())
                    || nv.getSoDienThoai().contains(tuKhoa)
                    || nv.getEmail().toLowerCase().contains(tuKhoa.toLowerCase())) {

                nv.hienThiThongTin();
                timThay = true;
            }
        }
    }

    if (!timThay) {
        System.out.println("Khong tim thay nhan vien!");
    }
}
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

    System.out.println("So luong quan ly: " + soQuanLy);
    System.out.println("So luong nhan vien khac: " + soNhanVienKhac);
    System.out.println("Tong so nhan vien: " + soLuong);
}
}
