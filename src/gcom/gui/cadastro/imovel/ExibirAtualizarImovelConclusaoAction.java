package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelContaEnvio;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.FiltroNomeConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.integracao.GisHelper;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirAtualizarImovelConclusaoAction extends GcomAction {
	/**
	 * < <Descrição do método>>
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
		.findForward("atualizarImovelConclusao");
		
		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		DynaValidatorForm inserirImovelConclusaoActionForm = (DynaValidatorForm) actionForm;
		
		/*
		 * GIS
		 * ==============================================================================================================	
		 */
		sessao.setAttribute("gis",true);		
		
		GisHelper gisHelper = (GisHelper) sessao.getAttribute("gisHelper");	
		
		if(gisHelper!= null){					
			carregarDadosGis(gisHelper,inserirImovelConclusaoActionForm,sessao,this.getFachada());
		}		
		
		//Verifica se existe cliente responsavel
		boolean eResponsavel = false;
		
		// Testa se já existe um clinte proprietário
		if (sessao.getAttribute("imovelClientesNovos") == null) {
			httpServletRequest.setAttribute("envioContaListar", "naoListar");
		} else {
			Collection imovelClientes = (Collection) sessao.getAttribute("imovelClientesNovos");
			ClienteImovel clienteImovel = new ClienteImovel();
			Iterator iteratorClienteImovel = imovelClientes.iterator();
			
			while (iteratorClienteImovel.hasNext()) {
				clienteImovel = null;
				clienteImovel = (ClienteImovel) iteratorClienteImovel.next();
				
				if (clienteImovel.getClienteRelacaoTipo() != null &&
						clienteImovel.getClienteRelacaoTipo().getId() == 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","obrigatorio");
					
					eResponsavel = true;
					
				}else if ( clienteImovel.getClienteRelacaoTipo() != null && !eResponsavel &&
						clienteImovel.getClienteRelacaoTipo().getId() != 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","opcional");
					
				}
				
				if ( inserirImovelConclusaoActionForm.get("imovelContaEnvio") != null 
						&& !inserirImovelConclusaoActionForm.get("imovelContaEnvio").equals("") ){
					
					String envioConta = (String) inserirImovelConclusaoActionForm.get("imovelContaEnvio");
					
					if ( ( envioConta.equals("4") || envioConta.equals("5") )
							&& clienteImovel.getIndicadorNomeConta().compareTo(new Short("1")) == 0
							&& ( clienteImovel.getCliente().getEmail() == null
							|| clienteImovel.getCliente().getEmail().equals("") )){
	
							throw new ActionServletException("atencao.email.nao.cadastrado");
					}
					
				}
				
				// verifica se há cliente responsável para o imóvel e verifica
				// e o campo "Extrato para responsavel" da aba de "Conclusão" deve
				// ser marcado obrigatorimente para "Emitir" e sem a possibilidade de alteracao.
				if (clienteImovel.getClienteRelacaoTipo().getDescricao().equalsIgnoreCase(ConstantesSistema.IMOVEL_ENVIO_CONTA)) {
					httpServletRequest.setAttribute("envioContaListar","listar");
				}		
			}
			
		}
	
		String valorBloqueio = "naoSetarOpcaoEmitirComoObrigatorio";
		String perfilImovel = (String) inserirImovelConclusaoActionForm.get("perfilImovel");
		
		if (perfilImovel != null && !perfilImovel.equals("")) {
			ImovelPerfil imovelPerfilProgramaEspecial = this.getSistemaParametro().getPerfilProgramaEspecial();
			
			if(imovelPerfilProgramaEspecial != null){
				if(perfilImovel.equals(""+imovelPerfilProgramaEspecial.getId())){
					valorBloqueio = "setarOpcaoEmitirComoObrigatorio";
				}
			}
		}
		
		httpServletRequest.setAttribute("setarEmitirObrigatorio",valorBloqueio);

		
		// Cria Filtros
		FiltroImovelContaEnvio filtroImovelContaEnvio = new FiltroImovelContaEnvio();

		//Faz a pesquisa de Ocupacao Tipo
		filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
				FiltroNomeConta.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		//Se existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 1
//		if ( eResponsavel ){
//			
//			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
//					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "1" ));
//			
//		}//Se não existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 2
//		else 
			
		if( !eResponsavel ){
			
			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "2" ));
			
		}
		
		filtroImovelContaEnvio.setCampoOrderBy(FiltroImovelContaEnvio.DESCRICAO);

		//Cria coleçao
		Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
		Collection colecaoImovelEnvioConta = null;

		colecaoImovelEnvioConta = 
			this.getFachada().pesquisar(filtroImovelContaEnvio, ImovelContaEnvio.class.getName());
		
		if (!colecaoImovelEnvioConta.isEmpty()) {
			
			//Coloca a coleçao da pesquisa na sessao
			sessao.setAttribute("colecaoImovelEnnvioConta", colecaoImovelEnvioConta);
			
			/*
			 * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Aryed Lins)
			 * OBJ: Marcar o indicador de emissão de extrato de faturamento para NAO EMITIR
			 */
			if (inserirImovelConclusaoActionForm.get("extratoResponsavel") != null &&
				!inserirImovelConclusaoActionForm.get("extratoResponsavel").equals("") ){
				
				inserirImovelConclusaoActionForm.set("extratoResponsavel", inserirImovelConclusaoActionForm.get("extratoResponsavel") );
			}else if (imovel.getIndicadorEmissaoExtratoFaturamento() != null){
				inserirImovelConclusaoActionForm.set("extratoResponsavel", imovel.getIndicadorEmissaoExtratoFaturamento());
			}
			
			httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "false");
			
			
		} else {
			
			if ( !eResponsavel ){
				
				httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "true");
			
			}else if ( eResponsavel ){
			
				throw new ActionServletException("atencao.naocadastrado",null,"Imóvel Conta Envio");
				
			}
				
		}
		
		if (imovel.getImovelPerfil() != null && 
				imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, ConstantesSistema.INDICADOR_TARIFA_SOCIAL));
			ImovelPerfil imovelPerfil = (ImovelPerfil) 
				Util.retonarObjetoDeColecao(this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName()));
			
			if (imovelPerfil.getIndicadorBloqueaDadosSocial().equals(ConstantesSistema.SIM)){
				httpServletRequest.setAttribute("tarifaSocial", "1");
			} else{
				httpServletRequest.setAttribute("tarifaSocial", "0");
			}
		} else {
			httpServletRequest.setAttribute("tarifaSocial", "0");
		}
		
		if(imovel.getInformacoesComplementares()!= null && !imovel.getInformacoesComplementares().equals("")){
			inserirImovelConclusaoActionForm.set("informacoesComplementares", imovel.getInformacoesComplementares());
		}else{
			inserirImovelConclusaoActionForm.set("informacoesComplementares", "");
		}
		
		imovel = null;
		
		String pesquisar = httpServletRequest.getParameter("pesquisar");
		
		// Cria variaveis
		String idImovel = (String) inserirImovelConclusaoActionForm.get("idImovel");
		
		if (pesquisar != null && !pesquisar.equalsIgnoreCase("")) {
			
			// Cria variaveis
			// Cria Filtros
			FiltroImovel filtroImovel = new FiltroImovel();
			
			// Objetos que serão retornados pelo Hibernate
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.imovelPrincipal");
			
			filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
					FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR, 2));
			
			filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));
			
			Collection imoveis = null;
			
			if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
				
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel)));
				
				Imovel imovelAtualizado = (Imovel) sessao.getAttribute("imovelAtualizacao");
				
				// testa se o imovel a ser principal é o mesmo que esta sendo
				// atualizado
				if (idImovel.equals(imovelAtualizado.getId().toString())) {
					
					// atualizarImovelPrincipalActionForm.set("idImovel","");
					throw new ActionServletException("atencao.imovel.principal.igual.atualizado");
				}
				
				imoveis = this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
				
				if (imoveis != null && !imoveis.isEmpty()) {
					
					filtroImovel = new FiltroImovel();
					filtroImovel.limparListaParametros();
					
					//Cria Variaveis
					String idLocalidade = (String) inserirImovelConclusaoActionForm.get("idLocalidade");
					String idSetorComercial = (String) inserirImovelConclusaoActionForm.get("idSetorComercial");
					String idQuadra = (String) inserirImovelConclusaoActionForm.get("idQuadra");
					String informacoesComplementares = (String) inserirImovelConclusaoActionForm.get("informacoesComplementares");
					
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel.trim()));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID,new Integer(idLocalidade)));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_CODIGO,new Integer(idSetorComercial)));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_NUMERO,new Integer(idQuadra)));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.INFORMACOES_COMPLEMENTARES,informacoesComplementares));
					
					Collection colecaoImovel = null;
					colecaoImovel = this.getFachada().pesquisar(filtroImovel,Imovel.class.getName());
					
					if(colecaoImovel == null || colecaoImovel.isEmpty()){
						throw new ActionServletException("atencao.imovel_principal.inexistente.quadra");        		
					}            	
					
					
					sessao.setAttribute("idImoveilPrincipal", ((Imovel) imoveis.iterator().next()).getId().toString());
					sessao.setAttribute("imoveisPrincipal", imoveis);
					
					inserirImovelConclusaoActionForm.set("idImovel", idImovel);
					inserirImovelConclusaoActionForm.set("informacoesComplementares",((Imovel) imoveis.iterator().next()).getInformacoesComplementares());
					
					httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", 
							this.getFachada().pesquisarInscricaoImovel(new Integer(idImovel.trim())));
					httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", null);                
					
				} else {
					httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", "true");
					httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", "IMOVEL INEXISTENTE");
				}
				
			}
		} else {
			if(idImovel != null && !idImovel.equals("")){
				inserirImovelConclusaoActionForm.set("idImovel", idImovel);
				httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", 
						this.getFachada().pesquisarInscricaoImovel(new Integer(idImovel.trim())));
				
			}
		}
		
		
		String remover = httpServletRequest.getParameter("remover");
		String idFuncionario = (String) inserirImovelConclusaoActionForm.get("idFuncionario");
		
		if (remover != null && !remover.equalsIgnoreCase("")) {
			//Cria variaveis
			Collection imoveisPrincipaisNovos = (Collection) sessao.getAttribute("imoveisPrincipal");
			
			inserirImovelConclusaoActionForm.set("idImovel", "");
			
			if (imoveisPrincipaisNovos != null && !imoveisPrincipaisNovos.equals("")) {
				
				Iterator ImovelPrincipalIterator = imoveisPrincipaisNovos.iterator();
				
				while (ImovelPrincipalIterator.hasNext()) {
					ImovelPrincipalIterator.next();
					ImovelPrincipalIterator.remove();
					inserirImovelConclusaoActionForm.set("idImovel", "");
					sessao.removeAttribute("idImoveilPrincipal");
				}
			}
		}
		
		if(idFuncionario != null && !idFuncionario.trim().equals("")){
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));
			
			Collection colecaoFuncionario = 
				this.getFachada().pesquisar(filtroFuncionario, Funcionario.class.getSimpleName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				
				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);
				
				httpServletRequest.setAttribute("idImovelPrincipalEncontrado","true");
				
				inserirImovelConclusaoActionForm.set("idFuncionario",funcionario.getId().toString());
				inserirImovelConclusaoActionForm.set("nomeFuncionario",funcionario.getNome());
				
			}else{
				
				inserirImovelConclusaoActionForm.set("idFuncionario","");
				inserirImovelConclusaoActionForm.set("nomeFuncionario","Funcionário Inexistente");
				
				
			}
		}
		
		String idRota = (String) inserirImovelConclusaoActionForm.get("idRota");
		
		if (idRota != null && !idRota.trim().equals("")) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
			
			Collection colecaoRotas = this.getFachada().pesquisar(filtroRota, Rota.class.getName());
			
			if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
				Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
				
				inserirImovelConclusaoActionForm.set("codigoRota", rota.getCodigo().toString());
			}
		}
		
		String idRotaAlternativa = (String) inserirImovelConclusaoActionForm.get("idRotaAlternativa");
		if (idRotaAlternativa != null && !idRotaAlternativa.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRotaAlternativa));
        	
        	Collection colecaoRotas = this.getFachada().pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		inserirImovelConclusaoActionForm.set("codigoRotaAlternativa", rota.getCodigo().toString());
        	}
        }
		
		/**
		 * Verifica se o perfil do imovel é do programa especial e desabilita o extrato para responsavel
		 */
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		if ( sistemaParametro.getPerfilProgramaEspecial() != null && 
			 sistemaParametro.getPerfilProgramaEspecial().getId() != null && 
			(sistemaParametro.getPerfilProgramaEspecial().getId().intValue() == 
				 new Integer(perfilImovel).intValue() ) ) {

			sessao.setAttribute("bloquearExtratoParaResponsavel", true);
			//inserirImovelConclusaoActionForm.set("extratoResponsavel", "2");
		} else {
			
			sessao.setAttribute("bloquearExtratoParaResponsavel", "");
		}
		
        inserirImovelConclusaoActionForm.set("indicadorEnvioContaFisica", "1");		
		
		return retorno;
	}
//	=================================================================================================================
	
	
	
	public void carregarDadosGis(			
			GisHelper gisHelper,
			DynaValidatorForm inserirImovelConclusaoActionForm,
			HttpSession sessao, Fachada fachada) {
		
		String nnCoordenadaNorte = gisHelper.getNnCoordenadaNorte(); 
		String nnCoordenadaLeste = gisHelper.getNnCoordenadaLeste(); 			
		
		inserirImovelConclusaoActionForm.set("cordenadasUtmX",nnCoordenadaLeste);
		inserirImovelConclusaoActionForm.set("cordenadasUtmY",nnCoordenadaNorte);
		
		sessao.removeAttribute("gisHelper");	
		
	}
	
}
