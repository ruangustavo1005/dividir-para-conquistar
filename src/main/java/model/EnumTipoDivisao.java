package model;

public enum EnumTipoDivisao {
    LINHAS("Linhas"),
    TAMANHO("Tamanho (em MB)");
    
    private final String descricao;

    private EnumTipoDivisao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
