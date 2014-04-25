package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * [UCXXXX] - Gerar Relatório Consultar Debitos
 * 
 * @author Rafael Corrêa
 * @date 07/03/2007
 */
public class RelatorioConsultarDebitosResumidoBean implements RelatorioBean {

	private String idImovel;
	
	private String inscricao;

	private String nomeUsuario;

	private String endereco;
	
	private String situacaoAgua;

	private String situacaoEsgoto;
	
	private String categoria;

	private String contas;

	private String guiasPagamento;

	private String acrescimos;

	private String totalAtualizado;

	private String debitosACobrar;
	
	private String creditosARealizar;

	private String totalGeral;
	
	private String valorTotalDebitos;
	
	private String valorTotalDebitosAtualizado;
	
	private String cpfCnpjUsuario;
	
	private String valorGuiasCliente;
	
	public RelatorioConsultarDebitosResumidoBean(String idImovel,
			String inscricao, String nomeUsuario, String cpfCnpjUsuario, String endereco,
			String situacaoAgua, String situacaoEsgoto, String categoria,
			String contas, String guiasPagamento,
			String acrescimos, String totalAtualizado,
			String debitosACobrar, String creditosARealizar,
			String totalGeral, String valorTotalDebitos,
			String valorTotalDebitosAtualizado, String valorGuiasCliente) {

		this.idImovel = idImovel;
		this.inscricao = inscricao;
		this.nomeUsuario = nomeUsuario;
		this.cpfCnpjUsuario = cpfCnpjUsuario;
		this.endereco = endereco;
		this.situacaoAgua = situacaoAgua;
		this.situacaoEsgoto = situacaoEsgoto;
		this.categoria = categoria;
		this.contas = contas;
		this.guiasPagamento = guiasPagamento;
		this.acrescimos = acrescimos;
		this.totalAtualizado = totalAtualizado;
		this.debitosACobrar = debitosACobrar;
		this.creditosARealizar = creditosARealizar;
		this.totalGeral = totalGeral;
		this.valorTotalDebitos = valorTotalDebitos;
		this.valorTotalDebitosAtualizado = valorTotalDebitosAtualizado;
		this.valorGuiasCliente = valorGuiasCliente;
		
		
	}

	/**
	 * @return Retorna o campo acrescimos.
	 */
	public String getAcrescimos() {
		return acrescimos;
	}

	/**
	 * @param acrescimos O acrescimos a ser setado.
	 */
	public void setAcrescimos(String acrescimos) {
		this.acrescimos = acrescimos;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo contas.
	 */
	public String getContas() {
		return contas;
	}

	/**
	 * @param contas O contas a ser setado.
	 */
	public void setContas(String contas) {
		this.contas = contas;
	}

	/**
	 * @return Retorna o campo creditosARealizar.
	 */
	public String getCreditosARealizar() {
		return creditosARealizar;
	}

	/**
	 * @param creditosARealizar O creditosARealizar a ser setado.
	 */
	public void setCreditosARealizar(String creditosARealizar) {
		this.creditosARealizar = creditosARealizar;
	}

	/**
	 * @return Retorna o campo debitosACobrar.
	 */
	public String getDebitosACobrar() {
		return debitosACobrar;
	}

	/**
	 * @param debitosACobrar O debitosACobrar a ser setado.
	 */
	public void setDebitosACobrar(String debitosACobrar) {
		this.debitosACobrar = debitosACobrar;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo guiasPagamento.
	 */
	public String getGuiasPagamento() {
		return guiasPagamento;
	}

	/**
	 * @param guiasPagamento O guiasPagamento a ser setado.
	 */
	public void setGuiasPagamento(String guiasPagamento) {
		this.guiasPagamento = guiasPagamento;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @return Retorna o campo situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	/**
	 * @param situacaoAgua O situacaoAgua a ser setado.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	/**
	 * @return Retorna o campo situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	/**
	 * @param situacaoEsgoto O situacaoEsgoto a ser setado.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	/**
	 * @return Retorna o campo totalAtualizado.
	 */
	public String getTotalAtualizado() {
		return totalAtualizado;
	}

	/**
	 * @param totalAtualizado O totalAtualizado a ser setado.
	 */
	public void setTotalAtualizado(String totalAtualizado) {
		this.totalAtualizado = totalAtualizado;
	}

	/**
	 * @return Retorna o campo totalGeral.
	 */
	public String getTotalGeral() {
		return totalGeral;
	}

	/**
	 * @param totalGeral O totalGeral a ser setado.
	 */
	public void setTotalGeral(String totalGeral) {
		this.totalGeral = totalGeral;
	}

	/**
	 * @return Retorna o campo valorTotalDebitos.
	 */
	public String getValorTotalDebitos() {
		return valorTotalDebitos;
	}

	/**
	 * @param valorTotalDebitos O valorTotalDebitos a ser setado.
	 */
	public void setValorTotalDebitos(String valorTotalDebitos) {
		this.valorTotalDebitos = valorTotalDebitos;
	}

	/**
	 * @return Retorna o campo valorTotalDebitosAtualizado.
	 */
	public String getValorTotalDebitosAtualizado() {
		return valorTotalDebitosAtualizado;
	}

	/**
	 * @param valorTotalDebitosAtualizado O valorTotalDebitosAtualizado a ser setado.
	 */
	public void setValorTotalDebitosAtualizado(String valorTotalDebitosAtualizado) {
		this.valorTotalDebitosAtualizado = valorTotalDebitosAtualizado;
	}

	public String getCpfCnpjUsuario() {
		return cpfCnpjUsuario;
	}

	public void setCpfCnpjUsuario(String cpfCnpjUsuario) {
		this.cpfCnpjUsuario = cpfCnpjUsuario;
	}

	public String getValorGuiasCliente() {
		return valorGuiasCliente;
	}

	public void setValorGuiasCliente(String valorGuiasCliente) {
		this.valorGuiasCliente = valorGuiasCliente;
	}
	
}
