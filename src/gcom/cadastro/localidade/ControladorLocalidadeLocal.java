package gcom.cadastro.localidade;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.micromedicao.bean.NumeroQuadraMinimoMaximoDaRotaHelper;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @created 6 de Setembro de 2005
 * @version 1.0
 */

public interface ControladorLocalidadeLocal extends javax.ejb.EJBLocalObject {
    /**
     * < <Descrição do método>>
     * 
     * @param setorComercial
     *            Descrição do parâmetro
     */
    public void atualizarSetorComercial(SetorComercial setorComercial,Collection colecaoFonteCaptacao) 
    	throws ControladorException;

    /**
     * < <Descrição do método>>
     * 
     * @param localidade
     *            Descrição do parâmetro
     */
    public void atualizarLocalidade(Localidade localidade) throws ControladorException;

    /**
     * < <Descrição do método>>
     * 
     * @param quadra
     *            Descrição do parâmetro
     */
    public void atualizarQuadra(Quadra quadra, Usuario usuarioLogado, 
    		Collection colecaoQuadraFace) throws ControladorException;

    /**
     * Pesquisa uma coleção de setor comercial com uma query especifica
     * 
     * @param idLocalidade
     *            parametros para a consulta
     * @return Description of the Return Value
     */
    public Collection pesquisarSetorComercial(int idLocalidade) throws ControladorException;

    /**
     * Pesquisa uma coleção de quadra com uma query especifica
     * 
     * @param idSetorComercial
     *            parametros para a consulta
     * @return Description of the Return Value
     */
    public Collection pesquisarQuadra(int idSetorComercial) throws ControladorException;
    
    /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     * @exception RemoteException
     *                Description of the Exception
     */
    // public Collection pesquisarQuadraRelatorio(Quadra quadraParametros)
    // throws ControladorException;
    /**
     * Pesquisa uma coleção de localidades por gerência regional
     * 
     * @param idGerenciaRegional
     *            Código da gerência regional solicitada
     * @return Coleção de Localidades da Gerência Regional solicitada
     * @exception ControladorException
     *                Erro no hibernate
     */
    public Collection<Localidade> pesquisarLocalidadePorGerenciaRegional(int idGerenciaRegional) throws ControladorException;

    /**
     * Pesquisa uma coleção de gerências regionais
     * 
     * @return Coleção de Gerências Regionais
     * @exception ErroRepositorioException
     *                Erro no hibernate
     */
    public Collection<GerenciaRegional> pesquisarGerenciaRegional() throws ControladorException;

    /**
     * Pesquisa uma gerência regional pelo id
     * 
     * @author Rafael Corrêa
     * @date 01/08/2006
     * 
     * @return Gerência Regional
     * @exception ErroRepositorioException
     *                Erro no hibernate
     */
    public GerenciaRegional pesquisarObjetoGerenciaRegionalRelatorio(Integer idGerenciaRegional) throws ControladorException;

    /**
     * Inseri um objeto do tipo setor comercial no BD
     * 
     * @param setorComercial
     * @return ID gerado
     * @throws ControladorException
     */
    public Integer inserirSetorComercial(SetorComercial setorComercial,Collection colecaoFonteCaptacao) 
    	throws ControladorException;

    /**
     * Inseri uma coleção de pagamentos no sistema
     * 
     * [UC0265] Inserir Pagamentos
     * 
     * Este fluxo secundário tem como objetivo pesquisar a localidade digitada
     * pelo usuário
     * 
     * [FS0007] - Verificar existência da localidade
     * 
     * @author Pedro Alexandre
     * @date 16/02/2006
     * 
     * @param idLocalidadeDigitada
     * @return
     * @throws ControladorException
     */
    public Localidade pesquisarLocalidadeDigitada(Integer idLocalidadeDigitada) throws ControladorException;

    /**
     * Método que retorna o maior número da quadra de um setor comercial
     * 
     * @author Rafael Corrêa
     * @date 11/07/2006
     * 
     * @param idSetorComercial
     * @return
     * @throws ControladorException
     */

    public int pesquisarMaximoCodigoQuadra(Integer idSetorComercial) throws ControladorException;

