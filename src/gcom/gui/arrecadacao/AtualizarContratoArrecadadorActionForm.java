package gcom.gui.arrecadacao;

import java.util.ArrayList;
import java.util.Collection;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.ArrecadadorContratoTarifa;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC510] Manter Contrato de Arrecadador [SB0001]Atualizar Contrato Arrecadador
 * 
 * @author Marcio Roberto	
 * @date 11/04/2007
 */

public class AtualizarContratoArrecadadorActionForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idContrato;
	 
	private String idArrecadador;
	
	private String idClienteCombo;

	private String nomeClienteCombo;

	private String numeroContrato;

	private String idContaBancariaArrecadador;
	
	private String idContaBancariaArrecadador2;
	
	private String bcoArrecadadorConta;
	
	private String bcoArrecadadorConta2;

	private String agArrecadadorConta;
	
	private String agArrecadadorConta2;

	private String numeroArrecadadorConta;
	
	private String numeroArrecadadorConta2;

	private String idContaBancariaTarifa;
	
	private String bcoTarifaConta;

	private String agTarifaConta;

	private String numeroTarifaConta;

	private String idCliente;
	
	private String nomeCliente;

	private String emailCliente;

	private String idConvenio;

	private String indicadorCobranca;

	private String dtInicioContrato;
	
	private String dtFimContrato;
	
	private String descricaoEmail;
	
	private String dataContratoEncerramento;
	
	private String contratoMotivoCancelamento;
	
	private String indicadorUso;
	
	private String tamanhoMaximoIdentificacaoImovel;
	
	//Colecao Componentes do arrecadador contrato tarifa
	private Collection<ArrecadadorContratoTarifa> colecaoArrecadadorContratoTarifa = new ArrayList<ArrecadadorContratoTarifa>();
	
	private String idContratoArrecadador;
	
	private String formaDeArrecadacao;
	
	private String descricaoFormaArrecadacao;
	
	private String numeroDiaFloat;
	
	private String valorTarifa;
	
	private String tamanhoColecao = "0"; 
	
	private String valorTarifaPercentual;

	public String getValorTarifaPercentual() {
		return valorTarifaPercentual;
	}

	public void setValorTarifaPercentual(String valorTarifaPercentual) {
		this.valorTarifaPercentual = valorTarifaPercentual;
	}

	public String getTamanhoMaximoIdentificacaoImovel() {
		return tamanhoMaximoIdentificacaoImovel;
	}

	public void setTamanhoMaximoIdentificacaoImovel(
			String tamanhoMaximoIdentificacaoImovel) {
		this.tamanhoMaximoIdentificacaoImovel = tamanhoMaximoIdentificacaoImovel;
	}

	public String getAgArrecadadorConta() {
		return agArrecadadorConta;
	}

	public void setAgArrecadadorConta(String agArrecadadorConta) {
		this.agArrecadadorConta = agArrecadadorConta;
	}

	public String getAgTarifaConta() {
		return agTarifaConta;
	}

	public void setAgTarifaConta(String agTarifaConta) {
		this.agTarifaConta = agTarifaConta;
	}

	public String getBcoArrecadadorConta() {
		return bcoArrecadadorConta;
	}

	public void setBcoArrecadadorConta(String bcoArrecadadorConta) {
		this.bcoArrecadadorConta = bcoArrecadadorConta;
	}

	public String getBcoTarifaConta() {
		return bcoTarifaConta;
	}

	public void setBcoTarifaConta(String bcoTarifaConta) {
		this.bcoTarifaConta = bcoTarifaConta;
	}

	public String getDtFimContrato() {
		return dtFimContrato;
	}

	public void setDtFimContrato(String dtFimContrato) {
		this.dtFimContrato = dtFimContrato;
	}

	public String getDtInicioContrato() {
		return dtInicioContrato;
	}

	public void setDtInicioContrato(String dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdClienteCombo() {
		return idClienteCombo;
	}

	public void setIdClienteCombo(String idClienteCombo) {
		this.idClienteCombo = idClienteCombo;
	}

	public String getIdContaBancariaArrecadador() {
		return idContaBancariaArrecadador;
	}

	public void setIdContaBancariaArrecadador(String idContaBancariaArrecadador) {
		this.idContaBancariaArrecadador = idContaBancariaArrecadador;
	}

	public String getIdContaBancariaTarifa() {
		return idContaBancariaTarifa;
	}

	public void setIdContaBancariaTarifa(String idContaBancariaTarifa) {
		this.idContaBancariaTarifa = idContaBancariaTarifa;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}

	public String getIndicadorCobranca() {
		return indicadorCobranca;
	}

	public void setIndicadorCobranca(String indicadorCobranca) {
		this.indicadorCobranca = indicadorCobranca;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeClienteCombo() {
		return nomeClienteCombo;
	}

	public void setNomeClienteCombo(String nomeClienteCombo) {
		this.nomeClienteCombo = nomeClienteCombo;
	}

	public String getNumeroArrecadadorConta() {
		return numeroArrecadadorConta;
	}

	public void setNumeroArrecadadorConta(String numeroArrecadadorConta) {
		this.numeroArrecadadorConta = numeroArrecadadorConta;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getNumeroTarifaConta() {
		return numeroTarifaConta;
	}

	public void setNumeroTarifaConta(String numeroTarifaConta) {
		this.numeroTarifaConta = numeroTarifaConta;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public String getDescricaoEmail() {
		return descricaoEmail;
	}

	public void setDescricaoEmail(String descricaoEmail) {
		this.descricaoEmail = descricaoEmail;
	}
	
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	// Esse método carrega todos os valores da base de dados
	// necesários para exibição da tela AtualizarArrecadador.
	public ArrecadadorContrato setFormValues(ArrecadadorContrato arrecadadorContrato) {
//		Fachada fachada = Fachada.getInstancia();
		
		// seta o atributo de Cliente no objeto arrecadador
		//Arrecadador arrecadador = new Arrecadador();
		//arrecadador.setId(new Integer(getIdArrecadador()));
		//arrecadadorContrato.setArrecadador(arrecadador);        
		
		// Seta Objeto Cliente para Arrecadador
		//FiltroCliente filtroCliente = new FiltroCliente();
		//filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, getIdCliente()));
		//Collection<Cliente> colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());		
		//Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
		//arrecadadorContrato.setCliente(cliente);
		
		return arrecadadorContrato;
	}

	public String getDataContratoEncerramento() {
		return dataContratoEncerramento;
	}

	public void setDataContratoEncerramento(String dataContratoEncerramento) {
		this.dataContratoEncerramento = dataContratoEncerramento;
	}

	public String getContratoMotivoCancelamento() {
		return contratoMotivoCancelamento;
	}

	public void setContratoMotivoCancelamento(String contratoMotivoCancelamento) {
		this.contratoMotivoCancelamento = contratoMotivoCancelamento;
	}

	public Collection<ArrecadadorContratoTarifa> getColecaoArrecadadorContratoTarifa() {
		return colecaoArrecadadorContratoTarifa;
	}

	public void setColecaoArrecadadorContratoTarifa(
			Collection<ArrecadadorContratoTarifa> colecaoArrecadadorContratoTarifa) {
		this.colecaoArrecadadorContratoTarifa = colecaoArrecadadorContratoTarifa;
	}

	public String getDescricaoFormaArrecadacao() {
		return descricaoFormaArrecadacao;
	}

	public void setDescricaoFormaArrecadacao(String descricaoFormaArrecadacao) {
		this.descricaoFormaArrecadacao = descricaoFormaArrecadacao;
	}

	public String getFormaDeArrecadacao() {
		return formaDeArrecadacao;
	}

	public void setFormaDeArrecadacao(String formaDeArrecadacao) {
		this.formaDeArrecadacao = formaDeArrecadacao;
	}

	public String getIdContratoArrecadador() {
		return idContratoArrecadador;
	}

	public void setIdContratoArrecadador(String idContratoArrecadador) {
		this.idContratoArrecadador = idContratoArrecadador;
	}

	public String getNumeroDiaFloat() {
		return numeroDiaFloat;
	}

	public void setNumeroDiaFloat(String numeroDiaFloat) {
		this.numeroDiaFloat = numeroDiaFloat;
	}

	public String getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(String valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public String getTamanhoColecao() {
		return tamanhoColecao;
	}

	public void setTamanhoColecao(String tamanhoColecao) {
		this.tamanhoColecao = tamanhoColecao;
	}

	public String getIdContaBancariaArrecadador2() {
		return idContaBancariaArrecadador2;
	}

	public void setIdContaBancariaArrecadador2(String idContaBancariaArrecadador2) {
		this.idContaBancariaArrecadador2 = idContaBancariaArrecadador2;
	}

	public String getBcoArrecadadorConta2() {
		return bcoArrecadadorConta2;
	}

	public void setBcoArrecadadorConta2(String bcoArrecadadorConta2) {
		this.bcoArrecadadorConta2 = bcoArrecadadorConta2;
	}

	public String getAgArrecadadorConta2() {
		return agArrecadadorConta2;
	}

	public void setAgArrecadadorConta2(String agArrecadadorConta2) {
		this.agArrecadadorConta2 = agArrecadadorConta2;
	}

	public String getNumeroArrecadadorConta2() {
		return numeroArrecadadorConta2;
	}

	public void setNumeroArrecadadorConta2(String numeroArrecadadorConta2) {
		this.numeroArrecadadorConta2 = numeroArrecadadorConta2;
	}
	
	

}

























