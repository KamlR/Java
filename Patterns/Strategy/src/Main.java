
public class Main {
    public static void main(String[] args) {

        LaserPrinter laserPrinter = new LaserPrinter();
        PhotoPrinter photoPrinter = new PhotoPrinter();
        Printer3D printer3D = new Printer3D();
        laserPrinter.print();
        photoPrinter.print();
        printer3D.print();
    }
}