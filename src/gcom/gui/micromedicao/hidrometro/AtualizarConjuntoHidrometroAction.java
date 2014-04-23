package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela atualização do conjunto de hidrometros
 * 
 * @author Sávio Luiz
 * @created 14 de Setembro de 2005
 */
public class AtualizarConjuntoHidrometroAction extends GcomAction {
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
		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);


		// Hidrometro que terá todas as modificações feitas no
		// hidrometro_atualizar_conjunto
		Hidrometro hidrometroAtualizado = new Hidrometro();
		
		String fixo = (String) sessao.getAttribute("fixo");
		String faixaInicial = (String) sessao.getAttribute("faixaInicial");
		String faixaFinal = (String) sessao.getAttribute("faixaFinal");

		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();
		
		//Pesquisa hidrometros
		//Collection hidrometros = fachada.pesquisarNumeroHidrometroFaixa(fixo,faixaInicial,faixaFinal);

		// Cria o objeto classe metrológica e seta o id
		HidrometroClasseMetrologica hidrometroClasseMetrologica = new HidrometroClasseMetrologica();
		hidrometroClasseMetrologica.setId(new Integer(hidrometroActionForm
				.getIdHidrometroClasseMetrologica()));
		hidrometroAtualizado
				.setHidrometroClasseMetrologica(hidrometroClasseMetrologica);

		// Cria o objeto hidrômetro marca e seta o id
		HidrometroMarca hidrometroMarca = new HidrometroMarca();
		hidrometroMarca.setId(new Integer(hidrometroActionForm
				.getIdHidrometroMarca()));
		hidrometroAtualizado.setHidrometroMarca(hidrometroMarca);

		// Cria o objeto hidrômetro capacidade e seta o id
		HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();
		hidrometroCapacidade.setId(new Integer(hidrometroActionForm
				.getIdHidrometroCapacidade()));
		hidrometroAtualizado.setHidrometroCapacidade(hidrometroCapacidade);

		// Cria o objeto hidrômetro diâmetro e seta o id
		HidrometroDiametro hidrometroDiametro = new HidrometroDiametro();
		hidrometroDiametro.setId(new Integer(hidrometroActionForm
				.getIdHidrometroDiametro()));
		hidrometroAtualizado.setHidrometroDiametro(hidrometroDiametro);

		// Cria o objeto hidrômetro tipo e seta o id
		HidrometroTipo hidrometroTipo = new HidrometroTipo();
		hidrometroTipo.setId(new Integer(hidrometroActionForm
				.getIdHidrometroTipo()));
		hidrometroAtualizado.setHidrometroTipo(hidrometroTipo);
		
		// Cria o objeto hidrômetro relojoaria e seta o id
		if(hidrometroActionForm
				.getIdHidrometroRelojoaria() != null && Integer.parseInt(hidrometroActionForm
						.getIdHidrometroRelojoaria()) > ConstantesSistema.NUMERO_NAO_INFORMADO){
			
			HidrometroRelojoaria hidrometroRelojoaria = new HidrometroRelojoaria();
			hidrometroRelojoaria.setId(new Integer(hidrometroActionForm
					.getIdHidrometroRelojoaria()));
			hidrometroAtualizado.setHidrometroRelojoaria(hidrometroRelojoaria);
			
		}else{
			hidrometroAtualizado.setHidrometroRelojoaria(null);
		}

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		Date dataAquisicao = null;
		try {
			dataAquisicao = formatoData.parse(hidrometroActionForm
					.getDataAquisicao());
		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}

		//SimpleDateFormat formatoDataAnterior = new SimpleDateFormat(
		//		"dd/MM/yyyy");

		Date dataAquisicaoAnterior = null;
		try {
			dataAquisicaoAnterior = formatoData.parse("01/01/1985");
		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}
		Calendar dataAtual = new GregorianCalendar();
		int anoAtual = dataAtual.get(Calendar.YEAR);
		// caso a data de aquisição seja menor que a data atual
		if (dataAquisicao.after(new Date())) {
			throw new ActionServletException(
					"atencao.data.aquisicao.nao.superior.data.corrente");
		}
		// caso a data de aquisição seja menor que 01/01/1985
		if (dataAquisicao.before(dataAquisicaoAnterior)) {
			throw new ActionServletException(
					"atencao.data.aquisicao.nao.inferior.1985");

		}
		Integer anoFabricacao = new Integer(hidrometroActionForm
				.getAnoFabricacao());
		// caso o ano de fabricação seja maior que o atual
		if (anoFabricacao > anoAtual) {
			throw new ActionServletException(
					"atencao.ano.fabricacao.nao.superior.data.corrente");

		}
		// caso o ano de fabricação seja menor que 1985
		if (anoFabricacao < 1985) {
			throw new ActionServletException(
					"atencao.ano.fabricacao.nao.inferior.1985");
		}

		Integer anoDataAquisicao = Util.getAno(dataAquisicao);
		// caso a data de aquisição seja menor que o ano fabricação
		if (anoDataAquisicao < anoFabricacao) {
			throw new ActionServletException(
					"atencao.ano.aquisicao.menor.ano.fabricacao");

		}
		
		//Vazao Transicao
		BigDecimal vazaoTransicao = null;
		
		if (hidrometroActionForm.getVazaoTransicao() != null 
				&& !hidrometroActionForm.getVazaoTransicao().equals("") ) {
			vazaoTransicao = Util.formatarMoedaRealparaBigDecimal( hidrometroActionForm.getVazaoTransicao() ) ;
		}
		
		//Vazao Nominal
		BigDecimal vazaoNominal = null;
		
		if (hidrometroActionForm.getVazaoNominal() != null 
				&& !hidrometroActionForm.getVazaoNominal().equals("") ) {
			vazaoNominal = Util.formatarMoedaRealparaBigDecimal( hidrometroActionForm.getVazaoNominal() ) ;
		}
		
		//Vazao Minima
		BigDecimal vazaoMinima = null;
		
		if (hidrometroActionForm.getVazaoMinima() != null 
				&& !hidrometroActionForm.getVazaoMinima().equals("") ) {
			vazaoMinima = Util.formatarMoedaRealparaBigDecimal( hidrometroActionForm.getVazaoMinima().replace("," , "." ) ) ;
		}
		
		//Nota Fiscal
		Integer notaFiscal = null;
		
		if ( hidrometroActionForm.getNotaFiscal() != null
				&& !hidrometroActionForm.getNotaFiscal().equals("") ) {
			notaFiscal = new Integer (hidrometroActionForm.getNotaFiscal() ) ;
		}
		
		//Tempo de Garantia
		Short tempoGarantiaAnos = null;
		
		if ( hidrometroActionForm.getTempoGarantiaAnos() != null
				&& !hidrometroActionForm.getTempoGarantiaAnos().equals("") ) {
			tempoGarantiaAnos = new Short (hidrometroActionForm.getTempoGarantiaAnos() ) ;
		}

		hidrometroAtualizado.setNumero(hidrometroActionForm
				.getNumeroHidrometro());
		hidrometroAtualizado.setDataAquisicao(dataAquisicao);
		hidrometroAtualizado.setAnoFabricacao(new Short(hidrometroActionForm
				.getAnoFabricacao()));
		hidrometroAtualizado.setIndicadorMacromedidor(new Short(
				hidrometroActionForm.getIndicadorMacromedidor()));
		hidrometroAtualizado.setNumeroDigitosLeitura(new Short(
				hidrometroActionForm.getIdNumeroDigitosLeitura()));
		hidrometroAtualizado.setVazaoTransicao(vazaoTransicao);
		hidrometroAtualizado.setVazaoNominal(vazaoNominal);
		hidrometroAtualizado.setVazaoMinima(vazaoMinima);
		hidrometroAtualizado.setNotaFiscal(notaFiscal);
		hidrometroAtualizado.setTempoGarantiaAnos(tempoGarantiaAnos);

		//Formatação para chamada do metodo "pesquisarNumeroHidrometroFaixaCount"
		String numeroFormatadoInicial = "";
		String numeroFormatadoFinal = "";

		numeroFormatadoInicial = Util.adicionarZerosEsquedaNumero(6,faixaInicial);
		numeroFormatadoFinal = Util.adicionarZerosEsquedaNumero(6,faixaFinal);
		
		String inicialFixo = fixo + numeroFormatadoInicial;
		String finalFixo =	fixo + numeroFormatadoFinal;
		
		Integer totalRegistros = fachada.pesquisarNumeroHidrometroFaixaCount(fixo,inicialFixo,finalFixo);

		//Verifica se o processo irá para batch
		if(totalRegistros>500){
			
		fachada.inserirProcessoAtualizarConjuntoHidrometro(fixo,faixaInicial,faixaFinal,hidrometroAtualizado,usuarioLogado,totalRegistros);

		// Método utilizado para montar a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Atualizar Conjunto de Hidrômetros enviado para Processamento", 
				"Voltar",
				"/exibirFiltrarHidrometroAction.do?menu=sim");
		}else{
		
		fachada.atualizarConjuntoHidrometro(fixo,faixaInicial,faixaFinal,hidrometroAtualizado,usuarioLogado);
			
		montarPaginaSucesso(httpServletRequest,totalRegistros+
				" Hidrômetro(s) com a numeração fixa igual a " + fixo + " atualizado(s) com sucesso.",
				"Realizar outra Manutenção de Hidrômetro",
				"exibirManterHidrometroAction.do?menu=sim");
		}
		
		// Remove objetos da sessão
		sessao.removeAttribute("HidrometroActionForm");
		sessao.removeAttribute("colecaoIntervalo");
		sessao.removeAttribute("colecaoHidrometroClasseMetrologica");
		sessao.removeAttribute("colecaoHidrometroMarca");
		sessao.removeAttribute("colecaoHidrometroDiametro");
		sessao.removeAttribute("colecaoHidrometroCapacidade");
		sessao.removeAttribute("colecaoHidrometroTipo");
		sessao.removeAttribute("hidrometros");
		sessao.removeAttribute("fixo");
		sessao.removeAttribute("faixaInicial");
		sessao.removeAttribute("faixaFinal");

		return retorno;
	}
}
