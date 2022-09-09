package controller;

import com.opencsv.exceptions.CsvValidationException;
import exception.ExceptionArquivoJaExiste;
import exception.ExceptionEntradaInvalida;
import factory.FactoryControllerDivideArquivo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.EnumSeparadorCSV;
import model.EnumTipoDivisao;
import util.NumberUtils;
import view.ViewMain;

public class ControllerMain {
    
    private final ViewMain view;
    private File arquivoOriginal;
    private EnumTipoDivisao tipoDivisao;
    private EnumSeparadorCSV separadorCSV;
    private String limiteDivisao;

    public ControllerMain() {
        this.view = new ViewMain();
        this.addListeners();
    }

    public void abreTela() {
        this.view.setVisible(true);
    }
    
    private void addListeners() {
        this.addListenerBtnSelecionar();
        this.addListenerBtnSobre();
        this.addListenerBtnProcessar();
    }
    
    private void addListenerBtnSelecionar() {
        this.view.getBtnSelecionar().addActionListener((e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Texto CSV", "csv"));
            
            int escolha = chooser.showOpenDialog(null);
            if (escolha == JFileChooser.APPROVE_OPTION) {
                this.setArquivoOriginal(chooser.getSelectedFile());
            }
        });
    }

    private void addListenerBtnSobre() {
        this.view.getBtnSobre().addActionListener((e) -> {
            JOptionPane.showMessageDialog(this.view, "Este utilitário foi desenvolvido por Ruan Gustavo Moretti (ruan.moretti@magazord.com.br)\n\nDúvidas e sugestões são bem vindas e podem ser enviadas para o mesmo", "Info", JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
    private void addListenerBtnProcessar() {
        this.view.getBtnProcessar().addActionListener((e) -> {
            try {
                this.validaEntradas();
                this.carregaParametros();
                this.processaArquivo();
                this.showSuccessMessage();
            }
            catch (ExceptionEntradaInvalida | ExceptionArquivoJaExiste exception) {
                JOptionPane.showMessageDialog(this.view, exception.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
            }
            catch (FileNotFoundException exception) {
                JOptionPane.showMessageDialog(this.view, "O arquivo a ser separado não foi encontrado", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
            catch (IOException exception) {
                JOptionPane.showMessageDialog(this.view, "Houve um erro não esperado ao tentar ler/escrever os arquivos: " + exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            catch (CsvValidationException exception) {
                JOptionPane.showMessageDialog(this.view, "Houve um erro de validação de CSV: " + exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void validaEntradas() throws ExceptionEntradaInvalida {
        this.validaArquivoSelecionado();
        this.validaTipoSelecionado();
        this.validaSeparadorCSV();
        this.validaLimiteDivisao();
    }
    
    private void validaArquivoSelecionado() throws ExceptionEntradaInvalida {
        if (this.arquivoOriginal == null) {
            throw new ExceptionEntradaInvalida("Selecione um arquivo válido para processar");
        }
    }
    
    private void validaTipoSelecionado() throws ExceptionEntradaInvalida {
        int indexSelecionado = this.view.getCbTipo().getSelectedIndex();
        if (indexSelecionado < 0) {
            throw new ExceptionEntradaInvalida("Selecione um tipo de divisão para processar");
        }
        EnumTipoDivisao tipo = this.view.getCbTipo().getItemAt(indexSelecionado);
        if (tipo != EnumTipoDivisao.LINHAS && tipo != EnumTipoDivisao.TAMANHO) {
            throw new ExceptionEntradaInvalida("Selecione um tipo de divisão para processar");
        }
    }
    
    
    private void validaSeparadorCSV() throws ExceptionEntradaInvalida {
        int indexSelecionado = this.view.getCbSeparador().getSelectedIndex();
        if (indexSelecionado < 0) {
            throw new ExceptionEntradaInvalida("Selecione um separador para processar");
        }
        EnumSeparadorCSV separador = this.view.getCbSeparador().getItemAt(indexSelecionado);
        if (separador != EnumSeparadorCSV.VIRGULA && separador != EnumSeparadorCSV.PONTO_E_VIRGULA) {
            throw new ExceptionEntradaInvalida("Selecione um separador para processar");
        }
    }
    
    private void validaLimiteDivisao() throws ExceptionEntradaInvalida {
        String divisor = this.view.getTxtDivisor().getText().trim();
        if (divisor.isEmpty()) {
            throw new ExceptionEntradaInvalida("Informa um limite de divisão");
        }
        
        // nesse ponto o index selecionado já foi validado
        EnumTipoDivisao tipo = this.view.getCbTipo().getItemAt(this.view.getCbTipo().getSelectedIndex());
        switch (tipo) {
            case LINHAS -> {
                if (!NumberUtils.isInt(divisor)) {
                    throw new ExceptionEntradaInvalida("Informa um limite de divisão válido (somente números, ex: 500)");
                }
            }
            case TAMANHO -> {
                if (!NumberUtils.isFloat(divisor)) {
                    throw new ExceptionEntradaInvalida("Informa um limite de divisão válido (somente números, podendo ser valores quebrados, ex: 1,5)");
                }
            }
        }
    }
    
    private void carregaParametros() {
        this.tipoDivisao = this.view.getCbTipo().getItemAt(this.view.getCbTipo().getSelectedIndex());
        this.separadorCSV = this.view.getCbSeparador().getItemAt(this.view.getCbSeparador().getSelectedIndex());
        this.limiteDivisao = this.view.getTxtDivisor().getText().trim();
    }
    
    private void processaArquivo() throws IOException, FileNotFoundException, ExceptionArquivoJaExiste, CsvValidationException {
        ControllerDivideArquivo controllerDivideArquivo = FactoryControllerDivideArquivo.createByTipoDivisao(
            this.tipoDivisao,
            this.arquivoOriginal,
            this.separadorCSV.getSeparador(),
            this.limiteDivisao
        );
        controllerDivideArquivo.divideArquivo();
    }
    
    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(this.view, "Arquivo divido com sucesso!\n\nVerifique o diretório onde o arquivo estava localizado para visualizar os arquivos resultantes", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public File getArquivoOriginal() {
        return arquivoOriginal;
    }

    public void setArquivoOriginal(File arquivoOriginal) {
        this.arquivoOriginal = arquivoOriginal;
        this.view.getTxtNomeArquivo().setText(this.arquivoOriginal.getAbsolutePath());
    }

}
