package gcom.micromedicao;

import gcom.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class RotaAtualizacaoSeq implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;
    
    /** persistent field */
    private Imovel imovel;
    
    /** persistent field */
    private Rota rota;

    /** persistent field */
    private int amFaturamento;

    /** persistent field */
    private int sequencialRota;

    /** persistent field */
    private Date dtUltimaAlteracao;

    /** default constructor */
    public RotaAtualizacaoSeq() {
    }

    public int getAmFaturamento() {
        return amFaturamento;
    }

    public void setAmFaturamento(int amfaturamento) {
        this.amFaturamento = amfaturamento;
    }

    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSequencialRota() {
        return sequencialRota;
    }

    public void setSequencialRota(int sequencialRota) {
        this.sequencialRota = sequencialRota;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public RotaAtualizacaoSeq(Integer id, Imovel imovel, Rota rota, int amFaturamento, int sequencialRota, Date dtUltimaAlteracao) {
        super();
        // TODO Auto-generated constructor stub
        this.id = id;
        this.imovel = imovel;
        this.rota = rota;
        this.amFaturamento = amFaturamento;
        this.sequencialRota = sequencialRota;
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


}
