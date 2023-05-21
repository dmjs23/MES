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
        System.out.printf("Alfa =" + this.alfa);
        System.out.printf("k =" + this.k);
        System.out.printf("L =" + this.L);
        System.out.printf("Liczba wezlow =" + this.liczbaWezlow);
        System.out.printf("Liczba elementów =" + this.nE);
        System.out.printf("q =" + this.q);
        System.out.printf("S =" + this.S);
        System.out.printf("Temp otoczenia =" + this.temp);
    }
}
