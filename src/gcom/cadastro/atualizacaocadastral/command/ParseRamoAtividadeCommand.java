package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

import java.util.Map;

public class ParseRamoAtividadeCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseRamoAtividadeCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, 
			ControladorImovelLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaRamoAtividade();
		
		String matriculaImovelRamoAtividade = parser.obterDadoParser(9).trim();
		linha.put("matriculaImovelRamoAtividade", matriculaImovelRamoAtividade);
		
		String ramoAtividade = parser.obterDadoParser(3).trim();
		linha.put("ramoAtividade", ramoAtividade);
		
		int idRamoAtividade = Integer.parseInt(ramoAtividade);
		
		if (idRamoAtividade > 0) {
			AtualizacaoCadastralImovel imovelAtual = atualizacao.getImovelAtual();
			if (imovelAtual.getDadosImovel().contemApenasResidencial()){
				imovelAtual.addMensagemErro("Categoria residencial não admite ramo de atividade");
				return;
			}
			
			boolean existeRamoAtividade = repositorioCadastro.existeRamoAtividade(idRamoAtividade);
			
			if (!existeRamoAtividade){
				imovelAtual.addMensagemErro("Código inválido do ramo de atividade");
				return;
			}
			
			DadoAtualizacaoRamoAtividade ramo = new DadoAtualizacaoRamoAtividade();
			ramo.setId(idRamoAtividade);
			atualizacao.getImovelAtual().addDadoRamoAtividade(ramo);
		}
	}
}
