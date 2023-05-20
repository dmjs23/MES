package pl.agh.mes.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GlobalData {

    private double Tot; //temperatura otoczenia
    private double alfa; //warunek brzegowy konwe
    private double q; //gęstość strumienia
    private double K; //waspółczynnik przewodzenia ciepła
    private double L; //długość pręta
    private double S; //pole przekroju pręta
    private int nN; // liczba węzłów siatki MES
    private int nE; //liczba elenemtów satki MES
}
