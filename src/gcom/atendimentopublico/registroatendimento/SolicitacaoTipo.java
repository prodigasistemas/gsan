package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class SolicitacaoTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR = 280; //Operacao.OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR
	public static final int ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER = 577; //Operacao.OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
	public static final int ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR = 664;//Operacao.OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	/** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private String descricao;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private short indicadorFaltaAgua;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private short indicadorTarifaSocial;

	/** persistent field */
	@ControleAlteracao(value=FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO,funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo;

	/** persistent field */
	private short indicadorUso;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private short indicadorUsoSistema;
	
	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_USO_ATIVO = new Short("1");
	public final static Short INDICADOR_FALTA_AGUA_SIM = new Short("1");
	public final static Short INDICADOR_USO_SISTEMA_SIM = new Short("1");
	public final static Short INDICADOR_USO_SISTEMA_NAO = new Short("2");
	
	public final static Integer FISCALIZACAO = new Integer("42894");

	/** full constructor */
	public SolicitacaoTipo(
			String descricao,
			Date ultimaAlteracao,
			short indicadorFaltaAgua,
			short indicadorTarifaSocial,
			gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo,
			short indicadorUso) {
		this.descricao = descricao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFaltaAgua = indicadorFaltaAgua;
		this.indicadorTarifaSocial = indicadorTarifaSocial;
		this.solicitacaoTipoGrupo = solicitacaoTipoGrupo;
		this.indicadorUso = indicadorUso;
	}

	/** default constructor */
	public SolicitacaoTipo() {
	}

	/** minimal constructor */
	public SolicitacaoTipo(
			Date ultimaAlteracao,
			short indicadorFaltaAgua,
			gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo) {
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFaltaAgua = indicadorFaltaAgua;
		this.solicitacaoTipoGrupo = solicitacaoTipoGrupo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorFaltaAgua() {
		return this.indicadorFaltaAgua;
	}

	public void setIndicadorFaltaAgua(short indicadorFaltaAgua) {
		this.indicadorFaltaAgua = indicadorFaltaAgua;
	}

	public gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo getSolicitacaoTipoGrupo() {
		return this.solicitacaoTipoGrupo;
	}

	public void setSolicitacaoTipoGrupo(
			gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo) {
		this.solicitacaoTipoGrupo = solicitacaoTipoGrupo;
	}

	public short getIndicadorTarifaSocial() {
		return indicadorTarifaSocial;
	}

	public void setIndicadorTarifaSocial(short indicadorTarifaSocial) {
		this.indicadorTarifaSocial = indicadorTarifaSocial;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo indicadorUsoSistema.
	 */
	public short getIndicadorUsoSistema() {
		return indicadorUsoSistema;
	}

	/**
	 * @param indicadorUsoSistema O indicadorUsoSistema a ser setado.
	 */
	public void setIndicadorUsoSistema(short indicadorUsoSistema) {
		this.indicadorUsoSistema = indicadorUsoSistema;
	}
	
	
	public Filtro retornaFiltro() {
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.ID,
				this.getId()));
		filtroSolicitacaoTipo.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoGrupo");
		return filtroSolicitacaoTipo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"descricao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao do Tipo de Solicitacao"};
		return labels;		
	}

}
