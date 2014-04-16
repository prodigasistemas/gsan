package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class AtualizarEspecificacaoSituacaoImovelActionForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;

	// Dados da Especificao Situacao Imovel
	private String idEspecificacao;
	private String descricaoEspecificacao;
    
    // Especificacao Imovel Situacao Criterior
    private Collection<EspecificacaoImovSitCriterio> especificacaoImovelSituacaoCriterio 
    	= new ArrayList<EspecificacaoImovSitCriterio>();
    
    private Collection<EspecificacaoImovSitCriterio> especificacaoImovelSituacaoCriterioRemovidas = 
    	new ArrayList<EspecificacaoImovSitCriterio>();
    
    // Usado na Tela de Atualizar
    private String idAtualizar;
    private String idEspecificacaoCriterio;
    private String indicadorHidrometroLigacaoAgua;
    private String indicadorHidrometroPoco;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;

    
    private String tamanhoColecao = "0";
    private String deleteSituacaoCriterioImovel;

	
	public String getDeleteSituacaoCriterioImovel() {
		return deleteSituacaoCriterioImovel;
	}
	public void setDeleteSituacaoCriterioImovel(String deleteSituacaoCriterioImovel) {
		this.deleteSituacaoCriterioImovel = deleteSituacaoCriterioImovel;
	}
	public String getTamanhoColecao() {
		return tamanhoColecao;
	}
	public void setTamanhoColecao(String tamanhoColecao) {
		this.tamanhoColecao = tamanhoColecao;
	}
	public String getDescricaoEspecificacao() {
		return descricaoEspecificacao;
	}
	public void setDescricaoEspecificacao(String descricaoEspecificacao) {
		this.descricaoEspecificacao = descricaoEspecificacao;
	}
	public Collection<EspecificacaoImovSitCriterio> getEspecificacaoImovelSituacaoCriterio() {
		return especificacaoImovelSituacaoCriterio;
	}
	public void setEspecificacaoImovelSituacaoCriterio(
			Collection<EspecificacaoImovSitCriterio> especificacaoImovelSituacaoCriterio) {
		this.especificacaoImovelSituacaoCriterio = especificacaoImovelSituacaoCriterio;
	}
	public String getIdEspecificacao() {
		return idEspecificacao;
	}
	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}
	public String getIndicadorHidrometroLigacaoAgua() {
		return indicadorHidrometroLigacaoAgua;
	}
	public void setIndicadorHidrometroLigacaoAgua(
			String indicadorHidrometroLigacaoAgua) {
		this.indicadorHidrometroLigacaoAgua = indicadorHidrometroLigacaoAgua;
	}
	public String getIndicadorHidrometroPoco() {
		return indicadorHidrometroPoco;
	}
	public void setIndicadorHidrometroPoco(String indicadorHidrometroPoco) {
		this.indicadorHidrometroPoco = indicadorHidrometroPoco;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getIdEspecificacaoCriterio() {
		return idEspecificacaoCriterio;
	}
	public void setIdEspecificacaoCriterio(String idEspecificacaoCriterio) {
		this.idEspecificacaoCriterio = idEspecificacaoCriterio;
	}
	public String getIdAtualizar() {
		return idAtualizar;
	}
	public void setIdAtualizar(String idAtualizar) {
		this.idAtualizar = idAtualizar;
	}
	public Collection<EspecificacaoImovSitCriterio> getEspecificacaoImovelSituacaoCriterioRemovidas() {
		return especificacaoImovelSituacaoCriterioRemovidas;
	}
	public void setEspecificacaoImovelSituacaoCriterioRemovidas(
			Collection<EspecificacaoImovSitCriterio> especificacaoImovelSituacaoCriterioRemovidas) {
		this.especificacaoImovelSituacaoCriterioRemovidas = especificacaoImovelSituacaoCriterioRemovidas;
	}
}
