package gcom.seguranca.acesso;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class Grupo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_INSERIR_GRUPO=6;
	public static final int ATRIBUTOS_REMOVER_GRUPO=7;
	public static final int ATRIBUTOS_ATUALIZAR_GRUPO=75;
	
	
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private String descricao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private String descricaoAbreviada;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private Short indicadorUso;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private Date ultimaAlteracao;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private Integer numDiasExpiracaoSenha;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private String mensagem;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private Short indicadorSuperintendencia;
	
	private BigDecimal competenciaRetificacao;

	/** Operacao de teste */
	public static final Integer ADMINISTRADOR = 1;
	
	public static final Integer ATENDENTE_LOJA = 46;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];

		retorno[0] = "id";
		
		return retorno;
	}
	
	/** full constructor */
	public Grupo(String descricao, String descricaoAbreviada,
			Short indicadorUso, Date ultimaAlteracao) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public Grupo() {
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

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	public Integer getNumDiasExpiracaoSenha() {
		return numDiasExpiracaoSenha;
	}

	public void setNumDiasExpiracaoSenha(Integer numDiasExpiracaoSenha) {
		this.numDiasExpiracaoSenha = numDiasExpiracaoSenha;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID, 
				this.getId()));
		
		return filtroGrupo;
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return getId().toString(); 
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"descricao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao"};
		return labels;		
	}

	public Short getIndicadorSuperintendencia() {
		return indicadorSuperintendencia;
	}

	public void setIndicadorSuperintendencia(Short indicadorSuperintendencia) {
		this.indicadorSuperintendencia = indicadorSuperintendencia;
	}

	public BigDecimal getCompetenciaRetificacao() {
		return competenciaRetificacao;
	}

	public void setCompetenciaRetificacao(BigDecimal competenciaRetificacao) {
		this.competenciaRetificacao = competenciaRetificacao;
	}
	
}
