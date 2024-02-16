
package botones;

import Main.Main;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class Controller_Main implements MouseListener {
    private final Main main;

    
    public final void events(){
        main.btnParking.addMouseListener(this);
        main.btnSeller.addMouseListener(this);
        main.jButton1.addMouseListener(this);


    }
    
    public Controller_Main (Main main){
        this.main = main;

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
        
        if(evt.equals(main.btnParking)){
            changeBackground(main.container_btn_parqueaderos_admin, new Color(61,103,71));
        }else if(evt.equals(main.btnSeller)){
            changeBackground(main.container_btn_vendedores_admin, new Color(61,103,71));
        }else if(evt.equals(main.jButton1)){
            changeBackground(main.container_btn_cerrar, new Color(61,103,71));
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        Object evt = me.getSource();
        
        if(evt.equals(main.btnParking)){
            changeBackground(main.container_btn_parqueaderos_admin, new Color(39,80,49));
        }else if(evt.equals(main.btnSeller)){
            changeBackground(main.container_btn_vendedores_admin, new Color(39,80,49));
        }else if(evt.equals(main.jButton1)){
            changeBackground(main.container_btn_cerrar, new Color(39,80,49));
        }
    }
    
    private void changeBackground(JPanel panel, Color color){
        panel.setBackground(color);
    }
}
