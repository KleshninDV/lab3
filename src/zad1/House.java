package zad1;

public class House {

    // Поле private для инкапсуляции
    // Поле final для неизменяемости
    private final int numberOfFloors;

    public House(int numberOfFloors) {
        // Если данные некорректны, выбрасываем исключение
        if (numberOfFloors <= 0) {
            throw new IllegalArgumentException("Количество этажей должно быть положительным! Введено: "
                    + numberOfFloors);
        }
        this.numberOfFloors = numberOfFloors;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    @Override
    public String toString() {
        return "Дом { этажей: " + numberOfFloors + " }";
    }
}