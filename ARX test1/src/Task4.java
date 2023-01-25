import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path sourceDirectory = Path.of("C:\\Users\\s.koshkin\\Desktop\\Справочники 2023");
        Path targetDirectory = Path.of("C:\\Users\\s.koshkin\\Desktop\\DestFolder");
        try(DirectoryStream<Path> files = Files.newDirectoryStream(sourceDirectory)) {
            for (Path path : files) {
                if (Files.isRegularFile(path)) {
                    Files.copy(path, targetDirectory.resolve(path.getFileName()));
                }
            }
        }

    }
}
