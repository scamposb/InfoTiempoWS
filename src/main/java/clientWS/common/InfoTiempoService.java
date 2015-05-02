package clientWS.common;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.utils.Options;
import javax.xml.namespace.QName;
/**
 * Sistemas y Tecnologías Web 2015
 * Practica 6.
 *
 * @author Sandra Campos (629928)
 */
public class InfoTiempoService {

    private final static String serverURL = "http://localhost:8080/axis/ServerWS/InfoTiempoServer";

    public static String descargarInfoTiempo(int municipio){
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            // Invocamos al servidor
            call.setTargetEndpointAddress(new java.net.URL(serverURL));
            // Le decimos al servidor que queremos utilizar el metodo descargarInfoTiempo
            call.setOperationName(new QName("ServicioInfoTiempo", "descargarInfoTiempo"));
            String res = (String) call.invoke(new Object[]{municipio});
            System.out.println(res);
            return res;
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(0);
            return null;
        }
    }

    public static String generarHTML(String URL){
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(serverURL));
            call.setOperationName(new QName("ServicioInfoTiempo", "generarHTML"));
            String res = (String) call.invoke(new Object[]{URL});
            return res;
        } catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }
    public static String generarJSON(String URL){
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(serverURL));
            call.setOperationName(new QName("ServicioInfoTiempo", "generarJSON"));
            String res = (String) call.invoke(new Object[]{URL});
            return res;
        } catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }
}
