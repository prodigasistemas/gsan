package gcom.atualizacaocadastral;

import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.seguranca.IRepositorioSeguranca;
import gcom.seguranca.RepositorioSegurancaHBM;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
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

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

public class ControladorAtualizacaoCadastral implements IControladorAtualizacaoCadastral, SessionBean {


	private static final long serialVersionUID = -3792912776769033056L;
	
	private static Logger logger = Logger.getLogger(ControladorAtualizacaoCadastral.class);
	
	private IRepositorioAtualizacaoCadastral repositorioAtualizacaoCadastral = null;
	private IRepositorioSeguranca repositorioSeguranca;

	
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
	
	private ControladorClienteLocal getControladorCliente() {

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
	
	public Collection<IImovel> obterImoveisParaAtualizar() throws ControladorException {
		Collection<IImovel> imoveis = null;
		try {
			imoveis = repositorioAtualizacaoCadastral.obterImoveisParaAtualizar();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar imoveis para atualizar.", e);
			throw new ControladorException("Erro ao pesquisar imoveis para atualizar.", e);

		}
		return imoveis;
	}
	
	public void atualizarImoveisAprovados(Integer idFuncionalidade, Usuario usuarioLogado) throws ControladorException{
		int idUnidadeIniciada = 0;
		int idImovel = -1;
		
		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidade, UnidadeProcessamento.FUNCIONALIDADE, 0);
			
			Collection<IImovel> imoveis = this.obterImoveisParaAtualizar();
			
			for (IImovel imovelRetorno : imoveis) {
				if (isImovelEmCampo(imovelRetorno.getIdImovel())) {
					idImovel = imovelRetorno.getId();
					imovelRetorno.setId(imovelRetorno.getIdImovel());
					Imovel imovel = new Imovel(imovelRetorno.getId());
					
					atualizarImovelAtualizacaoCadastral(imovelRetorno);
					atualizarImovelSubcategoriaAtualizacaoCadastral(imovel);
					atualizarImovelRamoAtividadeAtualizacaoCadastral(imovel, imovelRetorno);
					atualizarClienteFoneAtualizacaoCadastral(imovel);
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			logger.error("Erro ao atualizar imovel aprovado: " + idImovel, e);
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new ControladorException("Erro ao atualizar imóveis aprovados.", e);
		}
	}
	
	public void apagarInformacoesRetornoImovelAtualizacaoCadastral(Integer idImovel) throws Exception {
		atualizarImovelControle(idImovel);
		apagarTabelaAtualizacaoCadastralPorIdImovel(idImovel);
		apagarImovelRetorno(idImovel);
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
	
	/************************************************************
	 *PRIVATE METHODS 
	 ************************************************************/
	
	private void atualizarImovelAtualizacaoCadastral(IImovel imovelRetorno) throws ControladorException {
		Imovel imovel = getControladorImovel().pesquisarImovel(imovelRetorno.getIdImovel());

		MergeProperties.mergeProperties(imovel, imovelRetorno);
		
		getControladorUtil().atualizar(imovel);
	}
	
	private void atualizarImovelSubcategoriaAtualizacaoCadastral(IImovel imovelRetorno) throws Exception {
		imovelRetorno.setId(imovelRetorno.getIdImovel());
		Imovel imovel = new Imovel(imovelRetorno.getIdImovel());

		Collection<IImovelSubcategoria> subcategoriasRetorno = this.obterImovelSubcategoriaParaAtualizar(imovel.getId());
		Collection<Integer> idsSubcategorias = this.obterIdsSubcategoriasImovel(imovel);
		
		for (IImovelSubcategoria subcategoriaRetorno : subcategoriasRetorno) {
			ImovelSubcategoria imovelSubcategoria = this.obterSubcategoriaDoImovel(imovel, subcategoriaRetorno.getSubcategoria().getId());
			
			if (imovelSubcategoria != null) {
				MergeProperties.mergeProperties(imovelSubcategoria, subcategoriaRetorno);
				imovelSubcategoria.setUltimaAlteracao(new Date());
				getControladorUtil().atualizar(imovelSubcategoria);
				idsSubcategorias.remove(imovelSubcategoria.getComp_id().getSubcategoria().getId());
			} else {
				imovelSubcategoria = new ImovelSubcategoria();
				MergeProperties.mergeProperties(imovelSubcategoria, subcategoriaRetorno);
				getControladorUtil().inserir(imovelSubcategoria);
			}
		}
		
		this.removerSubcategoriasDoImovel(imovelRetorno, idsSubcategorias);
	}
	
	private void atualizarImovelRamoAtividadeAtualizacaoCadastral(Imovel imovel, IImovel imovelRetorno) throws Exception {
		try {
			Collection<IImovelRamoAtividade> ramosAtividadeRetorno = this.obterImovelRamoAtividadeParaAtualizar(imovel.getId());
			Collection<Integer> idsRamosAtividadesImovel = this.obterIdsRamosAtividadesImovel(imovel);
			
			for (IImovelRamoAtividade ramoAtividadeRetorno : ramosAtividadeRetorno) {
				ImovelRamoAtividade imovelRamoAtividade = this.obterRamoAtividadeDoImovel(imovel, ramoAtividadeRetorno.getRamoAtividade().getId());
				
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
			this.removerRamosAtividadeDoImovel(imovelRetorno, idsRamosAtividadesImovel);
		} catch (ControladorException e) {
			logger.error("Erro ao atualizar ramo de atividade do imóvel " + imovelRetorno.getId(), e);
			throw new ControladorException("Erro ao atualizar ramo de atividade do imóvel " + imovelRetorno.getId(), e);
		}
	}
	
	private void atualizarClienteFoneAtualizacaoCadastral(IImovel imovelRetorno) throws Exception {
		this.removerClienteFoneDoImovel(imovelRetorno);

		Collection<IClienteFone> clienteFonesRetorno = this.obterClientesFoneParaAtualizar(imovelRetorno.getId());
		
		for (IClienteFone clienteFoneRetorno : clienteFonesRetorno) {
			ClienteFone clienteFone = new ClienteFone();
			MergeProperties.mergeProperties(clienteFone, clienteFoneRetorno);
			clienteFone.setUltimaAlteracao(new Date());
			clienteFone.setId(null);
			getControladorUtil().inserir(clienteFone);
		}
	}

	private void removerSubcategoriasDoImovel(IImovel imovel, Collection<Integer> idsSubcategorias) throws ControladorException {
		for (Integer id : idsSubcategorias) {
			ImovelSubcategoria imovelSubcategoria = this.obterSubcategoriaDoImovel(imovel, id);
			getControladorUtil().remover(imovelSubcategoria);
		}
	}
	
	private void removerRamosAtividadeDoImovel(IImovel imovel, Collection<Integer> idsRamosAtividades) throws ControladorException {
		for (Integer id : idsRamosAtividades) {
			ImovelRamoAtividade imovelRamoAtividade = this.obterRamoAtiviadeDoImovel(imovel, id);
			getControladorUtil().remover(imovelRamoAtividade);
		}
	}
	
	private void removerClienteFoneDoImovel(IImovel imovel) throws ControladorException {
		Collection<ClienteFone> clienteFones = getControladorCliente().pesquisarClienteFoneDoImovel(imovel.getId());
		for (ClienteFone clienteFone : clienteFones) {
			getControladorUtil().remover(clienteFone);
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

	private ImovelSubcategoria obterSubcategoriaDoImovel(IImovel imovel, Integer idSubcategoria) throws ControladorException {
		Collection<ImovelSubcategoria> subcategoriasImovel = getControladorImovel().pesquisarImovelSubcategorias((Imovel)imovel);
		
		for (ImovelSubcategoria subcategoria : subcategoriasImovel) {
			if (subcategoria.getComp_id().getSubcategoria().getId().equals(idSubcategoria)) {
				return subcategoria;
			}
		}
		
		return null;
	}
	
	private ImovelRamoAtividade obterRamoAtividadeDoImovel(Imovel imovel, Integer id) throws ControladorException {
		Collection<ImovelRamoAtividade> ramosAtividadeImovel = getControladorImovel().pesquisarRamoAtividadeDoImovel(imovel.getId());
		
		for (ImovelRamoAtividade imovelRamoAtividade : ramosAtividadeImovel) {
			if (imovelRamoAtividade.getComp_id().getRamo_atividade().getId().equals(id)) {
				return imovelRamoAtividade;
			}
		}
		
		return null;
	}
	
	private Collection<Integer> obterIdsSubcategoriasImovel(IImovel imovel) throws ControladorException {
		Collection<Integer> ids = new ArrayList<Integer>();
		Collection<ImovelSubcategoria> subcategoriasImovel = getControladorImovel().pesquisarImovelSubcategorias((Imovel)imovel);
		
		for (ImovelSubcategoria imovelSubcategoria : subcategoriasImovel) {
			ids.add(imovelSubcategoria.getComp_id().getSubcategoria().getId());
		}
		
		return ids;

	}
	
	private Collection<Integer> obterIdsRamosAtividadesImovel(Imovel imovel) throws ControladorException{
		Collection<Integer> ids = new ArrayList<Integer>();
		Collection<ImovelRamoAtividade> ramosAtividade = getControladorImovel().pesquisarRamoAtividadeDoImovel(imovel.getId());
		
		for (ImovelRamoAtividade imovelRamoAtividade : ramosAtividade) {
			ids.add(imovelRamoAtividade.getComp_id().getRamo_atividade().getId());
		}
		return ids;
	}
	
	private Collection<IImovelSubcategoria> obterImovelSubcategoriaParaAtualizar(Integer idImovel) throws Exception {
		Collection<IImovelSubcategoria> subcategorias = repositorioAtualizacaoCadastral.obterImovelSubcategoriaParaAtualizar(idImovel);
		return subcategorias;
	}
	
	private Collection<IImovelRamoAtividade> obterImovelRamoAtividadeParaAtualizar(Integer idImovel) throws Exception {
		Collection<IImovelRamoAtividade> ramosAtividade = repositorioAtualizacaoCadastral.obterImovelRamoAtividadeParaAtualizar(idImovel);
		return ramosAtividade;
	}

	private boolean isImovelEmCampo(Integer idImovel) {
		try {
			getControladorMicromedicao().validarImovelEmCampo(idImovel); 
			return true;
		} catch (ControladorException e) {
			return false;
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

	public Collection<Integer> pesquisarImoveisPorSituacaoPeriodo(Date dataInicial, Date dataFinal, Integer idSituacaoCadastral)
			throws ControladorException {
		try {
			if(idSituacaoCadastral.equals(SituacaoAtualizacaoCadastral.APROVADO)) {
				return repositorioAtualizacaoCadastral.pesquisarImoveisAprovadosPorPeriodo(dataInicial, dataFinal);
			} else if(idSituacaoCadastral.equals(SituacaoAtualizacaoCadastral.DISPONIVEL)){
				return repositorioAtualizacaoCadastral.pesquisarImoveisDisponiveisPorPeriodo(dataInicial, dataFinal);
			} else if(idSituacaoCadastral.equals(SituacaoAtualizacaoCadastral.TRANSMITIDO)){
				return repositorioAtualizacaoCadastral.pesquisarImoveisTransmitidosPorPeriodo(dataInicial, dataFinal);
			}
		} catch (ErroRepositorioException e) {
			 throw new ControladorException("erro.sistema", e);
		}
		return null;
	}
}
