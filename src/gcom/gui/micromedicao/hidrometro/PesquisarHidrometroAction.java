package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obtém o action form
		PesquisarHidrometroActionForm pesquisarHidrometroActionForm = (PesquisarHidrometroActionForm) actionForm;

		// Coleção que armazena o resultado da pesquisa
		Collection<Hidrometro> hidrometros = null;

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("listaHidrometro");

		// cria sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Pega a instancia da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltroHidrometro filtroHidrometro = new FiltroHidrometro(
				FiltroHidrometro.NUMERO_HIDROMETRO);

		// Recupera os parâmetros do form
		String numeroHidrometro = pesquisarHidrometroActionForm.getNumeroHidrometro();
		String dataAquisicao = pesquisarHidrometroActionForm.getDataAquisicao();
		String anoFabricacao = pesquisarHidrometroActionForm.getAnoFabricacao();
		String indicadorMacromedidor = pesquisarHidrometroActionForm.getFinalidade();
		String idHidrometroClasseMetrologica = pesquisarHidrometroActionForm.getIdHidrometroClasseMetrologica();
		String idHidrometroMarca = pesquisarHidrometroActionForm.getIdHidrometroMarca();
		String idHidrometroDiametro = pesquisarHidrometroActionForm.getIdHidrometroDiametro();
		String idHidrometroCapacidade = pesquisarHidrometroActionForm.getIdHidrometroCapacidade();
		String idHidrometroTipo = pesquisarHidrometroActionForm.getIdHidrometroTipo();
		String idLocalArmazenagem = pesquisarHidrometroActionForm.getIdLocalArmazenamento();
		String idHidrometroSituacao = pesquisarHidrometroActionForm.getIdHidrometroSituacao();
		String fixo = pesquisarHidrometroActionForm.getFixo();
		String faixaInicial = pesquisarHidrometroActionForm.getFaixaInicial();
		String faixaFinal = pesquisarHidrometroActionForm.getFaixaFinal();

		boolean peloMenosUmParametroInformado = false;

		// Caso o fixo, a faixa inicial e faixa final seja diferente de null
		// então ignora os outros parametros e faz a pesquisa do filtro por
		// esses 3 parâmetros
		if ((fixo != null && !fixo.trim().equalsIgnoreCase(""))
				&& (faixaInicial != null
						&& !faixaInicial.trim().equalsIgnoreCase("") && (faixaFinal != null && !faixaFinal
						.trim().equalsIgnoreCase("")))) {

			// Verifica se a faixa inicial e final são iguais a zero
			if( faixaInicial.equals("") || faixaInicial == null  ){
				throw new ActionServletException(
				"atencao.faixa.inicial.deve.ser.informada");
			}else{
				if( faixaInicial.equals("0") ){
					throw new ActionServletException(
					"atencao.faixa.inicial.deve.ser.maior.zero");
				}
			}	
			if( faixaFinal.equals("") || faixaFinal == null  ){
				throw new ActionServletException(
					"atencao.faixa.final.deve.ser.informada");
			}else{
				if( faixaFinal.equals("0")){
					throw new ActionServletException(
						"atencao.faixa.final.deve.ser.maior.zero");
				}
			}	
			
			if( faixaInicial != null && faixaFinal != null  ){
				Integer faixaIni = new Integer(faixaInicial);
				Integer faixaFim = new Integer(faixaFinal);
				if( faixaIni > faixaFim ){
					throw new ActionServletException(
						"atencao.faixa.final.deve.ser.maior.faixao.inicial");
				}
			}
			
			hidrometros = fachada.pesquisarNumeroHidrometroFaixa(fixo,
					faixaInicial, faixaFinal);
		} else {
			// Insere os parâmetros informados no filtro
			if (numeroHidrometro != null
					&& !numeroHidrometro.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ComparacaoTexto(
						FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
			}
			if (dataAquisicao != null
					&& !dataAquisicao.trim().equalsIgnoreCase("")) {

				// Caso a data de aquisição seja maior que a data atual
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				Date dataAquisicaoFormatada = null;
				try {
					dataAquisicaoFormatada = formatoData.parse(dataAquisicao);
				} catch (ParseException ex) {
					// Erro no hibernate
					reportarErros(httpServletRequest, "erro.sistema", ex);
					// Atribui o mapeamento de retorno para a tela de erro
					retorno = actionMapping.findForward("telaErro");
				}
				
				// caso a data de aquisição seja menor que a data atual
				if (dataAquisicaoFormatada.after(new Date())) {
					throw new ActionServletException(
							"atencao.data.aquisicao.nao.superior.data.corrente");
				}

				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.DATA_AQUISICAO, Util.converteStringParaDate(dataAquisicao)));
			}

			if (anoFabricacao != null
					&& !anoFabricacao.trim().equalsIgnoreCase("")) {
				// Verifica se o ano de fabricação é maior que o ano da data de aquisição
				//Integer anoFabricacaoForm = new Integer(anoFabricacao);
				
				Short anoFabricacaoForm = new Short(anoFabricacao);
				
				
				if (dataAquisicao != null
						&& !dataAquisicao.trim().equalsIgnoreCase("")
						&& dataAquisicao.length() == 10) {
					
					Integer anoDataAquisicao = new Integer(dataAquisicao.substring(6));
					if (anoFabricacaoForm > anoDataAquisicao) {
						throw new ActionServletException(
								"atencao.ano.fabricacao.nao.superior.data.fabricacao");
					}	
				}
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.ANO_FABRICACAO, anoFabricacao));
			}

			if (indicadorMacromedidor != null
					&& !indicadorMacromedidor.trim().equalsIgnoreCase("")
					&& !indicadorMacromedidor.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.INDICADOR_MACROMEDIDOR,
						indicadorMacromedidor));
			}

			if (idHidrometroClasseMetrologica != null
					&& Integer.parseInt(idHidrometroClasseMetrologica) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_CLASSE_METROLOGICA_ID,
						idHidrometroClasseMetrologica));
			}

			if (idHidrometroMarca != null
					&& Integer.parseInt(idHidrometroMarca) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometro.HIDROMETRO_MARCA_ID,
								idHidrometroMarca));
			}

			if (idHidrometroDiametro != null
					&& Integer.parseInt(idHidrometroDiametro) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_DIAMETRO_ID,
						idHidrometroDiametro));
			}

			if (idHidrometroCapacidade != null
					&& Integer.parseInt(idHidrometroCapacidade) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_CAPACIDADE_ID,
						idHidrometroCapacidade));
			}
			if (idHidrometroTipo != null
					&& Integer.parseInt(idHidrometroTipo) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_TIPO_ID, idHidrometroTipo));
			}

			if (idLocalArmazenagem != null && !idLocalArmazenagem.equals("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_ID,
						idLocalArmazenagem));
			}

			if (idHidrometroSituacao != null
					&& Integer.parseInt(idHidrometroSituacao) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometro.HIDROMETRO_SITUACAO_ID,
								pesquisarHidrometroActionForm
										.getIdHidrometroSituacao()));
			}

			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");

			// Faz a pesquisa baseada no filtro
			//hidrometros = fachada.pesquisar(filtroHidrometro, Hidrometro.class
				//	.getName());
			
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroHidrometro, Hidrometro.class.getName());
			hidrometros = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

		}

		// Verificar se a pesquisa de hidrometros retornou vazia
		if (hidrometros == null || hidrometros.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Hidrômetro");

		}

		sessao.setAttribute("colecaoHidrometros",
				hidrometros);
		// Manda a coleção dos hidrômetros pesquisados para o request
		httpServletRequest.getSession(false).setAttribute("colecaoHidrometros",
				hidrometros);

		return retorno;
	}
}
