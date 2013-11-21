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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InformarImovelSituacaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirImovelSituacaoCobrancaActionForm form = (InserirImovelSituacaoCobrancaActionForm) actionForm;
		
		
		String idImovel = null;
		String idSituacaoCobranca = null;
		String idCliente = null;
		Date dataImplantacao = null;
		Integer anoMesInicio = null;
		Integer anoMesFim = null;
		
		Imovel imovel = new Imovel();
		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
		Cliente cliente = new Cliente();
		
		if (form.getCodigoImovel() != null &&
				!form.getCodigoImovel().equalsIgnoreCase("")){
			idImovel = form.getCodigoImovel();
			imovel.setId(new Integer(idImovel));
		}
		
		Cliente clienteEscritorio = null;
		Cliente clienteAdvogado = null;
		
		if (form.getSituacaoCobranca() != null && !form.getSituacaoCobranca().equalsIgnoreCase("")){
			
			idSituacaoCobranca = form.getSituacaoCobranca();

			cobrancaSituacao.setId(new Integer(idSituacaoCobranca));
			
			FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.ID, idSituacaoCobranca));
			filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
			filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("profissao");
			
			Collection colecaoCobrancaSituacao = Fachada.getInstancia().pesquisar(
					filtroCobrancaSituacao, CobrancaSituacao.class.getName());
			
			CobrancaSituacao cobrancaSituacao2 = (CobrancaSituacao) colecaoCobrancaSituacao.iterator().next();
			
			// Verificando se o indicador de exigencia de advogado for igual a 1 é necessario 
			// informar o advogado ou escritorio de advocacia. 
			if ( cobrancaSituacao2.getIndicadorExigenciaAdvogado() != null && 
					cobrancaSituacao2.getIndicadorExigenciaAdvogado().toString().equals(ConstantesSistema.SIM.toString()) ) {
				
				String cobrancaSituacaoDescricao = cobrancaSituacao2.getDescricao();
				
				if ((form.getIdEscritorio() == null || form.getIdEscritorio().equals(""))&& 
						(form.getIdAdvogado() == null || form.getIdAdvogado().equals(""))){
					
					throw new ActionServletException("atencao.situacao_cobranca_exige_advogado_ou_escritorio_advocacia", null, cobrancaSituacaoDescricao);
				}else{
					
					clienteEscritorio = setClienteEscritorio(form, clienteEscritorio);
					clienteAdvogado = setClienteAdvogado(form, clienteAdvogado);
				}
			}else{
				clienteEscritorio = setClienteEscritorio(form, clienteEscritorio);
				clienteAdvogado = setClienteAdvogado(form, clienteAdvogado);
			}
			
			
			
		
			if ( cobrancaSituacao2.getProfissao() != null ) {
				cobrancaSituacao.setProfissao(cobrancaSituacao2.getProfissao());
			}
			if ( cobrancaSituacao2.getRamoAtividade() != null ) {
				cobrancaSituacao.setRamoAtividade(cobrancaSituacao2.getRamoAtividade());
			}
			
			if (cobrancaSituacao2.getContaMotivoRevisao() != null){
				
				if (form.getAnoMesReferenciaInicio() != null &&
						!form.getAnoMesReferenciaInicio().equalsIgnoreCase("")){
					anoMesInicio = Util.formatarMesAnoComBarraParaAnoMes(
							form.getAnoMesReferenciaInicio());
				} else {
					throw new ActionServletException(
						"atencao.informe_anomes_referencia_debitos");
				}
				
				if (form.getAnoMesReferenciaFim() != null &&
						!form.getAnoMesReferenciaFim().equalsIgnoreCase("")){
					anoMesFim = Util.formatarMesAnoComBarraParaAnoMes(
							form.getAnoMesReferenciaFim());
				} else {
					throw new ActionServletException(
						"atencao.informe_anomes_referencia_debitos");
				}
			}
		}
		
		
		if (form.getIdClienteAlvo() != null &&
				!form.getIdClienteAlvo().equalsIgnoreCase("")){
			idCliente = form.getIdClienteAlvo();

			cliente.setId(new Integer(idCliente));
		}
		
		if (form.getDataImplantacao() != null &&
				!form.getDataImplantacao().equalsIgnoreCase("")){
			dataImplantacao = Util.converteStringParaDate(
					form.getDataImplantacao());
		}
		
		if (form.getAnoMesReferenciaInicio() != null &&
				!form.getAnoMesReferenciaInicio().equalsIgnoreCase("")){
			anoMesInicio = Util.formatarMesAnoComBarraParaAnoMes(
					form.getAnoMesReferenciaInicio());
		}
		
		if (form.getAnoMesReferenciaFim() != null &&
				!form.getAnoMesReferenciaFim().equalsIgnoreCase("")){
			
			anoMesFim = Util.formatarMesAnoComBarraParaAnoMes(
					form.getAnoMesReferenciaFim());
			
			if (anoMesFim < anoMesInicio){
				throw new ActionServletException(
					"atencao.ano_mes_inicio_menor_que_final");
			}
			
			
		}
		
		HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
        if ( clienteEscritorio != null && clienteAdvogado != null ){
        	throw new ActionServletException(
					"atencao.informar_escritorio_advogado");
        }
        
        // Acrescimo de 2 argumentos do tipo cliente (Kassia Albuquerque)
		Fachada.getInstancia().inserirImovelSitucaoCobranca(
				imovel, cobrancaSituacao, cliente, clienteEscritorio, 
				clienteAdvogado, dataImplantacao, anoMesInicio, anoMesFim, usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Situação de Cobrança do Imovel inserida com sucesso.",
				"Informar outra situação de cobrança do imovel",
				"exibirInformarImovelSituacaoCobrancaAction.do?menu=sim");
		
		return retorno;
	}

	
	//  VERIFICANDO SE O CLIENTE EXISTE.
	//		(Kassia Albuquerque)
	private Cliente setClienteAdvogado(InserirImovelSituacaoCobrancaActionForm form , Cliente clienteAdvogado) {
		String idAdvogado= form.getIdAdvogado();
		
		if (idAdvogado!=null && !idAdvogado.equalsIgnoreCase("")){
			
			FiltroCliente filtroClienteAdvogado = new FiltroCliente();
			filtroClienteAdvogado.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			filtroClienteAdvogado.adicionarCaminhoParaCarregamentoEntidade("profissao");
			filtroClienteAdvogado.adicionarParametro(new ParametroSimples(FiltroCliente.ID,idAdvogado));
			
			Collection colecaoClienteAdvogado = Fachada.getInstancia().pesquisar(filtroClienteAdvogado,Cliente.class.getName());
			
			Cliente clienteAdvogado2 = (Cliente)colecaoClienteAdvogado.iterator().next();
			
			if (clienteAdvogado2 == null){
				throw new ActionServletException("atencao.advogado_inexistente");
			}else{
				clienteAdvogado = new Cliente();
				clienteAdvogado = clienteAdvogado2;
			}
		}
		return clienteAdvogado;
	}
	
	
	//  VERIFICANDO SE O CLIENTE EXISTE.
	//		(Kassia Albuquerque)
	private Cliente setClienteEscritorio(InserirImovelSituacaoCobrancaActionForm form , Cliente clienteEscritorio) {
			String idEscritorio = form.getIdEscritorio();
		
		if (idEscritorio!=null && !idEscritorio.equalsIgnoreCase("")){
			
			FiltroCliente filtroClienteEscritorio = new FiltroCliente();
			filtroClienteEscritorio.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			filtroClienteEscritorio.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
			filtroClienteEscritorio.adicionarParametro(new ParametroSimples(FiltroCliente.ID,idEscritorio));
			
			Collection colecaoClienteEscritorio = Fachada.getInstancia().pesquisar(filtroClienteEscritorio,Cliente.class.getName());
			
			Cliente clienteEscritorio2 = (Cliente)colecaoClienteEscritorio.iterator().next();
			
			if (clienteEscritorio2 == null){
				throw new ActionServletException("atencao.escritorio_inexistente");
			}else{
				clienteEscritorio = new Cliente();
				clienteEscritorio = clienteEscritorio2;
			}
		}
		return clienteEscritorio;
	}
}