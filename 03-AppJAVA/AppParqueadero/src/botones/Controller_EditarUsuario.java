
package botones;

import VistaVendedor.EditarUsuario;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class Controller_EditarUsuario implements MouseListener {
    private final EditarUsuario editarusuario;

    
    public final void events(){
        editarusuario.btnCancelar.addMouseListener(this);
        editarusuario.jButton1.addMouseListener(this);

    }
    
    public Controller_EditarUsuario (EditarUsuario editarusuario){
        this.editarusuario = editarusuario;

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
        
        if(evt.equals(editarusuario.btnCancelar)){
            changeBackground(editarusuario.container_btn_volver, new Color(153,153,153));
        }else if(evt.equals(editarusuario.jButton1)){
            changeBackground(editarusuario.container_btn_editar, new Color(61,103,71));
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        Object evt = me.getSource();
        
        if(evt.equals(editarusuario.btnCancelar)){
            changeBackground(editarusuario.container_btn_volver, new Color(102,102,102));
        }else if(evt.equals(editarusuario.jButton1)){
            changeBackground(editarusuario.container_btn_editar, new Color(39,80,49));
        }
    }
    
    private void changeBackground(JPanel panel, Color color){
        panel.setBackground(color);
    }
}
