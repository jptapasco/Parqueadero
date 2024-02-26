package Main;
import Alerts.GeneratingAlert;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;


public final class Entrada extends javax.swing.JPanel {
    
    public MainVendedor main;
    private final Gson gson;
    ConsumoApi consumo;
    
    DefaultTableModel modelo;
    String id_asignacion;
    String responsable;
    String placa;
    String vehiculo;
    String encargado;
    
    
    public Entrada(MainVendedor main, String id_asignacion) {
        this.main = main;
        this.id_asignacion = id_asignacion;
        consumo = new ConsumoApi();
        gson = new Gson();
        initComponents();
        initAlternComponets();
        select();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nitParqueadero2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabelPlaca = new javax.swing.JLabel();
        jLabelVehiculo = new javax.swing.JLabel();
        jLabelTitular = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        selectVehiculo = new javax.swing.JComboBox<>();
        inputPlaca = new javax.swing.JTextField();
        inputTitular = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        container_btn_volver2 = new javax.swing.JPanel();
        btnEntrada = new javax.swing.JButton();

        nitParqueadero2.setText("NO DISPONIBLE");

        jCheckBox1.setText("jCheckBox1");

        setBackground(new java.awt.Color(255, 255, 255));

        jLabelPlaca.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelPlaca.setForeground(new java.awt.Color(61, 103, 71));
        jLabelPlaca.setText("TITULAR:");

        jLabelVehiculo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelVehiculo.setForeground(new java.awt.Color(61, 103, 71));
        jLabelVehiculo.setText("PLACA:");

        jLabelTitular.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitular.setForeground(new java.awt.Color(61, 103, 71));
        jLabelTitular.setText("VEHÍCULO:");

        jLabel1.setBackground(new java.awt.Color(61, 103, 71));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(61, 103, 71));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRAR ENTRADA DE VEHÍCULOS");

        selectVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        selectVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectVehiculo.setBorder(null);
        selectVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectVehiculoActionPerformed(evt);
            }
        });

        inputPlaca.setBackground(new java.awt.Color(255, 255, 255));
        inputPlaca.setBorder(null);
        inputPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPlacaActionPerformed(evt);
            }
        });

        inputTitular.setBackground(new java.awt.Color(255, 255, 255));
        inputTitular.setBorder(null);
        inputTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTitularActionPerformed(evt);
            }
        });

        container_btn_volver2.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setLayout(new java.awt.BorderLayout());

        btnEntrada.setBackground(new java.awt.Color(113, 0, 234));
        btnEntrada.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrada.setText("INGRESAR");
        btnEntrada.setBorder(null);
        btnEntrada.setContentAreaFilled(false);
        btnEntrada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });
        container_btn_volver2.add(btnEntrada, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectVehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputPlaca)
                    .addComponent(inputTitular, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(jLabelVehiculo)
                .addGap(1, 1, 1)
                .addComponent(inputPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelTitular)
                .addGap(1, 1, 1)
                .addComponent(selectVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelPlaca)
                .addGap(1, 1, 1)
                .addComponent(inputTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
    }// </editor-fold>//GEN-END:initComponents

    //Inicializando tabla de vehiculos actuales
    public void initAlternComponets(){
        
    }
    
    //Botón para editar parqueadero
    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {                                           
       
        placa = inputPlaca.getText();
        vehiculo = selectVehiculo.getSelectedItem().toString();
        encargado = inputTitular.getText();
       
        if (!placa.isEmpty() &&  !selectVehiculo.getSelectedItem().equals("Seleccionar Vehiculo") && !encargado.isEmpty() ) {
            
            Map<String, String> verificar = new HashMap<>();
            verificar.put("placa", placa);
            
            String selected = consumo.consumoPOST("http://localhost/API-PRQDR-05/API-tarifas/VerificarPlaca.php", verificar);
            
            System.out.println("RESPUESTA ASOCIACION: " + selected);
            
            JsonObject respuestaInsert = gson.fromJson(selected, JsonObject.class);

            boolean status = respuestaInsert.get("status").getAsBoolean();

            if (status) {
                responsable = respuestaInsert.get("responsable").getAsString();
                String tipoVehiculo = respuestaInsert.get("tipo_vehiculo").getAsString();

                System.out.println("Responsable: " + responsable);
                System.out.println("Tipo de Vehículo: " + tipoVehiculo);
                
                inputTitular.setText(responsable);
                selectVehiculo.setSelectedItem(responsable);
                
                Map<String, String> id_tarifa = new HashMap<>();
                id_tarifa.put("tipo_vehiculo", vehiculo);
                
                String idTarifa = consumo.consumoPOST("http://localhost/API-PRQDR-05/API-tarifas/ObtenerIdTarifa.php", id_tarifa);
                
                System.out.println("LO QUE ME LLEGO PARA TARIFAS ID: "+idTarifa);
                // Analizar la respuesta JSON
                JsonObject respuesta = gson.fromJson(idTarifa, JsonObject.class);

                // Verificar si la respuesta indica éxito
                boolean success = respuesta.get("success").getAsBoolean();

                if (success) {
                    // Obtener el ID de tarifa
                    String idTarifaObtenido = respuesta.get("id_tarifa").getAsString();
                    System.out.println("ID de Tarifa: " + idTarifaObtenido);

                    Map<String, String> ticket = new HashMap<>();
                    ticket.put("placa", placa);
                    ticket.put("id_asignacion", id_asignacion);
                    ticket.put("id_tarifa", idTarifaObtenido);
                    
                    String insertTicket = consumo.consumoPOST("http://localhost/API-PRQDR-05/API-ticket/insertTicket.php", ticket);
                    
                    System.out.println("RESPUESTA DEL INSERT DEL TICKET: "+insertTicket);
                    
                    JsonObject response = gson.fromJson(insertTicket, JsonObject.class);

                    boolean statusinsert = response.get("status").getAsBoolean();

                    if (statusinsert) {
                        
                        inputPlaca.setText("");
                        inputTitular.setText("");
                        
                        GeneratingAlert alert = new GeneratingAlert("EXITO","REGISTRO EXITOSO");
                        alert.setVisible(true);
                    } 
                }
            }else if(!status){
                System.out.println("entro a ststus falseeeee");
           
                Map<String, String> insertRegistroV = new HashMap<>();
                insertRegistroV.put("placa", placa);
                insertRegistroV.put("responsable", encargado);
                
                String insetRegistrov = consumo.consumoPOST("http://localhost/API-PRQDR-05/API-Ticket/insertRegistro.php", insertRegistroV);
                
                System.out.println("INSERTANDO NUEVO REGISTRO: "+insetRegistrov);
                
                JsonObject response = gson.fromJson(insetRegistrov, JsonObject.class);

                boolean statusinsert = response.get("status").getAsBoolean();
                
                if (statusinsert) {
                    
                    Map<String, String> id_tarifa = new HashMap<>();
                    id_tarifa.put("tipo_vehiculo", vehiculo);

                    String idTarifa = consumo.consumoPOST("http://localhost/API-PRQDR-05/API-tarifas/ObtenerIdTarifa.php", id_tarifa);

                    System.out.println("LO QUE ME LLEGO PARA TARIFAS ID: "+idTarifa);
                    // Analizar la respuesta JSON
                    JsonObject respuesta = gson.fromJson(idTarifa, JsonObject.class);

                    // Verificar si la respuesta indica éxito
                    boolean success = respuesta.get("success").getAsBoolean();

                    if (success) {
                        // Obtener el ID de tarifa
                        String idTarifaObtenido = respuesta.get("id_tarifa").getAsString();
                        System.out.println("ID de Tarifa: " + idTarifaObtenido);

                        Map<String, String> ticket = new HashMap<>();
                        ticket.put("placa", placa);
                        ticket.put("id_asignacion", id_asignacion);
                        ticket.put("id_tarifa", idTarifaObtenido);

                        String insertTicket = consumo.consumoPOST("http://localhost/API-PRQDR-05/API-Ticket/insertTicket.php", ticket);

                        JsonObject response1 = gson.fromJson(insertTicket, JsonObject.class);

                        boolean statusinsert2 = response1.get("status").getAsBoolean();

                        if (statusinsert2) {

                            inputPlaca.setText("");
                            inputTitular.setText("");

                            GeneratingAlert alert = new GeneratingAlert("EXITO","REGISTRO EXITOSO");
                            alert.setVisible(true);
                        }
                    }
                }
            }
        }
    }


    private void selectVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectVehiculoActionPerformed

    private void inputPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPlacaActionPerformed

    private void inputTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTitularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputTitularActionPerformed

    private void select(){
         // Limpia el selector de parqueaderos
        selectVehiculo.removeAllItems();

        // Agrega una opción predeterminada (en blanco o mensaje informativo)
        selectVehiculo.addItem("Seleccionar Vehiculo");

        // Luego, puedes cargar los parqueaderos reales
        String selected = consumo.consumoGET("http://localhost/API-PRQDR-05/API-tarifas/Obtener.php");

        if (selected != null) {
            JsonObject jsonTemp = gson.fromJson(selected, JsonObject.class);
            JsonArray parking = jsonTemp.getAsJsonArray("registros");

            // Agrega los parqueaderos reales a selectParking
            for (JsonElement element : parking) {
                JsonObject parkingData = element.getAsJsonObject();
                String parkingName = parkingData.get("tipo_vehiculo").getAsString();
                selectVehiculo.addItem(parkingName);
            }
        }
    }
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrada;
    public javax.swing.JPanel container_btn_volver2;
    private javax.swing.JTextField inputPlaca;
    private javax.swing.JTextField inputTitular;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelPlaca;
    private javax.swing.JLabel jLabelTitular;
    private javax.swing.JLabel jLabelVehiculo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel nitParqueadero2;
    private javax.swing.JComboBox<String> selectVehiculo;
    // End of variables declaration//GEN-END:variables
}
