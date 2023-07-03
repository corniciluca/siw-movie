# siw-movie
Progetto siw-movie per l'esame di SIW

* **Caso d'uso UC1: Inserimento di un nuovo film**  attore: _amministratore_
1. un amministratore seleziona la finestra per la gestione del sito.
2. l'amministratore seleziona l'attività per l'aggiunta di un nuovo film al sito.
3. l'amministratore inserisce titolo, anno di uscita, data di uscita e immagini del film. Infine, l'amministratore conferma l'inserimento.
4. I dati del film sono corretti. Il sistema registra il nuovo film.
5. _I dati del film non sono corretti. Il sistema termina l'operazione di inserimento film e visualizza un messaggio d'errore._

* **Caso d'uso UC2: Aggiornamento dei dati di un artista**  attore: _amministratore_
1. un amministratore seleziona la finestra per visualizzazione di tutti gli artisti.
2. l'amministratore seleziona l'attività di modifica dei dati per un determinato artista.
3. l'amministratore inserisce nome,cognome,data di nascita e, se esiste, data di morte dell'artista di cui vuole aggiornare i dati. Infine, l'amministratore conferma l'inserimento.
4. I dati dell'artista sono corretti. Il sistema aggiorna i dati dell'artista.
5. _I dati dell'artista non sono corretti. Il sistema termina l'operazione di aggiornamento e visualizza un messaggio d'errore._

* **Caso d'uso UC3: Inserimento nuova recensione**  attore: _utente registrato_
1. un utente registrato seleziona la finestra per la visualizzazione di tutti i film.
2. l'utente seleziona la finestra per la visualiazzazione dei dati di un determinato film.
3. l'utente inserisce titolo,testo e votazione della recensione. Infine, l'utente conferma la recensione.
4. I dati della recensione sono corretti e l'utente non ha già recensito il film. Il sistema aggiorna i dati dell'attore.
5. _I dati del film non sono corretti o l'utente ha già recensito il film. Il sistema termina l'operazione di aggiornamento e visualizza un messaggio d'errore._

* **Caso d'uso UC4: Visualizzazione film**  attore: _utente registrato_
1. un utente registrato seleziona la finestra per la visualizzazione di tutti i film e il sistema mostra tutti i film in catalogo.
2. l'utente seleziona la finestra per la visualiazzazione dei dati di un determinato film e il sistema mostra i dati del film.

* **Caso d'uso UC5: Visualizzazione artisti**  attore: _utente registrato_
1. un utente registrato seleziona la finestra per la visualizzazione di tutti gli artisti e il sistema mostra tutti gli artisti registrati.
2. l'utente seleziona la finestra per la visualiazzazione dei dati di un determinato film e il sistema mostra i dati del film.
   
* **Caso d'uso UC6: Visualizzazione film**  attore: _utente generico_
1. un utente generico seleziona la finestra per la visualizzazione di tutti i film e il sistema mostra tutti i film in catalogo.
2. l'utente seleziona la finestra per la visualiazzazione dei dati di un determinato film e il sistema mostra i dati del film.

* **Caso d'uso UC7: Visualizzazione artisti**  attore: _utente generico_
1. un utente generico seleziona la finestra per la visualizzazione di tutti gli artisti e il sistema mostra tutti gli artisti registrati.
2. l'utente seleziona la finestra per la visualiazzazione dei dati di un determinato film e il sistema mostra i dati del film.

* **Caso d'uso UC8: Cancellazione di una recensione**  attore: _amministratore_
1. un amministratore seleziona la finestra per la visualizzazione dei film in catalogo e il sistema mostra i film.
2. l'amministratore seleziona il film interessato e il sistema mostra i dati di tale film.
3. l'amministratore seleziona la recensione interessata, seleziona l'attività di cancellazione recensione.
4. Il sistema cancella la recensione e aggiorna i dati ad essa legati.


* **Caso d'uso UC9: Cancellazione di un film**  attore: _amministratore_
1. un amministratore seleziona la finestra per la gestione del sito.
2. l'amministratore seleziona l'operazione di cancellazione film per un determinato film.
3. Il sistema elimina il film e aggiorna i dati associati.

* **Caso d'uso UC10: Cancellazione di un artista**  attore: _amministratore_
1. un amministratore seleziona la finestra per la gestione del sito.
2. l'amministratore seleziona l'operazione di cancellazione artista per un determinato artista.
3. Il sistema elimina l'artista e aggiorna i dati associati.

* **Caso d'uso UC11: Inserimento nuovo artista**  attore: _amministratore_
1. un amministratore seleziona la finestra per la gestione del sito.
2. l'amministratore seleziona l'attività per l'aggiunta di un nuovo artista al sito.
3. l'amministratore inserisce nome, cognome, data di nascita e di morte, se esiste, e un'immagine dell'artista. Infine, l'amministratore conferma l'inserimento.
4. I dati dell'artista sono corretti. Il sistema registra il nuovo artista.
5. _I dati dell'artista non sono corretti. Il sistema termina l'operazione di inserimento artista e visualizza un messaggio d'errore._

* **Caso d'uso UC12: Aggirnamento dei dati di un film**  attore: _amministratore_
1. un amministratore seleziona la finestra per la visualizzazione di tutti i film e il sistema mostra tutti i film in catalogo.
2. l'amministratore seleziona un determinato film e il sistema mostra tutti i dati di quel film.
3. l'amministratore seleziona il campo che vuole aggiornare, inserisce il nuovo dato e conferma l'aggiornamento.
5. Il dato inserito dall'utente è corretto. Il sistema aggiorna i dati del film.
6. _Il dato inserito dall'utente non è corretto. Il sistema termina l'aggiornamento dei dati e mostra un messaggio d'errore._
