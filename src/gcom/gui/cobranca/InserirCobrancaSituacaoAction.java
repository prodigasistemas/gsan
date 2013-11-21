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


import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirCobrancaSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Situacao de Cobranca
	 * 
	 * [UC0858] Inserir Situacao de Cobranca
	 * 
	 * @author Arthur Carvalho
	 * @date 04/09/2008
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

		InserirCobrancaSituacaoActionForm form = (InserirCobrancaSituacaoActionForm) actionForm;

		String descricao = form.getDescricao();
		String contaMotivoRevisao = form.getContaMotivoRevisao();
		Short indicadorExigenciaAdvogado = form.getIndicadorExigenciaAdvogado();
		Short indicadorBloqueioParcelamento = form.getIndicadorBloqueioParcelamento();
		Short indicadorBloqueioRetirada = form.getIndicadorBloqueioRetirada();
		Short indicadorBloqueioInclusao = form.getIndicadorBloqueioInclusao();
		Short indicadorSelecaoApenasComPermissao = form.getIndicadorSelecaoApenasComPermissao();
		Integer indicadorPrescricaoImoveisParticulares = form.getIndicadorPrescricaoImoveisParticulares();
		Integer indicadorNaoIncluirImoveisEmCobrancaResultado = form.getIndicadorNaoIncluirImoveisEmCobrancaResultado();
		String profissao = form.getProfissao();
		String ramoAtividade = form.getRamoAtividade();
		
		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
		
		Collection colecaoPesquisa = null;		
		
		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(FiltroCobrancaSituacao.DESCRICAO, descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		//Descrição
		if (!descricao.equals("")) {
			cobrancaSituacao.setDescricao(descricao);
		} else {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		//Motivo de revisao da conta
		if(contaMotivoRevisao != null && 
			!contaMotivoRevisao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			ContaMotivoRevisao motivoRevisao = new ContaMotivoRevisao();
			motivoRevisao.setId(new Integer(form
					.getContaMotivoRevisao()));
			
			cobrancaSituacao.setContaMotivoRevisao(motivoRevisao);
		}
			
		//Exige advogado
		if(indicadorExigenciaAdvogado != null && 
			!indicadorExigenciaAdvogado.equals("")){
			
			cobrancaSituacao.setIndicadorExigenciaAdvogado(indicadorExigenciaAdvogado);
			
			if ( indicadorExigenciaAdvogado == 1 ) {
				//Profissao
				if(profissao != null && 
					profissao.equals("-1")){
					
					throw new ActionServletException("atencao.required", null,
					"Profissão");
				}
				//Ramo Atividade
				if(ramoAtividade != null && 
					ramoAtividade.equals("-1")){
					
					throw new ActionServletException("atencao.required", null,
					"Ramo Atividade");
				} 
			}
		}
		
		//Bloqueia Parcelamento
		if(indicadorBloqueioParcelamento != null && 
			!indicadorBloqueioParcelamento.equals("")){
			
			cobrancaSituacao.setIndicadorBloqueioParcelamento(indicadorBloqueioParcelamento);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Indicador Bloqueio Parcelamento");
		}
		
		//Profissao
		if(profissao != null && 
			!profissao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			Profissao prof = new Profissao();
			prof.setId( new Integer( form.getProfissao() ) );
			
			cobrancaSituacao.setProfissao(prof);
		}
		
		//Ramo Atividade
		if(ramoAtividade != null && 
			!ramoAtividade.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			RamoAtividade ramoAtiv = new RamoAtividade();
			ramoAtiv.setId( new Integer( form.getRamoAtividade() ) );
			
			cobrancaSituacao.setRamoAtividade(ramoAtiv);
		}
		
		//Indicador Bloqueio Retirada
		if(indicadorBloqueioRetirada != null && 
			!indicadorBloqueioRetirada.equals("")){
			
			cobrancaSituacao.setIndicadorBloqueioRetirada(indicadorBloqueioRetirada);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Indicador Bloqueio Retirada");
		}
		
		//Indicador Bloqueio Inclusao
		if(indicadorBloqueioInclusao != null && 
			!indicadorBloqueioInclusao.equals("")){
			
			cobrancaSituacao.setIndicadorBloqueioInclusao(indicadorBloqueioInclusao);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Indicador Bloqueio Inclusao");
		}
				
		// Indicador de Seleção da Situação Apenas pelos Usuários com Permissão Especial 
		if(indicadorSelecaoApenasComPermissao != null && 
			!indicadorSelecaoApenasComPermissao.equals("")){
			
			cobrancaSituacao.setIndicadorSelecaoApenasComPermissao(indicadorSelecaoApenasComPermissao);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Indicador de Seleção da Situação Apenas pelos Usuários com Permissão Especial");
		}
		
		// Indicador Prescricao Imoveis Particulares
		if(indicadorPrescricaoImoveisParticulares != null && 
				!indicadorPrescricaoImoveisParticulares.equals("")){
			
			cobrancaSituacao.setIndicadorPrescricaoImoveisParticulares(indicadorPrescricaoImoveisParticulares);
		}else{
			throw new ActionServletException("atencao.required", null,
			"Indicador de Prescrição para Imóveis Particulares");
		}
		
		// Indicador de não inclusão da cobrança por resultado
		if(indicadorNaoIncluirImoveisEmCobrancaResultado != null && 
				!indicadorNaoIncluirImoveisEmCobrancaResultado.equals("")){
			
			cobrancaSituacao.setIndicadorNaoIncluirImoveisEmCobrancaResultado(indicadorNaoIncluirImoveisEmCobrancaResultado);
		}else{
			throw new ActionServletException("atencao.required", null,
			"Indicador de não inclusão da cobrança por resultado");
		}
		
		cobrancaSituacao.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		cobrancaSituacao.setUltimaAlteracao(new Date());
		
		Integer idCobrancaSituacao = (Integer) this.getFachada().inserir(cobrancaSituacao);

		montarPaginaSucesso(httpServletRequest,
				"Situação de Cobrança " + descricao
						+ " inserido com sucesso.",
				"Inserir outra Situação de Cobrança",
				"exibirInserirCobrancaSituacaoAction.do?menu=sim",
				"exibirAtualizarCobrancaSituacaoAction.do?idRegistroAtualizacao="
						+ idCobrancaSituacao,
				"Atualizar Situação de Cobrança Inserida");

		return retorno;
		
	}
}		
