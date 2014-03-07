package gcom.cadastro.atualizacaocadastral.validador;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

public class ValidadorCepClienteProprietarioResponsavel extends ValidadorCommand {
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
		if(!StringUtils.isEmpty(nome)) {
			if(campoNumericoInvalido(cep)) {
				cadastroImovel.addMensagemErro(String.format(MSG_CEP_INVALIDO, cliente));
			}
		}
	}
}
