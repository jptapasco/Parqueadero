package Main;
import Main.Clases.ButtonEditor;
import Main.Clases.ButtonRenderer;
import com.google.gson.Gson;
import javax.swing.JTable;
import Main.ConsumoApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;


public final class Historial extends javax.swing.JPanel {
    
    public MainVendedor main;
    private ConsumoApi consumo;
    private final Gson gson;
    String id_asignacion;
    DefaultTableModel modelo;
    
    
    
    public Historial(MainVendedor main,String id_asignacion) {
        this.main = main;
        consumo = new ConsumoApi();
        this.id_asignacion = id_asignacion;
        gson = new Gson();
        initComponents();
        initAlternComponets();
        listaHistorial();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nitParqueadero2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        inputBuscarHistorial = new javax.swing.JTextField();
        jLabelDireccion = new javax.swing.JLabel();
        etq_fecha = new javax.swing.JLabel();
        jLabelTarifas = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        container_btn_volver2 = new javax.swing.JPanel();
        btnBuscarHistorial = new javax.swing.JButton();

        nitParqueadero2.setText("NO DISPONIBLE");

        setBackground(new java.awt.Color(255, 255, 255));

        tablaHistorial.setBackground(new java.awt.Color(255, 255, 255));
        tablaHistorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tablaHistorial.setForeground(new java.awt.Color(0, 0, 0));
        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "TICKET", "VEHÍCULO", "PLACA", "TITULAR", "TARIFA", "INGRESO", "SALIDA", "TIEMPO/H", "IMPRIMIR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaHistorial.setSelectionBackground(new java.awt.Color(61, 103, 71));
        tablaHistorial.setShowGrid(true);
        jScrollPane1.setViewportView(tablaHistorial);
        if (tablaHistorial.getColumnModel().getColumnCount() > 0) {
            tablaHistorial.getColumnModel().getColumn(0).setResizable(false);
        }

        inputBuscarHistorial.setBackground(new java.awt.Color(255, 255, 255));
        inputBuscarHistorial.setBorder(null);
        inputBuscarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBuscarHistorialActionPerformed(evt);
            }
        });

        jLabelDireccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelDireccion.setForeground(new java.awt.Color(61, 103, 71));
        jLabelDireccion.setText("FECHA:");

        etq_fecha.setText("NO DISPONIBLE");

        jLabelTarifas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTarifas.setForeground(new java.awt.Color(61, 103, 71));
        jLabelTarifas.setText("HISTORIAL");

        container_btn_volver2.setBackground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setForeground(new java.awt.Color(61, 103, 71));
        container_btn_volver2.setLayout(new java.awt.BorderLayout());

        btnBuscarHistorial.setBackground(new java.awt.Color(113, 0, 234));
        btnBuscarHistorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscarHistorial.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarHistorial.setText("BUSCAR");
        btnBuscarHistorial.setBorder(null);
        btnBuscarHistorial.setContentAreaFilled(false);
        btnBuscarHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarHistorialActionPerformed(evt);
            }
        });
        container_btn_volver2.add(btnBuscarHistorial, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelTarifas)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelDireccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etq_fecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(inputBuscarHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTarifas)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputBuscarHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etq_fecha)
                            .addComponent(jLabelDireccion))
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(container_btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    //BOTÓN BUSCAR VEHICULO
    private void btnBuscarHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHistorialActionPerformed
         String busqueda = inputBuscarHistorial.getText().trim(); 
        
        if (busqueda.isEmpty()) {
            listaHistorial();
            
        } else {

            ConsumoApi consumo = new ConsumoApi();
            Map<String, String> parametros = new HashMap<>();
            parametros.put("busqueda", busqueda);
            String obtenerVehiculo = consumo.consumoGET("http://localhost/API-PRQDR-05/buscarVehiculo.php", parametros);
            
            if (obtenerVehiculo != null) {
                JsonObject jsonTemp = gson.fromJson(obtenerVehiculo, JsonObject.class);
                JsonArray vehiculo = jsonTemp.getAsJsonArray("registros");
                modelo.setRowCount(0);

                for (int i = 0; i < vehiculo.size(); i++){
                    JsonObject verVehiculo = vehiculo.get(i).getAsJsonObject();
                    String id = verVehiculo.get(  "id").getAsString();
                    String placa = verVehiculo.get("placa").getAsString();
                    String createEntrada = verVehiculo.get("create_entrada").getAsString();
                    String salida = verVehiculo.get("salida").getAsString();
                    String tipoVehiculo = verVehiculo.get("tipo_vehiculo").getAsString();
                    String tarifa = verVehiculo.get("Tarifa").getAsString();
                    String responsable = verVehiculo.get("responsable").getAsString();
                    String tiempo = calcularTiempo(createEntrada, salida);

                    final int posicion = i;
                    
                    JButton btnImprimir = new JButton("IMPRIMIR");
                    btnImprimir.setBorderPainted(false);
                    btnImprimir.setContentAreaFilled(false);
                    btnImprimir.setForeground(new Color(39, 80, 49));
                    

                    btnImprimir.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //accionClickBotonImprimir( posicion );
                        }
                    });

                    Object[] fila = new Object[]{id, tipoVehiculo, placa, responsable, tarifa, createEntrada, salida, tiempo, btnImprimir};
                    modelo.addRow(fila);
                }
            }
        }
    }//GEN-LAST:event_btnBuscarHistorialActionPerformed

    private void inputBuscarHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputBuscarHistorialActionPerformed
        String busqueda = inputBuscarHistorial.getText().trim(); 
        
        if (busqueda.isEmpty()) {
            listaHistorial();
            
        } else {
            ConsumoApi consumo = new ConsumoApi();
            Map<String, String> parametros = new HashMap<>();
            parametros.put("busqueda", busqueda);
            String obtenerVehiculo = consumo.consumoGET("http://localhost/API-PRQDR-05/buscarVehiculo.php", parametros);
            
            if (obtenerVehiculo != null) {
                JsonObject jsonTemp = gson.fromJson(obtenerVehiculo, JsonObject.class);
                JsonArray vehiculo = jsonTemp.getAsJsonArray("registros");
                modelo.setRowCount(0);

                for (int i = 0; i < vehiculo.size(); i++){
                    JsonObject verVehiculo = vehiculo.get(i).getAsJsonObject();
                    String id = verVehiculo.get(  "id").getAsString();
                    String placa = verVehiculo.get("placa").getAsString();
                    String createEntrada = verVehiculo.get("create_entrada").getAsString();
                    String salida = verVehiculo.get("salida").getAsString();
                    String tipoVehiculo = verVehiculo.get("tipo_vehiculo").getAsString();
                    String tarifa = verVehiculo.get("Tarifa").getAsString();
                    String responsable = verVehiculo.get("responsable").getAsString();
                    String tiempo = calcularTiempo(createEntrada, salida);

                    final int posicion = i;

                    JButton btnImprimir = new JButton("IMPRIMIR");
                    btnImprimir.setBorderPainted(false);
                    btnImprimir.setContentAreaFilled(false);
                    btnImprimir.setForeground(new Color(39, 80, 49));

                    btnImprimir.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            accionClickBotonImprimir( posicion );
                        }
                    });

                    Object[] fila = new Object[]{id, tipoVehiculo, placa, responsable, tarifa, createEntrada, salida, tiempo, btnImprimir};
                    modelo.addRow(fila);
                }
            }
        }

    }//GEN-LAST:event_inputBuscarHistorialActionPerformed
    
    public void accionClickBotonImprimir(int fila){
        
        String id = (String) modelo.getValueAt(fila, 0);
        String tipo_vh = (String) modelo.getValueAt(fila, 1);
        String placa = (String) modelo.getValueAt(fila, 2);
        String titular = (String) modelo.getValueAt(fila, 3);
        String tarifa = (String) modelo.getValueAt(fila, 4);
        String entrada = (String) modelo.getValueAt(fila, 5);
        String salida = (String) modelo.getValueAt(fila, 6);
        String tiempo = (String) modelo.getValueAt(fila, 7);
        
        Imprimir im = new Imprimir(id,tipo_vh,placa,titular,tarifa,entrada,salida,tiempo, this);
        im.setVisible(true);
        
        main.setVisible(false);
        
    }
    
    private String calcularTiempo(String ingreso, String salida) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date fechaIngreso = format.parse(ingreso);
            Date fechaSalida = format.parse(salida);
            long diferencia = fechaSalida.getTime() - fechaIngreso.getTime();
            long horas = diferencia / (60 * 60 * 1000);
            long minutos = (diferencia % (60 * 60 * 1000)) / (60 * 1000);
            return horas + "h " + minutos + "m";
        } catch (Exception e) {
            return "";
        }
    }
    
    //INICIALIZACIÓN DE COMPONENTES PERSONALIZADOS
    
    //Inicializando tabla de vehiculos actuales
   public void initAlternComponets(){
       modelo = (DefaultTableModel) tablaHistorial.getModel();
       
       //Deshabilita la modificación de columnas en la interfaz
       tablaHistorial.getTableHeader().setReorderingAllowed(false);
       
       // Configurar la JTable para ajustar automáticamente el ancho de las columnas
       tablaHistorial.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

       tablaHistorial.setPreferredScrollableViewportSize(tablaHistorial.getPreferredSize());
        
       this.tablaHistorial.getColumn("IMPRIMIR").setCellRenderer(new ButtonRenderer());
       this.tablaHistorial.getColumn("IMPRIMIR").setCellEditor(new ButtonEditor(new JCheckBox()));
       Date fecha = new Date();
       String fecha2 = fecha.toString();
       etq_fecha.setText(fecha2);
    }
   
   
    private void listaHistorial(){
        ConsumoApi consumo = new ConsumoApi();
        Map<String, String> obtener = new HashMap<>();
        obtener.put("id_asignacion",id_asignacion);
        System.out.println("id_asignacion: "+id_asignacion);
        String obtenerHistorial = consumo.consumoPOST("http://localhost/API-PRQDR-05/API-voce/obtenerHistorial.php",obtener);

        if (obtenerHistorial != null) {
            JsonObject jsonTemp = gson.fromJson(obtenerHistorial, JsonObject.class);
            JsonArray historial = jsonTemp.getAsJsonArray("registros");
            modelo.setRowCount(0);

            for (int i = 0; i < historial.size(); i++){
                JsonObject verHistorial = historial.get(i).getAsJsonObject();
                String id = verHistorial.get(  "id").getAsString();
                String placa = verHistorial.get("placa").getAsString();
                String createEntrada = verHistorial.get("create_entrada").getAsString();
                String salida = verHistorial.get("salida").getAsString();
                String tipoVehiculo = verHistorial.get("tipo_vehiculo").getAsString();
                String tarifa = verHistorial.get("Tarifa").getAsString();
                String responsable = verHistorial.get("responsable").getAsString();
                String tiempo = calcularTiempo(createEntrada, salida);
                
                final int posicion = i;
                
                JButton btnImprimir = new JButton("IMPRIMIR");
                btnImprimir.setBorderPainted(false);
                btnImprimir.setContentAreaFilled(false);
                btnImprimir.setForeground(new Color(39, 80, 49));
                
                btnImprimir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accionClickBotonImprimir( posicion );
                    }
                });

                Object[] fila = new Object[]{id, tipoVehiculo, placa, responsable, tarifa, createEntrada, salida, tiempo, btnImprimir};
                modelo.addRow(fila);
            }
        }
    }
    
    
    
    //Botón para buscar vehículo parqueadero
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarHistorial;
    public javax.swing.JPanel container_btn_volver2;
    private javax.swing.JLabel etq_fecha;
    private javax.swing.JTextField inputBuscarHistorial;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelTarifas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel nitParqueadero2;
    private javax.swing.JTable tablaHistorial;
    // End of variables declaration//GEN-END:variables

}
