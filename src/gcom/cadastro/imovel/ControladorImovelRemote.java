package gcom.cadastro.imovel;

import gcom.util.ErroRepositorioException;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

public interface ControladorImovelRemote extends javax.ejb.EJBObject {

	public void inserirImovel(Imovel imovel) throws RemoteException;

	public int obterQuantidadeEconomias(Imovel imovel) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoImovelSubcategorias(Imovel imovel) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasCategoria(Imovel imovel) throws RemoteException;

	public Object pesquisarObterQuantidadeCategoria() throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Integer inserirImovelRetorno(Imovel imovel, Collection subCategorias, Collection enderecoImoveis, Collection clientes,
			Collection colecaoClientesImoveisRemovidos, Collection colecaoImovelSubcategoriasRemovidas) throws RemoteException;

	public void inserirImovelSubCategoria(ImovelSubcategoria imovelSubcategoria) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void informarImovelEconomias(Collection imoveisEconomias) throws RemoteException;

	public void removerImovelEconomia(ImovelEconomia imovelEconomia) throws RemoteException;

	public void atualizarImovel(Imovel imovel) throws RemoteException, ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarImovel(Imovel imovel, Collection subcategorias, Collection enderecoImovel, Collection clientes) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void verificarExistenciaIPTU(Collection imoveisEconomia, Imovel imovel, String numeroIptu, Date dataUltimaAlteracao) throws RemoteException,
			ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void verificarExistenciaCelpe(Collection imoveisEconomia, Imovel imovel, String numeroCelpe, Date dataUltimaAlteracao) throws RemoteException,
			ErroRepositorioException;

	public void removerImovel(String[] ids) throws RemoteException;

	public void atualizarImovelSubCategoria(ImovelSubcategoria imovelSubcategoria) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public Collection carregarImovelMicromedicao(Collection imoveisMicromedicao) throws RemoteException;

	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(Integer idEntidadeBeneficente) throws RemoteException;

	public Collection<ImovelDoacao> pesquisarImovelDoacao(FiltroImovelDoacao filtroImovelDoacao) throws RemoteException;

	public ImovelDoacao verificarExistenciaImovelDoacao(Integer idImovel, Integer idEntidadeBeneficente) throws RemoteException;

	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao) throws RemoteException;

}
