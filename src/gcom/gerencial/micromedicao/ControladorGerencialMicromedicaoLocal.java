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
package gcom.gerencial.micromedicao;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.relatorio.gerencial.micromedicao.FiltrarRelatorioResumoDistritoOperacionalHelper;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoDistritoOperacionalHelper;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoZonaAbastecimentoHelper;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.List;

/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface ControladorGerencialMicromedicaoLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadeAgua(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadePoco(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadeConsumo(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeLeitura(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeConsumo() throws ControladorException;

	/**
	 * Gera o resumo das ligações de hidrômetro.
	 * 
	 * [UC0564 - Gerar Resumo das Intalações de Hidrômetros]
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idSetorComercial
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoInstalacoesHidrometros(
			Integer anoMesReferenciaArrecadacao, Integer idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Método que gera o resumo da Leituras Anormalidades
	 * 
	 * [UC0551] - Gerar Resumo da Leitura Anormalidade
	 * 
	 * @author Ivan Sérgio
	 * @date 26/04/2007
	 */
	public void gerarResumoLeituraAnormalidade(int idLocalidade,
			int anoMesLeitura, int idFuncionalidadeIniciada)
			throws ControladorException;

	/**
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetros
	 * 
	 * Pesquisa os ids dos setores comercias dos imóveis que tem hidrometro
	 * instalado no histórico
	 * 
	 * @author Pedro Alexandre
	 * @date 08/05/2007
	 * 
	 * @param anoMesFaturamento
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(
			Integer anoMesFaturamento) throws ControladorException;

	/**
	 * Método que gera o resumo de Hidrometros
	 * 
	 * [UC0586] - Gerar Resumo Hidrometro
	 * 
	 * @author Thiago Tenório
	 * @date 30/04/2007
	 * 
	 */
	public void gerarResumoHidrometros(Integer idHidrometroMarca,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Método que gera resumo indicadores da micromedição
	 * 
	 * [UC0573] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresMicromedicao(int idFuncionalidadeIniciada)
			throws ControladorException;
	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Collection<RelatorioResumoDistritoOperacionalHelper>
	 * 
	 */
	public Collection<RelatorioResumoDistritoOperacionalHelper> pesquisarRelatorioResumoDistritoOperacional(FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ControladorException;
	
	/**
	 * 
	 * [UC0892]Consulta total dos registros do Relatorio Resumo Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Innteger
	 * 
	 */
	public Integer pesquisarTotalRegistroRelatorioResumoDistritoOperacional(FiltrarRelatorioResumoDistritoOperacionalHelper helper) throws ControladorException;
	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Zona Abastecimento
	 * 
	 * @author Hugo Amorim
	 * @date 23/04/2009
	 * 
	 * @return Collection<RelatorioResumoDistritoOperacionalHelper>
	 * 
	 */
	public Collection<RelatorioResumoZonaAbastecimentoHelper> pesquisarRelatorioResumoZonaAbastecimento(FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ControladorException;
	
	/**
	 * Método que gera resumo indicadores da micromedição
	 * 
	 * @author Fernando Fontelles
	 * @date 17/05/2010
	 * 
	 */
	public void gerarResumoIndicadoresMicromedicaoPorAno(int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Gera o resumo das ligações de hidrômetro por ano.
	 * 
	 * Gerar Resumo das Instalações de Hidrômetros Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idSetorComercial
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoInstalacoesHidrometrosPorAno(
			Integer anoMesReferenciaFaturamento, Integer idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException;
}	
