/*
 * Classe avec différentes opérations permettant de gérer une date
 * OutilDate.java
 */
package com.fsp.multiactivite;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Classe contenant des opérations permettant de gérer une date décrite par un numéro
 * de jour et un numéro de mois :
 *    - saisir un mois en recommençant en cas d'erreur
 *    - saisir un jour en recommençant en cas d'erreur
 *    - saisir un jour et un mois valide (saisie recommencée si erreur)
 *    - transforme un numéro de mois en la chaîne associée (janvier, février, ...)
 *    - détermine si une date est valide
 *    - détermine le jour et le mois de la date courante
 *    - détermine si une date est antérieure à une autre date
 * @author INFO1
 * @version 1.0
 */
public class OutilDate {
    
    
	/**
     * Tableau constant contenant tous les jours de la semaine
     */
    private static final String[] LISTE_JOUR = { "lundi", "mardi", "mercredi",
            "jeudi", "vendredi", "samedi", "dimanche" };

    /**
     * Tableau constant contenant tous les mois de l'année
     */
    private static final String[] LISTE_MOIS = { "janvier", "fevrier", "mars",
            "avril", "mai", "juin", "juillet", "aout", "septembre", "octobre",
            "novembre", "decembre" };

    /**
     * Constante égale Ã  la plus petite année gérée
     */
    private static final int ANNEE_MIN = 1900;

    /**
     * Constante égale Ã  la plus grande année gérée
     */
    private static final int ANNEE_MAX = 3000;

    /**
     * Effectue la saisie d'un mois, un entier entre 1 et 12.
     * La saisie est recommencée en cas d'erreur
     * @return mois un entier compris entre 1 et 12
     */
    public static int saisirMois() {
        Scanner entree = new Scanner(System.in);
        int mois = -1;      // initialisé avec une valeur incorrecte
        
        // recommence la saisie du mois jusqu'à obtenir une valeur correcte
        do {
            System.out.println("Entrez un mois : ");
            if (entree.hasNextInt()) {
                
                // l'utilisateur a saisi un entier
                mois = entree.nextInt();
                if (mois < 1 || mois > 12) {
                    System.out.println("Nombre incorrect. " 
                                       + "Un mois est compris entre 1 et 12");
                }
            } else {
                System.out.println("Erreur. Vous devez saisir un entier.");
            }
            entree.nextLine();            
        } while (mois < 1 || mois > 12);
        return mois;
    }
    
    
    /**
     * Effectue la saisie d'un jour dans un mois, un entier entre 1 et 31.
     * La saisie est recommencée en cas d'erreur
     * @return jour un entier compris entre 1 et 31
     */
    public static int saisirJour() {
        Scanner entree = new Scanner(System.in);
        int jour = -1;      // initialisé avec une valeur incorrecte
        
        // recommence la saisie du jour jusqu'à obtenir une valeur correcte
        do {
            System.out.println("Entrez un jour : ");
            if (entree.hasNextInt()) {
                
                // l'utilisateur a saisi un entier
                jour = entree.nextInt();
                if (jour < 1 || jour > 31) {
                    System.out.println("Nombre incorrect. " 
                                       + "Un jour est compris entre 1 et 31");
                }
            } else {
                System.out.println("Erreur. Vous devez saisir un entier.");
            }
            entree.nextLine();            
        } while (jour < 1 || jour > 31);
        return jour;
    }
    
    
    /**
     * Saisit un jour et un mois. La saisie est recommencée en cas d'erreur.
     * Le jour doit être valide par rapport au mois
     * @return un tableau de 2 entiers : le premier est le jour saisi, le deuxième
     *         est le mois saisi
     */
    public static int[] saisirJourMois() {
        int[] date = new int[2];        // contiendra le jour et le mois saisis
        do {
            System.out.println("Saisie d'une date jour/mois.");
            date[0] = saisirJour();
            date[1] = saisirMois();
            if (! estValide(date)) {
                System.out.println("La date " + jourMoisEnChaine(date) 
                		           + " n'est pas valide. Recommencez.");
            }
        } while (! estValide(date));
        return date;
    }
     
    
    /**
     * Détermine si la date argument est valide, c'est-à-dire si
     * le tableau contient bien 2 éléments et si 
     * le jour est bien inférieur ou égal au nombre de jours dans le mois. Pour le 
     * mois de février, on considère qu'il a 29 jours
     * @param date  un tableau de 2 entiers supposé contenir une date
     *              à l'indice 0 : le jour 
     *              à l'indice 1 : le mois 
     * @return  un booléen égal à vrai ssi le jour est valide pour le mois argument
     */
    public static boolean estValide(int[] date) {
        boolean valide;     // vrai ssi la date argument est valide
        if (date == null || date.length != 2) {
            valide = false;
        } else {
            
            // on envisage tous les cas possibles pour le mois (date[1])
            switch(date[1]) {
            case 2 :    // mois de février, au plus 29 jours
                        valide = date[0] >= 1 && date[0] <= 29;
                        break;
            case 4 : 
            case 6 : 
            case 9 : 
            case 11 :   // mois d'avril, juin, septembre et novemvre : 30 jours
                        valide = date[0] >= 1 && date[0] <= 30;
                        break;
            case 1 : 
            case 3 : 
            case 5 : 
            case 7 : 
            case 8 : 
            case 10 : 
            case 12 :   // mois de 31 jours
                        valide = date[0] >= 1 && date[0] <= 31;
                        break;                
            default :   // mois incorrect 
                        valide = false;
            }
        }
        return valide;        
    }

    
    /**
     * Renvoie le jour et le mois courants
     * @return un tableau de 2 éléments contenant le jour et le mois courant
     *         
     */
    public static int[] jourMoisCourant() {
        
        /*
         * tableau qui contiendra le résultat :
         *    à l'indice 0 le jour courant
         *    à l'indice 1 le mois courant
         */
        int[] dateCourante = new int[2];
        
        // dans un objet de type Calendar, on stocke la date courante
        Calendar dateActuelle = Calendar.getInstance();
        
        // on extrait de dateActuelle d'abord le jour, ensuite le mois (de 0 à 11)
        dateCourante[0] = dateActuelle.get(Calendar.DAY_OF_MONTH);
        dateCourante[1] = dateActuelle.get(Calendar.MONTH) + 1;
        return dateCourante;        
    }
    

