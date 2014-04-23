package gcom.gui.cadastro.entidadebeneficente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class ExibirAtualizarEntidadeBeneficenteAction extends GcomAction{

	
	
	/**
	 * 
	 * [UC0916] Manter Entidade Beneficente
	 * 
	 * 
	 * @author Hugo Fernando
	 * @date 21/01/2010
	 * 
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

        ActionForward retorno = actionMapping
                .findForward("atualizarEntidadeBeneficente");

        
        
        AtualizarEntidadeBeneficenteActionForm form = (AtualizarEntidadeBeneficenteActionForm) actionForm;

        
        
        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        
        
		// Verifica se veio do filtrar ou do manter
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} 
		else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		

		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao
		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName());

		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Empresa");
		}

		httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		
		
		
		//-------Parte que trata do código quando o usuário tecla enter        
		Cliente cliente = form.getCliente();
       
        //Verifica se o código do cliente foi digitado.
        if ( cliente != null && cliente.getId() != null && cliente.getId() > 0 ) {
        	
        
        	FiltroCliente filtroCliente = new FiltroCliente();

        	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, cliente.getId()));
			
        	
			Collection<Cliente> colecaoClienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());
			

			if (colecaoClienteEncontrado != null && !colecaoClienteEncontrado.isEmpty()) {
				//O cliente foi encontrado.
				
				Cliente clienteEncontrado = (Cliente) Util.retonarObjetoDeColecao(colecaoClienteEncontrado);
				
				
				Integer idClienteEncontrado = clienteEncontrado.getId();
				String nomeClienteEncontrado = clienteEncontrado.getNome();
				
				
                // seta os parametros do cliente encontrado no actionForm.
				cliente.setId(idClienteEncontrado);
				cliente.setNome(nomeClienteEncontrado);
				
				
				form.setCliente(cliente);
				if(form.getDebitoTipo().getId()!= null && form.getDebitoTipo().equals("0")){
				form.getDebitoTipo().setId(null);
				}

				httpServletRequest.setAttribute("idClienteEncontrado","true");
				
				
				httpServletRequest.setAttribute("nomeCampo", "debitoTipo.id");
				

			} 
			else {
				
				cliente.setId(null);
				cliente.setNome("CLIENTE NÃO EMCONTRADO");
				
				httpServletRequest.setAttribute("idClienteNaoEncontrado","true");
				
				form.setCliente(cliente);
				if(form.getDebitoTipo().getId()!= null && form.getDebitoTipo().equals("0")){
				form.getDebitoTipo().setId(null);
				}
				
				httpServletRequest.setAttribute("nomeCampo", "cliente.id");
				
			}
			
        }
        else{
        	
        	httpServletRequest.setAttribute("idClienteEncontrado","true");
        }
			
			
			
			
             DebitoTipo debitoTipo = form.getDebitoTipo();
			
	        //Verifica se o código do tipoDebito foi digitado.
	        if ( debitoTipo != null && debitoTipo.getId() != null  && debitoTipo.getId().intValue() > 0 ) {
	        	
	        
	        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

	        	
	        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, debitoTipo.getId()));
				
	        	
				Collection<DebitoTipo> colecaoDebitoTipoEncontrado = fachada.pesquisar(filtroDebitoTipo,
						DebitoTipo.class.getName());
				

				if (colecaoDebitoTipoEncontrado != null && !colecaoDebitoTipoEncontrado.isEmpty()) {
					//O debitoTipo foi encontrado com sucesso.

					
					DebitoTipo debitoTipoEncontrado = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipoEncontrado);
					
					
					Integer idDebitoTipo = debitoTipoEncontrado.getId();
					String descricaoDebitoTipo = debitoTipoEncontrado.getDescricao();
					
					debitoTipo.setId(idDebitoTipo);
					debitoTipo.setDescricao(descricaoDebitoTipo);
					
					form.setDebitoTipo(debitoTipo);
					
					if(form.getCliente().getId()!= null && form.getCliente().equals("0")){
						form.getCliente().setId(null);						
					}
					

					httpServletRequest.setAttribute("idDebitoTipoEncontrado","true");
					httpServletRequest.setAttribute("nomeCampo", "debitoTipo.id");

				} 
				else {

					form.getDebitoTipo().setId( null );
					form.getDebitoTipo().setDescricao( "TIPO DE DÉBITO NAO ENCONTRADO" );
					if(form.getCliente().getId()!= null && form.getCliente().equals("0")){
						form.getCliente().setId(null);						
					}
					httpServletRequest.setAttribute("idDebitoNaoTipoEncontrado","true");
					httpServletRequest.setAttribute("nomeCampo", "debitoTipo.id");
				}
				
        }
	        else{
	        	httpServletRequest.setAttribute("idDebitoTipoEncontrado","true");
	        	
	        }
        
        //-------Fim de parte que trata do código quando o usuário tecla enter
		
		
		

		
        //Verifica se o usuario informou a unidade organizacional e teclou enter
        if(httpServletRequest.getParameter("enter") == null){
        	
		// Verifica se o funcionario já está na sessão, em caso afirmativo
		// significa que o usuário já entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// está entrando pela primeira vez

			if (sessao.getAttribute("objetoEntidadeBeneficente") != null) {

				EntidadeBeneficente entidadeBeneficente = (EntidadeBeneficente) sessao
						.getAttribute("objetoEntidadeBeneficente");
				
				sessao.setAttribute("idEntidadeBeneficente", entidadeBeneficente.getId().toString());

				form.setCliente(entidadeBeneficente.getCliente());
				form.setDebitoTipo(entidadeBeneficente.getDebitoTipo());
				form.setEmpresa(entidadeBeneficente.getEmpresa());
				form.setId(entidadeBeneficente.getId());
				form.setIndicadorUso(entidadeBeneficente.getIndicadorUso());
				form.setUltimaAlteracao(entidadeBeneficente.getUltimaAlteracao());
				
				
				if( entidadeBeneficente.getAnoMesContratoInicial() != null ){
					
					String anoInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(0,4);
					String mesInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(4);
					
					if( mesInicioAdesao.length() == 1 ){
						mesInicioAdesao = "0"+mesInicioAdesao;	
					}
					
					form.setInicioMesAnoAdesao(mesInicioAdesao+"/"+anoInicioAdesao);
					entidadeBeneficente.setInicioMesAnoAdesao2(mesInicioAdesao+"/"+anoInicioAdesao);
				}
				
				
				if( entidadeBeneficente.getAnoMesContratoFinal() != null ){
					
					String anoFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(0,4);
					String mesFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(4);
					
					if( mesFimAdesao.length() == 1 ){
						mesFimAdesao = "0"+mesFimAdesao;	
					}
					
				    form.setFimMesAnoAdesao(mesFimAdesao+"/"+anoFimAdesao);
				    entidadeBeneficente.setFimMesAnoAdesao2(mesFimAdesao+"/"+anoFimAdesao);
				}
				
                
				sessao.setAttribute("entidadeBeneficenteAtualizar", entidadeBeneficente);
				
				sessao.removeAttribute("objetoEntidadeBeneficente");

			} 
			else {
		
		String idEntidadeBeneficente = null;

		if (httpServletRequest.getParameter("idEntidadeBeneficente") == null
				|| httpServletRequest.getParameter("idEntidadeBeneficente").equals("")) {
			idEntidadeBeneficente = (String) sessao.getAttribute("idEntidadeBeneficente");
		} 
		else {
			idEntidadeBeneficente = (String) httpServletRequest.getParameter("idEntidadeBeneficente");
			sessao.setAttribute("idEntidadeBeneficente", idEntidadeBeneficente);
		}

		
		httpServletRequest.setAttribute("idEntidadeBeneficente",idEntidadeBeneficente);
		sessao.setAttribute("idEntidadeBeneficente",idEntidadeBeneficente);
		
		
		FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();
		
		
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		
		
		
		filtroEntidadeBeneficente.adicionarParametro(new ParametroSimples(FiltroEntidadeBeneficente.ID, idEntidadeBeneficente));

		
		Collection<EntidadeBeneficente> colecaoEntidadeBeneficente = fachada
				.pesquisar(filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());

		
		if (colecaoEntidadeBeneficente == null || colecaoEntidadeBeneficente.isEmpty()) {
			throw new ActionServletException(
					"atencao.atualizacao.timestamp");
		}


		EntidadeBeneficente entidadeBeneficente = (EntidadeBeneficente) 
		colecaoEntidadeBeneficente.iterator().next();
		
		
		form.setCliente(entidadeBeneficente.getCliente());
		form.setDebitoTipo(entidadeBeneficente.getDebitoTipo());
		form.setEmpresa(entidadeBeneficente.getEmpresa());
		form.setId(entidadeBeneficente.getId());
		form.setIndicadorUso(entidadeBeneficente.getIndicadorUso());
		form.setUltimaAlteracao(entidadeBeneficente.getUltimaAlteracao());
		
		
		if( entidadeBeneficente.getAnoMesContratoInicial() != null ){
			
			String anoInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(0,4);
			String mesInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(4);
			
			if( mesInicioAdesao.length() == 1 ){
				mesInicioAdesao = "0"+mesInicioAdesao;	
			}
			
			form.setInicioMesAnoAdesao(mesInicioAdesao+"/"+anoInicioAdesao);
			entidadeBeneficente.setInicioMesAnoAdesao2(mesInicioAdesao+"/"+anoInicioAdesao);
		}
		
		
		if( entidadeBeneficente.getAnoMesContratoFinal() != null ){
			
			String anoFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(0,4);
			String mesFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(4);
			
			if( mesFimAdesao.length() == 1 ){
				mesFimAdesao = "0"+mesFimAdesao;	
			}
			
		    form.setFimMesAnoAdesao(mesFimAdesao+"/"+anoFimAdesao);
		    entidadeBeneficente.setFimMesAnoAdesao2(mesFimAdesao+"/"+anoFimAdesao);
		}
	
		httpServletRequest.setAttribute("colecaoEntidadeBeneficente",colecaoEntidadeBeneficente);
		sessao.setAttribute("entidadeBeneficenteAtualizar", entidadeBeneficente);
			
			
		}
        
        }
        
        
        
		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("sim")) {

			String idEntidadeBeneficente = null;

			if (httpServletRequest.getParameter("idEntidadeBeneficente") == null
				|| httpServletRequest.getParameter("idEntidadeBeneficente").equals("")) {
				
				idEntidadeBeneficente = (String) sessao.getAttribute("idEntidadeBeneficente");
				
			} 
			else {
				idEntidadeBeneficente = (String) httpServletRequest.getParameter("idEntidadeBeneficente");
				sessao.setAttribute("idEntidadeBeneficente", idEntidadeBeneficente);
			}

			if (idEntidadeBeneficente.equalsIgnoreCase("")) {
				idEntidadeBeneficente = null;
			}

			if (idEntidadeBeneficente == null) {

				EntidadeBeneficente entidadeBeneficente = (EntidadeBeneficente) sessao
						.getAttribute("objetoEntidadeBeneficente");
			
				
				form.setCliente(entidadeBeneficente.getCliente());
				form.setDebitoTipo(entidadeBeneficente.getDebitoTipo());
				form.setEmpresa(entidadeBeneficente.getEmpresa());
				form.setId(entidadeBeneficente.getId());
				form.setIndicadorUso(entidadeBeneficente.getIndicadorUso());
				form.setUltimaAlteracao(entidadeBeneficente.getUltimaAlteracao());
				
				
			if( entidadeBeneficente.getAnoMesContratoInicial() != null ){
					
					String anoInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(0,4);
					String mesInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(4);
					
					if( mesInicioAdesao.length() == 1 ){
						mesInicioAdesao = "0"+mesInicioAdesao;	
					}
					
					form.setInicioMesAnoAdesao(mesInicioAdesao+"/"+anoInicioAdesao);
					entidadeBeneficente.setInicioMesAnoAdesao2(mesInicioAdesao+"/"+anoInicioAdesao);
			}
				
				
			if( entidadeBeneficente.getAnoMesContratoFinal() != null ){
					
					String anoFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(0,4);
					String mesFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(4);
					
					if( mesFimAdesao.length() == 1 ){
						mesFimAdesao = "0"+mesFimAdesao;	
					}
					
				    form.setFimMesAnoAdesao(mesFimAdesao+"/"+anoFimAdesao);
				    entidadeBeneficente.setFimMesAnoAdesao2(mesFimAdesao+"/"+anoFimAdesao);
			}
				
				
				sessao.setAttribute("entidadeBeneficenteAtualizar", entidadeBeneficente);
				sessao.removeAttribute("entidadeBeneficente");
			}



			else if (idEntidadeBeneficente != null) {

				FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();
				
				filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("empresa");
				filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
				
				filtroEntidadeBeneficente.adicionarParametro(new ParametroSimples(
						FiltroEntidadeBeneficente.ID, idEntidadeBeneficente));
				

				Collection<EntidadeBeneficente> colecaoEntidadeBeneficente = fachada.pesquisar(filtroEntidadeBeneficente, 
						EntidadeBeneficente.class.getName());

				
				if (colecaoEntidadeBeneficente == null || colecaoEntidadeBeneficente.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoEntidadeBeneficente",
						colecaoEntidadeBeneficente);

				
				EntidadeBeneficente entidadeBeneficente = (EntidadeBeneficente) colecaoEntidadeBeneficente
						.iterator().next();

				
				form.setCliente(entidadeBeneficente.getCliente());
				form.setDebitoTipo(entidadeBeneficente.getDebitoTipo());
				form.setEmpresa(entidadeBeneficente.getEmpresa());
				form.setId(entidadeBeneficente.getId());
				form.setIndicadorUso(entidadeBeneficente.getIndicadorUso());
				form.setUltimaAlteracao(entidadeBeneficente.getUltimaAlteracao());

				
				if( entidadeBeneficente.getAnoMesContratoInicial() != null ){
					
					String anoInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(0,4);
					String mesInicioAdesao = entidadeBeneficente.getAnoMesContratoInicial().toString().substring(4);
					
					if( mesInicioAdesao.length() == 1 ){
						mesInicioAdesao = "0"+mesInicioAdesao;	
					}
					
					form.setInicioMesAnoAdesao(mesInicioAdesao+"/"+anoInicioAdesao);
					entidadeBeneficente.setInicioMesAnoAdesao2(mesInicioAdesao+"/"+anoInicioAdesao);
				}
				
				
				if( entidadeBeneficente.getAnoMesContratoFinal() != null ){
					
					String anoFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(0,4);
					String mesFimAdesao = entidadeBeneficente.getAnoMesContratoFinal().toString().substring(4);
					
					if( mesFimAdesao.length() == 1 ){
						mesFimAdesao = "0"+mesFimAdesao;	
					}
					
				    form.setFimMesAnoAdesao(mesFimAdesao+"/"+anoFimAdesao);
				    entidadeBeneficente.setFimMesAnoAdesao2(mesFimAdesao+"/"+anoFimAdesao);
				}
				

			}
		}
		// -------------- bt DESFAZER ---------------
        
        
        
        
        return retorno;
    }

	
}
