package gcom.gui.cobranca;


import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.InformarItensContratoServicoHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Arthur Carvalho
 * @date 14/08/2009
 */
public class InserirCobrancaGrupoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um Grupo de Cobranca
	 * 
	 * [UC0929] Inserir Grupo de Cobranca
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirCobrancaGrupoActionForm form = (InserirCobrancaGrupoActionForm) actionForm;

		Collection colecaoPesquisa = null;

		// Descrição
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
			throw new ActionServletException("atencao.required", null, "Mês/Ano de Referência" );
		}
		
		//Ano Mes Referencia
		String indicadorExecucaoAutomatica = form.getIndicadorExecucaoAutomatica();
		if ( indicadorExecucaoAutomatica == null || indicadorExecucaoAutomatica.equals("") ) {
			throw new ActionServletException("atencao.required", null, "Execução Automática" );
		}

		//E-mail do Funcionário Responsável
		String emailResponsavel = form.getEmailResponsavel();
		
		/*
		
		InformarItensContratoServicoHelper helper = null;
		ContratoEmpresaServico contratoEmpresaServico = null;
		Iterator iterator = null;
		
		iterator = colecaoHelper.iterator();
		
		while (iterator.hasNext()) {
			helper = (InformarItensContratoServicoHelper) iterator.next();
			contratoEmpresaServico = helper.getContratoEmpresaServico();
			
		}*/
		ContratoEmpresaServico contratoEmpresaServico = null;
		if (form.getIdNumeroContrato() != null && !form.getIdNumeroContrato().equals("")) {
			HttpSession sessao = httpServletRequest.getSession(false);
			List colecaoHelper = (List) sessao.getAttribute("collectionContrato");
			int posicaoComponente = new Integer(form.getIdNumeroContrato());
	    	
	    	if (colecaoHelper.size() >= posicaoComponente) {
	    		
				InformarItensContratoServicoHelper helper = (InformarItensContratoServicoHelper) 
					colecaoHelper.get(posicaoComponente-1);
				
				contratoEmpresaServico = helper.getContratoEmpresaServico();
				
				helper.setContratoEmpresaServico(contratoEmpresaServico);
				
				colecaoHelper.remove(posicaoComponente-1);
	    		colecaoHelper.add(helper);
	    	}
		}
		
		
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.adicionarParametro(
			new ParametroSimples(FiltroCobrancaGrupo.DESCRICAO, descricao));
		
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.grupo_cobranca_ja_cadastrada", null,descricao);
		} else {
			
			CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();
			String mes = anoMesReferencia.substring(0, 2);
			String ano = anoMesReferencia.substring(3, 7);
			anoMesReferencia = ano + "" + mes;
			
			cobrancaGrupo.setAnoMesReferencia(new Integer(anoMesReferencia));
			cobrancaGrupo.setDescricao(descricao);
			cobrancaGrupo.setDescricaoAbreviada(descricaoAbreviada);
			cobrancaGrupo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			cobrancaGrupo.setUltimaAlteracao(new Date());
			cobrancaGrupo.setContratoEmpresaServico(contratoEmpresaServico);
			cobrancaGrupo.setEmailResponsavel(emailResponsavel);
			cobrancaGrupo.setIndicadorExecucaoAutomatica(new Short(indicadorExecucaoAutomatica));

			Integer idCobrancaGrupo = (Integer) this.getFachada().inserir(cobrancaGrupo);

			montarPaginaSucesso(httpServletRequest,
					"Grupo de Cobrança " + descricao
							+ " inserido com sucesso.",
					"Inserir outro Grupo de Cobrança ",
					"exibirInserirCobrancaGrupoAction.do?menu=sim",
					"exibirAtualizarCobrancaGrupoAction.do?idRegistroAtualizacao="
							+ idCobrancaGrupo,
					"Atualizar Grupo de Cobrança Inserido");

			this.getSessao(httpServletRequest).removeAttribute("InserirCobrancaGrupoActionForm");
			this.getSessao(httpServletRequest).removeAttribute("collectionContrato");

			return retorno;
		}

	}
}		
