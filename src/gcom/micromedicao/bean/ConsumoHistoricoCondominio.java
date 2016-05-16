package gcom.micromedicao.bean;

import java.io.Serializable;

import gcom.micromedicao.consumo.LigacaoTipo;

public class ConsumoHistoricoCondominio implements Serializable{
    private static final long serialVersionUID = -8120340054578342736L;

    private Integer idImovelCondominio;
    
    private Integer referencia;
    
    private LigacaoTipo tipoLigacao;
    
    public ConsumoHistoricoCondominio() {
    }
    
    public ConsumoHistoricoCondominio(Integer idImovelCondominio, Integer referencia, LigacaoTipo tipoLigacao) {
        super();
        this.idImovelCondominio = idImovelCondominio;
        this.referencia = referencia;
        this.tipoLigacao = tipoLigacao;
    }

    public Integer getIdImovelCondominio() {
        return idImovelCondominio;
    }

    public void setIdImovelCondominio(Integer idImovelCondominio) {
        this.idImovelCondominio = idImovelCondominio;
    }

    public Integer getReferencia() {
        return referencia;
    }

    public void setReferencia(Integer referencia) {
        this.referencia = referencia;
    }

    public LigacaoTipo getTipoLigacao() {
        return tipoLigacao;
    }

    public void setTipoLigacao(LigacaoTipo tipoLigacao) {
        this.tipoLigacao = tipoLigacao;
    }
}