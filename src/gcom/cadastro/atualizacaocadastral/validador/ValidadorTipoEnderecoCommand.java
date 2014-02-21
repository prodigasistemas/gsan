package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorTipoEnderecoCommand extends ValidadorCommand{

	private final String MSG_TIPO_ENDERECO_RESPONSAVEL = "Tipo de Endereço do responsável inválido.";
	private final String MSG_TIPO_ENDERECO_PROPRIETARIO = "Tipo de Endereço do proprietário inválido.";
	
	public ValidadorTipoEnderecoCommand(AtualizacaoCadastralImovel imovelAtual, Map<String, String> linha) {
		super(imovelAtual, linha);
	}

	@Override
	public void execute() {
		if (StringUtils.isNotEmpty(linha.get("nomeProprietario"))){
			validarTipoEndereco(linha.get("tipoEnderecoProprietario"), MSG_TIPO_ENDERECO_PROPRIETARIO);
		}

		if (StringUtils.isNotEmpty(linha.get("nomeResponsavel"))){
			validarTipoEndereco(linha.get("tipoEnderecoResponsavel"), MSG_TIPO_ENDERECO_RESPONSAVEL);
		}
	}

	private void validarTipoEndereco(String tipoEndereco, String mensagem) {
		if (StringUtils.isEmpty(tipoEndereco) || !tipoEnderecoResidencialOuComercial(tipoEndereco.charAt(0))){
			cadastroImovel.addMensagemErro(mensagem);
		}
	}
	
	private boolean tipoEnderecoResidencialOuComercial(char tipo){
		return tipo == '1' || tipo == '2';
	}

}
