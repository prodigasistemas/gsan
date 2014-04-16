package gcom.gui.faturamento;

import gcom.faturamento.FiltroFaturamentoGrupoCronogramaMensal;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class FiltrarFaturamentoCronogramaAction extends GcomAction {

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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("retornarFiltroFaturamentoCronograma");

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        
       // Fachada fachada = Fachada.getInstancia();
        //Variaveis
        String idFaturamentoGrupo = (String) pesquisarActionForm
                .get("idGrupoFaturamento");
        
    	FiltroFaturamentoGrupoCronogramaMensal filtroFaturamentoGrupoCronogramaMensal = new FiltroFaturamentoGrupoCronogramaMensal();
        
        sessao.removeAttribute("indicadorAtualizar");
        
        String mesAno = (String)pesquisarActionForm.get("mesAno");
        /**if(mesAno != null && !mesAno.trim().equalsIgnoreCase("")){
        	String mes = mesAno.substring(0, 2);
        	String ano = mesAno.substring(3, 7);
        	String anoMes = ano+mes;
        	httpServletRequest.setAttribute("anoMes", anoMes);
        /*	filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ANO_MES_REFERENCIA,
        			ano+mes));*/
                
	     /**   FiltroFaturamentoGrupo filtroGrupo = new FiltroFaturamentoGrupo();
	        
	        filtroGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID,
	        		idFaturamentoGrupo));
	        Collection grupos = fachada.pesquisar(filtroGrupo, FaturamentoGrupo.class.getName());
	        
	        if(!grupos.isEmpty()){
	        	int anomes = Integer.parseInt(anoMes);
	        	FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)grupos.iterator().next();
	        	if(anomes < faturamentoGrupo.getAnoMesReferencia().intValue()){
	        		throw new ActionServletException("atencao.faturamento.mes_ano_filtrar_inferior");	        		
	        	}
	        }*/
	      
        //}
        
        String indicadorAtualizacao = (String)sessao.getAttribute("indicadorAtualizar");
        
        if(httpServletRequest.getParameter("indicadorAtualizar") != null
        		&& !httpServletRequest.getParameter("indicadorAtualizar").trim().equals("")){
        	indicadorAtualizacao = "1";
        }else{
        	indicadorAtualizacao = "";
        }
        	sessao.setAttribute("indicadorAtualizar", indicadorAtualizacao);
        
        boolean parametroInformado = false;
        	
        if (idFaturamentoGrupo != null
                && !idFaturamentoGrupo.trim().equalsIgnoreCase("")
                && !idFaturamentoGrupo.trim().equalsIgnoreCase("-1")) {
            //Vai pegar o faturamento grupo para compara a data com o
            // faturamento grupo cronograma mensal
            filtroFaturamentoGrupoCronogramaMensal.adicionarParametro(new ParametroSimples(
                    FiltroFaturamentoGrupoCronogramaMensal.ID_FATURAMENTO_GRUPO, idFaturamentoGrupo));
            
            parametroInformado = true;
        }
        if(mesAno != null && !mesAno.trim().equals("")){
        	String mes = mesAno.substring(0, 2);
        	String ano = mesAno.substring(3, 7);
        	String anoMes = ano+mes;
        	
        	filtroFaturamentoGrupoCronogramaMensal.adicionarParametro(new ParametroSimples(
        			FiltroFaturamentoGrupoCronogramaMensal.REFERENCIA, anoMes));
        	
        	parametroInformado = true;
        }
        
        if(parametroInformado){
        	sessao.setAttribute("filtroFaturamentoGrupoCronogramaMensal",
        			filtroFaturamentoGrupoCronogramaMensal);
        }else{
        	throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
        }
        

        return retorno;
    }

}
