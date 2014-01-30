package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ParserUtil;

import java.util.Map;

public class ValidadorTamanhoLinhaClienteCommand extends ValidadorCommand{
	private ParserUtil parser;

	public ValidadorTamanhoLinhaClienteCommand(ParserUtil parser) {
		super();
		this.parser = parser;
		
	}
	public ValidadorTamanhoLinhaClienteCommand(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if (parser.getFonte().length() <= 333){
			cadastroImovel.addMensagemErro("Linha de cliente....");
		}
	}
}
