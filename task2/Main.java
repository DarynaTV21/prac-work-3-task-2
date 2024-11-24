package task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String directoryPath;
        while (true) {
            System.out.print("Введіть шлях до директорії: ");
            directoryPath = scanner.nextLine();

            // Перевірка, чи існує директорія
            Path path = Paths.get(directoryPath);
            if (Files.exists(path) && Files.isDirectory(path)) {
                break; // якщо директорія існує, вийти з циклу
            } else {
                System.out.println("Вказаний шлях не є валідним або не є директорією. Спробуйте ще раз.");
            }
        }

        String fileExtension;
        while (true) {
            System.out.print("Введіть розширення файлів для пошуку (наприклад, .pdf): ");
            fileExtension = scanner.nextLine();

            // Перевірка, чи розширення не порожнє та починається з крапки
            if (fileExtension.startsWith(".")) {
                break; // якщо розширення коректне, вийти з циклу
            } else {
                System.out.println("Невірне розширення файлів. Введіть розширення, яке починається з крапки (наприклад, .pdf).");
            }
        }

        Path path = Paths.get(directoryPath);

        // Використання ForkJoinPool для виконання задачі
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask task = new ForkJoinTask(path, fileExtension);

        long startTime = System.currentTimeMillis();
        long result = forkJoinPool.invoke(task);
        long endTime = System.currentTimeMillis();

        System.out.println("Кількість файлів з розширенням " + fileExtension + ": " + result);
        System.out.println("Час виконання: " + (endTime - startTime) + " мс");
    }
}
