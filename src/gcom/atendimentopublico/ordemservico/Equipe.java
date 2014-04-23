package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Equipe extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	// CONSTANTE DE VALIDAÇÃO DA CARGA HORÁRIA
	public final static int CARGA_HORARIA_MAXIMA = new Integer(24);

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String nome;

	/** nullable persistent field */
	private String placaVeiculo;

	/** nullable persistent field */
	private Integer cargaTrabalho;

	/** nullable persistent field */
	private short indicadorUso;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;

	/** nullable persistent field */
	private String codigoDdd;

	/** nullable persistent field */
	private String numeroTelefone;

	/** nullable persistent field */
	private BigDecimal numeroImei;

	/** persistent field */
	private gcom.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo;
	
	private Usuario usuarioRespExecServico;
	
	private Short indicadorProgramacaoAutomatica;

	/** full constructor */
	public Equipe(
			String nome,
			String placaVeiculo,
			Integer cargaTrabalho,
			Date ultimaAlteracao,
			UnidadeOrganizacional unidadeOrganizacional,
			gcom.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo,
			short indicadorUso, Integer codigoDdd, Integer numeroTelefone,
			Integer numeroImei) {
		this.nome = nome;
		this.placaVeiculo = placaVeiculo;
		this.cargaTrabalho = cargaTrabalho;
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.servicoPerfilTipo = servicoPerfilTipo;
		this.indicadorUso = indicadorUso;

	}

	/** default constructor */
	public Equipe() {
	}

	/** minimal constructor */
	public Equipe(
			Date ultimaAlteracao,
			UnidadeOrganizacional unidadeOrganizacional,
			gcom.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo,
			Integer codigoDdd, Integer numeroTelefone, Integer numeroImei) {
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.servicoPerfilTipo = servicoPerfilTipo;

	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPlacaVeiculo() {
		return this.placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public Integer getCargaTrabalho() {
		return this.cargaTrabalho;
	}

	public void setCargaTrabalho(Integer cargaTrabalho) {
		this.cargaTrabalho = cargaTrabalho;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return this.unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public gcom.atendimentopublico.ordemservico.ServicoPerfilTipo getServicoPerfilTipo() {
		return this.servicoPerfilTipo;
	}

	public void setServicoPerfilTipo(
			gcom.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo) {
		this.servicoPerfilTipo = servicoPerfilTipo;
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
		FiltroEquipe filtroEquipe = new FiltroEquipe();

		filtroEquipe
				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroEquipe
				.adicionarCaminhoParaCarregamentoEntidade("servicoPerfilTipo");
		filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("codigoDdd");
		filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("numeroTelefone");
		filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("numeroImei");
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID,
				this.getId()));
		return filtroEquipe;
	}

	public String getCodigoDdd() {
		return codigoDdd;
	}

	public void setCodigoDdd(String codigoDdd) {
		this.codigoDdd = codigoDdd;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public BigDecimal getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(BigDecimal numeroImei) {
		this.numeroImei = numeroImei;
	}

	public Usuario getUsuarioRespExecServico() {
		return usuarioRespExecServico;
	}

	public void setUsuarioRespExecServico(Usuario usuarioRespExecServico) {
		this.usuarioRespExecServico = usuarioRespExecServico;
	}
	
	public Short getIndicadorProgramacaoAutomatica() {
		return indicadorProgramacaoAutomatica;
	}

	public void setIndicadorProgramacaoAutomatica(
			Short indicadorProgramacaoAutomatica) {
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}
}
