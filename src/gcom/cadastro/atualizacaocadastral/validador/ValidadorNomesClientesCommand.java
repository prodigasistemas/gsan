package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.Util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorNomesClientesCommand extends ValidadorCommand {

	public ValidadorNomesClientesCommand(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
	}
	
	public void execute() throws Exception{
		if (Util.nomeInvalido(linha.get("nomeUsuario"))){
			cadastroImovel.addMensagemErro("Nome de usuário inválido.");
		}
		
		if (StringUtils.isNotEmpty(linha.get("nomeProprietario"))){
			if (Util.nomeInvalido(linha.get("nomeProprietario"))){
				cadastroImovel.addMensagemErro("Nome de proprietário inválido.");
			}
		}
		
		if (StringUtils.isNotEmpty(linha.get("nomeResponsavel"))){
			if (Util.nomeInvalido(linha.get("nomeResponsavel"))){
				cadastroImovel.addMensagemErro("Nome de responsável inválido.");
			}
		}
	}
}
