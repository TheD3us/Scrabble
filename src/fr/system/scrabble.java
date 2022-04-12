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
	
	// Créer un tableau avec le dictionnaire
	
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
		
		
		String compProposition = "";
		String proposition = "";
		boolean ok;
		int points = 0;
		int compteurReponse = 0;
	
		while(!proposition.equalsIgnoreCase("q")) {
		String verif[] = new String[50];
		char verifChar[] = new char[mot.length()];
		compProposition = "";
		System.out.println(motModif);
		System.out.println("Veuillez écrire votre proposition, pour quitter tapez q et Entree :");
		proposition = sc.nextLine();
		
		
		for(int i = 0; i < mot.length(); i++) {
			verifChar[i] = mot.charAt(i);
			
		}
		
		
		for(int j = 0; j< proposition.length(); j++) {

		for(int i = 0; i < mot.length(); i++) {

			if(verifChar[i] == proposition.charAt(j)) {
				compProposition += verifChar[i];
				verifChar[i] = ' ' ;
				i = mot.length();
			}
		}
		}
		
		
		if(compProposition.equalsIgnoreCase(proposition)) {
			ok = true;
		} else {
			ok = false;
			
		}
		
		// je vérifie dans le tableau verif si le mot a déjà été rentré si il est trouvé
		// la boucle s'arrête et renvoie false sur ok
		for(int j = 0; j < verif.length; j++) {
			if(proposition.equalsIgnoreCase(verif[j]) == false && ok == true) {
				ok = true;
			}
			else {
				ok = false;
				j = verif.length;
			}
		}
		
		if(ok == true) {
		for(int i = 0; i < 22506; i++) {
			
			if(proposition.equalsIgnoreCase(tab[i]) ) {
				System.out.println("Bravo");
				points += proposition.length();
				System.out.println(" + " + points + " points gagnés!" );
				verif[compteurReponse] = proposition;
				compteurReponse++;
				ok = true;
				i = 22506;
			} else {
				ok = false;
			}
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
