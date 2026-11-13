package manager;

import entity.KhachHang;
import interfaces.IFileIO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class QuanLyKhachHang implements IFileIO {
    private KhachHang[] danhSach;
    private int soLuong;

    public QuanLyKhachHang() {
        danhSach = new KhachHang[10];
        soLuong = 0;
    }

    public void themKhachHang(KhachHang kh) {
        if (kh == null) {
            System.out.println("Thông tin khách hàng không hợp lệ.");
            return;
        }

        if (soLuong == danhSach.length) {
            System.out.println("Danh sách khách hàng đã đầy.");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaKH(), kh.getMaKH())) {
                System.out.println("Mã khách hàng đã tồn tại.");
                return;
            }
        }

        danhSach[soLuong] = kh;
        soLuong++;
        System.out.println("Thêm khách hàng thành công.");
    }

    public void suaKhachHang(String maKH) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < soLuong; i++) {
            KhachHang kh = danhSach[i];
            if (Objects.equals(kh.getMaKH(), maKH)) {
                System.out.println("Chọn thông tin cần sửa:");
                System.out.println("1. Họ tên");
                System.out.println("2. Ngày sinh");
                System.out.println("3. Số điện thoại");
                System.out.println("4. Email");
                System.out.println("5. Địa chỉ");
                System.out.print("Lựa chọn: ");

                String luaChon = scanner.nextLine();
                System.out.print("Nhập giá trị mới: ");
                String giaTriMoi = scanner.nextLine();

                switch (luaChon) {
                    case "1":
                        kh.setHoTen(giaTriMoi);
                        break;
                    case "2":
                        kh.setNgaySinh(giaTriMoi);
                        break;
                    case "3":
                        kh.setSoDienThoai(giaTriMoi);
                        break;
                    case "4":
                        kh.setEmail(giaTriMoi);
                        break;
                    case "5":
                        kh.setDiaChi(giaTriMoi);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                        return;
                }

                System.out.println("Cập nhật khách hàng thành công.");
                return;
            }
        }

        System.out.println("Không tìm thấy khách hàng có mã " + maKH + ".");
    }

    public void xoaKhachHang(String maKH) {
        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaKH(), maKH)) {
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }
                danhSach[soLuong - 1] = null;
                soLuong--;
                System.out.println("Xóa khách hàng thành công.");
                return;
            }
        }

        System.out.println("Không tìm thấy khách hàng có mã " + maKH + ".");
    }

    public void timKhachHang(String maKH) {
        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaKH(), maKH)) {
                danhSach[i].hienThiThongTin();
                return;
            }
        }

        System.out.println("Không tìm thấy khách hàng có mã " + maKH + ".");
    }

    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sách khách hàng đang trống.");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Khách hàng thứ " + (i + 1) + ":");
            danhSach[i].hienThiThongTin();
            System.out.println("--------------------");
        }
    }

    @Override
    public void ghiFile(String tenFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < soLuong; i++) {
                KhachHang kh = danhSach[i];
                writer.write(kh.getHoTen() + ";"
                        + kh.getNgaySinh() + ";"
                        + kh.getSoDienThoai() + ";"
                        + kh.getEmail() + ";"
                        + kh.getMaKH() + ";"
                        + kh.getDiaChi());
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
                String[] thongTin = dong.split(";", -1);
                if (thongTin.length != 6) {
                    System.out.println("Dòng dữ liệu không hợp lệ: " + dong);
                    continue;
                }

                if (soLuong == danhSach.length) {
                    System.out.println("Danh sách khách hàng đã đầy.");
                    break;
                }

                KhachHang kh = new KhachHang(
                        thongTin[0],
                        thongTin[1],
                        thongTin[2],
                        thongTin[3],
                        thongTin[4],
                        thongTin[5]);
                themKhachHang(kh);
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}
