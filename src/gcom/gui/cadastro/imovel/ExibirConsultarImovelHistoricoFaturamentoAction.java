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

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0473] Consultar Imovel
 * Metodo da 4° Aba - Histórico de  Faturamento
 * 
 * @author Rafael Santos
 * @since 13/09/2006
 * 
 */
public class ExibirConsultarImovelHistoricoFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = getSessao(httpServletRequest);

		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;

		if(isLimparDadosTela(httpServletRequest)){

			httpServletRequest.removeAttribute("idImovelHistoricoFaturamentoNaoEncontrado");

			limparFormSessao(consultarImovelActionForm, sessao);

		}else if( isImovelInformadoTelaHistoricoFaturamento(consultarImovelActionForm)
				|| isImovelInformadoOutraTela(sessao) ){

			consultarImovelActionForm.setIdImovelHistoricoFaturamento(
					definirIdImovelASerPesquisado(consultarImovelActionForm, sessao, httpServletRequest));

			Imovel imovel = obterImovelASerPesquisado(consultarImovelActionForm, sessao);

	        //deve ser chamado antes dos novos valores da sessão serem setados
			boolean imovelNovoPesquisado = isNovoImovelPesquisado(consultarImovelActionForm, sessao);

			if (imovel != null) {
				

				sessao.setAttribute("imovelDadosHistoricoFaturamento", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());

				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

				consultarImovelActionForm.setIdImovelHistoricoFaturamento(imovel.getId().toString());

				if(imovelNovoPesquisado){

					httpServletRequest.removeAttribute("idImovelHistoricoFaturamentoNaoEncontrado");

					setarDadosImovelNoFormESessao(consultarImovelActionForm, imovel, sessao);

				}

			} else {
				limparFormSessao(consultarImovelActionForm, sessao);
				
				httpServletRequest.setAttribute("idImovelHistoricoFaturamentoNaoEncontrado", "true");

				consultarImovelActionForm.setMatriculaImovelHistoricoFaturamento("IMÓVEL INEXISTENTE");
			}

		}else{
        	String idImovelAux = consultarImovelActionForm.getIdImovelHistoricoFaturamento();

         	httpServletRequest.removeAttribute("idImovelHistoricoFaturamentoNaoEncontrado");

            limparFormSessao(consultarImovelActionForm, sessao);

        	consultarImovelActionForm.setIdImovelHistoricoFaturamento(idImovelAux);
		}

		return actionMapping.findForward("consultarImovelHistoricoFaturamento");
	}

	/**
	 * Esse método seta os dados necessários do Imovel
	 * no form e alguns na sessão (coleções).
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosImovelNoFormESessao(ConsultarImovelActionForm consultarImovelActionForm, 
			Imovel imovel,HttpSession sessao) {

		consultarImovelActionForm.setMatriculaImovelHistoricoFaturamento(Fachada.getInstancia()
				.pesquisarInscricaoImovelExcluidoOuNao(new Integer(
						consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim())));

		consultarImovelActionForm.setSituacaoAguaHistoricoFaturamento(
				imovel.getLigacaoAguaSituacao().getDescricao());

		consultarImovelActionForm
		.setSituacaoEsgotoHistoricoFaturamento(imovel
				.getLigacaoEsgotoSituacao().getDescricao());

		sessao.setAttribute("colecaoContaImovel", Fachada.getInstancia().consultarContasImovel(imovel.getId()));
		sessao.setAttribute("colecaoContaHistoricoImovel", Fachada.getInstancia().consultarContasHistoricosImovel(imovel.getId()));

		sessao.setAttribute("colecaoDebitoACobrarImovel", Fachada.getInstancia().obterDebitoACobrarImovel(imovel.getId())); 
		sessao.setAttribute("colecaoDebitoACobrarHistoricoImovel", Fachada.getInstancia().obterDebitoACobrarHistoricoImovel(imovel.getId())); 

		sessao.setAttribute("colecaoCreditoARealizarImovel", Fachada.getInstancia().obterCreditoARealizarImovel(imovel.getId())); 
		sessao.setAttribute("colecaoCreditoARealizarHistoricoImovel", Fachada.getInstancia().obterCreditoARealizarHistoricoImovel(imovel.getId())); 

		sessao.setAttribute("colecaoGuiaPagamentoImovel", Fachada.getInstancia().obterGuiaPagamentoImovel(imovel.getId())); 
		sessao.setAttribute("colecaoGuiaPagamentoHistoricoImovel", Fachada.getInstancia().obterGuiaPagamentoHistoricoImovel(imovel.getId())); 

		setarTamanhoColacoesSessao(sessao);
	}

	/**
	 *Esse método limpa todos os atributos do form
	 *e os atributos na sessão 
	 *que são usados pelo action e/ou jsp.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private void limparFormSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		sessao.removeAttribute("idImovelPrincipalAba");
		sessao.removeAttribute("imovelDadosHistoricoFaturamento");

		sessao.removeAttribute("colecaoContaImovel");
		sessao.removeAttribute("colecaoContaHistoricoImovel");
		sessao.removeAttribute("colecaoDebitoACobrarImovel"); 
		sessao.removeAttribute("colecaoDebitoACobrarHistoricoImovel"); 
		sessao.removeAttribute("colecaoCreditoARealizarImovel"); 
		sessao.removeAttribute("colecaoCreditoARealizarHistoricoImovel");
		sessao.removeAttribute("colecaoGuiaPagamentoImovel"); 
		sessao.removeAttribute("colecaoGuiaPagamentoHistoricoImovel");
		sessao.removeAttribute("tamanhoColecaoDebitos"); 
		sessao.removeAttribute("tamanhoColecaoCreditos"); 
		sessao.removeAttribute("tamanhoColecaoGuias"); 

		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setMatriculaImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setSituacaoAguaHistoricoFaturamento(null);
		consultarImovelActionForm.setSituacaoEsgotoHistoricoFaturamento(null);
		consultarImovelActionForm.setConta(null);
		consultarImovelActionForm.setDescricaoImovel(null);

		consultarImovelActionForm.setIdImovelDadosComplementares(null);
		consultarImovelActionForm.setIdImovelDadosCadastrais(null);
		consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setIdImovelDebitos(null);
		consultarImovelActionForm.setIdImovelPagamentos(null);
		consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
		consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
		consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
		consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
		consultarImovelActionForm.setImovIdAnt(null);
	}

	/**
	 *Esse método seta na sessão os tamanhos das coleções de 
	 *débito(debito a cobrar + debito historico) ,
	 *crédito(credito a realizar + credito historico)  e 
	 *guias de pagamento(guias + guias historico).
	 *
	 *@since --
	 *@author --
	 */
	private void setarTamanhoColacoesSessao(HttpSession sessao) {

		Collection<DebitoACobrarHistorico> colecaoDebitoACobrarHistoricoImovel = (Collection<DebitoACobrarHistorico>)sessao.getAttribute("colecaoDebitoACobrarHistoricoImovel");
		Collection<DebitoACobrar> colecaoDebitoACobrarImovel = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitoACobrarImovel");
		Collection<CreditoARealizarHistorico> colecaoCreditoARealizarHistoricoImovel = (Collection<CreditoARealizarHistorico>)sessao.getAttribute("colecaoCreditoARealizarHistoricoImovel");
		Collection<CreditoARealizar> colecaoCreditoARealizarImovel = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizarImovel");
		Collection<GuiaPagamentoHistorico> colecaoGuiaPagamentoHistoricoImovel =(Collection<GuiaPagamentoHistorico>)sessao.getAttribute("colecaoGuiaPagamentoHistoricoImovel");
		Collection<GuiaPagamento> colecaoGuiaPagamentoImovel =(Collection<GuiaPagamento>)sessao.getAttribute("colecaoGuiaPagamentoImovel");

		Integer tamanhoColecaoDebitos = 0;
		Integer tamanhoColecaoCreditos = 0;
		Integer tamanhoColecaoGuias = 0;

		if(colecaoDebitoACobrarImovel != null){
			tamanhoColecaoDebitos = colecaoDebitoACobrarImovel.size();
		}
		if(colecaoDebitoACobrarHistoricoImovel != null){
			tamanhoColecaoDebitos = tamanhoColecaoDebitos + colecaoDebitoACobrarHistoricoImovel.size();
		}

		if(colecaoCreditoARealizarImovel != null){
			tamanhoColecaoCreditos = colecaoCreditoARealizarImovel.size();
		}
		if(colecaoCreditoARealizarHistoricoImovel != null){
			tamanhoColecaoCreditos = tamanhoColecaoCreditos + colecaoCreditoARealizarHistoricoImovel.size();
		}

		if(colecaoGuiaPagamentoImovel != null){
			tamanhoColecaoGuias = colecaoGuiaPagamentoImovel.size();
		}
		if(colecaoGuiaPagamentoHistoricoImovel != null){
			tamanhoColecaoGuias = tamanhoColecaoGuias + colecaoGuiaPagamentoHistoricoImovel.size();
		}

		sessao.setAttribute("tamanhoColecaoDebitos", tamanhoColecaoDebitos); 
		sessao.setAttribute("tamanhoColecaoCreditos", tamanhoColecaoCreditos); 
		sessao.setAttribute("tamanhoColecaoGuias", tamanhoColecaoGuias); 

	}

	/**
	 * Caso o usuário tenha clicado no botão de limpar
	 * esse método retornará true.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isLimparDadosTela(HttpServletRequest httpServletRequest) {
		return Util.verificarNaoVazio(httpServletRequest.getParameter("limparForm"));
	}

	/**
	 * Esse método verifica se já foi informado um imóvel em outra tela.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoOutraTela(HttpSession sessao) {
		return Util.verificarNaoVazio((String)sessao.getAttribute("idImovelPrincipalAba"));		
	}

	/**
	 * Esse método verifica se o imóvel foi informado na tela
	 * de Historico de Faturamento
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoTelaHistoricoFaturamento(ConsultarImovelActionForm consultarImovelActionForm) {
		return Util.verificarNaoVazio(consultarImovelActionForm.getIdImovelHistoricoFaturamento());
	}

	/**
	 * Esse método retorna o id do imóvel a ser pesquisado,
	 * verificando se é o id possivelmente informado pelo usuário na tela 
	 * de Historico Faturamento ou se o id já informado em uma outra tela.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private String definirIdImovelASerPesquisado(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao, HttpServletRequest httpServletRequest) {

		String idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");

		if( isImovelInformadoTelaHistoricoFaturamento(consultarImovelActionForm)
				&& isImovelInformadoOutraTela(sessao)){

			if( !Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorNovo")) ){        				
				return idImovelPrincipalAba;            		
			}

		}else if(isImovelInformadoOutraTela(sessao)){
			return idImovelPrincipalAba;            		
		}

		return consultarImovelActionForm.getIdImovelHistoricoFaturamento();
	}

	/**
	 * Consulta o Imovel com todas as informações necessárias,
	 * ou simplesmetne pega o Imovel da sessão caso o mesmo já
	 * tenha sido pesquisado.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private Imovel obterImovelASerPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		Imovel imovel = null;

		if(sessao.getAttribute("imovelDadosHistoricoFaturamento") == null){
			imovel = Fachada.getInstancia().consultarImovelHistoricoFaturamento(new Integer(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()));

		}else{
			imovel = (Imovel) sessao.getAttribute("imovelDadosHistoricoFaturamento");

			if( !imovel.getId().toString().equals(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()) ){
				imovel = Fachada.getInstancia().consultarImovelHistoricoFaturamento(new Integer(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()));
			}
		}
		return imovel;
	}


	/**
	 * Esse método retorna true se foi necessário consultar um novo imovel.
	 * Caso o imóvel seja o mesmo já consultado anteriormente ele retorna false.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isNovoImovelPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		if(sessao.getAttribute("imovelDadosHistoricoFaturamento") == null){
			return true;
		}

		Imovel imovelAux = (Imovel) sessao.getAttribute("imovelDadosHistoricoFaturamento");

		if( !imovelAux.getId().toString().equals(consultarImovelActionForm.getIdImovelHistoricoFaturamento().trim()) ){
			return true;
		}

		return false;
	}

}