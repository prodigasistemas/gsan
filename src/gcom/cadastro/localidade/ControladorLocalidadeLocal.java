package gcom.cadastro.localidade;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.micromedicao.bean.NumeroQuadraMinimoMaximoDaRotaHelper;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ControladorException;

import java.util.Collection;

public interface ControladorLocalidadeLocal extends javax.ejb.EJBLocalObject {

	@SuppressWarnings("rawtypes")
	public void atualizarSetorComercial(SetorComercial setorComercial, Collection colecaoFonteCaptacao) throws ControladorException;

	public void atualizarLocalidade(Localidade localidade) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarQuadra(Quadra quadra, Usuario usuarioLogado, Collection colecaoQuadraFace) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarSetorComercial(int idLocalidade) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQuadra(int idSetorComercial) throws ControladorException;

	public Collection<Localidade> pesquisarLocalidadePorGerenciaRegional(int idGerenciaRegional) throws ControladorException;

	public Collection<GerenciaRegional> pesquisarGerenciaRegional() throws ControladorException;

	public GerenciaRegional pesquisarObjetoGerenciaRegionalRelatorio(Integer idGerenciaRegional) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer inserirSetorComercial(SetorComercial setorComercial, Collection colecaoFonteCaptacao) throws ControladorException;

	public Localidade pesquisarLocalidadeDigitada(Integer idLocalidadeDigitada) throws ControladorException;

	public int pesquisarMaximoCodigoQuadra(Integer idSetorComercial) throws ControladorException;

	public int pesquisarMaximoCodigoSetorComercial(Integer idLocalidade) throws ControladorException;

	public int pesquisarMaximoIdLocalidade() throws ControladorException;

	public Localidade pesquisarObjetoLocalidadeRelatorio(Integer idLocalidade) throws ControladorException;

	public SetorComercial pesquisarObjetoSetorComercialRelatorio(Integer codigoSetorComercial, Integer idLocalidade) throws ControladorException;

	public Integer verificarExistenciaLocalidade(Integer idLocalidade) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer inserirQuadra(Quadra quadra, Collection colecaoQuadraFace, Usuario usuarioLogado) throws ControladorException;

	public void removerQuadra(String[] ids, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper,
			Usuario usuarioLogado) throws ControladorException;

	public Integer pesquisarIdGerenciaParaLocalidade(Integer idLocalidade) throws ControladorException;

	public void atualizarLogradouroCepGerenciaRegional(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ControladorException;

	public void atualizarLogradouroBairroGerenciaRegional(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo)
			throws ControladorException;

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ControladorException;

	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ControladorException;

	public Integer pesquisarIdUnidadeNegocioParaLocalidade(Integer idLocalidade) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarTodosIdsLocalidade() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQuadrasPorSetorComercialFaturamentoGrupo(int localidade, int[] idsSetorComercial, Integer idFaturamentoGrupo)
			throws ControladorException;

	public Short pesquisarIndicadorRedeEsgotoQuadra(int idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQuadrasPorRoteiroEmpresa(int idRoteiroEmpresa) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarEloPolo() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidadesMunicipio(Integer idMunicipio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdQuadraPorSetorComercial(Integer idSetorComercial) throws ControladorException;

	public Collection<Integer> pesquisarIdsLocalidadesImoveis() throws ControladorException;

	public void atualizarRotaDasQuadrasNoIntervaloNumero(int idNovaRota, int idSetorComercial, int numeroInicial, int numeroFinal) throws ControladorException;

	public Collection<NumeroQuadraMinimoMaximoDaRotaHelper> pesquisarNumeroQuadraMininoMaximoDaRota(Collection<Integer> idsRotas) throws ControladorException;

	public Integer pesqisarQuantidadeQuadrasDaRota(Integer idRota, Integer quadraInicial, Integer quadraFinal) throws ControladorException;

	public Quadra validarQuadra(InserirQuadraHelper helper) throws ControladorException;

	public IntegracaoQuadraFaceHelper integracaoQuadraFace(Integer idImovel) throws ControladorException;

	public Quadra obterQuadraSetorComercial(int idSetor, int numeroQuadra) throws ControladorException;

	public Collection<Integer> pesquisarIdsLocalidades() throws ControladorException;
	
	public ConsumoTarifa obterConsumoTarifaPadrao(Integer idLocalidade) throws ControladorException;

}
