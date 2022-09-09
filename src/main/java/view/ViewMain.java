package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.EnumTipoDivisao;
import model.EnumSeparadorCSV;

public class ViewMain extends javax.swing.JFrame {

    public ViewMain() {
        this.initComponents();
        this.loadTiposCombobox();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNomeArquivo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtDivisor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSelecionar = new javax.swing.JButton();
        btnProcessar = new javax.swing.JButton();
        btnSobre = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbSeparador = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("dividir para conquistar");

        txtNomeArquivo.setEditable(false);
        txtNomeArquivo.setEnabled(false);

        jLabel1.setText("Tipo:");

        jLabel2.setText("Limite de divisão:");

        jLabel3.setText("Arquivo:");

        btnSelecionar.setText("Selecionar");

        btnProcessar.setText("Processar");

        btnSobre.setText("Sobre");

        jLabel4.setText("Separador:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNomeArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelecionar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSeparador, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDivisor, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnProcessar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSobre, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnSelecionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtDivisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProcessar)
                    .addComponent(btnSobre)
                    .addComponent(jLabel4)
                    .addComponent(cbSeparador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loadTiposCombobox() {
        this.cbTipo.setModel(
            new DefaultComboBoxModel<>(
                new EnumTipoDivisao[] {
                    EnumTipoDivisao.LINHAS,
                    EnumTipoDivisao.TAMANHO
                }
            )
        );
        this.cbSeparador.setModel(
            new DefaultComboBoxModel<>(
                new EnumSeparadorCSV[] {
                    EnumSeparadorCSV.PONTO_E_VIRGULA,
                    EnumSeparadorCSV.VIRGULA
                }
            )
        );
    }

    public JButton getBtnSelecionar() {
        return btnSelecionar;
    }

    public JComboBox<EnumTipoDivisao> getCbTipo() {
        return cbTipo;
    }

    public JComboBox<EnumSeparadorCSV> getCbSeparador() {
        return cbSeparador;
    }

    public JTextField getTxtDivisor() {
        return txtDivisor;
    }

    public JTextField getTxtNomeArquivo() {
        return txtNomeArquivo;
    }

    public JButton getBtnProcessar() {
        return btnProcessar;
    }

    public JButton getBtnSobre() {
        return btnSobre;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcessar;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JButton btnSobre;
    private javax.swing.JComboBox<EnumSeparadorCSV> cbSeparador;
    private javax.swing.JComboBox<EnumTipoDivisao> cbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtDivisor;
    private javax.swing.JTextField txtNomeArquivo;
    // End of variables declaration//GEN-END:variables
}
