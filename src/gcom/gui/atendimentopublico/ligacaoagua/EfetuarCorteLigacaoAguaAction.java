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

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
*  Action que define o processamento da página de efetuar corte de ligação de água
*
* @author Leandro Cavalcanti 
* @date	  12/07/2006
* 
* Refeito
* @author Leonardo Regis
* @date 27/09/2006
*/
public class EfetuarCorteLigacaoAguaAction extends GcomAction {

	/**
	 * Efetuar Corte Ligação Água
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		EfetuarCorteLigacaoAguaActionForm corteLigacaoAguaActionForm = 
			(EfetuarCorteLigacaoAguaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
			
		if (corteLigacaoAguaActionForm.getIdOrdemServico() != null && !corteLigacaoAguaActionForm.getIdOrdemServico().equals("")) {

			OrdemServico ordemServico = new OrdemServico();
			Imovel imovel = new Imovel();
			LigacaoAgua ligacaoAgua = new LigacaoAgua();
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
			if (sessao.getAttribute("ordemServico") != null) {
				ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
			}
			
			// Imovel
			//Comentado por Raphael Rossiter em 28/02/2007
			//imovel = ordemServico.getRegistroAtendimento().getImovel();
			imovel = ordemServico.getImovel();
			
			LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
			ligacaoAguaSituacao.setId(LigacaoAguaSituacao.CORTADO);
			imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			imovel.setUltimaAlteracao(new Date());
			// Ligação Água
			ligacaoAgua.setId(imovel.getId());
			Date dataCorte = null;
			if (corteLigacaoAguaActionForm.getDataCorte() != null && corteLigacaoAguaActionForm.getDataCorte() != "") {
				dataCorte = Util.converteStringParaDate(corteLigacaoAguaActionForm.getDataCorte());
			} else {
				throw new ActionServletException("atencao.required", null," Data do Corte");
			}
			ligacaoAgua.setDataCorte(dataCorte);
			if (corteLigacaoAguaActionForm.getNumSeloCorte()!= null && !corteLigacaoAguaActionForm.getNumSeloCorte().equals("")){
				ligacaoAgua.setNumeroSeloCorte(new Integer(corteLigacaoAguaActionForm.getNumSeloCorte()));
			}else{
				ligacaoAgua.setNumeroSeloCorte(null);
			}
			CorteTipo corteTipo = new CorteTipo();
			corteTipo.setId(new Integer(corteLigacaoAguaActionForm.getTipoCorte()));
			ligacaoAgua.setCorteTipo(corteTipo);
			MotivoCorte motivoCorte = new MotivoCorte();
			motivoCorte.setId(new Integer(corteLigacaoAguaActionForm.getMotivoCorte()));
			ligacaoAgua.setMotivoCorte(motivoCorte);
			ligacaoAgua.setUltimaAlteracao(new Date());

			// Hidrometro Instalação Histórico
			if (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {
				hidrometroInstalacaoHistorico = imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico();
				//Validar Número de Leitura do Corte / Número do Selo de Corte
				if (corteLigacaoAguaActionForm.getNumLeituraCorte()!= null && !corteLigacaoAguaActionForm.getNumLeituraCorte().equals("")){
					hidrometroInstalacaoHistorico.setNumeroLeituraCorte(new Integer(corteLigacaoAguaActionForm.getNumLeituraCorte()));
				}else{
					hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
				}
				hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
			}
			ligacaoAgua.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			imovel.setLigacaoAgua(ligacaoAgua);

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));
			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
			if(corteLigacaoAguaActionForm.getMotivoNaoCobranca() != null &&	!corteLigacaoAguaActionForm.getMotivoNaoCobranca().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(corteLigacaoAguaActionForm.getMotivoNaoCobranca()));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
			
			BigDecimal valorAtual = new BigDecimal(0);
			if (corteLigacaoAguaActionForm.getValorDebito() != null) {
			    String valorDebito = corteLigacaoAguaActionForm
			     	.getValorDebito().toString().replace(".", "");
			    
			    valorDebito = valorDebito.replace(",", ".");
			    
			    valorAtual = new BigDecimal(valorDebito);

			    ordemServico.setValorAtual(valorAtual);
			}
			
			if(corteLigacaoAguaActionForm.getPercentualCobranca() != null && 
					!corteLigacaoAguaActionForm.getPercentualCobranca().equals("")) {
				ordemServico.setPercentualCobranca(new BigDecimal(corteLigacaoAguaActionForm.getPercentualCobranca()));	
			}
			ordemServico.setUltimaAlteracao(new Date());
			
			// Preenche Helper
			DadosEfetuacaoCorteLigacaoAguaHelper dadosHelper = new DadosEfetuacaoCorteLigacaoAguaHelper();
			dadosHelper.setImovel(imovel);
			dadosHelper.setLigacaoAgua(ligacaoAgua);
			dadosHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			dadosHelper.setOrdemServico(ordemServico);
			if(corteLigacaoAguaActionForm.getVeioEncerrarOS().equalsIgnoreCase("true")){
				dadosHelper.setVeioEncerrarOS(true);
			} else {
				dadosHelper.setVeioEncerrarOS(false);
			}
			if (corteLigacaoAguaActionForm.getQuantidadeParcelas() != null &&
					!corteLigacaoAguaActionForm.getQuantidadeParcelas().equals("")) {
				dadosHelper.setQtdeParcelas(new Integer(corteLigacaoAguaActionForm.getQuantidadeParcelas()).intValue());
			} else {
				dadosHelper.setQtdeParcelas(0);
			}
			
			integracaoComercialHelper.setDadosEfetuacaoCorteLigacaoAguaHelper(dadosHelper);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setUsuarioLogado(usuario);
			//efetuar Corte Ligação de Água
			
			if(corteLigacaoAguaActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
				
				fachada.efetuarCorteLigacaoAgua(integracaoComercialHelper);
			}else{
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
				
				sessao.setAttribute("integracaoComercialHelper", integracaoComercialHelper);
				
				if(sessao.getAttribute("semMenu") == null){
					retorno = actionMapping.findForward("encerrarOrdemServicoAction");
				}else{
					retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
				}
				sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
			}
			
			if(retorno.getName().equalsIgnoreCase("telaSucesso")){

				montarPaginaSucesso(httpServletRequest,"Corte de Ligação de Água do imóvel "+imovel.getId()+" efetuada com Sucesso",
					"Efetuar outra Corte de Ligação de Água",
					"exibirEfetuarCorteLigacaoAguaAction.do?menu=sim",
					"exibirAtualizarCorteLigacaoAguaAction.do?idOrdemServico"+ordemServico.getId(),
					"Atualização Corte Ligação de Água efetuada");
			}
			
			return retorno;
		} else {
			throw new ActionServletException(
					"atencao.required", null,"Ordem de Serviço");
		}
	}

}
