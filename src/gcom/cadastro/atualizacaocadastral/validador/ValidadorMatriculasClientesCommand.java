package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.IRepositorioClienteImovel;

import java.util.Collection;
import java.util.Map;

public class ValidadorMatriculasClientesCommand extends ValidadorCommand {

	private IRepositorioClienteImovel repositorioClienteImovel;

	public ValidadorMatriculasClientesCommand(AtualizacaoCadastralImovel cadastroImovel
			, Map<String, String> linha
			, IRepositorioClienteImovel repositorioClienteImovel) {
		super(cadastroImovel, linha);
		this.repositorioClienteImovel = repositorioClienteImovel;
	}
	
	public void execute() throws Exception{
		Collection<ClienteImovel> clientes = repositorioClienteImovel.pesquisarClienteImovel(cadastroImovel.getMatricula());
		
		validaProprietario(linha, clientes, "Proprietario", ClienteRelacaoTipo.PROPRIETARIO);
		validaProprietario(linha, clientes, "Responsavel", ClienteRelacaoTipo.RESPONSAVEL);
	}

	private void validaProprietario(Map<String, String> linha, Collection<ClienteImovel> clientes, String cliente, Short relacao) {
		int matricula = Integer.valueOf(linha.get("matricula" + cliente));
		
		int base = -1;
		for (ClienteImovel clienteImovel : clientes) {
			if (clienteImovel.getClienteRelacaoTipo().getId().intValue() == (int) relacao){
				base = clienteImovel.getCliente().getId();
			}
		}
		
		if (base == -1 && matricula != 0){
			linha.put("matricula" + cliente, "0");
		}
		
		if (base != -1 && base != matricula){
			cadastroImovel.addMensagemErro(String.format("Inconsistência na matrícula de %s.", cliente));
		}
	}
}
