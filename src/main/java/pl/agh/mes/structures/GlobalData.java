package pl.agh.mes.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalData {

    private double temp; //temperatura otoczenia
    private double alfa; //warunek brzegowy konwe
    private double q; //gęstość strumienia
    private double k; //waspółczynnik przewodzenia ciepła
    private double L; //długość pręta
    private double S; //pole przekroju pręta
    private int liczbaWezlow; // liczba węzłów siatki MES
    private int nE; //liczba elenemtów satki MES

    @Override
    public String toString() {
        return "GlobalData{" +
                "temp=" + temp +
                ", alfa=" + alfa +
                ", q=" + q +
                ", k=" + k +
                ", L=" + L +
                ", S=" + S +
                ", liczbaWezlow=" + liczbaWezlow +
                ", nE=" + nE +
                '}';
    }

    public void showData() {
        System.out.println("-------------------------");
        System.out.println("Dane:");
        System.out.println("-------------------------");
        System.out.printf("%-20s%.2f%n", "Alfa =", this.alfa);
        System.out.printf("%-20s%.2f%n", "k =", this.k);
        System.out.printf("%-20s%.2f%n", "L =", this.L);
        System.out.printf("%-20s%d%n", "Liczba wezlow =", this.liczbaWezlow);
        System.out.printf("%-20s%d%n", "Liczba elementów =", this.nE);
        System.out.printf("%-20s%.2f%n", "q =", this.q);
        System.out.printf("%-20s%.2f%n", "S =", this.S);
        System.out.printf("%-20s%.2f%n", "Temp otoczenia =", this.temp);
    }
}
