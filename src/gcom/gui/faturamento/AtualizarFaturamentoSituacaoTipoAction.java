
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
package gcom.gui.faturamento;





import java.util.Collection;
import java.util.Date;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarFaturamentoSituacaoTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarFaturamentoSituacaoTipoActionForm atualizarFaturamentoSituacaoTipoActionForm = (AtualizarFaturamentoSituacaoTipoActionForm) actionForm;

		FaturamentoSituacaoTipo faturamentoSituacaoTipo= (FaturamentoSituacaoTipo) sessao.getAttribute("atualizarFaturamentoSituacaoTipo");
		
		if(atualizarFaturamentoSituacaoTipoActionForm.getCodigo()!= null 
				&& !atualizarFaturamentoSituacaoTipoActionForm.getCodigo().equals("")){
			faturamentoSituacaoTipo.setId(new Integer(atualizarFaturamentoSituacaoTipoActionForm.getCodigo()));
		}else{
			faturamentoSituacaoTipo.setId(null);
		}
		
		String codigo = atualizarFaturamentoSituacaoTipoActionForm.getCodigo();
		String descricao = atualizarFaturamentoSituacaoTipoActionForm.getDescricao();
		String indicadorParalisacaoFaturamento = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoFaturamento();
		String indicadorParalisacaoLeitura = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoLeitura();
		String indicadorValidoAgua = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorValidoAgua();
		String indicadorValidoEsgoto = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorValidoEsgoto();
		String indicadorInformarConsumo = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorInformarConsumo();
		String indicadorInformarVolume = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorInformarVolume();
		String indicadorUso = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorUso();
		String leituraAnormalidadeLeituraComLeitura = atualizarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeLeituraComLeitura();
		String leituraAnormalidadeLeituraSemLeitura = atualizarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeLeituraSemLeitura();
		String leituraAnormalidadeConsumoComLeitura = atualizarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeConsumoComLeitura();
		String leituraAnormalidadeConsumoSemLeitura = atualizarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeConsumoSemLeitura();
		
       
        Collection colecaoPesquisa = null;
		
        faturamentoSituacaoTipo.setDescricao(descricao);
        faturamentoSituacaoTipo.setUltimaAlteracao(new Date());
        
        //Indicador de Uso
        faturamentoSituacaoTipo.setIndicadorUso(new Short(indicadorUso));

        //Indicador Paralisacao Faturamento
        faturamentoSituacaoTipo.setIndicadorParalisacaoFaturamento(new Short(indicadorParalisacaoFaturamento));
        
        //Indicador Paralisacao Leitura
        faturamentoSituacaoTipo.setIndicadorParalisacaoLeitura(new Short(indicadorParalisacaoLeitura));
        
        //Indicador Valido Água
        faturamentoSituacaoTipo.setIndicadorValidoAgua(new Short(indicadorValidoAgua));
        
        //Indicador Valido Esgoto
        faturamentoSituacaoTipo.setIndicadorValidoEsgoto(new Short(indicadorValidoEsgoto));
        
        //Indicador Informar Consumo
        faturamentoSituacaoTipo.setIndicadorInformarConsumo(new Short (indicadorInformarConsumo));
        
        //Indicador Informar Volume
        faturamentoSituacaoTipo.setIndicadorInformarVolume(new Short (indicadorInformarVolume));
        
        //Leitura Anormalidade Leitura Com Leitura
        if (leituraAnormalidadeLeituraComLeitura != null 
        		&& !leituraAnormalidadeLeituraComLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeLeitura leituraAnormalidadeComLeitura = new LeituraAnormalidadeLeitura();
        	leituraAnormalidadeComLeitura.setId(new Integer(leituraAnormalidadeLeituraComLeitura));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraComLeitura(leituraAnormalidadeComLeitura);
        }else{
        	faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraComLeitura(null);
        }

        //Leitura Anormalidade Leitura Sem Leitura
        if (leituraAnormalidadeLeituraSemLeitura != null 
        		&& !leituraAnormalidadeLeituraSemLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeLeitura leituraAnormalidadeSemLeitura = new LeituraAnormalidadeLeitura();
        	leituraAnormalidadeSemLeitura.setId(new Integer(leituraAnormalidadeLeituraSemLeitura));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraSemLeitura(leituraAnormalidadeSemLeitura);
        }else{
        	faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraSemLeitura(null);
        }

        //Leitura Anormalidade Consumo Com Leitura
        if (leituraAnormalidadeConsumoComLeitura != null 
        		&& !leituraAnormalidadeConsumoComLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeConsumo leituraAnormalidadeConsComLeitura = new LeituraAnormalidadeConsumo();
        	leituraAnormalidadeConsComLeitura.setId(new Integer(leituraAnormalidadeConsumoComLeitura));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoComLeitura(leituraAnormalidadeConsComLeitura);
        }else{
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoSemLeitura(null);
        }
        
        //Leitura Anormalidade Consumo Sem Leitura
        if (leituraAnormalidadeConsumoSemLeitura != null 
        		&& !leituraAnormalidadeConsumoSemLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeConsumo leituraAnormalidadeConsSemLeitura = new LeituraAnormalidadeConsumo();
        	leituraAnormalidadeConsSemLeitura.setId(new Integer(leituraAnormalidadeConsumoSemLeitura));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoSemLeitura(leituraAnormalidadeConsSemLeitura);
        }else{
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoSemLeitura(null);
        }
        
        FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo= new FiltroFaturamentoSituacaoTipo();
        filtroFaturamentoSituacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroFaturamentoSituacaoTipo.DESCRICAO, descricao));
        filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFaturamentoSituacaoTipo.ID, faturamentoSituacaoTipo.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		
		filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.ID, codigo));
		filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFaturamentoSituacaoTipo.ID, faturamentoSituacaoTipo.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigo+"");
		}
		
		fachada.atualizar(faturamentoSituacaoTipo);

		
		montarPaginaSucesso(httpServletRequest, "Tipo de Situacao de Faturamento"
				+ descricao + " atualizado com sucesso.",
				"Realizar outra Manutenção do Tipo de Situação de Faturamento",
				"exibirFiltrarFaturamentoSituacaoTipoAction.do?menu=sim");        
        
		return retorno;
	}
}