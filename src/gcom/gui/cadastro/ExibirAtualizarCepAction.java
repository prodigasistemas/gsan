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
 * @author Vinicius Medeiros
 * @date 12/02/2009
 */
public class ExibirAtualizarCepAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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
	
	private String municipioId;
	
	private String bairroId;
	
	private Collection colecaoPesquisa;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("cepAtualizar");

		AtualizarCepActionForm atualizarCepActionForm =
			(AtualizarCepActionForm) actionForm;

		// Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		String objetoConsulta = (String) httpServletRequest
			.getParameter("objetoConsulta");
		
		// Mudar quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String id = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			// Recebe o valor do campo municipioId do formulário.
			municipioId = atualizarCepActionForm.getMunicipioId();
			
			// Recebe o valor do campo bairroId do formulário.
			bairroId = atualizarCepActionForm.getBairroId();

			switch (Integer.parseInt(objetoConsulta)) {

				// Municipio
				case 1:
	
					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
	
					filtroMunicipio.adicionarParametro(
							new ParametroSimples(FiltroMunicipio.ID,
									municipioId));
	
					filtroMunicipio.adicionarParametro(
							new ParametroSimples(FiltroMunicipio.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));
	
					// Retorna Municipio
					colecaoPesquisa = fachada.pesquisar(
							filtroMunicipio,Municipio.class.getName());
	
					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						// Municipio nao encontrado
						// Limpa o campo MunicipioId do formulário
						atualizarCepActionForm.setMunicipioId("");
						atualizarCepActionForm.setMunicipio("Municipio inexistente.");
						httpServletRequest.setAttribute("corMunicipio","exception");
	
						httpServletRequest.setAttribute("nomeCampo","municipioId");
						
					} else {
						
						Municipio objetoMunicipio = 
							(Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
						
						atualizarCepActionForm.setMunicipioId(
								String.valueOf(objetoMunicipio.getId()));
						atualizarCepActionForm.setMunicipio(
								objetoMunicipio.getNome());
						
						httpServletRequest.setAttribute("corMunicipio","valor");
						httpServletRequest.setAttribute("nomeCampo","municipioId");
	
					}
	
					break;
					
					// Bairro
					case 2:
	
						FiltroBairro filtroBairro = new FiltroBairro();
		
	        			filtroBairro.adicionarParametro(new ParametroSimples(
	        					FiltroBairro.CODIGO, bairroId));
	        			filtroBairro.adicionarParametro(new ParametroSimples(
	        					FiltroBairro.MUNICIPIO_ID, municipioId));
		
						filtroBairro.adicionarParametro(
								new ParametroSimples(FiltroBairro.INDICADOR_USO,
										ConstantesSistema.INDICADOR_USO_ATIVO));
		
						// Retorna Bairro
						colecaoPesquisa = fachada.pesquisar(
								filtroBairro, Bairro.class.getName());
		
						if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	
							// Bairro nao encontrado
							// Limpa o campo BairroId do formulário
							atualizarCepActionForm.setBairroId("");
							atualizarCepActionForm.setBairro("Bairro inexistente.");
							httpServletRequest.setAttribute("corBairro","exception");
		
							httpServletRequest.setAttribute("nomeCampo","bairroId");
							
						} else {
							
							Bairro objetoBairro = 
								(Bairro) Util.retonarObjetoDeColecao(colecaoPesquisa);
							
							atualizarCepActionForm.setBairroId(
									String.valueOf(objetoBairro.getCodigo()));
							atualizarCepActionForm.setBairro(
									objetoBairro.getNome());
							
							httpServletRequest.setAttribute("corBairro","valor");
							httpServletRequest.setAttribute("nomeCampo","bairroId");
		
						}
	
					break;
	
				}
			}
	
		Cep cep = new Cep();

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0 && objetoConsulta == null) {

			FiltroCep filtroCep = new FiltroCep();
			filtroCep.adicionarParametro(new ParametroSimples(
					FiltroCep.CEPID, id));
			
			filtroCep.adicionarCaminhoParaCarregamentoEntidade("cepTipo");
			
			Collection colecaoCep = fachada.pesquisar(
					filtroCep, Cep.class.getName());
			if (colecaoCep != null
					&& !colecaoCep.isEmpty()) {
				cep = (Cep) Util
						.retonarObjetoDeColecao(colecaoCep);
			}

			if(cep.getMunicipio() != null){
				
				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
				
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.NOME,  cep.getMunicipio()));
				
				// Pesquisa a coleção de Siglas
				Collection colecaoMunicipio = fachada.pesquisar(
						filtroMunicipio, Municipio.class
								.getName());
				
				if(!colecaoMunicipio.isEmpty()){
					Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
				
					atualizarCepActionForm.setMunicipio(municipio.getNome());
					atualizarCepActionForm.setMunicipioId(municipio.getId().toString());
				}
				
				if(cep.getBairro() != null){
					
					FiltroBairro filtroBairro = new FiltroBairro();
					
					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.NOME,  cep.getBairro()));
					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.MUNICIPIO,  cep.getMunicipio()));
					
					// Pesquisa a coleção de Siglas
					Collection colecaoBairro = fachada.pesquisar(
							filtroBairro, Bairro.class
									.getName());
					
					if(!colecaoBairro.isEmpty()){
						Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
					
						atualizarCepActionForm.setBairro(bairro.getNome());
					
						int bairroCodigo = bairro.getCodigo();
						atualizarCepActionForm.setBairroId(String.valueOf(bairroCodigo));
					}
					
					sessao.setAttribute("colecaoBairro",
							colecaoBairro);
				}
				
				
				
				
				sessao.setAttribute("colecaoMunicipio",
						colecaoMunicipio);
				
			}
			
			
	        // Filtro de Tipo CEP para trazer apenas os que tiverem Indicador Uso = 1
	        
			FiltroCepTipo filtroCepTipo = new FiltroCepTipo();

			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCepTipo
					.setCampoOrderBy(FiltroCepTipo.ID);

			// Pesquisa a coleção de Tipo de CEP
			Collection colecaoCepTipo = fachada.pesquisar(
					filtroCepTipo, CepTipo.class
							.getName());
	        
			sessao.setAttribute("colecaoCepTipo",
					colecaoCepTipo);
			
			//	Filtro de Tipo Logradouro
	        Collection colecaoLogradouroTipo = fachada.retornaListaLogradouroTipoCep();
	        
	    	sessao.setAttribute("colecaoLogradouroTipo",
	    			colecaoLogradouroTipo);
			
			if (id == null || id.trim().equals("")) {

				atualizarCepActionForm.setCepId(cep
						.getCepId().toString());

				atualizarCepActionForm
						.setCodigo(cep.getCodigo().toString());

				atualizarCepActionForm
						.setCepTipo(cep
								.getCepTipo().toString());
				
				atualizarCepActionForm
						.setMunicipio(cep.getMunicipio());
				
				atualizarCepActionForm
						.setBairro(cep.getBairro());
				
				atualizarCepActionForm
						.setLogradouroTipo(cep
								.getDescricaoTipoLogradouro());
				
				atualizarCepActionForm
						.setLogradouro(cep
								.getLogradouro());
				
				atualizarCepActionForm
						.setIndicadorUso(cep
								.getIndicadorUso().toString());
				

			}

			atualizarCepActionForm.setCepId(id);
			
			atualizarCepActionForm.setCodigo(Util.formatarCEP(cep.getCodigo().toString()));
			
			atualizarCepActionForm.setCepTipo(cep
					.getCepTipo().getId().toString());

			atualizarCepActionForm
					.setMunicipio(cep
							.getMunicipio());

			atualizarCepActionForm
					.setBairro(cep
							.getBairro());
			
			atualizarCepActionForm
					.setLogradouroTipo(cep
							.getDescricaoTipoLogradouro());
			
			atualizarCepActionForm
					.setLogradouro(cep
							.getLogradouro());
			
			atualizarCepActionForm.setIndicadorUso(""
					+ cep.getIndicadorUso());

			sessao.setAttribute("atualizarCep", cep);

			if (sessao.getAttribute("colecaoCep") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarCepAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarCepAction.do");
			}

		}

		return retorno;
	}
	
	}
