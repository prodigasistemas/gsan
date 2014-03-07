package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ParserUtil;

public class ValidadorTamanhoLinhaRamoAtividadeCommand extends ValidadorCommand {
	private ParserUtil parser;

	public ValidadorTamanhoLinhaRamoAtividadeCommand(ParserUtil parser, AtualizacaoCadastralImovel cadastroImovel) {
		super(cadastroImovel, null);
		this.parser = parser;
	}

	@Override
	public void execute() {
		if (parser.getFonte().length() != 12){
			cadastroImovel.addMensagemErroLayout("Linha Tipo 03 (Ramo de Atividade) não compatível com o Layout.");
		}
	}
}
