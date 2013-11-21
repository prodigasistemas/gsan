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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.FiltroHidrometroTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da atualização do conjunto de hidrometro
 * 
 * @author Sávio Luiz
 * @created 14 de Setembro de 2005
 */
public class ExibirAtualizarConjuntoHidrometroAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping
				.findForward("atualizarConjuntoHidrometro");

		// Obtém o action form
		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;
		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoHidrometroCapacidade = null;
		/*
		 * if(sessao.getAttribute("colecaoHidrometroCapacidade")!= null &&
		 * !sessao.getAttribute("colecaoHidrometroCapacidade").equals("")){
		 * colecaoHidrometroCapacidade =
		 * (Collection)sessao.getAttribute("colecaoHidrometroCapacidade"); }
		 */

		// Filtro de hidrômetro capacidade para obter capacidade de hidrômetro
		// de acordo com o id
		FiltroHidrometroCapacidade filtroHidrometroCapacidadeNumeroDigitos = new FiltroHidrometroCapacidade();

		String fixo = (String) sessao.getAttribute("fixo");
		String faixaInicial = (String) sessao.getAttribute("faixaInicial");
		String faixaFinal = (String) sessao.getAttribute("faixaFinal");

		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();
		
		Collection hidrometros = fachada.pesquisarNumeroHidrometroFaixa(fixo,faixaInicial,faixaFinal);
		
		// caso o pesquisa intervalo seja diferente de nulo então veio do
		// hidrometro_atualizar_conjunto
		// e não será necessario verificar novamente se o conjunto de
		// hidrometros
		// estão com o mesmo valor
		String pesquisaIntervaloPorCapacidade = httpServletRequest
				.getParameter("pesquisaIntervalo");

		if (pesquisaIntervaloPorCapacidade == null
				|| pesquisaIntervaloPorCapacidade.equals("")) {

			// Verifica se os objetos estão na sessão
			if (hidrometros != null && !hidrometros.isEmpty()) {

				Iterator hidrometroIterator = hidrometros.iterator();

				boolean primeiraVez = true;
				Hidrometro primeiroHidrometro = null;

				while (hidrometroIterator.hasNext()) {
					if (primeiraVez) {

						primeiraVez = false;

						primeiroHidrometro = (Hidrometro) hidrometroIterator
								.next();
						// seta os valores no form
						sessao.setAttribute("hidrometro", primeiroHidrometro);
						hidrometroActionForm.setFixo(fixo);
						hidrometroActionForm.setFaixaFinal(faixaFinal);
						hidrometroActionForm.setFaixaInicial(faixaInicial);
						hidrometroActionForm
								.setAnoFabricacao(formatarResultado(""
										+ primeiroHidrometro.getAnoFabricacao()));
						SimpleDateFormat dataFormatoAtual = new SimpleDateFormat(
								"dd/MM/yyyy");
						// joga em dataInicial a parte da data
						String dataAquisicao = dataFormatoAtual
								.format(primeiroHidrometro.getDataAquisicao());

						hidrometroActionForm
								.setDataAquisicao(formatarResultado(dataAquisicao));
						hidrometroActionForm
								.setIdHidrometroCapacidade(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroCapacidade()
												.getId()));
						hidrometroActionForm
								.setIdHidrometroClasseMetrologica(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroClasseMetrologica()
												.getId()));
						hidrometroActionForm
								.setIdHidrometroDiametro(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroDiametro()
												.getId()));
						hidrometroActionForm
								.setIdHidrometroMarca(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroMarca().getId()));
						hidrometroActionForm
								.setIdHidrometroTipo(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroTipo().getId()));
						hidrometroActionForm
								.setIndicadorMacromedidor(formatarResultado(""
										+ primeiroHidrometro
												.getIndicadorMacromedidor()));
						hidrometroActionForm
								.setIdNumeroDigitosLeitura(formatarResultado(""
										+ primeiroHidrometro
												.getNumeroDigitosLeitura()));
						if(primeiroHidrometro.getHidrometroRelojoaria() != null && 
								!primeiroHidrometro.getHidrometroRelojoaria().equals("")){
						hidrometroActionForm
							    .setIdHidrometroRelojoaria(formatarResultado(""
									+ primeiroHidrometro
											.getHidrometroRelojoaria().getId()));
						}else{
							hidrometroActionForm
						    .setIdHidrometroRelojoaria("");
						}
						//Vazao Transicao
						if (primeiroHidrometro.getVazaoTransicao() != null ) {
							hidrometroActionForm
		                		.setVazaoTransicao( "" + Util.formatarMoedaReal(primeiroHidrometro.getVazaoTransicao()))  ;
						} else {
							hidrometroActionForm.setVazaoTransicao( "" );
						}
						//Vazao Nominal
						if (primeiroHidrometro.getVazaoNominal() != null ) {
		                hidrometroActionForm
		            		.setVazaoNominal( "" + Util.formatarMoedaReal(primeiroHidrometro.getVazaoNominal()));
						} else {
							hidrometroActionForm.setVazaoNominal( "" );
						}
						//Vazao Minima
						if (primeiroHidrometro.getVazaoMinima() != null ) {
		                hidrometroActionForm
		            		.setVazaoMinima( "" +  Util.formatarMoedaReal(primeiroHidrometro.getVazaoMinima())) ;
						} else {
							hidrometroActionForm.setVazaoMinima( "" );
						}
						//Nota Fiscal
		                if(primeiroHidrometro.getNotaFiscal() != null ) {
		                	hidrometroActionForm
		                		.setNotaFiscal( "" + primeiroHidrometro.getNotaFiscal() ) ;
		                } else {
		                	hidrometroActionForm.setNotaFiscal( "" );
		                }
		                //Tempo Garantia Anos
		                if(primeiroHidrometro.getTempoGarantiaAnos() != null ) {
		                	hidrometroActionForm
		                		.setTempoGarantiaAnos( "" +  primeiroHidrometro.getTempoGarantiaAnos() ) ;
		                } else {
		                	hidrometroActionForm.setTempoGarantiaAnos( "" ) ;
		                }

					} else {

						Hidrometro hidrometro = (Hidrometro) hidrometroIterator
								.next();
						// Caso as informações de data de aquisição, ano de
						// fabricação, finalidade da medição do hidrômetro,
						// classe metrológica, marca, diâmetro, capacidade e
						// tipo
						// não sejam todas iguais
						if (!primeiroHidrometro.equalsHidrometro(hidrometro)) {
							throw new ActionServletException(
									"atencao.faixa.nao.uniformidade");
						}
					}

				}

				// Filtro de hidrômetro classe metrológica para obter todas as
				// classes metrológicas ativas
				FiltroHidrometroClasseMetrologica filtroHidrometroClasseMetrologica = new FiltroHidrometroClasseMetrologica();

				filtroHidrometroClasseMetrologica
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroClasseMetrologica.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroClasseMetrologica
						.setCampoOrderBy(FiltroHidrometroClasseMetrologica.DESCRICAO);

				// Pesquisa a coleção de classe metrológica
				Collection colecaoHidrometroClasseMetrologica = fachada
						.pesquisar(filtroHidrometroClasseMetrologica,
								HidrometroClasseMetrologica.class.getName());

				// Filtro de hidrômetro marca para obter todas as marcas de
				// hidrômetros ativas
				FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMarca.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroMarca
						.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

				// Pesquisa a coleção de hidrômetro marca
				Collection colecaoHidrometroMarca = fachada.pesquisar(
						filtroHidrometroMarca, HidrometroMarca.class.getName());

				// Filtro de hidrômetro diâmetro para obter todos os diâmetros
				// de
				// hidrômetros ativos
				FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

				filtroHidrometroDiametro
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroDiametro.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroDiametro
						.setCampoOrderBy(FiltroHidrometroDiametro.DESCRICAO);

				// Pesquisa a coleção de hidrômetro capacidade
				Collection colecaoHidrometroDiametro = fachada.pesquisar(
						filtroHidrometroDiametro, HidrometroDiametro.class
								.getName());

				// Filtro de hidrômetro capacidade para obter todos as
				// capacidade de
				// hidrômetros ativas
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

				filtroHidrometroCapacidade
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroCapacidade.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroCapacidade
						.setCampoOrderBy(FiltroHidrometroCapacidade.DESCRICAO);

				// Pesquisa a coleção de hidrômetro capacidade
				colecaoHidrometroCapacidade = fachada.pesquisar(
						filtroHidrometroCapacidade, HidrometroCapacidade.class
								.getName());

				// Filtro de hidrômetro tipo para obter todos os tipos de
				// hidrômetros ativos
				FiltroHidrometroTipo filtroHidrometroTipo = new FiltroHidrometroTipo();

				filtroHidrometroTipo.adicionarParametro(new ParametroSimples(
						FiltroHidrometroTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroTipo
						.setCampoOrderBy(FiltroHidrometroTipo.DESCRICAO);

				// Pesquisa a coleção de hidrômetro tipo
				Collection colecaoHidrometroTipo = fachada.pesquisar(
						filtroHidrometroTipo, HidrometroTipo.class.getName());

				// Envia as coleções na sessão
				sessao.setAttribute("colecaoHidrometroClasseMetrologica",
						colecaoHidrometroClasseMetrologica);
				sessao.setAttribute("colecaoHidrometroMarca",
						colecaoHidrometroMarca);
				sessao.setAttribute("colecaoHidrometroDiametro",
						colecaoHidrometroDiametro);
				sessao.setAttribute("colecaoHidrometroCapacidade",
						colecaoHidrometroCapacidade);
				sessao.setAttribute("colecaoHidrometroTipo",
						colecaoHidrometroTipo);

				// Verifica se a coleção de hidrometro capacidade é diferente de
				// null
//				if (colecaoHidrometroCapacidade != null
//						&& !colecaoHidrometroCapacidade.isEmpty()) {
//
//					// Obtém o primeiro objeto da collection
//					Iterator colecaoHidrometroCapacidadeIterator = colecaoHidrometroCapacidade
//							.iterator();
//					HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) colecaoHidrometroCapacidadeIterator
//							.next();

					// Filtra pelo primeiro objeto da collection
					filtroHidrometroCapacidadeNumeroDigitos
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroCapacidade.ID,
									primeiroHidrometro.getHidrometroCapacidade().getId()));
