
package botones;

import Main.MainVendedor;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class Controller_MainVendedor implements MouseListener {
    private final MainVendedor mainvendedor;

    
    public final void events(){
        mainvendedor.btnParqueadero.addMouseListener(this);
        mainvendedor.btnEntrada.addMouseListener(this);
        mainvendedor.btnHistorial.addMouseListener(this);
        mainvendedor.btnTarifas.addMouseListener(this);
        mainvendedor.btnCerrarSesion.addMouseListener(this);
    }
    
    public Controller_MainVendedor (MainVendedor mainvendedor){
        this.mainvendedor = mainvendedor;

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
        
        if(evt.equals(mainvendedor.btnParqueadero)){
            changeBackground(mainvendedor.container_btn_parqueaderos_vendedor, new Color(61,103,71));
        }else if(evt.equals(mainvendedor.btnEntrada)){
            changeBackground(mainvendedor.container_btn_entrada_vendedor, new Color(61,103,71));
        }else if(evt.equals(mainvendedor.btnHistorial)){
            changeBackground(mainvendedor.container_btn_historial_vendedor, new Color(61,103,71));
        }else if(evt.equals(mainvendedor.btnTarifas)){
            changeBackground(mainvendedor.container_btn_tarifas_vendedor, new Color(61,103,71));
        }else if(evt.equals(mainvendedor.btnCerrarSesion)){
            changeBackground(mainvendedor.container_btn_cerrar_vendedor, new Color(61,103,71));
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        Object evt = me.getSource();
        
        if(evt.equals(mainvendedor.btnParqueadero)){
            changeBackground(mainvendedor.container_btn_parqueaderos_vendedor, new Color(39,80,49));
        }else if(evt.equals(mainvendedor.btnEntrada)){
            changeBackground(mainvendedor.container_btn_entrada_vendedor, new Color(39,80,49));
        }else if(evt.equals(mainvendedor.btnHistorial)){
            changeBackground(mainvendedor.container_btn_historial_vendedor, new Color(39,80,49));
        }else if(evt.equals(mainvendedor.btnTarifas)){
            changeBackground(mainvendedor.container_btn_tarifas_vendedor, new Color(39,80,49));
        }else if(evt.equals(mainvendedor.btnCerrarSesion)){
            changeBackground(mainvendedor.container_btn_cerrar_vendedor, new Color(39,80,49));
        }
    }
    
    private void changeBackground(JPanel panel, Color color){
        panel.setBackground(color);
    }
}
