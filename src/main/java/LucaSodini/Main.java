package LucaSodini;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Collezione collezione = new Collezione();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Gestione Collezione Giochi ---");
            System.out.println("1. Aggiungi un gioco");
            System.out.println("2. Cerca un gioco per ID");
            System.out.println("3. Cerca giochi per prezzo");
            System.out.println("4. Cerca giochi da tavolo per numero di giocatori");
            System.out.println("5. Rimuovi un gioco");
            System.out.println("6. Aggiorna il prezzo di un gioco");
            System.out.println("7. Mostra statistiche");
            System.out.println("8. Mostra Collezione");
            System.out.println("0. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    while (true) {
                        try {
                            System.out.println("Che tipo di gioco vuoi aggiungere? (1: Videogioco, 2: Gioco da Tavolo)");
                            int tipoGioco = scanner.nextInt();
                            scanner.nextLine();

                            if (tipoGioco == 1) {
                                String id = chiediId(scanner, collezione);
                                String titolo = chiediTitolo(scanner);
                                int anno = chiediAnno(scanner);
                                double prezzo = chiediPrezzo(scanner);
                                String piattaforma = chiediPiattaforma(scanner);
                                int durataOre = chiediDurataOre(scanner);
                                Videogioco.Genere genere = chiediGenere(scanner);

                                Videogioco videogioco = new Videogioco(id, titolo, anno, prezzo, piattaforma, durataOre, genere);
                                collezione.aggiungiGioco(videogioco);
                                System.out.println("Videogioco aggiunto con successo.");
                                break;
                            } else if (tipoGioco == 2) {
                                String id = chiediId(scanner, collezione);
                                String titolo = chiediTitolo(scanner);
                                int anno = chiediAnno(scanner);
                                double prezzo = chiediPrezzo(scanner);
                                int numeroGiocatori = chiediNumeroGiocatori(scanner);
                                int durataMinuti = chiediDurataMinuti(scanner);

                                GiocoDaTavolo giocoDaTavolo = new GiocoDaTavolo(id, titolo, anno, prezzo, numeroGiocatori, durataMinuti);
                                collezione.aggiungiGioco(giocoDaTavolo);
                                System.out.println("Gioco da Tavolo aggiunto con successo.");
                                break;
                            } else {
                                System.out.println("Tipo di gioco non valido, riprova.");
                            }
                        } catch (Exception e) {
                            System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                            scanner.nextLine();
                        }
                    }
                    break;

                case 2:
                    while (true) {
                        try {
                            System.out.println("Inserisci ID del gioco da cercare:");
                            String idCercato = scanner.nextLine();
                            Gioco gioco = collezione.cercaGiocoPerId(idCercato);
                            if (gioco != null) {
                                System.out.println(gioco);
                            } else {
                                System.out.println("Gioco non trovato.");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        try {
                            System.out.println("Inserisci il prezzo massimo per la ricerca:");
                            double prezzoMassimo = scanner.nextDouble();
                            List<Gioco> giochiTrovati = collezione.cercaGiochiPerPrezzo(prezzoMassimo);
                            if (giochiTrovati.isEmpty()) {
                                System.out.println("Nessun gioco trovato.");
                            } else {
                                giochiTrovati.forEach(g -> System.out.println(g.toString()));
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                            scanner.nextLine();
                        }
                    }
                    break;

                case 4:
                    while (true) {
                        try {
                            System.out.println("Inserisci il numero di giocatori:");
                            int numeroGiocatori = scanner.nextInt();
                            List<GiocoDaTavolo> giochiDaTavolo = collezione.cercaGiochiDaTavoloPerNumeroGiocatori(numeroGiocatori);
                            if (giochiDaTavolo.isEmpty()) {
                                System.out.println("Nessun gioco da tavolo trovato.");
                            } else {
                                giochiDaTavolo.forEach(g -> System.out.println(g.toString()));
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                            scanner.nextLine();
                        }
                    }
                    break;

                case 5:
                    while (true) {
                        try {
                            System.out.println("Inserisci ID del gioco da rimuovere:");
                            String idRimosso = scanner.nextLine();
                            collezione.rimuoviGioco(idRimosso);
                            System.out.println("Gioco rimosso con successo.");
                            break;
                        } catch (Exception e) {
                            System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                        }
                    }
                    break;

                case 6:
                    while (true) {
                        try {
                            System.out.println("Inserisci ID del gioco da aggiornare:");
                            String idAggiornato = scanner.nextLine();
                            System.out.println("Inserisci nuovo prezzo:");
                            double nuovoPrezzo = scanner.nextDouble();
                            collezione.aggiornaPrezzo(idAggiornato, nuovoPrezzo);
                            System.out.println("Prezzo aggiornato con successo.");
                            break;
                        } catch (Exception e) {
                            System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                            scanner.nextLine();
                        }
                    }
                    break;

                case 7:
                    collezione.stampaStatistiche();
                    break;

                case 8:
                    mostraCollezione(collezione);
                    break;

                case 0:
                    System.out.println("Uscita dal programma.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Scelta non valida, riprova.");
                    break;
            }
        }
    }

    public static void mostraCollezione(Collezione collezione) {
        List<Gioco> giochi = collezione.cercaGiochiPerPrezzo(Double.MAX_VALUE); // ottieni tutti i giochi
        if (giochi.isEmpty()) {
            System.out.println("Nessun gioco nella collezione.");
        } else {
            System.out.println("Collezione di Giochi:");
            for (Gioco gioco : giochi) {
                System.out.println(gioco.getTitolo() + " (ID: " + gioco.getIdGioco() + ")");
            }
        }
    }


    private static String chiediId(Scanner scanner, Collezione collezione) {
        while (true) {
            System.out.println("Inserisci ID:");
            String id = scanner.nextLine();
            if (collezione.contieneId(id)) {
                System.out.println("ID già esistente. Riprova con un ID diverso.");
            } else {
                return id;
            }
        }
    }

    private static String chiediTitolo(Scanner scanner) {
        while (true) {
            System.out.println("Inserisci titolo:");
            String titolo = scanner.nextLine();
            if (titolo.isEmpty()) {
                System.out.println("Il titolo non può essere vuoto. Riprova.");
            } else {
                return titolo;
            }
        }
    }

    private static int chiediAnno(Scanner scanner) {
        while (true) {
            System.out.println("Inserisci anno di pubblicazione:");
            try {
                int anno = scanner.nextInt();
                scanner.nextLine();
                if (anno > java.time.Year.now().getValue()) {
                    throw new IllegalArgumentException("L'anno non può essere nel futuro.");
                }
                return anno;
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                scanner.nextLine();
            }
        }
    }

    private static double chiediPrezzo(Scanner scanner) {
        while (true) {
            System.out.println("Inserisci prezzo:");
            try {
                double prezzo = scanner.nextDouble();
                scanner.nextLine();
                if (prezzo < 0) {
                    throw new IllegalArgumentException("Il prezzo deve essere positivo.");
                }
                return prezzo;
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                scanner.nextLine();
            }
        }
    }

    private static String chiediPiattaforma(Scanner scanner) {
        while (true) {
            System.out.println("Inserisci piattaforma (PC, PS5, XBox ecc...):");
            String piattaforma = scanner.nextLine();
            if (piattaforma.isEmpty() ) {
                System.out.println("Piattaforma non valida. Riprova.");
            } else {
                return piattaforma;
            }
        }
    }

    private static int chiediDurataOre(Scanner scanner) {
        while (true) {
            System.out.println("Inserisci durata di gioco in ore:");
            try {
                int durataOre = scanner.nextInt();
                scanner.nextLine();
                if (durataOre < 0) {
                    throw new IllegalArgumentException("La durata deve essere positiva.");
                }
                return durataOre;
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                scanner.nextLine();
            }
        }
    }

    private static int chiediNumeroGiocatori(Scanner scanner) {
        while (true) {
            System.out.println("Inserisci numero di giocatori (minimo 2, massimo 10):");
            try {
                int numeroGiocatori = scanner.nextInt();
                scanner.nextLine();
                if (numeroGiocatori < 2 || numeroGiocatori > 10) {
                    throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 10.");
                }
                return numeroGiocatori;
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                scanner.nextLine();
            }
        }
    }

    private static int chiediDurataMinuti(Scanner scanner) {
        while (true) {
            System.out.println("Inserisci durata media di una partita in minuti:");
            try {
                int durataMinuti = scanner.nextInt();
                scanner.nextLine();
                if (durataMinuti <= 0) {
                    throw new IllegalArgumentException("La durata deve essere positiva.");
                }
                return durataMinuti;
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage() + ". Riprova.");
                scanner.nextLine();
            }
        }
    }

    private static Videogioco.Genere chiediGenere(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Inserisci genere (1: AZIONE, 2: AVVENTURA, 3: GDR, 4: STRATEGIA, 5: SPORT):");
                int genereScelto = scanner.nextInt();
                if (genereScelto < 1 || genereScelto > 5) {
                    throw new IllegalArgumentException("Genere non valido. Riprova.");
                }
                return Videogioco.Genere.values()[genereScelto - 1];
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
