package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 13/11/2006
 */
public class ExibirAtualizarAdicionarSolicitacaoEspecificacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAdicionarSolicitacaoEspecificacao");

		AtualizarAdicionarSolicitacaoEspecificacaoActionForm atualizarAdicionarSolicitacaoEspecificacaoActionForm = (AtualizarAdicionarSolicitacaoEspecificacaoActionForm) actionForm;
		//
		// String idSolicitacaoTipo = (String) sessao
		// .getAttribute("idSolicitacaoTipo");

		if (sessao.getAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm") != null) {

			atualizarAdicionarSolicitacaoEspecificacaoActionForm = 
				(AtualizarAdicionarSolicitacaoEspecificacaoActionForm) sessao
					.getAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm");

		}

		String posicao = null;

		Set colecaoEspecificacaoServicoTipo = null;

		if (httpServletRequest.getParameter("posicao") != null) {
			posicao = (String) httpServletRequest.getParameter("posicao");

			sessao.setAttribute("posicao", posicao);
			sessao.setAttribute("posicaoComponente", new Integer(posicao));

			sessao.removeAttribute("colecaoEspecificacaoServicoTipo");

		}

		Integer posicaoComponente = null;

		Collection colecaoSolicitacaoTipoEspecificacao = 
			(Collection) sessao.getAttribute("colecaoSolicitacaoTipoEspecificacao");

		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("inserir") != null) {
			sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
			sessao.removeAttribute("atualizar");
			sessao.removeAttribute("colecaoEspecificacaoServicoTipo");

		}

		if (httpServletRequest.getParameter("atualizar") != null) {

			httpServletRequest.setAttribute("atualizar", true);
			sessao.setAttribute("atualizar", true);
		}

		if (sessao.getAttribute("atualizar") != null && 
			httpServletRequest.getParameter("objetoConsulta") == null) {

			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setCabecalho("Atualizar");
			httpServletRequest.setAttribute("atualizar", true);

			sessao.removeAttribute("inserir");

			if (colecaoSolicitacaoTipoEspecificacao != null
					&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {

				if (sessao.getAttribute("posicaoComponente") != null) {
					posicaoComponente = (Integer) sessao.getAttribute("posicaoComponente");
				} else {
					posicaoComponente = 0;
				}

				sessao.removeAttribute("posicao");
				sessao.setAttribute("posicaoComponente", posicaoComponente);

				int index = 0;

				Iterator colecaoSolicitacaoTipoEspecificacaoIterator = colecaoSolicitacaoTipoEspecificacao.iterator();

				while (colecaoSolicitacaoTipoEspecificacaoIterator.hasNext()) {

					index++;

					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacaoIterator.next();

					if (index == posicaoComponente) {

						if (sessao.getAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm") == null) {

							sessao.setAttribute("idSolicitacaoEspecificacao",solicitacaoTipoEspecificacao.getId());

							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoSolicitacao(solicitacaoTipoEspecificacao.getDescricao());
							if (solicitacaoTipoEspecificacao.getDiasPrazo() == null) {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento("");
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento(""	+ solicitacaoTipoEspecificacao.getDiasPrazo());
							}
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoCalcada("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoCalcada());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoRua("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoRua());							
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoAgua(""	+ solicitacaoTipoEspecificacao.getIndicadorLigacaoAgua());
							
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrancaMaterial("" + solicitacaoTipoEspecificacao.getIndicadorCobrancaMaterial());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorParecerEncerramento(""	+ solicitacaoTipoEspecificacao.getIndicadorParecerEncerramento());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarDebito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoDebito());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarCredito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoCredito());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCliente("" + solicitacaoTipoEspecificacao.getIndicadorCliente());
							
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorVerificarDebito("" + solicitacaoTipoEspecificacao.getIndicadorVerificarDebito());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorMatriculaImovel("" + solicitacaoTipoEspecificacao.getIndicadorMatricula());
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorUrgencia("" + solicitacaoTipoEspecificacao.getIndicadorUrgencia());
							
							if (solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao() != null
									&& !solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().equals("")) {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("" + solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().getId());
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("");
							}

							//Colocado por Raphael Rossiter em 26/02/2008
							if (solicitacaoTipoEspecificacao.getDebitoTipo() != null) {
								
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setIdDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getId().toString());
								
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setDescricaoDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getDescricao());
								
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setIdDebitoTipo("");
								
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setDescricaoDebitoTipo("");
							}
							
							//Colocado por Raphael Rossiter em 26/02/2008
							if (solicitacaoTipoEspecificacao.getValorDebito() != null){
								
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setValorDebito(Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getValorDebito()));
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.setValorDebito("");
							}

							//Colocado por Rafael Corrêa em 14/08/2008
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorEncerramentoAutomatico()));
							//Colocado por Raphael Rossiter em 14/03/2008
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorPermiteAlterarValor()));							
							//Colocado por Raphael Rossiter em 14/03/2008
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorCobrarJuros()));
							// Colocado por Mariana Victor em 10/01/2011
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorInformarContaRA()));

							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarPagamentoDP(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorInformarPagamentoDuplicidade()));
							
							// colocado por Nathalia Santos em 27/04/2011
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorAlterarVencimentoAlternativo(
							         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorAlterarVencimentoAlternativo()));
							
							// colocado por Davi Menezes em 30/08/2011
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLojaVirtual(
									String.valueOf(solicitacaoTipoEspecificacao.getIndicadorLojaVirtual()));
							
							if (solicitacaoTipoEspecificacao.getUnidadeOrganizacional() != null
									&& !solicitacaoTipoEspecificacao.getUnidadeOrganizacional().equals("")) {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("" + solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getId());
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getDescricao());
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("");
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao("");
							}
														
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGeraOrdemServico("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoOrdemServico());
							
							if (solicitacaoTipoEspecificacao.getServicoTipo() != null
									&& !solicitacaoTipoEspecificacao.getServicoTipo().equals("")) {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getId().toString());
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getDescricao());
							} else {
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("");
								atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("");
							}
							
							
                            Boolean trocou = false;
                            
                            if ( httpServletRequest.getParameter("trocou") != null ){
                                trocou = (Boolean) httpServletRequest.getParameter("trocou").equals("sim");
                            }
                            
                            // Colocado por Bruno Barros
                            adicionarEspecificacao( 
                                    atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                                    solicitacaoTipoEspecificacao,
                                    sessao,
                                    fachada,
                                    trocou );
						}
						
						if (sessao.getAttribute("colecaoEspecificacaoServicoTipo") != null) {
							colecaoEspecificacaoServicoTipo = (Set) sessao.getAttribute("colecaoEspecificacaoServicoTipo");
							solicitacaoTipoEspecificacao.setEspecificacaoServicoTipos(colecaoEspecificacaoServicoTipo);
						}

						colecaoEspecificacaoServicoTipo = solicitacaoTipoEspecificacao.getEspecificacaoServicoTipos();

						httpServletRequest.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

						sessao.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

						FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
						Collection colecaoImovelSituacao = fachada.pesquisar(filtroEspecificacaoImovelSituacao,EspecificacaoImovelSituacao.class.getName());
						httpServletRequest.setAttribute("colecaoImovelSituacao", colecaoImovelSituacao);

					}
				}

			}
		} else if (httpServletRequest.getParameter("objetoConsulta") == null){
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setCabecalho("Inserir");

			sessao.removeAttribute("atualizar");

			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;

			if (sessao.getAttribute("solicitacaoTipoEspecificacao") == null) {

				solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();

			} else {
				solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) sessao.getAttribute("solicitacaoTipoEspecificacao");
			}
			if (sessao.getAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm") == null) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoSolicitacao(solicitacaoTipoEspecificacao.getDescricao());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoCalcada("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoCalcada());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoAgua(""	+ solicitacaoTipoEspecificacao.getIndicadorLigacaoAgua());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoRua("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoRua());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrancaMaterial("" + solicitacaoTipoEspecificacao.getIndicadorCobrancaMaterial());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorParecerEncerramento(""	+ solicitacaoTipoEspecificacao.getIndicadorParecerEncerramento());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarDebito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoDebito());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarCredito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoCredito());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCliente("" + solicitacaoTipoEspecificacao.getIndicadorCliente());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorVerificarDebito("" + solicitacaoTipoEspecificacao.getIndicadorVerificarDebito());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico("" + solicitacaoTipoEspecificacao.getIndicadorEncerramentoAutomatico());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorUrgencia("" + solicitacaoTipoEspecificacao.getIndicadorUrgencia());

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorMatriculaImovel("" + solicitacaoTipoEspecificacao.getIndicadorMatricula());
				if (solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao() != null
						&& !solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().equals("")) {
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("" + solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().getId());
				}

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGeraOrdemServico("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoOrdemServico());

				if (solicitacaoTipoEspecificacao.getUnidadeOrganizacional() != null
						&& !solicitacaoTipoEspecificacao.getUnidadeOrganizacional().equals("")) {
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("" + solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getId());
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getDescricao());
				}
				if (solicitacaoTipoEspecificacao.getServicoTipo() != null
						&& !solicitacaoTipoEspecificacao.getServicoTipo().equals("")) {
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getId().toString());
					atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getDescricao());
				}
				
				//Colocado por Raphael Rossiter em 26/02/2008
				if (solicitacaoTipoEspecificacao.getDebitoTipo() != null) {
					
					atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setIdDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getId().toString());
					
					atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setDescricaoDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getDescricao());
					
				}

				//Colocado por Raphael Rossiter em 26/02/2008
				if (solicitacaoTipoEspecificacao.getValorDebito() != null){
					
					atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setValorDebito(Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getValorDebito()));
				}
				
				//Colocado por Raphael Rossiter em 14/03/2008
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor(
				String.valueOf(solicitacaoTipoEspecificacao.getIndicadorPermiteAlterarValor()));

				//Colocado por Raphael Rossiter em 14/03/2008
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros(
				String.valueOf(solicitacaoTipoEspecificacao.getIndicadorCobrarJuros()));

				//Colocado por Mariana Victor em 10/01/2011
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA(
				String.valueOf(solicitacaoTipoEspecificacao.getIndicadorInformarContaRA()));
				
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento( 
                        ( solicitacaoTipoEspecificacao.getDiasPrazo() == null ? "" : solicitacaoTipoEspecificacao.getDiasPrazo()+"" ) );
                
                // Colocado por Nathalia Santos em 27/04/2011
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorAlterarVencimentoAlternativo(
        				String.valueOf(solicitacaoTipoEspecificacao.getIndicadorAlterarVencimentoAlternativo()));
                
                // Colocado por Bruno Barros
                adicionarEspecificacao( 
                        atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                        solicitacaoTipoEspecificacao,
                        sessao,
                        fachada,
                        false );
				
			}
			if (sessao.getAttribute("colecaoEspecificacaoServicoTipo") != null) {

				colecaoEspecificacaoServicoTipo = (Set) sessao.getAttribute("colecaoEspecificacaoServicoTipo");
				solicitacaoTipoEspecificacao.setEspecificacaoServicoTipos(colecaoEspecificacaoServicoTipo);

			}
			colecaoEspecificacaoServicoTipo = solicitacaoTipoEspecificacao.getEspecificacaoServicoTipos();

			if (httpServletRequest.getParameter("adicionar") != null) {

				colecaoEspecificacaoServicoTipo = null;
			}
			httpServletRequest.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

		} else {
		    httpServletRequest.setAttribute( "atualizarAdicionarSolicitacaoEspecificacaoActionForm", atualizarAdicionarSolicitacaoEspecificacaoActionForm );
            
            // Colocado por Bruno Barros
            adicionarEspecificacao( 
                    atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                    new SolicitacaoTipoEspecificacao(),
                    sessao,
                    fachada,
                    false );            
        }

		if (httpServletRequest.getParameter("desfazer") != null	&& !httpServletRequest.getParameter("desfazer").equals("")) {

			Integer idSolicitacaoEspecificacao = (Integer) sessao.getAttribute("idSolicitacaoEspecificacao");

			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA);
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,idSolicitacaoEspecificacao.toString()));
			Collection colecaoSolicitacaoTipoEspecificacaoDesfazer = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao,SolicitacaoTipoEspecificacao.class.getName());

			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacaoDesfazer.iterator().next();

			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoSolicitacao(solicitacaoTipoEspecificacao.getDescricao());
			if (solicitacaoTipoEspecificacao.getDiasPrazo() == null) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento("");
			} else {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento("" + solicitacaoTipoEspecificacao.getDiasPrazo());
			}
			atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setIndicadorPavimentoCalcada("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoCalcada());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoAgua("" + solicitacaoTipoEspecificacao.getIndicadorLigacaoAgua());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoRua("" + solicitacaoTipoEspecificacao.getIndicadorPavimentoRua());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrancaMaterial("" + solicitacaoTipoEspecificacao.getIndicadorCobrancaMaterial());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorParecerEncerramento("" + solicitacaoTipoEspecificacao.getIndicadorParecerEncerramento());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarDebito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoDebito());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarCredito("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoCredito());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCliente("" + solicitacaoTipoEspecificacao.getIndicadorCliente());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorVerificarDebito("" + solicitacaoTipoEspecificacao.getIndicadorVerificarDebito());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorMatriculaImovel("" + solicitacaoTipoEspecificacao.getIndicadorMatricula());
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorUrgencia("" + solicitacaoTipoEspecificacao.getIndicadorUrgencia());
			if (solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao() != null
					&& !solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().equals("")) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("" + solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().getId());
			}

			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGeraOrdemServico("" + solicitacaoTipoEspecificacao.getIndicadorGeracaoOrdemServico());

			if (solicitacaoTipoEspecificacao.getUnidadeOrganizacional() != null
					&& !solicitacaoTipoEspecificacao.getUnidadeOrganizacional().equals("")) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("" + solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getId());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(solicitacaoTipoEspecificacao.getUnidadeOrganizacional().getDescricao());
			}
			if (solicitacaoTipoEspecificacao.getServicoTipo() != null
					&& !solicitacaoTipoEspecificacao.getServicoTipo().equals("")) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getId().toString());

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("" + solicitacaoTipoEspecificacao.getServicoTipo().getDescricao());
			}
			
			//Colocado por Raphael Rossiter em 26/02/2008
			if (solicitacaoTipoEspecificacao.getDebitoTipo() != null) {
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIdDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getId().toString());
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setDescricaoDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getDescricao());
				
			}

			//Colocado por Raphael Rossiter em 26/02/2008
			if (solicitacaoTipoEspecificacao.getValorDebito() != null){
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setValorDebito(Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getValorDebito()));
			}
			
			//Colocado por Raphael Rossiter em 14/03/2008
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor(
			String.valueOf(solicitacaoTipoEspecificacao.getIndicadorPermiteAlterarValor()));
			
			//Colocado por Raphael Rossiter em 14/03/2008
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros(
			String.valueOf(solicitacaoTipoEspecificacao.getIndicadorCobrarJuros()));
			
			//Colocado por Rafael Corrêa em 14/08/2008
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico(
			String.valueOf(solicitacaoTipoEspecificacao.getIndicadorEncerramentoAutomatico()));

			//Colocado por Mariana Victor em 10/01/2011
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA(
			String.valueOf(solicitacaoTipoEspecificacao.getIndicadorInformarContaRA()));
			
			//Colocado por Nathalia Santos em 27/04/2011
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorAlterarVencimentoAlternativo(
					String.valueOf(solicitacaoTipoEspecificacao.getIndicadorAlterarVencimentoAlternativo()));
			
			colecaoEspecificacaoServicoTipo = solicitacaoTipoEspecificacao.getEspecificacaoServicoTipos();

			httpServletRequest.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

			sessao.setAttribute("colecaoEspecificacaoServicoTipo",colecaoEspecificacaoServicoTipo);

			FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
			Collection colecaoImovelSituacao = fachada.pesquisar(filtroEspecificacaoImovelSituacao,EspecificacaoImovelSituacao.class.getName());
			httpServletRequest.setAttribute("colecaoImovelSituacao",colecaoImovelSituacao);
            
            // Colocado por Bruno Barros
            adicionarEspecificacao( 
                    atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                    solicitacaoTipoEspecificacao,
                    sessao,
                    fachada,
                    false);
		}
		// caso exista o parametro então limpa a sessão e o form
		if (httpServletRequest.getParameter("limpaSessao") != null
				&& !httpServletRequest.getParameter("limpaSessao").equals("")) {

			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoSolicitacao("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setPrazoPrevisaoAtendimento("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoCalcada("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorLigacaoAgua("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPavimentoRua("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrancaMaterial("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorParecerEncerramento("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarDebito("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGerarCredito("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCliente("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorMatriculaImovel("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorGeraOrdemServico("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("");
			
			//Colocado por Raphael Rossiter em 26/02/2008
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdDebitoTipo("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoDebitoTipo("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setValorDebito("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico("");
            
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorUrgencia("2");
			
			atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA("");
			
            // Colocado por Bruno Barros
            adicionarEspecificacao( 
                    atualizarAdicionarSolicitacaoEspecificacaoActionForm,
                    new SolicitacaoTipoEspecificacao(),
                    sessao,
                    fachada,
                    true);            
            
			sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
		}

		// Verifica se o tipoConsulta é diferente de nulo ou vazio.
		// Nesse caso é quando um o retorno da consulta vem para o action ao inves de ir
		// direto para o jsp
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			// verifica se retornou da pesquisa de unidade de tramite
			if (httpServletRequest.getParameter("tipoConsulta").equals("unidadeAtendimento")) {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao(httpServletRequest.getParameter("idCampoEnviarDados"));
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(httpServletRequest.getParameter("descricaoCampoEnviarDados"));

			}
			// verifica se retornou da pesquisa de tipo de serviço
			if (httpServletRequest.getParameter("tipoConsulta").equals("tipoServico")) {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS(httpServletRequest.getParameter("idCampoEnviarDados"));
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS(httpServletRequest.getParameter("descricaoCampoEnviarDados"));

			}
			
			/*
			 * Colocado por Raphael Rossiter em 25/02/2008
			 * Verifica se retornou da pesquisa de tipo de débito
			 */
			if (httpServletRequest.getParameter("tipoConsulta").equals("tipoDebito")) {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIdDebitoTipo(httpServletRequest.getParameter("idCampoEnviarDados"));

				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setDescricaoDebitoTipo(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
		}
		// -------Parte que trata do código quando o usuário tecla enter
		String idUnidadeInicialTramitacao = atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdUnidadeTramitacao();
		String descricaoInicialTramitacao = atualizarAdicionarSolicitacaoEspecificacaoActionForm.getDescricaoUnidadeTramitacao();
		String idTipoServicoOS = (String) atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdServicoOS();
		String descricaoServicoOS = atualizarAdicionarSolicitacaoEspecificacaoActionForm.getDescricaoServicoOS();

		//Colocado por Raphael Rossiter em 26/02/2008
		String idDebitoTipo = (String) atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdDebitoTipo();
		String descricaoDebitoTipo = atualizarAdicionarSolicitacaoEspecificacaoActionForm.getDescricaoDebitoTipo();
		
		// Verifica se o código foi digitado pela primeira vez ou se foi modificado
		if (idUnidadeInicialTramitacao != null
				&& !idUnidadeInicialTramitacao.trim().equals("")
				&& (descricaoInicialTramitacao == null || descricaoInicialTramitacao.trim().equals(""))) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,idUnidadeInicialTramitacao));
//			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection unidadeOrganizacionalEncontrado = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			if (unidadeOrganizacionalEncontrado != null && !unidadeOrganizacionalEncontrado.isEmpty()) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("" + ((UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado).get(0)).getId());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao(((UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado).get(0)).getDescricao());
				httpServletRequest.setAttribute("idUnidadeTramitacaoNaoEncontrado", "true");
				httpServletRequest.setAttribute("nomeCampo","indicadorGeraOrdemServico");

			} else {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdUnidadeTramitacao("");
				httpServletRequest.setAttribute("nomeCampo","idUnidadeTramitacao");
				httpServletRequest.setAttribute("idUnidadeTramitacaoNaoEncontrado", "exception");
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoUnidadeTramitacao("Unidade Organizacional Inexistente");

			}

		}

		// Verifica se o código foi digitado pela primeira vez ou se foi modificado
		if (idTipoServicoOS != null	&& !idTipoServicoOS.trim().equals("")
				&& (descricaoServicoOS == null || descricaoServicoOS.trim().equals(""))) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idTipoServicoOS));
			Collection servicoTipoEncontrado = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

			if (servicoTipoEncontrado != null && !servicoTipoEncontrado.isEmpty()) {

				// [SF0003] - Validar Tipo Serviço
				fachada.verificarServicoTipoReferencia(new Integer(idTipoServicoOS));

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("" + ((ServicoTipo) ((List) servicoTipoEncontrado).get(0)).getId());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS(((ServicoTipo) ((List) servicoTipoEncontrado).get(0)).getDescricao());
				httpServletRequest.setAttribute("idServicoOSNaoEncontrado",	"true");
				httpServletRequest.setAttribute("nomeCampo","adicionarTipoServico");

			} else {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("");
				httpServletRequest.setAttribute("nomeCampo", "idServicoOS");
				httpServletRequest.setAttribute("idServicoOSNaoEncontrado","exception");
				atualizarAdicionarSolicitacaoEspecificacaoActionForm.setDescricaoServicoOS("Tipo Serviço Inexistente");

			}

		}
		
		
		/*
		 * Colocado por Raphael Rossiter em 26/02/2008
		 * Verifica se o código foi digitado pela primeira vez ou se foi  modificado
		 */
		if (idDebitoTipo != null && !idDebitoTipo.trim().equals("") && 
			(descricaoDebitoTipo == null || descricaoDebitoTipo.trim().equals(""))) {

			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
			FiltroDebitoTipo.ID, idDebitoTipo));

			Collection debitoTipoEncontrado = fachada.pesquisar(
			filtroDebitoTipo, DebitoTipo.class.getName());

			if (debitoTipoEncontrado != null && !debitoTipoEncontrado.isEmpty()) {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIdDebitoTipo(idDebitoTipo);
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setDescricaoDebitoTipo(((DebitoTipo) ((List) debitoTipoEncontrado)
				.get(0)).getDescricao());
				
				httpServletRequest.setAttribute("nomeCampo", "valorDebito");

			} else {

				//[FS0008] - Validar Tipo de débito
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setIdDebitoTipo("");
				
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.setDescricaoDebitoTipo("Tipo de Débito Inexistente");
				
				httpServletRequest.setAttribute("corDebitoTipo", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idDebitoTipo");

			}

		}

		FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
		Collection colecaoImovelSituacao = fachada.pesquisar(filtroEspecificacaoImovelSituacao,EspecificacaoImovelSituacao.class.getName());
		sessao.setAttribute("colecaoImovelSituacao", colecaoImovelSituacao);

		// -------Fim da Parte que trata do código quando o usuário
		// tecla
		// enter

		// remove o retorno da
		// solicitação_especificação_tipo_servico_adicionar_popup.jsp
		sessao.removeAttribute("retornarTelaPopup");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaUnidadeOrganizacional");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoServico");
		
		sessao.removeAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm");

		return retorno;
	}
    
    /**
     * 
     * [UC0401] Manter tipo de solicitacao com especificações
     * Mostra os dados necessários para a inclusão do novo RA
     *
     * @author bruno
     * @date 13/04/2009
     *
     * @param atualizarAdicionarSolicitacaoEspecificacaoActionForm
     * @param solicitacaoTipoEspecificacao
     * @param sessao
     */
    private void adicionarEspecificacao( 
            AtualizarAdicionarSolicitacaoEspecificacaoActionForm atualizarAdicionarSolicitacaoEspecificacaoActionForm,
            SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao,
            HttpSession sessao,
            Fachada fachada,
            boolean trocou ){
        
        if ( trocou ){
            if ( solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA() != null ){
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" + solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getId() );
            } else {
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" );
                atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( "" );
            }
        }
        
       FiltroSolicitacaoTipo filtro = new FiltroSolicitacaoTipo();
       filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA, 2 ) );
       filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO, 1 ) );
       filtro.setCampoOrderBy( "descricao" );
       Collection<SolicitacaoTipo> colSolTip = fachada.pesquisar( filtro, SolicitacaoTipo.class.getName() );
       
       sessao.setAttribute( "colecaoTipoSolicitacao", colSolTip );                            

       // Verificamos se o tipo de especificação já foi informado
       if ( solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA() != null ){
           
           // Pesquisamos qual o tipo de solicitacao desta especificação
           filtro.limparCamposOrderBy();
           filtro.limparListaParametros();
           filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.ID, solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getSolicitacaoTipo().getId() ) );
           colSolTip = fachada.pesquisar( filtro, SolicitacaoTipo.class.getName() );
           
           SolicitacaoTipo solicitacaoTipo = ( SolicitacaoTipo ) Util.retonarObjetoDeColecao( colSolTip );                                
           atualizarAdicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( solicitacaoTipo.getId()+"" );
       }
       
       Collection<SolicitacaoTipoEspecificacao> colSolTipEsp = new ArrayList();
       
       if ( !atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao().equals( "" ) ){
           FiltroSolicitacaoTipoEspecificacao filtro2 = new FiltroSolicitacaoTipoEspecificacao();
           filtro2.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao() ) );
           filtro2.setCampoOrderBy( "descricao" );
           colSolTipEsp = fachada.pesquisar( filtro2, SolicitacaoTipoEspecificacao.class.getName() );
       }
       
       sessao.setAttribute( "colecaoEspecificacao", colSolTipEsp );        
    }
}
