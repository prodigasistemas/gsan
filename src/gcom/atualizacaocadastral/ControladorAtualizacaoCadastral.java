package gcom.atualizacaocadastral;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.FiltroTramite;
import gcom.atendimentopublico.registroatendimento.RABuilder;
import gcom.atendimentopublico.registroatendimento.RADadosGeraisHelper;
import gcom.atendimentopublico.registroatendimento.RALocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.RASolicitanteHelper;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelImagem;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;
import gcom.gui.cadastro.atualizacaocadastral.ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioFichaFiscalizacaoCadastralHelper;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioRelacaoImoveisRotaBean;
import gcom.seguranca.IRepositorioSeguranca;
import gcom.seguranca.RepositorioSegurancaHBM;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.MergeProperties;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ControladorAtualizacaoCadastral implements IControladorAtualizacaoCadastral, SessionBean {


	private static final long serialVersionUID = -3792912776769033056L;
	
	private static Logger logger = Logger.getLogger(ControladorAtualizacaoCadastral.class);
	
	private IRepositorioAtualizacaoCadastral repositorioAtualizacaoCadastral = null;
	private IRepositorioSeguranca repositorioSeguranca;
	private static List<Integer> listaRAParaExclusao = new ArrayList<Integer>();

	
	SessionContext sessionContext;
	
	public void ejbCreate() throws CreateException {
		repositorioAtualizacaoCadastral = RepositorioAtualizacaoCadastralHBM.getInstancia();
		repositorioSeguranca = RepositorioSegurancaHBM.getInstancia();
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	
	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	private ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {
		ControladorRegistroAtendimentoLocalHome localHome = null;
		ControladorRegistroAtendimentoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRegistroAtendimentoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	protected ControladorUtilLocal getControladorUtil() {
		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			local = (ControladorImovelLocal) localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	protected ControladorClienteLocal getControladorCliente() {
		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorClienteLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	protected ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	public void atualizarImoveisAprovados(Integer idFuncionalidade, Usuario usuarioLogado) throws ControladorException{
		int idUnidadeIniciada = 0;
		
		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidade, UnidadeProcessamento.FUNCIONALIDADE, 0);
			
			processarClientes();
			processarImoveis();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			
			if (!listaRAParaExclusao.isEmpty()) {
				deletarRAsPendente(listaRAParaExclusao);
				listaRAParaExclusao.clear();
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new ControladorException("Erro ao atualizar imóveis aprovados.", e);
		}
	}

	public void apagarInformacoesRetornoImovelAtualizacaoCadastral(Integer idImovel) throws Exception {
		atualizarImovelControle(idImovel);
		apagarTabelaAtualizacaoCadastralPorIdImovel(idImovel);
		apagarImovelRetorno(idImovel);
		apagarImagemRetorno(idImovel);
		apagarImovelSubcategoriaRetorno(idImovel);
		apagarImovelRamoAtividade(idImovel);
		apagarInformacoesRetornoCliente(idImovel);
	}

	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria,Integer idCategoria)
		throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.pesquisarImovelSubcategoriaAtualizacaoCadastral(idImovel, idSubcategoria, idCategoria);
        } catch (ErroRepositorioException ex) {
        	logger.error("Erro ao pesquisar imovel subcategoria para atualizacao cadastral do imovel " + idImovel, ex);
			throw new ControladorException("Erro ao pesquisar imovel subcategoria para atualizacao cadastral do imovel " + idImovel, ex);
        }
    }

	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel) throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.pesquisarImovelAtualizacaoCadastral(idImovel);
        } catch (ErroRepositorioException ex) {
            logger.error("Erro ao pesquisar imovel para atualizacao cadastral " + idImovel, ex);
			throw new ControladorException("Erro ao pesquisar imovel para atualizacao cadastral " + idImovel, ex);
        }
    }
	

	public Integer recuperaValorSequenceImovelRetorno() throws Exception {
		return repositorioAtualizacaoCadastral.recuperaValorSequenceImovelRetorno();
	}
	
	public void aprovarImoveisEmLote(Usuario usuarioLogado, Collection<ConsultarMovimentoAtualizacaoCadastralHelper> listaImoveis) throws ControladorException {
		try {
			this.aprovarImoveis(converterListaEmImovelRetorno(listaImoveis));
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar atualizar imóveis em lote.", e);
			throw new ControladorException("Erro ao atualizar imóveis em lote.", e);
		}
		
	}
	
	public ImovelControleAtualizacaoCadastral obterImovelControlePorImovelRetorno(Integer idImovelRetorno) throws ControladorException {
		return  repositorioAtualizacaoCadastral.obterImovelControlePorImovelRetorno(idImovelRetorno);
	}
	
	public ImovelControleAtualizacaoCadastral obterImovelControle(Integer idImovelControle) throws ControladorException {
		return  repositorioAtualizacaoCadastral.obterImovelControle(idImovelControle);
	}
	
	/************************************************************
	 *PRIVATE METHODS 
	 ************************************************************/
	
	private void processarClientes() throws ControladorException {
		atualizarClientes();
		incluirClientes();
		excluirClientes();
	}

	private void processarImoveis() throws ControladorException {
		atualizarImoveis();
		incluirImoveis();
		excluirImoveis();
	}
	
	private void atualizarImovelAtualizacaoCadastral(IImovel imovelRetorno) throws ControladorException {
		Imovel imovel = getControladorImovel().pesquisarImovel(imovelRetorno.getIdImovel());
		MergeProperties.mergeProperties(imovel, imovelRetorno);
		imovel.setId(imovelRetorno.getIdImovel());
		imovel.setUltimaAlteracao(new Date());
		getControladorUtil().atualizar(imovel);
		
		inserirImovelImagens(imovel.getId());
	}
	
	private void inserirImovelImagens(Integer idImovel) throws ControladorException {
		Collection<ImagemRetorno> colecaoImagemRetorno = this.pesquisarImagensRetornoPorIdImovel(idImovel);
		
		int ordemImagem = 0;
		
		for (ImagemRetorno imagemRetorno : colecaoImagemRetorno) {
			File imagem = null;
			try {
				imagem = this.copiarImagensRetorno(imagemRetorno);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ImovelImagem imovelImagem = new ImovelImagem();
			imovelImagem.setIdImovel(idImovel);
			imovelImagem.setNomeImagem(imagem.getName());
			String caminhoImagem = imagem.getAbsolutePath();
			String caminhoFinal = caminhoImagem.substring(caminhoImagem.indexOf("/imovel_imagem"));
			imovelImagem.setCaminhoImagem(caminhoFinal);
			imovelImagem.setUltimaAlteracao(new Date());
			
			Integer idImovelImagem = (Integer) getControladorUtil().inserir(imovelImagem);
			
			imovelImagem.setId(idImovelImagem);
			
			ordemImagem++;
			renomearImovelImagem(imagem, imovelImagem, ordemImagem);
		}
	}

	private void renomearImovelImagem(File imagem, ImovelImagem imovelImagem, int ordemImagem) throws ControladorException {
		String nomeImagem = imagem.getName();
		String extensao = nomeImagem.substring(nomeImagem.indexOf("."), nomeImagem.length());
		String novoNome = imovelImagem.getIdImovel() + "-" + ordemImagem + extensao;
		File imagemRenomeada = new File(imagem.getParentFile().getAbsolutePath(), novoNome);
		imagem.renameTo(imagemRenomeada);
		
		imovelImagem.setNomeImagem(imagemRenomeada.getName());
		String caminhoImagem = imagemRenomeada.getAbsolutePath();
		String caminhoFinal = caminhoImagem.substring(caminhoImagem.indexOf("/imovel_imagem"));
		imovelImagem.setCaminhoImagem(caminhoFinal);
		getControladorUtil().atualizar(imovelImagem);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ImagemRetorno> pesquisarImagensRetornoPorIdImovel(Integer idImovel) throws ControladorException {
		FiltroImagemRetorno filtro = new FiltroImagemRetorno(FiltroImagemRetorno.IMOVEL_ID);
		filtro.adicionarParametro(new ParametroSimples(FiltroImagemRetorno.IMOVEL_ID, idImovel));
		
		Collection<ImagemRetorno> colecaoImagemRetorno = getControladorUtil().pesquisar(filtro, ImagemRetorno.class.getName());
		
		return colecaoImagemRetorno;
	}

	private File copiarImagensRetorno(ImagemRetorno imagemRetorno) throws IOException {
		String caminhoJboss = System.getProperty("jboss.server.home.dir");
		File arquivoOrigem = new File(caminhoJboss + imagemRetorno.getPathImagem());
		File arquivoDestino = this.criarArquivoDestinoImovelImagem(imagemRetorno);

		FileChannel origemChannel = null;
		FileChannel destinoChannel = null;

		try {
			origemChannel = new FileInputStream(arquivoOrigem).getChannel();
			destinoChannel = new FileOutputStream(arquivoDestino).getChannel();
			
			origemChannel.transferTo(0, origemChannel.size(), destinoChannel);
		} finally {
			if (origemChannel != null && origemChannel.isOpen())
				origemChannel.close();
			if (destinoChannel != null && destinoChannel.isOpen())
				destinoChannel.close();
		}
		
		return arquivoDestino;
	}

	private File criarArquivoDestinoImovelImagem(ImagemRetorno imagemRetorno) {
		String pastaDestino = retornarPastaDestinoImovelImagem(imagemRetorno);
		
		File arquivoDestino = new File(pastaDestino, imagemRetorno.getNomeImagem());

		File caminhoDestino = arquivoDestino.getParentFile().getAbsoluteFile();
		if (!caminhoDestino.exists())
			caminhoDestino.mkdir();
		
		return arquivoDestino;
	}

	private String retornarPastaDestinoImovelImagem(ImagemRetorno imagemRetorno) {
		Imovel imovel = null;
		
		try {
			FiltroImovel filtro = new FiltroImovel();
			filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imagemRetorno.getIdImovel()));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
			imovel = (Imovel) Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro, Imovel.class.getName()));
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		
		String pasta = Util.completaStringComZeroAEsquerda(imovel.getLocalidade().getId()+"", 3) + "_"
				+ Util.completaStringComZeroAEsquerda(imovel.getSetorComercial().getCodigo()+"", 3) + "_"
				+ Util.completaStringComZeroAEsquerda(imovel.getQuadra().getNumeroQuadra()+"", 4);
		
		String caminhoJboss = System.getProperty("jboss.server.home.dir");
		
		return caminhoJboss + "/imovel_imagem/" + pasta;
	}
	
	private void atualizarImovelSubcategoriaAtualizacaoCadastral(IImovel imovelRetorno) throws Exception {
		imovelRetorno.setId(imovelRetorno.getIdImovel());

		Collection<IImovelSubcategoria> subcategoriasRetorno = this.obterImovelSubcategoriaParaAtualizar(imovelRetorno.getIdImovel());
		Collection<Integer> idsSubcategorias = this.obterIdsSubcategoriasImovel(imovelRetorno.getIdImovel());
		
		for (IImovelSubcategoria subcategoriaRetorno : subcategoriasRetorno) {
			ImovelSubcategoria imovelSubcategoria = this.obterSubcategoriaDoImovel(imovelRetorno.getIdImovel(), subcategoriaRetorno.getSubcategoria().getId());
			
			if (imovelSubcategoria != null) {
				MergeProperties.mergeProperties(imovelSubcategoria, subcategoriaRetorno);
				imovelSubcategoria.setUltimaAlteracao(new Date());
				getControladorUtil().atualizar(imovelSubcategoria);
				idsSubcategorias.remove(imovelSubcategoria.getComp_id().getSubcategoria().getId());
			} else {
				imovelSubcategoria = new ImovelSubcategoria(new ImovelSubcategoriaPK(subcategoriaRetorno.getImovel(), subcategoriaRetorno.getSubcategoria()));
				MergeProperties.mergeProperties(imovelSubcategoria, subcategoriaRetorno);
				imovelSubcategoria.setUltimaAlteracao(new Date());
				getControladorUtil().inserir(imovelSubcategoria);
			}
		}
		
		this.removerSubcategoriasDoImovel(imovelRetorno.getIdImovel(), idsSubcategorias);
	}
	
	private void atualizarImovelRamoAtividadeAtualizacaoCadastral(IImovel imovelRetorno) throws Exception {
		try {
			Collection<IImovelRamoAtividade> ramosAtividadeRetorno = this.obterImovelRamoAtividadeParaAtualizar(imovelRetorno.getIdImovel());
			Collection<Integer> idsRamosAtividadesImovel = this.obterIdsRamosAtividadesImovel(imovelRetorno.getIdImovel());
			
			for (IImovelRamoAtividade ramoAtividadeRetorno : ramosAtividadeRetorno) {
				ImovelRamoAtividade imovelRamoAtividade = this.obterRamoAtividadeDoImovel(imovelRetorno.getIdImovel(), ramoAtividadeRetorno.getRamoAtividade().getId());
				
				if (imovelRamoAtividade != null) {
					MergeProperties.mergeProperties(imovelRamoAtividade, ramoAtividadeRetorno);
					imovelRamoAtividade.setUltimaAlteracao(new Date());
					getControladorUtil().atualizar(imovelRamoAtividade);
					idsRamosAtividadesImovel.remove(imovelRamoAtividade.getComp_id().getRamo_atividade().getId());
				} else {
					ImovelRamoAtividade imovelRamoAtividadeNovo = new ImovelRamoAtividade(ramoAtividadeRetorno.getImovel().getId(), ramoAtividadeRetorno.getRamoAtividade().getId());
					imovelRamoAtividadeNovo.setUltimaAlteracao(new Date());
					
					getControladorUtil().inserir(imovelRamoAtividadeNovo);
				}
			}
			this.removerRamosAtividadeDoImovel(imovelRetorno.getIdImovel(), idsRamosAtividadesImovel);
		} catch (ControladorException e) {
			logger.error("Erro ao atualizar ramo de atividade retorno do imóvel " + imovelRetorno.getId(), e);
			throw new ControladorException("Erro ao atualizar ramo de atividade retorno do imóvel " + imovelRetorno.getId(), e);
		}
	}
	
	private void atualizarClienteFoneAtualizacaoCadastral(Integer idImovel) throws Exception {
		ImovelRetorno imovelRetorno = (ImovelRetorno) repositorioAtualizacaoCadastral.pesquisarImovelRetorno(idImovel);


		Collection<IClienteFone> clienteFonesRetorno = this.obterClientesFoneParaAtualizar(imovelRetorno.getIdImovel());
		
		for (IClienteFone clienteFoneRetorno : clienteFonesRetorno) {
			getControladorCliente().removerTodosTelefonesPorCliente(clienteFoneRetorno.getCliente().getId());
			
			ClienteFone clienteFone = new ClienteFone();
			MergeProperties.mergeProperties(clienteFone, clienteFoneRetorno);
			clienteFone.setUltimaAlteracao(new Date());
			clienteFone.setId(null);
			getControladorUtil().inserir(clienteFone);
		}
	}
	
	private void removerSubcategoriasDoImovel(Integer idImovel, Collection<Integer> idsSubcategorias) throws ControladorException {
		for (Integer idSubcategoria : idsSubcategorias) {
			ImovelSubcategoria imovelSubcategoria = this.obterSubcategoriaDoImovel(idImovel, idSubcategoria);
			getControladorUtil().remover(imovelSubcategoria);
		}
	}
	
	private void removerRamosAtividadeDoImovel(Integer idImovel, Collection<Integer> idsRamosAtividades) throws ControladorException {
		for (Integer id : idsRamosAtividades) {
			ImovelRamoAtividade imovelRamoAtividade = this.obterRamoAtiviadeDoImovel(new Imovel(idImovel), id);
			getControladorUtil().remover(imovelRamoAtividade);
		}
	}
	
	private ImovelRamoAtividade obterRamoAtiviadeDoImovel(IImovel imovel, Integer id) throws ControladorException {
		Collection<ImovelRamoAtividade> ramosAtividadeImovel = getControladorImovel().pesquisarRamoAtividadeDoImovel(imovel.getId());
		
		for (ImovelRamoAtividade ramoAtividade : ramosAtividadeImovel) {
			if (ramoAtividade.getComp_id().getRamo_atividade().getId().equals(id)) {
				return ramoAtividade;
			}
		}
		return null;
	}

	private ImovelSubcategoria obterSubcategoriaDoImovel(Integer idImovel, Integer idSubcategoria) throws ControladorException {
		Collection<ImovelSubcategoria> subcategoriasImovel = getControladorImovel().pesquisarImovelSubcategorias(new Imovel(idImovel));
		
		for (ImovelSubcategoria subcategoria : subcategoriasImovel) {
			if (subcategoria.getComp_id().getSubcategoria().getId().equals(idSubcategoria)) {
				return subcategoria;
			}
		}
		
		return null;
	}
	
	private ImovelRamoAtividade obterRamoAtividadeDoImovel(Integer idImovel, Integer id) throws ControladorException {
		Collection<ImovelRamoAtividade> ramosAtividadeImovel = getControladorImovel().pesquisarRamoAtividadeDoImovel(idImovel);
		
		for (ImovelRamoAtividade imovelRamoAtividade : ramosAtividadeImovel) {
			if (imovelRamoAtividade.getComp_id().getRamo_atividade().getId().equals(id)) {
				return imovelRamoAtividade;
			}
		}
		
		return null;
	}
	
	private Collection<Integer> obterIdsSubcategoriasImovel(Integer idImovel) throws ControladorException {
		Collection<Integer> ids = new ArrayList<Integer>();
		Collection<ImovelSubcategoria> subcategoriasImovel = getControladorImovel().pesquisarImovelSubcategorias(new Imovel(idImovel));
		
		for (ImovelSubcategoria imovelSubcategoria : subcategoriasImovel) {
			ids.add(imovelSubcategoria.getComp_id().getSubcategoria().getId());
		}
		
		return ids;

	}
	
	private Collection<Integer> obterIdsRamosAtividadesImovel(Integer idImovel) throws ControladorException{
		Collection<Integer> ids = new ArrayList<Integer>();
		Collection<ImovelRamoAtividade> ramosAtividade = getControladorImovel().pesquisarRamoAtividadeDoImovel(idImovel);
		
		for (ImovelRamoAtividade imovelRamoAtividade : ramosAtividade) {
			ids.add(imovelRamoAtividade.getComp_id().getRamo_atividade().getId());
		}
		return ids;
	}
	
	private Collection<IImovel> obterImoveisParaAtualizar(Integer tipoOperacao) throws ControladorException {
		Collection<IImovel> imoveis = null;
		try {
			imoveis = repositorioAtualizacaoCadastral.obterImoveisParaAtualizar(tipoOperacao);
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar imoveis para atualizar.", e);
			throw new ControladorException("Erro ao pesquisar imoveis para atualizar.", e);

		}
		return imoveis;
	}
	
	private Collection<IImovelSubcategoria> obterImovelSubcategoriaParaAtualizar(Integer idImovel) throws Exception {
		Collection<IImovelSubcategoria> subcategorias = repositorioAtualizacaoCadastral.obterImovelSubcategoriaParaAtualizar(idImovel);
		return subcategorias;
	}
	
	private Collection<IImovelRamoAtividade> obterImovelRamoAtividadeParaAtualizar(Integer idImovel) throws Exception {
		Collection<IImovelRamoAtividade> ramosAtividade = repositorioAtualizacaoCadastral.obterImovelRamoAtividadeParaAtualizar(idImovel);
		return ramosAtividade;
	}

	private Collection<ClienteImovelRetorno> obterClientesParaAtualizar() throws ControladorException {
		Collection<ClienteImovelRetorno> clienteImoveisRetorno = null;
		try {
			clienteImoveisRetorno = repositorioAtualizacaoCadastral.obterClientesParaAtualizar();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar clientes para atualizar.", e);
			throw new ControladorException("Erro ao pesquisar clientes para atualizar.", e);

		}
		return clienteImoveisRetorno;
	}

	private Collection<ClienteImovelRetorno> obterClientesParaIncluir() throws ControladorException {
		Collection<ClienteImovelRetorno> clienteImoveisRetorno = null;
		try {
			clienteImoveisRetorno = repositorioAtualizacaoCadastral.obterClientesParaIncluir();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar clientes para incluir.", e);
			throw new ControladorException("Erro ao pesquisar clientes para incluir.", e);

		}
		return clienteImoveisRetorno;
	}
	
	private Collection<IClienteImovel> obterClientesParaExcluirRelacao() throws ControladorException {
		Collection<IClienteImovel> clienteImoveis = null;
		try {
			clienteImoveis = repositorioAtualizacaoCadastral.obterClientesParaExcluirRelacao();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar clientes para excluir.", e);
			throw new ControladorException("Erro ao pesquisar clientes para excluir.", e);

		}
		return clienteImoveis;
	}

	private boolean isImovelEmCampo(Integer idImovel) {
		try {
			getControladorMicromedicao().validarImovelEmCampo(idImovel); 
			return false;
		} catch (ControladorException e) {
			return true;
		}
	}
	
	private Collection<IClienteFone> obterClientesFoneParaAtualizar(Integer idImovel) throws Exception {
		Collection<IClienteFone> clientesFone =  repositorioAtualizacaoCadastral.obterClienterFoneParaAtualizar(idImovel);
		return clientesFone;
	}
	
	private void atualizarImovelControle(Integer idImovel) throws Exception{
		ImovelControleAtualizacaoCadastral controle = repositorioAtualizacaoCadastral.pesquisarImovelControleAtualizacao(idImovel);
		if (controle != null){
			controle.setImovelRetorno(null);
			controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.EM_CAMPO));
			this.getControladorUtil().atualizar(controle);
		}
	}

	private void apagarInformacoesRetornoCliente(Integer idImovel) throws Exception  {
		Collection<Integer> idsClientesRetorno = repositorioAtualizacaoCadastral.pesquisarIdsClienteRetorno(idImovel);
		if(!idsClientesRetorno.isEmpty()) {
			apagarClienteEnderecoRetornoPorIdsClientes(idsClientesRetorno);
			apagarClienteFoneRetornoPorIdsClientes(idsClientesRetorno);
			apagarClienteRetorno(idsClientesRetorno);
		}
		apagarImovelClienteRetorno(idImovel);
	}

	private void apagarImovelClienteRetorno(Integer idImovel) throws Exception {
		repositorioAtualizacaoCadastral.apagarClienteImovelRetornoPorIdImovel(idImovel);
	}

	private void apagarImovelRetorno(Integer idImovel) throws Exception {
		repositorioAtualizacaoCadastral.apagarImovelRetornoPorIdImovel(idImovel);
	}

	private void apagarTabelaAtualizacaoCadastralPorIdImovel(Integer idImovel) throws Exception {
		List<TabelaAtualizacaoCadastral> colecaoTabelaAtualizacaoCadastral;
		List<TabelaColunaAtualizacaoCadastral> colecaoTabelaColunaAtualizacaoCadastral;
		colecaoTabelaAtualizacaoCadastral = repositorioSeguranca.pesquisaTabelaAtualizacaoCadastralPorImovel(idImovel);

		if(colecaoTabelaAtualizacaoCadastral != null) {
			for(TabelaAtualizacaoCadastral tabelaAtlzCad : colecaoTabelaAtualizacaoCadastral){
				colecaoTabelaColunaAtualizacaoCadastral = repositorioSeguranca.pesquisaTabelaColunaAtualizacaoCadastral(tabelaAtlzCad.getId());
				for(TabelaColunaAtualizacaoCadastral tabelaColunaAtlzCad : colecaoTabelaColunaAtualizacaoCadastral) {
					this.getControladorUtil().remover(tabelaColunaAtlzCad);
				}
				this.getControladorUtil().remover(tabelaAtlzCad);
			}
		}
	}
	
	private void apagarImovelSubcategoriaRetorno(Integer idImovel) throws Exception {
		List<ImovelSubcategoriaRetorno> colecaoImovelSubCategoriaRetorno;
		colecaoImovelSubCategoriaRetorno = repositorioAtualizacaoCadastral.pesquisarImovelSubcategoriaRetornoPorIdImovel(idImovel);
		if(colecaoImovelSubCategoriaRetorno != null) {
			for(ImovelSubcategoriaRetorno imovelSubCategoriaRetorno : colecaoImovelSubCategoriaRetorno) {
				this.getControladorUtil().remover(imovelSubCategoriaRetorno);
			}
		}
	}
	
	private void apagarImovelRamoAtividade(Integer idImovel) throws Exception {
		repositorioAtualizacaoCadastral.apagarImovelRetornoRamoAtividadeRetornoPorIdImovel(idImovel);
	}

	private void apagarClienteEnderecoRetornoPorIdsClientes(Collection<Integer> idsClientesRetorno) throws Exception{
		repositorioAtualizacaoCadastral.apagarClienteEnderecoRetorno(idsClientesRetorno);
	}
	
	private void apagarClienteFoneRetornoPorIdsClientes(Collection<Integer> idsClientesRetorno) throws Exception {
		repositorioAtualizacaoCadastral.apagarClienteFoneRetorno(idsClientesRetorno);
	}
	
	private void apagarClienteRetorno(Collection<Integer> idsClientesRetorno) throws Exception{
		repositorioAtualizacaoCadastral.apagarClienteRetorno(idsClientesRetorno);
	}

	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarSubCategoriasAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException{
		return repositorioAtualizacaoCadastral.pesquisarSubCategoriasAtualizacaoCadastral(idImovel);
	}

	
	private void apagarImagemRetorno(Integer idImovel) throws Exception {
		repositorioAtualizacaoCadastral.apagarImagemRetornoPorIdImovel(idImovel);
	}
	
	private void atualizarImoveis() throws ControladorException {
		int idImovelRetorno = -1;

		try {

			Collection<IImovel> imoveisAlteracao = this.obterImoveisParaAtualizar(AlteracaoTipo.ALTERACAO);
			for (IImovel imovelRetorno : imoveisAlteracao) {
				if (!isImovelEmCampo(imovelRetorno.getIdImovel())) {
					idImovelRetorno = imovelRetorno.getId();
					
					atualizarImovelAtualizacaoCadastral(imovelRetorno);
					atualizarImovelSubcategoriaAtualizacaoCadastral(imovelRetorno);
					atualizarImovelRamoAtividadeAtualizacaoCadastral(imovelRetorno);
					atualizarImovelProcessado(idImovelRetorno);
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao atualizar imóvel retorno " + idImovelRetorno, e);
			throw new ControladorException("Erro ao atualizar imóvel retorno  " + idImovelRetorno, e);
		}
	}
	
	private void incluirImoveis() throws ControladorException {
		Integer idImovel = null;

		try {
			Collection<IImovel> imoveisInclusao = this.obterImoveisParaAtualizar(AlteracaoTipo.INCLUSAO);
			
			for (IImovel imovelRetorno : imoveisInclusao) {
				
				imovelRetorno.setIdImovel(null);
				Integer idSetorComercial = getControladorCadastro().pesquisarIdSetorComercialPorCodigoELocalidade(imovelRetorno.getIdLocalidade(), imovelRetorno.getCodigoSetorComercial());
				
				String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();
				
				HashMap<ClienteRelacaoTipo, ICliente> mapClientesImovel = this.obterClientesImovel(imovelRetorno.getId());
				
				RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastralInclusaoImovel(imovelRetorno, mapClientesImovel, AlteracaoTipo.INCLUSAO, protocoloAtendimento);
				RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovelRetorno, idSetorComercial, AlteracaoTipo.INCLUSAO);
				RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral(); 
				
				Integer[] retorno = getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);
				
				listaRAParaExclusao.add(retorno[0]);
				
				atualizarImovelProcessado(imovelRetorno.getId());
			}

		} catch (Exception e) {
			logger.error("Erro ao inserir imóvel retorno " + idImovel);
			throw new ControladorException("Erro ao inserir imóvel retorno  " + idImovel, e);

		}
	}
	
	
	
	private HashMap<ClienteRelacaoTipo, ICliente> obterClientesImovel(Integer idImovelRetorno) throws ControladorException {
		HashMap<ClienteRelacaoTipo, ICliente> mapClientes = new HashMap<ClienteRelacaoTipo, ICliente>();
		
		try {
			Collection<ClienteImovelRetorno> clientesImovel = repositorioAtualizacaoCadastral.obterClienteImoveisDoImovel(idImovelRetorno);
			
			for (ClienteImovelRetorno clienteImovelRetorno :clientesImovel) {
				ICliente clienteRetorno = repositorioAtualizacaoCadastral.pesquisarClienteRetorno(clienteImovelRetorno);
				
				mapClientes.put(clienteImovelRetorno.getClienteRelacaoTipo(), clienteRetorno);
			}
		
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao obter clientes do imóvel imóvel retorno" + idImovelRetorno);
			throw new ControladorException("Erro ao obter clientes do imóvel retorno  "+ idImovelRetorno, e);
		}
		return mapClientes;
	}
	
	private void excluirImoveis() throws ControladorException {
		Integer idImovel = null;

		try {
			Collection<IImovel> imoveisExclusao = this.obterImoveisParaAtualizar(AlteracaoTipo.EXCLUSAO);
			
			for (IImovel imovelRetorno : imoveisExclusao) {
				
				if (!isImovelEmCampo(imovelRetorno.getIdImovel())) {
					Integer idSetorComercial = getControladorCadastro().pesquisarIdSetorComercialPorCodigoELocalidade(imovelRetorno.getIdLocalidade(), imovelRetorno.getCodigoSetorComercial());
					
					String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();
					
					RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastral(imovelRetorno, AlteracaoTipo.EXCLUSAO, protocoloAtendimento);
					RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovelRetorno, idSetorComercial, AlteracaoTipo.EXCLUSAO);
					RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral(); 
					
					Integer[] retorno = getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);
					
					listaRAParaExclusao.add(retorno[0]);
					
					atualizarImovelProcessado(imovelRetorno.getId());
				}
			}

		} catch (Exception e) {
			logger.error("Erro ao excluir imóvel retorno" + idImovel);
			throw new ControladorException("Erro ao excluir imóvel retorno  "+ idImovel, e);
		}
	}
	
	@SuppressWarnings("unused")
	private void atualizarImoveisProcessados(Collection<IImovel> listaImoveis) throws ControladorException {
		
		Collection<ImovelControleAtualizacaoCadastral> listaImoveisControle = repositorioAtualizacaoCadastral.obterImoveisControle(listaImoveis);
		
		for (ImovelControleAtualizacaoCadastral imovelControle :  listaImoveisControle) {
			imovelControle.setDataProcessamento(new Date());
			getControladorUtil().atualizar(imovelControle);
		}
	}
	
	private void atualizarImovelProcessado(Integer idImovelRetorno) throws ControladorException {
		
		ImovelControleAtualizacaoCadastral imovelControle = repositorioAtualizacaoCadastral.obterImovelControlePorImovelRetorno(idImovelRetorno);
		
		imovelControle.setDataProcessamento(new Date());
		imovelControle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.ATUALIZADO));
		
		getControladorUtil().atualizar(imovelControle);
	}

	public Collection<Integer> pesquisarImoveisPorSituacaoPeriodo(Date dataInicial, Date dataFinal, Integer idSituacaoCadastral)
			throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.pesquisarImoveisPorSituacaoPeriodo(idSituacaoCadastral, dataInicial, dataFinal);
		} catch (ErroRepositorioException e) {
			 throw new ControladorException("erro.sistema", e);
		}
	}
	
	private void atualizarClientes() throws ControladorException {
		int idImovel = -1;

		try {
			Collection<ClienteImovelRetorno> clientesAlteracao = this.obterClientesParaAtualizar();
			
			for (ClienteImovelRetorno clienteImovelRetorno : clientesAlteracao) {
				
				idImovel = clienteImovelRetorno.getImovel().getId();
				
				if (!isImovelEmCampo(clienteImovelRetorno.getImovel().getId())) {
					
					if (existeRelacaoClienteImovel(clienteImovelRetorno)) {
						atualizarInformacoesCliente(clienteImovelRetorno);
						atualizarClienteFoneAtualizacaoCadastral(clienteImovelRetorno.getImovel().getId());
						
					} else {
						incluirNovaRelacaoCliente(clienteImovelRetorno);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao atualizar clientes do imóvel " + idImovel, e);
			throw new ControladorException("Erro ao atualizar clientes do imóvel.", e);
		}
	}

	private void atualizarInformacoesCliente(ClienteImovelRetorno clienteImovelRetorno)	throws ControladorException {
		try {
			ICliente clienteRetorno = repositorioAtualizacaoCadastral.pesquisarClienteRetorno(clienteImovelRetorno);
			ICliente cliente = getControladorCliente().pesquisarCliente(clienteImovelRetorno.getCliente().getId());
			
			if (cliente != null) {
				MergeProperties.mergeInterfaceProperties(cliente, clienteRetorno);
				cliente.setUltimaAlteracao(new Date());
				getControladorUtil().atualizar(cliente);
			}
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao atualizar cliente imovel retorno: " + clienteImovelRetorno.getId(), e);
			throw new ControladorException("Erro ao atualizar cliente imovel retorno.", e);
		}
	}
	
	private void incluirNovaRelacaoCliente(ClienteImovelRetorno clienteImovelRetorno) throws ControladorException {
		Integer idImovel = null;
		
		try {
			ICliente clienteRetorno = repositorioAtualizacaoCadastral.pesquisarClienteRetorno(clienteImovelRetorno);
			IImovel imovelRetorno = repositorioAtualizacaoCadastral.pesquisarImovelRetorno(clienteImovelRetorno.getImovel().getId());
			
			Integer idSetorComercial = getControladorCadastro().pesquisarIdSetorComercialPorCodigoELocalidade(imovelRetorno.getIdLocalidade(), imovelRetorno.getCodigoSetorComercial());
			
			String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();
			
			RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastral(imovelRetorno, clienteRetorno, clienteImovelRetorno, AlteracaoTipo.INCLUSAO, protocoloAtendimento);
			RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovelRetorno, idSetorComercial, AlteracaoTipo.INCLUSAO);
			RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral(); 
			
			getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);
		} catch (Exception e) {
			logger.error("Erro ao incluir nova relacao de cliente para o imovel " + idImovel);
			throw new ControladorException("Erro ao incluir nova relacao de cliente para o imovel.", e);
		}
	}
	
	private boolean existeRelacaoClienteImovel(ClienteImovelRetorno clienteImovelRetorno) throws ControladorException {
		boolean existeRelacao = false;
		try {
			existeRelacao = repositorioAtualizacaoCadastral.existeRelacaoClienteImovel(
								clienteImovelRetorno.getImovel().getId(), 
								clienteImovelRetorno.getCliente().getId(), 
								clienteImovelRetorno.getClienteRelacaoTipo().getId());
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao verificar existencia de relacao cliente imovel.", e);
		}
		
		return existeRelacao;
		
	}
	
	private void incluirClientes() throws ControladorException {
		Integer idImovel = null;
		
		try {
			Collection<ClienteImovelRetorno> clientesImovelInclusao = this.obterClientesParaIncluir();
			
			for (ClienteImovelRetorno clienteImovelRetorno : clientesImovelInclusao) {
				
				if (!isImovelEmCampo(clienteImovelRetorno.getImovel().getId())) {
					ICliente clienteRetorno = repositorioAtualizacaoCadastral.pesquisarClienteRetorno(clienteImovelRetorno);
					IImovel imovelRetorno = repositorioAtualizacaoCadastral.pesquisarImovelRetorno(clienteImovelRetorno.getImovel().getId());
					
					Integer idSetorComercial = getControladorCadastro().pesquisarIdSetorComercialPorCodigoELocalidade(imovelRetorno.getIdLocalidade(), imovelRetorno.getCodigoSetorComercial());
					
					String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();
					
					RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastral(imovelRetorno, clienteRetorno, clienteImovelRetorno, AlteracaoTipo.INCLUSAO, protocoloAtendimento);
					RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovelRetorno, idSetorComercial, AlteracaoTipo.INCLUSAO);
					RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral(); 
					
					Integer[] retorno = getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);
					
					listaRAParaExclusao.add(retorno[0]);
					
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao inserir cliente." + idImovel);
			throw new ControladorException("Erro ao inserir cliente.", e);
			
		}
	}
	
	public void deletarRAsPendente(List<Integer> listaRAs) {
		try {
			
			
			for (Integer integer : listaRAs) {
				
				RegistroAtendimento registroAtendimento = getRegistroAtendimento(integer).iterator().next();

				FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = new FiltroRegistroAtendimentoSolicitante();
				filtroRegistroAtendimentoSolicitante.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
				
				FiltroTramite filtroTramite = new FiltroTramite();
				filtroTramite.adicionarParametro(new ParametroSimples(FiltroTramite.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
				
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
				
				FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();
				filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoUnidade.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
				
				Collection rasHelper = getControladorUtil().pesquisar(filtroRegistroAtendimentoSolicitante, RegistroAtendimentoSolicitante.class.getName());
				Collection tramiteHelper = getControladorUtil().pesquisar(filtroTramite, Tramite.class.getName());
				Collection osHelper = getControladorUtil().pesquisar(filtroOrdemServico, OrdemServico.class.getName());
				Collection raUnidadeHelper = getControladorUtil().pesquisar(filtroRegistroAtendimentoUnidade, RegistroAtendimentoUnidade.class.getName());
				
				if (osHelper != null && !osHelper.isEmpty()) {
					
					for (Object ordemServico : osHelper) {
						Collection osuHelper = getOrdemServicoUnidade(((OrdemServico) ordemServico).getId());
						
						if (osuHelper == null)
							continue;
						
						Iterator i = osuHelper.iterator();
						while (i.hasNext()) {
							getControladorUtil().remover(i.next());
						}
					}
					
					Iterator i = osHelper.iterator();
					
					while (i.hasNext()) {
						getControladorUtil().remover(i.next());
					}
				}
				
				
				
				if (raUnidadeHelper != null) {
					Iterator i = raUnidadeHelper.iterator();
					
					while (i.hasNext()) {
						getControladorUtil().remover(i.next());
					}
				}
				
				if (tramiteHelper != null) {
					Iterator i = tramiteHelper.iterator();
					
					while (i.hasNext()) {
						getControladorUtil().remover(i.next());
					}
				}
				
				if (rasHelper != null) {
					Iterator i = rasHelper.iterator();
					
					while (i.hasNext()) {
						getControladorUtil().remover(i.next());
					}
				}
				
				getControladorUtil().remover(registroAtendimento);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Collection<OrdemServicoUnidade> getOrdemServicoUnidade(Integer numeroOS) throws Exception {
		FiltroOrdemServicoUnidade filtroOrdemServicoUnidade = new FiltroOrdemServicoUnidade();
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(FiltroOrdemServicoUnidade.ORDEM_SERVICO_ID, numeroOS));
		
		Collection<OrdemServicoUnidade> colecaoOrdemServicoUnidade = getControladorUtil().pesquisar(filtroOrdemServicoUnidade, OrdemServicoUnidade.class.getName());
		
		if (colecaoOrdemServicoUnidade.isEmpty()) {
			return null;
		}
		
		return colecaoOrdemServicoUnidade;
		
		
	}
	
	
	
	public Collection<RegistroAtendimento> getRegistroAtendimento(Integer idRA) {
		try {
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, idRA));
//			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.CODIGO_SITUACAO, String.valueOf(RegistroAtendimento.SITUACAO_PENDENTE)));
//			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFICACAO, 1227)); // "ATUALIZACAO CADASTRAL GSAN"
			
			Collection<RegistroAtendimento> colecaoRegistroAtendimento = getControladorUtil().pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
			
			if (colecaoRegistroAtendimento.isEmpty()) {
				return null;
			}
			
			return colecaoRegistroAtendimento;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Collection<RegistroAtendimentoUnidade> getRAUnidade(Integer numeroRA) {
		try {
			FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();
			filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, numeroRA));
			
			Collection<RegistroAtendimentoUnidade> colecaoRegistroAtendimentoUnidade = getControladorUtil().pesquisar(filtroRegistroAtendimentoUnidade, RegistroAtendimentoUnidade.class.getName());
			
			if (colecaoRegistroAtendimentoUnidade.isEmpty()) {
				return null;
			}
			
			return colecaoRegistroAtendimentoUnidade;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private boolean isRAGerada(Integer matricula) {
		
		try {
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.IMOVEL, matricula));
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.CODIGO_SITUACAO, String.valueOf(RegistroAtendimento.SITUACAO_PENDENTE)));
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFICACAO, 1227)); // "ATUALIZACAO CADASTRAL GSAN"
			
			Collection<RegistroAtendimento> colecaoRegistroAtendimento = getControladorUtil().pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
			
			if (!colecaoRegistroAtendimento.isEmpty()) {
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private void excluirClientes() throws ControladorException {
		Collection<IClienteImovel> clientesImovelExcluirRelacao = this.obterClientesParaExcluirRelacao();
		
		for (IClienteImovel clienteImovel : clientesImovelExcluirRelacao) {
			if (!isImovelEmCampo(clienteImovel.getImovel().getId())) {
				ICliente cliente = getControladorCliente().pesquisarCliente(clienteImovel.getCliente().getId());
				Imovel imovel = getControladorImovel().pesquisarImovel(clienteImovel.getImovel().getId());
				
				Integer idSetorComercial = imovel.getSetorComercia().getId();
				
				String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();
				
				RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastral(imovel, cliente, clienteImovel, AlteracaoTipo.EXCLUSAO, protocoloAtendimento);
				RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovel, idSetorComercial, AlteracaoTipo.EXCLUSAO);
				RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral(); 
				
				Integer[] retorno = getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);
				
				listaRAParaExclusao.add(retorno[0]);
			}
		
		}
	}
	
	private void aprovarImoveis(Collection<IImovel> imoveisParaAprovar) throws ControladorException {
		try {
			repositorioAtualizacaoCadastral.aprovarImoveis(imoveisParaAprovar);
		} catch (Exception e) {
			logger.error("Erro ao aprovar imóveis em lote. " + e);
			throw new ControladorException("Erro ao aprovar imóveis em lote.", e);
		}
	}
	
	private Collection<IImovel> converterListaEmImovelRetorno(Collection<ConsultarMovimentoAtualizacaoCadastralHelper> listaImoveis) throws ErroRepositorioException {
		Collection<IImovel> listaImoveisRetorno = new ArrayList<IImovel>();
		
		for (ConsultarMovimentoAtualizacaoCadastralHelper helper : listaImoveis) {
			IImovel imovelRetorno = repositorioAtualizacaoCadastral.pesquisarImovelRetorno(helper.getIdImovel());

			if (imovelRetorno != null)
				listaImoveisRetorno.add(imovelRetorno);
		}
		return listaImoveisRetorno;
	}

	public Integer obterquantidadeImoveisAprovadosArquivo(Integer idArquivoAtualizacaoCadastral) throws ControladorException {
		return null;
	}
	
	public HashMap<String, Integer> obterDadosAnaliseSituacaoArquivoAtualizacaoCadastral(Integer idArquivo) throws ControladorException {
		HashMap<String, Integer> mapDadosAnalise = new HashMap<String, Integer>();
		
		try {
			
			ArquivoTextoAtualizacaoCadastral arquivo = getControladorCadastro().pesquisarArquivoTextoAtualizacaoCadastro(idArquivo);
			
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.TOTAL_IMOVEIS, arquivo.getQuantidadeImovel());
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.TRANSMITIDOS, (arquivo.getQuantidadeImoveisTransmitidos() != null ? arquivo.getQuantidadeImoveisTransmitidos() : 0));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.APROVADOS, repositorioAtualizacaoCadastral.obterquantidadeImoveisAprovadosArquivo(idArquivo));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ANORMALIDADE, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAnormalidadeArquivo(idArquivo));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ALTERACAO_HIDROMETRO, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAlteracaoFaturamentoArquivo(idArquivo, TabelaColuna.NOME_COLUNA_NUMERO_HUDROMETRO));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ALTERACAO_LIGACAO_AGUA, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAlteracaoFaturamentoArquivo(idArquivo, TabelaColuna.NOME_COLUNA_AGUA));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ALTERACAO_LIGACAO_ESGOTO, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAlteracaoFaturamentoArquivo(idArquivo, TabelaColuna.NOME_COLUNA_ESGOTO));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ALTERACAO_CATEGORIA_SUB_ECONOMIAS, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAlteracaoFaturamentoArquivo(idArquivo, TabelaColuna.NOME_COLUNA_ECONOMIAS));
		} catch (Exception e) {
			logger.error("Erro ao obter dados para análise do aquivo." + e);
			throw new ControladorException("Erro ao obter dados para análise do aquivo.", e);
		}
		return mapDadosAnalise;
	}
	
	private void atualizarImovelControlePorSituacao(Integer idImovel, Integer situacao) throws ControladorException {
		try {
			ImovelControleAtualizacaoCadastral controle = repositorioAtualizacaoCadastral.pesquisarImovelControleAtualizacao(idImovel);
			
			if (controle != null){
				controle.setImovelRetorno(null);
				controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(situacao));
				
				this.getControladorUtil().atualizar(controle);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
	}
	
	public void fiscalizarImovel(Integer idImovel) throws ControladorException {
		try {
			
			ImovelControleAtualizacaoCadastral controle = repositorioAtualizacaoCadastral.pesquisarImovelControleAtualizacao(idImovel);
			
			if (controle != null){
				controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(
						SituacaoAtualizacaoCadastral.EM_FISCALIZACAO));
				this.getControladorUtil().atualizar(controle);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection pesquisarDadosFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis) throws ControladorException {
		
		Collection retorno = new ArrayList();
		
		try {
			Collection colecaoDadosFicha = repositorioAtualizacaoCadastral.pesquisarDadosFichaFiscalizacaoCadastral(listaIdImoveis);
			
			if (colecaoDadosFicha != null && !colecaoDadosFicha.isEmpty()) {
				Iterator iterator = colecaoDadosFicha.iterator();
				
				while (iterator.hasNext()) {
					RelatorioFichaFiscalizacaoCadastralHelper helper = new RelatorioFichaFiscalizacaoCadastralHelper();
					Object[] objeto = (Object[]) iterator.next();
					
					helper.setIdImovel((Integer) objeto[0]);
					helper.setNomeLocalidade((String) objeto[1]);
					helper.setCodigoSetor((Integer) objeto[2]);
					helper.setNumeroQuadra((Integer) objeto[3]);
					helper.setNumeroLote((Integer) objeto[4]);
					helper.setNumeroSublote((Integer) objeto[5]);
					helper.setDescricaoLogradouroImovel((String) objeto[6]);
					helper.setIdLogradouroImovel((Integer) objeto[7]);
					helper.setNumeroImovel((String) objeto[8]);
					helper.setComplementoEnderecoImovel((String) objeto[9]);
					helper.setBairroImovel((String) objeto[10]);
					helper.setCepImovel((Integer) objeto[11]);
					helper.setCodigoRota((Integer) objeto[12]);
					helper.setNumeroFace((Integer) objeto[13]);
					helper.setNomeMunicipioImovel((String) objeto[14]);
					helper.setIdMunicipioImovel((Integer) objeto[15]);
					helper.setIdCliente((Integer) objeto[16]);
					helper.setNomeCliente((String) objeto[17]);
					helper.setCpfCnpj((String) objeto[18]);
					helper.setRg((String) objeto[19]);
					helper.setUf((String) objeto[20]);
					helper.setSexo((Integer) objeto[21]);
					helper.setDescricaoLogradouroCliente((String) objeto[22]);
					helper.setNumeroImovelCliente((String) objeto[23]);
					helper.setEnderecoTipoCliente((Integer) objeto[24]);
					helper.setNomeMunicipioCliente((String) objeto[25]);
					helper.setComplementoEnderecoCliente((String) objeto[26]);
					helper.setBairroCliente((String) objeto[27]);
					helper.setCepCliente((Integer) objeto[28]);
					helper.setEmailCliente((String) objeto[29]);
					helper.setDdd((String) objeto[30]);
					helper.setTelefone((String) objeto[31]);
					helper.setCelular((String) objeto[32]);
					
					retorno.add(helper);
				}
			}
			
			return retorno;
		} catch(ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.pesquisarImovelControleAtualizacao(idImovel);
		} catch (Exception e) {
			throw new ControladorException("Erro ao pesquisar ImovelControleAtualizacaoCadastral.", e);
		}
	}
	
	public Collection pesquisarDadosRelatorioRelacaoImoveisRotaAtualizacaoCadastral(String idLocalidade, String cdSetorComercial, String cdRota)
			throws ControladorException {
		
		Collection retorno = new ArrayList();
		
		try {
			Collection colecaoImoveisRetorno = repositorioAtualizacaoCadastral.pesquisarDadosImoveisPorRotaAtualizacaoCadastral(
					idLocalidade, cdSetorComercial, cdRota);
			
			if (colecaoImoveisRetorno != null && !colecaoImoveisRetorno.isEmpty()) {
				Iterator iterator = colecaoImoveisRetorno.iterator();
				
				while (iterator.hasNext()) {
					RelatorioRelacaoImoveisRotaBean bean = new RelatorioRelacaoImoveisRotaBean();
					Object[] objeto = (Object[]) iterator.next();
					
					String idImovel = objeto[0].toString();
					Integer idImovelRetorno = (Integer) objeto[10];
					
					Integer tipoOperacao = (Integer) objeto[9];
					if (tipoOperacao.equals(AlteracaoTipo.INCLUSAO)) {
						bean.setIdImovel("NOVO");
						bean.setDescCategorias(this.getCategoriasImovelRetornoRelatorioPorRota(idImovelRetorno));
						
					} else if (tipoOperacao.equals(AlteracaoTipo.ALTERACAO)) {
						bean.setIdImovel(idImovel);
						bean.setDescCategorias(this.getCategoriasImovelRetornoRelatorioPorRota(idImovelRetorno));
						
					} else if (tipoOperacao.equals(AlteracaoTipo.EXCLUSAO)) {
						bean.setIdImovel(idImovel + " - EXCLUÍDO");
						bean.setDescCategorias(this.getCategoriasImovelRetornoRelatorioPorRota(idImovelRetorno));
						
					} else if (tipoOperacao.equals(new Integer(0))) {
						bean.setIdImovel(idImovel);
						bean.setDescCategorias(this.getCategoriasImovelRelatorioPorRota(Integer.valueOf(idImovel)));
					}
					
					bean.setIdLocalidade(objeto[1].toString());
					bean.setNomeLocalidade(objeto[2].toString());
					bean.setCodigoSetorComercial(objeto[3].toString());
					bean.setNumQuadra(objeto[4].toString());
					bean.setNumLote(objeto[5].toString());
					bean.setNumSubLote(objeto[6].toString());
					bean.setDescSituacaoLigacaoAgua(objeto[7].toString());
					bean.setDescSituacaoImovelRecadastramento(objeto[8].toString());
					
					retorno.add(bean);
				}
			}
			
			return retorno;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	private String getCategoriasImovelRelatorioPorRota(Integer idImovel) throws ControladorException {
		String categorias = "";
		
		Collection colecaoCategorias = getControladorImovel().pesquisarCategoriasImovel(idImovel);
		for (Iterator iteratorCategorias = colecaoCategorias.iterator(); iteratorCategorias.hasNext();) {
			ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iteratorCategorias.next();
			
			String descCategoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria().getDescricao();
			
			if (!categorias.contains(descCategoria))
				categorias += descCategoria + "\n";
			
			if (!iteratorCategorias.hasNext()) {
				int index = categorias.lastIndexOf("\n");
				categorias = categorias.substring(0, index);
			}
		}
		
		return categorias;
	}
	
	private String getCategoriasImovelRetornoRelatorioPorRota(Integer idImovelRetorno) {
		String categorias = "";
		
		Collection<ImovelSubcategoriaRetorno> subcategorias;
		try {
			subcategorias = repositorioAtualizacaoCadastral.pesquisarSubcategoriasImovelRetorno(idImovelRetorno);
			
			for (Iterator iterator = subcategorias.iterator(); iterator.hasNext();) {
				ImovelSubcategoriaRetorno subcategoria = (ImovelSubcategoriaRetorno) iterator.next();
				
				String descCategoria = subcategoria.getSubcategoria().getCategoria().getDescricao();
				
				if (!categorias.contains(descCategoria))
					categorias += descCategoria + "\n";
				
				if (!iterator.hasNext()) {
					int index = categorias.lastIndexOf("\n");
					categorias = categorias.substring(0, index);
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return categorias;
	}
}
