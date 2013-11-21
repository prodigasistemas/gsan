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
package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Ana Maria
 * @date 30/06/2006
 */

public class EfetuarInstalacaoHidrometroAction extends GcomAction {
	/**
	 * Este caso de uso permite efetuar instalação de hidrômetro
	 * 
	 * [UC0362] Efetuar Instalação de Hidrômetro
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
		EfetuarInstalacaoHidrometroActionForm efetuarInstalacaoHidrometroActionForm = 
			(EfetuarInstalacaoHidrometroActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		 //Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");		
		
		String numeroHidrometro = efetuarInstalacaoHidrometroActionForm.getNumeroHidrometro();
		
		String idServicoMotivoNaoCobranca = efetuarInstalacaoHidrometroActionForm.getMotivoNaoCobranca();
		String valorPercentual = efetuarInstalacaoHidrometroActionForm.getPercentualCobranca();
		String qtdParcelas = efetuarInstalacaoHidrometroActionForm.getQuantidadeParcelas();
		//String nmLacre = efetuarInstalacaoHidrometroActionForm.getNumeroLacre();
		
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
		
		OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");	
        Imovel imovel = null;
        
        if(ordemServico.getImovel() != null){
            imovel = ordemServico.getImovel();
        }else{
            imovel = ordemServico.getRegistroAtendimento().getImovel();
        }
			
		if (numeroHidrometro != null) {
            //Constrói o filtro para pesquisa do Hidrômetro
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro.adicionarParametro(new ParametroSimples(FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
	        //Realiza a pesquisa do Hidrômetro
			Collection colecaoHidrometro = fachada.pesquisar(filtroHidrometro,Hidrometro.class.getName());
			
			//verificar se o número do hidrômetro não está cadastrado
			if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
				throw new ActionServletException("atencao.hidrometro_inexistente");
			}
			Iterator iteratorHidrometro = colecaoHidrometro.iterator();
			Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			Imovel imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			
			if (imovelComLocalidade != null && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null && 
				hidrometro.getHidrometroLocalArmazenagem() != null &&
				!hidrometro.getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
					throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
			}
			
            hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		}
		
		//Atualiza a entidade com os valores do formulário
		efetuarInstalacaoHidrometroActionForm.setFormValues(hidrometroInstalacaoHistorico);
		
		// Informa que o usuário que fez a instalação é o usuário logado
		hidrometroInstalacaoHistorico.setUsuarioInstalacao( usuario );
		
		
		if(ordemServico != null 
				 && efetuarInstalacaoHidrometroActionForm.getIdTipoDebito() != null){
					
			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
					
			ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);
					
			BigDecimal valorAtual = new BigDecimal(0);
			
			if (efetuarInstalacaoHidrometroActionForm.getValorDebito() != null) {
			    String valorDebito = efetuarInstalacaoHidrometroActionForm
			     	.getValorDebito().toString().replace(".", "");
			    
			    valorDebito = valorDebito.replace(",", ".");
			    
			    valorAtual = new BigDecimal(valorDebito);

			    ordemServico.setValorAtual(valorAtual);
			}
					
			if(idServicoMotivoNaoCobranca != null && !idServicoMotivoNaoCobranca.equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
			   servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
			   servicoNaoCobrancaMotivo.setId(new Integer(idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
					
			if(valorPercentual != null){
			   ordemServico.setPercentualCobranca(new BigDecimal(efetuarInstalacaoHidrometroActionForm.getPercentualCobranca()));
			}
					
			ordemServico.setUltimaAlteracao(new Date());				
		}
		
		if (efetuarInstalacaoHidrometroActionForm.getTipoPoco() != null && 
				!efetuarInstalacaoHidrometroActionForm.getTipoPoco().equals("-1")){
			
			PocoTipo pocoTipo = new PocoTipo();
			
			pocoTipo.setId(new Integer(efetuarInstalacaoHidrometroActionForm.getTipoPoco()));
			
			imovel.setPocoTipo(pocoTipo);
		}
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		
		integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		integracaoComercialHelper.setImovel(imovel);
		
		if(efetuarInstalacaoHidrometroActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			fachada.efetuarInstalacaoHidrometro(integracaoComercialHelper);
		}else{
			fachada.validacaoInstalacaoHidrometro(numeroHidrometro);
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
			//Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, "Instalação de Hidrômetro para o imóvel "+imovel.getId()
					+ " efetuada com sucesso.", "Efetuar outra Instalação de Hidrômetro",
					"exibirEfetuarInstalacaoHidrometroAction.do");
		}
		
		sessao.removeAttribute("EfetuarInstalacaoHidrometroActionForm");

		return retorno;
	}
	
}
