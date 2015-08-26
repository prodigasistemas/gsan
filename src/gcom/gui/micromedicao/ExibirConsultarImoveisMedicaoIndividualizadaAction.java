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
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_INICIAL);
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TITULO_PERIMETRO_INICIAL);
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_FINAL);
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TITULO_PERIMETRO_FINAL);

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
