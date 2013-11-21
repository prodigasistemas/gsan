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

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.RoteiroEmpresa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza a inclusão da rota de acordo com os parâmetros informados
 * 
 * @author Vivianne Sousa
 * @created 21/03/2006
 */
public class InserirRoteiroEmpresaAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclusão de um novo roteiro empresa
	 * 
	 * [UC0583] Inserir Roteiro Empresa
	 * 
	 * 
	 * @author Francisco Nascimento
	 * @date 25/07/2007
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


        ActionForward retorno = actionMapping.findForward("telaSucesso");
        InserirRoteiroEmpresaActionForm form = (InserirRoteiroEmpresaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);
        
        // obrigatórios
        String idEmpresa = form.getEmpresa();
        String idLocalidade = form.getIdLocalidade();
		String idFaturamentoGrupo = form.getFaturamentoGrupo(); 				
		String idLeiturista = form.getIdLeiturista();
		
		String[] idQuadrasAdicionar = form.getIdQuadraAdicionar();

		// n obrigatórios	
		
		validacaoRoteiroEmpresa (idEmpresa, idLocalidade, idFaturamentoGrupo, idLeiturista,  
				idQuadrasAdicionar,fachada,httpServletRequest);
		
        RoteiroEmpresa roteiro = new RoteiroEmpresa();
        
        if(idEmpresa != null && !idEmpresa.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	Empresa empresa = new Empresa();
        	empresa.setId(new Integer(idEmpresa));
        	roteiro.setEmpresa(empresa);
        }

        Leiturista leiturista = null;
        if (idLeiturista != null && !idLeiturista.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
       	
        	// Validar se a empresa escolhida for diferente da empresa a qual pertence o funcionário ou o cliente
			FiltroLeiturista filtro = new FiltroLeiturista();
			
			// Adiciona o id do leiturista
			filtro.adicionarParametro(new ParametroSimples(
				FiltroLeiturista.ID, idLeiturista));

			filtro.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna coleção de leituristas
			Collection colecaoLeituristas = fachada.pesquisar(filtro, Leiturista.class.getName());
			leiturista = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeituristas);

			if (leiturista.getEmpresa().getId().intValue() != Integer.parseInt(idEmpresa)){
				throw new ActionServletException("atencao.empresa_diferente");
			}
			
        	roteiro.setLeiturista(leiturista);
        	
        } 

        //Indicador de Uso
        roteiro.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

        //Ultima alteração
        roteiro.setUltimaAlteracao(new Date());
       
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        Integer idRoteiro = fachada.inserirRoteiroEmpresa(roteiro, idQuadrasAdicionar, usuarioLogado);

        
		sessao.removeAttribute("reloadPageURL");
		sessao.removeAttribute("reloadPage");
		sessao.removeAttribute("colecaoEmpresa");
		sessao.removeAttribute("colecaoFaturamentoGrupo");
		sessao.removeAttribute("colecaoSetorComercial");
		sessao.removeAttribute("colecaoSetoresSelecionados");
		sessao.removeAttribute("colecaoQuadras");
		
        montarPaginaSucesso(httpServletRequest, "Roteiro Empresa (" +
        	idRoteiro +	") inserido com sucesso.", 
        			"Inserir outro Roteiro",
                    "exibirInserirRoteiroEmpresaAction.do?desfazer=S",
                    "exibirAtualizarRoteiroEmpresaAction.do?idRegistroInseridoAtualizar="
					+ idRoteiro,
					"Atualizar Roteiro Empresa Inserido");
        
       return retorno;
    }
    
//		validacaoRoteiroEmpresa (empresa, idLocalidade, idFaturamentoGrupo, idFuncionario, idCliente, 
//	quadras,fachada,httpServletRequest);

    private void validacaoRoteiroEmpresa (String empresa, String idLocalidade
    		,String idFaturamentoGrupo, String idLeiturista
    		,String[] quadrasAdicionar
    		,Fachada fachada
    		,HttpServletRequest httpServletRequest
    		){
    	
    	//Empresa é obrigatório.
		if ((empresa == null) || (empresa.equals(""
				+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","empresa");
			throw new ActionServletException("atencao.empresa_nao_informada");			
		}else if (Util.validarValorNaoNumerico(empresa)){
			//Empresa não numérico.
			httpServletRequest.setAttribute("nomeCampo","empresa");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Empresa");		
		}
    	//Localidade é obrigatório.
		if ((idLocalidade == null) || (idLocalidade.equals(""))) {
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.localidade_nao_informada");			
		}else if (Util.validarValorNaoNumerico(idLocalidade)){
			
			//Localidade não numérico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade");		
		} else {
			verificaExistenciaIdLocalidade(idLocalidade, fachada, httpServletRequest);
		}
		
		// O grupo de faturamento é obrigatório.
		if ((idFaturamentoGrupo == null)
				|| (idFaturamentoGrupo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","faturamentoGrupo");
			throw new ActionServletException(
					"atencao.faturamento_grupo_nao_informado");
		}
		
		// O leiturista é obrigatório: funcionário ou cliente
		if (idLeiturista == null || idLeiturista.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			httpServletRequest.setAttribute("nomeCampo","leiturista");
			throw new ActionServletException("atencao.leiturista_tipo_nao_informado");
		}

		
		//[FS0010] Verificar inexistência de alguma quadra
		if (quadrasAdicionar == null || quadrasAdicionar.length == 0){
			throw new ActionServletException(
			"atencao.quadras.informar");			
		}
		
    }
    
    private void verificaExistenciaIdLocalidade(String idDigitadoEnterLocalidade,			
			Fachada fachada,HttpServletRequest httpServletRequest) {

		//Verifica se o código da localidade foi digitado
		if ((idDigitadoEnterLocalidade != null && 
				!idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase(""))) {
		
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			if (idDigitadoEnterLocalidade != null
				&& !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {
				
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, new Integer(idDigitadoEnterLocalidade)));
			}
			
			Collection<Localidade> localidades = fachada.pesquisar(
					filtroLocalidade, Localidade.class.getName());
					
			if (localidades == null || localidades.isEmpty()) {
				//a localidade não foi encontrada
				//Setor Comercial não existe para esta localidade
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
				throw new ActionServletException(
				"atencao.setor_comercial_nao_existe");						
			}
		
		}
	
	}

}
 