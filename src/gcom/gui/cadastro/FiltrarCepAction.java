package gcom.gui.cadastro;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0884]Filtrar Cep
 * 
 * @author Vinícius Medeiros
 * @date 12/02/2009
 */

public class FiltrarCepAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarCep");

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarCepActionForm filtrarCepActionForm = (FiltrarCepActionForm) actionForm;

		FiltroCep filtroCep = new FiltroCep();

		boolean informouCodigo = false;
		
		String codigo = filtrarCepActionForm.getCodigo();
		String codigoCep = null;
		
		if(codigo.length() == 10){
			codigoCep = Util.retirarFormatacaoCEP(filtrarCepActionForm.getCodigo());
		} else if (codigo.length() == 8){
			codigoCep = codigo;
		}
		
		if (codigoCep != null && !codigoCep.trim().equals("")) {
			
			//boolean achou = fachada.verificarExistenciaAgente(new Integer(codigoCep));
			
			//if (achou) {
				informouCodigo = true;
				filtroCep.adicionarParametro(
						new ParametroSimples(FiltroCep.CODIGO, 
								codigoCep));
			
			//}
		}
		
		Collection<Cep> colecaoCep = fachada.pesquisar(
				filtroCep, Cep.class.getName());

		// Verificar a existencia de um CEP
		if (colecaoCep.isEmpty()) {
			
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"CEP");
		}

		// Filtragem sem parametros
		if (!informouCodigo == true) {
		
			throw new ActionServletException(
					"atencao.cep_deve_ser_informado");
		}
		
		// Pesquisa sem registros
		if (colecaoCep == null || colecaoCep.isEmpty()) {
			
			throw new ActionServletException("atencao.cep.inexistente");
		
		} else {
		
			httpServletRequest.setAttribute("colecaoCep",colecaoCep);
			Cep cep = new Cep();
			cep = (Cep) Util.retonarObjetoDeColecao(colecaoCep);
			String idRegistroAtualizacao = cep.getCepId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
			httpServletRequest.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroCep", filtroCep);

		httpServletRequest.setAttribute("filtroCep",filtroCep);

		return retorno;

	}
}
