package com.fedorov;

import java.io.File;
import java.text.Collator;
import java.util.*;
import java.util.regex.Pattern;
import java.util.Comparator;


/**
 * Класс Sorter для сортировки найденных файлов
 */
public class Sorter implements Comparator {
    Pattern pattern = null;
    Collator collator = null;

    public Sorter() {
        String separator = File.separator;
        if (separator.equals("\\")) {
            separator = "\\\\";
        }

        pattern = Pattern.compile(separator, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        String country = System.getProperty("user.country");
        String language = System.getProperty("user.language");
        collator = Collator.getInstance(new Locale(country, language));

    }

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 != null && o2 != null && o1 instanceof File && o2 instanceof File) {
            File file1 = (File) o1;
            File file2 = (File) o2;
            String file1Path = file1.getAbsolutePath();
            String file2Path = file2.getAbsolutePath();
            if (file1Path.equals(file2Path)) {
                return 0;
            }

            String[] result1 = pattern.split(file1Path);
            String[] result2 = pattern.split(file2Path);
            if (result1.length > result2.length) {
                return 1;
            }
            if (result1.length < result2.length) {
                return -1;
            }

            if (result1.length == result2.length) {
                return collator.compare(file1Path, file2Path);
            }
        }
        return 0;
    }

    public List sort(List FileList) {
        ArrayList result = new ArrayList(FileList.size());
        result.addAll(FileList);
        Collections.sort(result, this);
        return result;
    }
}