    /**
     * Método que retorna o maior código de setor comercial de uma localidade
     * 
     * @author Rafael Corrêa
     * @date 11/07/2006
     * 
     * @param idSetorComercial
     * @return
     * @throws ControladorException
     */

    public int pesquisarMaximoCodigoSetorComercial(Integer idLocalidade) throws ControladorException;

    /**
     * Método que retorna o maior id da Localidade
     * 
     * @author Vivianne Sousa
     * @date 12/07/2006
     * 
     * @return
     * @throws ControladorException
     */

    public int pesquisarMaximoIdLocalidade() throws ControladorException;

    /**
     * Pesquisa uma localidade pelo id
     * 
     * @author Rafael Corrêa
     * @date 01/08/2006
     * 
     * @return Localidade
     * @exception ErroRepositorioException
     *                Erro no hibernate
     */
    public Localidade pesquisarObjetoLocalidadeRelatorio(Integer idLocalidade) throws ControladorException;

    /**
     * Pesquisa um setor comercial pelo código e pelo id da localidade
     * 
     * @author Rafael Corrêa
     * @date 01/08/2006
     * 
     * @return SetorComercial
     * @exception ErroRepositorioException
     *                Erro no hibernate
     */
    public SetorComercial pesquisarObjetoSetorComercialRelatorio(Integer codigoSetorComercial, Integer idLocalidade) throws ControladorException;

    public Integer verificarExistenciaLocalidade(Integer idLocalidade) throws ControladorException;

    /**
	 * Metódo responsável por inserir uma quadra no sitema
	 * 
	 * [UC0000 - Inserir Quadra]
	 * 
	 * @author Vivianne Sousa, Pedro Alexandre
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param quadra
	 * @param usuarioLogado
	 * @param usuarioLogado
	 * @return colecaoQuadraFace
	 * @throws ControladorException
	 */
	public Integer inserirQuadra(Quadra quadra, Collection colecaoQuadraFace, Usuario usuarioLogado)
			throws ControladorException ;

    /**
     * metódo responsável por verificar se o usuário que está tentando remover as quadras tem abrangência
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 18/11/2006
     *
     * @param ids
     * @param pacoteNomeObjeto
     * @param operacaoEfetuada
     * @param acaoUsuarioHelper
     * @param usuarioLogado
     * @throws ControladorException
     */
    public void removerQuadra(String[] ids, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper, Usuario usuarioLogado) throws ControladorException ;

    /**
     * Pesquisa o id da gerência regional para a qual a localidade pertence.
     * 
     * [UC0267] Encerrar Arrecadação do Mês, [UC0155] Encerrar Faturamento do Mês
     * 
     * @author Pedro Alexandre
     * @date 05/01/2007
     *
     * @param idLocalidade
     * @return
     * @throws ControladorException
     */
    public Integer pesquisarIdGerenciaParaLocalidade(Integer idLocalidade) throws ControladorException ;
    
//    /**
//     * < <Descrição do método>>
//     * 
//     * @param localidade
//     *            Descrição do parâmetro
//     */
//    public void atualizarGerenciaRegional(GerenciaRegional gerenciaRegional) throws ControladorException;
//    /**
//     * Metódo responsável por inserir uma gerência Regional
//     *
//     * [UC0000 - Inserir Gerencia Regional
//     *
//     * @author Thiago Tenório
//     * @date 27/06/2006, 16/11/2006
//     * 
//     * @param gerencia regional
//     * @param usuarioLogado
//     * @return
//     * @throws ControladorException
//     */
//    public Integer inserirGerenciaRegional(GerenciaRegional gerenciaRegional) throws ControladorException;
    
    
    /**
	 * Atualiza logradouroCep de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCepGerenciaRegional(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ControladorException ;
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairroGerenciaRegional(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ControladorException ;
	
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ControladorException ;
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ControladorException ;
	
	
	/**
     * Pesquisa o id da unidade negocio para a qual a localidade pertence.
     * 
     * [UC0267] Encerrar Arrecadação do Mês, [UC0155] Encerrar Faturamento do Mês
     * 
     * @author Raphael Rossiter
     * @date 30/05/2007
     *
     * @param idLocalidade
     * @throws ControladorException
     */
    public Integer pesquisarIdUnidadeNegocioParaLocalidade(Integer idLocalidade) throws ControladorException ;
    
