package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.ordemservico.GerarRelatorioBoletimOrdensServicoConcluidasActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioBoletimOrdensServicoConcluidas;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Ivan Sérgio
 * @created 07/05/2008
 * 
 * [UC0766] Gerar Relatorio Boletim de Ordens de Servico Concluidas
 * 
 */
public class GerarRelatorioBoletimOrdensServicoConcluidasAction extends
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

		GerarRelatorioBoletimOrdensServicoConcluidasActionForm form = 
			(GerarRelatorioBoletimOrdensServicoConcluidasActionForm) actionForm;

		// Recupera os valores do form para serem passados como parâmetros para o Relatorio
		String idFirma = form.getIdFirma();
		String nomeFirma = form.getNomeFirma();
		String idLocalidade = form.getIdLocalidade();
		String nomeLocalidade = form.getNomeLocalidade();
		String idSetorComercial = form.getIdSetorComercial();
		String codigoSetorComercial = form.getCodigoSetorComercial();
		String nomeSetorComercial = form.getNomeSetorComercial();
		String anoMesReferenciaEncerramento = form.getAnoMesReferenciaEncerramento();
		String situacao = form.getSituacao();

		// Valida a Localidade Informada
		if (idLocalidade != null && !idLocalidade.equals("")) {
			Localidade localidade = validarLocalidade(idLocalidade);
			
			if (nomeLocalidade == null || nomeLocalidade.equals("")) {
				nomeLocalidade = localidade.getDescricao();
			}
		}
		
		// Valida o Setor Informado
		if (codigoSetorComercial != null && !codigoSetorComercial.equals("")) {
			SetorComercial setorComercial = validarSetorComercial(idLocalidade, codigoSetorComercial);
			
			if (idSetorComercial == null || idSetorComercial.equals("")) {
				idSetorComercial = setorComercial.getId().toString();
			}
			if (nomeSetorComercial == null || nomeSetorComercial.equals("")) {
				nomeSetorComercial = setorComercial.getDescricao();
			}
		}
		
		String tipoGeracaoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		RelatorioBoletimOrdensServicoConcluidas relatorio = 
			new RelatorioBoletimOrdensServicoConcluidas(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		relatorio.addParametro("idFirma", idFirma);
		relatorio.addParametro("nomeFirma", nomeFirma);
		relatorio.addParametro("idLocalidade", idLocalidade);
		relatorio.addParametro("nomeLocalidade", nomeLocalidade);
		relatorio.addParametro("idSetorComercial", idSetorComercial);
		relatorio.addParametro("codigoSetorComercial", codigoSetorComercial);
		relatorio.addParametro("nomeSetorComercial", nomeSetorComercial);
		relatorio.addParametro("anoMesReferenciaEncerramento", anoMesReferenciaEncerramento);
		relatorio.addParametro("situacao", situacao);
		
		if (tipoGeracaoRelatorio == null) {
			tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
		
		retorno = processarExibicaoRelatorio(
				relatorio, tipoGeracaoRelatorio, httpServletRequest,
				httpServletResponse, actionMapping);

		return retorno;
	}

	private Localidade validarLocalidade(String localidade) {
		Localidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		FiltroLocalidade filtroLocalidade = null;
		Collection colecaoLocalidade = null;
		
		// Verifica se a Localidade existe
		if (localidade != null && !localidade.equals("")) {
			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidade));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Iterator iColecaoLocalidade = colecaoLocalidade.iterator();
				retorno = (Localidade) iColecaoLocalidade.next();
			}else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
			}
		}
		
		return retorno;
	}
	
	private SetorComercial validarSetorComercial(String localidade, String codigoSetorComercial) {
		SetorComercial retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		FiltroSetorComercial filtroSetorComercial = null;
		Collection colecaoSetorComercial = null;
		
		// Verifica se o Setor existe
		if (codigoSetorComercial != null && !codigoSetorComercial.equals("")) {
			filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, localidade));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
				Iterator iColecaoSetorComercial = colecaoSetorComercial.iterator();
				retorno = (SetorComercial) iColecaoSetorComercial.next();
			}else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
			}
		}
		
		return retorno;
	}
}
