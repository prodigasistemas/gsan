package gcom.relatorio.cadastro.micromedicao;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro
 * 
 * @author Hugo Leonardo
 *
 * @date 29/03/2010
 */
public class RelatorioResumoLigacoesCapacidadeHidrometroBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idGerenciaRegional;
	private String nomeGerenciaRegional;
	private Integer idUnidadeNegocio;
	private String nomeUnidadeNegocio;
	private Integer idLocalidade;
	private String nomeLocalidade;

	private String capacidadeHidrometro;
	private String diametroHidrometro;
	private Integer qtdLigacoes;
	private Integer qtdEconomias;
	private Integer volumeAguaMedido;
	private BigDecimal valorFaturado;
	private String opcaoTotalizacao;
	

	public RelatorioResumoLigacoesCapacidadeHidrometroBean(
			Integer idGerenciaRegional, String nomeGerenciaRegional, 
			Integer idUnidadeNegocio, String nomeUnidadeNegocio,
			Integer idLocalidade, String nomeLocalidade, 
			String capacidadeHidrometro, String diametroHidrometro,
			Integer qtdLigacoes, Integer qtdEconomias, 
			Integer volumeAguaMedido, BigDecimal valorFaturado, 
			String opcaoTotalizacao) {
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.capacidadeHidrometro = capacidadeHidrometro;
		this.diametroHidrometro = diametroHidrometro;
		this.qtdLigacoes = qtdLigacoes;
		this.qtdEconomias = qtdEconomias;
		this.volumeAguaMedido = volumeAguaMedido;
		this.valorFaturado = valorFaturado;
		this.opcaoTotalizacao = opcaoTotalizacao;
	}


	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	public String getDiametroHidrometro() {
		return diametroHidrometro;
	}

	public void setDiametroHidrometro(String diametroHidrometro) {
		this.diametroHidrometro = diametroHidrometro;
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

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
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

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public Integer getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(Integer qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public Integer getQtdLigacoes() {
		return qtdLigacoes;
	}

	public void setQtdLigacoes(Integer qtdLigacoes) {
		this.qtdLigacoes = qtdLigacoes;
	}

	public BigDecimal getValorFaturado() {
		return valorFaturado;
	}

	public void setValorFaturado(BigDecimal valorFaturado) {
		this.valorFaturado = valorFaturado;
	}

	public Integer getVolumeAguaMedido() {
		return volumeAguaMedido;
	}

	public void setVolumeAguaMedido(Integer volumeAguaMedido) {
		this.volumeAguaMedido = volumeAguaMedido;
	}
	
}
