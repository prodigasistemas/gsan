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
package gcom.gui.faturamento.credito;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoARealizar;
import gcom.faturamento.credito.FiltroCreditoOrigem;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirCreditoARealizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirInserirCreditoARealizar");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.INDICADORUSO, ConstantesSistema.SIM.intValue()));
		Collection<CreditoTipo> collectionCreditoTipo = fachada.pesquisar(
				filtroCreditoTipo, CreditoTipo.class.getName());

		httpServletRequest.setAttribute("collectionCreditoTipo",
				collectionCreditoTipo);

		FiltroCreditoOrigem filtroCreditoOrigem = new FiltroCreditoOrigem();
		filtroCreditoOrigem.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.INDICADORUSO, ConstantesSistema.SIM.intValue()));
		Collection<CreditoOrigem> collectionCreditoOrigem = fachada.pesquisar(filtroCreditoOrigem, CreditoOrigem.class.getName());

		httpServletRequest.setAttribute("collectionCreditoOrigem",
				collectionCreditoOrigem);

		InserirCreditoARealizarActionForm inserirCreditoARealizarActionForm = (InserirCreditoARealizarActionForm) actionForm;

		String limparForm = (String) httpServletRequest.getParameter("limparForm");
		String idRegistroAtendimento = inserirCreditoARealizarActionForm.getRegistroAtendimento();
		String idOrdemSerico = inserirCreditoARealizarActionForm.getOrdemServico();
		
		String idImovel = null;
		
	    if (httpServletRequest.getParameter("objetoConsulta") != null
                && httpServletRequest.getParameter("objetoConsulta").equalsIgnoreCase("2")) {
			  
			 //pesquisa o imovel pelo registro de atendimento 
			if(idRegistroAtendimento != null && !idRegistroAtendimento.trim().equals("")){
				FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
				filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID,idRegistroAtendimento));
				filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.IMOVEL);
				filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
				filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFICACAO);
				
				Collection colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento,RegistroAtendimento.class.getName());
				if(colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()){
				
					RegistroAtendimento registroAtendimento = (RegistroAtendimento) colecaoRegistroAtendimento.iterator().next();
					
					//Registro de Atendimento não está associado a um imóvel
					if(registroAtendimento.getImovel() == null){
						//FS0005 - Validar Registro de Atendimento
						throw new ActionServletException(
							"atencao.registro_atendimento.nao.associado.imovel");
					}
					
					//Registro de Atendimento está encerrado
					if(registroAtendimento.getAtendimentoMotivoEncerramento() != null){
						//FS0005 - Validar Registro de Atendimento
						throw new ActionServletException(
							"atencao.registro_atendimento.esta.encerrado");
					}

					//Especificação do Tipo de Solicitação do Registro de Atendimento não permite geração de credito
					if(registroAtendimento.getSolicitacaoTipoEspecificacao().getIndicadorGeracaoCredito() == 2){
						//FS0005 - Validar Registro de Atendimento
						throw new ActionServletException(
							"atencao.registro_atendimento.nao.permite.geracao.credito");
					}

					idImovel = registroAtendimento.getImovel().getId().toString();
					
					inserirCreditoARealizarActionForm.setRegistroAtendimento(registroAtendimento.getId().toString());
					inserirCreditoARealizarActionForm.setNomeRegistroAtendimento(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
					
					inserirCreditoARealizarActionForm.setMatriculaImovel(idImovel);
					
					httpServletRequest.setAttribute("corRegistroAtendimento",
					"valor");
					httpServletRequest.setAttribute("nomeCampo",
					"ordemServico");
					
					sessao.setAttribute("travarRegistroAtendimento",
					"nao");
					sessao.setAttribute("travarOrdemServico",
					null);
					inserirCreditoARealizarActionForm.setOrdemServico("");
					inserirCreditoARealizarActionForm.setNomeOrdemServico("");
											
					sessao.setAttribute("travarTipoCredito",
					"nao");
					
				}else{
					//FS0004-Validar Registro de Atendimento
					inserirCreditoARealizarActionForm.setMatriculaImovel("");
					inserirCreditoARealizarActionForm.setInscricaoImovel("");
					inserirCreditoARealizarActionForm.setNomeCliente("");
					inserirCreditoARealizarActionForm.setSituacaoAgua("");
					inserirCreditoARealizarActionForm.setSituacaoEsgoto("");
					inserirCreditoARealizarActionForm.setNomeRegistroAtendimento("RA inexistente");
					httpServletRequest.setAttribute("corRegistroAtendimento",
					"exception");
					httpServletRequest.setAttribute("nomeCampo",
					"registroAtendimento");
					sessao.setAttribute("travarRegistroAtendimento",
							"nao");
				}
			}
	    }else if (httpServletRequest.getParameter("objetoConsulta") != null
		       && httpServletRequest.getParameter("objetoConsulta").equalsIgnoreCase("3")) {
			
			//pesquisa o imovel pela ordem de serviço 
			if(idOrdemSerico != null && !idOrdemSerico.trim().equals("")){
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID,idOrdemSerico));
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.IMOVEL);
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.REGISTRO_ATENDIMENTO);
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.CREDITO_TIPO);
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.SOLICITACAO_TIPO_ESPECIFICACAO);
				
				Collection colecaoOrdemServico = fachada.pesquisar(filtroOrdemServico,OrdemServico.class.getName());
				if(colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()){
				
					OrdemServico ordemServico = (OrdemServico) colecaoOrdemServico.iterator().next();
					
					//Ordem de Serviço não está associada a um Registro de Atendimento
					if(ordemServico.getRegistroAtendimento() == null){
						//FS0005 - Validar Registro de Atendimento
						throw new ActionServletException(
							"atencao.ordem_servico.nao.esta.associado.registro_atendimento");
					}

					//Registro de Atendimento da Ordem de Serviço não está associado a um imóvel
					if(ordemServico.getRegistroAtendimento().getImovel() == null){
						//FS0007 - Validar Ordem de Serviço
						throw new ActionServletException(
							"atencao.ordem_servico.imovel.registro_atendimento.nao.associado");
					}
					
					if(ordemServico.getImovel() != null){
						idImovel = ordemServico.getImovel().getId().toString();	
					}
					
					inserirCreditoARealizarActionForm.setOrdemServico(ordemServico.getId().toString());
					inserirCreditoARealizarActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
					
					inserirCreditoARealizarActionForm.setRegistroAtendimento(ordemServico.getRegistroAtendimento().getId().toString());
					inserirCreditoARealizarActionForm.setNomeRegistroAtendimento(ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getDescricao());
					inserirCreditoARealizarActionForm.setMatriculaImovel(idImovel);

					httpServletRequest.setAttribute("corRegistroAtendimento", "valor");
					sessao.setAttribute("travarRegistroAtendimento", null);
					
					httpServletRequest.setAttribute("corNomeOrdemServico", "valor");
					sessao.setAttribute("nomeCampo", "tipoCredito");
					                                 
					sessao.setAttribute("travarOrdemServico", "nao");
					
					//validar credito tipo
					if(ordemServico.getServicoTipo().getCreditoTipo() != null){
						inserirCreditoARealizarActionForm.setTipoCredito(ordemServico.getServicoTipo().getCreditoTipo().getId().toString());
						sessao.setAttribute("travarTipoCredito", null);
					}else{
						sessao.setAttribute("travarTipoCredito", "valor");
					}
					
					//não encontrou a RA
				}else{
					//FS0004-Validar Registro de Atendimento
					inserirCreditoARealizarActionForm.setMatriculaImovel("");
					inserirCreditoARealizarActionForm.setInscricaoImovel("");
					inserirCreditoARealizarActionForm.setNomeCliente("");
					inserirCreditoARealizarActionForm.setSituacaoAgua("");
					inserirCreditoARealizarActionForm.setSituacaoEsgoto("");
					inserirCreditoARealizarActionForm.setNomeOrdemServico("OS inexistente");
					httpServletRequest.setAttribute("corNomeOrdemServico",
					"exception");
					httpServletRequest.setAttribute("nomeCampo",
					"ordemServico");
					sessao.setAttribute("travarOrdemServico",
					"nao");
					sessao.setAttribute("travarTipoCredito",
					"nao");
				}
			}
		}else{
			sessao.setAttribute("travarRegistroAtendimento",
			"valor");
			sessao.setAttribute("travarOrdemServico",
			"valor");
			sessao.setAttribute("travarTipoCredito",
			"valor");
		}

		if (idImovel != null && !idImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("quadra");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
			

			Collection<Imovel> imovelPesquisado = fachada.pesquisar(
					filtroImovel, Imovel.class.getName());

			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				sessao.setAttribute("corImovel","exception");
           		inserirCreditoARealizarActionForm
           			.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
           		
           		inserirCreditoARealizarActionForm.setNomeCliente("");
           		inserirCreditoARealizarActionForm.setSituacaoAgua("");
           		inserirCreditoARealizarActionForm.setSituacaoEsgoto("");
			}

			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				if (imovel.getIndicadorExclusao() == Imovel.IMOVEL_EXCLUIDO) {
					throw new ActionServletException(
							"atencao.pesquisa.imovel.excluido");
				}
			}

			// [FS0002 - Verificar usuário com débito em cobrança administrativa
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();

				filtroImovelCobrancaSituacao
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
				filtroImovelCobrancaSituacao
					.adicionarParametro(new ParametroSimples(FiltroImovelCobrancaSituacao.ID_COBRANCA_SITUACAO,CobrancaSituacao.COBRANCA_ADMINISTRATIVA));

				filtroImovelCobrancaSituacao
					.adicionarParametro(new ParametroNulo(FiltroImovelCobrancaSituacao.DATA_RETIRADA_COBRANCA));
				
				filtroImovelCobrancaSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroImovelCobrancaSituacao.IMOVEL_ID, imovel
										.getId()));

				Collection imovelCobrancaSituacaoEncontrada = fachada
						.pesquisar(filtroImovelCobrancaSituacao,
								ImovelCobrancaSituacao.class.getName());

				// Verifica se o imóvel tem débito em cobrança administrativa
				if (imovelCobrancaSituacaoEncontrada != null
						&& !imovelCobrancaSituacaoEncontrada.isEmpty()) {
							throw new ActionServletException(
									"atencao.pesquisa.imovel.cobranca_administrativa");
				}
			}

			// [FS0003 - Verificar situação ligação de ágiua e esgoto]
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				if ((imovel.getLigacaoAguaSituacao() != null)
						&& ((imovel.getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.POTENCIAL) || (imovel
								.getLigacaoEsgotoSituacao().getId() == LigacaoAguaSituacao.FACTIVEL))
						&& (imovel.getLigacaoEsgotoSituacao().getId() != LigacaoEsgotoSituacao.LIGADO)) {
					throw new ActionServletException(
							"atencao.pesquisa.imovel.inativo");
				}
			}

			// Obtem o cliente imovel do imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {

				Imovel imovel = imovelPesquisado.iterator().next();

				sessao.setAttribute("imovelPesquisado", imovel);

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, idImovel));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
						ClienteRelacaoTipo.USUARIO));

				Collection<ClienteImovel> clienteImovelPesquisado = fachada
						.pesquisar(filtroClienteImovel, ClienteImovel.class
								.getName());

				if (clienteImovelPesquisado != null
						&& !clienteImovelPesquisado.isEmpty()) {
					ClienteImovel clienteImovel = clienteImovelPesquisado
							.iterator().next();
					if (clienteImovel.getCliente() != null) {
						inserirCreditoARealizarActionForm
								.setNomeCliente(clienteImovel.getCliente()
										.getNome());
					}
				}
				if (imovel.getLigacaoAguaSituacao() != null) {
					inserirCreditoARealizarActionForm.setSituacaoAgua(imovel
							.getLigacaoAguaSituacao().getDescricao());
				}

				if (imovel.getLigacaoEsgotoSituacao() != null) {
					inserirCreditoARealizarActionForm.setSituacaoEsgoto(imovel
							.getLigacaoEsgotoSituacao().getDescricao());
				}
				inserirCreditoARealizarActionForm
						.setMatriculaImovel(idImovel);

				inserirCreditoARealizarActionForm.setInscricaoImovel(imovel
						.getInscricaoFormatada());
			}
		}

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {

			inserirCreditoARealizarActionForm.reset(actionMapping,
					httpServletRequest);

			if (sessao.getAttribute("imovelPesquisado") != null) {
				sessao.removeAttribute("imovelPesquisado");
			}
		}

		return retorno;
	}
}