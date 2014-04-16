package gcom.seguranca.acesso.usuario;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
@ControleAlteracao()
public class SolicitacaoAcessoSituacao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	public static final int OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR = 1707;
	public static final int OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR = 1710;
	public static final int OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER = 1709;
	
	public final static Short AG_AUTORIZACAO_SUP = new Short("1");
	public final static Short AG_CADASTRAMENTO = new Short("2");
	public final static Short ATIVO = new Short("3");

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private String descricao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private Short indicadorUso;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private Date ultimaAlteracao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade = {
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
			OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER })
	private Short codigoSituacao;

	/** default constructor */
	public SolicitacaoAcessoSituacao() {

	}

	/** Constructor */
	public SolicitacaoAcessoSituacao(String descricao, Short indicadorUso,
			Date ultimaAlteracao) {

		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
		filtroSolicitacaoAcessoSituacao
				.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoAcessoSituacao.ID, this.getId()));
		return filtroSolicitacaoAcessoSituacao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcessoSituacao. ID, this.getId()));

		return filtro;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] labels = { "descricao" };
		return labels;
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = { "Descricao" };
		return labels;
	}

	public Short getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Short codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

}
