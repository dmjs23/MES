package pl.agh.mes.structuresNiestacjonarnie;

public class HeatTransferSimulation {

    public static Nod[] generateNodes(GlobalData globalData) {
        Nod[] nodes = new Nod[globalData.nH];
        for (int i = 0; i < globalData.nH; i++) {
            BCond cond = (i == globalData.nH - 1) ? BCond.CONVECTION : BCond.NONE;
            Nod node = new Nod();
            node.temp = globalData.tempBeginning;
            node.x = (globalData.r / (globalData.nH - 1)) * i;
            node.bCond = cond;
            nodes[i] = node;
        }
        return nodes;
    }

    public static Elem[] generateElements(GlobalData globalData, Nod[] nodes) {
        Elem[] elements = new Elem[globalData.nE];
        for (int i = 0; i < globalData.nE; i++) {
            double[] p = new double[2];
            double[][] h = {{0, 0}, {0, 0}};
            Nod nStart = nodes[i];
            Nod nEnd = nodes[i + 1];
            double[][] dN = {{1, -1}, {-1, 1}};
            double elemLength = Math.abs(nEnd.x - nStart.x);

            for (int j = 0; j < globalData.w.length; j++) {
                double xPos = globalData.n1[j] * nStart.x + globalData.n2[j] * nEnd.x;
                double temp = globalData.n1[j] * nStart.temp + globalData.n2[j] * nEnd.temp;
                double[][] tempVecs = {
                        {globalData.n1[j] * globalData.n1[j], globalData.n1[j] * globalData.n2[j]},
                        {globalData.n2[j] * globalData.n1[j], globalData.n2[j] * globalData.n2[j]}
                };

                calculateH(h, dN, globalData.k, xPos, globalData.w[j], elemLength, globalData.c, globalData.ro, globalData.dTau, tempVecs);
                calculateP(p, globalData.n1[j], globalData.n2[j], globalData.c, globalData.ro, elemLength, xPos, temp, globalData.w[j], globalData.dTau);

                if (nEnd.bCond == BCond.CONVECTION) {
                    applyConvectionBoundary(h, p, globalData.alfaAir, globalData.r, globalData.tempAir);
                }
            }
            Elem element = new Elem();
            element.k = globalData.k;
            element.ids = new int[]{i, i + 1};
            element.le = elemLength;
            element.h = h;
            element.p = p;

            elements[i] = element;
        }
        return elements;
    }

    private static void calculateH(double[][] h, double[][] dN, double kValue, double xPos, double w, double elemLength,
                                   double c, double ro, double dTau, double[][] tempVecs) {
        for (int i = 0; i < tempVecs.length; i++) {
            for (int j = 0; j < tempVecs[i].length; j++) {
                h[i][j] += (dN[i][j] * kValue * xPos * w) / elemLength;
                h[i][j] += (c * ro * xPos * elemLength * tempVecs[i][j]) / dTau;
            }
        }
    }

    private static void calculateP(double[] p, double n1, double n2, double c, double ro, double elemLength, double xPos,
                                   double temp, double w, double dTau) {
        double[] temps = {n1, n2};
        double tempCoef = c * ro * elemLength * w;

        for (int k = 0; k < p.length; k++) {
            p[k] += (temps[k] * tempCoef * xPos * temp) / dTau;
        }
    }

    private static void applyConvectionBoundary(double[][] h, double[] p, double alfaAir, double r, double tempAir) {
        h[1][1] += alfaAir * r;
        p[1] += alfaAir * tempAir * r;
    }

    public static SOE aggregateSOE(Elem[] elements, Nod[] nodes) {
        int numNodes = nodes.length;
        double[][] hg = new double[numNodes][numNodes];
        double[] pg = new double[numNodes];
        double[] tg = new double[numNodes];

        for (Elem element : elements) {
            int i = element.ids[0];
            int j = element.ids[1];
            hg[i][i] += element.h[0][0];
            hg[i][j] += element.h[0][1];
            hg[j][i] += element.h[1][0];
            hg[j][j] += element.h[1][1];

            pg[i] += element.p[0];
            pg[j] += element.p[1];
        }

        SOE soe = new SOE();
        soe.hg = hg;
        soe.pg = pg;
        soe.tg = tg;
        viewData(numNodes, hg, pg, tg);
        return soe;
    }

    private static void viewData(int numNodes, double[][] hg, double[] pg, double[] tg) {
        System.out.println("Macierz HG:");
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                System.out.printf("%.15f | ", hg[i][j]);
            }
            System.out.println();
        }

        System.out.println("-------------------------");
        System.out.println("Wektor PG:");
        for (int i = 0; i < numNodes; i++) {
            System.out.print(pg[i] + " | ");
        }
        System.out.println("\n-------------------------");

        System.out.println("Wektor TG:");
        for (int i = 0; i < numNodes; i++) {
            System.out.print(tg[i] + " | ");
        }
        System.out.println("\n-------------------------");
    }

    public static double[] gaussElimination(double[][] A, double[] b) {
        int n = A.length;
        double[] x = new double[n];

        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double wsp = A[i][k] / A[k][k];
                for (int j = k + 1; j < n; j++) {
                    A[i][j] = A[i][j] - wsp * A[k][j];
                }
                b[i] = b[i] - wsp * b[k];
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            double s = 0;
            for (int j = i + 1; j < n; j++) {
                s += A[i][j] * x[j];
            }
            x[i] = (b[i] - s) / A[i][i];
        }

        System.out.println("-----------------eliminacji Gaussa-------------");
        System.out.println("Macierz A:");
        for (double[] doubles : A) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%.15f | ", doubles[j]);
            }
            System.out.println();
        }

        System.out.println("\nWektor b:");
        for (int i = 0; i < n; i++) {
            System.out.println(b[i]);
        }
        System.out.println("---------------------------------");
        return x;
    }
}
