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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.VolumesFaturadosRelatorioHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de volumes faturados
 * 
 * @author Rafael Corrêa
 * @created 12/09/2007
 */
public class RelatorioVolumesFaturadosResumido extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioVolumesFaturadosResumido(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_VOLUMES_FATURADOS_RESUMIDO);
	}

	@Deprecated
	public RelatorioVolumesFaturadosResumido() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Integer idLocalidade = (Integer) getParametro("idLocalidade");
		Integer anoMes = (Integer) getParametro("anoMes");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioVolumesFaturadosBean relatorioBean = null;

		Integer anoMes1 = Util.somaMesAnoMesReferencia(anoMes, 1);
		Integer anoMes2 = Util.somaMesAnoMesReferencia(anoMes, 2);
		Integer anoMes3 = Util.somaMesAnoMesReferencia(anoMes, 3);
		Integer anoMes4 = Util.somaMesAnoMesReferencia(anoMes, 4);
		Integer anoMes5 = Util.somaMesAnoMesReferencia(anoMes, 5);
		Integer anoMes6 = Util.somaMesAnoMesReferencia(anoMes, 6);

		Collection colecaoVolumesFaturadosRelatorioHelper = fachada
				.pesquisarDadosRelatorioVolumesFaturadosResumido(idLocalidade, anoMes,
						anoMes1, anoMes2, anoMes3, anoMes4, anoMes5, anoMes6);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoVolumesFaturadosRelatorioHelper != null
				&& !colecaoVolumesFaturadosRelatorioHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoVolumesFaturadosRelatorioHelperIterator = colecaoVolumesFaturadosRelatorioHelper
					.iterator();

			// Cria as variáveis de totalização
			Integer mediaTotalSetorComercial = new Integer("0");
			Integer mesAno1TotalSetorComercial = new Integer("0");
			Integer mesAno2TotalSetorComercial = new Integer("0");
			Integer mesAno3TotalSetorComercial = new Integer("0");
			Integer mesAno4TotalSetorComercial = new Integer("0");
			Integer mesAno5TotalSetorComercial = new Integer("0");
			Integer mesAno6TotalSetorComercial = new Integer("0");
			Integer mediaTotalLocalidade = new Integer("0");
			Integer mesAno1TotalLocalidade = new Integer("0");
			Integer mesAno2TotalLocalidade = new Integer("0");
			Integer mesAno3TotalLocalidade = new Integer("0");
			Integer mesAno4TotalLocalidade = new Integer("0");
			Integer mesAno5TotalLocalidade = new Integer("0");
			Integer mesAno6TotalLocalidade = new Integer("0");
			
			Integer idSetorComercialAnterior = null;
			Integer idLocalidadeAnterior = null;
			boolean zerarSetorComercial = false;
			boolean zerarLocalidade = false;

			String variacao1TotalSetorComercial = "";
			String variacao1TotalLocalidade = "";
			String variacao2TotalSetorComercial = "";
			String variacao2TotalLocalidade = "";
			String variacao3TotalSetorComercial = "";
			String variacao3TotalLocalidade = "";
			String variacao4TotalSetorComercial = "";
			String variacao4TotalLocalidade = "";
			String variacao5TotalSetorComercial = "";
			String variacao5TotalLocalidade = "";
			String variacao6TotalSetorComercial = "";
			String variacao6TotalLocalidade = "";
			
			// Cria as variáveis para verificar se existe algum dado do mês para cada totalizador
			boolean peloMenosUmDadoMesAno1SetorComercial = false;
			boolean peloMenosUmDadoMesAno2SetorComercial = false;
			boolean peloMenosUmDadoMesAno3SetorComercial = false;
			boolean peloMenosUmDadoMesAno4SetorComercial = false;
			boolean peloMenosUmDadoMesAno5SetorComercial = false;
			boolean peloMenosUmDadoMesAno6SetorComercial = false;
			
			boolean peloMenosUmDadoMesAno1Localidade = false;
			boolean peloMenosUmDadoMesAno2Localidade = false;
			boolean peloMenosUmDadoMesAno3Localidade = false;
			boolean peloMenosUmDadoMesAno4Localidade = false;
			boolean peloMenosUmDadoMesAno5Localidade = false;
			boolean peloMenosUmDadoMesAno6Localidade = false;

			// laço para criar a coleção de parâmetros da analise
			while (colecaoVolumesFaturadosRelatorioHelperIterator.hasNext()) {

				VolumesFaturadosRelatorioHelper volumesFaturadosRelatorioHelper = (VolumesFaturadosRelatorioHelper) colecaoVolumesFaturadosRelatorioHelperIterator
						.next();

				// Seta os valores das variáveis de controle de totalização para
				// verificar quando deve ser zerado os totalizadores
				if (idSetorComercialAnterior == null) {
					idSetorComercialAnterior = volumesFaturadosRelatorioHelper
							.getIdSetorComercial();
				}

				if (idLocalidadeAnterior == null) {
					idLocalidadeAnterior = volumesFaturadosRelatorioHelper
							.getIdLocalidade();
				}

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório

				// Localidade
				String localidade = "";

				if (volumesFaturadosRelatorioHelper.getIdLocalidade() != null) {
					localidade = volumesFaturadosRelatorioHelper
							.getIdLocalidade()
							+ " - "
							+ volumesFaturadosRelatorioHelper
									.getNomeLocalidade();

					// Caso tenha mudado a localidade do imóvel seta a variável
					// para true, para posteriormente zerar todas as variáveis
					// de totalização da localidade
					if (!idLocalidadeAnterior
							.equals(volumesFaturadosRelatorioHelper
									.getIdLocalidade())) {
						zerarLocalidade = true;
					}
				}

				// Setor Comercial
				String idSetorComercial = "";
				String setorComercial = "";

				if (volumesFaturadosRelatorioHelper.getIdSetorComercial() != null) {
					idSetorComercial = volumesFaturadosRelatorioHelper
							.getIdSetorComercial().toString();
					setorComercial = volumesFaturadosRelatorioHelper
							.getCodigoSetorComercial()
							+ " - "
							+ volumesFaturadosRelatorioHelper
									.getNomeSetorComercial();

					// Caso tenha mudado o setor comercial do imóvel seta a
					// variável para true, para posteriormente zerar todas as
					// variáveis de totalização do setor comercial
					if (!idSetorComercialAnterior
							.equals(volumesFaturadosRelatorioHelper
									.getIdSetorComercial())) {
						zerarSetorComercial = true;
					}
				}

				// Quadra
				String numeroQuadra = "";

				if (volumesFaturadosRelatorioHelper.getNumeroQuadra() != null) {
					numeroQuadra = volumesFaturadosRelatorioHelper
							.getNumeroQuadra().toString();
				}
				
				// Zera os totalizadores do setor comercial
				if (zerarSetorComercial) {
					mediaTotalSetorComercial = new Integer("0");
					mesAno1TotalSetorComercial = new Integer("0");
					mesAno2TotalSetorComercial = new Integer("0");
					mesAno3TotalSetorComercial = new Integer("0");
					mesAno4TotalSetorComercial = new Integer("0");
					mesAno5TotalSetorComercial = new Integer("0");
					mesAno6TotalSetorComercial = new Integer("0");
					
					variacao1TotalSetorComercial = "";
					variacao2TotalSetorComercial = "";
					variacao3TotalSetorComercial = "";
					variacao4TotalSetorComercial = "";
					variacao5TotalSetorComercial = "";
					variacao6TotalSetorComercial = "";

					zerarSetorComercial = false;
					
					peloMenosUmDadoMesAno1SetorComercial = false;
					peloMenosUmDadoMesAno2SetorComercial = false;
					peloMenosUmDadoMesAno3SetorComercial = false;
					peloMenosUmDadoMesAno4SetorComercial = false;
					peloMenosUmDadoMesAno5SetorComercial = false;
					peloMenosUmDadoMesAno6SetorComercial = false;
					
					idSetorComercialAnterior = volumesFaturadosRelatorioHelper
							.getIdSetorComercial();
				}

				// Zera os totalizadores da localidade
				if (zerarLocalidade) {
					mediaTotalLocalidade = new Integer("0");
					mesAno1TotalLocalidade = new Integer("0");
					mesAno2TotalLocalidade = new Integer("0");
					mesAno3TotalLocalidade = new Integer("0");
					mesAno4TotalLocalidade = new Integer("0");
					mesAno5TotalLocalidade = new Integer("0");
					mesAno6TotalLocalidade = new Integer("0");

					variacao1TotalLocalidade = "";
					variacao2TotalLocalidade = "";
					variacao3TotalLocalidade = "";
					variacao4TotalLocalidade = "";
					variacao5TotalLocalidade = "";
					variacao6TotalLocalidade = "";

					zerarLocalidade = false;
					
					peloMenosUmDadoMesAno1Localidade = false;
					peloMenosUmDadoMesAno2Localidade = false;
					peloMenosUmDadoMesAno3Localidade = false;
					peloMenosUmDadoMesAno4Localidade = false;
					peloMenosUmDadoMesAno5Localidade = false;
					peloMenosUmDadoMesAno6Localidade = false;
					
					idLocalidadeAnterior = volumesFaturadosRelatorioHelper
							.getIdLocalidade();
				}

				// Média de Consumo
				String media = "";
				Integer mediaConsumo = null;

				if (volumesFaturadosRelatorioHelper.getMediaConsumo() != null) {
					media = volumesFaturadosRelatorioHelper.getMediaConsumo()
							.toString();
					mediaConsumo = volumesFaturadosRelatorioHelper
							.getMediaConsumo();
					mediaTotalSetorComercial = mediaTotalSetorComercial
							+ mediaConsumo;
					mediaTotalLocalidade = mediaTotalLocalidade + mediaConsumo;
				}

				// Consumo e Variação Mês/Ano 1
				String consumoMesAno1 = "";
				String variacao1 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno1() != null) {
					consumoMesAno1 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno1().toString();
					
					peloMenosUmDadoMesAno1SetorComercial = true;
					peloMenosUmDadoMesAno1Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno1TotalSetorComercial = mesAno1TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno1();
					mesAno1TotalLocalidade = mesAno1TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno1();

					// Calcula a variação
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao1 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno1()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a variação dos totalizadores de cada grupo
				if (mesAno1TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao1TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno1TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno1TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao1TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno1TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Variação Mês/Ano 2
				String consumoMesAno2 = "";
				String variacao2 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno2() != null) {
					consumoMesAno2 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno2().toString();
					
					peloMenosUmDadoMesAno2SetorComercial = true;
					peloMenosUmDadoMesAno2Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno2TotalSetorComercial = mesAno2TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno2();
					mesAno2TotalLocalidade = mesAno2TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno2();

					// Calcula a variação
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao2 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno2()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a variação dos totalizadores de cada grupo
				if (mesAno2TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao2TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno2TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno2TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao2TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno2TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Variação Mês/Ano 3
				String consumoMesAno3 = "";
				String variacao3 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno3() != null) {
					consumoMesAno3 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno3().toString();
					
					peloMenosUmDadoMesAno3SetorComercial = true;
					peloMenosUmDadoMesAno3Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno3TotalSetorComercial = mesAno3TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno3();
					mesAno3TotalLocalidade = mesAno3TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno3();

					// Calcula a variação
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao3 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno3()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a variação dos totalizadores de cada grupo
				if (mesAno3TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao3TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno3TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno3TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao3TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno3TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Variação Mês/Ano 4
				String consumoMesAno4 = "";
				String variacao4 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno4() != null) {
					consumoMesAno4 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno4().toString();
					
					peloMenosUmDadoMesAno4SetorComercial = true;
					peloMenosUmDadoMesAno4Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno4TotalSetorComercial = mesAno4TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno4();
					mesAno4TotalLocalidade = mesAno4TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno4();

					// Calcula a variação
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao4 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno4()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a variação dos totalizadores de cada grupo
				if (mesAno4TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao4TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno4TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno4TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao4TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno4TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Variação Mês/Ano 5
				String consumoMesAno5 = "";
				String variacao5 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno5() != null) {
					consumoMesAno5 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno5().toString();
					
					peloMenosUmDadoMesAno5SetorComercial = true;
					peloMenosUmDadoMesAno5Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno5TotalSetorComercial = mesAno5TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno5();
					mesAno5TotalLocalidade = mesAno5TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno5();

					// Calcula a variação
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao5 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno5()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a variação dos totalizadores de cada grupo
				if (mesAno5TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao5TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno5TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno5TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao5TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno5TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Variação Mês/Ano 6
				String consumoMesAno6 = "";
				String variacao6 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno6() != null) {
					consumoMesAno6 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno6().toString();
					
					peloMenosUmDadoMesAno6SetorComercial = true;
					peloMenosUmDadoMesAno6Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno6TotalSetorComercial = mesAno6TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno6();
					mesAno6TotalLocalidade = mesAno6TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno6();

					// Calcula a variação
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao6 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno6()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a variação dos totalizadores de cada grupo
				if (mesAno6TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao6TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno6TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno6TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao6TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno6TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}
				
				// Cria as variáveis para caso não exista nenhum consumo para um totalizador setor vazio
				String mesAno1TotalSetorComercialFormatado = "";
				String mesAno2TotalSetorComercialFormatado = "";
				String mesAno3TotalSetorComercialFormatado = "";
				String mesAno4TotalSetorComercialFormatado = "";
				String mesAno5TotalSetorComercialFormatado = "";
				String mesAno6TotalSetorComercialFormatado = "";
				
				String mesAno1TotalLocalidadeFormatado = "";
				String mesAno2TotalLocalidadeFormatado = "";
				String mesAno3TotalLocalidadeFormatado = "";
				String mesAno4TotalLocalidadeFormatado = "";
				String mesAno5TotalLocalidadeFormatado = "";
				String mesAno6TotalLocalidadeFormatado = "";
				
				// Verifica se deve ser alterado o valor dos totalizadores de setor comercial de 0 para vazio
				if (peloMenosUmDadoMesAno1SetorComercial) {
					mesAno1TotalSetorComercialFormatado = mesAno1TotalSetorComercial.toString();
				} else {
					variacao1TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno2SetorComercial) {
					mesAno2TotalSetorComercialFormatado = mesAno2TotalSetorComercial.toString();
				} else {
					variacao2TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno3SetorComercial) {
					mesAno3TotalSetorComercialFormatado = mesAno3TotalSetorComercial.toString();
				} else {
					variacao3TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno4SetorComercial) {
					mesAno4TotalSetorComercialFormatado = mesAno4TotalSetorComercial.toString();
				} else {
					variacao4TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno5SetorComercial) {
					mesAno5TotalSetorComercialFormatado = mesAno5TotalSetorComercial.toString();
				} else {
					variacao5TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno6SetorComercial) {
					mesAno6TotalSetorComercialFormatado = mesAno6TotalSetorComercial.toString();
				} else {
					variacao6TotalSetorComercial = "";
				}
				
				// Verifica se deve ser alterado o valor dos totalizadores de localidade de 0 para vazio
				if (peloMenosUmDadoMesAno1Localidade) {
					mesAno1TotalLocalidadeFormatado = mesAno1TotalLocalidade.toString();
				} else {
					variacao1TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno2Localidade) {
					mesAno2TotalLocalidadeFormatado = mesAno2TotalLocalidade.toString();
				} else {
					variacao2TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno3Localidade) {
					mesAno3TotalLocalidadeFormatado = mesAno3TotalLocalidade.toString();
				} else {
					variacao3TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno4Localidade) {
					mesAno4TotalLocalidadeFormatado = mesAno4TotalLocalidade.toString();
				} else {
					variacao4TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno5Localidade) {
					mesAno5TotalLocalidadeFormatado = mesAno5TotalLocalidade.toString();
				} else {
					variacao5TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno6Localidade) {
					mesAno6TotalLocalidadeFormatado = mesAno6TotalLocalidade.toString();
				} else {
					variacao6TotalLocalidade = "";
				}

				relatorioBean = new RelatorioVolumesFaturadosBean(

				// Localidade
						localidade,

						// Id do Setor Comercial
						idSetorComercial,
						
						// Setor Comercial
						setorComercial,
						
						// Número da Quadra
						numeroQuadra,
						
						// Média de Consumo
						media,
						
						// Consumo do Mês/Ano 1
						consumoMesAno1,
						
						// Variação do Mês/Ano 1
						variacao1,
						
						// Consumo do Mês/Ano 2
						consumoMesAno2,
						
						// Variação do Mês/Ano 2
						variacao2,
						
						// Consumo do Mês/Ano 3
						consumoMesAno3,
						
						// Variação do Mês/Ano 3
						variacao3,
						
						// Consumo do Mês/Ano 4
						consumoMesAno4,
						
						// Variação do Mês/Ano 4
						variacao4,
						
						// Consumo do Mês/Ano 5
						consumoMesAno5,
						
						// Variação do Mês/Ano 5
						variacao5,
						
						// Consumo do Mês/Ano 6
						consumoMesAno6,
						
						// Variação do Mês/Ano 6
						variacao6,
						
						// Totalizadores do Setor Comercial
						// Média de Consumo
						mediaTotalSetorComercial.toString(),
						
						// Consumo do Mês/Ano 1
						mesAno1TotalSetorComercialFormatado,
						
						// Variação do Mês/Ano 1
						variacao1TotalSetorComercial,
						
						// Consumo do Mês/Ano 2
						mesAno2TotalSetorComercialFormatado,
						
						// Variação do Mês/Ano 2
						variacao2TotalSetorComercial,
						
						// Consumo do Mês/Ano 3
						mesAno3TotalSetorComercialFormatado,
						
						// Variação do Mês/Ano 3
						variacao3TotalSetorComercial,
						
						// Consumo do Mês/Ano 4
						mesAno4TotalSetorComercialFormatado,
						
						// Variação do Mês/Ano 4
						variacao4TotalSetorComercial,
						
						// Consumo do Mês/Ano 5
						mesAno5TotalSetorComercialFormatado,
						
						// Variação do Mês/Ano 5
						variacao5TotalSetorComercial,
						
						// Consumo do Mês/Ano 6
						mesAno6TotalSetorComercialFormatado,
						
						// Variação do Mês/Ano 6
						variacao6TotalSetorComercial,
						
						// Totalizadores da Localidade
						// Média de Consumo
						mediaTotalLocalidade.toString(),
						
						// Consumo do Mês/Ano 1
						mesAno1TotalLocalidadeFormatado,
						
						// Variação do Mês/Ano 1
						variacao1TotalLocalidade,
						
						// Consumo do Mês/Ano 2
						mesAno2TotalLocalidadeFormatado,
						
						// Variação do Mês/Ano 2
						variacao2TotalLocalidade,
						
						// Consumo do Mês/Ano 3
						mesAno3TotalLocalidadeFormatado,
						
						// Variação do Mês/Ano 3
						variacao3TotalLocalidade,
						
						// Consumo do Mês/Ano 4
						mesAno4TotalLocalidadeFormatado,
						
						// Variação do Mês/Ano 4
						variacao4TotalLocalidade,
						
						// Consumo do Mês/Ano 5
						mesAno5TotalLocalidadeFormatado,
						
						// Variação do Mês/Ano 5
						variacao5TotalLocalidade,
						
						// Consumo do Mês/Ano 6
						mesAno6TotalLocalidadeFormatado,
						
						// Variação do Mês/Ano 6
						variacao6TotalLocalidade);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);

			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(anoMes));
		parametros.put("mesAno1", Util.formatarAnoMesParaMesAno(anoMes1));
		parametros.put("mesAno2", Util.formatarAnoMesParaMesAno(anoMes2));
		parametros.put("mesAno3", Util.formatarAnoMesParaMesAno(anoMes3));
		parametros.put("mesAno4", Util.formatarAnoMesParaMesAno(anoMes4));
		parametros.put("mesAno5", Util.formatarAnoMesParaMesAno(anoMes5));
		parametros.put("mesAno6", Util.formatarAnoMesParaMesAno(anoMes6));

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_VOLUMES_FATURADOS_RESUMIDO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.VOLUMES_FATURADOS_RESUMIDO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioVolumesFaturadosResumido",
				this);
	}
}