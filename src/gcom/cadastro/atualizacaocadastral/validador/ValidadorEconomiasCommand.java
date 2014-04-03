package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.atualizacaocadastral.command.TipoEconomia;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorEconomiasCommand extends ValidadorCommand {

	public ValidadorEconomiasCommand(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
	}

	@Override
	public void execute() throws Exception {

		boolean existeEconomia = false;
		
		for (String key : linha.keySet()) {
			if (key.contains("subcategoria")) {
				String valor = linha.get(key).trim();
				
				if (StringUtils.isNotEmpty(valor)
						&& !StringUtils.containsOnly(valor.trim(), new char[] { '0' })) {
					
					existeEconomia = true;

					char codigo = key.replace("subcategoria", "").charAt(0);
					
					TipoEconomia tipo = TipoEconomia.getByCodigo(codigo);
					if (!cadastroImovel.getDadosImovel().contemTipoEconomia(tipo)) {
						cadastroImovel.getDadosImovel().addTipoEconomia(tipo);
					}
				}
			}
		}

		if (!existeEconomia) {
			cadastroImovel.addMensagemErro("Imóvel deve possuir ao menos uma economia");
		}
	}
}
