package manager;
import entity.PhieuNhap;
import entity.ChiTietPhieuNhap;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.SanPham;

import interfaces.IFileIO;
import utils.FileUtil;

import java.util.Arrays;
import entity.PhieuNhap;
import java.util.Arrays;

public class QuanLyPhieuNhap implements IFileIO {
    private PhieuNhap[] danhSach;
    private int soLuong;
    private QuanLySanPham qlSP;
    private QuanLyNhanVien qlNV;
    // Constructor khởi tạo danh sách quản lý
    public QuanLyPhieuNhap() {
        this.danhSach = new PhieuNhap[100]; // Mảng thủ công ban đầu chứa tối đa 100 phiếu
        this.soLuong = 0;
    }
    public QuanLyPhieuNhap(QuanLySanPham qlSP, QuanLyNhanVien qlNV) {
    this();
    this.qlSP = qlSP;
    this.qlNV = qlNV;
    }
    // Thêm phiếu nhập mới (tự co giãn mảng thủ công khi đầy)
    public void themPhieuNhap(PhieuNhap pn) {
        if (soLuong >= danhSach.length) {
            danhSach = Arrays.copyOf(danhSach, danhSach.length * 2);
        }
        danhSach[soLuong++] = pn;
        System.out.println("Thêm phiếu nhập thành công!");
    }

    // Xóa phiếu nhập theo mã
    public void xoaPhieuNhap(String maPN) {
        int index = -1;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPN().equalsIgnoreCase(maPN)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Không tìm thấy phiếu nhập có mã: " + maPN);
            return;
        }

        // Dồn mảng để xóa phần tử
        for (int i = index; i < soLuong - 1; i++) {
            danhSach[i] = danhSach[i + 1];
        }
        danhSach[--soLuong] = null;
        System.out.println("Đã xóa thành công phiếu nhập mã: " + maPN);
    }

    // Tìm kiếm phiếu nhập theo mã
    public void timPhieuNhap(String maPN) {
        boolean found = false;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPN().equalsIgnoreCase(maPN)) {
                System.out.println("Đã tìm thấy phiếu nhập:");
                danhSach[i].hienThi();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy phiếu nhập với mã: " + maPN);
        }
    }

    // Hiển thị toàn bộ danh sách phiếu nhập
    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sách phiếu nhập trống!");
            return;
        }
        System.out.println("\n========== DANH SÁCH PHIẾU NHẬP ==========");
        for (int i = 0; i < soLuong; i++) {
            danhSach[i].hienThi();
        }
    }

    // Thống kê tổng tiền nhập hàng của tất cả các phiếu
    public void thongKeTienNhap() {
        double tongChiPhi = 0;
        for (int i = 0; i < soLuong; i++) {
            tongChiPhi += danhSach[i].tinhTongTienNhap();
        }
        System.out.println("\n========== THỐNG KÊ NHẬP HÀNG ==========");
        System.out.println("Tổng số lượng phiếu nhập: " + soLuong);
        System.out.printf("Tổng chi phí nhập hàng: %.2f\n", tongChiPhi);
        System.out.println("========================================");
    }

    // Getters
    public PhieuNhap[] getDanhSach() {
        return danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }
    @Override
public void docFile(String tenFile) {

    if (qlSP == null || qlNV == null) {
        System.out.println("Chưa khởi tạo QuanLySanPham hoặc QuanLyNhanVien!");
        return;
    }

    String noiDung = FileUtil.docChuoi(tenFile);

    if (noiDung.isEmpty()) {
        System.out.println("File trống hoặc không đọc được!");
        return;
    }

    danhSach = new PhieuNhap[100];
    soLuong = 0;

    String[] dong = noiDung.split("\\R");

    for (String line : dong) {

        String[] data = line.split(";", -1);

        try {
            // Đọc thông tin phiếu nhập
            if (data[0].equals("P") && data.length == 9) {

                String maPN = data[1];
                String ngayNhap = data[2];

                NhaCungCap ncc = new NhaCungCap(
                    data[3],
                    data[4],
                    data[5],
                    data[6],
                    data[7]
                );

                String maNV = data[8];

                NhanVien nv = qlNV.layNhanVien(maNV);

                if (nv == null) {
                    System.out.println("Không tìm thấy nhân viên: " + maNV);
                    continue;
                }

                PhieuNhap pn = new PhieuNhap(
                    maPN, ngayNhap, ncc, nv
                );

                themPhieuNhap(pn);
            }

            // Đọc chi tiết phiếu nhập
            else if (data[0].equals("C") && data.length == 5) {

                String maPN = data[1];
                String maSP = data[2];

                int soLuong = Integer.parseInt(data[3]);
                double donGiaNhap = Double.parseDouble(data[4]);

                PhieuNhap pn = layPhieuNhapNoiBo(maPN);
                SanPham sp = qlSP.laySanPham(maSP);

                if (pn == null) {
                    System.out.println("Không tìm thấy phiếu nhập: " + maPN);
                    continue;
                }

                if (sp == null) {
                    System.out.println("Không tìm thấy sản phẩm: " + maSP);
                    continue;
                }

                ChiTietPhieuNhap ct = new ChiTietPhieuNhap(
                    sp, soLuong, donGiaNhap
                );

                pn.themChiTiet(ct);
            }

        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu số không hợp lệ: " + line);
        }
    }

    System.out.println("Đọc file phiếu nhập thành công!");
}
    private PhieuNhap layPhieuNhapNoiBo(String maPN) {
    for (int i = 0; i < soLuong; i++) {
        if (danhSach[i].getMaPN().equalsIgnoreCase(maPN)) {
            return danhSach[i];
        }
    }
    return null;
}
}
