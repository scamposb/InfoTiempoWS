package clientWS.common;

import clientWS.gui.PantallaPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Sistemas y Tecnologías Web. 2014-2015
 * Práctica 6
 *
 * @author Sandra Campos (629928)
 */
public class Main {

    private Vector<String> conversor (Map<String,String> e){
        Vector<String> resultado = new Vector<String>();
        List<String> lista = new ArrayList<String>(e.keySet());
        for(int i=0; i<lista.size();i++){
            resultado.add(lista.get(i));
        }
        return resultado;
    }

    public static void main (String [] args){
        /* Extraer del xml los municipios con ids */
        Map<String,String> municipios = Parser.extraerInfo();
        /* De ese, sacamos una lista con solo los municipios */
        Vector<String> nomMunicipios = new Main().conversor(municipios);
        /* Pasarle ambos al panel principal */
        new PantallaPrincipal(municipios,nomMunicipios).main(municipios,nomMunicipios);
    }

}
