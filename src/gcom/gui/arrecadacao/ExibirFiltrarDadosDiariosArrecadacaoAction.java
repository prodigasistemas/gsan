package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 15/05/2007
 */
public class ExibirFiltrarDadosDiariosArrecadacaoAction extends GcomAction {

    public ActionForward execute(
    		ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("filtrarDadosDiariosArrecadacao");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        // Verificamos se o processo de dados diários da arrecadação está rodando
        fachada.verificarBatchDadosDiariosArracadacaoRodando();
        
        FiltrarDadosDiariosArrecadacaoActionForm filtrarDadosDiariosArrecadacaoActionForm = (FiltrarDadosDiariosArrecadacaoActionForm) actionForm;
        
        String idLocalidade = filtrarDadosDiariosArrecadacaoActionForm.getLocalidade();
        String idElo = filtrarDadosDiariosArrecadacaoActionForm.getIdElo();
        String idArrecadador = filtrarDadosDiariosArrecadacaoActionForm.getIdArrecadador();
        
        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        boolean entrar = false;
        // Pesquisando a localidade
        if (idLocalidade != null && !idLocalidade.trim().equals("")) {
        	
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade.iterator().next();
				
				filtrarDadosDiariosArrecadacaoActionForm.setLocalidade(idLocalidade);
				filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade(localidade.getDescricao());
				
				if(idElo == null || idElo.equals("")) {
					filtrarDadosDiariosArrecadacaoActionForm.setIdElo(localidade.getLocalidade().getId().toString());
					filtrarDadosDiariosArrecadacaoActionForm.setNomeElo(localidade.getLocalidade().getDescricao());
				}
				entrar = true;
			} else {
				filtrarDadosDiariosArrecadacaoActionForm.setLocalidade("");
				filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInexistente", true);
			}
		} else {
			filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade("");
		}
        
        if (idArrecadador != null && !idArrecadador.trim().equals("") && Integer.parseInt(idArrecadador) > 0) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, idArrecadador));
			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			Collection<Arrecadador> arrecadadorEncontrado = fachada.pesquisar(filtroArrecadador,Arrecadador.class.getName());

			if (arrecadadorEncontrado != null && !arrecadadorEncontrado.isEmpty()) {
				Arrecadador arrecadador = (Arrecadador) arrecadadorEncontrado.iterator().next();

				filtrarDadosDiariosArrecadacaoActionForm.setIdArrecadador(idArrecadador);
				filtrarDadosDiariosArrecadacaoActionForm.setNomeArrecadador(arrecadador.getCliente().getNome());

			} else {
				filtrarDadosDiariosArrecadacaoActionForm.setIdArrecadador("");
				filtrarDadosDiariosArrecadacaoActionForm.setNomeArrecadador("ARRECADADOR INEXISTENTE");
				httpServletRequest.setAttribute("arrecadadorInexistente", true);
			}
		} else {
			filtrarDadosDiariosArrecadacaoActionForm.setNomeArrecadador("");
		}

        // Pesquisando a Elo Polo
        if (idElo != null && !idElo.trim().equals("")) {
        
        	filtroLocalidade.limparListaParametros();
        	
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, idElo));
			
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				boolean flag = false;
				Localidade elo = (Localidade) colecaoLocalidade.iterator().next();

				filtrarDadosDiariosArrecadacaoActionForm.setIdElo(idElo);
				filtrarDadosDiariosArrecadacaoActionForm.setNomeElo(elo.getLocalidade().getDescricao());
				
				Iterator iteratorColecaoLocalidade = colecaoLocalidade.iterator();
				
				while (iteratorColecaoLocalidade.hasNext()) {
					
					// Obtém os dados do crédito realizado
					Localidade elo2 = (Localidade) iteratorColecaoLocalidade.next();

					if(idLocalidade != null && !idLocalidade.equals("")) {
						if(elo2.getId().toString().equals(idLocalidade)) {
							flag = true;
							String eloDiferente = "NAO";
							filtrarDadosDiariosArrecadacaoActionForm.setEloDiferente(eloDiferente);
						}
					}
				}
				if(!flag && idLocalidade != null && !idLocalidade.equals("")){
					filtrarDadosDiariosArrecadacaoActionForm.setLocalidade("");
					filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("localidadeInexistente", true);
				}
				filtrarDadosDiariosArrecadacaoActionForm.setLocalidadeDoElo(elo.getId().toString());
			} else {
				filtrarDadosDiariosArrecadacaoActionForm.setIdElo("");
				filtrarDadosDiariosArrecadacaoActionForm.setNomeElo("ELO PÓLO INEXISTENTE");
				httpServletRequest.setAttribute("eloInexistente", true);
			}
		} else if (!entrar){
			filtrarDadosDiariosArrecadacaoActionForm.setNomeElo("");
		}
        
        // Pesquisando Gerência Regional
        if (sessao.getAttribute("colecaoGerenciaRegional") == null) {
    		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME_ABREVIADO);
    		filtroGerenciaRegional.setConsultaSemLimites(true);
    		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
    		Collection<GerenciaRegional> colecaoGerenciasRegionais = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
    		sessao.setAttribute("colecaoGerenciasRegionais",colecaoGerenciasRegionais);
        }

        
        // Pesquisando Perfil do Imóvel
        if (sessao.getAttribute("colecaoImovelPerfil") == null) {
        	FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
        	filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        	Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
        	sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
        }

        
        // Pesquisando o Tipo de Documento
        if (sessao.getAttribute("colecaoDocumentoTipo") == null) {
			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
			filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);
			Collection colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo, DocumentoTipo.class.getName());
			sessao.setAttribute("colecaoDocumentoTipo",colecaoDocumentoTipo);
		}

        
        // Pesquisando Situação da Ligação de Água
        if (sessao.getAttribute("colecaoSituacaoLigacaoAgua") == null) {
        	FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
        	filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
        	Collection<LigacaoAguaSituacao> colecaoSituacaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
        	sessao.setAttribute("colecaoSituacaoLigacaoAgua", colecaoSituacaoLigacaoAgua);
        }

        
        // Pesquisando Situação da Ligação de Esgoto
        if (sessao.getAttribute("colecaoSituacaoLigacaoEsgoto") == null) {
        	FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
        	filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
        	Collection<LigacaoEsgotoSituacao> colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
        	sessao.setAttribute("colecaoSituacaoLigacaoEsgoto", colecaoSituacaoLigacaoEsgoto);
        }
        
        
        // Pesquisando Categoria
        if (sessao.getAttribute("colecaoCategoria") == null) {
        	FiltroCategoria filtroCategoria = new FiltroCategoria();
        	filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
        	Collection<Categoria> colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
        	sessao.setAttribute("colecaoCategoria", colecaoCategoria);
        }
        
        
        // Pesquisando Categoria
        if (sessao.getAttribute("colecaoEsferaPoder") == null) {
        	FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
        	filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
        	Collection<EsferaPoder> colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
        	sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
        }
        
        // Pesquisando Sistema Parâmetros
        if (sessao.getAttribute("sistemaParametro") == null) {
        	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        	sessao.setAttribute("sistemaParametro", sistemaParametro);
        }
        
        // devolve o mapeamento de retorno
        return retorno;
    }

}
