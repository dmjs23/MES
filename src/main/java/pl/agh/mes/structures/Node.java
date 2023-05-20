package pl.agh.mes.structures;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Node {

    private int id;
    private double t; //temperatura na węźle(wynikowa
    private double x; //obliczyć L zamiast x-dla elementu
    private int BC; //warunki brzegowe, 1-konwekcja, 2-strumień ciepła

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", t=" + t +
                ", x=" + x +
                ", BC=" + BC +
                '}';
    }
}
