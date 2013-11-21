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
package gcom.gui.cobranca;


import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.InformarItensContratoServicoHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Arthur Carvalho
 * @date 14/08/2009
 */
public class InserirCobrancaGrupoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um Grupo de Cobranca
	 * 
	 * [UC0929] Inserir Grupo de Cobranca
	 * 
	 * @author Arthur Carvalho
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

		InserirCobrancaGrupoActionForm form = (InserirCobrancaGrupoActionForm) actionForm;

		Collection colecaoPesquisa = null;

		// Descrição
		String descricao = form.getDescricao();
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		// Descrição Abreviada
		String descricaoAbreviada = form.getDescricaoAbreviada();
		if (descricaoAbreviada == null || 
			"".equals(descricaoAbreviada ) ) {
			throw new ActionServletException("atencao.required", null,"Descrição Abreviada");
		}
		
		//Ano Mes Referencia
		String anoMesReferencia = form.getAnoMesReferencia();
		if ( anoMesReferencia == null || anoMesReferencia.equals("") ) {
			throw new ActionServletException("atencao.required", null, "Mês/Ano de Referência" );
		}
		
		//Ano Mes Referencia
		String indicadorExecucaoAutomatica = form.getIndicadorExecucaoAutomatica();
		if ( indicadorExecucaoAutomatica == null || indicadorExecucaoAutomatica.equals("") ) {
			throw new ActionServletException("atencao.required", null, "Execução Automática" );
		}

		//E-mail do Funcionário Responsável
		String emailResponsavel = form.getEmailResponsavel();
		
		/*
		
		InformarItensContratoServicoHelper helper = null;
		ContratoEmpresaServico contratoEmpresaServico = null;
		Iterator iterator = null;
		
		iterator = colecaoHelper.iterator();
		
		while (iterator.hasNext()) {
			helper = (InformarItensContratoServicoHelper) iterator.next();
			contratoEmpresaServico = helper.getContratoEmpresaServico();
			//TODO
		}*/
		ContratoEmpresaServico contratoEmpresaServico = null;
		if (form.getIdNumeroContrato() != null && !form.getIdNumeroContrato().equals("")) {
			HttpSession sessao = httpServletRequest.getSession(false);
			List colecaoHelper = (List) sessao.getAttribute("collectionContrato");
			int posicaoComponente = new Integer(form.getIdNumeroContrato());
	    	
	    	if (colecaoHelper.size() >= posicaoComponente) {
	    		
				InformarItensContratoServicoHelper helper = (InformarItensContratoServicoHelper) 
					colecaoHelper.get(posicaoComponente-1);
				
				contratoEmpresaServico = helper.getContratoEmpresaServico();
				
				helper.setContratoEmpresaServico(contratoEmpresaServico);
				
				colecaoHelper.remove(posicaoComponente-1);
	    		colecaoHelper.add(helper);
	    	}
		}
		
		
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.adicionarParametro(
			new ParametroSimples(FiltroCobrancaGrupo.DESCRICAO, descricao));
		
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.grupo_cobranca_ja_cadastrada", null,descricao);
		} else {
			
			CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();
			String mes = anoMesReferencia.substring(0, 2);
			String ano = anoMesReferencia.substring(3, 7);
			anoMesReferencia = ano + "" + mes;
			
			cobrancaGrupo.setAnoMesReferencia(new Integer(anoMesReferencia));
			cobrancaGrupo.setDescricao(descricao);
			cobrancaGrupo.setDescricaoAbreviada(descricaoAbreviada);
			cobrancaGrupo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			cobrancaGrupo.setUltimaAlteracao(new Date());
			cobrancaGrupo.setContratoEmpresaServico(contratoEmpresaServico);
			cobrancaGrupo.setEmailResponsavel(emailResponsavel);
			cobrancaGrupo.setIndicadorExecucaoAutomatica(new Short(indicadorExecucaoAutomatica));

			Integer idCobrancaGrupo = (Integer) this.getFachada().inserir(cobrancaGrupo);

			montarPaginaSucesso(httpServletRequest,
					"Grupo de Cobrança " + descricao
							+ " inserido com sucesso.",
					"Inserir outro Grupo de Cobrança ",
					"exibirInserirCobrancaGrupoAction.do?menu=sim",
					"exibirAtualizarCobrancaGrupoAction.do?idRegistroAtualizacao="
							+ idCobrancaGrupo,
					"Atualizar Grupo de Cobrança Inserido");

			this.getSessao(httpServletRequest).removeAttribute("InserirCobrancaGrupoActionForm");
			this.getSessao(httpServletRequest).removeAttribute("collectionContrato");

			return retorno;
		}

	}
}		
