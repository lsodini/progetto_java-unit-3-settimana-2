package LucaSodini;

public class Videogioco extends Gioco {

    private String piattaforma;
    private int durata; // in ore
    private Genere genere;

    public enum Genere {
        AZIONE, AVVENTURA, GDR, STRATEGIA, SPORT
    }

    public Videogioco(String idGioco, String titolo, int annoPubblicazione, double prezzo, String piattaforma, int durata, Genere genere) {
        super(idGioco, titolo, annoPubblicazione, prezzo);
        this.piattaforma = piattaforma;
        this.durata = durata;
        this.genere = genere;
    }


    @Override
    public String toString() {
        return super.toString() + ", Piattaforma: " + piattaforma + ", Durata: " + durata + " ore, Genere: " + genere;
    }
}
