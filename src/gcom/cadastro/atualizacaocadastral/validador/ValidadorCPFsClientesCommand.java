package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.util.Util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorCPFsClientesCommand extends ValidadorCommand {
	private final String MSG_FORMATO_CPF_CNPJ_INVALIDO = "Formato do CPF/CNPJ de %S inválido";
	private final String MSG_CPF_CNPJ_INVALIDO_BASE = "%S possui CPF/CNPJ na base de dados";

	private IRepositorioClienteImovel repositorioClienteImovel;

	public ValidadorCPFsClientesCommand(AtualizacaoCadastralImovel cadastroImovel
			, Map<String, String> linha
			, IRepositorioClienteImovel repositorioClienteImovel) {
		super(cadastroImovel, linha);
		this.repositorioClienteImovel = repositorioClienteImovel;
	}
	
	public void execute() throws Exception{
		validaCampoCpfCnpj("Usuario");
		validaCampoCpfCnpj("Proprietario");
		validaCampoCpfCnpj("Responsavel");
		
		Collection<ClienteImovel> clientes = repositorioClienteImovel.pesquisarClienteImovel(cadastroImovel.getMatricula());
		
		validaCpfCnpjComBase(linha, clientes, "Usuario", ClienteRelacaoTipo.USUARIO);
		validaCpfCnpjComBase(linha, clientes, "Proprietario", ClienteRelacaoTipo.PROPRIETARIO);
		validaCpfCnpjComBase(linha, clientes, "Responsavel", ClienteRelacaoTipo.RESPONSAVEL);
	}
	
	private void validaCampoCpfCnpj(String cliente){
		if (StringUtils.isNotEmpty(linha.get("cnpjCpf" + cliente)) && Util.cpfCnpjInvalido(linha.get("cnpjCpf" + cliente))){
			cadastroImovel.addMensagemErro(String.format(MSG_FORMATO_CPF_CNPJ_INVALIDO, cliente.toLowerCase()));
		}
	}

	private void validaCpfCnpjComBase(Map<String, String> linha, Collection<ClienteImovel> clientes, String cliente, Short relacao) {
		if (StringUtils.isEmpty(linha.get("cnpjCpf" + cliente))){
			String cpfCnpj = "";
			for (ClienteImovel clienteImovel : clientes) {
				if (clienteImovel.getClienteRelacaoTipo().getId().intValue() == (int) relacao){
					cpfCnpj = clienteImovel.getCliente().getCpf();
					if (StringUtils.isEmpty(cpfCnpj)){
						cpfCnpj = clienteImovel.getCliente().getCnpj();
					}
					if (StringUtils.isNotEmpty(cpfCnpj)){
						cadastroImovel.addMensagemErro(String.format(MSG_CPF_CNPJ_INVALIDO_BASE, cliente));
					}
				}
			}
			
		}
	}
}
