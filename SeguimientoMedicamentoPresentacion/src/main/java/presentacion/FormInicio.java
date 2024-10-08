package presentacion;

import dto.UsuarioDTO;

public class FormInicio extends javax.swing.JFrame {

    private UsuarioDTO usuarioDTO;
    
    
    public FormInicio(UsuarioDTO usuarioDTO) {
        initComponents();
        this.usuarioDTO = usuarioDTO;
       
        labelSubTitulo.setText("¡Bienvenido "+ usuarioDTO.getNombreUsuario()+"!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panFondo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelSubTitulo = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnTomar = new javax.swing.JButton();
        btnProximaDosis = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panFondo.setBackground(new java.awt.Color(204, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        labelTitulo.setText("Increible Sistema de Toma de Medicamentos");

        labelSubTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelSubTitulo.setText("Bienvenido Usuario!");

        btnAgregar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar Medicamento");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar Medicamento");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnEditar.setText("Editar Medicamento");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnTomar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnTomar.setText("Tomar Medicamento");
        btnTomar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTomarActionPerformed(evt);
            }
        });

        btnProximaDosis.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnProximaDosis.setText("Proxima Dosis del Medicamento");
        btnProximaDosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximaDosisActionPerformed(evt);
            }
        });

        btnCerrarSesion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panFondoLayout = new javax.swing.GroupLayout(panFondo);
        panFondo.setLayout(panFondoLayout);
        panFondoLayout.setHorizontalGroup(
            panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondoLayout.createSequentialGroup()
                .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSubTitulo)
                    .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panFondoLayout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnProximaDosis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTomar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panFondoLayout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addComponent(btnCerrarSesion))))
                        .addGroup(panFondoLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(labelTitulo))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panFondoLayout.setVerticalGroup(
            panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSubTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTomar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProximaDosis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrarSesion)
                .addGap(13, 13, 13))
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        FormAgregarMedicamento formAgregarMedicamento = new FormAgregarMedicamento(usuarioDTO);
        formAgregarMedicamento.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        FormObtenerMedicamento formObtenerMedicamento = new FormObtenerMedicamento("Eliminar", usuarioDTO);
        formObtenerMedicamento.setVisible(true);
        this.dispose();
        
//        FormEliminarMedicamento formEliminarMedicamento = new FormEliminarMedicamento();
//        formEliminarMedicamento.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        FormObtenerMedicamento formObtenerMedicamento = new FormObtenerMedicamento("Editar", usuarioDTO);
        formObtenerMedicamento.setVisible(true);
        this.dispose();
        
//        FormEditarMedicamento formEditarMedicamento = new FormEditarMedicamento();
//        formEditarMedicamento.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnTomarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTomarActionPerformed
        FormObtenerMedicamento formObtenerMedicamento = new FormObtenerMedicamento("Tomar", usuarioDTO);
        formObtenerMedicamento.setVisible(true);
        this.dispose();
        
//        FormTomarMedicamento formTomarMedicamento = new FormTomarMedicamento();
//        formTomarMedicamento.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnTomarActionPerformed

    private void btnProximaDosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximaDosisActionPerformed
        FormObtenerMedicamento formObtenerMedicamento = new FormObtenerMedicamento("Proxima Dosis", usuarioDTO);
        formObtenerMedicamento.setVisible(true);
        this.dispose();
        
//        FormProximaDosis proxima = new FormProximaDosis();
//        proxima.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnProximaDosisActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        FormIniciarSesion formIniciarSesion = new FormIniciarSesion();
        formIniciarSesion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnProximaDosis;
    private javax.swing.JButton btnTomar;
    private javax.swing.JLabel labelSubTitulo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panFondo;
    // End of variables declaration//GEN-END:variables
}
