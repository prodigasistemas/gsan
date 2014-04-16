package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipoVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio - Hugo Leonardo
 * @date 30/10/2006 - 22/04/2010
 */
public class FiltrarValorCobrancaServicoAction extends GcomAction {

	/**
	 * [UC0392] Filtrar Valor de Cobrança do Serviço
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa do Valor de
	 * Cobrança de Serviço
	 * 
	 * @author Rômulo Aurélio
	 * @date 30/10/2006
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterValorCobrancaServicoAction");

		FiltrarValorCobrancaServicoActionForm form = (FiltrarValorCobrancaServicoActionForm) actionForm;

		FiltroServicoCobrancaValor filtroServicoCobrancaValor = new FiltroServicoCobrancaValor();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String tipoServico = form.getTipoServico();
		String perfilImovel = form.getPerfilImovel();
		String indicadorMedido = form.getIndicadorMedido();
		String capacidadeHidrometro = form.getCapacidadeHidrometro();
		String valorServicoInicial = form.getValorServicoInicial();
		String valorServicoFinal = form.getValorServicoFinal();
		String quantidadeEconomiaInicial = form.getQuantidadeEconomiasInicial();
		String quantidadeEconomiaFinal = form.getQuantidadeEconomiasFinal();
		String categoria = form.getIdCategoria();
		String subCategoria = form.getIdSubCategoria();
		String indicadorGeracaoDebito = form.getIndicadorGeracaoDebito();	
		String indicativoTipoSevicoEconomias = form.getIndicativoTipoSevicoEconomias();
		
		// Verifica se o campo tipoServico foi informado
		if(categoria != null
				&& !categoria.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;

			filtroServicoCobrancaValor.adicionarParametro(new ParametroSimples(
					FiltroServicoCobrancaValor.CATEGORIA, categoria));
		}
		
		if(subCategoria != null
				&& !subCategoria.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;

			filtroServicoCobrancaValor.adicionarParametro(new ParametroSimples(
					FiltroServicoCobrancaValor.SUBCATEGORIA, subCategoria));
		}
		
		
		if (tipoServico != null && !tipoServico.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoCobrancaValor.adicionarParametro(new ParametroSimples(
					FiltroServicoCobrancaValor.SERVICOTIPO, tipoServico));
			
		}
		
		if(quantidadeEconomiaInicial != null && !quantidadeEconomiaInicial.equals("")
				&& quantidadeEconomiaFinal != null && !quantidadeEconomiaFinal.equals("")){
			
			filtroServicoCobrancaValor.adicionarParametro(new Intervalo(FiltroServicoCobrancaValor.QUANTIDADE_ECONOMIA_INICIAL,
					quantidadeEconomiaInicial, quantidadeEconomiaFinal));
		}
		
		filtroServicoCobrancaValor.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
		
		if(indicativoTipoSevicoEconomias != null && !indicativoTipoSevicoEconomias.equals("3")){
			
			peloMenosUmParametroInformado = true;

			filtroServicoCobrancaValor.adicionarParametro(new ParametroSimples(
					FiltroServicoCobrancaValor.INDICATIVOSERVICOECONOMIA, indicativoTipoSevicoEconomias));
		}

		// Verifica se o campo perfilImovel foi informado
		if (perfilImovel != null
				&& !perfilImovel.trim().equals("-1")) {

			peloMenosUmParametroInformado = true;

			filtroServicoCobrancaValor.adicionarParametro(new ComparacaoTexto(
					FiltroServicoCobrancaValor.IMOVELPERFIL, perfilImovel));
			
		}
		
		// Verifica se o campo Data vigencia inicial foi informado
		if ( Util.verificarNaoVazio(form.getDataVigenciaInicial())) {
			
			peloMenosUmParametroInformado = true;

			Date periodoInicialVigencia = Util
					.converteStringParaDate(form.getDataVigenciaInicial());

			Date periodoFinalVigencia = Util
					.converteStringParaDate(form.getDataVigenciaFinal());

			Calendar diInicio = Calendar.getInstance();
			diInicio.setTime(periodoInicialVigencia);
			diInicio.set(Calendar.HOUR, 0);
			diInicio.set(Calendar.MINUTE, 0);
			diInicio.set(Calendar.SECOND, 0);

			Calendar diFim = Calendar.getInstance();
			diFim.setTime(periodoFinalVigencia);
			diFim.set(Calendar.HOUR, 23);
			diFim.set(Calendar.MINUTE, 59);
			diFim.set(Calendar.SECOND, 59);
			
			filtroServicoCobrancaValor.adicionarParametro(new MaiorQue(
					FiltroServicoCobrancaValor.DATA_INICIO_RELACAO,diInicio.getTime()));
			
			filtroServicoCobrancaValor.adicionarParametro(new MenorQue(
					FiltroServicoCobrancaValor.DATA_FIM_RELACAO,diFim.getTime()));
		}

		// Verifica se o campo indicadorMedido foi informado
		if (indicadorMedido != null && !indicadorMedido.trim().equals("3")) {

			peloMenosUmParametroInformado = true;
			filtroServicoCobrancaValor
					.adicionarParametro(new ParametroSimples(
							FiltroServicoCobrancaValor.INDICADORMEDIDO,	indicadorMedido));
			
		}

		// Verifica se o campo capacidadeHidrometro foi informado
		if (capacidadeHidrometro != null
				&& !capacidadeHidrometro.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroServicoCobrancaValor.adicionarParametro(new ParametroSimples(
					FiltroServicoCobrancaValor.HIDROMETROCAPACIDADE,
					capacidadeHidrometro));
			
			filtroServicoCobrancaValor.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
		}
		if(indicadorGeracaoDebito!=null && indicadorGeracaoDebito.trim().equalsIgnoreCase("1")){
			filtroServicoCobrancaValor.adicionarParametro(new ParametroSimples(
					FiltroServicoCobrancaValor.INDICADOR_GERACAO_DEBITO,
					ConstantesSistema.SIM));
			
			if(valorServicoInicial != null && !valorServicoInicial.trim().equalsIgnoreCase("")
					&& valorServicoFinal != null && !valorServicoFinal.trim().equalsIgnoreCase("")){
			
				BigDecimal valorInicial = new BigDecimal(valorServicoInicial.replace(".", "").replace(",", "."));
		
				BigDecimal valorFinal = new BigDecimal(valorServicoFinal.replace(".", "").replace(",", "."));
		
				Integer resultado = valorInicial.compareTo(valorFinal);
		
				if (resultado == 1) {
					throw new ActionServletException(
							"atencao.valor_cobranca_servico_final_menor_valor_inicial");
				}
				
				 
				filtroServicoCobrancaValor.adicionarParametro(new Intervalo(
							FiltroDebitoTipoVigencia.VALOR, 
							Util.formatarMoedaRealparaBigDecimal(valorServicoInicial),
							Util.formatarMoedaRealparaBigDecimal(valorServicoFinal)));
				
			}
			
			peloMenosUmParametroInformado = true;
			
		}else if(indicadorGeracaoDebito!=null && indicadorGeracaoDebito.trim().equalsIgnoreCase("2")){
			filtroServicoCobrancaValor.adicionarParametro(new ParametroSimples(
					FiltroServicoCobrancaValor.INDICADOR_GERACAO_DEBITO,
					ConstantesSistema.NAO));
			
			peloMenosUmParametroInformado = true;
			
		}
		

		filtroServicoCobrancaValor.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroServicoCobrancaValor.setCampoOrderBy(FiltroServicoCobrancaValor.SERVICOTIPO);

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		Collection <ServicoCobrancaValor> colecaoServicoCobrancaValor = fachada.pesquisar(filtroServicoCobrancaValor, ServicoCobrancaValor.class.getName());

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterValorCobrancaServicoAction para nele verificar se irá
		// para o
		// atualizar ou para o manter
		if (form.getAtualizar() != null	&& form.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",form.getAtualizar());
		}
		
		if (colecaoServicoCobrancaValor == null || colecaoServicoCobrancaValor.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Servico Cobranca Valor");
		} else {
			httpServletRequest.setAttribute("colecaoServicoCobrancaValor",colecaoServicoCobrancaValor);
			ServicoCobrancaValor servicoCobrancaValor = new ServicoCobrancaValor();
			servicoCobrancaValor = (ServicoCobrancaValor) Util.retonarObjetoDeColecao(colecaoServicoCobrancaValor);
			String idRegistroAtualizar = servicoCobrancaValor.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}
		
		sessao.setAttribute("filtroServicoCobrancaValor",filtroServicoCobrancaValor);

		httpServletRequest.setAttribute("filtroServicoCobrancaValor",
				filtroServicoCobrancaValor);

		return retorno;

	}
}
