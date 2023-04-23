package gcom.cadastro.atualizacaocadastral.validador;

import java.util.Map;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ParserUtil;

public class ValidadorTamanhoLinhaClienteCommand extends ValidadorCommand {
	private ParserUtil parser;

	public ValidadorTamanhoLinhaClienteCommand(ParserUtil parser, AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
		this.parser = parser;
	}

	@Override
	public void execute() {
		if (parser.getFonte().length() != 793) {
			cadastroImovel.addMensagemErroLayout("Linha Tipo 01 (Cliente) não compatível com o Layout");
		}
	}
}
