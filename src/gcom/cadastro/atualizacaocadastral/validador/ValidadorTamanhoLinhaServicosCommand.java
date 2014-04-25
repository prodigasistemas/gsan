package gcom.cadastro.atualizacaocadastral.validador;

import java.util.Map;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ParserUtil;

public class ValidadorTamanhoLinhaServicosCommand extends ValidadorCommand {
	private ParserUtil parser;

	public ValidadorTamanhoLinhaServicosCommand(ParserUtil parser,
			AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha) {
		super(cadastroImovel, linha);
		this.parser = parser;
	}

	@Override
	public void execute() {
		if (parser.getFonte().length() != 81){
			cadastroImovel.addMensagemErroLayout("Linha Tipo 04 (Serviços) não compatível com o Layout");
		}
	}
}
