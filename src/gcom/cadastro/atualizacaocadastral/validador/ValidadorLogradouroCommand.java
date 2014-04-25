package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.imovel.IRepositorioImovel;

import java.util.Map;

public class ValidadorLogradouroCommand extends ValidadorCommand {

	private IRepositorioImovel repositorioImovel;
	
	public ValidadorLogradouroCommand(
			AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha,
			IRepositorioImovel repositorioImovel) {
		super(cadastroImovel, linha);

		this.repositorioImovel = repositorioImovel;
	}

	@Override
	public void execute() throws Exception {

		String codigoLogradouro = linha.get("codigoLogradouro");
		if (campoNumericoInvalido(codigoLogradouro)) {
			cadastroImovel.addMensagemErro("Código do logradouro é inválido");
		}

		Logradouro logradouro = repositorioImovel.pesquisarLogradouro(Integer.valueOf(codigoLogradouro));
		if (logradouro == null) {
			cadastroImovel.addMensagemErro("Logradouro inexistente");
		}

		Integer idLogradouro = repositorioImovel.pesquisarLogradouroImovelAtualizacaoCadastral(
				Integer.parseInt(linha.get("matricula")));
		if (idLogradouro != null && !idLogradouro.equals(Integer.valueOf(codigoLogradouro))) {
			cadastroImovel.addMensagemErro("Código do logradouro não pode ser alterado");
		}
	}
}
