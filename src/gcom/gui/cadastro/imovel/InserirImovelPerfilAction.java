package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
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
 * 
 * @author Wallace Thierre
 * @date 24/09/2010 
 *
 */
public class InserirImovelPerfilAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
				
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirImovelPerfilActionForm inserirImovelPerfilActionForm = 
			(InserirImovelPerfilActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		ImovelPerfil imovelPerfil = new ImovelPerfil();
		
		String descricao = inserirImovelPerfilActionForm.getDescricao();
		
		Collection colecaoPesquisa = null;
		
		//Descricao Imovel
		if(!"".equals(inserirImovelPerfilActionForm.getDescricao())){
			imovelPerfil.setDescricao(inserirImovelPerfilActionForm.getDescricao());
		} else{
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		//Indicador de Uso
		if(inserirImovelPerfilActionForm.getIndicadorUso()!= null){
			
			imovelPerfil.setIndicadorUso(inserirImovelPerfilActionForm.getIndicadorUso());			
		} else{
			throw new ActionServletException("atencao.required", null, "indicador de uso");
		}
		
		//Indicador de Geracao Automatico
		if(inserirImovelPerfilActionForm.getIndicadorGeracaoAutomatica()!= null){
			
			imovelPerfil.setIndicadorGeracaoAutomatica(inserirImovelPerfilActionForm.getIndicadorGeracaoAutomatica());
		} else{
			throw new ActionServletException("atencao.required", null, "indicador de geração automático");
		}
		
		//Indicador Inserir Manter Perfil
		if(inserirImovelPerfilActionForm.getIndicadorInserirManterPerfil()!= null){
			
			imovelPerfil.setIndicadorInserirManterPerfil(inserirImovelPerfilActionForm.getIndicadorInserirManterPerfil());
		} else{
			throw new ActionServletException("atencao.required", null, "inserir manter perfil");
		}
		
		//Indicador Gerar Dados Leitura
		if(inserirImovelPerfilActionForm.getIndicadorGerarDadosLeitura()!= null){
			
			imovelPerfil.setIndicadorGerarDadosLeitura(inserirImovelPerfilActionForm.getIndicadorGerarDadosLeitura());
		} else{
			throw new ActionServletException("atencao.required", null, "inserir gerar dados leitura");
		}
		
		//Indicador Bloquear Reatificação
		if(inserirImovelPerfilActionForm.getIndicadorBloquearRetificacao()!= null){
			
			imovelPerfil.setIndicadorBloquearRetificacao(inserirImovelPerfilActionForm.getIndicadorBloquearRetificacao());
		} else{
			throw new ActionServletException("atencao.required", null, "bloquear reatificação");
		}
		
		//Indicador de Grande Consumidor
		if(inserirImovelPerfilActionForm.getIndicadorGrandeConsumidor()!= null){
			
			imovelPerfil.setIndicadorGrandeConsumidor(inserirImovelPerfilActionForm.getIndicadorGrandeConsumidor());
		} else{
			throw new ActionServletException("atencao.required", null, "grande consumidor");
		}
		
		//Indicador Bloquear Dados Sociais
		if(inserirImovelPerfilActionForm.getIndicadorBloqueaDadosSocial()!= null){
			
			imovelPerfil.setIndicadorBloqueaDadosSocial(inserirImovelPerfilActionForm.getIndicadorBloqueaDadosSocial());
		} else{
			imovelPerfil.setIndicadorBloqueaDadosSocial((short) 2);
		}
		
		//Gerar Débitos 2° Via
		if(inserirImovelPerfilActionForm.getIndicadorGeraDebitoSegundaViaConta()!= null){
			
			imovelPerfil.setIndicadorGeraDebitoSegundaViaConta(inserirImovelPerfilActionForm.getIndicadorGeraDebitoSegundaViaConta());
		} else{
			throw new ActionServletException("atencao.required", null, "gerar debitos 2° via conta");
		}
		
		//Ultima Alteração
		imovelPerfil.setUltimaAlteracao(new Date());
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.DESCRICAO, imovelPerfil.getDescricao()));
		
		colecaoPesquisa = (Collection) fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoPesquisa!= null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null,
					imovelPerfil.getDescricao());
		}else{
			imovelPerfil.setDescricao(descricao);
		
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_IMOVEL_PERFIL_INSERIR, imovelPerfil.getId(),
				imovelPerfil.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		registradorOperacao.registrarOperacao(imovelPerfil);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
				
		Integer idImovelPerfil = (Integer) fachada.inserir(imovelPerfil);
		
		montarPaginaSucesso(httpServletRequest,"Imóvel perfil  " + descricao
						+ " inserido com sucesso.",
				"Inserir outro Imovel Perfil",
				"exibirInserirImovelPerfilAction.do?menu=sim",
				"exibirAtualizarImovelPerfilAction.do?idRegistroAtualizacao="
						+ idImovelPerfil,
				"Atualizar Imovel Perfil Inserido");

		sessao.removeAttribute("InserirImovelPerfilActionForm");	
		

		}
		return retorno;		
		
	}	

}
