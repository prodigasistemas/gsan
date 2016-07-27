package gcom.cadastro.imovel;

import java.util.Date;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

@ControleAlteracao()
public class ImovelTipoOcupanteQuantidadeAtualizacaoCadastral extends ObjetoTransacao implements IImovelTipoOcupanteQuantidade{
    private static final long serialVersionUID = 1199546479830159223L;

    public static final int ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = 1502;
    
    private Integer id;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer quantidade;
    
    private Imovel imovel;
    
    private ImovelTipoOcupante tipoOcupante;
    
    private Integer idImovelRetorno;
    
    public ImovelTipoOcupanteQuantidadeAtualizacaoCadastral() {
    }
    
    public ImovelTipoOcupanteQuantidadeAtualizacaoCadastral(Integer quantidade, Imovel imovel, ImovelTipoOcupante tipoOcupante) {
        super();
        this.quantidade = quantidade;
        this.imovel = imovel;
        this.tipoOcupante = tipoOcupante;
    }

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

    public String[] retornaCamposChavePrimaria() {
        return new String[]{ "id" };
    }

    @Override
    public Filtro retornaFiltro() {
        return null;
    }

    @Override
    public Date getUltimaAlteracao() {
        return null;
    }

    @Override
    public void setUltimaAlteracao(Date ultimaAlteracao) {
    }
}