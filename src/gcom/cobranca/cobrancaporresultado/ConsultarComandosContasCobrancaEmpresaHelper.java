package gcom.cobranca.cobrancaporresultado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC1167] Consultar Comandos de Cobrança por Empresa
 * 
 * @author Mariana Victor
 * @since 03/05/2011
 */
public class ConsultarComandosContasCobrancaEmpresaHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idComandoEmpresaCobrancaConta;

	private Integer idEmpresa;

	private String nomeEmpresa;

	private Date dataInicioCiclo;

	private Date dataFinalCiclo;

	private Date dataExecucao;

	private Date dataEncerramento;

	private Integer idImovel;

	private Integer idCliente;

	private String nomeCliente;

	private Integer idGerenciaRegional;

	private String nomeGerenciaRegional;

	private Integer idUnidadeNegocio;

	private String nomeUnidadeNegocio;

	private Integer idLocalidadeInicial;

	private String descricaoLocalidadeInicial;
	
	private Integer idLocalidadeFinal;
	
	private String descricaoLocalidadeFinal;

	private Integer codigoSetorComercialInicial;

	private Integer codigoSetorComercialFinal;

	private Integer numeroQuadraInicial;

	private Integer numeroQuadraFinal;

	private Integer anoMesReferenciaInicial;

	private Integer anoMesReferenciaFinal;
	
	private Date dataVencimentoInicial;
	
	private Date dataVencimentoFinal;
	
	private BigDecimal valorMinimoContas;
	
	private BigDecimal valorMaximoContas;
	
	private String idRegistro;
	

	public ConsultarComandosContasCobrancaEmpresaHelper(Integer idComandoEmpresaCobrancaConta, //1
			Integer idEmpresa, //2
			String nomeEmpresa, //3
			Date dataInicioCiclo,//4
			Date dataFinalCiclo, //5
			Date dataExecucao, //6
			Date dataEncerramento,//7
			Integer idImovel, //8
			Integer idCliente, //9
			String nomeCliente, //10
			//Integer idGerenciaRegional, //11
			//String nomeGerenciaRegional, //12
			//Integer idUnidadeNegocio, //13
			//String nomeUnidadeNegocio, //14
			Integer idLocalidadeInicial, //15
			//String descricaoLocalidadeInicial, //16
			Integer idLocalidadeFinal, //17
			//String descricaoLocalidadeFinal, //18
			Integer codigoSetorComercialInicial, //19
			Integer codigoSetorComercialFinal, //20
			Integer numeroQuadraInicial, //21
			Integer numeroQuadraFinal, //22
			Integer anoMesReferenciaInicial, //23
			Integer anoMesReferenciaFinal, //24
			Date dataVencimentoInicial, //25
			Date dataVencimentoFinal, //26
			BigDecimal valorMinimoContas, //27
			BigDecimal valorMaximoContas //28
			) { 
		super();
		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.dataInicioCiclo = dataInicioCiclo;
		this.dataFinalCiclo = dataFinalCiclo;
		this.dataExecucao = dataExecucao;
		this.dataEncerramento = dataEncerramento;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.idLocalidadeInicial = idLocalidadeInicial;
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
		this.idLocalidadeFinal = idLocalidadeFinal;
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
		this.numeroQuadraInicial = numeroQuadraInicial;
		this.numeroQuadraFinal = numeroQuadraFinal;
		this.anoMesReferenciaInicial = anoMesReferenciaInicial;
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
		this.dataVencimentoInicial = dataVencimentoInicial;
		this.dataVencimentoFinal = dataVencimentoFinal;
		this.valorMinimoContas = valorMinimoContas;
		this.valorMaximoContas = valorMaximoContas;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Date getDataFinalCiclo() {
		return dataFinalCiclo;
	}

	public void setDataFinalCiclo(Date dataFinalCiclo) {
		this.dataFinalCiclo = dataFinalCiclo;
	}

	public Date getDataInicioCiclo() {
		return dataInicioCiclo;
	}

	public void setDataInicioCiclo(Date dataInicioCiclo) {
		this.dataInicioCiclo = dataInicioCiclo;
	}

	public Integer getIdComandoEmpresaCobrancaConta() {
		return idComandoEmpresaCobrancaConta;
	}

	public void setIdComandoEmpresaCobrancaConta(
			Integer idComandoEmpresaCobrancaConta) {
		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}

	public void setAnoMesReferenciaFinal(Integer anoMesReferenciaFinal) {
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
	}

	public Integer getAnoMesReferenciaInicial() {
		return anoMesReferenciaInicial;
	}

	public void setAnoMesReferenciaInicial(Integer anoMesReferenciaInicial) {
		this.anoMesReferenciaInicial = anoMesReferenciaInicial;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public Date getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(Date dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public BigDecimal getValorMaximoContas() {
		return valorMaximoContas;
	}

	public void setValorMaximoContas(BigDecimal valorMaximoContas) {
		this.valorMaximoContas = valorMaximoContas;
	}

	public BigDecimal getValorMinimoContas() {
		return valorMinimoContas;
	}

	public void setValorMinimoContas(BigDecimal valorMinimoContas) {
		this.valorMinimoContas = valorMinimoContas;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

}
