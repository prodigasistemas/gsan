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
package gcom.micromedicao;

import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelCobrarDoacaoHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.micromedicao.FiltrarRelatorioAnormalidadeLeituraPeriodoHelper;
import gcom.relatorio.micromedicao.RelatorioAnormalidadeLeituraPeriodoBean;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * < <Descrição da Interface>>
 * 
 * @author Administrador
 * @created 13 de Setembro de 2005
 */
public interface ControladorMicromedicaoRemote extends javax.ejb.EJBObject {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param sistemaParametro
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void consistirLeiturasCalcularConsumos(
			FaturamentoGrupo faturamentoGrupo, SistemaParametro sistemaParametro)
			throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param sistemaParametro
	 *            Descrição do parâmetro
	 * @param medicaoTipo
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public int[] obterConsumoMedioHidrometro(Imovel imovel,
			SistemaParametro sistemaParametro, MedicaoTipo medicaoTipo)
			throws RemoteException;

	/**
	 * Description of the Method
	 * 
	 * @param hidrometro
	 *            Description of the Parameter
	 * @exception RemoteException
	 *                Description of the Exception
	 */
	public void atualizarHidrometro(Hidrometro hidrometro)
			throws RemoteException;

	/**
	 * Description of the Method
	 * 
	 * @param hidrometros
	 *            Description of the Parameter
	 * @param hidrometroAtualizado
	 *            Description of the Parameter
	 * @exception RemoteException
	 *                Description of the Exception
	 */
	public void atualizarConjuntoHidrometro(Collection hidrometros,
			Hidrometro hidrometroAtualizado) throws RemoteException;
	
	
	/*
	 * [UC0121] - Filtrar Exceções de Leituras e Consumos
	 * Flávio Leonardo Cavalcanti Cordeiro
	 */
	public Collection filtrarExcecoesLeiturasConsumos(FiltroImovelSubCategoria filtroImovelSubCategoria, 
			FiltroConsumoHistorico filtroConsumoHistorico, 
			FiltroMedicaoHistorico filtroMedicaoHistorico, String qtdEconomias, String consumoMedioMinimo)
			throws RemoteException;
	
	/**
	 * Permite pesquisar imóvel doação baseando-se em rotas
	 * [UC0394] Gerar Débitos a Cobrar de Doações
	 * @author  César Araújo
	 * @date    05/08/2006
	 * @param   Collection<Rota> rotas - Coleção de rotas
	 * @return  Collection<ImovelCobrarDoacaoHelper> - Coleção de ImovelCobrarDoacaoHelper 
	 *          já com as informações necessárias para registro da cobrança
	 * @throws  ErroRepositorioException
	 * @throws ControladorException 
	**/ 
	public Collection<ImovelCobrarDoacaoHelper> pesquisarImovelDoacaoPorRota(Collection<Rota> rotas) throws ControladorException;

	/**
	 *[UC0965] - Relatorio de Anormalidade de Leitura por Periodo
	 *
	 *@since 03/11/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioAnormalidadeLeituraPeriodoBean> pesquisarRelatorioAnormalidadeLeituraPeriodo(FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro) throws ControladorException;

}
