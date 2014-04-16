package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
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

public class RetornarContaBancariaPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Inicializacoes de variaveis
		ActionForward retorno = actionMapping
				.findForward("retornarContaBancariaPesquisar");
		HttpSession sessao = httpServletRequest.getSession(false);
		//Fachada fachada = Fachada.getInstancia();
		
		boolean peloMenosUmParametroInformado = false;
		
		ContaBancariaPesquisarActionForm form = (ContaBancariaPesquisarActionForm) actionForm;
		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
		//Inicializacoes de variaveis
		
		//Carregamento de Entidades para pesquisa com ID de Banco
		filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");
		String idBanco = form.getIdBanco().trim();
		if(idBanco != null && new Integer(idBanco).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
			filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.AGENCIA_BANCO_ID, idBanco));
			peloMenosUmParametroInformado = true;
		}
		//Adicionando id de Agencia para a pesquisa
		String idAgencia = form.getIdAgencia().trim();
		if(idAgencia != null && !idAgencia.equals("") 
				&& (new Integer(idAgencia).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.AGENCIA_ID, idAgencia));
			peloMenosUmParametroInformado = true;
		}
		//Adicionando numero de Conta para a pesquisa
		String numeroConta = form.getNumeroConta().trim();
		if(numeroConta != null && !numeroConta.equals("")){
			filtroContaBancaria.adicionarParametro(new ComparacaoTexto(FiltroContaBancaria.NUMERO_CONTA, numeroConta));
			peloMenosUmParametroInformado = true;
		}
		
		//Testa se pelo menos um parametro foi informado
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		//Pesquisa e retorno da collection de Contas Bancarias
		Collection<ContaBancaria> collectionContaBancaria = null;//
		
		//Alterado para retornar a coleção com o controle novo de paginação - Fernanda Paiva - 10/08/2006
		//Collection<ContaBancaria> collectionContaBancaria =fachada.pesquisar(filtroContaBancaria, ContaBancaria.class.getName());
		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroContaBancaria, ContaBancaria.class.getName());
		collectionContaBancaria = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Validacoes 
		if (collectionContaBancaria == null || collectionContaBancaria.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "conta bancaria");
		} else if (collectionContaBancaria.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a coleção na sessão
			sessao.setAttribute("collectionContaBancaria", collectionContaBancaria);

		}				
		
		return retorno;
	}

}
