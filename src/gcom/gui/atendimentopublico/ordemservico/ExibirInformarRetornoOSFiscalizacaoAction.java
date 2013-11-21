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

import gcom.atendimentopublico.ligacaoagua.FiltroMaterialLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroPerfilLigacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacaoServicoACobrar;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoServicoACobrar;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoFiscSit;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.SituacaoEncontradaHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.fachada.Fachada;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.autoinfracao.FiltroAutoInfracaoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
 * < <Descrição da Classe>>
 * 
 * @author Sávio Luiz
 * @date 13/11/2006
 */
public class ExibirInformarRetornoOSFiscalizacaoAction extends GcomAction {
	
	/**
	 * 
	 * [UC0448] Informar Retorno Ordem de Fiscalização
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

		ActionForward retorno = 
			actionMapping.findForward("informarRetornoOSFiscalizacao");
		
		InformarRetornoOSFiscalizacaoActionForm informarRetornoOSFiscalizacaoActionForm = 
			(InformarRetornoOSFiscalizacaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		String idOS = 
			informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico();
		
		String nomeOS = 
			informarRetornoOSFiscalizacaoActionForm.getNomeOrdemServico();
		
		String pesquisarFuncionario = httpServletRequest.getParameter("pesquisarFuncionario");
		
		SistemaParametro sistemaParametro = 
			this.getFachada().pesquisarParametrosDoSistema();
		
		String primeiraVez = (String)httpServletRequest.getParameter("menu");
		
		httpServletRequest.setAttribute("indicadorAtualizacaoAutosInfracao",
				informarRetornoOSFiscalizacaoActionForm.getIndicadorAtualizacaoAutosInfracao());


		// parte que valida o enter
		if ((idOS != null && !idOS.trim().equals(""))
				&& (nomeOS == null || nomeOS.equals(""))) {

			Object[] parmsOS = 
				this.getFachada().pesquisarParmsOS(Util.converterStringParaInteger(idOS));

			if (parmsOS != null) {

				OrdemServico ordemServico = new OrdemServico();

				if (parmsOS[7] != null) {
					ServicoTipo servicoTipo = new ServicoTipo();
					servicoTipo.setIndicadorFiscalizacaoInfracao((Short) parmsOS[7]);

					ordemServico.setServicoTipo(servicoTipo);
				}

				if (parmsOS[8] != null) {
					ordemServico.setSituacao((Short) parmsOS[8]);
				}

				if (parmsOS[9] != null) {
					CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
					cobrancaDocumento.setId((Integer) parmsOS[9]);

					ordemServico.setCobrancaDocumento(cobrancaDocumento);
				}

				// [FS0001 - Validar Ordem de Serviço]
				this.getFachada().validarOrdemServicoInformarRetornoOrdemFiscalizacao(ordemServico);

				sessao.setAttribute("ordemServico", ordemServico);
				
				String nomeOSPesquisar = "";
				Integer idImovel = null;
				String descricaoSituacaoAgua = "";
				String descricaoSituacaoEsgoto = "";
				String ocorrencia = "";

				if (parmsOS[0] != null) {
					nomeOSPesquisar = (String) parmsOS[0];
				}
				if (parmsOS[1] != null) {
					idImovel = (Integer) parmsOS[1];
				}
				if (parmsOS[2] != null) {
					descricaoSituacaoAgua = (String) parmsOS[2];
				}
				if (parmsOS[3] != null) {
					descricaoSituacaoEsgoto = (String) parmsOS[3];
				}
				if (parmsOS[4] != null) {
					ocorrencia = (String) parmsOS[4];
				}

				informarRetornoOSFiscalizacaoActionForm.setNomeOrdemServico(nomeOSPesquisar);
				informarRetornoOSFiscalizacaoActionForm.setMatriculaImovel("" + idImovel);

				// Inscrição Imóvel
				String inscricaoImovel = 
					this.getFachada().pesquisarInscricaoImovel(idImovel);
				
				informarRetornoOSFiscalizacaoActionForm.setInscricaoImovel(inscricaoImovel);
				informarRetornoOSFiscalizacaoActionForm.setSituacaoLigacaoAgua(descricaoSituacaoAgua);
				informarRetornoOSFiscalizacaoActionForm.setSituacaoLigacaoEsgoto(descricaoSituacaoEsgoto);

				// Cliente Usuário
				this.pesquisarCliente(informarRetornoOSFiscalizacaoActionForm);
				
				// ocorrencia
				informarRetornoOSFiscalizacaoActionForm.setOcorrencia(ocorrencia);
				
				getFiscalizacaoSituacaoSelecionada(sessao, 
				informarRetornoOSFiscalizacaoActionForm,httpServletRequest);
				
				String codigoTipoRecebimento = null;
				if (parmsOS[10] != null) {
					codigoTipoRecebimento = ((Short) parmsOS[10]).toString();
				}
				informarRetornoOSFiscalizacaoActionForm.setCodigoTipoRecebimentoOS(codigoTipoRecebimento);
				definirSelecaoDocumentoEntregue(informarRetornoOSFiscalizacaoActionForm, httpServletRequest);

			} else {
				httpServletRequest.setAttribute("ordemServicoEncontrado","exception");
				
				informarRetornoOSFiscalizacaoActionForm.setNomeOrdemServico("Ordem de Serviço inexistente");
				
				informarRetornoOSFiscalizacaoActionForm.setIdOrdemServico("");
				informarRetornoOSFiscalizacaoActionForm.setMatriculaImovel("");
				informarRetornoOSFiscalizacaoActionForm.setInscricaoImovel("");
				informarRetornoOSFiscalizacaoActionForm.setClienteUsuario("");
				informarRetornoOSFiscalizacaoActionForm.setCpfCnpjCliente("");
				informarRetornoOSFiscalizacaoActionForm.setSituacaoLigacaoAgua("");
				informarRetornoOSFiscalizacaoActionForm.setSituacaoLigacaoEsgoto("");
				informarRetornoOSFiscalizacaoActionForm.setOcorrencia("");
			}
			
			// Seta coleção de fiscalizacao situação
			getFiscalizacaoSituacao(sessao,informarRetornoOSFiscalizacaoActionForm,httpServletRequest);
			
			habilitaIndicadores(httpServletRequest,informarRetornoOSFiscalizacaoActionForm); 
			
		} 
		
		if ((idOS == null || idOS.trim().equals("")) &&
			(pesquisarFuncionario == null || pesquisarFuncionario.equals(""))){

			informarRetornoOSFiscalizacaoActionForm.setIdOrdemServico("");
			informarRetornoOSFiscalizacaoActionForm.setMatriculaImovel("");
			informarRetornoOSFiscalizacaoActionForm.setInscricaoImovel("");
			informarRetornoOSFiscalizacaoActionForm.setClienteUsuario("");
			informarRetornoOSFiscalizacaoActionForm.setCpfCnpjCliente("");
			informarRetornoOSFiscalizacaoActionForm.setSituacaoLigacaoAgua("");
			informarRetornoOSFiscalizacaoActionForm.setSituacaoLigacaoEsgoto("");
			informarRetornoOSFiscalizacaoActionForm.setNomeOrdemServico("");
			informarRetornoOSFiscalizacaoActionForm.setOcorrencia("");
			informarRetornoOSFiscalizacaoActionForm.setSituacao("");
			informarRetornoOSFiscalizacaoActionForm.setIndicadorTipoMedicao(MedicaoTipo.LIGACAO_AGUA.toString());
			informarRetornoOSFiscalizacaoActionForm.setIndicadorDocumentoEntregue("");
			informarRetornoOSFiscalizacaoActionForm.setIndicadorGeracaoDebito(ConstantesSistema.SIM.toString());
			
			informarRetornoOSFiscalizacaoActionForm.setIndicadorEncerramentoOS(ConstantesSistema.SIM.toString());
			informarRetornoOSFiscalizacaoActionForm.setAtendimentoMotivoEncerramento("");
			informarRetornoOSFiscalizacaoActionForm.setParecerEncerramento("");
		}
		
		if (primeiraVez != null && !primeiraVez.equals("")) {
			// Seta coleção de fiscalizacao situação
			getFiscalizacaoSituacao(sessao,informarRetornoOSFiscalizacaoActionForm,httpServletRequest);
			sessao.removeAttribute("colecaoLigacaoEsgotoDiametro");
		}

		if(httpServletRequest.getParameter("adicionarSituacaoEncontrada") != null &&
				 httpServletRequest.getParameter("adicionarSituacaoEncontrada").equalsIgnoreCase("S")){
					
			// --------------- botão adiciona Situação Encontrada ----------
			adicionarSituacaoEncontrada(informarRetornoOSFiscalizacaoActionForm,sessao,httpServletRequest);
					
		}	
		
		if(httpServletRequest.getParameter("removerSituacaoSelecionada") != null &&
				 httpServletRequest.getParameter("removerSituacaoSelecionada").equalsIgnoreCase("S")){
					
	    	String idFiscalizacaoSituacaoRemover = httpServletRequest.getParameter("idFiscalizacaoSituacaoRemover");
			
			// --------------- botão adiciona Situação Encontrada ----------
			removerSituacaoSelecionada(informarRetornoOSFiscalizacaoActionForm,sessao,
					idFiscalizacaoSituacaoRemover,httpServletRequest);
					
		}			
		
		

		// Permissão especial
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		if (this.getFachada().verificarPermissaoGeracaoDebitoOSFiscalizacao(usuarioLogado)) {
			
			httpServletRequest.setAttribute("disponibilizarNaoGeracaoDebito","OK");
		}

		
		httpServletRequest.setAttribute("indicadorAtualizacaoAutosInfracao",
				informarRetornoOSFiscalizacaoActionForm.getIndicadorAtualizacaoAutosInfracao());

		

		informarRetornoOSFiscalizacaoActionForm.setIndicadorEncerramentoOS("1");
		
		//Colocado por Raphael Rossiter em 03/08/2008 - Analista: Nelson Carvalho
		this.carregarDadosAutoInfracao(
			sessao, 
			httpServletRequest,
			informarRetornoOSFiscalizacaoActionForm,sistemaParametro);
		
		this.verificarObrigatoriedade(informarRetornoOSFiscalizacaoActionForm, 
				sessao, httpServletRequest, sistemaParametro);
		
		this.pesquisaAtendimentoMotivoEncerramento(httpServletRequest,informarRetornoOSFiscalizacaoActionForm);

		return retorno;
	}
	
	
	/**
	 * Pesquisa Atendimento Motivo Encerramento
	 * 
	 * @author Rafael Pinto
	 * @date 20/05/2011
	 */
	private void pesquisaAtendimentoMotivoEncerramento(HttpServletRequest httpServletRequest,
		InformarRetornoOSFiscalizacaoActionForm form){
		
		Collection<SituacaoEncontradaHelper> colecaoFiscalizacaoSituacaoSelecionada = null;
		Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = new ArrayList();
		boolean bloqueiaMotivoEncerramento = false;
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		if (sessao.getAttribute("colecaoFiscalizacaoSituacaoSelecionada") != null) {
			colecaoFiscalizacaoSituacaoSelecionada = (Collection<SituacaoEncontradaHelper>)
				sessao.getAttribute("colecaoFiscalizacaoSituacaoSelecionada"); 
		}
		
		if(colecaoFiscalizacaoSituacaoSelecionada != null && !colecaoFiscalizacaoSituacaoSelecionada.isEmpty()){
			
			Collection<Integer> colecaoIds = new ArrayList<Integer>();
			Iterator<SituacaoEncontradaHelper> iteraFiscalizacaoSituacaoSelecionada = colecaoFiscalizacaoSituacaoSelecionada.iterator();
			
			while (iteraFiscalizacaoSituacaoSelecionada.hasNext()) {
				SituacaoEncontradaHelper fiscalizacao = (SituacaoEncontradaHelper) iteraFiscalizacaoSituacaoSelecionada.next();
				colecaoIds.add(fiscalizacao.getFiscalizacaoSituacao().getId());
			}
			
			FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
			filtroFiscalizacaoSituacao.adicionarParametro(
				new ParametroSimplesIn(
					FiltroFiscalizacaoSituacao.ID,
					colecaoIds));
			filtroFiscalizacaoSituacao.adicionarCaminhoParaCarregamentoEntidade(FiltroFiscalizacaoSituacao.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
			filtroFiscalizacaoSituacao.setCampoOrderBy(FiltroFiscalizacaoSituacao.DESCRICAO);
			
			Collection<FiscalizacaoSituacao> colecaoFiscalizacaoSituacao =  
				this.getFachada().pesquisar(
					filtroFiscalizacaoSituacao,
					FiscalizacaoSituacao.class.getName());
			
			if(colecaoFiscalizacaoSituacao != null && !colecaoFiscalizacaoSituacao.isEmpty()){
				Iterator iteraFiscalizacaoSituacao = colecaoFiscalizacaoSituacao.iterator();
				
				while (iteraFiscalizacaoSituacao.hasNext()) {
					FiscalizacaoSituacao fiscalizacaoSituacao = (FiscalizacaoSituacao) iteraFiscalizacaoSituacao.next();

					AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = fiscalizacaoSituacao.getAtendimentoMotivoEncerramento();
					colecaoAtendimentoMotivoEncerramento.add(fiscalizacaoSituacao.getAtendimentoMotivoEncerramento());

					if(colecaoFiscalizacaoSituacaoSelecionada.size() == 1){
						form.setAtendimentoMotivoEncerramento(""+atendimentoMotivoEncerramento.getId());
						bloqueiaMotivoEncerramento = true;
					}
					
					
				}
			}
		}
		
		if(colecaoAtendimentoMotivoEncerramento == null || colecaoAtendimentoMotivoEncerramento.isEmpty()){

			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = 
				new FiltroAtendimentoMotivoEncerramento();

			filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);

			filtroAtendimentoMotivoEncerramento.adicionarParametro(
				new ParametroSimples(
					FiltroAtendimentoMotivoEncerramento.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Verifica se os dados foram informados da tabela 
			// existem e joga numa colecao
			colecaoAtendimentoMotivoEncerramento = 
				this.getFachada().pesquisar(
					filtroAtendimentoMotivoEncerramento,
					AtendimentoMotivoEncerramento.class.getName());
			
		}

		if (colecaoAtendimentoMotivoEncerramento == null || colecaoAtendimentoMotivoEncerramento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", 
				null,
				"Tabela Atendimento Motivo Encerramento");
		}
		
		httpServletRequest.setAttribute("colecaoAtendimentoMotivoEncerramento",colecaoAtendimentoMotivoEncerramento);
		httpServletRequest.setAttribute("bloqueiaMotivoEncerramento",bloqueiaMotivoEncerramento);

		
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 01/09/2006
	 */
	private void pesquisarCliente(
			InformarRetornoOSFiscalizacaoActionForm informarRetornoOSFiscalizacaoActionForm) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID,
				informarRetornoOSFiscalizacaoActionForm.getMatriculaImovel()));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			informarRetornoOSFiscalizacaoActionForm.setClienteUsuario(cliente
					.getNome());
			informarRetornoOSFiscalizacaoActionForm
					.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}
	
	/**
	 * Método auxiliar responsável por carregar os campos referentes
	 * aos dados de ligação de esgoto.
	 * 
	 * @author Diogo Peixoto
	 * @since 09/08/2011
	 * 
	 * @param httpServletRequest
	 * @param form
	 * @param sessao
	 */
	private void pesquisarIndicacaoNotificacaoEsgoto(HttpServletRequest httpServletRequest,
		InformarRetornoOSFiscalizacaoActionForm form, HttpSession sessao){

		Collection<LigacaoEsgotoDiametro> colecaoLigacaoEsgotoDiametro;
				
		FiltroLigacaoEsgotoDiametro filtro = new FiltroLigacaoEsgotoDiametro(FiltroLigacaoEsgotoDiametro.DESCRICAO);
		filtro.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoDiametro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		colecaoLigacaoEsgotoDiametro = Fachada.getInstancia().pesquisar(filtro, LigacaoEsgotoDiametro.class.getName());

		/*
		 * [FS0017] - Verificar existência de dados
		 * 
		 * Caso exista ligação de esgoto diâmetro ativa.
		 * Caso contrátio exibir uma mensagem na tela.
		 */
		if(!Util.isVazioOrNulo(colecaoLigacaoEsgotoDiametro)){
			
			FiltroMaterialLigacao filtroMaterial = new FiltroMaterialLigacao(FiltroMaterialLigacao.DESCRICAO);
			filtroMaterial.adicionarParametro(new ParametroSimples(
					FiltroMaterialLigacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<LigacaoEsgotoMaterial> colecaoMaterialEsgoto = Fachada.getInstancia().pesquisar(
					filtroMaterial, LigacaoEsgotoMaterial.class.getName());

			/*
			 * [FS0017] - Verificar existência de dados
			 * 
			 * Caso exista ligação esgoto material ativa.
			 * Caso contrátio exibir uma mensagem na tela.
			 */
			if(!Util.isVazioOrNulo(colecaoMaterialEsgoto)){
				
				FiltroPerfilLigacao filtroPerfil = new FiltroPerfilLigacao(FiltroPerfilLigacao.DESCRICAO);
				filtroPerfil.adicionarParametro(new ParametroSimples(
						FiltroPerfilLigacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<LigacaoEsgotoPerfil> colecaoPerfilLigacaoEsgoto = Fachada.getInstancia().pesquisar(
						filtroPerfil, LigacaoEsgotoPerfil.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoPerfilLigacaoEsgoto)){
					sessao.setAttribute("colecaoLigacaoEsgotoDiametro", colecaoLigacaoEsgotoDiametro);
					sessao.setAttribute("colecaoMaterialEsgoto", colecaoMaterialEsgoto);
					sessao.setAttribute("colecaoPerfilLigacaoEsgoto", colecaoPerfilLigacaoEsgoto);
					
				}else{
					throw new ActionServletException(
							"atencao.atencao.atencao.entidade_sem_dados_para_selecao", "Ligação Esgoto Perfil");
				}
				
			}else{
				throw new ActionServletException(
						"atencao.atencao.atencao.entidade_sem_dados_para_selecao", "Ligação Esgoto Material");
			}
		}else{
			throw new ActionServletException("atencao.atencao.atencao.entidade_sem_dados_para_selecao", "Ligação Esgoto Diâmetro");
		}
		
		/*
		 * Caso a situação de fiscalização selecionada tenha indicação de notificação 
		 * de esgoto, o sistema deve deixar todas os campos relativos ao documento entregue
		 * desabilitados e notificação de esgoto selecionado. 
		 */
		httpServletRequest.setAttribute("notificacaoEsgoto", true);
		httpServletRequest.setAttribute("desabilitaEncerrarOS", true);
		httpServletRequest.setAttribute("desabilita", "notificacaoEsgoto");
		form.setIndicadorDocumentoEntregue("3");
		
		//Limpar os campos dos dados da ligação esgoto.
		form.setSituacaoLigacao("");
		form.setIndicadorEncerramentoOS("1");
		form.setDataLigacaoEsgoto("");
		form.setIndicadorLigacaoEsgoto("");
		form.setDiametroLigacaoEsgoto("");
		form.setMaterialLigacaoEsgoto("");
		form.setPerfilLigacaoEsgoto("");
		form.setPercentualColeta("");
	}

	

	/**
	 * Carrega coleção de motivo da não cobrança.
	 * 
	 * @author Leonardo Regis
	 * @date 16/09/2006
	 * 
	 * @param sessao
	 */
	private void getFiscalizacaoSituacao(HttpSession sessao, 
			InformarRetornoOSFiscalizacaoActionForm informarRetornoOSFiscalizacaoActionForm,
			HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();

		if (sessao.getAttribute("colecaoFiscalizacaoSituacao") == null
				|| !sessao.getAttribute("colecaoFiscalizacaoSituacao").equals("")) {
			
			Collection colecaoServicoNaoCobrancaMotivo = null;
			if(informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico() != null &&
					!informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico().equals("")){
				colecaoServicoNaoCobrancaMotivo = fachada.recuperaFiscalizacaoSituacao(
						new Integer(informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()));
			}else{
				
				FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
				filtroFiscalizacaoSituacao.setCampoOrderBy(FiltroFiscalizacaoSituacao.DESCRICAO);
				colecaoServicoNaoCobrancaMotivo = fachada.pesquisar(
						filtroFiscalizacaoSituacao, FiscalizacaoSituacao.class.getName());
			}
			
			Collection colecaoFinal = new ArrayList();
			

			if (colecaoServicoNaoCobrancaMotivo != null
					&& !colecaoServicoNaoCobrancaMotivo.isEmpty()) {

				Iterator iteratorColecaoServicoNaoCobrancaMotivo = colecaoServicoNaoCobrancaMotivo.iterator();
				FiscalizacaoSituacao fiscalizacaoSituacao = null;

				FiltroFiscalizacaoSituacaoServicoACobrar filtroFiscalizacaoSituacaoServicoACobrar = null;
				Collection colecaoFiscalizacaoSituacaoServicoACobrar = null;

				SituacaoEncontradaHelper situacaoEncontradaHelper = null;
//				String exibirAtualizarSituacaoLigacaoAguaEsgoto = ConstantesSistema.SIM.toString();
				
				while (iteratorColecaoServicoNaoCobrancaMotivo.hasNext()) {

					fiscalizacaoSituacao = (FiscalizacaoSituacao) iteratorColecaoServicoNaoCobrancaMotivo.next();

					situacaoEncontradaHelper = new SituacaoEncontradaHelper();
					situacaoEncontradaHelper.setFiscalizacaoSituacao(fiscalizacaoSituacao);

					filtroFiscalizacaoSituacaoServicoACobrar = new FiltroFiscalizacaoSituacaoServicoACobrar();

					filtroFiscalizacaoSituacaoServicoACobrar.adicionarParametro(new ParametroSimples(
						FiltroFiscalizacaoSituacaoServicoACobrar.ID_FISCALIZACAO_SITUACAO,
						fiscalizacaoSituacao.getId()));

					colecaoFiscalizacaoSituacaoServicoACobrar = fachada.pesquisar(
						filtroFiscalizacaoSituacaoServicoACobrar,
						FiscalizacaoSituacaoServicoACobrar.class.getName());

					if (colecaoFiscalizacaoSituacaoServicoACobrar != null
							&& !colecaoFiscalizacaoSituacaoServicoACobrar.isEmpty()) {

						situacaoEncontradaHelper.setGeracaoDebito(new Short("1"));
					} else {

						situacaoEncontradaHelper.setGeracaoDebito(new Short("2"));
					}

					colecaoFinal.add(situacaoEncontradaHelper);
					
				}
				
				sessao.setAttribute("colecaoFiscalizacaoSituacao",	colecaoFinal);

			} else {
				throw new ActionServletException("atencao.naocadastrado", null,	"Fiscalização Situação");
			}
		}
	}
	
	
	private void carregarDadosAutoInfracao(HttpSession sessao, HttpServletRequest httpServletRequest,
			InformarRetornoOSFiscalizacaoActionForm form, SistemaParametro sistemaParametro){
		
		
		if (sistemaParametro.getIndicadorControlaAutoInfracao() == ConstantesSistema.SIM.shortValue()){
			
			
			//SITUAÇÃO DO AUTO DE INFRAÇÃO
			FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();

			filtroAutoInfracaoSituacao.setCampoOrderBy(FiltroAutoInfracaoSituacao.DESCRICAO);

			filtroAutoInfracaoSituacao.adicionarParametro(new ParametroSimples(
			FiltroAutoInfracaoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<AutoInfracaoSituacao> colecaoAutoInfracaoSituacao = this.getFachada()
				.pesquisar(filtroAutoInfracaoSituacao, AutoInfracaoSituacao.class.getName());

			if (colecaoAutoInfracaoSituacao == null || colecaoAutoInfracaoSituacao.isEmpty()) {
					
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
							"Tabela Auto Infracao Situacao");
			}
			
			httpServletRequest.setAttribute("colecaoAutoInfracaoSituacao", colecaoAutoInfracaoSituacao);
			
			// FUNCIONÁRIO RESPONSÁVEL
			String pesquisarFuncionario = httpServletRequest.getParameter("pesquisarFuncionario");
			
			AutosInfracao autosInfracao = null;
			if(form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("")){
				autosInfracao = getFachada().
				pesquisarAutosInfracaoPorOS(new Integer(form.getIdOrdemServico()));

				if(autosInfracao != null){
					Collection colecaoAutosInfracaoDebitoACobrar = getFachada().
						pesquisaAutosInfracaoDebitoACobrar(autosInfracao.getId());
					
					if(colecaoAutosInfracaoDebitoACobrar != null && !colecaoAutosInfracaoDebitoACobrar.isEmpty()){
						httpServletRequest.setAttribute("existeDebitoACobrarAutoInfracao","1");
					}
					
				}
				
			}
			
			if(autosInfracao != null 
			&&( pesquisarFuncionario == null
			|| pesquisarFuncionario.equalsIgnoreCase(""))){
				
				if (autosInfracao.getFuncionario()!= null) {

					FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

					filtroFuncionario.adicionarParametro(new ParametroSimples(
							FiltroFuncionario.ID, autosInfracao.getFuncionario().getId()));

					Collection colecaoFuncionario = this.getFachada().pesquisar(
							filtroFuncionario, Funcionario.class.getName());

					if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {

						form.setIdFuncionarioResponsavel("");
						form.setNomeFuncionarioResponsavel("");

					} else {
						Funcionario funcionario = (Funcionario) Util
								.retonarObjetoDeColecao(colecaoFuncionario);

						form.setIdFuncionarioResponsavel(funcionario.getId().toString());
						form.setNomeFuncionarioResponsavel(funcionario.getNome());
					}
				} else {
					form.setIdFuncionarioResponsavel("");
					form.setNomeFuncionarioResponsavel("");
				}
				if(autosInfracao.getAutoInfracaoSituacao() != null && 
						form.getIdAutoInfracaoSituacao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
					form.setIdAutoInfracaoSituacao(autosInfracao.getAutoInfracaoSituacao().getId().toString());	
				}
				if(autosInfracao.getNumeroParcelasDebito() != null && form.getQuantidadeParcelas().equals("")){
					form.setQuantidadeParcelas(autosInfracao.getNumeroParcelasDebito().toString());
				}
				if(autosInfracao.getDataEmissao() != null && form.getDataEmissao().equals("")){
					form.setDataEmissao(Util.formatarData(autosInfracao.getDataEmissao()));
				}
				if(autosInfracao.getDataInicioRecurso() != null && form.getDataInicioRecurso() == null){
					form.setDataInicioRecurso(Util.formatarData(autosInfracao.getDataInicioRecurso()));
				}
				if(autosInfracao.getDataTerminoRecurso() != null && form.getDataTerminoRecurso() == null){
					form.setDataTerminoRecurso(Util.formatarData(autosInfracao.getDataTerminoRecurso()));
				}
				if(autosInfracao.getObservacao() != null && form.getObservacao().equals("")){
					form.setObservacao(autosInfracao.getObservacao());
				}
				
			}else{
				
				if (form.getIdFuncionarioResponsavel() != null
						&& !form.getIdFuncionarioResponsavel().equals("")
						&& pesquisarFuncionario != null
						&& !pesquisarFuncionario.equalsIgnoreCase("")) {

					FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

					filtroFuncionario.adicionarParametro(new ParametroSimples(
							FiltroFuncionario.ID, form.getIdFuncionarioResponsavel()));

					Collection colecaoFuncionario = this.getFachada().pesquisar(
							filtroFuncionario, Funcionario.class.getName());

					if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {

						form.setIdFuncionarioResponsavel("");
						form.setNomeFuncionarioResponsavel("Funcionário inexistente");

						httpServletRequest.setAttribute("corFuncionario","exception");
						httpServletRequest.setAttribute("nomeCampo","idFuncionarioResponsavel");

					} else {
						Funcionario funcionario = (Funcionario) Util
								.retonarObjetoDeColecao(colecaoFuncionario);

						form.setIdFuncionarioResponsavel(funcionario.getId().toString());
						form.setNomeFuncionarioResponsavel(funcionario.getNome());
					}
				} else {
					form.setIdFuncionarioResponsavel("");
					form.setNomeFuncionarioResponsavel("");
				}
				
				
			}
			
			

			
			verificaGeraDebito(form,sessao,httpServletRequest);
			httpServletRequest.setAttribute("icControlaAutosInfracao", ConstantesSistema.SIM.toString());
		
			habilitaIndicadores( httpServletRequest, form);
		}
		
		
		
	}
	
	public void verificarObrigatoriedade(
			InformarRetornoOSFiscalizacaoActionForm form, HttpSession sessao,
			HttpServletRequest httpServletRequest, SistemaParametro sistemaParametro) {

		if (form.getIdAutoInfracaoSituacao() != null
				&& !form.getIdAutoInfracaoSituacao().equals("-1")) {

			FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();

			filtroAutoInfracaoSituacao.adicionarParametro(new ParametroSimples(
					FiltroAutoInfracaoSituacao.ID, form
							.getIdAutoInfracaoSituacao()));

			Collection<AutoInfracaoSituacao> colecaoAutoInfracaoSituacao = this
					.getFachada().pesquisar(filtroAutoInfracaoSituacao,
							AutoInfracaoSituacao.class.getName());

			AutoInfracaoSituacao autoInfracaoSituacao = (AutoInfracaoSituacao) Util
					.retonarObjetoDeColecao(colecaoAutoInfracaoSituacao);

			if (!autoInfracaoSituacao.getIndicadorDataInicioRecurso().equals(
					AutoInfracaoSituacao.NULO)) {

				sessao.setAttribute("dataInicialRecurso", true);

			} else {

				sessao.removeAttribute("dataInicialRecurso");
			}

			if (!autoInfracaoSituacao.getIndicadorDataTerminoRecurso().equals(
					AutoInfracaoSituacao.NULO)) {

				sessao.setAttribute("dataTerminoRecurso", true);

			} else {

				sessao.removeAttribute("dataTerminoRecurso");
			}
			
			
			Collection colecaoFiscalizacaoSituacaoSelecionada = (Collection) 
				sessao.getAttribute("colecaoFiscalizacaoSituacaoSelecionada");
			
			String indicadorGerarDebito = null;
			if(colecaoFiscalizacaoSituacaoSelecionada != null && !colecaoFiscalizacaoSituacaoSelecionada.isEmpty()){
				Iterator iteratorFiscalizacaoSituacaoSelecionada = colecaoFiscalizacaoSituacaoSelecionada.iterator();
	    		while (iteratorFiscalizacaoSituacaoSelecionada.hasNext()) {
					SituacaoEncontradaHelper situacaoEncontradaHelper = (SituacaoEncontradaHelper) iteratorFiscalizacaoSituacaoSelecionada.next();
					
					if(situacaoEncontradaHelper.getGeracaoDebito() == 1){
						indicadorGerarDebito = "1";
					}
				}
			}

			if(indicadorGerarDebito != null && indicadorGerarDebito.equalsIgnoreCase("2") 
				&& autoInfracaoSituacao.getIndicadorGerarDebito().toString().equalsIgnoreCase("1")) {
    			
    			throw new ActionServletException("atencao.situacao_auto_infracao_incompativel_geracao_debito");
    		}

		} else {
			sessao.removeAttribute("dataInicialRecurso");
			sessao.removeAttribute("dataTerminoRecurso");
		}

	

		if (sistemaParametro.getIndicadorControlaAutoInfracao() == ConstantesSistema.SIM.shortValue()) {
			

			if (sessao.getAttribute("colecaoFiscalizacaoSituacaoSelecionada") != null) {
				if(form.getIndicadorAtualizacaoAutosInfracao() != null &&
					form.getIndicadorAtualizacaoAutosInfracao().equals(ConstantesSistema.NAO.toString())) {

					httpServletRequest.setAttribute("desabilita", "autoInfracao");

				} else {

					httpServletRequest.setAttribute("desabilita", "outros");
				}
			}
	
		}

		
	}
	public void verificaGeraDebito(InformarRetornoOSFiscalizacaoActionForm form, HttpSession sessao,
			HttpServletRequest httpServletRequest){
		
		if(form.getIdAutoInfracaoSituacao()!=null && !form.getIdAutoInfracaoSituacao().equals("")){
			FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();
			
			filtroAutoInfracaoSituacao.adicionarParametro(
					new ParametroSimples(FiltroAutoInfracaoSituacao.ID,form.getIdAutoInfracaoSituacao()));
			
			Collection colecaoAutoInfracaoSituacao = this.getFachada().pesquisar(filtroAutoInfracaoSituacao,
					AutoInfracaoSituacao.class.getName());
			
			if(colecaoAutoInfracaoSituacao!=null && !colecaoAutoInfracaoSituacao.isEmpty()){
			
			AutoInfracaoSituacao autoInfracaoSituacao = (AutoInfracaoSituacao) Util
				.retonarObjetoDeColecao(colecaoAutoInfracaoSituacao);
			
				if(autoInfracaoSituacao.getIndicadorGerarDebito()!=null &&
					!autoInfracaoSituacao.getIndicadorGerarDebito().equals("")){
					
					if(autoInfracaoSituacao.getIndicadorGerarDebito().compareTo(new Short("1"))==0){
						form.setIndicarGeracaoDebitoAutoInfracao("1");
						httpServletRequest.setAttribute("indicarGeracaoDebitoAutoInfracao","1");
					}else{
						form.setIndicarGeracaoDebitoAutoInfracao("2");
						httpServletRequest.setAttribute("indicarGeracaoDebitoAutoInfracao","2");
					}
				}
			}
		}

	}
	
	
    private void adicionarSituacaoEncontrada(
		InformarRetornoOSFiscalizacaoActionForm informarRetornoOSFiscalizacaoActionForm,
		HttpSession sessao,HttpServletRequest httpServletRequest){
    	
    	if(informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico() != null &&
    	   !informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico().trim().equals("")){
    		
    		if(informarRetornoOSFiscalizacaoActionForm.getSituacao() != null
    				 && !informarRetornoOSFiscalizacaoActionForm.getSituacao().equals("")
    				 && !informarRetornoOSFiscalizacaoActionForm.getSituacao().equals("" 
    					 + ConstantesSistema.NUMERO_NAO_INFORMADO)){
    	        	
    	    		Integer idFiscalizacaoSituacaoSelecionada = new Integer(informarRetornoOSFiscalizacaoActionForm.getSituacao());
    	        	
    	    		Collection<SituacaoEncontradaHelper> colecaoFiscalizacaoSituacaoSelecionada = null;
    	    		if (!Util.isVazioOrNulo((Collection<SituacaoEncontradaHelper>)sessao.getAttribute("colecaoFiscalizacaoSituacaoSelecionada"))) {
    	    			colecaoFiscalizacaoSituacaoSelecionada = (Collection<SituacaoEncontradaHelper>) 
    	    				sessao.getAttribute("colecaoFiscalizacaoSituacaoSelecionada");
    	    			
    	    			/*
    	    			 * Caso a coleção de fiscalização de situação possuir uma fiscalização,
    	    			 * verificar se a mesma possui um indicação de notificação de esgoto.
    	    			 * Se possuir não deve permitir que adicione mais de uma situação,
    	    			 * caso contrátio pode adicionar.
    	    			 * 
    	    			 * PS: Caso seja do tipo de notificação de esgoto, a ordem de serviço
    	    			 * só pode ter uma fiscalização situação. 
    	    			 */
    	    			SituacaoEncontradaHelper situacaoEncontradaHelper = 
    	    				(SituacaoEncontradaHelper )Util.retonarObjetoDeColecao(colecaoFiscalizacaoSituacaoSelecionada);
    	    			FiscalizacaoSituacao situacao = situacaoEncontradaHelper.getFiscalizacaoSituacao();
    	    			if(situacao.getIndicadorNotificacaoEsgoto() == 1){
    	    				throw new ActionServletException("atencao.validar_situacao_notificacao_esgoto");
    	    			}
    	    		} else {
    	    			colecaoFiscalizacaoSituacaoSelecionada = new ArrayList<SituacaoEncontradaHelper>();
    	    		}
    	        	
    	    		//cria o objeto selecionado
    				FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
    				filtroFiscalizacaoSituacao.adicionarParametro(new ParametroSimples(FiltroFiscalizacaoSituacao.ID,idFiscalizacaoSituacaoSelecionada));
    				filtroFiscalizacaoSituacao.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
    				Collection<FiscalizacaoSituacao> colecaoServicoNaoCobrancaMotivo = this.getFachada().pesquisar(
    						filtroFiscalizacaoSituacao, FiscalizacaoSituacao.class.getName());
    	    		
    				FiscalizacaoSituacao fiscalizacaoSituacaoSelecionada = (FiscalizacaoSituacao)
    					colecaoServicoNaoCobrancaMotivo.iterator().next();
    				
    				
    				//Validação se pode adicionar ou não a situação
    				
    				Object[] fiscalizacaoSituacaoAgua = null;
    		        
    		        Object[] fiscalizacaoSituacaoEsgoto = null;
    		        
    		        //LigacaoAguaSituacao do Imóvel
	    				Integer idLigacaoAguaSituacaoImovel = this.getFachada()
	    						.pesquisarIdLigacaoAguaSituacao(Util
	    								.converterStringParaInteger(informarRetornoOSFiscalizacaoActionForm
	    										.getMatriculaImovel()));
	
	    			// LigacaoEsgotoSituacao do Imóvel
	    				Integer idLigacaoEsgotoSituacaoImovel = this.getFachada()
	    						.pesquisarIdLigacaoEsgotoSituacao(Util
	    								.converterStringParaInteger(informarRetornoOSFiscalizacaoActionForm
	    										.getMatriculaImovel()));
    		        
    				if (fiscalizacaoSituacaoSelecionada != null &&
    						fiscalizacaoSituacaoSelecionada.getIndicadorAguaSituacao() == ConstantesSistema.SIM){
    					
    					fiscalizacaoSituacaoAgua = Fachada.getInstancia().pesquisarIdFiscalizacaoSituacaoAgua(
								idLigacaoAguaSituacaoImovel,fiscalizacaoSituacaoSelecionada.getId());
				
						if (fiscalizacaoSituacaoAgua == null) {
							
							FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
							filtroLigacaoAguaSituacao.adicionarParametro(
								new ParametroSimples(FiltroLigacaoAguaSituacao.ID, 
									idLigacaoAguaSituacaoImovel));
							
							Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = 
								Fachada.getInstancia().pesquisar(filtroLigacaoAguaSituacao, 
									LigacaoAguaSituacao.class.getName());
							
							LigacaoAguaSituacao ligacaoAguaSituacao = 
								(LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
							
							throw new ActionServletException("atencao.situacao.agua.incompativel",
							ligacaoAguaSituacao.getDescricao(),fiscalizacaoSituacaoSelecionada.getDescricaoFiscalizacaoSituacao());
							
					}
    					
    					
    				} else if (fiscalizacaoSituacaoSelecionada.getIndicadorEsgotoSituacao() == ConstantesSistema.SIM){
    					
    					fiscalizacaoSituacaoEsgoto = Fachada.getInstancia()
						.pesquisarIdFiscalizacaoSituacaoEsgoto(
								idLigacaoEsgotoSituacaoImovel,
								fiscalizacaoSituacaoSelecionada.getId());
    					
    					if (fiscalizacaoSituacaoEsgoto == null) {
    						
							FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
							filtroLigacaoEsgotoSituacao.adicionarParametro(
								new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, 
									idLigacaoEsgotoSituacaoImovel));
							
							Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = 
								Fachada.getInstancia().pesquisar(filtroLigacaoEsgotoSituacao, 
									LigacaoEsgotoSituacao.class.getName());
							
							LigacaoEsgotoSituacao ligacaoEsgotoSituacao = 
								(LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
							
							throw new ActionServletException("atencao.situacao.esgoto.incompativel",
									ligacaoEsgotoSituacao.getDescricao(),fiscalizacaoSituacaoSelecionada.getDescricaoFiscalizacaoSituacao());
							
						}
    					
    				}
    				
    				// Fim validação
    				
    				SituacaoEncontradaHelper situacaoEncontradaHelper = new SituacaoEncontradaHelper();
    				situacaoEncontradaHelper.setFiscalizacaoSituacao(fiscalizacaoSituacaoSelecionada);

    				FiltroFiscalizacaoSituacaoServicoACobrar filtroFiscalizacaoSituacaoServicoACobrar = 
    					new FiltroFiscalizacaoSituacaoServicoACobrar();

    				filtroFiscalizacaoSituacaoServicoACobrar.adicionarParametro(new ParametroSimples(
    					FiltroFiscalizacaoSituacaoServicoACobrar.ID_FISCALIZACAO_SITUACAO, fiscalizacaoSituacaoSelecionada.getId()));

    				Collection<FiscalizacaoSituacaoServicoACobrar> colecaoFiscalizacaoSituacaoServicoACobrar = this.getFachada().pesquisar(
    					filtroFiscalizacaoSituacaoServicoACobrar,
    					FiscalizacaoSituacaoServicoACobrar.class.getName());

    				if (colecaoFiscalizacaoSituacaoServicoACobrar != null
    						&& !colecaoFiscalizacaoSituacaoServicoACobrar.isEmpty()) {
    					situacaoEncontradaHelper.setGeracaoDebito(new Short("1"));
    					informarRetornoOSFiscalizacaoActionForm.setOpcaoGeracao("1");
    				} else {
    					situacaoEncontradaHelper.setGeracaoDebito(new Short("2"));
    				}
    	    		
    				//4.1.2. Data da Fiscalização
    				//(Data corrente ou Data da Fiscalização já existente para a ordem de serviço 
    				//(OSFS_DTFISCALIZACAOSITUACAO da tabela ORDEM_SERVICO_FISC_SIT 
    				//para ORSE_ID=ORSE_ID da tabela ORDEM_SERVICO)).
    				Date dataFiscalizacaoSituacao =  this.getFachada().recuperaDataFiscalizacaoSituacao(new Integer(
    						informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()),idFiscalizacaoSituacaoSelecionada);
    				if(dataFiscalizacaoSituacao == null){
    					dataFiscalizacaoSituacao = new Date();
    				}
    				situacaoEncontradaHelper.setDataFiscalizacao(dataFiscalizacaoSituacao);
    				
    	    		if (colecaoFiscalizacaoSituacaoSelecionada != null && !colecaoFiscalizacaoSituacaoSelecionada.isEmpty()){

    	    			Iterator<SituacaoEncontradaHelper> iterator = colecaoFiscalizacaoSituacaoSelecionada.iterator();
    	    			
    	    			while (iterator.hasNext()) {
    	    				SituacaoEncontradaHelper helper = (SituacaoEncontradaHelper)iterator.next();
    	    				FiscalizacaoSituacao fiscalizacaoSituacao = helper.getFiscalizacaoSituacao();
    	    				//FS0012 – Verificar situação já existente na lista
    	    	        	//Caso a Situação da Fiscalização já esteja na lista,
    	    	        	//exibir a mensagem “Esta Situação da Fiscalização já foi selecionada” 
    	    				if (idFiscalizacaoSituacaoSelecionada.equals(fiscalizacaoSituacao.getId())){
    	    					throw new ActionServletException("atencao.situacao.fiscalizacao.ja.selecionada");
    	    				}
    	    			}
    	    		}
    	    		
    	    		OrdemServico ordemServico = this.getFachada().recuperaOrdemServico(
							new Integer(informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()));
    	    		
    	    		if(fiscalizacaoSituacaoSelecionada.getIndicadorAguaSituacao() == 1){
    					
    					if(informarRetornoOSFiscalizacaoActionForm.getIndicadorAguaSituacao() == "1" ||
    					(ordemServico.getIndicadorAtualizaAgua() != null && ordemServico.getIndicadorAtualizaAgua().equals(ConstantesSistema.SIM))){
    						throw new ActionServletException("atencao.validar_situacao_ligacao_agua");
    					}
    				}
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorEsgotoSituacao() == 1){
    					
    					if(informarRetornoOSFiscalizacaoActionForm.getIndicadorEsgotoSituacao() == "1" ||
    					(ordemServico.getIndicadorAtualizaEsgoto() != null && ordemServico.getIndicadorAtualizaEsgoto().equals(ConstantesSistema.SIM))){
    						throw new ActionServletException("atencao.validar_situacao_ligacao_esgoto");
    					}	
    				}
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorLigacaoDataAtualiza() == 1){
    					
    					if(informarRetornoOSFiscalizacaoActionForm.getIndicadorLigacaoDataAtualiza() == "1"){
    						throw new ActionServletException("atencao.alterar_ligacao_imovel");
    					}	
    				}
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorHidrometroCapacidade() == 1){
    					
    					if(informarRetornoOSFiscalizacaoActionForm.getIndicadorHidrometroCapacidade() == "1"){
    						throw new ActionServletException("atencao.validar_capacidade_hidrometro_imovel");
    					}	
    				}
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorAtualizacaoAutosInfracao() == 1){

    					if(informarRetornoOSFiscalizacaoActionForm.getIndicadorAtualizacaoAutosInfracao() == "1"){
    						throw new ActionServletException("atencao.controle_autos_infracao");
    					}	
    				}
    				
    	    		if(fiscalizacaoSituacaoSelecionada.getIndicadorAguaSituacao() == 1){
						informarRetornoOSFiscalizacaoActionForm.setIndicadorAguaSituacao("1");
						informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("1");
    				}
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorEsgotoSituacao() == 1){
						informarRetornoOSFiscalizacaoActionForm.setIndicadorEsgotoSituacao("1");
						informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("1");
    				}
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorLigacaoDataAtualiza() == 1){
						informarRetornoOSFiscalizacaoActionForm.setIndicadorLigacaoDataAtualiza("1");
    				}
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorHidrometroCapacidade() == 1){
						informarRetornoOSFiscalizacaoActionForm.setIndicadorHidrometroCapacidade("1");
    				}
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorAtualizacaoAutosInfracao() == 1){
						informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizacaoAutosInfracao("1");
    				}
    	    		
    				if(situacaoEncontradaHelper.getGeracaoDebito() == 1){
    					informarRetornoOSFiscalizacaoActionForm.setHabilitaGeracaoDebito("1");
    				}
    				
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorOpcaoMedicao() == 1){
    					informarRetornoOSFiscalizacaoActionForm.setHabilitaTipoMedicao("1");
    					informarRetornoOSFiscalizacaoActionForm.setIndicadorTipoMedicao("1");
    				}
    				
    				/*
    				 * [FS0013] - Verificar seleção de situação
    				 * Caso a situação de fiscalização selecionada seja de notificação de esgoto,
    				 * não poderá mais haver nenhuma situação de fiscalização.
    				 */
    				if(fiscalizacaoSituacaoSelecionada.getIndicadorNotificacaoEsgoto() == 1){
    					if(colecaoFiscalizacaoSituacaoSelecionada.isEmpty()){
    						pesquisarIndicacaoNotificacaoEsgoto(httpServletRequest, informarRetornoOSFiscalizacaoActionForm, sessao);
    					}else{
    						throw new ActionServletException("atencao.validar_situacao_notificacao_esgoto");
    					}
    				}
    				
    				colecaoFiscalizacaoSituacaoSelecionada.add(situacaoEncontradaHelper);

    	    		//ordena a lista pela descrição da situação 
    	    		Collections.sort((List<SituacaoEncontradaHelper>) colecaoFiscalizacaoSituacaoSelecionada, new Comparator() {
    	    			public int compare(Object a, Object b) {
    	    			String descricaoFiscalizacaoSituacao1 = new String(((SituacaoEncontradaHelper) a).getFiscalizacaoSituacao().getDescricaoFiscalizacaoSituacao()) ;
    	    			String descricaoFiscalizacaoSituacao2 = new String(((SituacaoEncontradaHelper) b).getFiscalizacaoSituacao().getDescricaoFiscalizacaoSituacao()) ;
    	    			
    	    			return descricaoFiscalizacaoSituacao1.compareTo(descricaoFiscalizacaoSituacao2);
    	    			
    	    			}
    	    		});
    	    		
    	    		sessao.setAttribute("colecaoFiscalizacaoSituacaoSelecionada", colecaoFiscalizacaoSituacaoSelecionada);
    	    		
    	    		//retirar situação selecionada da coleção de situação encontrada
    	    		if (sessao.getAttribute("colecaoFiscalizacaoSituacao") != null
    	    				|| sessao.getAttribute("colecaoFiscalizacaoSituacao").equals("")) {
    	    			
    	    			Collection<SituacaoEncontradaHelper> colecaoFiscalizacaoSituacao = 
    	    				(Collection<SituacaoEncontradaHelper>)sessao.getAttribute("colecaoFiscalizacaoSituacao");
    	    			Iterator<SituacaoEncontradaHelper> iteratorFiscalizacaoSituacao = colecaoFiscalizacaoSituacao.iterator();

    	    			while (iteratorFiscalizacaoSituacao.hasNext()) {
    						SituacaoEncontradaHelper helper = (SituacaoEncontradaHelper) iteratorFiscalizacaoSituacao.next();
    						if(helper.getFiscalizacaoSituacao().getId().equals(idFiscalizacaoSituacaoSelecionada)){
    							iteratorFiscalizacaoSituacao.remove();
    							break;
    						}
    					}
    	    			sessao.setAttribute("colecaoFiscalizacaoSituacao", colecaoFiscalizacaoSituacao);
    	    		}
    	    	
    	    		habilitaIndicadores(httpServletRequest, informarRetornoOSFiscalizacaoActionForm); 
    	    		
    	        }else{
    	        	throw new ActionServletException("atencao.required", null, "Situação Encontrada");
    	        }
    	}else{
    		throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null, "Ordem de Serviço");
    	}
       
	}
	
	
    private void removerSituacaoSelecionada(
		InformarRetornoOSFiscalizacaoActionForm informarRetornoOSFiscalizacaoActionForm,
		HttpSession sessao, String idFiscalizacaoSituacaoRemover,
		HttpServletRequest httpServletRequest){
    	
    	if(idFiscalizacaoSituacaoRemover != null && !idFiscalizacaoSituacaoRemover.equals("")){
    		
			String habilitaGeracaoDebito = "2";
			String habilitaTipoMedicao = "2";
			String opcaoGeracao = "2";
			
    		Collection colecaoFiscalizacaoSituacao = null;
    		if (sessao.getAttribute("colecaoFiscalizacaoSituacao") != null) {
    			colecaoFiscalizacaoSituacao = (Collection) sessao.getAttribute("colecaoFiscalizacaoSituacao");
    		} else {
    			colecaoFiscalizacaoSituacao = new ArrayList();
    		}
    		
    		Collection colecaoFiscalizacaoSituacaoSelecionada =	(Collection)
    			sessao.getAttribute("colecaoFiscalizacaoSituacaoSelecionada");
    		
    		Iterator iteratorFiscalizacaoSituacaoSelecionada = colecaoFiscalizacaoSituacaoSelecionada.iterator();
    		
    		while (iteratorFiscalizacaoSituacaoSelecionada.hasNext()) {
				SituacaoEncontradaHelper helper = (SituacaoEncontradaHelper) 
					iteratorFiscalizacaoSituacaoSelecionada.next();
				
				FiscalizacaoSituacao fiscalizacaoSituacao = helper.getFiscalizacaoSituacao();
				
				OrdemServicoFiscSit ordemServicoFiscSit = this.getFachada().recuperaOrdemServicoFiscSit(
						new Integer(informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()),
						fiscalizacaoSituacao.getId());
				
				if(fiscalizacaoSituacao.getId().equals(new Integer(idFiscalizacaoSituacaoRemover))){
					
					//Caso o usuário desmarque uma situação de fiscalização 
					//anteriormente selecionada para a ordem de serviço 
					//(existe ocorrência na tabela ORDEM_SERVICO_FISC_SIT para ORSE_ID=ORSE_ID 
					//da tabela ORDEM_SERVICO e FZST_ID=Id da Situação da Fiscalização desmarcada):
					
					//Caso o usuário desmarque uma situação de 
					//fiscalização anteriormente selecionada para a ordem de serviço 
					if(ordemServicoFiscSit != null){
					
						OrdemServico ordemServico = this.getFachada().recuperaOrdemServico(
							new Integer(informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()));
						
						//Caso a situação da fiscalização desmarcada tenha indicação 
						//de validar a situação da ligação de água do imóvel fiscalizado 
						if(fiscalizacaoSituacao.getIndicadorAguaSituacao() == 1){
							
							//Caso a atualização da situação da ligação de água 
							//do imóvel já tenha sido realizada 
							if(ordemServico.getIndicadorAtualizaAgua() != null &&
								ordemServico.getIndicadorAtualizaAgua().equals(ConstantesSistema.SIM)){
								
								//exibir a mensagem “Não é possível desmarcar essa 
								//situação porque a situação da ligação de água do 
								//imóvel <<IMOV_ID da tabela ORDEM_SERVICO>> já foi alterada.” 
								throw new ActionServletException("atencao.situacao_agua_imovel_ja_alterada",
									null, ordemServico.getImovel().getId().toString());
								
							}
							
//							informarRetornoOSFiscalizacaoActionForm.setIndicadorAguaSituacao("2");
//							informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("2");
						}
						
						//Caso a situação da fiscalização desmarcada tenha indicação 
						//de validar a situação da ligação de esgoto do imóvel fiscalizado 
						if(fiscalizacaoSituacao.getIndicadorEsgotoSituacao() == 1){
						
							//Caso a atualização da situação da ligação de esgoto do imóvel já tenha sido realizada 
							if(ordemServico.getIndicadorAtualizaEsgoto() != null &&
									ordemServico.getIndicadorAtualizaEsgoto().equals(ConstantesSistema.SIM)){
								
								//exibir a mensagem “Não é possível desmarcar essa 
								//situação porque a situação da ligação de esgoto do 
								//imóvel <<IMOV_ID da tabela ORDEM_SERVICO>> já foi alterada.” 
								throw new ActionServletException("atencao.situacao_esgoto_imovel_ja_alterada",
										ordemServico.getImovel().getId().toString());
								
							}
//							informarRetornoOSFiscalizacaoActionForm.setIndicadorEsgotoSituacao("2");
//							informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("2");
                        }
						
//						pesquisar p ver se existe na base !!!
						if(ordemServicoFiscSit.getIndicadorDebito().equals(ConstantesSistema.SIM)){
							//Exibir a mensagem “Não é possível desmarcar essa situação
							//porque já houve a geração do débito a cobrar para essa situação.”.
							throw new ActionServletException("atencao.nao_e_possivel_desmarcar_debito_gerado");
						}	
						
						
						//Caso a situação da fiscalização desmarcada tenha 
						//indicação do controle dos autos de infração 
						if(fiscalizacaoSituacao.getIndicadorAtualizacaoAutosInfracao() == 1){
							
							//Caso já existam dados do auto de infração 
							//exibir a mensagem “Não é possível desmarcar essa situação 
							//porque os dados do auto de infração já foram informados.” 
							if(this.getFachada().verificarExistenciaAutosInfracaoPorOS(ordemServico.getId())){
								throw new ActionServletException("atencao.auto_infracao_ja_informada");
							}else{
								informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizacaoAutosInfracao("2");
							}
						
						}
						
					}
					
					colecaoFiscalizacaoSituacao.add(helper);
					//ordena a lista pela descrição da situação 
		    		Collections.sort((List) colecaoFiscalizacaoSituacao, new Comparator() {
		    			public int compare(Object a, Object b) {
		    			String descricaoFiscalizacaoSituacao1 = new String(((SituacaoEncontradaHelper) a).getFiscalizacaoSituacao().getDescricaoFiscalizacaoSituacao()) ;
		    			String descricaoFiscalizacaoSituacao2 = new String(((SituacaoEncontradaHelper) b).getFiscalizacaoSituacao().getDescricaoFiscalizacaoSituacao()) ;
		    			
		    			return descricaoFiscalizacaoSituacao1.compareTo(descricaoFiscalizacaoSituacao2);
		    			
		    			}
		    		});
					
					if(fiscalizacaoSituacao.getIndicadorAguaSituacao() == 1){
						informarRetornoOSFiscalizacaoActionForm.setIndicadorAguaSituacao("2");
						informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("2");
					}
					if(fiscalizacaoSituacao.getIndicadorEsgotoSituacao() == 1){
						informarRetornoOSFiscalizacaoActionForm.setIndicadorEsgotoSituacao("2");
						informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("2");
					}
    				if(fiscalizacaoSituacao.getIndicadorLigacaoDataAtualiza() == 1){
    					informarRetornoOSFiscalizacaoActionForm.setIndicadorLigacaoDataAtualiza("2");
    				}
    				if(fiscalizacaoSituacao.getIndicadorHidrometroCapacidade() == 1){
    					informarRetornoOSFiscalizacaoActionForm.setIndicadorHidrometroCapacidade("2");
    				}
    				if(fiscalizacaoSituacao.getIndicadorAtualizacaoAutosInfracao() == 1){
    					informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizacaoAutosInfracao("2");
    					definirSelecaoDocumentoEntregue(informarRetornoOSFiscalizacaoActionForm, httpServletRequest);
    				}
    				
    				if(fiscalizacaoSituacao.getIndicadorNotificacaoEsgoto() == 1){
						informarRetornoOSFiscalizacaoActionForm.setIndicadorDocumentoEntregue("");
						/*
						 * Remove o atributo da coleção de esgoto diâmetro. Para validar a seleção a
						 * situação de fiscalização com indicação de esgoto. A validação é feita através
						 * deste atributo da sessão. (Caso o atributo seja null então não existe uma ligação
						 * de fiscalização com indicação de esgoto. Caso contrário já existe e deve exibir
						 * uma mensagem de erro).
						 */
						sessao.removeAttribute("colecaoLigacaoEsgotoDiametro");
    				}
					iteratorFiscalizacaoSituacaoSelecionada.remove();
				}else{
					
    				if(helper.getGeracaoDebito() == 1){
    					opcaoGeracao = "1";
    					if(ordemServicoFiscSit != null){
    						if(ordemServicoFiscSit.getIndicadorDebito().equals(ConstantesSistema.NAO)){
    							habilitaGeracaoDebito = "1";
    						}
    					}else{
    						habilitaGeracaoDebito = "1";
    					}
    				}
    				
    				if(fiscalizacaoSituacao.getIndicadorOpcaoMedicao() == 1 && ordemServicoFiscSit == null){
    					habilitaTipoMedicao = "1";
    				}
				}
			}
    		
    		informarRetornoOSFiscalizacaoActionForm.setHabilitaGeracaoDebito(habilitaGeracaoDebito);
    		informarRetornoOSFiscalizacaoActionForm.setHabilitaTipoMedicao(habilitaTipoMedicao);
    		informarRetornoOSFiscalizacaoActionForm.setOpcaoGeracao(opcaoGeracao);
    		
    		habilitaIndicadores(httpServletRequest, informarRetornoOSFiscalizacaoActionForm); 
    		sessao.setAttribute("colecaoFiscalizacaoSituacao", colecaoFiscalizacaoSituacao);
    		sessao.setAttribute("colecaoFiscalizacaoSituacaoSelecionada", colecaoFiscalizacaoSituacaoSelecionada);
    	}
    	
	}
    
    /**
	 * @author Vivianne Sousa
	 * @date 03/08/2010
	 * 
	 * @param sessao
	 */
	private void getFiscalizacaoSituacaoSelecionada(HttpSession sessao, 
			InformarRetornoOSFiscalizacaoActionForm informarRetornoOSFiscalizacaoActionForm,
			HttpServletRequest httpServletRequest) {
		
		Collection colecaoFiscalizacaoSituacaoSelecionada = this.getFachada().
				recuperaFiscalizacaoSituacaoSelecionada(new Integer(
				informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()));
		
		String habilitaGeracaoDebito = "2";
		if(colecaoFiscalizacaoSituacaoSelecionada != null &&
				!colecaoFiscalizacaoSituacaoSelecionada.isEmpty()){
			
			Iterator iterFiscalizacaoSituacaoSelecionada = colecaoFiscalizacaoSituacaoSelecionada.iterator();
			
			FiscalizacaoSituacao fiscalizacaoSituacao = null;

			FiltroFiscalizacaoSituacaoServicoACobrar filtroFiscalizacaoSituacaoServicoACobrar = null;
			Collection colecaoFiscalizacaoSituacaoServicoACobrar = null;
			Collection colecaoFinal = new ArrayList();
			SituacaoEncontradaHelper situacaoEncontradaHelper = null;
			
			while (iterFiscalizacaoSituacaoSelecionada.hasNext()) {
				fiscalizacaoSituacao = (FiscalizacaoSituacao) iterFiscalizacaoSituacaoSelecionada.next();

				situacaoEncontradaHelper = new SituacaoEncontradaHelper();
				situacaoEncontradaHelper.setFiscalizacaoSituacao(fiscalizacaoSituacao);

				filtroFiscalizacaoSituacaoServicoACobrar = new FiltroFiscalizacaoSituacaoServicoACobrar();

				filtroFiscalizacaoSituacaoServicoACobrar.adicionarParametro(new ParametroSimples(
					FiltroFiscalizacaoSituacaoServicoACobrar.ID_FISCALIZACAO_SITUACAO,
					fiscalizacaoSituacao.getId()));

				colecaoFiscalizacaoSituacaoServicoACobrar = this.getFachada().pesquisar(
					filtroFiscalizacaoSituacaoServicoACobrar,
					FiscalizacaoSituacaoServicoACobrar.class.getName());

				if (colecaoFiscalizacaoSituacaoServicoACobrar != null
						&& !colecaoFiscalizacaoSituacaoServicoACobrar.isEmpty()) {

					situacaoEncontradaHelper.setGeracaoDebito(new Short("1"));
					informarRetornoOSFiscalizacaoActionForm.setOpcaoGeracao("1");
				} else {

					situacaoEncontradaHelper.setGeracaoDebito(new Short("2"));
				}
				
				OrdemServicoFiscSit  ordemServicoFiscSit =  this.getFachada().recuperaOrdemServicoFiscSit(new Integer(
						informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()),fiscalizacaoSituacao.getId());
				
				Date dataFiscalizacaoSituacao  = ordemServicoFiscSit.getDataFiscalizacaoSituacao();
				if(dataFiscalizacaoSituacao == null){
					dataFiscalizacaoSituacao = new Date();
				}
				situacaoEncontradaHelper.setDataFiscalizacao(dataFiscalizacaoSituacao);
				
				if(situacaoEncontradaHelper.getGeracaoDebito() == 1){
					if(ordemServicoFiscSit != null){
						situacaoEncontradaHelper.setIndicadorDebitoOrdemServicoFiscSit(ordemServicoFiscSit.getIndicadorDebito());
						if(ordemServicoFiscSit.getIndicadorDebito().equals(ConstantesSistema.NAO)){
							habilitaGeracaoDebito = "1";
						}
					}else{
						habilitaGeracaoDebito = "1";
					}
				}
				
				colecaoFinal.add(situacaoEncontradaHelper);
				

				OrdemServico ordemServico = getFachada().recuperaOrdemServico(new Integer(
						informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()));
				
				if(fiscalizacaoSituacao.getIndicadorAguaSituacao() == 1 
						&& ordemServico.getIndicadorAtualizaAgua().equals(ConstantesSistema.NAO)){
					informarRetornoOSFiscalizacaoActionForm.setIndicadorAguaSituacao("1");
					informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("1");
				}
				if(fiscalizacaoSituacao.getIndicadorEsgotoSituacao() == 1
						&& ordemServico.getIndicadorAtualizaEsgoto().equals(ConstantesSistema.NAO)){
					informarRetornoOSFiscalizacaoActionForm.setIndicadorEsgotoSituacao("1");
					informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("1");
				}
				if(fiscalizacaoSituacao.getIndicadorLigacaoDataAtualiza() == 1){
					informarRetornoOSFiscalizacaoActionForm.setIndicadorLigacaoDataAtualiza("1");
				}
				if(fiscalizacaoSituacao.getIndicadorHidrometroCapacidade() == 1){
					informarRetornoOSFiscalizacaoActionForm.setIndicadorHidrometroCapacidade("1");
				}
				if(fiscalizacaoSituacao.getIndicadorAtualizacaoAutosInfracao() == 1){
   					informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizacaoAutosInfracao("1");
				}
				
				
//				if(fiscalizacaoSituacao.getIndicadorOpcaoMedicao() == 1){
//					informarRetornoOSFiscalizacaoActionForm.setHabilitaTipoMedicao("1");
//				}
//				
				
			}
			
			informarRetornoOSFiscalizacaoActionForm.setHabilitaGeracaoDebito(habilitaGeracaoDebito);
			sessao.setAttribute("colecaoFiscalizacaoSituacaoSelecionada", colecaoFinal);
			habilitaIndicadores(httpServletRequest, informarRetornoOSFiscalizacaoActionForm); 
		}
		
		
	}
	
	
    /**
	 * @author Vivianne Sousa
	 * @date 03/08/2010
	 * 
	 * @param sessao
	 */
	private void habilitaIndicadores(HttpServletRequest httpServletRequest,
			InformarRetornoOSFiscalizacaoActionForm informarRetornoOSFiscalizacaoActionForm) {
		
		String exibirAtualizarSituacaoLigacaoAguaEsgoto = null;
		
		if(informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico() != null &&
		!informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico().equals("")){
	
			if((informarRetornoOSFiscalizacaoActionForm.getIndicadorAguaSituacao() != null 
				&& informarRetornoOSFiscalizacaoActionForm.getIndicadorAguaSituacao().equals("1")) ||
				(informarRetornoOSFiscalizacaoActionForm.getIndicadorEsgotoSituacao() != null
			   && informarRetornoOSFiscalizacaoActionForm.getIndicadorEsgotoSituacao().equals("1"))){
				
				OrdemServico ordemServico = this.getFachada().recuperaOrdemServico(
					new Integer(informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()));
		
				if(ordemServico.getIndicadorAtualizaAgua() != null 
					&& ordemServico.getIndicadorAtualizaAgua().equals(ConstantesSistema.SIM)
					&& ordemServico.getIndicadorAtualizaEsgoto() != null
				    && ordemServico.getIndicadorAtualizaEsgoto().equals(ConstantesSistema.SIM)){
					
					exibirAtualizarSituacaoLigacaoAguaEsgoto = ConstantesSistema.NAO.toString();
					informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("2");
				}else{
					exibirAtualizarSituacaoLigacaoAguaEsgoto = ConstantesSistema.SIM.toString();
				}
			}else{
				exibirAtualizarSituacaoLigacaoAguaEsgoto = ConstantesSistema.NAO.toString();
				informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("2");
			}
			
		}else{
			exibirAtualizarSituacaoLigacaoAguaEsgoto = ConstantesSistema.NAO.toString();
			informarRetornoOSFiscalizacaoActionForm.setIndicadorAtualizarSituacaoLigacaoAguaEsgoto("2");
		}
		
		httpServletRequest.setAttribute("exibirAtualizarSituacaoLigacaoAguaEsgoto", exibirAtualizarSituacaoLigacaoAguaEsgoto);
		
		if(informarRetornoOSFiscalizacaoActionForm.getIndicadorAtualizacaoAutosInfracao() != null &&
				informarRetornoOSFiscalizacaoActionForm.getIndicadorAtualizacaoAutosInfracao().equals("1")){
			SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
			if (sistemaParametro.getIndicadorControlaAutoInfracao() == ConstantesSistema.SIM.shortValue()) {
				informarRetornoOSFiscalizacaoActionForm.setIndicadorDocumentoEntregue("2");
			}
		}
		

		httpServletRequest.setAttribute("habilitaTipoMedicao", informarRetornoOSFiscalizacaoActionForm.getHabilitaTipoMedicao());
		httpServletRequest.setAttribute("habilitaGeracaoDebito", informarRetornoOSFiscalizacaoActionForm.getHabilitaGeracaoDebito());
		
	}

	
	 /**
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @date 09/08/2010, 08/08/2011 
	 */
	private void definirSelecaoDocumentoEntregue(InformarRetornoOSFiscalizacaoActionForm form, HttpServletRequest request) {
		
		Collection colecaoOrdemServicoFiscSit = this.getFachada().recuperaOrdemServicoFiscSit(
				new Integer(form.getIdOrdemServico()));
		
		/*
		 * Caso a ordem de serviço já tenha situação de fiscalização informada
		 */
		if(colecaoOrdemServicoFiscSit != null && !colecaoOrdemServicoFiscSit.isEmpty()){
			
			String codigoTipoRecebimento = form.getCodigoTipoRecebimentoOS();
			
			if(codigoTipoRecebimento != null &&	(codigoTipoRecebimento.equals("1") || codigoTipoRecebimento.equals("2"))){
				form.setIndicadorDocumentoEntregue(codigoTipoRecebimento.toString());
			}else{
				form.setIndicadorDocumentoEntregue("4");
			}
			request.setAttribute("desabilitaNotificacaoEsgoto", true);
		}else{
			form.setIndicadorDocumentoEntregue("");
		}
		
	}
}