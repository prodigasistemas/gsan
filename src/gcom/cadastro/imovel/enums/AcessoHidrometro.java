package gcom.cadastro.imovel.enums;

public enum AcessoHidrometro {
    BOM  ((short) 1, "Bom"), 
    RUIM ((short) 2, "Ruim"), 
    SEM  ((short) 3, "Sem"); 
    
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
