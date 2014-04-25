package gcom.cadastro.atualizacaocadastral.validador;

import java.util.Map;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ParserUtil;

public class ValidadorTamanhoLinhaAnormalidadeCommand extends ValidadorCommand {
	private ParserUtil parser;

	public ValidadorTamanhoLinhaAnormalidadeCommand(ParserUtil parser,
			AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha) {
		super(cadastroImovel, linha);
		this.parser = parser;
	}

	@Override
	public void execute() {
		if (parser.getFonte().length() != 358){
			cadastroImovel.addMensagemErroLayout("Linha Tipo 06 (Anormalidade) não compatível com o Layout");
		}
	}
}
