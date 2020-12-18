package gcom.gui.atendimentopublico;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
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

/**
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarLigacaoAguaComInstalacaoHidrometroAction extends GcomAction {

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

		EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm = (EfetuarLigacaoAguaComInstalacaoHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdOrdemServico();

		LigacaoAgua ligacaoAgua = this
				.setDadosLigacaoAgua(efetuarLigacaoAguaComInstalacaoHidrometroActionForm, fachada);

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();

		hidrometroInstalacaoHistorico = this
				.setDadosHidrometroInstalacaoHistorico(
						hidrometroInstalacaoHistorico,
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
						usuario);

		Imovel imovel = null;

		String idImovel = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdImovel();

		if (idImovel != null && !idImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			String inscricaoImovelEncontrado = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			if (inscricaoImovelEncontrado != null
					&& !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

				imovel = (Imovel) fachada.pesquisarDadosImovel(new Integer(
						idImovel));

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, imovel.getId()));

				Collection colecaoImoveis = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				Imovel imovelComLocalidade = (Imovel) Util
						.retonarObjetoDeColecao(colecaoImoveis);

				if (imovelComLocalidade != null
						&& imovelComLocalidade.getLocalidade()
								.getHidrometroLocalArmazenagem() != null
						&& hidrometroInstalacaoHistorico.getHidrometro()
								.getHidrometroLocalArmazenagem() != null
						&& !hidrometroInstalacaoHistorico
								.getHidrometro()
								.getHidrometroLocalArmazenagem()
								.getId()
								.equals(
										imovelComLocalidade
												.getLocalidade()
												.getHidrometroLocalArmazenagem()
												.getId())) {
					throw new ActionServletException(
							"atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}

				imovel.setUltimaAlteracao(new Date());

				ligacaoAgua.setImovel(imovel);

				hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);

				IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

				integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
				integracaoComercialHelper.setImovel(imovel);
				integracaoComercialHelper.setOrdemServico(null);
				integracaoComercialHelper.setQtdParcelas(null);
				integracaoComercialHelper
						.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

				fachada.efetuarLigacaoAguaComInstalacaoHidrometro(
						integracaoComercialHelper, usuario);

				if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
					// Monta a página de sucesso
					montarPaginaSucesso(
							httpServletRequest,
							"Ligação de Água com Instalação de Hidrômetro efetuada com Sucesso",
							"Efetuar outra Ligação de Água com Instalação de Hidrômetro",
							"exibirEfetuarLigacaoAguaComInstalacaoHidrometroAction.do?menu=sim");
				}

				return retorno;

			} else {

				httpServletRequest.setAttribute("corImovel", "exception");

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}

		}

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

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, imovel.getId()));

				Collection colecaoImoveis = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				Imovel imovelComLocalidade = (Imovel) Util
						.retonarObjetoDeColecao(colecaoImoveis);

				if (imovelComLocalidade != null
						&& imovelComLocalidade.getLocalidade()
								.getHidrometroLocalArmazenagem() != null
						&& hidrometroInstalacaoHistorico.getHidrometro()
								.getHidrometroLocalArmazenagem() != null
						&& !hidrometroInstalacaoHistorico
								.getHidrometro()
								.getHidrometroLocalArmazenagem()
								.getId()
								.equals(
										imovelComLocalidade
												.getLocalidade()
												.getHidrometroLocalArmazenagem()
												.getId())) {
					throw new ActionServletException(
							"atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}

				// hidrometroInstalacaoHistorico.setImovel(imovel);

				ligacaoAgua.setId(imovel.getId());
				hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
			}

			hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);

			ordemServico = 
				this.setDadosOrdemServico(ordemServico,
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm);

			String qtdParcelas = 
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getQuantidadeParcelas();

			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

			if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.getVeioEncerrarOS().equalsIgnoreCase("FALSE")) {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

				fachada.efetuarLigacaoAguaComInstalacaoHidrometro(
						integracaoComercialHelper, usuario);
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
				montarPaginaSucesso(
						httpServletRequest,
						"Ligação de Água com Instalação de Hidrômetro efetuada com Sucesso",
						"Efetuar outra Ligação de Água com Instalação de Hidrômetro",
						"exibirEfetuarLigacaoAguaComInstalacaoHidrometroAction.do?menu=sim");
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
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
			Fachada fachada) {

		String diametroLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDiametroLigacao();
		String materialLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getMaterialLigacao();
		String idPerfilLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getPerfilLigacao();
		
		//[FS0016] - verificar tarifa de consumo associada. 
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples (
				FiltroConsumoTarifa.LIGACAO_AGUA_PERFIL,
				idPerfilLigacao));
		
		Collection pesquisa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());
		
		if (!pesquisa.isEmpty()){
			Boolean existeTarifaIgual = false;
			Iterator iteratorColecaoConsumoTarifa = pesquisa.iterator();
			Imovel imovelConsulta=null;
			
			if(efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getMatriculaImovel() != null 
					&& efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getMatriculaImovel() != ""){
				imovelConsulta= fachada.pesquisarImovel(new Integer(efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getMatriculaImovel()));
			}else{
				//esse caso e apenas para usuario com permissao especial para efetuarLigacaoAguaCominstalacaoHidrometroSemRA
				if(efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getIdImovel() != null 
						&& efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getIdImovel() != ""){
					imovelConsulta= fachada.pesquisarImovel(new Integer(efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getIdImovel()));
				}
			}
			
			while(iteratorColecaoConsumoTarifa.hasNext()){
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) iteratorColecaoConsumoTarifa.next();
				if (consumoTarifa.getLigacaoAguaPerfil() != null){
					if(imovelConsulta != null){
						if (imovelConsulta.getConsumoTarifa().getId().intValue() == consumoTarifa.getId().intValue()){
							existeTarifaIgual = true;
						}
					}
				}
			}
			
			if (!existeTarifaIgual){
				throw new ActionServletException("atencao.tarifa_consumo_perfil_ligacao",null, "Perfil da Ligação");
			}
		}
		
		String ramalLocalInstalacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getRamalLocalInstalacao();
		String idLigacaoOrigem = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdLigacaoOrigem();
		/*
		 * String posicaoLigacao =
		 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
		 * .getPosicaoLigacao(); String abastecimentoAlternativo =
		 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
		 * .getAbastecimentoAlternativo(); String situacaoAbastecimento =
		 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
		 * .getSituacaoAbastecimento();
		 */
		String numeroLacre = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroLacre();

		LigacaoAgua ligacaoAgua = new LigacaoAgua();

		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataLigacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataLigacao().equals("")) {
			Date data = Util
					.converteStringParaDate(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getDataLigacao());
			ligacaoAgua.setDataLigacao(data);
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					" Data da Ligação");
		}

		if (diametroLigacao != null
				&& !diametroLigacao.equals("")
				&& !diametroLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro();
			ligacaoAguaDiametro.setId(new Integer(diametroLigacao));
			ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Diametro da Ligação");
		}

		if (materialLigacao != null
				&& !materialLigacao.equals("")
				&& !materialLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial();
			ligacaoAguaMaterialMaterial.setId(new Integer(materialLigacao));
			ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Material da Ligação");
		}

		if (idPerfilLigacao != null
				&& !idPerfilLigacao.equals("")
				&& !idPerfilLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
			ligacaoAguaPerfil.setId(new Integer(idPerfilLigacao));
			ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Perfil da Ligação");
		}

		if (ramalLocalInstalacao != null
				&& !ramalLocalInstalacao.equals("")
				&& !ramalLocalInstalacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao();
			ramalLocal.setId(new Integer(ramalLocalInstalacao));

			ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
		}

		if (ramalLocalInstalacao != null
				&& !ramalLocalInstalacao.equals("")
				&& !ramalLocalInstalacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao();
			ramalLocal.setId(new Integer(ramalLocalInstalacao));

			ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
		}

		if (numeroLacre != null && !numeroLacre.equals("")) {
			ligacaoAgua.setNumeroLacre(numeroLacre);
		}

		if (idLigacaoOrigem != null
				&& !idLigacaoOrigem.equals("")
				&& !idLigacaoOrigem.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
			ligacaoOrigem.setId(new Integer(idLigacaoOrigem));
			ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
		}

		return ligacaoAgua;
	}

	// [SB0003] - Atualizar Ordem de Serviço
	//
	// Método responsável por setar os dados da ordem de serviço
	// de acordo com as exigências do caso de uso
	public OrdemServico setDadosOrdemServico(
			OrdemServico ordemServico,
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm) {

		String idServicoMotivoNaoCobranca = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getPercentualCobranca();

		if (ordemServico != null
				&& efetuarLigacaoAguaComInstalacaoHidrometroActionForm
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
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.getPercentualCobranca()));
			}

			ordemServico.setUltimaAlteracao(new Date());

		}

		BigDecimal valorAtual = new BigDecimal(0);
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getValorDebito() != null) {
			String valorDebito = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
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
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
			Usuario usuarioLogado) {

		Fachada fachada = Fachada.getInstancia();

		String numeroHidrometro = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroHidrometro();

		String numeroLacreHidrometro = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroLacreHidrometro();

		if (numeroHidrometro != null) {
			// Pesquisa o Hidrômetro
			Hidrometro hidrometro = fachada
					.pesquisarHidrometroPeloNumero(numeroHidrometro);

			// FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			// filtroHidrometro.adicionarParametro(new ParametroSimples(
			// FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
			// // Realiza a pesquisa do Hidrômetro
			// Collection colecaoHidrometro =
			// fachada.pesquisar(filtroHidrometro,
			// Hidrometro.class.getName());
			//
			// // verificar se o número do hidrômetro não está cadastrado
			// if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
			// throw new ActionServletException(
			// "atencao.hidrometro_inexistente");
			// }
			// Iterator iteratorHidrometro = colecaoHidrometro.iterator();
			// Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();

			if (hidrometro == null) {
				throw new ActionServletException(
						"atencao.hidrometro_inexistente");
			}

			hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		}

		// Data instalação
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataInstalacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataInstalacao().equals("")) {

			hidrometroInstalacaoHistorico
					.setDataInstalacao(Util
							.converteStringParaDate(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.getDataInstalacao()));

		}

		// Medição tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);

		// hidrômetro local instalação
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer
				.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getLocalInstalacao()));
		hidrometroInstalacaoHistorico
				.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);

		// proteção
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer
				.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);

		// leitura instalação
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getLeituraInstalacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getLeituraInstalacao().trim().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroLeituraInstalacao(Integer
							.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.getLeituraInstalacao()));
		} else {
			hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);
		}

		// cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short
				.parseShort(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
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
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getNumeroSelo() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getNumeroSelo().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroSelo(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getNumeroSelo());
		} else {
			hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}

		// Número do Lacre
		if (numeroLacreHidrometro != null
				&& !numeroLacreHidrometro.trim().equals("")) {
			hidrometroInstalacaoHistorico.setNumeroLacre(numeroLacreHidrometro);
		} else {
			hidrometroInstalacaoHistorico.setNumeroLacre(null);
		}
		// tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());

		// indicador instalação substituição
		hidrometroInstalacaoHistorico
				.setIndicadorInstalcaoSubstituicao(new Short("1"));

		// indicador troca de protecao
		hidrometroInstalacaoHistorico
			.setIndicadorTrocaProtecao(ConstantesSistema.NAO);
		
		hidrometroInstalacaoHistorico
			.setIndicadorTrocaRegistro(ConstantesSistema.NAO);
		
		// data última alteração
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());

		// Usuário Instalação
		hidrometroInstalacaoHistorico.setUsuarioInstalacao(usuarioLogado);

		return hidrometroInstalacaoHistorico;

	}
}
