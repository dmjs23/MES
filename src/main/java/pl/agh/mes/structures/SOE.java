package pl.agh.mes.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SOE {
    private double HG;//nNxnN
    private double PG;//nNx1
    private double T;//nNx1
    //TODO funkcja rozwiązywania układu równań
}
