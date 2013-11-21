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


import gcom.cadastro.cliente.FiltroProfissao;
import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * @created 04 de setembro de 2008
 */
public class ExibirInserirCobrancaSituacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirCobrancaSituacao");

		InserirCobrancaSituacaoActionForm inserirCobrancaSituacaoActionForm = (InserirCobrancaSituacaoActionForm) actionForm;
		
		
		//Motivo de Revisao da Conta
		 FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
	        
		 filtroContaMotivoRevisao.setCampoOrderBy(FiltroContaMotivoRevisao.ID);
	        
		 filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(
				 FiltroContaMotivoRevisao.INDICADOR_USO,
	        		ConstantesSistema.INDICADOR_USO_ATIVO));
	       
	        Collection colecaoPesquisa = null;
	        
	        //Retorna tipo de situacao especial de faturamento
	       colecaoPesquisa = this.getFachada().pesquisar(filtroContaMotivoRevisao,
	    		   ContaMotivoRevisao.class.getName());
	        
	        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Motivo de Revisão da Conta");
	        } else {
	            httpServletRequest.setAttribute("colecaoContaMotivoRevisao", colecaoPesquisa);
	        }
	        
	        //Profissão
	        FiltroProfissao filtroProfissao = new FiltroProfissao();
	        filtroProfissao.setCampoOrderBy(FiltroProfissao.ID);
	        filtroProfissao.adicionarParametro(new ParametroSimples(
	        		FiltroProfissao.INDICADOR_USO,
	        			ConstantesSistema.INDICADOR_USO_ATIVO));
	        Collection colecaoProfissao = null;
	        colecaoProfissao = this.getFachada().pesquisar(filtroProfissao, 
	        		Profissao.class.getName());
	        
	        if ( colecaoProfissao == null || colecaoProfissao.isEmpty() ) {
	        	throw new ActionServletException (
	        			"atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Profissão");
	        } else {
	        	httpServletRequest.setAttribute("colecaoProfissao", colecaoProfissao );
	        }
	        
	        //Ramo Atividade
	        FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
	        filtroRamoAtividade.setCampoOrderBy(FiltroRamoAtividade.ID);
	        filtroRamoAtividade.adicionarParametro(new ParametroSimples(
	        		FiltroRamoAtividade.INDICADOR_USO,
	        			ConstantesSistema.INDICADOR_USO_ATIVO));
	        Collection colecaoRamoAtividade = null;
	        colecaoRamoAtividade = this.getFachada().pesquisar(filtroRamoAtividade, 
	        		RamoAtividade.class.getName());
	        
	        if ( colecaoRamoAtividade == null || colecaoRamoAtividade.isEmpty() ) {
	        	throw new ActionServletException (
	        			"atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Ramo Atividade");
	        } else {
	        	httpServletRequest.setAttribute("colecaoRamoAtividade", colecaoRamoAtividade );
	        }
	        
        if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
        	inserirCobrancaSituacaoActionForm.setIndicadorBloqueioParcelamento(ConstantesSistema.NAO);
        	inserirCobrancaSituacaoActionForm.setIndicadorExigenciaAdvogado(ConstantesSistema.NAO); 
        	inserirCobrancaSituacaoActionForm.setIndicadorBloqueioInclusao(ConstantesSistema.NAO);
        	inserirCobrancaSituacaoActionForm.setIndicadorBloqueioRetirada(ConstantesSistema.NAO);
        	inserirCobrancaSituacaoActionForm.setIndicadorSelecaoApenasComPermissao(ConstantesSistema.NAO);
        	inserirCobrancaSituacaoActionForm.setIndicadorPrescricaoImoveisParticulares( new Integer(ConstantesSistema.NAO.toString()));
        	inserirCobrancaSituacaoActionForm.setIndicadorNaoIncluirImoveisEmCobrancaResultado( new Integer(ConstantesSistema.NAO.toString()));
        }
        
        
		return retorno;
	}
}
