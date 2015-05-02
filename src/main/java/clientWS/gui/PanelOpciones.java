package clientWS.gui;

import clientWS.common.Parser;

import javax.swing.*;
import java.util.Vector;

/**
 * Sistemas y Tecnolog�as Web. 2014-2015
 * Pr�ctica 6
 *
 * @author Sandra Campos (629928)
 */
public class PanelOpciones extends JPanel{

    JComboBox lista;

    public PanelOpciones(Vector<String> nomMunicipios){
        lista = new JComboBox(nomMunicipios);
        lista.setSelectedIndex(0);
        this.add(lista);
    }

    public JComboBox getLista(){
        return lista;
    }
}
