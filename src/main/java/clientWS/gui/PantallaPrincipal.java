package clientWS.gui;

import clientWS.common.InfoTiempoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Sistemas y Tecnologías Web. 2014-2015
 * Práctica 6
 *
 * @author Sandra Campos (629928)
 */
public class PantallaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private Map<String,String> municipios;
    private Vector<String> nomMunicipios;
    private InfoTiempoService is;

    public PantallaPrincipal(Map<String,String> municipios, Vector<String> nomMunicipios) {
        this.municipios = municipios;
        this.nomMunicipios = nomMunicipios;
        this.is = new InfoTiempoService();
    }

    /**
     * Devuelve el frame completo de la aplicacion
     */
    public PantallaPrincipal getPrincipal() {
        return this;
    }

    private JScrollPane presentarResultado(String texto, String mime){
        JEditorPane editorPane = new JEditorPane("text/"+mime,texto);
        editorPane.setEditable(false);
        //Put the editor pane in a scroll pane.
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        setVisible(true);
        return editorScrollPane;
    }

    public void distribute() {
        this.setTitle("El tiempo");
        this.setLayout(new GridLayout(2,1));
        final PanelOpciones po = new PanelOpciones(nomMunicipios);
        this.add(po);
        PanelBotones pb = new PanelBotones();
        pb.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(pb);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Configuración de los listeners
        // BOTON EXTRAER EN HTML
        pb.getButton("HTML").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String municipio = (String) po.getLista().getSelectedItem();
                //invocamos primero al xml para que podamos utilizarlo para el HTML
                String xml = is.descargarInfoTiempo(Integer.parseInt(municipios.get(municipio)));
                String contenido = is.generarHTML(xml);
                System.out.println();
                JDialog nueva = new JDialog(getPrincipal(), true);
                nueva.add(presentarResultado(xml,"html"));
                nueva.setSize(1000,500);
                nueva.setLocationRelativeTo(null);
                nueva.setVisible(true);
            }
        });
        // BOTON EXTRAER EN JSON
        pb.getButton("JSON").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String municipio = (String) po.getLista().getSelectedItem();
                String xml = is.descargarInfoTiempo(Integer.parseInt(municipios.get(municipio)));
                String contenido = is.generarJSON(xml);
                JDialog nueva = new JDialog(getPrincipal(), true);
                nueva.add(presentarResultado(contenido,"json"));
                nueva.setSize(500,500);
                nueva.setLocationRelativeTo(null);
                nueva.setVisible(true);
            }
        });
    }

    public static void main(Map<String,String> municipios, Vector<String> nomMunicipios) {
        PantallaPrincipal pp = new PantallaPrincipal(municipios,nomMunicipios);
        pp.distribute();
    }
}

