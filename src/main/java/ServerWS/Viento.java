package ServerWS;

public class Viento {

    private String direccion;
    private String velocidad;

    public Viento(String direccion, String velocidad) {
        this.direccion = direccion;
        this.velocidad = velocidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public String toString() {
        return "Viento{" +
                "direccion='" + direccion + '\'' +
                ", velocidad='" + velocidad + '\'' +
                '}';
    }
}
