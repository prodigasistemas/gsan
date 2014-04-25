package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1180] Gerar Relatório de Imóveis Com Leituras
 * 
 * @author Magno Gouveia
 * @date 03/06/2011
 */
public class ExibirGerarRelatorioImoveisComLeiturasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioImoveisComLeituras");

		GerarRelatorioImoveisComLeiturasActionForm form = (GerarRelatorioImoveisComLeiturasActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		if (Util.verificarNaoVazio(objetoConsulta)){
			
			// Faz a consulta de Localidade: 1 - Inicial || 3 - Final
			if(objetoConsulta.trim().equals("1") || objetoConsulta.trim().equals("3")) {
				this.pesquisarLocalidade(form, objetoConsulta);
			}
			
			// Faz a consulta de Setor Comercial: 2 - Inicial || 4 - Final
			if(objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("4")){
				this.pesquisarSetorComercial(form, objetoConsulta);
			}
		}

		FiltroFaturamentoGrupo filtroFaturamentroGrupo = new FiltroFaturamentoGrupo(FiltroFaturamentoGrupo.ID);
		filtroFaturamentroGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentroGrupo, FaturamentoGrupo.class.getName());
		if(colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()){
			sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Grupo de Faturamento");
		}

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_LEITURA, ConstantesSistema.SIM));
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.SIM));
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		if(colecaoEmpresa != null && !colecaoEmpresa.isEmpty()){
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		}
		
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
		if(Util.verificarIdNaoVazio(form.getEmpresa())){
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, form.getEmpresa()));
		}
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
		filtroLeiturista.setCampoOrderBy(FiltroLeiturista.FUNCIONARIO_NOME, FiltroLeiturista.CLIENTE_NOME);
		
		Collection colecaoLeituristaAuxiliar = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
		ArrayList<DadosLeiturista> colecaoLeiturista = new ArrayList<DadosLeiturista>();
		
		if (colecaoLeituristaAuxiliar != null && !colecaoLeituristaAuxiliar.isEmpty()) {
			Iterator it = colecaoLeituristaAuxiliar.iterator();
	
			Leiturista leiturista = null;
			String nomeLeiturista = null;
			while (it.hasNext()) {
				leiturista = (Leiturista) it.next();
				if (leiturista.getCliente() != null) {
					nomeLeiturista = leiturista.getCliente().getNome();
				} else {
					nomeLeiturista = leiturista.getFuncionario().getNome();
				}
				colecaoLeiturista.add(new DadosLeiturista(leiturista.getId(), nomeLeiturista));
			}
		}
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
		
		// Seta os request´s encontrados
		this.setaRequest(httpServletRequest, form);

		return retorno;
	}

	/**
	 * Pesquisa Localidade
	 * 
	 * @author Rafael Pinto
	 * @date 02/01/2008
	 */
	private void pesquisarLocalidade(GerarRelatorioImoveisComLeiturasActionForm form, String objetoConsulta) {

		Object local = form.getLocalidadeInicial();

		if (!objetoConsulta.trim().equals("1")) {
			local = form.getLocalidadeFinal();
		}

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, local));

		// Recupera Localidade
		Collection colecaoLocalidade = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

			if (objetoConsulta.trim().equals("1")) {
				form.setLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			}

			form.setLocalidadeFinal(localidade.getId().toString());
			form.setNomeLocalidadeFinal(localidade.getDescricao());

		} else {
			if (objetoConsulta.trim().equals("1")) {
				form.setLocalidadeInicial(null);
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");

				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
			} else {
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
		}
	}

	/**
	 * Pesquisa Setor comercial
	 * 
	 * @author Rafael Pinto
	 * @date 02/01/2008
	 */
	private void pesquisarSetorComercial(GerarRelatorioImoveisComLeiturasActionForm form, String objetoConsulta) {

		Object local = form.getLocalidadeInicial();
		Object setor = form.getSetorComercialInicial();

		if (!objetoConsulta.trim().equals("2")) {
			local = form.getLocalidadeFinal();
			setor = form.getSetorComercialFinal();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setor));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE, local));
		Collection colecaoSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

			if (objetoConsulta.trim().equals("2")) {
				form.setSetorComercialInicial("" + setorComercial.getCodigo());
				form.setNomeSetorComercialInicial(setorComercial.getDescricao());
			}

			form.setSetorComercialFinal("" + setorComercial.getCodigo());
			form.setNomeSetorComercialFinal(setorComercial.getDescricao());
		} else {

			if (objetoConsulta.trim().equals("2")) {
				form.setSetorComercialInicial(null);
				form.setNomeSetorComercialInicial("Setor Comercial Inicial inexistente");
				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal(null);
			} else {
				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal("Setor Comercial Final inexistente");
			}
		}
	}

	/**
	 * Seta os request com os id encontrados
	 * 
	 * @author Rafael Pinto
	 * @date 02/01/2008
	 */
	private void setaRequest(HttpServletRequest httpServletRequest, GerarRelatorioImoveisComLeiturasActionForm form) {

		// Localidade Inicial
		if (Util.verificarNaoVazio(form.getLocalidadeInicial())
				&& Util.verificarNaoVazio(form.getNomeLocalidadeInicial())) {

			httpServletRequest.setAttribute("localidadeInicialEncontrada", true);
			httpServletRequest.setAttribute("localidadeFinalEncontrada", true);
		} else {

			if (form.getLocalidadeFinal() != null
					&& !form.getLocalidadeFinal().equals("")
					&& form.getNomeLocalidadeFinal() != null
					&& !form.getNomeLocalidadeFinal().equals("")) {

				httpServletRequest.setAttribute("localidadeFinalEncontrada", true);
			}
		}

		// Setor Comercial Inicial
		if (Util.verificarNaoVazio(form.getSetorComercialInicial())
				&& Util.verificarNaoVazio(form.getNomeSetorComercialInicial())) {

			httpServletRequest.setAttribute("setorComercialInicialEncontrado", true);
			httpServletRequest.setAttribute("setorComercialFinalEncontrado", true);
		} else if (Util.verificarNaoVazio(form.getSetorComercialFinal())
				&& Util.verificarNaoVazio(form.getNomeSetorComercialFinal())) {

			httpServletRequest.setAttribute("setorComercialFinalEncontrado", true);
		}
	}
}
