import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;
/*
0,0 0,1 0,2 prima riga
1,0 1,1 1,2 seconda
2,0 2,1 2,2 terza
prima colonna || seconda || terza
 */
void main() throws InterruptedException {
    Scanner in = new Scanner(System.in); //scanner
    int x, y; //coordinate
    String[][] campo;
    campo = new String[3][3];
    campo = griglia(campo);
    print(campo);
    String modalita;
    System.out.println();
    // /==\
    //  ||
    //  ||
    //setup
    do {
        System.out.println("PvP o PvE?");
        modalita = in.nextLine();
        if (modalita.equals("PvP"))
            pvp();
        else if (modalita.equals("PvE"))
            System.out.print("");
    } while (!modalita.equals("PvP") && !modalita.equals("PvE"));

    //selettore difficoltà facile difficile
    int howmuchhard;
    int possibility = 0; //possibilità
    do {
        System.out.println("Selettore difficoltà ");
        System.out.println("25 - 50 - 75 - 100");
        howmuchhard = in.nextInt();
        if (howmuchhard != 25 && howmuchhard != 50 && howmuchhard != 75 && howmuchhard != 100) {
            System.out.println("valore errato");
        }
    } while (howmuchhard != 25 && howmuchhard != 50 && howmuchhard != 75 && howmuchhard != 100);

    while (true) {
        //giocatore
        System.out.println();
        System.out.println("inserie coordinata y");
        y = in.nextInt();
        System.out.println("inserie coordinata x");
        x = in.nextInt();
        if (x > 2 || y > 2) {
            System.out.println("sei un mona, rifai!");
        }
        if (campo[y][x].equals("*")) {
            System.out.println("prefetto");
        } else {
            System.out.println("casella già occupata, sei un mona");
        }
        if ((x <= 2 && y <= 2) && campo[y][x].equals("*")) {
            campo[y][x] = "x";
        }
        winningrules(campo);
        System.out.println();
        print(campo);
        System.out.println();

        //computer

        //howmuchhard 25
        if (howmuchhard == 25) {
            boolean varbool = possibility == Math.random() % 4;
            if (possibility == 0) {
                campo = computer(campo);
            } else {
                x = (int) (Math.random() % 3);
                Thread.sleep(1000);
                y = (int) (Math.random() % 3);
                if (campo[y][x].equals("*"))
                    campo[y][x] = "o";
            }
            while (campo[y][x].equals("x") && campo[y][x].equals("o")) ;
        }
        //howmuchhard 50
        if (howmuchhard == 50) {
            boolean varbool = possibility == Math.random() % 4;
            if (possibility == 0 || possibility == 1) {
                campo = computer(campo);
            } else {
                x = (int) (Math.random() % 3);
                Thread.sleep(1000);
                y = (int) (Math.random() % 3);
                if (campo[y][x].equals("*"))
                    campo[y][x] = "o";
            }
            while (campo[y][x].equals("x") && campo[y][x].equals("o")) ;
        }
        //howmuchhard 75
        if (howmuchhard == 75) {
            boolean varbool = possibility == Math.random() % 4;
            if (possibility == 0 || possibility == 1 || possibility == 2) {
                campo = computer(campo);
            } else {
                x = (int) (Math.random() % 3);
                Thread.sleep(1000);
                y = (int) (Math.random() % 3);
                if (campo[y][x].equals("*"))
                    campo[y][x] = "o";
            }
            while (campo[y][x].equals("x") && campo[y][x].equals("o")) ;
        }
        //howmuchhard 100
        if (howmuchhard == 100) {
            boolean varbool = possibility == Math.random() % 4;
            campo = computer(campo);
        }
        winningrules(campo);
        System.out.println();
        print(campo);
    }
}

//stampa
void print(String[][] campo) {
    int x = 0, y = 0;
    for (int i = 0; i < 3; i++) {
        System.out.print(campo[0][i]);
    }
    System.out.println();
    for (int i = 0; i < 3; i++) {
        System.out.print(campo[1][i]);
    }
    System.out.println();
    for (int i = 0; i < 3; i++) {
        System.out.print(campo[2][i]);
    }
}

//griglia base
String[][] griglia(String[][] griglia) {
    griglia[0][0] = "*";
    griglia[0][1] = "*";
    griglia[0][2] = "*";
    griglia[1][0] = "*";
    griglia[1][1] = "*";
    griglia[1][2] = "*";
    griglia[2][0] = "*";
    griglia[2][1] = "*";
    griglia[2][2] = "*";
    return griglia;
}


