# QL-CH-MayTinh
Hệ Thống Quản Lý Cửa Hàng Bán Máy Tính (Java OOP)
1. Giới thiệu tổng quanĐồ án Quản Lý Cửa Hàng Bán Máy Tính được xây dựng bằng ngôn ngữ Java theo định hướng lập trình hướng đối tượng (OOP) và mô hình phân tầng. Điểm đặc trưng của hệ thống là tự cài đặt cấu trúc mảng thủ công (mở rộng kích thước linh hoạt khi đầy) thay thế hoàn toàn cho các thư viện dựng sẵn như ArrayList, nhằm rèn luyện tư duy thuật toán và quản lý bộ nhớ.   
2. Cấu trúc thư mục dự ánCấu trúc các package và thành phần mã nguồn được phân chia theo tiêu chuẩn thực tế:   
QuanLyCuaHangMayTinh/
│
├── data/                      # Thư mục lưu trữ dữ liệu văn bản (text files)
│   ├── maytinh.txt
│   ├── nhanvien.txt
│   └── hoadon.txt
│
└── src/
    ├── entity/                # Chứa các lớp đối tượng nghiệp vụ (OOP)
    │   ├── SanPham.java       # (Abstract) Lớp cha chung cho sản phẩm
    │   ├── MayTinh.java       # Kế thừa SanPham
    │   ├── LinhKien.java      # (Abstract) Lớp cha các loại linh kiện
    │   ├── CPU.java, RAM.java, CardDoHoa.java, OCung.java, Nguon.java
    │   ├── Nguoi.java         # (Abstract) Lớp cha cho con người
    │   ├── NhanVien.java, QuanLy.java, KhachHang.java
    │   ├── HoaDon.java, ChiTietHoaDon.java
    │   └── NhaCungCap.java, PhieuNhap.java, ChiTietPhieuNhap.java
    │
    ├── interface_def/         # Định nghĩa các hành vi chung (Interface)
    │   └── IFileIO.java       # Chuẩn đọc/ghi dữ liệu
    │
    ├── manager/               # Các class quản lý mảng thủ công & thuật toán nghiệp vụ
    │   ├── QuanLySanPham.java
    │   ├── QuanLyNhanVien.java
    │   ├── QuanLyKhachHang.java
    │   ├── QuanLyHoaDon.java
    │   └── QuanLyPhieuNhap.java
    │
    ├── utils/                 # Các tiện ích bổ trợ
    │   ├── FileUtil.java      # Xử lý đọc/ghi file text
    │   └── Validator.java     # Kiểm tra dữ liệu nhập an toàn từ Scanner
    │
    └── main/                  # Chương trình chính
        └── Main.java          # Menu điều hướng ứng dụng
    ```

---

## 3. Thiết kế hướng đối tượng (OOP) cốt lõi

### A. Nhóm Sản Phẩm & Linh Kiện
* **`SanPham` (Abstract Class):** Quản lý các thuộc tính chung gồm mã sản phẩm (`maSP`), tên (`tenSP`), hãng sản xuất (`hangSX`), giá nhập, giá bán, số lượng tồn kho, thời gian bảo hành[cite: 5]. Định nghĩa các phương thức trừu tượng `hienThiThongTin()`, `tinhGiaBan()`.
* **Kế thừa đa cấp:** 
  * `MayTinh` kế thừa `SanPham` (bổ sung thông tin loại máy, CPU, RAM, SSD...).
  * `LinhKien` (Abstract) kế thừa `SanPham`, phân nhánh thành các chi tiết cụ thể như `CPU`, `RAM`, `CardDoHoa`, `OCung`, `Nguon` với các thông số kỹ thuật đặc thù riêng.

### B. Nhóm Người Dùng
* **`Nguoi` (Abstract Class):** Lớp cơ sở chứa thông tin họ tên, ngày sinh, số điện thoại, email.
* **Kế thừa:** 
  * `NhanVien`: Bổ sung mã nhân viên, lương cơ bản, phụ cấp, tiền thưởng và phương thức tính lương tổng (`tinhLuong() = LuongCoBan + PhuCap + TienThuong`).
  * `QuanLy`: Kế thừa `NhanVien`, bổ sung phụ cấp chức vụ riêng.
  * `KhachHang`: Bổ sung địa chỉ và điểm tích lũy.

### C. Nhóm Giao Dịch & Nhập Hàng
* **`HoaDon` & `ChiTietHoaDon`:** Quản lý thông tin xuất bán sản phẩm cho khách hàng, liên kết với nhân viên lập hóa đơn và mảng chi tiết sản phẩm mua kèm, tự động tính tổng tiền.
* **`PhieuNhap` & `ChiTietPhieuNhap`:** Quản lý quy trình nhập hàng từ các `NhaCungCap`, cập nhật lại số lượng tồn kho và tính toán tổng tiền nhập.

---

## 4. Các tính năng quản lý danh sách thủ công

Mỗi module quản lý (`QuanLySanPham`, `QuanLyNhanVien`, `QuanLyKhachHang`, `QuanLyHoaDon`, `QuanLyPhieuNhap`) đều được xây dựng dựa trên mảng tĩnh tự co giãn kích thước với đầy đủ các thao tác:
1. **Khởi tạo & Nhập danh sách:** Nhập danh sách $n$ phần tử ban đầu.
2. **Hiển thị:** Xuất danh sách dạng bảng trực quan, rõ ràng.
3. **Thêm mới:** Thêm phần tử vào cuối hoặc chèn vào vị trí bất kỳ trong mảng.
4. **Chỉnh sửa:** Cập nhật thông tin chi tiết của đối tượng dựa theo mã định danh (ví dụ: `maSP`, `maNV`).
5. **Xóa:** Xóa phần tử khỏi danh sách dựa theo mã và dịch chuyển mảng tối ưu.
6. **Tìm kiếm & Sắp xếp:** Hỗ trợ tìm kiếm theo mã, tìm gần đúng theo tên, sắp xếp danh sách theo giá bán bằng thuật toán sắp xếp cơ bản (như Bubble Sort).
7. **Thống kê:** Thống kê tổng số lượng tồn kho, giá trị tồn kho, tổng doanh thu bán hàng.

---

## 5. Xử lý ngoại lệ & Lưu trữ dữ liệu

* **Xử lý dữ liệu (`FileUtil` & `IFileIO`):** Triển khai cơ chế đọc/ghi file văn bản (text) để lưu trữ trạng thái của danh sách sản phẩm, nhân viên, hóa đơn qua mỗi lần tắt/mở ứng dụng.
* **Kiểm tra dữ liệu nhập (`Validator`):** Sử dụng khối lệnh `try-catch` để bắt lỗi người dùng nhập sai kiểu dữ liệu (ví dụ: nhập chữ vào ô số lượng/giá tiền), đảm bảo chương trình không bị crash đột ngột.

---

## 6. Hướng dẫn biên dịch và chạy chương trình

1. **Yêu cầu hệ thống:** Đã cài đặt Java Development Kit (JDK 8 trở lên).
2. **Biên dịch mã nguồn:**
   Mở terminal tại thư mục chứa source code và thực hiện biên dịch các file `.java`:
   ```bash
   javac -d bin src/**/*.java

1. **Chạy ứng dụng:**
    java -cp bin main.Main