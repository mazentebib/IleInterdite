package model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Cellule {
	// class cellule de map
	private int etat;
    public int fire;
    public int ice;
    public int sand;
	private final int size = 32;
	private int chest;
	public int hasarti;
	private String imgName;
	private Image img;
	protected int x, y;
	
	private IleModel modele;
	
	//	etat : traversable,innodee,submerge,bloque,heli
	//constructeur de cellule avec postion , etat 
	public Cellule(IleModel modele, int x, int y, int etat, int fire , int ice , int sand,int chest) {
		this.modele = modele;
		this.chest = chest;
		if (this.chest == 1) {
			this.hasarti =1;
		}else {
			this.hasarti = 0;
		}
		this.setEtat(etat);
		this.fire = fire;
		this.sand = sand;
	    this.ice = ice;
		this.x = x;
		this.y = y;

	}

	public Cellule(int x, int y) {
		this.x = x;
		this.y = y;
		this.setEtat(0) ;
	}
	// set l'image de cellule
	public void setImg(String imgName) {
		this.imgName=imgName;
		this.img = new ImageIcon(getClass().getClassLoader().getResource(this.imgName)).getImage();
	}
	public int artifact() {
		return this.chest;
	}
	// abscisse de cellule
	public int x() {
		return x;
	}
	// l'ordonnée de cellule
	public int y() {
		return y;
	}
	// check si cellule traversable 
	public boolean estTraversable() {
		return getEtat() == 0;
	}
	// check si cellule été innodee 
	public boolean wasInnodee() {
		return getEtat() == -1;
	}
	// check si cellule traversable 
	public boolean estInnodee() {
		return getEtat() == 1;
	}
	// get l'image de cellule
	public Image getImg() {
		return this.img;
	}
	// test si cellule submerge
	public boolean estSubmergee() {
		return getEtat() == 2;
	}
	// test si cellule bloquee
	public boolean estBloquee() {
		return getEtat() == 3;
	}

	public void setEtat(int e) {
		this.etat = e;
	}
	public int getEtat() {
		return etat;
	}
	// paint cellule
	public void paintCel(Graphics2D g, Image img, int x, int y) {

		g.drawImage(img, x, y, 32, 32, null);
	}

	

}