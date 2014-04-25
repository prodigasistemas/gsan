package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * [UCXXXX] - Gerar Relatório Consultar Debitos
 * 
 * @author Rafael Corrêa
 * @date 07/03/2007
 */
public class RelatorioConsultarDebitosBean implements RelatorioBean {

	private String idConta;
	
	private String mesAnoConta;

	private String vencimentoConta;

	private String valorAgua;
	
	private String consumoAgua;

	private String valorEsgoto;
	
	private String consumoEsgoto;

	private String valorDebitos;

	private String valorCreditos;

	private String valorConta;

	private String acrescimoImpontualidade;

	private String situacaoConta;
	
	private String idDebitoACobrar;

	private String tipoDebitoDebitoACobrar;

	private String mesAnoReferenciaDebito;

	private String mesAnoCobrancaDebito;

	private String parcelasACobrarDebito;

	private String valorACobrarDebito;
	
	private String idCreditoARealizar;
	
	private String tipoCredito;

	private String mesAnoReferenciaCredito;

	private String mesAnoCobrancaCredito;

	private String parcelasACreditar;

	private String valorACreditar;
	
	private String idGuiaPagamento;

	private String tipoDebitoGuia;

	private String dataEmissaoGuia;
	
	private String dataVencimentoGuia;

	private String valorGuia;
	
	private String valorTotalDebitos;
	
	private String valorTotalDebitosAtualizado;
	
	private String idImovel;
	
	private String endereco;
	
	private String nomeUsuario;
	
	private String imprimirEndereco;
	
	private String cpfCnpjUsuario;

	public RelatorioConsultarDebitosBean(String mesAnoConta,
			String vencimentoConta, String valorAgua, String consumoAgua,
			String valorEsgoto, String consumoEsgoto, String valorDebitos,
			String valorCreditos, String valorConta,
			String acrescimoImpontualidade, String situacaoConta,
			String idDebitoACobrar, String tipoDebitoDebitoACobrar,
			String mesAnoReferenciaDebito, String mesAnoCobrancaDebito,
			String parcelasACobrarDebito, String valorACobrarDebito,
			String idCreditoARealizar, String tipoCredito,
			String mesAnoReferenciaCredito, String mesAnoCobrancaCredito,
			String parcelasACreditar, String valorACreditar,
			String idGuiaPagamento, String tipoDebitoGuia,
			String dataEmissaoGuia, String dataVencimentoGuia,
			String valorGuia, String valorTotalDebitos,
			String valorTotalDebitosAtualizado) {

		this.mesAnoConta = mesAnoConta;
		this.vencimentoConta = vencimentoConta;
		this.valorAgua = valorAgua;
		this.consumoAgua = consumoAgua;
		this.valorEsgoto = valorEsgoto;
		this.consumoEsgoto = consumoEsgoto;
		this.valorDebitos = valorDebitos;
		this.valorCreditos = valorCreditos;
		this.valorConta = valorConta;
		this.acrescimoImpontualidade = acrescimoImpontualidade;
		this.situacaoConta = situacaoConta;
		this.idDebitoACobrar = idDebitoACobrar;
		this.tipoDebitoDebitoACobrar = tipoDebitoDebitoACobrar;
		this.mesAnoReferenciaDebito = mesAnoReferenciaDebito;
		this.mesAnoCobrancaDebito = mesAnoCobrancaDebito;
		this.parcelasACobrarDebito = parcelasACobrarDebito;
		this.valorACobrarDebito = valorACobrarDebito;
		this.idCreditoARealizar = idCreditoARealizar;
		this.tipoCredito = tipoCredito;
		this.mesAnoReferenciaCredito = mesAnoReferenciaCredito;
		this.mesAnoCobrancaCredito = mesAnoCobrancaCredito;
		this.parcelasACreditar = parcelasACreditar;
		this.valorACreditar = valorACreditar;
		this.idGuiaPagamento = idGuiaPagamento;
		this.tipoDebitoGuia = tipoDebitoGuia;
		this.dataEmissaoGuia = dataEmissaoGuia;
		this.dataVencimentoGuia = dataVencimentoGuia;
		this.valorGuia = valorGuia;
		this.valorTotalDebitos = valorTotalDebitos;
		this.valorTotalDebitosAtualizado = valorTotalDebitosAtualizado;

	}

