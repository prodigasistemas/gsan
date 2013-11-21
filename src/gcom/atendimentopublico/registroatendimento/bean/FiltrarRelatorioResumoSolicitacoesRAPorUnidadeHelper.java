package gcom.atendimentopublico.registroatendimento.bean;

import java.util.Collection;
import java.util.Date;

public class FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper {
	
	private Short situacao;
	private Date dataAtendimentoInicial;
	private Date dataAtendimentoFinal;
	private Collection<Integer> idsSolicitacaoTipo;
	private Collection<Integer> idsSolicitacaoTipoEspecificacao;
	private Collection<Integer> idsUnidadeAtual;
	private Integer idMunicipio;
	private Integer idBairro;
	
	public FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper() { }

	/**
	 * @return Retorna o campo dataAtendimentoFinal.
	 */
	public Date getDataAtendimentoFinal() {
		return dataAtendimentoFinal;
	}

	/**
	 * @param dataAtendimentoFinal O dataAtendimentoFinal a ser setado.
	 */
	public void setDataAtendimentoFinal(Date dataAtendimentoFinal) {
		this.dataAtendimentoFinal = dataAtendimentoFinal;
	}

	/**
	 * @return Retorna o campo dataAtendimentoInicial.
	 */
	public Date getDataAtendimentoInicial() {
		return dataAtendimentoInicial;
	}

	/**
	 * @param dataAtendimentoInicial O dataAtendimentoInicial a ser setado.
	 */
	public void setDataAtendimentoInicial(Date dataAtendimentoInicial) {
		this.dataAtendimentoInicial = dataAtendimentoInicial;
	}

	/**
	 * @return Retorna o campo idBairro.
	 */
	public Integer getIdBairro() {
		return idBairro;
	}

	/**
	 * @param idBairro O idBairro a ser setado.
	 */
	public void setIdBairro(Integer idBairro) {
		this.idBairro = idBairro;
	}

	/**
	 * @return Retorna o campo idMunicipio.
	 */
	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio O idMunicipio a ser setado.
	 */
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return Retorna o campo idsSolicitacaoTipo.
	 */
	public Collection<Integer> getIdsSolicitacaoTipo() {
		return idsSolicitacaoTipo;
	}

	/**
	 * @param idsSolicitacaoTipo O idsSolicitacaoTipo a ser setado.
	 */
	public void setIdsSolicitacaoTipo(Collection<Integer> idsSolicitacaoTipo) {
		this.idsSolicitacaoTipo = idsSolicitacaoTipo;
	}

	/**
	 * @return Retorna o campo idsSolicitacaoTipoEspecificacao.
	 */
	public Collection<Integer> getIdsSolicitacaoTipoEspecificacao() {
		return idsSolicitacaoTipoEspecificacao;
	}

	/**
	 * @param idsSolicitacaoTipoEspecificacao O idsSolicitacaoTipoEspecificacao a ser setado.
	 */
	public void setIdsSolicitacaoTipoEspecificacao(
			Collection<Integer> idsSolicitacaoTipoEspecificacao) {
		this.idsSolicitacaoTipoEspecificacao = idsSolicitacaoTipoEspecificacao;
	}

	/**
	 * @return Retorna o campo idsUnidadeAtual.
	 */
	public Collection<Integer> getIdsUnidadeAtual() {
		return idsUnidadeAtual;
	}

	/**
	 * @param idsUnidadeAtual O idsUnidadeAtual a ser setado.
	 */
	public void setIdsUnidadeAtual(Collection<Integer> idsUnidadeAtual) {
		this.idsUnidadeAtual = idsUnidadeAtual;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public Short getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}

	
	
}
