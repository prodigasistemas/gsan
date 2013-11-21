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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.DocumentoEmissaoForma;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaDocumentoItem;
import gcom.cobranca.bean.CobrancaDocumentoHelper;
import gcom.cobranca.bean.FiltrarDocumentoCobrancaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Administrador
 * @date 07/03/2006
 */
public class FiltrarDocumentosCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarDocumentosCobrancaResultado");

		// DynaValidatorActionForm pesquisarActionForm =
		// (DynaValidatorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// Parte que pega as coleções da sessão

		FiltrarDocumentosCobrancaActionForm filtrarDocumentosCobrancaActionForm = (FiltrarDocumentosCobrancaActionForm) actionForm;
		
		FiltrarDocumentoCobrancaHelper filtrarDocumentoCobrancaHelper = new FiltrarDocumentoCobrancaHelper();
		
		
		
		/*if(httpServletRequest.getParameter("idGerenciaRegional")== null){
			filtrarDocumentosCobrancaActionForm.setIdGerenciaRegional(null);
		}
		if(httpServletRequest.getParameter("idUnidadeNegocio") == null){
			filtrarDocumentosCobrancaActionForm.setIdUnidadeNegocio(null);
		}*/
		
		boolean informUm = false;
		
		//Intervalo de Valor do Documento
		//===============================================================================
		//String valorInicioSTR = null;
		//String valorFimSTR = null;
		BigDecimal valorInicio = null;
		BigDecimal valorFim = null;
		
		if (filtrarDocumentosCobrancaActionForm.getValorInicial() != null
			&& !filtrarDocumentosCobrancaActionForm.getValorInicial().equals("")) {
			/*
			valorInicioSTR = filtrarDocumentosCobrancaActionForm.getValorInicial().replace(".","");
			valorInicioSTR = valorInicioSTR.replace(",",".");
			
			
			valorInicio = new BigDecimal(valorInicioSTR);*/
			
			valorInicio = Util.formatarMoedaRealparaBigDecimal(filtrarDocumentosCobrancaActionForm.getValorInicial());
		}

		if (filtrarDocumentosCobrancaActionForm.getValorFinal() != null
			&& !filtrarDocumentosCobrancaActionForm.getValorFinal().equals("")) {
			
			/*valorFimSTR = filtrarDocumentosCobrancaActionForm.getValorFinal().replace(".","");
			valorFimSTR = valorFimSTR.replace(",",".");
			
			valorFim = new BigDecimal(valorFimSTR);*/
			
			valorFim = Util.formatarMoedaRealparaBigDecimal(filtrarDocumentosCobrancaActionForm.getValorFinal());
			
		}

		
		if ((valorInicio != null) && (valorFim != null)) {
			
			if (valorInicio.compareTo(valorFim) > 0) {
				throw new ActionServletException(
						"atencao.valor_fim_menor_inicio");
			}else {
				
				filtrarDocumentoCobrancaHelper.setValorDocumentoInicial(valorInicio);
				filtrarDocumentoCobrancaHelper.setValorDocumentoFinal(valorFim);
			}
		}
		else if (valorInicio != null && valorFim == null){	
			
			filtrarDocumentoCobrancaHelper.setValorDocumentoInicial(valorInicio);
			filtrarDocumentoCobrancaHelper.setValorDocumentoFinal(valorInicio);
		}
		//===================================================================================
		
		if (filtrarDocumentosCobrancaActionForm.getIdImovel() != null
			&& !filtrarDocumentosCobrancaActionForm.getIdImovel().equals("")) {
			
			informUm = true;
			filtrarDocumentoCobrancaHelper.setIdImovel(new Integer(filtrarDocumentosCobrancaActionForm.getIdImovel()));
		}

		if (filtrarDocumentosCobrancaActionForm.getIdGerenciaRegional() != null
			&& !filtrarDocumentosCobrancaActionForm.getIdGerenciaRegional().equals("")
			&& !filtrarDocumentosCobrancaActionForm.getIdGerenciaRegional().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			
			filtrarDocumentoCobrancaHelper.setIdGerenciaRegional(new Integer(filtrarDocumentosCobrancaActionForm.getIdGerenciaRegional()));
			informUm = true;
		}
		
		//Unidade Negocio
		if (filtrarDocumentosCobrancaActionForm.getIdUnidadeNegocio() != null
				&& !filtrarDocumentosCobrancaActionForm.getIdUnidadeNegocio().equals("")
				&& !filtrarDocumentosCobrancaActionForm.getIdUnidadeNegocio().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				
				filtrarDocumentoCobrancaHelper.setIdUnidadeNegocio(new Integer(filtrarDocumentosCobrancaActionForm.getIdUnidadeNegocio()));
				informUm = true;
		}
		
		
		//Cobrança Ação
		String[] idsCobrancaAcao = null;
		if (filtrarDocumentosCobrancaActionForm.getCobrancaAcao() != null
			&& filtrarDocumentosCobrancaActionForm.getCobrancaAcao().length > 0){
				
			idsCobrancaAcao = filtrarDocumentosCobrancaActionForm.getCobrancaAcao();
			Integer[] idsCobrancaAcaoAux = null;
			
			if (idsCobrancaAcao[0].equals("-1")){
				idsCobrancaAcaoAux = new Integer[idsCobrancaAcao.length - 1];
			}else{
				idsCobrancaAcaoAux = new Integer[idsCobrancaAcao.length];
			}
			
			for(int i=0; i< idsCobrancaAcao.length; i++ ){
				if (!idsCobrancaAcao[i].equals("-1")){
					if (idsCobrancaAcao[0].equals("-1")){
						idsCobrancaAcaoAux[i-1] = new Integer(idsCobrancaAcao[i]);
					}else{
						idsCobrancaAcaoAux[i] = new Integer(idsCobrancaAcao[i]);
					}
				}
			}
			
			filtrarDocumentoCobrancaHelper.setIdsCobrancaAcao(idsCobrancaAcaoAux);
		}
		
		//CobrancaAcaoSituacao
		if (filtrarDocumentosCobrancaActionForm.getIdCobrancaAcaoSituacao() != null
			&& filtrarDocumentosCobrancaActionForm.getIdCobrancaAcaoSituacao().length > 0){
				
			String[] idsCobrancaAcaoSituacao = filtrarDocumentosCobrancaActionForm.getIdCobrancaAcaoSituacao();
			Integer[] idsCobrancaAcaoSituacaoAux = null;
			
			if (idsCobrancaAcaoSituacao[0].equals("-1")){
				idsCobrancaAcaoSituacaoAux = new Integer[idsCobrancaAcaoSituacao.length - 1];
			}else{
				idsCobrancaAcaoSituacaoAux = new Integer[idsCobrancaAcaoSituacao.length];
			}
			
			for(int i=0; i< idsCobrancaAcaoSituacao.length; i++ ){
			     
				if (!idsCobrancaAcaoSituacao[i].equals("-1")){
					if (idsCobrancaAcaoSituacao[0].equals("-1")){
						idsCobrancaAcaoSituacaoAux[i-1] = new Integer(idsCobrancaAcaoSituacao[i]);
					}else{
						idsCobrancaAcaoSituacaoAux[i] = new Integer(idsCobrancaAcaoSituacao[i]);
					}
				}
			}
			
			filtrarDocumentoCobrancaHelper.setIdsAcaoSituacao(idsCobrancaAcaoSituacaoAux);
		}
		
		//CobrancaDebitoSituacao
		if (filtrarDocumentosCobrancaActionForm.getIdCobrancaDebitoSituacao() != null
			&& filtrarDocumentosCobrancaActionForm.getIdCobrancaDebitoSituacao().length > 0){
				
			String[] idsCobrancaDebitoSituacao = filtrarDocumentosCobrancaActionForm.getIdCobrancaDebitoSituacao();
			Integer[] idsCobrancadebitoSituacaoAux = null;
			
			if (idsCobrancaDebitoSituacao[0].equals("-1")){
				idsCobrancadebitoSituacaoAux = new Integer[idsCobrancaDebitoSituacao.length - 1];
			}else{
				idsCobrancadebitoSituacaoAux = new Integer[idsCobrancaDebitoSituacao.length];
			}
			
			for(int i=0; i< idsCobrancaDebitoSituacao.length; i++ ){
			     
				if (!idsCobrancaDebitoSituacao[i].equals("-1")){
					if (idsCobrancaDebitoSituacao[0].equals("-1")){
						idsCobrancadebitoSituacaoAux[i-1] = new Integer(idsCobrancaDebitoSituacao[i]);
					}else{
						idsCobrancadebitoSituacaoAux[i] = new Integer(idsCobrancaDebitoSituacao[i]);
					}
				}
			}
			
			filtrarDocumentoCobrancaHelper.setIdsDebitoSituacao(idsCobrancadebitoSituacaoAux);
		}
				
		//Categoria
		if (filtrarDocumentosCobrancaActionForm.getIdCategoria() != null
			&& filtrarDocumentosCobrancaActionForm.getIdCategoria().length > 0){
				
			String[] idsCategoria = filtrarDocumentosCobrancaActionForm.getIdCategoria();
			Integer[] idsCategoriaAux = null;
			
			if (idsCategoria[0].equals("-1")){
				idsCategoriaAux = new Integer[idsCategoria.length - 1];
			}else{
				idsCategoriaAux = new Integer[idsCategoria.length];
			}
			
			
			for(int i=0; i< idsCategoria.length; i++ ){
			    
				if (!idsCategoria[i].equals("-1")){
					if (idsCategoria[0].equals("-1")){
						idsCategoriaAux[i-1] = new Integer(idsCategoria[i]);
					}else{
						idsCategoriaAux[i] = new Integer(idsCategoria[i]);
					}
				}
			}
			
			filtrarDocumentoCobrancaHelper.setIdsImovelCategoria(idsCategoriaAux);
		}
		
		//Empresa
		if (filtrarDocumentosCobrancaActionForm.getIdFirma() != null
			&& filtrarDocumentosCobrancaActionForm.getIdFirma().length > 0){
				
			String[] idsEmpresas = filtrarDocumentosCobrancaActionForm.getIdFirma();
			Integer[] idsEmpresasAux = null;
			
			if (idsEmpresas[0].equals("-1")){
				idsEmpresasAux = new Integer[idsEmpresas.length - 1];
			}else{
				idsEmpresasAux = new Integer[idsEmpresas.length];
			}
			
			for(int i=0; i< idsEmpresas.length; i++ ){
				
				if (!idsEmpresas[i].equals("-1")){
					if (idsEmpresas[0].equals("-1")){
						idsEmpresasAux[i-1] = new Integer(idsEmpresas[i]);
					}else{
						idsEmpresasAux[i] = new Integer(idsEmpresas[i]);
					}
				}
			}
			
			filtrarDocumentoCobrancaHelper.setIdsEmpresa(idsEmpresasAux);
		}
		//fim alteração
		
		
		
		//Motivo da Não Entrega do Documento
		if (filtrarDocumentosCobrancaActionForm.getMotivoNaoEntregaDocumento() != null
			&& filtrarDocumentosCobrancaActionForm.getMotivoNaoEntregaDocumento().length > 0){
			
			String[] idsMotivoNaoEntregaDocumento = filtrarDocumentosCobrancaActionForm.getMotivoNaoEntregaDocumento();
			Integer[] idsMotivoNaoEntregaDocumentoAux = null;
			
			if (idsMotivoNaoEntregaDocumento[0].equals("-1")){
				idsMotivoNaoEntregaDocumentoAux = new Integer[idsMotivoNaoEntregaDocumento.length - 1];
			}else{
				idsMotivoNaoEntregaDocumentoAux = new Integer[idsMotivoNaoEntregaDocumento.length];
			}
			
			for(int i=0; i< idsMotivoNaoEntregaDocumento.length; i++ ){
			    
				if (!idsMotivoNaoEntregaDocumento[i].equals("-1")){
					if (idsMotivoNaoEntregaDocumento[0].equals("-1")){
						idsMotivoNaoEntregaDocumentoAux[i-1] = new Integer(idsMotivoNaoEntregaDocumento[i]);
					}else{
						idsMotivoNaoEntregaDocumentoAux[i] = new Integer(idsMotivoNaoEntregaDocumento[i]);
					}
				}
			}
			
			filtrarDocumentoCobrancaHelper.setIdsMotivoNaoEntrega(idsMotivoNaoEntregaDocumentoAux);
		}
		
		
		//Perfil do Imóvel
		if (filtrarDocumentosCobrancaActionForm.getImovelPerfil() != null
			&& filtrarDocumentosCobrancaActionForm.getImovelPerfil().length > 0){
				
			String[] idsImovelPerfil = filtrarDocumentosCobrancaActionForm.getImovelPerfil();
			Integer[] idsImovelPerfilAux = null;
			
			if (idsImovelPerfil[0].equals("-1")){
				idsImovelPerfilAux = new Integer[idsImovelPerfil.length - 1];
			}else{
				idsImovelPerfilAux = new Integer[idsImovelPerfil.length];
			}
			
			for(int i=0; i< idsImovelPerfil.length; i++ ){
			    
				if (!idsImovelPerfil[i].equals("-1")){
					if (idsImovelPerfil[0].equals("-1")){
						idsImovelPerfilAux[i-1] = new Integer(idsImovelPerfil[i]);
					}else{
						idsImovelPerfilAux[i] = new Integer(idsImovelPerfil[i]);
					}
				}
			}
			
			filtrarDocumentoCobrancaHelper.setIdsImovelPerfil(idsImovelPerfilAux);
		}
		
		
		// Buscando por localidade, Setor Comercial e Quadra, fazendo validacoes
		// de intervalos

		if ((filtrarDocumentosCobrancaActionForm.getLocalidadeOrigemID() != null)
				&& (!filtrarDocumentosCobrancaActionForm
						.getLocalidadeOrigemID().equals(""))
				&& (filtrarDocumentosCobrancaActionForm
						.getLocalidadeDestinoID() != null)
				&& (!filtrarDocumentosCobrancaActionForm
						.getLocalidadeDestinoID().equals(""))) {
			
			informUm = true;
			Integer localidadeInicial = new Integer(
					filtrarDocumentosCobrancaActionForm.getLocalidadeOrigemID());
			Integer localidadeFinal = new Integer(
					filtrarDocumentosCobrancaActionForm
							.getLocalidadeDestinoID());
			int resultado = localidadeInicial.compareTo(localidadeFinal);

			if (resultado == 0) {
				filtrarDocumentoCobrancaHelper.setIdLocalidadeInicial(localidadeInicial);
				filtrarDocumentoCobrancaHelper.setIdLocalidadeFinal(localidadeFinal);

				if ((filtrarDocumentosCobrancaActionForm
						.getSetorComercialOrigemCD() != null)
						&& (!filtrarDocumentosCobrancaActionForm
								.getSetorComercialOrigemCD().equals(""))
						&& (filtrarDocumentosCobrancaActionForm
								.getSetorComercialDestinoCD() != null)
						&& (!filtrarDocumentosCobrancaActionForm
								.getSetorComercialDestinoCD().equals(""))) {
					Integer setorInicial = new Integer(
							filtrarDocumentosCobrancaActionForm
									.getSetorComercialOrigemCD());
					Integer setorFinal = new Integer(
							filtrarDocumentosCobrancaActionForm
									.getSetorComercialDestinoCD());
					int resultadoSetor = setorInicial.compareTo(setorFinal);

					if (resultadoSetor == 0) {
						
						FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
						filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorInicial));
						filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeInicial));
						Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
						SetorComercial setorComercialInicio = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
						
						filtrarDocumentoCobrancaHelper.setIdSetorComercialInicial(setorComercialInicio.getId());
						filtrarDocumentoCobrancaHelper.setIdSetorComercialFinal(setorComercialInicio.getId());

						if ((filtrarDocumentosCobrancaActionForm
								.getQuadraOrigemNM() != null)
								&& (!filtrarDocumentosCobrancaActionForm
										.getQuadraOrigemNM().equals(""))
								&& (filtrarDocumentosCobrancaActionForm
										.getQuadraDestinoNM() != null)
								&& (!filtrarDocumentosCobrancaActionForm
										.getQuadraDestinoNM().equals(""))) {
							Integer quadraInicial = new Integer(
									filtrarDocumentosCobrancaActionForm
											.getQuadraOrigemNM());
							Integer quadraFinal = new Integer(
									filtrarDocumentosCobrancaActionForm
											.getQuadraDestinoNM());
							int resultadoQuadra = quadraInicial
									.compareTo(quadraFinal);

							if (resultadoQuadra == 0) {
								FiltroQuadra filtroQuadra = new FiltroQuadra();
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraInicial));
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialInicio.getId()));
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, localidadeInicial));
								Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
								Quadra quadraInicio = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
								
								filtrarDocumentoCobrancaHelper.setIdQuadraInicial(quadraInicio.getId());
								filtrarDocumentoCobrancaHelper.setIdQuadraFinal(quadraInicio.getId());
							} else if (resultadoQuadra > 0) {
								throw new ActionServletException(
										"atencao.valor_fim_menor_inicio");
							} else {
								
								FiltroQuadra filtroQuadra = new FiltroQuadra();
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraInicial));
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialInicio.getId()));
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, localidadeInicial));
								Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
								Quadra quadraInicio = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
								
								filtrarDocumentoCobrancaHelper.setIdQuadraInicial(quadraInicio.getId());
								
								FiltroQuadra filtroQuadraFinal = new FiltroQuadra();
								filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraFinal));
								filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialInicio.getId()));
								filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, localidadeFinal));
								Collection colecaoQuadraFinal = fachada.pesquisar(filtroQuadraFinal, Quadra.class.getName());
								Quadra quadraFim = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadraFinal);
								
								filtrarDocumentoCobrancaHelper.setIdQuadraFinal(quadraFim.getId());
							}
						}

					} else if (resultadoSetor > 0) {
						throw new ActionServletException(
								"atencao.valor_fim_menor_inicio");
					} else {
						FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
						filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorInicial));
						filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeInicial));
						Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
						SetorComercial setorComercialInicio = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
						
						filtrarDocumentoCobrancaHelper.setIdSetorComercialInicial(setorComercialInicio.getId());
						
						FiltroSetorComercial filtroSetorComercialFinal = new FiltroSetorComercial();
						filtroSetorComercialFinal.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorFinal));
						filtroSetorComercialFinal.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeFinal));
						Collection colecaoSetorComercialFim = fachada.pesquisar(filtroSetorComercialFinal, SetorComercial.class.getName());
						SetorComercial setorComercialFim = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialFim);
						
						filtrarDocumentoCobrancaHelper.setIdSetorComercialFinal(setorComercialFim.getId());
						
						if ((filtrarDocumentosCobrancaActionForm
								.getQuadraOrigemNM() != null)
								&& (!filtrarDocumentosCobrancaActionForm
										.getQuadraOrigemNM().equals(""))
								&& (filtrarDocumentosCobrancaActionForm
										.getQuadraDestinoNM() != null)
								&& (!filtrarDocumentosCobrancaActionForm
										.getQuadraDestinoNM().equals(""))) {
							Integer quadraInicial = new Integer(
									filtrarDocumentosCobrancaActionForm
											.getQuadraOrigemNM());
							Integer quadraFinal = new Integer(
									filtrarDocumentosCobrancaActionForm
											.getQuadraDestinoNM());
							int resultadoQuadra = quadraInicial
									.compareTo(quadraFinal);

							if (resultadoQuadra == 0) {
								FiltroQuadra filtroQuadra = new FiltroQuadra();
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraInicial));
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialInicio.getId()));
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, localidadeInicial));
								Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
								Quadra quadraInicio = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
								
								filtrarDocumentoCobrancaHelper.setIdQuadraInicial(quadraInicio.getId());
							} else if (resultadoQuadra > 0) {
								throw new ActionServletException(
										"atencao.valor_fim_menor_inicio");
							} else {
								
								FiltroQuadra filtroQuadra = new FiltroQuadra();
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraInicial));
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialInicio.getId()));
								filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, localidadeInicial));
								Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
								Quadra quadraInicio = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
								
								filtrarDocumentoCobrancaHelper.setIdQuadraInicial(quadraInicio.getId());
								
								FiltroQuadra filtroQuadraFinal = new FiltroQuadra();
								filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraFinal));
								filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercialFim.getId()));
								filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, localidadeFinal));
								Collection colecaoQuadraFinal = fachada.pesquisar(filtroQuadraFinal, Quadra.class.getName());
								Quadra quadraFim = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadraFinal);
								
								filtrarDocumentoCobrancaHelper.setIdQuadraFinal(quadraFim.getId());
							}
						}
					}
				}

			} else if (resultado > 0) {
				throw new ActionServletException(
						"atencao.valor_fim_menor_inicio");
			} else {
				filtrarDocumentoCobrancaHelper.setIdLocalidadeInicial(localidadeInicial);
				filtrarDocumentoCobrancaHelper.setIdLocalidadeFinal(localidadeFinal);
			}
		}
		
		//Forma de emissão
		if (filtrarDocumentosCobrancaActionForm.getDocumentoEmissaoForma() != null
			&& filtrarDocumentosCobrancaActionForm.getDocumentoEmissaoForma().length > 0){
				
			String[] idsFormaEmissao = filtrarDocumentosCobrancaActionForm.getDocumentoEmissaoForma();
			Integer[] idsDocumentoEmissaoForma = null;
			
			if (idsFormaEmissao[0].equals("-1")){ 
				idsDocumentoEmissaoForma = new Integer[idsFormaEmissao.length - 1];
			}else{
				idsDocumentoEmissaoForma = new Integer[idsFormaEmissao.length];
			}
			
			for(int i=0; i< idsFormaEmissao.length; i++ ){
				if (!idsFormaEmissao[i].equals("-1")){
					if (idsFormaEmissao[0].equals("-1")){
						idsDocumentoEmissaoForma[i-1] = new Integer(idsFormaEmissao[i]);
					}else{
						idsDocumentoEmissaoForma[i] = new Integer(idsFormaEmissao[i]);
					}
				}
			}
			filtrarDocumentoCobrancaHelper.setIdsDocumentoEmissaoForma(idsDocumentoEmissaoForma);
		}
		
		//Período de Data de Emissão
		String dataInicial = filtrarDocumentosCobrancaActionForm.getDataEmissaoInicio();
		String dataFinal = filtrarDocumentosCobrancaActionForm.getDataEmissaoFim();
		
		if ((dataInicial.trim().length() == 10)
				&& (dataFinal.trim().length() == 10)) {
			
			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();
            
            calendarInicio.setTime( Util.converteStringParaDate( dataInicial ) );
            calendarFim.setTime( Util.converteStringParaDate( dataFinal ) );

			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
			
			//[FS0011] - Validar forma de emissão
			//Caso o mês/ano de referência esteja informado e 
			//forma de emissão CRONOGRAMA não esteja selecionada
			boolean achouCronograma = false;
			String[] idsFormaEmissao = filtrarDocumentosCobrancaActionForm.getDocumentoEmissaoForma();
			if (filtrarDocumentosCobrancaActionForm.getMesAnoReferencia() != null 
					&& !filtrarDocumentosCobrancaActionForm.getMesAnoReferencia().equals("")){
				
				if ( idsFormaEmissao!=null && idsFormaEmissao.length > 0){
					//só considera o ciclo caso haja forma de emissão cronograma selecionada
					if (new Integer(idsFormaEmissao[0]).intValue() == DocumentoEmissaoForma.CRONOGRAMA){
						achouCronograma = true;
		
						//Ciclo
						//[FS0010] Validar mês/ano de referência
//						FiltroSistemaParametro filtroSistemaParametro= new FiltroSistemaParametro();
//						Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
//						
//						if (colecaoSistemaParametro != null && !colecaoSistemaParametro.isEmpty()) {
							
//							SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
							String anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(filtrarDocumentosCobrancaActionForm.getMesAnoReferencia());
//							String anoMesFaturamentoCorrente = ""+ sistemaParametro.getAnoMesFaturamento();
							String anoMesCorrente = Util.getAnoMesComoString(new Date()); 
							Integer resultado = anoMesReferencia.compareTo(anoMesCorrente);
							
							if (resultado > 0){
								
								throw new ActionServletException( "atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
										null, Util.formatarAnoMesParaMesAno(anoMesCorrente));
							}
							
							
							Collection idsCobrancaAcaoAux = pesquisarIdsAcoesCobranca(fachada, idsCobrancaAcao);
							
							Collection colecaoIdsRetorno = fachada.pesquisarIdsAcoesCiclo(idsCobrancaAcaoAux, new Integer(anoMesReferencia));
							
							if (colecaoIdsRetorno != null && colecaoIdsRetorno.size() > 0){
							
								Integer[] idsCobrancaAcaoAtividadeCronograma =  new Integer[colecaoIdsRetorno.size()];
								int ind = 0;
								for (Iterator colecaoIdsRetornoIterator = colecaoIdsRetorno.iterator(); colecaoIdsRetornoIterator
										.hasNext();) {
									Integer idCobrancaAcaoAtividadeCronograma = (Integer) colecaoIdsRetornoIterator.next();
									idsCobrancaAcaoAtividadeCronograma[ind] = idCobrancaAcaoAtividadeCronograma;
									ind++;
								}
								
								filtrarDocumentoCobrancaHelper.setIdsCobrancaAcaoAtividadeCronograma(idsCobrancaAcaoAtividadeCronograma);
								filtrarDocumentoCobrancaHelper.setCiclo(filtrarDocumentosCobrancaActionForm.getMesAnoReferencia());
							}
//						}
					}
				}
				
				if (achouCronograma == false){
					throw new ActionServletException(
					"atencao.ciclo_apenas_acoes_cronograma");
				}
			}
			
			
			
			filtrarDocumentoCobrancaHelper.setDataEmissaoInicial(dataInicial);
			filtrarDocumentoCobrancaHelper.setDataEmissaoFinal(dataFinal);
			
		}else{
			//[FS0011] - Validar forma de emissão
			//Caso o período de emissão não esteja informado e 
			//forma de emissão EVENTUAL ou INDIVIDUAL estejam selecionadas
			boolean achouEventual = false;
			boolean achouIndividual = false;
			boolean achouCronograma = false;
			String[] idsFormaEmissao = filtrarDocumentosCobrancaActionForm.getDocumentoEmissaoForma();
			
			if (idsFormaEmissao != null){
				for (int i = 0; i < idsFormaEmissao.length; i++) {
					if (new Integer(idsFormaEmissao[i]).intValue() == DocumentoEmissaoForma.EVENTUAL ){
						achouEventual = true;
					}
					
					if (new Integer(idsFormaEmissao[i]).intValue() == DocumentoEmissaoForma.INDIVIDUAL ){
						achouIndividual = true;
					}
					
					if (new Integer(idsFormaEmissao[i]).intValue() == DocumentoEmissaoForma.CRONOGRAMA ){
						achouCronograma = true;
					}
				}
				
				if (achouEventual || achouIndividual){
					throw new ActionServletException(
					"atencao.periodo_obrigatorio_eventual_individual");
				}
				
				if (achouCronograma == false && filtrarDocumentosCobrancaActionForm.getMesAnoReferencia() !=null 
						&& !filtrarDocumentosCobrancaActionForm.getMesAnoReferencia().equals("")){
					throw new ActionServletException(
					"atencao.ciclo_apenas_acoes_cronograma");
				}
				
				if (filtrarDocumentosCobrancaActionForm.getMesAnoReferencia() != null 
						&& !filtrarDocumentosCobrancaActionForm.getMesAnoReferencia().equals("")){
					//Ciclo
					if (idsFormaEmissao.length > 0 && achouCronograma){
						//[FS0010] Validar mês/ano de referência
//						FiltroSistemaParametro filtroSistemaParametro= new FiltroSistemaParametro();
//						Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
//						
//						if (colecaoSistemaParametro != null && !colecaoSistemaParametro.isEmpty()) {
							
							//RM3967 - alterado por Vivianne Sousa - 05/01/2010 - analista: Ana Cristina
//							SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
							String anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(filtrarDocumentosCobrancaActionForm.getMesAnoReferencia());
//							String anoMesFaturamentoCorrente = ""+ sistemaParametro.getAnoMesFaturamento();
							String anoMesCorrente = Util.getAnoMesComoString(new Date()); 
							
							Integer resultado = anoMesReferencia.compareTo(anoMesCorrente);
							
							if (resultado > 0){
								
								throw new ActionServletException( "atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
										null, Util.formatarAnoMesParaMesAno(anoMesCorrente));
							}
							
							Collection idsCobrancaAcaoAux = pesquisarIdsAcoesCobranca(fachada, idsCobrancaAcao);
							
							Collection colecaoIdsRetorno = fachada.pesquisarIdsAcoesCiclo(idsCobrancaAcaoAux, new Integer(anoMesReferencia));
							
							if (colecaoIdsRetorno != null && colecaoIdsRetorno.size() > 0){
							
								Integer[] idsCobrancaAcaoAtividadeCronograma =  new Integer[colecaoIdsRetorno.size()];
								int ind = 0;
								for (Iterator colecaoIdsRetornoIterator = colecaoIdsRetorno.iterator(); colecaoIdsRetornoIterator
										.hasNext();) {
									Integer idCobrancaAcaoAtividadeCronograma = (Integer) colecaoIdsRetornoIterator.next();
									idsCobrancaAcaoAtividadeCronograma[ind] = idCobrancaAcaoAtividadeCronograma;
									ind++;
								}
								
								filtrarDocumentoCobrancaHelper.setIdsCobrancaAcaoAtividadeCronograma(idsCobrancaAcaoAtividadeCronograma);
								filtrarDocumentoCobrancaHelper.setCiclo(filtrarDocumentosCobrancaActionForm.getMesAnoReferencia());
							}
//						}
					}
				}
			}
		}
		
		//verifica se campos obrigatorios foram preenchidos
		if (!informUm && httpServletRequest.getParameter("page.offset")== null){
			throw new ActionServletException(
				"atencao.informe_matricula_gerencia_localidade");
		}

		Collection colecaoDocumentoCobranca = fachada.consultarCobrancaDocumento(filtrarDocumentoCobrancaHelper);
		
		if (colecaoDocumentoCobranca == null
				|| colecaoDocumentoCobranca.isEmpty()) {
			// [FS0010] Nenhum registro encontrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "");
		}
	
		//Esquema de Paginação
		// 1º Passo - Pegar o total de registros
		Integer totalRegistros = colecaoDocumentoCobranca.size();
		
		// 2º Passo - Chamar a função de Paginação passando o total de
		// registros
		retorno = controlarPaginacao(httpServletRequest, retorno, totalRegistros);
		
		//3º Passo - Obter a coleção da consulta que aparecerá na tela
		//passando o numero de paginas
		// da pesquisa que está no request
		colecaoDocumentoCobranca = buscaColecaoPaginada((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"), colecaoDocumentoCobranca);
		
		sessao.setAttribute("totalRegistros", totalRegistros);
		sessao.setAttribute("numeroPaginasPesquisa",httpServletRequest
				.getAttribute("numeroPaginasPesquisa"));
		
		//final esquema paginacao
		
		Iterator colecaoDocumentoCobrancaIterator = colecaoDocumentoCobranca
				.iterator();
		CobrancaDocumentoHelper cobrancaDocumentoHelper = null;
		CobrancaDocumento cobrancaDocumento = null;
		FiltroCobrancaDocumentoItem filtroCobrancaDocumentoItem = new FiltroCobrancaDocumentoItem();
		Collection colecaoCobrancaDocumentoItem = null;
		Collection colecaoCobrancaDocumentoHelper = new ArrayList();

		while (colecaoDocumentoCobrancaIterator.hasNext()) {
			cobrancaDocumento = (CobrancaDocumento) colecaoDocumentoCobrancaIterator
					.next();
			filtroCobrancaDocumentoItem
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaDocumentoItem.COBRANCA_DOCUMENTO_ID,
							cobrancaDocumento.getId()));
			colecaoCobrancaDocumentoItem = fachada.pesquisar(
					filtroCobrancaDocumentoItem, CobrancaDocumentoItem.class
							.getName());
			cobrancaDocumentoHelper = new CobrancaDocumentoHelper();
			cobrancaDocumentoHelper.setCobrancaDocumento(cobrancaDocumento);
			
			Object[] dadosOrdemServico = fachada.pesquisarDadosOrdemServicoDocumentoCobranca(cobrancaDocumento.getId());
			if(dadosOrdemServico != null){
				if(dadosOrdemServico[0] != null){
					cobrancaDocumentoHelper.setIdOrdemServico((Integer)dadosOrdemServico[0]);
				}
				if(dadosOrdemServico[1] != null){
					Short situacaoOS = (Short)dadosOrdemServico[1];
					if(situacaoOS.equals(OrdemServico.SITUACAO_PENDENTE)){
						cobrancaDocumentoHelper.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESC_ABREV_PENDENTE);
					}
					if(situacaoOS.equals(OrdemServico.SITUACAO_ENCERRADO)){
						cobrancaDocumentoHelper.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESC_ABREV_ENCERRADO);
					}
					if(situacaoOS.equals(OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO)){
						cobrancaDocumentoHelper.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESC_ABREV_EXECUCAO_EM_ANDAMENTO);
					}
					if(situacaoOS.equals(OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO)){
						cobrancaDocumentoHelper.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESC_ABREV_AGUARDANDO_LIBERACAO);
					}
				}
			}

			if (colecaoCobrancaDocumentoItem == null
					|| colecaoCobrancaDocumentoItem.isEmpty()) {
				cobrancaDocumentoHelper.setQuantidadeItensCobrancaDocumento(0);
			} else {
				cobrancaDocumentoHelper
						.setQuantidadeItensCobrancaDocumento(colecaoCobrancaDocumentoItem
								.size());
			}
			colecaoCobrancaDocumentoHelper.add(cobrancaDocumentoHelper);
			filtroCobrancaDocumentoItem.limparListaParametros();
		}

		sessao.setAttribute("colecaoDocumentoCobranca",
				colecaoCobrancaDocumentoHelper);
		sessao.setAttribute("filtrarDocumentoCobrancaHelper", filtrarDocumentoCobrancaHelper);
		
		return retorno;

	}
	
	/**
	 * Este método é responsável por verificar as ações de cobrança selecionadas 
	 * para uso posterior na pesquisa dos ids da ações do ciclo
	 * 
	 * @author Anderson Italo
	 * @param fachada
	 * @param idsCobrancaAcao
	 * @return
	 */
	private Collection pesquisarIdsAcoesCobranca(Fachada fachada, String[] idsCobrancaAcao) {
		Collection idsCobrancaAcaoAux = null;
		if (idsCobrancaAcao  == null){
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			
			Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
			
			if (colecaoCobrancaAcao !=null && !colecaoCobrancaAcao.isEmpty()){
				idsCobrancaAcaoAux = new ArrayList();
				
				int indice = 0;
				for (Iterator colecaoIterator = colecaoCobrancaAcao.iterator(); colecaoIterator
						.hasNext();) {
					CobrancaAcao cobrancaAcao = (CobrancaAcao) colecaoIterator.next();
					idsCobrancaAcaoAux.add(cobrancaAcao.getId());
					indice ++;
				}
			}
			
		}else if (idsCobrancaAcao.length < 1){
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			
			Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
			if (colecaoCobrancaAcao !=null && !colecaoCobrancaAcao.isEmpty()){
				idsCobrancaAcaoAux = new ArrayList();
				
				int indice = 0;
				for (Iterator colecaoIterator = colecaoCobrancaAcao.iterator(); colecaoIterator
						.hasNext();) {
					CobrancaAcao cobrancaAcao = (CobrancaAcao) colecaoIterator.next();
					idsCobrancaAcaoAux.add(cobrancaAcao.getId());
					indice ++;
				}
			}
		}else if (idsCobrancaAcao[0].toString().equals("-1")){
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			
			Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
			if (colecaoCobrancaAcao !=null && !colecaoCobrancaAcao.isEmpty()){
				idsCobrancaAcaoAux = new ArrayList();
				int indice = 0;
				for (Iterator colecaoIterator = colecaoCobrancaAcao.iterator(); colecaoIterator
						.hasNext();) {
					CobrancaAcao cobrancaAcao = (CobrancaAcao) colecaoIterator.next();
					idsCobrancaAcaoAux.add(cobrancaAcao.getId());
					indice ++;
				}
			}
		}else if (idsCobrancaAcao.length >= 1){
			
			for (int i = 0; i < idsCobrancaAcao.length; i++) {
				idsCobrancaAcaoAux = new ArrayList();
				idsCobrancaAcaoAux.add(new Integer( idsCobrancaAcao[i]));
			}
		}
		return idsCobrancaAcaoAux;
	}
	
	/**
	 * Este método é responsável pela paginação da coleção
	 * a ser exibida na tela de resultados
	 * 
	 * @author Anderson Italo
	 * @param fachada
	 * @param idsCobrancaAcao
	 * @return
	 */
	private Collection buscaColecaoPaginada(int numeroPagina, Collection colecaoDocumentosCobranca){
		
		Collection colecao = null;
		if (numeroPagina > 0) {

			if (colecaoDocumentosCobranca != null
					&& !colecaoDocumentosCobranca.isEmpty()) {

				int posicao = 0;

				colecao = new ArrayList();

				Iterator iteratorColecaoDocumentosCobranca = colecaoDocumentosCobranca
						.iterator();
				CobrancaDocumento documentoCobranca = null;
				while (iteratorColecaoDocumentosCobranca.hasNext()) {
					documentoCobranca = (CobrancaDocumento) iteratorColecaoDocumentosCobranca
							.next();

					if (posicao >= (numeroPagina * 10)) {
						colecao.add(documentoCobranca);
					}
					posicao++;
				}
			}
		} else {
			colecao = colecaoDocumentosCobranca;
		}
		
		return colecao;
	}
	
}
