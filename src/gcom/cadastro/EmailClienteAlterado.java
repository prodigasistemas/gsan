package gcom.cadastro;

import gcom.cadastro.cliente.Cliente;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

/**
 * @author Fernando Fontelles
 * @created 09/07/2010
 */
@ControleAlteracao()
public class EmailClienteAlterado extends ObjetoTransacao {
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Cliente idCliente;
	private String nomeClienteAnterior;
	private String cpfAnterior;
	private String cnpjAnterior;
	private String emailAnterior;
	
	private String nomeSolicitante;
	private String cpfSolicitante;
	
	private String nomeClienteAtual;
	private String cpfAtual;
	private String cnpjAtual;
	private String emailAtual;
	
	private Date confirmacaoOnline;
	private Date solicitacaoOnline;

	private Date ultimaAlteracao;
	
	private Integer telefoneContato;
	
	public EmailClienteAlterado(){
		
	}

	public EmailClienteAlterado(Cliente idCliente, String nomeClienteAnterior, 
			String cpfAnterior, String cnpjAnterior, String emailAnterior, 
			String nomeSolicitante, String cpfSolicitante, String nomeClienteAtual, 
			String cpfAtual, String cnpjAtual, String emailAtual, Date solicitacaoOnline) {
		super();
		// TODO Auto-generated constructor stub
		this.idCliente = idCliente;
		this.nomeClienteAnterior = nomeClienteAnterior;
		this.cpfAnterior = cpfAnterior;
		this.cnpjAnterior = cnpjAnterior;
		this.emailAnterior = emailAnterior;
		this.nomeSolicitante = nomeSolicitante;
		this.cpfSolicitante = cpfSolicitante;
		this.nomeClienteAtual = nomeClienteAtual;
		this.cpfAtual = cpfAtual;
		this.cnpjAtual = cnpjAtual;
		this.emailAtual = emailAtual;
		this.solicitacaoOnline = solicitacaoOnline;
		
	}

	public Date getConfirmacaoOnline() {
		return confirmacaoOnline;
	}

	public void setConfirmacaoOnline(Date confirmacaoOnline) {
		this.confirmacaoOnline = confirmacaoOnline;
	}

	public Date getSolicitacaoOnline() {
		return solicitacaoOnline;
	}

	public void setSolicitacaoOnline(Date solicitacaoOnline) {
		this.solicitacaoOnline = solicitacaoOnline;
	}

	public String getCnpjAnterior() {
		return cnpjAnterior;
	}

	public void setCnpjAnterior(String cnpjAnterior) {
		this.cnpjAnterior = cnpjAnterior;
	}

	public String getCnpjAtual() {
		return cnpjAtual;
	}

	public void setCnpjAtual(String cnpjAtual) {
		this.cnpjAtual = cnpjAtual;
	}

	public String getCpfAnterior() {
		return cpfAnterior;
	}

	public void setCpfAnterior(String cpfAnterior) {
		this.cpfAnterior = cpfAnterior;
	}

	public String getCpfAtual() {
		return cpfAtual;
	}

	public void setCpfAtual(String cpfAtual) {
		this.cpfAtual = cpfAtual;
	}

	public String getCpfSolicitante() {
		return cpfSolicitante;
	}

	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfSolicitante = cpfSolicitante;
	}

	public String getEmailAnterior() {
		return emailAnterior;
	}

	public void setEmailAnterior(String emailAnterior) {
		this.emailAnterior = emailAnterior;
	}

	public String getEmailAtual() {
		return emailAtual;
	}

	public void setEmailAtual(String emailAtual) {
		this.emailAtual = emailAtual;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeClienteAnterior() {
		return nomeClienteAnterior;
	}

	public void setNomeClienteAnterior(String nomeClienteAnterior) {
		this.nomeClienteAnterior = nomeClienteAnterior;
	}

	public String getNomeClienteAtual() {
		return nomeClienteAtual;
	}

	public void setNomeClienteAtual(String nomeClienteAtual) {
		this.nomeClienteAtual = nomeClienteAtual;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	@Override
	public Date getUltimaAlteracao() {
		// TODO Auto-generated method stub
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		// TODO Auto-generated method stub
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(Integer telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

}
