package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Cellule;
import model.IleModel;

public class ButtonEnd extends JButton implements MouseListener {
	IleModel model;
	// constucteur de button fin de tour
	public ButtonEnd(IleModel model) {
		super("fin de tour");
		this.model = model;
		this.addMouseListener(this);
		this.setFocusable(false);

	}
	// méthod des actions qui passent en cliquant sur button
	@Override
	public void mouseClicked(MouseEvent e) {
		ArrayList<Cellule> zoneNonSubmergee = this.model.nonSubmergee();
		int size = zoneNonSubmergee.size();
		//ajouter 3 nouveaux zones innodees
		Random randomizer = new Random();
		for (int i = 0; i < 3; i++) {
			int j = randomizer.nextInt(size);
			Cellule c = zoneNonSubmergee.get(j);
			model.setCelluleEtat(c.x(), c.y());
			zoneNonSubmergee.remove(j);
			size--;
		}
	
		this.model.playerArray[model.index].resetCpt();
		// index change to next joueur 
		model.index++;
		if (model.index >= 4) {
			model.index = 0;
		}
		//ajouter un cle si notre random = 3 apres chaque tour
		Random random = new Random();
		int a = randomizer.nextInt(8);
		if (a == 3 && model.playerArray[model.index].keycount() < 4 && model.playerArray[0].Artifact()<4) {
			int s=0;
			for(int i=0;i<model.playerArray.length;i++) {
				s=s+model.playerArray[model.index].keycount();
			}
			if(s<4) {
				model.playerArray[model.index].AddKey();
				showMessage();
			}
		}
		//donne un action special sandbox ou teleport
		int b = randomizer.nextInt(10);
		if (b == 3) {
			model.playerArray[model.index].SacdeSable = 1;
			JOptionPane.showMessageDialog(null,
					model.playerArray[model.index].getName() + " got special action Sand Box");

		}
		int c = randomizer.nextInt(15);
		if (c == 3) {
			model.playerArray[model.index].teleport = 1;
			JOptionPane.showMessageDialog(null,
					model.playerArray[model.index].getName() + " got special action teleport");

		}
		// check si apres un tour les joueurs ont gagné
		if (checkWinning()) {
			JOptionPane.showMessageDialog(null, "Congratulation you have won the game !!!");
			System.exit(0);
		}
		// check si apres un tour les joueurs ont perdu
		if (checkEndGame()) {
			JOptionPane.showMessageDialog(null, "Game Lost Player Died");
			System.exit(0);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void showMessage() {
		JOptionPane.showMessageDialog(null, "the team got a key!!!!");
	}
	// methode d'end game
	// check si un joueur submergé ou heliport submergé ou un joeur peut pas deplacer
	private boolean checkEndGame() {
		if (model.playerSubmergee() || model.cellules[12][12].estSubmergee() || (!model.playersCanMove())) {
			return true;
		}
		return false;
	}
	// methode check winning
	// test si tous les joueurs sont dans l'helicopter et ont tous les atrifacts 
	private boolean checkWinning() {
		return model.playerOnDeck() && model.playersHasAllArti();
	}

}
