package LucaSodini;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Collezione {
    private List<Gioco> giochi;

    public Collezione() {
        this.giochi = new ArrayList<>();
    }

    public void aggiungiGioco(Gioco gioco) {
        if (gioco == null) {
            System.out.println("Errore: Il gioco non può essere nullo.");
            return;
        }

        String idGioco = gioco.getIdGioco();
        if (idGioco == null || idGioco.isEmpty()) {
            System.out.println("Errore: ID del gioco non può essere nullo o vuoto.");
            return;
        }

        if (giochi.stream().anyMatch(g -> g.getIdGioco().equals(idGioco))) {
            System.out.println("Errore: Un gioco con questo ID esiste già.");
            return;
        }

        giochi.add(gioco);
    }


    public Gioco cercaGiocoPerId(String id) {
        Optional<Gioco> gioco = giochi.stream()
                .filter(g -> g.getIdGioco().equals(id))
                .findFirst();
        return gioco.orElse(null);
    }

    public List<Gioco> cercaGiochiPerPrezzo(double prezzoMassimo) {
        return giochi.stream()
                .filter(g -> g.getPrezzo() <= prezzoMassimo)
                .collect(Collectors.toList());
    }

    public List<GiocoDaTavolo> cercaGiochiDaTavoloPerNumeroGiocatori(int numeroGiocatori) {
        return giochi.stream()
                .filter(g -> g instanceof GiocoDaTavolo)
                .map(g -> (GiocoDaTavolo) g)
                .filter(g -> g.getNumeroGiocatori() == numeroGiocatori)
                .collect(Collectors.toList());
    }

    public void rimuoviGioco(String id) {
        giochi.removeIf(g -> g.getIdGioco().equals(id));
    }

    public void aggiornaPrezzo(String id, double nuovoPrezzo) {
        Gioco gioco = cercaGiocoPerId(id);
        if (gioco != null) {
            gioco.setPrezzo(nuovoPrezzo);
        } else {
            System.out.println("Errore: Gioco non trovato con l'ID fornito.");
        }
    }
    public boolean contieneId(String id) {
        return giochi.stream().anyMatch(gioco -> gioco.getIdGioco().equals(id));
    }

    public void stampaStatistiche() {
        long numeroVideogiochi = giochi.stream()
                .filter(g -> g instanceof Videogioco)
                .count();

        long numeroGiochiDaTavolo = giochi.stream()
                .filter(g -> g instanceof GiocoDaTavolo)
                .count();

        Optional<Gioco> giocoPiuCostoso = giochi.stream()
                .max(Comparator.comparingDouble(Gioco::getPrezzo));

        double prezzoMedio = giochi.stream()
                .mapToDouble(Gioco::getPrezzo)
                .average()
                .orElse(0.0);

        System.out.println("Numero di videogiochi: " + numeroVideogiochi);
        System.out.println("Numero di giochi da tavolo: " + numeroGiochiDaTavolo);
        giocoPiuCostoso.ifPresent(g -> System.out.println("Gioco più costoso: " + g));
        System.out.println("Prezzo medio: " + prezzoMedio);
    }
}
