import java.util.Scanner;
import zad1.House;
import zad2.Gun;
import zad3.MachineGun;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Выполнение Задания 1
        runTask1(scanner);

        System.out.println("\n---------------------------------------------");

        // Выполнение Задания 2
        runTask2(scanner);

        System.out.println("\n---------------------------------------------");

        // Выполнение Задания 3
        runTask3(scanner);

        System.out.println("\nВсе задания выполнены.");
        scanner.close();
    }

    // ================= МЕТОДЫ ЗАДАНИЯ 1 (Дом) =================
    private static void runTask1(Scanner scanner) {
        System.out.println("============== ЗАДАНИЕ 1 (Дом) ==============");
        System.out.println("Создание дома. Введите данные с клавиатуры.");
        System.out.println("Введите количество этажей (попробуйте <= 0 для проверки ошибки):");

        // Цикл ввода, пока не будет создан корректный объект
        while (true) {
            int floors = getValidIntInput(scanner);
            try {
                // Попытка создания объекта
                House userHouse = new House(floors);
                System.out.println("Успех! Создан объект: " + userHouse);
                break; // Выходим из цикла, так как дом создан
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка валидации: " + e.getMessage());
                System.out.println("Попробуйте ввести значение еще раз.");
            }
        }
    }

    // ================= МЕТОДЫ ЗАДАНИЯ 2 (Пистолет) =================
    private static void runTask2(Scanner scanner) {
        System.out.println("============== ЗАДАНИЕ 2 (Пистолет) ==============");
        System.out.println("Создание пистолета.");
        System.out.println("Введите максимальную вместимость магазина:");

        Gun userGun = null;

        // Цикл создания пистолета
        while (true) {
            int max = getValidIntInput(scanner);
            try {
                userGun = new Gun(max);
                System.out.println("Пистолет создан: " + userGun);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + " Попробуйте снова.");
            }
        }

        // Интерактивное меню управления
        boolean active = true;
        while (active) {
            System.out.println("\nМеню пистолета:");
            System.out.println("1. Стрелять (Бах! / Клац!)");
            System.out.println("2. Зарядить патроны");
            System.out.println("3. Разрядить (вынуть все)");
            System.out.println("4. Информация о пистолете");
            System.out.println("0. Завершить задание 2");
            System.out.print("Ваш выбор > ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userGun.shoot();
                    break;

                case "2":
                    System.out.println("Сколько патронов зарядить?");
                    // Внутренний цикл для ввода количества патронов
                    while (true) {
                        int amount = getValidIntInput(scanner);
                        try {
                            int excess = userGun.reload(amount);
                            System.out.println("Заряжено. Лишних патронов вернулось: " + excess);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: " + e.getMessage() + " Попробуйте снова.");
                        }
                    }
                    break;

                case "3":
                    int removed = userGun.discharge();
                    System.out.println("Пистолет разряжен. Извлечено патронов: " + removed);
                    break;

                case "4":
                    System.out.println("Статус: " + userGun);
                    System.out.println("Заряжен? " + (userGun.isLoaded() ? "Да" : "Нет"));
                    System.out.println("Патронов внутри: " + userGun.getAmmo());
                    System.out.println("Вместимость: " + userGun.getMaxAmmo());
                    break;

                case "0":
                    active = false;
                    break;

                default:
                    System.out.println("Неверная команда.");
            }
        }
    }

    // ================= МЕТОДЫ ЗАДАНИЯ 3 (Автомат) =================
    private static void runTask3(Scanner scanner) {
        System.out.println("============== ЗАДАНИЕ 3 (Автомат) ==============");
        System.out.println("Выберите способ создания автомата:");
        System.out.println("1. По умолчанию (30 патронов, 30 выстр/сек)");
        System.out.println("2. Только вместимость (скорострельность = вместимость / 2)");
        System.out.println("3. Вместимость и скорострельность вручную");

        MachineGun machineGun = null;

        // Цикл создания автомата
        while (machineGun == null) {
            System.out.print("Ваш выбор способа > ");
            String mode = scanner.nextLine();

            try {
                switch (mode) {
                    case "1":
                        machineGun = new MachineGun();
                        break;

                    case "2":
                        System.out.println("Введите вместимость:");
                        int caps = getValidIntInput(scanner);
                        machineGun = new MachineGun(caps);
                        break;

                    case "3":
                        System.out.println("Введите вместимость:");
                        int c = getValidIntInput(scanner);
                        System.out.println("Введите скорострельность:");
                        int r = getValidIntInput(scanner);
                        machineGun = new MachineGun(c, r);
                        break;

                    default:
                        System.out.println("Неверный выбор. Введите 1, 2 или 3.");
                }

                if (machineGun != null) {
                    System.out.println("Автомат успешно создан: " + machineGun);
                }

            } catch (IllegalArgumentException e) {
                // Перехват логических ошибок (например, <= 0 патронов)
                System.out.println("Ошибка создания: " + e.getMessage());
                System.out.println("Попробуйте снова.");
                machineGun = null; // Сброс для повтора цикла
            }
        }

        // Интерактивное меню автомата
        boolean active = true;
        while (active) {
            System.out.println("\nМеню автомата:");
            System.out.println("1. Одиночный вызов 'Стрелять' (серия = скорострельности)");
            System.out.println("2. Стрелять N секунд");
            System.out.println("3. Зарядить");
            System.out.println("4. Разрядить");
            System.out.println("5. Инфо");
            System.out.println("0. Завершить задание 3");
            System.out.print("Ваш выбор > ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    machineGun.shoot();
                    break;

                case "2":
                    System.out.println("Сколько секунд стрелять?");
                    while (true) {
                        int sec = getValidIntInput(scanner);
                        try {
                            machineGun.shoot(sec);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                    }
                    break;

                case "3":
                    System.out.println("Сколько зарядить?");
                    // Используем методы родителя (Gun), но вызываем их у объекта machineGun
                    int toReload = getValidIntInput(scanner);
                    try {
                        int back = machineGun.reload(toReload);
                        System.out.println("Заряжено. Вернулось лишних: " + back);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case "4":
                    int removed = machineGun.discharge();
                    System.out.println("Разряжено. Извлечено: " + removed);
                    break;

                case "5":
                    System.out.println(machineGun); // Вывод toString
                    System.out.println("Текущих патронов: " + machineGun.getAmmo());
                    break;

                case "0":
                    active = false;
                    break;

                default:
                    System.out.println("Неверная команда.");
            }
        }
    }

    // Общий метод ввода целых чисел с проверкой
    public static int getValidIntInput(Scanner scanner) {
        while (true) {
            System.out.print("Ввод числа: ");
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введено не число. Попробуйте снова.");
            }
        }
    }
}