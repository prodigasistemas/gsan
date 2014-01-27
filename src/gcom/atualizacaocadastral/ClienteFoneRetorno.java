package gcom.atualizacaocadastral;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.IClienteFone;

import java.util.Date;

public class ClienteFoneRetorno implements IClienteFone{

	private Integer id;
	private String ddd;
	private String telefone;
	private FoneTipo foneTipo;
	private Cliente cliente;
	private Date ultimaAlteracao;
	
	public ClienteFoneRetorno(IClienteFone clienteFone){
		this.ddd = clienteFone.getDdd();
		this.telefone = clienteFone.getTelefone();
		this.foneTipo = clienteFone.getFoneTipo();
		this.cliente = clienteFone.getCliente();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public FoneTipo getFoneTipo() {
		return foneTipo;
	}

	public void setFoneTipo(FoneTipo foneTipo) {
		this.foneTipo = foneTipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}