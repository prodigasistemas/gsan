package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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
 * [UC0389] - Inserir Imóvel Doação Action responsável pela pre-exibição da
 * pagina de inserir ImovelDoacao
 * 
 * @author César Araújo
 * @created 22 de agosto de 2006
 */
public class ExibirInserirImovelDoacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		/*** Declara e inicializa variáveis ***/
		ActionForward retorno                               = null;
		Fachada fachada                                     = null;
		HttpSession sessao                                  = null;
		ImovelDoacaoActionForm imovelDoacaoActionForm       = null;
		FiltroEntidadeBeneficente filtroEntidadeBeneficente = null;

		/*** Procedimentos básicos para execução do método ***/
		retorno = actionMapping.findForward("inserirImovelDoacao");
		imovelDoacaoActionForm = (ImovelDoacaoActionForm) actionForm;
		fachada = Fachada.getInstancia();
		sessao = httpServletRequest.getSession(false);

        /*** Define filtro pra pesquisar e alimenta a colecao de entidade beneficente ***/
		//----------------------------------------------------------------------
		// CRC933 
		// Autor: Yara T. Souza
		// Data : 06/01/2009
		//----------------------------------------------------------------------
		
		Collection<EntidadeBeneficente> colecaoEntidadeBeneficente = null;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");			
		filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();	
		
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");		
		filtroEntidadeBeneficente.adicionarParametro(new ParametroSimples(FiltroEntidadeBeneficente.ID_EMPRESA, 
				usuarioLogado.getEmpresa().getId()));
		colecaoEntidadeBeneficente =  fachada.pesquisar(filtroEntidadeBeneficente, 
				EntidadeBeneficente.class.getName());
	
		/*** Seta colecao de entidade beneficente na sessão ***/
		if(colecaoEntidadeBeneficente.size()>0){			
			sessao.setAttribute("colecaoEntidadeBeneficente", colecaoEntidadeBeneficente);
		}else{			
			filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();		
			filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");				
			colecaoEntidadeBeneficente =  fachada.pesquisar(filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());		
			sessao.setAttribute("colecaoEntidadeBeneficente", colecaoEntidadeBeneficente);			
		}		
		
		/*** Seta o valor sugerido com o valor que vem da tabela debito tipo ***/
		String qtdParcela = "";
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		String idEntidadeBeneficiente = httpServletRequest.getParameter("idEntidadeSelecionada");
		
		if ((idEntidadeBeneficiente != null) && (!idEntidadeBeneficiente.equals(""))){
			
			filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();
			filtroEntidadeBeneficente.adicionarParametro(
				new ParametroSimples(FiltroEntidadeBeneficente.ID, 
					new Integer(idEntidadeBeneficiente)));
			filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			
			colecaoEntidadeBeneficente =  
				fachada.pesquisar(filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());
			
			if (colecaoEntidadeBeneficente != null && colecaoEntidadeBeneficente.size() > 0 ){
				
				EntidadeBeneficente entidadeBeneficiente = 
					(EntidadeBeneficente)Util.retonarObjetoDeColecao(colecaoEntidadeBeneficente);
				
				if (entidadeBeneficiente.getAnoMesContratoFinal() != null && 
					!entidadeBeneficiente.getAnoMesContratoFinal().equals("") && 
					!Util.compararAnoMesReferencia(entidadeBeneficiente.getAnoMesContratoFinal(),
						sistemaParametro.getAnoMesFaturamento(),"<") ) {
					
					qtdParcela =  Util.retornaQuantidadeMeses(
						entidadeBeneficiente.getAnoMesContratoFinal(),
						sistemaParametro.getAnoMesFaturamento()).toString();
				}				
				
				Collection<DebitoTipo> colecaoDebitoTipo = null;
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, entidadeBeneficiente.getDebitoTipo().getId()));
				colecaoDebitoTipo =  
					fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());		
				
				if (colecaoDebitoTipo.size() > 0){
					
					DebitoTipo debitoTipo = (DebitoTipo)Util.retonarObjetoDeColecao(colecaoDebitoTipo);
					
					if(debitoTipo.getValorSugerido() != null){
						
						imovelDoacaoActionForm.setValorDoacao(debitoTipo.getValorSugerido().toString().replace(".", ","));
						imovelDoacaoActionForm.setQuantidadeParcela(qtdParcela);
						
						httpServletRequest.setAttribute("qtdParcelaMaxima",qtdParcela);
						httpServletRequest.setAttribute("existeAnoMesContratoFinal",true);
					}
				}
			}
		}else{
			imovelDoacaoActionForm.setValorDoacao("");
			imovelDoacaoActionForm.setQuantidadeParcela("");
		}
				
		/*** Valida se a coleção está preenchida ***/
		if (colecaoEntidadeBeneficente != null && colecaoEntidadeBeneficente.size() == 0) {
			throw new ActionServletException("atencao.naocadastrado", null, "Entidade Beneficente");		
		}
     
		//----------------------------------------------------------------------
		
        /*** Avalia se o código do imóvel digitado na página é válido ***/ 
		String idImovel = (String) imovelDoacaoActionForm.getIdImovel();
		if (idImovel != null && !idImovel.trim().equals("")) {
			String imovelEncontrado = fachada.pesquisarInscricaoImovel(new Integer(idImovel));
			if (imovelEncontrado != null && !imovelEncontrado.equalsIgnoreCase("")) {
				imovelDoacaoActionForm.setIdImovel(idImovel);
				imovelDoacaoActionForm.setInscricaoImovel(imovelEncontrado);
				httpServletRequest.setAttribute("corInscricao", "#000000");
			} else {
				httpServletRequest.setAttribute("corInscricao", "#ff0000");
				imovelDoacaoActionForm.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}
		}
		
		return retorno;
	}
	
}
