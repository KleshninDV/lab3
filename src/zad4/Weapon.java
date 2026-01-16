package zad4;

public abstract class Weapon {

    private int ammo;

    // Конструктор
    public Weapon(int ammo) {
        if (ammo < 0) {
            throw new RuntimeException("Количество патронов не может быть отрицательным");
        }
        this.ammo = ammo;
    }

    // Абстрактный метод, который должны реализовать наследники
    abstract void shoot();

    public int ammo() {
        return ammo;
    }

    // Метод, который "достает" патрон.
    public boolean getAmmo() {
        if (ammo == 0) {
            return false;
        }
        ammo--;
        return true; // Патрон извлечен
    }

    // Метод загрузки патронов. Возвращает сколько было передано.
    public int load(int ammo) {
        if (ammo < 0) {
            throw new RuntimeException("Нельзя зарядить отрицательное количество");
        }
        int tmp = ammo;
        this.ammo += ammo;
        return tmp;
    }
}