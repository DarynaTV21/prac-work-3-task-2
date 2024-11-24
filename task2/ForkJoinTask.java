package task2;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTask extends RecursiveTask<Integer> {
    private final Path directory;
    private final String fileExtension;

    public ForkJoinTask(Path directory, String fileExtension) {
        this.directory = directory;
        this.fileExtension = fileExtension;
    }

    @Override
    protected Integer compute() {
        List<ForkJoinTask> subTasks = new ArrayList<>();
        int count = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    // Якщо це директорія, створити підзадачу
                    ForkJoinTask subTask = new ForkJoinTask(entry, fileExtension);
                    subTasks.add(subTask);
                    subTask.fork();
                } else if (entry.toString().endsWith(fileExtension)) {
                    // Якщо це файл з потрібним розширенням, збільшити лічильник
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка доступу до директорії: " + directory);
        }

        // Чекати завершення всіх підзадач та підсумовувати результати
        for (ForkJoinTask subTask : subTasks) {
            count += subTask.join();
        }

        return count;
    }
}
