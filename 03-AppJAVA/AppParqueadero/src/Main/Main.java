package Main;

import VistaParqueadero.Parqueaderos;
import VistaVendedor.Vendedores;
import botones.Controller_Main;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Main extends javax.swing.JFrame {
    
    public Controller_Main controller;
    
    public Main() {
        initComponents();
        centrarPantalla();
        
        Image icono = getToolkit().createImage( ClassLoader.getSystemResource("Img/tractor.png") );
        setIconImage(icono);
        setTitle("Parking");
        
        controller = new Controller_Main(this);
        
        panelPrincipal.removeAll();

        Parqueaderos mostrarPanel = new Parqueaderos(this);

        mostrarPanel.setSize(panelPrincipal.getSize() );
        panelPrincipal.add( mostrarPanel );
        
        repaint();
        revalidate();
        
        try {
            // Cargar la imagen desde el archivo en el directorio de recursos
            BufferedImage originalImage = ImageIO.read(getClass().getResource("/Img/tractor.png"));

            // Redimensionar la imagen al tamaño deseado
            Image resizedImage = originalImage.getScaledInstance(140, 110, Image.SCALE_SMOOTH);

            // Crear un ImageIcon a partir de la imagen redimensionada
            ImageIcon imageIcon = new ImageIcon(resizedImage);

            // Asignar el ImageIcon al JLabel
            etq_img.setIcon(imageIcon);
        } catch (IOException e) {
            // Manejo de errores en caso de que la imagen no se pueda cargar

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        etq_img = new javax.swing.JLabel();
        container_btn_parqueaderos_admin = new javax.swing.JPanel();
        btnParking = new javax.swing.JButton();
        container_btn_vendedores_admin = new javax.swing.JPanel();
        btnSeller = new javax.swing.JButton();
        container_btn_cerrar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(61, 103, 71));

        etq_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login-icon.png"))); // NOI18N
        etq_img.setMaximumSize(new java.awt.Dimension(125, 125));
        etq_img.setMinimumSize(new java.awt.Dimension(125, 125));
        etq_img.setPreferredSize(new java.awt.Dimension(125, 125));

        container_btn_parqueaderos_admin.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_parqueaderos_admin.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_parqueaderos_admin.setLayout(new java.awt.BorderLayout());

        btnParking.setBackground(new java.awt.Color(61, 103, 71));
        btnParking.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnParking.setForeground(new java.awt.Color(255, 255, 255));
        btnParking.setText("PARQUEADEROS");
        btnParking.setBorder(null);
        btnParking.setBorderPainted(false);
        btnParking.setContentAreaFilled(false);
        btnParking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnParking.setFocusPainted(false);
        btnParking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParkingActionPerformed(evt);
            }
        });
        container_btn_parqueaderos_admin.add(btnParking, java.awt.BorderLayout.CENTER);

        container_btn_vendedores_admin.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_vendedores_admin.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_vendedores_admin.setLayout(new java.awt.BorderLayout());

        btnSeller.setBackground(new java.awt.Color(61, 103, 71));
        btnSeller.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSeller.setForeground(new java.awt.Color(255, 255, 255));
        btnSeller.setText("VENDEDORES");
        btnSeller.setBorder(null);
        btnSeller.setBorderPainted(false);
        btnSeller.setContentAreaFilled(false);
        btnSeller.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSeller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSellerActionPerformed(evt);
            }
        });
        container_btn_vendedores_admin.add(btnSeller, java.awt.BorderLayout.CENTER);

        container_btn_cerrar.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_cerrar.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_cerrar.setLayout(new java.awt.BorderLayout());

        jButton1.setBackground(new java.awt.Color(61, 103, 71));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("CERRAR SESION");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        container_btn_cerrar.add(jButton1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etq_img, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(container_btn_cerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(container_btn_parqueaderos_admin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(container_btn_vendedores_admin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(etq_img, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(container_btn_parqueaderos_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(container_btn_vendedores_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addComponent(container_btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();

        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSellerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSellerActionPerformed
        // ACA VA EL CODIGO PARA EL INTERCAMBIO DE PANEL VENDEDOR

        System.out.println("SE DIO AL BOTONO VENDEDORES");

        panelPrincipal.removeAll();

        Vendedores mostrarPanel = new Vendedores(this);

        mostrarPanel.setSize(panelPrincipal.getSize() );
        panelPrincipal.add( mostrarPanel );

        repaint();
        revalidate();
    }//GEN-LAST:event_btnSellerActionPerformed

    private void btnParkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParkingActionPerformed
        // ACA VA EL CODIGO PARA EL INTERCAMBIO DE PANEL PARQUEADEROS

        System.out.println("SE DIO AL BOTONO PARQUEADERO");

        panelPrincipal.removeAll();

        Parqueaderos mostrarPanel = new Parqueaderos(this);

        mostrarPanel.setSize(panelPrincipal.getSize() );
        panelPrincipal.add( mostrarPanel );

        repaint();
        revalidate();
    }//GEN-LAST:event_btnParkingActionPerformed
    
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
    public javax.swing.JButton btnParking;
    public javax.swing.JButton btnSeller;
    public javax.swing.JPanel container_btn_cerrar;
    public javax.swing.JPanel container_btn_parqueaderos_admin;
    public javax.swing.JPanel container_btn_vendedores_admin;
    private javax.swing.JLabel etq_img;
    public javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
