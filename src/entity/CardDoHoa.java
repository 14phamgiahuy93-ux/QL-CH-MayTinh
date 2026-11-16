package entity;
public class CardDoHoa extends LinhKien {
    private int dungLuongVRAM;
    private String chipDoHoa;

    // Constructors
    public CardDoHoa() {
        super();
    }

    public CardDoHoa(String maSP, String tenSP, String hangSX, double giaNhap, double giaBan, int soLuongTon, int baoHanh,
                     String loaiLinhKien, String thongSoKyThuat, int dungLuongVRAM, String chipDoHoa) {
        super(maSP, tenSP, hangSX, giaNhap, giaBan, soLuongTon, baoHanh, loaiLinhKien, thongSoKyThuat);
        this.dungLuongVRAM = dungLuongVRAM;
        this.chipDoHoa = chipDoHoa;
    }

    // Getters and Setters
    public int getDungLuongVRAM() {
        return dungLuongVRAM;
    }

    public void setDungLuongVRAM(int dungLuongVRAM) {
        this.dungLuongVRAM = dungLuongVRAM;
    }

    public String getChipDoHoa() {
        return chipDoHoa;
    }

    public void setChipDoHoa(String chipDoHoa) {
        this.chipDoHoa = chipDoHoa;
    }

    // Overridden Methods
    @Override
    public double tinhGiaBan() {
        return this.giaBan;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("--- CARD ĐỒ HỌA ---");
        System.out.println("Mã SP: " + maSP + " | Tên SP: " + tenSP + " | Hãng SX: " + hangSX);
        System.out.println("Loại linh kiện: " + loaiLinhKien + " | Thông số: " + thongSoKyThuat);
        System.out.println("Chip đồ họa: " + chipDoHoa + " | VRAM: " + dungLuongVRAM + " GB");
        System.out.println("Giá nhập: " + giaNhap + " | Giá bán: " + tinhGiaBan() + " | Tồn kho: " + soLuongTon + " | Bảo hành: " + baoHanh + " tháng");
    }
}