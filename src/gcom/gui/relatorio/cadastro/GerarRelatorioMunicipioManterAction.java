package gcom.gui.relatorio.cadastro;

import java.util.Collection;

import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.geografico.FiltrarMunicipioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioManterMunicipio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioMunicipioManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		FiltrarMunicipioActionForm filtrarMunicipioActionForm = (FiltrarMunicipioActionForm) actionForm;

		FiltroMunicipio filtroMunicipio = (FiltroMunicipio) sessao
				.getAttribute("filtroMunicipio");
		

		// Inicio da parte que vai mandar os parametros para o relatório
		Fachada fachada = Fachada.getInstancia();
		
		Municipio municipioParametros = new Municipio();
		Regiao regiaoParametros = new Regiao();
		RegiaoDesenvolvimento regiaoDesenvolvimentoParametros = new RegiaoDesenvolvimento();
		UnidadeFederacao unidadeFederacaoParametros = new UnidadeFederacao();
		Microrregiao microrregiaoParametros = new Microrregiao();
		
		if(filtrarMunicipioActionForm.getUnidadeFederacao() !=null && !filtrarMunicipioActionForm.getUnidadeFederacao().equals("")){
			
			FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
			filtroUnidadeFederacao.adicionarParametro(new ParametroSimples(FiltroUnidadeFederacao.ID, filtrarMunicipioActionForm.getUnidadeFederacao()));
			
			Collection colecaoUnidadeFederacao = fachada.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());
			if(colecaoUnidadeFederacao != null && !colecaoUnidadeFederacao.isEmpty()){
				UnidadeFederacao unidadeFederacao = (UnidadeFederacao) Util.retonarObjetoDeColecao(colecaoUnidadeFederacao);
				unidadeFederacaoParametros = unidadeFederacao;
			}
		}
		
		String id = null;

		String idMunicipioPesquisar = (String) filtrarMunicipioActionForm.getCodigoMunicipio();

		if (idMunicipioPesquisar != null && !idMunicipioPesquisar.equals("")) {
			id = idMunicipioPesquisar;
		}
		
		Short indicadorUso = null;
		
		if(filtrarMunicipioActionForm.getIndicadorUso()!= null && !filtrarMunicipioActionForm.getIndicadorUso().equals("")){
			
			indicadorUso = new Short ("" + filtrarMunicipioActionForm.getIndicadorUso());
		}

		//regiao de desenvolvimento
		if (filtrarMunicipioActionForm.getRegiaoDesenv() != null && !filtrarMunicipioActionForm.getRegiaoDesenv().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroRegiaoDesenvolvimento filtroRegiaoDesenvolvimento = new FiltroRegiaoDesenvolvimento();
			filtroRegiaoDesenvolvimento.adicionarParametro(new ParametroSimples(FiltroRegiaoDesenvolvimento.ID, filtrarMunicipioActionForm.getRegiaoDesenv()));
			
			Collection colecaoMunicipio = fachada.pesquisar(filtroRegiaoDesenvolvimento, RegiaoDesenvolvimento.class.getName());
			
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				RegiaoDesenvolvimento regiaoDesenvolvimento = (RegiaoDesenvolvimento) Util.retonarObjetoDeColecao(colecaoMunicipio);
				regiaoDesenvolvimentoParametros = regiaoDesenvolvimento;
			}
			
		}
		//regiao
		if (filtrarMunicipioActionForm.getRegiao() != null && !filtrarMunicipioActionForm.getRegiao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroRegiao filtroRegiao = new FiltroRegiao();
			filtroRegiao.adicionarParametro(new ParametroSimples(FiltroRegiao.ID, filtrarMunicipioActionForm.getRegiao()));
			
			Collection colecaoMunicipio = fachada.pesquisar(filtroRegiao, Regiao.class.getName());
			
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				Regiao regiao = (Regiao) Util.retonarObjetoDeColecao(colecaoMunicipio);
				regiaoParametros = regiao;
			}
			
		}
		
		//microrregiao
		if (filtrarMunicipioActionForm.getMicroregiao() != null && !filtrarMunicipioActionForm.getMicroregiao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();
			filtroMicrorregiao.adicionarParametro(new ParametroSimples(FiltroMicrorregiao.ID, filtrarMunicipioActionForm.getMicroregiao()));
			
			Collection colecaoMunicipio = fachada.pesquisar(filtroMicrorregiao, Microrregiao.class.getName());
			
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				Microrregiao microrregiao = (Microrregiao) Util.retonarObjetoDeColecao(colecaoMunicipio);
				microrregiaoParametros = microrregiao;
			}
			
		}
		
		String nome = null;
		if(filtrarMunicipioActionForm.getNomeMunicipio() != null && !filtrarMunicipioActionForm.getNomeMunicipio().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			nome = filtrarMunicipioActionForm.getNomeMunicipio();
		}
		
		
		// seta os parametros que serão mostrados no relatório
		municipioParametros.setNome(nome);
		municipioParametros.setId(id == null ? null : new Integer(
				id));
		municipioParametros.setIndicadorUso(indicadorUso);
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
	
		RelatorioManterMunicipio relatorioManterMunicipio = new RelatorioManterMunicipio(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterMunicipio.addParametro("filtroMunicipio",
				filtroMunicipio);
		relatorioManterMunicipio.addParametro("municipioParametros",
				municipioParametros);
		relatorioManterMunicipio.addParametro("regiaoParametros",
				regiaoParametros);
		relatorioManterMunicipio.addParametro("regiaoDesenvolvimentoParametros",
				regiaoDesenvolvimentoParametros);
		relatorioManterMunicipio.addParametro("unidadeFederacaoParametros",
				unidadeFederacaoParametros);
		relatorioManterMunicipio.addParametro("microrregiaoParametros",
				microrregiaoParametros);
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterMunicipio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterMunicipio,
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