//				}
			}
		} else {

			// Filtra pelo id selecionado no combobox
			filtroHidrometroCapacidadeNumeroDigitos
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroCapacidade.ID, new Integer(
									hidrometroActionForm
											.getIdHidrometroCapacidade())));

		}

		// Pesquisa o número de dígitos de acordo com o filtro
		Collection colecaoHidrometroCapacidadeNumeroDigitos = fachada
				.pesquisar(filtroHidrometroCapacidadeNumeroDigitos,
						HidrometroCapacidade.class.getName());

		// Retorna o objeto pesquisado
		HidrometroCapacidade hidrometroCapacidadeNumeroDigitos = (HidrometroCapacidade) Util
				.retonarObjetoDeColecao(colecaoHidrometroCapacidadeNumeroDigitos);

		// Obtém as leituras
		Integer leituraMinimo = new Integer(hidrometroCapacidadeNumeroDigitos
				.getLeituraMinimo().toString());
		Integer leituraMaximo = new Integer(hidrometroCapacidadeNumeroDigitos
				.getLeituraMaximo().toString());

		// Obtém a quantidade da diferença
		int quantidade = (leituraMaximo.intValue() - leituraMinimo.intValue()) + 1;

		Collection colecaoIntervalo = new ArrayList();

		// Adiciona a quantidade da diferença na coleção
		for (int i = 0; i < quantidade; i++) {

			colecaoIntervalo.add(new Integer(leituraMinimo.intValue() + i));
		}

		// Envia pela sessão
		sessao.setAttribute("colecaoIntervalo", colecaoIntervalo);

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}

}
