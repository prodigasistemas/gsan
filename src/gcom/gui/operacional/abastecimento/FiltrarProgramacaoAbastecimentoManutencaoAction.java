package gcom.gui.operacional.abastecimento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0414] - Informar Programação de Abastecimento e Manutenção
 * 
 * @author Rafael Pinto
 * @date 14/11/2006
 */
public class FiltrarProgramacaoAbastecimentoManutencaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = 
			actionMapping.findForward("exibirInformarProgramacaoAbastecimentoManutencaoAction");

		FiltrarProgramacaoAbastecimentoManutencaoActionForm form = 
			(FiltrarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		String mesAnoReferencia = form.getMesAnoReferencia();
		String idMunicipio = form.getMunicipio();
		this.pesquisarMunicipio(new Integer(idMunicipio),httpServletRequest);
		
		String idBairro = "";
		String areaBairro = "";
		if ( form.getBairro() != null && !form.getBairro().equals("") ) {
			
			idBairro = form.getBairro();
			this.pesquisarBairro(new Integer(idMunicipio),new Integer(idBairro),httpServletRequest);
			
			areaBairro = form.getAreaBairro();
			this.pesquisarAreaBairro(new Integer(areaBairro),httpServletRequest);
		}
		
		
		this.getSessao(httpServletRequest).setAttribute("mesAnoReferencia",mesAnoReferencia);

		Collection colecaoProgramacaoAbastecimento = 
			this.getFachada().consultarProgramacaoAbastecimento(idMunicipio, idBairro,areaBairro, mesAnoReferencia);

		Collection colecaoProgramacaoManutencao = 
			this.getFachada().consultarProgramacaoManutencao(idMunicipio, idBairro,areaBairro, mesAnoReferencia);
		
		this.getSessao(httpServletRequest).setAttribute("colecaoProgramacaoAbastecimento", colecaoProgramacaoAbastecimento);
		this.getSessao(httpServletRequest).setAttribute("colecaoProgramacaoManutencao", colecaoProgramacaoManutencao);
		this.getSessao(httpServletRequest).setAttribute("ultimaAlteracao", new Date());

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
	private void pesquisarMunicipio(Integer idMunicipio,HttpServletRequest httpServletRequest) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.ID,idMunicipio));

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

			this.getSessao(httpServletRequest).setAttribute("municipio",municipio);
			
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Município");
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
	private void pesquisarBairro(Integer idMunicipio,Integer idBairro,HttpServletRequest httpServletRequest) {
		
		//[FS0002] - Verificar informação do municipio
		if(idMunicipio == null || idMunicipio.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			throw new ActionServletException("atencao.filtrar_informar_municipio");	
		}
		
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO,idBairro));

		filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.MUNICIPIO_ID,idMunicipio));

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
			
			this.getSessao(httpServletRequest).setAttribute("bairro",bairro);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Bairro");
		}
	}	
	
	/**
	 * Pesquisa Area do Bairro pelo Id 
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void pesquisarAreaBairro(Integer idArea,HttpServletRequest httpServletRequest){
		
		// Parte que passa as coleções necessárias no jsp
		Collection colecaoAreaBairro = new ArrayList();
			
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID,idArea));

		filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.zonaAbastecimento");
		filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento");
		filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento");

		colecaoAreaBairro = 
			this.getFachada().pesquisar(filtroBairroArea, BairroArea.class.getName());

		if (colecaoAreaBairro != null && !colecaoAreaBairro.isEmpty()) {
			
			BairroArea bairroArea =
				(BairroArea) Util.retonarObjetoDeColecao(colecaoAreaBairro);
			
			this.getSessao(httpServletRequest).setAttribute("bairroArea",bairroArea);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Área do Bairro");
		}
		
	}	
}
