package gcom.relatorio.cobranca.parcelamento;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos
 * 
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioParcelamentoDetalhamentoBean implements RelatorioBean {

	private String matriculaImovel;

	private String nomeCliente;

	private String referencia1;

	private String valorFatura1;

	private String referencia2;

	private String valorFatura2;

	private String codigoServico;

	private String descricaoServico;

	private String valorServico;

	private String numeroGuia;

	private String descricaoGuia;

	private String valorGuia;

	private String codigoCredito;

	private String descricaoCredito;

	private String valorCredito;

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosBean
	 */
	public RelatorioParcelamentoDetalhamentoBean(String matriculaImovel,
			String nomeCliente, String referencia1, String valorFatura1,
			String referencia2, String valorFatura2, String codigoServico,
			String descricaoServico, String valorServico, String numeroGuia,
			String descricaoGuia, String valorGuia, String codigoCredito,
			String descricaoCredito, String valorCredito) {
		this.matriculaImovel = matriculaImovel;
		this.nomeCliente = nomeCliente;
		this.referencia1 = referencia1;
		this.valorFatura1 = valorFatura1;
		this.referencia2 = referencia2;
		this.valorFatura2 = valorFatura2;
		this.codigoServico = codigoServico;
		this.descricaoServico = descricaoServico;
		this.valorServico = valorServico;
		this.numeroGuia = numeroGuia;
		this.descricaoGuia = descricaoGuia;
		this.valorGuia = valorGuia;
		this.codigoCredito = codigoCredito;
		this.descricaoCredito = descricaoCredito;
		this.valorCredito = valorCredito;

	}

	public String getCodigoCredito() {
		return codigoCredito;
	}

	public void setCodigoCredito(String codigoCredito) {
		this.codigoCredito = codigoCredito;
	}

	public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}

	public String getDescricaoCredito() {
		return descricaoCredito;
	}

	public void setDescricaoCredito(String descricaoCredito) {
		this.descricaoCredito = descricaoCredito;
	}

	public String getDescricaoGuia() {
		return descricaoGuia;
	}

	public void setDescricaoGuia(String descricaoGuia) {
		this.descricaoGuia = descricaoGuia;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public String getReferencia1() {
		return referencia1;
	}

	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}

	public String getReferencia2() {
		return referencia2;
	}

	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}

	public String getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(String valorCredito) {
		this.valorCredito = valorCredito;
	}

	public String getValorFatura1() {
		return valorFatura1;
	}

	public void setValorFatura1(String valorFatura1) {
		this.valorFatura1 = valorFatura1;
	}

	public String getValorFatura2() {
		return valorFatura2;
	}

	public void setValorFatura2(String valorFatura2) {
		this.valorFatura2 = valorFatura2;
	}

	public String getValorGuia() {
		return valorGuia;
	}

	public void setValorGuia(String valorGuia) {
		this.valorGuia = valorGuia;
	}

	public String getValorServico() {
		return valorServico;
	}

	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
