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

    // Them nhan vien
    public boolean themNhanVien(NhanVien nv) {
        if (nv == null || soLuong == danhSach.length) {
            return false;
        }

        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaNV(), nv.getMaNV())) {
                return false;
            }
        }

        danhSach[soLuong] = nv;
        soLuong++;
        return true;
    }

    // Sua thong tin nhan vien
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
                        default:
                            return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }

                return true;
            }
        }

        return false;
    }

    // Xoa nhan vien
    public boolean xoaNhanVien(String maNV) {
        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaNV(), maNV)) {
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }

                danhSach[soLuong - 1] = null;
                soLuong--;
                return true;
            }
        }

        return false;
    }

    // Tim nhan vien
    public boolean timNhanVien(String maNV) {
        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaNV(), maNV)) {
                danhSach[i].hienThiThongTin();
                return true;
            }
        }

        return false;
    }

    // Hien thi danh sach
    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sach nhan vien dang trong.");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhan vien thu " + (i + 1) + ":");
            danhSach[i].hienThiThongTin();
            System.out.println("--------------------");
        }
    }

    // Ghi file
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
                   .append(nv.getTienThuong());

            // Neu la QuanLy thi ghi them phu cap chuc vu
            if (nv instanceof QuanLy) {
                QuanLy ql = (QuanLy) nv;
                noiDung.append(";").append(ql.getPhuCapChucVu());
            } else {
                noiDung.append(";");
                // Nhan vien thuong khong co phu cap chuc vu
            }

            if (i < soLuong - 1) {
                noiDung.append(System.lineSeparator());
            }
        }

        FileUtil.ghiChuoi(tenFile, noiDung.toString());
    }

    // Doc file
    @Override
    public void docFile(String tenFile) {
        String noiDung = FileUtil.docChuoi(tenFile);

        soLuong = 0;

        for (int i = 0; i < danhSach.length; i++) {
            danhSach[i] = null;
        }

        if (noiDung == null || noiDung.isEmpty()) {
            System.out.println("File nhan vien rong.");
            return;
        }

        String[] cacDong = noiDung.split("\\R");

        for (String dong : cacDong) {
            if (dong.isEmpty()) {
                continue;
            }

            String[] thongTin = dong.split(";", -1);

            try {
                if (thongTin.length != 9) {
                    System.out.println("Dong du lieu khong hop le: " + dong);
                    continue;
                }

                NhanVien nv;

                if (thongTin[8].isEmpty()) {
                    nv = new NhanVien(
                        thongTin[0],
                        thongTin[1],
                        thongTin[2],
                        thongTin[3],
                        thongTin[4],
                        Double.parseDouble(thongTin[5]),
                        Double.parseDouble(thongTin[6]),
                        Double.parseDouble(thongTin[7])
                    );
                } else {
                    nv = new QuanLy(
                        thongTin[0],
                        thongTin[1],
                        thongTin[2],
                        thongTin[3],
                        thongTin[4],
                        Double.parseDouble(thongTin[5]),
                        Double.parseDouble(thongTin[6]),
                        Double.parseDouble(thongTin[7]),
                        Double.parseDouble(thongTin[8])
                    );
                }

                if (!themNhanVien(nv)) {
                    System.out.println("Khong the them nhan vien: " + nv.getMaNV());
                }

            } catch (NumberFormatException e) {
                System.out.println("Du lieu so khong hop le: " + dong);
            }
        }
    }
}
