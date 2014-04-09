package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.MovimentoHidrometroHelper;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ConfirmarMovimentarHidrometroAction extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém o action form
        ConfirmarMovimentarHidrometroActionForm confirmarMovimentarHidrometroActionForm = (ConfirmarMovimentarHidrometroActionForm) actionForm;

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        Fachada fachada = Fachada.getInstancia();

        //Filtro para obter o local de armazenagem ativo de id informado
        FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

        filtroHidrometroLocalArmazenagem
                .adicionarParametro(new ParametroSimples(
                        FiltroHidrometroLocalArmazenagem.ID, new Integer(
                                confirmarMovimentarHidrometroActionForm
                                        .getIdLocalArmazenagemDestino()),
                        ParametroSimples.CONECTOR_AND));
        filtroHidrometroLocalArmazenagem
                .adicionarParametro(new ParametroSimples(
                        FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

        //Pesquisa de acordo com os parâmetros informados no filtro
        Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(
                filtroHidrometroLocalArmazenagem,
                HidrometroLocalArmazenagem.class.getName());

        //Verifica se a pesquisa retornou algum objeto para a coleção
        if (colecaoHidrometroLocalArmazenagem == null
                || colecaoHidrometroLocalArmazenagem.isEmpty()) {
            throw new ActionServletException(
                    "atencao.pesquisa.hidrometro_local_armazenagem.inexistente");
        }

        // Validando data de Movimentação
        Integer dia = new Integer(confirmarMovimentarHidrometroActionForm.getDataMovimentacao().substring(0, 2));
        Integer mes = new Integer(confirmarMovimentarHidrometroActionForm.getDataMovimentacao().substring(3, 5));
        Integer ano = new Integer(confirmarMovimentarHidrometroActionForm.getDataMovimentacao().substring(6, 10));
        
        Calendar dataMovimentacao = new GregorianCalendar();
        dataMovimentacao.set(Calendar.YEAR, ano);
        dataMovimentacao.set(Calendar.MONTH, mes);
        dataMovimentacao.set(Calendar.DATE, dia);

        Calendar dataLimite = new GregorianCalendar();
        dataLimite.add(Calendar.DATE, -720);
        
		// caso a data de movimentação seja menor que 720 dias antes da data atual
		if (dataMovimentacao.before(dataLimite)) {
			throw new ActionServletException("atencao.data.movimentacao.nao.inferior.data.limite");
		}

		String numeroHidrometrosSelecionados = (String) sessao
                .getAttribute("numeroHidrometrosSelecionados");

        Collection colecaoHidrometroSelecionado = (Collection) sessao
                .getAttribute("colecaoHidrometroSelecionado");

        //Verifica se a colecao vai para batch ou não.
        if ( !colecaoHidrometroSelecionado.equals("") && colecaoHidrometroSelecionado.size() < 500 ) {

        	fachada.inserirAtualizarMovimentacaoHidrometroIds(
	                colecaoHidrometroSelecionado,
	                confirmarMovimentarHidrometroActionForm.getDataMovimentacao(),
	                confirmarMovimentarHidrometroActionForm.getHoraMovimentacao(),
	                confirmarMovimentarHidrometroActionForm
	                        .getIdLocalArmazenagemDestino(),
	                confirmarMovimentarHidrometroActionForm
	                        .getIdMotivoMovimentacao(),
	                confirmarMovimentarHidrometroActionForm.getParecer(), usuario);
        } else {
        	
        	MovimentoHidrometroHelper helper = new MovimentoHidrometroHelper();
        	helper.setColecaoHidrometroSelecionado( colecaoHidrometroSelecionado );
            helper.setDataMovimentacao( confirmarMovimentarHidrometroActionForm.getDataMovimentacao() );
            helper.setHoraMovimentacao( confirmarMovimentarHidrometroActionForm.getHoraMovimentacao() );
            helper.setIdLocalArmazenagemDestino( confirmarMovimentarHidrometroActionForm
	                        .getIdLocalArmazenagemDestino() );
            helper.setIdMotivoMovimentacao(confirmarMovimentarHidrometroActionForm.getIdMotivoMovimentacao() );
            helper.setUsuario(usuario);
            
        	fachada.inserirAtualizarMovimentacaoHidrometroIdsBatch( helper );
        }
        
        if ( !colecaoHidrometroSelecionado.equals("") && colecaoHidrometroSelecionado.size() < 500 ) {
        	//Método utilizado para montar a página de sucesso
            montarPaginaSucesso(httpServletRequest,
            		numeroHidrometrosSelecionados + " Hidrômetro(s) " 
                            + " movimentado(s) com sucesso.",
                    "Movimentar outro(s) Hidrômetro(s)",
                    "exibirFiltrarHidrometroAction.do?menu=sim&tela=movimentarHidrometro");
            
        } else {
        	//Método utilizado para montar a página de sucesso batch
        	montarPaginaSucesso(httpServletRequest,
        		"Movimentação de Hidrômetros enviado para Processamento",
        		"Voltar",
                "exibirFiltrarHidrometroAction.do?menu=sim&tela=movimentarHidrometro");
        }
        
        //Remove objetos da sessão
        sessao.removeAttribute("colecaoHidrometroSelecionado");
        sessao.removeAttribute("codigoDescricaoLocalArmazenagemAtual");
        sessao.removeAttribute("numeroHidrometrosSelecionados");
        sessao.removeAttribute("ManutencaoRegistroActionForm");
        sessao.removeAttribute("ConfirmarMovimentarHidrometroActionForm");

        return retorno;
    }
}
