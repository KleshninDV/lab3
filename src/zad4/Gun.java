package zad4;

// Наследуемся от абстрактного оружия
public class Gun extends Weapon {

    private final int maxAmmo;

    // Конструктор
    public Gun(int maxAmmo) {
        // Вызываем конструктор родителя, инициализируем 0 патронов
        super(0);
        this.maxAmmo = maxAmmo;
    }

    // Реализация абстрактного метода стрельбы
    @Override
    public void shoot() {
        if (getAmmo()) {
            System.out.println("Бах!");
        } else {
            System.out.println("Клац!");
        }
    }

    // Метод перезарядки (как в задании 2)
    public int reload(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Патроны не могут быть отрицательными");
        }

        // Получаем текущее количество через метод родителя
        int current = ammo();
        int needed = maxAmmo - current;

        if (count > needed) {
            // Заряжаем только то, что влезает
            super.load(needed);
            // Возвращаем остаток
            return count - needed;
        } else {
            // Заряжаем всё
            super.load(count);
            return 0;
        }
    }

    // Метод разрядки
    public int discharge() {
        int count = ammo();

        while (getAmmo()) {
        }

        return count;
    }

    // Геттер вместимости
    public int getMaxAmmo() {
        return maxAmmo;
    }

    // Проверка заряжен ли (используем метод родителя ammo())
    public boolean isLoaded() {
        return ammo() > 0;
    }

    @Override
    public String toString() {
        return "Пистолет (Weapon) { патроны: " + ammo() + "/" + maxAmmo + " }";
    }
}