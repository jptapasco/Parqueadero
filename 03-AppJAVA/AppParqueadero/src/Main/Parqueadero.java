package Main;
import Alerts.AlertCobrar;
import Alerts.GeneratingAlert;
import Clases.ButtonEditor;
import Clases.ButtonRenderer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public final class Parqueadero extends javax.swing.JPanel {
    
    public MainVendedor main;
    private final Gson gson;
    private ConsumoApi consumo;
    DefaultTableModel modelo;
    String nit;
    String nombre;
    String direccion;
    String telefono;
    String user;
    
    public Parqueadero(MainVendedor main,String nit, String nombre, String direccion, String telefono, String user ) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.user = user;
        this.main = main;
        gson = new Gson();
        consumo = new ConsumoApi();
        initComponents();
        initAlternComponets();        
        mostrarDatosParqueadero();
        listaVehiculos();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nitParqueadero2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabVehiculosActuales = new javax.swing.JTable();
        inputBuscarUpdate = new javax.swing.JTextField();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jLabelVendedores = new javax.swing.JLabel();
        etq_nombre = new javax.swing.JLabel();
        etq_telefono = new javax.swing.JLabel();
        etq_direccion = new javax.swing.JLabel();
        etq_vendedor = new javax.swing.JLabel();
        etq_nit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelTarifas = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        container_btn_volver2 = new javax.swing.JPanel();
        btnBuscarVehiculoParqueadero = new javax.swing.JButton();

        nitParqueadero2.setText("NO DISPONIBLE");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        tabVehiculosActuales.setBackground(new java.awt.Color(255, 255, 255));
        tabVehiculosActuales.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tabVehiculosActuales.setForeground(new java.awt.Color(0, 0, 0));
        tabVehiculosActuales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "TIKECT", "VEHÍCULO", "PLACA", "TITULAR", "TARIFA", "INGRESO", "TIEMPO/H", "SALIDA"
            }
        ));
        tabVehiculosActuales.setSelectionBackground(new java.awt.Color(61, 103, 71));
        tabVehiculosActuales.setShowGrid(true);
        jScrollPane1.setViewportView(tabVehiculosActuales);

        inputBuscarUpdate.setBackground(new java.awt.Color(255, 255, 255));
        inputBuscarUpdate.setBorder(null);
        inputBuscarUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBuscarUpdateActionPerformed(evt);
            }
        });

        jLabelDireccion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelDireccion.setForeground(new java.awt.Color(61, 103, 71));
        jLabelDireccion.setText("DIRECCIÓN:");

        jLabelNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(61, 103, 71));
        jLabelNombre.setText("NOMBRE:");

        jLabelTelefono.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTelefono.setForeground(new java.awt.Color(61, 103, 71));
        jLabelTelefono.setText("TELÉFONO:");

        jLabelVendedores.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelVendedores.setForeground(new java.awt.Color(61, 103, 71));
        jLabelVendedores.setText("VENDEDORES:");

        etq_nombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etq_nombre.setForeground(new java.awt.Color(0, 0, 0));
        etq_nombre.setText("Null");

        etq_telefono.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etq_telefono.setForeground(new java.awt.Color(0, 0, 0));
        etq_telefono.setText("Null");

        etq_direccion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etq_direccion.setForeground(new java.awt.Color(0, 0, 0));
        etq_direccion.setText("Null");

        etq_vendedor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etq_vendedor.setForeground(new java.awt.Color(0, 0, 0));
        etq_vendedor.setText("Null");

        etq_nit.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        etq_nit.setForeground(new java.awt.Color(0, 0, 0));
        etq_nit.setText("00");

        jLabel1.setBackground(new java.awt.Color(15, 11, 25));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(61, 103, 71));
        jLabel1.setText("NIT:");

        jLabelTarifas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTarifas.setForeground(new java.awt.Color(61, 103, 71));
        jLabelTarifas.setText("PARQUEADERO");

        container_btn_volver2.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setLayout(new java.awt.BorderLayout());

        btnBuscarVehiculoParqueadero.setBackground(new java.awt.Color(61, 103, 71));
        btnBuscarVehiculoParqueadero.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarVehiculoParqueadero.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarVehiculoParqueadero.setText("BUSCAR VEHÍCULO");
        btnBuscarVehiculoParqueadero.setBorder(null);
        btnBuscarVehiculoParqueadero.setContentAreaFilled(false);
        btnBuscarVehiculoParqueadero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarVehiculoParqueadero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVehiculoParqueaderoActionPerformed(evt);
            }
        });
        container_btn_volver2.add(btnBuscarVehiculoParqueadero, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 184, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelDireccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etq_direccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTelefono)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etq_telefono))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etq_nit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelVendedores)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etq_vendedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etq_nombre))
                            .addComponent(jLabelTarifas))
                        .addGap(0, 184, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputBuscarUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTarifas)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etq_nit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNombre)
                    .addComponent(etq_nombre)
                    .addComponent(etq_vendedor)
                    .addComponent(jLabelVendedores))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDireccion)
                    .addComponent(etq_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTelefono)
                    .addComponent(etq_telefono))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputBuscarUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    //Inicializando tabla de vehiculos actuales
    public void initAlternComponets(){
       modelo = (DefaultTableModel) tabVehiculosActuales.getModel();
       
       // Configurar la JTable para ajustar automáticamente el ancho de las columnas
        tabVehiculosActuales.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tabVehiculosActuales.setPreferredScrollableViewportSize(tabVehiculosActuales.getPreferredSize());
        
        this.tabVehiculosActuales.getColumn("SALIDA").setCellRenderer(new ButtonRenderer());
        this.tabVehiculosActuales.getColumn("SALIDA").setCellEditor(new ButtonEditor(new JCheckBox()));
       
       
    }
     
    
    //Botón para buscar vehículo parqueadero
    private void btnBuscarVehiculoParqueaderoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVehiculoParqueaderoActionPerformed
        System.out.println("SE APRETO EL BOTON DE BUSCAR VEHÍCULO");
        String busqueda = inputBuscarUpdate.getText().trim(); 
        
        if (busqueda.isEmpty()) {
            listaVehiculos();
            
        } else {

            Map<String, String> parametros = new HashMap<>();
            parametros.put("busqueda", busqueda);
            String obtenerVehiculo = consumo.consumoGET("http://localhost/API-PRQDR-05/API-voce/buscarVehiculo.php", parametros);
            
            if (obtenerVehiculo != null) {
                JsonObject jsonTemp = gson.fromJson(obtenerVehiculo, JsonObject.class);
                JsonArray vehiculo = jsonTemp.getAsJsonArray("registros");
                modelo.setRowCount(0);

                for (int i = 0; i < vehiculo.size(); i++){
                    JsonObject verVehiculo = vehiculo.get(i).getAsJsonObject();
                    String id = verVehiculo.get(  "id").getAsString();
                    String tipo_vehiculo = verVehiculo.get("tipo_vehiculo").getAsString();
                    String placa = verVehiculo.get("placa").getAsString();
                    String responsable = verVehiculo.get("responsable").getAsString();
                    String Tarifa = verVehiculo.get("Tarifa").getAsString();
                    String create_entrada = verVehiculo.get("create_entrada").getAsString();
                    String tiempo = calcularTiempo(create_entrada);

                    final int posicion = i;
                    
                    JButton btnSalida = new JButton("SALIDA");
                    btnSalida.setBorderPainted(false);
                    btnSalida.setContentAreaFilled(false);
                    btnSalida.setForeground(new Color(255, 0, 0));

                    btnSalida.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            accionClickDarSalida( posicion );
                        }
                    });

                    Object[] fila = new Object[]{id, tipo_vehiculo, placa, responsable, Tarifa, create_entrada, tiempo, btnSalida};
                    modelo.addRow(fila);
                }
            }
        }
    }//GEN-LAST:event_btnBuscarVehiculoParqueaderoActionPerformed

    private void inputBuscarUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputBuscarUpdateActionPerformed
        
    }//GEN-LAST:event_inputBuscarUpdateActionPerformed
    
    public void accionClickDarSalida(int fila){
        // Obtener los datos de la fila seleccionada
        String ticket = (String) modelo.getValueAt(fila, 0);
        String vehiculo = (String) modelo.getValueAt(fila, 1);
        String placa = (String) modelo.getValueAt(fila, 2);
        String titular = (String) modelo.getValueAt(fila, 3);
        String tarifa = (String) modelo.getValueAt(fila, 4);
        String ingreso = (String) modelo.getValueAt(fila, 5);
        String tiempo = (String) modelo.getValueAt(fila, 6);

        System.out.println("Ticket: " + ticket);
        System.out.println("Vehículo: " + vehiculo);
        System.out.println("Placa: " + placa);
        System.out.println("Titular: " + titular);
        System.out.println("Tarifa: " + tarifa);
        System.out.println("Ingreso: " + ingreso);
        System.out.println("Tiempo: " + tiempo);
        
         double tarifaDouble = Double.parseDouble(tarifa);
        
        // Datos de entrada
        double tarifaPorHora = tarifaDouble; // Tarifa 
        String tiempoT = tiempo; 

        // Extraer horas y minutos del tiempo
        int horas = 0;
        int minutos = 0;

        if (tiempoT.contains("h")) {
            String[] partesTiempo = tiempoT.split("h");
            horas = Integer.parseInt(partesTiempo[0].trim());

            if (partesTiempo[1].contains("m")) {
                minutos = Integer.parseInt(partesTiempo[1].replace("m", "").trim());
            }
        } else if (tiempoT.contains("m")) {
            minutos = Integer.parseInt(tiempoT.replace("m", "").trim());
        }

        // Calcular la tarifa
        double tarifaTotal = (horas * tarifaPorHora) + ((minutos > 0) ? tarifaPorHora : 0);

        System.out.println("Tarifa total: $" + tarifaTotal);
        
        Map<String, String> update = new HashMap<>();
        update.put("placa", placa);
        
        
        String verificarT = consumo.consumoGET("http://localhost/API-PRQDR-05/API-tarifas/VerificarTicket.php", update);
        
        System.out.println("EL TICKET RETORNADO: "+verificarT);
        
        if(verificarT != null ){
            
            AlertCobrar alert = new AlertCobrar(placa, tarifaTotal,this, tiempo);
            alert.setVisible(true);
            
        }
        
        
    }
    
   
    public void mostrarDatosParqueadero(){                     
        try {
            System.out.println("nit a panel: " + nit);
            System.out.println("nombre panel : " + nombre);
            System.out.println("direccion panel : " + direccion);
            System.out.println("telefono panel : " + telefono);
            System.out.println("user panel : " + user);            
            etq_nit.setText(nit);
            etq_nombre.setText(nombre);
            etq_direccion.setText(direccion);
            etq_telefono.setText(telefono);
            etq_vendedor.setText(user);
        } catch (Exception e) {
            System.out.println("NO HAY ASIGNACION");
        }
    }
    
     //FUNCIÓN CALCULAR TIEMPO    
    private String calcularTiempo(String ingreso) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        try {
            Date fechaIngreso = format.parse(ingreso);
            Date fechaActual = new Date();
            long diferencia = fechaActual.getTime() - fechaIngreso.getTime();
            long horas = diferencia / (60 * 60 * 1000);
            long minutos = (diferencia % (60 * 60 * 1000)) / (60 * 1000);
            return horas + "h " + minutos + "m";
        } catch (Exception e) {
            System.err.println("Error al calcular tiempo: " + e.getMessage());
            return "";
        }
    }
    
    public void listaVehiculos(){
        ConsumoApi consumo = new ConsumoApi();
        String obtenerVehiculos = consumo.consumoGET("http://localhost/API-PRQDR-05/API-voce/obtenerParqueadero.php");
        
        if (obtenerVehiculos != null){
            JsonObject jsonTemp = gson.fromJson(obtenerVehiculos, JsonObject.class);
            JsonArray parqueadero = jsonTemp.getAsJsonArray("registros");
            modelo.setRowCount(0);
            
            for (int i=0 ; i < parqueadero.size(); i++){
                JsonObject verParqueadero = parqueadero.get(i).getAsJsonObject();
                String id = verParqueadero.get("id").getAsString();
                String tipo_vehiculo = verParqueadero.get("tipo_vehiculo").getAsString();
                String placa = verParqueadero.get("placa").getAsString();
                String responsable = verParqueadero.get("responsable").getAsString();
                String Tarifa = verParqueadero.get("Tarifa").getAsString();
                String create_entrada = verParqueadero.get("create_entrada").getAsString();
                
                String tiempo = calcularTiempo(create_entrada);
                
                final int posicion = i;
                
                JButton btnSalida = new JButton("SALIDA");
                btnSalida.setBorderPainted(false);
                btnSalida.setContentAreaFilled(false);
                btnSalida.setForeground(new Color(255, 0, 0)); 
                
                btnSalida.addActionListener(new ActionListener() {
                      @Override
                      public void actionPerformed(ActionEvent e) {
                          accionClickDarSalida( posicion );
                      }
                  });
                
                Object[] fila = new Object[]{id, tipo_vehiculo, placa, responsable, Tarifa, create_entrada, tiempo, btnSalida};
                modelo.addRow(fila);
                
            }
        }
    }
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarVehiculoParqueadero;
    public javax.swing.JPanel container_btn_volver2;
    public javax.swing.JLabel etq_direccion;
    public javax.swing.JLabel etq_nit;
    private javax.swing.JLabel etq_nombre;
    public javax.swing.JLabel etq_telefono;
    public javax.swing.JLabel etq_vendedor;
    private javax.swing.JTextField inputBuscarUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTarifas;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelVendedores;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel nitParqueadero2;
    private javax.swing.JTable tabVehiculosActuales;
    // End of variables declaration//GEN-END:variables
}
