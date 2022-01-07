package views;

import model.ElementMobile;
import nicellipse.component.NiImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrProgressIndicator extends NiImage {

    BufferedImage file0Percents, file25Percents, file50Percents, file75Percents, file100Percents;

    public GrProgressIndicator(GrElementMobile elementMobile) {
        super();
        this.initFiles(elementMobile);
        this.setImage(file0Percents);
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
        if(this.getImage() != this.getProgressIndicatorFileToDisplay(element))
            this.setImage(this.getProgressIndicatorFileToDisplay(element));
    }

    public void initFiles(GrElementMobile elementMobile) {
        try {
            file0Percents = this.resizeImage(ImageIO.read(new File("SatelliteEtBaliseWithDeplacementAsDecorator/0.png")), 20, ((int) elementMobile.getHeight()));
            file25Percents = this.resizeImage(ImageIO.read(new File("SatelliteEtBaliseWithDeplacementAsDecorator/25.png")), 20, ((int) elementMobile.getHeight()));
            file50Percents = this.resizeImage(ImageIO.read(new File("SatelliteEtBaliseWithDeplacementAsDecorator/50.png")), 20, ((int) elementMobile.getHeight()));
            file75Percents = this.resizeImage(ImageIO.read(new File("SatelliteEtBaliseWithDeplacementAsDecorator/75.png")), 20, ((int) elementMobile.getHeight()));
            file100Percents = this.resizeImage(ImageIO.read(new File("SatelliteEtBaliseWithDeplacementAsDecorator/100.png")), 20, ((int) elementMobile.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getProgressIndicatorFileToDisplay(ElementMobile element) {
        if(element.dataSize() == element.memorySize()) return file100Percents;
        else if(element.dataSize() >= ((element.memorySize()/2) + (element.memorySize()/4))) return file75Percents;
        else if(element.dataSize() >= element.memorySize()/2) return file50Percents;
        else if(element.dataSize() >= element.memorySize()/4) return file25Percents;
        else return file0Percents;
    }
}
