package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.Util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorNomesClientesCommand extends ValidadorCommand {

	public ValidadorNomesClientesCommand(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
	}

	public void execute() throws Exception {
		String nomeUsuario = linha.get("nomeUsuario");
		if (StringUtils.isNotEmpty(nomeUsuario)) {
			if (Util.nomeInvalido(linha.get("nomeUsuario"))) {
				cadastroImovel.addMensagemErro("Nome do cliente usuário inválido.");
			}
		}
		String nomeProprietario = linha.get("nomeProprietario");
		if (StringUtils.isNotEmpty(nomeProprietario)) {
			if (Util.nomeInvalido(nomeProprietario)) {
				cadastroImovel.addMensagemErro("Nome do cliente proprietário inválido.");
			}
		}

		String nomeResponsavel = linha.get("nomeResponsavel");
		if (StringUtils.isNotEmpty(nomeResponsavel)) {
			if (Util.nomeInvalido(nomeResponsavel)) {
				cadastroImovel.addMensagemErro("Nome do cliente responsável inválido.");
			}
		}
	}
}