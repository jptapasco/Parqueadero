
package botones;

import VistaVendedor.VerUsuario;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class Controller_VerUsuario implements MouseListener {
    private final VerUsuario verusuario;

    
    public final void events(){
        verusuario.btnCancelar.addMouseListener(this);
    }
    
    public Controller_VerUsuario (VerUsuario verusuario){
        this.verusuario = verusuario;

        events();
    }
    

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
        Object evt = me.getSource();
        
        if(evt.equals(verusuario.btnCancelar)){
            changeBackground(verusuario.container_btn_volver_verusuario, new Color(61,103,71));
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        Object evt = me.getSource();
        
        if(evt.equals(verusuario.btnCancelar)){
            changeBackground(verusuario.container_btn_volver_verusuario, new Color(39,80,49));
        }
    }
    
    private void changeBackground(JPanel panel, Color color){
        panel.setBackground(color);
    }
}
