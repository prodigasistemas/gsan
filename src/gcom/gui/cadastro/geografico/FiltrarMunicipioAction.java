package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0006]	FILTRAR MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 03/01/2007
 */

public class FiltrarMunicipioAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirManterMunicipio");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarMunicipioActionForm form = (FiltrarMunicipioActionForm) actionForm;
		
		
		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
		String codigoMunicipio = form.getCodigoMunicipio();
		String nomeMunicipio = form.getNomeMunicipio();
		String tipoPesquisa = form.getTipoPesquisa();
		String regiaoDesenv = form.getRegiaoDesenv();
		String regiao = form.getRegiao();
		String microregiao = form.getMicroregiao();
		String unidadeFederacao = form.getUnidadeFederacao();
		String indicadorUso = form.getIndicadorUso();
		String ordernarPor = form.getOrdernarPor();
		
		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		
		FiltroMunicipio filtroMunicipio = null;
		
		if(ordernarPor != null && ordernarPor.equals(ConstantesSistema.ORDENAR_POR_CODIGO)){
			filtroMunicipio = new FiltroMunicipio(FiltroMunicipio.ID);
		}else{
			filtroMunicipio = new FiltroMunicipio(FiltroMunicipio.NOME);
		}
		
		// Objetos que serão retornados pelo hibernate
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("regiaoDesenvolvimento");
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("microrregiao.regiao");
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
		
		boolean peloMenosUmParametroInformado = false;
		
		
		// Código do Município
		if (codigoMunicipio != null	&& !codigoMunicipio.trim().equals("")) {
			
			// [FS0004] - Verificando a existência do Municipio
			boolean achou = fachada.verificarExistenciaMunicipio(new Integer(codigoMunicipio));
			
			if (achou){
				peloMenosUmParametroInformado = true;
				filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, codigoMunicipio));
			}
		}
		
		
		// Nome do Municipio
		if (nomeMunicipio != null && !nomeMunicipio.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroMunicipio.adicionarParametro(new ComparacaoTextoCompleto(FiltroMunicipio.NOME, 
						nomeMunicipio));
			} else {
				
				filtroMunicipio.adicionarParametro(new ComparacaoTexto(FiltroMunicipio.NOME, nomeMunicipio));
			}
		}
		
		
		// Região de Desenvolvimento
		if (regiaoDesenv != null && !regiaoDesenv.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.
				NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.REGIAO_DESENVOLVOMENTO_ID,
					regiaoDesenv));
		}
		
		
		// Região 
		if (regiao != null && !regiao.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.REGIAO_ID, regiao));
		}
		
		
		// Microrregião
		if (microregiao != null && !microregiao.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.
				NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.MICRORREGICAO_ID, microregiao));
		}
		
		
		// Região de Desenvolvimento
		if (unidadeFederacao != null && !unidadeFederacao.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.
				NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.UNIDADE_FEDERACAO_ID, 
					unidadeFederacao));
		}
		
		
		// Indicador de Uso
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")&& !indicadorUso.equalsIgnoreCase("3")) {
			
			peloMenosUmParametroInformado = true;
			
			if (indicadorUso.equalsIgnoreCase(String.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				
				filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				
				filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		
		// Manda o filtro pela sessao para o
		// ExibirManterMunicipioAction
		sessao.setAttribute("filtroMunicipio", filtroMunicipio);
				
		return retorno;
		}
}

				
				
			
				
				
				
				
