/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisAlteracaoInscricaoViaBatch;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
 * 
 * @author Hugo Leonardo
 *
 * @date 19/01/2011
 */

public class GerarRelatorioImoveisAlteracaoInscricaoViaBatchAction extends ExibidorProcessamentoTarefaRelatorio {

	private Collection colecaoPesquisa = null;
	private String localidadeID = null;
	private String setorComercialCD = null;
	private String setorComercialID = null;
	private String quadraNM = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		   
		// Form
		GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm form = (GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm) actionForm;
		
		FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper helper = new FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		Fachada fachada = Fachada.getInstancia();
		
		boolean peloMenosUmParametroInformado = false;
		
		// Escolha Relatório
		if ( form.getEscolhaRelatorio() != null && 
				!form.getEscolhaRelatorio().equals("-1")) {
			
			helper.setEscolhaRelatorio(new Integer(form.getEscolhaRelatorio()));
			peloMenosUmParametroInformado = true;
		}else{
			
			throw new ActionServletException( "atencao.tipo_relatorio_nao_informado");
		}
		
		// Período de Alteração
		String periodo = "";
		if (form.getDataInicio() != null && !form.getDataInicio().equals("")){
			if (!Util.validarDiaMesAno(form.getDataInicio())) {
				
				periodo = form.getDataInicio() + " a ";
				helper.setDataInicio(Util.formatarDataInicial( Util.converteStringParaDate(form.getDataInicio())));
				
				if (!Util.validarDiaMesAno(form.getDataFim())) {
					
					periodo += form.getDataFim();
					helper.setDataFim(Util.formatarDataFinal( Util.converteStringParaDate(form.getDataFim())));
					
					if(Util.compararData(helper.getDataInicio(), helper.getDataFim()) == 1){
						
						throw new ActionServletException("atencao.periodo_alteracao_final_menor");
					}
					
					peloMenosUmParametroInformado = true;
					
				}else{
					throw new ActionServletException("atencao.periodo_alteracao_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.periodo_alteracao_inicial_invalida");
			}
		}
			
		// Localidade
		String localidadeInicial = " -- ";
		if(form.getLocalidadeOrigemID() != null && 
				!form.getLocalidadeOrigemID().equals("")){
			
			localidadeInicial = form.getLocalidadeOrigemID();
			helper.setLocalidadeInicial(new Integer(form.getLocalidadeOrigemID()));
			peloMenosUmParametroInformado = true;
		}
		
		String localidadeFinal = " -- ";
		if(form.getLocalidadeDestinoID() != null && 
				!form.getLocalidadeDestinoID().equals("")){
			
			localidadeFinal = form.getLocalidadeDestinoID();
			helper.setLocalidadeFinal(new Integer(form.getLocalidadeDestinoID()));
			peloMenosUmParametroInformado = true;
		}
		
		// Setor Comercial
		String setorComercialInicial = " -- ";
		if((form.getSetorComercialOrigemID() != null && !form.getSetorComercialOrigemID().equals("")) || 
				form.getSetorComercialOrigemCD() != null && !form.getSetorComercialOrigemCD().equals("")){
			
			if(form.getSetorComercialOrigemID() != null && 
					!form.getSetorComercialOrigemID().equals("")){
			
				setorComercialInicial = form.getSetorComercialOrigemCD();
				helper.setSetorComercialInicial(new Integer(form.getSetorComercialOrigemID()));
				peloMenosUmParametroInformado = true;
			}else{
				
				localidadeID = (String) form.getLocalidadeOrigemID();
				
				if(localidadeID == null || localidadeID.equals("")){
					
					throw new ActionServletException("atencao.localidade_nao_informada");
				}
				
				setorComercialCD = (String) form.getSetorComercialOrigemCD();
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException("atencao.processo.setorComercialNaoCadastrada");
				}
				
				SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				setorComercialInicial = form.getSetorComercialOrigemCD();
				helper.setSetorComercialInicial(objetoSetorComercial.getId());
				peloMenosUmParametroInformado = true;
			}
		}
		
