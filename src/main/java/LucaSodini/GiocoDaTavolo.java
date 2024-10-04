package LucaSodini;

public class GiocoDaTavolo extends Gioco {
    private int numeroGiocatori;
    private int durataMinuti;

    public GiocoDaTavolo(String id, String titolo, int anno, double prezzo, int numeroGiocatori, int durataMinuti) throws Exception {
        super(id, titolo, anno, prezzo);

        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new Exception("Il numero di giocatori deve essere tra 2 e 10.");
        }
        this.numeroGiocatori = numeroGiocatori;

        if (durataMinuti <= 0) {
            throw new Exception("La durata media di una partita deve essere positiva.");
        }
        this.durataMinuti = durataMinuti;
    }


    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    @Override
    public String toString() {
        return super.toString() + ", Numero Giocatori: " + numeroGiocatori + ", Durata: " + durataMinuti + " minuti";
    }
}
