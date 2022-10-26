package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    //  Oppgave 1
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> barn = rot;
        Node<T> forelder = null;

        int cmp = 0;                                    //  Hjelpevariabel for komparatoren

        //  While-løkke som forsetter frem til barnet er ute av treet, dvs. barnet har ikke barn.
        //  Denne forteller at foreldrene vet hvem barna er.
        while (barn != null) {
            forelder = barn;                            //  Når barnet får et barn blir den en forelder
            cmp = comp.compare(verdi, barn.verdi);      //  Sammenligner verdiene ved hjelp av komparatoren

            if (cmp < 0) {
                barn = barn.venstre;                    //  Barnet blir lagt til venstre hvis den er mindre enn
                                                        //  foreldren
            } else {
                barn = barn.høyre;                      //  Barnet blir lagt til høyre hvis den er større enn
                                                        //  foreldren
            }
        }

        //  Når barn har null under seg, dvs. at barnet ikke har barn, vet vi at forelder var den siste vi passerte
        //  barnet. Kodene under forteller barnet hvem foreldren er.
        barn = new Node<>(verdi, forelder);             //  Ett nyfødt barn

        if (forelder == null) {
            rot = barn;                                 //  Hvis barnet ikke har en forelder, er den stamforeldren rot
        } else if (cmp < 0) {
            forelder.venstre = barn;                    //  Foreldren sin venstre barn
        } else {
            forelder.høyre = barn;                      //  Foreldren sin høyre barn
        }
        antall++;                                       //  Én verdi mer i treet
        return true;                                    //  Vellykket innlegging
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //  Oppgave 2
    public int antall(T verdi) {
        Node<T> barn = rot;
        int antallVerdi = 0;                                //  Hjelpevariabel for å telle antall forekomster
                                                            //  av en verdi
        while (barn != null) {
            int cmp = comp.compare(verdi, barn.verdi);      //  Sammenligner verdiene ved hjelp av komparatoren

            if (cmp < 0) {
                barn = barn.venstre;                        //  Tall som er mindre enn roten, blir lagt på venstre side
            } else {
                if (cmp == 0) {
                    antallVerdi++;                          //  Teller antall forekomster som kommer inn. Den ligger på
                                                            //  høyre side fordi alle noder som er større enn eller lik
                                                            //  noden skal på høyre side.
                }
                barn = barn.høyre;                          //  Tall som er lik eller større enn roten, blir lagt på
                                                            //  høyre side
            }
        }
        return antallVerdi;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //  Oppgave 3
    //  Se Programkode 5.1.7 g)
    private static <T> Node<T> førstePostorden(Node<T> p) {
        Node<T> barn = p;                                   //  Hjelpevariabel for å presisere barn. Husk at alle har
                                                            //  en gang vært et barn

        Objects.requireNonNull(barn, "Ulovlig med nullverdier.");

        //  While-loop som traverserer nedover til barn ikke har barn
        while (true) {
            //  Venstre side
            if (barn.venstre != null) {
                barn = barn.venstre;

            //  Høyre side
            } else if (barn.høyre != null) {
                barn = barn.høyre;

            //  Hvis barnet er en rot
            } else {
                return barn;
            }
        }
    }

    //  Oppgave 3
    private static <T> Node<T> nestePostorden(Node<T> p) {
        Node<T> barn = p;                                   //  Hjelpevariabel for å presisere barn. Husk at alle har
                                                            //  en gang vært et barn

        Node<T> forelder = barn.forelder;                   //  Hjelpevariabel for å presissere forelder.

        //  Hvis forelder er null, returnerer den null tilbake. Dette innebærer at det ikke finnes en nestepostorden
        //  hvis treet inneholder kun roten
        if (forelder == null) {
            return null;
        }

        //  Hvis høyrebarnet har samme verdi som blir tatt inn, returner den hvem forelderen er. Dette innbærer at man
        //  traverserer fra høyrebarn til sitt forelder. Gjelder også hvis høyrebarnet er tomt, altså forelderen har
        //  bare et venstrebarn. Nødvendig da man skal traversere fra venstrebarn til sitt forelder.
        if (forelder.høyre == barn || forelder.høyre == null) {
            return forelder;

        //  Kaller underliggende metode for å undersøke om oppgitte verdi har en forelder som har et høyrebarn og
        //  finner nestepostorden
        } else {
            return førstePostorden(forelder.høyre);
        }
    }

    //  Oppgave 4
    public void postorden(Oppgave<? super T> oppgave) {

        Node<T> postorden = førstePostorden(rot);                   //  Instansierer den første postorden
        oppgave.utførOppgave(postorden.verdi);                      //  Skriver ut den første postorden

        //  While-løkke som finner de neste verdiene av postorden frem til det når null
        while (postorden.forelder != null) {
            postorden = nestePostorden(postorden);                  //  Neste postorden
            oppgave.utførOppgave(postorden.verdi);                  //  Skriver ut neste postorden
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    //  Oppgave 4
    //  Se https://youtu.be/9OBxLX0W2gY?t=826
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        //  Basistilfellet hvis man ikke har noen noder for å stanse det rekursive kallet. Et obligatorisk kriterium
        //  for et rekursivt kall!
        if (p == null) {
            return;
        }
        postordenRecursive(p.venstre, oppgave);         //  Rekursivt kall for venstrebarn
        postordenRecursive(p.høyre, oppgave);           //  Rekursivt kall for høyrebarn
        oppgave.utførOppgave(p.verdi);                  //  Skriver ut noden
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    public static void main(String[] args) {
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) { tre.leggInn(verdi); }

        System.out.println(tre.antall());      // Utskrift: 10
        System.out.println(tre.antall(5));     // Utskrift: 0
        System.out.println(tre.antall(4));     // Utskrift: 3
        System.out.println(tre.antall(7));     // Utskrift: 2
        System.out.println(tre.antall(10));    // Utskrift: 1
    }

} // ObligSBinTre
