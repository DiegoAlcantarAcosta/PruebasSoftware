package presentacion;

import casoDeUsoMarcarTomado.CasoDeUsoMarcarTomado;
import casoDeUsoMarcarTomado.CasoDeUsoMarcarTomadoException;
import casoDeUsoMarcarTomado.ICasoDeUsoMarcarTomado;
import dto.MedicamentoDTO;
import dto.RegistroDTO;
import dto.UsuarioDTO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FormTomarMedicamento extends javax.swing.JFrame {

    private String flujo;
    private MedicamentoDTO medicamentoDTO;
    private ICasoDeUsoMarcarTomado casoDeUsoMarcarTomado;
    private UsuarioDTO usuarioDTO;
    
    /**
     * Creates new form FormTomarMedicamento
     */
    public FormTomarMedicamento(String flujo, MedicamentoDTO medicamentoDTO, UsuarioDTO usuarioDTO) {
        
        initComponents();
        this.flujo = flujo;
        this.medicamentoDTO = medicamentoDTO;
        casoDeUsoMarcarTomado = new CasoDeUsoMarcarTomado();
        this.usuarioDTO = usuarioDTO;
        
        nombreField.setText(""+medicamentoDTO.getNombre());
        consumoField.setText(""+medicamentoDTO.getTipoConsumo());
        cantidadField.setText(""+medicamentoDTO.getCantidad());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        panFondo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        labelTitulo1 = new javax.swing.JLabel();
        labelCantidad = new javax.swing.JLabel();
        labelTipoConsumo = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelTitulo2 = new javax.swing.JLabel();
        btnRegresar1 = new javax.swing.JButton();
        labelTitulo3 = new javax.swing.JLabel();
        cantidadField = new javax.swing.JLabel();
        nombreField = new javax.swing.JLabel();
        consumoField = new javax.swing.JLabel();

        jLabel3.setText("-----");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panFondo.setBackground(new java.awt.Color(204, 255, 255));

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
        labelTitulo1.setText("Tomar Medicamento");

        labelCantidad.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelCantidad.setText("Cantidad:");

        labelTipoConsumo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelTipoConsumo.setText("Tipo de Consumo:");

        labelNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelNombre.setText("Nombre:");

        labelTitulo2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        labelTitulo2.setText("¿Quieres marcar como tomado este");

        btnRegresar1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnRegresar1.setText("Marcar Medicamento como Tomado");
        btnRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar1ActionPerformed(evt);
            }
        });

        labelTitulo3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        labelTitulo3.setText("Medicamento?");

        cantidadField.setText("-----");

        nombreField.setText("-----");

        consumoField.setText("-----");

        javax.swing.GroupLayout panFondoLayout = new javax.swing.GroupLayout(panFondo);
        panFondo.setLayout(panFondoLayout);
        panFondoLayout.setHorizontalGroup(
            panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondoLayout.createSequentialGroup()
                .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panFondoLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelTitulo1)
                                .addComponent(labelTitulo)
                                .addGroup(panFondoLayout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelNombre)
                                        .addComponent(labelTipoConsumo)
                                        .addComponent(labelCantidad))
                                    .addGap(18, 18, 18)
                                    .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cantidadField)
                                        .addComponent(nombreField)
                                        .addComponent(consumoField)))))
                        .addGroup(panFondoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnRegresar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegresar1)))
                    .addGroup(panFondoLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panFondoLayout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(labelTitulo3))
                            .addComponent(labelTitulo2))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panFondoLayout.setVerticalGroup(
            panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTitulo1)
                .addGap(18, 18, 18)
                .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(nombreField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipoConsumo)
                    .addComponent(consumoField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCantidad)
                    .addComponent(cantidadField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(labelTitulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTitulo3)
                .addGap(18, 18, 18)
                .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnRegresar1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        FormObtenerMedicamento formObtenerMedicamento = new FormObtenerMedicamento(flujo, usuarioDTO);
        formObtenerMedicamento.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar1ActionPerformed
        RegistroDTO registroDTO=new RegistroDTO(new Date(), medicamentoDTO.getCantidad(), true);
        try {
            if (casoDeUsoMarcarTomado.tomaDeMedicamento(registroDTO, medicamentoDTO, usuarioDTO)){
                JOptionPane.showMessageDialog(null, "¡Medicamento Tomado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                FormObtenerMedicamento formObtenerMedicamento = new FormObtenerMedicamento(flujo, usuarioDTO);
                formObtenerMedicamento.setVisible(true);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "No se pudo Tomar el medicamento. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (CasoDeUsoMarcarTomadoException ex) {
            Logger.getLogger(FormTomarMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegresar1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnRegresar1;
    private javax.swing.JLabel cantidadField;
    private javax.swing.JLabel consumoField;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelCantidad;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTipoConsumo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTitulo1;
    private javax.swing.JLabel labelTitulo2;
    private javax.swing.JLabel labelTitulo3;
    private javax.swing.JLabel nombreField;
    private javax.swing.JPanel panFondo;
    // End of variables declaration//GEN-END:variables
}
