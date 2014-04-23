package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.seguranca.acesso.usuario.Usuario;
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
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Classe responável pela validação dos dados que formarão a inscrição do imóvel 
 *
 * @author Raphael Rossiter
 * @date 12/05/2009
 */
public class AtualizarImovelLocalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm atualizarImovelLocalidadeActionForm = (DynaValidatorForm) actionForm;

		sessao.removeAttribute("gis");
		
		Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");

		String idLocalidade = null;
		String idSetorComercial = null;
		String idQuadra = null;
		String idQuadraFace = null;
		String lote = null;
		String subLote = null;
		String sequencialRota = null;

		
		//RECEBENDO AS INFORMACOES INSERIDAS NO FORMULARIO
		idLocalidade = (String) atualizarImovelLocalidadeActionForm.get("idLocalidade");
		idSetorComercial = (String) atualizarImovelLocalidadeActionForm.get("idSetorComercial");
		idQuadra = (String) atualizarImovelLocalidadeActionForm.get("idQuadra");
		idQuadraFace = (String) atualizarImovelLocalidadeActionForm.get("idQuadraFace");
		lote = (String) atualizarImovelLocalidadeActionForm.get("lote");
		subLote = (String) atualizarImovelLocalidadeActionForm.get("subLote");
		sequencialRota = (String)atualizarImovelLocalidadeActionForm.get("sequencialRota");
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Validacao dos dados
		ImovelAbaLocalidadeHelper helper = new ImovelAbaLocalidadeHelper();
		helper.setIdImovel(imovel.getId());
		helper.setIdLocalidade(idLocalidade);
		helper.setCodigoSetorComercial(idSetorComercial);
		helper.setNumeroQuadra(idQuadra);
		helper.setIdQuadraFace(idQuadraFace);
		helper.setLote(lote);
		helper.setSublote(subLote);
		helper.setUsuario((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO));
		
		ImovelAbaLocalidadeRetornoHelper resultado = fachada.validarImovelAbaLocalidade(helper);
		Collection setorComerciais = new ArrayList();
		setorComerciais.add(resultado.getSetorComercial());
		
		sessao.setAttribute("localidade", resultado.getLocalidade());
		sessao.setAttribute("setorComerciais", setorComerciais);
		sessao.setAttribute("setorComercial", resultado.getSetorComercial());
		sessao.setAttribute("quadra", resultado.getQuadra());
		sessao.setAttribute("quadraFace", resultado.getQuadraFace());
		sessao.setAttribute("quadraCaracteristicas", resultado.getQuadra());
		
		//validacao sequencial rota([FS0020])
		if(sequencialRota != null && !sequencialRota.trim().equals("")
				&& idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")
				&& idSetorComercial != null
				&& !idSetorComercial.trim().equalsIgnoreCase("")
				&& idQuadra != null && !idQuadra.trim().equalsIgnoreCase("")){
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.LOCALIDADE_ID, new Integer(idLocalidade)));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.SETOR_COMERCIAL_CODIGO, new Integer(
							idSetorComercial)));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.QUADRA_NUMERO, new Integer(idQuadra)));
			
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.NUMERO_SEQUENCIAL_ROTA, sequencialRota));
			
			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class
					.getName());
			
			StatusWizard statusWizard = (StatusWizard)sessao.getAttribute("statusWizard");
        	
        	 String idImovel = statusWizard.getId();
			
			if (!imoveis.isEmpty()) {
				Iterator iterator = imoveis.iterator();
				
				while(iterator.hasNext()){
					Imovel imovelSequencial = (Imovel)iterator.next();
					if(imovelSequencial.getNumeroSequencialRota().toString().equals(sequencialRota)
							&& !imovelSequencial.getId().toString().equals(idImovel)){
						
						retorno = montarPaginaConfirmacaoWizard(
	                            "atencao.imovel.ja.existe.na.sequencia.rota",
	                            httpServletRequest, actionMapping);
						
						//throw new ActionServletException("atencao.imovel.ja.existe.na.sequencia.rota",null);
										
					}
				}
			}
		}

		String localiade = Util.adicionarZerosEsquedaNumero(3, new Integer(
				idLocalidade.trim()).toString());
		String codigoSetorComercial = Util.adicionarZerosEsquedaNumero(3,
				new Integer(idSetorComercial.trim()).toString());
		String quadra = Util.adicionarZerosEsquedaNumero(3, new Integer(
				idQuadra.trim()).toString());
		String numeroLote = Util.adicionarZerosEsquedaNumero(4, new Integer(
				lote.trim()).toString());
		String numerosublote = Util.adicionarZerosEsquedaNumero(3, new Integer(
				subLote.trim()).toString());

		// atribui os valores q vem pelo request as variaveis
		atualizarImovelLocalidadeActionForm.set("idLocalidade", localiade);
		atualizarImovelLocalidadeActionForm.set("idSetorComercial", codigoSetorComercial);
		atualizarImovelLocalidadeActionForm.set("idQuadra", quadra);
		atualizarImovelLocalidadeActionForm.set("lote", numeroLote);
		atualizarImovelLocalidadeActionForm.set("subLote", numerosublote);
		
		

		

		return retorno;
	}
	
	

}
