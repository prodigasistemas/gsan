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
* Rômulo Aurélio de Melo Souza Filho
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

import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirGuiaPagamento");

		InserirGuiaPagamentoActionForm inserirGuiaPagamentoActionForm = (InserirGuiaPagamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Verifica se o usuário tem permissão especial para inserir Guia de Pagamento sem RA(38)
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		boolean inserirGuiaPagamentoSemRa = Fachada.getInstancia().
			verificarPermissaoEspecial(PermissaoEspecial.INSERIR_GUIA_DE_PAGAMENTO_SEM_RA, usuarioLogado);
		httpServletRequest.setAttribute("inserirGuiaPagamentoSemRa", inserirGuiaPagamentoSemRa);
		
		
		if (httpServletRequest.getParameter("menu")!=null && !httpServletRequest.getParameter("menu").equals("")){
			inserirGuiaPagamentoActionForm.setNumeroTotalPrestacao(""+1);
			inserirGuiaPagamentoActionForm.setIndicadorEmitirObservacao(ConstantesSistema.NAO.toString());
		}
		
		if(httpServletRequest.getParameter("limparDebitoTipo") != null){
			sessao.removeAttribute("colecaoGuiaDebitoTipo");
		}
		
		if (httpServletRequest.getParameter("limparOS") != null) {
			inserirGuiaPagamentoActionForm.setOrdemServico("");
			inserirGuiaPagamentoActionForm.setDescricaoOrdemServico("");
		}
		
		
		// Carregar a data corrente do sistema
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData.format(dataCorrente.getTime()));

		// Data Corrente + 60 dias
		dataCorrente.add(Calendar.DATE, 60);
		httpServletRequest.setAttribute("dataAtual60", formatoData.format(dataCorrente.getTime()));

		// Código do Cliente
		String codigoDigitadoClienteEnter = inserirGuiaPagamentoActionForm.getCodigoCliente();

		// Matrícula do Imóvel
		String codigoDigitadoImovelEnter = inserirGuiaPagamentoActionForm.getIdImovel();

		// Verifica se o código do imóvel foi digitado
		if (codigoDigitadoImovelEnter != null && !codigoDigitadoImovelEnter.trim().equals("") && 
			Integer.parseInt(codigoDigitadoImovelEnter) > 0) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

			filtroImovel.adicionarParametro(
				new ParametroSimples(FiltroImovel.ID, codigoDigitadoImovelEnter));

			Collection imovelEncontrado = fachada.pesquisar(filtroImovel,Imovel.class.getName());

			if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {

				// O imovel foi encontrado
				Imovel imovel = (Imovel) imovelEncontrado.iterator().next();

				FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();

				filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
				filtroImovelCobrancaSituacao.adicionarParametro(
					new ParametroSimples(FiltroImovelCobrancaSituacao.IMOVEL_ID, imovel.getId()));

				Collection imovelCobrancaSituacaoEncontrada = 
					fachada.pesquisar(filtroImovelCobrancaSituacao,ImovelCobrancaSituacao.class.getName());

				if (imovel.getIndicadorExclusao() == null ? false : imovel.getIndicadorExclusao().equals(Imovel.IMOVEL_EXCLUIDO)) {
					throw new ActionServletException("atencao.imovel.excluido");
				}

				// Verifica se o imóvel tem débito em cobrança administrativa
				if (imovelCobrancaSituacaoEncontrada != null && !imovelCobrancaSituacaoEncontrada.isEmpty()) {
					
					ImovelCobrancaSituacao imovelCobrancaSituacao =
						((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada).get(0)); 
					
						if (imovelCobrancaSituacao.getCobrancaSituacao() != null) {
						
						if (imovelCobrancaSituacao.getCobrancaSituacao().getId().equals(CobrancaSituacao.COBRANCA_ADMINISTRATIVA) && 
							imovelCobrancaSituacao.getDataRetiradaCobranca() == null) {
							
							throw new ActionServletException("atencao.pesquisa.imovel.cobranca_administrativa");
						}
					}
				}

				inserirGuiaPagamentoActionForm.setIdImovel(""+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());
				inserirGuiaPagamentoActionForm.setInscricaoImovel(((Imovel) ((List) imovelEncontrado).get(0)).getInscricaoFormatada());
				inserirGuiaPagamentoActionForm.setSituacaoAgua(((Imovel) ((List) imovelEncontrado)
					.get(0)).getLigacaoAguaSituacao().getDescricao());
				
				inserirGuiaPagamentoActionForm.setSituacaoEsgoto(((Imovel) ((List) imovelEncontrado)
					.get(0)).getLigacaoEsgotoSituacao().getDescricao());
				
				inserirGuiaPagamentoActionForm.setLocalidade(""+ ((Imovel) ((List) imovelEncontrado).get(0))
						.getLocalidade().getId());
				
				httpServletRequest.setAttribute("nomeCampo", "registroAtendimento");

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

				filtroClienteImovel.adicionarParametro(
					new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,codigoDigitadoImovelEnter));
				
				Collection clientesImovelEncontrado = 
					fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
				
				ClienteImovel clienteImovel = null;

				if (clientesImovelEncontrado != null && !clientesImovelEncontrado.isEmpty()) {

					// O cliente imovel foi encontrado
					Iterator clienteImovelEncontradoIterator = clientesImovelEncontrado.iterator();

					while (clienteImovelEncontradoIterator.hasNext()) {
						
						clienteImovel = (ClienteImovel) clienteImovelEncontradoIterator.next();

						if (clienteImovel.getClienteRelacaoTipo().getId().equals(ClienteRelacaoTipo.USUARIO)) {
							break;
						}
					}
					inserirGuiaPagamentoActionForm.setNomeClienteUsuario(clienteImovel.getCliente().getNome());
				}

			} else {
				inserirGuiaPagamentoActionForm.setIdImovel("");
				throw new ActionServletException("atencao.pesquisa.imovel.inexistente.guia");
			}
			
			
		}

		// Verifica se o do cliente código foi digitado
		if (codigoDigitadoClienteEnter != null && !codigoDigitadoClienteEnter.trim().equals("") && 
			Integer.parseInt(codigoDigitadoClienteEnter) > 0) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");

			filtroCliente.adicionarParametro(
				new ParametroSimples(FiltroCliente.ID, codigoDigitadoClienteEnter));

			Collection clienteEncontrado = 
				fachada.pesquisar(filtroCliente,Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {

				// O Cliente foi encontrado
				Cliente cliente = ((Cliente) ((List) clienteEncontrado).get(0));
				
				if (cliente.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",null,""+cliente.getId());
				}

				inserirGuiaPagamentoActionForm.setNomeCliente(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idTipoDebito");

				if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {

					if (cliente.getCpfFormatado() == null || 
						cliente.getCpfFormatado().equalsIgnoreCase("null")) {
						
						inserirGuiaPagamentoActionForm.setCpf("");
					} else {
						inserirGuiaPagamentoActionForm.setCpf(cliente.getCpfFormatado());
					}

					inserirGuiaPagamentoActionForm.setProfissao(
						cliente.getProfissao() == null ? "" : cliente.getProfissao().getDescricao());

				} else {

					if (cliente.getCnpjFormatado() == null ||
						cliente.getCnpjFormatado().equalsIgnoreCase("null")) {
						
						inserirGuiaPagamentoActionForm.setCpf("");

					} else {
						inserirGuiaPagamentoActionForm.setCpf(cliente.getCnpjFormatado());
					}

					inserirGuiaPagamentoActionForm.setRamoAtividade(
						cliente.getRamoAtividade() == null ? "": cliente.getRamoAtividade().getDescricao());
				}

			} else {

				inserirGuiaPagamentoActionForm.setCodigoCliente("");
				throw new ActionServletException("atencao.pesquisa.cliente.inexistente.guia");

			}
			
			httpServletRequest.setAttribute("nomeCampo", "registroAtendimento");
		}
		
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String  idOrdemServico = inserirGuiaPagamentoActionForm.getOrdemServico();
		String  idRegistroAtendimento = inserirGuiaPagamentoActionForm.getRegistroAtendimento();
		String descOrdemServico = inserirGuiaPagamentoActionForm.getDescricaoOrdemServico();
		String descRA = inserirGuiaPagamentoActionForm.getNomeRegistroAtendimento();
		
		if(descRA != null && !descRA.equals("") && sessao.getAttribute("idRA") != null && !((String)sessao.getAttribute("idRA")).trim().equals("")){
			inserirGuiaPagamentoActionForm.setRegistroAtendimento((String)sessao.getAttribute("idRA"));
		}
		
		if(idOrdemServico == null || idOrdemServico.equals("")){
			sessao.removeAttribute("desabilitaPorOS");
		}
		
		// Pesquisar Ordem Servico
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")
				&& (descOrdemServico == null || descOrdemServico.equals(""))) {
			// Faz a consulta de Ordem Servico
			this.pesquisarOrdemServico(inserirGuiaPagamentoActionForm,httpServletRequest, sessao);
		}

		// Pesquisar Registro Atendimento
		if (
				idRegistroAtendimento != null && 
				!idRegistroAtendimento.trim().equals("") && 
				( httpServletRequest.getParameter("objetoConsulta") != null && 
				  httpServletRequest.getParameter("objetoConsulta").equals( "1" ) ) ) {
//				&& (descRA == null || descRA.equals(""))) {
			// Faz a consulta de Registro Atendimento
			this.pesquisarRegistroAtendimento(inserirGuiaPagamentoActionForm,httpServletRequest);
		}
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,inserirGuiaPagamentoActionForm);
		
         // código para checar ou naum o Atualizar
        String primeiraVez = httpServletRequest.getParameter("menu");
        if (primeiraVez != null && !primeiraVez.equals("")) {
            inserirGuiaPagamentoActionForm.setQtdeDiasVencimento("30");
        }
		
        boolean alterarValorSugeridoEmTipoDebito = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO,
						usuarioLogado);

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
				alterarValorSugeridoEmTipoDebito);

		if (inserirGuiaPagamentoActionForm.getValorDebito() == null
				|| inserirGuiaPagamentoActionForm.getValorDebito()
						.equals("")) {

			httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
					true);

		}
        
        
        if(httpServletRequest.getParameter("idGuiaPagamentoItem") != null){
        	removerGuiaPagamentoItem(httpServletRequest.getParameter("idGuiaPagamentoItem"), sessao);
        }

		return retorno;
	}
	
	/**
	 * Pesquisa Registro Atendimento 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private void pesquisarRegistroAtendimento(InserirGuiaPagamentoActionForm form,HttpServletRequest httpServletRequest) {

		// Pesquisa de acordo com os parâmetros informados no filtro
		ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimentoHelper = 
			Fachada.getInstancia().obterDadosRegistroAtendimento(new Integer(form.getRegistroAtendimento()));
		
		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (obterDadosRegistroAtendimentoHelper != null && 
			obterDadosRegistroAtendimentoHelper.getRegistroAtendimento() != null) {

			// Obtém o objeto da coleção pesquisada
			RegistroAtendimento registroAtendimento = obterDadosRegistroAtendimentoHelper.getRegistroAtendimento();
			
			if ( registroAtendimento.getCodigoSituacao() != RegistroAtendimento.SITUACAO_PENDENTE ){
				throw new ActionServletException( "atencao.registro_atendimento.nao.pendente" );
			}
			
			Integer idImovel = null;
			if(form.getIdImovel() != null && !form.getIdImovel().trim().equals("") ){
				idImovel = new Integer(form.getIdImovel());
			}
			
			Integer idCliente = null;
			if(form.getCodigoCliente() != null && !form.getCodigoCliente().trim().equals("") ){
				idCliente = new Integer(form.getCodigoCliente());
				form.setLocalidade("" + registroAtendimento.getLocalidade().getId());
			}
			
			Fachada.getInstancia().validarExibirInserirGuiaPagamento(registroAtendimento,null,idImovel,idCliente);
			
			form.setRegistroAtendimento(registroAtendimento.getId().toString());
			form.setNomeRegistroAtendimento(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			

		} else {
			httpServletRequest.setAttribute("nomeCampo", "registroAtendimento");
			form.setRegistroAtendimento("");
			form.setNomeRegistroAtendimento("Registro Atendimento inexistente");
		}
	}
	
	/**
	 * Pesquisa Ordem Serviço 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private void pesquisarOrdemServico(InserirGuiaPagamentoActionForm form,HttpServletRequest httpServletRequest,
			HttpSession sessao) {
		
		// Pesquisa de acordo com os parâmetros informados no filtro
		OrdemServico ordemServico = 
			Fachada.getInstancia().recuperaOSPorId(new Integer(form.getOrdemServico()));

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (ordemServico != null) {

			Integer idImovel = null;
			if(form.getIdImovel() != null && !form.getIdImovel().trim().equals("") ){
				idImovel = new Integer(form.getIdImovel());
			}
			
			Integer idCliente = null;
			if(form.getCodigoCliente() != null && !form.getCodigoCliente().trim().equals("") ){
				idCliente = new Integer(form.getCodigoCliente());
			}

			Fachada.getInstancia().validarExibirInserirGuiaPagamento(null,ordemServico,idImovel,idCliente);

			form.setOrdemServico(""+ordemServico.getId());
			form.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
			
			RegistroAtendimento registroAtendimento = ordemServico.getRegistroAtendimento();
				
			form.setRegistroAtendimento(""+registroAtendimento.getId());
			form.setNomeRegistroAtendimento(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			
			sessao.setAttribute("idRA", ""+registroAtendimento.getId());
			
			if(ordemServico.getServicoTipo().getDebitoTipo() != null){
				
				form.setHabilitaTipoDebito("false");
				
				
				
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, ordemServico.getServicoTipo().getDebitoTipo().getId()));
				Collection colecaoFiltro = Fachada.getInstancia().pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());

				
				if(!colecaoFiltro.isEmpty()){
					DebitoTipo debitoTipo = (DebitoTipo)colecaoFiltro.iterator().next();
					Collection colecaoGuiaItem = new ArrayList();					
					GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
					guiaPagamentoItem.setDebitoTipo(debitoTipo);
					guiaPagamentoItem.setValorDebito(debitoTipo.getValorSugerido());
					colecaoGuiaItem.add(guiaPagamentoItem);
					
					sessao.setAttribute("colecaoGuiaDebitoTipo", colecaoGuiaItem);
					
					sessao.setAttribute("desabilitaPorOS", "sim");
				}			
			}

		} else {
			httpServletRequest.setAttribute("nomeCampo", "ordemServico");
			form.setOrdemServico("");
			form.setDescricaoOrdemServico("Ordem de serviço inexistente");
			
		}
	}

	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,InserirGuiaPagamentoActionForm form){
		
		//Registro Atendimento
		if(form.getRegistroAtendimento() != null && !form.getRegistroAtendimento().equals("") && 
			form.getNomeRegistroAtendimento() != null && !form.getNomeRegistroAtendimento().equals("")){
					
			httpServletRequest.setAttribute("numeroRAEncontrada","true");
		}
		
		//Documento Cobrança
		if(form.getOrdemServico() != null && !form.getOrdemServico().equals("") && 
			form.getDescricaoOrdemServico() != null && !form.getDescricaoOrdemServico().equals("")){
					
			httpServletRequest.setAttribute("ordemServicoEncontrada","true");
		}
	}
	
	private void removerGuiaPagamentoItem(String idGuiaPagamentoItem, HttpSession sessao){
		Collection colecaoSessao = (Collection)sessao.getAttribute("colecaoGuiaDebitoTipo");
		
		Iterator iterator = colecaoSessao.iterator();
		while(iterator.hasNext()){
			Integer idGuiaRemover = new Integer(idGuiaPagamentoItem);
			GuiaPagamentoItem guiaPagamentoItem = (GuiaPagamentoItem)iterator.next();
			if(guiaPagamentoItem.getDebitoTipo().getId().equals(idGuiaRemover)){
				iterator.remove();
			}
		}
		sessao.setAttribute("colecaoGuiaDebitoTipo", colecaoSessao);
	}
	
	
}
