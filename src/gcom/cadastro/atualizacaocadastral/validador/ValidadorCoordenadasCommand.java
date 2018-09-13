package gcom.cadastro.atualizacaocadastral.validador;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.atualizacaocadastral.Visita;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ControladorException;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ValidadorCoordenadasCommand extends ValidadorCommand {

	private ControladorAtualizacaoCadastralLocal controlador;

	public ValidadorCoordenadasCommand(AtualizacaoCadastralImovel cadastroImovel, ControladorAtualizacaoCadastralLocal controlador) {
		super(cadastroImovel);

		this.controlador = controlador;
		this.linha = cadastroImovel.getLinhaAnormalidade();
	}

	@Override
	public void execute() throws Exception {
		String latitude = linha.get("latitude");
		String longitude = linha.get("longitude");

		if (StringUtils.isEmpty(latitude) || StringUtils.containsOnly(latitude.trim(), new char[] { '0' })) {
			cadastroImovel.addMensagemErro("Latitude inválida.");
		}

		if (StringUtils.isEmpty(longitude) || StringUtils.containsOnly(longitude.trim(), new char[] { '0' })) {
			cadastroImovel.addMensagemErro("Longitude inválida.");
		}

		if (coordenadasRepetidas())
			cadastroImovel.addMensagemErro("Já existe uma visita no imóvel para as coordenadas informadas no arquivo.");
	}

	private boolean coordenadasRepetidas() throws ControladorException {
		ImovelControleAtualizacaoCadastral controle = controlador.obterImovelControle(cadastroImovel.getMatricula());

		List<Visita> visitas = controlador.obterVisitasPorImovelControleECoordenadas(controle, 
				cadastroImovel.getLinhaAnormalidade("latitude"), 
				cadastroImovel.getLinhaAnormalidade("longitude"));

		return visitas != null && !visitas.isEmpty();
	}
}