	/**
	 * Pesquisar os ids da Localidade
	 * 
	 * @author Thiago tenório
	 * @date 07/02/2007
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTodosIdsLocalidade() throws ControladorException ;

    /**
     * Pesquisa uma coleção de quadra com uma query especifica
     * 
     * @param idsSetorComercial
     * @param idFaturamentoGrupo
     *            parametros para a consulta
     * @return Description of the Return Value
     * @throws ControladorException
     */
    public Collection pesquisarQuadrasPorSetorComercialFaturamentoGrupo(int localidade, int []idsSetorComercial, Integer idFaturamentoGrupo) throws ControladorException;
    
    
    /**
	 * 
	 * [UC608] Efetuar Ligação de Esgoto sem RA
	 * 
	 * @author Sávio Luiz
	 * @date 10/09/2007
	 * 
	 * @return
	 */

	public Short pesquisarIndicadorRedeEsgotoQuadra(int idImovel)
			throws ControladorException;
 
	/**
	 * Pesquisar quadras de um roteiro empresa 
	 * @param idRoteiroEmpresa
	 * @return coleção de quadras
	 * @throws ControladorException
	 */
	public Collection pesquisarQuadrasPorRoteiroEmpresa(int idRoteiroEmpresa) throws ControladorException;
	
	/**
	 * Obtém Elo Pólo
	 * @author Ana Maria
	 * @date 10/12/2007
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarEloPolo()throws ControladorException;
	
	/**
	 * pesquisar localidades do município
	 * @author Sávio Luiz
	 * @date 25/02/2008
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesMunicipio(Integer idMunicipio)throws ControladorException;
    
    /**
     * @author: Vivianne Sousa
     * @date: 16/05/2008 
     */
    public Collection pesquisarIdQuadraPorSetorComercial(Integer idSetorComercial)
        throws ControladorException;
	
	/**
	 * Pesquisa os ids das localidaes que possuem imóveis
	 *
	 * @author Pedro Alexandre
	 * @date 07/07/2008
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesImoveis() throws ControladorException ;
	
    /**
     * Atualiza o campo rota das quadras que pertencem ao intervalo de numero informado e ao setor comercial
     * 
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public void atualizarRotaDasQuadrasNoIntervaloNumero(
    		int idNovaRota, int idSetorComercial, int numeroInicial, int numeroFinal) throws ControladorException;
    
    /**
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public Collection<NumeroQuadraMinimoMaximoDaRotaHelper> pesquisarNumeroQuadraMininoMaximoDaRota(
    		Collection<Integer> idsRotas) throws ControladorException;
    
    /**
     * [UC0???] Informar Subdivisões de Rota
     * 
     * @author: Victor Cisneiros
     * @date: 27/10/2008
     */
    public Integer pesqisarQuantidadeQuadrasDaRota(
    		Integer idRota, Integer quadraInicial, Integer quadraFinal) throws ControladorException;
    
    /**
     * [UC0024] Inserir Quadra
     *
     * @author Raphael Rossiter
     * @date 06/04/2009
     *
     * @param helper
     * @return Quadra
     * @throws ControladorException
     */
    public Quadra validarQuadra(InserirQuadraHelper helper) throws ControladorException;
    
    /**
     * Integração com o conceito de face da quadra
     *
     * @author Raphael Rossiter
     * @date 21/05/2009
     *
     * @param idImovel
     * @return IntegracaoQuadraFaceHelper
     * @throws ControladorException
     */
    public IntegracaoQuadraFaceHelper integracaoQuadraFace(Integer idImovel) throws ControladorException;

	/**
	 * [UC0928] - Manter Situação Especial de Faturamento
	 * [FS0004] - Verificar Existência da Quadra
	 * 
	 * Pesquisa uma coleção de quadra com filtrando também pelo setor comercial.
	 * 
	 */
	public Quadra obterQuadraSetorComercial(int idSetor, int numeroQuadra) throws ControladorException;
	
	/**
	 * Pesquisa os ids de Todas as localidaes.
	 *
	 * @author Hugo Leonardo
	 * @date 08/07/2010
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidades() throws ControladorException;

}

