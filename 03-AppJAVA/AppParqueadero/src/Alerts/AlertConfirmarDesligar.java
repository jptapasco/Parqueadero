
package Alerts;

import Main.ConsumoApi;
import Main.Main;
import VistaVendedor.Vendedores;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class AlertConfirmarDesligar extends javax.swing.JFrame {

    private String cedula;
    Vendedores contentVendedores;
    ConsumoApi consumo;
    Gson gson;
    
    public AlertConfirmarDesligar(String cedula,Vendedores contentVendedores) {
        this.cedula = cedula;
        this.contentVendedores = contentVendedores;
        consumo = new ConsumoApi();
        gson = new Gson();
        initComponents();
        centrarPantalla();
        Image icono = getToolkit().createImage( ClassLoader.getSystemResource("Img/tractor.png") );
        setIconImage(icono);
        setTitle("Parking");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        container_btn_volver2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        container_btn_volver3 = new javax.swing.JPanel();
        btnDesligar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(61, 103, 71));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¿ESTÁS SEGURO?");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("UNA VEZ HECHOS LOS CAMBIOS");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NO HAY VUELTA ATRÁS.");

        container_btn_volver2.setBackground(new java.awt.Color(153, 153, 153));
        container_btn_volver2.setForeground(new java.awt.Color(153, 153, 153));
        container_btn_volver2.setLayout(new java.awt.BorderLayout());

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBorder(null);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        container_btn_volver2.add(btnCancelar, java.awt.BorderLayout.CENTER);

        container_btn_volver3.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_volver3.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_volver3.setLayout(new java.awt.BorderLayout());

        btnDesligar.setBackground(new java.awt.Color(113, 0, 234));
        btnDesligar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDesligar.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar.setText("DESLIGAR");
        btnDesligar.setBorder(null);
        btnDesligar.setBorderPainted(false);
        btnDesligar.setContentAreaFilled(false);
        btnDesligar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDesligar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesligarActionPerformed(evt);
            }
        });
        container_btn_volver3.add(btnDesligar, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(container_btn_volver3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(container_btn_volver3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDesligarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesligarActionPerformed

        //CODIGO PARA HACER EL UPDATE DEL PARQUEADERO
        Map<String, String> queryDesligamiento = new HashMap<>();
        queryDesligamiento.put("cedula",cedula);

        String desligar = consumo.consumoPOST("http://localhost/APIenPHP/API-Personas/Desligar.php", queryDesligamiento);

        System.out.println("Lo que llego"+desligar);

        JsonObject jsonResponse = gson.fromJson(desligar, JsonObject.class);

        boolean status = jsonResponse.get("status").getAsBoolean();

        if( status ){

            this.contentVendedores.mostrarVendedores();

            Main main = new Main();
            main.setVisible(true);

            //MOSTRAMOS MENSAJE DE EXITO DE UPDATE
            AlertExitoDesvinculo mostrar = new AlertExitoDesvinculo();
            mostrar.setVisible(true);

            //CERRA VETANA ACTUAL
            this.dispose();
        }else{

            Main main = new Main();
            main.setVisible(true);

            GeneratingAlert alert = new GeneratingAlert("ERROR", "PARECE QUE ESTA ASOCIADO");
            alert.setVisible(true);
        }
    }//GEN-LAST:event_btnDesligarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //CERRAMOS VENTANA DE ALERTA PARA CONFIRMAR UPDATE
        this.dispose();

        Main main = new Main();
        main.setVisible(true);

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void centrarPantalla(){
        // Centrar la ventana en la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnDesligar;
    public javax.swing.JPanel container_btn_volver2;
    public javax.swing.JPanel container_btn_volver3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
