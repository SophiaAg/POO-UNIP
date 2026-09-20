package br.unip.sicc.exercicio.view.view;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class DestacaFocoText implements FocusListener{

    @Override
    public void focusGained(FocusEvent event) {
    	trocaCor(event, Color.YELLOW);
    }

    @Override
    public void focusLost(FocusEvent event) {
    	trocaCor(event, Color.WHITE);
    }
    

    public void trocaCor(FocusEvent event, Color cor) {
        Object source = event.getSource();
        if(source instanceof JTextField){
        	JTextField jTextField= (JTextField) source;
        	jTextField.setBackground(cor);
        }
    }
    
    
}
