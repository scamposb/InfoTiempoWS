package ServerWS;

/**
 * Sistemas y Tecnologías Web 2015
 * Practica 6.
 *
 * @author Sandra Campos (629928)
 */
public class InfoTiempoServer {
    public static String descargarInfoTiempo(int municipio){
        return "http://www.aemet.es/xml/municipios/localidad_"+municipio+".xml";
    }

    public static String generarHTML(String URL){
        return new Buscador().generarInfo("HTML",URL);
    }
    public static String generarJSON(String URL){
        return new Buscador().generarInfo("JSON",URL);
    }
}
