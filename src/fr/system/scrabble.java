package fr.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class scrabble {

	public static void main(String[] args) {
		String motModif = "";
		String dictionnaire = "C:\\Users\\ib\\Documents\\Java\\partie1\\partie1\\ateliers\\Atelier1\\dictionnaire.txt";
		int nbrMots = comptageDictionnaire(dictionnaire);
		String mot =  selectionMot(nbrMots, dictionnaire);
		System.out.println(mot);
		motModif =melangeMot(mot);
		verifJoueur(mot);
	}
	
	// on compte le nombre de mots dans le dictionnaire
	
	public static int comptageDictionnaire(String chemin) {
		int nbrMots = 0;
		try {
			FileInputStream fis = new FileInputStream(chemin);
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()) {
				sc.nextLine();
				nbrMots++;
			}
			sc.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nbrMots;
	}
	
	// Je choisis un mot au hasard
	
	public static String selectionMot(int nbrMots, String chemin) {
		String mot = null;
		int alea = (int) (Math.random() * nbrMots);
		try {
			FileInputStream fis = new FileInputStream(chemin);
			Scanner sc = new Scanner(fis);
			for(int i = 0; i < alea; i++ ) {
				sc.nextLine();
			}
			mot = sc.nextLine();
			sc.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
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
		System.out.println(motModif);
		return motModif;
		
	
	}
	
	public static boolean verifJoueur(String mot) {
		boolean correct = false;
		String proposition = null;
		Scanner sc = new Scanner(System.in);
		while(!mot.equalsIgnoreCase(proposition)) {
		System.out.println("Veuillez écrire votre proposition :");
		proposition = sc.nextLine();
		}
		System.out.println("Bravo ! Le mot est bien " + mot);
		correct = true;
		return correct;
	}

}
