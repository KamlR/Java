public class StartStopComputer implements Computer {
    @Override
    public void turnOnLaptop() {
        System.out.println("Включение компьютера...");
        System.out.println("Запуск системы POST");
        System.out.println("Загрузчик 1-ого уровня");
        System.out.println("Загрузчик 2-ого уровня");
        System.out.println("Загрузчик 3-ого уровня");
        System.out.println("Запуск ядра ОС\n");
    }

    @Override
    public void turnOffLaptop() {
        System.out.println("Выклчюение...");
        System.out.println("Проверка отрытых приложений/программ");
        System.out.println("Оставновка фоновых сервисов");
        System.out.println("Сброс кеша на диск");
        System.out.println("Отправка сигнала выключения ACPI");
    }
}
