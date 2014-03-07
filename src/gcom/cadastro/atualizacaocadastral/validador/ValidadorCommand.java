package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public abstract class ValidadorCommand {
	protected Map<String, String> linha;
	protected AtualizacaoCadastralImovel cadastroImovel;
	
	public ValidadorCommand(){}
	
	public ValidadorCommand(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha){
		this.cadastroImovel = cadastroImovel;
		this.linha = linha;
	}
	
	public abstract void execute() throws Exception;
	
	protected boolean campoNumericoInvalido(String campo) {
		return StringUtils.isEmpty(campo) || !StringUtils.isNumeric(campo) || StringUtils.containsOnly(campo, new char[]{'0'}) ;
	}
}
