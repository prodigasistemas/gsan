package gcom.gui.cobranca.spcserasa;

import java.util.Collection;
import java.util.Date;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.NegativacaoImoveis;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.spcserasa.FiltroNegativacaoImoveis;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Classe responsável por atualizar um Negativador.
 * 
 * @author Thiago Vieira
 * date: 24/12/07
 */
public class AtualizarNegativadorAction extends GcomAction {


    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	
//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Pega o form do cliente
        AtualizarNegativadorActionForm form = (AtualizarNegativadorActionForm) actionForm; 

//		 ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();


		operacao.setId(Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

        
        short indicadorUso = Short.parseShort(form.getAtivo());
        Integer clienteId = new Integer(form.getCodigoCliente());
        
        Integer imovelId = null;
        if(!"".equals(form.getCodigoImovel())){
        	imovelId = new Integer(form.getCodigoImovel());
        }
         
       	String inscricaoEstadual = form.getInscricaoEstadual();
       	Integer idNegativador = new Integer(form.getIdNegativador());
       	short codigoAgente = Short.parseShort(form.getCodigoAgente());
       	Long time = Long.parseLong(form.getTime()); 
       	
		Cliente cliente = new Cliente(); 
		cliente.setId(clienteId);
	
		
		//Criando objeto Negativador a ser incluido
		Negativador negativador = new Negativador();
		negativador.setId(idNegativador);
		negativador.setCodigoAgente(codigoAgente);
		negativador.setCliente(cliente);
		
		if(imovelId != null){
			Imovel imovel = new Imovel();
			imovel.setId(imovelId);	
			negativador.setImovel(imovel);
		}
		
		negativador.setNumeroInscricaoEstadual(inscricaoEstadual);
		negativador.setIndicadorUso(indicadorUso);
			
		//......................................................................
		FiltroNegativador filtroNegativador = new FiltroNegativador();

		filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_CLIENTE, clienteId));
		filtroNegativador.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroNegativador.ID, idNegativador));
		
		Collection collNegativador= fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
		
		if(collNegativador != null && !collNegativador.isEmpty()){		
			throw new ActionServletException("atencao.negativador_associado_cliente");
			
		}		
		
		//......................................................................
		if(imovelId!=null){
			filtroNegativador = new FiltroNegativador();

			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_IMOVEL, imovelId));
			filtroNegativador.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroNegativador.ID, idNegativador));
			Collection collNegativadorImovel= fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
			
			if(collNegativadorImovel != null && !collNegativadorImovel.isEmpty()){		
				throw new ActionServletException("atencao.negativador_associado_imovel");
				
			}	
			
		}
			
		//......................................................................
		
		if(indicadorUso == ConstantesSistema.INDICADOR_USO_DESATIVO){
			
			FiltroNegativacaoImoveis filtroNegativacaoImoveis = new FiltroNegativacaoImoveis();
			filtroNegativacaoImoveis.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroNegativacaoImoveis.NEGATIVADOR_ID, idNegativador));
			filtroNegativacaoImoveis.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroNegativacaoImoveis.INDICADOR_EXCLUIDO, "2"));
			Collection collNegativacaoImoveis= fachada.pesquisar(filtroNegativacaoImoveis,NegativacaoImoveis.class.getName());	
			
			if(collNegativacaoImoveis != null && !collNegativacaoImoveis.isEmpty()){		
				throw new ActionServletException("atencao.imoveis_no_negativador");
				
			}
			
			
		}
			
		//.......................................................................
			
		//Check para atualização realizada por outro usuário 
		filtroNegativador = new FiltroNegativador(); 
		filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.ID, idNegativador));
		
		Collection negativadores = Fachada.getInstancia().pesquisar(filtroNegativador, Negativador.class.getName());
		Negativador negativadorAtual = (Negativador)negativadores.iterator().next();

		if (negativadorAtual.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		negativador.setUltimaAlteracao(new Date());
//			 Atualiza o Negativador
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		negativador.setOperacaoEfetuada(operacaoEfetuada);
		negativador.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativador);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		Fachada.getInstancia().atualizar(negativador);
		

		
		

		montarPaginaSucesso(httpServletRequest, "Negativador "
				+ form.getCodigoAgente() + " atualizado com sucesso.",
				"Realizar outra manutenção de Negativador",
				"exibirFiltrarNegativadorAction.do?menu=sim",
				"","");
		        
		return retorno;
        
    }
}
