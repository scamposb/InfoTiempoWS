package ServerWS;

import java.util.HashMap;
import java.util.Map;

public class Datos {

    private int dia;
    private Map<String,String> precipitaciones;
    private Map<String,String> nieve;
    private Map<String,String> temperatura;
    private Map<String,Viento> viento;
    private int uv;

    public Datos(int dia) {
        this.dia = dia;
        this.precipitaciones = new HashMap<String, String>();
        this.nieve = new HashMap<String, String>();
        this.temperatura = new HashMap<String, String>();
        this.viento = new HashMap<String, Viento>();
    }

    public Map<String, String> getPrecipitaciones() {
        return precipitaciones;
    }

    public void setPrecipitaciones(Map<String, String> precipitaciones) {
        this.precipitaciones = precipitaciones;
    }

    public Map<String, String> getNieve() {
        return nieve;
    }

    public void setNieve(Map<String, String> nieve) {
        this.nieve = nieve;
    }

    public Map<String, String> getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Map<String, String> temperatura) {
        this.temperatura = temperatura;
    }

    public Map<String, Viento> getViento() {
        return viento;
    }

    public void setViento(Map<String, Viento> viento) {
        this.viento = viento;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }
}
