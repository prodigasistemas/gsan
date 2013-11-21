package gcom.gui.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @see gcom.gui.micromedicao.InformarSubdivisoesDeRotaAction
 * @see gcom.gui.micromedicao.InformarSubdivisoesDeRotaActionForm
 * @see gcom.gui.micromedicao.ExibirInformarSubdivisoesDeRotaAction
 * 
 * @author Ivan Sergio
 * @date 18/02/2010
 */
public class ExibirAlterarSubdivisoesDeRotaAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno = mapping.findForward("exibirAlterarSubdivisoesDeRotaAction");
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		HttpSession session = request.getSession();
		String opcao = request.getParameter("opcao");
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		if (usuario.getEmpresa() == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usuário logado.");
		}
		
		// Adicionando subdivisao de ROTA
		if (opcao != null && opcao.equals("adicionarSubrota")) {
			List<InformarSubdivisoesDeRotaActionForm> subdivisoesDeRota = new ArrayList<InformarSubdivisoesDeRotaActionForm>();
			if (session.getAttribute("subdivisoesDeRota") != null) {
				subdivisoesDeRota = (List<InformarSubdivisoesDeRotaActionForm>) request.getSession().getAttribute("subdivisoesDeRota");
			}
			
			// Atualiza a Lista antes de adicionar a nova Rota
			List<InformarSubdivisoesDeRotaActionForm> subdivisoesDeRotaNova = new ArrayList<InformarSubdivisoesDeRotaActionForm>();
			for (InformarSubdivisoesDeRotaActionForm subdivisao : subdivisoesDeRota) {
				subdivisao.setQuadraInicial(request.getParameter("quadra_ini_" + subdivisao.getCodigoRota()));
				subdivisao.setQuadraFinal(request.getParameter("quadra_fim_" + subdivisao.getCodigoRota()));
				subdivisao.setIdLeiturista(request.getParameter("leiturista_" + subdivisao.getCodigoRota()));
				
				subdivisoesDeRotaNova.add(subdivisao);
				session.setAttribute("quadraFinalAnterior", new Integer(subdivisao.getQuadraFinal()));
			}
			
			InformarSubdivisoesDeRotaActionForm form = (InformarSubdivisoesDeRotaActionForm) actionForm;
			Integer quadraFinalAnterior = (Integer) session.getAttribute("quadraFinalAnterior");
			int quadraInicial = Integer.parseInt(form.getQuadraInicial());
			int quadraFinal = Integer.parseInt(form.getQuadraFinal());
			
			if (quadraFinal < quadraInicial) {
				throw new ActionServletException("atencao.quadraInicial.maior.que.quadraFinal");
			}
			
			if (quadraFinalAnterior != null && quadraInicial <= quadraFinalAnterior) {
				throw new ActionServletException("atencao.quadraInicial.menor.que.quadraFinal.anterior");
			}
			
			InformarSubdivisoesDeRotaActionForm linha = new InformarSubdivisoesDeRotaActionForm();
			// Informa -1 como flag de nova Rota
			linha.setIdRota("-1");
			linha.setQuadraInicial(form.getQuadraInicial());
			linha.setQuadraFinal(form.getQuadraFinal());
			linha.setIdLeiturista(form.getIdLeiturista());
			
			Integer codigoRota = new Integer(session.getAttribute("codigoRotaFinalAnterior").toString());
			codigoRota++;
			linha.setCodigoRota(codigoRota.toString());
			
			subdivisoesDeRotaNova.add(linha);
			session.setAttribute("quadraFinalAnterior", quadraFinal);
			session.setAttribute("subdivisoesDeRota", subdivisoesDeRotaNova);
		}
		
		
		// Pesquisa LEITURISTA
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
		if (!usuario.getEmpresa().getDescricao().equals(sistemaParametro.getNomeAbreviadoEmpresa())) {
			request.setAttribute("idEmpresaLeituristica", "?idEmpresaLeituristicaRecebida=" + usuario.getEmpresa().getId() + "&");
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, usuario.getEmpresa().getId()));
		}
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
		//*******************************************************
		// Autor: Ivan Segio
		// Data: 15/07/2009
		// Evita erro quando o Leiturista esta cadastrado como
		// Funcionario. Foi definido que um Leiturista deve ser
		// cadastrado como Cliente.
		//*******************************************************
		filtroLeiturista.adicionarParametro(new ParametroNaoNulo(FiltroLeiturista.CLIENTE));
		//*******************************************************
		filtroLeiturista.setCampoOrderBy("cliente.nome");

		Collection<Leiturista> colecaoLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
		
		request.setAttribute("colecaoLeiturista", colecaoLeiturista);
		
		// Recupera o ultimo codigo da rota para o caso do usuario inserir novas rotas
		List<InformarSubdivisoesDeRotaActionForm> subdivisoesDeRota = 
			(List<InformarSubdivisoesDeRotaActionForm>) request.getSession().getAttribute("subdivisoesDeRota");
		InformarSubdivisoesDeRotaActionForm ultimo = subdivisoesDeRota.get(subdivisoesDeRota.size() -1);
		session.setAttribute("codigoRotaFinalAnterior", new Integer(ultimo.getCodigoRota()));
		session.setAttribute("quadraFinalAnterior", new Integer(ultimo.getQuadraFinal()));
		
		return retorno;
	}
}
