package controller;

import java.io.File;

public class ControllerDivideArquivoByQuantidadeLinhas extends ControllerDivideArquivo {

    private final int limiteLinhas;
    private int quantidadeLinhasArquivoAtual = 0;
    
    public ControllerDivideArquivoByQuantidadeLinhas(File fileOriginal, char separadorCSV, int limiteLinhas) {
        super(fileOriginal, separadorCSV);
        this.limiteLinhas = limiteLinhas;
    }
    
    @Override
    protected boolean atingiuLimite() {
        return this.quantidadeLinhasArquivoAtual == this.limiteLinhas;
    }

    @Override
    protected void atualizaParametrosLimitador() {
        this.quantidadeLinhasArquivoAtual++;
    }

    @Override
    protected void resetaParametrosLimitador() {
        this.quantidadeLinhasArquivoAtual = 0;
    }
    
}
