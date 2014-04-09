package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.FiltroClienteImovelFimRelacaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição do fim da relação entre o cliente e o
 * imóvel em manter imóvel
 * 
 * @author Rafael Santos
 * @since 17/04/2006
 */
public class ExibirManterImovelFimRelacaoClienteImovelAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Prepara o retorno da Ação
        ActionForward retorno = actionMapping
                .findForward("fimRelacaoManterImovelClienteImovel");

        FimRelacaoClienteImovelActionForm fimRelacaoClienteImovelActionForm = (FimRelacaoClienteImovelActionForm) actionForm;

        //Cria a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        //Obtém a fachada
        Fachada fachada = Fachada.getInstancia();

        //Filtro FiltroClienteImovelFimRelacaoMotivo
        FiltroClienteImovelFimRelacaoMotivo filtroClienteImovelFimRelacaoMotivo = new FiltroClienteImovelFimRelacaoMotivo();

        filtroClienteImovelFimRelacaoMotivo
                .adicionarParametro(new ParametroSimples(
                        FiltroClienteImovelFimRelacaoMotivo.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection clienteImoveisFimRelacaoMotivo = fachada.pesquisar(
                filtroClienteImovelFimRelacaoMotivo,
                ClienteImovelFimRelacaoMotivo.class.getName());

        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
        //joga em dataInicial a parte da data
        String dataFinal = dataFormato.format(new Date());

        fimRelacaoClienteImovelActionForm.setDataTerminoRelacao(dataFinal);

        sessao.setAttribute("clienteImoveisFimRelacaoMotivo",
                clienteImoveisFimRelacaoMotivo);

        return retorno;
    }
}
