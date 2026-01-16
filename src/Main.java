import java.util.Scanner;
import zad1.House;
import zad2.Gun;
import zad3.MachineGun;
import zad5.Point;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        runTask1(scanner);
        System.out.println("\n---------------------------------------------");

        runTask2(scanner);
        System.out.println("\n---------------------------------------------");

        runTask3(scanner);
        System.out.println("\n---------------------------------------------");

        runTask4(scanner);
        System.out.println("\n---------------------------------------------");

        runTask5(scanner);
        System.out.println("\n---------------------------------------------");

        runTask6(scanner);

        System.out.println("\nВсе задания выполнены.");
        scanner.close();
    }

    // ... (методы runTask1, runTask2, runTask3, runTask4, runTask5 остаются без изменений) ...

    // ================= МЕТОДЫ ЗАДАНИЯ 1 =================
    private static void runTask1(Scanner scanner) {
        System.out.println("============== ЗАДАНИЕ 1 (Дом) ==============");
        System.out.println("Создание дома. Введите количество этажей:");
        while (true) {
            int floors = getValidIntInput(scanner);
            try {
                House userHouse = new House(floors);
                System.out.println("Успех! Создан: " + userHouse);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.print("Попробуйте снова: ");
            }
        }
    }

    // ================= МЕТОДЫ ЗАДАНИЯ 2 =================
    private static void runTask2(Scanner scanner) {
        System.out.println("============== ЗАДАНИЕ 2 (Пистолет) ==============");
        System.out.println("Введите вместимость:");
        Gun userGun = null;
        while (true) {
            try {
                userGun = new Gun(getValidIntInput(scanner));
                System.out.println("Создан: " + userGun);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.print("Попробуйте снова: ");
            }
        }
        // Краткое демо
        System.out.println("Демонстрация: Заряжаем 5, Стреляем.");
        userGun.reload(5);
        userGun.shoot();
        System.out.println(userGun);
    }

    // ================= МЕТОДЫ ЗАДАНИЯ 3 =================
    private static void runTask3(Scanner scanner) {
        System.out.println("============== ЗАДАНИЕ 3 (Автомат) ==============");
        System.out.println("Создаем автомат по умолчанию.");
        MachineGun mg = new MachineGun();
        System.out.println("Создан: " + mg);
    }

    // ================= МЕТОДЫ ЗАДАНИЯ 4 =================
    private static void runTask4(Scanner scanner) {
        System.out.println("============== ЗАДАНИЕ 4 (Abstract Weapon) ==============");
        System.out.println("Введите вместимость:");
        while(true) {
            try {
                zad4.Gun g = new zad4.Gun(getValidIntInput(scanner));
                System.out.println("Создан: " + g);
                break;
            } catch(Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    // ================= МЕТОДЫ ЗАДАНИЯ 5 =================
    private static void runTask5(Scanner scanner) {
        System.out.println("============== ЗАДАНИЕ 5 (Точки) ==============");
        System.out.println("Введите X и Y для точки 1:");
        Point p1 = new Point(getValidIntInput(scanner), getValidIntInput(scanner));
        System.out.println("Введите X и Y для точки 2:");
        Point p2 = new Point(getValidIntInput(scanner), getValidIntInput(scanner));
        System.out.println("Точки равны? " + p1.equals(p2));
    }

    // ================= МЕТОДЫ ЗАДАНИЯ 6 =================
    private static void runTask6(Scanner scanner) {
        System.out.println("============== ЗАДАНИЕ 6 (Protected Ammo) ==============");
        System.out.println("В этом задании поле ammo имеет модификатор protected.");
        System.out.println("Это позволило упростить метод discharge() в классе Gun.");

        System.out.println("Введите вместимость пистолета:");
        zad6.Gun gun = null;
        while(true) {
            try {
                gun = new zad6.Gun(getValidIntInput(scanner));
                System.out.println("Создан: " + gun);
                break;
            } catch(Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        boolean active = true;
        while (active) {
            System.out.println("\nМеню (Protected Gun):");
            System.out.println("1. Стрелять (прямой доступ к ammo)");
            System.out.println("2. Зарядить");
            System.out.println("3. Разрядить (упрощенный метод)");
            System.out.println("4. Инфо");
            System.out.println("0. Завершить задание 6");
            System.out.print("Ваш выбор > ");

            String c = scanner.nextLine();
            switch (c) {
                case "1":
                    gun.shoot();
                    break;
                case "2":
                    System.out.print("Сколько зарядить? ");
                    try {
                        int back = gun.reload(getValidIntInput(scanner));
                        System.out.println("Вернулось: " + back);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                case "3":
                    // Здесь работает упрощенный код: ammo = 0
                    System.out.println("Разряжено: " + gun.discharge());
                    break;
                case "4":
                    System.out.println(gun);
                    break;
                case "0":
                    active = false;
                    break;
                default:
                    System.out.println("Неверная команда.");
            }
        }
    }

    public static int getValidIntInput(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Ввод числа: ");
            }
        }
    }
}