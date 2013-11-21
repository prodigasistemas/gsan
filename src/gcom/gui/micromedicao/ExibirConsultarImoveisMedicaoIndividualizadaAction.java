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

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Sávio Luiz
 * @created 28 de Junho de 2004
 */
public class ExibirConsultarImoveisMedicaoIndividualizadaAction extends
		GcomAction {
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

		ActionForward retorno = actionMapping.findForward("exibirConsultarImoveisMedicaoIndividualizada");

		ConsultarImoveisMedicaoIndividualizadaActionForm consultarImoveisMedicaoIndividualizadaActionForm = (ConsultarImoveisMedicaoIndividualizadaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String codigoImovel = consultarImoveisMedicaoIndividualizadaActionForm.getCodigoImovel();

		if (httpServletRequest.getParameter("objetoConsulta") != null
				&& !httpServletRequest.getParameter("objetoConsulta").equals("")) {

			if (codigoImovel != null && !codigoImovel.trim().equalsIgnoreCase("")) {

				if (codigoImovel != null && !codigoImovel.trim().equals("")) {

					// verifica a existencia do imovel
					Integer idImovel = fachada.verificarExistenciaImovel(new Integer(codigoImovel));

					if (idImovel == null) {
						consultarImoveisMedicaoIndividualizadaActionForm.setInscricaoImovel("MATRÍCULA INEXISTENTE");
						consultarImoveisMedicaoIndividualizadaActionForm.setCodigoImovel("");
						httpServletRequest.setAttribute("nomeCampo", "codigoImovel");
						httpServletRequest.setAttribute("corImovel", "exception");
					} else {
						httpServletRequest.setAttribute("corImovel", "true");
						FiltroImovel filtroImovel = new FiltroImovel();
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, codigoImovel));
						
						//Alterado por Raphael Rossiter em 29/01/2007
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
						/*filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");*/
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio.unidadeFederacao");
						/*filtroImovel.adicionarCaminhoParaCarregamentoEntidade("lote");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("subLote");*/
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");

						Collection imovelPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());

						Imovel imovelCondominio = (Imovel) Util.retonarObjetoDeColecao(imovelPesquisado);

						// [FS0001] - Verificar se o imóvel é um condomínio
						if (imovelCondominio.getIndicadorImovelCondominio() == null
								|| imovelCondominio.getIndicadorImovelCondominio().equals(Imovel.IMOVEL_NAO_CONDOMINIO)) {
							throw new ActionServletException("atencao.imovel.nao_condominio");
						}

						consultarImoveisMedicaoIndividualizadaActionForm.setInscricaoImovel(imovelCondominio.getInscricaoFormatada());
						
						if (imovelCondominio.getLigacaoAguaSituacao() != null) {
							consultarImoveisMedicaoIndividualizadaActionForm.setSituacaoAgua(imovelCondominio.getLigacaoAguaSituacao().getDescricao());
						}
						
						if (imovelCondominio.getLigacaoEsgotoSituacao() != null) {
							consultarImoveisMedicaoIndividualizadaActionForm.setSituacaoEsgoto(imovelCondominio.getLigacaoEsgotoSituacao().getDescricao());
						}

						boolean achou = false;

						String descricaoRateioTipo = null;
						if (imovelCondominio.getLigacaoAguaSituacao() != null) {
							if (imovelCondominio.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.CORTADO)
									|| imovelCondominio.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.LIGADO)) {

								descricaoRateioTipo = fachada.pesquisarDescricaoRateioTipoLigacaoAgua(imovelCondominio.getId());
								achou = true;
							}
						}

						if (!achou) {
							if (imovelCondominio.getLigacaoEsgotoSituacao() != null) {
								if (imovelCondominio.getLigacaoEsgotoSituacao().getId().equals(LigacaoEsgotoSituacao.LIGADO)) {
									descricaoRateioTipo = fachada.pesquisarDescricaoRateioTipoLigacaoEsgoto(imovelCondominio.getId());
								}
							}
						}
						
						if (descricaoRateioTipo != null) {
							consultarImoveisMedicaoIndividualizadaActionForm.setRateioTipo(descricaoRateioTipo);
						}

						consultarImoveisMedicaoIndividualizadaActionForm.setEndereco(imovelCondominio.getEnderecoFormatado());
						
						//Alterado por Raphael Rossiter em 29/01/2007
						FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel(FiltroClienteImovel.IMOVEL_INDICADOR_IMOVEL_AREA_COMUM);
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel");
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
						/*filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.subLote");*/
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
						filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
						filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_CONDOMINIO_ID, imovelCondominio.getId()));
						filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));

						Collection<ClienteImovel> imovelPesquisadoVinculados = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

						consultarImoveisMedicaoIndividualizadaActionForm.setQuantidadeDeImoveisVinculados("" + imovelPesquisadoVinculados.size());

						// httpServletRequest.setAttribute("colecaoImoveisASerVinculados", imovelPesquisadoVinculados);
						sessao.setAttribute("colecaoImoveisASerVinculados", imovelPesquisadoVinculados);
					}

				}
			}
		} else {
			sessao.removeAttribute("colecaoImoveisASerVinculados");
			consultarImoveisMedicaoIndividualizadaActionForm.setCodigoImovel(null);
			consultarImoveisMedicaoIndividualizadaActionForm.setEndereco(null);
			consultarImoveisMedicaoIndividualizadaActionForm.setInscricaoImovel(null);
			consultarImoveisMedicaoIndividualizadaActionForm.setSituacaoAgua(null);
			consultarImoveisMedicaoIndividualizadaActionForm.setSituacaoEsgoto(null);
			consultarImoveisMedicaoIndividualizadaActionForm.setRateioTipo(null);
			consultarImoveisMedicaoIndividualizadaActionForm.setQuantidadeDeImoveisVinculados(null);
		}
		
		return retorno;
	}
}