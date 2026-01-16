package zad2;

public class Gun {

    // Максимальное количество патронов (неизменяемое)
    private final int maxAmmo;
    // Текущее количество патронов
    private int currentAmmo;

    // Конструктор: создает пистолет с указанной вместимостью
    // Изначально пистолет разряжен (0 патронов)
    public Gun(int maxAmmo) {
        if (maxAmmo <= 0) {
            throw new IllegalArgumentException("Максимальное количество патронов должно быть > 0.");
        }
        this.maxAmmo = maxAmmo;
        this.currentAmmo = 0;
    }

    // Метод для стрельбы
    public void shoot() {
        if (currentAmmo > 0) {
            System.out.println("Бах!");
            currentAmmo--; // Уменьшаем количество патронов
        } else {
            System.out.println("Клац!");
        }
    }

    // Перезарядка: добавляет патроны, возвращает лишние
    public int reload(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Нельзя зарядить отрицательное количество патронов!");
        }

        // Вычисляем, сколько патронов нужно для полного магазина
        int needed = maxAmmo - currentAmmo;

        if (count > needed) {
            currentAmmo = maxAmmo;
            return count - needed;
        } else {
            currentAmmo += count;
            return 0;
        }
    }

    // Разрядка: обнуляет патроны и возвращает их количество
    public int discharge() {
        int returnedAmmo = currentAmmo;
        currentAmmo = 0;
        return returnedAmmo;
    }

    // Геттер для текущего количества патронов
    public int getAmmo() {
        return currentAmmo;
    }

    // Геттер для максимальной вместимости
    public int getMaxAmmo() {
        return maxAmmo;
    }

    // Проверка состояния (заряжен/разряжен)
    // Считаем пистолет заряженным, если в нем есть хотя бы 1 патрон
    public boolean isLoaded() {
        return currentAmmo > 0;
    }

    @Override
    public String toString() {
        return "Пистолет { патроны: " + currentAmmo + "/" + maxAmmo + " }";
    }
}