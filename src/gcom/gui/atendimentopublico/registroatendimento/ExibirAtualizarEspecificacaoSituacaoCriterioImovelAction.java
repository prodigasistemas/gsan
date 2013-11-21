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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.FiltroMaterialLigacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

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
 * [UC0404] - Manter Especificação da Situação do Imóvel
 * 
 * [SB0001] - Atualizar Especificação da situação Criterio Imovel
 *
 * @author Rafael Pinto
 * 
 * @date 09/11/2006
 */

public class ExibirAtualizarEspecificacaoSituacaoCriterioImovelAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarEspecificacaoSituacaoCriterioImovel");

		AtualizarEspecificacaoSituacaoImovelActionForm form = 
			(AtualizarEspecificacaoSituacaoImovelActionForm) actionForm;
		
		HttpSession sessao = this.getSessao(httpServletRequest); 
		
		String idAtualizar = null;
		String tipoOperacao = httpServletRequest.getParameter("tipoOperacao");
		
		if(tipoOperacao != null && !tipoOperacao.equals("")){
			
			idAtualizar = form.getIdAtualizar();

			int index = 0;
			
			for (Iterator iter = form.getEspecificacaoImovelSituacaoCriterio().iterator(); iter.hasNext();) {
				
				index++;
				
				if (idAtualizar.equals(""+index)) {
					
					EspecificacaoImovSitCriterio novaEspecificacaoImovSitCriterio = new EspecificacaoImovSitCriterio();
					
					if(form.getIndicadorHidrometroLigacaoAgua() != null && 
						!form.getIndicadorHidrometroLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
						
						novaEspecificacaoImovSitCriterio.setIndicadorHidrometroLigacaoAgua(
							new Short(form.getIndicadorHidrometroLigacaoAgua()));	
					}
					
					if(form.getIndicadorHidrometroPoco() != null &&
						!form.getIndicadorHidrometroPoco().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
						novaEspecificacaoImovSitCriterio.setIndicadorHidrometroPoco(
							new Short(form.getIndicadorHidrometroPoco()));
					}		
					
					

					if(form.getSituacaoLigacaoAgua() != null &&
						!form.getSituacaoLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
						
						Integer idSituacaoLigacaoAgua = new Integer(form.getSituacaoLigacaoAgua());
						
						LigacaoAguaSituacao ligacaoAguaSituacao = this.retornaLigacaoAguaSituacao(sessao,idSituacaoLigacaoAgua);
						
						novaEspecificacaoImovSitCriterio.setLigacaoAguaSituacao(ligacaoAguaSituacao);
					}		
					
					if(form.getSituacaoLigacaoEsgoto() != null && 
						!form.getSituacaoLigacaoEsgoto().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

						Integer idSituacaoLigacaoEsgoto = new Integer(form.getSituacaoLigacaoEsgoto());
						
						LigacaoEsgotoSituacao ligacaoEsgotoSituacao = 
							this.retornaLigacaoEsgotoSituacao(sessao,idSituacaoLigacaoEsgoto);

						novaEspecificacaoImovSitCriterio.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
					}		

					// Faz as validações de inserção de especificao situacao imovel
					this.getFachada().validarExibirInsercaoEspecificacaoImovSitCriterio(
						form.getEspecificacaoImovelSituacaoCriterio(),novaEspecificacaoImovSitCriterio);

					EspecificacaoImovSitCriterio especificacaoImovSitCriterio = (EspecificacaoImovSitCriterio) iter.next();
					
					especificacaoImovSitCriterio.setIndicadorHidrometroLigacaoAgua(
						novaEspecificacaoImovSitCriterio.getIndicadorHidrometroLigacaoAgua());
					
					especificacaoImovSitCriterio.setIndicadorHidrometroPoco(
						novaEspecificacaoImovSitCriterio.getIndicadorHidrometroPoco());
					
					especificacaoImovSitCriterio.setLigacaoAguaSituacao(
						novaEspecificacaoImovSitCriterio.getLigacaoAguaSituacao());							
					
					especificacaoImovSitCriterio.setLigacaoEsgotoSituacao(
						novaEspecificacaoImovSitCriterio.getLigacaoEsgotoSituacao());
					
					especificacaoImovSitCriterio.setUltimaAlteracao(new Date());					
					
					httpServletRequest.setAttribute("fechaPopup", "true");
				}//fim do if index
			}
			
		}else{
		
			idAtualizar = httpServletRequest.getParameter("idAtualizar");
			
			EspecificacaoImovSitCriterio especificacaoImovSitCriterio = null;
			
			int index = 0;
			
			for (Iterator iter = form.getEspecificacaoImovelSituacaoCriterio().iterator(); iter.hasNext();) {
				
				index++;
				
				EspecificacaoImovSitCriterio element = (EspecificacaoImovSitCriterio) iter.next();
				
				if (idAtualizar.equals(""+index)) {
					especificacaoImovSitCriterio = element;
					break;
				}
			}
			
			form.setIdAtualizar(idAtualizar);
			form.setIdEspecificacaoCriterio(""+especificacaoImovSitCriterio.getId());
			
			if(especificacaoImovSitCriterio.getIndicadorHidrometroLigacaoAgua() != null){
				form.setIndicadorHidrometroLigacaoAgua(
					""+especificacaoImovSitCriterio.getIndicadorHidrometroLigacaoAgua());	
			}
			
			if(especificacaoImovSitCriterio.getIndicadorHidrometroPoco() != null){
				form.setIndicadorHidrometroPoco(
					""+especificacaoImovSitCriterio.getIndicadorHidrometroPoco());		
			}		
			
			if(especificacaoImovSitCriterio.getLigacaoAguaSituacao() != null){
				form.setSituacaoLigacaoAgua(""+especificacaoImovSitCriterio.getLigacaoAguaSituacao().getId());			
			}		
			
			if(especificacaoImovSitCriterio.getLigacaoEsgotoSituacao() != null){
				form.setSituacaoLigacaoEsgoto(""+especificacaoImovSitCriterio.getLigacaoEsgotoSituacao().getId());
			}		
			
			
			this.consultaSelectObrigatorio(sessao);
		}		
		return retorno;
	}

	
	private void consultaSelectObrigatorio(HttpSession sessao){
		
		// Filtro para o campo Situação Ligação Água
		Collection colecaoSituacaoLigacaoAgua = (Collection) 
			sessao.getAttribute("colecaoSituacaoLigacaoAgua");

		if(colecaoSituacaoLigacaoAgua == null){

			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoSituacaoLigacaoAgua = 
				this.getFachada().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

			if (colecaoSituacaoLigacaoAgua != null && !colecaoSituacaoLigacaoAgua.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoLigacaoAgua",colecaoSituacaoLigacaoAgua);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Situação Ligação de Agua");
			}
		}	

		// Filtro para o campo Situacao da Ligação Esgoto
		Collection colecaoSituacaoLigacaoEsgoto = (Collection) 
			sessao.getAttribute("colecaoSituacaoLigacaoEsgoto");

		if(colecaoSituacaoLigacaoEsgoto == null){

			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoSituacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroMaterialLigacao.DESCRICAO);

			colecaoSituacaoLigacaoEsgoto = 
				this.getFachada().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());

			if (colecaoSituacaoLigacaoEsgoto != null && !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoLigacaoEsgoto",colecaoSituacaoLigacaoEsgoto);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Situação Ligação de Esgoto");
			}
		}
	}	

	//retorna LigacaoAguaSituacao pelo id na colecao de ligacaoAguaSituacao	
	private LigacaoAguaSituacao retornaLigacaoAguaSituacao(HttpSession sessao,Integer id){

		LigacaoAguaSituacao retorno = null;

		Collection colecao = (Collection) sessao.getAttribute("colecaoSituacaoLigacaoAgua");
		
		if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) itera.next();
				
				if(ligacaoAguaSituacao.getId().intValue() == id.intValue()){
					retorno = ligacaoAguaSituacao;
					break;
				}
			}
		}

		return retorno;
	}

	//retorna LigacaoEsgotoSituacao pelo id na colecao de ligacaoEsgotoSituacao	
	private LigacaoEsgotoSituacao retornaLigacaoEsgotoSituacao(HttpSession sessao,Integer id){

		LigacaoEsgotoSituacao retorno = null;

		Collection colecao = (Collection) sessao.getAttribute("colecaoSituacaoLigacaoEsgoto");
		
		if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) itera.next();
				
				if(ligacaoEsgotoSituacao.getId().intValue() == id.intValue()){
					retorno = ligacaoEsgotoSituacao;
					break;
				}
			}
		}

		return retorno;
	}	
}