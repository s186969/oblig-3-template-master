# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Alex Vu, s186969, s186969@oslomet.no


# Oppgavebeskrivelse

<h3>Oppgave 1</h3>
<p>Relevant kildekode som har blitt benyttet er programkode 5.2.3 a) fra Ulf Uttersrud sin kompendium av 2021. 
Overordnet problemstilling er at programkoden skal få korrekt verdi i hver node for referansen <i>forelder</i>. </p>
<p>Besvarelsen har kun lagt inn <i>forelder</i> når det skal opprettes en ny node. Hensikten er at når det 
opprettes en ny node, vil noden peke til forelderen for hver opprettelse. Dermed vil metoden gi <i>forelder</i> 
for hver verdi.</p>

<h3>Oppgave 2</h3>
<p>Besvarelsen har løst denne oppgaven på samme måte som i oppgave 2 i avsnitt 5.2.6 i kompendiumet. Metoden fungerer 
slik at har en hjelpevariabel som teller antall forekomster av <i>verdi</i>. Dette kjøres gjennom en while-løkke som 
leter oppgitte verdi. For at hjelpevariabelen skal kunne telle antall forekomster av duplikater, er variabelen plassert
på høyrebarnet ettersom verdiene som skal på høyre side er større eller lik foreldrenoden.</p>

<h3>Oppgave 3</h3>
<p>Oppgaveteksten reiser to problemstillinger. Den første metoden skal returnere første node som kommer i postorden, 
mens den andre metoden skal returnere noden etter den første.</p>
<p>For å traversere postorden stilles det tre kumulative vilkår. For det første skal man traversere venstre subtre. 
Deretter traverserer man høyresubtre før man avslutter på roten av treet.</p>
<p>I første metode har besvarelsen benyttet av seg en while-løkke som traverserer nedover et tre til den siste noden
til den finner en node som ikke har et barn. Rekkefølgen starter på samme måte som definisjonen i andre avsnitt: venstre 
subtre, høyresub tre og roten.</p>
<p>I andre metode har besvarelsen laget betingelser som letter etter foreldernoden til barnenoden fra første metode. 
Hensikten er at man skal kunne traversere tilbake et nivå ettersom et barn er i utgangpunktet kommer fra forelderen. 
Besvarelsen av tre betingelser i denne oppgaven.</p>
<p>Den første betingelsen gjelder hvis  forelder er null, så vil den returnere null tilbake. Dette innebærer at det 
ikke finnes en neste postorden hvis treet inneholder kun roten.</p>
<p>Den andre betingelsen er hvis høyrebarnet har samme verdi som blir tatt inn, returner den hvem 
forelderen er. Dette innbærer at man traverserer fra høyrebarn til sitt forelder. Dette gjelder også hvis 
høyrebarnet er tomt, altså forelderen har bare et venstrebarn, ettersom man skal traversere fra venstrebarn til 
sitt forelder.</p>
<p>Den siste betingelsen går nedover høyresubtre for å undersøke om oppgitte verdi har en forelder som har 
et høyrebarn for å finne neste postorden.</p>

<h3>Oppgave 4</h3>
<h3>Oppgave 5</h3>
<h3>Oppgave 6</h3>