package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ParserUtil;

public class ValidadorTamanhoLinhaServicosCommand extends ValidadorCommand {
	private ParserUtil parser;

	public ValidadorTamanhoLinhaServicosCommand(ParserUtil parser, AtualizacaoCadastralImovel cadastroImovel) {
		super(cadastroImovel, null);
		this.parser = parser;
	}

	@Override
	public void execute() {
		if (parser.getFonte().length() != 81){
			cadastroImovel.addMensagemErroLayout("A linha Tipo 04 não está compatível ao definido no leiaute");
		}
	}
}
