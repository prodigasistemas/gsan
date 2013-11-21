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

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.RotaAcaoCriterioHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;
import gcom.micromedicao.bean.AssociarConjuntoRotasCriterioCobrancaHelper;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * @author Raphael Rossiter
 * @date 24/01/2008 
 */
public class DesassociarConjuntoRotasCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AssociarConjuntoRotasCriterioCobrancaActionForm form = (AssociarConjuntoRotasCriterioCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		//CARREGANDO OBJETO HELPER
		AssociarConjuntoRotasCriterioCobrancaHelper parametros = this.carregarHelper(form);
		
		//************************************************************************************************************************************
		//INSTANCIANDO OBJETO HELPER PARA REGISTRO TRANSACAO
		RotaAcaoCriterioHelper rotaAcaoCriterioHelper = new RotaAcaoCriterioHelper();
		
		if ( form.getIdAcaoCobranca() != null && ! form.getIdAcaoCobranca().equals("") ){
			CobrancaAcao cobrancaAcao = new CobrancaAcao();
			cobrancaAcao.setId(new Integer(form.getIdAcaoCobranca()));
			rotaAcaoCriterioHelper.setCobrancaAcao(cobrancaAcao);
		}
		
		if ( form.getIdCriterioCobranca() != null && ! form.getIdCriterioCobranca().equals("") ){
			CobrancaCriterio cobrancaCriterio = new CobrancaCriterio();
			cobrancaCriterio.setId(new Integer(form.getIdCriterioCobranca()));
			rotaAcaoCriterioHelper.setCobrancaCriterio(cobrancaCriterio);
		}
		
		if ( form.getIdGrupoCobranca() != null && ! form.getIdGrupoCobranca().equals("") ){
			CobrancaGrupo grupo = new CobrancaGrupo();
			grupo.setId(new Integer(form.getIdGrupoCobranca()));
			rotaAcaoCriterioHelper.setCobrancaGrupo(grupo);
		}
		
		if (rotaAcaoCriterioHelper.getCobrancaGrupo() == null){
			
			if ( form.getIdGerenciaRegional() != null && ! form.getIdGerenciaRegional().equals("") &&  new Integer(form.getIdGerenciaRegional())> 0){
				GerenciaRegional gerenciaRegional = new GerenciaRegional();
			    gerenciaRegional.setId(new Integer(form.getIdGerenciaRegional()));
				rotaAcaoCriterioHelper.setGerenciaRegional(gerenciaRegional);
			}
			
			if ( form.getIdUnidadeNegocio() != null && ! form.getIdUnidadeNegocio().equals("") &&  new Integer(form.getIdUnidadeNegocio())> 0 ){
				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(form.getIdUnidadeNegocio()));
				rotaAcaoCriterioHelper.setUnidadeNegocio(unidadeNegocio);
			}
			
			if ( form.getIdLocalidadeInicial() != null && ! form.getIdLocalidadeInicial().equals("")){
				Localidade localidadeInicial = new Localidade();
				localidadeInicial.setId(new Integer(form.getIdLocalidadeInicial()));
				rotaAcaoCriterioHelper.setLocalidadeInicial(localidadeInicial);
			}
			
			if ( form.getIdLocalidadeFinal() != null && ! form.getIdLocalidadeFinal().equals("")){
				Localidade localidadeFinal = new Localidade();
				localidadeFinal.setId(new Integer(form.getIdLocalidadeFinal()));
				rotaAcaoCriterioHelper.setLocalidadeFinal(localidadeFinal);
			}
			
			if ( form.getCodigoSetorComercialInicial() != null && ! form.getCodigoSetorComercialInicial().equals("")){
				SetorComercial setorComercialInicial = new SetorComercial();
				setorComercialInicial.setId(new Integer(form.getCodigoSetorComercialInicial()));
				rotaAcaoCriterioHelper.setSetorComercialInicial(setorComercialInicial);
			}
					
			if ( form.getCodigoSetorComercialFinal() != null && ! form.getCodigoSetorComercialFinal().equals("") ){
				SetorComercial setorComercialFinal = new SetorComercial();
				setorComercialFinal.setId(new Integer(form.getCodigoSetorComercialFinal()));
				rotaAcaoCriterioHelper.setSetorComercialFinal(setorComercialFinal);
			}
				
			if ( form.getNumeroRotaInicial() != null && ! form.getNumeroRotaInicial().equals("") &&  new Integer(form.getNumeroRotaInicial())> 0 ){
				Rota rotaInicial = new Rota();
				rotaInicial.setId(new Integer(form.getNumeroRotaInicial()));
				rotaAcaoCriterioHelper.setRotaInicial(rotaInicial);
			}
			
			if ( form.getNumeroRotaFinal() != null && ! form.getNumeroRotaFinal().equals("") &&  new Integer(form.getNumeroRotaFinal())> 0 ){
				Rota rotaFinal = new Rota();
				rotaFinal.setId(new Integer(form.getNumeroRotaFinal()));
				rotaAcaoCriterioHelper.setRotaFinal(rotaFinal);
			}
		}
		
		//************************************************************************************************************************************
		
		//[UC0543] Associar Conjunto de Rotas a Critério de Cobrança
		fachada.desassociarConjuntoRotasCriterioCobranca(parametros, usuarioLogado, rotaAcaoCriterioHelper);
		
		
		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Rotas desassociadas ao critério de cobrança com sucesso.",
				"Associar outras Rotas", "exibirAssociarRotasCriterioCobrancaAction.do?menu=sim");

		return retorno;
	}
	
	
	private AssociarConjuntoRotasCriterioCobrancaHelper carregarHelper(AssociarConjuntoRotasCriterioCobrancaActionForm 
			form){
		
		AssociarConjuntoRotasCriterioCobrancaHelper parametros = 
		new AssociarConjuntoRotasCriterioCobrancaHelper();
		
		String idGrupoCobranca = form.getIdGrupoCobranca();
		String idGerencialRegional = form.getIdGerenciaRegional();
		String idUnidadeNegocio = form.getIdUnidadeNegocio();
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		String codigoSetorInicial = form.getCodigoSetorComercialInicial();
		String codigoSetorFinal = form.getCodigoSetorComercialFinal();
		String rotaInicial = form.getNumeroRotaInicial();
		String rotaFinal = form.getNumeroRotaFinal();
		String idCobrancaAcao = form.getIdAcaoCobranca();
		String idCriterioCobranca = form.getIdCriterioCobranca();
		
		parametros.setIdCobrancaAcao(idCobrancaAcao != null && 
		!idCobrancaAcao.equals("-1")?new Integer(idCobrancaAcao): null);
		
		parametros.setIdGrupoCobranca(idGrupoCobranca != null && 
		!idGrupoCobranca.equals("-1")?new Integer(idGrupoCobranca): null);
		parametros.setIdGerencialRegional(idGerencialRegional != null && 
		!idGerencialRegional.equals("-1")?new Integer(idGerencialRegional): null);
		parametros.setIdUnidadeNegocio(idUnidadeNegocio != null && 
		!idUnidadeNegocio.equals("-1")?new Integer(idUnidadeNegocio): null);
		
		parametros.setIdLocalidadeInicial(idLocalidadeInicial != null && 
		!idLocalidadeInicial.equals("")?new Integer(idLocalidadeInicial): null);
		parametros.setIdLocalidadeFinal(idLocalidadeFinal != null && 
		!idLocalidadeFinal.equals("")?new Integer(idLocalidadeFinal): null);
		
		parametros.setCdSetorComercialInicial(codigoSetorInicial != null && 
		!codigoSetorInicial.equals("")?new Integer(codigoSetorInicial): null);
		parametros.setCdSetorComercialFinal(codigoSetorFinal != null && 
		!codigoSetorFinal.equals("")?new Integer(codigoSetorFinal): null);
		
		parametros.setNnRotaInicial(rotaInicial != null && 
		!rotaInicial.equals("-1")?new Integer(rotaInicial): null);
		parametros.setNnRotaFinal(rotaFinal != null && 
		!rotaFinal.equals("-1")?new Integer(rotaFinal): null);
		
		parametros.setIdCriterioCobranca(idCriterioCobranca != null && 
		!idCriterioCobranca.equals("")?new Integer(idCriterioCobranca): null);
		
		parametros.setValidarCriterioCobranca(false);
		
		return parametros;
	}
}
