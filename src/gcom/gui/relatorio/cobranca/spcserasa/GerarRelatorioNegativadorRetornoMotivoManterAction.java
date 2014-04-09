package gcom.gui.relatorio.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.spcserasa.FiltrarNegativadorRetornoMotivoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.spcserasa.RelatorioManterNegativadorRetornoMotivo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
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
 * action responsável pela exibição do relatório de Tipo do Registro do Negativador manter
 * 
 * @author Yara Taciane
 * @created 26 de Fevereiro de 2008
 */
public class GerarRelatorioNegativadorRetornoMotivoManterAction extends
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

		FiltrarNegativadorRetornoMotivoActionForm form = (FiltrarNegativadorRetornoMotivoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroNegativadorRetornoMotivo filtro = (FiltroNegativadorRetornoMotivo) sessao.getAttribute("filtroNegativadorRetornoMotivo");

		// Inicio da parte que vai mandar os parametros para o relatório

		NegativadorRetornoMotivo parametros = new NegativadorRetornoMotivo();

		String idNegativador = (String) form.getIdNegativador();
		
		Short codigoMotivo = null;
		if (form.getCodigoMotivo() != null && !form.getCodigoMotivo().equals("")){
			codigoMotivo = Short.parseShort(form.getCodigoMotivo());
	    }
			
		String descricao = (String) form.getDescricaoRetornoMotivo();
		
		Short indicadorRegistroAceito = null;
	    if (form.getIndicadorRegistroAceito() != null && !form.getIndicadorRegistroAceito().equals("") && !form.getIndicadorRegistroAceito().equals(ConstantesSistema.TODOS.toString())){
				indicadorRegistroAceito = Short.parseShort( form.getIndicadorRegistroAceito());
	    }
		
	    Short indicadorUso = null;
		if (form.getIndicadorUso() != null && !form.getIndicadorUso().equals("") && !form.getIndicadorUso().equals(ConstantesSistema.TODOS.toString())){
			indicadorUso = Short.parseShort(form.getIndicadorUso());
	    }
	
		Negativador negativador = new Negativador();
		Cliente cliente = new Cliente();

		if (idNegativador != null && !idNegativador.equals("")) {
				FiltroNegativador filtroNegativador = new FiltroNegativador();

				filtroNegativador.adicionarParametro(new ParametroSimples(
						FiltroNegativador.ID, idNegativador));
				filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");

				Collection collNegativador = fachada.pesquisar(filtroNegativador,
						Negativador.class.getName());

				if (collNegativador != null && !collNegativador.isEmpty()) {
					// A Negativador foi encontrado
					Iterator it = collNegativador.iterator();

					Negativador negativadorPesquisa = (Negativador) it.next();

					negativador.setId(negativadorPesquisa.getId());
					
					cliente.setId(negativadorPesquisa.getCliente().getId());
					cliente.setNome(negativadorPesquisa.getCliente().getNome());
					
					negativador.setCliente(cliente);

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Negativador");
				}
			

		}

		
		// seta os parametros que serão mostrados no relatório

		negativador.setCliente(cliente);
		parametros.setNegativador(negativador);
		parametros.setDescricaoRetornocodigo(descricao);	
		parametros.setCodigoRetornoMotivo(codigoMotivo);
		parametros.setIndicadorRegistroAceito(indicadorRegistroAceito);
		parametros.setIndicadorUso(indicadorUso);
		

		
		
		
		
		// Fim da parte que vai mandar os parametros para o relatório

			// cria uma instância da classe do relatório
			RelatorioManterNegativadorRetornoMotivo relatorioManterNegativadorRetornoMotivo = new RelatorioManterNegativadorRetornoMotivo(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorioManterNegativadorRetornoMotivo.addParametro("filtroNegativadorRetornoMotivo", filtro);
			relatorioManterNegativadorRetornoMotivo.addParametro("negativadorRetornoMotivoParametros",parametros);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioManterNegativadorRetornoMotivo.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorioManterNegativadorRetornoMotivo,
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
