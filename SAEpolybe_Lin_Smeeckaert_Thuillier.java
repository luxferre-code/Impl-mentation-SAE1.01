class SAEpolybe_Lin_Smeeckaert_Thuillier extends Program {

    final int LARGEUR = 5;  // taille du carré (5x5 dans notre cas)
    
    //////////////////////////////////////////////////////////////////////////    
    
    // La fonction String initialiserCarre() retourne une chaine de caractères contenant le carré de Polybe (version de base, sans clé, c'est-à-dire la chaine "ABCDEFGHIJKLMNOPQRSTUVXYZ", le V et le W sont "fusionnés" en V)
    String initialiserCarreSimple(){
        return "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction void afficherCarre(String carre) affiche le carré de Polybe carrz passé en paramètre comme illustré dans l'exemple ci-après.
    // Par exemple : si le carré passé en paramètre est : "ABCDEFGHIJKLMNOPQRSTUVXYZ", la fonction devra afficher : 
    //  |0 1 2 3 4
    // ------------
    // 0|A B C D E
    // 1|F G H I J
    // 2|K L M N O 
    // 3|P Q R S T 
    // 4|U V X Y Z

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)

    void afficherCarre(String carre){
        println(" |0 1 2 3 4");
        println("------------");
        for(int ligne = 0; ligne < LARGEUR; ligne++) {
            print(ligne + "|");
            for(int colonne = 0; colonne < LARGEUR; colonne++) {
                print(charAt(carre, 5 * ligne + colonne) + " ");
            }
            println("");
        }
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction String coderLettre (String carre, char lettre) retourne une chaîne de 2 caractères (2 entiers entre 0 inclus et LARGEUR exclus) contenant l'encodage du caractère lettre passé en paramètre en considérant le carré de Polybe carre également passé en paramètre.
    // Par exemple :
    // si on considère le carré de Polybe sans clé (càd le carré ABCDE représenté par la chaine "ABCDEFGHIJKLMNOPQRSTUVXYZ") : 
    //                                                           FGHIJ
    //                                                           KLMNO                   
    //                                                           PQRST 
    //                                                           UVWYZ
    //      'A' est codé "00"
    //      'B' est codé "01"
    //      'F' est codé "10"
    //      'V' est codé "41"
    //      'W' est codé "41"
    //      'Z' est codé "44"
    //      'P' est codé "30"

    // si on considère le carré de Polybe donné par la chaine "AZERTYUIOPQSDFGHJKLMXCVBN" :
    //      'A' est codé "00"
    //      'B' est codé "43"
    //      'Z' est codé "01"
    
    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)

    // Indication : pensez à la division entière et au modulo

    String coderLettre(String carre, char lettre){
        String resultat = "";
        for(int ligne = 0; ligne < LARGEUR; ligne++) {
            for(int colonne = 0; colonne < LARGEUR; colonne++) {
                if(charAt(carre, 5 * ligne + colonne) == lettre || lettre == 'W' && charAt(carre, 5 * ligne + colonne) == 'V') {
                    resultat = "" + ligne + colonne;
                }
            }
        }
        return resultat;
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction String coderMessage (String carre, String message) retourne une chaîne de caractères contenant l'encodage de la chaîne de caractères message passé en paramètre avec le carré de Polybe carre donné en paramètre.
    // Chaque paire d'entiers (compris entre 0 et 4) correspondant à chaque lettre sera séparée de la suivante par un espace.
    // Pensez à utiliser la fonction coderLettre.
    // Par exemple, si le carré considéré est celui sans clé ("ABCDEFGHIJKLMNOPQRSTUVXYZ") et le message à coder est "BONJOUR" alors le résultat attendu est "01 24 23 14 24 40 32 "

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)
    // NB : On considère dans cette fonction que le message passé en paramètre est valide (càd constitué uniquement des 26 lettres de l'alphabet en majuscule)

    String coderMessage(String carre, String message){
        String string_coder = "";
        for(int indice = 0; indice < length(message); indice++) {
            string_coder = string_coder + coderLettre(carre, charAt(message, indice)) + " ";
        }
        return string_coder; 
    }
    //////////////////////////////////////////////////////////////////////////
    
    // La fonction String decoderMessage (String carre, String messageCode) retourne une chaîne de caractères contenant le décodage de la chaîne de caractère messageCode avec le carré de Polybe carre donné en paramètre.
    // Par exemple, si carre = "ABCDEFGHIJKLMNOPQRSTUVXYZ" et messageCode = "01 24 23 14 24 40 32 " alors le résultat attendu est "BONJOUR"

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)
    // NB : On considère dans cette fonction que le message codé passé en paramètre est valide (càd constitué de paires d'entiers compris entre 0 et LARGEUR-1 inclus et séparées par un espace)

    String decoderMessage(String carre, String messageCode){
        String decoder = "";
        for(int indice = 1; indice < length(messageCode) - 1; indice = indice + 3) {
            int nbr_1, nbr_2;
            nbr_1 = charAt(messageCode, indice - 1) - '0';
            nbr_2 = charAt(messageCode, indice) - '0';
            decoder = decoder + charAt(carre, 5 * nbr_1 + nbr_2);
        }
        return decoder;
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction boolean estPresent(String mot, char lettre) retourne True si le caractère lettre est dans mot, faux sinon.
    // Par exemple :
    //      si mot = "BONJOUR" et lettre = 'B' alors le résultat de la fonction est True
    //      si mot = "BONJOUR" et lettre = 'R' alors le résultat de la fonction est True
    //      si mot = "BONJOUR" et lettre = 'M' alors le résultat de la fonction est False
    
    boolean estPresent(String mot, char lettre){
        for(int indice = 0; indice < length(mot); indice++) {
            if(charAt(mot, indice) == lettre) { return true; }
        }
        return false;
    }
  
    //////////////////////////////////////////////////////////////////////////
  
    // La fonction String initialiserCarreAvecCle(String cle) retourne une chaine de caractères contenant le carré de Polybe amélioré en considérant la clé passée en paramètre.
    // Pensez à utiliser la fonction estPresent
    // Par exemple, si cle = "BONJOUR" alors le résultat attendu est : "BONJURACDEFGHIKLMPQSTVXYZ"
    // Par exemple, si cle = "BUTINFORMATIQUE" alors le résultat attendu est : "BUTINFORMAQECDGHJKLPSVXYZ"

    // NB : On considère dans cette fonction que la clé passée en paramètre est valide (càd constituée uniquement de lettres de l'alphabet en majuscule, le W se substituera en V)

    String initialiserCarreAvecCle(String cle){
        String carreAvecCle = "";
        String carre_base = initialiserCarreSimple();
        for(int indice = 0; indice < length(cle); indice++) {
            char cara = charAt(cle, indice);
            if(!estPresent(carreAvecCle, cara)) {
                carreAvecCle = carreAvecCle + cara;
            }
        }

        for(int indice = 0; indice < length(carre_base); indice++) {
            char cara_carre = charAt(carre_base, indice);
            if(!estPresent(carreAvecCle, cara_carre)) { carreAvecCle = carreAvecCle + cara_carre; }
        }

        return carreAvecCle;

    }
    
    //////////////////////////////////////////////////////////////////////////
    // LES FONCTIONS QUI SUIVENT SONT DES FONCTIONS DE VERIFICATION DE SAISIE
    //////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction boolean estLettreMajuscule(char c) vérifie le caractère passé en paramètre est une lettre de l'alphabet en majuscule
    // Par exemple :
    //  si c='b', la fonction retourne false
    //  si c='B', la fonction retourne true

    boolean estLettreMajuscule(char c){
        return c >= 'A' && c <= 'Z';
    }
   
    //////////////////////////////////////////////////////////////////////////
   
    // La fonction estCleValide vérifie que la clé passée en paramètre est valide (càd constituée uniquement de lettres de l'alphabet en majuscule)
    // Par exemple :
    //  si cle="BUTINFORMATIQUE", la fonction retourne true
    //  si cle="BUTINF ORMATIQUE", la fonction retourne false
    //  si cle="BUTINFORMATIQUE!", la fonction retourne false
    //  si cle="ButInformatique", la fonction retourne false
   
    boolean estCleValide(String cle){
        for(int indice = 0; indice < length(cle); indice++) {
            if(!estLettreMajuscule(charAt(cle, indice))) { return false; }
        }
        return true;
    }

    //////////////////////////////////////////////////////////////////////////
   
    // La fonction estChiffreOK vérifie que le chiffre passé en paramètre est valide (càd est un entier compris entre 0 et LARGEUR-1)
    // Par exemple :
    //  si messageCode=""01 24 23 14 24 40 32 ", la fonction retourne true
    //  si messageCode=""01 24 23 14 24 40 32", la fonction retourne false
    //  si messageCode=""01 24 23 14 24 40 3", la fonction retourne false
    //  si messageCode=""01 25 23 14 24 40 32 ", la fonction retourne false
    //  si messageCode=""01242314244032", la fonction retourne false

    boolean estChiffreOK(int chiffre){
        return chiffre >= 0 && chiffre < LARGEUR;
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction estMessageCodeValide vérifie que le message codé passé en paramètre est valide (càd constituée uniquement de paires d'entiers compris entre 0 et LARGEUR-1 et que chaque paire est séparée de la suivante par un espace, et un espace final)
    // Par exemple :
    //  si messageCode=""01 24 23 14 24 40 32 ", la fonction retourne true
    //  si messageCode=""01 24 23 14 24 40 32", la fonction retourne false
    //  si messageCode=""01 24 23 14 24 40 3", la fonction retourne false
    //  si messageCode=""01 25 23 14 24 40 32 ", la fonction retourne false
    //  si messageCode=""01242314244032", la fonction retourne false

    boolean estMessageCodeValide(String messageCode){
        if(charAt(messageCode, length(messageCode) - 1) != ' ') { return false; }
        int nbr_1 = -1, nbr_2 = -1;
        boolean firstNbrSelect = true;
        for(int indice = 0; indice < length(messageCode); indice++) {
            char cara = charAt(messageCode, indice);
            if(cara == ' ') {
                if(nbr_1 != -1 && nbr_2 != -1) {
                    nbr_1 = -1;
                    nbr_2 = -1;
                } else { return false; }
            } else if(firstNbrSelect) {
                int nbr = cara - '0';
                if(estChiffreOK(nbr)) { nbr_1 = nbr; firstNbrSelect = false; }
                else { return false; }
            } else {
                int nbr = cara - '0';
                if(estChiffreOK(nbr)) { nbr_2 = nbr; firstNbrSelect = true; }
                else { return false; }
            }
        }
        return true;
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction estMessageValide vérifie que le message passé en paramètre est valide (càd constitué uniquement de lettres de l'alphabet en majuscule)

    boolean estMessageValide(String message){
        for(int indice = 0; indice < length(message); indice++) {
            if(!estLettreMajuscule(charAt(message, indice))) { return false; }
        }
        return true;
    }

    boolean userUseKey() {
        boolean useKey = false;
        String choix = "";
        do {
            print("Voulez-vous utiliser une clé de chiffrement ? ");
            choix = readString();
            if(equals(choix, "oui")) { useKey = true; }
            else if(equals(choix, "non")) { useKey = false; }
            else { println("Merci d'entrer oui ou non."); }
        } while(!equals(choix, "oui") && !equals(choix, "non"));
        return useKey;
    }

    //////////////////////////////////////////////////////////////////////////
    // PROGRAMME PRINCIPAL
    //////////////////////////////////////////////////////////////////////////

    // Ecrire un programme principal qui :
    // 1. affiche un message d'introduction à l'utilisateur
    // 2. affiche un message à l'utilisateur demandant s'il veut utiliser une clé ?
    // 3. lit la réponse de l'utilisateur
    // 4. si l'utilisateur a répondu oui, demande et lit la clé souhaitée
    // 5. affiche le carré de Polybe (générique (càd sans clé) ou avec clé selon la réponse précédente de l'utilisateur)
    // 6. tant que la réponse n'est pas 0, affiche un menu et demande à l'utilisateur de saisir un entier (0 ou 1 ou 2 ou 3) pour :
    //              0. QUITTER
    //              1. CODER UN MESSAGE
    //              2. DECODER UN MESSAGE
    //              3. MODIFIER LE MODE AVEC/SANS CLE
    //        puis agit en conséquence.
    // NB : si et tant qu'une saisie de l'utilisateur n'est pas correcte, il faut la redemander (que ce soit pour la clé, le message à coder, le message à décoder ou le choix dans le menu)

    void algorithm(){
        println("SAE1.01 - Le carré de Polybe");
        println("Inventé vers -150 par l'historien éponyme, le carré de Polybe fut notamment utilisé par les nihilistes russes enfermés dans les prisons des tsars. Il s'agit d'un code trivial où chaque lettre de l'alphabet est remplacée par les coordonnées de sa position dans un carré");
        
        boolean useKey = userUseKey();
        
        String carre = "";
        if(useKey) {
            String key = "";
            do {
                print("Merci d'entrer une clé valide : ");
                key = readString();
            } while(!estCleValide(key));
            carre = initialiserCarreAvecCle(key);
        } else {
            carre = initialiserCarreSimple();
        }

        int reponse;
        do {
            println("Voici le carré de Polybe généré : ");
            afficherCarre(carre);

            println("0. Stop");
            println("1. Coder un message");
            println("2. Décoder un message");
            println("3. Modifier le mode avec/sans clé");
            print("Que voulez-vous faire ? ");
            reponse = readInt();
            
            if(reponse == 1) { // Coder le message
                String message;
                do {
                    print("Quelle est le message ? ");
                    message = readString();
                } while(!estMessageValide(message));
                String messageCodé = coderMessage(carre, message);
                println("Voici votre message codé : " + messageCodé);
                println("Cliquez sur entrée pour revenir au menu.");
                String nonUtilisé = readString();
            } else if(reponse == 2) { //Décoder le message
                String messageCode;
                do {
                    print("Quelle est votre message codé ? ");
                    messageCode = readString();
                    if(charAt(messageCode, length(messageCode) - 1) != ' ') { messageCode = messageCode + " "; }
                } while(!estMessageCodeValide(messageCode));
                String messageDecode = decoderMessage(carre, messageCode);
                println("Voici votre message décodé : " + messageDecode);
                println("Cliquez sur entrée pour revenir au menu.");
                String nonUtilisé = readString();
            } else if(reponse == 3) {
                useKey = userUseKey();
                if(useKey) {
                    String key = "";
                    do {
                        print("Merci d'entrer une clé valide : ");
                        key = readString();
                    } while(!estCleValide(key));
                    carre = initialiserCarreAvecCle(key);
                } else {
                    carre = initialiserCarreSimple();
                }
            }
            
        } while(reponse != 0);
        

    }
    //////////////////////////////////////////////////////////////////////////  
}
