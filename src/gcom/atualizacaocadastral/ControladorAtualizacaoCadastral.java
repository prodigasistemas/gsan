package gcom.atualizacaocadastral;

import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
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
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.rmi.RemoteException;
import java.util.Collection;
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
	
	public Collection<IImovel> obterImoveisParaAtualizar() throws ControladorException {
		Collection<IImovel> imoveis = null;
		try {
			imoveis = repositorioAtualizacaoCadastral.obterImoveisParaAtualizar();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar imoveis para atualizar.", e);
		}
		return imoveis;
	}
	
	public void atualizarImoveisAprovados(Integer idFuncionalidade, Usuario usuarioLogado) throws ControladorException{
		
		int idUnidadeIniciada = 0;
		
		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidade, UnidadeProcessamento.FUNCIONALIDADE, 0);
			
			Collection<IImovel> imoveis = this.obterImoveisParaAtualizar();
			
			for (IImovel imovel : imoveis) {
				getControladorUtil().atualizar((Imovel)imovel);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
		}
	}
	
	public void atualizarIdArquivoTextoImovelAtualizacaoCadastral(
			Integer idArquivoTexto, Integer idImovel) throws ControladorException {
		
		ArquivoTextoAtualizacaoCadastral arquitoTexto = new ArquivoTextoAtualizacaoCadastral();
		arquitoTexto.setId(idArquivoTexto);
		
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = this.pesquisarImovelAtualizacaoCadastral(idImovel);
		imovelAtualizacaoCadastral.setIdArquivoTexto(idArquivoTexto);
		
		this.getControladorUtil().atualizar(imovelAtualizacaoCadastral);
	}


	public void apagarInformacoesRetornoImovelAtualizacaoCadastral(Integer idImovel) throws ControladorException {
		apagarTabelaAtualizacaoCadastralPorIdImovel(idImovel);
		apagarImovelRetorno(idImovel);
		apagarImovelSubcategoriaRetorno(idImovel);
		apagarImovelRamoAtividade(idImovel);
		apagarImovelClienteRetorno(idImovel);
	}

	private void apagarImovelRetorno(Integer idImovel) throws ControladorException {
		ImovelRetorno imovelRetorno;
		try {
			imovelRetorno = repositorioAtualizacaoCadastral.pesquisarImovelRetornoPorIdImovel(idImovel);
			if(imovelRetorno != null) {
				this.getControladorUtil().remover(imovelRetorno);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}

	private void apagarTabelaAtualizacaoCadastralPorIdImovel(Integer idImovel) throws ControladorException {
		List<TabelaAtualizacaoCadastral> colecaoTabelaAtualizacaoCadastral;
		List<TabelaColunaAtualizacaoCadastral> colecaoTabelaColunaAtualizacaoCadastral;
		try {
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
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	private void apagarImovelSubcategoriaRetorno(Integer idImovel) throws ControladorException {
		List<ImovelSubcategoriaRetorno> colecaoImovelSubCategoriaRetorno;
		try {
			colecaoImovelSubCategoriaRetorno = repositorioAtualizacaoCadastral.pesquisarImovelSubcategoriaRetornoPorIdImovel(idImovel);
			if(colecaoImovelSubCategoriaRetorno != null) {
				for(ImovelSubcategoriaRetorno imovelSubCategoriaRetorno : colecaoImovelSubCategoriaRetorno) {
					this.getControladorUtil().remover(imovelSubCategoriaRetorno);
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	private void apagarImovelRamoAtividade(Integer idImovel) throws ControladorException {
		try {
			repositorioAtualizacaoCadastral.apagarImovelRetornoRamoAtividadeRetornoPorIdImovel(idImovel);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}

	private void apagarImovelClienteRetorno(Integer idImovel) throws ControladorException {
		try {
			repositorioAtualizacaoCadastral.apagarClienteImovelRetornoPorIdImovel(idImovel);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria,Integer idCategoria)
		throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.pesquisarImovelSubcategoriaAtualizacaoCadastral(idImovel, idSubcategoria, idCategoria);
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
    }
	
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel)
		throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.pesquisarImovelAtualizacaoCadastral(idImovel);
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
    }

}
