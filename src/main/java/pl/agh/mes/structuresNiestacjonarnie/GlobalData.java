package pl.agh.mes.structuresNiestacjonarnie;

public class GlobalData {
    public int nH;
    public int nE;
    public int nP;
    public double[] w;
    public double[] e;
    public double[] n1;
    public double[] n2;
    public double dTau;
    public double tauMax;
    public double tempBeginning;
    public double tempAir;
    public double c;
    public double alfaAir;
    public double ro;
    public double k;
    public double r;

    public GlobalData() {
        r = 0.08;
        nH = 9;
        nE = 8;
        nP = 2;
        w = new double[]{1, 1};
        e = new double[]{-0.5773502692, 0.5773502692};
        n1 = new double[]{0.5 * (1 - e[0]), 0.5 * (1 - e[1])};
        n2 = new double[]{0.5 * (1 + e[0]), 0.5 * (1 + e[1])};
        dTau = 10;
        tauMax = 1000;
        tempBeginning = 100;
        tempAir = 1200;
        c = 700;
        alfaAir = 300;
        ro = 7800;
        k = 25;
    }
}
