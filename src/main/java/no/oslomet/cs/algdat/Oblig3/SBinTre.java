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

    private static <T> Node<T> førstePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
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
