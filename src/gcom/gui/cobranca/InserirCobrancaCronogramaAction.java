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
package gcom.gui.cobranca;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupoCronogramaMes;
import gcom.cobranca.bean.AcaoEAtividadeCobrancaHelper;
import gcom.cobranca.bean.CobrancaCronogramaHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Administrador
 */
public class InserirCobrancaCronogramaAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String confirmacao = httpServletRequest.getParameter("confirmado");

		
//		Collection acoesCobranca = (Collection) sessao.getAttribute("acoesCobranca");
		Collection atividadesCobranca = (Collection) sessao.getAttribute("atividadesCobranca");
//		Collection atividadesCobrancaObrigatoriedadeAtivo = (Collection) sessao.getAttribute("atividadesCobrancaObrigatoriedadeAtivo");

		Collection colecaoAcaoEAtividadeCobranca = (Collection)sessao.getAttribute("colecaoAcaoEAtividadeCobranca");
		Iterator iterAcaoEAtividadeCobranca = colecaoAcaoEAtividadeCobranca.iterator();
		
		Collection cobrancasAtividadesParaInsercao = new ArrayList();
		Collection colecaoCobrancaCronogramaHelper = new ArrayList();

		CobrancaAcao cobrancaAcao = null;
		CobrancaAtividade cobrancaAtividade = null;
		CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes = null;
		CobrancaAcaoCronograma cobrancaAcaoCronograma = null;
		CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = null;
		CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();
		CobrancaCronogramaHelper cobrancaCronogramaHelper = null;

		cobrancaGrupo.setId(new Integer(cobrancaActionForm.getIdGrupoCobranca()));

