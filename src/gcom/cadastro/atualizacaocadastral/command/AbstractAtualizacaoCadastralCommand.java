package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelRamoAtividadeAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
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
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

public abstract class AbstractAtualizacaoCadastralCommand {

	private static Logger logger = Logger.getLogger(AbstractAtualizacaoCadastralCommand.class);

	protected ParserUtil parser;
	protected IRepositorioCadastro repositorioCadastro;
	protected ControladorUtilLocal controladorUtil;
	protected ControladorTransacaoLocal controladorTransacao;
	protected IRepositorioImovel repositorioImovel;
	protected ControladorEnderecoLocal controladorEndereco;
	protected ControladorAtualizacaoCadastralLocal controladorAtualizacaoCadastral;
	protected ControladorClienteLocal controladorCliente;

	public AbstractAtualizacaoCadastralCommand(){
	}

	public AbstractAtualizacaoCadastralCommand(ParserUtil parser){
		this.parser = parser;
	}

	public AbstractAtualizacaoCadastralCommand(
			ParserUtil parser,
			IRepositorioCadastro repositorioCadastro,
			ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao,
			IRepositorioImovel repositorioImovel,
			ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorAtualizacaoCadastral,
			ControladorClienteLocal controladorCliente) {

		this.parser = parser;
		this.repositorioCadastro = repositorioCadastro;
		this.controladorUtil = controladorUtil;
		this.controladorTransacao = controladorTransacao;
		this.repositorioImovel = repositorioImovel;
		this.controladorEndereco = controladorEndereco;
		this.controladorAtualizacaoCadastral = controladorAtualizacaoCadastral;
		this.controladorCliente = controladorCliente;
	}

