package gcom.cadastro.imovel.enums;

public enum ClasseSocial {
    ALTA ((short) 1, "Alta"), 
    MEDIA((short) 2, "Media"), 
    BAIXA((short) 3, "Baixa"), 
    SUB  ((short) 4, "Sub");
    
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
