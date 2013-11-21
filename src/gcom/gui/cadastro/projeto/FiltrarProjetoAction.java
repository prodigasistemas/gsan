package gcom.gui.cadastro.projeto;

import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarProjetoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterProjeto");
		
		FiltrarProjetoActionForm form = (FiltrarProjetoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera dados do form
		String codigo = form.getId();
		String nome = form.getNome();
		String nomeAbreviado = form.getNomeAbreviado();
		String idOrgaoFinanciador = form.getIdOrgaoFinanciador();
		String tipoPesquisa = form.getTipoPesquisa();
		String situacao = form.getSituacao(); 
		
		boolean peloMenosUmParametroInformado = false;
		
		FiltroProjeto filtroProjeto = new FiltroProjeto();
		
		filtroProjeto.adicionarCaminhoParaCarregamentoEntidade("orgaoFinanciador");
		
		if(codigo!=null && !codigo.equals("")){
			filtroProjeto.adicionarParametro(new ParametroSimples(FiltroProjeto.ID,codigo));
			peloMenosUmParametroInformado = true;
		}
		if(nome!=null && !nome.equals("")){
			if(tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())){
				filtroProjeto.adicionarParametro(new ComparacaoTexto(FiltroProjeto.NOME,nome));
			}else if(tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())){
				filtroProjeto.adicionarParametro(new ComparacaoTextoCompleto(FiltroProjeto.NOME,nome));
			}
			peloMenosUmParametroInformado = true;
		}
		if(nomeAbreviado!=null && !nomeAbreviado.equals("")){
			filtroProjeto.adicionarParametro(new ComparacaoTexto(FiltroProjeto.NOME_ABREVIADO,nomeAbreviado));
			peloMenosUmParametroInformado = true;
		}
		if(idOrgaoFinanciador!=null && !idOrgaoFinanciador.equals("")){
			filtroProjeto.adicionarParametro(new ParametroSimples(FiltroProjeto.ID_ORGAO_FINACIADOR,idOrgaoFinanciador));
			peloMenosUmParametroInformado = true;
		}
		if(situacao!=null && !situacao.equals("")){
			Integer tipo = new Integer(situacao);
			switch (tipo) {
			//Todos
			case 1:

				break;
			//Em andamento	
			case 2:
				
				filtroProjeto.adicionarParametro(new ParametroNulo(FiltroProjeto.DATA_FIM));
				
				break;
			//Encerrados	
			case 3:
				
				filtroProjeto.adicionarParametro(new ParametroNaoNulo(FiltroProjeto.DATA_FIM));
				
				break;						
			default:
				
				break;
			}
			
			 peloMenosUmParametroInformado = true;

		}
		//Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		Collection colecaoProjetos = fachada.pesquisar(filtroProjeto,Projeto.class.getName());
		
		if(colecaoProjetos==null || colecaoProjetos.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null, "Projeto");
		}
		
		//Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pela sessão uma variável para o
		// ExibirManterEquipeAction e nele verificar se irá para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sessão
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}
		
		
		sessao.setAttribute("filtroProjeto",filtroProjeto);
		
		
		return retorno;
	}
}
