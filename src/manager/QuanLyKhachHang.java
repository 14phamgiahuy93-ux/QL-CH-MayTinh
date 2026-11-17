package manager;
import entity.KhachHang;
import interfaces.IFileIO;
import utils.FileUtil;
import java.util.Objects;
import java.util.Scanner;

public class QuanLyKhachHang implements IFileIO {

    private KhachHang[] danhSach;
    private int soLuong;

    public QuanLyKhachHang() {
        danhSach = new KhachHang[10];
        soLuong = 0;
    }

    // Them khach hang
    public void themKhachHang(KhachHang kh) {
        if (kh == null) {
            System.out.println("Thong tin khach hang khong hop le.");
            return;
        }

        if (soLuong == danhSach.length) {
            System.out.println("Danh sach khach hang da day.");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaKH(), kh.getMaKH())) {
                System.out.println("Ma khach hang da ton tai.");
                return;
            }
        }

        danhSach[soLuong] = kh;
        soLuong++;

        System.out.println("Them khach hang thanh cong.");
    }

    // Sua khach hang
    public void suaKhachHang(String maKH) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < soLuong; i++) {
            KhachHang kh = danhSach[i];

            if (Objects.equals(kh.getMaKH(), maKH)) {
                System.out.println("Chon thong tin can sua:");
                System.out.println("1. Ho ten");
                System.out.println("2. Ngay sinh");
                System.out.println("3. So dien thoai");
                System.out.println("4. Email");
                System.out.println("5. Dia chi");
                System.out.print("Lua chon: ");

                String luaChon = scanner.nextLine();

                System.out.print("Nhap gia tri moi: ");
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
                        System.out.println("Lua chon khong hop le.");
                        return;
                }

                System.out.println("Cap nhat khach hang thanh cong.");
                return;
            }
        }

        System.out.println("Khong tim thay khach hang co ma " + maKH);
    }

    // Xoa khach hang
    public void xoaKhachHang(String maKH) {
        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaKH(), maKH)) {

                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }

                danhSach[soLuong - 1] = null;
                soLuong--;

                System.out.println("Xoa khach hang thanh cong.");
                return;
            }
        }

        System.out.println("Khong tim thay khach hang co ma " + maKH);
    }

    // Tim khach hang
    public void timKhachHang(String maKH) {
        for (int i = 0; i < soLuong; i++) {
            if (Objects.equals(danhSach[i].getMaKH(), maKH)) {
                danhSach[i].hienThiThongTin();
                return;
            }
        }

        System.out.println("Khong tim thay khach hang co ma " + maKH);
    }

    // Hien thi danh sach
    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sach khach hang dang trong.");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Khach hang thu " + (i + 1) + ":");
            danhSach[i].hienThiThongTin();
            System.out.println("--------------------");
        }
    }

    // Ghi file
    @Override
    public void ghiFile(String tenFile) {
        StringBuilder noiDung = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {
            KhachHang kh = danhSach[i];

            noiDung.append(kh.getHoTen()).append(";")
                   .append(kh.getNgaySinh()).append(";")
                   .append(kh.getSoDienThoai()).append(";")
                   .append(kh.getEmail()).append(";")
                   .append(kh.getMaKH()).append(";")
                   .append(kh.getDiaChi());

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

        // Xoa du lieu cu
        soLuong = 0;

        for (int i = 0; i < danhSach.length; i++) {
            danhSach[i] = null;
        }

        if (noiDung == null || noiDung.isEmpty()) {
            System.out.println("File khach hang rong.");
            return;
        }

        String[] cacDong = noiDung.split("\\R");

        for (String dong : cacDong) {

            if (dong.isEmpty()) {
                continue;
            }

            String[] thongTin = dong.split(";", -1);

            if (thongTin.length != 6) {
                System.out.println("Dong du lieu khong hop le: " + dong);
                continue;
            }

            if (soLuong == danhSach.length) {
                System.out.println("Danh sach khach hang da day.");
                break;
            }

            KhachHang kh = new KhachHang(
                thongTin[0],
                thongTin[1],
                thongTin[2],
                thongTin[3],
                thongTin[4],
                thongTin[5]
            );

            themKhachHang(kh);
        }
    }
}
