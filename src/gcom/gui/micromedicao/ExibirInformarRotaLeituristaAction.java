package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarRotaLeituristaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InformarRotaLeituristaActionForm form = (InformarRotaLeituristaActionForm) actionForm;
		
		ActionForward retorno = actionMapping.findForward("InformarRotaLeituristaAction");
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		
		Fachada f = Fachada.getInstancia();
		
		Collection colecaoEmpresa = f.pesquisar(filtroEmpresa,Empresa.class.getName());
		
		String mudou = (String)httpServletRequest.getParameter("mudou");
        
         Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
         
         if (httpServletRequest.getParameter("menu") != null) {
             form.setEmpresaID(""+usuarioLogado.getEmpresa().getId());
         }
		
		if(mudou!=null){
			if(mudou.equals("empresa")){
				form.setRotas( null);
				form.setLeitursitaID(null);
			}else if(mudou.equals("leiturista")){
				form.setRotas(null);
			}
		}
        
        sessao.removeAttribute("permissao");
        if(usuarioLogado.getEmpresa().getIndicadorEmpresaPrincipal().equals(new Short("1"))){
            sessao.setAttribute("permissao", "1");
        }else{
            sessao.setAttribute("permissao", "2");
        }
		
		if(colecaoEmpresa !=null){
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
		Collection colecaoLeiturista = new ArrayList();
		if(form.getEmpresaID()!=null && !form.getEmpresaID().equals("")){
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.ID);
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, form.getEmpresaID()));
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			
			filtroLeiturista.setCampoOrderBy( FiltroLeiturista.FUNCIONARIO_NOME, FiltroLeiturista.CLIENTE_NOME );
			
			Collection colecao = f.pesquisar(filtroLeiturista, Leiturista.class.getName());
			
			if(colecao!=null && !colecao.isEmpty()){
				Iterator it = colecao.iterator();
				while(it.hasNext()){
					Leiturista leitu =(Leiturista) it.next();
					DadosLeiturista dadosLeiu = null;
					if(leitu.getFuncionario()!=null){
						dadosLeiu = new DadosLeiturista(leitu.getId(),leitu.getFuncionario().getNome());
					}else{
						dadosLeiu = new DadosLeiturista(leitu.getId(),leitu.getCliente().getNome());
					}
					colecaoLeiturista.add(dadosLeiu);
				}
			}
			
		}
		Collection rotas = null;
		
		if(form.getLeitursitaID()!=null && !form.getLeitursitaID().equals("")
				&& !form.getLeitursitaID().equals("-1")){
			// Ordenar pelo novo campo q eu vou colocar
			FiltroRota filtroRota = new FiltroRota(FiltroRota.LEITURA_SEQUENCIA);
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LEITURISTA_ID,form.getLeitursitaID()));
			filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
			filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
			filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
			filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
			
			Collection colecao = f.pesquisar(filtroRota, Rota.class.getName());
			
			
			if(colecao != null && !colecao.isEmpty()){
				Iterator it = colecao.iterator();
				rotas = new ArrayList();
				while(it.hasNext()){
					Rota rota = (Rota) it.next();
					String descricao = rota.getEmpresa().getDescricao() + " " 
						+ rota.getFaturamentoGrupo().getDescricaoAbreviada() + " " +
						rota.getSetorComercial().getLocalidade().getId() +"." + 
						rota.getSetorComercial().getCodigo() + "." + rota.getCodigo();
					DadosRota dadosRota = new DadosRota(rota.getId(),descricao);
					rotas.add(dadosRota);
				}
			}
			
			
		}
		sessao.setAttribute("colecaoRotas", rotas);
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
		return retorno;
	}
	
}
