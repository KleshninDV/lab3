package zad3;

import zad2.Gun;

// Наследуемся от класса Gun, чтобы получить все его свойства и методы
public class MachineGun extends Gun {

    // Поле для хранения скорострельности (выстрелов в секунду)
    // final - неизменяемое, как требует задание
    private final int fireRate;

    // Конструктор C: С указанием вместимости и скорострельности (Основной)
    public MachineGun(int maxAmmo, int fireRate) {
        // Вызываем конструктор родителя (Gun) для установки патронов
        super(maxAmmo);

        if (fireRate <= 0) {
            throw new IllegalArgumentException("Скорострельность должна быть положительной! Введено: "
                    + fireRate);
        }
        this.fireRate = fireRate;
    }

    // Конструктор A: Без параметров
    // Скорострельность 30, вместимость 30
    public MachineGun() {
        this(30, 30); // Вызов собственного конструктора C
    }

    // Конструктор B: Только вместимость
    // Скорострельность равна половине обоймы
    public MachineGun(int maxAmmo) {
        this(maxAmmo, maxAmmo / 2); // Вызов собственного конструктора C
    }

    // При одном вызове делает fireRate выстрелов
    @Override
    public void shoot() {
        // Делаем цикл на количество выстрелов, равное скорострельности
        for (int i = 0; i < fireRate; i++) {
            super.shoot();
        }
    }

    // Новый метод: Стрельба в течение N секунд
    public void shoot(int seconds) {
        if (seconds <= 0) {
            throw new IllegalArgumentException("Время стрельбы должно быть положительным.");
        }

        // Общее количество выстрелов = секунды * скорострельность
        int totalShots = seconds * fireRate;

        System.out.println("Стрельба " + seconds + " сек. (" + totalShots + " попыток выстрела):");

        for (int i = 0; i < totalShots; i++) {
            super.shoot();
        }
    }

    // Геттер для скорострельности
    public int getFireRate() {
        return fireRate;
    }

    @Override
    public String toString() {
        // Используем super.toString() для получения базовой информации и добавляем свою
        return "Автомат { скорострельность: " + fireRate + ", состояние: " + super.toString() + " }";
    }
}