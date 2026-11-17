
package manager;

import entity.SanPham;
import entity.MayTinh;
import entity.ChiTietSanPham;
import interfaces.IFileIO;
import utils.FileUtil;
import utils.Validator;
import java.util.HashMap;
import java.util.Map;
public class QuanLySanPham implements IFileIO {

    private SanPham[] danhSach;
    private int soLuong;

    public QuanLySanPham(int kichThoc) {
        if (kichThoc <= 0) {
            kichThoc = 10;
        }
        danhSach = new SanPham[kichThoc];
        soLuong = 0;
    }

    // 1. Them san pham
    public void themSanPham(SanPham sp) {
        if (sp == null) {
            System.out.println("San pham khong hop le!");
            return;
        }

        if (soLuong == danhSach.length) {
            SanPham[] mangMoi = new SanPham[danhSach.length * 2];

            for (int i = 0; i < soLuong; i++) {
                mangMoi[i] = danhSach[i];
            }

            danhSach = mangMoi;
        }

        danhSach[soLuong++] = sp;
        System.out.println("Them san pham thanh cong!");
    }

    // 2. Sua san pham theo ma
    public void suaSanPham(String maSP) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaSP().equalsIgnoreCase(maSP)) {

                System.out.println("Nhap thong tin moi:");

                String tenSP = Validator.readString("Ten san pham: ");
                String hangSX = Validator.readString("Hang san xuat: ");
                double giaNhap = Validator.readDouble("Gia nhap: ", 0);
                double giaBan = Validator.readDouble("Gia ban: ", 0);
                int soLuongTon = Validator.readInt("So luong ton: ", 0, 100000);
                int baoHanh = Validator.readInt("Bao hanh (thang): ", 0, 120);

                danhSach[i].setTenSP(tenSP);
                danhSach[i].setHangSX(hangSX);
                danhSach[i].setGiaNhap(giaNhap);
                danhSach[i].setGiaBan(giaBan);
                danhSach[i].setSoLuongTon(soLuongTon);
                danhSach[i].setBaoHanh(baoHanh);

                System.out.println("Cap nhat san pham thanh cong!");
                return;
            }
        }

        System.out.println("Khong tim thay san pham!");
    }

    // 3. Xoa san pham theo ma
    public void xoaSanPham(String maSP) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaSP().equalsIgnoreCase(maSP)) {

                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }

                danhSach[--soLuong] = null;

                System.out.println("Xoa san pham thanh cong!");
                return;
            }
        }

        System.out.println("Khong tim thay san pham!");
    }

    // 4. Tim san pham theo ma
    public void timSanPham(String maSP) {
        SanPham sp = laySanPham(maSP);

        if (sp != null) {
            sp.hienThiThongTin();
        } else {
            System.out.println("Khong tim thay san pham!");
        }
    }

    // 5. Tim san pham theo ten
    public void timTheoTen(String tenSP) {
        boolean timThay = false;

        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getTenSP().toLowerCase()
                    .contains(tenSP.toLowerCase())) {

                danhSach[i].hienThiThongTin();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay san pham!");
        }
    }

    // 6. Hien thi danh sach
    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sach san pham rong!");
            return;
        }

        for (int i = 0; i < soLuong; i++) {
            danhSach[i].hienThiThongTin();
        }
    }

    // 7. Sap xep theo gia ban tang dan
    public void sapXepTheoGia() {
        for (int i = 0; i < soLuong - 1; i++) {
            for (int j = i + 1; j < soLuong; j++) {

                if (danhSach[i].tinhGiaBan()
                        > danhSach[j].tinhGiaBan()) {

                    SanPham temp = danhSach[i];
                    danhSach[i] = danhSach[j];
                    danhSach[j] = temp;
                }
            }
        }

        System.out.println("Sap xep san pham thanh cong!");
    }

    // 8. Kiem tra ton kho
    public void kiemTraTonKho() {
        boolean canhBao = false;

        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getSoLuongTon() < 5) {

                System.out.println("Canh bao ton kho thap: "
                        + danhSach[i].getTenSP());

                canhBao = true;
            }
        }

        if (!canhBao) {
            System.out.println("Khong co san pham ton kho thap!");
        }
    }

    // 9. Lay san pham theo ma
    public SanPham laySanPham(String maSP) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaSP().equalsIgnoreCase(maSP)) {
                return danhSach[i];
            }
        }

        return null;
    }

    // 10. Ghi danh sach san pham vao file
    @Override
    public void ghiFile(String tenFile) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {

            SanPham sp = danhSach[i];

            // Ghi thong tin may tinh
            if (sp instanceof MayTinh) {

                MayTinh mt = (MayTinh) sp;

                sb.append("M;")
                  .append(mt.getMaSP()).append(";")
                  .append(mt.getTenSP()).append(";")
                  .append(mt.getHangSX()).append(";")
                  .append(mt.getGiaNhap()).append(";")
                  .append(mt.getGiaBan()).append(";")
                  .append(mt.getSoLuongTon()).append(";")
                  .append(mt.getBaoHanh()).append(";")
                  .append(mt.getLoaiMay()).append(";")
                  .append(mt.getCPU()).append(";")
                  .append(mt.getRAM()).append(";")
                  .append(mt.getDungLuongSSD())
                  .append("\n");
            }

            // Ghi thong tin chi tiet san pham
            else if (sp instanceof ChiTietSanPham) {

                ChiTietSanPham ct = (ChiTietSanPham) sp;

                sb.append("C;")
                  .append(ct.getMaSP()).append(";")
                  .append(ct.getTenSP()).append(";")
                  .append(ct.getHangSX()).append(";")
                  .append(ct.getGiaNhap()).append(";")
                  .append(ct.getGiaBan()).append(";")
                  .append(ct.getSoLuongTon()).append(";")
                  .append(ct.getBaoHanh()).append(";")
                  .append(ct.getSoSeri()).append(";")
                  .append(ct.getMaSPChiTiet()).append(";")
                  .append(ct.getTrangThai())
                  .append("\n");
            }
        }

        FileUtil.ghiChuoi(tenFile, sb.toString());

        System.out.println("Ghi file san pham thanh cong!");
    }

    // 11. Doc danh sach san pham tu file
    @Override
    public void docFile(String tenFile) {

        String noiDung = FileUtil.docChuoi(tenFile);

        if (noiDung.isEmpty()) {
            System.out.println("File rong hoac khong doc duoc!");
            return;
        }

        // Xoa danh sach cu truoc khi doc
        danhSach = new SanPham[10];
        soLuong = 0;

        String[] cacDong = noiDung.split("\\R");

        for (String dong : cacDong) {

            if (dong.trim().isEmpty()) {
                continue;
            }

            String[] data = dong.split(";", -1);

            try {

                // Doc loai MayTinh
                if (data[0].equals("M") && data.length == 12) {

                    MayTinh mt = new MayTinh(
                        data[1],
                        data[2],
                        data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]),
                        data[8],
                        data[9],
                        Integer.parseInt(data[10]),
                        Integer.parseInt(data[11])
                    );

                    themSanPham(mt);
                }

                // Doc loai ChiTietSanPham
                else if (data[0].equals("C") && data.length == 11) {

                    ChiTietSanPham ct = new ChiTietSanPham(
                        data[1],
                        data[2],
                        data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]),
                        data[8],
                        data[9],
                        data[10]
                    );

                    themSanPham(ct);
                }

                else {
                    System.out.println("Dong du lieu khong hop le: " + dong);
                }

            } catch (NumberFormatException e) {
                System.out.println("Loi du lieu tai dong: " + dong);
            }
        }

        System.out.println("Doc file san pham hoan tat!");
    }

    // 12. Getter
    public int getSoLuong() {
        return soLuong;
    }

    public SanPham[] getDanhSach() {
        return danhSach;
    }
    
