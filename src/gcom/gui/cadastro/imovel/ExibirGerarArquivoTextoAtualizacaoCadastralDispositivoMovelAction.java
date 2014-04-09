package gcom.gui.cadastro.imovel;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroSituacaoTransmissaoLeitura;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
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

public class ExibirGerarArquivoTextoAtualizacaoCadastralDispositivoMovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirGerarArquivoTextoAtualizacaoCadastralDispositivoMovel");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form = (GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm) actionForm;
        
        Collection colecaoImovelFiltrado = (Collection)sessao.getAttribute("colecaoImovelFiltrado");
        
        if ( colecaoImovelFiltrado != null && !colecaoImovelFiltrado.isEmpty() ) {
        	form.setTamanhoColecaoImovel(colecaoImovelFiltrado.size());
        
        } else {
        	Integer quantidadeImovel = fachada.pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount();
        	
        	if ( quantidadeImovel == null || quantidadeImovel <= 0 ){
        		//Nenhum Imovel cadastrado
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
        	}
        	form.setTamanhoColecaoImovel(quantidadeImovel);
        }
        
        //Pesquisar Leiturista
   	 	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
   	 	Integer idEmpresa = usuario.getEmpresa().getId();
   	 	
		Collection colecaoLeiturista = new ArrayList();
		
		// Leiturista da Empresa
		if (idEmpresa != null && !idEmpresa.equals("")) {

			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(
					FiltroLeiturista.ID);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.EMPRESA_ID, idEmpresa));
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);

			Collection colecao = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				Iterator it = colecao.iterator();
				while (it.hasNext()) {
					Leiturista leitu = (Leiturista) it.next();
					DadosLeiturista dadosLeiu = null;
					if (leitu.getFuncionario() != null) {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getFuncionario().getNome());
					} else {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getCliente().getNome());
					}
					colecaoLeiturista.add(dadosLeiu);
				}
			}

		}
		
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
   	 	
        //Pesquisar Situacão Transmissão Leitura
        Collection colecaoSituacaoTransmissaoLeitura = (Collection)sessao.getAttribute("colecaoSituacaoTransmissaoLeitura");
        if(colecaoSituacaoTransmissaoLeitura == null || colecaoSituacaoTransmissaoLeitura.isEmpty()){

			FiltroSituacaoTransmissaoLeitura filtroSituacaoTransmissaoLeitura = new FiltroSituacaoTransmissaoLeitura();
			filtroSituacaoTransmissaoLeitura
					.setCampoOrderBy(FiltroSituacaoTransmissaoLeitura.DESCRICAO);
			filtroSituacaoTransmissaoLeitura
					.adicionarParametro(new ParametroSimples(
							FiltroSituacaoTransmissaoLeitura.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
	
			colecaoSituacaoTransmissaoLeitura = Fachada.getInstancia()
					.pesquisar(filtroSituacaoTransmissaoLeitura,
							SituacaoTransmissaoLeitura.class.getName());
	
			sessao.setAttribute("colecaoSituacaoTransmissaoLeitura",
					colecaoSituacaoTransmissaoLeitura);
        }
        
        return retorno;
    }
    
}
