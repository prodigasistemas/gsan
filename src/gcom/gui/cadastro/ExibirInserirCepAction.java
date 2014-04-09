package gcom.gui.cadastro;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.FiltroCep;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0883] Inserir CEP
 * 
 * @author Vinícius de Melo Medeiros
 * @created 10/02/2009
 */


public class ExibirInserirCepAction extends GcomAction {
	
    private Collection colecaoPesquisa;
	
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("inserirCep");

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirCepActionForm inserirCepActionForm = (InserirCepActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoCepTipo = null;
		Collection colecaoLogradouroTipo = null;
        String municipioId = inserirCepActionForm
        	.getMunicipioId();
		
       String objetoConsulta = (String) httpServletRequest
        .getParameter("objetoConsulta");

        if (objetoConsulta != null
        		&& !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            
            switch (Integer.parseInt(objetoConsulta)) {
            
            // Municipio

            case 1:

                FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

                filtroMunicipio
                        .adicionarParametro(new ParametroSimples(
                                FiltroMunicipio.ID,
                                municipioId));

                filtroMunicipio
                        .adicionarParametro(new ParametroSimples(
                                FiltroMunicipio.INDICADOR_USO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Municipio
                colecaoPesquisa = fachada.pesquisar(filtroMunicipio,
                        Municipio.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Municipio nao encontrado
                    //Limpa o campo MunicipioId do formulário
                	inserirCepActionForm.setMunicipioId("");
                	inserirCepActionForm
                            .setMunicipio("Municipio inexistente.");
                    httpServletRequest.setAttribute("corMunicipio",
                            "exception");
                    sessao.setAttribute("corMunicipio",
                    "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "municipioId");
                } else {
                    Municipio objetoMunicipio = (Municipio) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);
                    inserirCepActionForm.setMunicipioId(String
                            .valueOf(objetoMunicipio.getId()));
                    inserirCepActionForm
                            .setMunicipio(objetoMunicipio
                                    .getNome());
                    httpServletRequest.setAttribute("corMunicipio",
                            "valor");
                }
                
                break;
            
                case 2:
                    
                    String bairroId = inserirCepActionForm
                            .getBairroId();

                    FiltroBairro filtroBairro = new FiltroBairro();

        			filtroBairro.adicionarParametro(new ParametroSimples(
        					FiltroBairro.CODIGO, bairroId));
        			filtroBairro.adicionarParametro(new ParametroSimples(
        					FiltroBairro.MUNICIPIO_ID, municipioId));

                    filtroBairro
                            .adicionarParametro(new ParametroSimples(
                                    FiltroBairro.INDICADOR_USO,
                                    ConstantesSistema.INDICADOR_USO_ATIVO));

                    //Retorna Bairro
                    colecaoPesquisa = fachada.pesquisar(filtroBairro,
                            Bairro.class.getName());

                    if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                        //Bairro nao encontrado
                        //Limpa o campo BairroId do formulário
                    	inserirCepActionForm.setBairroId("");
                    	inserirCepActionForm
                                .setBairro("Bairro inexistente.");
                        httpServletRequest.setAttribute("corBairro",
                                "exception");
                        
                        httpServletRequest.setAttribute("nomeCampo", "bairroId");
                    } else {
                        Bairro objetoBairro = (Bairro) Util
                                .retonarObjetoDeColecao(colecaoPesquisa);
                        inserirCepActionForm.setBairroId(String
                                .valueOf(objetoBairro.getCodigo()));
                        inserirCepActionForm
                                .setBairro(objetoBairro
                                        .getNome());
                        httpServletRequest.setAttribute("corBairro",
                                "valor");
                    }
                    break;
                }
        }


        // Filtro de Tipo CEP para trazer apenas os que tiverem Indicador Uso = 1
        
		FiltroCepTipo filtroCepTipo = new FiltroCepTipo();

		filtroCepTipo.adicionarParametro(new ParametroSimples(
				FiltroCepTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCepTipo
				.setCampoOrderBy(FiltroCepTipo.ID);

		// Pesquisa a coleção de Tipo de CEP
		colecaoCepTipo = fachada.pesquisar(
				filtroCepTipo, CepTipo.class
						.getName());
        
        
		httpServletRequest.setAttribute("colecaoCepTipo",
				colecaoCepTipo);
		
		// Filtro de Tipo Logradouro
        colecaoLogradouroTipo = fachada.retornaListaLogradouroTipoCep();
        
        sessao.setAttribute("colecaoLogradouroTipo",
    			colecaoLogradouroTipo);
		
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirCepActionForm.setCepTipo("");
			inserirCepActionForm.setSigla("");
			inserirCepActionForm.setCodigo("");
			inserirCepActionForm.setMunicipioId("");
			inserirCepActionForm.setMunicipio("");
			inserirCepActionForm.setBairroId("");
			inserirCepActionForm.setBairro("");
			inserirCepActionForm.setLogradouroTipo("");
			inserirCepActionForm.setLogradouro("");
			
			if (inserirCepActionForm.getCodigo() == null
					|| inserirCepActionForm.getCodigo().equals("")) {

				Collection colecaoPesquisa = null;
					FiltroCep filtroCep = new FiltroCep();

				filtroCep.setCampoOrderBy(FiltroCep.CODIGO);
				
				colecaoPesquisa = fachada.pesquisar(filtroCep,
						Cep.class.getName());

			    // Verifica se há algum registro na tabela
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"CEP");
				} else {
					sessao.setAttribute("colecaoCep", colecaoPesquisa);
				}

				// Coleção de CEP
				FiltroCep filtroCep2 = new FiltroCep();
				filtroCep2.setCampoOrderBy(FiltroCep.CEPID);

				Collection colecaoCep2 = fachada.pesquisar(filtroCep2,
						Cep.class.getName());
				
				sessao.setAttribute("colecaoCep2", colecaoCep2);

			}

		}
		
		return retorno;
	}
}
