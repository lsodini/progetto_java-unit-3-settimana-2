package LucaSodini;

import java.util.*;
import java.util.stream.*;

public class Collezione {
    private List<Gioco> giochi = new ArrayList<>();
    public Collezione() throws Exception {

        giochi.add(new Videogioco("V001", "Halo Infinite", 2021, 59.99, "PC", 9, Videogioco.Genere.AZIONE));
        giochi.add(new Videogioco("V002", "The Witcher 3", 2015, 29.99, "PS4", 9, Videogioco.Genere.GDR));
        giochi.add(new Videogioco("V003", "Cyberpunk 2077", 2020, 49.99, "Xbox", 8, Videogioco.Genere.AZIONE));


        giochi.add(new GiocoDaTavolo("T001", "Monopoly", 1935, 19.99, 2, 90));
        giochi.add(new GiocoDaTavolo("T002", "Catan", 1995, 39.99, 3, 120));
        giochi.add(new GiocoDaTavolo("T003", "Risiko", 1957, 29.99, 2, 180));
    }
    public void aggiungiGioco(Gioco gioco) {
        if (giochi.stream().anyMatch(g -> g.getIdGioco().equals(gioco.getIdGioco()))) {
            throw new IllegalArgumentException("ID già presente!");
        }
        giochi.add(gioco);
    }

    public Gioco cercaGiocoPerId(String id) {
        return giochi.stream().filter(g -> g.getIdGioco().equals(id)).findFirst().orElse(null);
    }

    public List<Gioco> cercaGiochiPerPrezzo(double prezzoMassimo) {
        return giochi.stream().filter(g -> g.getPrezzo() <= prezzoMassimo).collect(Collectors.toList());
    }

    public List<GiocoDaTavolo> cercaGiochiDaTavoloPerNumeroGiocatori(int numeroGiocatori) {
        return giochi.stream().filter(g -> g instanceof GiocoDaTavolo && ((GiocoDaTavolo) g).getNumeroGiocatori() == numeroGiocatori)
                .map(g -> (GiocoDaTavolo) g).collect(Collectors.toList());
    }

    public void rimuoviGioco(String id) {
        giochi.removeIf(g -> g.getIdGioco().equals(id));
    }

    public void aggiornaPrezzo(String id, double nuovoPrezzo) {
        Gioco gioco = cercaGiocoPerId(id);
        if (gioco != null) {
            gioco.setPrezzo(nuovoPrezzo);
        } else {
            throw new IllegalArgumentException("Gioco non trovato!");
        }
    }

    public void stampaStatistiche() {
        long numVideogiochi = giochi.stream().filter(g -> g instanceof Videogioco).count();
        long numGiochiTavolo = giochi.stream().filter(g -> g instanceof GiocoDaTavolo).count();
        Optional<Gioco> giocoPiuCostoso = giochi.stream().max(Comparator.comparingDouble(Gioco::getPrezzo));
        double prezzoMedio = giochi.stream().mapToDouble(Gioco::getPrezzo).average().orElse(0.0);

        System.out.println("Numero di videogiochi: " + numVideogiochi);
        System.out.println("Numero di giochi da tavolo: " + numGiochiTavolo);
        giocoPiuCostoso.ifPresent(g -> System.out.println("Gioco più costoso: " + g));
        System.out.println("Prezzo medio: " + prezzoMedio);
    }

    public boolean contieneId(String id) {
        return giochi.stream().anyMatch(g -> g.getIdGioco().equals(id));
    }
}
