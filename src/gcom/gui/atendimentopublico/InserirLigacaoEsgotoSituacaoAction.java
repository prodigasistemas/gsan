/**
 * 
 */
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

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Descrição da classe
 * 
 * @author Vinícius Medeiros
 * @date 14/05/2008
 */
public class InserirLigacaoEsgotoSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Situacao de Ligacao de Esgoto
	 * 
	 * [UC0788] Inserir Situacao de Ligacao de Esgoto
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 14/05/2008
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirLigacaoEsgotoSituacaoActionForm inserirLigacaoEsgotoSituacaoActionForm = (InserirLigacaoEsgotoSituacaoActionForm) actionForm;

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirLigacaoEsgotoSituacaoActionForm.getDescricao();
		String descricaoAbreviado = inserirLigacaoEsgotoSituacaoActionForm.getDescricaoAbreviado();
		String volumeMinimoFaturamento = inserirLigacaoEsgotoSituacaoActionForm.getVolumeMinimoFaturamento();
		String indicadorExistenciaLigacao = inserirLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaLigacao();
		String indicadorExistenciaRede = inserirLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaRede();
		String indicadorFaturamentoSituacao = inserirLigacaoEsgotoSituacaoActionForm.getIndicadorFaturamentoSituacao();

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
		Collection colecaoPesquisa = null;

		
		// Verifica se o campo Descrição foi preenchido
		if (!"".equals(inserirLigacaoEsgotoSituacaoActionForm.getDescricao())) {
			
			ligacaoEsgotoSituacao.setDescricao(inserirLigacaoEsgotoSituacaoActionForm
					.getDescricao());
		
		} else {
		
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		// Verifica se o campo Descrição Abreviada foi preenchido
		if (!"".equals(inserirLigacaoEsgotoSituacaoActionForm.getDescricaoAbreviado())) {
			
			ligacaoEsgotoSituacao.setDescricaoAbreviado(inserirLigacaoEsgotoSituacaoActionForm
					.getDescricaoAbreviado());
			
		} else {
			
			throw new ActionServletException("atencao.required", null,
					"Descrição Abreviada");
		}
		
		// Verifica se o campo Volume Mínimo da Situação de Ligação foi preenchido
        if (volumeMinimoFaturamento == null || volumeMinimoFaturamento.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Volume Mínimo da Situação de Ligação");
        
        }else{  
        
        	ligacaoEsgotoSituacao.setVolumeMinimoFaturamento(new Integer(volumeMinimoFaturamento));
        
        }
		
		
		// Verifica se o campo Indicador de Existência de Ligação foi preenchido
        if (indicadorExistenciaLigacao == null || indicadorExistenciaLigacao.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Indicador de Existência de Ligação");
        
        }else{  
        	
        	ligacaoEsgotoSituacao.setIndicadorExistenciaLigacao(new Short(indicadorExistenciaLigacao));
        
        }
        
		// Verifica se o campo Indicador de Existência de Rede foi preenchido
        
        if (indicadorExistenciaRede == null || indicadorExistenciaRede.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Indicador de Existência de Rede");
        
        }else{  
        
        	ligacaoEsgotoSituacao.setIndicadorExistenciaRede(new Short(indicadorExistenciaRede));
        
        }
        
		// Verifica se o Indicador de Faturamento foi preenchido
        if (indicadorFaturamentoSituacao == null || indicadorFaturamentoSituacao.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Indicador de Faturamento");
        
        }else{  
        
        	ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao(new Short(indicadorFaturamentoSituacao));
        
        }
		
		
		// Ultima alteração
		ligacaoEsgotoSituacao.setUltimaAlteracao(new Date());
		
        // Indicador de uso
		Short iu = 1;
		ligacaoEsgotoSituacao.setIndicadorUso(iu);

		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.DESCRICAO, ligacaoEsgotoSituacao.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(filtroLigacaoEsgotoSituacao,
				LigacaoEsgotoSituacao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
			// Caso já tenha uma Situação de Ligação de Esgoto cadastrada
			throw new ActionServletException(
					"atencao.ligacao_situacao_esgoto_ja_cadastrada", null, ligacaoEsgotoSituacao
							.getDescricao());
		
		} else {
			
			ligacaoEsgotoSituacao.setDescricao(descricao);
			ligacaoEsgotoSituacao.setDescricaoAbreviado(descricaoAbreviado);
			ligacaoEsgotoSituacao.setVolumeMinimoFaturamento(new Integer (volumeMinimoFaturamento));
			ligacaoEsgotoSituacao.setIndicadorExistenciaLigacao(new Short (indicadorExistenciaLigacao));
			ligacaoEsgotoSituacao.setIndicadorExistenciaRede(new Short (indicadorExistenciaRede));
			ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao(new Short (indicadorFaturamentoSituacao));
			
			Integer idSituacaoEsgotoLigacao = (Integer) fachada.inserir(ligacaoEsgotoSituacao);

			montarPaginaSucesso(httpServletRequest,
					"Situação de Ligação de Esgoto " + descricao
							+ " inserida com sucesso.",
					"Inserir outra Situação de Ligação de Esgoto",
					"exibirInserirLigacaoEsgotoSituacaoAction.do?menu=sim",
					"exibirAtualizarLigacaoEsgotoSituacaoAction.do?idRegistroAtualizacao="
							+ idSituacaoEsgotoLigacao,
					"Atualizar Situação de Ligação de Esgoto Inserida");

			sessao.removeAttribute("InserirLigacaoEsgotoSituacaoActionForm");

			return retorno;
		}

	}
}