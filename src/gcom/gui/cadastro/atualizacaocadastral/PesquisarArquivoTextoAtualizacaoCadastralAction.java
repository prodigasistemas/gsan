package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroArquivoTextoAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarArquivoTextoAtualizacaoCadastralAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("retornarArquivoTextoAtualizacaoCadastralPesquisar");
        
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        PesquisarArquivoTextoAtualizacaoCadastralActionForm pesquisarActionForm = (PesquisarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

        //cria variaveis
        String descricaoArquivo = pesquisarActionForm.getDescricao();
        String idLeiturista = pesquisarActionForm.getIdLeiturista();
        String idSituacaoTransmissao = pesquisarActionForm.getIdSituacaoTransmissao();
        
        boolean peloMenosUmParametroInformado = false;

        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();

        FiltroArquivoTextoAtualizacaoCadastral filtroArquivoTextoAtualizacaoCadastral = new FiltroArquivoTextoAtualizacaoCadastral(FiltroArquivoTextoAtualizacaoCadastral.ID);
        
        //Objetos que serão retornados pelo Hibernate
        filtroArquivoTextoAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade("situacaoTransmissaoLeitura");
       
        if (descricaoArquivo != null && !descricaoArquivo.trim().equalsIgnoreCase("")) {
			filtroArquivoTextoAtualizacaoCadastral.adicionarParametro(
					new ComparacaoTexto(FiltroArquivoTextoAtualizacaoCadastral.DESCRICAO, descricaoArquivo));
            peloMenosUmParametroInformado = true;
        }
        if (idLeiturista != null && !idLeiturista.trim().equalsIgnoreCase("")) {
            filtroArquivoTextoAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
                    FiltroArquivoTextoAtualizacaoCadastral.LEITURISTA_ID, new Integer(idLeiturista)));
            peloMenosUmParametroInformado = true;
        }
        if (idSituacaoTransmissao != null && !idSituacaoTransmissao.trim().equalsIgnoreCase("")
                && !idSituacaoTransmissao.trim().equalsIgnoreCase(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
            filtroArquivoTextoAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
                    FiltroArquivoTextoAtualizacaoCadastral.SITUACAO_TRANSMISSAO_LEITURA_ID, new Integer(idSituacaoTransmissao)));
            peloMenosUmParametroInformado = true;
        }
           
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }
        
        Collection arquivosTextoAtualizacaoCadastral = fachada.pesquisar(filtroArquivoTextoAtualizacaoCadastral,
                ArquivoTextoAtualizacaoCadastral.class.getName());

        if (arquivosTextoAtualizacaoCadastral != null && !arquivosTextoAtualizacaoCadastral.isEmpty()) {
//        	 Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroArquivoTextoAtualizacaoCadastral, ArquivoTextoAtualizacaoCadastral.class.getName());
			arquivosTextoAtualizacaoCadastral = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			sessao.setAttribute("arquivosTextoAtualizacaoCadastral", arquivosTextoAtualizacaoCadastral);
        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Arquivo");
        }
        
        return retorno;
    }

}
