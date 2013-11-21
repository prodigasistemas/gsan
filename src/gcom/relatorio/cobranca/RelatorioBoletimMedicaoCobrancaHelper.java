package gcom.relatorio.cobranca;

import gcom.micromedicao.ItemServico;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC1152] Emissão Boletim Medição Cobrança
 * 
 * Classe responsável por emitir o relatório de Boletim de Medição de Cobrança
 * 
 * @author Mariana Victor
 *
 * @date 21/03/2011
 */
public class RelatorioBoletimMedicaoCobrancaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idCobrancaBoletimMedicao;
	
	private Date dataGeracao;
	
	private ItemServico itemServico;
	
	private String mesAnoReferencia;
	
	private Integer grupoCobranca;
	
	private Integer gerenciaRegional;
	
	private Integer localidadeInicial;
	
	private Integer localidadeFinal;
	
	private String nomeGerenciaRegional;
	
	private String nomeLocalidade;
	
	private String nomeUnidadeNegocio;
	
	private Integer idGerenciaRegional;
	
	private Integer idLocalidade;

	
	public RelatorioBoletimMedicaoCobrancaHelper() {
		super();
	}

	public RelatorioBoletimMedicaoCobrancaHelper(Integer grupoCobranca, String mesAnoReferencia) {
		super();
		this.grupoCobranca = grupoCobranca;
		this.mesAnoReferencia = mesAnoReferencia;
	}
	

	public Integer getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(Integer grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public Integer getIdCobrancaBoletimMedicao() {
		return idCobrancaBoletimMedicao;
	}

	public void setIdCobrancaBoletimMedicao(Integer idCobrancaBoletimMedicao) {
		this.idCobrancaBoletimMedicao = idCobrancaBoletimMedicao;
	}

	public ItemServico getItemServico() {
		return itemServico;
	}

	public void setItemServico(ItemServico itemServico) {
		this.itemServico = itemServico;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}
	
}
