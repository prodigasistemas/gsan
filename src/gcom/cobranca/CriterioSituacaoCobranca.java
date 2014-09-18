package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CriterioSituacaoCobranca extends ObjetoTransacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CriterioSituacaoCobrancaPK comp_id;
	
	private Date ultimaAlteracao;

	public CriterioSituacaoCobrancaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(CriterioSituacaoCobrancaPK comp_id) {
		this.comp_id = comp_id;
	}
	
	public CriterioSituacaoCobranca() {
	}

	public CriterioSituacaoCobranca(CriterioSituacaoCobrancaPK comp_id) {
		super();
		
		this.comp_id = comp_id;
	}
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"comp_id"};
		return retorno;
	}

	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;		
	}

	public Filtro retornaFiltro(){
		Filtro filtro = new FiltroCriterioSituacaoCobranca();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCriterioSituacaoCobranca.COBRANCA_CRITERIO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCriterioSituacaoCobranca.COBRANCA_SITUACAO);
		filtro.adicionarParametro(
				new ParametroSimples(FiltroCriterioSituacaoCobranca.COBRANCA_CRITERIO_ID, 
						this.getComp_id().getCobrancaCriterio().getId()));
		filtro.adicionarParametro(
				new ParametroSimples(FiltroCriterioSituacaoCobranca.COBRANCA_SITUACAO_ID, 
						this.getComp_id().getCobrancaSituacao().getId()));
		return filtro; 
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id())
				.toString();
	}

	/**
	 * Description of the Method
	 * 
	 * @param other
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof CriterioSituacaoCobranca)) {
			return false;
		}
		CriterioSituacaoCobranca castOther = (CriterioSituacaoCobranca) other;

		return new EqualsBuilder().append(this.getComp_id(),
				castOther.getComp_id()).isEquals();
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
//	public int hashCode() {
//		return this.ultimaAlteracao.hashCode();
//	}	
}
