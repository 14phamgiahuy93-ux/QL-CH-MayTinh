package manager;

import entity.PhieuNhap;
import java.util.Arrays;

public class QuanLyPhieuNhap {
    private PhieuNhap[] danhSach;
    private int soLuong;

    // Constructor khởi tạo danh sách quản lý
    public QuanLyPhieuNhap() {
        this.danhSach = new PhieuNhap[100]; // Mảng thủ công ban đầu chứa tối đa 100 phiếu
        this.soLuong = 0;
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
}