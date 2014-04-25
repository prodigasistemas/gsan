package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.RamoAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirRamoAtividadeAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

	        //Seta o retorno
	        ActionForward retorno = actionMapping.findForward("telaSucesso");
	
	        InserirRamoAtividadeActionForm form = (InserirRamoAtividadeActionForm) actionForm;
	
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
	
	        //------------ REGISTRAR TRANSAÇÃO ----------------
	        
	        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_RAMO_ATIVIDADE_INSERIR,
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	        
	        Operacao operacao = new Operacao();
	        operacao.setId(Operacao.OPERACAO_RAMO_ATIVIDADE_INSERIR);
	
	        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
	        operacaoEfetuada.setOperacao(operacao);
	        
	        //------------ REGISTRAR TRANSAÇÃO ----------------
	
	        Short codigo = form.getCodigo();
	        String descricao = form.getDescricao();
	        
	        
	        if(codigo == null || codigo.equals("")){
	        	
	        	//Descrição não informada
	        	throw new ActionServletException("atencao.codigo_ramo_atividade_nao_informado");
	        } else if(descricao == null || descricao.equals("")){
	        
	        	//Descrição não informada
	        	throw new ActionServletException("atencao.descricao_sistema_abastecimento_nao_informado");
	        } else{
	        	
	        	//Criar o objeto ramoAtividade que será inserido na base
	        	RamoAtividade ramoAtividade = new RamoAtividade();
	        	
	        	ramoAtividade.setCodigo(codigo);
	        	ramoAtividade.setDescricao(descricao);
	        	ramoAtividade.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
	        	ramoAtividade.setUltimaAlteracao(new Date());
	        	
	        	//------------ REGISTRAR TRANSAÇÃO ----------------
	        	ramoAtividade.setOperacaoEfetuada(operacaoEfetuada);
	        	ramoAtividade.adicionarUsuario(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
	        	registradorOperacao.registrarOperacao(ramoAtividade);
	        	//------------ REGISTRAR TRANSAÇÃO ----------------
	        	
	        	Integer codigoRamoAtividadeInserido = 
	          		(Integer) this.getFachada().inserir(ramoAtividade);
	        	
	        	montarPaginaSucesso(httpServletRequest,
	            		"Ramo de Atividade de código " + ramoAtividade.getCodigo() 
	                    	+ " - "  + ramoAtividade.getDescricao().toUpperCase() 
	                    	+ " inserido com sucesso.",
	                    	"Inserir outro Ramo de Atividade",
	                    	"exibirInserirRamoAtividadeAction.do?menu=sim",
	                    	"exibirAtualizarRamoAtividadeAction.do?menu=sim&idRegistroAtualizacao=" + 
	                    	codigoRamoAtividadeInserido, "Atualizar Ramo de Atividade Inserido");
	        	
	       }
	
	        //devolve o mapeamento de retorno
	        return retorno;
	    }

    }