// Tim san pham theo nhieu tieu chi
public void timSanPhamNhieuTieuChi(String tuKhoa) {

    boolean timThay = false;

    // Buoc 1: Tim chinh xac theo ma, ten hoac hang san xuat
    for (int i = 0; i < soLuong; i++) {
        SanPham sp = danhSach[i];

        if (sp.getMaSP().equalsIgnoreCase(tuKhoa)
                || sp.getTenSP().equalsIgnoreCase(tuKhoa)
                || sp.getHangSX().equalsIgnoreCase(tuKhoa)) {

            System.out.println("Tim thay chinh xac:");
            sp.hienThiThongTin();
            timThay = true;
        }
    }

    if (timThay) {
        return;
    }

    // Buoc 2: Tim gan dung
    for (int i = 0; i < soLuong; i++) {
        SanPham sp = danhSach[i];

        if (sp.getMaSP().toLowerCase().contains(tuKhoa.toLowerCase())
                || sp.getTenSP().toLowerCase().contains(tuKhoa.toLowerCase())
                || sp.getHangSX().toLowerCase().contains(tuKhoa.toLowerCase())) {

            System.out.println("Tim thay gan dung:");
            sp.hienThiThongTin();
            timThay = true;
        }
    }

    if (!timThay) {
        System.out.println("Khong tim thay san pham!");
    }
}

    // 13. Thong ke theo hang san xuat va loai san pham
    public void thongKeTheoNhieuKhoa() {

        if (soLuong == 0) {
            System.out.println("Danh sach san pham rong!");
            return;
        }

        Map<String, double[]> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            SanPham sp = danhSach[i];

            // Tao khoa thong ke tu hang san xuat va loai san pham
            String khoa = sp.getHangSX() + " - "
                    + sp.getClass().getSimpleName();

            double[] soLieu = thongKe.get(khoa);

            if (soLieu == null) {
                soLieu = new double[3];
                thongKe.put(khoa, soLieu);
            }

            // Dem so mat hang
            soLieu[0]++;

            // Tinh tong so luong ton
            soLieu[1] += sp.getSoLuongTon();

            // Tinh tong gia tri ton kho
            soLieu[2] += sp.tinhGiaTriTonKho();
        }

        System.out.println("\n===== THONG KE SAN PHAM =====");

        for (Map.Entry<String, double[]> entry : thongKe.entrySet()) {

            double[] soLieu = entry.getValue();

            System.out.println("\nNhom: " + entry.getKey());
            System.out.println("So mat hang: " + (int) soLieu[0]);
            System.out.println("Tong so luong ton: " + (int) soLieu[1]);
            System.out.println("Tong gia tri ton kho: "
                    + soLieu[2] + " VND");
        }
    }

    // 14. Cap nhat so luong ton kho khi nhap hang
    public boolean capNhatTonKho(String maSP, int soLuongNhap) {

        if (soLuongNhap <= 0) {
            System.out.println("So luong nhap phai lon hon 0!");
            return false;
        }

        SanPham sp = laySanPham(maSP);

        if (sp == null) {
            System.out.println("Khong tim thay san pham!");
            return false;
        }

        sp.setSoLuongTon(sp.getSoLuongTon() + soLuongNhap);

        System.out.println("Cap nhat ton kho thanh cong!");
        System.out.println("San pham: " + sp.getTenSP());
        System.out.println("So luong ton moi: " + sp.getSoLuongTon());

        return true;
    }

}
