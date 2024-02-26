
package botones;

import VistaVendedor.Vendedores;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class Controller_Vendedores implements MouseListener {
    private final Vendedores vendedores;

    
    public final void events(){
        vendedores.BtnConAsignar.addMouseListener(this);
        vendedores.btnBuscarSeller.addMouseListener(this);
        vendedores.BtnSinAsignar.addMouseListener(this);
        vendedores.jButton2.addMouseListener(this);
    }
    
    public Controller_Vendedores (Vendedores vendedores){
        this.vendedores = vendedores;

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
        
        if(evt.equals(vendedores.BtnConAsignar)){
            changeBackground(vendedores.container_btn_vca, new Color(61,103,71));
        }else if(evt.equals(vendedores.btnBuscarSeller)){
            changeBackground(vendedores.container_btn_buscar, new Color(61,103,71));
        }else if(evt.equals(vendedores.BtnSinAsignar)){
            changeBackground(vendedores.container_btn_vsa, new Color(61,103,71));
        }else if(evt.equals(vendedores.jButton2)){
            changeBackground(vendedores.container_btn_crearvendedor, new Color(61,103,71));
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        Object evt = me.getSource();
        
        if(evt.equals(vendedores.BtnConAsignar)){
            changeBackground(vendedores.container_btn_vca, new Color(39,80,49));
        }else if(evt.equals(vendedores.btnBuscarSeller)){
            changeBackground(vendedores.container_btn_buscar, new Color(39,80,49));
        }else if(evt.equals(vendedores.BtnSinAsignar)){
            changeBackground(vendedores.container_btn_vsa, new Color(39,80,49));
        }else if(evt.equals(vendedores.jButton2)){
            changeBackground(vendedores.container_btn_crearvendedor, new Color(39,80,49));
        }
    }
    
    private void changeBackground(JPanel panel, Color color){
        panel.setBackground(color);
    }
}
