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

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe responsável pela inserção dos dados de uma quadra 
 *
 * @author Ivan Sérgio, Raphael Rossiter
 * @date 10/02/2009, 06/04/2009
 */
public class InserirQuadraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirQuadraActionForm inserirQuadraActionForm = (InserirQuadraActionForm) actionForm;
		
		
		//Verificar a existencia de Setor alternativo
		String setorComercialId = inserirQuadraActionForm.getSetorComercialID();
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.ID,
    			setorComercialId ) );
    	
    	Collection setorComercial = this.getFachada()
    			.pesquisar( filtroSetorComercial, SetorComercial.class.getName() );

    	Iterator iteratorSetorComercial = setorComercial.iterator();
    	SetorComercial setor = null;
    
    	while ( iteratorSetorComercial.hasNext() ) {
    	
    		setor = (SetorComercial) iteratorSetorComercial.next();
            
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ) {
    			
    			throw new ActionServletException("atencao.setor_comercial_alternativo");
    		}
    	}
		//CARREGANDO O OBJETO INSERIR_QUADRA_HELPER
		InserirQuadraHelper helper = this.carregarInserirQuadraHelper(inserirQuadraActionForm);
		
		//VALIDANDO OS DADOS DA QUADRA
		Quadra quadraInserir = fachada.validarQuadra(helper);
		
		//OBTENDO AS FACES DA QUADRA
		Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");

		Integer idQuadra = null;
		
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		idQuadra = fachada.inserirQuadra(quadraInserir, colecaoQuadraFace, usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Quadra de número "
				+ quadraInserir.getNumeroQuadra() + " do setor comercial "
				+ helper.getSetorComercialCD() + "-" + quadraInserir.getSetorComercial().getDescricao()
				+ " da localidade " + helper.getLocalidadeID() + "-"
				+ quadraInserir.getSetorComercial().getLocalidade().getDescricao() + " inserida com sucesso.",
				"Inserir outra Quadra", "exibirInserirQuadraAction.do",
				"exibirAtualizarQuadraAction.do?idRegistroInseridoAtualizar="
				+ idQuadra, "Atualizar Quadra Inserida");

		sessao.removeAttribute("InserirQuadraActionForm");
		sessao.removeAttribute("colecaoPerfilQuadra");
		sessao.removeAttribute("colecaoSistemaEsgoto");
		sessao.removeAttribute("colecaoZeis");
		sessao.removeAttribute("colecaoBacia");
		sessao.removeAttribute("colecaoQuadraFace");

		// devolve o mapeamento de retorno
		return retorno;
	}
	
	
	private InserirQuadraHelper carregarInserirQuadraHelper(InserirQuadraActionForm inserirQuadraActionForm){
		
		InserirQuadraHelper helper = new InserirQuadraHelper();
		
		helper.setLocalidadeID(inserirQuadraActionForm.getLocalidadeID());
		helper.setSetorComercialCD(inserirQuadraActionForm.getSetorComercialCD());
		helper.setQuadraNM(inserirQuadraActionForm.getQuadraNM());
		helper.setPerfilQuadraID(inserirQuadraActionForm.getPerfilQuadra());
		helper.setAreaTipoID(inserirQuadraActionForm.getAreaTipoID());
		helper.setIndicadorRedeAgua(inserirQuadraActionForm.getIndicadorRedeAguaAux());
		helper.setIndicadorRedeEsgoto(inserirQuadraActionForm.getIndicadorRedeEsgotoAux());
		helper.setSistemaEsgotoID(inserirQuadraActionForm.getSistemaEsgotoID());
		helper.setBaciaID(inserirQuadraActionForm.getBaciaID());
		helper.setDistritoOperacionalID(inserirQuadraActionForm.getDistritoOperacionalID());
		helper.setSetorCensitarioID(inserirQuadraActionForm.getSetorCensitarioID());
		helper.setZeisID(inserirQuadraActionForm.getZeisID());
		helper.setRotaCD(inserirQuadraActionForm.getCodigoRota());
		/*
         * TODO - COSANPA - Mantis 536 - Felipe Santos - 14/03/2012
         * 
         * Adição do id da rota no helper para pesquisa
         */
		helper.setRotaID(inserirQuadraActionForm.getRotaID());
		// fim da alteração
		helper.setIndicadorIncrementoLote(inserirQuadraActionForm.getIndicadorIncrementoLote());
		helper.setBairroCD(inserirQuadraActionForm.getBairroID());
		helper.setMunicipioID(inserirQuadraActionForm.getMunicipioID());
		
		return helper;
	}

}
