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
package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
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
 * @date 15/05/2008
 */
public class InserirLigacaoAguaSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Situacao de Ligacao de Agua
	 * 
	 * [UC0785] Inserir Situacao de Ligacao de Agua
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 15/05/2008
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

		InserirLigacaoAguaSituacaoActionForm inserirLigacaoAguaSituacaoActionForm = (InserirLigacaoAguaSituacaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirLigacaoAguaSituacaoActionForm.getDescricao();
		String descricaoAbreviado = inserirLigacaoAguaSituacaoActionForm.getDescricaoAbreviado();
		String consumoMinimoFaturamento = inserirLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento();
		String indicadorExistenciaLigacao = inserirLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao();
		String indicadorExistenciaRede = inserirLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede();
		String indicadorFaturamentoSituacao = inserirLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao();
		String indicadorAbastecimento = inserirLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento();
		String indicadorAguaAtiva = inserirLigacaoAguaSituacaoActionForm.getIndicadorAguaAtiva();
		String indicadorAguaDesligada = inserirLigacaoAguaSituacaoActionForm.getIndicadorAguaDesligada();
		String indicadorAguaCadastrada = inserirLigacaoAguaSituacaoActionForm.getIndicadorAguaCadastrada();
		String indicadorAnalizeAgua = inserirLigacaoAguaSituacaoActionForm.getIndicadorAnalizeAgua();

		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		Collection colecaoPesquisa = null;

		
		// Descrição
		if (!"".equals(inserirLigacaoAguaSituacaoActionForm.getDescricao())) {
			ligacaoAguaSituacao.setDescricao(inserirLigacaoAguaSituacaoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição");
		}
		
		// Descrição Abreviada
		if (!"".equals(inserirLigacaoAguaSituacaoActionForm.getDescricaoAbreviado())) {
			ligacaoAguaSituacao.setDescricaoAbreviado(inserirLigacaoAguaSituacaoActionForm
					.getDescricaoAbreviado());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição Abreviada");
		}
		
		// Volume Mínimo da Situação de Ligação
        if (consumoMinimoFaturamento == null 
        		|| consumoMinimoFaturamento.equals("")){
        	throw new ActionServletException("atencao.required",null,"Consumo Mínimo");
        }else{  
        	ligacaoAguaSituacao.setConsumoMinimoFaturamento(new Integer(consumoMinimoFaturamento));
        }
		
		
		// Indicador de Existência de Ligação
        if (indicadorExistenciaLigacao == null 
        		|| indicadorExistenciaLigacao.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Existência de Ligação");
        }else{  
        	ligacaoAguaSituacao.setIndicadorExistenciaLigacao(new Short(indicadorExistenciaLigacao));
        }
        
		// Indicador de Existência de Rede
        if (indicadorExistenciaRede == null 
        		|| indicadorExistenciaRede.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Existência de Rede");
        }else{  
        	ligacaoAguaSituacao.setIndicadorExistenciaRede(new Short(indicadorExistenciaRede));
        }
        
		// Indicador de Faturamento
        if (indicadorFaturamentoSituacao == null 
        		|| indicadorFaturamentoSituacao.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Faturamento");
        }else{  
        	ligacaoAguaSituacao.setIndicadorFaturamentoSituacao(new Short(indicadorFaturamentoSituacao));
        }
        
        //Indicador de Abastecimento
        if (indicadorAbastecimento == null 
        		|| indicadorAbastecimento.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Abastecimento");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAbastecimento(new Short(indicadorAbastecimento));
        }
        
        //Indicador Água Ativa
        if (indicadorAguaAtiva == null 
        		|| indicadorAguaAtiva.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador Água ativa");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAguaAtiva(new Short(indicadorAguaAtiva));
        }
        
        //Indicador Água Desligada
        if (indicadorAguaDesligada == null 
        		|| indicadorAguaDesligada.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador Água Desligada");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAguaDesligada(new Short(indicadorAguaDesligada));
        }
        
        //Indicador Água Cadastrada
        if (indicadorAguaCadastrada == null 
        		|| indicadorAguaCadastrada.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador Água Cadastrada");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAguaCadastrada(new Short(indicadorAguaCadastrada));
        }
        
        //Indicado Analize de Água
        if (indicadorAnalizeAgua == null 
        		|| indicadorAnalizeAgua.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Analize de Água");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAnalizeAgua(new Short(indicadorAnalizeAgua));
        }
		
		
		// Ultima alteração
        ligacaoAguaSituacao.setUltimaAlteracao(new Date());
		// Indicador de uso
		Short iu = 1;
		ligacaoAguaSituacao.setIndicadorUso(iu);

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.DESCRICAO, ligacaoAguaSituacao.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(filtroLigacaoAguaSituacao,
				LigacaoAguaSituacao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.ligacao_situacao_agua_ja_cadastrada", null, ligacaoAguaSituacao
							.getDescricao());
		} else {
			ligacaoAguaSituacao.setDescricao(descricao);
			ligacaoAguaSituacao.setDescricaoAbreviado(descricaoAbreviado);
			ligacaoAguaSituacao.setConsumoMinimoFaturamento(new Integer (consumoMinimoFaturamento));
			ligacaoAguaSituacao.setIndicadorExistenciaLigacao(new Short (indicadorExistenciaLigacao));
			ligacaoAguaSituacao.setIndicadorExistenciaRede(new Short (indicadorExistenciaRede));
			ligacaoAguaSituacao.setIndicadorFaturamentoSituacao(new Short (indicadorFaturamentoSituacao));
			ligacaoAguaSituacao.setIndicadorAbastecimento(new Short (indicadorAbastecimento));
			ligacaoAguaSituacao.setIndicadorAguaAtiva(new Short(indicadorAguaAtiva));
			ligacaoAguaSituacao.setIndicadorAguaDesligada(new Short(indicadorAguaDesligada));
			ligacaoAguaSituacao.setIndicadorAguaCadastrada(new Short(indicadorAguaCadastrada));
			ligacaoAguaSituacao.setIndicadorAnalizeAgua(new Short(indicadorAnalizeAgua));
			
			Integer idSituacaoAguaLigacao = (Integer) fachada.inserir(ligacaoAguaSituacao);

			montarPaginaSucesso(httpServletRequest,
					"Situação de Ligação de Água " + descricao
							+ " inserida com sucesso.",
					"Inserir outra Situação de Ligação de Água",
					"exibirInserirLigacaoAguaSituacaoAction.do?menu=sim",
					"exibirAtualizarLigacaoAguaSituacaoAction.do?idRegistroAtualizacao="
							+ idSituacaoAguaLigacao,
					"Atualizar Situação de Ligação de Água Inserida");

			sessao.removeAttribute("InserirLigacaoAguaSituacaoActionForm");

			return retorno;
		}

	}
}