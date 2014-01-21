package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelRamoAtividadeAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.interceptor.Interceptador;
import gcom.interceptor.ObjetoTransacao;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaLinhaColunaAlteracao;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.jboss.logging.Logger;

public abstract class AbstractAtualizacaoCadastralCommand {
	
	private static Logger logger = Logger.getLogger(AbstractAtualizacaoCadastralCommand.class);

	protected ParserUtil parser;
	protected IRepositorioCadastro repositorioCadastro;
	protected ControladorUtilLocal controladorUtil;
	protected ControladorTransacaoLocal controladorTransacao;
	protected IRepositorioImovel repositorioImovel;
	protected ControladorImovelLocal controladorImovel;
	protected ControladorClienteLocal controladorCliente;

	public AbstractAtualizacaoCadastralCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, 
			ControladorImovelLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		this.parser = parser;
		this.repositorioCadastro = repositorioCadastro;
		this.controladorUtil = controladorUtil;
		this.controladorTransacao = controladorTransacao;
		this.repositorioImovel = repositorioImovel;
		this.controladorImovel = controladorImovel;
		this.controladorCliente = controladorCliente;
	}

	public abstract void execute(AtualizacaoCadastral atualizacao) throws Exception;

	public void salvarTabelaColunaAtualizacaoCadastral(AtualizacaoCadastral atualizacaoCadastral, Object objetoAtualizacaoCadastralBase, Object objetoAtualizacaoCadastralTxt, int matriculaImovel) throws ControladorException {
		Collection<TabelaLinhaColunaAlteracao> colunasAlteradas = null;
		
		ArquivoTextoAtualizacaoCadastral arquivoTexto = atualizacaoCadastral.getArquivoTexto();
		Interceptador interceptador = Interceptador.getInstancia();

		try {
			Integer idImovel = null;

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL, matriculaImovel,
					matriculaImovel, new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_BATCH, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			colunasAlteradas = interceptador.compareObjetoTransacao((ObjetoTransacao) objetoAtualizacaoCadastralTxt,
					(ObjetoTransacao) objetoAtualizacaoCadastralBase, null);

			registradorOperacao.registrarOperacao((ObjetoTransacao) objetoAtualizacaoCadastralTxt);

			Collection<TabelaColunaAtualizacaoCadastral> colecaoTabelaColunaAtualizacaoCadastral = new ArrayList<TabelaColunaAtualizacaoCadastral>();

			if (colunasAlteradas != null && !colunasAlteradas.isEmpty()) {
				TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral = new TabelaAtualizacaoCadastral();
				AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
				alteracaoTipo.setId(AlteracaoTipo.ALTERACAO);
				tabelaAtualizacaoCadastral.setAlteracaoTipo(alteracaoTipo);
				Tabela tabela = new Tabela();

				if (objetoAtualizacaoCadastralBase instanceof ClienteAtualizacaoCadastral) {
					ClienteAtualizacaoCadastral base = (ClienteAtualizacaoCadastral) objetoAtualizacaoCadastralBase;
					ClienteAtualizacaoCadastral txt = (ClienteAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					tabelaAtualizacaoCadastral.setIdRegistroAlterado(base.getIdCliente());
					tabelaAtualizacaoCadastral.setCodigoCliente(base.getIdCliente());
					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabela.setId(Tabela.CLIENTE_ATUALIZACAO_CADASTRAL);
					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("2"));

				} else if (objetoAtualizacaoCadastralBase instanceof ImovelAtualizacaoCadastral) {
					ImovelAtualizacaoCadastral base = (ImovelAtualizacaoCadastral) objetoAtualizacaoCadastralBase;
					ImovelAtualizacaoCadastral txt = (ImovelAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					tabelaAtualizacaoCadastral.setIdRegistroAlterado(base.getIdImovel());
					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabela.setId(Tabela.IMOVEL_ATUALIZACAO_CADASTRAL);
					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("1"));

					idImovel = base.getIdImovel();
				} else if (objetoAtualizacaoCadastralBase instanceof ClienteFoneAtualizacaoCadastral) {
					ClienteFoneAtualizacaoCadastral base = (ClienteFoneAtualizacaoCadastral) objetoAtualizacaoCadastralBase;
					ClienteFoneAtualizacaoCadastral txt = (ClienteFoneAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("2"));
					tabela.setId(Tabela.CLIENTE_FONE_ATUALIZACAO_CADASTRAL);
					tabelaAtualizacaoCadastral.setCodigoCliente(txt.getIdCliente());
					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabelaAtualizacaoCadastral.setIdRegistroAlterado(txt.getIdCliente());
				} else if (objetoAtualizacaoCadastralBase instanceof ImovelSubcategoriaAtualizacaoCadastral) {
					ImovelSubcategoriaAtualizacaoCadastral base = (ImovelSubcategoriaAtualizacaoCadastral) objetoAtualizacaoCadastralBase;
					ImovelSubcategoriaAtualizacaoCadastral txt = (ImovelSubcategoriaAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					tabelaAtualizacaoCadastral.setIdRegistroAlterado(matriculaImovel);
					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabelaAtualizacaoCadastral.setComplemento(txt.getDescricaoCategoria() + " - " + txt.getDescricaoSubcategoria());
					tabela.setId(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL);
					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("2"));
				} else if (objetoAtualizacaoCadastralBase instanceof ImovelRamoAtividadeAtualizacaoCadastral) {
					ImovelRamoAtividadeAtualizacaoCadastral base = (ImovelRamoAtividadeAtualizacaoCadastral) objetoAtualizacaoCadastralBase;
					ImovelRamoAtividadeAtualizacaoCadastral txt = (ImovelRamoAtividadeAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					tabelaAtualizacaoCadastral.setIdRegistroAlterado(matriculaImovel);
					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabela.setId(Tabela.IMOVEL_RAMO_ATIVIDADE_ATUALIZACAO_CADASTRAL);
					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("2"));
				}

				tabelaAtualizacaoCadastral.setCodigoImovel(matriculaImovel);
				tabelaAtualizacaoCadastral.setLeiturista(arquivoTexto.getLeiturista());

				tabelaAtualizacaoCadastral.setArquivoTextoAtualizacaoCadastral(arquivoTexto);
				tabelaAtualizacaoCadastral.setTabela(tabela);
				tabelaAtualizacaoCadastral.setIndicadorAutorizado(ConstantesSistema.INDICADOR_REGISTRO_NAO_ACEITO);

				Iterator colunasAlteradasIter = colunasAlteradas.iterator();
				while (colunasAlteradasIter.hasNext()) {
					TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = (TabelaLinhaColunaAlteracao) colunasAlteradasIter.next();
					TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral = new TabelaColunaAtualizacaoCadastral();
					tabelaColunaAtualizacaoCadastral.setColunaValorAnterior(tabelaLinhaColunaAlteracao.getConteudoColunaAnterior());
					tabelaColunaAtualizacaoCadastral.setColunaValorAtual(tabelaLinhaColunaAlteracao.getConteudoColunaAtual());
					tabelaColunaAtualizacaoCadastral.setIndicadorAutorizado(ConstantesSistema.INDICADOR_REGISTRO_NAO_ACEITO);
					tabelaColunaAtualizacaoCadastral.setTabelaAtualizacaoCadastral(tabelaAtualizacaoCadastral);

					FiltroTabelaColuna filtroColuna = new FiltroTabelaColuna();
					filtroColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.COLUNA, tabelaLinhaColunaAlteracao.getTabelaColuna().getColuna()));
					filtroColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.TABELA, tabela));
					Collection<TabelaColuna> tabelas = Fachada.getInstancia().pesquisar(filtroColuna, TabelaColuna.class.getName());
					for (TabelaColuna tabelaColuna : tabelas) {
						tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
						logger.info("coluna: " + tabelaColuna.getColuna() + " - " + tabelaColuna.getId());
					}

					tabelaColunaAtualizacaoCadastral.setTabelaColuna(tabelaLinhaColunaAlteracao.getTabelaColuna());
					colecaoTabelaColunaAtualizacaoCadastral.add(tabelaColunaAtualizacaoCadastral);

				}

				controladorTransacao.inserirOperacaoEfetuadaAtualizacaoCadastral(
						((ObjetoTransacao) objetoAtualizacaoCadastralTxt).getUsuarioAcaoUsuarioHelp(),
						((ObjetoTransacao) objetoAtualizacaoCadastralTxt).getOperacaoEfetuada(), tabelaAtualizacaoCadastral,
						colecaoTabelaColunaAtualizacaoCadastral);

				if (idImovel != null) {
					atualizarSituacaoImovelAtualizacaoCadastral(idImovel, SituacaoAtualizacaoCadastral.TRANSMITIDO);
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao persistir alteracao na coluna.", e);
		}
	}

	private void atualizarSituacaoImovelAtualizacaoCadastral(Integer idImovel, Integer situacao) throws ControladorException {
		FiltroImovelAtualizacaoCadastral filtroImovel = new FiltroImovelAtualizacaoCadastral();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID, idImovel));

		ImovelAtualizacaoCadastral imovel = (ImovelAtualizacaoCadastral) Util.retonarObjetoDeColecao(controladorUtil.pesquisar(filtroImovel, ImovelAtualizacaoCadastral.class.getName()));

		imovel.setIdSituacaoAtualizacaoCadastral(situacao);

		controladorUtil.atualizar(imovel);
	}

}
