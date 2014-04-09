package gcom.relatorio.cadastro.imovel;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório de imoveis por Programas Especiais
 * 
 * @author Hugo Leonardo
 * @created 18/01/2010
 */
public class RelatorioImoveisProgramasEspeciaisBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	//private Integer idUnidadeNegocio;
	//private String nomeUnidadeNegocio;
	private Integer idRegiaoDesenvolvimento;
	private String nomeRegiaoDesenvolvimento;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private String idImovel;
	private String nomeUsuario;
	private String situacaoMedicao;
	private BigDecimal valorConta;
	private Integer consumoAgua;
	private String endereco;
	
	private Integer qtdImoveisSemHidr;
	private BigDecimal valorContasSemHidr;
	private Integer qtdImoveisComHidr;
	private BigDecimal valorContasComHidr;
	
	private Integer qtdTotalImoveis;
	private BigDecimal valorTotalContas;
	

	public RelatorioImoveisProgramasEspeciaisBean(Integer idRegiaoDesenvolvimento, String nomeRegiaoDesenvolvimento, 
			Integer idLocalidade, String nomeLocalidade, String idImovel, String nomeUsuario, String situacaoMedicao,
			Integer consumoAgua, BigDecimal valorConta, String endereco) {
		
		this.idRegiaoDesenvolvimento = idRegiaoDesenvolvimento;
		this.nomeRegiaoDesenvolvimento = nomeRegiaoDesenvolvimento;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.idImovel = idImovel;
		this.nomeUsuario = nomeUsuario;
		this.situacaoMedicao = situacaoMedicao;
		this.valorConta = valorConta;
		this.consumoAgua = consumoAgua;
		this.endereco = endereco;
	}
	
	public RelatorioImoveisProgramasEspeciaisBean(Integer idRegiaoDesenvolvimento, String nomeRegiaoDesenvolvimento, 
			Integer idLocalidade, String nomeLocalidade, Integer qtdImoveisSemHidr,
			BigDecimal valorContasSemHidr, Integer qtdImoveisComHidr, BigDecimal valorContasComHidr, 
			Integer qtdTotalImoveis, BigDecimal valorTotalContas){
		
		this.idRegiaoDesenvolvimento = idRegiaoDesenvolvimento;
		this.nomeRegiaoDesenvolvimento = nomeRegiaoDesenvolvimento;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.qtdImoveisSemHidr = qtdImoveisSemHidr;
		this.valorContasSemHidr = valorContasSemHidr;
		this.qtdImoveisComHidr = qtdImoveisComHidr;
		this.valorContasComHidr = valorContasComHidr;
		this.qtdTotalImoveis = qtdTotalImoveis;
		this.valorTotalContas = valorTotalContas;
	}

	/**
	 * @return Returns the consumoAgua.
	 */
	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	/**
	 * @param consumoAgua The consumoAgua to set.
	 */
	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	/**
	 * @return Returns the idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel The idImovel to set.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Returns the idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade The idLocalidade to set.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Returns the nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade The nomeLocalidade to set.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Returns the nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario The nomeUsuario to set.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @return Returns the situacaoMedicao.
	 */
	public String getSituacaoMedicao() {
		return situacaoMedicao;
	}

	/**
	 * @param situacaoMedicao The situacaoMedicao to set.
	 */
	public void setSituacaoMedicao(String situacaoMedicao) {
		this.situacaoMedicao = situacaoMedicao;
	}

	/**
	 * @return Returns the valorConta.
	 */
	public BigDecimal getValorConta() {
		return valorConta;
	}

	/**
	 * @param valorConta The valorConta to set.
	 */
	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	/**
	 * @return Returns the endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco The endereco to set.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Returns the qtdImoveisComHidr.
	 */
	public Integer getQtdImoveisComHidr() {
		return qtdImoveisComHidr;
	}

	/**
	 * @param qtdImoveisComHidr The qtdImoveisComHidr to set.
	 */
	public void setQtdImoveisComHidr(Integer qtdImoveisComHidr) {
		this.qtdImoveisComHidr = qtdImoveisComHidr;
	}

	/**
	 * @return Returns the qtdImoveisSemHidr.
	 */
	public Integer getQtdImoveisSemHidr() {
		return qtdImoveisSemHidr;
	}

	/**
	 * @param qtdImoveisSemHidr The qtdImoveisSemHidr to set.
	 */
	public void setQtdImoveisSemHidr(Integer qtdImoveisSemHidr) {
		this.qtdImoveisSemHidr = qtdImoveisSemHidr;
	}

	/**
	 * @return Returns the valorContasComHidr.
	 */
	public BigDecimal getValorContasComHidr() {
		return valorContasComHidr;
	}

	/**
	 * @param valorContasComHidr The valorContasComHidr to set.
	 */
	public void setValorContasComHidr(BigDecimal valorContasComHidr) {
		this.valorContasComHidr = valorContasComHidr;
	}

	/**
	 * @return Returns the valorContasSemHidr.
	 */
	public BigDecimal getValorContasSemHidr() {
		return valorContasSemHidr;
	}

	/**
	 * @param valorContasSemHidr The valorContasSemHidr to set.
	 */
	public void setValorContasSemHidr(BigDecimal valorContasSemHidr) {
		this.valorContasSemHidr = valorContasSemHidr;
	}

	/**
	 * @return Returns the qtdTotalImoveis.
	 */
	public Integer getQtdTotalImoveis() {
		return qtdTotalImoveis;
	}

	/**
	 * @param qtdTotalImoveis The qtdTotalImoveis to set.
	 */
	public void setQtdTotalImoveis(Integer qtdTotalImoveis) {
		this.qtdTotalImoveis = qtdTotalImoveis;
	}

	/**
	 * @return Returns the valorTotalContas.
	 */
	public BigDecimal getValorTotalContas() {
		return valorTotalContas;
	}

	/**
	 * @param valorTotalContas The valorTotalContas to set.
	 */
	public void setValorTotalContas(BigDecimal valorTotalContas) {
		this.valorTotalContas = valorTotalContas;
	}

	public Integer getIdRegiaoDesenvolvimento() {
		return idRegiaoDesenvolvimento;
	}

	public void setIdRegiaoDesenvolvimento(Integer idRegiaoDesenvolvimento) {
		this.idRegiaoDesenvolvimento = idRegiaoDesenvolvimento;
	}

	public String getNomeRegiaoDesenvolvimento() {
		return nomeRegiaoDesenvolvimento;
	}

	public void setNomeRegiaoDesenvolvimento(String nomeRegiaoDesenvolvimento) {
		this.nomeRegiaoDesenvolvimento = nomeRegiaoDesenvolvimento;
	}
	
}
