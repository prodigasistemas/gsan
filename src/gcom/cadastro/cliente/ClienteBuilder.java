package gcom.cadastro.cliente;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import org.apache.commons.lang.StringUtils;

public abstract class ClienteBuilder {

	protected AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	protected IClienteAtualizacaoCadastral clienteTxt = null;
	
	public final static String USUARIO = "Usuario";
	public final static String PROPRIETARIO = "Proprietario";
	public final static String RESPONSAVEL = "Responsavel";
	
	public ClienteBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		clienteTxt = new ClienteAtualizacaoCadastral();
	}
	
	protected void buildCliente(String tipoCliente, Short clienteRelacaoTipo) {
		clienteTxt.setNome(atualizacaoCadastralImovel.getLinhaCliente("nome" + tipoCliente));
		clienteTxt.setCpf(atualizacaoCadastralImovel.getLinhaCliente("cpf" + tipoCliente));
		clienteTxt.setCnpj(atualizacaoCadastralImovel.getLinhaCliente("cnpj" + tipoCliente));
		clienteTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rg" + tipoCliente));
		clienteTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRg" + tipoCliente));
		String campo = atualizacaoCadastralImovel.getLinhaCliente("sexo" + tipoCliente);
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			PessoaSexo sexo = new PessoaSexo(Integer.parseInt(campo));
			clienteTxt.setPessoaSexo(sexo);
		}
		campo = atualizacaoCadastralImovel.getLinhaCliente("tipoPessoa" + tipoCliente);
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdClienteTipo(Integer.parseInt(campo));
		}
		clienteTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("email" + tipoCliente));
		clienteTxt.setIdCliente(new Integer(atualizacaoCadastralImovel.getLinhaCliente("matricula" + tipoCliente)));
		
		clienteTxt.setIdClienteRelacaoTipo(new Integer(clienteRelacaoTipo));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdImovel(Integer.parseInt(campo));
		}
	}
	
	public abstract IClienteAtualizacaoCadastral buildCliente(Short clienteRelacaoTipo);
}