//cosa fare per difendersi
String[][] computer(String[][] campo) throws InterruptedException {
    if ((campo[0][0].equals("x") && campo[1][0].equals("x")) && campo[2][0].equals("*")) //prima colonna
        campo[2][0] = "o";
    else if ((campo[2][0].equals("x") && campo[1][0].equals("x")) && campo[0][0].equals("*")) //prima colonna
        campo[0][0] = "o";
    else if ((campo[2][0].equals("x") && campo[0][0].equals("x")) && campo[1][0].equals("*")) //prima colonna
        campo[1][0] = "o";
    else if ((campo[0][1].equals("x") && campo[1][1].equals("x")) && campo[2][1].equals("*")) //seconda colonna
        campo[2][1] = "o";
    else if ((campo[2][1].equals("x") && campo[1][1].equals("x")) && campo[0][1].equals("*"))  //seconda colonna
        campo[0][1] = "o";
    else if ((campo[2][1].equals("x") && campo[0][1].equals("x")) && campo[1][1].equals("*")) //prima colonna
        campo[1][1] = "o";
    else if ((campo[0][2].equals("x") && campo[1][2].equals("x")) && campo[2][2].equals("*")) //terza colonna
        campo[2][2] = "o";
    else if ((campo[2][2].equals("x") && campo[1][2].equals("x")) && campo[0][2].equals("*")) //terza colonna
        campo[0][2] = "o";
    else if ((campo[2][2].equals("x") && campo[0][2].equals("x")) && campo[1][2].equals("*")) //prima colonna
        campo[1][2] = "o";
    else if ((campo[0][0].equals("x") && campo[0][1].equals("x")) && campo[0][2].equals("*")) //prima riga
        campo[0][2] = "o";
    else if ((campo[0][1].equals("x") && campo[0][2].equals("x")) && campo[0][0].equals("*"))//prima riga
        campo[0][0] = "o";
    else if ((campo[0][0].equals("x") && campo[0][2].equals("x")) && campo[0][1].equals("*"))//prima riga
        campo[0][1] = "o";
    else if ((campo[1][0].equals("x") && campo[1][1].equals("x")) && campo[1][2].equals("*"))//seconda riga
        campo[1][2] = "o";
    else if ((campo[1][1].equals("x") && campo[1][2].equals("x")) && campo[1][0].equals("*"))//seconda riga
        campo[1][0] = "o";
    else if ((campo[1][0].equals("x") && campo[1][2].equals("x")) && campo[1][1].equals("*"))//seconda riga
        campo[1][1] = "o";
    else if ((campo[2][0].equals("x") && campo[2][1].equals("x")) && campo[2][2].equals("*"))//terza riga
        campo[2][2] = "o";
    else if ((campo[2][1].equals("x") && campo[2][2].equals("x")) && campo[2][0].equals("*"))//terza riga
        campo[2][0] = "o";
    else if ((campo[2][0].equals("x") && campo[2][2].equals("x")) && campo[2][1].equals("*"))//prima riga
        campo[2][1] = "o";
    else if ((campo[0][0].equals("x") && campo[1][1].equals("x")) && campo[2][2].equals("*")) //obliquo funzione negativa
        campo[2][2] = "o";
    else if ((campo[1][1].equals("x") && campo[2][2].equals("x")) && campo[0][0].equals("*")) //obliquo funzione negativa
        campo[0][0] = "o";
    else if ((campo[0][0].equals("x") && campo[2][2].equals("x")) && campo[1][1].equals("*")) //obliquo funzione negativa
        campo[1][1] = "o";
    else if ((campo[2][0].equals("x") && campo[1][1].equals("x")) && campo[0][2].equals("*")) //obliquo funzione positiva
        campo[0][2] = "o";
    else if ((campo[0][2].equals("x") && campo[1][1].equals("x")) && campo[2][0].equals("*")) //obliquo funzione positiva
        campo[2][0] = "o";
    else if ((campo[0][2].equals("x") && campo[2][2].equals("x")) && campo[1][1].equals("*")) //obliquo funzione positiva
        campo[1][1] = "o";
    else {
        int x, y;
        do {
            x = (int) (Math.random() % 3);
            Thread.sleep(1000);
            y = (int) (Math.random() % 3);
            if (campo[y][x].equals("*"))
                campo[y][x] = "o";
        } while (campo[y][x].equals("x") && campo[y][x].equals("o"));
    }
    return campo;
}

