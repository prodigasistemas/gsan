/**
 * 
 */
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
package gcom.cadastro.localidade;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * 
 * Title: GCOM
 * 
 * Description: Interface do Repositório de Localidade
 * 
 * Copyright: Copyright (c) 2005
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Pedro Alexandre
 * @created 13 de Janeiro de 2006
 * @version 1.0
 */
public interface IRepositorioLocalidade {

    /**
	 * Pesquisa uma coleção de localidades por gerência regional
	 * 
	 * @param idGerenciaRegional
	 *            Código da gerência regional solicitada
	 * @return Coleção de Localidades da Gerência Regional solicitada
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
    public Collection<Localidade> pesquisarLocalidadePorGerenciaRegional(int idGerenciaRegional) throws ErroRepositorioException;
    
    /**
	 * Obtém o id da localidade
	 * 
	 * @author Sávio Luiz
	 * @date 08/03/2006
	 * 
	 * @param idImovel
	 * @return Um integer que representa o id da localidade
	 * @throws ControladorException
	 */
	public Integer pesquisarIdLocalidade(Integer idImovel) throws ErroRepositorioException;

	 /**
	 * Método que retorna o maior id da Localidade
	 * 
	 * @author Vivianne Sousa
	 * @date 12/07/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */   
    public int pesquisarMaximoIdLocalidade() throws ErroRepositorioException;
    
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
	public Object[] pesquisarObjetoLocalidadeRelatorio(
			Integer idLocalidade) throws ErroRepositorioException;
	
	public Integer verificarExistenciaLocalidade(
			Integer idLocalidade) throws ErroRepositorioException;
	
	
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
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
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
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;
	
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
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
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
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;
	
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
	public Collection pesquisarTodosIdLocalidade() throws ErroRepositorioException ;
	
	/**
	 * Obtém Elo Pólo
	 * @author Ana Maria
	 * @date 10/12/2007
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarEloPolo()throws ErroRepositorioException;	
	
	
	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores
	 * 
	 * @author Raphael Rossiter
	 * @date 09/01/2008
	 *
	 * @param idConta
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorConta(Integer idConta)throws ErroRepositorioException ;
	
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 11/01/2008
	 *
	 * @param idGuiaPagamento
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorGuiaPagamento(Integer idGuiaPagamento)throws ErroRepositorioException ;
	
	/**
	 * 
	 * Pesquisar as localidades do município
	 * 
	 * @author Sávio Luiz
	 * @date 25/02/2008
	 * 
	 */

	public Collection pesquisarLocalidadesMunicipio(Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * Pesquisa os ids das localidades que possuem imóveis.
	 *
	 * @author Pedro Alexandre
	 * @date 07/07/2008
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesImoveis()throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 26/11/2008
	 *
	 * @param idDebitoACobrar
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorDebitoACobrar(Integer idDebitoACobrar)throws ErroRepositorioException ;
	
	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores
	 * 
	 * @author Raphael Rossiter
	 * @date 09/01/2008
	 *
	 * @param idConta
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorContaHistorico(Integer idContaHistorico)throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 11/01/2008
	 *
	 * @param idGuiaPagamento
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorGuiaPagamentoHistorico(Integer idGuiaPagamentoHistorico)throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 26/11/2008
	 *
	 * @param idDebitoACobrar
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorDebitoACobrarHistorico(Integer idDebitoACobrarHistorico)throws ErroRepositorioException ;

	/**
	 * Pesquisa os ids de Todas as localidaes.
	 *
	 * @author Hugo Leonardo
	 * @date 08/07/2010
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarIdsLocalidades() throws ErroRepositorioException;
	
}
