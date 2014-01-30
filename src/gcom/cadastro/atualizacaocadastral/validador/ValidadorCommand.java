package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import java.util.Map;

public abstract class ValidadorCommand {
	protected Map<String, String> linha;
	protected AtualizacaoCadastralImovel cadastroImovel;
	
	public ValidadorCommand(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha){
		this.cadastroImovel = cadastroImovel;
		this.linha = linha;
	}
	
	public abstract void execute();
}
