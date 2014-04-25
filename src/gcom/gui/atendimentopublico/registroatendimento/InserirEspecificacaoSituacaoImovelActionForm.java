package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class InserirEspecificacaoSituacaoImovelActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	// Dados da Especificao Situacao Imovel
	private String descricaoEspecificacao;
    
    // Especificacao Imovel Situacao Criterior
    private Collection<EspecificacaoImovSitCriterio> especificacaoImovelSituacaoCriterio 
    	= new ArrayList<EspecificacaoImovSitCriterio>();
    
    private String indicadorHidrometroLigacaoAgua;
    private String indicadorHidrometroPoco;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;

    // Controle
    private String method = "";
    private String tamanhoColecao = "0";
    private String deleteSituacaoCriterioImovel;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
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
	public String getDeleteSituacaoCriterioImovel() {
		return deleteSituacaoCriterioImovel;
	}
	public void setDeleteSituacaoCriterioImovel(String deleteSituacaoCriterioImovel) {
		this.deleteSituacaoCriterioImovel = deleteSituacaoCriterioImovel;
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
}