//		Iterator iteratorAcaoCobranca = acoesCobranca.iterator();

		String idAcaoCobranca = "";
		String qtdMaximaDocumentos = "";
		String dataPrevista = "";
		String anoMes = "";
		String mesAno = "";

		//se não vem da página de confirmação
		if (confirmacao == null || !confirmacao.trim().equalsIgnoreCase("ok")){
			int contadorAtividades = 0;

			
			while (iterAcaoEAtividadeCobranca.hasNext()) {
				AcaoEAtividadeCobrancaHelper helper = (AcaoEAtividadeCobrancaHelper) iterAcaoEAtividadeCobranca.next();
				
				cobrancaAcao = helper.getAcaoCobranca();
				atividadesCobranca = helper.getAtividadesCobranca();
//			}
//			while (iteratorAcaoCobranca.hasNext()) {
				Iterator iteratorAtividadesCobranca = atividadesCobranca.iterator();
				cobrancaCronogramaHelper = new CobrancaCronogramaHelper();
				cobrancasAtividadesParaInsercao = new ArrayList();

//				cobrancaAcao = (CobrancaAcao) iteratorAcaoCobranca.next();

				// ----seta os valores no objeto CobrancaGrupoCronogramaMes
				cobrancaGrupoCronogramaMes = new CobrancaGrupoCronogramaMes();
				cobrancaGrupoCronogramaMes.setCobrancaGrupo(cobrancaGrupo);
				mesAno = cobrancaActionForm.getMesAno();
				String mes = mesAno.substring(0, 2);
				String ano = mesAno.substring(3, 7);
				anoMes = ano + "" + mes;
				cobrancaGrupoCronogramaMes.setAnoMesReferencia(Integer.parseInt(anoMes));
				// cobrancaGrupoCronogramaMes.setUltimaAlteracao(new Date());

				cobrancaCronogramaHelper.setCobrancaGrupoCronogramaMes(cobrancaGrupoCronogramaMes);

				// ----contador utilizado para verificar se ha alguma atividade com
				// data preenchida quando comandaer for nulo
				int verificaDataPreenchida = 0;

				// ----seta os valores no objeto CobrancaAcaoCronograma
				cobrancaAcaoCronograma = new CobrancaAcaoCronograma();
				cobrancaAcaoCronograma.setCobrancaAcao(cobrancaAcao);
				cobrancaCronogramaHelper.setCobrancaAcaoCronograma(cobrancaAcaoCronograma);

				// ------ se o indicador de obrigatoriedade for igual a sim(1)
				/**
				 * Caso o usuário não informe data prevista para todas as atividades
				 * das ações que obrigatoriamente devem constar no
				 * cronograma(CBAC_ICOBRIGATORIEDADE=1), exibir a mensagem "É
				 * necessário informar a data prevista para as atividades das ações
				 * que obrigatoriamente devem constar no cronograma" e retornar para
				 * o passo correspodente no fluxo principal.
				 */
				if (cobrancaAcao.getIndicadorObrigatoriedade().intValue() == 1) {
					while (iteratorAtividadesCobranca.hasNext()) {
						contadorAtividades += 1;

						cobrancaAtividade = (CobrancaAtividade) iteratorAtividadesCobranca.next();
						
						qtdMaximaDocumentos = (String) httpServletRequest.getParameter("qtd_a"
								+ cobrancaAcao.getId().toString() + "n"
								+ cobrancaAtividade.getId().toString());
						
						dataPrevista = "";
						dataPrevista = (String) httpServletRequest.getParameter("a"
								+ cobrancaAcao.getId().toString() + "n"
								+ cobrancaAtividade.getId().toString());
						
						
						if (dataPrevista.trim().equals("") && cobrancaAtividade.getIndicadorObrigatoriedade() == 1) {
							throw new ActionServletException("atencao.cobranca.data_prevista_acao_obrigatoria");
						} else {
							// cobrancaAcao =
							// (CobrancaAcao)iteratorAcaoCobranca.next();
							// --------pega o valor de comandar.Ex: comandar2
							idAcaoCobranca = (String) httpServletRequest.getParameter("comandar"
											+ cobrancaAcao.getId().toString()
											+ "atividade" + cobrancaAtividade.getId());
							// -------- separa o id da string comandar
							// ----seta os valores no objeto
							// CobrancaAcaoAtividadeCronograma
							cobrancaAcaoAtividadeCronograma = new CobrancaAcaoAtividadeCronograma();
							cobrancaAcaoAtividadeCronograma.setCobrancaAtividade(cobrancaAtividade);

							// Verifica se foi preenchido o campo de quantidade maxima de documento e
							// seta no objeto 
							if (qtdMaximaDocumentos != null && !qtdMaximaDocumentos.equals("")){
								cobrancaAcaoAtividadeCronograma.setQuantidadeMaximaDocumentos(new Integer(qtdMaximaDocumentos));
							}
							
							if (!dataPrevista.trim().equals("")) {
								
								Date DataPrevistaAcao = Util.converteStringParaDate(dataPrevista);
								//  [FS0004]  Validar Datas Previstas
								//	Caso o usuário informe uma data prevista para qualquer atividade de qualquer ação 
								//	com o mês/ano menor que o mês/ano da data atual, exibir a mensagem 
								//	'Data Prevista deve ser maior do que a data atual.' e retornar para o passo 
								//  correspondente no fluxo principal
								if(DataPrevistaAcao.compareTo(Util.formatarDataSemHora(new Date()))<0){
									throw new ActionServletException("atencao.data_prevista_menor_data_atual");
								}
								
								cobrancaAcaoAtividadeCronograma.setDataPrevista(Util.converteStringParaDate(dataPrevista));
								if (idAcaoCobranca != null	&& idAcaoCobranca.trim().equals("1")) {
									cobrancaAcaoAtividadeCronograma.setComando(Util
										.converteStringParaDateHora(dataPrevista
										+ " " + Util.formatarHoraSemData(new Date())));
								} else {
									cobrancaAcaoAtividadeCronograma.setComando(null);
								}
								
								cobrancasAtividadesParaInsercao.add(cobrancaAcaoAtividadeCronograma);
							}
						}
					}
				} else {
					verificaDataPreenchida = 0;
					while (iteratorAtividadesCobranca.hasNext()) {
						contadorAtividades += 1;

						cobrancaAtividade = (CobrancaAtividade) iteratorAtividadesCobranca.next();

						qtdMaximaDocumentos = (String) httpServletRequest.getParameter("qtd_a"
								+ cobrancaAcao.getId().toString() + "n"
								+ cobrancaAtividade.getId().toString());
						
						dataPrevista = "";
						dataPrevista = (String) httpServletRequest.getParameter("a"
								+ cobrancaAcao.getId().toString() + "n"
								+ cobrancaAtividade.getId().toString());
						
						

						cobrancaAcaoAtividadeCronograma = new CobrancaAcaoAtividadeCronograma();
						cobrancaAcaoAtividadeCronograma.setCobrancaAtividade(cobrancaAtividade);
						
						// Verifica se foi preenchido o campo de quantidade maxima de documento e seta no objeto 
						if (qtdMaximaDocumentos != null && !qtdMaximaDocumentos.equals("")){
							cobrancaAcaoAtividadeCronograma.setQuantidadeMaximaDocumentos(new Integer(qtdMaximaDocumentos));
						}					

						if (!dataPrevista.trim().equals("")
								|| cobrancaAtividade.getIndicadorObrigatoriedade()
										.equals(CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO)) {
							verificaDataPreenchida += 1;

							if (!dataPrevista.trim().equals("")) {
								
								Date DataPrevistaAcao = Util.converteStringParaDate(dataPrevista);
								//  [FS0004]  Validar Datas Previstas
								//	Caso o usuário informe uma data prevista para qualquer atividade de qualquer ação 
								//	com o mês/ano menor que o mês/ano da data atual, exibir a mensagem 
								//	'Data Prevista deve ser maior do que a data atual.' e retornar para o passo 
								//  correspondente no fluxo principal
								if(DataPrevistaAcao.compareTo(Util.formatarDataSemHora(new Date()))<0){
									throw new ActionServletException("atencao.data_prevista_menor_data_atual");
								}
								
								// --------pega o valor de comandar.Ex: comandar2
								idAcaoCobranca = (String) httpServletRequest.getParameter("comandar"
												+ cobrancaAcao.getId().toString()
												+ "atividade"	+ cobrancaAtividade.getId());
								// -------- separa o id da string comandar

								// ----seta os valores no objeto
								// CobrancaAcaoAtividadeCronograma
								cobrancaAcaoAtividadeCronograma.setDataPrevista(Util.converteStringParaDate(dataPrevista));
								if (idAcaoCobranca != null && idAcaoCobranca.trim().equals("1")) {
									cobrancaAcaoAtividadeCronograma
											.setComando(Util.converteStringParaDateHora(dataPrevista
											+ " " + Util.formatarHoraSemData(new Date())));
								} else {
									cobrancaAcaoAtividadeCronograma.setComando(null);
								}
							}
						} else {
							cobrancaAcaoAtividadeCronograma.setDataPrevista(null);
							cobrancaAcaoAtividadeCronograma.setComando(null);
						}
						if (cobrancaAcaoAtividadeCronograma.getDataPrevista() != null
								&& !cobrancaAcaoAtividadeCronograma.getDataPrevista().equals("")) {
							
							cobrancasAtividadesParaInsercao.add(cobrancaAcaoAtividadeCronograma);
						}
					}
					/**
					 * Caso o usuario informe a data prevista somente para algumas
					 * atividades da acao, exibir a mensagem "É necessário informar
					 * a data prevista para todas as atividades da ação."
					 */
					if (verificaDataPreenchida > 0) {
						
						FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
						filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
						filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
						filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_OBRIGATORIEDADE, CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO));
						filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
						filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
								FiltroCobrancaAtividade.ID_COBRANCA_ACAO, cobrancaAcao.getId()));
						Collection atividadesCobrancaObrigatoriedadeAtivo  = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());

						if(atividadesCobrancaObrigatoriedadeAtivo == null || atividadesCobrancaObrigatoriedadeAtivo.isEmpty()){
			
							filtroCobrancaAtividade = new FiltroCobrancaAtividade();
							filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
							filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
							filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.INDICADOR_OBRIGATORIEDADE, CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO));
							filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
							filtroCobrancaAtividade.adicionarParametro(new ParametroNulo(FiltroCobrancaAtividade.ID_COBRANCA_ACAO));
							
							atividadesCobrancaObrigatoriedadeAtivo = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
						}
						
						if(verificaDataPreenchida < atividadesCobrancaObrigatoriedadeAtivo.size()){
							throw new ActionServletException("atencao.cobranca.data_prevista_algumas_atividades");
						}
						
						
					}
				}
				
				cobrancaCronogramaHelper.setCritica1(false);
				cobrancaCronogramaHelper.setCritica2(false);
				cobrancaCronogramaHelper.setCritica3(false);
				cobrancaCronogramaHelper.setCobrancasAtividadesParaInsercao(cobrancasAtividadesParaInsercao);
				colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelper);
			}

			if (contadorAtividades == 0) {
				throw new ActionServletException("atencao.cobranca.nenhuma_atividade");
			}

		}else{
			colecaoCobrancaCronogramaHelper = (Collection) sessao.getAttribute("colecaoCobrancaCronogramaHelper");
			cobrancaGrupo = (CobrancaGrupo) sessao.getAttribute("cobrancaGrupo");
			mesAno = (String) sessao.getAttribute("mesAno");
			cobrancaGrupoCronogramaMes = (CobrancaGrupoCronogramaMes) sessao.getAttribute("cobrancaGrupoCronogramaMes");
		}
		
		for (Iterator iterCobrancacronogramaHelper = colecaoCobrancaCronogramaHelper.iterator(); iterCobrancacronogramaHelper
		.hasNext();) {
			CobrancaCronogramaHelper cobrancaCronogramaHelperAux = (CobrancaCronogramaHelper) iterCobrancacronogramaHelper.next();
			
			
			//[FS0006] - Verificar ação predecessora
			// se ação tem predecessora
			if (cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getCobrancaAcaoPredecessora() != null){
				
				for (Iterator iterCobrancaAcaoAtividadeCronograma = cobrancaCronogramaHelperAux.getCobrancasAtividadesParaInsercao()
						.iterator(); iterCobrancaAcaoAtividadeCronograma.hasNext();) {
					
					CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma2 = (CobrancaAcaoAtividadeCronograma) iterCobrancaAcaoAtividadeCronograma.next();
					
					//se a atividade for EMITIR
					if (cobrancaAcaoAtividadeCronograma2.getCobrancaAtividade().getId().intValue() == CobrancaAtividade.EMITIR){
						
						//Caso a atividade EMITIR da ação predecessora já tenha perdido a validade
						CobrancaAcao cobrancaAcaoPredecessora = cobrancaCronogramaHelperAux
						.getCobrancaAcaoCronograma().getCobrancaAcao().getCobrancaAcaoPredecessora();
						
						//recupera cobrancaAcaoAtividadeCronograma referente a ação predecessora da ação em questão
						cobrancaAcaoAtividadeCronograma =  recuperaAcaoPredecessora(cobrancaAcaoPredecessora.getId(), colecaoCobrancaCronogramaHelper);
						
						if (cobrancaCronogramaHelperAux.getCritica1() == false){
							if (cobrancaAcaoAtividadeCronograma != null){
								//data de vencimento da ação predecessora
								Date dataVencimentoAcaoPredecessora = Util.adicionarNumeroDiasDeUmaData(cobrancaAcaoAtividadeCronograma.getDataPrevista(),
										cobrancaAcaoPredecessora.getNumeroDiasValidade().intValue());

								if (!(cobrancaAcaoAtividadeCronograma2.getDataPrevista().compareTo(dataVencimentoAcaoPredecessora) <= 0)){
									
									//seta os atributos na sessão para uso posterior
									//colecaoCobrancaCronogramaHelper.remove(cobrancaCronogramaHelperAux);
									cobrancaCronogramaHelperAux.setCritica1(true);
									//colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelperAux);
									sessao.setAttribute("colecaoCobrancaCronogramaHelper", colecaoCobrancaCronogramaHelper);
									sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
									sessao.setAttribute("mesAno", mesAno);
									sessao.setAttribute("cobrancaGrupoCronogramaMes", cobrancaGrupoCronogramaMes);
									sessao.setAttribute("reexibirCritica", "true");
									
									// mapear para página de confirmação
									httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/inserirCobrancaCronogramaAction.do");
									
									// Monta a página de confirmação
									// Exibe a pergunta: Confirma data da atividade para a ação <<descrição da ação>> ? 
									// Se o usuário confirmar prosseguir, caso contrário aguardar a 
									// informação da nova data
									return montarPaginaConfirmacao(
											"atencao.data_validade_acao_predecessora_vencido",
											httpServletRequest, actionMapping, new String[] {cobrancaAcaoPredecessora.getDescricaoCobrancaAcao(),
											cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao()});
			
								}
							}
						}
						if (cobrancaCronogramaHelperAux.getCritica2() == false){
							//Caso a data informada para a atividade EMITIR não seja maior que NN dias da atividade EMITIR da ação predecessora
							if (cobrancaAcaoAtividadeCronograma != null){
								//data de vencimento da ação predecessora
								//if colocado pois bases de algumas empresas esse campo 
								//está nulo para ações que são predecessoras de outra
								if (cobrancaAcaoPredecessora.getNumeroDiasMinimoAcaoPrecedente() != null){
									Date dataVencimentoAcaoPredecessora = Util.adicionarNumeroDiasDeUmaData(cobrancaAcaoAtividadeCronograma.getDataPrevista(),
										cobrancaAcaoPredecessora.getNumeroDiasMinimoAcaoPrecedente());
								
									if (!(cobrancaAcaoAtividadeCronograma2.getDataPrevista().compareTo(dataVencimentoAcaoPredecessora) >= 0)){
										
										//seta os atributos na sessão para uso posterior
										//colecaoCobrancaCronogramaHelper.remove(cobrancaCronogramaHelperAux);
										cobrancaCronogramaHelperAux.setCritica2(true);
										//colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelperAux);
										sessao.setAttribute("colecaoCobrancaCronogramaHelper", colecaoCobrancaCronogramaHelper);
										sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
										sessao.setAttribute("mesAno", mesAno);
										sessao.setAttribute("cobrancaGrupoCronogramaMes", cobrancaGrupoCronogramaMes);
										sessao.setAttribute("reexibirCritica", "true");
										
										//mapear para página de confirmação
										httpServletRequest.setAttribute("caminhoActionConclusao",
										"/gsan/inserirCobrancaCronogramaAction.do");
										
										// Monta a página de confirmação
										// Exibe a pergunta: Confirma data da atividade para a ação <<descrição da ação>> ? 
										// Se o usuário confirmar prosseguir, caso contrário aguardar a 
										// informação da nova data
										return montarPaginaConfirmacao(
												"atencao.intervalo_dias_minimo_entre_acao_e_predecessora",
												httpServletRequest, actionMapping,
												new String[] { cobrancaAcaoPredecessora.getDescricaoCobrancaAcao(), cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao()
													,cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao()});
									}
								}
							}
						}
						
						break;
					}
				}
			}else{
				//[FS0010] - Verificar ação sem predecessora
				// Para cada Ação de Cobrança que não tenha ação predecessora (CBAC_IDACAOPRECEDENTE com valor igual a nulo):
				
				for (Iterator iterCobrancaAcaoAtividadeCronograma = cobrancaCronogramaHelperAux.getCobrancasAtividadesParaInsercao()
						.iterator(); iterCobrancaAcaoAtividadeCronograma.hasNext();) {
					
					CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma2 = (CobrancaAcaoAtividadeCronograma) iterCobrancaAcaoAtividadeCronograma.next();
					
					if (cobrancaAcaoAtividadeCronograma2.getCobrancaAtividade().getId().intValue() == CobrancaAtividade.EMITIR
							|| cobrancaAcaoAtividadeCronograma2.getCobrancaAtividade().getId().intValue() == CobrancaAtividade.SIMULAR){
						
						FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
						filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoGrupo.ID, new Integer(cobrancaActionForm.getIdGrupoCobranca())));
						
						Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
						FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
						
						/*Caso exista grupo de faturamento, ou seja, possua registro (na tabela FATURAMENTO_GRUPO 
						 * onde FTGR_ID = CBGR_ID da tabela da tabela COBRANCA_GRUPO e FTGR_AMREFERENCIA <> NULL 
						 * e FTGR_NNDIAVENCIMENTO <> NULL) */
						if (faturamentoGrupo != null 
								&& faturamentoGrupo.getAnoMesReferencia() != null
								&& faturamentoGrupo.getDiaVencimento() != null){
							
							/*Caso a data de vencimento das contas do último ciclo de faturamento do grupo de 
							 * faturamento correspondente ao grupo de cobrança para o qual está sendo informado 
							 * o cronograma (compor a data de vencimento com o dia de vencimento + mês/ano de 
							 * referência da tabela FATURAMENT_GRUPO) somada ao número de dias para considerar 
							 * débito, obtido na tabela SISTEMA_PARAMETROS, maior ou igual a data informada 
							 * para a atividade EMITIR OU SIMULAR da ação em questão*/
							int diaVencimentoGrupo = faturamentoGrupo.getDiaVencimento();
							int mesVencimentoGrupo = new Integer(faturamentoGrupo.getAnoMesReferencia().toString().substring(4)).intValue();
							int anoVencimentoGrupo = new Integer(faturamentoGrupo.getAnoMesReferencia().toString().substring(0,4)).intValue();
							
							Date dateVencimentoGrupo = Util.criarData(diaVencimentoGrupo, mesVencimentoGrupo, anoVencimentoGrupo);
							
							FiltroSistemaParametro filtroSistemaParametro= new FiltroSistemaParametro();
							Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
							
							if (colecaoSistemaParametro != null && !colecaoSistemaParametro.isEmpty()) {
								
								SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
								
								dateVencimentoGrupo = Util.adicionarNumeroDiasDeUmaData(dateVencimentoGrupo, sistemaParametro.getNumeroDiasVencimentoCobranca().intValue());
								
								if (cobrancaCronogramaHelperAux.getCritica3() == false){
									if (cobrancaAcaoAtividadeCronograma2.getDataPrevista().compareTo(dateVencimentoGrupo) < 0){
										
//										colecaoCobrancaCronogramaHelper.remove(cobrancaCronogramaHelperAux);
										cobrancaCronogramaHelperAux.setCritica3(true);
//										colecaoCobrancaCronogramaHelper.add(cobrancaCronogramaHelperAux);
										sessao.setAttribute("colecaoCobrancaCronogramaHelper", colecaoCobrancaCronogramaHelper);
										sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
										sessao.setAttribute("mesAno", mesAno);
										sessao.setAttribute("cobrancaGrupoCronogramaMes", cobrancaGrupoCronogramaMes);
										sessao.setAttribute("reexibirCritica", "true");
										
										// mapear para página de confirmação
										httpServletRequest.setAttribute("caminhoActionConclusao",
										"/gsan/inserirCobrancaCronogramaAction.do");
										
										// Monta a página de confirmação
										// Exibe a pergunta: Confirma data da atividade para a ação <<descrição da ação>> ? 
										// Se o usuário confirmar prosseguir, caso contrário aguardar a 
										// informação da nova data
										return montarPaginaConfirmacao(
												"atencao.acao_nao_contemplara_contas",
												httpServletRequest, actionMapping, new String[] {cobrancaCronogramaHelperAux.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao(),
														Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia())});
										
									}
								}
							}
						}
						
					}
				}
			}
			
		}
		
		
		
		
		// -----Chama o metodo inserirCobrancaCronograma da fachada
		fachada.inserirCobrancaCronograma(colecaoCobrancaCronogramaHelper, this
				.getUsuarioLogado(httpServletRequest));

		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, cobrancaGrupo.getId()));

		Collection colecaoCobrancaGrupo = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
		CobrancaGrupo cobrancaGrupoExibicao = (CobrancaGrupo) colecaoCobrancaGrupo.iterator().next();

		FiltroCobrancaGrupoCronogramaMes filtroCobrancaGrupoCronogramaMes = new FiltroCobrancaGrupoCronogramaMes();
		filtroCobrancaGrupoCronogramaMes.adicionarParametro(new ParametroSimples(
				FiltroCobrancaGrupoCronogramaMes.ID_COBRANCA_GRUPO,	cobrancaGrupo.getId()));
		filtroCobrancaGrupoCronogramaMes.adicionarParametro(new ParametroSimples(
						FiltroCobrancaGrupoCronogramaMes.ANO_MES_REFERENCIA,
						Util.formatarMesAnoParaAnoMesSemBarra(mesAno)));

		Collection colecaoCobrancaGrupoCronogramaMes = fachada.pesquisar(
				filtroCobrancaGrupoCronogramaMes, CobrancaGrupoCronogramaMes.class.getName());
		CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMesAtualizacao = null;
		cobrancaGrupoCronogramaMesAtualizacao = (CobrancaGrupoCronogramaMes) colecaoCobrancaGrupoCronogramaMes.iterator().next();

		//remove da sessão objetos não mais utilizáveis
		sessao.removeAttribute("colecaoCobrancaCronogramaHelper");
		sessao.removeAttribute("cobrancaGrupo");
		sessao.removeAttribute("mesAno");
		sessao.removeAttribute("cobrancaGrupoCronogramaMes");
		sessao.removeAttribute("reexibirCritica");
		
		montarPaginaSucesso(httpServletRequest,
			"Cronograma de Cobrança do " + cobrancaGrupoExibicao.getDescricao()	+ " referente a "
			+ Util.formatarAnoMesParaMesAno(cobrancaGrupoCronogramaMes.getAnoMesReferencia())
			+ " inserido com sucesso.",	"Inserir outro Cronograma de Cobrança",
			"exibirInserirCronogramaCobrancaAction.do?menu=sim",
			"exibirAtualizarCobrancaCronogramaAction.do?filtro=S&menu=sim&idRegistroAtualizacao="
			+ cobrancaGrupoCronogramaMesAtualizacao.getId().toString(),
			"Atualizar o Cronograma de Cobrança inserido");

		return retorno;
	}

	private CobrancaAcaoAtividadeCronograma recuperaAcaoPredecessora(int idCobrancaAcao, Collection colecaoCobrancaCronogramaHelper){
		CobrancaAcaoAtividadeCronograma retorno = null;
		
		for (Iterator iter = colecaoCobrancaCronogramaHelper.iterator(); iter
				.hasNext();) {
			CobrancaCronogramaHelper cobrancaCronogramaHelper = (CobrancaCronogramaHelper) iter.next();
			
			if (cobrancaCronogramaHelper.getCobrancaAcaoCronograma() != null){
				if (cobrancaCronogramaHelper.getCobrancaAcaoCronograma().getCobrancaAcao().getId().intValue() == idCobrancaAcao){
					for (Iterator iterator = cobrancaCronogramaHelper.getCobrancasAtividadesParaInsercao()
							.iterator(); iterator.hasNext();) {
						CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) iterator.next();
						
						if (cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getId().intValue() == CobrancaAtividade.EMITIR){
							retorno = cobrancaAcaoAtividadeCronograma;
							break;
						}
						
					}
					break;
				}
			}
			
		}
		
		return retorno;
	}

}
