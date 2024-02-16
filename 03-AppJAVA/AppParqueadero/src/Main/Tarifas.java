package Main;
import Alerts.AlertTarifaUpdate;
import Alerts.AlertErrorUpdateTarifa; 
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public final class Tarifas extends javax.swing.JPanel {
    public MainVendedor main;
    private final Gson gson;
    
    DefaultTableModel modelo;
    public Tarifas(MainVendedor main) {
        this.main = main;
        gson = new Gson();
        initComponents();
        initAlternComponets();
        listaTarifa();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nitParqueadero2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabTarifas = new javax.swing.JTable();
        jLabelTarifas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        campo_tarifa = new javax.swing.JTextField();
        jLabelTarifa = new javax.swing.JLabel();
        jLabelVehiculo = new javax.swing.JLabel();
        boxVehiculos = new javax.swing.JComboBox<>();
        container_btn_volver2 = new javax.swing.JPanel();
        btnModificar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        nitParqueadero2.setText("NO DISPONIBLE");

        setBackground(new java.awt.Color(255, 255, 255));

        tabTarifas.setBackground(new java.awt.Color(255, 255, 255));
        tabTarifas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tabTarifas.setForeground(new java.awt.Color(0, 0, 0));
        tabTarifas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID TARIFA", "VEHÍCULO", "TARIFA/HORA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabTarifas.setSelectionBackground(new java.awt.Color(61, 103, 71));
        tabTarifas.setShowGrid(true);
        jScrollPane1.setViewportView(tabTarifas);

        jLabelTarifas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTarifas.setForeground(new java.awt.Color(61, 103, 71));
        jLabelTarifas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTarifas.setText("TARIFAS");

        jPanel1.setBackground(new java.awt.Color(212, 221, 214));

        campo_tarifa.setBackground(new java.awt.Color(212, 221, 214));
        campo_tarifa.setBorder(null);
        campo_tarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_tarifaActionPerformed(evt);
            }
        });

        jLabelTarifa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTarifa.setForeground(new java.awt.Color(61, 103, 71));
        jLabelTarifa.setText("TARIFA:");

        jLabelVehiculo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelVehiculo.setForeground(new java.awt.Color(61, 103, 71));
        jLabelVehiculo.setText("VEHÍCULO:");

        boxVehiculos.setBackground(new java.awt.Color(212, 221, 214));
        boxVehiculos.setForeground(new java.awt.Color(212, 221, 214));
        boxVehiculos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        boxVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxVehiculosActionPerformed(evt);
            }
        });

        container_btn_volver2.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setLayout(new java.awt.BorderLayout());

        btnModificar.setBackground(new java.awt.Color(61, 103, 71));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("MODIFICAR");
        btnModificar.setBorder(null);
        btnModificar.setContentAreaFilled(false);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        container_btn_volver2.add(btnModificar, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(305, 305, 305))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(campo_tarifa)
                    .addComponent(boxVehiculos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addComponent(jLabelTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabelVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(boxVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(campo_tarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(190, 190, 190))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabelTarifas)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ConsumoApi consumo = new ConsumoApi();
        String nuevoValor = campo_tarifa.getText();
        String tipoVehiculo = (String) boxVehiculos.getSelectedItem();
        System.out.println("Tarifa: " + nuevoValor);

        // Envía la solicitud POST a tu API PHP
        Map<String, String> postData = new HashMap<>();
        postData.put("tipo_vehiculo", tipoVehiculo);
        postData.put("tarifa", nuevoValor);
        String cambiarTarifa = consumo.consumoPOST("http://localhost/APIenPHP/API-tarifas/UpdateTarifa.php", postData);
        System.out.println("raspuesta api: "+ cambiarTarifa);
        if (cambiarTarifa != null) {
            JsonObject jsonTemp = gson.fromJson(cambiarTarifa, JsonObject.class);
            boolean status = jsonTemp.get("status").getAsBoolean();
            String message = jsonTemp.get("message").getAsString();

            if (status) {
                System.out.println("Tarifa actualizada correctamente.");
                AlertTarifaUpdate alert = new AlertTarifaUpdate();
                alert.setVisible(true);
                alert.setLocationRelativeTo(null);
                listaTarifa();
            } else {
                System.out.println("Error al actualizar la tarifa: " + message);
                AlertErrorUpdateTarifa alert = new AlertErrorUpdateTarifa();
                alert.setVisible(true);
                alert.setLocationRelativeTo(null);
            }
        } else {
            System.out.println("Error al consumir la API.");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void boxVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxVehiculosActionPerformed

        String opcionSelected = (String) boxVehiculos.getSelectedItem();
        ConsumoApi consumo = new ConsumoApi();
        String obtenerT = consumo.consumoGET("http://localhost/APIenPHP/API-tarifas/obtenerTarifa.php?tipo_vehiculo=" + opcionSelected);

        // Parsear la respuesta JSON
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(obtenerT).getAsJsonObject();

        // Verificar si "tarifa" existe en la respuesta JSON
        JsonElement tarifaElement = jsonObject.get("tarifa");
        if (tarifaElement != null && !tarifaElement.isJsonNull()) {
            // Obtener el valor de la tarifa del JSON
            String tarifa = tarifaElement.getAsString();
            campo_tarifa.setText(tarifa);
        } else {
            campo_tarifa.setText("No se encontró la tarifa");
        }
    }//GEN-LAST:event_boxVehiculosActionPerformed

    private void campo_tarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_tarifaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_tarifaActionPerformed

    //Inicializando tabla de vehiculos actuales
    public void initAlternComponets(){
        modelo = (DefaultTableModel) tabTarifas.getModel();

        //Deshabilita la modificación de columnas en la interfaz
        tabTarifas.getTableHeader().setReorderingAllowed(false);

        // Configurar la JTable para ajustar automáticamente el ancho de las columnas
        tabTarifas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tabTarifas.setPreferredScrollableViewportSize(tabTarifas.getPreferredSize());
       
    }
    
    private void listaTarifa(){
        ConsumoApi consumo = new ConsumoApi();
        String obtenerTarifas = consumo.consumoGET("http://localhost/APIenPHP/API-tarifas/Obtener.php");

        if (obtenerTarifas != null) {
            JsonObject jsonTemp = gson.fromJson(obtenerTarifas, JsonObject.class);
            JsonArray tarifas = jsonTemp.getAsJsonArray("registros");
            modelo.setRowCount(0);
            String[] opcion = new String[tarifas.size()];

            for (int i = 0; i < tarifas.size(); i++){
                JsonObject verTarifa = tarifas.get(i).getAsJsonObject();
                String id = verTarifa.get("id").getAsString();
                String tipo = verTarifa.get("tipo_vehiculo").getAsString();
                opcion[i] = tipo; // Asignar el tipo al arreglo opcion[]
                String tarifa = verTarifa.get("Tarifa").getAsString();
                Object[] fila = new Object[]{id, tipo, tarifa};
                modelo.addRow(fila);
            }
            boxVehiculos.setModel(new DefaultComboBoxModel<>(opcion));
        }
    }

    
    
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxVehiculos;
    public javax.swing.JButton btnModificar;
    private javax.swing.JTextField campo_tarifa;
    public javax.swing.JPanel container_btn_volver2;
    private javax.swing.JLabel jLabelTarifa;
    private javax.swing.JLabel jLabelTarifas;
    private javax.swing.JLabel jLabelVehiculo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel nitParqueadero2;
    private javax.swing.JTable tabTarifas;
    // End of variables declaration//GEN-END:variables
}
