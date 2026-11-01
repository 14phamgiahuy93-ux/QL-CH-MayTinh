package manager;

import entity.NhanVien;
import java.util.Objects;

public class QuanLyNhanVien {
    private NhanVien[] danhSach;
    private int soLuong;

    public QuanLyNhanVien() {
        danhSach = new NhanVien[10];
    }

    public int getSoLuong() {
        return soLuong;
    }

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

    public boolean suaThongTinNhanVien(String maNV, int luaChon, String giaTriMoi) {
        for (int i = 0; i < soLuong; i++) {
            NhanVien nv = danhSach[i];

            if (Objects.equals(nv.getMaNV(), maNV)) {
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
                return true;
            }
        }
        return false;
    }

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

    public boolean timNhanVien(String maNV) {
        for (int i = 0; i < soLuong; i++){
            if (Objects.equals(danhSach[i].getMaNV(), maNV)) {
                danhSach[i].hienThiThongTin();
                return true;
            }
        }
        return false;
    }

 public void hienThiDanhSach() {
    if (soLuong == 0) {
        System.out.println("Danh sách nhân viên đang trống.");
        return;
    }

    for (int i = 0; i < soLuong; i++) {
        System.out.println("Nhân viên thứ " + (i + 1) + ":");
        danhSach[i].hienThiThongTin();
        System.out.println("--------------------");
    }
}
}