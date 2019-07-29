package gcom.cadastro.atualizacaocadastral.validador;

import java.util.Map;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ParserUtil;

public class ValidadorTamanhoLinhaAnormalidadeCommand extends ValidadorCommand {
	private ParserUtil parser;

	public ValidadorTamanhoLinhaAnormalidadeCommand(ParserUtil parser, AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
		this.parser = parser;
	}

	@Override
	public void execute() {
		int tamanho = parser.getFonte().length();
		if (tamanho != 358 && tamanho != 369) {
			cadastroImovel.addMensagemErroLayout("Linha Tipo 06 (Anormalidade) não compatível com o Layout");
		}
	}
}
