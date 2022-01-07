package views;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.VisitorColor;
import nicellipse.component.NiImage;

/**
 * Class GrBalise qui permet de gérer l'aspect de la balise
 */
public class GrBalise extends GrElementMobile {
	private static final long serialVersionUID = -8672390241177685075L;

	public Color accept(VisitorColor v) {
		return v.visit(this);
	}

	public GrBalise(GrEther ether) {
		super(ether);
		File path = new File("SatelliteEtBaliseWithDeplacementAsDecorator/balise.png");
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
