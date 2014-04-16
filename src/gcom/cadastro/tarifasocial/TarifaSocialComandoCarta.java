package gcom.cadastro.tarifasocial;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.interceptor.ObjetoGcom;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class TarifaSocialComandoCarta extends ObjetoGcom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer codigoTipoCarta;
	private Integer quantidadeDiasComparecimento;
	private Integer quantidadeCartasGeradas;
	private Integer quantidadeDiasDebitoVencimento;
	private Integer anoMesInicialImplantacao;
	private Integer anoMesFinalImplantacao;
	private Short indicadorCriterioCpf;
	private Short indicadorCriterioIdentidade;
	private Short indicadorCriterioContratoEnergia;
	private Short indicadorCriterioDadosEnergia;
	private Short indicadorCriterioProgramaSocial;
	private Short indicadorCriterioSeguroDesemprego;
	private Short indicadorCriterioRendaComprovada;
	private Short indicadorCriterioRendaDeclarada;
	private Short indicadorCriterioQtdeEconomia;
	private Short indicadorCriterioRecadastramento;
	private Date dataSimulacao;
	private Date dataGeracao;
	private Date dataProcessamento;
	private Date dataExecucao;
	private Date ultimaAlteracao;
	private GerenciaRegional gerenciaRegional;
	private UnidadeNegocio unidadeNegocio;
	private Localidade localidade;
	private Usuario usuario;
	
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public Integer getAnoMesFinalImplantacao() {
		return anoMesFinalImplantacao;
	}

	public void setAnoMesFinalImplantacao(Integer anoMesFinalImplantacao) {
		this.anoMesFinalImplantacao = anoMesFinalImplantacao;
	}

	public Integer getAnoMesInicialImplantacao() {
		return anoMesInicialImplantacao;
	}

	public void setAnoMesInicialImplantacao(Integer anoMesInicialImplantacao) {
		this.anoMesInicialImplantacao = anoMesInicialImplantacao;
	}

	public Integer getCodigoTipoCarta() {
		return codigoTipoCarta;
	}

	public void setCodigoTipoCarta(Integer codigoTipoCarta) {
		this.codigoTipoCarta = codigoTipoCarta;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorCriterioContratoEnergia() {
		return indicadorCriterioContratoEnergia;
	}

	public void setIndicadorCriterioContratoEnergia(
			Short indicadorCriterioContratoEnergia) {
		this.indicadorCriterioContratoEnergia = indicadorCriterioContratoEnergia;
	}

	public Short getIndicadorCriterioCpf() {
		return indicadorCriterioCpf;
	}

	public void setIndicadorCriterioCpf(Short indicadorCriterioCpf) {
		this.indicadorCriterioCpf = indicadorCriterioCpf;
	}

	public Short getIndicadorCriterioDadosEnergia() {
		return indicadorCriterioDadosEnergia;
	}

	public void setIndicadorCriterioDadosEnergia(Short indicadorCriterioDadosEnergia) {
		this.indicadorCriterioDadosEnergia = indicadorCriterioDadosEnergia;
	}

	public Short getIndicadorCriterioIdentidade() {
		return indicadorCriterioIdentidade;
	}

	public void setIndicadorCriterioIdentidade(Short indicadorCriterioIdentidade) {
		this.indicadorCriterioIdentidade = indicadorCriterioIdentidade;
	}

	public Short getIndicadorCriterioProgramaSocial() {
		return indicadorCriterioProgramaSocial;
	}

	public void setIndicadorCriterioProgramaSocial(
			Short indicadorCriterioProgramaSocial) {
		this.indicadorCriterioProgramaSocial = indicadorCriterioProgramaSocial;
	}

	public Short getIndicadorCriterioQtdeEconomia() {
		return indicadorCriterioQtdeEconomia;
	}

	public void setIndicadorCriterioQtdeEconomia(Short indicadorCriterioQtdeEconomia) {
		this.indicadorCriterioQtdeEconomia = indicadorCriterioQtdeEconomia;
	}

	public Short getIndicadorCriterioRecadastramento() {
		return indicadorCriterioRecadastramento;
	}

	public void setIndicadorCriterioRecadastramento(
			Short indicadorCriterioRecadastramento) {
		this.indicadorCriterioRecadastramento = indicadorCriterioRecadastramento;
	}

	public Short getIndicadorCriterioRendaComprovada() {
		return indicadorCriterioRendaComprovada;
	}

	public void setIndicadorCriterioRendaComprovada(
			Short indicadorCriterioRendaComprovada) {
		this.indicadorCriterioRendaComprovada = indicadorCriterioRendaComprovada;
	}

	public Short getIndicadorCriterioRendaDeclarada() {
		return indicadorCriterioRendaDeclarada;
	}

	public void setIndicadorCriterioRendaDeclarada(
			Short indicadorCriterioRendaDeclarada) {
		this.indicadorCriterioRendaDeclarada = indicadorCriterioRendaDeclarada;
	}

	public Short getIndicadorCriterioSeguroDesemprego() {
		return indicadorCriterioSeguroDesemprego;
	}

	public void setIndicadorCriterioSeguroDesemprego(
			Short indicadorCriterioSeguroDesemprego) {
		this.indicadorCriterioSeguroDesemprego = indicadorCriterioSeguroDesemprego;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getQuantidadeCartasGeradas() {
		return quantidadeCartasGeradas;
	}

	public void setQuantidadeCartasGeradas(Integer quantidadeCartasGeradas) {
		this.quantidadeCartasGeradas = quantidadeCartasGeradas;
	}

	public Integer getQuantidadeDiasComparecimento() {
		return quantidadeDiasComparecimento;
	}

	public void setQuantidadeDiasComparecimento(Integer quantidadeDiasComparecimento) {
		this.quantidadeDiasComparecimento = quantidadeDiasComparecimento;
	}

	public Integer getQuantidadeDiasDebitoVencimento() {
		return quantidadeDiasDebitoVencimento;
	}

	public void setQuantidadeDiasDebitoVencimento(
			Integer quantidadeDiasDebitoVencimento) {
		this.quantidadeDiasDebitoVencimento = quantidadeDiasDebitoVencimento;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public Date getDataSimulacao() {
		return dataSimulacao;
	}

	public void setDataSimulacao(Date dataSimulacao) {
		this.dataSimulacao = dataSimulacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getAnoMesInicialImplantacaoFormatado() {
		String retorno = "";
		if(anoMesInicialImplantacao != null){
			retorno = Util.formatarMesAnoReferencia(anoMesInicialImplantacao);
		}
		return retorno;
	}
	
	public String getAnoMesFinalImplantacaoFormatado() {
		String retorno = "";
		if(anoMesFinalImplantacao != null){
			retorno = Util.formatarMesAnoReferencia(anoMesFinalImplantacao);
		}
		return retorno;
	}
	
	public String getDataLimiteComparecimentoFormatado() {
		String retorno = "";
		if(dataGeracao != null && quantidadeDiasComparecimento != null){
			Date dataLimite = Util.adicionarNumeroDiasDeUmaData(dataGeracao,quantidadeDiasComparecimento);
			retorno = Util.formatarData(dataLimite);
		}
		return retorno;
	}
}
