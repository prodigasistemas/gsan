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
* Anderson Italo Felinto de Lima
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;
import gcom.util.filtro.ParametroSimplesIn;


/**			
 * @date 20/10/09
 * @author Anderson Italo
 * Confirmação para operação de transferencia de 
 * Rotas entre Grupos e/ou Empresas
 */
public class TransferirRotaGrupoEmpresaConfirmarAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("confirmarTransferirRotaGrupoEmpresa");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection rotasSelecionadas = (Collection)sessao.getAttribute("rotasSelecionadas");
		
		TransferirRotasGruposEmpresasActionForm form = (TransferirRotasGruposEmpresasActionForm) actionForm;

		/*4.2.	Os grupos de faturamento aos quais pertencem as rotas 
		 * (Obter na tabela ROTA a partir de FTGR_ID aos grupos de faturamento
		 *  aos quais pertencem as rotas da selecionadas*/
		Collection idsGrupoFaturamento = new ArrayList();
		
		for (Iterator iter = rotasSelecionadas.iterator(); iter.hasNext();) {
			Rota rota = (Rota) iter.next();
			idsGrupoFaturamento.add(rota.getFaturamentoGrupo().getId());
		}
		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimplesIn(FiltroFaturamentoGrupo.ID , idsGrupoFaturamento));
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		Collection colecaoGrupoFaturamento = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		httpServletRequest.setAttribute("colecaoGrupoFaturamentoSelecao", colecaoGrupoFaturamento);
		
		/*4.3.	Os grupos de cobrança aos quais pertencem as rotas 
		 * (Obter na tabela ROTA a partir de CBGR_ID aos grupos de cobrança 
		 * aos quais pertencem as rotas da selecionadas.*/
		Collection idsGrupoCobranca = new ArrayList();
		
		for (Iterator iter = rotasSelecionadas.iterator(); iter.hasNext();) {
			Rota rota = (Rota) iter.next();
			idsGrupoCobranca.add(rota.getCobrancaGrupo().getId());
		}
		
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimplesIn(FiltroCobrancaGrupo.ID , idsGrupoCobranca));
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
		Collection colecaoGrupoCobranca = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
		httpServletRequest.setAttribute("colecaoGrupoCobrancaSelecao", colecaoGrupoCobranca);
		
		/*4.4.	As empresas de faturamento às quais pertencem 
		 * as rotas (Obter na tabela ROTA a partir de EMPR_ID as 
		 * empresas de faturamento às quais estão vinculadas as 
		 * rotas da selecionadas)*/
		Collection idsEmpresaFaturamento = new ArrayList();
		
		for (Iterator iter = rotasSelecionadas.iterator(); iter.hasNext();) {
			Rota rota = (Rota) iter.next();
			idsEmpresaFaturamento.add(rota.getEmpresa().getId());
		}
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimplesIn(FiltroEmpresa.ID , idsEmpresaFaturamento));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		Collection colecaoEmpresaFaturamento = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		httpServletRequest.setAttribute("colecaoEmpresaFaturamentoSelecao", colecaoEmpresaFaturamento);
		
		/*4.5.	As empresas de cobrança às quais pertencem as rotas (Obter 
		 * na tabela ROTA a partir de EMPR_IDCOBRANCA as empresas de cobrança 
		 * às quais estão vinculadas as rotas da selecionadas)*/
		Collection idsEmpresaCobranca = new ArrayList();
		
		for (Iterator iter = rotasSelecionadas.iterator(); iter.hasNext();) {
			Rota rota = (Rota) iter.next();
			idsEmpresaCobranca.add(rota.getEmpresaCobranca().getId());
		}
		
		filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimplesIn(FiltroEmpresa.ID , idsEmpresaCobranca));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		Collection colecaoEmpresaCobranca = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		httpServletRequest.setAttribute("colecaoEmpresaCobrancaSelecao", colecaoEmpresaCobranca);
		
		if (form.getIdGrupoCobrancaDestino() != null && !form.getIdGrupoCobrancaDestino().equals("")
				&& !form.getIdGrupoCobrancaDestino().equals("-1")){
			/*5.1.	Grupo de Cobrança (seleciona a partir da tabela COBRANCA_GRUPO) 
			 * [FS0001 - Verificar existência de dados].*/
			filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
			Collection colecaoCobrancaGrupoDestino = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			
			if (colecaoCobrancaGrupoDestino == null || colecaoCobrancaGrupoDestino.isEmpty()){
				throw new ActionServletException("Atencao.Sem_dados_para_selecao");
			}
			
			httpServletRequest.setAttribute("colecaoCobrancaGrupoDestino", colecaoCobrancaGrupoDestino);
		}
		
		if (form.getIdGrupoFaturamentoDestino() != null && !form.getIdGrupoFaturamentoDestino().equals("")
				&& !form.getIdGrupoFaturamentoDestino().equals("-1")){
			/*5.2.	Grupo de Faturamento (seleciona a partir da tabela FATURAMENTO_GRUPO) 
			 * [FS0001 - Verificar existência de dados].*/
			filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
			Collection colecaoFaturamentoGrupoDestino = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			
			if (colecaoFaturamentoGrupoDestino == null || colecaoFaturamentoGrupoDestino.isEmpty()){
				throw new ActionServletException("Atencao.Sem_dados_para_selecao");
			}
			
			httpServletRequest.setAttribute("colecaoFaturamentoGrupoDestino",colecaoFaturamentoGrupoDestino);
		}
		
		if (form.getIdEmpresaCobrancaDestino() != null && !form.getIdEmpresaCobrancaDestino().equals("")
				&& !form.getIdEmpresaCobrancaDestino().equals("-1")){
			
			 /* 5.4.	Empresa de Cobrança (seleciona a partir da tabela EMPRESA) 
			 * [FS0001 - Verificar existência de dados];*/
			
			filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			Collection colecaoEmpresaCobrancaDestino = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			if (colecaoEmpresaCobrancaDestino == null || colecaoEmpresaCobrancaDestino.isEmpty()){
				throw new ActionServletException("Atencao.Sem_dados_para_selecao");
			}
			
			httpServletRequest.setAttribute("colecaoEmpresaCobrancaDestino",colecaoEmpresaCobrancaDestino);
		}
		
		if (form.getIdEmpresaFaturamentoDestino() != null && !form.getIdEmpresaFaturamentoDestino().equals("")
				&& !form.getIdEmpresaFaturamentoDestino().equals("-1")){
			/*5.3.	Empresa de Faturamento (seleciona a partir da tabela EMPRESA)
			 * [FS0001 - Verificar existência de dados];*/
			
			filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			Collection colecaoEmpresaFaturamentoDestino = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			if (colecaoEmpresaFaturamentoDestino == null || colecaoEmpresaFaturamentoDestino.isEmpty()){
				throw new ActionServletException("Atencao.Sem_dados_para_selecao");
			}
			
			httpServletRequest.setAttribute("colecaoEmpresaFaturamentoDestino", colecaoEmpresaFaturamentoDestino);
		}
		
		return retorno;
	}

}
