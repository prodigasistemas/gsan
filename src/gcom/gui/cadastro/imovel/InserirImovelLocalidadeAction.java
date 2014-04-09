package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Classe responável pela validação dos dados que formarão a inscrição do imóvel 
 *
 * @author Raphael Rossiter
 * @date 11/05/2009
 */
public class InserirImovelLocalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("gerenciadorProcesso");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm inserirImovelLocalidadeActionForm = (DynaValidatorForm) sessao
		.getAttribute("InserirImovelActionForm");

		String idLocalidade = null;
		String idSetorComercial = null;
		String idQuadra = null;
		String idQuadraFace = null;
		String lote = null;
		String subLote = null;
		String sequencialRota = null;

		// RECEBENDO AS INFORMACOES INSERIDAS NO FORMULARIO
		idLocalidade = (String) inserirImovelLocalidadeActionForm.get("idLocalidade");
		idSetorComercial = (String) inserirImovelLocalidadeActionForm.get("idSetorComercial");
		idQuadra = (String) inserirImovelLocalidadeActionForm.get("idQuadra");
		idQuadraFace = (String) inserirImovelLocalidadeActionForm.get("idQuadraFace");
		lote = (String) inserirImovelLocalidadeActionForm.get("lote");
		subLote = (String) inserirImovelLocalidadeActionForm.get("subLote");
		sequencialRota = (String)inserirImovelLocalidadeActionForm.get("sequencialRota");

		sessao.removeAttribute("gis");
		
		//Verificar a existencia de Setor alternativo
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
    			idSetorComercial ) );
    	
    	Collection setorComercial = this.getFachada()
    			.pesquisar( filtroSetorComercial, SetorComercial.class.getName() );

    	Iterator iteratorSetorComercial = setorComercial.iterator();
    	SetorComercial setor = null;
    
    	while ( iteratorSetorComercial.hasNext() ) {
    	
    		setor = (SetorComercial) iteratorSetorComercial.next();
            
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) &&
    				setor.getLocalidade().getId().equals( new Integer( idLocalidade ) ) &&
    						  ("" + setor.getCodigo()).equals(idSetorComercial) )  {
    			
    			throw new ActionServletException("atencao.setor_comercial_alternativo_nao_pode_ser_informado");
    		}
    	}
    	
		
		// ENCAPSULANDO AS INFORMAÇÕES DO FORMULARIO PARA VALIDAÇÃO
		ImovelAbaLocalidadeHelper helper = new ImovelAbaLocalidadeHelper();
		
		helper.setIdLocalidade(idLocalidade);
		helper.setCodigoSetorComercial(idSetorComercial);
		helper.setNumeroQuadra(idQuadra);
		helper.setIdQuadraFace(idQuadraFace);
		helper.setLote(lote);
		helper.setSublote(subLote);
		helper.setUsuario((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO));
		
		// VALIDANDO AS INFORMAÇÕES DO FORMULÁRIO
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
			if (!imoveis.isEmpty()) {
				Imovel imovelSequencial = (Imovel)Util.retonarObjetoDeColecao(imoveis);
				if(imovelSequencial.getNumeroSequencialRota().toString().equals(sequencialRota)){
					
					retorno = montarPaginaConfirmacaoWizard(
                            "atencao.imovel.ja.existe.na.sequencia.rota",
                            httpServletRequest, actionMapping);
									
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
		inserirImovelLocalidadeActionForm.set("idLocalidade", localiade);
		inserirImovelLocalidadeActionForm.set("idSetorComercial", codigoSetorComercial);
		inserirImovelLocalidadeActionForm.set("idQuadra", quadra);
		inserirImovelLocalidadeActionForm.set("lote", numeroLote);
		inserirImovelLocalidadeActionForm.set("subLote", numerosublote);
		
		

		httpServletRequest.setAttribute("destino", "inserirImovelEndereco");

		return retorno;
	}

}
