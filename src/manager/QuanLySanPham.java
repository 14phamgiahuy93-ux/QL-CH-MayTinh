
package manager;

import entity.SanPham;
import entity.MayTinh;
import entity.ChiTietSanPham;
import entity.CPU;
import entity.RAM;
import entity.CardDoHoa;
import entity.OCung;
import entity.Nguon;

import interfaces.IFileIO;
import utils.FileUtil;
import utils.Validator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QuanLySanPham implements IFileIO {

    private SanPham[] danhSach;
    private int soLuong;

    // Constructor mac dinh
    public QuanLySanPham() {
        this(10);
    }

    // Constructor co kich thuoc
    public QuanLySanPham(int kichThoc) {

        if (kichThoc <= 0) {
            kichThoc = 10;
        }

        danhSach = new SanPham[kichThoc];
        soLuong = 0;
    }

    // 1. Them san pham
    public void themSanPham(SanPham sp) {

        if (sp == null || sp.getMaSP() == null
                || sp.getMaSP().trim().isEmpty()) {

            System.out.println("San pham khong hop le!");
            return;
        }

        if (laySanPham(sp.getMaSP()) != null) {
            System.out.println("Ma san pham da ton tai!");
            return;
        }

        if (soLuong >= danhSach.length) {
            danhSach = Arrays.copyOf(
                danhSach, danhSach.length * 2
            );
        }

        danhSach[soLuong++] = sp;

        System.out.println("Them san pham thanh cong!");
    }

    // 2. Them nhieu san pham
    public void themNhieuSanPham(SanPham[] ds, int k) {

        if (ds == null || k <= 0 || k > ds.length) {
            System.out.println("Du lieu khong hop le!");
            return;
        }

        int soThem = 0;

        for (int i = 0; i < k; i++) {

            int truocDo = soLuong;

            themSanPham(ds[i]);

            if (soLuong > truocDo) {
                soThem++;
            }
        }

        System.out.println("Da them " + soThem + " san pham!");
    }

    // 3. Sua san pham theo ma
    public void suaSanPham(String maSP) {

        SanPham sp = laySanPham(maSP);

        if (sp == null) {
            System.out.println("Khong tim thay san pham!");
            return;
        }

        System.out.println("Nhap thong tin moi:");

        String tenSP = Validator.readString("Ten san pham: ");
        String hangSX = Validator.readString("Hang san xuat: ");
        double giaNhap = Validator.readDouble("Gia nhap: ", 0);
        double giaBan = Validator.readDouble("Gia ban: ", 0);

        int soLuongTon = Validator.readInt(
            "So luong ton: ", 0, 100000
        );

        int baoHanh = Validator.readInt(
            "Bao hanh (thang): ", 0, 120
        );

        sp.setTenSP(tenSP);
        sp.setHangSX(hangSX);
        sp.setGiaNhap(giaNhap);
        sp.setGiaBan(giaBan);
        sp.setSoLuongTon(soLuongTon);
        sp.setBaoHanh(baoHanh);

        System.out.println("Cap nhat san pham thanh cong!");
    }

    // 4. Xoa san pham theo ma
    public void xoaSanPham(String maSP) {

        if (maSP == null || maSP.trim().isEmpty()) {
            System.out.println("Ma san pham khong hop le!");
            return;
        }

        int index = -1;

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null
                    && danhSach[i].getMaSP() != null
                    && danhSach[i].getMaSP()
                    .equalsIgnoreCase(maSP.trim())) {

                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Khong tim thay san pham!");
            return;
        }

        for (int i = index; i < soLuong - 1; i++) {
            danhSach[i] = danhSach[i + 1];
        }

        danhSach[--soLuong] = null;

        System.out.println("Xoa san pham thanh cong!");
    }

    // 5. Tim san pham theo ma
    public void timSanPham(String maSP) {

        SanPham sp = laySanPham(maSP);

        if (sp != null) {
            sp.hienThiThongTin();
        } else {
            System.out.println("Khong tim thay san pham!");
        }
    }

    // 6. Tim san pham theo ten
    public void timTheoTen(String tenSP) {

        if (tenSP == null || tenSP.trim().isEmpty()) {
            System.out.println("Ten san pham khong hop le!");
            return;
        }

        boolean timThay = false;

        for (int i = 0; i < soLuong; i++) {

            SanPham sp = danhSach[i];

            if (sp != null && sp.getTenSP() != null
                    && sp.getTenSP().toLowerCase()
                    .contains(tenSP.trim().toLowerCase())) {

                sp.hienThiThongTin();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay san pham!");
        }
    }

    // 7. Tim san pham theo nhieu tieu chi
    public void timSanPhamNhieuTieuChi(String tuKhoa) {

        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            System.out.println("Tu khoa khong hop le!");
            return;
        }

        tuKhoa = tuKhoa.trim();

        boolean timThay = false;

        // Tim chinh xac truoc
        for (int i = 0; i < soLuong; i++) {

            SanPham sp = danhSach[i];

            if (sp == null) {
                continue;
            }

            String ma = sp.getMaSP() == null ? "" : sp.getMaSP();
            String ten = sp.getTenSP() == null ? "" : sp.getTenSP();
            String hang = sp.getHangSX() == null ? "" : sp.getHangSX();

            if (ma.equalsIgnoreCase(tuKhoa)
                    || ten.equalsIgnoreCase(tuKhoa)
                    || hang.equalsIgnoreCase(tuKhoa)) {

                System.out.println("Tim thay chinh xac:");
                sp.hienThiThongTin();
                timThay = true;
            }
        }

        if (timThay) {
            return;
        }

        // Tim gan dung
        String tuKhoaLower = tuKhoa.toLowerCase();

        for (int i = 0; i < soLuong; i++) {

            SanPham sp = danhSach[i];

            if (sp == null) {
                continue;
            }

            String ma = sp.getMaSP() == null ? "" : sp.getMaSP();
            String ten = sp.getTenSP() == null ? "" : sp.getTenSP();
            String hang = sp.getHangSX() == null ? "" : sp.getHangSX();

            if (ma.toLowerCase().contains(tuKhoaLower)
                    || ten.toLowerCase().contains(tuKhoaLower)
                    || hang.toLowerCase().contains(tuKhoaLower)) {

                System.out.println("Tim thay gan dung:");
                sp.hienThiThongTin();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay san pham!");
        }
    }

    // 8. Hien thi danh sach
    public void hienThiDanhSach() {

        if (soLuong == 0) {
            System.out.println("Danh sach san pham rong!");
            return;
        }

        System.out.println("\n===== DANH SACH SAN PHAM =====");

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null) {
                danhSach[i].hienThiThongTin();
            }
        }
    }

    // 9. Sap xep theo gia ban tang dan
    public void sapXepTheoGia() {

        for (int i = 0; i < soLuong - 1; i++) {

            for (int j = i + 1; j < soLuong; j++) {

                if (danhSach[i] != null && danhSach[j] != null
                        && danhSach[i].tinhGiaBan()
                        > danhSach[j].tinhGiaBan()) {

                    SanPham temp = danhSach[i];
                    danhSach[i] = danhSach[j];
                    danhSach[j] = temp;
                }
            }
        }

        System.out.println("Sap xep san pham thanh cong!");
    }

    // 10. Kiem tra ton kho
    public void kiemTraTonKho() {

        boolean canhBao = false;

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null
                    && danhSach[i].getSoLuongTon() < 5) {

                System.out.println("Canh bao ton kho thap: "
                        + danhSach[i].getTenSP());

                canhBao = true;
            }
        }

        if (!canhBao) {
            System.out.println("Khong co san pham ton kho thap!");
        }
    }

    // 11. Lay san pham theo ma
    public SanPham laySanPham(String maSP) {

        if (maSP == null || maSP.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i] != null
                    && danhSach[i].getMaSP() != null
                    && danhSach[i].getMaSP()
                    .equalsIgnoreCase(maSP.trim())) {

                return danhSach[i];
            }
        }

        return null;
    }

    // 12. Thong ke theo hang san xuat va loai san pham
    public void thongKeTheoNhieuKhoa() {

        if (soLuong == 0) {
            System.out.println("Danh sach san pham rong!");
            return;
        }

        Map<String, double[]> thongKe = new HashMap<>();

        for (int i = 0; i < soLuong; i++) {

            SanPham sp = danhSach[i];

            if (sp == null) {
                continue;
            }

            String hang = sp.getHangSX() == null
                    ? "Khong ro" : sp.getHangSX();

            String khoa = hang + " - "
                    + sp.getClass().getSimpleName();

            double[] soLieu = thongKe.get(khoa);

            if (soLieu == null) {
                soLieu = new double[3];
                thongKe.put(khoa, soLieu);
            }

            soLieu[0]++;
            soLieu[1] += sp.getSoLuongTon();
            soLieu[2] += sp.tinhGiaTriTonKho();
        }

        System.out.println("\n===== THONG KE SAN PHAM =====");

        for (Map.Entry<String, double[]> entry : thongKe.entrySet()) {

            double[] soLieu = entry.getValue();

            System.out.println("\nNhom: " + entry.getKey());
            System.out.println("So mat hang: " + (int) soLieu[0]);
            System.out.println("Tong so luong ton: " + (int) soLieu[1]);

            System.out.printf(
                "Tong gia tri ton kho: %.2f VND%n",
                soLieu[2]
            );
        }
    }

    // 13. Cap nhat so luong ton kho
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

        if (sp.getSoLuongTon() > Integer.MAX_VALUE - soLuongNhap) {
            System.out.println("So luong ton vuot gioi han!");
            return false;
        }

        sp.setSoLuongTon(
            sp.getSoLuongTon() + soLuongNhap
        );

        System.out.println("Cap nhat ton kho thanh cong!");
        System.out.println("San pham: " + sp.getTenSP());
        System.out.println("So luong ton moi: " + sp.getSoLuongTon());

        return true;
    }

    // 14. Ghi danh sach san pham vao file
    @Override
    public void ghiFile(String tenFile) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < soLuong; i++) {

            SanPham sp = danhSach[i];

            if (sp == null) {
                continue;
            }

            // May tinh
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

            // Chi tiet san pham
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

            // CPU
            else if (sp instanceof CPU) {

                CPU cpu = (CPU) sp;

                sb.append("CPU;")
                  .append(cpu.getMaSP()).append(";")
                  .append(cpu.getTenSP()).append(";")
                  .append(cpu.getHangSX()).append(";")
                  .append(cpu.getGiaNhap()).append(";")
                  .append(cpu.getGiaBan()).append(";")
                  .append(cpu.getSoLuongTon()).append(";")
                  .append(cpu.getBaoHanh()).append(";")
                  .append(cpu.getLoaiLinhKien()).append(";")
                  .append(cpu.getThongSoKyThuat()).append(";")
                  .append(cpu.getSocket()).append(";")
                  .append(cpu.getSoNhan()).append(";")
                  .append(cpu.getXungNhip())
                  .append("\n");
            }

            // RAM
            else if (sp instanceof RAM) {

                RAM ram = (RAM) sp;

                sb.append("RAM;")
                  .append(ram.getMaSP()).append(";")
                  .append(ram.getTenSP()).append(";")
                  .append(ram.getHangSX()).append(";")
                  .append(ram.getGiaNhap()).append(";")
                  .append(ram.getGiaBan()).append(";")
                  .append(ram.getSoLuongTon()).append(";")
                  .append(ram.getBaoHanh()).append(";")
                  .append(ram.getLoaiLinhKien()).append(";")
                  .append(ram.getThongSoKyThuat()).append(";")
                  .append(ram.getDungLuong()).append(";")
                  .append(ram.getLoaiRAM()).append(";")
                  .append(ram.getTocDoBus())
                  .append("\n");
            }

            // Card do hoa
            else if (sp instanceof CardDoHoa) {

                CardDoHoa card = (CardDoHoa) sp;

                sb.append("GPU;")
                  .append(card.getMaSP()).append(";")
                  .append(card.getTenSP()).append(";")
                  .append(card.getHangSX()).append(";")
                  .append(card.getGiaNhap()).append(";")
                  .append(card.getGiaBan()).append(";")
                  .append(card.getSoLuongTon()).append(";")
                  .append(card.getBaoHanh()).append(";")
                  .append(card.getLoaiLinhKien()).append(";")
                  .append(card.getThongSoKyThuat()).append(";")
                  .append(card.getDungLuongVRAM()).append(";")
                  .append(card.getChipDoHoa())
                  .append("\n");
            }

            // O cung
            else if (sp instanceof OCung) {

                OCung oCung = (OCung) sp;

                sb.append("OC;")
                  .append(oCung.getMaSP()).append(";")
                  .append(oCung.getTenSP()).append(";")
                  .append(oCung.getHangSX()).append(";")
                  .append(oCung.getGiaNhap()).append(";")
                  .append(oCung.getGiaBan()).append(";")
                  .append(oCung.getSoLuongTon()).append(";")
                  .append(oCung.getBaoHanh()).append(";")
                  .append(oCung.getLoaiLinhKien()).append(";")
                  .append(oCung.getThongSoKyThuat()).append(";")
                  .append(oCung.getDungLuong()).append(";")
                  .append(oCung.getLoaiOCung())
                  .append("\n");
            }

            // Nguon
            else if (sp instanceof Nguon) {

                Nguon nguon = (Nguon) sp;

                sb.append("PSU;")
                  .append(nguon.getMaSP()).append(";")
                  .append(nguon.getTenSP()).append(";")
                  .append(nguon.getHangSX()).append(";")
                  .append(nguon.getGiaNhap()).append(";")
                  .append(nguon.getGiaBan()).append(";")
                  .append(nguon.getSoLuongTon()).append(";")
                  .append(nguon.getBaoHanh()).append(";")
                  .append(nguon.getLoaiLinhKien()).append(";")
                  .append(nguon.getThongSoKyThuat()).append(";")
                  .append(nguon.getCongSuat()).append(";")
                  .append(nguon.getChungNhan())
                  .append("\n");
            }
        }

        FileUtil.ghiChuoi(tenFile, sb.toString());

        System.out.println("Ghi file san pham thanh cong!");
    }

    // 15. Doc danh sach san pham tu file
    @Override
    public void docFile(String tenFile) {

        String noiDung = FileUtil.docChuoi(tenFile);

        if (noiDung == null || noiDung.trim().isEmpty()) {
            System.out.println("File rong hoac khong doc duoc!");
            return;
        }

        SanPham[] danhSachCu = danhSach;
        int soLuongCu = soLuong;

        danhSach = new SanPham[10];
        soLuong = 0;

        String[] cacDong = noiDung.split("\\R");

        for (String dong : cacDong) {

            if (dong.trim().isEmpty()) {
                continue;
            }

            String[] data = dong.split(";", -1);

            try {

                String loai = data[0];

                // May tinh
                if (loai.equals("M") && data.length == 12) {

                    SanPham sp = new MayTinh(
                        data[1], data[2], data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]),
                        data[8], data[9],
                        Integer.parseInt(data[10]),
                        Integer.parseInt(data[11])
                    );

                    themSanPham(sp);
                }

                // Chi tiet san pham
                else if (loai.equals("C") && data.length == 11) {

                    SanPham sp = new ChiTietSanPham(
                        data[1], data[2], data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]),
                        data[8], data[9], data[10]
                    );

                    themSanPham(sp);
                }

                // CPU
                else if (loai.equals("CPU") && data.length == 13) {

                    SanPham sp = new CPU(
                        data[1], data[2], data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]),
                        data[8], data[9],
                        data[10],
                        Integer.parseInt(data[11]),
                        Double.parseDouble(data[12])
                    );

                    themSanPham(sp);
                }

                // RAM
                else if (loai.equals("RAM") && data.length == 13) {

                    SanPham sp = new RAM(
                        data[1], data[2], data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]),
                        data[8], data[9],
                        Integer.parseInt(data[10]),
                        data[11],
                        Integer.parseInt(data[12])
                    );

                    themSanPham(sp);
                }

                // Card do hoa
                else if (loai.equals("GPU") && data.length == 12) {

                    SanPham sp = new CardDoHoa(
                        data[1], data[2], data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]),
                        data[8], data[9],
                        Integer.parseInt(data[10]),
                        data[11]
                    );

                    themSanPham(sp);
                }

                // O cung
                else if (loai.equals("OC") && data.length == 12) {

                    SanPham sp = new OCung(
                        data[1], data[2], data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]),
                        data[8], data[9],
                        Integer.parseInt(data[10]),
                        data[11]
                    );

                    themSanPham(sp);
                }

                // Nguon
                else if (loai.equals("PSU") && data.length == 12) {

                    Nguon nguon = new Nguon(
                        data[1],
                        data[2],
                        data[3],
                        Double.parseDouble(data[5]),
                        Integer.parseInt(data[6]),
                        Integer.parseInt(data[10]),
                        data[11]
                    );

                    nguon.setGiaNhap(Double.parseDouble(data[4]));
                    nguon.setBaoHanh(Integer.parseInt(data[7]));
                    nguon.setLoaiLinhKien(data[8]);
                    nguon.setThongSoKyThuat(data[9]);

                    themSanPham(nguon);
                }

                else {
                    System.out.println(
                        "Dong du lieu khong hop le: " + dong
                    );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                    "Loi du lieu tai dong: " + dong
                );

            } catch (Exception e) {

                System.out.println(
                    "Khong the doc dong: " + dong
                );
            }
        }

        // Neu khong doc duoc san pham nao thi giu danh sach cu
        if (soLuong == 0) {

            danhSach = danhSachCu;
            soLuong = soLuongCu;

            System.out.println(
                "Khong doc duoc san pham, giu du lieu cu!"
            );

            return;
        }

        System.out.println("Doc file san pham hoan tat!");
    }

    // 16. Getters
    public int getSoLuong() {
        return soLuong;
    }

    public SanPham[] getDanhSach() {
        return danhSach;
    }
    public boolean giamTonKho(String maSP, int soLuongBan) {

    if (soLuongBan <= 0) {
        System.out.println("So luong ban phai lon hon 0!");
        return false;
    }

    SanPham sp = laySanPham(maSP);

    if (sp == null) {
        System.out.println("Khong tim thay san pham!");
        return false;
    }

    if (sp.getSoLuongTon() < soLuongBan) {
        System.out.println("Khong du hang trong kho!");
        return false;
    }

    sp.setSoLuongTon(
        sp.getSoLuongTon() - soLuongBan
    );

    System.out.println("Da cap nhat ton kho!");
    System.out.println("So luong ton moi: "
            + sp.getSoLuongTon());

    return true;
}
}
