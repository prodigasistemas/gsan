package gcom.gui.util.tabelaauxiliar;

import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

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
public class ExibirInserirTabelaAuxiliarAction extends GcomAction {
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

        //Prepara o retorno da Ação
        ActionForward retorno = actionMapping
                .findForward("inserirTabelaAuxiliar");


        //Declaração de objetos e tipos primitivos
        String descricao = "Descrição";
        int tamanhoMaximoCampo = 40;

        //Cria a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
        
        SistemaParametro sistemaParametro = (SistemaParametro) fachada
		.pesquisarParametrosDoSistema();

        sessao.setAttribute("nomeSistema", sistemaParametro.getNomeEmpresa());
        
        //tempo da sessão
        sessao.setMaxInactiveInterval(1000);
        
        String tela = httpServletRequest.getParameter("tela");
        
		DadosTelaTabelaAuxiliar dados = DadosTelaTabelaAuxiliar
				.obterDadosTelaTabelaAuxiliar(tela);
	

		if (dados.getTabelaAuxiliar() instanceof RegiaoDesenvolvimento) {
			tamanhoMaximoCampo = 20;
			
		}
        
        //Adiciona os objetos na sessão
        sessao.setAttribute("tabelaAuxiliar", dados.getTabelaAuxiliar());
        sessao.setAttribute("funcionalidadeTabelaAuxiliarInserir",dados.getFuncionalidadeTabelaAuxInserir());
        sessao.setAttribute("titulo", dados.getTitulo());

        //Adiciona o objeto no request
        httpServletRequest.setAttribute("descricao", descricao);
        httpServletRequest.setAttribute("tamanhoMaximoCampo", new Integer(tamanhoMaximoCampo));
        httpServletRequest.setAttribute("tela",tela);

        return retorno;
    }

}
