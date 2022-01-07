package views;


import java.awt.*;

import events.PositionChangeListener;
import events.PositionChanged;
import events.SynchroEvent;
import events.SynchroEventListener;
import model.ElementMobile;
import model.Visitable;
import model.VisitorColor;
import nicellipse.component.NiRectangle;

public class GrElementMobile extends NiRectangle implements PositionChangeListener, SynchroEventListener, Visitable {
	private static final long serialVersionUID = -5422724191168577346L;
	ElementMobile model;
	GrEther ether;
	Boolean duringSynchro = false;

	public GrElementMobile(GrEther ether) {
		this.ether = ether;
		this.setBorder(null);
		this.setBackground(null);
		this.setOpaque(false);
	}

	public GrElementMobile() {
		this.setBorder(null);
		this.setBackground(null);
		this.setOpaque(false);
	}

	public Color accept(VisitorColor v) {
		return Color.black;
	}

	Object getModel() { return this.model; }
	
	public void setModel(ElementMobile model) {
		this.model = model;
		model.registerListener(PositionChanged.class, this);
		model.registerListener(SynchroEvent.class, this);
		this.setLocation(this.model.getPosition());
		this.repaint();		
	}
	
	@Override
	public void whenStartSynchro(SynchroEvent arg) {
		duringSynchro = true;
		this.ether.startSync(this);	
	}

	@Override
	public void whenStopSynchro(SynchroEvent arg) {
		duringSynchro = false;
		this.ether.stopSync(this);	
	}

	@Override
	public void whenPositionChanged(PositionChanged arg) {
		this.setLocation(this.model.getPosition());
		this.repaint();				
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}