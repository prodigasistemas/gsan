package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarAnormalidadeLeituraAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("listaLeituraAnormalidades");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarLeituraAnormalidadeActionForm pesquisarLeituraAnormalidadeActionForm = (PesquisarLeituraAnormalidadeActionForm) actionForm;

		// Recupera os parametros

		String descricao = pesquisarLeituraAnormalidadeActionForm
				.getDescricao();
		String anormalidadeHidrometro = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeRelativaHidrometro();
		String anormalidadeAceitaSemHidrometro = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeSemHidrometro();
		String anormalidadeUsoSistema = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeRestritoSistema();
		String anormalidadePerdaTarifaSocial = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadePerdaTarifaSocial();
		String anormalidadeOrdemServicoAutomatica = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeOrdemServicoAutomatica();
		Integer tipoServico =  new Integer(pesquisarLeituraAnormalidadeActionForm
				.getTipoServico());
		Integer consumoLeituraInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getConsumoCobradoLeituraInformada());
		Integer consumoLeituraNaoInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getConsumoCobradoLeituraNaoInformada());
		Integer leituraFaturamentoLeituraInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getLeituraFaturamentoLeituraInformada());
		Integer leituraFaturamentoLeituraNaoInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getLeituraFaturamentoLeituraNaoInformada());

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade(
				FiltroLeituraAnormalidade.ID);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro

		if (descricao != null && !descricao.equals("")) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ComparacaoTexto(
					FiltroLeituraAnormalidade.DESCRICAO, descricao));
		}

		if (anormalidadeHidrometro != null
				&& !anormalidadeHidrometro.equals("") && 
				!(new Short(anormalidadeHidrometro)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_RELATIVO_HIDROMETRO,
					anormalidadeHidrometro));
		}

		if (anormalidadeAceitaSemHidrometro != null
				&& !anormalidadeAceitaSemHidrometro.equals("") && !(new Short(anormalidadeAceitaSemHidrometro)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_IMOVEL_SEM_HIDROMETRO,
					anormalidadeAceitaSemHidrometro));
		}

		if (anormalidadeUsoSistema != null
				&& !anormalidadeUsoSistema.equals("") && !(new Short(anormalidadeUsoSistema)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_USO_SISTEMA,
					anormalidadeUsoSistema));
		}

		if (anormalidadePerdaTarifaSocial != null
				&& !anormalidadePerdaTarifaSocial.equals("") && !(new Short(anormalidadePerdaTarifaSocial)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_PERDA_TARIFA_SOCIAL,
					anormalidadePerdaTarifaSocial));
		}

		if (anormalidadeOrdemServicoAutomatica != null
				&& !anormalidadeOrdemServicoAutomatica.equals("") && !(new Short(anormalidadeOrdemServicoAutomatica)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_EMISSAO_ORDEM_SERVICO,
					anormalidadeOrdemServicoAutomatica));
		}

		if (tipoServico != null
				&& tipoServico.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_TIPO_SERVICO, tipoServico));
		}

		if (consumoLeituraInformada != null
				&& consumoLeituraInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_COM_LEITURA,
					consumoLeituraInformada));
		}

		if (consumoLeituraNaoInformada != null
				&& consumoLeituraNaoInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_SEM_LEITURA,
					consumoLeituraNaoInformada));
		}

		if (leituraFaturamentoLeituraInformada != null
				&& leituraFaturamentoLeituraInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_COM_LEITURA,
					leituraFaturamentoLeituraInformada));
		}

		if (leituraFaturamentoLeituraNaoInformada != null
				&& leituraFaturamentoLeituraNaoInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_SEM_LEITURA,
					leituraFaturamentoLeituraNaoInformada));
		}


		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		
		Collection leituraAnormalidades = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// adiciona as dependências para serem mostradas na página
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoSemleitura");
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoComleitura");
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraSemleitura");
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraComleitura");

		// Faz a busca das empresas
		leituraAnormalidades = fachada.pesquisar(filtroLeituraAnormalidade,
				LeituraAnormalidade.class.getName());

		if (leituraAnormalidades == null || leituraAnormalidades.isEmpty()) {

			// Nenhum municipio cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Anormalidade de Leitura");
		} else if (leituraAnormalidades.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException(
					"atencao.pesquisa.muitosregistros");
		} else {
			if (leituraAnormalidades.size() == ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
				httpServletRequest.setAttribute("limitePesquisa", "");
			}

			// Coloca a coleção na sessão
			sessao.setAttribute("leituraAnormalidades", leituraAnormalidades);

		}

		return retorno;
	}

}
