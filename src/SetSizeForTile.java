import javax.swing.*;
import java.awt.*;

public class SetSizeForTile {

        public static ImageIcon setSize(ImageIcon oldImageIcon, int width,
                                        int height) {

            Image oldImage = oldImageIcon.getImage();
            Image newImg = oldImage.getScaledInstance(width, height,
                    java.awt.Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(newImg);

            return imgIcon;
        }

}

