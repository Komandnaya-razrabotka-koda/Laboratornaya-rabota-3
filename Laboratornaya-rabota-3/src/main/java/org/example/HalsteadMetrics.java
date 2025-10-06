package org.example;
import java.util.Scanner;

public class HalsteadMetrics {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите параметры для расчёта метрик Холстеда:");
        System.out.print("Количество отслеживаемых параметров: "); //12
        int trackedParameters = scanner.nextInt();
        System.out.print("Количество рассчитываемых параметров: "); //3
        int calculatedParameters = scanner.nextInt();
        System.out.print("Число одновременно сопровождаемых целей: "); //30
        int a = scanner.nextInt();
        System.out.print("Количество измерений каждого отслеживаемого параметра: "); //26
        int b = scanner.nextInt();
        System.out.print("Уровень языка программирования: "); //1,8
        double lambda = scanner.nextDouble();
        scanner.close();

        // Расчёт минимального числа операндов (n2*)
        int n2Star = trackedParameters * a * b + calculatedParameters * a;

        // Расчёт потенциального объёма программы (V*)
        double vStar = (n2Star + 2) * (Math.log(n2Star + 2) / Math.log(2));

        // Расчёт потенциального числа ошибок (B)
        double c = Math.pow(vStar, 2) / (3000 * lambda);

        System.out.println("Результаты расчёта для задания 1 (вариант 3):");
        System.out.printf("Минимальное число операндов (n2*): %d\n", n2Star);
        System.out.printf("Потенциальный объём программы (V*): %.2f\n", vStar);
        System.out.printf("Потенциальное число ошибок (B): %.2f\n", c);
    }
}