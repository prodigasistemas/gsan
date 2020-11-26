package gcom.seguranca.acesso.usuario;

import gcom.api.servicosOperacionais.DTO.UsuarioDTO;
import gcom.seguranca.acesso.Operacao;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * Declaração pública de serviços do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorUsuarioLocal extends javax.ejb.EJBLocalObject {

	/**
	 * Inseri um usuario com seus grupos
	 * 
	 * [UC0230]Inserir Usuario
	 * 
	 * @author Thiago Toscano
	 * @date 19/05/2006
	 * 
	 * @param usuario
	 * @param idGrupo
	 *            grupos que o usuario faz parte
	 * @throws ControladorException
	 */
	public void inserirUsuario(Usuario usuario, Integer[] idGrupos, Usuario usuarioLogado, 
			String idSolicitacaoAcesso)
			throws ControladorException;

	/**
	 * Atualiza um usuario com seus grupos
	 * 
	 * [UC0231]Inserir Usuario
	 * 
	 * @author Thiago Toscano
	 * @date 19/05/2006
	 * 
	 * @param usuario
	 * @param idGrupo
	 *            grupos que o usuario faz parte
	 * @throws ControladorException
	 */
	 public void atualizarUsuario(Usuario usuario, Integer[] idGrupos, String processo, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0291] Bloquear/Desbloquear Acesso Usuario
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/06/2006
	 * 
	 * @param usuario
	 * @throws ControladorException
	 */

	public void bloquearDesbloquearUsuarioSituacao(Usuario usuario)
			throws ControladorException;

	/**
	 * Método que consulta os grupos do usuário
	 * 
	 * @author Sávio Luiz
	 * @date 27/06/2006
	 */
	public Collection pesquisarGruposUsuario(Integer idUsuario)
			throws ControladorException;

	/**
	 * Método que consulta as abrangências dos usuário pelos os ids das
	 * abrangências superiores e com o id da abrangência diferente do id da
	 * abrangência do usuário que está inserindo(usuário logado)
	 * 
	 * @author Sávio Luiz
	 * @date 28/06/2006
	 */
	public Collection pesquisarUsuarioAbrangenciaPorSuperior(
			Collection colecaoUsuarioAbrangencia,
			Integer idUsuarioAbrangenciaLogado) throws ControladorException;

	/**
	 * Informa o número total de registros de usuario grupo, auxiliando o
	 * esquema de paginação
	 * 
	 * @author Sávio Luiz
	 * @date 30/06/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return Número de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public int totalRegistrosPesquisaUsuarioGrupo(
			FiltroUsuarioGrupo filtroUsuarioGrupo) throws ControladorException;

	/**
	 * Informa o número total de registros de usuario grupo, auxiliando o
	 * esquema de paginação
	 * 
	 * @author Sávio Luiz
	 * @date 30/06/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return Número de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public Collection pesquisarUsuariosDosGruposUsuarios(
			FiltroUsuarioGrupo filtroUsuarioGrupo, Integer numeroPagina)
			throws ControladorException;

	/**
	 * Remove usuario(s)
	 * 
	 * [UC0231] Manter Usuario
	 * 
	 * @author Sávio Luiz
	 * @date 07/07/2006
	 * @param idsUsuario
	 * @param usuario
	 * @throws ControladorException
	 */
	public void removerUsuario(String[] idsUsuario, Usuario usuario, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Método que consulta os grupos funcionários operações passando os ids dos
	 * grupos
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */

	public Collection pesquisarGruposFuncionalidadeOperacoes(Integer[] idsGrupos)
			throws ControladorException;

	/**
	 * Método que consulta os grupos funcionários operações passando os ids dos
	 * grupos e o id da funcionalidade
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarGruposFuncionalidadesOperacoesPelaFuncionalidade(
			Integer[] idsGrupos, Integer idFuncionalidade)
			throws ControladorException;

	/**
	 * Método que consulta os usuários restrinção passando os ids dos grupos , o
	 * id da funcionalidade e o id do usuário
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarUsuarioRestrincao(Integer[] idsGrupos,
			Integer idFuncionalidade, Integer idUsuario)
			throws ControladorException;

	/**
	 * Método que consulta as funcionalidades da(s) funcionalidade(s)
	 * princpial(is)
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarFuncionanidadesDependencia(
			Collection idsFuncionalidades) throws ControladorException;

	/**
	 * Método que consulta as operações da(s) funcionalidade(s)
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarOperacoes(Collection idsFuncionalidades)
			throws ControladorException;

	/**
	 * Método que consulta as operações da(s) funcionalidade(s) e das
	 * funcionalidades dependencia
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection recuperarOperacoesFuncionalidadesEDependentes(
			Integer idFuncionalidade) throws ControladorException;

	/**
	 * Retorna 2 coleções e um array ,com os valores que vão retornar
	 * marcados,uma com as permissões do usuário que ele possa marcar/desmarcar
	 * e a outra o usuário logado não vai poder marcar/desmarcar
	 * 
	 * [UC0231] - Manter Usuário [SB0010] - Selecionar Permissões Especiais
	 * (n°2)
	 * 
	 * @author Sávio Luiz
	 * @date 13/07/2006
	 */
	public Object[] pesquisarPermissoesEspeciaisUsuarioEUsuarioLogado(
			Usuario usuarioAtualizar, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Retorna um array com os ids dos objetos da coleção
	 * 
	 * @author Sávio Luiz
	 * @date 13/07/2006
	 */
	public String[] retornarPermissoesMarcadas(Collection permissoesEspeciais);

	/**
	 * Método que atualiza o controle de acesso do usuário
	 * 
	 * [UC0231] - Manter Usuário
	 * 
	 * @author Sávio Luiz
	 * @date 14/07/2006
	 * 
	 * @param String[]
	 * @param grupoFuncionalidadeOperacao
	 */
	public void atualizarControleAcessoUsuario(String[] permissoesEspeciais,
			Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadesMap,
			Usuario usuarioAtualizar, Integer[] idsGrupos,
			String permissoesCheckBoxVazias, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Retorna um map com o indicador dizendo se vai aparecer
	 * marcado(1),desmarcado(2) ou desabilitado(3) para cada operação da
	 * funcionalidade escolhida
	 * 
	 * [UC0231] - Manter Usuário [SB0008] - Selecionar Restrinções (n°2)
	 * 
	 * @author Sávio Luiz
	 * @date 17/07/2006
	 */
	public Map<Integer, Map<Integer, Collection<Operacao>>> organizarOperacoesComValor(
			Integer codigoFuncionalidade,
			Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadesMap, Integer[] idsGrupos,
			Usuario usuarioAtualizar) throws ControladorException;

	/**
	 * Retorna um map com o indicador dizendo se vai aparecer
	 * marcado(1),desmarcado(2) ou desabilitado(3) para cada operação da
	 * funcionalidade escolhida e a coleção com as operações e funcionalidades
	 * que que foram desmarcados
	 * 
	 * [UC0231] - Manter Usuário [SB0008] - Selecionar Restrinções (n°2)
	 * 
	 * @author Sávio Luiz
	 * @date 17/07/2006
	 */
	public Map<Integer, Map<Integer, Collection<Operacao>>> recuperaFuncionalidadeOperacaoRestrincao(
			Integer codigoFuncionalidade, String[] idsOperacoes,
			Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadesMap)
			throws ControladorException;
	
	/**
	 * Método que consulta os grupos do usuário da tabela grupoAcessos
	 * 
	 * @author Sávio Luiz
	 * @date 21/02/2007
	 */
	public Collection pesquisarGruposUsuarioAcesso(Collection colecaoUsuarioGrupos)throws ControladorException;
	
	public UsuarioDTO pesquisarUsuario(Integer idUsuario) throws ControladorException;
	
	/**
	* Método que consulta o nome do usuário de uma guia de devolução,
	* passando por parâmetro o id da guia de devolucao
	*
	* @author Daniel Alves
	* @date 22/02/2010
	*/
	public String pesquisarUsuarioPorGuiaDevolucao(Integer idGuiaDevolucao)throws ControladorException;
	
	
	/**
	 * [UC0204] Consultar Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/11/2010
	 */
	public Collection pesquisarUsuario(Integer idOperacao,
			Integer idImovel,String referenciaConta)throws ControladorException;
	/**
	 * [UC0146] Manter Conta
	 * [SB0012] – Determinar competência de retificação de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public Collection pesquisarGrupoUsuario(Integer idUsuario)throws ControladorException;


	/**
	 * [UC0146] Manter Conta
	 * [SB0012] – Determinar competência de retificação de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public BigDecimal pesquisarMaiorCompetenciaRetificacaoGrupo()throws ControladorException;
	
	/**
	 * [UC0230] Inserir Usuário
	 * [FS0020] Verificar existência de usuário batch
	 * [FS0021] Verificar usuário batch
	 * 
	 * @author Paulo Diniz
	 * @throws ControladorException 
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioRotinaBatch() throws ControladorException;	
	
	/**
	 * [UC0230] Inserir Usuário
	 * [FS0022] Verificar existência de usuário internet
	 * [FS0023] Verificar usuário internet
	 * 
	 * @author Paulo Diniz
	 * @throws ControladorException 
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioInternet() throws ControladorException;
	

	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * Filtra os Usuarios por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ControladorException 
	 */
	public Collection filtrarAutocompleteUsuario(String valor)throws ControladorException;
}
