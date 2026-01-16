package zad6;

public class Gun extends Weapon {

    private final int maxAmmo;

    public Gun(int maxAmmo) {
        super(0);
        this.maxAmmo = maxAmmo;
    }

    @Override
    public void shoot() {
        // Мы можем обращаться к ammo напрямую
        if (ammo > 0) {
            System.out.println("Бах!");
            ammo--; // Прямое изменение поля родителя
        } else {
            System.out.println("Клац!");
        }
    }

    public int reload(int count) {
        if (count < 0) throw new IllegalArgumentException("Нельзя зарядить отрицательное число");

        // Прямое чтение поля ammo
        int needed = maxAmmo - ammo;

        if (count > needed) {
            // Прямая запись (или можно использовать load)
            ammo = maxAmmo;
            return count - needed;
        } else {
            ammo += count;
            return 0;
        }
    }

    // Упрощенный метод разрядки (как требовалось в задании)
    public int discharge() {
        int current = ammo; // Берем значение напрямую
        ammo = 0;           // Обнуляем напрямую
        return current;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    @Override
    public String toString() {
        return "Пистолет (Protected Field) { патроны: " + ammo + "/" + maxAmmo + " }";
    }
}