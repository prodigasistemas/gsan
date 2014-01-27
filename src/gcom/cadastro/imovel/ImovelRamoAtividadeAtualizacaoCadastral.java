package gcom.cadastro.imovel;

import gcom.atualizacaocadastral.IImovelRamoAtividade;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;


@ControleAlteracao()
public class ImovelRamoAtividadeAtualizacaoCadastral extends ObjetoTransacao implements IImovelRamoAtividade {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = 1502;

    private Integer id;

	private Integer idImovel;
    
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idRamoAtividade;

    private Date ultimaAlteracao;
    
    private ImovelRamoAtividadePK comp_id;
    
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdRamoAtividade() {
		return idRamoAtividade;
	}

	public void setIdRamoAtividade(Integer idRamoAtividade) {
		this.idRamoAtividade = idRamoAtividade;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroTabela.ID, this.getIdImovel()));
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	public ImovelRamoAtividadePK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ImovelRamoAtividadePK comp_id) {
		this.comp_id = comp_id;
	}
	
}
