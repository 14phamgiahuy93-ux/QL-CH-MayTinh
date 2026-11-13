package manager;
import java.util.Arrays;

public class QuanLyHoaDon {
    private HoaDon[] danhSach;
    private int soLuong;

    // Constructor
    public QuanLyHoaDon() {
        danhSach = new HoaDon[0];
        soLuong = 0;
    }

    // Getter và Setter
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

    // 1. Thêm hóa đơn
    public void themHoaDon(HoaDon hd) {
        if (hd == null) {
            System.out.println("Hoa don khong hop le!");
            return;
        }

        // Tăng kích thước mảng thêm 1
        danhSach = Arrays.copyOf(danhSach, soLuong + 1);

        // Thêm hóa đơn vào vị trí cuối
        danhSach[soLuong] = hd;
        soLuong++;

        System.out.println("Them hoa don thanh cong!");
    }

    // 2. Xóa hóa đơn
    public void xoaHoaDon(String maHD) {
        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i].getMaHD().equals(maHD)) {

                // Dồn các phần tử phía sau lên trước
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }

                soLuong--;

                // Giảm kích thước mảng
                danhSach = Arrays.copyOf(danhSach, soLuong);

                System.out.println("Xoa hoa don thanh cong!");
                return;
            }
        }

        System.out.println("Khong tim thay hoa don!");
    }

    // 3. Tìm hóa đơn
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

    // 4. Hiển thị danh sách
    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sach hoa don rong!");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            danhSach[i].hienThi();
        }
    }

    // 5. Thống kê doanh thu
    public void thongKeDoanhThu() {
        double doanhThu = 0;

        for (int i = 0; i < soLuong; i++) {
            doanhThu += danhSach[i].tinhTongTien();
        }

        System.out.println("Tong doanh thu: " + doanhThu);
    }
}