	public RelatorioConsultarDebitosBean(String idConta, String mesAnoConta,
			String vencimentoConta, String valorAgua, String consumoAgua,
			String valorEsgoto, String consumoEsgoto, String valorDebitos,
			String valorCreditos, String valorConta,
			String acrescimoImpontualidade, String situacaoConta,
			String idDebitoACobrar, String tipoDebitoDebitoACobrar,
			String mesAnoReferenciaDebito, String mesAnoCobrancaDebito,
			String parcelasACobrarDebito, String valorACobrarDebito,
			String idCreditoARealizar, String tipoCredito,
			String mesAnoReferenciaCredito, String mesAnoCobrancaCredito,
			String parcelasACreditar, String valorACreditar,
			String idGuiaPagamento, String tipoDebitoGuia,
			String dataEmissaoGuia, String dataVencimentoGuia,
			String valorGuia, String valorTotalDebitos,
			String valorTotalDebitosAtualizado, String idImovel) {
		
		this.idConta = idConta;
		this.mesAnoConta = mesAnoConta;
		this.vencimentoConta = vencimentoConta;
		this.valorAgua = valorAgua;
		this.consumoAgua = consumoAgua;
		this.valorEsgoto = valorEsgoto;
		this.consumoEsgoto = consumoEsgoto;
		this.valorDebitos = valorDebitos;
		this.valorCreditos = valorCreditos;
		this.valorConta = valorConta;
		this.acrescimoImpontualidade = acrescimoImpontualidade;
		this.situacaoConta = situacaoConta;
		this.idDebitoACobrar = idDebitoACobrar;
		this.tipoDebitoDebitoACobrar = tipoDebitoDebitoACobrar;
		this.mesAnoReferenciaDebito = mesAnoReferenciaDebito;
		this.mesAnoCobrancaDebito = mesAnoCobrancaDebito;
		this.parcelasACobrarDebito = parcelasACobrarDebito;
		this.valorACobrarDebito = valorACobrarDebito;
		this.idCreditoARealizar = idCreditoARealizar;
		this.tipoCredito = tipoCredito;
		this.mesAnoReferenciaCredito = mesAnoReferenciaCredito;
		this.mesAnoCobrancaCredito = mesAnoCobrancaCredito;
		this.parcelasACreditar = parcelasACreditar;
		this.valorACreditar = valorACreditar;
		this.idGuiaPagamento = idGuiaPagamento;
		this.tipoDebitoGuia = tipoDebitoGuia;
		this.dataEmissaoGuia = dataEmissaoGuia;
		this.dataVencimentoGuia = dataVencimentoGuia;
		this.valorGuia = valorGuia;
		this.valorTotalDebitos = valorTotalDebitos;
		this.valorTotalDebitosAtualizado = valorTotalDebitosAtualizado;
		this.idImovel = idImovel;
		
	}
	