	public abstract void execute(AtualizacaoCadastral atualizacao) throws Exception;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void salvarTabelaColunaAtualizacaoCadastral(AtualizacaoCadastral atualizacaoCadastral,
			Object objetoAtualizacaoCadastralBase, Object objetoAtualizacaoCadastralTxt,
			int matriculaImovel, int tipoOperacao) throws ControladorException {
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

			Collection<TabelaColunaAtualizacaoCadastral> colecaoInserirTabelaColunaAtualizacaoCadastral = new ArrayList<TabelaColunaAtualizacaoCadastral>();

			if (colunasAlteradas != null && !colunasAlteradas.isEmpty()) {
				TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral = pesquisarTabelaParaAtualizar(objetoAtualizacaoCadastralTxt, matriculaImovel);
				AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
				alteracaoTipo.setId(tipoOperacao);
				tabelaAtualizacaoCadastral.setAlteracaoTipo(alteracaoTipo);
				Tabela tabela = new Tabela();

				Long idPorTempo = Calendar.getInstance().getTimeInMillis();

				if (objetoAtualizacaoCadastralBase instanceof ClienteAtualizacaoCadastral) {
					ClienteAtualizacaoCadastral txt = (ClienteAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					if (txt.getIdCliente() != null){
						idPorTempo = (long) txt.getIdCliente();
					}

					tabelaAtualizacaoCadastral.setIdRegistroAlterado(idPorTempo);
					tabelaAtualizacaoCadastral.setCodigoCliente(idPorTempo);

					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabela.setId(Tabela.CLIENTE_ATUALIZACAO_CADASTRAL);
					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("2"));

				} else if (objetoAtualizacaoCadastralBase instanceof ImovelAtualizacaoCadastral) {
					ImovelAtualizacaoCadastral base = (ImovelAtualizacaoCadastral) objetoAtualizacaoCadastralBase;
					ImovelAtualizacaoCadastral txt = (ImovelAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					if (base.getIdImovel() != null){
						idPorTempo = (long) base.getIdImovel();
					}
					tabelaAtualizacaoCadastral.setIdRegistroAlterado(idPorTempo);
					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabela.setId(Tabela.IMOVEL_ATUALIZACAO_CADASTRAL);
					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("1"));

					idImovel = base.getIdImovel();
				} else if (objetoAtualizacaoCadastralBase instanceof ClienteFoneAtualizacaoCadastral) {
					ClienteFoneAtualizacaoCadastral txt = (ClienteFoneAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("2"));
					tabela.setId(Tabela.CLIENTE_FONE_ATUALIZACAO_CADASTRAL);

					if (txt.getIdCliente() != null){
						idPorTempo = (long) txt.getIdCliente();
					}
					tabelaAtualizacaoCadastral.setCodigoCliente(idPorTempo);
					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabelaAtualizacaoCadastral.setIdRegistroAlterado(idPorTempo);
					
				} else if (objetoAtualizacaoCadastralBase instanceof ImovelSubcategoriaAtualizacaoCadastral) {
					ImovelSubcategoriaAtualizacaoCadastral txt = (ImovelSubcategoriaAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					tabelaAtualizacaoCadastral.setIdRegistroAlterado((long) matriculaImovel);
					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabelaAtualizacaoCadastral.setComplemento(txt.getDescricaoCategoria() + " - " + txt.getDescricaoSubcategoria());
					tabela.setId(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL);
					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("2"));
					
				} else if (objetoAtualizacaoCadastralBase instanceof ImovelRamoAtividadeAtualizacaoCadastral) {
					ImovelRamoAtividadeAtualizacaoCadastral txt = (ImovelRamoAtividadeAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

					tabelaAtualizacaoCadastral.setIdRegistroAlterado((long) matriculaImovel);
					tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
					tabela.setId(Tabela.IMOVEL_RAMO_ATIVIDADE_ATUALIZACAO_CADASTRAL);
					tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("2"));
				}  else if (objetoAtualizacaoCadastralBase instanceof ImovelTipoOcupanteQuantidadeAtualizacaoCadastral) {
				    ImovelTipoOcupanteQuantidadeAtualizacaoCadastral txt = (ImovelTipoOcupanteQuantidadeAtualizacaoCadastral) objetoAtualizacaoCadastralTxt;

                    tabelaAtualizacaoCadastral.setIdRegistroAlterado((long) matriculaImovel);
                    tabelaAtualizacaoCadastral.setOperacaoEfetuada(txt.getOperacaoEfetuada());
                    tabelaAtualizacaoCadastral.setComplemento(txt.getTipoOcupante().getDescricao());
                    tabela.setId(Tabela.IMOVEL_QUANTIDADE_TIPO_OCUPANTE_ATUALIZACAO_CADASTRAL);
                    tabelaAtualizacaoCadastral.setIndicadorPrincipal(new Short("2"));
                }

				tabelaAtualizacaoCadastral.setLeiturista(arquivoTexto.getLeiturista());
				tabelaAtualizacaoCadastral.setArquivoTextoAtualizacaoCadastral(arquivoTexto);
				tabelaAtualizacaoCadastral.setTabela(tabela);
				tabelaAtualizacaoCadastral.setIndicadorAutorizado(ConstantesSistema.INDICADOR_REGISTRO_NAO_ACEITO);

				Iterator colunasAlteradasIterator = colunasAlteradas.iterator();
				while (colunasAlteradasIterator.hasNext()) {
					
					TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = (TabelaLinhaColunaAlteracao) colunasAlteradasIterator.next();
					
					TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral = this.pesquisarColunaParaAtualizar(tabela, tabelaLinhaColunaAlteracao.getTabelaColuna(), 
							matriculaImovel,  tabelaAtualizacaoCadastral.getComplemento());
					
					if (arquivoTexto.isArquivoRetornoTransmissao() || arquivoTexto.isArquivoRetornoRevisita()) {
						tabelaColunaAtualizacaoCadastral.setColunaValorAnterior(tabelaLinhaColunaAlteracao.getConteudoColunaAnterior());
						tabelaColunaAtualizacaoCadastral.setColunaValorTransmitido(tabelaLinhaColunaAlteracao.getConteudoColunaAtual());
					} else {
 						if (arquivoTexto.isArquivoRetornoRevisao())
 							tabelaColunaAtualizacaoCadastral.setColunaValorAnterior(tabelaLinhaColunaAlteracao.getConteudoColunaAnterior());
							tabelaColunaAtualizacaoCadastral.setColunaValorRevisado(tabelaLinhaColunaAlteracao.getConteudoColunaAtual());
						
						if (arquivoTexto.isArquivoRetornoFiscalizacao())
							tabelaColunaAtualizacaoCadastral.setColunaValorAnterior(tabelaLinhaColunaAlteracao.getConteudoColunaAnterior());
							tabelaColunaAtualizacaoCadastral.setColunaValorFiscalizado(tabelaLinhaColunaAlteracao.getConteudoColunaAtual());
					}
						
					tabelaColunaAtualizacaoCadastral.setIndicadorAutorizado(ConstantesSistema.INDICADOR_REGISTRO_NAO_ACEITO);
					
					if (tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral() != null)
						tabelaAtualizacaoCadastral.setId(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId());
					
					tabelaColunaAtualizacaoCadastral.setTabelaAtualizacaoCadastral(tabelaAtualizacaoCadastral);
					
					FiltroTabelaColuna filtroColuna = new FiltroTabelaColuna();
					filtroColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.COLUNA, tabelaLinhaColunaAlteracao.getTabelaColuna().getColuna()));
					filtroColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.TABELA, tabela));
					Collection<TabelaColuna> tabelas = Fachada.getInstancia().pesquisar(filtroColuna, TabelaColuna.class.getName());

					if (tabelas.isEmpty()){
						throw new Exception("Nao ha registro em tabela_coluna para " + tabelaLinhaColunaAlteracao.getTabelaColuna().getColuna());
					}
					for (TabelaColuna tabelaColuna : tabelas) {
						tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
					}
					
					tabelaColunaAtualizacaoCadastral.setTabelaColuna(tabelaLinhaColunaAlteracao.getTabelaColuna());
					
					colecaoInserirTabelaColunaAtualizacaoCadastral.add(tabelaColunaAtualizacaoCadastral);
				}

				controladorTransacao.inserirOperacaoEfetuadaAtualizacaoCadastral(
						((ObjetoTransacao) objetoAtualizacaoCadastralTxt).getUsuarioAcaoUsuarioHelp(),
						((ObjetoTransacao) objetoAtualizacaoCadastralTxt).getOperacaoEfetuada(), tabelaAtualizacaoCadastral,
						colecaoInserirTabelaColunaAtualizacaoCadastral);

				if (idImovel != null && arquivoTexto.isArquivoRetornoTransmissao()) {
					atualizarSituacaoImovelAtualizacaoCadastral(idImovel, SituacaoAtualizacaoCadastral.TRANSMITIDO);
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao persistir alteracao na coluna.", e);
		}
	}

	private String getComplemento(Object objeto) {
		String complemento = null;
		if (objeto instanceof ImovelSubcategoriaAtualizacaoCadastral) {
			ImovelSubcategoriaAtualizacaoCadastral subcategoria = (ImovelSubcategoriaAtualizacaoCadastral) objeto;
			complemento = subcategoria.getDescricaoCategoria() + " - " + subcategoria.getDescricaoSubcategoria();
		}
			
		return complemento;
	}

	private TabelaAtualizacaoCadastral pesquisarTabelaParaAtualizar(Object objeto, Integer matriculaImovel) throws ControladorException {
		Tabela tabela = obterTabelaAtualizacaoCadastral(objeto);

		TabelaAtualizacaoCadastral tabelaAtualizacao = controladorAtualizacaoCadastral.pesquisarTabelaPorImovel(tabela, matriculaImovel, getComplemento(objeto));

		if (tabelaAtualizacao == null) {
			tabelaAtualizacao = new TabelaAtualizacaoCadastral();
			tabelaAtualizacao.setCodigoImovel(matriculaImovel);
			tabelaAtualizacao.setRegistroInclusao(true);
		} else {
			tabelaAtualizacao.setRegistroInclusao(false);
		}
		return tabelaAtualizacao;
	}

	private Tabela obterTabelaAtualizacaoCadastral(Object objetoAtualizacaoCadastralBase) {
			Tabela tabela = new Tabela();

			if (objetoAtualizacaoCadastralBase instanceof ClienteAtualizacaoCadastral) {
					tabela.setId(Tabela.CLIENTE_ATUALIZACAO_CADASTRAL);
			} else if (objetoAtualizacaoCadastralBase instanceof ImovelAtualizacaoCadastral) {
					tabela.setId(Tabela.IMOVEL_ATUALIZACAO_CADASTRAL);
			} else if (objetoAtualizacaoCadastralBase instanceof ClienteFoneAtualizacaoCadastral) {
					tabela.setId(Tabela.CLIENTE_FONE_ATUALIZACAO_CADASTRAL);
			} else if (objetoAtualizacaoCadastralBase instanceof ImovelSubcategoriaAtualizacaoCadastral) {
					tabela.setId(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL);
			} else if (objetoAtualizacaoCadastralBase instanceof ImovelRamoAtividadeAtualizacaoCadastral) {
					tabela.setId(Tabela.IMOVEL_RAMO_ATIVIDADE_ATUALIZACAO_CADASTRAL);
			}  else if (objetoAtualizacaoCadastralBase instanceof ImovelTipoOcupanteQuantidadeAtualizacaoCadastral) {
				tabela.setId(Tabela.IMOVEL_QUANTIDADE_TIPO_OCUPANTE_ATUALIZACAO_CADASTRAL);
			}
			return tabela;
		}

	private TabelaColunaAtualizacaoCadastral pesquisarColunaParaAtualizar(Tabela tabela, TabelaColuna coluna, Integer idImovel, String complemento) throws ControladorException {
		coluna.setTabela(tabela);
		TabelaColunaAtualizacaoCadastral tabelaColuna = controladorAtualizacaoCadastral.pesquisarTabelaColunaPorImovel(coluna, idImovel, complemento);

		if (tabelaColuna == null) {
			tabelaColuna = new TabelaColunaAtualizacaoCadastral();
			tabelaColuna.setRegistroInclusao(true);
		} else {
			tabelaColuna.setRegistroInclusao(false);
		}
		return tabelaColuna;
	}

	private void atualizarSituacaoImovelAtualizacaoCadastral(Integer idImovel, Integer situacao) throws ControladorException {
		FiltroImovelAtualizacaoCadastral filtroImovel = new FiltroImovelAtualizacaoCadastral();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID, idImovel));

		@SuppressWarnings("unchecked")
		ImovelAtualizacaoCadastral imovel = (ImovelAtualizacaoCadastral) Util.retonarObjetoDeColecao(controladorUtil.pesquisar(filtroImovel, ImovelAtualizacaoCadastral.class.getName()));

		if (imovel != null){
			imovel.setIdSituacaoAtualizacaoCadastral(situacao);
			controladorUtil.atualizar(imovel);
		}
	}

	protected Integer getTipoOperacaoCliente(Integer matricula, Integer matriculaImovel, String cpfCliente, Short clienteRelacaoTipo, IRepositorioClienteImovel repositorioClienteImovel) throws Exception {
		if (matricula == null || matricula == 0){
			return AlteracaoTipo.INCLUSAO;
		}

		Cliente cliente = repositorioClienteImovel.pesquisarClienteImovelTipo(matricula, matriculaImovel, clienteRelacaoTipo.intValue());
		if (cliente != null){
			if (StringUtils.equals(cliente.getCnpj(), cpfCliente) || StringUtils.equals(cliente.getCpf(), cpfCliente)){
				return AlteracaoTipo.ALTERACAO;
			}else{
				Collection<Cliente> clientes = controladorCliente.pesquisarClientePorCpfCnpj(cpfCliente);
				if (clientes.isEmpty()){
					return AlteracaoTipo.ALTERACAO;
				}else{
					return AlteracaoTipo.INCLUSAO;
				}
			}
		}else{
			return AlteracaoTipo.INCLUSAO;
		}
	}
	
}
