package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorSexoCommand extends ValidadorCommand {

	private final String MSG_SEXO_USUARIO = "Sexo de usuário inválido";
	private final String MSG_SEXO_PROPRIETARIO = "Sexo de proprietário inválido";
	private final String MSG_SEXO_RESPONSAVEL = "Sexo de responsável inválido";
	
	public ValidadorSexoCommand(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
	}

	public void execute() {
		validarSexo(linha.get("sexoUsuario"), MSG_SEXO_USUARIO);
		
		if (StringUtils.isNotEmpty(linha.get("nomeProprietario"))){
			validarSexo(linha.get("sexoProprietario"), MSG_SEXO_PROPRIETARIO);
		}

		if (StringUtils.isNotEmpty(linha.get("nomeResponsavel"))){
			validarSexo(linha.get("sexoResponsavel"), MSG_SEXO_RESPONSAVEL);
		}
	}

	private void validarSexo(String sexo, String mensagem) {
		if (StringUtils.isEmpty(sexo) || !sexoMasculinoOuFeminino(sexo.charAt(0))){
			cadastroImovel.addMensagemErro(mensagem);
		}
	}
	
	private boolean sexoMasculinoOuFeminino(char sexo){
		return sexo == '1' || sexo == '2';
	}
}
