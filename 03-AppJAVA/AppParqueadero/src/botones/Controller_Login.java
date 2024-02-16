
package botones;

import Main.Login;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class Controller_Login implements MouseListener {
    private final Login login;

    
    public final void events(){
        login.btn_ingresar.addMouseListener(this);
    }
    
    public Controller_Login (Login login){
        this.login = login;

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
        
        if(evt.equals(login.btn_ingresar)){
            changeBackground(login.container_btn_ingresar, new Color(61,103,71));
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        Object evt = me.getSource();
        
        if(evt.equals(login.btn_ingresar)){
            changeBackground(login.container_btn_ingresar, new Color(39,80,49));
        }
    }
    
    private void changeBackground(JPanel panel, Color color){
        panel.setBackground(color);
    }
}
