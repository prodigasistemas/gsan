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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0006]	FILTRAR MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 03/01/2007
 */

public class FiltrarMunicipioAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirManterMunicipio");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarMunicipioActionForm form = (FiltrarMunicipioActionForm) actionForm;
		
		
		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
		String codigoMunicipio = form.getCodigoMunicipio();
		String nomeMunicipio = form.getNomeMunicipio();
		String tipoPesquisa = form.getTipoPesquisa();
		String regiaoDesenv = form.getRegiaoDesenv();
		String regiao = form.getRegiao();
		String microregiao = form.getMicroregiao();
		String unidadeFederacao = form.getUnidadeFederacao();
		String indicadorUso = form.getIndicadorUso();
		String ordernarPor = form.getOrdernarPor();
		
		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		
		FiltroMunicipio filtroMunicipio = null;
		
		if(ordernarPor != null && ordernarPor.equals(ConstantesSistema.ORDENAR_POR_CODIGO)){
			filtroMunicipio = new FiltroMunicipio(FiltroMunicipio.ID);
		}else{
			filtroMunicipio = new FiltroMunicipio(FiltroMunicipio.NOME);
		}
		
		// Objetos que serão retornados pelo hibernate
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("regiaoDesenvolvimento");
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("microrregiao.regiao");
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
		
		boolean peloMenosUmParametroInformado = false;
		
		
		// Código do Município
		if (codigoMunicipio != null	&& !codigoMunicipio.trim().equals("")) {
			
			// [FS0004] - Verificando a existência do Municipio
			boolean achou = fachada.verificarExistenciaMunicipio(new Integer(codigoMunicipio));
			
			if (achou){
				peloMenosUmParametroInformado = true;
				filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, codigoMunicipio));
			}
		}
		
		
		// Nome do Municipio
		if (nomeMunicipio != null && !nomeMunicipio.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroMunicipio.adicionarParametro(new ComparacaoTextoCompleto(FiltroMunicipio.NOME, 
						nomeMunicipio));
			} else {
				
				filtroMunicipio.adicionarParametro(new ComparacaoTexto(FiltroMunicipio.NOME, nomeMunicipio));
			}
		}
		
		
		// Região de Desenvolvimento
		if (regiaoDesenv != null && !regiaoDesenv.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.
				NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.REGIAO_DESENVOLVOMENTO_ID,
					regiaoDesenv));
		}
		
		
		// Região 
		if (regiao != null && !regiao.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.REGIAO_ID, regiao));
		}
		
		
		// Microrregião
		if (microregiao != null && !microregiao.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.
				NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.MICRORREGICAO_ID, microregiao));
		}
		
		
		// Região de Desenvolvimento
		if (unidadeFederacao != null && !unidadeFederacao.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.
				NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.UNIDADE_FEDERACAO_ID, 
					unidadeFederacao));
		}
		
		
		// Indicador de Uso
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")&& !indicadorUso.equalsIgnoreCase("3")) {
			
			peloMenosUmParametroInformado = true;
			
			if (indicadorUso.equalsIgnoreCase(String.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				
				filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				
				filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		
		// Manda o filtro pela sessao para o
		// ExibirManterMunicipioAction
		sessao.setAttribute("filtroMunicipio", filtroMunicipio);
				
		return retorno;
		}
}

				
				
			
				
				
				
				