package gcom.cadastro.imovel.enums;

public enum TipoUsoImovel {
    DORMITORIO ((short) 1, "Dormitorio"), 
    MORADA     ((short) 2, "Morada"), 
    VERANEIO   ((short) 3, "Veraneio"), 
    OUTROS     ((short) 4, "Outros");
    
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
