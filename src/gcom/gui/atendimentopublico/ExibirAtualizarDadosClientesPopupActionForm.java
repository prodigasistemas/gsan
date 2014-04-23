package gcom.gui.atendimentopublico;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;

import java.util.Collection;
import java.util.HashMap;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Daniel Alves
 * @date 26/07/2010
 */
public class ExibirAtualizarDadosClientesPopupActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String matricula;
	
	private String inscricao;

	//private String nomeConta;
	
	private String endereco;
	
	//codigo do clienteImovel a ser atualizado
	private String codClienteImovel;
	
	//codigo do clienteImovel usuário
	private String codClienteImovelUsuario;
	
	//ClienteModificado
	private String idCliente;	
    
	private String novoNomeCliente;
	
	private String tipoPessoa;
	
	private String cpfCnpjCliente;
	
	// Categorias, Subcategorias e Economias
	private Collection categoriaSubcategoriaEconomia;
	
	private String totalEconomias;

	// Cliente
	private Collection<ClienteImovel> colecaoClienteImovel;

	private Collection<ClienteFone> clienteFone;
	
	private Collection<ClienteFone> removerClienteFone;
	
	private HashMap telefonePrincipal;
	
	private String fecharPopup;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	/*public String getNomeConta() {
		return nomeConta;
	}
	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}*/
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Collection getCategoriaSubcategoriaEconomia() {
		return categoriaSubcategoriaEconomia;
	}
	public void setCategoriaSubcategoriaEconomia(
			Collection categoriaSubcategoriaEconomia) {
		this.categoriaSubcategoriaEconomia = categoriaSubcategoriaEconomia;
	}		
	public Collection<ClienteImovel> getColecaoClienteImovel() {
		return colecaoClienteImovel;
	}
	public void setColecaoClienteImovel(
			Collection<ClienteImovel> colecaoClienteImovel) {
		this.colecaoClienteImovel = colecaoClienteImovel;
	}
	public String getNovoNomeCliente() {
		return novoNomeCliente;
	}
	public void setNovoNomeCliente(String novoNomeCliente) {
		this.novoNomeCliente = novoNomeCliente;
	}
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public Collection<ClienteFone> getClienteFone() {
		return clienteFone;
	}
	public void setClienteFone(Collection<ClienteFone> clienteFone) {
		this.clienteFone = clienteFone;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getTotalEconomias() {
		return totalEconomias;
	}
	public void setTotalEconomias(String totalEconomias) {
		this.totalEconomias = totalEconomias;
	}
	public Collection<ClienteFone> getRemoverClienteFone() {
		return removerClienteFone;
	}
	public void setRemoverClienteFone(Collection<ClienteFone> removerClienteFone) {
		this.removerClienteFone = removerClienteFone;
	}
	public String getCodClienteImovel() {
		return codClienteImovel;
	}
	public void setCodClienteImovel(String codClienteImovel) {
		this.codClienteImovel = codClienteImovel;
	}
	public String getFecharPopup() {
		return fecharPopup;
	}
	public void setFecharPopup(String fecharPopup) {
		this.fecharPopup = fecharPopup;
	}
	public String getCodClienteImovelUsuario() {
		return codClienteImovelUsuario;
	}
	public void setCodClienteImovelUsuario(String codClienteImovelUsuario) {
		this.codClienteImovelUsuario = codClienteImovelUsuario;
	}
	public HashMap getTelefonePrincipal() {
		return telefonePrincipal;
	}
	public void setTelefonePrincipal(HashMap telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}
	
}
