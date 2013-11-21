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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Action de execução do filtro de pesquisa
 * 
 * @author Thiago Nascimento
 *
 */
public class PesquisarRotaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("PesquisarRotaAction");
		
		PesquisarRotaActionForm form = (PesquisarRotaActionForm)actionForm;
		
		//Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroRota filtroRota = new FiltroRota();
		
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LEITURA_TIPO);
		
		boolean peloMenosUmParametro = false;
		
		if(!form.getIdGrupoFaturamento().equals("-1")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.FATURAMENTO_GRUPO_ID,new Integer(form.getIdGrupoFaturamento())));
			peloMenosUmParametro = true;
		}
		
		if(form.getIdLocalidade()!=null && !form.getIdLocalidade().trim().equals("")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID,new Integer(form.getIdLocalidade())));
			peloMenosUmParametro = true;
		}
		
		if(form.getCodigoSetorComercial()!=null && !form.getCodigoSetorComercial().trim().equals("")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO,new Integer(form.getCodigoSetorComercial())));
			peloMenosUmParametro = true;
		}
		
		if(form.getCodigoRota()!=null && !form.getCodigoRota().trim().equals("")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA,new Integer(form.getCodigoRota())));
			peloMenosUmParametro = true;
		}
		
		if(!form.getEmpresaLeituristica().equals("-1")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.EMPRESA_ID,new Integer(form.getEmpresaLeituristica())));
			peloMenosUmParametro = true;
		}
		
		if(form.getIndicadorUso()!=null && !form.getIndicadorUso().trim().equals("3")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,new Integer(form.getIndicadorUso())));
			peloMenosUmParametro = true;
		}
		
		if(form.getIndicadorRotaAlternativa()!= null){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_ROTA_ALTERNATIVA,new Integer(form.getIndicadorRotaAlternativa())));
			peloMenosUmParametro = true;
		}
		
		if (!peloMenosUmParametro) {
			throw new ActionServletException(
			"atencao.filtro.nenhum_parametro_informado");
		}
		
		Collection colecao = Fachada.getInstancia().pesquisar(filtroRota,Rota.class.getName());
		
		if(colecao!=null && !colecao.isEmpty()){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroRota, Rota.class.getName());
			colecao = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			sessao.setAttribute("rotas",colecao);
		}else {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Rota");
		}
		
		return retorno;
	}
	
}
