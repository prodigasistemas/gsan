package gcom.gui.cadastro;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.FiltroCepTipo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCepAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCepActionForm atualizarCepActionForm = (AtualizarCepActionForm) actionForm;

		Cep cep = (Cep) sessao.getAttribute("atualizarCep");
		
		Collection colecaoPesquisa = null;
		
        String codigo = Util.retirarFormatacaoCEP(atualizarCepActionForm.getCodigo());
        String cepTipo = atualizarCepActionForm.getCepTipo();    
        String municipio = atualizarCepActionForm.getMunicipio();
        String bairro = atualizarCepActionForm.getBairro();
        String LogradouroTipo = atualizarCepActionForm.getLogradouroTipo();
        String logradouro = atualizarCepActionForm.getLogradouro();
        String indicadordeUso = atualizarCepActionForm.getIndicadorUso();
        
        //Municipio é obrigatório.
        
        if (municipio == null || municipio.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.required", null, "Município");
        }
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.NOME, municipio));

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.INDICADOR_USO,
		        ConstantesSistema.INDICADOR_USO_ATIVO));

		//Retorna Municipio
		colecaoPesquisa = fachada.pesquisar(filtroMunicipio,
				Municipio.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    throw new ActionServletException(
		            "atencao.pesquisa.municipio_inexistente");
		}
        
        //Bairro é obrigatório.
        if (bairro == null || bairro.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.required", null, "Bairro");
        }
		FiltroBairro filtroBairro = new FiltroBairro();
		
		String bairroId = atualizarCepActionForm.getBairroId();
		String municipioId = atualizarCepActionForm.getMunicipioId();
		
		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.CODIGO, bairroId));
		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.NOME,bairro));
		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.MUNICIPIO_ID, municipioId));
		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.INDICADOR_USO,
		        ConstantesSistema.INDICADOR_USO_ATIVO));

		//Retorna Bairro
		colecaoPesquisa = fachada.pesquisar(filtroBairro,
				Bairro.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    throw new ActionServletException(
		            "atencao.pesquisa.bairro_inexistente");
		}
        
       	// Atualiza o Codigo
        cep.setCodigo(new Integer(codigo));
        
        // Atualiza o Bairro
        cep.setBairro(bairro);	

		// Verifica se o Tipo de CEP foi passado
		if (cepTipo != null
				&& !cepTipo.equalsIgnoreCase("")) {

			FiltroCepTipo filtroCepTipo = new FiltroCepTipo();

			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.ID, cepTipo));
			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna Tipo de CEP
			colecaoPesquisa = fachada.pesquisar(filtroCepTipo,
					CepTipo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.cep_tipo_inexistente");
			}
			CepTipo objetoCepTipo = (CepTipo) Util
					.retonarObjetoDeColecao(colecaoPesquisa);
			cep.setCepTipo(objetoCepTipo);
		}
        
        
        
        // Atualiza o Municipio
        cep.setMunicipio(municipio);
        
        //Atualiza o Logradouro
        cep.setLogradouro(logradouro);
        
        //Atualiza o Logradouro Tipo
        cep.setDescricaoTipoLogradouro(LogradouroTipo);
        
        // Atualiza o indicador de uso
        cep.setIndicadorUso(new Short(indicadordeUso));
		
	   	// Seta a data da alteração
	    cep.setUltimaAlteracao( new Date() );	
	    
		fachada.atualizar(cep);
	
		montarPaginaSucesso(httpServletRequest, "CEP "
				+ Util.formatarCEP(codigo) + " atualizado com sucesso.",
				"Realizar outra Manutenção de CEP ",
				"exibirFiltrarCepAction.do?menu=sim");        
	    
		return retorno;
	}
}