int pvp() { //giocatore 1 "x", giocatpre 2 "y"
    String[][] campo;
    campo = new String[3][3];
    campo = griglia(campo);
    String annulla_mossa;
    Scanner in = new Scanner(System.in);
    int y, x;
    /*    /==\
           ||
         setup */
    print(campo);
    while (true) {
        do {
            System.out.println("giocatore 1, inserire coordinata X");
            x = in.nextInt();
            System.out.println("giocatore 1, inserire coordinata Y");
            y = in.nextInt();
            if (campo[y][x].equals("*"))
                campo[y][x] = "x";
            else if (!campo[y][x].equals("*"))
                System.out.println("sei un mona, rifai");
            System.out.println();
            print(campo);
            do {
                System.out.println("vuoi annullare la mossa (Si / No)");
                annulla_mossa = in.nextLine();
            } while (!annulla_mossa.equals("Si") && !annulla_mossa.equals("No"));
            if (annulla_mossa.equals("Si"))
                campo[y][x] = "*";
        } while (!campo[y][x].equals("x") || annulla_mossa.equals("Si"));
        winningrules(campo);
        do {
            System.out.println("giocatore 2, inserire coordinata X");
            x = in.nextInt();
            System.out.println("giocatore 2, inserire coordinata Y");
            y = in.nextInt();
            if (campo[y][x].equals("*"))
                campo[y][x] = "o";
            else if (!campo[y][x].equals("*"))
                System.out.println("sei un mona, rifai");
            System.out.println();
            print(campo);
            do {
                System.out.println("vuoi annullare la mossa (Si / No)");
                annulla_mossa = in.nextLine();
            } while (!annulla_mossa.equals("Si") && !annulla_mossa.equals("No"));
            if (annulla_mossa.equals("Si"))
                campo[y][x] = "*";
        } while (!campo[y][x].equals("x") || annulla_mossa.equals("Si"));
        winningrules(campo);
    }
}

void winningrules(String[][] campo) {
    //giocatore 1
    if (campo[0][0].equals("x") && campo[1][0].equals("x") && campo[2][0].equals("x")) { //prima colonna
        System.out.println("Abbiamo un vincitore!!!");
        playSoundwin();
    } else if (campo[0][1].equals("x") && campo[1][1].equals("x") && campo[2][1].equals("*x")) { //seconda colonna
        System.out.println("Abbiamo un vincitore!!!");
        playSoundwin();
    } else if (campo[0][2].equals("x") && campo[1][2].equals("x") && campo[2][2].equals("x")) { //terza colonna
        System.out.println("Abbiamo un vincitore!!!");
        playSoundwin();
    } else if (campo[0][0].equals("x") && campo[0][1].equals("x") && campo[0][2].equals("x")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundwin();
    } else if (campo[1][0].equals("x") && campo[1][1].equals("x") && campo[1][2].equals("x")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundwin();
    } else if (campo[2][0].equals("x") && campo[2][1].equals("x") && campo[2][2].equals("x")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundwin();
    } else if (campo[0][0].equals("x") && campo[1][1].equals("x") && campo[2][2].equals("x")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundwin();
    } else if (campo[2][0].equals("x") && campo[1][1].equals("x") && campo[0][2].equals("x")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundwin();
    } else if (campo[0][0].equals("o") && campo[1][0].equals("o") && campo[2][0].equals("o")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundlose();
    } else if (campo[0][1].equals("o") && campo[1][1].equals("o") && campo[2][1].equals("o")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundlose();
    } else if (campo[0][2].equals("o") && campo[1][2].equals("o") && campo[2][2].equals("o")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundlose();
    } else if (campo[0][0].equals("o") && campo[0][1].equals("o") && campo[0][2].equals("o")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundlose();
    } else if (campo[1][0].equals("o") && campo[1][1].equals("o") && campo[1][2].equals("o")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundlose();
    } else if (campo[2][0].equals("o") && campo[2][1].equals("o") && campo[2][2].equals("o")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundlose();
    } else if (campo[0][0].equals("o") && campo[1][1].equals("o") && campo[2][2].equals("o")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundlose();
    } else if (campo[2][0].equals("o") && campo[1][1].equals("o") && campo[0][2].equals("o")) { //prima riga
        System.out.println("Abbiamo un vincitore!!!");
        playSoundlose();
    }
}

//suono vittoria PvP giocatore 1 e PvE
public void playSoundwin() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/win.wa").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch (Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
    }
}

//suuono sconfitta PvE e vittoria PvP giocatore 2
public void playSoundlose() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/lose.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch (Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
    }
}
