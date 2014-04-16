package gcom.gui.relatorio.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroQualidadeAgua;
import gcom.faturamento.QualidadeAgua;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.FiltrarQualidadeAguaActionForm;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioQualidadeAgua;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Flávio Leonardo
 */
public class GerarRelatorioQualidadeAguaAction extends ExibidorProcessamentoTarefaRelatorio {

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

		FiltrarQualidadeAguaActionForm form = (FiltrarQualidadeAguaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroQualidadeAgua filtroQualidadeAgua = (FiltroQualidadeAgua) sessao.getAttribute("filtroQualidadeAgua");

		// Inicio da parte que vai mandar os parametros para o relatório

		QualidadeAgua qualidadeParametros = new QualidadeAgua();

		String idLocalidade = (String) form.getIdLocalidade();
		String nomeLocalidade = (String) form.getNomeLocalidade();

		Localidade localidade = new Localidade();

		if (idLocalidade != null && !idLocalidade.equals("")) {
			if (nomeLocalidade != null && !nomeLocalidade.equals("")) {
				localidade.setId(new Integer(idLocalidade));
				localidade.setDescricao(nomeLocalidade);

			} else {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidade));

				Collection localidades = fachada.pesquisar(filtroLocalidade,
						Localidade.class.getName());

				if (localidades != null && !localidades.isEmpty()) {
					// A localidade foi encontrada
					Iterator localidadeIterator = localidades.iterator();

					Localidade localidadePesquisa = (Localidade) localidadeIterator
							.next();

					localidade.setId(localidadePesquisa.getId());
					localidade.setDescricao(localidadePesquisa.getDescricao());

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Localidade");
				}
			}

		}

		String idSetorComercial = (String) form.getCodigoSetor();
		String nomeSetorComercial = (String) form.getNomeSetor();

		SetorComercial setorComercial = new SetorComercial();

		if (idSetorComercial != null && !idSetorComercial.equals("")) {
			if (nomeSetorComercial != null && !nomeSetorComercial.equals("")) {
				setorComercial.setCodigo(new Integer(idSetorComercial));
				setorComercial.setDescricao(nomeSetorComercial);
			} else {
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercial));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

				Collection setoresComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (setoresComerciais != null && !setoresComerciais.isEmpty()) {
					// O Setor Comercial foi encontrado
					Iterator setorComercialIterator = setoresComerciais
							.iterator();

					SetorComercial setorComercialPesquisa = (SetorComercial) setorComercialIterator
							.next();

					setorComercial.setCodigo(setorComercialPesquisa.getCodigo());
					setorComercial.setDescricao(setorComercialPesquisa
							.getDescricao());

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Setor Comercial");
				}
			}

		}
		
		SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();
		
		if(form.getSistemaAbastecimento() != null && !form.getSistemaAbastecimento().equals("-1")){
			
			FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
			
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples
					(FiltroSistemaAbastecimento.ID,form.getSistemaAbastecimento()));
			
			Collection colecaoSistemaAbastecimneto = fachada.pesquisar
				(filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
			
			if(colecaoSistemaAbastecimneto != null && !colecaoSistemaAbastecimneto.isEmpty()){
				SistemaAbastecimento sistemaAbastecimentoPesquisa = (SistemaAbastecimento) 
					colecaoSistemaAbastecimneto.iterator().next();
				
				sistemaAbastecimento.setDescricaoAbreviada(sistemaAbastecimentoPesquisa.getDescricaoAbreviada());
				sistemaAbastecimento.setDescricao(sistemaAbastecimentoPesquisa.getDescricao());
				
			}
		}else{
			sistemaAbastecimento.setDescricaoAbreviada("");
		}

		// seta os parametros que serão mostrados no relatório

		setorComercial.setLocalidade(localidade);
		qualidadeParametros.setSetorComercial(setorComercial);
		qualidadeParametros.setSistemaAbastecimento(sistemaAbastecimento);
		
		// Fim da parte que vai mandar os parametros para o relatório

			// cria uma instância da classe do relatório
			RelatorioQualidadeAgua relatorioQualidadeAgua = new RelatorioQualidadeAgua(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorioQualidadeAgua.addParametro("filtroQualidadeAgua", filtroQualidadeAgua);
			relatorioQualidadeAgua.addParametro("qualidadeParametros",
					qualidadeParametros);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioQualidadeAgua.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorioQualidadeAgua,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
