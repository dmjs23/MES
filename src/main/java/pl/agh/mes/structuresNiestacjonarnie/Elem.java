package pl.agh.mes.structuresNiestacjonarnie;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Elem {
    public double k;
    public int[] ids;
    public double le;
    public double[][] h;
    public double[] p;

}
