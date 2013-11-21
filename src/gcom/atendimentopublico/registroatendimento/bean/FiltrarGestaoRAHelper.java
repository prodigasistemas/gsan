package gcom.atendimentopublico.registroatendimento.bean;

import java.util.Collection;
import java.util.Date;

public class FiltrarGestaoRAHelper {
	
	private Short situacao;
	private Date dataAtendimentoInicial;
	private Date dataAtendimentoFinal;
	private Collection<Integer> idsSolicitacaoTipo;
	private Collection<Integer> idsSolicitacaoTipoEspecificacao;
	private Collection<Integer> idsUnidadeAtual;
	private Integer idMunicipio;
	private Integer idBairro;
	
	public FiltrarGestaoRAHelper() { }

	public Integer getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Integer idBairro) {
		this.idBairro = idBairro;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Collection<Integer> getIdsSolicitacaoTipoEspecificacao() {
		return idsSolicitacaoTipoEspecificacao;
	}

	public void setIdsSolicitacaoTipoEspecificacao(
			Collection<Integer> idsSolicitacaoTipoEspecificacao) {
		this.idsSolicitacaoTipoEspecificacao = idsSolicitacaoTipoEspecificacao;
	}

	public Collection<Integer> getIdsUnidadeAtual() {
		return idsUnidadeAtual;
	}

	public void setIdsUnidadeAtual(Collection<Integer> idsUnidadeAtual) {
		this.idsUnidadeAtual = idsUnidadeAtual;
	}

	public Short getSituacao() {
		return situacao;
	}

	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}

	public Collection<Integer> getIdsSolicitacaoTipo() {
		return idsSolicitacaoTipo;
	}

	public void setIdsSolicitacaoTipo(Collection<Integer> idsSolicitacaoTipo) {
		this.idsSolicitacaoTipo = idsSolicitacaoTipo;
	}

	public Date getDataAtendimentoFinal() {
		return dataAtendimentoFinal;
	}

	public void setDataAtendimentoFinal(Date dataAtendimentoFinal) {
		this.dataAtendimentoFinal = dataAtendimentoFinal;
	}

	public Date getDataAtendimentoInicial() {
		return dataAtendimentoInicial;
	}

	public void setDataAtendimentoInicial(Date dataAtendimentoInicial) {
		this.dataAtendimentoInicial = dataAtendimentoInicial;
	}

}
