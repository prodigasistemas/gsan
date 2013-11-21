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
package gcom.gui.atendimentopublico.ordemservico;


import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeIdOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadesOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de exibir consultar OS Popup
 * 
 * @author Leonardo Regis
 * @created 14/08/2006
 */
public class ExibirConsultarDadosOrdemServicoPopupAction extends GcomAction {
	/**
	 * Execute do Consultar OS Popup
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarDadosOrdemServicoPopup");
		Fachada fachada = Fachada.getInstancia();
		
		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ConsultarDadosOrdemServicoPopupActionForm consultarDadosOrdemServicoPopupActionForm = (ConsultarDadosOrdemServicoPopupActionForm) actionForm;
		OrdemServico ordemServico = pesquisarOrdemServico(new Integer(consultarDadosOrdemServicoPopupActionForm.getNumeroOS()));
		consultarDadosOrdemServicoPopupActionForm.resetarConsultarDadosOSPopup();
		
		if(httpServletRequest.getParameter("voltar")!= null && !httpServletRequest.getParameter("voltar").equals("")){
			httpServletRequest.setAttribute("voltar","s");
		}
		
		//Dados Gerais da OS
		consultarDadosOrdemServicoPopupActionForm.setNumeroOS(ordemServico.getId()+"");
		consultarDadosOrdemServicoPopupActionForm.setSituacaoOSId(ordemServico.getSituacao()+"");
		//Caso de Uso [UC0454]
		ObterDescricaoSituacaoOSHelper situacaoOS =	fachada.obterDescricaoSituacaoOS(ordemServico.getId());
		consultarDadosOrdemServicoPopupActionForm.setSituacaoOS(situacaoOS.getDescricaoSituacao());		
		if (ordemServico.getRegistroAtendimento() != null) {
			consultarDadosOrdemServicoPopupActionForm.setNumeroRA(ordemServico.getRegistroAtendimento().getId()+"");
			//Caso de Uso [UC0420]
			ObterDescricaoSituacaoRAHelper situacaoRA = fachada.obterDescricaoSituacaoRA(ordemServico.getRegistroAtendimento().getId());
			consultarDadosOrdemServicoPopupActionForm.setSituacaoRA(situacaoRA.getDescricaoSituacao());		
		}
		if (ordemServico.getCobrancaDocumento() != null) {
			consultarDadosOrdemServicoPopupActionForm.setNumeroDocumentoCobranca(ordemServico.getCobrancaDocumento().getId()+"");			
		}
		consultarDadosOrdemServicoPopupActionForm.setDataGeracao(Util.formatarData(ordemServico.getDataGeracao()));
		consultarDadosOrdemServicoPopupActionForm.setTipoServicoId(ordemServico.getServicoTipo().getId()+"");
		consultarDadosOrdemServicoPopupActionForm.setTipoServicoDescricao(ordemServico.getServicoTipo().getDescricao());
		if (ordemServico.getOsReferencia() != null) {
			consultarDadosOrdemServicoPopupActionForm.setNumeroOSReferencia(ordemServico.getOsReferencia().getId()+"");
		}
		if (ordemServico.getServicoTipoReferencia() != null) {
			consultarDadosOrdemServicoPopupActionForm.setTipoServicoReferenciaId(ordemServico.getServicoTipoReferencia().getId()+"");
			consultarDadosOrdemServicoPopupActionForm.setTipoServicoReferenciaDescricao(ordemServico.getServicoTipoReferencia().getDescricao());
		}
        if (ordemServico.getServicoTipo().getServicoTipoReferencia() != null) {
            consultarDadosOrdemServicoPopupActionForm.setIndicadorDiagnostico(ordemServico.getServicoTipo().getServicoTipoReferencia().getIndicadorDiagnostico()+"");
        }
        
		if (ordemServico.getOsReferidaRetornoTipo() != null) {
			consultarDadosOrdemServicoPopupActionForm.setRetornoOSReferida(ordemServico.getOsReferidaRetornoTipo().getDescricao());
		}
		consultarDadosOrdemServicoPopupActionForm.setObservacao(ordemServico.getObservacao());

		String valorServicoOriginal = "";
		if(ordemServico.getValorOriginal() != null){
			valorServicoOriginal = ordemServico.getValorOriginal()+"";			
		}
		consultarDadosOrdemServicoPopupActionForm.setValorServicoOriginal(valorServicoOriginal.replace(".",","));
		
		String valorServicoAtual = "";
		if(ordemServico.getValorAtual() != null){
			valorServicoAtual = ordemServico.getValorAtual()+"";	
		}
		
		consultarDadosOrdemServicoPopupActionForm.setValorServicoAtual(valorServicoAtual.replace(".",","));
		consultarDadosOrdemServicoPopupActionForm.setPrioridadeOriginal(ordemServico.getServicoTipoPrioridadeOriginal().getDescricao());
		consultarDadosOrdemServicoPopupActionForm.setPrioridadeAtual(ordemServico.getServicoTipoPrioridadeAtual().getDescricao()+"");
		OrdemServicoUnidade ordemServicoUnidade = consultarOrdemServicoUnidade(ordemServico.getId(), AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		if (ordemServicoUnidade != null) {
			consultarDadosOrdemServicoPopupActionForm.setUnidadeGeracaoId(ordemServicoUnidade.getUnidadeOrganizacional().getId()+"");
			consultarDadosOrdemServicoPopupActionForm.setUnidadeGeracaoDescricao(ordemServicoUnidade.getUnidadeOrganizacional().getDescricao());
			consultarDadosOrdemServicoPopupActionForm.setUsuarioGeracaoId(ordemServicoUnidade.getUsuario().getId()+"");
			consultarDadosOrdemServicoPopupActionForm.setUsuarioGeracaoNome(ordemServicoUnidade.getUsuario().getNomeUsuario());
		}
		if (ordemServico.getDataEmissao() != null) {
			consultarDadosOrdemServicoPopupActionForm.setDataUltimaEmissao(Util.formatarData(ordemServico.getDataEmissao()));
		}
		//Pesquisar dados da programação
		OrdemServicoProgramacao ordemServicoProgramacao = fachada.pesquisarDataEquipeOSProgramacao(ordemServico.getId());
		if(ordemServicoProgramacao != null && !ordemServicoProgramacao.equals("")){
			httpServletRequest.setAttribute("achouDadosProgramacao", "ok");
			if(ordemServicoProgramacao.getProgramacaoRoteiro().getDataRoteiro() != null){
				consultarDadosOrdemServicoPopupActionForm.setDataProgramacao(Util.formatarData(ordemServicoProgramacao.getProgramacaoRoteiro().getDataRoteiro()));	
			}else{
				consultarDadosOrdemServicoPopupActionForm.setDataProgramacao("");	
			}
			if(ordemServicoProgramacao.getEquipe().getNome() != null){
				consultarDadosOrdemServicoPopupActionForm.setEquipeProgramacao(ordemServicoProgramacao.getEquipe().getNome());	
			}else{
				consultarDadosOrdemServicoPopupActionForm.setEquipeProgramacao("");
			}
		}else{
			consultarDadosOrdemServicoPopupActionForm.setDataProgramacao("");
			consultarDadosOrdemServicoPopupActionForm.setEquipeProgramacao("");
		}
		//Pesquisar dados do local de ocorrência
		if (ordemServico.getRegistroAtendimento() != null) {
		httpServletRequest.setAttribute("achouDadosLocalOcorrencia", "ok");
		  String enderecoOcorrencia = fachada.obterEnderecoOcorrenciaRA(ordemServico.getRegistroAtendimento().getId());		
		  consultarDadosOrdemServicoPopupActionForm.setEnderecoOcorrencia(enderecoOcorrencia);
		}else if(ordemServico.getCobrancaDocumento() != null){
		  if(ordemServico.getCobrancaDocumento().getImovel() != null){
			  httpServletRequest.setAttribute("achouDadosLocalOcorrencia", "ok");
			  String enderecoOcorrencia = fachada.pesquisarEndereco(ordemServico.getCobrancaDocumento().getImovel().getId());		
			  consultarDadosOrdemServicoPopupActionForm.setEnderecoOcorrencia(enderecoOcorrencia);
		  }
		}else{
			consultarDadosOrdemServicoPopupActionForm.setEnderecoOcorrencia("");	
		}
		Imovel imovel = ordemServico.getImovel();
		if(imovel != null){		
			httpServletRequest.setAttribute("achouDadosLocalOcorrencia", "ok");
			consultarDadosOrdemServicoPopupActionForm.setMatriculaImovel(""+imovel.getId());
			consultarDadosOrdemServicoPopupActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
			consultarDadosOrdemServicoPopupActionForm.setRota(ordemServico.getImovel().getQuadra().getRota().getCodigo().toString());
			
			if (ordemServico.getImovel().getNumeroSequencialRota() != null) {
				consultarDadosOrdemServicoPopupActionForm.setSequencialRota(ordemServico.getImovel().getNumeroSequencialRota().toString());
			}else{
				consultarDadosOrdemServicoPopupActionForm.setSequencialRota("");
			}
		}else{
			consultarDadosOrdemServicoPopupActionForm.setMatriculaImovel("");
			consultarDadosOrdemServicoPopupActionForm.setInscricaoImovel("");
			consultarDadosOrdemServicoPopupActionForm.setRota("");	
			consultarDadosOrdemServicoPopupActionForm.setSequencialRota("");
		}
		
		// Dados de Execução de OS
		if (new Short(ordemServico.getSituacao()).intValue() == OrdemServico.SITUACAO_ENCERRADO.intValue()) {
			consultarDadosOrdemServicoPopupActionForm.setDataEncerramento(Util.formatarDataComHora(ordemServico.getDataEncerramento()));
			if (ordemServico.getDescricaoParecerEncerramento() != null && 
					!ordemServico.equals("")) {
				consultarDadosOrdemServicoPopupActionForm.setParecerEncerramento(ordemServico.getDescricaoParecerEncerramento());
			}
			if (ordemServico.getAreaPavimento() != null) {
				String areaPavimentacao = ordemServico.getAreaPavimento()+"";
				consultarDadosOrdemServicoPopupActionForm.setAreaPavimentacao(areaPavimentacao.replace(".",","));
			}
			if (new Short(ordemServico.getIndicadorComercialAtualizado()).intValue() == 1) {
				consultarDadosOrdemServicoPopupActionForm.setComercialAtualizado("SIM");
			} else {
				consultarDadosOrdemServicoPopupActionForm.setComercialAtualizado("NÃO");
			}
			if (ordemServico.getPercentualCobranca() != null) {
				String percentualCobrado = ordemServico.getPercentualCobranca()+"";
				consultarDadosOrdemServicoPopupActionForm.setPercentualCobranca(percentualCobrado.replace(".",","));
			} else {
				consultarDadosOrdemServicoPopupActionForm.setPercentualCobranca("0,00");
			}
			if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
				consultarDadosOrdemServicoPopupActionForm.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getDescricao());
				consultarDadosOrdemServicoPopupActionForm.setServicoCobrado("NÃO");
			} else {
				consultarDadosOrdemServicoPopupActionForm.setMotivoNaoCobranca(null);
				if (ordemServico.getValorAtual() != null && 
						ordemServico.getPercentualCobranca() != null){
					BigDecimal valorAtual = new BigDecimal(Util.converterObjetoParaString(ordemServico.getValorAtual()));
					BigDecimal percentual = new BigDecimal(Util.converterObjetoParaString(ordemServico.getPercentualCobranca()));					
					BigDecimal valorCobrado = valorAtual.multiply(percentual).divide(new BigDecimal("100"),  2,
							BigDecimal.ROUND_HALF_UP);										
					consultarDadosOrdemServicoPopupActionForm.setValorCobrado(Util.formatarMoedaReal(valorCobrado)+"");
				} else {
					consultarDadosOrdemServicoPopupActionForm.setValorCobrado("0,00");
				}
				consultarDadosOrdemServicoPopupActionForm.setServicoCobrado("SIM");
			}
			OrdemServicoUnidade ordemServicoUnidadeEncerramento = consultarOrdemServicoUnidade(ordemServico.getId(), AtendimentoRelacaoTipo.ENCERRAR);
			if (ordemServicoUnidadeEncerramento != null) {
				consultarDadosOrdemServicoPopupActionForm.setUnidadeEncerramentoId(ordemServicoUnidadeEncerramento.getUnidadeOrganizacional().getId()+"");
				consultarDadosOrdemServicoPopupActionForm.setUnidadeEncerramentoDescricao(ordemServicoUnidadeEncerramento.getUnidadeOrganizacional().getDescricao());
				consultarDadosOrdemServicoPopupActionForm.setUsuarioEncerramentoId(ordemServicoUnidadeEncerramento.getUsuario().getId()+"");
				consultarDadosOrdemServicoPopupActionForm.setUsuarioEncerramentoNome(ordemServicoUnidadeEncerramento.getUsuario().getNomeUsuario());
			}
		}
		
		Collection<ObterDadosAtividadesOSHelper> colecaoObterDadosAtividadesOSHelper = fachada.obterDadosAtividadesOS(ordemServico.getId());
		Collection<ObterDadosAtividadeIdOSHelper> colecaoAtividade = new ArrayList();
		ObterDadosAtividadeIdOSHelper obterAtividadeIdHelper = null;
		if (colecaoObterDadosAtividadesOSHelper != null && !colecaoObterDadosAtividadesOSHelper.isEmpty()) {
			for (ObterDadosAtividadesOSHelper dadosAtividade : colecaoObterDadosAtividadesOSHelper) {
				obterAtividadeIdHelper = new ObterDadosAtividadeIdOSHelper();
				obterAtividadeIdHelper.setIdOS(ordemServico.getId());
				if (dadosAtividade.isMaterial()) {
					if (!atividadePossuiMaterial(colecaoAtividade, dadosAtividade)) {
						obterAtividadeIdHelper.setMaterial(true);
						obterAtividadeIdHelper.setAtividade(dadosAtividade.getAtividade());
						colecaoAtividade.add(obterAtividadeIdHelper);
					}
				} else {
					if (!atividadePossuiMaterial(colecaoAtividade, dadosAtividade)) {
						obterAtividadeIdHelper.setPeriodo(true);
						obterAtividadeIdHelper.setAtividade(dadosAtividade.getAtividade());
						colecaoAtividade.add(obterAtividadeIdHelper);
					}
				}
			}
			consultarDadosOrdemServicoPopupActionForm.setColecaoOSAtividade(colecaoAtividade);
		}
		
		
		// Definindo a tela de retorno que será chamada no botão Voltar - Raphael Rossiter em 13/02/2007
		String caminhoTelaPesquisaRetorno = httpServletRequest.getParameter("caminhoTelaPesquisaRetorno");
		
		if (caminhoTelaPesquisaRetorno != null && !caminhoTelaPesquisaRetorno.equals("")){
			
			httpServletRequest.setAttribute("caminhoTelaPesquisaRetorno", caminhoTelaPesquisaRetorno);
		}
		
		//Colocado por Hugo Amorim em 13/10/2009
		if(ordemServico.getProjeto()!=null){
			FiltroProjeto filtroProjeto = new FiltroProjeto();
			
			filtroProjeto.adicionarParametro(new ParametroSimples(FiltroProjeto.ID,ordemServico.getProjeto().getId()));
			
			Collection projetos = fachada.pesquisar(filtroProjeto,Projeto.class.getName());
			
			Projeto projeto = (Projeto) Util.retonarObjetoDeColecao(projetos);
			
			if(projeto!=null){
				consultarDadosOrdemServicoPopupActionForm.setNomeProjeto(projeto.getNome());
				httpServletRequest.setAttribute("nomeProjeto",true);
			}	
		}
		
		/**
		 * @author Arthur Carvalho
		 * @date 13/04/2010
		 * Caso existam dados de pavimentação inseri no form para exibição dos valores
		 */
		casoExistaDadosPavimentacaoInseriNoForm(consultarDadosOrdemServicoPopupActionForm, 
			ordemServico, 
			sessao);
		
