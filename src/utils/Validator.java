package utils;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validator {

    private static final Scanner scanner = new Scanner(System.in);

    // Nhap so nguyen
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println("Loi: Gia tri phai tu "
                        + min + " den " + max + "!");

            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so nguyen hop le!");
            }
        }
    }

    // Nhap so thuc
    public static double readDouble(String prompt, double min) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());

                if (Double.isFinite(value) && value >= min) {
                    return value;
                }

                System.out.println("Loi: Gia tri phai lon hon hoac bang "
                        + min + "!");

            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so thuc hop le!");
            }
        }
    }

    // Nhap chuoi khong rong
    public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Loi: Thong tin khong duoc de trong!");
        }
    }

    // Kiem tra email
    public static String readEmail(String prompt) {
        while (true) {
            String email = readString(prompt);

            if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return email;
            }

            System.out.println("Loi: Email khong dung dinh dang!");
        }
    }

    // Kiem tra so dien thoai Viet Nam
    public static String readPhone(String prompt) {
        while (true) {
            String phone = readString(prompt);

            if (phone.matches("0[0-9]{9}")) {
                return phone;
            }

            System.out.println("Loi: So dien thoai phai gom 10 chu so va bat dau bang 0!");
        }
    }

    // Kiem tra ngay theo dinh dang yyyy-MM-dd
    public static String readDate(String prompt) {
        while (true) {
            String date = readString(prompt);

            try {
                LocalDate.parse(date);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Loi: Ngay khong hop le! Dinh dang yyyy-MM-dd.");
            }
        }
    }
}
