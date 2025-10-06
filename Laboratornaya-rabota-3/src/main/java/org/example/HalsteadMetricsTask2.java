package org.example;
import java.util.Scanner;

public class HalsteadMetricsTask2 {

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
        System.out.print("Количество программистов: "); //5
        int m = scanner.nextInt();
        System.out.print("Производительность (команд/день): "); //20
        int v = scanner.nextInt();
        System.out.print("Продолжительность рабочего дня в часах: "); //8
        int hoursPerDay = scanner.nextInt();
        scanner.close();

        // Расчёт минимального числа операндов (n2*)
        int n2Star = trackedParameters * a * b + calculatedParameters * a;

        // a) Расчёт структурных параметров
        double k = n2Star / 8.0;
        boolean isHierarchical = k > 8;

        double K;
        if (isHierarchical) {
            int i = (int) (Math.log(n2Star) / Math.log(2) / 3 + 1);
            K = n2Star / 8.0 + n2Star / Math.pow(8, 2);
        } else {
            K = Math.ceil(k); // Округляем вверх для получения целого числа модулей
        }

        // б) Расчет длины программы
        double N = 220 * K + K * (Math.log(K) / Math.log(2));

        // в) Расчет объема программного обеспечения
        double V = K * 220 * (Math.log(48) / Math.log(2));

        // г) Расчет количества команд ассемблера
        double P = 3 * N / 8;

        // д) Расчет календарного времени программирования (в днях)
        double TkDays = 3 * N / (8 * m * v);

        // Перевод времени в часы
        double TkHours = TkDays * hoursPerDay;

        // е) Расчет потенциального количества ошибок
        double B = V / 3000;

        // ж) Расчет начальной надежности ПО
        double lnB = Math.log(B);
        double tn = TkHours / (2 * Math.abs(lnB)); // Используем абсолютное значение логарифма

        System.out.println("Результаты расчёта для задания №2 (вариант 3):");
        System.out.printf("Число модулей (K): %.2f\n", K);
        System.out.printf("Длина программы (N): %.2f\n", N);
        System.out.printf("Объем программного обеспечения (V): %.2f\n", V);
        System.out.printf("Количество команд ассемблера (P): %.2f\n", P);
        System.out.printf("Календарное время программирования (Tk): %.2f дней или %.2f часов\n",
                TkDays, TkHours);
        System.out.printf("Потенциальное количество ошибок (B): %.2f\n", B);
        System.out.printf("Начальная надежность ПО (tn): %.2f часов\n", tn);
    }
}
