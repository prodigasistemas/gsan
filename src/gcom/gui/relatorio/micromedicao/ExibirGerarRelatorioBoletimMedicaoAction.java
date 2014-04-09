package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroContratoEmpresaServico;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1054] - Gerar Relatório Boletim de Medição
 * 
 * @author Hugo Leonardo
 *
 * @date 04/08/2010
 */

public class ExibirGerarRelatorioBoletimMedicaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioBoletimMedicao");
		
		GerarRelatorioBoletimMedicaoForm form = 
			(GerarRelatorioBoletimMedicaoForm) actionForm;
		
		if (form.getFormaGeracao() == null
				|| form.getFormaGeracao().equals("")) {
			form.setFormaGeracao("3");
		}
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("1") || objetoConsulta.trim().equals("3"))  ) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form, objetoConsulta);
		}
		
		this.pesquisarEmpresa(httpServletRequest);
		
		if(form.getEmpresa() != null && !form.getEmpresa().equals("")){
			this.pesquisarContrato(httpServletRequest, form);
		}
		
		this.pesquisarGerenciaRegional(httpServletRequest);
		
		// Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);

		return retorno;
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioBoletimMedicaoForm form){
		
		// Localidade Inicial
		if(form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") && 
			form.getNomeLocalidadeInicial() != null && 
			!form.getNomeLocalidadeInicial().equals("")){
					
			httpServletRequest.setAttribute("localidadeInicialEncontrada","true");
			httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
		}else{

			if(form.getLocalidadeFinal() != null && 
				!form.getLocalidadeFinal().equals("") && 
				form.getNomeLocalidadeFinal() != null && 
				!form.getNomeLocalidadeFinal().equals("")){
								
				httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
			}
		}
	}
	
	private void pesquisarEmpresa(HttpServletRequest httpServletRequest){
		
		/*
		FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();
		
		filtroContratoEmpresaServico.setConsultaSemLimites(true);
		
		filtroContratoEmpresaServico.adicionarParametro(
				new ParametroNulo(FiltroContratoEmpresaServico.DATA_FIM_CONTRATO, ParametroSimples.CONECTOR_OR));
		
		filtroContratoEmpresaServico.adicionarParametro(
				new MaiorQue(FiltroContratoEmpresaServico.DATA_FIM_CONTRATO, new Date()));
		
		filtroContratoEmpresaServico.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoEmpresaServico.EMPRESA);
		filtroContratoEmpresaServico.setCampoOrderBy(FiltroContratoEmpresaServico.DESCRICAO);		

		Collection colecaoEmpresa = 
			this.getFachada().pesquisar(filtroContratoEmpresaServico, ContratoEmpresaServico.class.getName());

		*/
		
		Collection<Empresa> colecaoEmpresa = this.getFachada().pesquisarEmpresasContratoServico();
		
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		} else {
			httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	private void pesquisarContrato(HttpServletRequest httpServletRequest,  GerarRelatorioBoletimMedicaoForm form){
		
		FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();
		
		filtroContratoEmpresaServico.setConsultaSemLimites(true);
		
		filtroContratoEmpresaServico.adicionarParametro(
				new ParametroSimples(FiltroContratoEmpresaServico.EMPRESA, form.getEmpresa()));

		filtroContratoEmpresaServico.setCampoOrderBy(FiltroContratoEmpresaServico.DESCRICAO);		

		Collection colecaoContratoEmpresaServico = 
			this.getFachada().pesquisar(filtroContratoEmpresaServico, ContratoEmpresaServico.class.getName());

		if (colecaoContratoEmpresaServico == null || colecaoContratoEmpresaServico.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Contrato");
		} else {
			httpServletRequest.setAttribute("colecaoContrato", colecaoContratoEmpresaServico);
		}
	}
	
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());

		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	private void pesquisarLocalidade( GerarRelatorioBoletimMedicaoForm form, String objetoConsulta) {

		Object local = form.getLocalidadeInicial();
			
		if(!objetoConsulta.trim().equals("1")){
			local = form.getLocalidadeFinal();
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID, local));
		
		// Recupera Localidade
		Collection colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = 
				(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			}
			
			form.setLocalidadeFinal(localidade.getId().toString());
			form.setNomeLocalidadeFinal(localidade.getDescricao());

			
		} else {
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidadeInicial(null);
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");
				
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
			}else{
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
		}
	}
}
