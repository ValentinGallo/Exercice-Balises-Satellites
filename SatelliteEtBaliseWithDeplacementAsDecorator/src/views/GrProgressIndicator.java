package views;

import model.Balise;
import nicellipse.component.NiImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrProgressIndicator extends GrElementMobile {

    public GrProgressIndicator(GrEther ether, Dimension parentDimension) {
        super(ether);
        File path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/0.png");
        BufferedImage rawImage = null;
        try {
            rawImage = ImageIO.read(path);
            rawImage = this.resizeImage(rawImage, 20, ((int) parentDimension.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(new NiImage(rawImage));
        this.setDimension(new Dimension(((int) parentDimension.getWidth()), ((int) parentDimension.getHeight())));
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public void changeProgressIndicator(Balise balise) {
        this.remove(0);

        BufferedImage rawImage = null;
        try {
            File path = this.getProgressIndicatorFileToDisplay(balise);
            rawImage = ImageIO.read(path);
            rawImage = this.resizeImage(rawImage, 20, ((int) this.getHeight()));
            this.add(new NiImage(rawImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getProgressIndicatorFileToDisplay(Balise balise) {
        File path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/0.png");

        if(balise.dataSize() >= balise.memorySize()/4) path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/25.png");
        if(balise.dataSize() >= balise.memorySize()/2) path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/50.png");
        if(balise.dataSize() >= ((balise.memorySize()/2) + (balise.memorySize()/4))) path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/75.png");
        if(balise.dataSize() == balise.memorySize()) path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/100.png");

        return path;
    }
}
