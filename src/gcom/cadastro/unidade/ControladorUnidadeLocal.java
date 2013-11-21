/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cadastro.unidade;

import java.util.Collection;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;


public interface ControladorUnidadeLocal extends javax.ejb.EJBLocalObject {
	
	
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
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeOrganizacionalAberturaRAAtivoUsuario(String loginUsuario)
		throws ControladorException ;

	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a unidade de atendimento não tenha autorização para efetuar a abertura de registros de atendimento 
	 * (UNID_ICABERTURARA com o valor correspondente a dois na tabela UNIDADE_ORGANIZACIONAL com UNID_ID=Id da 
	 * Unidade de Atendimento), exibir a mensagem “A unidade <<UNID_NMUNIDADE da tabela UNIDADE_ORGANIZACIONAL>> 
	 * não tem autorização para efetuar a abertura de registro de atendimento” e retornar para o passo 
	 * correspondente no fluxo principal.
	 * 
	 * [FS0004] - Verificar existência da unidade de atendimento
	 * 
	 * [FS0033] - Verificar autorização da unidade de atendimento para abertura de registro de atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 * 
	 * @param idUnidadeOrganizacional, levantarExceptionUnidadeInexistente
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificarAutorizacaoUnidadeAberturaRA(Integer idUnidadeOrganizacional, 
			boolean levantarExceptionUnidadeInexistente)
			throws ControladorException;
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a unidade destino definida esteja vinculada a uma unidade centralizadora (UNID_IDCENTRALIZADORA com o 
	 * valor diferente de nulo na tabela UNIDADE_ORGANIZACIONAL para UNID_ID=Id da unidade destino), 
	 * definir a unidade destino a partir da unidade centralizadora (UNID_ID e UNID_DSUNIDADE da tabela 
	 * UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e UNID_ID=UNID_IDCENTRALIZADORA).
	 * 
	 * [FS0018] - Verificar existência de unidade centralizadora
	 * 
	 * @author Raphael Rossiter
	 * @date 26/07/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificarExistenciaUnidadeCentralizadora(UnidadeOrganizacional unidadeOrganizacional)
		throws ControladorException;
	
	/**
	 * [UC0373] Inserir Unidade Organizacional
	 * 
	 * Metodo inserção da unidade organizacional
	 * 
	 * [FS0001] - Verificar existência de unidade centralizadora
	 * 
	 * @author Raphael Pinto
	 * @date 31/07/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Object
	 * @throws ControladorException
	 */
	public Object inserirUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional, Usuario usuario)
		throws ControladorException;
	
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
	public Collection<UnidadeOrganizacional> recuperarUnidadesSubordinadasPorUnidadeSuperior(UnidadeOrganizacional unidadeOrganizacional) throws ControladorException;	
	
    /**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * [FS007] Verificar existência de unidades subordinadas
	 * 
	 * @author Leonardo Regis
	 * @date 05/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @throws ControladorException
	 */
	public void verificarExistenciaUnidadesSubordinadas(UnidadeOrganizacional unidadeOrganizacional) throws ControladorException;
	
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
    public UnidadeOrganizacional recuperaUnidadeAtualPorRA(RegistroAtendimento registroAtendimento) throws ControladorException;
    
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
	 * @param idUnidadeOrganizacional
	 * @return void
	 * @throws ControladorException
	 */
	public void verificaPossibilidadeEncaminhamentoUnidadeDestino(Integer idUnidadeDestino) throws ControladorException ;
	
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */	
	public Collection<UnidadeOrganizacional> pesquisarUnidadeOrganizacionalPorUnidade(Integer unidadeLotacao)
		throws ControladorException;
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */	
	public Collection<UnidadeOrganizacional> pesquisarUnidadeOrganizacionalPorRA(Collection<Integer> idsRa)
		throws ControladorException;
	
	/**
	 * Pesquisa a Unidade Organizacional do Usuário Logado
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2006
	 * 
	 * @param id
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
    public UnidadeOrganizacional pesquisarUnidadeUsuario(Integer idUsuario)
		throws ControladorException;
    
	/**
	 * [UC0375] Manter Unidade Organizacional
	 * 
	 * @author Ana Maria
	 * @date 24/11/2006
	 * 
	 * @param unidadeOrganizacional
	 * @throws ControladorException
	 */
    public void atualizarUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional, Usuario usuario)
		throws ControladorException;   
    
	/**
	 * [UC0375] Manter Unidade Organizacional
	 * 
	 * @author Ana Maria
	 * @date 28/11/2006
	 * 
	 * @param unidadeOrganizacional
	 * @throws ControladorException
	 */
    public UnidadeOrganizacional pesquisarUnidadeOrganizacional(Integer idUnidadeOrganizacional)
		throws ControladorException;    
    
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
	public Collection pesquisarUnidadeOrganizacionalFiltro(
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional, Integer numeroPagina)throws ControladorException;
	
	public Integer pesquisarUnidadeOrganizacionalFiltroCount(FiltroUnidadeOrganizacional filtroUnidadeOrganizacional)
			throws ControladorException;
	
	/**
	 * Pesquisar unidade organizacional por localidade
	 * 
	 * @author Sávio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idUnidade
	 * @return String
	 * @throws ControladorException
	 */
    public UnidadeOrganizacional pesquisarUnidadeOrganizacionalLocalidade(Integer idLocalidade)
		throws ControladorException;

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobrança por Empresa
	 * 
	 * @author Mariana Victor
	 * @date 14/04/2011
	 */	
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalPorImovel(Integer idImovel)
		throws ControladorException;
}
