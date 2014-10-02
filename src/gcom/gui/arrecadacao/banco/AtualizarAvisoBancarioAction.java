package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.aviso.AvisoAcerto;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action de exibir atulizar o aviso bancario
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class AtualizarAvisoBancarioAction  extends GcomAction {

    /**
     * Método responsavel por responder a requisicao
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
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        AvisoBancarioActionForm form = (AvisoBancarioActionForm) actionForm;

        HttpSession sessao = request.getSession(false);

        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        // ------------ REGISTRAR TRANSAÇÃO ----------------
        
//        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_AVISO_BANCARIO_ATUALIZAR,
//				new UsuarioAcaoUsuarioHelper(usuarioLogado,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//        
//        Operacao operacao = new Operacao();
//        operacao.setId(Operacao.OPERACAO_SUBCATEGORIA_ATUALIZAR);
//
//        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------
        

    	AvisoBancario avisoBancario = (AvisoBancario) sessao.getAttribute("avisoBancario");
    	
    	avisoBancario.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	
    	Map<String, String[]> requestMap = request.getParameterMap();

    	//avisoBancario.setUltimaAlteracao(new Date(System.currentTimeMillis()));
    	FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
    	filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia");
    	filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");

    	Date dataLancamento = null;
    	if(form.getDataLancamento() != null && !form.getDataLancamento().equals("")){
    		dataLancamento = Util.converteStringParaDate(form.getDataLancamento());
    		avisoBancario.setDataLancamento(dataLancamento);
    	}else{
    		throw new ActionServletException("atencao.avisoBancario.data_lancamento_invalida");
    	}
    	
    	if (form.getIdContaBancaria()!= null && !"".equals(form.getIdContaBancaria().trim())) {
        	filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.ID,form.getIdContaBancaria()));
        	Collection coll = Fachada.getInstancia().pesquisar(filtroContaBancaria, ContaBancaria.class.getSimpleName());
    		avisoBancario.setContaBancaria((ContaBancaria)coll.iterator().next());
    	}else{
    		
    		if (requestMap.get("idContaRequest") != null) 
    		{
    			String idConta = (requestMap.get("idContaRequest"))[0];
            	filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.ID,idConta));
            	Collection coll = Fachada.getInstancia().pesquisar(filtroContaBancaria, ContaBancaria.class.getSimpleName());
        		avisoBancario.setContaBancaria((ContaBancaria)coll.iterator().next());
    		}
    	}
    	
		// ------------------------------
		// -- ArrecadacaoForma
		// ------------------------------
		if (form.getIdFormaArrecadacao() != null && !form.getIdFormaArrecadacao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(new Integer(form.getIdFormaArrecadacao()));
			avisoBancario.setArrecadacaoForma(arrecadacaoForma);
		}
    	
    	if (form.getNumeroDocumento()!= null && !"".equals(form.getNumeroDocumento().trim())) 
    		avisoBancario.setNumeroDocumento(Integer.parseInt(form.getNumeroDocumento()));
    	if (form.getDataRealizacao()!= null && !"".equals(form.getDataRealizacao().trim())) {
    		
			Date data = Util.converteStringParaDate(form.getDataRealizacao());
			
			if (data == null) {
				throw new ActionServletException("atencao.avisoBancario.data_realizacao_invalida");
			}
			if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException("atencao.avisoBancario.data_realizacao_menor_que_hoje",null,Util.formatarData(new Date(System.currentTimeMillis())));
			}
			if (data.getTime() < dataLancamento.getTime()) {
				throw new ActionServletException("atencao.avisoBancario.data_realizacao_menor_que_data_lancamento", Util.formatarData(data), Util.formatarData(dataLancamento));
			}
    		avisoBancario.setDataRealizada(Util.converteStringParaDate(form.getDataRealizacao()));
    	}	
    	if (form.getValorArrecadacao()!= null && !"".equals(form.getValorArrecadacao().trim()))
    		avisoBancario.setValorArrecadacaoInformado(Util.formatarMoedaRealparaBigDecimal(form.getValorArrecadacao().trim()));
    	if (form.getValorDevolucao()!= null && !"".equals(form.getValorDevolucao().trim()))
    		avisoBancario.setValorDevolucaoInformado(Util.formatarMoedaRealparaBigDecimal(form.getValorDevolucao().trim()));
    	
    	// pegando os valores que foram alterados
    	Collection collAvisoDeducoes = (Collection) sessao.getAttribute("avisoDeducoes");
    	BigDecimal somatorioValorDeducao = new BigDecimal("0.00");
		
    	if (collAvisoDeducoes != null && !collAvisoDeducoes.isEmpty()) {
    		Iterator it = collAvisoDeducoes.iterator();
    		int i = 0;
    		while (it.hasNext()) {
    			AvisoDeducoes avisoDeducoes = (AvisoDeducoes) it.next();
    			if (request.getParameter("posicaoAvisoDeducao_" + i) != null) {
    				avisoDeducoes.setValorDeducao(Util.formatarMoedaRealparaBigDecimal(request.getParameter("posicaoAvisoDeducao_" + i).toString().trim()));
    				somatorioValorDeducao = somatorioValorDeducao.add((Util.formatarMoedaRealparaBigDecimal(request.getParameter("posicaoAvisoDeducao_" + i).toString().trim())));
    			}
    			i++;
    		}
    	}
    	
    	Collection collAvisoDeducoesRemover = (Collection) sessao.getAttribute("avisoDeducoesRemover");
    	/*if (collAvisoDeducoesRemover != null) {
    		Iterator it = collAvisoDeducoesRemover.iterator(); 
        	while(it.hasNext()) {
        		
        	}
    	}*/

//  	 pegando os valores que foram alterados
    	Collection collAvisoAcerto = (Collection) sessao.getAttribute("avisoAcerto");
    	if (collAvisoAcerto != null && !collAvisoAcerto.isEmpty()) {
    		Iterator it = collAvisoAcerto.iterator();
    		int i = 0;
    		while (it.hasNext()) {
    			AvisoAcerto avisoAcerto = (AvisoAcerto) it.next();
    			if (request.getParameter("posicaoAvisoAcerto_" + i) != null) {
    				avisoAcerto.setValorAcerto(Util.formatarMoedaRealparaBigDecimal(request.getParameter("posicaoAvisoAcerto_" + i).toString().trim()));
    			}
    			i++;
    		}
    	}
    	
    	Collection collAvisoAcertoRemover = (Collection) sessao.getAttribute("avisoAcertoRemover");
    	/*if (collAvisoAcertoRemover != null) {
    		Iterator it = collAvisoAcertoRemover.iterator(); 
        	while(it.hasNext()) {
        	}
    	}*/
    	
    	if (form.getValorArrecadacao() == null || form.getValorArrecadacao().equals("")){
    		form.setValorArrecadacao("0,00");
    	}
    	
    	if (form.getValorDevolucao() == null || form.getValorDevolucao().equals("")){
    		form.setValorDevolucao("0,00");
    	}
    	
    	if (form.getValorDeducao() == null || form.getValorDeducao().equals("")){
    		form.setValorDeducao("0,00");
    	}
    	
    	BigDecimal valorRealizado = Util.formatarMoedaRealparaBigDecimal(form.getValorArrecadacao()).subtract(Util.formatarMoedaRealparaBigDecimal(form.getValorDevolucao()));
    	valorRealizado =  valorRealizado.subtract(somatorioValorDeducao); 	
    	
    	avisoBancario.setValorRealizado(valorRealizado);

    	
    	Fachada.getInstancia().atualizarAvisoBancario(avisoBancario, collAvisoDeducoes, collAvisoDeducoesRemover, collAvisoAcerto, collAvisoAcertoRemover, usuarioLogado);

    	sessao.removeAttribute("avisoDeducoes");
    	sessao.removeAttribute("avisoDeducoesRemover");
        sessao.removeAttribute("avisoAcerto");
    	sessao.removeAttribute("avisoAcertoRemover");

        request.setAttribute("caminhoFuncionalidade","exibirFiltrarAvisoBancarioAction.do?menu=sim");
		request.setAttribute("labelPaginaSucesso"," Realizar outra Manutenção de Aviso Bancário");
		request.setAttribute("mensagemPaginaSucesso","Aviso Bancário de código " + avisoBancario.getId() 
				+ " com Data de Lançamento " + Util.formatarData(avisoBancario.getDataLancamento())  
				+ " e seqüencial " + avisoBancario.getNumeroSequencial()
				+ " do arrecadador " + avisoBancario.getArrecadador().getCliente().getNome() 
				+ " atualizado com sucesso. ");
		
        return retorno;
    }
}
