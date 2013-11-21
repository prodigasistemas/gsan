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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroLocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidadePK;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.integracao.GisHelper;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da Atualização de um R.A (Aba nº 02 - Dados do
 * local de ocorrência)
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class ExibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction
		extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = 
			actionMapping.findForward("atualizarRegistroAtendimentoDadosLocalOcorrencia");

		
		HttpSession sessao = httpServletRequest.getSession(false);
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;
		
        /*
		 * GIS
		 * ==============================================================================================================	
		 */
		sessao.setAttribute("gis",true);		
		
		GisHelper gisHelper = (GisHelper) sessao.getAttribute("gisHelper");	
		
		if(gisHelper!= null){					
		   carregarDadosGis(gisHelper,atualizarRegistroAtendimentoActionForm,sessao);
		}		
		
		/*
		 * Carregamento inicial da tela responsável pelo recebimento das
		 * informações referentes ao local da ocorrência (ABA Nº 02)
		 * ============================================================================================================
		 */

		List<RegistroAtendimentoConta> colecaoRAContasAtualizar = new ArrayList();
		List<RegistroAtendimentoConta> colecaoRAContasRemover = new ArrayList();
		
		if (sessao.getAttribute("colecaoRAContasAtualizar") != null && 
			!sessao.getAttribute("colecaoRAContasAtualizar").equals("") && 
			(httpServletRequest.getParameter("destino") == null || !httpServletRequest.getParameter("destino").equals("2"))){
			
			colecaoRAContasAtualizar = (List<RegistroAtendimentoConta>) sessao.getAttribute("colecaoRAContasAtualizar");
		} else {
			
			// Dados das Contas relacionados
			// Mariana Victor em 28/01/2011
			FiltroRegistroAtendimentoConta filtroRegistroAtendimentoConta = new FiltroRegistroAtendimentoConta();
			
			filtroRegistroAtendimentoConta.adicionarParametro(
				new ParametroSimples(
					FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO_ID,
					atualizarRegistroAtendimentoActionForm.getNumeroRA()));

			filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(
				FiltroRegistroAtendimentoConta.CONTA);
			filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(
				FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO);
			
			colecaoRAContasAtualizar = 
				(List<RegistroAtendimentoConta>) this.getFachada().pesquisar(
					filtroRegistroAtendimentoConta, RegistroAtendimentoConta.class.getName());
			
			if (colecaoRAContasAtualizar != null && !colecaoRAContasAtualizar.isEmpty()) {
				sessao.setAttribute("colecaoRAContasAtualizar", colecaoRAContasAtualizar);
			} else {
				sessao.removeAttribute("colecaoRAContasAtualizar");
			}
		}

		if (sessao.getAttribute("colecaoRAContasRemover") != null && 
			!sessao.getAttribute("colecaoRAContasRemover").equals("")){
			
			colecaoRAContasRemover = (List<RegistroAtendimentoConta>) sessao.getAttribute("colecaoRAContasRemover");
		}


		
		/*
		 * Adicionar endereço
		 */
		String adicionarEndereco = httpServletRequest.getParameter("tipoPesquisaEndereco");

		if (adicionarEndereco != null && !adicionarEndereco.trim().equalsIgnoreCase("")) {
			retorno = actionMapping.findForward("informarEndereco");
		} else {

			/*
			 * Divisão de Esgoto - Carregando a coleção que irá ficar disponível
			 * para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			Collection colecaoDivisaoEsgoto = (Collection) sessao.getAttribute("colecaoDivisaoEsgoto");

			if (colecaoDivisaoEsgoto == null) {

				FiltroDivisaoEsgoto filtroDivisaoEsgoto = 
					new FiltroDivisaoEsgoto(FiltroDivisaoEsgoto.DESCRICAO);

				filtroDivisaoEsgoto.adicionarParametro(
					new ParametroSimples(
						FiltroDivisaoEsgoto.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroDivisaoEsgoto.setConsultaSemLimites(true);

				colecaoDivisaoEsgoto = 
					this.getFachada().pesquisar(
						filtroDivisaoEsgoto,
						DivisaoEsgoto.class.getName());

				if (colecaoDivisaoEsgoto == null || 
					colecaoDivisaoEsgoto.isEmpty()) {
					
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"DIVISAO_ESGOTO");
				} else {
					sessao.setAttribute("colecaoDivisaoEsgoto",colecaoDivisaoEsgoto);
				}
			}

			/*
			 * Local de Ocorrência - Carregando a coleção que irá ficar
			 * disponível para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			Collection colecaoLocalOcorrencia = 
				(Collection) sessao.getAttribute("colecaoLocalOcorrencia");

			if (colecaoLocalOcorrencia == null) {

				FiltroLocalOcorrencia filtroLocalOcorrencia = 
					new FiltroLocalOcorrencia(
						FiltroLocalOcorrencia.DESCRICAO);

				filtroLocalOcorrencia.adicionarParametro(
					new ParametroSimples(
						FiltroLocalOcorrencia.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroLocalOcorrencia.setConsultaSemLimites(true);

				colecaoLocalOcorrencia = 
					this.getFachada().pesquisar(
						filtroLocalOcorrencia, LocalOcorrencia.class.getName());

				if (colecaoLocalOcorrencia == null || colecaoLocalOcorrencia.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", 
						null,
						"LOCAL_OCORRENCIA");
				} else {
					sessao.setAttribute("colecaoLocalOcorrencia",colecaoLocalOcorrencia);
				}
			}

			/*
			 * Pavimento Rua - Carregando a coleção que irá ficar disponível
			 * para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			Collection colecaoPavimentoRua = 
				(Collection) sessao.getAttribute("colecaoPavimentoRua");

			if (colecaoPavimentoRua == null) {

				FiltroPavimentoRua filtroPavimentoRua = 
					new FiltroPavimentoRua(FiltroPavimentoRua.DESCRICAO);

				filtroPavimentoRua.adicionarParametro(
					new ParametroSimples(FiltroPavimentoRua.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroPavimentoRua.setConsultaSemLimites(true);

				colecaoPavimentoRua = 
					this.getFachada().pesquisar(filtroPavimentoRua,
						PavimentoRua.class.getName());

				if (colecaoPavimentoRua == null || colecaoPavimentoRua.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"PAVIMENTO_RUA");
				} else {
					sessao.setAttribute("colecaoPavimentoRua",colecaoPavimentoRua);
				}
			}

			/*
			 * Pavimento Calçada - Carregando a coleção que irá ficar disponível
			 * para escolha do usuário
			 * 
			 * [FS0003] - Verificar existência de dados
			 */
			Collection colecaoPavimentoCalcada = (Collection) sessao.getAttribute("colecaoPavimentoCalcada");

			if (colecaoPavimentoCalcada == null) {

				FiltroPavimentoCalcada filtroPavimentoCalcada = 
					new FiltroPavimentoCalcada(
						FiltroPavimentoCalcada.DESCRICAO);

				filtroPavimentoCalcada.adicionarParametro(
					new ParametroSimples(
						FiltroPavimentoCalcada.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroPavimentoCalcada.setConsultaSemLimites(true);

				colecaoPavimentoCalcada = 
					this.getFachada().pesquisar(
						filtroPavimentoCalcada, 
						PavimentoCalcada.class.getName());

				if (colecaoPavimentoCalcada == null || colecaoPavimentoCalcada.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"PAVIMENTO_CALCADA");
				} else {
					sessao.setAttribute("colecaoPavimentoCalcada",colecaoPavimentoCalcada);
				}
			}

			// [SB0002] - Habilita/Desabilita Município, Bairro, Área do Bairro
			// e
			// Divisão de Esgoto
			ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto = 
				this.getFachada().habilitarGeograficoDivisaoEsgoto(
					new Integer(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao()));

			if (habilitaGeograficoDivisaoEsgoto != null) {
				if (habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoFaltaAgua()) {
					
					sessao.setAttribute("solicitacaoTipoRelativoFaltaAgua","SIM");
					
					//Verificar carregamento do Município e Bairro de acordo com o tipo de solicitação
					if (atualizarRegistroAtendimentoActionForm.getIdImovel() != null
						&& !atualizarRegistroAtendimentoActionForm.getIdImovel().equalsIgnoreCase("")){
						
						ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = 
							this.getFachada().obterDadosIdentificacaoLocalOcorrencia(
								new Integer(atualizarRegistroAtendimentoActionForm.getIdImovel()), 
								new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()), 
								new Integer(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao()), 
								false);
						
						this.carregarMunicipioBairroParaImovel(
							habilitaGeograficoDivisaoEsgoto,
							dadosIdentificacaoLocalOcorrencia,
							atualizarRegistroAtendimentoActionForm, 
							sessao);
					}
				} else {
					sessao.setAttribute("solicitacaoTipoRelativoFaltaAgua","NAO");
				}

				if (habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto()) {
					sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto","SIM");
				} else {
					if (atualizarRegistroAtendimentoActionForm.getIdImovel() == null || 
						atualizarRegistroAtendimentoActionForm.getIdImovel().equals("")) {
						
						sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto", "NAO");
					} else {
						sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto", "SIM");
					}
				}
			} else {
				sessao.setAttribute("solicitacaoTipoRelativoFaltaAgua", "SIM");
				sessao.setAttribute("solicitacaoTipoRelativoAreaEsgoto", "SIM");
			}
			// [SB0030] – Habilita/Desabilita Conta
			short indicadorInformarPagamentoDuplicidade = ConstantesSistema.NAO;
			if (atualizarRegistroAtendimentoActionForm.getTipoSolicitacao() != null && 
				!atualizarRegistroAtendimentoActionForm.getTipoSolicitacao().equals("") && 
				!atualizarRegistroAtendimentoActionForm.getEspecificacao().equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
						new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao())));
				
