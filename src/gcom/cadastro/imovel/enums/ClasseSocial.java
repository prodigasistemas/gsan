package gcom.cadastro.imovel.enums;

public enum ClasseSocial {
    ALTA ((short) 1, "ALTA"), 
    MEDIA((short) 2, "MEDIA"), 
    BAIXA((short) 3, "BAIXA"), 
    SUB  ((short) 4, "SUB");
    
    private short id;
    private String descricao;
    
    ClasseSocial(short id, String desc){
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
