package vue;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.IleModel;
import model.Player;

public class Ile {	
	// main de game 
	// creer 4 players et model et vue 
	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			Player[] playerArray = new Player[4];
			for(int i=0;i<playerArray.length;i++) {
				playerArray[i]=new Player(null,i);
			}
			IleModel modele = new IleModel(playerArray);
			for(int i=0;i<playerArray.length;i++) {
				playerArray[i].setModel(modele);
			}

			IleVue vue = new IleVue(modele);

		});
	}
}