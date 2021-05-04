package com.fedorov;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс Finder для поиска файлов
 */
public class Finder {
    private long FilesCount = 0;
    private long DirectoriesNumber = 0;

    private final int FILES = 0;


    /**
     * Метод находит все файлы
     *
     * @param -firstPath (корневая директория)
     */
    public List findFiles(String firstPath)
            throws Exception {
        return find(firstPath);
    }

    public long getFilesCount() {
        return FilesCount;
    }

    /**
     * Метод проверяет условия поиска и осуществляет поиск
     *
     * @param -firstPath (корневая директория), objectType (объекты заданного типа)
     */
    private List<?> find(String firstPath)
            throws Exception {
        if (firstPath == null) {
            throw new Exception("Не задана корневая папка");
        }
        File topDirectory = new File(firstPath);
        if (!topDirectory.exists()) {
            throw new Exception("Указанный путь не существует");
        }
        FilesCount = 0;
        ArrayList<?> result = new ArrayList<>();
        search(topDirectory, (List<File>) result, 0);
        return result;
    }


    /**
     * Метод осуществляет поиск заданного типа. При нахождении вложенной папки,
     * метод рекурсивно вызывает сам себя.
     *
     * @param -currentDirectory (текущая директория), objectType (объекты заданного типа)
     */
    private void search(File currentDirectory, List<File> result, int objectType) {
        File[] list = currentDirectory.listFiles();
        for (File file : list) {
            if (file.isDirectory()) {
                if (objectType != FILES) {
                    DirectoriesNumber++;
                    result.add(file);
                }
                search(file, result, objectType);
            } else {
                int DIRECTORIES = 1;
                if (objectType != DIRECTORIES) {
                    FilesCount++;
                    result.add(file);
                }
            }
        }
    }
}

