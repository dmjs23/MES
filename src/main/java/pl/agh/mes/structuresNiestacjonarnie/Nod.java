package pl.agh.mes.structuresNiestacjonarnie;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Nod {
    public double temp;
    public double x;
    public BCond bCond;

    @Override
    public String toString() {
        return "Nod{" +
                "temp=" + temp +
                ", x=" + x +
                ", bCond=" + bCond +
                '}';
    }
}
