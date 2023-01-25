import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Task2 {
    private static final String THIS_IS_FILE = " - это файл";
    private static final String THIS_IS_DIR = " - это директория";
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            Path path = Path.of(str);
            if (isRegularFile(path)) {
                System.out.println(path + THIS_IS_FILE);
            } else if (isDirectory(path)){
                System.out.println(path + THIS_IS_DIR);
            } else {
                break;
            }
        }
    }

    public static boolean isRegularFile(Path path) {
        return Files.isRegularFile(path);
    }
    public static boolean isDirectory(Path path) {
        return Files.isDirectory(path);
    }
}
