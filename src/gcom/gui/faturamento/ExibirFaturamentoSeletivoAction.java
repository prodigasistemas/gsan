package gcom.gui.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosMovimentacao;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFaturamentoSeletivoAction extends GcomAction {

	private int qtdRgistrosExibidos = 12;
	
	@Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        HttpSession sessao = httpServletRequest.getSession(false);

        ActionForward retorno = actionMapping.findForward("exibirFaturamentoSeletivo");

        Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);

        FaturamentoSeletivoActionForm form = (FaturamentoSeletivoActionForm) actionForm;

        limparLeiturasAntigas(form);

        FiltroLeituraAnormalidade filtro = new FiltroLeituraAnormalidade(FiltroLeituraAnormalidade.ID);
        filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO, ConstantesSistema.SIM));
        filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO_SISTEMA, ConstantesSistema.NAO));

        Fachada fachada = Fachada.getInstancia();

        StringBuffer faixas = new StringBuffer();
        
        Integer matriculaImovel = null;
        if (form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")) {
        	matriculaImovel = new Integer(form.getMatriculaImovel());
        }
        
        verificarAbrangenciaUsuario(httpServletRequest, usuarioLogado, Util.converterStringParaInteger(form.getIdLocalidade()));

        Rota rota = obterRota(form, fachada);
        
        if (rota != null) {
            form.setDescricaoRota(getDescricaoRota(rota));

            Collection<DadosMovimentacao> dados = fachada.buscarImoveisPorRota(matriculaImovel, rota, form.getTipo().trim().equals("1"));

            if (dados != null && !dados.isEmpty()) {

                Vector<DadosMovimentacao> v = new Vector<DadosMovimentacao>();
                v.addAll(dados);
                
                form.setDados(v);
                form.setIndice(new Integer(1));
                form.setTotal(obterQtdTotalPaginas(dados));
                
                Collection<DadosMovimentacao> dadosExibicao = new ArrayList<DadosMovimentacao>();
                Iterator<DadosMovimentacao> it = dados.iterator();
                char delimitador = '/';
                char delimitador2 = ';';

                for (int i = 0; i < this.qtdRgistrosExibidos && it.hasNext(); i++) {
                    DadosMovimentacao dado = it.next();
                    dado.getInscricao();
                    faixas.append(dado.getFaixaLeituraEsperadaInferior());
                    faixas.append(delimitador2);
                    faixas.append(dado.getFaixaLeituraEsperadaSuperior());
                    if (i + 1 < this.qtdRgistrosExibidos && it.hasNext()) {
                        faixas.append(delimitador);
                    }
                    dadosExibicao.add(dado);
                }

                sessao.setAttribute("colecaoLeituras", dadosExibicao);
                httpServletRequest.setAttribute("qnt", "" + dadosExibicao.size());

                listarAnormalidadesSessao(httpServletRequest, filtro,delimitador, delimitador2);
                
                httpServletRequest.setAttribute("faixa", faixas.toString());

            } else {
                throw new ActionServletException("atencao.rota_sem_imovel_para_leitura", form.getRota());
            }

        } else {
            throw new ActionServletException("atencao.pesquisa.rota_inexistente");
        }

        httpServletRequest.setAttribute("nomeCampo", "rota");

        return retorno;
    }

	@SuppressWarnings("unchecked")
	private Rota obterRota(FaturamentoSeletivoActionForm form, Fachada fachada) {
		Rota rota = null;
		
		FiltroRota filtroRota = new FiltroRota();
        filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
        filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
        filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
        filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
        filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LEITURA_TIPO);
        filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, form.getIdLocalidade()));
        filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, form.getCodigoSetorComercial()));
        filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getRota()));

        Collection<Rota> colecao = (Collection<Rota>)fachada.pesquisar(filtroRota, Rota.class.getName());
        
        if (colecao != null && !colecao.isEmpty()) {
            rota = (Rota) colecao.iterator().next();
        }
		return rota;
	}

	@SuppressWarnings("unchecked")
	private void listarAnormalidadesSessao(HttpServletRequest httpServletRequest, FiltroLeituraAnormalidade filtro, char delimitador, char delimitador2) {
		Collection<LeituraAnormalidade> colecaoLeituraAnormalidade = Fachada.getInstancia().pesquisar(filtro, LeituraAnormalidade.class.getName());

		Iterator<LeituraAnormalidade> iterator = colecaoLeituraAnormalidade.iterator();
		StringBuffer anormalidades = new StringBuffer();
		while (iterator.hasNext()) {
		    LeituraAnormalidade l = (LeituraAnormalidade) iterator.next();
		    anormalidades.append(l.getId().toString());
		    anormalidades.append(delimitador2);
		    anormalidades.append(l.getIndicadorLeitura().toString());

		    if (iterator.hasNext()) {
		        anormalidades.append(delimitador);
		    }

		}

		httpServletRequest.setAttribute("anormalidadesBanco", anormalidades.toString());
	}

	private Integer obterQtdTotalPaginas(Collection<DadosMovimentacao> dados) {
		Integer total = 0;
		
		if (dados.size() % this.qtdRgistrosExibidos == 0) {
		    total = new Integer(dados.size() / this.qtdRgistrosExibidos);
		} else {
		    total = new Integer((dados.size() / this.qtdRgistrosExibidos) + 1);
		}
		
		return total;
	}

	private String getDescricaoRota(Rota rota) {
		return rota.getEmpresa().getDescricao() + " "
		        + rota.getFaturamentoGrupo().getDescricaoAbreviada() + " "
		        + rota.getSetorComercial().getLocalidade().getId() + "." + rota.getSetorComercial().getCodigo()
		        + "." + rota.getCodigo();
	}

	private void limparLeiturasAntigas(FaturamentoSeletivoActionForm form) {
		form.setAnormalidades(null);
        form.setLeituras(null);
        form.setDatas(null);
	}

	private void verificarAbrangenciaUsuario(HttpServletRequest httpServletRequest, Usuario usuarioLogado, Integer idLocalidade) {

        Fachada fachada = Fachada.getInstancia();

        Localidade localidade = obterLocalidade(idLocalidade, fachada);

        if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.LOCALIDADE)) {

            if (!usuarioLogado.getLocalidade().getId().equals(idLocalidade)) {
                throw new ActionServletException("atencao.acesso.negado.abrangencia");
            }

        } else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.GERENCIA_REGIONAL)) {

            if (!usuarioLogado.getGerenciaRegional().getId().equals(localidade.getGerenciaRegional().getId())) {
                throw new ActionServletException("atencao.acesso.negado.abrangencia");
            }

        } else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.UNIDADE_NEGOCIO)) {

            if (!usuarioLogado.getUnidadeNegocio().getId().equals(localidade.getUnidadeNegocio().getId())) {
                throw new ActionServletException("atencao.acesso.negado.abrangencia");
            }

        } else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.ELO_POLO)) {

            if (!usuarioLogado.getLocalidadeElo().getId().equals(localidade.getLocalidade().getId())) {
                throw new ActionServletException("atencao.acesso.negado.abrangencia");
            }

        }
    }

	@SuppressWarnings("unchecked")
	private Localidade obterLocalidade(Integer idLocalidade, Fachada fachada) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
        Collection<Localidade> colecaoLocalidade = (Collection<Localidade>) fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
        Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
		return localidade;
	}

}