//				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
//						FiltroSolicitacaoTipoEspecificacao.INDICADOR_INFORMAR_CONTA_RA,
//						ConstantesSistema.SIM));
				
				Collection colecao = 
					this.getFachada().pesquisar(
						filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());
				
				if (colecao != null && !colecao.isEmpty()) {

					SolicitacaoTipoEspecificacao especificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecao);
					
					indicadorInformarPagamentoDuplicidade = especificacao.getIndicadorInformarPagamentoDuplicidade();
					short indicadorInformarRaConta = especificacao.getIndicadorInformarContaRA();
					
					if(indicadorInformarPagamentoDuplicidade == ConstantesSistema.NAO.shortValue() && 
						indicadorInformarRaConta == ConstantesSistema.SIM.shortValue()){
						
						sessao.setAttribute("conta", "habilita");	
					}else{
						sessao.removeAttribute("conta");
					}
					
				} else {
					sessao.removeAttribute("conta");
				}
			}
			/*
			 * Fim do carregamento inicial
			 * ============================================================================================================
			 * ============================================================================================================
			 */

			/*
			 * [FS0013] - Verificar compatibilidade entre divisão de esgoto e
			 * localidade/setor/quadra [SB0007] - Define Unidade Destino da
			 * Divisão de Esgoto
			 */
			String verificarCompatibilidade = 
				httpServletRequest.getParameter("verificarCompatibilidade");

			if (verificarCompatibilidade != null && 
				!verificarCompatibilidade.equalsIgnoreCase("")) {

				this.verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(atualizarRegistroAtendimentoActionForm);

				httpServletRequest.setAttribute("nomeCampo", "idUnidadeDestino");
			}

			/*
			 * Removendo ColecaoBairroArea
			 */
			String removerColecaoBairroArea = 
				httpServletRequest.getParameter("removerColecaoBairroArea");

			if (removerColecaoBairroArea != null && !removerColecaoBairroArea.equalsIgnoreCase("")) {
				
				sessao.removeAttribute("colecaoBairroArea");
				httpServletRequest.setAttribute("nomeCampo", httpServletRequest.getParameter("campoFoco"));
			}

			/*
			 * Removendo endereço
			 */
			String removerEndereco = httpServletRequest.getParameter("removerEndereco");

			if (removerEndereco != null && !removerEndereco.trim().equalsIgnoreCase("")) {

				if (sessao.getAttribute("colecaoEnderecos") != null) {

					Collection enderecos = 
						(Collection) sessao.getAttribute("colecaoEnderecos");

					if (!enderecos.isEmpty()) {
						enderecos.remove(enderecos.iterator().next());
					}
				}
			}

			
			//Removendo Município Associado ao local de ocorrência
			String removerMunicipioOcorrencia = httpServletRequest.getParameter("limparMunicipioOcorrencia"); 
			if (removerMunicipioOcorrencia != null && !removerMunicipioOcorrencia.trim().equalsIgnoreCase("")) {
				sessao.removeAttribute("desabilitaMunicipioLocalidade");
			}
			/*
			 * Pesquisas realizadas a partir do ENTER
			 * ===========================================================================================================
			 */

			/*
			 * Dados da identificação do local de ocorrência
			 * 
			 * [FS0019] - Verificar endereço do imóvel
			 * 
			 */
			// caso esses parametros forem nulos então verifica os enter
			if (removerColecaoBairroArea == null && 
				verificarCompatibilidade == null && 
				removerEndereco == null) {

				String idImovel = atualizarRegistroAtendimentoActionForm.getIdImovel();

				int indValidacaoMatriculaImovel = 0;
				if (atualizarRegistroAtendimentoActionForm.getIndValidacaoMatriculaImovel() == null || 
					atualizarRegistroAtendimentoActionForm.getIndValidacaoMatriculaImovel().equals("")) {
					
					// [SB0024] - Verifica registro de Atendimento Sem
					// Identificação
					// do
					// Local da Ocorrência
					if (idImovel != null && !idImovel.equals("")) {
						
						indValidacaoMatriculaImovel = 
							this.getFachada().verificarRASemIdentificacaoLO(
								new Integer(idImovel), 
								new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()));

						atualizarRegistroAtendimentoActionForm.setIndValidacaoMatriculaImovel(""+indValidacaoMatriculaImovel);
						
						if (indValidacaoMatriculaImovel != 1) {

							ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = 
								this.getFachada().obterDadosIdentificacaoLocalOcorrenciaAtualizar(
									new Integer(atualizarRegistroAtendimentoActionForm.getIdImovel()),
									new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()),
									new Integer(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao()),
									new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()),
									false);
							
							if (dadosIdentificacaoLocalOcorrencia.isHabilitarAlteracaoEndereco()) {
								sessao.setAttribute("habilitarAlteracaoEndereco", "SIM");
							} else {
								sessao.setAttribute("habilitarAlteracaoEndereco", "NAO");
							}
							if (dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo() != null && 
								!dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo().equals("")) {
								
								sessao.setAttribute("desabilitarMunicipioBairro", "OK");
							}

						}
					}
				} else {
					indValidacaoMatriculaImovel = Integer.parseInt(atualizarRegistroAtendimentoActionForm.getIndValidacaoMatriculaImovel());
				}

				// caso seja a pesquisa do enter do imóvel ou o indicador de
				// validação de matrícula do imóvel seja 1
				if ((idImovel != null && !idImovel.equalsIgnoreCase("") ) || indValidacaoMatriculaImovel == 1) {

					/*
					 * [SB0004] - Obtém e Habilita/Desabilita Dados da
					 * Identificação do Local da Ocorrência e Dados do
					 * Solicitante
					 * 
					 * [FS0019] - Verificar endereço do imóvel [FS0020] -
					 * Verificar existência de registro de atendimento para o
					 * imóvel com a mesma especificação
					 * 
					 * [SB0020] - Verifica Situação do Imóvel e Especificação
					 * 
					 * [SB0032] - Verifica se o imóvel informado tem débito
					 * 
					 */
					ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = 
						this.getFachada().obterDadosIdentificacaoLocalOcorrenciaAtualizar(
							new Integer(atualizarRegistroAtendimentoActionForm.getIdImovel()),
							new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()),
							new Integer(atualizarRegistroAtendimentoActionForm.getTipoSolicitacao()),
							new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()), 
							false);

					if (dadosIdentificacaoLocalOcorrencia.getImovel() != null) {

						boolean msgAlert = false;

						// [SB0021] - Verifica Existência de Registro de
						// Atendimento
						// Pendente para o Imóvel
						boolean raPendente = 
							this.getFachada().verificaExistenciaRAPendenteImovel(dadosIdentificacaoLocalOcorrencia.getImovel().getId());

						if (raPendente) {
							
							httpServletRequest.setAttribute(
								"msgAlert",
								"Atenção! " 
								+ "Existe Registro de Atendimento pendente para o imóvel "
								+ dadosIdentificacaoLocalOcorrencia.getImovel().getId().toString());
							
							msgAlert = true;
						}

						// [SB0020] - Verifica Situação do Imóvel e
						// Especificação
						this.getFachada().verificarSituacaoImovelEspecificacao(
							dadosIdentificacaoLocalOcorrencia.getImovel(),
							new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()));
						
						//[SB0032] - Verifica se o imóvel informado tem débito
						this.getFachada().verificarImovelComDebitos(
							new Integer(atualizarRegistroAtendimentoActionForm.getEspecificacao()), 
							dadosIdentificacaoLocalOcorrencia.getImovel().getId());

						atualizarRegistroAtendimentoActionForm.setIdImovel(
							dadosIdentificacaoLocalOcorrencia.getImovel().getId().toString());

						atualizarRegistroAtendimentoActionForm.setInscricaoImovel(
							dadosIdentificacaoLocalOcorrencia.getImovel().getInscricaoFormatada());

						if (!dadosIdentificacaoLocalOcorrencia.isInformarEndereco()) {
							
							Collection colecaoEnderecos = new ArrayList();
							colecaoEnderecos.add(dadosIdentificacaoLocalOcorrencia.getImovel());
							sessao.setAttribute("colecaoEnderecos",colecaoEnderecos);
							sessao.setAttribute("enderecoPertenceImovel", "OK");
						
						} else if (dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo() != null) {
							
							atualizarRegistroAtendimentoActionForm.setDescricaoLocalOcorrencia(
								dadosIdentificacaoLocalOcorrencia.getEnderecoDescritivo());

							if (msgAlert) {
								httpServletRequest.setAttribute(
									"msgAlert2",
									"O Registro de Atendimento ficará bloqueado até que seja informado o logradouro para o imóvel");
							} else {
								httpServletRequest.setAttribute(
									"msgAlert",
									"O Registro de Atendimento ficará bloqueado até que seja informado o logradouro para o imóvel");
							}

							sessao.removeAttribute("colecaoEnderecos");
							sessao.removeAttribute("enderecoPertenceImovel");
						} else {
							sessao.removeAttribute("colecaoEnderecos");
							sessao.removeAttribute("enderecoPertenceImovel");
						}

						if (dadosIdentificacaoLocalOcorrencia.isHabilitarAlteracaoEndereco()) {
							sessao.setAttribute("habilitarAlteracaoEndereco","SIM");
						} else {
							sessao.setAttribute("habilitarAlteracaoEndereco","NAO");
						}

						this.carregarMunicipioBairroParaImovel(
								habilitaGeograficoDivisaoEsgoto,
								dadosIdentificacaoLocalOcorrencia,
								atualizarRegistroAtendimentoActionForm, 
								sessao);

						Municipio municipio = dadosIdentificacaoLocalOcorrencia.getImovel().getLocalidade().getMunicipio(); 
						if(municipio != null){
							atualizarRegistroAtendimentoActionForm.setDescricaoMunicipioOcorrencia(municipio.getNome());
							httpServletRequest.setAttribute("desabilitaMunicipioLocalidade", "OK");
						}
						
						atualizarRegistroAtendimentoActionForm.setIdLocalidade(
							dadosIdentificacaoLocalOcorrencia.getImovel().getLocalidade().getId().toString());

						atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade(
							dadosIdentificacaoLocalOcorrencia.getImovel().getLocalidade().getDescricao());

						atualizarRegistroAtendimentoActionForm.setIdSetorComercial(
							dadosIdentificacaoLocalOcorrencia.getImovel().getSetorComercial().getId().toString());

						atualizarRegistroAtendimentoActionForm.setCdSetorComercial(
							String.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel().getSetorComercial().getCodigo()));

						atualizarRegistroAtendimentoActionForm.setDescricaoSetorComercial(
							dadosIdentificacaoLocalOcorrencia.getImovel().getSetorComercial().getDescricao());

						atualizarRegistroAtendimentoActionForm.setIdQuadra(
							String.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel().getQuadra().getId()));

						atualizarRegistroAtendimentoActionForm.setNnQuadra(
							String.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel().getQuadra().getNumeroQuadra()));

						sessao.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");

					} else {

						atualizarRegistroAtendimentoActionForm.setIdImovel("");
						atualizarRegistroAtendimentoActionForm.setInscricaoImovel("Imóvel Inexistente");

						httpServletRequest.setAttribute("corImovel","exception");
						httpServletRequest.setAttribute("nomeCampo", "idImovel");
					}

				}

				String idMunicipio = atualizarRegistroAtendimentoActionForm.getIdMunicipio();
				String descricaoMunicipio = atualizarRegistroAtendimentoActionForm.getDescricaoMunicipio();

				if (idMunicipio != null && 
					!idMunicipio.equalsIgnoreCase("") && 
					(descricaoMunicipio == null || descricaoMunicipio.equals(""))) {

					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

					filtroMunicipio.adicionarParametro(
						new ParametroSimples(
							FiltroMunicipio.ID,
							atualizarRegistroAtendimentoActionForm.getIdMunicipio()));

					filtroMunicipio.adicionarParametro(
						new ParametroSimples(
							FiltroMunicipio.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoMunicipio = 
						this.getFachada().pesquisar(
							filtroMunicipio, 
							Municipio.class.getName());

					if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {

						atualizarRegistroAtendimentoActionForm.setIdMunicipio("");
						atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio("Município Inexistente");

						httpServletRequest.setAttribute("corMunicipio","exception");
						httpServletRequest.setAttribute("nomeCampo","idMunicipio");

					} else {
						Municipio municipio = 
							(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

						atualizarRegistroAtendimentoActionForm.setIdMunicipio(municipio.getId().toString());
						atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio(municipio.getNome());

						httpServletRequest.setAttribute("nomeCampo", "cdBairro");
					}
				}

				String codigoBairro = atualizarRegistroAtendimentoActionForm.getCdBairro();
				String descricaoBairro = atualizarRegistroAtendimentoActionForm.getDescricaoBairro();

				if (codigoBairro != null && !codigoBairro.equalsIgnoreCase("")) {

					if ((descricaoBairro == null || descricaoBairro.equals(""))) {

						FiltroBairro filtroBairro = new FiltroBairro();

						filtroBairro.adicionarParametro(
							new ParametroSimples(
								FiltroBairro.CODIGO,
								atualizarRegistroAtendimentoActionForm.getCdBairro()));

						filtroBairro.adicionarParametro(
							new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								atualizarRegistroAtendimentoActionForm.getIdMunicipio()));

						filtroBairro.adicionarParametro(
							new ParametroSimples(
								FiltroBairro.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

						Collection colecaoBairro = 
							this.getFachada().pesquisar(filtroBairro, Bairro.class.getName());

						if (colecaoBairro == null || colecaoBairro.isEmpty()) {

							atualizarRegistroAtendimentoActionForm.setCdBairro("");
							atualizarRegistroAtendimentoActionForm.setDescricaoBairro("Bairro Inexistente");

							httpServletRequest.setAttribute("corBairro","exception");
							httpServletRequest.setAttribute("nomeCampo","cdBairro");

						} else {
							Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

							atualizarRegistroAtendimentoActionForm.setCdBairro(String.valueOf(bairro.getCodigo()));
							atualizarRegistroAtendimentoActionForm.setCdBairro(String.valueOf(bairro.getId()));
							atualizarRegistroAtendimentoActionForm.setDescricaoBairro(bairro.getNome());
							
							this.pesquisarBairroArea(
								new Integer(atualizarRegistroAtendimentoActionForm.getIdBairro()), 
								sessao);

						}
					}

				}

				String pesquisarBairroArea = httpServletRequest.getParameter("pesquisarBairroArea");

				if (pesquisarBairroArea != null && 
					!pesquisarBairroArea.equalsIgnoreCase("") || 
					(atualizarRegistroAtendimentoActionForm.getIdBairro() != null && 
						!atualizarRegistroAtendimentoActionForm.getIdBairro().equals(""))) {

					this.pesquisarBairroArea(
						new Integer(atualizarRegistroAtendimentoActionForm.getIdBairro()), 
						sessao);

					httpServletRequest.setAttribute("nomeCampo", "idBairroArea");
				}

				String idLocalidade = atualizarRegistroAtendimentoActionForm.getIdLocalidade();
				String descricaoLocalidade = atualizarRegistroAtendimentoActionForm.getDescricaoBairro();

				if (idLocalidade != null && 
					!idLocalidade.equalsIgnoreCase("") && 
					(descricaoLocalidade == null || descricaoLocalidade.equals(""))) {
					
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

					filtroLocalidade.adicionarParametro(
						new ParametroSimples(
							FiltroLocalidade.ID,
							atualizarRegistroAtendimentoActionForm.getIdLocalidade()));

					filtroLocalidade.adicionarParametro(
						new ParametroSimples(
							FiltroLocalidade.INDICADORUSO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoLocalidade = 
						this.getFachada().pesquisar(
							filtroLocalidade, 
							Localidade.class.getName());

					if (colecaoLocalidade == null || 
						colecaoLocalidade.isEmpty()) {

						atualizarRegistroAtendimentoActionForm.setIdLocalidade("");
						atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade("Localidade Inexistente");

						httpServletRequest.setAttribute("corLocalidade","exception");
						httpServletRequest.setAttribute("nomeCampo","idLocalidade");

					} else {
						Localidade localidade = 
							(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

						atualizarRegistroAtendimentoActionForm.setIdLocalidade(localidade.getId().toString());
						atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade(localidade.getDescricao());

						httpServletRequest.setAttribute("nomeCampo","cdSetorComercial");

					}
				}

				String cdSetorComercial = atualizarRegistroAtendimentoActionForm.getCdSetorComercial();
				String descricaoSetorComercial = atualizarRegistroAtendimentoActionForm.getDescricaoSetorComercial();

				if (cdSetorComercial != null && 
					!cdSetorComercial.equalsIgnoreCase("") && 
					(descricaoSetorComercial == null || descricaoSetorComercial.equals(""))) {

					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

					filtroSetorComercial.adicionarParametro(
						new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE,
							atualizarRegistroAtendimentoActionForm.getIdLocalidade()));

					filtroSetorComercial.adicionarParametro(
						new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
							atualizarRegistroAtendimentoActionForm.getCdSetorComercial()));

					filtroSetorComercial.adicionarParametro(
						new ParametroSimples(
							FiltroSetorComercial.INDICADORUSO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoSetorComercial = 
						this.getFachada().pesquisar(filtroSetorComercial, 
							SetorComercial.class.getName());

					if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {

						atualizarRegistroAtendimentoActionForm.setIdSetorComercial("");
						atualizarRegistroAtendimentoActionForm.setCdSetorComercial("");
						atualizarRegistroAtendimentoActionForm.setDescricaoSetorComercial("Setor Comercial Inexistente");

						httpServletRequest.setAttribute("corSetorComercial","exception");
						httpServletRequest.setAttribute("nomeCampo","cdSetorComercial");

					} else {
						SetorComercial setorComercial = 
							(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

						atualizarRegistroAtendimentoActionForm.setIdSetorComercial(setorComercial.getId().toString());
						atualizarRegistroAtendimentoActionForm.setCdSetorComercial(String.valueOf(setorComercial.getCodigo()));
						atualizarRegistroAtendimentoActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());

						httpServletRequest.setAttribute("nomeCampo", "nnQuadra");

					}
				}

				String nnQuadra = atualizarRegistroAtendimentoActionForm.getNnQuadra();
				String pesquisarQuadra = httpServletRequest.getParameter("pesquisarQuadra");

				if (nnQuadra != null && 
					!nnQuadra.equalsIgnoreCase("") && 
					(pesquisarQuadra != null && pesquisarQuadra.equals(""))) {

					FiltroQuadra filtroQuadra = new FiltroQuadra();

					filtroQuadra.adicionarParametro(
						new ParametroSimples(
							FiltroQuadra.ID_SETORCOMERCIAL,
							atualizarRegistroAtendimentoActionForm.getIdSetorComercial()));

					filtroQuadra.adicionarParametro(
						new ParametroSimples(
							FiltroQuadra.NUMERO_QUADRA,
							atualizarRegistroAtendimentoActionForm.getNnQuadra()));

					filtroQuadra.adicionarParametro(
						new ParametroSimples(
							FiltroQuadra.INDICADORUSO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoQuadra = 
						this.getFachada().pesquisar(
							filtroQuadra,
							Quadra.class.getName());

					if (colecaoQuadra == null || colecaoQuadra.isEmpty()) {

						atualizarRegistroAtendimentoActionForm.setIdQuadra("");
						atualizarRegistroAtendimentoActionForm.setNnQuadra("");

						httpServletRequest.setAttribute("msgQuadra","QUADRA INEXISTENTE");
						httpServletRequest.setAttribute("nomeCampo", "nnQuadra");

					} else {
						Quadra quadra = 
							(Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

						atualizarRegistroAtendimentoActionForm.setIdQuadra(quadra.getId().toString());
						atualizarRegistroAtendimentoActionForm.setNnQuadra(String.valueOf(quadra.getNumeroQuadra()));

						// [SB0006] - Obtém Divisão de Esgoto
						DivisaoEsgoto divisaoEsgoto = 
							this.getFachada().obterDivisaoEsgoto(
								quadra.getId(),
								habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());

						if (divisaoEsgoto != null) {
							atualizarRegistroAtendimentoActionForm.setIdDivisaoEsgoto(divisaoEsgoto.getId().toString());

							/*
							 * [FS0013] - Verificar compatibilidade entre
							 * divisão de esgoto e localidade/setor/quadra
							 * [SB0007] - Define Unidade Destino da Divisão de
							 * Esgoto
							 */
							this.verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(atualizarRegistroAtendimentoActionForm);

						} else {
							httpServletRequest.setAttribute("nomeCampo","idDivisaoEsgoto");
						}

					}
				}
			}
			
			// Pesquisar Conta
			// Mariana Victor - 31/01/2011
			String pesquisarConta = httpServletRequest.getParameter("pesquisarConta");

			if (pesquisarConta != null && !pesquisarConta.equalsIgnoreCase("")) {

				RegistroAtendimentoConta registroAtendimentoConta = 
					this.pesquisarConta(atualizarRegistroAtendimentoActionForm, sessao, usuarioLogado);
				
				if (registroAtendimentoConta != null && registroAtendimentoConta.getConta() != null) {
					
					atualizarRegistroAtendimentoActionForm.setIdConta(registroAtendimentoConta.getConta().getFormatarAnoMesParaMesAno() + "");
					atualizarRegistroAtendimentoActionForm.setDescConta(registroAtendimentoConta.getConta().getFormatarAnoMesParaMesAno());

					httpServletRequest.setAttribute("contaEncontrada", "");
				}
				
			}
			
			//Pesquisar Localidade
			String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");

			if (pesquisarLocalidade != null	&& !pesquisarLocalidade.equalsIgnoreCase("")) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, atualizarRegistroAtendimentoActionForm.getIdLocalidade()));

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("municipio");
				
				Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade,
						Localidade.class.getName());

				if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {

					atualizarRegistroAtendimentoActionForm.setIdLocalidade("");
					atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade("Localidade Inexistente");

					httpServletRequest.setAttribute("corLocalidade", "exception");
					httpServletRequest.setAttribute("nomeCampo", "idLocalidade");

				} else {
					Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

					atualizarRegistroAtendimentoActionForm.setIdLocalidade(localidade.getId().toString());
					atualizarRegistroAtendimentoActionForm.setDescricaoLocalidade(localidade.getDescricao());

					httpServletRequest.setAttribute("nomeCampo", "cdSetorComercial");

					Municipio municipio = localidade.getMunicipio();
					if(municipio != null){
						atualizarRegistroAtendimentoActionForm.setDescricaoMunicipioOcorrencia(municipio.getNome());
						httpServletRequest.setAttribute("desabilitaMunicipioLocalidade", "OK");
					}
					httpServletRequest.setAttribute("desabilitarDescricaoLocalOcorrencia", "OK");
				}
			}
			/*
			 * Fim das pesquisas realizadas pelo ENTER
			 * ===========================================================================================================
			 * ===========================================================================================================
			 */
			
			/*
			 * ===========================================================================================================
			 * Adicionar Conta
			 * Mariana Victor - 31/01/2011
			 * ===========================================================================================================
			 */
			String adicionarConta = httpServletRequest.getParameter("adicionarConta");

			if (adicionarConta != null && 
				!adicionarConta.equalsIgnoreCase("") && 
				atualizarRegistroAtendimentoActionForm.getIdConta() != null && 
				!atualizarRegistroAtendimentoActionForm.getIdConta().equals("")) {
				
				RegistroAtendimentoConta registroAtendimentoConta = 
					this.pesquisarConta(atualizarRegistroAtendimentoActionForm, sessao, usuarioLogado);
				
				if (registroAtendimentoConta != null) {
					atualizarRegistroAtendimentoActionForm.setIdConta("");
					atualizarRegistroAtendimentoActionForm.setDescConta("");
					
					if (!this.adicionado(colecaoRAContasAtualizar, registroAtendimentoConta)) {
						colecaoRAContasAtualizar.add(registroAtendimentoConta);
						
						sessao.setAttribute("colecaoRAContasAtualizar", colecaoRAContasAtualizar);
					}
				}
			}
			
			/*
			 * ===========================================================================================================
			 * Remover Conta
			 * Mariana Victor - 31/01/2011
			 * ===========================================================================================================
			 */
			String removerConta = httpServletRequest.getParameter("removerConta");

			if (removerConta != null && !removerConta.equalsIgnoreCase("")) {
				Integer indice = new Integer(httpServletRequest.getParameter("removerConta"));
	        	
	        	if (colecaoRAContasAtualizar != null
	        			&& !colecaoRAContasAtualizar.isEmpty()
	        			&& colecaoRAContasAtualizar.size() >= indice) {
					
					if (colecaoRAContasAtualizar.get(indice-1).getUltimaAlteracao() != null){
						colecaoRAContasRemover.add(colecaoRAContasAtualizar.get(indice-1));
	        			sessao.setAttribute("colecaoRAContasRemover",colecaoRAContasRemover);
	        		}
					colecaoRAContasAtualizar.remove(indice-1);
					sessao.setAttribute("colecaoRAContasAtualizar", colecaoRAContasAtualizar);
	        	}
			}
			
			Integer idImovelPagamentoDuplicidade = (Integer) sessao.getAttribute("idImovelPagamentoDuplicidade");
			Integer idImovelFormulario = null;
			
			if(atualizarRegistroAtendimentoActionForm.getIdImovel() != null && 
				!atualizarRegistroAtendimentoActionForm.getIdImovel().equals("")){
				
				idImovelFormulario =  new Integer(atualizarRegistroAtendimentoActionForm.getIdImovel());
			}
			
			
			if(idImovelFormulario != null && !idImovelFormulario.equals(idImovelPagamentoDuplicidade) || 
				sessao.getAttribute("colecaoPagamentosDuplicidade") == null){
				
				if(idImovelPagamentoDuplicidade != null && 
				(idImovelFormulario != null && !idImovelFormulario.equals(idImovelPagamentoDuplicidade))){
					
					boolean existePagamento = 
						this.existePagamentoDuplicadoDevolvido(new Integer(atualizarRegistroAtendimentoActionForm.getNumeroRA()));
					
					if(!existePagamento){
						Collection colecaoPagamento = 
							this.pesquisaPagamentosEmDuplicidade(idImovelFormulario);
						
						if(colecaoPagamento != null && 
							!colecaoPagamento.isEmpty()){
							
							sessao.setAttribute("colecaoPagamentosDuplicidade",colecaoPagamento);
							sessao.setAttribute("idImovelPagamentoDuplicidade",idImovelFormulario);
								
						}else{
							throw new ActionServletException("nao_exite_pagamento_duplicidade");
						}
						
					}else{
						throw new ActionServletException("existe_pagamento_duplicidade_ra");
					}

				}else if (sessao.getAttribute("colecaoPagamentosDuplicidade") == null){
					
					FiltroRegistroAtendimentoPagamentoDuplicidade filtroRegistroAtendimentoPagamentoDuplicidade = 
						new FiltroRegistroAtendimentoPagamentoDuplicidade();
					
					filtroRegistroAtendimentoPagamentoDuplicidade.adicionarParametro(
						new ParametroSimples(
							FiltroRegistroAtendimentoPagamentoDuplicidade.REGISTRO_ATENDIMENTO,
							atualizarRegistroAtendimentoActionForm.getNumeroRA()));
					
					Collection colecaoRegistroAtendimentoPagamentoDuplicidade = 
						(Collection) this.getFachada().pesquisar(
							filtroRegistroAtendimentoPagamentoDuplicidade, 
							RegistroAtendimentoPagamentoDuplicidade.class.getName());
					
					if(colecaoRegistroAtendimentoPagamentoDuplicidade != null && !colecaoRegistroAtendimentoPagamentoDuplicidade.isEmpty()){
						sessao.setAttribute("idImovelPagamentoDuplicidade",idImovelFormulario);
						sessao.setAttribute("colecaoPagamentosDuplicidade",colecaoRegistroAtendimentoPagamentoDuplicidade);
					}					
					
				}
			}
			
			
			String removerPagamento = httpServletRequest.getParameter("removerPagamento");

			if (removerPagamento != null && !removerPagamento.equals("")) {
				Integer idPagamento = new Integer(httpServletRequest.getParameter("removerPagamento"));
	        	
				Collection colecaoPagamentosDuplicidade = 
					(Collection) sessao.getAttribute("colecaoPagamentosDuplicidade");
				
				Iterator itera = colecaoPagamentosDuplicidade.iterator();
				
				while (itera.hasNext()) {
					RegistroAtendimentoPagamentoDuplicidade registroAtendimentoPagamentoDuplicidade = 
						(RegistroAtendimentoPagamentoDuplicidade) itera.next();
					
					if(registroAtendimentoPagamentoDuplicidade.getComp_id().getPagamentoId().intValue() == idPagamento.intValue()){
						itera.remove();
						break;
					}
				}
			}
			
			String atualizarPagamentosDuplicidade = httpServletRequest.getParameter("atualizarPagamentosDuplicidade");
			
			if(atualizarPagamentosDuplicidade != null && !atualizarPagamentosDuplicidade.equals("")){
				
				if(idImovelFormulario != null && !idImovelFormulario.equals("")){
					
					Collection colecaoPagamentosDuplicidade = 
						this.pesquisaPagamentosEmDuplicidade(
							new Integer(idImovelFormulario));
					
					if(colecaoPagamentosDuplicidade != null && 
						!colecaoPagamentosDuplicidade.isEmpty()){
						
						sessao.setAttribute("colecaoPagamentosDuplicidade",colecaoPagamentosDuplicidade);
					}else{
						throw new ActionServletException("nao_exite_pagamento_duplicidade");
					}
				}
			}			

			/*
			 * Limpar Imóvel
			 */
			String limparImovel = httpServletRequest.getParameter("limparImovel");

			if (limparImovel != null && !limparImovel.trim().equalsIgnoreCase("")) {

				this.limparImovel(atualizarRegistroAtendimentoActionForm,sessao, httpServletRequest);
				httpServletRequest.setAttribute("nomeCampo", "idImovel");
			}
		}

		return retorno;

	}
	
	
	private boolean existePagamentoDuplicadoDevolvido(Integer idRa){
		boolean existePagamento = false;
		
		FiltroRegistroAtendimentoPagamentoDuplicidade filtroRegistroAtendimentoPagamentoDuplicidade = 
			new FiltroRegistroAtendimentoPagamentoDuplicidade();
		
		filtroRegistroAtendimentoPagamentoDuplicidade.adicionarParametro(
			new ParametroSimples(
				FiltroRegistroAtendimentoPagamentoDuplicidade.REGISTRO_ATENDIMENTO,
				idRa));

		filtroRegistroAtendimentoPagamentoDuplicidade.adicionarParametro(
			new ParametroSimples(
				FiltroRegistroAtendimentoPagamentoDuplicidade.INDICADOR_PAGAMENTO_DEVOLVIDO,
				ConstantesSistema.SIM));
		
		Collection colecaoRegistroAtendimentoPagamentoDuplicidade = 
			(Collection) this.getFachada().pesquisar(
				filtroRegistroAtendimentoPagamentoDuplicidade, 
				RegistroAtendimentoPagamentoDuplicidade.class.getName());
		
		if(colecaoRegistroAtendimentoPagamentoDuplicidade != null && !colecaoRegistroAtendimentoPagamentoDuplicidade.isEmpty()){
			existePagamento = true;
		}	
		
		return existePagamento;
	}
	
	/*
		 * Métodos auxiliares
		 * =============================================================================================================
		 */

	public void verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm) {

		this.getFachada().verificarCompatibilidadeDivisaoEsgotoLocalidadeSetorQuadra(
			Util.converterStringParaInteger(atualizarRegistroAtendimentoActionForm.getIdLocalidade()),
			Util.converterStringParaInteger(atualizarRegistroAtendimentoActionForm.getIdSetorComercial()),
			Util.converterStringParaInteger(atualizarRegistroAtendimentoActionForm.getIdQuadra()),
			Util.converterStringParaInteger(atualizarRegistroAtendimentoActionForm.getIdDivisaoEsgoto()));
	}

	public void pesquisarBairroArea(Integer idBairro,
			HttpSession sessao) {

		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

		filtroBairroArea.adicionarParametro(
			new ParametroSimples(
				FiltroBairroArea.ID_BAIRRO, 
				idBairro));

		Collection colecaoBairroArea = 
			this.getFachada().pesquisar(
				filtroBairroArea,
				BairroArea.class.getName());

		if (colecaoBairroArea == null || colecaoBairroArea.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"BAIRRO_AREA");
		} else {
			sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
		}
	}

	public void carregarMunicipioBairroParaImovel(
			ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto,
			ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrenciaHelper,
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			HttpSession sessao) {

		if (habilitaGeograficoDivisaoEsgoto != null && 
			habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoFaltaAgua() && 
			obterDadosIdentificacaoLocalOcorrenciaHelper.getEnderecoDescritivo() == null) {

			atualizarRegistroAtendimentoActionForm.setIdMunicipio(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getMunicipio().getId().toString());

			atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getMunicipio().getNome());

			atualizarRegistroAtendimentoActionForm.setIdBairro(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getId().toString());

			atualizarRegistroAtendimentoActionForm.setCdBairro(
				String.valueOf(obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getCodigo()));

			atualizarRegistroAtendimentoActionForm.setDescricaoBairro(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getNome());

			this.pesquisarBairroArea(
				obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel().getLogradouroBairro().getBairro().getId(),sessao);

			sessao.setAttribute("desabilitarMunicipioBairro", "OK");

		} else {

			atualizarRegistroAtendimentoActionForm.setIdMunicipio("");
			atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio("");
			atualizarRegistroAtendimentoActionForm.setIdBairro("");
			atualizarRegistroAtendimentoActionForm.setCdBairro("");
			atualizarRegistroAtendimentoActionForm.setDescricaoBairro("");

			sessao.removeAttribute("colecaoBairroArea");
			sessao.removeAttribute("desabilitarMunicipioBairro");
		}
	}

	private void limparImovel(AtualizarRegistroAtendimentoActionForm form,
			HttpSession sessao, HttpServletRequest httpServletRequest) {

		form.setIdImovel("");
		form.setInscricaoImovel("");
		form.setDescricaoLocalOcorrencia("");
		form.setIdMunicipio("");
		form.setDescricaoMunicipio("");
		form.setIdBairro("");
		form.setCdBairro("");
		form.setDescricaoBairro("");
		form.setIdLocalidade("");
		form.setDescricaoLocalidade("");
		form.setIdSetorComercial("");
		form.setCdSetorComercial("");
		form.setDescricaoSetorComercial("");
		form.setIdQuadra("");
		form.setNnQuadra("");
		form.setIdPavimentoRua("");
		form.setIdPavimentoCalcada("");
		form.setPontoReferencia("");
		form.setDescricaoMunicipioOcorrencia("");

		sessao.removeAttribute("colecaoEnderecos");
		sessao.removeAttribute("enderecoPertenceImovel");
		sessao.removeAttribute("habilitarAlteracaoEndereco");
		sessao.removeAttribute("colecaoBairroArea");
		sessao.removeAttribute("colecaoPagamentosDuplicidade");
		httpServletRequest.removeAttribute("desabilitaMunicipioLocalidade");
		
	}

	//=================================================================================================================
	
	
	
	public void carregarDadosGis(			
			GisHelper gisHelper,
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			HttpSession sessao) {
	
		String nnCoordenadaNorte = gisHelper.getNnCoordenadaNorte(); 
		String nnCoordenadaLeste = gisHelper.getNnCoordenadaLeste(); 			
		
		atualizarRegistroAtendimentoActionForm.setNnCoordenadaNorte(nnCoordenadaNorte);
		atualizarRegistroAtendimentoActionForm.setNnCoordenadaLeste(nnCoordenadaLeste);
		
	     sessao.removeAttribute("gisHelper");	
	
	}
	
	private RegistroAtendimentoConta pesquisarConta(AtualizarRegistroAtendimentoActionForm form, HttpSession sessao, Usuario usuarioLogado) {
		Conta conta = null;
		RegistroAtendimentoConta registroAtendimentoConta = null;
		
		String anoMes = Util.formatarMesAnoParaAnoMesSemBarra(form.getIdConta());
		
		FiltroConta filtroConta = new FiltroConta();
		if (form.getIdContaPesquisada() != null && 
			!form.getIdContaPesquisada().equals("")) {
			
			filtroConta.adicionarParametro(
				new ParametroSimples(
					FiltroConta.ID, 
					form.getIdContaPesquisada()));
		} else {
			filtroConta.adicionarParametro(
				new ParametroSimples(
					FiltroConta.REFERENCIA, 
					anoMes));
		}
		filtroConta.adicionarParametro(
			new ParametroSimples(
				FiltroConta.IMOVEL_ID, 
				form.getIdImovel()));
		
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL);
		
		Collection colecao = 
			this.getFachada().pesquisar(filtroConta, Conta.class.getName());

		// [FS0046] – Verificar existência da conta.
		if (colecao != null && !colecao.isEmpty()) {
			conta = this.retornaConta(colecao);
			
			// [FS0047] – Verificar se a conta pode ser associada.
			if (conta != null) {
				
				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();

				// Verifica se o usuário possua permissão especial
				boolean temPermissaoParaRetificarContaNorma = 
					getFachada().verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, usuarioLogado);	
				
				// [FS0048] – Verificar prazo de vencimento das contas para associação
				if (temPermissaoParaRetificarContaNorma || 
					Util.adcionarOuSubtrairMesesAData(conta.getDataVencimentoConta(), sistemaParametro.getNumeroMesesRetificarConta(), 0).compareTo(new Date()) != -1) {
					
					registroAtendimentoConta = new RegistroAtendimentoConta();
					registroAtendimentoConta.setConta(conta);
					
					form.setIdConta(conta.getReferencia() + "");
					form.setDescConta(conta.getFormatarAnoMesParaMesAno());
					sessao.setAttribute("contaEncontrada", "");
					
					return registroAtendimentoConta;
					
				} else {
					form.setIdConta("");
					form.setDescConta("Conta com prazo para associação excedido!");
					sessao.removeAttribute("contaEncontrada");
				}

			} else {
				form.setIdConta("");
				form.setDescConta("Conta não pode ser associada ao Registro de Atendimento!");
				sessao.removeAttribute("contaEncontrada");
			}
		} else {

			form.setIdConta("");
			form.setDescConta("Conta não Localizada");

			sessao.removeAttribute("contaEncontrada");
		}
		
		return null;
	}
	
	/**
	 * Caso a especificacao tenha o indicador de pagamento em duplicidade,
	 * eh feita a pesquisa dos pagamentos em duplicidade
	 * 
	 * @author Rafael Pinto
	 * @date 15/03/2011
	 * 
	 * @param Integer idImovel
	 * @return Collection colecao de pagamentos em duplicidade
	 */
	private Collection<RegistroAtendimentoPagamentoDuplicidade> pesquisaPagamentosEmDuplicidade(Integer idImovel){
		
		FiltroPagamento filtro = new FiltroPagamento(FiltroPagamento.ANO_MES_REFERENCIA_PAGAMENTO);
		
		filtro.adicionarParametro(
			new ParametroSimples(FiltroPagamento.IMOVEL,
				idImovel));
		
		filtro.adicionarParametro(
			new ParametroSimples(FiltroPagamento.PAGAMENTO_SITUACAO_ATUAL_ID,
				PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE));
		
		filtro.adicionarParametro(
			new ParametroNaoNulo(FiltroPagamento.ANO_MES_REFERENCIA_PAGAMENTO));
		
		filtro.adicionarParametro(new ParametroNulo(FiltroPagamento.GUIA_PAGAMENTO));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroPagamento.IMOVEL);
		
		Collection colecaoPagamento = 
			(Collection) this.getFachada().pesquisar(filtro,Pagamento.class.getName());
		
		Collection<RegistroAtendimentoPagamentoDuplicidade> 
			colecaoRegistroAtendimentoPagamentoDuplicidade = new ArrayList();
		if(colecaoPagamento != null && !colecaoPagamento.isEmpty()){
			Iterator itera = colecaoPagamento.iterator();
			
			while (itera.hasNext()) {
				Pagamento paga = (Pagamento) itera.next();
				
				
				RegistroAtendimentoPagamentoDuplicidade registro = new RegistroAtendimentoPagamentoDuplicidade();
				
				RegistroAtendimentoPagamentoDuplicidadePK registroAtendimentoPagamentoDuplicidadePK = 
					new RegistroAtendimentoPagamentoDuplicidadePK();
				
				registroAtendimentoPagamentoDuplicidadePK.setPagamentoId(paga.getId());
				
				registro.setComp_id(registroAtendimentoPagamentoDuplicidadePK);
				registro.setPagamento(paga);
				registro.setValorPagamento(paga.getValorPagamento());
				registro.setDataPagamento(paga.getDataPagamento());
				registro.setAnoMesReferenciaPagamento(paga.getAnoMesReferenciaPagamento());
				
				colecaoRegistroAtendimentoPagamentoDuplicidade.add(registro);
			}
			
		
		}
		
		return colecaoRegistroAtendimentoPagamentoDuplicidade;
	}
	
	private boolean adicionado(Collection<RegistroAtendimentoConta> colecaoRegistroAtendimentoConta, RegistroAtendimentoConta registroAtendimentoConta) {
		
		Iterator iterator = colecaoRegistroAtendimentoConta.iterator();
		
		while(iterator.hasNext()) {
			RegistroAtendimentoConta RAContaAdicionado = (RegistroAtendimentoConta) iterator.next();
			
			if (RAContaAdicionado.getConta().getId().equals(registroAtendimentoConta.getConta().getId())
					&& (RAContaAdicionado.getRegistroAtendimento() == null
							|| registroAtendimentoConta.getRegistroAtendimento() == null
							|| RAContaAdicionado.getRegistroAtendimento().getId().equals(registroAtendimentoConta.getRegistroAtendimento().getId()))) {
				return true;
			}
		}
		
		return false;
	}
	
	private Conta retornaConta(Collection colecao) {
		
		Iterator iterator = colecao.iterator();
		
		while(iterator.hasNext()) {
			Conta conta = (Conta) iterator.next();
			
			if (conta.getDebitoCreditoSituacaoAtual() != null
					&& (conta.getDebitoCreditoSituacaoAtual().getId().equals(ConstantesSistema.DEBITO_CREDITO_SITUACAO_NORMAL)
						|| conta.getDebitoCreditoSituacaoAtual().getId().equals(ConstantesSistema.DEBITO_CREDITO_SITUACAO_RETIFICADA)
						|| conta.getDebitoCreditoSituacaoAtual().getId().equals(ConstantesSistema.DEBITO_CREDITO_SITUACAO_INCLUIDA))) {
				return conta;
			}
		}
		
		return null;
	}
}
