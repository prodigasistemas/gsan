package gcom.spcserasa.bean;

import gcom.cadastro.imovel.Categoria;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;

import java.util.Collection;

public class ObterDocumentosNegativacaoPorPeriodoHelper implements Cloneable {

	private static final long serialVersionUID = 1L;

	private Integer idImovel;
	private Collection<ContaValoresHelper> colecaoContasValores;
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores;
	private boolean contaParcelada;
	private NegativacaoCriterio criterio;
	private Categoria categoriaPrincipalDoImovel;
	private NegativacaoComando comando;
	private Collection<Integer> colecaoContasIds;

	public ObterDocumentosNegativacaoPorPeriodoHelper(
			Integer idImovel,
			Collection<ContaValoresHelper> colecaoContasValores,
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores,
			boolean contaParcelada,
			NegativacaoCriterio criterio,
			Categoria categoriaPrincipalDoImovel,
			NegativacaoComando comando,
			Collection<Integer> colecaoContasIds) {
		super();
		this.idImovel = idImovel;
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
		this.contaParcelada = contaParcelada;
		this.criterio = criterio;
		this.categoriaPrincipalDoImovel = categoriaPrincipalDoImovel;
		this.comando = comando;
		this.colecaoContasIds = colecaoContasIds;
	}

	public ObterDocumentosNegativacaoPorPeriodoHelper() {
		super();
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Collection<ContaValoresHelper> getColecaoContasValores() {
		return colecaoContasValores;
	}

	public void setColecaoContasValores(Collection<ContaValoresHelper> colecaoContasValores) {
		this.colecaoContasValores = colecaoContasValores;
	}

	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiasPagamentoValores() {
		return colecaoGuiasPagamentoValores;
	}

	public void setColecaoGuiasPagamentoValores(Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public boolean isContaParcelada() {
		return contaParcelada;
	}

	public void setContaParcelada(boolean contaParcelada) {
		this.contaParcelada = contaParcelada;
	}

	public NegativacaoCriterio getCriterio() {
		return criterio;
	}

	public void setCriterio(NegativacaoCriterio criterio) {
		this.criterio = criterio;
	}

	public Categoria getCategoriaPrincipalDoImovel() {
		return categoriaPrincipalDoImovel;
	}

	public void setCategoriaPrincipalDoImovel(Categoria categoriaPrincipalDoImovel) {
		this.categoriaPrincipalDoImovel = categoriaPrincipalDoImovel;
	}

	public NegativacaoComando getComando() {
		return comando;
	}

	public void setComando(NegativacaoComando comando) {
		this.comando = comando;
	}

	public Collection<Integer> getColecaoContasIds() {
		return colecaoContasIds;
	}

	public void setColecaoContasIds(Collection<Integer> colecaoContasIds) {
		this.colecaoContasIds = colecaoContasIds;
	}
}