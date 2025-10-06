package org.example;
import java.util.Scanner;

public class HalsteadMetricsTask3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите параметры для оценки программиста, используя метрики Холстеда:");
        System.out.print("Введите объемы программ (через пробел): "); //2 3 4 5 6
        String[] volumesInput = scanner.nextLine().split(" ");
        int[] programVolumes = new int[volumesInput.length];
        for (int i = 0; i < volumesInput.length; i++) {
            programVolumes[i] = Integer.parseInt(volumesInput[i]);
        }
        System.out.print("Введите ошибки программ (через пробел): "); //0 0 3 4 3
        String[] errorsInput = scanner.nextLine().split(" ");
        int[] programErrors = new int[errorsInput.length];
        for (int i = 0; i < errorsInput.length; i++) {
            programErrors[i] = Integer.parseInt(errorsInput[i]);
        }
        System.out.print("Начальный рейтинг: "); //3000
        double initialRating = scanner.nextDouble();
        System.out.print("Уровень языка программирования: "); //1,8
        double lambda = scanner.nextDouble();
        System.out.print("Предполагается написать программу объемом в Кбайт: "); //16
        int newProgramVolume = scanner.nextInt();
        scanner.close();

        // Проверка длины массивов
        if (programVolumes.length != programErrors.length) {
            System.out.println("Ошибка: массивы разной длины!");
            return;
        }

        // Рассчитаем для трех вариантов коэффициента c
        calculateForCoefficientType(initialRating, lambda, programVolumes, programErrors, newProgramVolume, 1);
        calculateForCoefficientType(initialRating, lambda, programVolumes, programErrors, newProgramVolume, 2);
        calculateForCoefficientType(initialRating, lambda, programVolumes, programErrors, newProgramVolume, 3);
    }

    private static void calculateForCoefficientType(double initialRating, double lambda, int[] volumes, int[] errors, int newVolume, int coefType) {
        double rating = initialRating;
        int n = volumes.length;

        // Проходим по всем программам и обновляем рейтинг
        for (int i = 0; i < n; i++) {
            double c = calculateC(lambda, rating, coefType);
            double term = (errors[i] > 0) ? errors[i] / c : 0;
            rating = rating * (1 + 0.001 * (volumes[i] - term));
        }

        // Рассчитываем ожидаемое количество ошибок для новой программы
        double cNew = calculateC(lambda, rating, coefType);
        double expectedErrors = cNew * newVolume;

        System.out.println("Результаты для варианта коэффициента " + coefType + ":");
        System.out.printf("Финальный рейтинг: %.2f\n", rating);
        System.out.printf("Ожидаемое количество ошибок в новой программе: %.2f\n", expectedErrors);
        System.out.println();
    }

    private static double calculateC(double lambda, double rating, int coefType) {
        switch (coefType) {
            case 1: return 1 / (lambda + rating);
            case 2: return 1 / (lambda * rating);
            case 3: return 1/lambda + 1/rating;
            default: return 0;
        }
    }
}