	public RelatorioConsultarDebitosBean(String idConta, String mesAnoConta,
			String vencimentoConta, String valorAgua, String consumoAgua,
			String valorEsgoto, String consumoEsgoto, String valorDebitos,
			String valorCreditos, String valorConta,
			String acrescimoImpontualidade, String situacaoConta,
			String idDebitoACobrar, String tipoDebitoDebitoACobrar,
			String mesAnoReferenciaDebito, String mesAnoCobrancaDebito,
			String parcelasACobrarDebito, String valorACobrarDebito,
			String idCreditoARealizar, String tipoCredito,
			String mesAnoReferenciaCredito, String mesAnoCobrancaCredito,
			String parcelasACreditar, String valorACreditar,
			String idGuiaPagamento, String tipoDebitoGuia,
			String dataEmissaoGuia, String dataVencimentoGuia,
			String valorGuia, String valorTotalDebitos,
			String valorTotalDebitosAtualizado, String idImovel, String endereco, 
			String nomeUsuario, String imprimirEndereco, String cpfCnpjUsuario) {
		
		this.idConta = idConta;
		this.mesAnoConta = mesAnoConta;
		this.vencimentoConta = vencimentoConta;
		this.valorAgua = valorAgua;
		this.consumoAgua = consumoAgua;
		this.valorEsgoto = valorEsgoto;
		this.consumoEsgoto = consumoEsgoto;
		this.valorDebitos = valorDebitos;
		this.valorCreditos = valorCreditos;
		this.valorConta = valorConta;
		this.acrescimoImpontualidade = acrescimoImpontualidade;
		this.situacaoConta = situacaoConta;
		this.idDebitoACobrar = idDebitoACobrar;
		this.tipoDebitoDebitoACobrar = tipoDebitoDebitoACobrar;
		this.mesAnoReferenciaDebito = mesAnoReferenciaDebito;
		this.mesAnoCobrancaDebito = mesAnoCobrancaDebito;
		this.parcelasACobrarDebito = parcelasACobrarDebito;
		this.valorACobrarDebito = valorACobrarDebito;
		this.idCreditoARealizar = idCreditoARealizar;
		this.tipoCredito = tipoCredito;
		this.mesAnoReferenciaCredito = mesAnoReferenciaCredito;
		this.mesAnoCobrancaCredito = mesAnoCobrancaCredito;
		this.parcelasACreditar = parcelasACreditar;
		this.valorACreditar = valorACreditar;
		this.idGuiaPagamento = idGuiaPagamento;
		this.tipoDebitoGuia = tipoDebitoGuia;
		this.dataEmissaoGuia = dataEmissaoGuia;
		this.dataVencimentoGuia = dataVencimentoGuia;
		this.valorGuia = valorGuia;
		this.valorTotalDebitos = valorTotalDebitos;
		this.valorTotalDebitosAtualizado = valorTotalDebitosAtualizado;
		this.idImovel = idImovel;
		this.endereco = endereco;
		this.nomeUsuario = nomeUsuario;
		this.imprimirEndereco = imprimirEndereco;
		this.cpfCnpjUsuario = cpfCnpjUsuario;
		
	}
	
	/**
	 * @return Retorna o campo consumoAgua.
	 */
	public String getConsumoAgua() {
		return consumoAgua;
	}

	/**
	 * @param consumoAgua O consumoAgua a ser setado.
	 */
	public void setConsumoAgua(String consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	/**
	 * @return Retorna o campo consumoEsgoto.
	 */
	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}

	/**
	 * @param consumoEsgoto O consumoEsgoto a ser setado.
	 */
	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
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

	/**
	 * @return Retorna o campo idCreditoARealizar.
	 */
	public String getIdCreditoARealizar() {
		return idCreditoARealizar;
	}

	/**
	 * @param idCreditoARealizar O idCreditoARealizar a ser setado.
	 */
	public void setIdCreditoARealizar(String idCreditoARealizar) {
		this.idCreditoARealizar = idCreditoARealizar;
	}

	/**
	 * @return Retorna o campo idDebitoACobrar.
	 */
	public String getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	/**
	 * @param idDebitoACobrar O idDebitoACobrar a ser setado.
	 */
	public void setIdDebitoACobrar(String idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}

	/**
	 * @return Retorna o campo idGuiaPagamento.
	 */
	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	/**
	 * @param idGuiaPagamento O idGuiaPagamento a ser setado.
	 */
	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	/**
	 * @return Retorna o campo acrescimoImpontualidade.
	 */
	public String getAcrescimoImpontualidade() {
		return acrescimoImpontualidade;
	}

	/**
	 * @param acrescimoImpontualidade O acrescimoImpontualidade a ser setado.
	 */
	public void setAcrescimoImpontualidade(String acrescimoImpontualidade) {
		this.acrescimoImpontualidade = acrescimoImpontualidade;
	}

	/**
	 * @return Retorna o campo dataEmissaoGuia.
	 */
	public String getDataEmissaoGuia() {
		return dataEmissaoGuia;
	}

	/**
	 * @param dataEmissaoGuia O dataEmissaoGuia a ser setado.
	 */
	public void setDataEmissaoGuia(String dataEmissaoGuia) {
		this.dataEmissaoGuia = dataEmissaoGuia;
	}

