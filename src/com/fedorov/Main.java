package com.fedorov;

import java.io.*;


import java.util.List;

/**
 * Класс Main для создания экземпляров и запуска программы
 */
public class Main {
    public static void main(String[] args) {
        Finder finder = new Finder();
        String userName = System.getProperty("user.name");
        String path1 = "//Desktop//result.txt";
        String path2 = "C://Users//";
        String resultPath = path2 + userName + path1;
        String firstPath = "C:\\Users\\zdes\\Desktop\\test";
        try {
            List<?> result = finder.findFiles(firstPath);
            Sorter sorter = new Sorter();
            result = sorter.sort(result);
            for (Object o : result) {
                File currentObject = (File) o;
                System.out.println("Найден файл: " + currentObject.getName());
            }
            for (Object f : result) {
                FileInputStream in = new FileInputStream((File) f);
                FileOutputStream out = new FileOutputStream(resultPath, true);
                byte[] buffer = new byte[in.available()];
                in.read(buffer, 0, buffer.length);
                out.write(buffer, 0, buffer.length);
            }
            System.out.println("Всего найдено: " + finder.getFilesCount() + " " + "файлов");
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
