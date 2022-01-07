package views;

import model.Balise;
import model.ElementMobile;
import nicellipse.component.NiImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrProgressIndicator extends NiImage {

    public GrProgressIndicator(GrElementMobile elementMobile) {
        super();
        File path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/0.png");
        BufferedImage rawImage = null;
        try {
            rawImage = ImageIO.read(path);
            rawImage = this.resizeImage(rawImage, 20, elementMobile.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setImage(rawImage);
        this.setDimension(new Dimension(elementMobile.getWidth(), elementMobile.getHeight()));
        elementMobile.add(this);
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public void changeProgressIndicator(ElementMobile element) {
        BufferedImage rawImage = null;
        try {
            File path = this.getProgressIndicatorFileToDisplay(element);
            rawImage = ImageIO.read(path);
            rawImage = this.resizeImage(rawImage, 20, ((int) this.getHeight()));
            this.setImage(rawImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getProgressIndicatorFileToDisplay(ElementMobile element) {
        File path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/0.png");

        if(element.dataSize() >= element.memorySize()/4) path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/25.png");
        if(element.dataSize() >= element.memorySize()/2) path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/50.png");
        if(element.dataSize() >= ((element.memorySize()/2) + (element.memorySize()/4))) path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/75.png");
        if(element.dataSize() == element.memorySize()) path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/100.png");

        return path;
    }
}
