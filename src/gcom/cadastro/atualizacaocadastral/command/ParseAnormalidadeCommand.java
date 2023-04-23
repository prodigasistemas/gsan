package gcom.cadastro.atualizacaocadastral.command;

import java.util.Collection;
import java.util.Map;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaAnormalidadeCommand;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ParseAnormalidadeCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseAnormalidadeCommand(ParserUtil parser, 
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
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaAnormalidade();
		AtualizacaoCadastralImovel imovel = atualizacao.getImovelAtual();

		int tamanhoLinha = parser.getFonte().length();
		
		new ValidadorTamanhoLinhaAnormalidadeCommand(parser, imovel, linha).execute();

		if (!imovel.isErroLayout()) {

			String matriculaImovelAnormalidade = parser.obterDadoParser(9).trim();
			linha.put("matriculaImovelAnormalidade", matriculaImovelAnormalidade);

			String codigoAnormalidade = parser.obterDadoParser(3).trim();
			linha.put("codigoAnormalidade", codigoAnormalidade);

			String comentario = parser.obterDadoParser(300).trim();
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
			
			if (tamanhoLinha == 369) {
				String loginUsuario = parser.obterDadoParser(11).trim();
				Usuario usuario = pesquisarUsuario(loginUsuario);
				atualizacao.getImovelAtual().setUsuario(usuario);
			}

			validarCodigoAnormalidade(atualizacao.getImovelAtual());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void validarCodigoAnormalidade(AtualizacaoCadastralImovel imovelAtual) throws ControladorException {
		Filtro filtro = new FiltroCadastroOcorrencia();
		filtro.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.ID, imovelAtual.getLinhaAnormalidade("codigoAnormalidade")));
		Collection pesquisa = controladorUtil.pesquisar(filtro, CadastroOcorrencia.class.getName());

		if (pesquisa != null && !pesquisa.isEmpty()) {
			imovelAtual.setCadastroOcorrencia((CadastroOcorrencia) Util.retonarObjetoDeColecao(pesquisa));
		} else {
			imovelAtual.addMensagemErro("Código de Anormalidade Inválido");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Usuario pesquisarUsuario(String login) {
		Filtro filtro = new FiltroUsuario();
		filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, login));
		Collection colecao = Fachada.getInstancia().pesquisar(filtro, Usuario.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			return (Usuario) Util.retonarObjetoDeColecao(colecao);
		} else {
			return null;
		}
	}
}
