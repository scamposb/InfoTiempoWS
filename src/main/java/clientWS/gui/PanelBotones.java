package clientWS.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Sistemas y Tecnologías Web. 2014-2015
 * Práctica 6
 *
 * @author Sandra Campos (629928)
 */
public class PanelBotones extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton HTML,JSON;

    public PanelBotones(){
        this.setLayout(new GridLayout(1,2));
        HTML=new JButton("Extraer en HTML"); this.add(HTML);
        JSON=new JButton("Extraer en JSON"); this.add(JSON);
    }

    public JButton getButton(String opcion){
        if(opcion.equals("HTML")) return HTML;
        else return JSON;
    }
}
