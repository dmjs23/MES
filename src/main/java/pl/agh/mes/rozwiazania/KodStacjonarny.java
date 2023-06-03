package pl.agh.mes.rozwiazania;

import lombok.NoArgsConstructor;
import pl.agh.mes.structures.Element;
import pl.agh.mes.structures.GlobalData;
import pl.agh.mes.structures.Node;
import pl.agh.mes.structures.SOE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
public class KodStacjonarny {

    public void obliczStacjonarnie() {
        GlobalData data = loadGlobalData();
        data.showData();
        double elLength = getElLength(data);
        System.out.println("dlugosc elementu : " + elLength);
        List<Element> el = getElementList(data);
        calculateVectorPLocal(data, el);
        System.out.println("-------------------------");
        System.out.println("Wspolrzedne wezlow: [x]");
        System.out.println("-------------------------");
        List<Node> node = getNode(data, elLength);
        for (int i = 0; i < data.getLiczbaWezlow(); i++) {
            System.out.println(node.get(i).toString());
        }
        System.out.println("-------------------------");
        System.out.println("H lokalne dla kazdego elementu: P lokalne:");
        System.out.printf("\t\t\t%5s%8s%8s%8s%10s%10s%n", "[0][0]", "[0][1]", "[1][0]", "[1][1]", "[0]", "[1]");
        System.out.println("--------------------------------------------|---------------------");
        calculateHLocal(data, elLength, el);
        System.out.println("--------------------------------------------|---------------------");
        System.out.println("Tworze H GLobal");
        System.out.println("-------------------------");
        SOE soe = new SOE(data);
        System.out.println("--------------------------");
        System.out.println("Uzupełniam HGlobalne:");
        System.out.println("--------------------------");
        soe.calculateHGlobal(data, el);
        soe.showHGlobal(data);
        System.out.println("-------------------------");
        System.out.println("PGlobalne:");
        System.out.println("-------------------------");
        soe.calculatePGlobal(data, el);
        soe.showPGlobal(data);
        soe.rozkladMacierzyHGLobal(data);
        soe.T = soe.PG;
        soe.calculateTGlobal(data);
        System.out.println("-------------------------");
        System.out.println("TGlobalne:");
        System.out.println("-------------------------");
        soe.showTGlobal(data);
    }

    private static void calculateHLocal(GlobalData data, double elLength, List<Element> el) {
        for (int i = 0; i < data.getLiczbaWezlow() - 1; i++) {
            el.get(i).Hlocal[0][0] = ((data.getS() * data.getK()) / elLength);
            el.get(i).Hlocal[0][1] = -((data.getS() * data.getK()) / elLength);
            el.get(i).Hlocal[1][0] = -((data.getS() * data.getK()) / elLength);
            el.get(i).Hlocal[1][1] = ((data.getS() * data.getK()) / elLength);
            System.out.printf("Element: %d\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f%n",
                    (i + 1), el.get(i).Hlocal[0][0], el.get(i).Hlocal[0][1], el.get(i).Hlocal[1][0],
                    el.get(i).Hlocal[1][1], el.get(i).Plocal[0], el.get(i).Plocal[1]);
        }
    }

    private static List<Node> getNode(GlobalData data, double elLength) {
        List<Node> node = new ArrayList<>();
        for (int i = 0; i < data.getLiczbaWezlow(); i++) {
            node.add(new Node(i, data.getTemp(), i * elLength, 0));
        }
        return node;
    }

    private static void calculateVectorPLocal(GlobalData data, List<Element> el) {
        el.get(0).Plocal[0] = data.getQ() * data.getS();
        el.get(0).Plocal[1] = 0;
        el.get(data.getLiczbaWezlow() - 2).Plocal[0] = 0;
        el.get(data.getLiczbaWezlow() - 2).Plocal[1] = -data.getAlfa() * data.getTemp() * data.getS();
        for (int i = 1; i < data.getLiczbaWezlow() - 2; i++) {
            el.get(i).Plocal[0] = 0;
            el.get(i).Plocal[1] = 0;
        }
    }

    private static List<Element> getElementList(GlobalData data) {
        List<Element> el = new ArrayList<>();
        for (int i = 0; i < data.getLiczbaWezlow() - 1; i++) {
            el.add(new Element());
        }
        return el;
    }

    private static double getElLength(GlobalData data) {
        return data.getL() / (data.getLiczbaWezlow() - 1);
    }

    private static GlobalData loadGlobalData() {
        GlobalData data = new GlobalData();
        File file = new File("src/main/java/pl/agh/mes/daneStacjonarne.txt");
        try {
            Scanner scanner = new Scanner(file);
            data.setS(scanner.nextDouble());
            data.setK(scanner.nextDouble());
            data.setL(scanner.nextDouble());
            data.setAlfa(scanner.nextDouble());
            data.setTemp(scanner.nextDouble());
            data.setQ(scanner.nextDouble());
            data.setLiczbaWezlow(scanner.nextInt());
            data.setNE(scanner.nextInt());
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}


