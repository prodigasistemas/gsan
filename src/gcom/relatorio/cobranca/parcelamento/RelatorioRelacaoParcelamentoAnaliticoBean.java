package gcom.relatorio.cobranca.parcelamento;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC0878] Gerar Relação de Parcelamento - Visão Analitica
 * 
 * Bean que preencherá o relatorio
 * 
 * @author Bruno Barros
 * @date 04/02/2009
 *
 */
public class RelatorioRelacaoParcelamentoAnaliticoBean implements RelatorioBean {
	
	private String nome;
	private String matricula;
	private String telefone;
	private String unidade;
	private String numero;
	private Integer vencimento;
	private BigDecimal debito;
	private BigDecimal valorParcela;
	private BigDecimal valorEntrada;
	private Date data;
	private Integer parcela;
	private Integer qtdParcelasQuitadas;
	private Integer qtdParcelasCobradas;
	private Integer qtdParcelasACobrar;
	private BigDecimal valorParcelasQuitadas;
	private BigDecimal valorCobradas;
	private BigDecimal valorACobrar;
	private Integer idGerencia;
	private Integer idLocalidade;
	private Integer idMunicipio;
	private String descricaoGerencia;
	private String descricaoLocalidade;
	private String descricaoMunicipio;
	private String login;
	
	
	/**
	 * 
	 * Cria uma instancia do bean para o relatório
	 * 
	 * @param nome
	 * @param matricula
	 * @param telefone
	 * @param unidade
	 * @param numero
	 * @param vencimento
	 * @param debito
	 * @param valorParcelamento
	 * @param valorEntrada
	 * @param data
	 * @param parcela
	 * @param qtdParcelasQuitadas
	 * @param qtdParcelasCobradas
	 * @param qtdParcelasACobrar
	 * @param valorParcelasQuitadas
	 * @param valorCobradas
	 * @param valorACobrar
	 * @param idGerencia
	 * @param idLocalidade
	 * @param idMunicipio
	 * @param descricaoMunicipio
	 */
	public RelatorioRelacaoParcelamentoAnaliticoBean(
			String nome, 
			String matricula, 
			String telefone, 
			String unidade, 
			String numero, 
			Integer vencimento, 
			BigDecimal debito, 
			BigDecimal valorParcela, 
			BigDecimal valorEntrada, 
			Date data, 
			Integer parcela, 
			Integer qtdParcelasQuitadas, 
			Integer qtdParcelasCobradas, 
			Integer qtdParcelasACobrar, 
			BigDecimal valorParcelasQuitadas, 
			BigDecimal valorCobradas, 
			BigDecimal valorACobrar, 
			Integer idGerencia, 
			Integer idLocalidade,
			Integer idMunicipio,
			String descricaoGerencia, 
			String descricaoLocalidade,
			String descricaoMunicipio,
			String login) {
		super();

		this.nome = nome;
		this.matricula = matricula;
		this.telefone = telefone;
		this.unidade = unidade;
		this.numero = numero;
		this.vencimento = vencimento;
		this.debito = debito;
		this.valorParcela = valorParcela;
		this.valorEntrada = valorEntrada;
		this.data = data;
		this.parcela = parcela;
		this.qtdParcelasQuitadas = qtdParcelasQuitadas;
		this.qtdParcelasCobradas = qtdParcelasCobradas;
		this.qtdParcelasACobrar = qtdParcelasACobrar;
		this.valorParcelasQuitadas = valorParcelasQuitadas;
		this.valorCobradas = valorCobradas;
		this.valorACobrar = valorACobrar;
		this.idGerencia = idGerencia;
		this.idLocalidade = idLocalidade;
		this.idMunicipio = idMunicipio;
		this.descricaoGerencia = idGerencia + " " + descricaoGerencia;
		this.descricaoLocalidade = idLocalidade + " " + descricaoLocalidade;
		if(idMunicipio != null && descricaoMunicipio != null){
			this.descricaoMunicipio = idMunicipio + " " + descricaoMunicipio;
		}
		this.login = login;
	}
	public Date getData() {
		return data;
	}
	public BigDecimal getDebito() {
		return debito;
	}
	public Integer getIdGerencia() {
		return idGerencia;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getNome() {
		return nome;
	}
	public String getNumero() {
		return numero;
	}
	public Integer getParcela() {
		return parcela;
	}
	public Integer getQtdParcelasACobrar() {
		return qtdParcelasACobrar;
	}
	public Integer getQtdParcelasCobradas() {
		return qtdParcelasCobradas;
	}
	public Integer getQtdParcelasQuitadas() {
		return qtdParcelasQuitadas;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getUnidade() {
		return unidade;
	}
	public BigDecimal getValorACobrar() {
		return valorACobrar;
	}
	public BigDecimal getValorCobradas() {
		return valorCobradas;
	}
	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	public BigDecimal getValorParcelasQuitadas() {
		return valorParcelasQuitadas;
	}
	public Integer getVencimento() {
		return vencimento;
	}
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}
}
