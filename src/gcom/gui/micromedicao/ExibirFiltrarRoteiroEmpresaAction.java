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

import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pré-processamento da página de Filtrar Roteiro Empresa
 * 
 * @author Francisco Nascimento
 * @created 10/08/2007
 */
public class ExibirFiltrarRoteiroEmpresaAction extends GcomAction {
	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de Roteiro Empresa
	 * 
	 * [UC0585] Filtrar Roteiro Empresa
	 * 
	 * 
	 * @author Francisco Nascimento
	 * @date 10/08/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("filtrarRoteiroEmpresa");
        FiltrarRoteiroEmpresaActionForm form = (FiltrarRoteiroEmpresaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false); 
        
		sessao.removeAttribute("equipeAtualizar");

		if (httpServletRequest.getParameter("menu") != null) {

			form.setAtualizar("1");
			form.setIndicadorUso("");
			httpServletRequest.setAttribute("nomeCampo", "codigo");
			sessao.setAttribute("atualizar", "1");

		} else {

			if (httpServletRequest.getParameter("paginacao") == null && sessao.getAttribute("filtrar") == null) {

				String atualizar = httpServletRequest.getParameter("atualizar");

				if (atualizar != null && !atualizar.equals("")) {
					sessao.setAttribute("atualizar", atualizar);
				} else {
					sessao.removeAttribute("atualizar");
				}

			}
		}
        
        //Pesquisando empresas
        if (sessao.getAttribute("colecaoEmpresa") == null){
        	
        	FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEsferaPoder.DESCRICAO);
        	filtroEmpresa.setConsultaSemLimites(true);
        	
