package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ParserUtil;

public class ValidadorTamanhoLinhaClienteCommand extends ValidadorCommand{
	private ParserUtil parser;

	public ValidadorTamanhoLinhaClienteCommand(ParserUtil parser, AtualizacaoCadastralImovel cadastroImovel) {
		super(cadastroImovel, null);
		this.parser = parser;
	}

	@Override
	public void execute() {
		if (parser.getFonte().length() != 742){
			cadastroImovel.addMensagemErroLayout("Linha Tipo 01 (Cliente) não compatível com o Layout.");
		}
	}
}
