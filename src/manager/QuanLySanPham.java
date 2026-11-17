package manager;
import entity.SanPham;
public class QuanLySanPham {
    private SanPham[] danhSach;
    private int soLuong;

    public QuanLySanPham(int kichThoc) {
        danhSach = new SanPham[kichThoc];
        soLuong = 0;
    }

    public void themSanPham(SanPham sp) {
        if (soLuong < danhSach.length) {
            danhSach[soLuong++] = sp;
        }
    }

    public void suaSanPham(String maSP) {
        // Viết code sửa theo yêu cầu đồ án
    }

    public void xoaSanPham(String maSP) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaSP().equalsIgnoreCase(maSP)) {
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }
                danhSach[--soLuong] = null;
                break;
            }
        }
    }

    public void timSanPham(String maSP) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaSP().equalsIgnoreCase(maSP)) {
                danhSach[i].hienThiThongTin();
                return;
            }
        }
    }

    public void timTheoTen(String tenSP) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getTenSP().toLowerCase().contains(tenSP.toLowerCase())) {
                danhSach[i].hienThiThongTin();
            }
        }
    }

    public void hienThiDanhSach() {
        for (int i = 0; i < soLuong; i++) {
            danhSach[i].hienThiThongTin();
        }
    }

    public void sapXepTheoGia() {
        for (int i = 0; i < soLuong - 1; i++) {
            for (int j = i + 1; j < soLuong; j++) {
                if (danhSach[i].tinhGiaBan() > danhSach[j].tinhGiaBan()) {
                    SanPham temp = danhSach[i];
                    danhSach[i] = danhSach[j];
                    danhSach[j] = temp;
                }
            }
        }
    }

    public void kiemTraTonKho() {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getSoLuongTon() < 5) {
                System.out.println("Cảnh báo tồn kho thấp: " + danhSach[i].getTenSP());
            }
        }
    }
    public SanPham laySanPham(String maSP) {
    for (int i = 0; i < soLuong; i++) {
        if (danhSach[i].getMaSP().equalsIgnoreCase(maSP)) {
            return danhSach[i];
        }
    }
    return null;
}
}
