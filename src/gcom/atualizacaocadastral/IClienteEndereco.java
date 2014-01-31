package gcom.atualizacaocadastral;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.EnderecoTipo;

import java.util.Date;

public interface IClienteEndereco {

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public abstract String getNumero();

	public abstract void setNumero(String numero);

	public abstract String getComplemento();

	public abstract void setComplemento(String complemento);

	public abstract Date getUltimaAlteracao();

	public abstract void setUltimaAlteracao(Date ultimaAlteracao);

	public abstract EnderecoTipo getEnderecoTipo();

	public abstract void setEnderecoTipo(EnderecoTipo enderecoTipo);

	public abstract Cliente getCliente();

	public abstract void setCliente(Cliente cliente);

	public abstract String getNomeMunicipio();

	public abstract void setNomeMunicipio(String nomeMunicipio);

	public abstract String getNomeBairro();

	public abstract void setNomeBairro(String nomeBairro);
	
	public abstract String getDescricaoLogradouro();

	public abstract void setDescricaoLogradouro(String descricaoLogradouro);
	
	public abstract Integer getCodigoCep();

	public abstract void setCodigoCep(Integer codigoCep);
}