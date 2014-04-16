package gcom.cadastro.unidade;

import java.util.Collection;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;


public interface IRepositorioUnidade {

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Obtém a unidade associada ao usuário que estiver efetuando o registro de atendimento 
	 * (UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com 
	 * UNID_ID=(UNID_ID da tabela USUARIO com USUR_NMLOGIN=
	 * Login do usuário que estiver efetuando o registro de atendimento) e UNID_ICABERTURARA=1)
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 * 
	 * @param login
	 * @return UnidadeOrganizacional
	 * @throws ErroRepositorioException
	 */
    public UnidadeOrganizacional obterUnidadeOrganizacionalAberturaRAAtivoUsuario(String loginUsuario)
		throws ErroRepositorioException ;
    
    /**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 05/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Collection<UnidadeOrganizacional> unidades subordinadas
	 * @throws ErroRepositorioException
	 */
	public Collection<UnidadeOrganizacional> recuperarUnidadesSubordinadasPorUnidadeSuperior(UnidadeOrganizacional unidadeOrganizacional) throws ErroRepositorioException;

    /**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * [FS007] Verificar existência de unidades subordinadas
	 * 
	 * @author Leonardo Regis
	 * @date 05/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return qtde unidades subordinadas
	 * @throws ErroRepositorioException
	 */
	public int consultarTotalUnidadesSubordinadasPorUnidadeSuperior(UnidadeOrganizacional unidadeOrganizacional) throws ErroRepositorioException;
	
    /**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 *  Caso exista registro de atendimento que estão na unidade atual informada 
	 *  (existe ocorrência na tabela REGISTRO_ATENDIMENTO com TRAMITE=Id da Unidade Atual 
	 *  e maior TRAM_TMTRAMITE)
	 *  
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return RegistroAtendimento
	 * @throws ControladorException
	 */
    public UnidadeOrganizacional recuperaUnidadeAtualPorRA(RegistroAtendimento registroAtendimento) throws ErroRepositorioException;
    
	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * Caso a unidade destino informada não possa receber registros de
	 * atendimento (UNID_ICTRAMITE=2 na tabela UNIDADE_ORGANIZACIONAL com
	 * UNID_ID=Id da unidade destino informada).
	 * 
	 * [FS0013] - Verificar possibilidade de encaminhamento para a unidade
	 * destino
	 * 
	 * @author Ana Maria
	 * @date 03/09/2006
	 * 
	 * @return idUnidadeDestino
	 * @throws ErroRepositorioException
	 */
	public Short verificaTramiteUnidade(Integer idUnidadeDestino) throws ErroRepositorioException;
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */	
	public Collection<UnidadeOrganizacional> pesquisarUnidadeOrganizacionalPorRA(Collection<Integer> idsRa)
		throws ErroRepositorioException;
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */	
	public Collection<UnidadeOrganizacional> pesquisarUnidadeOrganizacionalPorUnidade(Integer unidadeLotacao)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa a Unidade Organizacional do Usuário Logado
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2006
	 * 
	 * @param id
	 * @return UnidadeOrganizacional
	 * @throws ErroRepositorioException
	 */
    public UnidadeOrganizacional pesquisarUnidadeUsuario(Integer idUsuario)
		throws ErroRepositorioException;
    
	/**
	 * [UC0375] Manter Unidade Organizacional
	 * 
	 * @author Ana Maria
	 * @date 24/11/2006
	 * 
	 * @param unidadeOrganizacional
	 * @throws ErroRepositorioException
	 */
    public void atualizarUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional)
		throws ErroRepositorioException;    
    
    /**
	 * Verificar se a unidade organizacional está associada 
	 * a uma divisão de esgoto
	 * 
	 * @author Ana Maria
	 * @date 27/11/2006
	 * 
	 * @param idUnidade
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
    public String verificarUnidadeEsgoto(Integer idUnidade)
		throws ErroRepositorioException;    
    
    /**
	 * Verificar se a unidade organizacional está associada 
	 * a uma especificação de solicitação
	 * 
	 * @author Ana Maria
	 * @date 28/11/2006
	 * 
	 * @param idUnidade
	 * @return String
	 * @throws ErroRepositorioException
	 */
    public String verificarUnidadeTramitacao(Integer idUnidade)
		throws ErroRepositorioException; 
    
    /**
	 * Pesquisar unidade organizacional
	 * 
	 * @author Ana Maria
	 * @date 28/11/2006
	 * 
	 * @param idUnidade
	 * @return String
	 * @throws ErroRepositorioException
	 */
    public UnidadeOrganizacional pesquisarUnidadeOrganizacional(Integer idUnidade)
		throws ErroRepositorioException;    
    
	/**
	 * [UC0374] Filtrar Unidade Organizacional
	 * 
	 * Pesquisa as unidades organizacionais com os condicionais informados
	 * filtroUnidadeOrganizacional
	 * 
	 * @author Ana Maria
	 * @date 30/11/2006
	 * 
	 * @param filtro
	 * @return Collection
	 */
	public Collection pesquisarUnidadeOrganizacionalFiltro(FiltroUnidadeOrganizacional 
			filtroUnidadeOrganizacional, Integer numeroPagina) throws ErroRepositorioException;
	
	public Integer pesquisarUnidadeOrganizacionalFiltroCount(FiltroUnidadeOrganizacional 
			filtroUnidadeOrganizacional) throws ErroRepositorioException;
	
	/**
	 * Pesquisar unidade organizacional por localidade
	 * 
	 * @author Sávio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idUnidade
	 * @return String
	 * @throws ErroRepositorioException
	 */
    public UnidadeOrganizacional pesquisarUnidadeOrganizacionalLocalidade(Integer idLocalidade)
		throws ErroRepositorioException;
    
    
    /**
     * Pesquisa o id da unidade negocio para a qual a localidade pertence.
     * 
     * [UC0267] Encerrar Arrecadação do Mês
     * 
     * @author Raphael Rossiter
     * @date 30/05/2007
     * 
     * @param idLocalidade
     * @throws ErroRepositorioException
     */
    public Integer pesquisarIdUnidadeNegocioParaLocalidade(Integer idLocalidade) throws ErroRepositorioException ;

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobrança por Empresa
	 * 
	 * @author Mariana Victor
	 * @date 14/04/2011
	 */	
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalPorImovel(Integer idImovel)
		throws ErroRepositorioException;
}
