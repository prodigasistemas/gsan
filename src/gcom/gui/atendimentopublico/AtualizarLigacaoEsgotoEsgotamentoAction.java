
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
package gcom.gui.atendimentopublico;


import java.util.Collection;
import java.util.Date;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarLigacaoEsgotoEsgotamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarLigacaoEsgotoEsgotamentoActionForm atualizarLigacaoEsgotoEsgotamentoActionForm = (AtualizarLigacaoEsgotoEsgotamentoActionForm) actionForm;

		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento= (LigacaoEsgotoEsgotamento) sessao.getAttribute("atualizarLigacaoEsgotoEsgotamento");
		
		Collection colecaoPesquisa = null;
		
		if(atualizarLigacaoEsgotoEsgotamentoActionForm.getCodigo()!= null 
				&& !atualizarLigacaoEsgotoEsgotamentoActionForm.getCodigo().equals("")){
			ligacaoEsgotoEsgotamento.setId(new Integer(atualizarLigacaoEsgotoEsgotamentoActionForm.getCodigo()));
		}else{
			ligacaoEsgotoEsgotamento.setId(null);
		}
		
		String codigo = atualizarLigacaoEsgotoEsgotamentoActionForm.getCodigo();		
        String descricao = atualizarLigacaoEsgotoEsgotamentoActionForm.getDescricao();
        Short indicadorUso = atualizarLigacaoEsgotoEsgotamentoActionForm.getIndicadorUso();
        String quantidadeMesesSituacaoEspecial = atualizarLigacaoEsgotoEsgotamentoActionForm.getQuantidadeMesesSituacaoEspecial();
        String faturamentoSituacaoTipo = atualizarLigacaoEsgotoEsgotamentoActionForm.getFaturamentoSituacaoTipo();
        String faturamentoSituacaoMotivo = atualizarLigacaoEsgotoEsgotamentoActionForm.getFaturamentoSituacaoMotivo();
        
        
        ligacaoEsgotoEsgotamento.setDescricao(descricao);
        
        if(quantidadeMesesSituacaoEspecial != null && !quantidadeMesesSituacaoEspecial.equals("")){
        
        	ligacaoEsgotoEsgotamento.setQuantidadeMesesSituacaoEspecial(new Integer (quantidadeMesesSituacaoEspecial));
       
        } else {
        
        	ligacaoEsgotoEsgotamento.setQuantidadeMesesSituacaoEspecial(null);
       
        }
        
        
        if(codigo != null && !codigo.equals("")){
        	ligacaoEsgotoEsgotamento.setId(new Integer(codigo));
        }else{
        	ligacaoEsgotoEsgotamento.setId(null);
        }
        
        ligacaoEsgotoEsgotamento.setIndicadorUso(new Short(indicadorUso));
        ligacaoEsgotoEsgotamento.setUltimaAlteracao(new Date());

        //Tipo Situação Especial Faturamento
		if(faturamentoSituacaoTipo != null && !faturamentoSituacaoTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FaturamentoSituacaoTipo faturamentoTipo = new FaturamentoSituacaoTipo();
			faturamentoTipo.setId(new Integer(atualizarLigacaoEsgotoEsgotamentoActionForm
					.getFaturamentoSituacaoTipo()));
			
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoTipo(faturamentoTipo);
			
		} else {
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoTipo(null);
		}
		
		//Motivo Situacao Especial Faturamento
		if(faturamentoSituacaoMotivo != null && !faturamentoSituacaoMotivo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FaturamentoSituacaoMotivo faturamentoMotivo = new FaturamentoSituacaoMotivo();
			faturamentoMotivo.setId(new Integer(atualizarLigacaoEsgotoEsgotamentoActionForm
					.getFaturamentoSituacaoMotivo()));
			
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoMotivo(faturamentoMotivo);

		} else{
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoMotivo(null);
		}


        FiltroLigacaoEsgotoEsgotamento filtroLigEsgEsgotamento= new FiltroLigacaoEsgotoEsgotamento();
        filtroLigEsgEsgotamento.adicionarParametro(
				new ParametroSimples(FiltroFonteAbastecimento.DESCRICAO, descricao));
        filtroLigEsgEsgotamento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroLigacaoEsgotoEsgotamento.ID, ligacaoEsgotoEsgotamento.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroLigEsgEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		
		FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
		filtroLigacaoEsgotoEsgotamento.adicionarParametro(
					new ParametroSimples(FiltroFonteAbastecimento.ID, codigo));
		filtroLigacaoEsgotoEsgotamento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroLigacaoEsgotoEsgotamento.ID, ligacaoEsgotoEsgotamento.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigo+"");
		}
		
		fachada.atualizar(ligacaoEsgotoEsgotamento);

		
		montarPaginaSucesso(httpServletRequest, "Ligação de Esgoto Esgotamento "
				+ descricao + " atualizado com sucesso.",
				"Realizar outra Manutenção da Ligação de Esgoto Esgotamento",
				"exibirFiltrarLigacaoEsgotoEsgotamentoAction.do?menu=sim");        
        
		return retorno;
	}
}