
package botones;

import VistaVendedor.CreateSeller;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class Controller_CrearUsuario implements MouseListener {
    private final CreateSeller createseller;

    
    public final void events(){
        createseller.btnCancelar.addMouseListener(this);
        createseller.btnCrear.addMouseListener(this);

    }
    
    public Controller_CrearUsuario (CreateSeller createseller){
        this.createseller = createseller;

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
        
        if(evt.equals(createseller.btnCancelar)){
            changeBackground(createseller.container_btn_volver, new Color(153,153,153));
        }else if(evt.equals(createseller.btnCrear)){
            changeBackground(createseller.container_btn_crear, new Color(61,103,71));
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        Object evt = me.getSource();
        
        if(evt.equals(createseller.btnCancelar)){
            changeBackground(createseller.container_btn_volver, new Color(102,102,102));
        }else if(evt.equals(createseller.btnCrear)){
            changeBackground(createseller.container_btn_crear, new Color(39,80,49));
        }
    }
    
    private void changeBackground(JPanel panel, Color color){
        panel.setBackground(color);
    }
}
