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
package gcom.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Declaração pública de serviços do Session Bean de ControladorLigacaoEsgoto
 * 
 * @author Leonardo Regis
 * @date 08/09/2006
 */
public interface ControladorLigacaoEsgotoLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC0367]Atualizar Ligação de Esgoto no sistema.
	 * 
	 * [SB002] Atualiza ligação de esgoto.
	 * 
	 * @author Leonardo Regis
	 * @date 17/07/2006
	 * 
	 * @param ligacaoEsgoto
	 *            a ser atualizado
	 * 
	 * @throws ControladorException
	 */
	public void atualizarLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto, Usuario usuario)
			throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * ligação de esgoto do imóvel no momento da exibição.
	 * 
	 * [FS0001] Verificar existência da matrícula do imóvel. [FS0002] Verificar
	 * Situação do Imovel. [FS0003] Validar Situação de Esgoto do imóvel.
	 * [FS0006] Validar Percentual de Coleta. [FS0007] Validar Volume Mínimo
	 * Fixado.
	 * 
	 * @author Leonardo Regis
	 * @date 20/07/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ControladorException
	 */
	public void validarLigacaoEsgotoImovelExibir(OrdemServico ordem,boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * ligação de esgoto do imóvel no momento da atualização.
	 * 
	 * [FS0001] Verificar existência da matrícula do imóvel. [FS0002] Verificar
	 * Situação do Imovel. [FS0003] Validar Situação de Esgoto do imóvel.
	 * [FS0006] Validar Percentual de Coleta. [FS0007] Validar Volume Mínimo
	 * Fixado.
	 * 
	 * @author Leonardo Regis
	 * @date 20/07/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ControladorException
	 */
	public void validarLigacaoEsgotoImovelAtualizar(Imovel imovel)
			throws ControladorException;
	
	/**
	 * [UC0464] Atualizar Volume Mínimo de Ligação de Esgoto
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * atualização do volume mínimo da ligação de esgoto
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param OrdemServico
	 * @param veioEncerrarOS
	 * @throws ControladorException
	 */
	public void validarExibirAtualizarVolumeMinimoLigacaoEsgoto(OrdemServico ordemServico, boolean veioEncerrarOS) throws ControladorException;
	
	/**
	 * [UC0464] Atualizar Volume Mínimo de Ligação de Esgoto
	 * 
	 * [FS004] Validar Volume Mínimo
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarVolumeMinimoLigacaoEsgoto(Imovel imovel) throws ControladorException;
	
	/**
	 * [UC0464] Atualizar Volume Mínimo da Ligação de Esgoto
	 * 
	 * [SB0001] Atualizar Ligação de Esgoto.
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ControladorException
	 */
	public void atualizarVolumeMinimoLigacaoEsgoto(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0004] - Calcular Valor de Água e/ou Esgoto
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public BigDecimal recuperarPercentualEsgoto(Integer idLigacaoEsgoto) throws ControladorException;
	
	/**
	 * [UC0349] Emitir Documento de Cobrança - Ordem de Fiscalização
	 * 
	 * @author Sávio Luiz
	 * @date 21/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer recuperarConsumoMinimoEsgoto(Integer idImovel)
	throws ControladorException;
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Imóvel] 
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoEsgotoSituacao
	 * @param idConsumoTipo
	 * @return LigacaoEsgotoSituacaoConsumoTipo
	 * @throws ControladorException
	 */
	public LigacaoEsgotoSituacaoConsumoTipo pesquisarLigacaoEsgotoSituacaoConsumoTipo(Integer idLigacaoEsgotoSituacao,
			Integer idConsumoTipo) throws ControladorException;
	
	/**
	 * TODO: COSANPA
	 * 
	 * @autor: Wellington Rocha
	 * @date: 21/03/2012
	 * 
	 *        Pesquisar Situações de Ligação de Esgoto ativas.
	 * 
	 *        Geração de rotas para recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 * 
	 */
	public Collection<LigacaoEsgotoSituacao> pesquisarLigacaoEsgotoSituacao()
			throws ControladorException;
}