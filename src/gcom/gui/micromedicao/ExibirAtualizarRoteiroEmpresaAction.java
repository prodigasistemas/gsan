package gcom.gui.micromedicao;

import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.RoteiroEmpresa;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir atulizar roteiro 
 * @author Francisco do Nascimento
 * @created 20/09/07
 */

public class ExibirAtualizarRoteiroEmpresaAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	    	
    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("atualizarRoteiroEmpresa");
        InserirRoteiroEmpresaActionForm form = (InserirRoteiroEmpresaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);

    	FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEsferaPoder.DESCRICAO);
    	filtroEmpresa.setConsultaSemLimites(true);  	
    	filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO,
    			ConstantesSistema.INDICADOR_USO_ATIVO));  	
    	Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, 
    			Empresa.class.getName());    	
    	sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
        
        //Pesquisando grupo de faturamento
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA);
        Collection<FaturamentoGrupo> collectionFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
        //httpServletRequest.setAttribute("collectionFaturamentoGrupo", collectionFaturamentoGrupo);
        sessao.setAttribute("colecaoFaturamentoGrupo", collectionFaturamentoGrupo);
        //Fim de pesquisando grupo de faturamento
       
        String idLocalidadeDigitado = form.getIdLocalidade();
        
        String idRoteiroEmpresa = null;
		if ( httpServletRequest.getParameter("reloadPage") == null ||  
				httpServletRequest.getParameter("reloadPage").equals("")){
			//Recupera o id do Rota que vai ser atualizado            
            if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
            	idRoteiroEmpresa = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
            	//Definindo a volta do botão Voltar p Filtrar Rota
    	    	sessao.setAttribute("voltar", "filtrar");
    	    	sessao.setAttribute("idRegistroAtualizacao",idRoteiroEmpresa);
            }else if(httpServletRequest.getParameter("idRegistroAtualizacao") == null){
            	idRoteiroEmpresa = (String)sessao.getAttribute("idRegistroAtualizacao");
            	//Definindo a volta do botão Voltar p Filtrar Rota
    	    	sessao.setAttribute("voltar", "filtrar");
            }else if (httpServletRequest.getParameter("idRegistroAtualizacao")!= null) {
            	idRoteiroEmpresa = httpServletRequest.getParameter("idRegistroAtualizacao");
            	//Definindo a volta do botão Voltar p Manter Rota
            	sessao.setAttribute("voltar", "manter");
            	sessao.setAttribute("idRegistroAtualizacao",idRoteiroEmpresa);
            	sessao.setAttribute("indicadorAtualizar","1");
            }
            
           exibirRoteiroEmpresa(idRoteiroEmpresa, form, fachada, sessao, httpServletRequest);

		}else {
			idRoteiroEmpresa = (String)sessao.getAttribute("idRegistroAtualizacao");
		}

		String idLocalidade = form.getIdLocalidade();
        String idDigitadoEnterLeiturista = form.getIdLeiturista();

		if (httpServletRequest.getParameter("desfazer") != null
        		&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
    	
	    	//-------------- bt DESFAZER ---------------
	    	exibirRoteiroEmpresa(idRoteiroEmpresa, form,fachada,sessao,httpServletRequest);
    
        }

        String removerSetor = httpServletRequest.getParameter("removerSetor");
        
        Collection colecaoSetorComercial = (Collection) sessao.getAttribute("colecaoSetorComercial");        
		       
        // Pesquisando Setor Comercial
		if (idLocalidade != null
				&& !idLocalidade.trim().equalsIgnoreCase("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			
			// Adiciona o id da localidade que está no formulário para
			// compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna coleção de setor comercial
			colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial,
					SetorComercial.class.getName());

			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {			
				sessao.setAttribute("colecaoSetorComercial",
						colecaoSetorComercial);
				sessao.setAttribute("colecaoSetoresSelecionados",
						new ArrayList());					
				
			} 
		} 
		if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
			sessao.setAttribute("colecaoSetorComercial", new ArrayList());			
		}
		
		// Preencher lista de setores selecionados
		String setoresSelecionados[] = form.getIdSetorComercialSelecionados();
		Collection colecaoSetoresSelecionados = (Collection) sessao.getAttribute("colecaoSetoresSelecionados");
		if (colecaoSetoresSelecionados == null){
			colecaoSetoresSelecionados = new ArrayList();
			sessao.setAttribute("colecaoSetoresSelecionados", colecaoSetoresSelecionados);			
		}
		
		ArrayList quadras = (ArrayList) httpServletRequest.getAttribute("colecaoQuadras");
		
		if (setoresSelecionados != null && setoresSelecionados.length > 0){
			if (removerSetor != null && !removerSetor.equalsIgnoreCase("")){			
				for (int i = 0; i < setoresSelecionados.length; i++) {
					Iterator iter = colecaoSetoresSelecionados.iterator();
					while (iter.hasNext()) {
						SetorComercial setor = (SetorComercial) iter.next();
						if (setor.getId().intValue() == Integer.parseInt(setoresSelecionados[i])){
							colecaoSetorComercial.add(setor);
							colecaoSetoresSelecionados.remove(setor);
							break;
						}
					}
				}
			} else {//if (carregarQuadras != null && !carregarQuadras.equals("")){
				//colecaoSetoresSelecionados = new ArrayList();
				for (int i = 0; i < setoresSelecionados.length; i++) {
					Iterator iter = colecaoSetorComercial.iterator();
					while (iter.hasNext()) {
						SetorComercial setor = (SetorComercial) iter.next();
						if (setor.getId().intValue() == Integer.parseInt(setoresSelecionados[i])){
							colecaoSetoresSelecionados.add(setor);
							colecaoSetorComercial.remove(setor);
							break;
						}
					}
				}				
			}		
			sessao.setAttribute("colecaoSetorComercial", colecaoSetorComercial);			
			sessao.setAttribute("colecaoSetoresSelecionados", colecaoSetoresSelecionados);
			form.setIdSetorComercialSelecionados(null);
		}
		
