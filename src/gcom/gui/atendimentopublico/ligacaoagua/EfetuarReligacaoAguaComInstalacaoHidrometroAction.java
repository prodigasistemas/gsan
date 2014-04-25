package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
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
 * Action responsável pela realização de efetuar religação de água com instalação de hidrometro
 * 
 * @author Sávio Luiz
 * @created 29/01/2008
 */
public class EfetuarReligacaoAguaComInstalacaoHidrometroAction extends GcomAction {

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

		EfetuarReligacaoAguaComInstalacaoHidrometroActionForm efetuarReligacaoAguaComInstalacaoHidrometroActionForm = (EfetuarReligacaoAguaComInstalacaoHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = efetuarReligacaoAguaComInstalacaoHidrometroActionForm
				.getIdOrdemServico();

		LigacaoAgua ligacaoAgua = this
				.setDadosLigacaoAgua(efetuarReligacaoAguaComInstalacaoHidrometroActionForm,fachada);
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
		hidrometroInstalacaoHistorico = this
				.setDadosHidrometroInstalacaoHistorico(
						hidrometroInstalacaoHistorico,
						efetuarReligacaoAguaComInstalacaoHidrometroActionForm);
		
		Imovel imovel = null;

		if (ordemServicoId != null && !ordemServicoId.equals("")) {

			OrdemServico ordemServico = (OrdemServico) sessao
					.getAttribute("ordemServico");

			if (ordemServico == null) {
				throw new ActionServletException(
						"atencao.ordem_servico_inexistente", null,
						"ORDEM DE SERVIÇO INEXISTENTE");
			}

			if (sessao.getAttribute("imovel") != null) {
				imovel = (Imovel) sessao.getAttribute("imovel");
				imovel.setUltimaAlteracao(new Date());
				ligacaoAgua.setImovel(imovel);
//				hidrometroInstalacaoHistorico.setImovel(imovel);
				
				//ligacaoAgua.setId(imovel.getId());
				hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
			}

			hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
			
			
			ordemServico = this.setDadosOrdemServico(ordemServico, efetuarReligacaoAguaComInstalacaoHidrometroActionForm);
			
			String qtdParcelas = efetuarReligacaoAguaComInstalacaoHidrometroActionForm
					.getQuantidadeParcelas();

			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

			if (efetuarReligacaoAguaComInstalacaoHidrometroActionForm
					.getVeioEncerrarOS().equalsIgnoreCase("FALSE")) {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

				fachada.efetuarReligacaoAguaComInstalacaoHidrometro(integracaoComercialHelper, usuario);
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
				// Monta a página de sucesso
				montarPaginaSucesso(httpServletRequest,
						"Religação de Água com Instalação de Hidrômetro efetuada com Sucesso",
						"Efetuar outra Religação de Água com Instalação de Hidrômetro",
						"exibirEfetuarReligacaoAguaComInstalacaoHidrometroAction.do?menu=sim");
			}

			return retorno;
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					"Ordem de Serviço");
		}
	}

	// [SB0001] - Gerar Ligação de Água
	//
	// Método responsável por setar os dados da ligação de água
	// de acordo com os dados selecionados pelo usuário e pelas exigências do
	// caso de uso
	public LigacaoAgua setDadosLigacaoAgua(
			EfetuarReligacaoAguaComInstalacaoHidrometroActionForm efetuarReligacaoAguaComInstalacaoHidrometroActionForm,Fachada fachada) {


		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAgua.ID, efetuarReligacaoAguaComInstalacaoHidrometroActionForm.getMatriculaImovel()));
		Collection colecaoLigacaoAguaBase = fachada
				.pesquisar(filtroLigacaoAgua,
						LigacaoAgua.class.getName());
		
		LigacaoAgua ligacaoAgua = (LigacaoAgua) Util
				.retonarObjetoDeColecao(colecaoLigacaoAguaBase);

		if (efetuarReligacaoAguaComInstalacaoHidrometroActionForm
				.getDataReligacao() != null
				&& !efetuarReligacaoAguaComInstalacaoHidrometroActionForm
						.getDataReligacao().equals("")) {
			Date data = Util
					.converteStringParaDate(efetuarReligacaoAguaComInstalacaoHidrometroActionForm
							.getDataReligacao());
			ligacaoAgua.setDataReligacao(data);
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					" Data da Religação");
		}

		
		return ligacaoAgua;
	}

	// [SB0003] - Atualizar Ordem de Serviço
	//
	// Método responsável por setar os dados da ordem de serviço
	// de acordo com as exigências do caso de uso
	public OrdemServico setDadosOrdemServico(
			OrdemServico ordemServico,
			EfetuarReligacaoAguaComInstalacaoHidrometroActionForm efetuarReligacaoAguaComInstalacaoHidrometroActionForm) {

		String idServicoMotivoNaoCobranca = efetuarReligacaoAguaComInstalacaoHidrometroActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarReligacaoAguaComInstalacaoHidrometroActionForm
				.getPercentualCobranca();

		if (ordemServico != null
				&& efetuarReligacaoAguaComInstalacaoHidrometroActionForm
						.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			if (idServicoMotivoNaoCobranca != null
					&& !idServicoMotivoNaoCobranca
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}

			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(
						efetuarReligacaoAguaComInstalacaoHidrometroActionForm
								.getPercentualCobranca()));
			}

			ordemServico.setUltimaAlteracao(new Date());

		}

		BigDecimal valorAtual = new BigDecimal(0);
		if (efetuarReligacaoAguaComInstalacaoHidrometroActionForm
				.getValorDebito() != null) {
			String valorDebito = efetuarReligacaoAguaComInstalacaoHidrometroActionForm
					.getValorDebito().toString().replace(".", "");

			valorDebito = valorDebito.replace(",", ".");

			valorAtual = new BigDecimal(valorDebito);

			ordemServico.setValorAtual(valorAtual);
		}

		return ordemServico;
	}

	// [SB0004] - Gerar Histórico de Instalação do Hidrômetro
	//
	// Método responsável por setar os dados do hidrômetro instalação histórico
	// de acordo com os dados selecionados pelo usuário e pelas exigências do
	// caso de uso
	public HidrometroInstalacaoHistorico setDadosHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			EfetuarReligacaoAguaComInstalacaoHidrometroActionForm efetuarReligacaoAguaComInstalacaoHidrometroActionForm) {

		Fachada fachada = Fachada.getInstancia();

		String numeroHidrometro = efetuarReligacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroHidrometro();

		if (numeroHidrometro != null) {
			// Pesquisa o Hidrômetro
			Hidrometro hidrometro = fachada.pesquisarHidrometroPeloNumero(numeroHidrometro);
			
//			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
//			filtroHidrometro.adicionarParametro(new ParametroSimples(
//					FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
//			// Realiza a pesquisa do Hidrômetro
//			Collection colecaoHidrometro = fachada.pesquisar(filtroHidrometro,
//					Hidrometro.class.getName());
//
//			// verificar se o número do hidrômetro não está cadastrado
//			if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
//				throw new ActionServletException(
//						"atencao.hidrometro_inexistente");
//			}
//			Iterator iteratorHidrometro = colecaoHidrometro.iterator();
//			Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();
			
			if (hidrometro == null) {
				throw new ActionServletException(
						"atencao.hidrometro_inexistente");
			}
			
			hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		}

		// Data instalação
		if (efetuarReligacaoAguaComInstalacaoHidrometroActionForm
				.getDataInstalacao() != null
				&& !efetuarReligacaoAguaComInstalacaoHidrometroActionForm
						.getDataInstalacao().equals("")) {

			hidrometroInstalacaoHistorico
					.setDataInstalacao(Util
							.converteStringParaDate(efetuarReligacaoAguaComInstalacaoHidrometroActionForm
									.getDataInstalacao()));

		}
		
		// Medição tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);

		// hidrômetro local instalação
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer
				.parseInt(efetuarReligacaoAguaComInstalacaoHidrometroActionForm
						.getLocalInstalacao()));
		hidrometroInstalacaoHistorico
				.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);

		// proteção
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer
				.parseInt(efetuarReligacaoAguaComInstalacaoHidrometroActionForm
						.getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);

		// leitura instalação
		if (efetuarReligacaoAguaComInstalacaoHidrometroActionForm
				.getLeituraInstalacao() != null
				&& !efetuarReligacaoAguaComInstalacaoHidrometroActionForm
						.getLeituraInstalacao().trim().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroLeituraInstalacao(Integer
							.parseInt(efetuarReligacaoAguaComInstalacaoHidrometroActionForm
									.getLeituraInstalacao()));
		} else {
			hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);
		}

		// cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short
				.parseShort(efetuarReligacaoAguaComInstalacaoHidrometroActionForm
						.getSituacaoCavalete()));

		/*
		 * Campos opcionais
		 */

		// data da retirada
		hidrometroInstalacaoHistorico.setDataRetirada(null);
		// leitura retirada
		hidrometroInstalacaoHistorico.setNumeroLeituraRetirada(null);
		// leitura corte
		hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
		// leitura supressão
		hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
		// numero selo
		if (efetuarReligacaoAguaComInstalacaoHidrometroActionForm.getNumeroSelo() != null
				&& !efetuarReligacaoAguaComInstalacaoHidrometroActionForm
						.getNumeroSelo().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroSelo(efetuarReligacaoAguaComInstalacaoHidrometroActionForm
							.getNumeroSelo());
		} else {
			hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}
		// tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());

		// indicador instalação substituição
		hidrometroInstalacaoHistorico
				.setIndicadorInstalcaoSubstituicao(new Short("1"));

		// data última alteração
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());

		return hidrometroInstalacaoHistorico;

	}
}
