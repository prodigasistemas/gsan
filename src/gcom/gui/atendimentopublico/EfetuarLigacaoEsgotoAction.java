package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarLigacaoEsgotoAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm = (EfetuarLigacaoEsgotoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = ligacaoEsgotoActionForm.getIdOrdemServico();

		String materialLigacao = ligacaoEsgotoActionForm.getMaterialLigacao();
		String perfilLigacao = ligacaoEsgotoActionForm.getPerfilLigacao();
		String percentual = ligacaoEsgotoActionForm.getPercentualColeta()
				.toString().replace(",", ".");
		String percentualEsgoto = ligacaoEsgotoActionForm.getPercentualEsgoto()
				.toString().replace(",", ".");
		String idServicoMotivoNaoCobranca = ligacaoEsgotoActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = ligacaoEsgotoActionForm
				.getPercentualCobranca();
		String indicadorCaixaGordura = ligacaoEsgotoActionForm
				.getIndicadorCaixaGordura();

		String ligacaoEsgotoEsgotamento = ligacaoEsgotoActionForm
				.getCondicaoEsgotamento();
		String ligacaoEsgotoDestinoDejetos = ligacaoEsgotoActionForm
				.getDestinoDejetos();
		String ligacaoEsgotoCaixaInspecao = ligacaoEsgotoActionForm
				.getSituacaoCaixaInspecao();
		String ligacaoEsgotoDestinoAguasPluviais = ligacaoEsgotoActionForm
				.getDestinoAguasPluviais();
		String idLigacaoOrigem = ligacaoEsgotoActionForm.getIdLigacaoOrigem();
		String idImovel = ligacaoEsgotoActionForm.getIdImovel();
		String indicadorLigacaoEsgoto = ligacaoEsgotoActionForm.getIndicadorLigacao();
		Imovel imovel = null;
		
		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
		
		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
		
		if (sessao.getAttribute("imovel") != null) {
			imovel = (Imovel) sessao.getAttribute("imovel");
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, imovel.getId()));
		} else if (idImovel != null && !idImovel.trim().equals("")) {
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, idImovel));
		}
		
		Collection colecaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());
		
		if (colecaoLigacaoEsgoto != null && !colecaoLigacaoEsgoto.isEmpty()) {
			ligacaoEsgoto = (LigacaoEsgoto) Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);
		}

		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {

			imovel = new Imovel();
			imovel.setId(new Integer(idImovel));
			imovel.setUltimaAlteracao(new Date());

			/*---------------------  InícioDados da Ligação Esgoto  ------------------------*/
			// lesg_iccaixagordura
			ligacaoEsgoto.setIndicadorCaixaGordura(new Short(
					indicadorCaixaGordura));
			
			ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(indicadorLigacaoEsgoto));
			// lagu_tultimaalteracao
			ligacaoEsgoto.setUltimaAlteracao(new Date());
			// lest_id
			ligacaoEsgoto.setId(imovel.getId());
			// LEST_ID
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			// ligacaoEsgoto.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

			String diametroLigacao = ligacaoEsgotoActionForm
					.getDiametroLigacao();
			if (diametroLigacao != null
					&& !diametroLigacao.equals("")
					&& !diametroLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
				ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
				ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Diametro da Ligação");
			}
			if (ligacaoEsgotoActionForm.getDataLigacao() != null
					&& !ligacaoEsgotoActionForm.getDataLigacao().equals("")) {

				Date data = Util.converteStringParaDate(ligacaoEsgotoActionForm
						.getDataLigacao());
				ligacaoEsgoto.setDataLigacao(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data da Ligação");
			}

			if (materialLigacao != null
					&& !materialLigacao.equals("")
					&& !materialLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
				ligacaoEsgotoMaterialMaterial
						.setId(new Integer(materialLigacao));
				ligacaoEsgoto
						.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Material da Ligação");
			}

			if (perfilLigacao != null
					&& !perfilLigacao.equals("")
					&& !perfilLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
				ligacaoEsgotoPerfil.setId(new Integer(perfilLigacao));
				ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Perfil da Ligação");
			}
			// item 4.5 - [FS006] caso 1,3
			if (percentual != null && !percentual.equals("")) {

				BigDecimal percentualInformadoColeta = new BigDecimal(
						percentual);
				if (percentualInformadoColeta != null
						&& !percentualInformadoColeta.equals("")
						&& (percentualInformadoColeta.intValue() <= ConstantesSistema.NUMERO_MAXIMO_CONSUMO_MINIMO_FIXADO)) {
					ligacaoEsgoto
							.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
				}
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Percentual de Coleta");
			}

			// Colocado por Rômulo Aurélio Analista: Sávio Luiz Data:03/09/2008
			// Ligacao Origem
			if (idLigacaoOrigem != null
					&& !idLigacaoOrigem.equals("")
					&& !idLigacaoOrigem.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
				ligacaoOrigem.setId(new Integer(idLigacaoOrigem));

				ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);

			}

			if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

				BigDecimal percentualEsgotoColeta = new BigDecimal(
						percentualEsgoto);
				ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
			}

			// Condicação do Esgotamento
			if (ligacaoEsgotoEsgotamento != null
					&& Integer.parseInt(ligacaoEsgotoEsgotamento) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoEsgotamento le = new LigacaoEsgotoEsgotamento();
				le.setId(Integer.parseInt(ligacaoEsgotoEsgotamento));
				ligacaoEsgoto.setLigacaoEsgotoEsgotamento(le);
			}

			// Destino dos dejetos
			if (ligacaoEsgotoDestinoDejetos != null
					&& Integer.parseInt(ligacaoEsgotoDestinoDejetos) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoDejetos ledd = new LigacaoEsgotoDestinoDejetos();
				ledd.setId(Integer.parseInt(ligacaoEsgotoDestinoDejetos));
				ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(ledd);
			}

			// Situacao da caixa de inspecao
			if (ligacaoEsgotoCaixaInspecao != null
					&& Integer.parseInt(ligacaoEsgotoCaixaInspecao) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoCaixaInspecao leci = new LigacaoEsgotoCaixaInspecao();
				leci.setId(Integer.parseInt(ligacaoEsgotoCaixaInspecao));
				ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(leci);
			}

			// Destino das Aguas Pluviais
			if (ligacaoEsgotoDestinoAguasPluviais != null
					&& Integer.parseInt(ligacaoEsgotoDestinoAguasPluviais) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoAguasPluviais ledap = new LigacaoEsgotoDestinoAguasPluviais();
				ledap
						.setId(Integer
								.parseInt(ligacaoEsgotoDestinoAguasPluviais));
				ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(ledap);
			}

			ligacaoEsgoto.setImovel(imovel);

			String qtdParcelas = ligacaoEsgotoActionForm
					.getQuantidadeParcelas();
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoEsgoto(ligacaoEsgoto);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(null);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			fachada.inserirLigacaoEsgoto(integracaoComercialHelper);
			
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				montarPaginaSucesso(httpServletRequest,
						"Ligação de Esgoto efetuada com Sucesso",
						"Efetuar outra Ligação de Esgoto",
						"exibirEfetuarLigacaoEsgotoAction.do?menu=sim",
						"exibirAtualizarLigacaoEsgotoAction.do?menu=sim&matriculaImovel="
								+ imovel.getId(),
						"Atualização Ligação de Esgoto efetuada");

			}

		}

		OrdemServico ordemServico = null;
		if (ordemServicoId != null && !ordemServicoId.equals("")) {
			ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
			if (ordemServico == null) {
				throw new ActionServletException(
						"atencao.ordem_servico_inexistente", null,
						"ORDEM DE SERVIÇO INEXISTENTE");

			}
			
			ligacaoEsgoto.setImovel(imovel);

			if (ligacaoEsgotoActionForm.getDataLigacao() != null
					&& ligacaoEsgotoActionForm.getDataLigacao() != "") {
				Date data = Util.converteStringParaDate(ligacaoEsgotoActionForm
						.getDataLigacao());
				ligacaoEsgoto.setDataLigacao(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data da Ligação");
			}
			/*---------------------  InícioDados da Ligação Esgoto  ------------------------*/
			// lesg_iccaixagordura
			ligacaoEsgoto.setIndicadorCaixaGordura(new Short(
					indicadorCaixaGordura));
			ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(indicadorLigacaoEsgoto));
			// lagu_tultimaalteracao
			ligacaoEsgoto.setUltimaAlteracao(new Date());
			// lest_id
			ligacaoEsgoto.setId(imovel.getId());
			// LEST_ID
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			// ligacaoEsgoto.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

			String diametroLigacao = ligacaoEsgotoActionForm
					.getDiametroLigacao();
			if (diametroLigacao != null
					&& !diametroLigacao.equals("")
					&& !diametroLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
				ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
				ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Diametro da Ligação");
			}

			if (materialLigacao != null
					&& !materialLigacao.equals("")
					&& !materialLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
				ligacaoEsgotoMaterialMaterial
						.setId(new Integer(materialLigacao));
				ligacaoEsgoto
						.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Material da Ligação");
			}

			if (perfilLigacao != null
					&& !perfilLigacao.equals("")
					&& !perfilLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
				ligacaoEsgotoPerfil.setId(new Integer(perfilLigacao));
				ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Perfil da Ligação");
			}
			// item 4.5 - [FS006] caso 1,3
			if (percentual != null && !percentual.equals("")) {

				BigDecimal percentualInformadoColeta = new BigDecimal(
						percentual);
				if (percentualInformadoColeta != null
						&& !percentualInformadoColeta.equals("")
						&& (percentualInformadoColeta.intValue() <= ConstantesSistema.NUMERO_MAXIMO_CONSUMO_MINIMO_FIXADO)) {
					ligacaoEsgoto
							.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
				}
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Percentual de Coleta");
			}

			// Colocado por Rômulo Aurélio Analista: Sávio Luiz Data:03/09/2008
			// Ligacao Origem
			if (idLigacaoOrigem != null
					&& !idLigacaoOrigem.equals("")
					&& !idLigacaoOrigem.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
				ligacaoOrigem.setId(new Integer(idLigacaoOrigem));

				ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);

			}

			if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

				BigDecimal percentualEsgotoColeta = new BigDecimal(
						percentualEsgoto);
				ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
			}

			// Condicação do Esgotamento
			if (ligacaoEsgotoEsgotamento != null
					&& Integer.parseInt(ligacaoEsgotoEsgotamento) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoEsgotamento le = new LigacaoEsgotoEsgotamento();
				le.setId(Integer.parseInt(ligacaoEsgotoEsgotamento));
				ligacaoEsgoto.setLigacaoEsgotoEsgotamento(le);
			}

			// Destino dos dejetos
			if (ligacaoEsgotoDestinoDejetos != null
					&& Integer.parseInt(ligacaoEsgotoDestinoDejetos) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoDejetos ledd = new LigacaoEsgotoDestinoDejetos();
				ledd.setId(Integer.parseInt(ligacaoEsgotoDestinoDejetos));
				ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(ledd);
			}

			// Situacao da caixa de inspecao
			if (ligacaoEsgotoCaixaInspecao != null
					&& Integer.parseInt(ligacaoEsgotoCaixaInspecao) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoCaixaInspecao leci = new LigacaoEsgotoCaixaInspecao();
				leci.setId(Integer.parseInt(ligacaoEsgotoCaixaInspecao));
				ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(leci);
			}

			// Destino das Aguas Pluviais
			if (ligacaoEsgotoDestinoAguasPluviais != null
					&& Integer.parseInt(ligacaoEsgotoDestinoAguasPluviais) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoAguasPluviais ledap = new LigacaoEsgotoDestinoAguasPluviais();
				ledap
						.setId(Integer
								.parseInt(ligacaoEsgotoDestinoAguasPluviais));
				ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(ledap);
			}

			if (ordemServico != null
					&& ligacaoEsgotoActionForm.getIdTipoDebito() != null) {

				ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

				ordemServico.setIndicadorComercialAtualizado(new Short("1"));

				BigDecimal valorAtual = new BigDecimal(0);
				if (ligacaoEsgotoActionForm.getValorDebito() != null) {
					String valorDebito = ligacaoEsgotoActionForm
							.getValorDebito().toString().replace(".", "");

					valorDebito = valorDebito.replace(",", ".");

					valorAtual = new BigDecimal(valorDebito);

					ordemServico.setValorAtual(valorAtual);
				}

				if (idServicoMotivoNaoCobranca != null
						&& !idServicoMotivoNaoCobranca
								.equals(ConstantesSistema.NUMERO_NAO_INFORMADO
										+ "")) {
					servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
					servicoNaoCobrancaMotivo.setId(new Integer(
							idServicoMotivoNaoCobranca));
				}
				ordemServico
						.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

				if (valorPercentual != null) {
					ordemServico.setPercentualCobranca(new BigDecimal(
							ligacaoEsgotoActionForm.getPercentualCobranca()));
				}

				ordemServico.setUltimaAlteracao(new Date());

			}

			String qtdParcelas = ligacaoEsgotoActionForm
					.getQuantidadeParcelas();
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoEsgoto(ligacaoEsgoto);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			integracaoComercialHelper.setUsuarioLogado(usuario);

			if (ligacaoEsgotoActionForm.getVeioEncerrarOS().equalsIgnoreCase(
					"FALSE")) {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

				fachada.inserirLigacaoEsgoto(integracaoComercialHelper);
			} else {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);

				sessao.setAttribute("integracaoComercialHelper",
						integracaoComercialHelper);

				if (sessao.getAttribute("semMenu") == null) {
					retorno = actionMapping
							.findForward("encerrarOrdemServicoAction");
				} else {
					retorno = actionMapping
							.findForward("encerrarOrdemServicoPopupAction");
				}
				sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
			}

			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				montarPaginaSucesso(httpServletRequest,
						"Ligação de Esgoto efetuada com Sucesso",
						"Efetuar outra Ligação de Esgoto",
						"exibirEfetuarLigacaoEsgotoAction.do?menu=sim",
						"exibirAtualizarLigacaoEsgotoAction.do?menu=sim&idOrdemServico="
								+ ordemServico.getId(),
						"Atualização Ligação de Esgoto efetuada");

			}

		}
		return retorno;

		/*
		 * else { throw new ActionServletException(
		 * "atencao.informe_campo_obrigatorio", null, "Ordem de Serviço"); }
		 */
	}

}
