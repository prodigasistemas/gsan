package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.imovel.IRepositorioImovel;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorTipoLogradouroCommand extends ValidadorCommand {
	
	private IRepositorioImovel repositorioImovel;

	public ValidadorTipoLogradouroCommand(
			AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha,
			IRepositorioImovel repositorioImovel) {
		super(cadastroImovel, linha);
		
		this.repositorioImovel = repositorioImovel;
	}

	@Override
	public void execute() throws Exception {
		String tipoLogradouro = linha.get("idTipoLogradouroImovel");

		if (StringUtils.isEmpty(tipoLogradouro)) {
			cadastroImovel.addMensagemErro("Tipo do logradouro do imóvel inválido");
		} else {
			LogradouroTipo tipo = repositorioImovel.pesquisarTipoLogradouro(Integer.valueOf(tipoLogradouro));
			if (tipo == null) {
				cadastroImovel.addMensagemErro("Tipo do logradouro do imóvel inexistente");
			}
		}
	}
}
