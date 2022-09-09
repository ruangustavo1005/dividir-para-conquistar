package model;

public enum EnumSeparadorCSV {
    VIRGULA("Vírgula", ','),
    PONTO_E_VIRGULA("Ponto e vírgula", ';');
    
    private final String descricao;
    private final char separador;

    private EnumSeparadorCSV(String descricao, char separador) {
        this.descricao = descricao;
        this.separador = separador;
    }

    public char getSeparador() {
        return separador;
    }

    @Override
    public String toString() {
        return this.descricao + " (" + this.separador + ")";
    }
}
