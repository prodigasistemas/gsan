package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

public class ExibirFiltrarGerarLoteAtualizacaoCadastralAction extends GcomAction {
	
	private static Logger logger = Logger.getLogger(ExibirFiltrarGerarLoteAtualizacaoCadastralAction.class);
	
	private GerarLoteAtualizacaoCadastralForm form;
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.form = (GerarLoteAtualizacaoCadastralForm) actionForm;

		try {
			HttpSession sessao = request.getSession(false);

			carregarComboEmpresas(sessao);
			carregarComboLeiturista(sessao);
			
			form.setNomeLocalidadeInicial(this.pesquisarNomeLocalidade(form.getIdLocalidadeInicial()));
			form.setNomeSetorComercialInicial(this.pesquisarNomeSetor(form));
			
			carregarComboOcorrencias(sessao);
		} catch (Exception e) {
			logger.error("Erro ao filtrar Cadastro", e);
		}

		return mapping.findForward("exibirFiltrarGerarLoteAtualizacaoCadastralAction");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarComboLeiturista(HttpSession sessao) {
		Collection colecao = new ArrayList();
		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1") && !form.getIdEmpresa().equals("")) {
			Filtro filtro = new FiltroLeiturista(FiltroLeiturista.FUNCIONARIO_NOME);
			filtro.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, form.getIdEmpresa()));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);

			Collection pesquisa = getFachada().pesquisar(filtro, Leiturista.class.getName());

			if (pesquisa != null && !pesquisa.isEmpty()) {
				Iterator it = pesquisa.iterator();
				while (it.hasNext()) {
					Leiturista leiturista = (Leiturista) it.next();
					DadosLeiturista dadosLeiturista = new DadosLeiturista(leiturista);
					colecao.add(dadosLeiturista);
				}
			}
		}

		sessao.setAttribute("colecaoLeiturista", colecao);
	}

	@SuppressWarnings("unchecked")
	private void carregarComboEmpresas(HttpSession sessao) {
		if (sessao.getAttribute("colecaoEmpresa") == null) {
			Filtro filtro = new FiltroEmpresa();
			filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

			Collection<Empresa> colecao = getFachada().pesquisar(filtro, Empresa.class.getName());

			if ((colecao == null) || (colecao.size() == 0)) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
			} else {
				sessao.setAttribute("colecaoEmpresa", colecao);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
    private String pesquisarNomeLocalidade(String idLocalidade) {
        if(idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("") && !idLocalidade.trim().equalsIgnoreCase("")) {
            Filtro filtro = new FiltroLocalidade(FiltroLocalidade.DESCRICAO);
            filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
            
            Collection pesquisa = getFachada().pesquisar(filtro, Localidade.class.getName());
            
            String nomeLocalidade = "";
            if (pesquisa != null && !pesquisa.isEmpty()) {
                nomeLocalidade = (((List<Localidade>) pesquisa).get(0)).getDescricao();
            }
            
            return nomeLocalidade.trim();
        } else {
            return null;
        }
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String pesquisarNomeSetor(GerarLoteAtualizacaoCadastralForm form) {
		String idSetor = form.getCdSetorComercialInicial();
		
        if(idSetor != null && !idSetor.trim().equalsIgnoreCase("") && !idSetor.trim().equalsIgnoreCase("")) {
            Filtro filtro = new FiltroSetorComercial(FiltroSetorComercial.DESCRICAO);
            filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCdSetorComercialInicial()));
            filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidadeInicial()));
            
            Collection pesquisa = getFachada().pesquisar(filtro, SetorComercial.class.getName());
            
            String nomeLocalidade = "";
            if (pesquisa != null && !pesquisa.isEmpty()) {
                nomeLocalidade = (((List<SetorComercial>) pesquisa).get(0)).getDescricao();
            }
            
            return nomeLocalidade.trim();
        } else {
            return null;
        }
    }
	
	@SuppressWarnings("unchecked")
	private void carregarComboOcorrencias(HttpSession sessao) {
		if (StringUtils.isNotEmpty(form.getOcorrenciaCadastro()) && !form.getOcorrenciaCadastro().equals("-1")) {

			Filtro filtro = new FiltroCadastroOcorrencia(FiltroCadastroOcorrencia.ID);
			filtro.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.INDICADOR_VALIDACAO, form.getOcorrenciaCadastro()));

			Collection<CadastroOcorrencia> colecao = getFachada().pesquisar(filtro, CadastroOcorrencia.class.getName());
			sessao.setAttribute("colecaoCadastroOcorrencia", colecao);
		} else {
			sessao.setAttribute("colecaoCadastroOcorrencia", null);
		}
	} 
}
