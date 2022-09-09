package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ControllerDivideArquivoByTamanhoArquivo extends ControllerDivideArquivo {

    private final long limiteTamanho;
    private long tamanhoArquivoAtual;
    
    public ControllerDivideArquivoByTamanhoArquivo(File fileOriginal, char separadorCSV, float limiteTamanho) {
        super(fileOriginal, separadorCSV);
        this.limiteTamanho = (long) (limiteTamanho * 1007616);
    }
    
    @Override
    protected boolean atingiuLimite() {
        return this.tamanhoArquivoAtual >= this.limiteTamanho;
    }

    @Override
    protected void atualizaParametrosLimitador() {
        try {
            this.tamanhoArquivoAtual = Files.size(this.getFileAtual().toPath());
        } catch (IOException ex) {
            System.out.println("Não foi possível pegar o tamanho do arquivo atual");
        }
    }

    @Override
    protected void resetaParametrosLimitador() {
        this.tamanhoArquivoAtual = 0;
    }
    
}
