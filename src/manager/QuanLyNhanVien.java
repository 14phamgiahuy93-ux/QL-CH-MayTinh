package manager;

import entity.NhanVien;
import entity.QuanLy;
import interfaces.IFileIO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class QuanLyNhanVien implements IFileIO {
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

    @Override
    public void ghiFile(String tenFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < soLuong; i++) {
                QuanLy nv = (QuanLy) danhSach[i];

                writer.write(nv.getHoTen() + ";"
                        + nv.getNgaySinh() + ";"
                        + nv.getSoDienThoai() + ";"
                        + nv.getEmail() + ";"
                        + nv.getMaNV() + ";"
                        + nv.getLuongCoBan() + ";"
                        + nv.getPhuCap() + ";"
                        + nv.getTienThuong() + ";"
                        + nv.getPhuCapChucVu());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    @Override
    public void docFIle(String tenFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String dong;
            soLuong = 0;

            while ((dong = reader.readLine()) != null) {
                String[] thongTin = dong.split(";");
                if (thongTin.length != 9) {
                    System.out.println("Dòng dữ liệu không hợp lệ: " + dong);
                    continue;
                }

                QuanLy nv = new QuanLy(
                        thongTin[0],
                        thongTin[1],
                        thongTin[2],
                        thongTin[3],
                        thongTin[4],
                        Double.parseDouble(thongTin[5]),
                        Double.parseDouble(thongTin[6]),
                        Double.parseDouble(thongTin[7]),
                        Double.parseDouble(thongTin[8]));

                if (!themNhanVien(nv)) {
                    System.out.println("Không thể thêm nhân viên: " + nv.getMaNV());
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}