package model;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import javax.swing.JPanel;

import vue.Observable;

public class IleModel extends Observable {

	public Player[] playerArray;
	public static final int HAUTEUR = 24, LARGEUR = 24;
	public int index;
	public Cellule[][] cellules;
	//construit notre model de l'ile avec des joueurs et des cellules ainsi que les artefacts et les zones speciales
	public IleModel(Player[] playerArray) {
		this.index = 0;
		cellules = new Cellule[LARGEUR][HAUTEUR];
		for (int i = 0; i < LARGEUR ; i++) {
			for (int j = 0; j < HAUTEUR ; j++) {
				if (i <= 11 && j <= 11) {
					cellules[i][j] = new Cellule(this, i, j, 0, 1, 0, 0, 0);
					
				} else if (i > 11 && i < LARGEUR + 2 && j <= 11) {
					cellules[i][j] = new Cellule(this, i, j, 0, 0, 0, 1, 0);
				} else if (i <= 11 && j > 11 && j < HAUTEUR + 2) {
					cellules[i][j] = new Cellule(this, i, j, 0, 0, 1, 0, 0);
				} else {
					cellules[i][j] = new Cellule(this, i, j, 0, 0, 0, 0, 0);
				}
			}
		}
		//the beach of the island
		for(int i=1;i<LARGEUR-1;i++) {
			cellules[i][0].setImg("fou9.png");
			cellules[i][0].setEtat(3);
		}
		for(int i=1;i<HAUTEUR-1;i++) {
			cellules[0][i].setImg("L.png");
			cellules[0][i].setEtat(3);
		}
		for(int i=1;i<HAUTEUR-1;i++) {
			cellules[LARGEUR-1][i].setImg("R.png");
			cellules[LARGEUR-1][i].setEtat(3);
		}
		for(int i=1;i<LARGEUR-1;i++) {
			cellules[i][HAUTEUR-1].setImg("louta.png");
			cellules[i][HAUTEUR-1].setEtat(3);
		}
		//borders of map
		cellules[0][0].setImg("l0c0.png");
		cellules[LARGEUR-1][0].setImg("l0c60.png");
		cellules[0][HAUTEUR-1].setImg("l60c0.png");
		cellules[LARGEUR-1][HAUTEUR-1].setImg("l60c60.png");
		cellules[0][0].setEtat(3);
		cellules[LARGEUR-1][0].setEtat(3);
		cellules[0][HAUTEUR-1].setEtat(3);
		cellules[LARGEUR-1][HAUTEUR-1].setEtat(3);
		// the chests
		cellules[6][21] = new Cellule(this,6,21, 0,0,0,0,1);
		cellules[21][22] = new Cellule(this,21,22, 0,0,0,0,1);
		cellules[21][2] = new Cellule(this,21,2, 0,0,0,0,1);
		cellules[2][2] = new Cellule(this,2,2, 0,0,0,0,1);
		
		cellules[12][12] = new Cellule(this, 12, 12,0,0,0,0,0); //helicopter
		
		this.playerArray = playerArray;
		
		this.playerArray[0].setPos(cellules[12][14]);
		this.playerArray[1].setPos(cellules[12][13]);
		this.playerArray[2].setPos(cellules[13][12]);
		this.playerArray[3].setPos(cellules[13][13]);

	}
	//renvoie la cellule qui se situe au coordonnees x ,y de notre tableau
	public Cellule getCellule(int x, int y) {
		return cellules[x][y];
	}
	//change l'etat de la cellule passée en parametre (ex:de innondée a submergée || de traversable a innondée )
	public void setCelluleEtat(int x, int y) {
		int etat = cellules[x][y].getEtat();
		cellules[x][y].setEtat(etat + 1);

	}

	public ArrayList<Cellule> Chest() {
		ArrayList<Cellule> res = new ArrayList<Cellule>();
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < HAUTEUR; j++) {
				if (cellules[i][j].artifact() == 1 && cellules[i][j].hasarti == 1) {
					res.add(cellules[i][j]);
				}
			}
		}
		return res;
	}
	//renvoie les zones non submergées de notre carte sous forme de ArrayList
	public ArrayList<Cellule> nonSubmergee() {
		ArrayList<Cellule> res = new ArrayList<Cellule>();
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < HAUTEUR; j++) {
				if (cellules[i][j].getEtat() == 0 || cellules[i][j].getEtat() == 1) {
					res.add(cellules[i][j]);
				}
			}
		}

		return res;
	}
	//retourne les zones adajcentes a un joueur passé en parametre
	public ArrayList<Cellule> celluleAdj(Player p) {
		Cellule pos = p.getPos();
		int x = pos.x();
		int y = pos.y();
		ArrayList<Cellule> res = new ArrayList<Cellule>();
		if (x != 0) {
			res.add(cellules[x - 1][y]);
			if (x != (LARGEUR - 1)) {
				res.add(cellules[x + 1][y]);
			}
		} else {
			res.add(cellules[x + 1][y]);
		}
		if (y != 0) {
			res.add(cellules[x][y - 1]);
			if (y != (HAUTEUR - 1)) {
				res.add(cellules[x][y + 1]);
			}
		} else {
			res.add(cellules[x][y + 1]);
		}
		return res;
	}
	// test si un cellule est adjacent à player 
	public boolean estAdj(Cellule c) {
		ArrayList<Cellule> adj = this.celluleAdj(playerArray[index]);
		return adj.contains(c);
	}
	// test si un player peut se deplacer et pas bloqué 
	public boolean playersCanMove() {
		boolean res = true;
		for (int i = 0; i < this.playerArray.length; i++) {
			ArrayList<Cellule> adj = this.celluleAdj(playerArray[i]);
			int h = 0;
			for (Cellule c : adj) {
				if (c.estSubmergee()) {
					h++;
				}
				if (h == 4) {
					res = false;
					break;
				}
			}
		}
		return res;
	}
	// test si un player est submergé
	public boolean playerSubmergee() {
		Cellule pos;
		for (int i = 0; i < playerArray.length; i++) {
			pos = this.playerArray[i].getPos();
			if (cellules[pos.x()][pos.y()].estSubmergee()) {
				return true;
			}
		}
		return false;
	}
	// test si tous les joueurs dans l'helicopter 
	public boolean playerOnDeck() {
		int j =0;
		for (int i=0;i<playerArray.length;i++) {
		   if (playerArray[i].getPos().x()==12 && playerArray[i].getPos().y()==12) {
			   j++;
		   }
		}
		return j==4;
	}
	// test si tous les joueurs ont des artifacts 
	public boolean playersHasAllArti() {
		return playerArray[0].Artifact()==4;
	}

}
