package pl.agh.mes.rozwiazania;

import lombok.NoArgsConstructor;
import pl.agh.mes.structuresNiestacjonarnie.*;

import static pl.agh.mes.structuresNiestacjonarnie.HeatTransferSimulation.gaussElimination;

@NoArgsConstructor
public class KodNiestacjonarny {

    public void  obliczNiestacjonarnie(){

        GlobalData globalData = new GlobalData();

        Nod[] nodes = HeatTransferSimulation.generateNodes(globalData);

        double[] res = new double[0];
        for (int tau = 0; tau < 1; tau++) {
            System.out.println("Wypisz wszystkie węzły dla tau = "+tau);
            for (Nod node : nodes) {
                System.out.println(node);
            }
            System.out.println("-------------------------");
            Elem[] elements = HeatTransferSimulation.generateElements(globalData, nodes);
            System.out.println("Wypisz wszystkie elementy");
            for (Elem elem : elements) {
                System.out.println(elem);
            }
            System.out.println("-------------------------");
            SOE soe = HeatTransferSimulation.aggregateSOE(elements, nodes);
            double[] result = gaussElimination(soe.hg, soe.pg);
            for (int i = 0; i < nodes.length; i++) {
                nodes[i].temp = result[i];
            }
            res = result;
        }

        for (double re : res) {
            System.out.println(re);
        }
        System.out.println();
    }
}
