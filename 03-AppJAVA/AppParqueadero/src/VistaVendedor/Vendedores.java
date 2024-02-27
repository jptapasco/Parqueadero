package VistaVendedor;

import Alerts.AlertConfirmarDesligar;
import Alerts.GeneratingAlert;
import Clases.ButtonEditor;
import Clases.ButtonRenderer;
import Main.ConsumoApi;
import Main.Main;
import botones.Controller_Vendedores;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

public class Vendedores extends javax.swing.JPanel {
    public Controller_Vendedores controller;
    
    private ConsumoApi consumo;
    private Gson gson;
    private Font font;
    DefaultTableModel modelo;
    public Main main;
    
    public Vendedores(Main main) {
        consumo = new ConsumoApi();
        this.main = main;
        gson = new Gson();
        font = new Font("Segoe UI", Font.BOLD, 14);
        initComponents();
        initAlternComponets();
        mostrarVendedores();
        controller = new Controller_Vendedores(this);
    }

    Vendedores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVendedor = new javax.swing.JTable();
        inputBuscar = new javax.swing.JTextField();
        TituloInformativo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        container_btn_vca = new javax.swing.JPanel();
        BtnConAsignar = new javax.swing.JButton();
        container_btn_buscar = new javax.swing.JPanel();
        btnBuscarSeller = new javax.swing.JButton();
        container_btn_vsa = new javax.swing.JPanel();
        BtnSinAsignar = new javax.swing.JButton();
        container_btn_crearvendedor = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        tablaVendedor.setBackground(new java.awt.Color(255, 255, 255));
        tablaVendedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tablaVendedor.setForeground(new java.awt.Color(0, 0, 0));
        tablaVendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "DOCUMENTO", "NOMBRE", "APELLIDO", "VER", "EDITAR", "OPCION"
            }
        ));
        tablaVendedor.setSelectionBackground(new java.awt.Color(61, 103, 71));
        tablaVendedor.setShowGrid(true);
        jScrollPane1.setViewportView(tablaVendedor);

        inputBuscar.setBackground(new java.awt.Color(255, 255, 255));
        inputBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        inputBuscar.setForeground(new java.awt.Color(0, 0, 0));
        inputBuscar.setBorder(null);
        inputBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBuscarActionPerformed(evt);
            }
        });

        TituloInformativo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TituloInformativo.setForeground(new java.awt.Color(61, 103, 71));
        TituloInformativo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloInformativo.setText("VENDEDORES");

        container_btn_vca.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_vca.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_vca.setLayout(new java.awt.BorderLayout());

        BtnConAsignar.setBackground(new java.awt.Color(61, 103, 71));
        BtnConAsignar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BtnConAsignar.setForeground(new java.awt.Color(255, 255, 255));
        BtnConAsignar.setText("VENDEDORES CON ASIGNACION");
        BtnConAsignar.setBorder(null);
        BtnConAsignar.setContentAreaFilled(false);
        BtnConAsignar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnConAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConAsignarActionPerformed(evt);
            }
        });
        container_btn_vca.add(BtnConAsignar, java.awt.BorderLayout.CENTER);

        container_btn_buscar.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_buscar.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_buscar.setLayout(new java.awt.BorderLayout());

        btnBuscarSeller.setBackground(new java.awt.Color(61, 103, 71));
        btnBuscarSeller.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarSeller.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarSeller.setText("BUSCAR");
        btnBuscarSeller.setBorder(null);
        btnBuscarSeller.setContentAreaFilled(false);
        btnBuscarSeller.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarSeller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarSellerActionPerformed(evt);
            }
        });
        container_btn_buscar.add(btnBuscarSeller, java.awt.BorderLayout.CENTER);

        container_btn_vsa.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_vsa.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_vsa.setLayout(new java.awt.BorderLayout());

        BtnSinAsignar.setBackground(new java.awt.Color(61, 103, 71));
        BtnSinAsignar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BtnSinAsignar.setForeground(new java.awt.Color(255, 255, 255));
        BtnSinAsignar.setText("VENDEDORES SIN ASIGNACION");
        BtnSinAsignar.setBorder(null);
        BtnSinAsignar.setContentAreaFilled(false);
        BtnSinAsignar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnSinAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSinAsignarActionPerformed(evt);
            }
        });
        container_btn_vsa.add(BtnSinAsignar, java.awt.BorderLayout.CENTER);

        container_btn_crearvendedor.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_crearvendedor.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_crearvendedor.setLayout(new java.awt.BorderLayout());

        jButton2.setBackground(new java.awt.Color(61, 103, 71));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("CREAR VENDEDOR");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        container_btn_crearvendedor.add(jButton2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TituloInformativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(container_btn_vca, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(inputBuscar)
                                        .addGap(12, 12, 12)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(container_btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(container_btn_crearvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(container_btn_vsa, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(15, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(TituloInformativo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(container_btn_crearvendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(container_btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(container_btn_vca, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(container_btn_vsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void initAlternComponets(){
        
        BtnConAsignar.setVisible(false);
        
        modelo = (DefaultTableModel) tablaVendedor.getModel();
        
        this.tablaVendedor.getColumn("VER").setCellRenderer(new ButtonRenderer());
        this.tablaVendedor.getColumn("VER").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        this.tablaVendedor.getColumn("EDITAR").setCellRenderer(new ButtonRenderer());
        this.tablaVendedor.getColumn("EDITAR").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        this.tablaVendedor.getColumn("OPCION").setCellRenderer(new ButtonRenderer());
        this.tablaVendedor.getColumn("OPCION").setCellEditor(new ButtonEditor(new JCheckBox())); 
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // ACA VA EL CODIGO PARA MOSTRAR LA VENTANA PARA REGISTAR UN PARQUEADERO
        System.out.println("\n SE APRETO EL BOTON DE CREAR VENDEDOR \n");
        
        // HACEMOS LA INSTANCIA DE LA VENTANA QUE QUEREMOS CREAR Y LA MOSTRAMOS
        CreateSeller mostrarFrame = new CreateSeller(this);
        mostrarFrame.setVisible(true);
        
        this.main.setVisible(false);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BtnConAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConAsignarActionPerformed
        BtnConAsignar.setVisible(false);
        BtnSinAsignar.setVisible(true);
        mostrarVendedores();
    }//GEN-LAST:event_BtnConAsignarActionPerformed

    private void BtnSinAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSinAsignarActionPerformed
        
        BtnConAsignar.setVisible(true);
        BtnSinAsignar.setVisible(false);
        mostrarVendedoresSinAsignar();
      
    }//GEN-LAST:event_BtnSinAsignarActionPerformed

    private void btnBuscarSellerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSellerActionPerformed
        // ACA VA EL CODIGO PARA BUSCAR USUARIO
        System.out.println("\n SE APRETO EL BOTON DE BUSCAR USUARIO \n");
        
        String cedula = inputBuscar.getText();
        if(cedula != null && !cedula.isEmpty()) {
            // MAPEAMOS LOS DATOS
            Map<String, String> consultaUser = new HashMap<>();
            consultaUser.put("cedula",cedula);
            String consultarUsuario = consumo.consumoPOST("http://localhost/API-PRQDR-05/API-Personas/VerificarPersona.php",consultaUser);
            System.out.println("consultar usuario: " + consultarUsuario);
            JsonObject jsonResponse = gson.fromJson(consultarUsuario, JsonObject.class);

            boolean status = jsonResponse.get("status").getAsBoolean();
            if (status) {
                modelo.setRowCount(0);
                JsonArray usuario = jsonResponse.getAsJsonArray("registros");
                for(int i = 0; i < usuario.size(); i++ ){
                    JsonObject viewUser = usuario.get(i).getAsJsonObject();
                    String nombre = viewUser.get("nombre").getAsString();
                    String apellido = viewUser.get("apellidos").getAsString();
                    String cedulaB = viewUser.get("cedula").getAsString();   

                    JButton btnVer = new JButton("VER");
                    btnVer.setBorderPainted(false);
                    btnVer.setContentAreaFilled(false);
                    btnVer.setForeground(new Color(81, 168, 199));
                    btnVer.setFont(font);

                    JButton btnEditar = new JButton("EDITAR");
                    btnEditar.setBorderPainted(false);
                    btnEditar.setContentAreaFilled(false);
                    btnEditar.setForeground(new Color(39, 80, 49));
                    btnEditar.setFont(font);

                    JButton btnDesligar = new JButton("Desligar");
                    btnDesligar.setBorderPainted(false);
                    btnDesligar.setContentAreaFilled(false);
                    btnDesligar.setForeground(new Color(255, 0, 0));
                    btnDesligar.setFont(font);

                     // Crear un botón de edición para cada fila
                    final int posicion = i;

                     btnVer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            accionClickBotonVer( posicion );
                        }
                    });

                    btnEditar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            accionClickBotonEditar( posicion );
                        }
                    });

                    btnDesligar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            accionClickBotonDesligar( posicion );
                        }
                    });
                    
                    Object[] fila = new Object[]{cedulaB,nombre,apellido,btnVer,btnEditar,btnDesligar};

                    modelo.addRow(fila);
                }
            }else{
                GeneratingAlert alert = new GeneratingAlert("ERROR", "VENDEDOR NO ENCONTRADO");
                alert.setVisible(true);
                inputBuscar.setText("");
                mostrarVendedores();
            }
        }else {
            //HACEMOS APARECER UNA ALERTA
            GeneratingAlert mostrarFrame = new GeneratingAlert("ERROR","POR FAVOR INGRESE UN DATO");
            mostrarFrame.setVisible(true);
            mostrarVendedores();
        }
    }//GEN-LAST:event_btnBuscarSellerActionPerformed

    private void inputBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputBuscarActionPerformed

    public void mostrarVendedores(){
              
        TituloInformativo.setText("USUARIOS CON PARQUEADERO ASOCIADO:");
        // PETICION PARA OBTENER TODOS LOS PARQUEADEROS 
        String obtenerPersonas = consumo.consumoGET("http://localhost/API-PRQDR-05/API-Personas/ObtenerPersonas.php");
        
        if( obtenerPersonas != null ){
            JsonObject jsonTemp  = gson.fromJson(obtenerPersonas, JsonObject.class);
            
            JsonArray personas = jsonTemp.getAsJsonArray("registros");
            System.out.println(personas);
            modelo.setRowCount(0);
            
             for(int i = 0; i < personas.size(); i++ ){
                JsonObject viewParking = personas.get(i).getAsJsonObject();
                String nombre = viewParking.get("nombre").getAsString();
                String apellido = viewParking.get("apellidos").getAsString();
                String documento = viewParking.get("cedula").getAsString(); 
                
                JButton btnVer = new JButton("VER");
                btnVer.setBorderPainted(false);
                btnVer.setContentAreaFilled(false);
                btnVer.setForeground(new Color(81, 168, 199));
                btnVer.setFont(font);
                
                JButton btnEditar = new JButton("EDITAR");
                btnEditar.setBorderPainted(false);
                btnEditar.setContentAreaFilled(false);
                btnEditar.setForeground(new Color(39, 80, 49));
                btnEditar.setFont(font);
                
                JButton btnDesligar = new JButton("Desligar");
                btnDesligar.setBorderPainted(false);
                btnDesligar.setContentAreaFilled(false);
                btnDesligar.setForeground(new Color(255, 0, 0));
                btnDesligar.setFont(font);
            
                 // Crear un botón de edición para cada fila
                final int posicion = i;
                
                 btnVer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accionClickBotonVer( posicion );
                    }
                });
                
                btnEditar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accionClickBotonEditar( posicion );
                    }
                });
                
                btnDesligar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accionClickBotonDesligar( posicion );
                    }
                });
                
                Object[] fila = new Object[]{documento,nombre,apellido,btnVer,btnEditar, btnDesligar};
                
                modelo.addRow(fila);
                
            }
        }else{ 
            System.out.println("entro");
        }
    }
    
    public void mostrarVendedoresSinAsignar(){
        TituloInformativo.setText("USUARIOS CON PARQUEADEROS SIN ASOCIAR:");
        
        String obtenerPersonas = consumo.consumoGET("http://localhost/API-PRQDR-05/API-Personas/ObtenerPersonasSinAsignar.php");
         
         if( obtenerPersonas != null ){
            JsonObject jsonTemp  = gson.fromJson(obtenerPersonas, JsonObject.class);
            
            JsonArray personas = jsonTemp.getAsJsonArray("registros");
            System.out.println(personas);
            modelo.setRowCount(0);
            
             for(int i = 0; i < personas.size(); i++ ){
                JsonObject viewParking = personas.get(i).getAsJsonObject();
                String nombre = viewParking.get("nombre").getAsString();
                String apellido = viewParking.get("apellidos").getAsString();
                String documento = viewParking.get("cedula").getAsString(); 
                
                JButton btnVer = new JButton("VER");
                btnVer.setBorderPainted(false);
                btnVer.setContentAreaFilled(false);
                btnVer.setForeground(new Color(81, 168, 199));
                btnVer.setFont(font);
                
                JButton btnEditar = new JButton("EDITAR");
                btnEditar.setBorderPainted(false);
                btnEditar.setContentAreaFilled(false);
                btnEditar.setForeground(new Color(39, 80, 49));
                btnEditar.setFont(font);
                
                JButton btnAsociar = new JButton("ASOCIAR");
                btnAsociar.setBorderPainted(false);
                btnAsociar.setContentAreaFilled(false);
                btnAsociar.setForeground(new Color(39, 80, 49));
                btnAsociar.setFont(font);
            
                 // Crear un botón de edición para cada fila
                final int posicion = i;
                
                 btnVer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accionClickBotonVer( posicion );
                    }
                });
                
                btnEditar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accionClickBotonEditar( posicion );
                    }
                });
                
                btnAsociar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accionClickBotonAsociar( posicion );
                    }
                });
                
                Object[] fila = new Object[]{documento,nombre,apellido,btnVer,btnEditar, btnAsociar};
                
                modelo.addRow(fila);
                
            }
        }else{
            System.out.println("NO SE ENCONTRO");
        }
    }
    
    public void accionClickBotonVer(int fila) {
        
        String cedula = (String) modelo.getValueAt(fila, 0);
        VerUsuario frame = new VerUsuario(this,cedula);
        frame.setVisible(true);
       
        this.main.setVisible(false);
       
    }
    
    public void accionClickBotonEditar(int fila){
        System.out.println("\n SE DIO CLICK EN EL BOTON DE EDITAR \n");
        
        String cedula = (String) modelo.getValueAt(fila, 0);
        EditarUsuario frame = new EditarUsuario(this,cedula);
        frame.setVisible(true);
        
        this.main.setVisible(false);
        
        
       
    }
    
    public void accionClickBotonDesligar(int fila){
        System.out.println("\n SE DIO CLICK EN EL BOTON DE DESLIGAR \n");
        
        String cedula = (String) modelo.getValueAt(fila, 0);
        String nombre = (String) modelo.getValueAt(fila, 1);
        String apellido = (String) modelo.getValueAt(fila, 2);
        
        // Ejemplo de cómo mostrar los datos en la consola
        System.out.println("");
        System.out.println("Se hizo clic en el botón de edición en la fila " + fila);
        System.out.println("Documento: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("");

        //VERIFICAR QUE LA PERSONA QUE QUEREMOS DESLIGAR SI EXISTE
        Map<String, String> queryData = new HashMap<>();
        queryData.put("cedula",cedula);
        
        String verificar = consumo.consumoPOST("http://localhost/API-PRQDR-05/API-Personas/VerificarPersona.php", queryData);
        
        if( verificar != null ){
            
            this.main.dispose();
            
              
            AlertConfirmarDesligar mostrarFrame = new AlertConfirmarDesligar(cedula,this);
            mostrarFrame.setVisible(true);
        }
    }
    
     public void accionClickBotonAsociar(int fila) {
        
        String cedula = (String) modelo.getValueAt(fila, 0);
        AsignarParqueadero frame = new AsignarParqueadero(this,cedula);
        frame.setVisible(true);
       
        this.main.dispose();
       
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BtnConAsignar;
    public javax.swing.JButton BtnSinAsignar;
    private javax.swing.JLabel TituloInformativo;
    public javax.swing.JButton btnBuscarSeller;
    public javax.swing.JPanel container_btn_buscar;
    public javax.swing.JPanel container_btn_crearvendedor;
    public javax.swing.JPanel container_btn_vca;
    public javax.swing.JPanel container_btn_vsa;
    private javax.swing.JTextField inputBuscar;
    public javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaVendedor;
    // End of variables declaration//GEN-END:variables
}
