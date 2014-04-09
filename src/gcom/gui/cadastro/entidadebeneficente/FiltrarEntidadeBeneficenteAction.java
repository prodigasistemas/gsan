package gcom.gui.cadastro.entidadebeneficente;


import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**

 * Prepara o filtro dos dados da entidade beneficente e aciona o caso de uso [UC0916] para fazer a consulta.

 *  

 * @author Hugo Fernando

 * @date 18/01/2010

 * @since 4.1.6.4

 *

 */

public class FiltrarEntidadeBeneficenteAction extends GcomAction{

	
	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		 ActionForward retorno = actionMapping.findForward("filtrarEntidadeBeneficente");

		 

		 
	  // Mudar isso quando implementar a parte de segurança
      HttpSession sessao = httpServletRequest.getSession(false);

      FiltrarEntidadeBeneficenteActionForm form = (FiltrarEntidadeBeneficenteActionForm) actionForm;

	  
	  
      // Recupera os parâmetros do form
	  Integer cliente            = form.getCliente().getId();
	  Integer tipoDebito         = form.getDebitoTipo().getId();
	  Integer empresa            = form.getEmpresa().getId();
	  String mesAnoAdesaoInic   = form.getInicioMesAnoAdesao();
	  String mesAnoAdesaoFim    = form.getFimMesAnoAdesao();
	  String indicadorUso       = ""+form.getIndicadorUso();

		
		
		FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();
		
		
		
		boolean peloMenosUmParametroInformado = false;
		
		
        // Insere os parâmetros informados no filtro
		if( cliente != null && cliente.intValue() > 0 ){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.ID_CLIENTE , cliente ) );
		}
		if( tipoDebito != null && tipoDebito.intValue() > 0  ){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.ID_DEBITO_TIPO , tipoDebito ) );
		}
		if( empresa != null && empresa.intValue() > 0 ){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.ID_EMPRESA , empresa ) );
		}
		if( mesAnoAdesaoInic != null && !mesAnoAdesaoInic.trim().equals("") ){
			peloMenosUmParametroInformado = true;
			
			String anoAdesaoInicio =  mesAnoAdesaoInic.substring(3);
			String mesAdesaoInicio = mesAnoAdesaoInic.substring(0,2);
			mesAnoAdesaoInic = anoAdesaoInicio+""+mesAdesaoInicio;
			
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.CONTRATO_INICIAL , new Integer(mesAnoAdesaoInic) ) );
		}
		if( mesAnoAdesaoFim != null && !mesAnoAdesaoFim.trim().equals("") ){
			peloMenosUmParametroInformado = true;
			
			String anoAdesaoFim =  mesAnoAdesaoFim.substring(3);
			String mesAdesaoFim = mesAnoAdesaoFim.substring(0,2);
			mesAnoAdesaoInic = anoAdesaoFim+""+mesAdesaoFim;
			
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.CONTRATO_FINAL , new Integer(mesAnoAdesaoInic )) );
		}
		if( indicadorUso != null && !indicadorUso.trim().equals("") && indicadorUso.equals("1")){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO ) );
		}
		else if( indicadorUso != null && !indicadorUso.trim().equals("") && indicadorUso.equals("2")){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_DESATIVO ) );
		}
		if(indicadorUso.equals("3") && peloMenosUmParametroInformado == false){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.setCampoOrderBy(FiltroEntidadeBeneficente.ID);
		}
		
		
		filtroEntidadeBeneficente.setCampoOrderBy(FiltroEntidadeBeneficente.ID);
		
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("empresa");
		
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if ( peloMenosUmParametroInformado == false) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		
		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterEntidadeBeneficenteAction e nele verificar se irá para o
		// atualizar ou para o manter.		
		if (form.getAtualizar() != null	&& form.getAtualizar()
				.equalsIgnoreCase("1")) {
	    httpServletRequest.setAttribute("atualizar",form.getAtualizar());
	
         }
		
		
		// Manda o filtro pela sessão para o ExibirManterEntidadeBeneficenteAction
		sessao.setAttribute("filtroEntidadeBeneficente", filtroEntidadeBeneficente);
		
		
		httpServletRequest.setAttribute("filtroEntidadeBeneficente", filtroEntidadeBeneficente);
		

	  	 
        // Devolve o mapeamento de retorno 
		return retorno;

	}	
}
