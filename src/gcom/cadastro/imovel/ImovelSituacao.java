package gcom.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ImovelSituacao extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

	/** persistent field */
	private gcom.cadastro.imovel.ImovelSituacaoTipo imovelSituacaoTipo;

	/** persistent field */
	private LigacaoAguaSituacao ligacaoAguaSituacao;

	/**
	 * nullable persistent field
	 */
	private Date ultimaAlteracao;

	/** full constructor */
	public ImovelSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			gcom.cadastro.imovel.ImovelSituacaoTipo imovelSituacaoTipo,
			LigacaoAguaSituacao ligacaoAguaSituacao, Date ultimaAlteracao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.imovelSituacaoTipo = imovelSituacaoTipo;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao
	 *            O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public ImovelSituacao() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return this.ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public gcom.cadastro.imovel.ImovelSituacaoTipo getImovelSituacaoTipo() {
		return this.imovelSituacaoTipo;
	}

	public void setImovelSituacaoTipo(
			gcom.cadastro.imovel.ImovelSituacaoTipo imovelSituacaoTipo) {
		this.imovelSituacaoTipo = imovelSituacaoTipo;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return this.ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroImovelSituacao filtroImovelSituacao = new FiltroImovelSituacao();
		filtroImovelSituacao.adicionarParametro(new ParametroSimples(
				FiltroImovelSituacao.ID, this.getId()));

		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");

		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

		return filtroImovelSituacao;
	}

}
