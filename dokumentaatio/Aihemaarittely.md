Ohjelmoinnin harjoitustyö - aihemäärittely

#ATC
Air Traffic Control -lennonjohtopeli



##Aihe

Toteutetaan peli, jossa ohjataan näytöllä näkyviä symboleja. Pelin on tarkoitus jäljitellä lennonjohdon toimintaa yksinkertaistetulla tasolla. Symbolit kuvastavat lentokoneita ja niille annetaan käskyjä tekstimuodossa. 

Pelaaja kirjoittaa lyhyitä komentoja, joissa identifioidaan haluttu lentokone ja annetaan käsky. Käskyt voivat pitää sisällään halutun lentokorkeuden, suunnan ja nopeuden. Näytöllä näkyvät symbolit liikkuvat annettujen käskyjen perusteella. Genre lienee top-down real-time strategy.



Pelissä tavoitteena on ohjata lentokone sopivaan paikkaan sopivalla korkeudella, suunnalla ja nopeudella, jotta se voi laskeutua lentokentälle. Samalla pitää välttää, että mikään lentokoneista ei ajaudu ulos tutkaruudun alueelta. Onnistuneista laskeutumisista ja lentokoneiden karkaamisista ulos tutkaruudusta pidetään kirjaa ja se näytetään pelaajalle. Mikäli kaksi lentokonetta ajautuu liian lähelle toisiaan, päättyy peli ja pelaajalle näytetään game over -ilmoitus.

##Käyttäjät:
Pelaaja (yksinpeli)



##Käyttäjän toiminnot

Pelaaja kirjoittaa näppäimistöllä komentoja.

##Rakenteen kuvaus
Ohjelma on rakenteeltaan jaettu kolmeen pakettiin: atc.main, atc.logic ja atc.gui.

###Atc.main
Atc.gui pitää sisällään ainoastaan luokat Main ja Timer. Main käynnistää ohjelman ja Timer vastaa ajanlaskennan tarjoamisesta ohjelman logiikalle.

###Atc.logic
Atc.logic pitää sisällään neljä luokkaa: GameLogic, Aircraft, CommandParser ja Values. GameLogic on ohjelman looginen ydin ja se huolehtii muun muassa lentokoneiden luomisesta, niiden liikkumisen päivittämisestä ja niiden tilanteen seuraamisesta.

Aircraft-luokka pitää sisällään kaiken lentokoneen liikkumiseen liittyvän logiikan. Käyttäjä antaa lentokoneelle käskyjä ja Aircraft-luokka vastaa lentokoneiden liikuttamisesta käskyjen mukaisesti. Kukin lentokone on yksi Aircraft-luokan olio.

CommandParser käsittelee käyttäjän syöttämiä komentoja. Ne ovat merkkijonoja, joista CommandParser erottelee lentokoneen tunnisteen ja sille annettavan komennon. Komento voi pitää sisällään lentokorkeuden, suunnan tai nopeuden. Komento lähetetään asianmukaiselle lentokoneelle kutsumalla suoraan sen metodeja. Jos käyttäjä syöttää virheellisen tunnisteen, ilmoitetaan siitä käyttäjälle. Jos tunniste on oikea, mutta komento epävalidi, jätetään komento huomiotta.

Values-luokka on erikoisempi luokka, jonka tehtävänä on luoda joitakin muuttujien arvoja toisille luokille. Values-luokka toteuttaa uuden lentokoneen tunnuksen luomisen satunnaisella menetelmällä. Lisäksi se tuottaa taulukon, jota tarvitaan lentosuuntien muuntamisessa liikkumisnopeuksiksi x- ja y-koordinaatistossa.

###Atc.gui
Atc.gui vastaa graafisesta käyttöliittymästä. Se sisältää GUIFrame-luokan, joka luo framen käyttöliittymää varten. Käyttöliittymä koostuu viidestä paneelista. Nämä on toteutettu omina luokkinaan: RadarPanel, ContainerPanel, CommandPanel, InfoPanel1 ja InfoPanel2.

RadarPanel huolehtii pelin tutkakuvan näyttämisestä. Se pitää sisällään liikkuvat lentokonesymbolit informaatioteksteineen sekä minimalistisen esityksen kiitoradasta ja lähestymisalueesta. ContainerPanel on tyhjä paneeli, joka luo pohjan kolmelle pienemmälle paneelille.

Näistä yksi on oikeassa alakulmassa oleva CommandPanel, joka näyttää käyttäjän syöttämän komentomerkkijonon sekä pelin loppuessa tietoa sen aiheuttaneista lentokoneista. CommandPanelin yläpuolella on InfoPanel1, joka näyttää tietoa siitä lentokoneesta, jolle käyttäjä on viimeksi antanut komennon. Lisäksi käyttäjä voi laittaa paneelin näyttämään tiedot kaikista tutkalla olevista lentokoneista. InfoPanel1:n yläpuolella on InfoPanel2, jossa näkyy saapuvien lentokoneiden aikataulu sekä kaksi laskuria, jotka näyttävät laskeutuneiden koneiden määrän sekä tutkaruudulta ulos karanneiden koneiden määrän.

Paneelien lisäksi atc.gui-paketti pitää sisällään KeyboardListener-luokan. Tämä luokka vastaanottaa näppäimistön syötteitä ja välittää ne CommandPanel-luokalle sekä atc.logic-paketissa olevalle CommandParser-luokalle.