        sessao.getAttribute("botaoEncerraOs");
        
        
        Collection colecaoFiscalizacaoSituacao = fachada.pesquisaOrdemServicoFiscSit(ordemServico.getId());
        httpServletRequest.setAttribute("colecaoFiscalizacaoSituacao",colecaoFiscalizacaoSituacao);
        
        
        if(httpServletRequest.getParameter("botaoEncerraOs") != null && 
        		httpServletRequest.getParameter("botaoEncerraOs").equals("NAO")){
        	httpServletRequest.setAttribute("botaoEncerraOs","N");
        }else{
        	httpServletRequest.setAttribute("botaoEncerraOs","S");
        }
        
		return retorno;
	}

	private boolean atividadePossuiMaterial(Collection<ObterDadosAtividadeIdOSHelper> colecaoAtividade, ObterDadosAtividadesOSHelper dadosAtividade){
		boolean retorno = false;
		for (ObterDadosAtividadeIdOSHelper helper : colecaoAtividade) {
			if(helper.getAtividade().getId().intValue() == dadosAtividade.getAtividade().getId().intValue()) {
				if (!dadosAtividade.isMaterial()) {
					helper.setPeriodo(true);
				}
				retorno = true;
				break;
			}
		}
		return retorno;
	}
	
	/**
	 * Consulta a ordem de serviço pelo id informado
	 * 
	 * @author Leonardo Regis
	 * @created 14/08/2006
	 */
	private OrdemServico pesquisarOrdemServico(Integer id){
		Fachada fachada = Fachada.getInstancia();
		OrdemServico retorno = fachada.consultarDadosOrdemServico(id);
		if (retorno == null) {
			throw new ActionServletException("atencao.naocadastrado",null, "Ordem de Serviço");
		}
		return retorno;
	}

	/**
	 * Consulta a Ordem Serviço Unidade pelo id do OS e Tipo (1=ABRIR/REGISTRAR e 3-ENCERRAR)
	 * 
	 * @author Leonardo Regis
	 * @date 15/08/2006
	 */
	private OrdemServicoUnidade consultarOrdemServicoUnidade(Integer idOS, Integer idAtendimentoTipo){

		OrdemServicoUnidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoOrdemServicoUnidade = null; 

		FiltroOrdemServicoUnidade filtroOrdemServicoUnidade = new FiltroOrdemServicoUnidade();
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(FiltroOrdemServicoUnidade.ORDEM_SERVICO_ID, idOS));
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(FiltroOrdemServicoUnidade.ATENDIMENTO_RELACAO_TIPO_ID, idAtendimentoTipo));
		
		filtroOrdemServicoUnidade.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroOrdemServicoUnidade.adicionarCaminhoParaCarregamentoEntidade("usuario");
		
		colecaoOrdemServicoUnidade = fachada.pesquisar(filtroOrdemServicoUnidade, OrdemServicoUnidade.class.getName());
		if (colecaoOrdemServicoUnidade != null && !colecaoOrdemServicoUnidade.isEmpty()) {
			retorno = (OrdemServicoUnidade) Util.retonarObjetoDeColecao(colecaoOrdemServicoUnidade);
		} 
		return retorno;
	}
	
	private void casoExistaDadosPavimentacaoInseriNoForm( 
			ConsultarDadosOrdemServicoPopupActionForm form ,
			OrdemServico ordemServico , 
			HttpSession sessao) {
		
		
		FiltroOrdemServicoPavimento filtro = new FiltroOrdemServicoPavimento();
		filtro.adicionarParametro(
			new ParametroSimples(FiltroOrdemServicoPavimento.ORDEM_SERVICO_ID, 
			ordemServico.getId()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.PAVIMENTO_RUA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.PAVIMENTO_CALCADA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.PAVIMENTO_RUA_RETORNO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.PAVIMENTO_CALCADA_RETORNO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.UNIDADE_REPAVIMENTADORA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.MOTIVO_REJEICAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.USUARIO_ACEITE);
		
		
		Collection colecaoOrdemServicoPavimento = 
			this.getFachada().pesquisar(filtro, OrdemServicoPavimento.class.getName());
		
		if( colecaoOrdemServicoPavimento != null && !colecaoOrdemServicoPavimento.isEmpty() ) {
			
			OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) 
				Util.retonarObjetoDeColecao(colecaoOrdemServicoPavimento);
		
			if(ordemServicoPavimento.getUnidadeRepavimentadora() != null){
				form.setIdUnidadeRepavimentadora(ordemServicoPavimento.getUnidadeRepavimentadora().getId().toString());
				form.setDescricaoUnidadeRepavimentadora(ordemServicoPavimento.getUnidadeRepavimentadora().getDescricao());
			}

			if(ordemServicoPavimento.getPavimentoRua() != null){
				form.setTipoPavimentoRua(ordemServicoPavimento.getPavimentoRua().getDescricao());
			}

			if(ordemServicoPavimento.getAreaPavimentoRua() != null){
				form.setAreaPavimentoRua(Util.formataBigDecimal(ordemServicoPavimento.getAreaPavimentoRua(),2,true));
			}

			if(ordemServicoPavimento.getPavimentoRuaRetorno() != null){
				form.setTipoPavimentoRuaRetorno(ordemServicoPavimento.getPavimentoRuaRetorno().getDescricao());
			}
			
			if(ordemServicoPavimento.getAreaPavimentoRuaRetorno() != null){
				form.setAreaPavimentoRuaRetorno(Util.formataBigDecimal(ordemServicoPavimento.getAreaPavimentoRuaRetorno(),2,true));
			}
			
			if(ordemServicoPavimento.getPavimentoCalcada() != null){
				form.setTipoPavimentoCalcada(ordemServicoPavimento.getPavimentoCalcada().getDescricao());
			}
			
			if(ordemServicoPavimento.getAreaPavimentoCalcada() != null){
				form.setAreaPavimentoCalcada(Util.formataBigDecimal(ordemServicoPavimento.getAreaPavimentoCalcada(),2,true));
			}
			
			if(ordemServicoPavimento.getPavimentoCalcadaRetorno() != null){
				form.setTipoPavimentoCalcadaRetorno(ordemServicoPavimento.getPavimentoCalcadaRetorno().getDescricao());
			}

			if(ordemServicoPavimento.getAreaPavimentoCalcadaRetorno() != null){
				form.setAreaPavimentoCalcadaRetorno(Util.formataBigDecimal(ordemServicoPavimento.getAreaPavimentoCalcadaRetorno(),2,true));
			}
			
			if(ordemServicoPavimento.getDataExecucao() != null){
				form.setDataRetornoRepavimentacao(Util.formatarData(ordemServicoPavimento.getDataExecucao()));
			}

			if(ordemServicoPavimento.getObservacao() != null){
				form.setObservacaoRetornoRepavimentacao(ordemServicoPavimento.getObservacao());
			}
			
			if(ordemServicoPavimento.getDataRejeicao() != null){
				form.setDataRejeicaoRepavimentacao(Util.formatarData(ordemServicoPavimento.getDataRejeicao()));
			}
			
			if(ordemServicoPavimento.getMotivoRejeicao() != null){
				form.setMotivoRejeicaoRepavimentacao(ordemServicoPavimento.getMotivoRejeicao().getDescricao());
			}
			
			if(ordemServicoPavimento.getDescricaoRejeicao() != null){
				form.setDescricaoRejeicaoRepavimentacao(ordemServicoPavimento.getDescricaoRejeicao());
			}
			
			if(ordemServicoPavimento.getIndicadorAceite() != null){
				if(ordemServicoPavimento.getIndicadorAceite().equals(ConstantesSistema.SIM)){
					form.setSituacaoAceiteRepavimentacao("Aceita");
				}else{
					form.setSituacaoAceiteRepavimentacao("Não Aceita");
				}
			}else{
				form.setSituacaoAceiteRepavimentacao("Sem Aceite");
			}
			
			if(ordemServicoPavimento.getDataAceite() != null){
				form.setDataAceiteRepavimentacao(Util.formatarData(ordemServicoPavimento.getDataAceite()));
			}
			
			if(ordemServicoPavimento.getUsuarioAceite() != null){
				form.setUsuarioAceiteId(ordemServicoPavimento.getUsuarioAceite().getLogin());
				form.setUsuarioAceiteNome(ordemServicoPavimento.getUsuarioAceite().getNomeUsuario());
			}
			
			if(ordemServicoPavimento.getDescricaoMotivoAceite() != null){
				form.setDescricaoMotivoAceite(ordemServicoPavimento.getDescricaoMotivoAceite());
			}			
			
			sessao.setAttribute("ordemServicoPavimento", ordemServicoPavimento);
		}
		
	}
}