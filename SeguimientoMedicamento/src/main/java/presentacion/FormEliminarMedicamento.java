/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dto.MedicamentoDTO;
import javax.swing.JOptionPane;
import negocio.MedicamentoBO;

/**
 *
 * @author ITSON
 */
public class FormEliminarMedicamento extends javax.swing.JFrame {

    private String flujo;
    private MedicamentoBO medicamentoBO;
    private MedicamentoDTO medicamentoDTO;
    
    /**
     * Creates new form FormEliminarMedicamento
     */
    public FormEliminarMedicamento(String flujo, MedicamentoDTO medicamentoDTO) {
        initComponents();
        this.flujo = flujo;
        this.medicamentoDTO = medicamentoDTO;
        medicamentoBO = new MedicamentoBO();
        
        labelNombre.setText("Nombre: "+medicamentoDTO.getNombre());
        labelTipoConsumo.setText("Tipo de Consumo: "+medicamentoDTO.getTipoConsumo());
        labelCantidad.setText("Cantidad: "+medicamentoDTO.getCantidad());
        labelFrecuencia.setText("Frecuencia: "+medicamentoDTO.getFrecuencia());
        labelPrimeraDosis.setText("Hora de Primera Dosis: "+medicamentoDTO.getHoraPrimeraDosis());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panFondo4 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        labelTitulo1 = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelTipoConsumo = new javax.swing.JLabel();
        labelCantidad = new javax.swing.JLabel();
        labelFrecuencia = new javax.swing.JLabel();
        labelPrimeraDosis = new javax.swing.JLabel();
        labelPrimeraDosis1 = new javax.swing.JLabel();
        labelPrimeraDosis2 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panFondo4.setBackground(new java.awt.Color(204, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        labelTitulo.setText("Increible Sistema de Toma de Medicamentos");

        btnRegresar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        labelTitulo1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelTitulo1.setText("Eliminar Medicamento");

        labelNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelNombre.setText("Nombre:");

        labelTipoConsumo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelTipoConsumo.setText("Tipo de Consumo:");

        labelCantidad.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelCantidad.setText("Cantidad:");

        labelFrecuencia.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelFrecuencia.setText("Frecuencia:");

        labelPrimeraDosis.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelPrimeraDosis.setText("Hora de Primera Dosis:");

        labelPrimeraDosis1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        labelPrimeraDosis1.setText("Seguro que quieres eliminar este medicamento?");

        labelPrimeraDosis2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelPrimeraDosis2.setForeground(new java.awt.Color(255, 51, 51));
        labelPrimeraDosis2.setText("Una vez eliminado no se puede recuperar!!!");

        btnEliminar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar Medicamento");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panFondo4Layout = new javax.swing.GroupLayout(panFondo4);
        panFondo4.setLayout(panFondo4Layout);
        panFondo4Layout.setHorizontalGroup(
            panFondo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondo4Layout.createSequentialGroup()
                .addGroup(panFondo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panFondo4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar))
                    .addGroup(panFondo4Layout.createSequentialGroup()
                        .addGroup(panFondo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panFondo4Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panFondo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTitulo1)
                                    .addComponent(labelTitulo)
                                    .addGroup(panFondo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelNombre)
                                        .addComponent(labelTipoConsumo)
                                        .addComponent(labelCantidad)
                                        .addComponent(labelFrecuencia)
                                        .addComponent(labelPrimeraDosis))))
                            .addGroup(panFondo4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelPrimeraDosis1))
                            .addGroup(panFondo4Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(labelPrimeraDosis2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panFondo4Layout.setVerticalGroup(
            panFondo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondo4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTitulo1)
                .addGap(18, 18, 18)
                .addComponent(labelNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTipoConsumo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCantidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPrimeraDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelPrimeraDosis1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPrimeraDosis2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(panFondo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panFondo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panFondo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        FormObtenerMedicamento formObtenerMedicamento = new FormObtenerMedicamento(flujo);
        formObtenerMedicamento.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (medicamentoBO.EliminarMedicamento(medicamentoDTO)){
            JOptionPane.showMessageDialog(null, "¡Medicamento agregado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            FormObtenerMedicamento formObtenerMedicamento = new FormObtenerMedicamento(flujo);
            formObtenerMedicamento.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "No se pudo Eliminar el medicamento. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel labelCantidad;
    private javax.swing.JLabel labelFrecuencia;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelPrimeraDosis;
    private javax.swing.JLabel labelPrimeraDosis1;
    private javax.swing.JLabel labelPrimeraDosis2;
    private javax.swing.JLabel labelTipoConsumo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTitulo1;
    private javax.swing.JPanel panFondo4;
    // End of variables declaration//GEN-END:variables
}
