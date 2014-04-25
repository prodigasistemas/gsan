package gcom.gui.cobranca;

import java.util.Iterator;
import java.util.List;

import gcom.cobranca.CobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.InformarItensContratoServicoHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCobrancaGrupoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCobrancaGrupoActionForm form = (AtualizarCobrancaGrupoActionForm) actionForm;

		CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) sessao.getAttribute("atualizarCobrancaGrupo");
		
		
		String idCobrancaGrupo = form.getId();	
		
		//Descrição
		String descricao = form.getDescricao();
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		// Descrição Abreviada
		String descricaoAbreviada = form.getDescricaoAbreviada();
		if (descricaoAbreviada == null || 
			"".equals(descricaoAbreviada ) ) {
			throw new ActionServletException("atencao.required", null,"Descrição Abreviada");
		}
		
		//Ano Mes Referencia
		String anoMesReferencia = form.getAnoMesReferencia();
		if ( anoMesReferencia == null || anoMesReferencia.equals("") ) {
			throw new ActionServletException("atencao.required", null,"Mês/Ano de Referência");
		}
        
		//Indicador de Uso
        Short indicadorUso = form.getIndicadorUso();
        if ( indicadorUso != null && !indicadorUso.equals("") ) {
        	indicadorUso = form.getIndicadorUso() ;
		} 
        
		String emailResponsavel = form.getEmailResponsavel();

		//Ano Mes Referencia
		Short indicadorExecucaoAutomatica = form.getIndicadorExecucaoAutomatica();
		if ( indicadorExecucaoAutomatica == null || indicadorExecucaoAutomatica.equals("") ) {
			throw new ActionServletException("atencao.required", null, "Execução Automática" );
		}
		
		ContratoEmpresaServico contratoEmpresaServico = null;
		if (form.getIdNumeroContrato() != null && !form.getIdNumeroContrato().equals("")
				&& sessao.getAttribute("collectionContrato") != null
				&& !sessao.getAttribute("collectionContrato").equals("")) {
			List colecaoHelper = (List) sessao.getAttribute("collectionContrato");
			int posicaoComponente = new Integer(form.getIdNumeroContrato());
				
			Iterator iColecaoHelper = colecaoHelper.iterator();
			
			while (iColecaoHelper.hasNext()){
				
				InformarItensContratoServicoHelper helper = (InformarItensContratoServicoHelper)iColecaoHelper.next();
					
				if (helper.getContratoEmpresaServico() != null && 
						helper.getContratoEmpresaServico().getId() == posicaoComponente){
					
					contratoEmpresaServico = helper.getContratoEmpresaServico();
				}
				
			}
			
		}
		
        //Concatena ano mes para insercao
        String mes = anoMesReferencia.substring(0, 2);
        String ano = anoMesReferencia.substring(3, 7);

        anoMesReferencia = ano + "" + mes;
        
        cobrancaGrupo.setId(new Integer( idCobrancaGrupo ));
        cobrancaGrupo.setDescricao(descricao);
        cobrancaGrupo.setDescricaoAbreviada(descricaoAbreviada);
        cobrancaGrupo.setAnoMesReferencia(new Integer( anoMesReferencia ));
        cobrancaGrupo.setIndicadorUso(indicadorUso);
        cobrancaGrupo.setIndicadorExecucaoAutomatica(indicadorExecucaoAutomatica);
        cobrancaGrupo.setEmailResponsavel(emailResponsavel);
        cobrancaGrupo.setContratoEmpresaServico(contratoEmpresaServico);
		fachada.atualizar(cobrancaGrupo);

		
		montarPaginaSucesso(httpServletRequest, "Grupo de Cobrança "
				+ idCobrancaGrupo + " atualizado com sucesso.",
				"Realizar outra Manutenção de Grupo de Cobrança ",
				"exibirFiltrarCobrancaGrupoAction.do?menu=sim");        
        
		return retorno;
	}
}
