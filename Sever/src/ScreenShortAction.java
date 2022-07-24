import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScreenShortAction {
    public BufferedImage screenShort() throws AWTException, IOException {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        return image;
    }
}
