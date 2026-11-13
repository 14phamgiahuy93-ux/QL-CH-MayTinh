package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    public static void ghiChuoi(String tenFile, String noiDung) {
        try {
            Files.writeString(
                Path.of(tenFile),
                noiDung,
                StandardCharsets.UTF_8
            );
            System.out.println("Ghi file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public static String docChuoi(String tenFile) {
        try {
            return Files.readString(
                Path.of(tenFile),
                StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
            return "";
        }
    }
}
