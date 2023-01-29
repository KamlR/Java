public class PhotoPrinter extends Printers {
    public PhotoPrinter() {
        super(new PhotoStrategy());
    }
}
