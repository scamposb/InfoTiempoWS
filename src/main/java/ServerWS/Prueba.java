package ServerWS;

import javax.swing.*;
import java.awt.*;

/**
 * Sistemas y Tecnologías Web. 2015.
 * Práctica 6.
 *
 * @author Sandra Campos (629928)
 */
public class Prueba extends JFrame{

    private void presentarResultado(String texto){
        JEditorPane editorPane = new JEditorPane("text/xml",texto);
        editorPane.setEditable(false);
        //Put the editor pane in a scroll pane.
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        this.add(editorScrollPane);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main (String [] args){
        new Prueba().presentarResultado("fsdgsghsd");
    }
}
