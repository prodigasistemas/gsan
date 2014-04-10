package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaAnormalidadeCommand;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

import java.util.Map;

public class ParseAnormalidadeCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseAnormalidadeCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorEndereco, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaAnormalidade();
		AtualizacaoCadastralImovel imovel = atualizacao.getImovelAtual();

		new ValidadorTamanhoLinhaAnormalidadeCommand(parser, imovel).execute();

		if(!imovel.isErroLayout()) {

			String matriculaImovelAnormalidade = parser.obterDadoParser(9).trim();
			linha.put("matriculaImovelAnormalidade", matriculaImovelAnormalidade);

			String codigoAnormalidade = parser.obterDadoParser(3).trim();
			linha.put("codigoAnormalidade", codigoAnormalidade);

			String comentario = parser.obterDadoParser(200).trim();
			linha.put("comentario", comentario);

			String pathFoto1 = parser.obterDadoParser(30).trim();
			linha.put("pathFoto1", pathFoto1);

			String pathFoto2 = parser.obterDadoParser(30).trim();
			linha.put("pathFoto2", pathFoto2);

			String latitude = parser.obterDadoParser(20).trim();
			linha.put("latitude", latitude);

			String longitude = parser.obterDadoParser(20).trim();
			linha.put("longitude", longitude);

			String dataServico = parser.obterDadoParser(26).trim();
			linha.put("dataServico", dataServico);

			String tipoEntrevistado = parser.obterDadoParser(20).trim();
			linha.put("tipoEntrevistado", tipoEntrevistado);
		}

	}
}
