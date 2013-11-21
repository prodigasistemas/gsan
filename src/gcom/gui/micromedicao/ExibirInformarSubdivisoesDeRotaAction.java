package gcom.gui.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.bean.NumeroQuadraMinimoMaximoDaRotaHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * @author Victor Cisneiros
 * @date 28/09/2008
 */
public class ExibirInformarSubdivisoesDeRotaAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno = mapping.findForward("exibirInformarSubdivisoesDeRotaAction");
		InformarSubdivisoesDeRotaActionForm form = (InformarSubdivisoesDeRotaActionForm) actionForm;
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String opcao = request.getParameter("opcao");
		
		List<InformarSubdivisoesDeRotaActionForm> subdivisoesDeRota = new ArrayList<InformarSubdivisoesDeRotaActionForm>();
		if (session.getAttribute("subdivisoesDeRota") != null) {
			subdivisoesDeRota = (List<InformarSubdivisoesDeRotaActionForm>) request.getSession().getAttribute("subdivisoesDeRota");
		}
		
		Boolean editable = true;
		Rota rota = null;
		
		// Pesquisa ROTA
		if (form.getIdRota() != null && !form.getIdRota().trim().equals("")) {
			Integer idRota = new Integer(form.getIdRota());
			
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
			filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
			filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
			filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
			filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
			
			Collection pesquisa = fachada.pesquisar(filtroRota, Rota.class.getName());
			if (pesquisa != null && !pesquisa.isEmpty()) {
				rota = (Rota) Util.retonarObjetoDeColecao(pesquisa);
				
				Integer grupoFaturamento = rota.getFaturamentoGrupo().getId();
				Integer anoMes = sistemaParametro.getAnoMesFaturamento();
				
				// Permitir somente a subdivisão de todas cujo grupo não foi comandado
				Boolean grupoComandado = fachada.verificarGrupoFaturamentoComandado(anoMes, grupoFaturamento);
				if (grupoComandado) {
					throw new ActionServletException("atencao.rota.grupo.faturado");
				}
				
				form.setIdRota(rota.getId().toString());
				form.setDescricaoRota(
						rota.getEmpresa().getDescricao() + " " + 
						rota.getFaturamentoGrupo().getDescricaoAbreviada() + " " +
						rota.getSetorComercial().getLocalidade().getId() +"." + 
						rota.getSetorComercial().getCodigo() + "." + rota.getCodigo());
				
				FiltroRota filtroRotas = new FiltroRota();
				filtroRotas.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
				filtroRotas.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LEITURISTA);
				filtroRotas.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.CLIENTE);
				filtroRotas.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, rota.getSetorComercial().getCodigo()));
				filtroRotas.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, rota.getSetorComercial().getLocalidade().getId()));
				filtroRotas.adicionarParametro(new ParametroSimples(
						FiltroRota.INDICADOR_USO, ConstantesSistema.SIM));
				filtroRotas.setCampoOrderBy(FiltroRota.CODIGO_ROTA);
				Collection<Rota> pesquisaRotas = fachada.pesquisar(filtroRotas, Rota.class.getName());
				
				// se a rota original ja foi subdividida
				if (pesquisa != null && pesquisaRotas.size() > 1) {
					Collection<Integer> idsRotas = new ArrayList<Integer>();
					
					subdivisoesDeRota.clear();
					for (Rota r : pesquisaRotas) {
						idsRotas.add(r.getId());
						
						InformarSubdivisoesDeRotaActionForm af = new InformarSubdivisoesDeRotaActionForm();
						af.setIdRota(r.getId().toString());
						af.setCodigoRota(r.getCodigo().toString());
						if(r.getLeiturista() != null){
						  af.setNomeLeiturista(r.getLeiturista().getCliente().getNome());
						  af.setIdLeiturista(r.getLeiturista().getId().toString());
						}
						af.setQuadraInicial("0");
						af.setQuadraFinal("0");
						af.setQuantidadeQuadras("0");
						
						af.setIdGrupoFaturamento(r.getFaturamentoGrupo().getId().toString());
						af.setIdCobrancaGrupo(r.getCobrancaGrupo().getId().toString());
						
						subdivisoesDeRota.add(af);
					}
					
					Collection<NumeroQuadraMinimoMaximoDaRotaHelper> minmax = 
						fachada.pesquisarNumeroQuadraMininoMaximoDaRota(idsRotas);
					
					for (InformarSubdivisoesDeRotaActionForm af : subdivisoesDeRota) {
						for (NumeroQuadraMinimoMaximoDaRotaHelper numeros : minmax) {
							if (new Integer(af.getIdRota()).equals(numeros.getIdRota())) {
								af.setQuadraInicial(numeros.getNumeroQuadraMinimo().toString());
								af.setQuadraFinal(numeros.getNumeroQuadraMaximo().toString());
								af.setQuantidadeQuadras(numeros.getQuantidadeQuadras().toString());
							}
						}
					}
					
					editable = false;
					session.setAttribute("subdivisoesDeRota", subdivisoesDeRota);
				}
			
			} else {
				form.setIdRota(null);
				form.setDescricaoRota("ROTA INEXISTENTE");
			}
		}
		
		// Removendo subdivisao de ROTA
		if (opcao != null && opcao.equals("removerSubrota")) {
			int index = Integer.parseInt(request.getParameter("index"));
			if (subdivisoesDeRota.size() > index) {
				subdivisoesDeRota.remove(index);
			}
			
			if (subdivisoesDeRota.size() > 0) {
				InformarSubdivisoesDeRotaActionForm ultimo = subdivisoesDeRota.get(subdivisoesDeRota.size() -1);
				session.setAttribute("quadraFinalAnterior", new Integer(ultimo.getQuadraFinal()));
			} else {
				session.removeAttribute("quadraFinalAnterior");
			}
		}
		
		// Adicionando subdivisao de ROTA
		if (opcao != null && opcao.equals("adicionarSubrota")) {
			if (rota == null) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Rota");
			}
			
			Integer quadraFinalAnterior = (Integer) session.getAttribute("quadraFinalAnterior");
			int quadraInicial = Integer.parseInt(form.getQuadraInicial());
			int quadraFinal = Integer.parseInt(form.getQuadraFinal());
			
			if (quadraFinal < quadraInicial) {
				throw new ActionServletException("atencao.quadraInicial.maior.que.quadraFinal");
			}
			
			if (quadraFinalAnterior != null && quadraInicial <= quadraFinalAnterior) {
				throw new ActionServletException("atencao.quadraInicial.menor.que.quadraFinal.anterior");
			}
			
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, form.getIdLeiturista()));
			Collection pesquisaLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
			if (pesquisaLeiturista == null || pesquisaLeiturista.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Leiturista");
			}
			Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(pesquisaLeiturista);
			
			InformarSubdivisoesDeRotaActionForm linha = new InformarSubdivisoesDeRotaActionForm();
			linha.setIdRota(form.getIdRota());
			
			Integer quantidade = fachada.pesqisarQuantidadeQuadrasDaRota(rota.getId(), quadraInicial, quadraFinal);
			
			linha.setQuadraInicial(form.getQuadraInicial());
			linha.setQuadraFinal(form.getQuadraFinal());
			linha.setIdLeiturista(form.getIdLeiturista());
			linha.setQuantidadeQuadras(quantidade.toString());
			linha.setNomeLeiturista(leiturista.getCliente().getNome());
			
			subdivisoesDeRota.add(linha);
			session.setAttribute("quadraFinalAnterior", quadraFinal);
			session.setAttribute("subdivisoesDeRota", subdivisoesDeRota);
		}
		
		if (opcao != null && opcao.equals("resetarSubrotas") && editable) {
			session.removeAttribute("quadraFinalAnterior");
			session.removeAttribute("subdivisoesDeRota");
		}
		
		if (session.getAttribute("quadraFinalAnterior") == null) {
			session.setAttribute("quadraFinalAnterior", -1);
		}
		
		session.setAttribute("editable", editable);
		if (subdivisoesDeRota.size() >= 10) {
			session.setAttribute("limiteSubdivisoes", true);
		} else {
			session.setAttribute("limiteSubdivisoes", false);
		}
		
		if (rota != null && editable) {
			String codigoRota = rota.getCodigo().toString();
			
			int index = 0;
			for (InformarSubdivisoesDeRotaActionForm subrota : subdivisoesDeRota) {
				if (index == 0) {
					subrota.setCodigoRota(codigoRota);
				} else {
					subrota.setCodigoRota(codigoRota + index);
				}
				index++;
			}
		}
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		if (usuario.getEmpresa() == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usuário logado.");
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

		return retorno;
	}

}
