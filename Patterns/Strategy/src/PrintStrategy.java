public interface PrintStrategy {
    public void print();
}

class BlackWhiteStrategy implements PrintStrategy{
    @Override
    public void print() {
        System.out.println("Ваш чёрно-белый документ готов!");
    }
}

class ThreeDStrategy implements PrintStrategy{

    @Override
    public void print() {
        System.out.println("Ваша 3D модель готова к использованию!");
    }
}

class PhotoStrategy implements PrintStrategy{

    @Override
    public void print() {
        System.out.println("Ваша фотография напечатана!");
    }
}