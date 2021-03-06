package gcom.cadastro.atualizacaocadastral.command;

import org.apache.commons.lang.StringUtils;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ErroRepositorioException;
import gcom.util.ParserUtil;
import gcom.util.exception.ArquivoAtualizacaoInexistenteException;

public class ParseHeaderCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseHeaderCommand(
			ParserUtil parser, 
			IRepositorioCadastro repositorioCadastro, 
			ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, 
			IRepositorioImovel repositorioImovel, 
			ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorImovel, 
			ControladorClienteLocal controladorCliente) {
		
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorEndereco, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		if (parser.getFonte().length() == 32) {
			parser.obterDadoParser(3);
		}

		String localidade = parser.obterDadoParser(3);
		String setor = parser.obterDadoParser(3);
		String rota = parser.obterDadoParser(2);
		
		if (parser.getFonte().length() == 32) {
			parser.obterDadoParser(6);
		}
		
		String idRota = parser.obterDadoParser(4);
		parser.obterDadoParser(10);
		String tipoRetorno = parser.obterDadoParser(1);
		
		String idArquivoTexto = parser.obterDadoParser(11);

		atualizacao.setArquivoTexto(pesquisarArquivoTexto(localidade, setor, rota, tipoRetorno, idArquivoTexto));
		atualizacao.setIdRota(Integer.valueOf(idRota));
	}

	private ArquivoTextoAtualizacaoCadastral pesquisarArquivoTexto(String localidade, String setor, String rota, String tipoRetorno,
			String idArquivoTexto) throws ErroRepositorioException {
		
		ArquivoTextoAtualizacaoCadastral arquivoTexto = null;
		if (StringUtils.isNotEmpty(idArquivoTexto) && StringUtils.isNumeric(idArquivoTexto) && Integer.parseInt(idArquivoTexto) > 0) {
			arquivoTexto = repositorioCadastro.pesquisarArquivoTextoAtualizacaoCadastro(Integer.parseInt(idArquivoTexto));
		} else {
			arquivoTexto = repositorioCadastro.pesquisarArquivoTextoAtualizacaoCadastro(localidade + "_" + setor + "_" + rota);
		}

		if (arquivoTexto == null) {
			throw new ArquivoAtualizacaoInexistenteException();
		}

		arquivoTexto.setTipoRetorno(tipoRetorno);
		
		return arquivoTexto;
	}
}
