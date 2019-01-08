package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;
import gcom.util.exception.ArquivoAtualizacaoInexistenteException;

public class ParseHeaderCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseHeaderCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorEndereco, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		String grupo = parser.obterDadoParser(3);
		String localidade = parser.obterDadoParser(3);
		String setor = parser.obterDadoParser(3);
		String rota = parser.obterDadoParser(2);
		String anoMesReferencia = parser.obterDadoParser(6);
		String idRota = parser.obterDadoParser(4);
		String versaoCelular = parser.obterDadoParser(10);
		
		String tipoRetorno = parser.obterDadoCabecalhoParser(1);
		
		ArquivoTextoAtualizacaoCadastral arquivoTexto = repositorioCadastro.pesquisarArquivoTextoAtualizacaoCadastro(
				localidade + "_" + setor + "_" + rota);
		
		if (arquivoTexto == null){
			throw new ArquivoAtualizacaoInexistenteException();
		}
		
		arquivoTexto.setTipoRetorno(tipoRetorno);
		
		atualizacao.setArquivoTexto(arquivoTexto);
		atualizacao.setIdRota(Integer.valueOf(idRota));
	}
	
}
