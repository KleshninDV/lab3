package zad6;

public abstract class Weapon {

    // Было private, стало protected.
    // Теперь наследники (Gun) видят это поле и могут его менять напрямую.
    protected int ammo;

    public Weapon(int ammo) {
        if (ammo < 0) {
            throw new RuntimeException("Патроны не могут быть отрицательными");
        }
        this.ammo = ammo;
    }

    abstract void shoot();

    public int ammo() {
        return ammo;
    }

    public boolean getAmmo() {
        if (ammo == 0) return false;
        ammo--;
        return true;
    }

    public int load(int ammo) {
        if (ammo < 0) throw new RuntimeException("Нельзя зарядить отрицательное количество");
        int tmp = ammo;
        this.ammo += ammo;
        return tmp;
    }
}