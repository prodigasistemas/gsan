package gcom.gui.relatorio.faturamento;

import gcom.micromedicao.bean.MonitorarLeituraMobilePopupHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioMonitorarLeituraMobile;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class GerarRelatorioMonitorarLeituraMobileAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection<MonitorarLeituraMobilePopupHelper> colHelper =
			( Collection<MonitorarLeituraMobilePopupHelper> ) sessao.getAttribute( "colecao" );
		
		String anoMes = (String)sessao.getAttribute( "anoMes");
		String grupoFaturamento = (String)sessao.getAttribute( "grupoFaturamento");
		String nomeLocalidade = (String)sessao.getAttribute( "nomeLocalidade");
		String nomeEmpresa = (String)sessao.getAttribute( "nomeEmpresa");
		String nomeLeiturista = (String)sessao.getAttribute( "nomeLeiturista");
		String tipoServico = (String)sessao.getAttribute( "tipoServico");
		String situacaoTextoLeitura = (String)sessao.getAttribute( "situacaoTextoLeitura");
		String idRota = (String)sessao.getAttribute( "idRota");
		String cdRota = (String)sessao.getAttribute( "cdRota");
		String tipoMedicao = (String)sessao.getAttribute( "tipoMedicao");
		String imovelImpresso = (String)sessao.getAttribute( "imovelImpresso");
		
/*
		Fachada fachada = Fachada.getInstancia();

		FiltroBairro filtroBairro = (FiltroBairro) sessao.getAttribute("filtroBairro");

		// Inicio da parte que vai mandar os parametros para o relatório
		Bairro bairroParametros = new Bairro();

		String idMunicipio = (String) pesquisarActionForm.get("idMunicipio");

		Municipio municipio = null;

		if (idMunicipio != null && !idMunicipio.equals("")) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipios = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipios != null && !municipios.isEmpty()) {
				// O municipio foi encontrado
				Iterator municipioIterator = municipios.iterator();

				municipio = (Municipio) municipioIterator.next();

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Município");
			}

		} else {
			municipio = new Municipio();
		}

		int codigoBairro = 0;

		String codigoBairroPesquisar = (String) pesquisarActionForm
				.get("codigoBairro");

		if (codigoBairroPesquisar != null && !codigoBairroPesquisar.equals("")) {
			codigoBairro = Integer.parseInt(codigoBairroPesquisar);
		}

		Short indicadorDeUso = null;

		if (pesquisarActionForm.get("indicadorUso") != null
				&& !pesquisarActionForm.get("indicadorUso").equals("")) {

			indicadorDeUso = new Short(""
					+ pesquisarActionForm.get("indicadorUso"));
		}

		// seta os parametros que serão mostrados no relatório
		bairroParametros.setMunicipio(municipio);
		bairroParametros.setCodigo(codigoBairro);
		bairroParametros.setNome("" + pesquisarActionForm.get("nomeBairro"));
		bairroParametros.setIndicadorUso(indicadorDeUso);
*/
		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioMonitorarLeituraMobile relatorio = new RelatorioMonitorarLeituraMobile((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorio.addParametro("colHelper", colHelper);
		
		relatorio.addParametro("anoMes", anoMes );
	    relatorio.addParametro("grupoFaturamento", grupoFaturamento );
	    relatorio.addParametro("nomeLocalidade", nomeLocalidade );
	    relatorio.addParametro("nomeEmpresa", nomeEmpresa );
	    relatorio.addParametro("nomeLeiturista", nomeLeiturista );
	    relatorio.addParametro("tipoServico", tipoServico );
	    relatorio.addParametro("situacaoTextoLeitura", situacaoTextoLeitura );
	    relatorio.addParametro("idRota", idRota );
	    relatorio.addParametro("cdRota", cdRota );
	    relatorio.addParametro("tipoMedicao", tipoMedicao );
	    relatorio.addParametro("imovelImpresso", imovelImpresso );

	    if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
