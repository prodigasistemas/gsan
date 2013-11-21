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

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFiltroHelper;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirResultadoPesquisaRegistroAtendimento");
		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm = (PesquisarRegistroAtendimentoActionForm) actionForm;
		boolean parametroInformado = false;
		
		RegistroAtendimento ra = new RegistroAtendimento();
		FiltrarRegistroAtendimentoHelper filtroRA = new FiltrarRegistroAtendimentoHelper();
		
		// Numero RA
		if (pesquisarRegistroAtendimentoActionForm.getNumeroRA() != null &&
				!pesquisarRegistroAtendimentoActionForm.getNumeroRA().equals("")) {
			ra.setId(new Integer(pesquisarRegistroAtendimentoActionForm.getNumeroRA()));
			parametroInformado = true;
		}
		
		//Numero Protocolo Atendimento
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = null;
		if (pesquisarRegistroAtendimentoActionForm.getNumeroProtocoloAtendimento() != null &&
			!pesquisarRegistroAtendimentoActionForm.getNumeroProtocoloAtendimento().equals("")) {
			
			registroAtendimentoSolicitante = new RegistroAtendimentoSolicitante();
			
			registroAtendimentoSolicitante.setNumeroProtocoloAtendimento(pesquisarRegistroAtendimentoActionForm
			.getNumeroProtocoloAtendimento());
			
			parametroInformado = true;
		}
		
		//Número Manual
		if (pesquisarRegistroAtendimentoActionForm.getNumeroRAManual() != null &&
				!pesquisarRegistroAtendimentoActionForm.getNumeroRAManual().equals("")) {
			
			//String[] arrayNumeroRAManual = pesquisarRegistroAtendimentoActionForm.getNumeroRAManual().split("-");
			String[] arrayNumeroRAManual = new String[2];
			arrayNumeroRAManual[0] = pesquisarRegistroAtendimentoActionForm.getNumeroRAManual().substring(0, pesquisarRegistroAtendimentoActionForm.getNumeroRAManual().length() - 1);
			arrayNumeroRAManual[1] = pesquisarRegistroAtendimentoActionForm.getNumeroRAManual().substring(pesquisarRegistroAtendimentoActionForm.getNumeroRAManual().length() - 1, pesquisarRegistroAtendimentoActionForm.getNumeroRAManual().length());
			
			Integer numeracao = new Integer(arrayNumeroRAManual[0]);
			Integer digitoModulo11 = new Integer(arrayNumeroRAManual[1]);
			
			//Caso o dígito verificador do número informado não bata com o dígito calculado com o módulo 11
			if (!digitoModulo11.equals(Util.obterDigitoVerificadorModulo11(Long.parseLong(numeracao.toString())))){
				throw new ActionServletException("atencao.numeracao_ra_manual_digito_invalido");
			}
			
			//ra.setManual(Util.obterNumeracaoRAManual(pesquisarRegistroAtendimentoActionForm.getNumeroRAManual()));
			ra.setManual(new Integer(pesquisarRegistroAtendimentoActionForm.getNumeroRAManual()));
			parametroInformado = true;
		}
		
		// Quantidade RA Reiteradas
		Integer qtdeRAReiteradasInicial = null;
		Integer qtdeRAReiteradasFinal = null;
		
		if (pesquisarRegistroAtendimentoActionForm.getQuantidadeRAReiteradasInicial() != null &&
				!pesquisarRegistroAtendimentoActionForm.getQuantidadeRAReiteradasInicial().equals("")) {
			qtdeRAReiteradasInicial = new Integer(pesquisarRegistroAtendimentoActionForm.getQuantidadeRAReiteradasInicial());
			qtdeRAReiteradasFinal = new Integer(pesquisarRegistroAtendimentoActionForm.getQuantidadeRAReiteradasFinal());
			parametroInformado = true;
		}
		
		// Imovel
		if (pesquisarRegistroAtendimentoActionForm.getMatriculaImovel() != null &&
				!pesquisarRegistroAtendimentoActionForm.getMatriculaImovel().equals("")) {
			Imovel imovel = new Imovel();
			imovel.setId(new Integer(pesquisarRegistroAtendimentoActionForm.getMatriculaImovel()));
			ra.setImovel(imovel);
			parametroInformado = true;
		}
		// Situação
		if (pesquisarRegistroAtendimentoActionForm.getSituacao() != null &&
				!pesquisarRegistroAtendimentoActionForm.getSituacao().equals("")) {
			ra.setCodigoSituacao(new Short(pesquisarRegistroAtendimentoActionForm.getSituacao()));		
			parametroInformado = true;
		}
		// Tipo Especificação
		Collection<Integer> colecaoSolicitacaoTipoEspecificacao = new ArrayList();
		if (pesquisarRegistroAtendimentoActionForm.getEspecificacao() != null &&
				pesquisarRegistroAtendimentoActionForm.getEspecificacao().length > 0) {
			String[] tipoSolicitacaoEspecificacao = pesquisarRegistroAtendimentoActionForm.getEspecificacao();
			for (int i = 0; i < tipoSolicitacaoEspecificacao.length; i++) {
				if (new Integer(tipoSolicitacaoEspecificacao[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecaoSolicitacaoTipoEspecificacao.add(new Integer(tipoSolicitacaoEspecificacao[i]));
					// passar a coleção de especificação por parâmetro			
					parametroInformado = true;
				}
			}
		}
        
        // Tipo Solicitação
        Collection<Integer> colecaoSolicitacao = new ArrayList();
        if (pesquisarRegistroAtendimentoActionForm.getTipoSolicitacao() != null &&
                pesquisarRegistroAtendimentoActionForm.getTipoSolicitacao().length > 0) {
            String[] tipoSolicitacao = pesquisarRegistroAtendimentoActionForm.getTipoSolicitacao();
            for (int i = 0; i < tipoSolicitacao.length; i++) {
                if (new Integer(tipoSolicitacao[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
                    colecaoSolicitacao.add(new Integer(tipoSolicitacao[i]));
                    // passar a coleção de especificação por parâmetro          
                    parametroInformado = true;
                }
            }
        }        
        
		// Data de Atendimento
		Date dataAtendimentoInicial = null;
		Date dataAtendimentoFinal = null;
		if (pesquisarRegistroAtendimentoActionForm.getPeriodoAtendimentoInicial() != null &&
				!pesquisarRegistroAtendimentoActionForm.getPeriodoAtendimentoInicial().equals("")) {
			
			dataAtendimentoInicial = Util.converteStringParaDate(pesquisarRegistroAtendimentoActionForm.getPeriodoAtendimentoInicial());
			dataAtendimentoInicial = Util.formatarDataInicial(dataAtendimentoInicial);
			
			dataAtendimentoFinal = null;
			if (pesquisarRegistroAtendimentoActionForm.getPeriodoAtendimentoFinal() != null &&
					!pesquisarRegistroAtendimentoActionForm.getPeriodoAtendimentoFinal().equals("")) {
				dataAtendimentoFinal = Util.converteStringParaDate(pesquisarRegistroAtendimentoActionForm.getPeriodoAtendimentoFinal());
				dataAtendimentoFinal = Util.adaptarDataFinalComparacaoBetween(dataAtendimentoFinal);
				
			} else {
				dataAtendimentoFinal = new Date();
				dataAtendimentoFinal = Util.formatarDataFinal(dataAtendimentoFinal);
			}
			
			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataAtendimentoInicial, dataAtendimentoFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}
			// passar as datas de atendimento por parâmetro
			parametroInformado = true;
			
		} else {

			if (pesquisarRegistroAtendimentoActionForm
					.getPeriodoAtendimentoFinal() != null
					&& !pesquisarRegistroAtendimentoActionForm
							.getPeriodoAtendimentoFinal().equals("")) {

				dataAtendimentoFinal = Util
						.converteStringParaDate(pesquisarRegistroAtendimentoActionForm
								.getPeriodoAtendimentoFinal());
				dataAtendimentoFinal = Util
						.formatarDataFinal(dataAtendimentoFinal);

				dataAtendimentoInicial = Util
						.converteStringParaDate("01/01/1900");
				dataAtendimentoInicial = Util
						.formatarDataInicial(dataAtendimentoInicial);

				// passar as datas de atendimento por parâmetro
				parametroInformado = true;
			}

		}
		
		// Data de Encerramento
		Date dataEncerramentoInicial = null;
		Date dataEncerramentoFinal = null;
		if (pesquisarRegistroAtendimentoActionForm.getPeriodoEncerramentoInicial() != null &&
				!pesquisarRegistroAtendimentoActionForm.getPeriodoEncerramentoInicial().equals("")){
			
			dataEncerramentoInicial = Util.converteStringParaDate(pesquisarRegistroAtendimentoActionForm.getPeriodoEncerramentoInicial());
			dataEncerramentoInicial = Util.formatarDataInicial(dataEncerramentoInicial);
			
			dataEncerramentoFinal = null;
			if (pesquisarRegistroAtendimentoActionForm.getPeriodoEncerramentoFinal() != null &&
					!pesquisarRegistroAtendimentoActionForm.getPeriodoEncerramentoFinal().equals("") ) {
				dataEncerramentoFinal = Util.converteStringParaDate(pesquisarRegistroAtendimentoActionForm.getPeriodoEncerramentoFinal());
				dataEncerramentoFinal = Util.adaptarDataFinalComparacaoBetween(dataEncerramentoFinal);
			} else {
				dataEncerramentoFinal = new Date();
				dataEncerramentoFinal = Util.formatarDataInicial(dataEncerramentoFinal);
			}
			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataEncerramentoInicial, dataEncerramentoFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}
			// passar as datas de encerramento por parâmetro
			parametroInformado = true;
		} else {

			if (pesquisarRegistroAtendimentoActionForm
					.getPeriodoEncerramentoFinal() != null
					&& !pesquisarRegistroAtendimentoActionForm
							.getPeriodoEncerramentoFinal().equals("")) {

				dataEncerramentoFinal = Util
						.converteStringParaDate(pesquisarRegistroAtendimentoActionForm
								.getPeriodoEncerramentoFinal());
				dataAtendimentoFinal = Util
						.formatarDataFinal(dataEncerramentoFinal);

				dataEncerramentoInicial = Util
						.converteStringParaDate("01/01/1900");
				dataEncerramentoInicial = Util
						.formatarDataInicial(dataAtendimentoInicial);

				// passar as datas de atendimento por parâmetro
				parametroInformado = true;
			}

		}
		
		//Registro Atendimento Unidade
		RegistroAtendimentoUnidade registroAtendimentoUnidade = null;
		Usuario usuario = null;
		if (pesquisarRegistroAtendimentoActionForm.getLoginUsuario() != null &&
			!pesquisarRegistroAtendimentoActionForm.getLoginUsuario().equals("")) {
			
			usuario = new Usuario();
			usuario.setLogin(pesquisarRegistroAtendimentoActionForm.getLoginUsuario());
			
			registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
			registroAtendimentoUnidade.setUsuario(usuario);
			
			// passar coleção de registro atendimento unidades(usuário) por parâmetro
			parametroInformado = true;
		}
		
		// Unidade de Atendimento
		UnidadeOrganizacional unidadeAtendimento = null;
		if (pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoId().equals("")) {
			unidadeAtendimento = new UnidadeOrganizacional();
			unidadeAtendimento.setId(new Integer(pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoId()));
			// passar coleção de unidades por parâmetro
			parametroInformado = true;
		}
		// Unidade de Atual
		UnidadeOrganizacional unidadeAtual = null;
		if (pesquisarRegistroAtendimentoActionForm.getUnidadeAtualId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getUnidadeAtualId().equals("")) {
			unidadeAtual = new UnidadeOrganizacional();
			unidadeAtual.setId(new Integer(pesquisarRegistroAtendimentoActionForm.getUnidadeAtualId()));
			// passar coleção de unidades por parâmetro			
			parametroInformado = true;
		}
		// Unidade de Atual
		UnidadeOrganizacional unidadeSuperior = null;
		if (pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorId().equals("")) {
			unidadeSuperior = new UnidadeOrganizacional();
			unidadeSuperior.setId(new Integer(pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorId()));
			// passar coleção de unidades por parâmetro			
			parametroInformado = true;
		}
		// Município
		String municipioId = null;
		if (pesquisarRegistroAtendimentoActionForm.getMunicipioId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
			municipioId = pesquisarRegistroAtendimentoActionForm.getMunicipioId(); 
			parametroInformado = true;
		}
		// Bairro
		String bairroId = null;
		String bairroCodigo = null;
		if (pesquisarRegistroAtendimentoActionForm.getBairroCodigo() != null &&
				!pesquisarRegistroAtendimentoActionForm.getBairroCodigo().equals("")) {
			
			//[FS009] Verificar informação do município
			if (pesquisarRegistroAtendimentoActionForm.getMunicipioId() == null ||
					pesquisarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
				
				throw new ActionServletException("atencao.filtrar_informar_municipio");
			}
			
			bairroCodigo = pesquisarRegistroAtendimentoActionForm.getBairroCodigo();
			
			if (pesquisarRegistroAtendimentoActionForm.getBairroId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getBairroId().equals("")){
				
				bairroId = pesquisarRegistroAtendimentoActionForm.getBairroId();
			}
			
			parametroInformado = true;
		}
		
		// Bairro Área
		if (new Integer(pesquisarRegistroAtendimentoActionForm.getAreaBairroId()).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			BairroArea bairroArea = new BairroArea();
			bairroArea.setId(new Integer(pesquisarRegistroAtendimentoActionForm.getAreaBairroId()));
			ra.setBairroArea(bairroArea);
			parametroInformado = true;
		}
		// Logradouro
		String logradouroId = null;
		if (pesquisarRegistroAtendimentoActionForm.getLogradouroId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getLogradouroId().equals("")) {
			logradouroId = pesquisarRegistroAtendimentoActionForm.getLogradouroId();
			parametroInformado = true;
		}
		
		// Filtra Registro Atendimento
		if (parametroInformado) {
			Collection<RegistroAtendimento> colecaoRegistroAtendimento = new ArrayList();
			
			filtroRA.setRegistroAtendimento(ra);
			filtroRA.setUnidadeAtendimento(unidadeAtendimento);
			filtroRA.setUnidadeAtual(unidadeAtual);
			filtroRA.setUnidadeSuperior(unidadeSuperior);
			filtroRA.setDataAtendimentoInicial(dataAtendimentoInicial);
			filtroRA.setDataAtendimentoFinal(dataAtendimentoFinal);
			filtroRA.setDataEncerramentoInicial(dataEncerramentoInicial);
			filtroRA.setDataEncerramentoFinal(dataEncerramentoFinal);
			filtroRA.setColecaoTipoSolicitacaoEspecificacao(colecaoSolicitacaoTipoEspecificacao);
            filtroRA.setColecaoTipoSolicitacao(colecaoSolicitacao);
			filtroRA.setMunicipioId(municipioId);
			filtroRA.setBairroId(bairroId);
			filtroRA.setBairroCodigo(bairroCodigo);
			filtroRA.setLogradouroId(logradouroId);
			filtroRA.setQuantidadeRAReiteradasIncial(qtdeRAReiteradasInicial);
			filtroRA.setQuantidadeRAReiteradasFinal(qtdeRAReiteradasFinal);
			
			filtroRA.setNumeroPagina(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
			
			filtroRA.setRegistroAtendimentoSolicitante(registroAtendimentoSolicitante);
			
			Integer totalRegistros = fachada.filtrarRegistroAtendimento(filtroRA).size();
		
			retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
			
			filtroRA.setNumeroPagina(((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")));
			colecaoRegistroAtendimento = fachada.filtrarRegistroAtendimento(filtroRA);

			if (colecaoRegistroAtendimento != null) {
				// Carrega Coleção
				Collection colecaoRAHelper = loadColecaoRAHelper(colecaoRegistroAtendimento);
				sessao.setAttribute("colecaoRAHelper", colecaoRAHelper);
			} else {
				// Nenhum resultado
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
		} else {
			throw new ActionServletException("atencao.filtrar_informar_um_filtro");
		}
		return retorno;
	}
	
	/**
	 * Carrega coleção de registro atendimento, situação abreviada e unidade atual no 
	 * objeto facilitador 
	 *
	 * @author Leonardo Regis
	 * @date 10/08/2006
	 *
	 * @param colecaoRegistroAtendimento
	 * @return
	 */
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
}