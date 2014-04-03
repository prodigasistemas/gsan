package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorCoordenadasCommand extends ValidadorCommand {

	public ValidadorCoordenadasCommand(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
	}

	@Override
	public void execute() throws Exception {

		if (StringUtils.isEmpty(linha.get("latitude"))) {
			cadastroImovel.addMensagemErro("Latitude inválida");
		} else {
			if (StringUtils.containsOnly(linha.get("latitude").trim(), new char[] { '0' })) {
				cadastroImovel.addMensagemErro("Latitude inválida");
			}
		}

		if (StringUtils.isEmpty(linha.get("longitude"))) {
			cadastroImovel.addMensagemErro("Longitude inválida");
		} else {
			if (StringUtils.containsOnly(linha.get("longitude").trim(), new char[] { '0' })) {
				cadastroImovel.addMensagemErro("Longitude inválida");
			}
		}
	}
}