	/**
	 * @return Retorna o campo dataVencimentoGuia.
	 */
	public String getDataVencimentoGuia() {
		return dataVencimentoGuia;
	}

	/**
	 * @param dataVencimentoGuia O dataVencimentoGuia a ser setado.
	 */
	public void setDataVencimentoGuia(String dataVencimentoGuia) {
		this.dataVencimentoGuia = dataVencimentoGuia;
	}

	/**
	 * @return Retorna o campo mesAnoCobrancaCredito.
	 */
	public String getMesAnoCobrancaCredito() {
		return mesAnoCobrancaCredito;
	}

	/**
	 * @param mesAnoCobrancaCredito O mesAnoCobrancaCredito a ser setado.
	 */
	public void setMesAnoCobrancaCredito(String mesAnoCobrancaCredito) {
		this.mesAnoCobrancaCredito = mesAnoCobrancaCredito;
	}

	/**
	 * @return Retorna o campo mesAnoCobrancaDebito.
	 */
	public String getMesAnoCobrancaDebito() {
		return mesAnoCobrancaDebito;
	}

	/**
	 * @param mesAnoCobrancaDebito O mesAnoCobrancaDebito a ser setado.
	 */
	public void setMesAnoCobrancaDebito(String mesAnoCobrancaDebito) {
		this.mesAnoCobrancaDebito = mesAnoCobrancaDebito;
	}

	/**
	 * @return Retorna o campo mesAnoConta.
	 */
	public String getMesAnoConta() {
		return mesAnoConta;
	}

	/**
	 * @param mesAnoConta O mesAnoConta a ser setado.
	 */
	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	/**
	 * @return Retorna o campo mesAnoReferenciaCredito.
	 */
	public String getMesAnoReferenciaCredito() {
		return mesAnoReferenciaCredito;
	}

	/**
	 * @param mesAnoReferenciaCredito O mesAnoReferenciaCredito a ser setado.
	 */
	public void setMesAnoReferenciaCredito(String mesAnoReferenciaCredito) {
		this.mesAnoReferenciaCredito = mesAnoReferenciaCredito;
	}

	/**
	 * @return Retorna o campo mesAnoReferenciaDebito.
	 */
	public String getMesAnoReferenciaDebito() {
		return mesAnoReferenciaDebito;
	}

	/**
	 * @param mesAnoReferenciaDebito O mesAnoReferenciaDebito a ser setado.
	 */
	public void setMesAnoReferenciaDebito(String mesAnoReferenciaDebito) {
		this.mesAnoReferenciaDebito = mesAnoReferenciaDebito;
	}

	/**
	 * @return Retorna o campo parcelasACobrarDebito.
	 */
	public String getParcelasACobrarDebito() {
		return parcelasACobrarDebito;
	}

	/**
	 * @param parcelasACobrarDebito O parcelasACobrarDebito a ser setado.
	 */
	public void setParcelasACobrarDebito(String parcelasACobrarDebito) {
		this.parcelasACobrarDebito = parcelasACobrarDebito;
	}

	/**
	 * @return Retorna o campo parcelasACreditar.
	 */
	public String getParcelasACreditar() {
		return parcelasACreditar;
	}

	/**
	 * @param parcelasACreditar O parcelasACreditar a ser setado.
	 */
	public void setParcelasACreditar(String parcelasACreditar) {
		this.parcelasACreditar = parcelasACreditar;
	}

	/**
	 * @return Retorna o campo situacaoConta.
	 */
	public String getSituacaoConta() {
		return situacaoConta;
	}

