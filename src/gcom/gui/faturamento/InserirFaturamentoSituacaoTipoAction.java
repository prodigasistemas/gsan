/* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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

import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirFaturamentoSituacaoTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão do tipo da situação de faturamento
	 * 
	 * [UC0845] Inserir Tipo da Situação de Faturamento
	 * 
	 * @author Arthur Carvalho
	 * @date 19/08/2008
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

		InserirFaturamentoSituacaoTipoActionForm inserirFaturamentoSituacaoTipoActionForm = (InserirFaturamentoSituacaoTipoActionForm) actionForm;

		
		String descricao = inserirFaturamentoSituacaoTipoActionForm.getDescricao();
		Short indicadorParalisacaoFaturamento = inserirFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoFaturamento();
		Short indicadorParalisacaoLeitura = inserirFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoLeitura();
		Short indicadorValidoAgua = inserirFaturamentoSituacaoTipoActionForm.getIndicadorValidoAgua();
		Short indicadorValidoEsgoto = inserirFaturamentoSituacaoTipoActionForm.getIndicadorValidoEsgoto();
		Short indicadorInformarConsumo = inserirFaturamentoSituacaoTipoActionForm.getIndicadorInformarConsumo();
	    Short indicadorInformarVolume = inserirFaturamentoSituacaoTipoActionForm.getIndicadorInformarVolume();
		String leituraAnormalidadeLeituraComLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeLeituraComLeitura();
		String leituraAnormalidadeLeituraSemLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeLeituraSemLeitura();
		String leituraAnormalidadeConsumoComLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeConsumoComLeitura();
		String leituraAnormalidadeConsumoSemLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeConsumoSemLeitura();
		
		FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
		Collection colecaoPesquisa = null;

		
		// Descrição
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
		filtroFaturamentoSituacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroFaturamentoSituacaoTipo.DESCRICAO, descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		//Indicador de Paralisação do Faturamento
		if (!"".equals(indicadorParalisacaoFaturamento)) {
			faturamentoSituacaoTipo.setIndicadorParalisacaoFaturamento(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorParalisacaoFaturamento());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorParalisacaoFaturamento");
		}
		
		//Indicador de Paralisação de Leitura
		if (!"".equals(indicadorParalisacaoLeitura)) {
			faturamentoSituacaoTipo.setIndicadorParalisacaoLeitura(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorParalisacaoLeitura());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorParalisacaoLeitura");
		}
		
		//Indicador de Valido para Água
		if (!"".equals(indicadorValidoAgua)) {
			faturamentoSituacaoTipo.setIndicadorValidoAgua(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorValidoAgua());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorValidoAgua");
		}
		
		//Indicador de Valido para Esgoto
		if (!"".equals(indicadorValidoEsgoto)) {
			faturamentoSituacaoTipo.setIndicadorValidoEsgoto(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorValidoEsgoto());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorValidoEsgoto");
		}
		
		//Indicador de Informar Consumo Fixo
		if (!"".equals(indicadorInformarConsumo)) {
			faturamentoSituacaoTipo.setIndicadorInformarConsumo(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorInformarConsumo());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorInformarConsumo");
		}	
		
		//Indicador de Informar Volume Fixo
		if (!"".equals(indicadorInformarVolume)) {
			faturamentoSituacaoTipo.setIndicadorInformarVolume(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorInformarVolume());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Indicador de Informar Volume Fixo");
		}	
		
		//Anormalidade de Consumo Cobrar Sem Leitura
        if (leituraAnormalidadeConsumoSemLeitura != null && 
        		!leituraAnormalidadeConsumoSemLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeConsumo leituraConsumoSemLeitura = new LeituraAnormalidadeConsumo();
        	leituraConsumoSemLeitura.setId(new Integer( leituraAnormalidadeConsumoSemLeitura ));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoSemLeitura( leituraConsumoSemLeitura );
        }else {
			throw new ActionServletException("atencao.required", null,
			"Anormalidade de Consumo Cobrar Sem Leitura");
        }	
		
        //Leitura Anormalidade Consumo Com Leitura
		if(leituraAnormalidadeConsumoComLeitura != null &&
				!leituraAnormalidadeConsumoComLeitura.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			LeituraAnormalidadeConsumo leituraConsumoComLeitura = new LeituraAnormalidadeConsumo();
			leituraConsumoComLeitura.setId(new Integer(leituraAnormalidadeConsumoComLeitura));
			faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoComLeitura(leituraConsumoComLeitura);
		}else {
			throw new ActionServletException("atencao.required", null,
			"Leitura Anormalidade Consumo Com Leitura");
        }	
		
		//Leitura Anormalidade Leitura Sem Leitura
		if(leituraAnormalidadeLeituraSemLeitura != null &&
				!leituraAnormalidadeLeituraSemLeitura.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			LeituraAnormalidadeLeitura leituraSemLeitura = new LeituraAnormalidadeLeitura();
			leituraSemLeitura.setId(new Integer(leituraAnormalidadeLeituraSemLeitura));
			faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraSemLeitura(leituraSemLeitura);
		}else {
			throw new ActionServletException("atencao.required", null,
			"Leitura Anormalidade Leitura Sem Leitura");
        }	
		
		//Leitura Anormalidade Leitura Com Leitura
		if(leituraAnormalidadeLeituraComLeitura != null &&
				!leituraAnormalidadeLeituraComLeitura.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			LeituraAnormalidadeLeitura leituraComLeitura = new LeituraAnormalidadeLeitura();
			leituraComLeitura.setId(new Integer(leituraAnormalidadeLeituraComLeitura));
			faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraComLeitura(leituraComLeitura);
		}else {
			throw new ActionServletException("atencao.required", null,
			"Leitura Anormalidade Leitura Com Leitura");
        }	
        
			faturamentoSituacaoTipo.setDescricao(descricao);
			faturamentoSituacaoTipo.setUltimaAlteracao(new Date());
			faturamentoSituacaoTipo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

			Integer idFaturamentoSituacaoTipo = (Integer) this.getFachada().inserir(faturamentoSituacaoTipo);

			montarPaginaSucesso(httpServletRequest,
					"Tipo da Situação de Faturamento " + descricao
							+ " inserida com sucesso.",
					"Inserir outro tipo de situação de faturamento",
					"exibirInserirFaturamentoSituacaoTipoAction.do?menu=sim",
					"exibirAtualizarFaturamentoSituacaoTipoAction.do?idRegistroAtualizacao="
							+ idFaturamentoSituacaoTipo,
					"Atualizar tipo de situação de faturamento Inserida");

			this.getSessao(httpServletRequest).removeAttribute("InserirFaturamentoSituacaoTipoActionForm");

			return retorno;
		
		
	}
}		
