package gcom.cadastro.empresa;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Empresa extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	private String email;

	/** nullable persistent field */
	private Short indicadorEmpresaPrincipal;
	
	private Short indicadorEmpresaContratadaCobranca;
	
	private Short indicadorLeitura;

	public static final Short INDICADOR_EMPRESA_PRINCIPAL = new Short("1");

	public static final Short EMPRESA_FEBRABAN_COMPESA = new Short("18");

	public static final Short INDICADOR_EMPRESA_COBRANCA = new Short("1");
	
	public static final Integer QUALIAGUA_SERVICOS_DE_HDT_LTDA = new Integer ("32");

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/** full constructor */
	public Empresa(String descricao, String descricaoAbreviada,
			Short indicadorUso, String email, Date ultimaAlteracao,
			Short indicadorEmpresaPrincipal) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.email = email;
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
	}

	/** default constructor */
	public Empresa() {
	}

	public Integer getId() {
		return this.id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Short getIndicadorEmpresaPrincipal() {
		return indicadorEmpresaPrincipal;
	}

	public void setIndicadorEmpresaPrincipal(Short indicadorEmpresaPrincipal) {
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
	}

	public Filtro retornaFiltro() {
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
				this.getId()));
		return filtroEmpresa;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
				this.getId()));
		return filtro;
	}

	public Short getIndicadorEmpresaContratadaCobranca() {
		return indicadorEmpresaContratadaCobranca;
	}

	public void setIndicadorEmpresaContratadaCobranca(
			Short indicadorEmpresaContratadaCobranca) {
		this.indicadorEmpresaContratadaCobranca = indicadorEmpresaContratadaCobranca;
	}

	public Short getIndicadorLeitura() {
		return indicadorLeitura;
	}

	public void setIndicadorLeitura(Short indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}

}
