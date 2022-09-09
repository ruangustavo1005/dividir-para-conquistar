package factory;

import controller.ControllerDivideArquivo;
import controller.ControllerDivideArquivoByQuantidadeLinhas;
import controller.ControllerDivideArquivoByTamanhoArquivo;
import java.io.File;
import model.EnumTipoDivisao;

public abstract class FactoryControllerDivideArquivo {
    
    public static ControllerDivideArquivo createByTipoDivisao(EnumTipoDivisao tipoDivisao, File file, char separadorCSV, String limitador) {
        switch (tipoDivisao) {
            case LINHAS -> {
                return new ControllerDivideArquivoByQuantidadeLinhas(file, separadorCSV, Integer.parseInt(limitador));
            }
            case TAMANHO -> {
                return new ControllerDivideArquivoByTamanhoArquivo(file, separadorCSV, Float.parseFloat(limitador.replace(',', '.')));
            }
        }
        return null;
    }
    
}
