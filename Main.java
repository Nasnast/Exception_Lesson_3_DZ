package task5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String userData; // ввод только латиницей! :(
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(
                    "Введите через пробел: ФИО, дата рождения (dd.mm.yyyy), номер телефона (6 цифр), пол (f/m)");
            userData = br.readLine();
            // System.out.println(userData);
            checkUserData(userData);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public static void checkUserData(String userData) throws IllegalArgumentException {

        String[] userDataArr = userData.split(" ");

        if (userDataArr.length > 6) {
            throw new IllegalArgumentException(
                    "Неверное количество данных. Вы ввели лишние данные!");
        }
        if (userDataArr.length < 6) {
            throw new IllegalArgumentException(
                    "Неверное количество данных. Вы ввели не все данные!");
        }

        String namePattern = "[A-Za-zА-Яа-я]+";
        String datePattern = "([0-9]{2}).([0-9]{2}).([0-9]{4})";
        String phonePattern = "[0-9]{6}";
        String genderPattern = "[fm]";

        if (!userDataArr[0].matches(namePattern) || !userDataArr[1].matches(namePattern)
                || !userDataArr[2].matches(namePattern)) {
            throw new IllegalArgumentException("Неверный формат ФИО");
        }

        if (!userDataArr[3].matches(datePattern)) {
            throw new IllegalArgumentException("Неверный формат даты рождения");
        }

        if (!userDataArr[4].matches(phonePattern)) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }

        if (!userDataArr[5].matches(genderPattern)) {
            throw new IllegalArgumentException("Неверный формат пола");
        }

        System.out.println("Данные введены корректно: " + '[' + userData + ']');
        writeToFile(userDataArr);

    }

    public static void writeToFile(String[] userDataArr) {

        String outputFileName = userDataArr[0] + ".txt";

        try {
            FileWriter writer = new FileWriter(outputFileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);

            for (String value : userDataArr) {
                bufferWriter.write('<' + value + '>');
            }
            bufferWriter.append('\n');
            System.out.println("Данные записаны в файл " + outputFileName);
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
