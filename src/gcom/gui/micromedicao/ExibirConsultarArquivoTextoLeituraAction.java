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
package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroServicoTipoCelular;
import gcom.micromedicao.FiltroSituacaoTransmissaoLeitura;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirConsultarArquivoTextoLeituraAction extends GcomAction {

	/**
	 * 
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

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = this.getSistemaParametro();

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarArquivoTextoLeituraActionForm consultarArquivoTextoLeituraActionForm = (ConsultarArquivoTextoLeituraActionForm) actionForm;

		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = new ArquivoTextoRoteiroEmpresa();

		Collection colecaoLeiturista = new ArrayList();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		if (httpServletRequest.getParameter("menu") != null) {

			consultarArquivoTextoLeituraActionForm.setEmpresaID(""
					+ usuarioLogado.getEmpresa().getId());
			consultarArquivoTextoLeituraActionForm.setGrupoFaturamentoID("");
			consultarArquivoTextoLeituraActionForm.setMesAno("");
			consultarArquivoTextoLeituraActionForm.setSituaTransmLeitura("");

		} else {

			if (httpServletRequest.getParameter("paginacao") == null
					&& sessao.getAttribute("filtrar") == null) {

				String atualizar = httpServletRequest.getParameter("atualizar");

				if (atualizar != null && !atualizar.equals("")) {
					sessao.setAttribute("atualizar", atualizar);
				} else {
					sessao.removeAttribute("atualizar");
				}

			}
		}

		sessao.removeAttribute("permissao");
		if (usuarioLogado.getEmpresa().getIndicadorEmpresaPrincipal().equals(
				new Short("1"))) {
			sessao.setAttribute("permissao", "1");
		} else {
			sessao.setAttribute("permissao", "2");
		}

		sessao.removeAttribute("equipeAtualizar");

		// Parte que passa as coleções da Empresa necessárias no jsp
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.adicionarParametro(
				new ParametroSimples(FiltroEmpresa.INDICADOR_LEITURA,
						ConstantesSistema.SIM));
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName());

		if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Empresa");
		}

		// Parte que passa as coleções da Grupo de Faturamento necessárias no
		// jsp
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoFaturamentoGrupo != null
				&& !colecaoFaturamentoGrupo.isEmpty()) {
			sessao.setAttribute("colecaoFaturamentoGrupo",
					colecaoFaturamentoGrupo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo de Faturamento");
		}
		
		//Parte que realiza a pesquisa pelos SERVICO_TIPO_CELULAR cujo indicadores de uso sejam 1 e colocar no JSP
		FiltroServicoTipoCelular filtroServicoTipoCelular = new FiltroServicoTipoCelular();
		filtroServicoTipoCelular.adicionarParametro(new ParametroSimples(FiltroServicoTipoCelular.INDICADOR_USO,
				                                                         ConstantesSistema.INDICADOR_USO_ATIVO));
		
	    Collection colecaoServicoTipoCelular = fachada.pesquisar(filtroServicoTipoCelular,ServicoTipoCelular.class.getName());
	    
	    if(colecaoServicoTipoCelular != null && !colecaoServicoTipoCelular.isEmpty()){
	    	sessao.setAttribute("colecaoServicoTipoCelular",colecaoServicoTipoCelular);
	    	
	    	if(consultarArquivoTextoLeituraActionForm.getServicoTipoCelular() == null ||
	    		consultarArquivoTextoLeituraActionForm.getServicoTipoCelular().equals("") ||
	    		consultarArquivoTextoLeituraActionForm.getServicoTipoCelular().equals("-1")){
	    		
	    		if(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("COMPESA")){
	    			consultarArquivoTextoLeituraActionForm.setServicoTipoCelular("2");
	    		}else{
	    			consultarArquivoTextoLeituraActionForm.setServicoTipoCelular("1");
	    		}
	    		/*sessao.setAttribute("servicoTipoCelular","1");*/
	    	}
	    	
	    }
	    
	    if(sessao.getAttribute("colecaoSituacaoTransmissaoLeitur")==null){
	    	FiltroSituacaoTransmissaoLeitura filtroSituacaoTransmissaoLeitura = 
	    		new FiltroSituacaoTransmissaoLeitura();
	    	
	    	filtroSituacaoTransmissaoLeitura.setCampoOrderBy(FiltroSituacaoTransmissaoLeitura.ID);
	    	
	    	filtroSituacaoTransmissaoLeitura.adicionarParametro(
	    			new ParametroSimples(FiltroSituacaoTransmissaoLeitura.INDICADOR_USO,
	    					ConstantesSistema.INDICADOR_USO_ATIVO));
	    	
	    	Collection colecaoSituacaoTransmissaoLeitura = fachada.pesquisar(
	    			filtroSituacaoTransmissaoLeitura, SituacaoTransmissaoLeitura.class.getName());
	    	
	    	if(!Util.isVazioOrNulo(colecaoSituacaoTransmissaoLeitura)){
	    		sessao.setAttribute("colecaoSituacaoTransmissaoLeitura",
	    				colecaoSituacaoTransmissaoLeitura);
	    	}else{
	    		sessao.removeAttribute("colecaoSituacaoTransmissaoLeitura");
	    	}
	    }	

		// Leiturista da Empresa
		if (consultarArquivoTextoLeituraActionForm.getEmpresaID() != null
				&& !consultarArquivoTextoLeituraActionForm.getEmpresaID()
						.equals("-1")
				&& !consultarArquivoTextoLeituraActionForm.getEmpresaID()
						.equals("")) {

			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(
					FiltroLeiturista.ID);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.EMPRESA_ID,
					consultarArquivoTextoLeituraActionForm.getEmpresaID()));
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			
			filtroLeiturista.setCampoOrderBy(FiltroLeiturista.CLIENTE_NOME);

			Collection colecao = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				Iterator it = colecao.iterator();
				while (it.hasNext()) {
					Leiturista leitu = (Leiturista) it.next();
					DadosLeiturista dadosLeiu = null;
					if (leitu.getFuncionario() != null) {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getFuncionario().getNome());
					} else {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getCliente().getNome());
						}
					
					colecaoLeiturista.add(dadosLeiu);
				}
			}

			Collections.sort((List) colecaoLeiturista, new Comparator() {
				public int compare(Object a, Object b) {
				String valor1 = ((DadosLeiturista)a).getDescricao();
				String valor2 = ((DadosLeiturista)b).getDescricao();

				return valor1.compareTo(valor2);

				}
				});

			
		}

		// Pega os codigos que o usuario digitou para a pesquisa direta de localidade.
		if (consultarArquivoTextoLeituraActionForm.getIdLocalidade() != null && !consultarArquivoTextoLeituraActionForm.getIdLocalidade().trim().equals("")) {
			
			pesquisarLocalidade( httpServletRequest, consultarArquivoTextoLeituraActionForm);
		}
		
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarArquivoTextoLeitura");

		sessao.setAttribute("arquivoTextoRoteiroEmpresaLiberar",
				arquivoTextoRoteiroEmpresa);
		sessao.setAttribute("achou","2");
		
		sessao.removeAttribute("idArquivoTextoRoteiroEmpresaDivisao");
		
		if ( fachada.verificarPermissaoEspecial( PermissaoEspecial.GERAR_ARQUIVO_TEXTO_IMOVEIS_NAO_ENVIADOS, usuarioLogado ) ){
			httpServletRequest.setAttribute( "permissaoRegerarArquivoImoveisNaoEnviados", "SIM" );
		} else {
			httpServletRequest.setAttribute( "permissaoRegerarArquivoImoveisNaoEnviados", "NAO" );
		}


		return retorno;

	}
	
	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, 
			ConsultarArquivoTextoLeituraActionForm form) {

		Fachada fachada = Fachada.getInstancia();
		
		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.ID, form.getIdLocalidade()));
		
		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
			filtroLocalidade, Localidade.class.getName());
		
		// Se nenhuma localidade for encontrada a mensagem é enviada para a
		// página
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			form.setIdLocalidade(""+localidade.getId());
			form.setNomeLocalidade(localidade.getDescricao());
			
		} else {
			form.setIdLocalidade("");
			form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
			httpServletRequest.setAttribute("localidadeInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
		}
	}
}
