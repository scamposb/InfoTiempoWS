package ServerWS;

import com.google.gson.GsonBuilder;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Sistemas y Tecnologías Web. 2014-2015
 * Práctica 6
 *
 * @author Sandra Campos (629928)
 */
public class Buscador {

    private int fecha;
    private Datos[] informacion;
    private String ciudad;

    private void extraerDatos(String URL){
        try {
            // Utilizamos el parser XML Xerces con validacion de XML activada
            SAXBuilder constructor = new SAXBuilder(false);
            // Construimos el documento con el arbol XML a partir del archivo
            // XML
            // pasado como argumento
            constructor.setFeature(
                    "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            Document doc = constructor.build(new URL(URL));
                    //Document doc = constructor.build("src\\main\\data\\prediccion.xml");
                    // Comprobamos que el archivo sea del tipo departamentos
                    // leyendo el elemento raiz
                    Element raiz = doc.getRootElement();
            if (raiz.getName().equals("root")) {
                fecha = extraerDia(raiz.getChild("elaborado").getValue());
                ciudad = raiz.getChild("nombre").getValue();
                /* Inicializamos la estructura encargada de almacenar la informacion */
                inicializarInformacion(fecha);

                List<Element> listaDias = raiz.getChild("prediccion").getChildren();
                for(int i=0;i<listaDias.size();i++){
                    Element infoDia = listaDias.get(i);
                    List<Element> listaInfo = infoDia.getChildren();
                    /*Ahora que tenemos toda la informacion de un dia concreto,
                    vamos a desgranarla */
                    for(Element e : listaInfo){
                        if(e.getName().equals("prob_precipitacion")){
                            String valor = e.getValue();
                            if(valor.equals("")) valor = "0";
                            informacion[i].getPrecipitaciones().put(e.getAttributeValue("periodo"),valor);

                        }
                        else if(e.getName().equals("cota_nieve_prov")){
                            informacion[i].getNieve().put(e.getAttributeValue("periodo"),e.getValue());
                        }
                        else if(e.getName().equals("temperatura")){
                            informacion[i].getTemperatura().put("maxima",e.getChild("maxima").getValue());
                            informacion[i].getTemperatura().put("minima",e.getChild("minima").getValue());
                        }
                        else if(e.getName().equals("viento")){
                            informacion[i].getViento().put(e.getAttributeValue("periodo"),
                                    new Viento(e.getChild("direccion").getValue(),e.getChild("velocidad").getValue()));
                        }
                        else if(e.getName().equals("uv_max")){
                            informacion[i].setUv(Integer.parseInt(e.getValue()));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String crearHTML()throws IOException {

        String contenido ="<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "<style>\n" +
                "table,th, td {\n" +
                "    border: 1px solid black;\n" +
                "    border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "    padding: 5px;\n" +
                "    text-align: left;    \n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<h2>Prediccion del tiempo para "+ciudad+"</h2>\n" +
                "<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <th rowspan = \"2\">Fecha</th>\n" +
                "    <th colspan=\"2\">Dia "+fecha+"</th>\n" +
                "    <th colspan=\"4\">Dia "+(fecha+1)+"</th>\n" +
                "\t<th colspan=\"2\">Dia "+(fecha+2)+"</th>\n" +
                "\t<th COLSPAN=2>Dia "+(fecha+3)+"</th>\n" +
                "\t<th rowspan = \"2\" colspan=\"2\">Dia "+(fecha+4)+"</th>\n" +
                "\t<th rowspan = \"2\" colspan=\"2\">Dia "+(fecha+5)+"</th>\n" +
                "\t<th rowspan = \"2\" colspan=\"2\">Dia "+(fecha+6)+"</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "\t<td>00-12</td>\n" +
                "\t<td>12-24</td>\n" +
                "\t<td>00-06</td>\n" +
                "    <td>06-12</td>\n" +
                "    <td>12-18</td>\n" +
                "    <td>18-24</td>\n" +
                "\t<td>00-12</td>\n" +
                "\t<td>12-24</td>\n" +
                "\t<td>00-12</td>\n" +
                "\t<td>12-24</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "\t<td>Prob. Precipitacion</td>\n" +
                "\t<td>"+informacion[0].getPrecipitaciones().get("00-12")+"%</td>\n" +
                "\t<td>"+informacion[0].getPrecipitaciones().get("12-24")+"%</td>\n" +
                "\t<td>"+informacion[1].getPrecipitaciones().get("00-06")+"%</td>\n" +
                "\t<td>"+informacion[1].getPrecipitaciones().get("06-12")+"%</td>\n" +
                "\t<td>"+informacion[1].getPrecipitaciones().get("12-18")+"%</td>\n" +
                "\t<td>"+informacion[1].getPrecipitaciones().get("18-24")+"%</td>\n" +
                "\t<td>"+informacion[2].getPrecipitaciones().get("00-12")+"%</td>\n" +
                "\t<td>"+informacion[2].getPrecipitaciones().get("12-24")+"%</td>\n" +
                "\t<td>"+informacion[3].getPrecipitaciones().get("00-12")+"%</td>\n" +
                "\t<td>"+informacion[3].getPrecipitaciones().get("12-24")+"%</td>\n" +
                "\t<td colspan=\"2\">"+informacion[4].getPrecipitaciones().get(null)+"%</td>\n" +
                "\t<td colspan=\"2\">"+informacion[5].getPrecipitaciones().get(null)+"%</td>\n" +
                "\t<td colspan=\"2\">"+informacion[6].getPrecipitaciones().get(null)+"%</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "\t<td>Cota de nieve</td>\n" +
                "\t<td>"+informacion[0].getNieve().get("00-12")+"</td>\n" +
                "\t<td>"+informacion[0].getNieve().get("12-24")+"</td>\n" +
                "\t<td>"+informacion[1].getNieve().get("00-06")+"</td>\n" +
                "\t<td>"+informacion[1].getNieve().get("06-12")+"</td>\n" +
                "\t<td>"+informacion[1].getNieve().get("12-18")+"</td>\n" +
                "\t<td>"+informacion[1].getNieve().get("18-24")+"</td>\n" +
                "\t<td>"+informacion[2].getNieve().get("00-12")+"</td>\n" +
                "\t<td>"+informacion[2].getNieve().get("12-24")+"</td>\n" +
                "\t<td>"+informacion[3].getNieve().get("00-12")+"</td>\n" +
                "\t<td>"+informacion[3].getNieve().get("12-24")+"</td>\n" +
                "\t<td colspan=\"2\">"+informacion[4].getNieve().get(null)+"</td>\n" +
                "\t<td colspan=\"2\">"+informacion[5].getNieve().get(null)+"</td>\n" +
                "\t<td colspan=\"2\">"+informacion[6].getNieve().get(null)+"</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "\t<td>Temp. min/max</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[0].getTemperatura().get("minima")+"/"
                +informacion[0].getTemperatura().get("maxima")+"</td>\n" +
                "\t<td align=\"center\" colspan=\"4\">"+informacion[1].getTemperatura().get("minima")+"/"
                +informacion[1].getTemperatura().get("maxima")+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[2].getTemperatura().get("minima")+"/"
                +informacion[2].getTemperatura().get("maxima")+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[3].getTemperatura().get("minima")+"/"
                +informacion[3].getTemperatura().get("maxima")+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[4].getTemperatura().get("minima")+"/"
                +informacion[4].getTemperatura().get("maxima")+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[5].getTemperatura().get("minima")+"/"
                +informacion[5].getTemperatura().get("maxima")+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[6].getTemperatura().get("minima")+"/"
                +informacion[6].getTemperatura().get("maxima")+"</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "\t<td>Viento (km/h)</td>\n" +
                "\t<td>"+informacion[0].getViento().get("00-12").getDireccion()+informacion[0].getViento().
                get("00-12").getVelocidad()+"</td>\n" +
                "\t<td>"+informacion[0].getViento().get("12-24").getDireccion()+informacion[0].getViento().
                get("12-24").getVelocidad()+"</td>\n" +
                "\t<td>"+informacion[1].getViento().get("00-06").getDireccion()+informacion[1].getViento().
                get("00-06").getVelocidad()+"</td>\n" +
                "\t<td>"+informacion[1].getViento().get("06-12").getDireccion()+informacion[1].getViento().
                get("06-12").getVelocidad()+"</td>\n" +
                "\t<td>"+informacion[1].getViento().get("12-18").getDireccion()+informacion[1].getViento().
                get("12-18").getVelocidad()+"</td>\n" +
                "\t<td>"+informacion[1].getViento().get("18-24").getDireccion()+informacion[1].getViento().
                get("18-24").getVelocidad()+"</td>\n" +
                "\t<td>"+informacion[2].getViento().get("00-12").getDireccion()+informacion[2].getViento().
                get("00-12").getVelocidad()+"</td>\n" +
                "\t<td>"+informacion[2].getViento().get("12-24").getDireccion()+informacion[2].getViento().
                get("12-24").getVelocidad()+"</td>\n" +
                "\t<td>"+informacion[3].getViento().get("00-12").getDireccion()+informacion[3].getViento().
                get("00-12").getVelocidad()+"</td>\n" +
                "\t<td>"+informacion[3].getViento().get("12-24").getDireccion()+informacion[3].getViento().
                get("12-24").getVelocidad()+"</td>\n" +
                "\t<td colspan=\"2\">"+informacion[4].getViento().get(null).getDireccion()+informacion[4].getViento().
                get(null).getVelocidad()+"</td>\n" +
                "\t<td colspan=\"2\">"+informacion[5].getViento().get(null).getDireccion()+informacion[5].getViento().
                get(null).getVelocidad()+"</td>\n" +
                "\t<td colspan=\"2\">"+informacion[6].getViento().get(null).getDireccion()+informacion[6].getViento().
                get(null).getVelocidad()+"</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "\t<td>Indice UV maximo</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[0].getUv()+"</td>\n" +
                "\t<td align=\"center\" colspan=\"4\">"+informacion[1].getUv()+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[2].getUv()+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[3].getUv()+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[4].getUv()+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[5].getUv()+"</td>\n" +
                "\t<td align=\"center\" colspan=\"2\">"+informacion[6].getUv()+"</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>";
        return contenido;
    }
    private int extraerDia(String fechaCompleta){
        StringTokenizer st = new StringTokenizer(fechaCompleta,"T");
        String fecha = st.nextToken();
        StringTokenizer st2 = new StringTokenizer(fecha,"-");
        st2.nextToken(); st2.nextToken();
        String dia = st2.nextToken();
        return Integer.parseInt(dia);
    }

    private void inicializarInformacion(int dia){
        informacion = new Datos[7];
        for(int i=0;i<informacion.length;i++){
            informacion[i]=new Datos(i);
            dia++;
        }
    }

    private String crearJSON(){
        return new GsonBuilder().setPrettyPrinting().create().toJson(informacion);

    }
    public String generarInfo (String tipo, String URL){
        String result = null;
        try{
            Buscador b = new Buscador();
            b.extraerDatos(URL);
            if(tipo.equals("HTML")) result = b.crearHTML();
            else result = b.crearJSON();
            return result;

        }catch(Exception e){return "Ha habido un error"+e.getStackTrace();}
    }
}
