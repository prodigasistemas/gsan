package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorTipoClientesCommand extends ValidadorCommand {

	private final String MSG_TIPO_PROPRIETARIO_INVALIDO = "Usuário Proprietário Inválido";
	private final String MSG_TIPO_RESPONSAVEL_INVALIDO = "Tipo Responsável Inválido";
	private final String MSG_TIPO_RESPONSAVEL_INCONSISTENTE = "Tipo Responsável Inconsistente";


	public ValidadorTipoClientesCommand(AtualizacaoCadastralImovel cadastroImovel
			, Map<String, String> linha) {
		super(cadastroImovel, linha);
	}
	
	public void execute() throws Exception {
		String usuarioProprietario = linha.get("usuarioProprietario");
		String tipoResponsavel  = linha.get("tipoResponsavel");
		
		if (!tipoResponsavelValido(tipoResponsavel)){
			cadastroImovel.addMensagemErro(MSG_TIPO_RESPONSAVEL_INVALIDO);
		}

		if (!tipoProprietarioValido(usuarioProprietario)){
			cadastroImovel.addMensagemErro(MSG_TIPO_PROPRIETARIO_INVALIDO);
		}		
	}

	private boolean tipoResponsavelValido(String tipo) {
		return !StringUtils.isEmpty(tipo)  && (tipo.charAt(0) == '0' || tipo.charAt(0) == '1' || tipo.charAt(0) == '2');
	}

	private boolean tipoProprietarioValido(String tipo) {
		return !StringUtils.isEmpty(tipo) && (tipo.charAt(0) == '1' || tipo.charAt(0) == '2');
	}

	private boolean responsavelIgualProprietario(String tipoCliente) {
		return tipoCliente.equals("1");
	}

	private boolean proprietarioIgualUsuario(String tipoCliente) {
		return tipoCliente.equals("1");
	}	
}
