package Main;

import com.google.gson.Gson;
import Alerts.AlertDatosErroneos;
import Alerts.GeneratingAlert;
import botones.Controller_Login;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Login extends javax.swing.JFrame {
    
    public Controller_Login controller;
    
    private ConsumoApi consumo;
    private Gson gson;
    public MainVendedor main;
    
    public String nit;
    public String nombre;
    public String direccion;
    public String telefono;
    public String user;
    String id_asiganacion;
    
    
    public Login() {
        this.main = main;
        consumo = new ConsumoApi();
        gson = new Gson();
        initComponents();
        initComponentsAltern();
        controller = new Controller_Login(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        etq_img = new javax.swing.JLabel();
        etq_correo = new javax.swing.JLabel();
        campo_correo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        campo_contrasena = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        container_btn_ingresar = new javax.swing.JPanel();
        btn_ingresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        etq_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login-icon.png"))); // NOI18N

        etq_correo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etq_correo.setForeground(new java.awt.Color(61, 103, 71));
        etq_correo.setText("Correo:");

        campo_correo.setBackground(new java.awt.Color(255, 255, 255));
        campo_correo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        campo_correo.setForeground(new java.awt.Color(0, 0, 0));
        campo_correo.setBorder(null);
        campo_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_correoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(61, 103, 71));
        jLabel1.setText("Contraseña:");

        campo_contrasena.setBackground(new java.awt.Color(255, 255, 255));
        campo_contrasena.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        campo_contrasena.setBorder(null);
        campo_contrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_contrasenaActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(61, 103, 71));

        jSeparator2.setForeground(new java.awt.Color(61, 103, 71));

        container_btn_ingresar.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_ingresar.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_ingresar.setLayout(new java.awt.BorderLayout());

        btn_ingresar.setBackground(new java.awt.Color(61, 103, 71));
        btn_ingresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_ingresar.setForeground(new java.awt.Color(255, 255, 255));
        btn_ingresar.setText("INGRESAR");
        btn_ingresar.setAlignmentY(0.0F);
        btn_ingresar.setBorder(null);
        btn_ingresar.setContentAreaFilled(false);
        btn_ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ingresar.setFocusPainted(false);
        btn_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresarActionPerformed(evt);
            }
        });
        container_btn_ingresar.add(btn_ingresar, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(etq_img, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etq_correo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campo_correo)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campo_contrasena)
                    .addComponent(jSeparator2)
                    .addComponent(container_btn_ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(etq_img, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etq_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(campo_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(campo_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(container_btn_ingresar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void initComponentsAltern(){
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        
        Image icono = getToolkit().createImage( ClassLoader.getSystemResource("Img/tractor.png") );
        setIconImage(icono);
        setTitle("Parking");
        
        int width = 200;
        int height = 150;

        try {
            // Cargar la imagen desde el archivo en el directorio de recursos
            BufferedImage originalImage = ImageIO.read(getClass().getResource("/Img/tractor.png"));

            // Redimensionar la imagen al tamaño deseado
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Crear un ImageIcon a partir de la imagen redimensionada
            ImageIcon imageIcon = new ImageIcon(resizedImage);

            // Asignar el ImageIcon al JLabel
            etq_img.setIcon(imageIcon);
        } catch (IOException e) {
            // Manejo de errores en caso de que la imagen no se pueda cargar
            e.printStackTrace();
        }
        
    }
    private void campo_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_correoActionPerformed

    private void btn_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresarActionPerformed
        
        String correo = campo_correo.getText();
        String contrasena = campo_contrasena.getText();

        if (!correo.isEmpty() && !contrasena.isEmpty()) {
            Map<String, String> postData = new HashMap<>();
            postData.put("correo", correo);
            postData.put("contrasena", contrasena);

            String iniciarSesion = consumo.consumoPOST("http://localhost/APIenPHP/API-login/acceso.php", postData);
            System.out.println("RETURN LOGIN: "+iniciarSesion);

            if (iniciarSesion != null) {
                JsonObject jsonTemp = gson.fromJson(iniciarSesion, JsonObject.class);
                boolean success = jsonTemp.get("success").getAsBoolean();

                if (success) {
                    // Verificar si el usuario existe
                    if (jsonTemp.get("user") != null && jsonTemp.get("user").isJsonObject()) {
                        JsonObject usuario = jsonTemp.getAsJsonObject("user");
                        
                        System.out.println("PASO 1 LOGIN: "+usuario);
                        
                        String idUsuario = usuario.get("id").getAsString();
                        System.out.println("LOGIN PASO 1.5 = "+ idUsuario);
                        
                        Map<String, String> obtener = new HashMap<>();
                        obtener.put("id_usuario", idUsuario);
                        
                        String ObtenParking = consumo.consumoPOST("http://localhost/APIenPHP/API-Ticket/obtenerParqueadero.php" , obtener);
                          
                        System.out.println("PASO 2 LOGIN: "+ ObtenParking);
                        
                        if (ObtenParking != null) {
                            JsonObject jsonTemp2 = gson.fromJson(ObtenParking, JsonObject.class);
                            boolean success2 = jsonTemp2.get("success").getAsBoolean();

                            if (success2) {
                                JsonArray parqueaderos = jsonTemp2.getAsJsonArray("parqueaderos");

                                for (JsonElement parqueadero : parqueaderos) {
                                    JsonObject parq = parqueadero.getAsJsonObject();
                                    nit = parq.get("nit").getAsString();
                                    nombre = parq.get("nombre").getAsString();
                                    direccion = parq.get("direccion").getAsString();
                                    telefono = parq.get("telefono").getAsString();
                                    user = parq.get("num_vendedores").getAsString();
                                    
                                    System.out.println("NIT LOGUEADO: "+nit);
                                    System.out.println("NOMBRE LOGUEADO: "+nombre);
                                    System.out.println("DIRECCION: "+direccion);
                                    System.out.println("TELEFONO: "+telefono);
                                    System.out.println("EMAIL: "+user);
                                    
                                    
                                    Map<String, String> queryAsignacion = new HashMap<>();
                                    queryAsignacion.put("id_usuario", idUsuario);
                                    
                                    String ObtenerAsignacion = consumo.consumoPOST("http://localhost/APIenPHP/API-parqueadero/obtenerIdAsignacion.php", queryAsignacion);
                                    
                                    System.out.println("ESTE ES EL ID DEL ASIGNACION "+ObtenerAsignacion);
                                    
                                    JsonObject respuesta = gson.fromJson(ObtenerAsignacion, JsonObject.class);
                                    
                                    id_asiganacion = respuesta.get("id_asignacion").getAsString();
                                    System.out.println("ID de Asignación: " + id_asiganacion);
                                }
                            } else {
                                System.out.println("Error al obtener los parqueaderos.");
                            }
                        } else {
                            System.out.println("Error al consumir la API de parqueaderos.");
                        }

                        String tipoUser = usuario.get("tipo").getAsString();
                        System.out.println("Tipo: " + tipoUser);
                        String ven = "VENDEDOR";
                        String adm = "ADMIN";
                        if (tipoUser.equalsIgnoreCase(ven)) {
                            MainVendedor mainv = new MainVendedor(nit, nombre, direccion, telefono, user, id_asiganacion);
                            mainv.setVisible(true);
                            dispose();
                        } else if(tipoUser.equalsIgnoreCase(adm)){
                            new Main().setVisible(true);
                            dispose();
                        }
                    } else {
                        System.out.println("El usuario no existe en la base de datos.");
                        AlertDatosErroneos alert = new AlertDatosErroneos();
                        alert.setVisible(true);
                        alert.setLocationRelativeTo(null);
                        campo_correo.setText("");
                        campo_contrasena.setText("");
                    }
                } else {
                    System.out.println("Error en el inicio de sesión: " );                          
                }
            } else {
                System.out.println("Error al consumir la API.");
            }
        } else {
            GeneratingAlert alert = new GeneratingAlert("ERROR","DATOS INCOMPLETOS");
            alert.setVisible(true);
        }                                                                                                                                                                                              
    }//GEN-LAST:event_btn_ingresarActionPerformed

    private void campo_contrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_contrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_contrasenaActionPerformed
    
       public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_ingresar;
    private javax.swing.JPasswordField campo_contrasena;
    private javax.swing.JTextField campo_correo;
    public javax.swing.JPanel container_btn_ingresar;
    private javax.swing.JLabel etq_correo;
    private javax.swing.JLabel etq_img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
