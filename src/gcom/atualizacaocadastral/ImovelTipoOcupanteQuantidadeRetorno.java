package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.IImovelTipoOcupanteQuantidade;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelTipoOcupante;

public class ImovelTipoOcupanteQuantidadeRetorno implements IImovelTipoOcupanteQuantidade{

    private Integer id;

    private Integer quantidade;
    
    private Imovel imovel;
    
    private ImovelTipoOcupante tipoOcupante;
    
    private Integer idImovelRetorno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public ImovelTipoOcupante getTipoOcupante() {
        return tipoOcupante;
    }

    public void setTipoOcupante(ImovelTipoOcupante tipoOcupante) {
        this.tipoOcupante = tipoOcupante;
    }

    public Integer getIdImovelRetorno() {
        return idImovelRetorno;
    }

    public void setIdImovelRetorno(Integer idImovelRetorno) {
        this.idImovelRetorno = idImovelRetorno;
    }
}
