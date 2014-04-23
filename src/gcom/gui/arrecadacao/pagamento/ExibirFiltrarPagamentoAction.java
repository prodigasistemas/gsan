package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Cria um filtro que será usado na pesquisa de pagamentos
 * 
 * [UC0255] Filtrar Pagamentos
 *
 * @author Tiago Moreno, Roberta Costa ,Vivianne Sousa,Rafael Santos
 * @date 31/01/2006, 05/05/2003,07/10/2006
 */
public class ExibirFiltrarPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarPagamento");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Pega o formulário
		ConsultarPagamentoActionForm consultarPagamentoActionForm = (ConsultarPagamentoActionForm) actionForm;

		String tela = httpServletRequest.getParameter("tela");
		
		// Verifica se o imóvel é válido
		String idImovel = consultarPagamentoActionForm.getIdImovel();
		if (idImovel != null && !idImovel.equals("")) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class
					.getName());

			if (imoveis != null && !imoveis.isEmpty()) {
				Imovel imovel = (Imovel) ((List) imoveis).get(0);
				httpServletRequest.setAttribute("imovel", imovel);
				consultarPagamentoActionForm.setInscricao(imovel
						.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("matriculaInexistente", "true");
				consultarPagamentoActionForm.setIdImovel("");
				consultarPagamentoActionForm
						.setInscricao("MATRÍCULA INEXISTENTE");
			}
		} else if (idImovel != null && idImovel.equals("")
				&& consultarPagamentoActionForm.getInscricao()
						.equalsIgnoreCase("MATRÍCULA INEXISTENTE")) {
			httpServletRequest.setAttribute("matriculaInexistente", "true");
		}
		
		// Verifica se o CLIENTE é válido
		String idCliente = consultarPagamentoActionForm.getIdCliente();
		
		if (idCliente != null && !idCliente.equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection clientes = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clientes != null && !clientes.isEmpty()) {
				Cliente cliente = (Cliente) ((List) clientes).get(0);
				consultarPagamentoActionForm.setNomeCliente(cliente.getNome());
				sessao.setAttribute("cliente", cliente);
			} else {
				httpServletRequest.setAttribute("clienteInexistente", "true");
				consultarPagamentoActionForm.setIdCliente("");
				consultarPagamentoActionForm
						.setNomeCliente("CÓDIGO DE CLIENTE INEXISTENTE");
			}
		} else if (idCliente != null && idCliente.equals("")
				&& consultarPagamentoActionForm.getNomeCliente()
						.equalsIgnoreCase("CÓDIGO DE CLIENTE INEXISTENTE")) {
			httpServletRequest.setAttribute("clienteInexistente", "true");
		}

		// Verifica se a LOCALIDADE é válida
		String idLocalidadeInicial = consultarPagamentoActionForm.getLocalidadeInicial();
		if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade
						.iterator().next();
				
				consultarPagamentoActionForm.setLocalidadeInicial(idLocalidadeInicial);
				consultarPagamentoActionForm
						.setDescricaoLocalidadeInicial(localidade.getDescricao());

			} else {
				consultarPagamentoActionForm.setLocalidadeInicial("");
				consultarPagamentoActionForm
						.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInexistente", true);
			}
		}
		
		String idLocalidadeFinal = consultarPagamentoActionForm.getLocalidadeFinal();
		if (idLocalidadeFinal != null && !idLocalidadeFinal.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade
						.iterator().next();
				
				consultarPagamentoActionForm.setLocalidadeFinal(idLocalidadeFinal);
				consultarPagamentoActionForm
						.setDescricaoLocalidadeFinal(localidade.getDescricao());

			} else {
				consultarPagamentoActionForm.setLocalidadeFinal("");
				consultarPagamentoActionForm
						.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInexistente", true);
			}
		}
        
        String idAvisoBancario = consultarPagamentoActionForm.getIdAvisoBancario();        
        
        if (idAvisoBancario != null && !idAvisoBancario.trim().equals("")) {
            FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
            filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
            filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
            
            Collection colecaoAvisoBancario = fachada.pesquisar(filtroAvisoBancario, AvisoBancario.class.getName());
            
            if (colecaoAvisoBancario != null && !colecaoAvisoBancario.isEmpty()) {
                AvisoBancario avisoBancario = (AvisoBancario) Util.retonarObjetoDeColecao(colecaoAvisoBancario);
                
                consultarPagamentoActionForm.setCodigoAgenteArrecadador( avisoBancario.getArrecadador().getCodigoAgente().toString());
                consultarPagamentoActionForm.setDataLancamentoAviso( Util.formatarData(avisoBancario.getDataLancamento()) );
                consultarPagamentoActionForm.setNumeroSequencialAviso( avisoBancario.getNumeroSequencial().toString() );
                
            } else {
                
                consultarPagamentoActionForm.setCodigoAgenteArrecadador( "");
                consultarPagamentoActionForm.setDataLancamentoAviso( "AVISO INEXISTENTE" );
                consultarPagamentoActionForm.setNumeroSequencialAviso( "" );
                consultarPagamentoActionForm.setIdAvisoBancario( "" );

                httpServletRequest.setAttribute("avisoInexistente", true);
            }
        }

		// Pegando valores pra o combo Tipo de Relacao do Cliente
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo(
				FiltroClienteRelacaoTipo.DESCRICAO);
		filtroClienteRelacaoTipo
			.adicionarParametro(new ParametroSimples(
					FiltroClienteRelacaoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoClienteRelacaoTipo = fachada
			.pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());
		sessao.setAttribute("colecaoClienteRelacaoTipo", colecaoClienteRelacaoTipo);
		
		// Pegando valores pra o combo Tipo de Situacao de Pagamentos
		FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao(
				FiltroPagamentoSituacao.DESCRICAO);
		Collection colecaoPagamentoSituacao = fachada
			.pesquisar(filtroPagamentoSituacao, PagamentoSituacao.class.getName());
		sessao.setAttribute("colecaoPagamentoSituacao", colecaoPagamentoSituacao);
		
		// Pegando valores pra o combo FORMA DE ARRECADACAO
		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma(
				FiltroArrecadacaoForma.DESCRICAO);
		Collection colecaoArrecadacaoForma = fachada
			.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
		sessao.setAttribute("colecaoArrecadacaoForma", colecaoArrecadacaoForma);

		// Pegando valores pra o combo Tipo de Débito		
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo(
				FiltroDebitoTipo.DESCRICAO);
		filtroDebitoTipo.setConsultaSemLimites(true);
		Collection colecaoDebitoTipo = fachada
			.pesquisar(filtroDebitoTipo, DebitoTipo.class.getCanonicalName());
		sessao.setAttribute("colecaoDebitoTipo", colecaoDebitoTipo);
		
		//Pegando valores pra o combo Tipo de Documento
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo(
				FiltroDocumentoTipo.DESCRICAO);
		Collection colecaoDocumentoTipo = fachada
			.pesquisar(filtroDocumentoTipo, DocumentoTipo.class.getName());
		sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
		
		if (httpServletRequest.getParameter("tela") != null) {		
			sessao.setAttribute("tela",tela);
			
			String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
				sessao.setAttribute("indicadorAtualizar","1");
			}
		}
		//se voltou da tela de Atualizar Pagamento
		if (sessao.getAttribute("voltar") != null &&
			sessao.getAttribute("voltar").equals("filtrar")){
			sessao.setAttribute("indicadorAtualizar","1");
		}
		
		//seta o valor para  a opção de pagamento
		if(consultarPagamentoActionForm.getOpcaoPagamento() == null){
			consultarPagamentoActionForm.setOpcaoPagamento("atual");
		}
		
		return retorno;
	}
}
