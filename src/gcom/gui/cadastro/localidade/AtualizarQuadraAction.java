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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/***
 * @author Administrador, Ivan Sergio
 * @date 16/02/2009
 * @alteracao 16/02/2009 - CRC1178 - Adicionado o Indicador de Incremento do Lote
 */
public class AtualizarQuadraAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarQuadraActionForm atualizarQuadraActionForm = (AtualizarQuadraActionForm) actionForm;
        
        Quadra quadraParaManter = (Quadra) sessao.getAttribute("quadraManter");
        
        //CARREGANDO O OBJETO INSERIR_QUADRA_HELPER
		InserirQuadraHelper helper = this.carregarInserirQuadraHelper(atualizarQuadraActionForm);
		
        //VALIDANDO OS DADOS DA QUADRA
		Quadra quadraAtualizar = fachada.validarQuadra(helper);
		quadraAtualizar.setId(quadraParaManter.getId());
		quadraAtualizar.setUltimaAlteracao(quadraParaManter.getUltimaAlteracao());
		quadraAtualizar.setIndicadorBloqueio(new Short(atualizarQuadraActionForm.getIndicadorBloqueio()));
		
		//OBTENDO AS FACES DA QUADRA
		Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");

        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        if(quadraAtualizar.getRota() != null){
        	if(quadraAtualizar.getRota().getIndicadorRotaAlternativa().shortValue() == 2){
            	fachada.atualizarQuadra(quadraAtualizar, usuarioLogado, colecaoQuadraFace);	
            }else{
            	throw new ActionServletException(
				"atencao.rota_alternativa_nao_pode_associar_quadra");
            }
        }

        montarPaginaSucesso(httpServletRequest,
                "Quadra de número  " + helper.getQuadraNM() +
                " do setor comercial " + helper.getSetorComercialCD() + 
                "-" + quadraAtualizar.getSetorComercial().getDescricao() +
                " da localidade " + helper.getLocalidadeID() + 
                "-" + quadraAtualizar.getSetorComercial().getLocalidade().getDescricao() +
                " atualizada com sucesso.",
                "Realizar outra Manutenção de Quadra", "exibirFiltrarQuadraAction.do?desfazer=S");
        
        sessao.removeAttribute("quadraManter");
        sessao.removeAttribute("colecaoPerfilQuadra");
        sessao.removeAttribute("colecaoSistemaEsgoto");
        sessao.removeAttribute("colecaoZeis");
        sessao.removeAttribute("colecaoBacia");
        sessao.removeAttribute("colecaoQuadraFace");

        return retorno;
    }
    
    private InserirQuadraHelper carregarInserirQuadraHelper(AtualizarQuadraActionForm atualizarQuadraActionForm){
		
		InserirQuadraHelper helper = new InserirQuadraHelper();
		
		helper.setQuadraId(atualizarQuadraActionForm.getQuadraID());
		helper.setIndicadorUso(atualizarQuadraActionForm.getIndicadorUso());
		
		helper.setLocalidadeID(atualizarQuadraActionForm.getLocalidadeID());
		helper.setSetorComercialCD(atualizarQuadraActionForm.getSetorComercialCD());
		helper.setQuadraNM(atualizarQuadraActionForm.getQuadraNM());
		helper.setPerfilQuadraID(atualizarQuadraActionForm.getPerfilQuadra());
		helper.setAreaTipoID(atualizarQuadraActionForm.getAreaTipoID());
		helper.setIndicadorRedeAgua(atualizarQuadraActionForm.getIndicadorRedeAguaAux());
		helper.setIndicadorRedeEsgoto(atualizarQuadraActionForm.getIndicadorRedeEsgotoAux());
		helper.setSistemaEsgotoID(atualizarQuadraActionForm.getSistemaEsgotoID());
		helper.setBaciaID(atualizarQuadraActionForm.getBaciaID());
		helper.setDistritoOperacionalID(atualizarQuadraActionForm.getDistritoOperacionalID());
		helper.setSetorCensitarioID(atualizarQuadraActionForm.getSetorCensitarioID());
		helper.setZeisID(atualizarQuadraActionForm.getZeisID());
		helper.setRotaCD(atualizarQuadraActionForm.getCodigoRota());
		/*
         * TODO - COSANPA - Mantis 536 - Felipe Santos - 08/03/2012
         * 
         * Adição do id da rota no helper para pesquisa
         */
		helper.setRotaID(atualizarQuadraActionForm.getRotaID());
		// fim da alteração
		helper.setIndicadorIncrementoLote(atualizarQuadraActionForm.getIndicadorIncrementoLote());
		
		helper.setBairroCD(atualizarQuadraActionForm.getBairroID());
		helper.setMunicipioID(atualizarQuadraActionForm.getMunicipioID());
		
		return helper;
	}

}
