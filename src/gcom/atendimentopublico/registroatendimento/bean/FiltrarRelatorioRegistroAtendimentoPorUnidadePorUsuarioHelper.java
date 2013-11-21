package gcom.atendimentopublico.registroatendimento.bean;

import java.util.Collection;
import java.util.Date;

public class FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper {
	

	
	private Short situacao;
	private Date dataAtendimentoInicial;
	private Date dataAtendimentoFinal;
	private Collection<Integer> idsUnidadeAtual;
	
	public FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper() { }

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
