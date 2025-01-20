package ambiente;

import java.util.Date;

public class Medida {
    private String numSensor;
    private int medida;
    private Date dataHora;

    public Medida(String numSensor, Date dataHora, int medida) {
        this.numSensor = numSensor;
        this.medida = medida;
        this.dataHora = dataHora;
    }

    public String getNumSensor() {
        return numSensor;
    }

    public void setNumSensor(String numSensor) {
        this.numSensor = numSensor;
    }

    public int getMedida() {
        return medida;
    }

    public void setMedida(int medida) {
        this.medida = medida;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Medida{" +
                "numSensor='" + numSensor + '\'' +
                ", medida=" + medida +
                ", dataHora=" + dataHora +
                '}';
    }
}