	/**
	 * @param situacaoConta O situacaoConta a ser setado.
	 */
	public void setSituacaoConta(String situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	/**
	 * @return Retorna o campo tipoCredito.
	 */
	public String getTipoCredito() {
		return tipoCredito;
	}

	/**
	 * @param tipoCredito O tipoCredito a ser setado.
	 */
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	/**
	 * @return Retorna o campo tipoDebitoDebitoACobrar.
	 */
	public String getTipoDebitoDebitoACobrar() {
		return tipoDebitoDebitoACobrar;
	}

	/**
	 * @param tipoDebitoDebitoACobrar O tipoDebitoDebitoACobrar a ser setado.
	 */
	public void setTipoDebitoDebitoACobrar(String tipoDebitoDebitoACobrar) {
		this.tipoDebitoDebitoACobrar = tipoDebitoDebitoACobrar;
	}

	/**
	 * @return Retorna o campo tipoDebitoGuia.
	 */
	public String getTipoDebitoGuia() {
		return tipoDebitoGuia;
	}

	/**
	 * @param tipoDebitoGuia O tipoDebitoGuia a ser setado.
	 */
	public void setTipoDebitoGuia(String tipoDebitoGuia) {
		this.tipoDebitoGuia = tipoDebitoGuia;
	}

	/**
	 * @return Retorna o campo valorACobrarDebito.
	 */
	public String getValorACobrarDebito() {
		return valorACobrarDebito;
	}

	/**
	 * @param valorACobrarDebito O valorACobrarDebito a ser setado.
	 */
	public void setValorACobrarDebito(String valorACobrarDebito) {
		this.valorACobrarDebito = valorACobrarDebito;
	}

	/**
	 * @return Retorna o campo valorACreditar.
	 */
	public String getValorACreditar() {
		return valorACreditar;
	}

	/**
	 * @param valorACreditar O valorACreditar a ser setado.
	 */
	public void setValorACreditar(String valorACreditar) {
		this.valorACreditar = valorACreditar;
	}

	/**
	 * @return Retorna o campo valorAgua.
	 */
	public String getValorAgua() {
		return valorAgua;
	}

	/**
	 * @param valorAgua O valorAgua a ser setado.
	 */
	public void setValorAgua(String valorAgua) {
		this.valorAgua = valorAgua;
	}

	/**
	 * @return Retorna o campo valorConta.
	 */
	public String getValorConta() {
		return valorConta;
	}

	/**
	 * @param valorConta O valorConta a ser setado.
	 */
	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}

	/**
	 * @return Retorna o campo valorCreditos.
	 */
	public String getValorCreditos() {
		return valorCreditos;
	}

	/**
	 * @param valorCreditos O valorCreditos a ser setado.
	 */
	public void setValorCreditos(String valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	/**
	 * @return Retorna o campo valorDebitos.
	 */
	public String getValorDebitos() {
		return valorDebitos;
	}

	/**
	 * @param valorDebitos O valorDebitos a ser setado.
	 */
	public void setValorDebitos(String valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	/**
	 * @return Retorna o campo valorEsgoto.
	 */
	public String getValorEsgoto() {
		return valorEsgoto;
	}

	/**
	 * @param valorEsgoto O valorEsgoto a ser setado.
	 */
	public void setValorEsgoto(String valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	/**
	 * @return Retorna o campo valorGuia.
	 */
	public String getValorGuia() {
		return valorGuia;
	}

	/**
	 * @param valorGuia O valorGuia a ser setado.
	 */
	public void setValorGuia(String valorGuia) {
		this.valorGuia = valorGuia;
	}

	/**
	 * @return Retorna o campo vencimentoConta.
	 */
	public String getVencimentoConta() {
		return vencimentoConta;
	}

	/**
	 * @param vencimentoConta O vencimentoConta a ser setado.
	 */
	public void setVencimentoConta(String vencimentoConta) {
		this.vencimentoConta = vencimentoConta;
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
	 * @return Retorna o campo idConta.
	 */
	public String getIdConta() {
		return idConta;
	}

	/**
	 * @param idConta O idConta a ser setado.
	 */
	public void setIdConta(String idConta) {
		this.idConta = idConta;
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
	 * @return Retorna o campo imprimirEndereco.
	 */
	public String getImprimirEndereco() {
		return imprimirEndereco;
	}

	/**
	 * @param imprimirEndereco O imprimirEndereco a ser setado.
	 */
	public void setImprimirEndereco(String imprimirEndereco) {
		this.imprimirEndereco = imprimirEndereco;
	}

	public String getCpfCnpjUsuario() {
		return cpfCnpjUsuario;
	}

	public void setCpfCnpjUsuario(String cpfCnpjUsuario) {
		this.cpfCnpjUsuario = cpfCnpjUsuario;
	}

	
}