//	    if (carregarQuadras != null && !carregarQuadras.equalsIgnoreCase("")){  			
			// Pesquisando quadras do setores comerciais selecionados
			String faturGrp = form.getFaturamentoGrupo();
			if (quadras == null){
				quadras = new ArrayList();	
			}
			
			if (faturGrp != null && !faturGrp.trim().equalsIgnoreCase("") && 
					colecaoSetoresSelecionados.size() > 0 && idLocalidade != null &&
					!idLocalidade.trim().equals("")){
				
				int[] idsSetores = new int[colecaoSetoresSelecionados.size()];
								
				int i = 0;
				for (Iterator iter = colecaoSetoresSelecionados.iterator(); iter.hasNext();) {
					SetorComercial setor = (SetorComercial) iter.next();
					idsSetores[i++] = setor.getId().intValue();					
				}
				Integer intFaturGrupo = new Integer(faturGrp);
				// Retorna quadras
				// a coleção 'quadras' chega aqui preenchida com as quadras do roteiro empresa
				// e esta pesquisa acrescentará as quadras sem roteiro empresa
				quadras.addAll(fachada.pesquisarQuadrasPorSetorComercialFaturamentoGrupo(
						Integer.parseInt(idLocalidade), idsSetores, intFaturGrupo));
				Collections.sort(quadras, new ComparadorQuadra());
			}
			
			sessao.setAttribute("colecaoQuadras", quadras);
