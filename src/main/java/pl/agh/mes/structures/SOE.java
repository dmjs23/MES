package pl.agh.mes.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SOE {
    public double[][] HG;//nNxnN
    public double[] PG;//nNx1
    public double[] T;//nNx1

    public SOE(GlobalData data) {
        this.HG = new double[data.getLiczbaWezlow()][data.getLiczbaWezlow()];
        this.PG = new double[data.getLiczbaWezlow()];
        this.T = new double[data.getLiczbaWezlow()];
        for (int i = 0; i < data.getLiczbaWezlow(); ++i) {
            for (int j = 0; j < data.getLiczbaWezlow(); ++j) {
                this.HG[i][j] = 0;
                System.out.printf("%.2f\t", this.HG[i][j]);
            }
            System.out.println();
        }
    }

    public void calculateHGlobal(GlobalData data, List<Element> el) {
        for (int i = 0; i < data.getLiczbaWezlow() - 1; i++) {
            this.HG[i][i] += el.get(i).Hlocal[0][0];
            this.HG[i][i + 1] += el.get(i).Hlocal[0][1];
            this.HG[i + 1][i] += el.get(i).Hlocal[1][0];
            this.HG[i + 1][i + 1] += el.get(i).Hlocal[1][1];
        }
        this.HG[data.getLiczbaWezlow() - 1][data.getLiczbaWezlow() - 1] =
                (el.get(data.getLiczbaWezlow() - 2).Hlocal[0][0] + (data.getAlfa() * data.getS()));
    }

    public void calculatePGlobal(GlobalData data, List<Element> el) {
        for (int i = 0; i < data.getLiczbaWezlow(); i++) {
            this.PG[i] = 0;
        }
        for (int i = 0; i < data.getLiczbaWezlow() - 1; i++) {
            this.PG[i] += el.get(i).Plocal[0];
            this.PG[i + 1] += el.get(i).Plocal[1];
        }
    }

    public void calculateTGlobal(GlobalData data) {
        double s;
        for (int i = 1; i < data.getLiczbaWezlow(); i++) {
            s = 0;
            for (int j = 0; j < i; j++) {
                s += this.HG[i][j] * this.T[j];
            }
            this.T[i] = this.PG[i] - s;
        }
        this.T[data.getLiczbaWezlow() - 1] = this.T[data.getLiczbaWezlow() - 1] / (this.HG[data.getLiczbaWezlow() - 1][data.getLiczbaWezlow() - 1]);
        for (int i = data.getLiczbaWezlow() - 2; i >= 0; i--) {
            s = 0;
            for (int j = i + 1; j < data.getLiczbaWezlow(); j++) {
                s += this.HG[i][j] * this.T[j];
            }
            this.T[i] = (this.T[i] - s) / this.HG[i][i];
        }
    }

    public void rozkladMacierzyHGLobal(GlobalData data) {
        for (int k = 0; k < data.getLiczbaWezlow(); k++) {
            for (int i = k + 1; i < data.getLiczbaWezlow(); i++) {
                this.HG[i][k] /= this.HG[k][k];
            }
            for (int i = k + 1; i < data.getLiczbaWezlow(); i++) {
                for (int j = k + 1; j < data.getLiczbaWezlow(); j++) {
                    this.HG[i][j] -= this.HG[i][k] * this.HG[k][j];
                }
            }
        }
    }

    public void showHGlobal(GlobalData data){
        for (int i = 0; i < data.getLiczbaWezlow(); i++) {
            for (int j = 0; j < data.getLiczbaWezlow(); j++) {
                System.out.printf("%.2f\t", this.HG[i][j]);
            }
            System.out.println();
        }
    }
    public void showTGlobal(GlobalData data){
        for (int i = 0; i < data.getLiczbaWezlow(); i++) {
            System.out.print(this.T[i]*-1 +" ");
        }
    }
    public void showPGlobal(GlobalData data){
        for (int i = 0; i < data.getLiczbaWezlow(); i++) {
            System.out.print(this.PG[i]);
        }
        System.out.println();
    }
}
