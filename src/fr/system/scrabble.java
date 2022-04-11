package fr.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class scrabble {
	
	static Scanner sc;

	public static void main(String[] args) {
		String tab[] = new String[22506];
		sc = new Scanner(System.in);
		String decision = "o";
		int scoreJoueur = 0;
		String motModif = "";
		String dictionnaire = "C:\\Users\\ib\\Documents\\Java\\partie1\\partie1\\ateliers\\Atelier1\\dictionnaire.txt";
		String mot;
		while(decision.equalsIgnoreCase("o")) {
			creationTableau(dictionnaire,tab);
			mot =  selectionMot(tab);
			motModif =melangeMot(mot);
			scoreJoueur += verifJoueur(mot, tab, motModif);
			System.out.println("Vous avez un total de " + scoreJoueur);
			System.out.println("Voulez vous continuer ? o/n");
			decision = sc.nextLine();
		}
		
	}
	
	public static void creationTableau(String chemin,String tab[]) {
		int i = 0;
		try {
			FileInputStream fis = new FileInputStream(chemin);
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()) {
			tab[i] = sc.nextLine();
			i++;
			
			}
			sc.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Je choisis un mot au hasard
	
	public static String selectionMot(String tab[]) {
		String mot = null;
		int alea = (int) (Math.random() * 22506);
			mot = tab[alea];
			return mot;
		}
	
	// je melange le mot après l'avoir découpé et entré dans un tableau char
	
	public static String melangeMot(String mot) {
		String motModif = "";
		char tab[] = new char [mot.length()];
		char temp;
		char temp2;
		int alea1 = 0;
		int alea2 = 0;
		for(int i = 0; i < mot.length(); i++) {
			temp = mot.charAt(i);
			tab[i] = temp;
		}
		for(int i = 0; i < 200; i++) {
			alea1 = (int) (Math.random() * mot.length());
			alea2 = (int) (Math.random() * mot.length());
			temp = tab[alea1];
			temp2 = tab[alea2];
			tab[alea1]= temp2;
			tab[alea2]= temp;
		
		
		}
		for(int i = 0; i < mot.length(); i++) {
			System.out.print(tab[i]);
			motModif += tab[i];
		}
		System.out.println("");
		return motModif;
		
	
	}
	
	// je demande au joueur de faire une proposition 
	
	public static int verifJoueur(String mot, String tab[],String motModif) {
		int points = 0;
		boolean ok = false;
		String verif[] = new String[50];
		int a = 0;
		String proposition = "";
		while(!proposition.equalsIgnoreCase("q")) {
		System.out.println(motModif);
		System.out.println("Veuillez écrire votre proposition, pour quitter tapez q et Entree :");
		proposition = sc.nextLine();
		for(int j = 0; j < verif.length; j++) {
			if(!proposition.equalsIgnoreCase(verif[j])) {
				ok = true;
			}
			else {
				ok = false;
			}
		}
		for(int i = 0; i < 22506; i++) {
			
			if(proposition.equalsIgnoreCase(tab[i]) && ok == true) {
				System.out.println("Bravo");
				points += proposition.length();
				System.out.println(" + " + points + " points gagnés!" );
				verif[a] = proposition;
			} 
		}
		if(ok == false) {
			System.out.println("Essayez un autre mot !");
		}
		}
		System.out.println("Le mot était " + mot);
		return points;
	}

}
