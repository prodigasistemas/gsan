package gcom.gui.cadastro.localidade;
//
//import gcom.cadastro.localidade.FiltroGerenciaRegional;
//import gcom.cadastro.localidade.GerenciaRegional;
//import gcom.fachada.Fachada;
//import gcom.faturamento.RegistradorOperacao;
//import gcom.gui.ActionServletException;
//import gcom.gui.GcomAction;
//import gcom.seguranca.acesso.Operacao;
//import gcom.seguranca.acesso.OperacaoEfetuada;
//import gcom.seguranca.acesso.usuario.Usuario;
//import gcom.seguranca.acesso.usuario.UsuarioAcao;
//import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
//import gcom.util.ConstantesSistema;
//import gcom.util.Util;
//import gcom.util.filtro.ParametroSimples;
//
//import java.util.Collection;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//
//public class ManterGerenciaRegionalAction extends GcomAction {
//	
//	/**
//	 * Este caso de uso permite inserir Gerência Regional 
//	 * 
//	 * [UC0396] Inserir Gerência Regional
//	 * 
//	 * @param actionMapping
//	 * @param actionForm
//	 * @param httpServletRequest
//	 * @param httpServletResponse
//	 * @return
//	 */
//
//    public ActionForward execute(ActionMapping actionMapping,
//            ActionForm actionForm, HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse) {
//
//        //Seta o retorno
//        ActionForward retorno = actionMapping.findForward("telaSucesso");
//
//        //Obtém a instância da fachada
//        Fachada fachada = Fachada.getInstancia();
// 
//        //Obtém a sessão
//        HttpSession sessao = httpServletRequest.getSession(false);
//
//        InserirGerenciaRegionalActionForm inserirGerenciaRegionalActionForm = (InserirGerenciaRegionalActionForm) actionForm;
//
//
//        //------------ REGISTRAR TRANSAÇÃO ----------------
//        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_GERENCIA_REGIONAL_INSERIR,
//				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//        
//        Operacao operacao = new Operacao();
//        operacao.setId(Operacao.OPERACAO_GERENCIA_REGIONAL_INSERIR);
//
//        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//        operacaoEfetuada.setOperacao(operacao);
//        //------------ REGISTRAR TRANSAÇÃO ----------------
//        
//        
//        String gerenciaRegionalID = inserirGerenciaRegionalActionForm
//                .getGerenciaRegionalID();
//        String nome = inserirGerenciaRegionalActionForm
//                .getNome();
//        String nomeAbreviado = inserirGerenciaRegionalActionForm
//        .getNomeAbreviado();
//        Collection colecaoEnderecos = (Collection) sessao
//                .getAttribute("colecaoEnderecos");
//        String telefone = inserirGerenciaRegionalActionForm.getTelefone();
//        String ramal = inserirGerenciaRegionalActionForm.getRamal();
//        String fax = inserirGerenciaRegionalActionForm.getFax();
//        String email = inserirGerenciaRegionalActionForm.getEmail();
//
//
//        GerenciaRegional gerenciaRegionalInserir = new GerenciaRegional();
//        Collection colecaoPesquisa = null;
//
//        sessao.removeAttribute("tipoPesquisaRetorno");
//        //O código da Gerencia Regional é obrigatório.
//        if (gerenciaRegionalID == null || gerenciaRegionalID.equalsIgnoreCase("")) {
//            throw new ActionServletException(
//            		"atencao.required",null,"Código");
//        }
//
//        //O nome da Gerência Regional é obrigatório.
//        if (nome == null || nome.equalsIgnoreCase("")) {
//            throw new ActionServletException(
//            		"atencao.required",null,"Nome");
//        }
//        
//        //O nome Abreviado da Gerência Regional é obrigatório.
//        if (nomeAbreviado == null || nome.equalsIgnoreCase("")) {
//            throw new ActionServletException(
//            		"atencao.required",null,"Nome Abreviado");
//        }
//
//        //O endereço da localidade é obrigatório.
//        /*if (colecaoEnderecos == null || colecaoEnderecos.isEmpty()) {
//            throw new ActionServletException(
//                    "atencao.endereco_localidade_nao_informado");
//        } else {
//            localidadeInserir = (Localidade) Util
//                    .retonarObjetoDeColecao(colecaoEnderecos);
//            localidadeInserir.setId(new Integer(localidadeID));
//            localidadeInserir.setDescricao(localidadeNome);
//        }*/
//        
//        
////        if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
////        	gerenciaRegionalInserir = (GerenciaRegional) Util
////            .retonarObjetoDeColecao(colecaoEnderecos);
////        } 
//
//        gerenciaRegionalInserir.setId(new Integer(gerenciaRegionalID));
//        gerenciaRegionalInserir.setNome(nome);
//        
//        
//        //O telefone é obrigatório caso o ramal tenha sido informado.
//        if (ramal != null && !ramal.equalsIgnoreCase("")) {
//        	gerenciaRegionalInserir.setRamalFone(ramal);
//            if (telefone == null || telefone.equalsIgnoreCase("")) {
//                throw new ActionServletException(
//                        "atencao.telefone_localidade_nao_informado");
//            }
//            else if (telefone.length() < 7){
//            	throw new ActionServletException(
//                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Telefone");
//            }
//        }
//
//        //Telefone.
//        if (telefone != null && !telefone.equalsIgnoreCase("")) {
//        	if (telefone.length() < 7){
//        		throw new ActionServletException(
//                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Telefone");
//        	}
//        	else{
//        		gerenciaRegionalInserir.setFone(telefone);
//        	}
//        }
//
//        //Fax.
//        if (fax != null && !fax.equalsIgnoreCase("")) {
//        	if (fax.length() < 7){
//        		throw new ActionServletException(
//                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Fax");
//        	}
//        	else{
//        		gerenciaRegionalInserir.setFax(fax);
//        	}
//        }
//
//        //E-mail.
//        if (email != null && !email.equalsIgnoreCase("")) {
//        	gerenciaRegionalInserir.setEmail(email);
//        }
//    
// 
//
//
//
//        //Indicador de Uso
//        gerenciaRegionalInserir
//                .setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
//
//        //Ultima alteração
//        gerenciaRegionalInserir.setUltimaAlteracao(new Date());
//
//        FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
//
//        filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
//        		FiltroGerenciaRegional.ID, gerenciaRegionalInserir.getId()));
//
//        //Verificar existência da Gerência Regional
//        colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class
//                .getName());
//
//        if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
//            //Gerencia Regional já existe
//            throw new ActionServletException(
//                    "atencao.pesquisa_localidade_ja_cadastrada", null, gerenciaRegionalID);
//        } else {
//        	Integer idGerenciaRegional = null;
//            
//        	        	
//            //------------ REGISTRAR TRANSAÇÃO ----------------
//        	gerenciaRegionalInserir.setOperacaoEfetuada(operacaoEfetuada);
//        	gerenciaRegionalInserir.adicionarUsuario(Usuario.USUARIO_TESTE, 
//            		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//            registradorOperacao.registrarOperacao(gerenciaRegionalInserir);
//            //------------ REGISTRAR TRANSAÇÃO ----------------  
////            idGerenciaRegional = fachada.inserirGerenciaRegional(gerenciaRegionalInserir);
//
//            montarPaginaSucesso(httpServletRequest, "Gerência Regional de código  "
//                    + gerenciaRegionalInserir.getId().intValue()
//                    + " inserida com sucesso.", "Inserir outra Gerência Regional",
//                    "exibirInserirGerenciaRegionalAction.do?menu=sim",
//                    "exibirAtualizarGerenciaRegionalAction.do?idRegistroInseridoAtualizar="
//					+ idGerenciaRegional,
//					"Atualizar Gerência Regional Inserida");
//
//        }
//
//        sessao.removeAttribute("colecaoEnderecos");
//
//
//
//        //devolve o mapeamento de retorno
//        return retorno;
//    }
//
//}
