package pl.agh.mes;

import pl.agh.mes.structures.Element;
import pl.agh.mes.structures.GlobalData;
import pl.agh.mes.structures.Macierz;
import pl.agh.mes.structures.Node;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        GlobalData globalData = new GlobalData(25, 5, 2, 0.55, 100, 25, 103, 102);

        List<Node> nodeList = new ArrayList<>();
        nodeListFillWithGlobalData(globalData, nodeList);
        nodeList.forEach(it -> System.out.println(it.toString()));


        List<Element> elementList = new ArrayList<>();
        elementListFillWithNodeList(globalData, nodeList, elementList);
        elementList.forEach(it -> System.out.println(it.toString()));

    }

    private static void elementListFillWithNodeList(GlobalData globalData, List<Node> nodeList, List<Element> elementList) {
        for (int i = 0; i < globalData.getNE(); i++) {
            var elementLE = getLeForElement(i, nodeList)
            elementList.add(new Element(i, globalData.getK(),elementLE , obliczMacierz(globalData,elementLE)));
        }
    }

    private static void nodeListFillWithGlobalData(GlobalData globalData, List<Node> nodeList) {
        for (int i = 0; i < globalData.getNN(); i++) {
            if (i == 0) {
                nodeList.add(new Node(i, 0.00, 0, 1));
            } else if (i == globalData.getNN() - 1) {
                nodeList.add(new Node(i, 0.00, globalData.getL(), 2));
            } else
                nodeList.add(new Node(i, 0.00, getXForNode(globalData) * i, 0));
        }
    }

    private static double obliczMacierz(GlobalData globalData, double elementLE) {
        return (globalData.getS()* globalData.getK()) / elementLE;
    }
    private static double obliczWektor(int i, List<Node> nodeList) {
        return 0;
    }

    private static double getXForNode(GlobalData globalData) {
        return globalData.getL() / globalData.getNE();
    }


    private static double getLeForElement(int i, List<Node> nodeList) {
        return nodeList.get(i + 1).getX() - nodeList.get(i).getX();
    }
}
