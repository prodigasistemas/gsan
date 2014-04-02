package gcom.cadastro.atualizacaocadastral.validador;

import java.util.Map;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ParserUtil;

public class ValidadorTamanhoLinhaMedidorCommand extends ValidadorCommand {
	private ParserUtil parser;

	public ValidadorTamanhoLinhaMedidorCommand(ParserUtil parser,
			AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha) {
		super(cadastroImovel, linha);
		this.parser = parser;
	}

	@Override
	public void execute() {
		if (parser.getFonte().length() != 92){
			cadastroImovel.addMensagemErroLayout("Linha Tipo 05 (Medidor) não compatível com o Layout");
		}
	}
}
