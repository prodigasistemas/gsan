package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorCepClienteProprietarioResponsavel extends ValidadorCommand {
	
	private final String USUARIO_IGUAL_PROPRIETARIO = "1";
	private final String RESPONSAVEL_IGUAL_OUTRO = "2";
	
	private final String MSG_CEP_INVALIDO = "CEP de %s inválido";

	public ValidadorCepClienteProprietarioResponsavel(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
	}
	
	public void execute() throws Exception {
		validarCepCliente(cadastroImovel, linha, "Proprietario");
		validarCepCliente(cadastroImovel, linha, "Responsavel");
	}

	private void validarCepCliente(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha, String cliente) {
		String cep = linha.get("cep" + cliente);
		String nome = linha.get("nome" + cliente);
		String usuarioProprietario = linha.get("usuarioProprietario");
		String tipoResponsavel = linha.get("tipoResponsavel");
		
		if (!usuarioProprietario.equals(USUARIO_IGUAL_PROPRIETARIO) 
				&& !tipoResponsavel.equals(RESPONSAVEL_IGUAL_OUTRO)) {
			
			if (!StringUtils.isEmpty(nome)){
				if (campoNumericoInvalido(cep)) {
					cadastroImovel.addMensagemErro(String.format(MSG_CEP_INVALIDO, cliente));
				}
			}
		}
	}
}
