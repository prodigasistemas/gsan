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
package gcom.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.util.Collection;

/**
 * Declaração pública de serviços do Session Bean de ControladorLigacaoAgua
 * 
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public interface ControladorLigacaoAguaLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC0463] Atualizar Consumo Mínimo de Ligação Água
	 * 
	 * Este método se destina a validar todas as situações e particularidades
	 * da atualização do consumo mínimo da ligação de agua
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param OrdemServico
	 * @param veioEncerrarOS 
	 */	
	public void validarExibirAtualizarConsumoMinimoLigacaoAgua(OrdemServico ordemServico, boolean veioEncerrarOS) 
		throws ControladorException;
	
	/**
	 * [UC0463] Atualizar Consumo Mínimo de Ligação de Água
	 * 
	 * [FS004] Validar Consumo Mínimo
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param imovel
	 */
	public void validarConsumoMinimoLigacaoAgua(Imovel imovel) throws ControladorException;
	
	/**
	 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
	 * 
	 * [SB0001] Atualizar Ligação de Água.
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param ligacaoAgua
	 * @throws ControladorException
	 */
	public void atualizarConsumoMinimoLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)	throws ControladorException;
	
	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades
	 * do corte ligação de agua
	 * 
	 * @author Rafael Pinto
	 * @date 25/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirCorteLigacaoAgua(OrdemServico ordemServico,boolean veioEncerrarOS) 
		throws ControladorException;
	
	/**
	 * [UC0355] Efetuar Corte de Ligação de Água.
	 * 
	 * @author Leonardo Regis.
	 * @date 25/09/2006
	 * 
	 * @param helper
	 * @throws ControladorException
	 */
	public void efetuarCorteLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException;
	

	/**
	 * [UC0365] Efetuar Corte Administrativo da Ligação Agua
	 * 
	 * @author Thiago Tenório
	 * @date 30/06/2006
	 * 
	 * @param helper
	 * @throws ControladorException
	 */
	public void efetuarCorteAdministrativoLigacaoAgua(DadosEfetuacaoCorteLigacaoAguaHelper helper, Usuario usuario) throws ControladorException;
	
	
	/**
	 * [UC0463] Efetuar Restabelecimento da Ligação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água
	 * 
	 * @author Flávio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaRestabelecimento(LigacaoAgua ligacaoAgua)throws ControladorException;
	
	/**
	 * [UC0357] Efetuar Religação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água
	 * 
	 * @author Flávio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaReligacao(LigacaoAgua ligacaoAgua)throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera os parámetros necessários da Ligacao de água
	 * (id,dataCorte,dataSupressao)
	 * 
	 * @author Sávio Luiz
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public LigacaoAgua recuperaParametrosLigacaoAgua(Integer idImovel)
			throws ControladorException;
	
	/**
	 * 
	 * Pesquisa o id do hidrometro
	 * 
	 * @author Sávio Luiz
	 * @date 19/02/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdHidrometroInstalacaoHistorico(Integer idImovel)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados da Tarifa Social 
	 * 
	 * Recupera o consumo mínimo fixado do Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 04/0/2006
	 * 
	 * @param idImovel
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarConsumoMinimoFixado(Integer idImovel) throws ControladorException;
	
	public Collection verificaExistenciaLigacaoAgua(Integer idImovel);
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Imóvel]
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoAguaSituacao
	 * @param idConsumoTipo
	 * @return LigacaoAguaSituacaoConsumoTipo
	 * @throws ControladorException
	 */
	public LigacaoAguaSituacaoConsumoTipo pesquisarLigacaoAguaSituacaoConsumoTipo(Integer idLigacaoAguaSituacao,
			Integer idConsumoTipo) throws ControladorException;
		
	/**
	 * 
	 * Atualiza o tipo de corte
	 * 
	 * Autor: Hugo Amorim
	 * 
	 * Data: 18/05/2009
	 */
	public void atualizarTipoCorte(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException;
	
	/**
	 * TODO: COSANPA
	 * 
	 * @autor: Wellington Rocha
	 * @date: 21/03/2012
	 * 
	 *        Pesquisar Situações de Ligação de Água ativas.
	 * 
	 *        Geração de rotas para recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 * 
	 */
	public Collection<LigacaoAguaSituacao> pesquisarLigacaoAguaSituacao()
			throws ControladorException;
}