package gcom.gui.faturamento.consumotarifa;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.TarifaTipoCalculo;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Tiago Moreno
 */
public class InserirConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirConsumoTarifaActionForm inserirConsumoTarifaActionForm = (InserirConsumoTarifaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String descTarifa = inserirConsumoTarifaActionForm.getDescTarifa();
		String slcDescTarifa = inserirConsumoTarifaActionForm
				.getSlcDescTarifa();
		String dataVigencia = inserirConsumoTarifaActionForm.getDataVigencia();
		
		String idLigacaoAguaPerfil = inserirConsumoTarifaActionForm.getIdLigacaoAguaPerfil();
		
		String idTarifaTipoCalculo = inserirConsumoTarifaActionForm.getIdTarifaTipoCalculo();

		// Carregando o objeto consumoTarifa
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();

		if ((descTarifa == null) || (descTarifa.equalsIgnoreCase(""))) {
			consumoTarifa.setId(new Integer(slcDescTarifa));
		} else {
			consumoTarifa.setDescricao(descTarifa);
			consumoTarifa
					.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			consumoTarifa.setUltimaAlteracao(new Date());
		}
		
		if (!(idLigacaoAguaPerfil==null) && !(idLigacaoAguaPerfil.equals("")) && (new Integer(idLigacaoAguaPerfil)>0)){
			LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
			ligacaoAguaPerfil.setId(new Integer(idLigacaoAguaPerfil));
			consumoTarifa.setLigacaoAguaPerfil(ligacaoAguaPerfil);
		}
		
		if (!(idTarifaTipoCalculo==null) && !(idTarifaTipoCalculo.equals("")) && (new Integer(idTarifaTipoCalculo)>0)){
			TarifaTipoCalculo tarifaTipoCalculo = new TarifaTipoCalculo();
			tarifaTipoCalculo.setId(new Integer(idTarifaTipoCalculo));
			consumoTarifa.setTarifaTipoCalculo(tarifaTipoCalculo);
		}

		// Carregando o objeto consumoTarifaVigencia

		// Data de vigência da tarifa
		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		Date dataVencimentoTarifa;

		try {
			dataVencimentoTarifa = formatoData.parse(dataVigencia);
		} catch (ParseException ex) {
			dataVencimentoTarifa = null;
		}

		ConsumoTarifaVigencia consumoTarifaVigencia = new ConsumoTarifaVigencia();

		consumoTarifaVigencia.setDataVigencia(dataVencimentoTarifa);
		if (consumoTarifa.getId() != null) {
			consumoTarifaVigencia.setConsumoTarifa(consumoTarifa);
		}
		consumoTarifaVigencia.setUltimaAlteracao(new Date());

		// OBS - O objeto ConsumoTarifa será carregado no controlador

		Collection<CategoriaFaixaConsumoTarifaHelper> colecaoConsumoTarifaCategoriaHelper = new ArrayList();
		Collection<ConsumoTarifaCategoria> colecaoConsumoTarifaCategoria = new ArrayList();

		if (sessao.getAttribute("colecaoConsumoTarifaCategoria") != null
				&& !((Collection) sessao
						.getAttribute("colecaoConsumoTarifaCategoria"))
						.isEmpty()) {
			colecaoConsumoTarifaCategoriaHelper = (Collection) sessao
					.getAttribute("colecaoConsumoTarifaCategoria");
			for (CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper : colecaoConsumoTarifaCategoriaHelper) {
				String descricaoCategoria = categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getCategoria().getDescricao();
				String consumoMinimo = httpServletRequest.getParameter("ValorConMinimo."+descricaoCategoria);
				String tarifaMinimo = httpServletRequest.getParameter("ValorTarMin."+descricaoCategoria);
				if(consumoMinimo != null && !consumoMinimo.equals("")){
					categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().
					setNumeroConsumoMinimo(new Integer(consumoMinimo));
				}else{
					throw new ActionServletException(
					"atencao.consumo_minimo_vazio");
				}
				
				if(tarifaMinimo != null && !tarifaMinimo.equals("")){
					categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().
					setValorTarifaMinima(Util
							.formatarMoedaRealparaBigDecimal(tarifaMinimo));
				}else{
					throw new ActionServletException(
					"atencao.tarifa_minimo_vazio");
				}
				
				colecaoConsumoTarifaCategoria
						.add(categoriaFaixaConsumoTarifaHelper
								.getConsumoTarifaCategoria());

			}
		} else {
			throw new ActionServletException("atencao.nenhuma_categoria_tarifa");
		}

		/*Collection<ConsumoTarifaFaixa> colecaoTarifaFaixa = new ArrayList();
		if (sessao.getAttribute("colecaoFaixa") != null) {
			colecaoTarifaFaixa = (Collection) sessao
					.getAttribute("colecaoFaixa");
		}*/

		fachada.inserirConsumoTarifa(consumoTarifa, consumoTarifaVigencia,
				colecaoConsumoTarifaCategoria);

		if (consumoTarifa.getDescricao() == null) {
			String idConsumo = inserirConsumoTarifaActionForm
					.getSlcDescTarifa();
			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();

			filtroConsumoTarifa.adicionarParametro(new ParametroSimples(
					FiltroConsumoTarifa.ID, idConsumo));
			Collection colecaoConsumoSelect = (Collection) fachada.pesquisar(
					filtroConsumoTarifa, ConsumoTarifa.class.getName());

			ConsumoTarifa consumoSelect = (ConsumoTarifa) gcom.util.Util
					.retonarObjetoDeColecao(colecaoConsumoSelect);

			montarPaginaSucesso(httpServletRequest, consumoSelect
					.getDescricao()
					+ " de vigência "
					+ gcom.util.Util.formatarData(consumoTarifaVigencia
							.getDataVigencia()) + " inserida com sucesso.",
					"Inserir outra Tarifa de Consumo",
					"exibirInserirConsumoTarifaAction.do?menu=sim",
					"exibirManterConsumoTarifaExistenteAction.do?idVigencia="
							+ consumoTarifaVigencia.getId().toString(),
					"Atualizar Tarifa de Consumo Inserida");

		} else {
			montarPaginaSucesso(httpServletRequest, consumoTarifa
					.getDescricao()
					+ " de vigência "
					+ gcom.util.Util.formatarData(consumoTarifaVigencia
							.getDataVigencia()) + " inserida com sucesso.",
					"Inserir outra Tarifa de Consumo",
					"exibirInserirConsumoTarifaAction.do?menu=sim",
					"exibirManterConsumoTarifaExistenteAction.do?idVigencia="
							+ consumoTarifaVigencia.getId().toString(),
					"Atualizar Tarifa de Consumo Inserida");
		}

		return retorno;
	}
}
