package views;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.VisitorColor;
import nicellipse.component.NiImage;

/**
 * Class GrBalise qui permet de gérer l'aspect d'un satellite
 */
public class GrSatellite extends GrElementMobile {
	private static final long serialVersionUID = -8534493300853878234L;

	public Color accept(VisitorColor v) {
		return v.visit(this);
	}

	public GrSatellite(GrEther ether) {
		super(ether);
		File path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/satellite.png");
		BufferedImage rawImage = null;
		try {
			rawImage = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(new NiImage(rawImage));
		this.setDimension(new Dimension(rawImage.getWidth(), rawImage.getHeight()));
	}
	
}
