package pl.agh.mes.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Element {

    private int id;
    private double K; //współczynnik przewodzenia ciepła
    //TODO id[] //E1-N1, N2-id[1,2]
    private double Le; //długość elementu
    double H; //lokalna macież
  //TODO  Wektor P; //lokalne


    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", K=" + K +
                ", Le=" + Le +
                '}';
    }
}
