package LucaSodini;

public abstract class Gioco {
    private String idGioco;
    private String titolo;
    private int annoPubblicazione;
    private double prezzo;

    public Gioco(String idGioco, String titolo, int annoPubblicazione, double prezzo) {
        if (idGioco == null || idGioco.isEmpty()) {
            throw new IllegalArgumentException("ID del gioco non può essere nullo o vuoto.");
        }
        if (prezzo <= 0) {
            throw new IllegalArgumentException("Il prezzo deve essere positivo."); // Lancia un'eccezione per prezzo non valido
        }
        this.idGioco = idGioco;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getIdGioco() {
        return idGioco;
    }


    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0) {
            throw new IllegalArgumentException("Il prezzo deve essere positivo.");
        }
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "ID: " + idGioco + ", Titolo: " + titolo + ", Anno: " + annoPubblicazione + ", Prezzo: " + prezzo;
    }
}
