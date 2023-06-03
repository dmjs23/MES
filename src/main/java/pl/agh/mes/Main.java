package pl.agh.mes;

import pl.agh.mes.rozwiazania.KodNiestacjonarny;
import pl.agh.mes.rozwiazania.KodStacjonarny;

public class Main {

    public static void main(String[] args) {

        System.out.println("Kod stacjonarny");
        KodStacjonarny kodStacjonarny = new KodStacjonarny();
        kodStacjonarny.obliczStacjonarnie();

        System.out.println("----------------------------------");

        System.out.println("Kod niestacjonarny");
        KodNiestacjonarny kodNiestacjonarny = new KodNiestacjonarny();
        kodNiestacjonarny.obliczNiestacjonarnie();
    }
}
