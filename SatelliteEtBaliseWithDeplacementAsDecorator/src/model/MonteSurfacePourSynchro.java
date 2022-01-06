package model;

import java.awt.Point;

/**
 * Class de déplacement permettant à la balise de remonter à la surface pour synchroniser la balise avec un satellite héritant de "DeplacementBalise"
 */
public class MonteSurfacePourSynchro extends DeplacementBalise {
	
	public MonteSurfacePourSynchro(Deplacement next) {
		super (next);
	}
		
	@Override
	public void bouge(ElementMobile target) {
		Point p = target.getPosition();
		int y = p.y;
		if (y > 0) {
			y -= 3;
			if (y < 0) y = 0;
			target.setPosition(new Point(p.x, y));
		} else {
			target.getManager().baliseReadyForSynchro((Balise) target);
			target.setDeplacement(this.next);			
		}
	}
	
}
