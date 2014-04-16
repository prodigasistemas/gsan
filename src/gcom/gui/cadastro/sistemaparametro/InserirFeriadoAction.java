package gcom.gui.cadastro.sistemaparametro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0534]	INSERIR FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 12/01/2007
 */

public class InserirFeriadoAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirFeriadoActionForm inserirFeriadoActionForm = (InserirFeriadoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String municipio = inserirFeriadoActionForm.getIdMunicipio();
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		
		if (municipio != null && !municipio.trim().equals("")) {

			FiltroTabelaAuxiliarAbreviada filtroMunicipio = new FiltroTabelaAuxiliarAbreviada();

			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, municipio));

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
	
			if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
				inserirFeriadoActionForm.setIdMunicipio("");
				throw new ActionServletException("atencao.municipio_inexistente");
			}
		}
		
		MunicipioFeriado municipioFeriado = null;
		NacionalFeriado nacionalFeriado = null;
		
		String nomeFeriado = null; 
		String tipoFeriado = null;
		
		if (municipio != null && !municipio.trim().equals("")) {
			municipioFeriado = new MunicipioFeriado();
			
			inserirFeriadoActionForm.setFormValuesMunicipal( municipioFeriado);
			nomeFeriado= "Municipal";
			tipoFeriado= "2";
		} else {
			nacionalFeriado = new NacionalFeriado();
			
			inserirFeriadoActionForm.setFormValuesNacional( nacionalFeriado);
			nomeFeriado = "Nacional";
			tipoFeriado= "1";
		}
		
		
		
		//Inserir na base de dados Feriado
		Integer idFeriado = fachada.inserirFeriado(nacionalFeriado, municipioFeriado,usuarioLogado);
		
		sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarFeriadoAction.do");
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Feriado "+ nomeFeriado + " de código " + idFeriado +" inserido com sucesso.",
				"Inserir outro Feriado","exibirInserirFeriadoAction.do?menu=sim",
				"exibirAtualizarFeriadoAction.do?tipoFeriado="+tipoFeriado+"&idRegistroInseridoAtualizar="+ idFeriado,"Atualizar Feriado Inserido");
		
		
		sessao.removeAttribute("InserirFeriadoActionForm");

		return retorno;
	}
}
