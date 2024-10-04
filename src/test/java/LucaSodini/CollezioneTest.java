package LucaSodini;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class CollezioneTest {
    private Collezione collezione;

    @Before
    public void setUp() throws Exception {

        collezione = new Collezione();
    }

    @Test
    public void testCercaGiocoPerIdEsistente() {
        Gioco gioco = collezione.cercaGiocoPerId("V001");
        assertNotNull("Il gioco dovrebbe essere presente", gioco);
        assertEquals("Halo Infinite", gioco.getTitolo());
    }

    @Test
    public void testCercaGiocoPerIdNonEsistente() {
        Gioco gioco = collezione.cercaGiocoPerId("V999");
        assertNull("Il gioco con ID non esistente dovrebbe essere null", gioco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAggiungiGiocoConIdDuplicato() {
        Gioco giocoDuplicato = new Videogioco("V001", "Duplicato", 2021, 59.99, "PC", 9, Videogioco.Genere.AZIONE);
        collezione.aggiungiGioco(giocoDuplicato); // Dovrebbe lanciare un'eccezione per ID duplicato
    }

    @Test
    public void testCercaGiochiPerPrezzo() {
        List<Gioco> giochiEconomici = collezione.cercaGiochiPerPrezzo(30.00);
        assertEquals("Dovrebbero esserci 3 giochi con prezzo <= 30.00", 3, giochiEconomici.size());
        assertTrue(giochiEconomici.stream().anyMatch(g -> g.getTitolo().equals("Monopoly")));
    }

    @Test
    public void testCercaGiochiDaTavoloPerNumeroGiocatori() {
        List<GiocoDaTavolo> giochiPerDueGiocatori = collezione.cercaGiochiDaTavoloPerNumeroGiocatori(2);
        assertEquals("Dovrebbero esserci 2 giochi da tavolo per 2 giocatori", 2, giochiPerDueGiocatori.size());
        assertTrue(giochiPerDueGiocatori.stream().anyMatch(g -> g.getTitolo().equals("Monopoly")));
    }

    @Test
    public void testRimuoviGioco() {
        collezione.rimuoviGioco("V001");
        assertNull("Il gioco dovrebbe essere stato rimosso", collezione.cercaGiocoPerId("V001"));
    }

    @Test
    public void testAggiornaPrezzo() {
        collezione.aggiornaPrezzo("V002", 19.99);
        Gioco aggiornato = collezione.cercaGiocoPerId("V002");
        assertEquals("Il prezzo dovrebbe essere aggiornato a 19.99", 19.99, aggiornato.getPrezzo(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAggiornaPrezzoGiocoNonEsistente() {
        collezione.aggiornaPrezzo("V999", 19.99); // Dovrebbe lanciare un'eccezione per gioco non trovato
    }

    @Test
    public void testStampaStatistiche() {

        collezione.stampaStatistiche();

        assertEquals("Dovrebbero esserci 3 videogiochi", 3L, collezione.cercaGiochiPerPrezzo(Double.MAX_VALUE).stream().filter(g -> g instanceof Videogioco).count());
        assertEquals("Dovrebbero esserci 3 giochi da tavolo", 3L, collezione.cercaGiochiPerPrezzo(Double.MAX_VALUE).stream().filter(g -> g instanceof GiocoDaTavolo).count());
    }
}
