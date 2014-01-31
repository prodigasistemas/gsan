package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

import java.util.Map;

public class ParseServicosCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseServicosCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorEndereco, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaServicos();

		String matriculaImovelServicos = parser.obterDadoParser(9).trim();
		linha.put("matriculaImovelServicos", matriculaImovelServicos);
		
		String ligacaoAguaSituacao = parser.obterDadoParser(2).trim();
		linha.put("ligacaoAguaSituacao", ligacaoAguaSituacao);
		
		String ligacaoEsgotoSituacao = parser.obterDadoParser(2).trim();
		linha.put("ligacaoEsgotoSituacao", ligacaoEsgotoSituacao);
		
		String localInstalacaoRamal = parser.obterDadoParser(2).trim();
		linha.put("localInstalacaoRamal", localInstalacaoRamal);
		
		String latitude = parser.obterDadoParser(20).trim();
		linha.put("latitude", latitude);
		
		String longitude = parser.obterDadoParser(20).trim();
		linha.put("longitude", longitude);

		String dataServico = parser.obterDadoParser(26).trim();
		linha.put("dataServico", dataServico);
	}

}