//        } 
	    if (quadras == null || quadras.isEmpty()){
	    	sessao.setAttribute("colecaoQuadras", new ArrayList());
	    }    	

        //-------Parte que trata do código quando o usuário tecla enter
	    if (idLocalidadeDigitado == null){
	    	idLocalidadeDigitado = form.getIdLocalidade();
	    }

    	if (idLocalidadeDigitado != null &&
    			!idLocalidadeDigitado.equalsIgnoreCase("")){
    			
    		if (Util.validarValorNaoNumerico(idLocalidadeDigitado)){
				//Localidade não numérico.
				httpServletRequest.setAttribute("nomeCampo","idLocalidade");
				throw new ActionServletException("atencao.nao.numerico",
						null,"Localidade ");		
			} else {
		        verificaExistenciaCodLocalidade(idLocalidadeDigitado,form,
		       			httpServletRequest,fachada);	
		        pesquisarSetoresComerciais(Integer.parseInt(idLocalidadeDigitado), fachada, httpServletRequest);
			}
		}    	

    	if (idDigitadoEnterLeiturista != null &&
    			!idDigitadoEnterLeiturista.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idDigitadoEnterLeiturista)){
			//Leiturista não numérico.
			httpServletRequest.setAttribute("nomeCampo","idLeiturista");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Leiturista ");		
		} else {
	        verificaExistenciaCodLeiturista(idDigitadoEnterLeiturista,form,
	       			httpServletRequest,fachada);       			
		}

        //-------Fim de parte que trata do código quando o usuário tecla enter
	    
       sessao.removeAttribute("caminhoRetornoTelaPesquisa");
		// DEFINIÇÃO QUE IRÁ AUXILIAR O RETORNO DOS POPUPS
       sessao.setAttribute("UseCase", "ATUALIZARROTEIROEMPRESA");
		
       return retorno;  
         
    }

	private void exibirRoteiroEmpresa(String idRoteiroEmpresa, 
		InserirRoteiroEmpresaActionForm form,
		Fachada fachada,HttpSession sessao,
		HttpServletRequest httpServletRequest) {

  	    //Cria a variável que vai armazenar o roteiro a ser atualizado
        RoteiroEmpresa roteiro = new RoteiroEmpresa();
        roteiro.setId(Integer.parseInt(idRoteiroEmpresa));
        
    	//Cria o filtro de roteiro empresa e seta o id do roteiro para ser atualizado no filtro
		//e indica quais objetos devem ser retornados pela pesquisa
        Filtro filtro = roteiro.retornaFiltro();
		Collection<RoteiroEmpresa> collectionRoteiro = fachada.pesquisar(filtro, RoteiroEmpresa.class.getName()) ;	
					
		//Caso a pesquisa tenha retornado o roteiro
		if (collectionRoteiro != null	&& !collectionRoteiro.isEmpty()){
			
			//Recupera da coleção o roteiro que vai ser atualizado
			roteiro = (RoteiroEmpresa) Util.retonarObjetoDeColecao(collectionRoteiro);

			Collection<Quadra> quadras = fachada.pesquisarQuadrasPorRoteiroEmpresa(roteiro.getId());
			
			httpServletRequest.setAttribute("colecaoQuadras", quadras);
			
			TreeSet<String> setoresSelecionados = new TreeSet<String>();
			String[] quadrasSelecionadas = new String[quadras.size()];
			int i = 0;
			for (Quadra quadra : quadras) {
				setoresSelecionados.add(quadra.getSetorComercial().getId() + "");
				quadrasSelecionadas[i++] = quadra.getId() + "";
			}
			String[] idSetoresSelecionados = setoresSelecionados.toArray(new String[0]);
			form.setIdSetorComercialSelecionados(idSetoresSelecionados);
			form.setIdQuadraAdicionar(quadrasSelecionadas);
			//sessao.setAttribute("colecaoSetoresSelecionados", setoresSelecionados);
			
			Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(quadras);
			
			//Seta no form os dados de roteiro
        	form.setIdLocalidade("" + quadra.getSetorComercial().getLocalidade().getId());
        	form.setDescricaoLocalidade("" + quadra.getSetorComercial().getLocalidade().getDescricao());

        	form.setFaturamentoGrupo("" + quadra.getRota().getFaturamentoGrupo().getId());
        	form.setEmpresa("" + roteiro.getEmpresa().getId());
        	form.setIdLeiturista("" + roteiro.getLeiturista().getId());
        	String nomeLeiturista = "";
        	if (roteiro.getLeiturista().getFuncionario() != null) {
        		nomeLeiturista = roteiro.getLeiturista().getFuncionario().getNome();		        		
        	} else {
        		if (roteiro.getLeiturista().getCliente() != null) {
        			nomeLeiturista = roteiro.getLeiturista().getCliente().getNome();	
        		}		        		
        	}
        	httpServletRequest.setAttribute("idLeituristaEncontrado","true");
        	form.setNomeLeiturista(nomeLeiturista);
        	if ((roteiro.getIndicadorUso() != null) && (!roteiro.getIndicadorUso().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))){
        		form.setIndicadorUso("" + roteiro.getIndicadorUso());
        	}
    	    form.setUltimaAlteracao(Util.formatarDataComHora(roteiro.getUltimaAlteracao()));   
    	    //pesquisarSetoresComerciais(quadra.getSetorComercial().getLocalidade().getId(), fachada, httpServletRequest);
		}
     	
    }

    
    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, 
			InserirRoteiroEmpresaActionForm form,
			HttpServletRequest httpServletRequest,
			Fachada fachada) {

		//Verifica se o código da Localidade foi digitado
		if (idDigitadoEnterLocalidade != null
		&& !idDigitadoEnterLocalidade.trim().equals("")
		&& Integer.parseInt(idDigitadoEnterLocalidade) > 0) {
		
			//Recupera a localidade informada pelo usuário
			Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));
			
			//Caso a localidade informada pelo usuário esteja cadastrada no sistema
			//Seta os dados da localidade no form
			//Caso contrário seta as informações da localidade para vazio 
			//e indica ao usuário que a localidade não existe 
			
			if (localidadeEncontrada != null) {
				//a localidade foi encontrada
				form.setIdLocalidade("" + (localidadeEncontrada.getId()));
				form
				.setDescricaoLocalidade(localidadeEncontrada.getDescricao());
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
				"true");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			} else {
				//a localidade não foi encontrada
				form.setIdLocalidade("");
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
				"exception");
				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
			
			}
		}
	
	}

    private void pesquisarSetoresComerciais(int idLocalidade, Fachada fachada, HttpServletRequest request){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		// Adiciona o id da localidade que está no formulário para
		// compor a pesquisa.
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Retorna coleção de setor comercial
		Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial,
				SetorComercial.class.getName());

		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {			
			request.setAttribute("colecaoSetorComercial",
					colecaoSetorComercial);
			request.setAttribute("colecaoSetoresSelecionados",
					new ArrayList());					
			
		} 
    	
    }

    private void verificaExistenciaCodLeiturista(String idDigitadoEnterLeiturista, 
			InserirRoteiroEmpresaActionForm inserirRoteiroEmpresaActionForm,
			HttpServletRequest httpServletRequest,
			Fachada fachada) {
		
		//Verifica se o código do Leiturista foi digitado
		if (idDigitadoEnterLeiturista != null
			&& !idDigitadoEnterLeiturista.trim().equals("")
			&& Integer.parseInt(idDigitadoEnterLeiturista) > 0) {
			
			//Recupera o leiturista informado pelo usuário
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.ID, idDigitadoEnterLeiturista));
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection leituristaEncontrado = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());
			
			//Caso o leiturista informado pelo usuário esteja cadastrado no sistema
			//Seta os dados do leiturista no form
			//Caso contrário seta as informações de leiturista para vazio 
			//e indica ao usuário que o leiturista não existe 
			
			if (leituristaEncontrado != null && leituristaEncontrado.size() > 0) {
				//leiturista foi encontrado
				Leiturista leiturista = (Leiturista) ((List) leituristaEncontrado).get(0); 
				inserirRoteiroEmpresaActionForm.setIdLeiturista("" + 
					leiturista.getId());
				if (leiturista.getFuncionario() != null){
					inserirRoteiroEmpresaActionForm.setNomeLeiturista(leiturista.getFuncionario().getNome());					
				} else if (leiturista.getCliente() != null){
					inserirRoteiroEmpresaActionForm.setNomeLeiturista(leiturista.getCliente().getNome());
				}
				httpServletRequest.setAttribute("idLeituristaEncontrado","true");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			} else {
				//o leiturista não foi encontrado
				inserirRoteiroEmpresaActionForm.setIdLeiturista("");
				inserirRoteiroEmpresaActionForm.setNomeLeiturista("LEITURISTA INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","idLeiturista");
			}
		}
		
    }

    static class ComparadorQuadra implements Comparator{

		public int compare(Object o1, Object o2) {
			if (o1 instanceof Quadra && o2 instanceof Quadra){
				Quadra q1 = (Quadra) o1;
				Quadra q2 = (Quadra) o2;
				if (q1.getNumeroQuadra() < q2.getNumeroQuadra()){
					return -1;
				} else {
					return 1;
				}
			}
			return 0;
		}
    	
    }
}
