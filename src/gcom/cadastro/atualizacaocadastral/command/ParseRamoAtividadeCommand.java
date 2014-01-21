package gcom.cadastro.atualizacaocadastral.command;

import java.util.Map;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.ImovelRamoAtividadeAtualizacaoCadastral;
import gcom.interceptor.Interceptador;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

public class ParseRamoAtividadeCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseRamoAtividadeCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, 
			ControladorImovelLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		
		Map<String, String> linha = atualizacao.getLinhaRamoAtividade();

		String matriculaImovelRamoAtividade = parser.obterDadoParser(9).trim();
		linha.put("matriculaImovelRamoAtividade", matriculaImovelRamoAtividade);

		String ramoAtividade = parser.obterDadoParser(3).trim();
		linha.put("ramoAtividade", ramoAtividade);

		int matriculaImovel = Integer.parseInt(matriculaImovelRamoAtividade);
		int idRamoAtividade = Integer.parseInt(ramoAtividade);
		
		DadoAtualizacaoRamoAtividade ramo = new DadoAtualizacaoRamoAtividade();
		ramo.setId(idRamoAtividade);
		atualizacao.addDadoRamoAtividade(ramo);

		boolean existeRamoAtividade = repositorioCadastro.existeImovelRamoAtividadeAtualizacaoCadastral(matriculaImovel, idRamoAtividade);

		if (!existeRamoAtividade) {
			ImovelRamoAtividadeAtualizacaoCadastral ramoAtividadeTxt = new ImovelRamoAtividadeAtualizacaoCadastral();
			ramoAtividadeTxt.setIdImovel(matriculaImovel);
			ramoAtividadeTxt.setIdRamoAtividade(idRamoAtividade);

			Interceptador interceptador = Interceptador.getInstancia();
			
			ArquivoTextoAtualizacaoCadastral arquivoTexto = atualizacao.getArquivoTexto();

			salvarTabelaColunaAtualizacaoCadastral(atualizacao, new ImovelRamoAtividadeAtualizacaoCadastral(), ramoAtividadeTxt, matriculaImovel);
		}
	}
}
