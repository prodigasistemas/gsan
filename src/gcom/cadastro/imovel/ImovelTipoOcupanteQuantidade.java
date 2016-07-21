package gcom.cadastro.imovel;

import java.util.Date;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

@ControleAlteracao()
public class ImovelTipoOcupanteQuantidade extends ObjetoTransacao{
    private static final long serialVersionUID = 1199546479830159223L;

    private Integer id;

    @ControleAlteracao(funcionalidade={Imovel.ATRIBUTOS_IMOVEL_INSERIR, Imovel.ATRIBUTOS_IMOVEL_ATUALIZAR,Imovel.ATRIBUTOS_IMOVEL_REMOVER,Imovel.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
    private Integer quantidade;
    
    private Imovel imovel;
    
    private ImovelTipoOcupante tipoOcupante;
    
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