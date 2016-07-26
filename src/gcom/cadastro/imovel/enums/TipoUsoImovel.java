package gcom.cadastro.imovel.enums;

public enum TipoUsoImovel {
    DORMITORIO ((short) 1, "DORMITORIO"), 
    MORADA     ((short) 2, "MORADA"), 
    VERANEIO   ((short) 3, "VERANEIO"), 
    OUTROS     ((short) 4, "OUTROS");
    
    private short id;
    private String descricao;
    
    TipoUsoImovel(short id, String desc){
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