		String setorComercialFinal = " -- ";
		if((form.getSetorComercialDestinoID() != null && !form.getSetorComercialDestinoID().equals("")) || 
				form.getSetorComercialDestinoCD() != null && !form.getSetorComercialDestinoCD().equals("")){
			
			if(form.getSetorComercialDestinoID() != null && 
					!form.getSetorComercialDestinoID().equals("")){
			
				setorComercialFinal = form.getSetorComercialDestinoCD();
				helper.setSetorComercialFinal(new Integer(form.getSetorComercialDestinoID()));
				peloMenosUmParametroInformado = true;
			}else{
				
				localidadeID = (String) form.getLocalidadeDestinoID();
				
				if(localidadeID == null || localidadeID.equals("")){
					
					throw new ActionServletException("atencao.localidade_nao_informada");
				}
				setorComercialCD = (String) form.getSetorComercialDestinoCD();
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException("atencao.processo.setorComercialNaoCadastrada");
				}
				
				SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				setorComercialFinal = form.getSetorComercialDestinoCD();
				helper.setSetorComercialFinal(objetoSetorComercial.getId());
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Quadra
		String quadraIncial = " -- ";
		if((form.getQuadraOrigemNM() != null && !form.getQuadraOrigemNM().equals("")) || 
				form.getQuadraOrigemID() != null && !form.getQuadraOrigemID().equals("") ){
			
			if(form.getQuadraOrigemID() != null && !form.getQuadraOrigemID().equals("")){
				
				quadraIncial = form.getQuadraOrigemNM();
				helper.setQuadraInicial(new Integer(form.getQuadraOrigemID()));
				peloMenosUmParametroInformado = true;
			}else{
				
				localidadeID = (String) form.getLocalidadeOrigemID();
				
				if(localidadeID == null || localidadeID.equals("")){
					
					throw new ActionServletException("atencao.localidade_nao_informada");
				}
				
				if(form.getSetorComercialOrigemID() != null && !form.getSetorComercialOrigemID().equals("")){
					
					setorComercialID = (String) form.getSetorComercialOrigemID();
					
					if(setorComercialID == null || setorComercialID.equals("")){
						
						throw new ActionServletException("atencao.setor_comercial_nao_informado");
					}
				}else{
					
					setorComercialCD = (String) form.getSetorComercialOrigemCD();
					
					if(setorComercialID == null || setorComercialID.equals("")){
						
						throw new ActionServletException("atencao.setor_comercial_nao_informado");
					}
					
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

					// Retorna setorComercial
					colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
					
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					setorComercialID = objetoSetorComercial.getId().toString();
				}
				
				quadraNM = (String) form.getQuadraOrigemNM();
				
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer( localidadeID)));
				
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				filtroQuadra.adicionarParametro(new ParametroSimples( FiltroQuadra.NUMERO_QUADRA, quadraNM));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException("atencao.quadra.inexistente");
				}
				
				Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				quadraIncial = form.getQuadraOrigemNM();
				helper.setQuadraInicial(objetoQuadra.getId());
				peloMenosUmParametroInformado = true;
			}
		}
		
		String quadraFinal = " -- ";
		if((form.getQuadraDestinoNM() != null && !form.getQuadraDestinoNM().equals("")) || 
				form.getQuadraDestinoID() != null && !form.getQuadraDestinoID().equals("") && 
				form.getLocalidadeDestinoID() != null && !form.getLocalidadeDestinoID().equals("") && 
				(form.getSetorComercialDestinoCD() != null && !form.getSetorComercialDestinoCD().equals("") || 
				form.getSetorComercialDestinoID() != null && !form.getSetorComercialDestinoID().equals("") )){
			
			if(form.getQuadraDestinoID() != null && !form.getQuadraDestinoID().equals("")){
				
				quadraFinal = form.getQuadraDestinoNM();
				helper.setQuadraFinal(new Integer(form.getQuadraDestinoID()));
				peloMenosUmParametroInformado = true;
			}else{
				
				localidadeID = (String) form.getLocalidadeDestinoID();
				
				if(localidadeID == null || localidadeID.equals("")){
					
					throw new ActionServletException("atencao.localidade_nao_informada");
				}
				
				if(form.getSetorComercialDestinoID() != null && !form.getSetorComercialDestinoID().equals("")){
					
					setorComercialID = (String) form.getSetorComercialDestinoID();
					
					if(setorComercialID == null || setorComercialID.equals("")){
						
						throw new ActionServletException("atencao.setor_comercial_nao_informado");
					}
				}else{
					
					setorComercialCD = (String) form.getSetorComercialDestinoCD();
									
					if(setorComercialID == null || setorComercialID.equals("")){
						
						throw new ActionServletException("atencao.setor_comercial_nao_informado");
					}
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

					// Retorna setorComercial
					colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
					
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					setorComercialID = objetoSetorComercial.getId().toString();
				}
				
				quadraNM = (String) form.getQuadraDestinoNM();
				
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer( localidadeID)));
				
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				filtroQuadra.adicionarParametro(new ParametroSimples( FiltroQuadra.NUMERO_QUADRA, quadraNM));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException("atencao.quadra.inexistente");
				}
				
				Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				quadraFinal = form.getQuadraDestinoNM();
				helper.setQuadraFinal(objetoQuadra.getId());
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Lote
		String loteInicial = " -- ";
		if(form.getLoteOrigem() != null && 
				!form.getLoteOrigem().equals("")){
			
			loteInicial = form.getLoteOrigem();
			helper.setLoteInicial(new Integer(form.getLoteOrigem()));
			peloMenosUmParametroInformado = true;
		}
		
		String loteFinal = " -- ";
		if(form.getLoteDestino() != null && 
				!form.getLoteDestino().equals("")){
			
			loteFinal = form.getLoteDestino();
			helper.setLoteFinal(new Integer(form.getLoteDestino()));
			peloMenosUmParametroInformado = true;
		}
		
		// Sub-Lote
		String subLoteInicial = " -- ";
		if(form.getSubLoteOrigem() != null && 
				!form.getSubLoteOrigem().equals("")){
			
			subLoteInicial = form.getSubLoteOrigem();
			helper.setSubLoteInicial(new Integer(form.getSubLoteOrigem()));
			peloMenosUmParametroInformado = true;
		}
		
		String subLoteFinal = " -- ";
		if(form.getSubLoteDestino() != null && 
				!form.getSubLoteDestino().equals("")){
			
			subLoteFinal = form.getSubLoteDestino();
			helper.setSubLoteFinal(new Integer(form.getSubLoteDestino()));
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			
			throw new ActionServletException( "atencao.filtro.nenhum_parametro_informado");
		}
		
		TarefaRelatorio relatorio = new RelatorioImoveisAlteracaoInscricaoViaBatch((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("filtrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper", helper);
		
		relatorio.addParametro("usuario", usuario);
		relatorio.addParametro("periodo", periodo);
		
		relatorio.addParametro("localidadeInicial", localidadeInicial);
		relatorio.addParametro("localidadeFinal", localidadeFinal);
		
		relatorio.addParametro("setorComercialInicial", setorComercialInicial);
		relatorio.addParametro("setorComercialFinal", setorComercialFinal);
		
		relatorio.addParametro("quadraIncial", quadraIncial);
		relatorio.addParametro("quadraFinal", quadraFinal);
		
		relatorio.addParametro("loteInicial", loteInicial);
		relatorio.addParametro("loteFinal", loteFinal);
		
		relatorio.addParametro("subLoteInicial", subLoteInicial);
		relatorio.addParametro("subLoteFinal", subLoteFinal);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
}