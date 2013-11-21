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
package gcom.gui.cadastro.imovel;

import gcom.batch.Processo;
import gcom.cadastro.imovel.bean.GerarArquivoTextoAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarArquivoTextoAtualizacaoCadastralDispositivoMovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
        GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form = (GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm) actionForm;
        
        Collection colecaoImovelFiltrado = (Collection)sessao.getAttribute("colecaoImovelFiltrado");
        
        GerarArquivoTextoAtualizacaoCadastralHelper helper = new GerarArquivoTextoAtualizacaoCadastralHelper();
        
        helper.setDescricao(form.getDescricaoArquivo());
        helper.setIdLeiturista(new Integer(form.getIdLeiturista()));
        if(form.getLocalidade()!= null && !form.getLocalidade().equals("")){
          helper.setIdLocalidade(new Integer(form.getLocalidade()));
          if(form.getSetorComercialCD()!= null && !form.getSetorComercialCD().equals("")){
	          helper.setSetorComercialCD(new Integer(form.getSetorComercialCD()));
	          if(form.getQuadraInicial() != null && !form.getQuadraInicial().equals("")){
	        	 helper.setNumeroQuadraInicial(new Integer(form.getQuadraInicial()));
	        	 helper.setNumeroQuadraFinal(new Integer(form.getQuadraFinal()));
	        	 // Alterado por Tiago Moreno - 23/07/2009 - CRC2374
	        	 if (form.getCodigoRota() != null && !form.getCodigoRota().equals("")){
	        		 helper.setRotaCD(new Integer(form.getCodigoRota()));
	        	 }
	          }else{
	        	  // Alterado por Tiago Moreno - 23/07/2009 - CRC2374
	        	 if (form.getCodigoRota() != null && !form.getCodigoRota().equals("")){
		        	 helper.setRotaCD(new Integer(form.getCodigoRota()));
		         } 
	          }
           }
        }
        helper.setSituacao(SituacaoTransmissaoLeitura.LIBERADO);
        helper.setQtdImovel(form.getTamanhoColecaoImovel());
        
        if ( colecaoImovelFiltrado != null && !colecaoImovelFiltrado.isEmpty() ) {
        	helper.setColecaoImovel(colecaoImovelFiltrado);
        }
        
		Map parametros = new HashMap();
		parametros.put("gerarArquivoTextoAtualizacaoCadastralHelper",helper);

		Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
		             		Processo.GERAR_ARQUIVO_TEXTO_ATU_CADASTRAL ,
		             		this.getUsuarioLogado(httpServletRequest));
		
		montarPaginaSucesso(httpServletRequest, "Geração do arquivo texto encaminhada para Batch.", "", "");

		return retorno;
	}
	

	
}
