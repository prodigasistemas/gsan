package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorCPFsClientesCommand extends ValidadorCommand {
	private final String MSG_FORMATO_CPF_CNPJ_INVALIDO = "Formato do CPF/CNPJ de %S inválido";
	private final String MSG_CPF_CNPJ_INVALIDO_BASE = "Não é possível apagar cpf/cnpj do %S";

	private IRepositorioClienteImovel repositorioClienteImovel;

	public ValidadorCPFsClientesCommand(AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha,
			IRepositorioClienteImovel repositorioClienteImovel) {
		super(cadastroImovel, linha);
		this.repositorioClienteImovel = repositorioClienteImovel;
	}
	
	public void execute() throws Exception {
		validarCampoCpfCnpj("Usuario");
		validarCampoCpfCnpj("Proprietario");
		validarCampoCpfCnpj("Responsavel");
		
		Collection<ClienteImovel> clientes = repositorioClienteImovel.pesquisarClienteImovel(cadastroImovel.getMatricula());
		
		validarCpfCnpjComBase(linha, clientes, "Usuario", ClienteRelacaoTipo.USUARIO);
		validarCpfCnpjComBase(linha, clientes, "Proprietario", ClienteRelacaoTipo.PROPRIETARIO);
		validarCpfCnpjComBase(linha, clientes, "Responsavel", ClienteRelacaoTipo.RESPONSAVEL);
	}
	
	private void validarCampoCpfCnpj(String cliente) {
		
		if (StringUtils.isNotEmpty(linha.get("cnpjCpf" + cliente))
				&& Util.cpfCnpjInvalido(linha.get("cnpjCpf" + cliente))) {
			cadastroImovel.addMensagemErro(String.format(MSG_FORMATO_CPF_CNPJ_INVALIDO, cliente.toLowerCase()));
		}
	}

	private void validarCpfCnpjComBase(Map<String, String> linha,
			Collection<ClienteImovel> clientes,  String cliente, Short relacao) {
		
		Integer matriculaRetorno = Integer.valueOf(linha.get("matricula" + cliente));
		String cpfCpnjRetorno = linha.get("cnpjCpf" + cliente);
		
		if (StringUtils.isEmpty(cpfCpnjRetorno)) {
			
			for (ClienteImovel clienteImovel : clientes) {
				
				int relacaoTipoBase = clienteImovel.getClienteRelacaoTipo().getId().intValue();
				
				if ((relacao.equals(ClienteRelacaoTipo.USUARIO))
						|| (relacao.equals(ClienteRelacaoTipo.PROPRIETARIO)
								&& linha.get("usuarioProprietario").equals(String.valueOf(ConstantesSistema.NAO)))
						|| (relacao.equals(ClienteRelacaoTipo.RESPONSAVEL) 
								&& linha.get("tipoResponsavel").equals(String.valueOf(ConstantesSistema.NAO)))) {

					if (relacaoTipoBase == (int) relacao && matriculaRetorno.equals(clienteImovel.getCliente().getId())) {
						verificarCpfCnpjVazio(cliente, clienteImovel);
					}
				}
			}
		}
	}

	private void verificarCpfCnpjVazio(String cliente,
			ClienteImovel clienteImovel) {
		
		String cpfCnpj = clienteImovel.getCliente().getCpf();
		
		if (StringUtils.isEmpty(cpfCnpj)) {
			cpfCnpj = clienteImovel.getCliente().getCnpj();
		}
		
		if (StringUtils.isNotEmpty(cpfCnpj)) {
			cadastroImovel.addMensagemErro(String.format(MSG_CPF_CNPJ_INVALIDO_BASE, cliente));
		}
	}
}
