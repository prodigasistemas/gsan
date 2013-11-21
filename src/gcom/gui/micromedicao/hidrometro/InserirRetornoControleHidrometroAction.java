package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.FiltroRetornoControleHidrometro;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Descrição da classe
 * 
 * @author Wallace Lima
 * @date 29/07/2010 
 */
public class InserirRetornoControleHidrometroAction extends GcomAction{
	
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirRetornoControleHidrometroActionForm inserirRetornoControleHidrometroActionForm = 
			(InserirRetornoControleHidrometroActionForm) actionForm;
		
		//Muda quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");          
        
		
		String descricao = inserirRetornoControleHidrometroActionForm.getDescricao();
		
		RetornoControleHidrometro retornoControleHidrometro = new RetornoControleHidrometro();
		Collection colecaoPesquisa = null;
		
		//Descrição
		if(!"".equals(inserirRetornoControleHidrometroActionForm.getDescricao())){
			retornoControleHidrometro.setDescricao(inserirRetornoControleHidrometroActionForm.getDescricao());
		}else{
			throw new ActionServletException ("atencao.required", null,
					"descrição");
		}
		
		//Indicador de Geração
		if((inserirRetornoControleHidrometroActionForm.getIndicadorGeracao())!= null){
			retornoControleHidrometro.setIndicadorGeracao(
					inserirRetornoControleHidrometroActionForm.getIndicadorGeracao());
		}else{
			throw new ActionServletException("atencao.required", null,"indicadorGeracao");
		}
		
		
		//Indicador de Uso
		retornoControleHidrometro.setIndicadorUso(
				inserirRetornoControleHidrometroActionForm.getIndicadorUso());
		
		//Data Corrente		
		retornoControleHidrometro.setDataCorrente(new Date());
		
		//fachada.inserir(retornoControleHidrometro);
		
		FiltroRetornoControleHidrometro filtroRetornoControleHidrometro = new FiltroRetornoControleHidrometro();
		
		filtroRetornoControleHidrometro.adicionarParametro(new ParametroSimples(FiltroRetornoControleHidrometro.DESCRICAO, retornoControleHidrometro.getDescricao()));
		
		colecaoPesquisa = (Collection) fachada.pesquisar(filtroRetornoControleHidrometro, RetornoControleHidrometro.class.getName());
		
		if(colecaoPesquisa!= null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null,
					retornoControleHidrometro.getDescricao());
		}else{
			retornoControleHidrometro.setDescricao(descricao);
			
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_RETORNO_CONTROLE_HIDROMETRO_INSERIR, retornoControleHidrometro.getId(),
					retornoControleHidrometro.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
			registradorOperacao.registrarOperacao(retornoControleHidrometro);
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			
			
			
			Integer idRetornoControleHidrometro = (Integer) fachada.inserir(retornoControleHidrometro);
			
			montarPaginaSucesso(httpServletRequest,"Retorno de Controle de Hidrômetro  " + descricao
							+ " inserido com sucesso.",
					"Inserir outro Retorno de Controle de Hidrômetro",
					"exibirInserirRetornoControleHidrometroAction.do?menu=sim",
					"exibirAtualizarRetornoControleHidrometroAction.do?idRegistroAtualizacao="
							+ idRetornoControleHidrometro,
					"Atualizar Retorno de Controle de Hidrômetro Inserido");
 
			sessao.removeAttribute("InserirRetornoControleHidrometroActionForm");
		}
		return retorno;
	}
}
