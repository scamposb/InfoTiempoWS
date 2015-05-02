package clientWS.common;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Sistemas y Tecnologías Web. 2014-2015
 * Práctica 6
 *
 * @author Sandra Campos (629928)
 */
public class Parser {

    private static final String fichero = "src\\main\\resources\\codigoMunicipios.xml";

    /**
     * Extraera del fichero xml un par de elementos <municipio,idMunicipio>
     * @return estructura donde se almacenara cada par de datos
     */
    public static Map<String,String> extraerInfo(){
        SortedMap<String,String> info = new TreeMap<String, String>();

        try {
            // Utilizamos el parser XML Xerces con validacion de XML activada
            SAXBuilder constructor = new SAXBuilder(false);
            // Construimos el documento con el arbol XML a partir del archivo
            // XML
            // pasado como argumento
            constructor.setFeature(
                    "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            Document doc = constructor.build(fichero);
            // Comprobamos que el archivo sea del tipo departamentos
            // leyendo el elemento raiz
            Element raiz = doc.getRootElement();
            List<Element> elementos = raiz.getChildren();
            /* Empezamos en 3 para que no coja la informacion de inicio que contiene el XML */
            for(int i=3; i<elementos.size();i++){
                Element e = elementos.get(i);
                Element row = e.getChild("Row");
                String codigo = row.getAttributeValue("A")+row.getAttributeValue("B");
                info.put(row.getAttributeValue("D"), codigo);
            }
        }catch(Exception e){
            System.out.println("Error durante el parseo");
            e.printStackTrace();
        }

        return info;
    }
}
