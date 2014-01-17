package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

public class ParseHeaderCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseHeaderCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, 
			ControladorImovelLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		String grupo      = parser.obterDadoParser(3);
		String localidade = parser.obterDadoParser(3);
		String setor      = parser.obterDadoParser(3);
		String rota       = parser.obterDadoParser(2);
		String anoMesReferencia = parser.obterDadoParser(6);
		String idRota     = parser.obterDadoParser(4);
		String versaoCelular = parser.obterDadoParser(10);
		
		ArquivoTextoAtualizacaoCadastral arquivoTexto = repositorioCadastro.pesquisarArquivoTextoAtualizacaoCadastro(localidade + setor + rota, anoMesReferencia);
		
		atualizacao.setArquivoTexto(arquivoTexto);
	}
}
