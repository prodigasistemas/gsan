package gcom.gui.operacional.abastecimento;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rafael Pinto
 * @date 13/11/2006
 */
public class ExibirFiltrarProgramacaoAbastecimentoManutencaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarProgramacaoAbastecimentoManutencao");

		FiltrarProgramacaoAbastecimentoManutencaoActionForm form = (FiltrarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		//[UC0075] - Pesquisar Municipio
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("1")) {

			// Faz a consulta de Municipio
			this.pesquisarMunicipio(form);
		}

		//[UC0141] - Pesquisar Bairro
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("2")) {

			// Faz a consulta de Documento Cobrança
			this.pesquisarBairro(form,httpServletRequest);
		}
		
		String mesAnoReferencia = form.getMesAnoReferencia();
		
		//[FS0004] - Validar mês e ano deferência
		if(mesAnoReferencia != null && !mesAnoReferencia.equals("")){
			
			if (!Util.validarMesAno(mesAnoReferencia)) {
				
				throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",
					null, mesAnoReferencia);
			}
		}
		
		this.setaRequest(httpServletRequest,form);
		
		return retorno;
	}
	
	/**
	 * [UC0075] - Pesquisar Municipio
	 * 
	 * [FS0001] - Verificar existência do município
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void pesquisarMunicipio(FiltrarProgramacaoAbastecimentoManutencaoActionForm form) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.ID,new Integer(form.getMunicipio())));

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoMunicipio = 
			this.getFachada().pesquisar(filtroMunicipio,Municipio.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

			form.setMunicipio(municipio.getId().toString());
			form.setNomeMunicipio(municipio.getNome());
			

		} else {
			form.setMunicipio(null);
			form.setNomeMunicipio("Município inexistente");
		}
	}
	
	/**
	 * [UC0075] - Pesquisar Bairro
	 * 
	 * [FS0002] - Verificar informação do municipio
	 * [FS0003] - Verificar existência do bairro
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void pesquisarBairro(FiltrarProgramacaoAbastecimentoManutencaoActionForm form,HttpServletRequest httpServletRequest) {
		
		//[FS0002] - Verificar informação do municipio
		String codigoMunicipio = form.getMunicipio();
		if(codigoMunicipio == null || codigoMunicipio.equals("")){
			throw new ActionServletException("atencao.filtrar_informar_municipio");	
		}
		
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO,new Integer(form.getBairro())));

		filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.MUNICIPIO_ID,new Integer(codigoMunicipio)));

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoBairro = 
			this.getFachada().pesquisar(filtroBairro,Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Bairro bairro = 
				(Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

			this.montarAreaBairroPorId(httpServletRequest,new Integer(bairro.getId()));			
			
			form.setBairro(""+bairro.getCodigo());
			form.setNomeBairro(bairro.getNome());

		} else {
			form.setBairro(null);
			form.setNomeBairro("Bairro inexistente");
		}
	}	
	
	/**
	 * Pesquisa Area do Bairro pelo Id 
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void montarAreaBairroPorId(HttpServletRequest request,Integer id){
		
		// Parte que passa as coleções necessárias no jsp
		Collection colecaoAreaBairro = new ArrayList();
			
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO,id));

		colecaoAreaBairro = 
			this.getFachada().pesquisar(filtroBairroArea, BairroArea.class.getName());

		if (colecaoAreaBairro != null && !colecaoAreaBairro.isEmpty()) {
			request.setAttribute("colecaoAreaBairro", colecaoAreaBairro);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Área do Bairro");
		}
		
	}
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 01/12/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			FiltrarProgramacaoAbastecimentoManutencaoActionForm form){
		
		//Municipio
		if(form.getMunicipio() != null && 
			!form.getMunicipio().equals("") && 
			form.getNomeMunicipio() != null && 
			!form.getNomeMunicipio().equals("")){
			
			httpServletRequest.setAttribute("municipioEncontrado","true");			
		}

		//Bairro
		if(form.getBairro() != null && 
			!form.getBairro().equals("") && 
			form.getNomeBairro() != null && 
			!form.getNomeBairro().equals("")){
					
			httpServletRequest.setAttribute("bairroEncontrado","true");
		}	
	}
}
