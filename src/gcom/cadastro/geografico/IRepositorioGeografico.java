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
package gcom.cadastro.geografico;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.Filtro;

import java.util.Collection;

/**
 * Interface para o repositório de cliente
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */
public interface IRepositorioGeografico {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param codigoSetorComercial
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarMunicipoPeloSetorComercial(
			String codigoSetorComercial, String idMunicipio)
			throws ErroRepositorioException;

	/**
	 * Método que retorna o maior código do bairro de um município
	 * 
	 * @author Rafael Corrêa
	 * @date 10/07/2006
	 * 
	 * @param idMunicipio
	 * @return
	 * @throws ControladorException
	 */
	
	public int pesquisarMaximoCodigoBairro(Integer idMunicipio)
			throws ErroRepositorioException;

	/**
	 * Pesquisa um município pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Município
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoMunicipioRelatorio(
			Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * Pesquisa um bairro pelo código e pelo id do município
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Bairro
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoBairroRelatorio(Integer codigoBairro,
			Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 26/12/2006
	 * 
	 * @return colecao de BairroArea
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection pesquisarBairroArea(Integer idBairro)
			throws ErroRepositorioException;
	
	/**
	 * Remove todos os BairroArea de um determinado Bairro
	 * 
	 * @author Vivianne Sousa
	 * @date 27/12/200
	 * 
	 * @param idBairro
	 * @exception ErroRepositorioException
	 *              
	 */
	public void removerTodosBairroAreaPorBairro(Integer idBairro)
			throws ErroRepositorioException;
	
	 /**
	 * Método que retorna o maior id do Município
	 * 
	 * @author Rafael Corrêa
	 * @date 24/07/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */   
    
    public int pesquisarMaximoIdMunicipio()
			throws ErroRepositorioException;
    
    /**
	 * Método que retorna o municipio do Imovel
	 * 
	 * @author Hugo Amorim
	 * @date 27/08/2009
	 * 
	 * @return Municipio
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipioDoImovel(Integer idImovel) 
    	throws ErroRepositorioException;
	
    /**
	 * Método reponsável por retornar todos os municípios que possuem alguma
	 * associação com uma localidade (localidade.muni_idprincipal != null)
	 * 
	 * @author Diogo Peixoto
	 * @date 26/04/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */  
    public Collection pesquisarMunicipiosAssociadoLocalidade() throws ErroRepositorioException;
}
