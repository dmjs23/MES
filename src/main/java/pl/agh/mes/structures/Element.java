package pl.agh.mes.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Element {

//    private int id;
//    private double K; //współczynnik przewodzenia ciepła
    //TODO id[] //E1-N1, N2-id[1,2]
//    private double Le; //długość elementu
    public double[] Plocal = new double[2];
    public double[][] Hlocal = new double[2][2];

}
