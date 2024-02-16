package Alerts;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

public class GeneratingAlert extends javax.swing.JFrame {

    String titulo;
    String texto;
    public GeneratingAlert(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
        initComponents();
        
        UpdateContent();
        centrarPantalla();
        Image icono = getToolkit().createImage( ClassLoader.getSystemResource("Img/tractor.png") );
        setIconImage(icono);
        setTitle("Parking");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LabelTitle = new javax.swing.JLabel();
        LabelTexto = new javax.swing.JLabel();
        container_btn_volver2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        LabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LabelTitle.setForeground(new java.awt.Color(61, 103, 71));
        LabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitle.setText("...");

        LabelTexto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LabelTexto.setForeground(new java.awt.Color(0, 0, 0));
        LabelTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTexto.setText("...");

        container_btn_volver2.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setLayout(new java.awt.BorderLayout());

        jButton1.setBackground(new java.awt.Color(113, 0, 234));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("OKEY");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        container_btn_volver2.add(jButton1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LabelTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(LabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(LabelTitle)
                .addGap(35, 35, 35)
                .addComponent(LabelTexto)
                .addGap(35, 35, 35)
                .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void UpdateContent(){
        LabelTitle.setText(titulo);
        LabelTexto.setText(texto);
    }

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
    private javax.swing.JLabel LabelTexto;
    private javax.swing.JLabel LabelTitle;
    public javax.swing.JPanel container_btn_volver2;
    public javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
