package gcom.atualizacaocadastral;

import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.geografico.UnidadeFederacao;

import java.util.Date;

public interface ICliente {

	public abstract Integer getIdCliente();

	public abstract void setIdCliente(Integer idCliente);

	public abstract String getNome();

	public abstract void setNome(String nomeCliente);

	public abstract String getRg();

	public abstract void setRg(String rg);
	
	public abstract UnidadeFederacao getUnidadeFederacao();
	
	public abstract void setUnidadeFederacao(UnidadeFederacao unidadeFederacao);

	public abstract PessoaSexo getPessoaSexo();

	public abstract void setPessoaSexo(PessoaSexo pessoaSexo);

	public abstract String getCpf();

	public abstract void setCpf(String cpf);
	
	public abstract String getCnpj();
	
	public abstract void setCnpj(String cnpj);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract Date getUltimaAlteracao();

	public abstract void setUltimaAlteracao(Date ultimaAlteracao);
	
	public abstract ClienteTipo getClienteTipo();
	
	public abstract void setClienteTipo(ClienteTipo clienteTipo);
	
	public abstract Integer getIdClienteTipo();
	
	public abstract void setIdClienteTipo(Integer idClienteTipo);
	
	public abstract Integer getTipoOperacao();
	
	public abstract void setTipoOperacao(Integer tipoOperacao);
	
	public String getNumeroNIS();
	
	public void setNumeroNIS(String numeroNIS);
	
}