    /**
     * Détermine si les 3 entiers argument forment une date valide
     * @param jour  jour de la date
     * @param mois mois de la date
     * @param annee année de la date
     * @return un booléen égal Ã  vrai ssi la date argument est valide
     */
    private static boolean dateValide(int jour, int mois, int annee) {
        return annee >= ANNEE_MIN && annee <= ANNEE_MAX && mois >= 1
                && mois <= 12 && jour >= 1
                && jour <= nombreJourMois(mois, annee);
    }
    
    /**
     * Détermine si les 2 dates arguments sont ordonnées
     * @param date1   tableau contenant le jour et le mois de la date 1
     * @param date2   tableau contenant le jour et le mois de la date 2   
     * @return un booléen égal à vrai ssi la première date est située avant
     *         (strictement) la deuxième
     */
    public static boolean sontOrdonnes(int[] date1, int[] date2) {
        if (date1 == null || date1.length != 2 || date2 == null || date2.length != 2) {
            System.out.println("Erreur inattendue méthode sontOrdonnees de la classe "
                               + " OutilDate. L'un des arguments est invalide.");
            return false;
        }
        
        // sinon : dates correctes
        return date1[1] < date2[1] || (date1[1] == date2[1] && date1[0] < date2[0]);
    }
    
    
    /**
     * Renvoie l'équivalent en toutes lettres du mois argument
     * @param mois  un entier contenant un mois (entier entre 1 et 12)
     * @return une chaîne contenant le mois en toutes lettres
     *         ou bien la chaîne "erreur" si le mois argument n'est pas valide
     */
    public static String enLettre(int mois) {
        final String[] LES_MOIS = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                                    "Juillet", "Août", "Septembre", "Octobre", "Novembre",
                                    "Décembre" };                
        return mois < 1 || mois > 12 ? "erreur" : LES_MOIS[mois-1];
    }
    
    
    
    
    /**
     * Renvoie une chaîne contenant la date argument
     * @param date tableau contenant 2 entiers : le jour et le mois à renvoyer
     * @return une chaîne contenant la date argument sous la forme jour/mois
     *         (ou "Erreur format date" si l'argument ne forme pas une date)
     */
    public static String jourMoisEnChaine(int[] date) {
        if (date == null || date.length != 2) {
            return "Erreur format date";
        } else {
            return date[0] + "/" + date[1];
        }        
    }
    
    /**
     * Détermine si l'année argument est bissextile
     * @param annee à  tester
     * @return un booléen égal à vrai ssi l'année est bissextile
     */
    private static boolean anneeBissextile(int annee) {
        return (annee % 4 == 0 && annee % 100 != 0) || annee % 400 == 0;
    }

    /**
     * Détermine le nombre de jours d'une année
     * @param annee annnée dont la méthode calcule le nombre de jours
     * @return le nombre de jours de l'année argument
     */
    private static int nombreJourAnnee(int annee) {
        return anneeBissextile(annee) ? 366 : 365;
    }
    
    /**
     * Détermine le nombre de jours dans un mois et une année donnée
     * @param mois  mois pour lequel la méthode calcule le nombre de jours
     * @param annee année du mois
     * @return nombreJour le nombre de jours dans le mois ou -1 si le mois n'est
     *         pas valide
     */
    private static int nombreJourMois(int mois, int annee) {
        int nombreJour; // nombre de jours dans le mois
        switch (mois) { // mois de février
        case 2:
            nombreJour = anneeBissextile(annee) ? 29 : 28;
            break;
        case 4:
        case 6: // mois de 30 jours
        case 9:
        case 11:
            nombreJour = 30;
            break;
        case 1:
        case 3: // mois de 31 jours
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            nombreJour = 31;
            break;
        default:
            nombreJour = -1; // mois invalide
        }
        return nombreJour;
    }
    
    /**
     * Détermine si les 2 dates argument sont ordonnées
     * @param jour1 jour de la première date
     * @param mois1  mois de la première date
     * @param an1 année de la première date
     * @param jour2 jour de la deuxième date
     * @param mois2 mois de la deuxième date
     * @param an2 année de la deuxème date
     * @return vrai ssi la première date est antérieure Ã  la deuxième
     */
    private static boolean datesOrdonnees(int jour1, int mois1, int an1,
            int jour2, int mois2, int an2) {
        
        // si les années sont différentes
        if (an1 != an2) {
            return an1 < an2;
        }
        
        // else : les années sont égales
        if (mois1 != mois2) {
            return mois1 < mois2;
        }
        
        // else : les mois sont égaux
        return jour1 <= jour2;
    }

    /**
     * Détermine l'écart entre 2 dates situées dans une même année. Les dates
     * argument doivent être ordonnées
     * @param jour1 jour de la date 1
     * @param mois1 mois de la date 1
     * @param jour2 jour de la date 2
     * @param mois2 mois de la date 2
     * @param an année des deux dates
     * @return le nombre de jours qui séparent les 2 dates ou -1 si les dates ne
     *         sont pas ordonnées
     */
    public static int ecartDateMemeAnnee(int jour1, int mois1, int jour2,
                                         int mois2, int an) {
        if (!datesOrdonnees(jour1, mois1, an, jour2, mois2, an)) {
            return -1;          // les dates ne sont pas ordonnées
        }
        
        // le calcul est possible
        int ecart = 0;              // nombre de jours qui séparent les 2 dates
        if (mois1 == mois2) {       // les dates sont dans le même mois
            ecart = jour2 - jour1;  // l'écart est la différence des jours
        } else {
            
            /*
             * on parcourt les mois complets entre les 2 dates, et on ajoute
             * leur nombre de jours
             */
            for (int mois = mois1 + 1; mois < mois2; mois++) {
                ecart += nombreJourMois(mois, an);
            }
            
            // on ajoute les jours de séparation pour les mois incomplets
            ecart += jour2 + (nombreJourMois(mois1, an) - jour1);
        }
        return ecart;
    }

    
    /**
     * Détermine l'écart entre 2 dates. L'écart est un nombre de jours en valeur
     * absolue
     * @param jour1 jour de la date 1
     * @param mois1 mois de la date 1
     * @param an1 année de la date 1
     * @param jour2 jour de la date 2
     * @param mois2 mois de la date 2
     * @param an2 année de la date 2
     * @return le nombre de jours qui séparent les 2 dates
     */
    public static int ecartDate(int jour1, int mois1, int an1, int jour2,
                                int mois2, int an2) {
        if (!datesOrdonnees(jour1, mois1, an1, jour2, mois2, an2)) {
            
            // appel récursif en permutant les 2 dates argument
            return ecartDate(jour2, mois2, an2, jour1, mois1, an1);
        }
        
        // les dates sont ordonnées
        int ecart = 0;              // nombre de jours qui séparent les 2 dates
        if (an1 == an2) {           // les dates appartiennent Ã  la mÃªme année
            ecart = ecartDateMemeAnnee(jour1, mois1, jour2, mois2, an1);
        } else {
            
            /*
             * on parcourt les années complètes entre les 2 dates, et on cumule
             * le nombre de jours de ces années
             */            
            for (int annee = an1 + 1; annee < an2; annee++) {
                ecart += nombreJourAnnee(annee);
            }
            
            
            /*
             * on calcule le nombre de jours qui séparent la date 1 de la fin de
             * son année
             */            
            ecart += ecartDateMemeAnnee(jour1, mois1, 31, 12, an1);
            
            /*
             * on calcule le nombre de jours entre le 1er janvier de l'année de
             * la date 2, et la date 2 elle-même
             */            
            ecart += ecartDateMemeAnnee(1, 1, jour2, mois2, an2) + 1;
        }
        return ecart;
    }

    /**
     * Détermine le jour de la semaine pour une date donnée
     * @param jour jour de la date
     * @param mois mois de la date
     * @param annee année de la date
     * @return une chaîne égale Ã  Lundi, Mardi, ... ou Dimanche ou
     *         "Date invalide" si la date est invalide
     */
    public static String jourSemaine(int jour, int mois, int annee) {
        if (!dateValide(jour, mois, annee)) { // la date est invalide
            return "Date invalide";
        }
        
        /*
         * la date est valide : on calcule le nombre de jours qui la séparent
         * du 1/1/1900 (c'était un lundi)
         */
        int difference = ecartDate(1, 1, ANNEE_MIN, jour, mois, annee);
        return LISTE_JOUR[difference % 7];              // on renvoie le jour
    }
    
}
