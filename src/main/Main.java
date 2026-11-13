package main;
import entity.SanPham;
import entity.MayTinh;
import entity.CPU;
import entity.RAM;
public class Main{
    public static void main(String[] args) {

        // Tao doi tuong MayTinh
        MayTinh mt = new MayTinh(
            "MT01", "Dell Inspiron", "Dell",
            12000000, 15000000, 10, 24,
            "Laptop", "Intel Core i5", 16, 512
        );

        // Tao doi tuong CPU
        CPU cpu = new CPU(
            "CPU01", "Intel Core i5-12400", "Intel",
            3000000, 4000000, 20, 36,
            "CPU", "6 nhan 12 luong",
            "LGA1700", 6, 4.4
        );

        // Tao doi tuong RAM
        RAM ram = new RAM(
            "RAM01", "Kingston Fury", "Kingston",
            800000, 1100000, 30, 36,
            "RAM", "Bo nho DDR4",
            16, "DDR4", 3200
        );

        // Hien thi thong tin
        mt.hienThiThongTin();
        System.out.println();

        cpu.hienThiThongTin();
        System.out.println();

        ram.hienThiThongTin();
        System.out.println();

        // Thu nghiem Getter va Setter
        mt.setGiaBan(14500000);
        System.out.println("Gia ban moi cua may tinh: "
                           + mt.getGiaBan());

        cpu.setSoNhan(8);
        System.out.println("So nhan CPU moi: "
                           + cpu.getSoNhan());

        ram.setDungLuong(32);
        System.out.println("Dung luong RAM moi: "
                           + ram.getDungLuong());

        // Cap nhat ton kho
        mt.capNhatSoLuong(15);

        System.out.println("Gia tri ton kho may tinh: "
                           + mt.tinhGiaTriTonKho());

        // Dem so doi tuong san pham
        System.out.println("Tong so san pham: "
                           + SanPham.demSanPham());
    }
}
