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

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitanteFone;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.RaMotivoReativacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitanteFone;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterRAAssociadoHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFiltroHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
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
 * Action que define o pré-processamento da página de consultar RA
 * 
 * @author Rafael Pinto
 * @created 25/07/2006
 */
public class ExibirConsultarRegistroAtendimentoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimento");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ConsultarRegistroAtendimentoActionForm consultarRegistroAtendimentoActionForm = 
		(ConsultarRegistroAtendimentoActionForm) actionForm;
		
		
		Integer idRA = null;
		if (consultarRegistroAtendimentoActionForm.getNumeroRA() != null &&
			!consultarRegistroAtendimentoActionForm.getNumeroRA().equalsIgnoreCase("")){
				
			idRA = new Integer(consultarRegistroAtendimentoActionForm.getNumeroRA());
			
			if (httpServletRequest.getParameter("pesquisaUnitaria") != null){
				consultarRegistroAtendimentoActionForm.reset(actionMapping, httpServletRequest);
				sessao.removeAttribute("colecaoCompleta");
				sessao.setAttribute("naoHabilitarNavegacao", "OK");
			}
			else if (sessao.getAttribute("colecaoRAHelper") != null){
				sessao.removeAttribute("naoHabilitarNavegacao");
			}
			else{
				sessao.setAttribute("naoHabilitarNavegacao", "OK");
			}
		}
		else {
			idRA = (Integer) sessao.getAttribute("numeroOS");
			sessao.removeAttribute("colecaoCompleta");
			
			sessao.setAttribute("naoHabilitarNavegacao", "OK");
		}
		
		System.out.println(sessao.getAttribute("colecaoCompleta"));
		
		if (sessao.getAttribute("colecaoRAHelper") != null) {
			FiltrarRegistroAtendimentoHelper filtro = (FiltrarRegistroAtendimentoHelper) sessao.getAttribute("filtroRA");
			List<RAFiltroHelper> colecao = (List<RAFiltroHelper>) sessao.getAttribute("colecaoRAHelper");
			System.out.println("colecaoRAHelper: " + colecao.size());
			
			Integer totalRegistros = (Integer) sessao.getAttribute("totalRegistros");
			System.out.println("totalRegistros: " + totalRegistros);
			Integer numeroPaginasPesquisa = (int) Math.ceil((double) totalRegistros / 10.0);
			System.out.println("numeropaginas: " + numeroPaginasPesquisa);
			Integer page = (Integer) sessao.getAttribute("page.offset");
			System.out.println("page: " + page);
			
			boolean anterior = (httpServletRequest.getParameter("raAnterior") != null) ? true : false;
			boolean proximo = (httpServletRequest.getParameter("proximoRA") != null) ? true : false;
			
			int index = obterIndexRAColecao(idRA, colecao);
			if (index != -1) {
				
				if (anterior) index--;
				if (proximo) index++;
				System.out.println("Index: " + index);
				boolean mudaPagina = false;
				if (index > 9 && page < numeroPaginasPesquisa) {
					index = 0;
					page = page +1;
					mudaPagina = true;
				}
				else if (index < 0 && page > 1) {
					index = 9;
					page = page -1;
					mudaPagina = true;
				}
				if (mudaPagina) {
					System.out.println("mudaPagina");
					filtro.setNumeroPagina(page -1);
					sessao.setAttribute("page.offset", page);
					
					Collection<RegistroAtendimento> colecaoRA = fachada.filtrarRegistroAtendimento(filtro);
					if (colecaoRA != null && colecaoRA.size() > 0) {
						colecao = (List<RAFiltroHelper>) loadColecaoRAHelper(colecaoRA);
						sessao.setAttribute("colecaoRAHelper", colecao);
						idRA = colecao.get(index).getRegistroAtendimento().getId();
					} else {
						httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
					}
				}
				
				if (page == 1 && index == 0) {
					httpServletRequest.setAttribute("desabilitaBotaoAnterior", "true");
				}
				if ((page >= numeroPaginasPesquisa) && index >= colecao.size() -1) {
					System.out.println("EEK!");
					httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
				}
				
				if (index >= 0 && index <= 9) {
					if (index > colecao.size() -1) {
						httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
					}
					idRA = colecao.get(index).getRegistroAtendimento().getId();
					
				}
				
			} else {
				httpServletRequest.setAttribute("desabilitaBotaoAnterior", "true");
				httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
			}
		} else {
			httpServletRequest.setAttribute("naoHabilitarNavegacao", "OK");
		}
		
		
		ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimentoHelper = 
		fachada.obterDadosRegistroAtendimento(new Integer(idRA));
		
		if (obterDadosRegistroAtendimentoHelper == null || 
				obterDadosRegistroAtendimentoHelper.getRegistroAtendimento() == null) {
			
			throw new ActionServletException("atencao.naocadastrado",null, "Registro Atendimento");
		}

			
		RegistroAtendimento registroAtendimento = obterDadosRegistroAtendimentoHelper.getRegistroAtendimento();
		
		
		//Dados Gerais do Registro de Atendimento
		consultarRegistroAtendimentoActionForm.setNumeroRAPesquisado(""+registroAtendimento.getId());
		
		if (registroAtendimento.getManual() != null){
			int tamanhoNumeracao = registroAtendimento.getManual().toString().length();
			String numeracao = registroAtendimento.getManual().toString().substring(0, tamanhoNumeracao - 1);
			consultarRegistroAtendimentoActionForm.setNumeroRAManual(
			Util.formatarNumeracaoRAManual(new Integer(numeracao)));
		}
		else{
			consultarRegistroAtendimentoActionForm.setNumeroRAManual("");
		}
		
		consultarRegistroAtendimentoActionForm.setCodigoSituacao(""+registroAtendimento.getCodigoSituacao());
		
		//Caso de Uso [UC0420]
		ObterDescricaoSituacaoRAHelper situacaoRA = 
			fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
		
		consultarRegistroAtendimentoActionForm.setSituacaoRA(situacaoRA.getDescricaoSituacao());		

		//Caso de Uso [UC0433]		
		ObterRAAssociadoHelper obterRAAssociadoHelper = fachada.obterRAAssociadoConsultarRA(registroAtendimento.getId());
		
		if(obterRAAssociadoHelper != null && obterRAAssociadoHelper.getRegistroAtendimentoAssociado() != null){
			consultarRegistroAtendimentoActionForm.setNumeroRaAssociado(""+obterRAAssociadoHelper.getRegistroAtendimentoAssociado().getId());

			ObterDescricaoSituacaoRAHelper situacaoRAssociado = 
				fachada.obterDescricaoSituacaoRA(obterRAAssociadoHelper.getRegistroAtendimentoAssociado().getId());
			
			consultarRegistroAtendimentoActionForm.setSituacaoRaAssociado(situacaoRAssociado.getDescricaoSituacao());
			
			if(obterRAAssociadoHelper.getCodigoExistenciaRAAssociado() == RegistroAtendimento.CODIGO_ASSOCIADO_RA_REFERENCIA){
				consultarRegistroAtendimentoActionForm.setDescricaoRAAssociada("Número do RA de Referência:");
				consultarRegistroAtendimentoActionForm.setDescricaoSituacaoRAAssociada("Situação do RA de Referência:");
			}else if(obterRAAssociadoHelper.getCodigoExistenciaRAAssociado() == RegistroAtendimento.CODIGO_ASSOCIADO_RA_ATUAL){
				consultarRegistroAtendimentoActionForm.setDescricaoRAAssociada("Número do RA Atual:");
				consultarRegistroAtendimentoActionForm.setDescricaoSituacaoRAAssociada("Situação do RA Atual:");
			}else if(obterRAAssociadoHelper.getCodigoExistenciaRAAssociado() == RegistroAtendimento.CODIGO_ASSOCIADO_RA_ANTERIOR){
				consultarRegistroAtendimentoActionForm.setDescricaoRAAssociada("Número do RA Anterior:");
				consultarRegistroAtendimentoActionForm.setDescricaoSituacaoRAAssociada("Situação do RA Anterior:");
			}
			
			httpServletRequest.setAttribute("existeRaAssociado",true);
		}
		
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
			registroAtendimento.getSolicitacaoTipoEspecificacao();
		
		if(solicitacaoTipoEspecificacao != null){
			
			if(solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null){
				consultarRegistroAtendimentoActionForm.setIdTipoSolicitacao(""+solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId());
				consultarRegistroAtendimentoActionForm.setTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());	
			}
			
			if(solicitacaoTipoEspecificacao.getServicoTipo() != null){
				String valorPrevisto = Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getServicoTipo().getValor());
				consultarRegistroAtendimentoActionForm.setValorSugerido(valorPrevisto);
			}
			
			consultarRegistroAtendimentoActionForm.setIdEspecificacao(""+solicitacaoTipoEspecificacao.getId());
			consultarRegistroAtendimentoActionForm.setEspecificacao(solicitacaoTipoEspecificacao.getDescricao());		
		}

		//Perfil do Imovel
		if(registroAtendimento.getImovel()!= null){
			ImovelPerfil imovelPerfil = 
				registroAtendimento.getImovel().getImovelPerfil();
			
			if(imovelPerfil != null){				
				consultarRegistroAtendimentoActionForm.setPerfilImovel(imovelPerfil.getDescricao());		
			}
			
		}
		

		consultarRegistroAtendimentoActionForm.setTipoAtendimento(""+registroAtendimento.getIndicadorAtendimentoOnline());
		
		Date dataAtendimento = registroAtendimento.getRegistroAtendimento();
		
		consultarRegistroAtendimentoActionForm.setDataAtendimento(Util.formatarData(dataAtendimento));		
		consultarRegistroAtendimentoActionForm.setHoraAtendimento(Util.formatarHoraSemSegundos(dataAtendimento));
		
		consultarRegistroAtendimentoActionForm.setTempoEsperaInicio(Util.formatarHoraSemSegundos(registroAtendimento.getDataInicioEspera()));		
		consultarRegistroAtendimentoActionForm.setTempoEsperaTermino(Util.formatarHoraSemSegundos(registroAtendimento.getDataFimEspera()));
		
		consultarRegistroAtendimentoActionForm.setDataPrevista(Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
		
		if(registroAtendimento.getMeioSolicitacao() != null){
			consultarRegistroAtendimentoActionForm.setIdMeioSolicitacao(""+registroAtendimento.getMeioSolicitacao().getId());
			consultarRegistroAtendimentoActionForm.setMeioSolicitacao(registroAtendimento.getMeioSolicitacao().getDescricao());	
		}
		
		//Caso de Uso [UC0421]
		UnidadeOrganizacional unidadeAtendimento = fachada.obterUnidadeAtendimentoRA(registroAtendimento.getId());
		
		if(unidadeAtendimento != null){
			
			consultarRegistroAtendimentoActionForm.setIdUnidadeAtendimento(""+unidadeAtendimento.getId());
			consultarRegistroAtendimentoActionForm.setUnidadeAtendimento(unidadeAtendimento.getDescricao());
			
			RegistroAtendimentoUnidade registroAtendimentoUnidade = 
				this.consultarRegistroAtendimentoUnidade(registroAtendimento.getId(),unidadeAtendimento.getId(),
						AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
			
			Usuario usuario = registroAtendimentoUnidade.getUsuario();
			
			if(usuario != null){
				consultarRegistroAtendimentoActionForm.setIdUsuario(""+usuario.getId());
				consultarRegistroAtendimentoActionForm.setUsuario(usuario.getNomeUsuario());
			}
			
			
		}

		//Caso de Uso [UC0418]
		UnidadeOrganizacional unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
		
		if(unidadeAtual != null){
			consultarRegistroAtendimentoActionForm.setIdUnidadeAtual(""+unidadeAtual.getId());
			consultarRegistroAtendimentoActionForm.setUnidadeAtual(unidadeAtual.getDescricao());
		}
		
		UnidadeOrganizacional unidadeAnterior = fachada.verificaUnidadeAnteriorRA(registroAtendimento.getId());
		if(unidadeAnterior != null){
			consultarRegistroAtendimentoActionForm.setIdUnidadeAnterior(""+unidadeAnterior.getId());
			consultarRegistroAtendimentoActionForm.setUnidadeAnterior(unidadeAnterior.getDescricao());
		}else{
			consultarRegistroAtendimentoActionForm.setIdUnidadeAnterior("");
			consultarRegistroAtendimentoActionForm.setUnidadeAnterior("");
		}
		
		consultarRegistroAtendimentoActionForm.setObservacao(registroAtendimento.getObservacao());
		
		
		//Dados do Local da Ocorrencia
		Imovel imovel = registroAtendimento.getImovel();
		if(imovel != null){
			
			consultarRegistroAtendimentoActionForm.setMatriculaImovel(""+imovel.getId());
			consultarRegistroAtendimentoActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
			consultarRegistroAtendimentoActionForm.setRota(obterDadosRegistroAtendimentoHelper.getCodigoRota().toString());
			
			if (obterDadosRegistroAtendimentoHelper.getSequencialRota() != null) {
				consultarRegistroAtendimentoActionForm.setSequencialRota(obterDadosRegistroAtendimentoHelper.getSequencialRota().toString());
			}
		}

		//Caso de Uso [UC0422]		
		String enderecoOcorrencia = fachada.obterEnderecoOcorrenciaRA(registroAtendimento.getId());
		
		consultarRegistroAtendimentoActionForm.setEnderecoOcorrencia(enderecoOcorrencia);
		consultarRegistroAtendimentoActionForm.setPontoReferencia(registroAtendimento.getPontoReferencia());
		
		//*******************************************************************
		// Por: Ivan Sergio
		// Data: 09/09/2009
		// CRC2621
		//*******************************************************************
		if (registroAtendimento.getNnCoordenadaNorte() != null) {
			consultarRegistroAtendimentoActionForm.setNumeroCoordenadaNorte(
					"" + registroAtendimento.getNnCoordenadaNorte());
		}else {
			consultarRegistroAtendimentoActionForm.setNumeroCoordenadaNorte("");
		}
		if (registroAtendimento.getNnCoordenadaLeste() != null) {
			consultarRegistroAtendimentoActionForm.setNumeroCoordenadaLeste(
					"" + registroAtendimento.getNnCoordenadaLeste());
		}else {
			consultarRegistroAtendimentoActionForm.setNumeroCoordenadaLeste("");
		}
		//*******************************************************************
		
		
		//Caso o registro atendimento esteja associado a uma área de bairro,
		//obter os dados da área do bairro
		BairroArea bairroArea = registroAtendimento.getBairroArea();
		
		if(bairroArea != null){

			consultarRegistroAtendimentoActionForm.setIdMunicipio(""+bairroArea.getBairro().getMunicipio().getId());
			consultarRegistroAtendimentoActionForm.setMunicipio(bairroArea.getBairro().getMunicipio().getNome());
			
			consultarRegistroAtendimentoActionForm.setIdBairro(""+bairroArea.getBairro().getId());
			consultarRegistroAtendimentoActionForm.setBairro(bairroArea.getBairro().getNome());
			
			consultarRegistroAtendimentoActionForm.setIdAreaBairro(""+bairroArea.getId());
			consultarRegistroAtendimentoActionForm.setAreaBairro(bairroArea.getNome());
			
		}
		
		Localidade localidade = registroAtendimento.getLocalidade();
		
		if(localidade != null){
			
			consultarRegistroAtendimentoActionForm.setIdLocalidade(""+localidade.getId());
			consultarRegistroAtendimentoActionForm.setLocalidade(localidade.getDescricao());
		}
		
		SetorComercial setorComercial = registroAtendimento.getSetorComercial();
		
		if(setorComercial != null){
			consultarRegistroAtendimentoActionForm.setIdSetorComercial(""+setorComercial.getCodigo());
			consultarRegistroAtendimentoActionForm.setSetorComercial(setorComercial.getDescricao());
		}
		
		Quadra quadra = registroAtendimento.getQuadra();
		
		if(quadra != null){
			consultarRegistroAtendimentoActionForm.setIdQuadra(""+quadra.getNumeroQuadra());
		}
		
		DivisaoEsgoto divisaoEsgoto = registroAtendimento.getDivisaoEsgoto();
		
		if(divisaoEsgoto != null){

			consultarRegistroAtendimentoActionForm.setIdDivisaoEsgoto(""+divisaoEsgoto.getId());
			consultarRegistroAtendimentoActionForm.setDivisaoEsgoto(divisaoEsgoto.getDescricao());			
		}
		
		LocalOcorrencia localOcorrencia = registroAtendimento.getLocalOcorrencia();
		
		if(localOcorrencia != null){
			consultarRegistroAtendimentoActionForm.setLocalOcorrencia(localOcorrencia.getDescricao());
		}
		
		PavimentoRua pavimentoRua = registroAtendimento.getPavimentoRua();
		
		if(pavimentoRua != null){
			consultarRegistroAtendimentoActionForm.setPavimentoRua(pavimentoRua.getDescricao());
		}

		PavimentoCalcada pavimentoCalcada = registroAtendimento.getPavimentoCalcada();
		
		if(pavimentoCalcada != null){
			consultarRegistroAtendimentoActionForm.setPavimentoCalcada(pavimentoCalcada.getDescricao());
		}

		consultarRegistroAtendimentoActionForm.setDescricaoLocalOcorrencia(registroAtendimento.getDescricaoLocalOcorrencia());
		
		//Dados do Solicitante
		
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = 
			this.consultarRegistroAtendimentoSolicitante(registroAtendimento.getId());
		
		if(registroAtendimentoSolicitante != null){
			
			Cliente cliente = registroAtendimentoSolicitante.getCliente();
			UnidadeOrganizacional unidadeSolicitante = registroAtendimentoSolicitante.getUnidadeOrganizacional();

			//PROTOCOLO DE ATENDIMENTO
			if (registroAtendimentoSolicitante.getNumeroProtocoloAtendimento() != null &&
				!registroAtendimentoSolicitante.getNumeroProtocoloAtendimento().equals("")){
				
				consultarRegistroAtendimentoActionForm.setNumeroProtocolo(
				registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
			}
			
			//Caso o principal solicitante do registro de atendimento seja um cliente
			//obter os dados do cliente
			if(cliente != null){
			
				consultarRegistroAtendimentoActionForm.setIdClienteSolicitante(""+cliente.getId());
				consultarRegistroAtendimentoActionForm.setClienteSolicitante(cliente.getNome());	

			//Caso o principal solicitante do registro de atendimento seja uma unidade
			//obter os dados da unidade
			}else if(unidadeSolicitante != null){

				consultarRegistroAtendimentoActionForm.setIdUnidadeSolicitante(""+unidadeSolicitante.getId());
				consultarRegistroAtendimentoActionForm.setUnidadeSolicitante(unidadeSolicitante.getDescricao());	

			//Caso o principal solicitante do registro de atendimento não seja um cliente, nem uma unidade
			//obter os dados do solicitante
			}else{
				consultarRegistroAtendimentoActionForm.setNomeSolicitante(registroAtendimentoSolicitante.getSolicitante());
			}
			
			Funcionario funcionario = registroAtendimentoSolicitante.getFuncionario();
			
			if(funcionario != null){
				consultarRegistroAtendimentoActionForm.setIdFuncionarioResponsavel(""+funcionario.getId());
				consultarRegistroAtendimentoActionForm.setFuncionarioResponsavel(funcionario.getNome());
			}

			//Caso de Uso [UC0423]
			String enderecoSolicitante = fachada.obterEnderecoSolicitanteRA(registroAtendimentoSolicitante.getID());
			
			consultarRegistroAtendimentoActionForm.setEnderecoSolicitante(enderecoSolicitante);
			consultarRegistroAtendimentoActionForm.setPontoReferenciaSolicitante(
				registroAtendimentoSolicitante.getPontoReferencia());

			SolicitanteFone solicitanteFone = consultarSolicitanteFone(registroAtendimentoSolicitante.getID());
			
			if(solicitanteFone != null){
				consultarRegistroAtendimentoActionForm.setFoneDDD(""+solicitanteFone.getDdd());
				consultarRegistroAtendimentoActionForm.setFone(solicitanteFone.getFone());
				consultarRegistroAtendimentoActionForm.setFoneRamal(solicitanteFone.getRamal());
				
			}
			
			//[RM1094] Questionario de Satisfacao do Cliente
			if(registroAtendimentoSolicitante != null && registroAtendimentoSolicitante.getIndicadorEnvioEmailPesquisa() != null){
				sessao.setAttribute("habilitarCampoSatisfacaoEmail", true);
    			consultarRegistroAtendimentoActionForm.setEnviarEmailSatisfacao(registroAtendimentoSolicitante.getIndicadorEnvioEmailPesquisa().intValue()+"");
    			consultarRegistroAtendimentoActionForm.setEnderecoEmail(registroAtendimentoSolicitante.getEnderecoEmail());
	    	}else{
	    		sessao.setAttribute("habilitarCampoSatisfacaoEmail", false);
	    	}
			
		}
		
		
		/*
		 * ANEXOS
		 * -----------------------------------------------------------------------------------------------------------
		 */
		//CARREGANDO OS ANEXOS QUE ESTÃO CADASTRADOS NA BASE
		String visualizar = httpServletRequest.getParameter("visualizar");
		
		FiltroRegistroAtendimentoAnexo filtroRegistroAtendimentoAnexo = new FiltroRegistroAtendimentoAnexo();
			
		filtroRegistroAtendimentoAnexo.adicionarParametro(new ParametroSimples(
		FiltroRegistroAtendimentoAnexo.REGISTRO_ATENDIMENTO_ID,
		registroAtendimento.getId()));

		Collection colecaoRegistroAtendimentoAnexo = fachada.pesquisar(filtroRegistroAtendimentoAnexo,
		RegistroAtendimentoAnexo.class.getName());
			
		httpServletRequest.setAttribute("colecaoRegistroAtendimentoAnexo", colecaoRegistroAtendimentoAnexo);
		
		//OBTENDO ARQUIVO PARA VISUALIZAÇÃO
		RegistroAtendimentoAnexo registroAtendimentoAnexo = this.obterArquivoParaVisualizacao(visualizar, 
		colecaoRegistroAtendimentoAnexo);
		
		//PREPARANDO VISUALIZAÇÃO DO ARQUIVO
		if (registroAtendimentoAnexo != null){
			
			OutputStream out = null;
			
			String mimeType = ConstantesSistema.CONTENT_TYPE_GENERICO;
			
			if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_DOC)){
				mimeType = ConstantesSistema.CONTENT_TYPE_MSWORD;
			}
			else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_PDF)){
				mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
			}
			else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_JPG)){
				mimeType = ConstantesSistema.CONTENT_TYPE_JPEG;
			}
			
			try {
				httpServletResponse.setContentType(mimeType);
				out = httpServletResponse.getOutputStream();
				
				out.write(registroAtendimentoAnexo.getImagemDocumento());
				out.flush();
				out.close();
			} 
			catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
			}
		}
		/*
		 * FIM DOS ANEXOS
		 * -----------------------------------------------------------------------------------------------------------
		 */
		
		
		//Dados da Ultima Tramitação

		Tramite tramite = 
			fachada.recuperarTramiteMaisAtualPorRA(registroAtendimento.getId());
		
		if(tramite != null){
			
			UnidadeOrganizacional unidadeOrigem = tramite.getUnidadeOrganizacionalOrigem();
			
			if(unidadeOrigem != null){
				
				consultarRegistroAtendimentoActionForm.setIdUnidadeOrigem(""+unidadeOrigem.getId());
				consultarRegistroAtendimentoActionForm.setUnidadeOrigem(unidadeOrigem.getDescricao());
			}
			
			UnidadeOrganizacional unidadeDestino = tramite.getUnidadeOrganizacionalDestino();
			
			if(unidadeDestino != null){
			
				consultarRegistroAtendimentoActionForm.setIdUnidadeAtualTramitacao(""+unidadeDestino.getId());
				consultarRegistroAtendimentoActionForm.setUnidadeAtualTramitacao(unidadeDestino.getDescricao());

			}			
			
			Date dataTramite = tramite.getDataTramite();
			
			consultarRegistroAtendimentoActionForm.setDataTramite(Util.formatarData(dataTramite));
			consultarRegistroAtendimentoActionForm.setHoraTramite(Util.formatarHoraSemSegundos(dataTramite));

			consultarRegistroAtendimentoActionForm.setParecerTramite(tramite.getParecerTramite());
			
			Usuario usuarioResponsavel =  tramite.getUsuarioResponsavel();
			
			if(usuarioResponsavel != null){
				consultarRegistroAtendimentoActionForm.setIdUsuarioResponsavel(""+usuarioResponsavel.getId());
				consultarRegistroAtendimentoActionForm.setUsuarioResponsavel(usuarioResponsavel.getNomeUsuario());
			}
		}
		
		//Dados da Reiteração
		
		//Caso o registro atendimento tenha sido reiterado,
		//exibir os dados da reiteração
		if(registroAtendimento.getQuantidadeReiteracao() != null){
			
			Date dataUltimaReiteracao = registroAtendimento.getUltimaReiteracao();

			consultarRegistroAtendimentoActionForm.setQuantidade(""+registroAtendimento.getQuantidadeReiteracao());
			consultarRegistroAtendimentoActionForm.setDataUltimaReiteracao(Util.formatarData(dataUltimaReiteracao));
			consultarRegistroAtendimentoActionForm.setHoraUltimaReiteracao(Util.formatarHoraSemSegundos(dataUltimaReiteracao));

		}
		
		obterDadosReiteracaoRa(registroAtendimento.getId(),fachada,sessao);
		
		//Dados da Reativação
		
		//Caso o registro atendimento tenha sido reativado
		//exibir os dados da reativação
		Short codigoAssociado = obterRAAssociadoHelper.getCodigoExistenciaRAAssociado();
		
		RegistroAtendimento registroAtendimentoAssociado = 
			obterRAAssociadoHelper.getRegistroAtendimentoAssociado();
			
		//Caso de Uso [UC0420]
		ObterDescricaoSituacaoRAHelper situacaoRAAssociado = null;
		if(registroAtendimentoAssociado != null){
			situacaoRAAssociado = fachada.obterDescricaoSituacaoRA(registroAtendimentoAssociado.getId());
		}

		if(codigoAssociado == RegistroAtendimento.CODIGO_ASSOCIADO_RA_ATUAL && registroAtendimentoAssociado != null){
			
			consultarRegistroAtendimentoActionForm.setNumeroRaAtual(""+registroAtendimentoAssociado.getId());
			consultarRegistroAtendimentoActionForm.setSituacaoRaAtual(situacaoRAAssociado.getDescricaoSituacao());
			
			RaMotivoReativacao raMotivoReativacao = registroAtendimentoAssociado.getRaMotivoReativacao();
			if(raMotivoReativacao!= null){
				consultarRegistroAtendimentoActionForm.setIdMotivoReativacao(""+raMotivoReativacao.getId());
				consultarRegistroAtendimentoActionForm.setMotivoReativacao(raMotivoReativacao.getDescricao());
			}
			
			Date dataRegistro = registroAtendimentoAssociado.getRegistroAtendimento();
			Date dataPrevista = registroAtendimentoAssociado.getDataPrevistaAtual();
			
			consultarRegistroAtendimentoActionForm.setDataReativacao(Util.formatarData(dataRegistro));
			consultarRegistroAtendimentoActionForm.setHoraReativacao(Util.formatarHoraSemSegundos(dataRegistro));
			
			consultarRegistroAtendimentoActionForm.setDataPrevistaRaAtual(Util.formatarData(dataPrevista));

			//Caso de Uso [UC0421]			
			UnidadeOrganizacional unidadeReativacao = 
				fachada.obterUnidadeAtendimentoRA(registroAtendimentoAssociado.getId());
			
			if(unidadeReativacao != null){
				consultarRegistroAtendimentoActionForm.setIdUnidadeReativacao(""+unidadeReativacao.getId());
				consultarRegistroAtendimentoActionForm.setUnidadeReativacao(unidadeReativacao.getDescricao());		
			}
			
			//Caso de Uso [UC0418]			
			UnidadeOrganizacional unidadeRAAtual = 
				fachada.obterUnidadeAtualRA(registroAtendimentoAssociado.getId());
			
			if(unidadeRAAtual != null){
				consultarRegistroAtendimentoActionForm.setIdUnidadeRaAtual(""+unidadeRAAtual.getId());
				consultarRegistroAtendimentoActionForm.setUnidadeRaAtual(unidadeRAAtual.getDescricao());
			}
			
			consultarRegistroAtendimentoActionForm.setObservacaoReativacao(registroAtendimentoAssociado.getObservacao());
		}
		
		//Dados do encerramento
		
		//Caso o registro atendimento seja encerrado,
		//exibir os dados do encerramento
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = 
			registroAtendimento.getAtendimentoMotivoEncerramento();
		
		if(atendimentoMotivoEncerramento != null){
			
			consultarRegistroAtendimentoActionForm.setIdMotivoEncerramento(""+atendimentoMotivoEncerramento.getId());	
			consultarRegistroAtendimentoActionForm.setMotivoEncerramento(atendimentoMotivoEncerramento.getDescricao());

			if(codigoAssociado == RegistroAtendimento.CODIGO_ASSOCIADO_RA_REFERENCIA && registroAtendimentoAssociado != null){
				
				consultarRegistroAtendimentoActionForm.setNumeroRaReferencia(""+registroAtendimentoAssociado.getId());

				//Caso de Uso [UC0420]
				consultarRegistroAtendimentoActionForm.setSituacaoRaReferencia(situacaoRAAssociado.getDescricaoSituacao());
				
			}

			//Caso de Uso [UC0434]
			UnidadeOrganizacional unidadeEncerramento = 
				fachada.obterUnidadeEncerramentoRA(registroAtendimento.getId());
			
			if(unidadeEncerramento != null){
				
				consultarRegistroAtendimentoActionForm.setIdUnidadeEncerramento(""+unidadeEncerramento.getId());
				consultarRegistroAtendimentoActionForm.setUnidadeEncerramento(unidadeEncerramento.getDescricao());		

				RegistroAtendimentoUnidade registroAtendimentoUnidade = 
					this.consultarRegistroAtendimentoUnidade(
							registroAtendimento.getId(),
							unidadeEncerramento.getId(),
							AtendimentoRelacaoTipo.ENCERRAR);
				
				Usuario usuario = registroAtendimentoUnidade.getUsuario();
				if(usuario != null){
					
					consultarRegistroAtendimentoActionForm.setIdUsuarioEncerramento(""+usuario.getId());
					consultarRegistroAtendimentoActionForm.setUsuarioEncerramento(usuario.getNomeUsuario());
				}
			}
			
			Date dataEncerramento = registroAtendimento.getDataEncerramento();
			
			consultarRegistroAtendimentoActionForm.setDataEncerramento(Util.formatarData(dataEncerramento));
			consultarRegistroAtendimentoActionForm.setHoraEncerramento(Util.formatarHoraSemSegundos(dataEncerramento));
			
			consultarRegistroAtendimentoActionForm.setDataPrevistaEncerramento(
				Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
			
			consultarRegistroAtendimentoActionForm.setParecerEncerramento(registroAtendimento.getParecerEncerramento());
			
			if(registroAtendimento.getServicoNaoCobrancaMotivo() != null){
				consultarRegistroAtendimentoActionForm.setMotivoNaoCobranca(
						registroAtendimento.getServicoNaoCobrancaMotivo().getDescricao());
			}
		}
		
		// Dados das Contas relacionados
		// Mariana Victor em 28/01/2011
		FiltroRegistroAtendimentoConta filtroRegistroAtendimentoConta = new FiltroRegistroAtendimentoConta();
		filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(
				FiltroRegistroAtendimentoConta.CONTA);
		filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(
				FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO);
		filtroRegistroAtendimentoConta.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
		
		Collection colecaoRAContas = fachada.pesquisar(
				filtroRegistroAtendimentoConta, RegistroAtendimentoConta.class.getName());
		
		if (colecaoRAContas != null && !colecaoRAContas.isEmpty()) {
			sessao.setAttribute("colecaoRAContas", colecaoRAContas);
		} else {
			sessao.removeAttribute("colecaoRAContas");
		}
		
		//Colocado por Raphael Rossiter em 26/10/2006
		consultarRegistroAtendimentoActionForm.setNumeroRA("");
		httpServletRequest.setAttribute("nomeCampo", "numeroRA");
		
		//Pagamentos Duplicidade
		FiltroRegistroAtendimentoPagamentoDuplicidade filtroRegistroAtendimentoPagamentoDuplicidade = 
			new FiltroRegistroAtendimentoPagamentoDuplicidade();
		filtroRegistroAtendimentoPagamentoDuplicidade.adicionarParametro(
			new ParametroSimples(
				FiltroRegistroAtendimentoPagamentoDuplicidade.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
		
		Collection<RegistroAtendimentoPagamentoDuplicidade> colecaoRAPagamentoDuplicidade = 
			this.getFachada().pesquisar(filtroRegistroAtendimentoPagamentoDuplicidade, RegistroAtendimentoPagamentoDuplicidade.class.getName());
		
		if (colecaoRAPagamentoDuplicidade != null && !colecaoRAPagamentoDuplicidade.isEmpty()){
			sessao.setAttribute("colecaoRAPagamentoDuplicidade", colecaoRAPagamentoDuplicidade);
		} else {
			sessao.removeAttribute("colecaoRAPagamentoDuplicidade");
		}		
		
		
		return retorno;
	}

	/**
	 * Consulta o registro atendimento solicitante pelo id do registro atendimento
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private RegistroAtendimentoSolicitante consultarRegistroAtendimentoSolicitante(Integer idRegistroAtendimento){

		RegistroAtendimentoSolicitante retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimento = null; 

		FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimento = new FiltroRegistroAtendimentoSolicitante();

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID,idRegistroAtendimento));

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
			FiltroRegistroAtendimentoSolicitante.INDICADOR_SOLICITANTE_PRINCIPAL,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		
		colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento,
				RegistroAtendimentoSolicitante.class.getName());

		if (colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()) {
			retorno = (RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
			
		} 
		
		return retorno;
	}

	/**
	 * Consulta o solicitante fone pelo id do registro atendimentoSolicitante
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private SolicitanteFone consultarSolicitanteFone(Integer idRegistroAtendimentoSolicitante){

		SolicitanteFone retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoSolicitanteFone = null; 

		FiltroSolicitanteFone filtroSolicitanteFone = new FiltroSolicitanteFone();

		filtroSolicitanteFone.adicionarParametro(
			new ParametroSimples(FiltroSolicitanteFone.REGISTRO_ATENDIMENTO_SOLICITANTE_ID,
					idRegistroAtendimentoSolicitante));
		
		colecaoSolicitanteFone = fachada.pesquisar(filtroSolicitanteFone,
				SolicitanteFone.class.getName());

		if (colecaoSolicitanteFone != null && !colecaoSolicitanteFone.isEmpty()) {
			retorno = (SolicitanteFone) Util.retonarObjetoDeColecao(colecaoSolicitanteFone);
			
		} 
		
		return retorno;
	}

	/**
	 * Consulta o Registro Atendimento Unidade pelo id da RA
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private RegistroAtendimentoUnidade consultarRegistroAtendimentoUnidade(
			Integer idRA,Integer idUnidade,Integer atendimentoRelacaoTipoId){

		RegistroAtendimentoUnidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimentoUnidade = null; 

		FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();

		filtroRegistroAtendimentoUnidade.adicionarParametro(
			new ParametroSimples(FiltroRegistroAtendimentoUnidade.REGISTRO_ATENDIMENTO_ID,idRA));

		filtroRegistroAtendimentoUnidade.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoUnidade.UNIDADE_ORGANIZACIONAL_ID,idUnidade));
		
		if(atendimentoRelacaoTipoId!=null){
			filtroRegistroAtendimentoUnidade.adicionarParametro(
					new ParametroSimples(FiltroRegistroAtendimentoUnidade.ATENDIMENTO_RELACAO_TIPO,atendimentoRelacaoTipoId));
		}
		
		filtroRegistroAtendimentoUnidade.adicionarCaminhoParaCarregamentoEntidade("usuario");
		
		colecaoRegistroAtendimentoUnidade = 
			fachada.pesquisar(filtroRegistroAtendimentoUnidade,RegistroAtendimentoUnidade.class.getName());

		if (colecaoRegistroAtendimentoUnidade != null && !colecaoRegistroAtendimentoUnidade.isEmpty()) {
			retorno = (RegistroAtendimentoUnidade) Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoUnidade);
			
		} 
		
		return retorno;
	}
	
	
	
	private int obterIndexRAColecao(Integer idRA, Collection<RAFiltroHelper> colecao) {
		int index = 0;
		for (RAFiltroHelper helper : colecao) {
			if (helper.getRegistroAtendimento().getId().equals(idRA)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	private Collection loadColecaoRAHelper(Collection<RegistroAtendimento> colecaoRegistroAtendimento) {
		Fachada fachada = Fachada.getInstancia();
		Collection colecaoRAHelper = new ArrayList();
		UnidadeOrganizacional unidadeAtual = null;
		ObterDescricaoSituacaoRAHelper situacao = null;
		RAFiltroHelper helper = null;
		for (Iterator iter = colecaoRegistroAtendimento.iterator(); iter.hasNext();) {
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) iter.next();
			
			situacao = fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
			unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
			helper = new RAFiltroHelper();
			helper.setRegistroAtendimento(registroAtendimento);
			helper.setUnidadeAtual(unidadeAtual);
			helper.setSituacao(situacao.getDescricaoAbreviadaSituacao());
			colecaoRAHelper.add(helper);
		}
		return colecaoRAHelper;
	}
	
	/**
	 * Removendo um arquivo da coleção
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private RegistroAtendimentoAnexo obterArquivoParaVisualizacao(String identificacao, 
			Collection colecaoRegistroAtendimentoAnexo){
		
		RegistroAtendimentoAnexo registroAtendimentoAnexo = null;
		
		if (identificacao != null && !identificacao.equals("")){
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					registroAtendimentoAnexo = anexoColecao;
					break;
				}
			}
		}
		
		return registroAtendimentoAnexo;
	}

	/**
	 * @author Vivianne Sousa
	 * @date 16/05/2011
	 */	
	private void obterDadosReiteracaoRa(Integer numeroRA, 
			Fachada fachada,HttpSession sessao) {
		
		sessao.removeAttribute("colecaoDadosReiteracao");
		
		if(numeroRA != null){
			Collection colecaoDadosReiteracao = fachada.pesquisarDadosReiteracaoRA(numeroRA);

			if(colecaoDadosReiteracao != null && !colecaoDadosReiteracao.isEmpty()){
				sessao.setAttribute("colecaoDadosReiteracao",colecaoDadosReiteracao);
			}
		}
	}
}
