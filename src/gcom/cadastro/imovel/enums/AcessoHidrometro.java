package gcom.cadastro.imovel.enums;

public enum AcessoHidrometro {
    BOM  ((short) 1, "BOM"), 
    RUIM ((short) 2, "RUIM"), 
    SEM  ((short) 3, "SEM"); 
    
    private short id;
    private String descricao;
    
    AcessoHidrometro(short id, String desc){
        this.id        = id;
        this.descricao = desc;
    }

    public short getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
