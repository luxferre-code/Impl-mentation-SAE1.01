LARGEUR = 5

def initialiser_carre_simple() -> str:
    """Return a simple carre"""
    return "ABCDEFGHIJKLMNOPQRSTUVXYZ"

def afficher_carre(carre: str) -> None:
    """Print the carre"""
    print(" |0 1 2 3 4\n------------")
    for i in range(LARGEUR):
        print(str(i) + "|", end="")
        for j in range(LARGEUR):
            print(carre[i * LARGEUR + j], end=" ")
        print()

def coder_lettre(lettre: str, carre: str) -> str:
    """Return the coded letter"""
    for k in range(len(carre)):
        if carre[k] == lettre or (lettre == "W" and carre[k] == "V"):
            return f"{k // LARGEUR}{k % LARGEUR}"
    return "Not Found"

def coder_message(message: str, carre: str) -> str:
    """Return the coded message"""
    message_coded = ""
    for lettre in message:
        message_coded += coder_lettre(lettre, carre) + " "
    return message_coded
    
def decoder_message(message: str, carre: str) -> str:
    """Return the decoded message"""
    message_decoded = ""
    for i in range(0, len(message), 2):
        message_decoded += carre[int(message[i]) * LARGEUR + int(message[i + 1])]
    return message_decoded

def est_present()

if __name__ == "__main__":
    afficher_carre(initialiser_carre_simple())