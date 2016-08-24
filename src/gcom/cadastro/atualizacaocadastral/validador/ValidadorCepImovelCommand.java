package gcom.cadastro.atualizacaocadastral.validador;

import java.util.Map;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

public class ValidadorCepImovelCommand extends ValidadorCommand {
	
	public ValidadorCepImovelCommand(
			AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha) {
		super(cadastroImovel, linha);
	}

	@Override
	public void execute() throws Exception {
		String cep = linha.get("cep");
		
		if (campoNumericoInvalido(cep)) {
			cadastroImovel.addMensagemErro("CEP do imóvel inválido");
		}
	}
}
