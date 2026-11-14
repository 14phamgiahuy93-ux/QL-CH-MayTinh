package utils;
import java.util.Scanner;

public class Validator {
    private static final Scanner scanner = new Scanner(System.in);

    // Kiểm tra và đọc số nguyên an toàn
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Lỗi: Giá trị phải nằm trong khoảng từ " + min + " đến " + max + "!");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập vào một số nguyên hợp lệ!");
            }
        }
    }

    // Kiểm tra và đọc số thực (dùng cho giá tiền)
    public static double readDouble(String prompt, double min) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min) {
                    return value;
                }
                System.out.println("Lỗi: Giá trị phải lớn hơn hoặc bằng " + min + "!");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập vào một số thực hợp lệ!");
            }
        }
    }

    // Kiểm tra chuỗi không được để trống
    public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Lỗi: Thông tin không được để trống!");
        }
    }
}