        	filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, 
        			Empresa.class.getName());
        	
        	if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "EMPRESA");
        	}
        	
        	sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
        }
        //Fim de pesquisando empresas
       
        Collection colecaoSetorComercial = (Collection) sessao.getAttribute("colecaoSetorComercial");        
        String idLocalidade = form.getIdLocalidade();
        
        // Pesquisando Setor Comercial
		if (idLocalidade != null
				&& !idLocalidade.trim().equalsIgnoreCase("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			
			// Adiciona o id da localidade que está no formulário para
			// compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna coleção de setor comercial
			colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial,
					SetorComercial.class.getName());

			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {			
				sessao.setAttribute("colecaoSetorComercial",
						colecaoSetorComercial);
			} 
		} 
		if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
			sessao.setAttribute("colecaoSetorComercial", new ArrayList());			
		}
		
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------
        	
        	form.setIdLocalidade("");
        	form.setDescricaoLocalidade("");
        	form.setEmpresa("");
        	form.setIdLeiturista("");
        	form.setNomeLeiturista("");
        	form.setIdSetorComercial("");
        	form.setIndicadorUso("3");
        	sessao.setAttribute("indicadorAtualizar","1");
        	sessao.removeAttribute("caminhoRetornoTelaPesquisa");
        }
        
		if (form.getIndicadorUso() == null 
        		|| form.getIndicadorUso().equalsIgnoreCase("")){
			form.setIndicadorUso("3");
        }

        // código para checar ou naum o Atualizar
        String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar","1");
			form.setIndicadorUso("3");
		}
		// se voltou da tela de Atualizar Localidade
		if (sessao.getAttribute("voltar") !=null &&
				sessao.getAttribute("voltar").equals("filtrar")){
			sessao.setAttribute("indicadorAtualizar","1");
		}
		
        //-------Parte que trata do código quando o usuário tecla enter     
        String idDigitadoEnterLocalidade = form.getIdLocalidade();
    	if (idDigitadoEnterLocalidade != null &&
    			!idDigitadoEnterLocalidade.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idDigitadoEnterLocalidade)){
			//Localidade não numérico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade ");		
		} else {
	        verificaExistenciaCodLocalidade(idDigitadoEnterLocalidade,form,
	       			httpServletRequest,fachada);			
		}

        String idDigitadoEnterLeiturista = form.getIdLeiturista();
    	if (idDigitadoEnterLeiturista != null &&
    			!idDigitadoEnterLeiturista.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idDigitadoEnterLeiturista)){
			//Leiturista não numérico.
			httpServletRequest.setAttribute("nomeCampo","idLeiturista");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Leiturista ");		
		} else {
	        verificaExistenciaCodLeiturista(idDigitadoEnterLeiturista,form,
	       			httpServletRequest,fachada);       			
		}
       //-------Fim de parte que trata do código quando o usuário tecla enter
                          
        
        sessao.removeAttribute("caminhoRetornoTelaPesquisa");
		// DEFINIÇÃO QUE IRÁ AUXILIAR O RETORNO DOS POPUPS
		sessao.setAttribute("UseCase", "FILTRARROTEIROEMPRESA");
        
       return retorno;
    }
    
    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, 
    											FiltrarRoteiroEmpresaActionForm form,
    											HttpServletRequest httpServletRequest,
    											Fachada fachada) {
    	
        //Verifica se o código da Localidade foi digitado
        if (idDigitadoEnterLocalidade != null
				&& !idDigitadoEnterLocalidade.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterLocalidade) > 0) {
	        
	        //Recupera a localidade informada pelo usuário
	        Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));
	        
	        //Caso a localidade informada pelo usuário esteja cadastrada no sistema
	        //Seta os dados da localidade no form
	        //Caso contrário seta as informações da localidade para vazio 
	        //e indica ao usuário que a localidade não existe 
	
			if (localidadeEncontrada != null) {
				//a localidade foi encontrada
				form.setIdLocalidade("" + (localidadeEncontrada.getId()));
				form.setDescricaoLocalidade(localidadeEncontrada.getDescricao());
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
				"true");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			} else {
				//a localidade não foi encontrada
				form.setIdLocalidade("");
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
						"exception");
				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
	
			}
        }

    }
    
    private void verificaExistenciaCodLeiturista(String idDigitadoEnterLeiturista, 
			FiltrarRoteiroEmpresaActionForm inserirRoteiroEmpresaActionForm,
			HttpServletRequest httpServletRequest,
			Fachada fachada) {
		
		//Verifica se o código do Leiturista foi digitado
		if (idDigitadoEnterLeiturista != null
			&& !idDigitadoEnterLeiturista.trim().equals("")
			&& Integer.parseInt(idDigitadoEnterLeiturista) > 0) {
			
			//Recupera o leiturista informado pelo usuário
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.ID, idDigitadoEnterLeiturista));
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection leituristaEncontrado = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());
			
			//Caso o leiturista informado pelo usuário esteja cadastrado no sistema
			//Seta os dados do leiturista no form
			//Caso contrário seta as informações de leiturista para vazio 
			//e indica ao usuário que o leiturista não existe 
			
			if (leituristaEncontrado != null && leituristaEncontrado.size() > 0) {
				//leiturista foi encontrado
				Leiturista leiturista = (Leiturista) ((List) leituristaEncontrado).get(0); 
				inserirRoteiroEmpresaActionForm.setIdLeiturista("" + 
					leiturista.getId());
				if (leiturista.getFuncionario() != null){
					inserirRoteiroEmpresaActionForm.setNomeLeiturista(leiturista.getFuncionario().getNome());					
				} else if (leiturista.getCliente() != null){
					inserirRoteiroEmpresaActionForm.setNomeLeiturista(leiturista.getCliente().getNome());
				}
				httpServletRequest.setAttribute("idLeituristaEncontrado","true");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			} else {
				//o leiturista não foi encontrado
				inserirRoteiroEmpresaActionForm.setIdLeiturista("");
				inserirRoteiroEmpresaActionForm.setNomeLeiturista("LEITURISTA INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","idLeiturista");
			}
		}
		
    }

    
}
 