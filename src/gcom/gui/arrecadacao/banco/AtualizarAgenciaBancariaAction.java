package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarAgenciaBancariaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarAgenciaBancariaActionForm atualizarAgenciaBancariaActionForm = (AtualizarAgenciaBancariaActionForm) actionForm;

		Agencia agencia = (Agencia) sessao.getAttribute("agenciaAtualizar");

		agencia.setCodigoAgencia(atualizarAgenciaBancariaActionForm
				.getCodigo());
		agencia.setNomeAgencia(atualizarAgenciaBancariaActionForm.getNome());

		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");

		agencia.setNumeroTelefone(atualizarAgenciaBancariaActionForm
				.getTelefone());
		agencia.setNumeroRamal(atualizarAgenciaBancariaActionForm.getRamal());
		
		agencia.setNumeroFax(atualizarAgenciaBancariaActionForm.getFax());
		
		agencia.setEmail(atualizarAgenciaBancariaActionForm.getEmail());



	       String codigo = atualizarAgenciaBancariaActionForm
           .getCodigo();
	       
	        String nome = atualizarAgenciaBancariaActionForm
            .getNome();
		
        if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
        	Agencia agenciaEndereco = (Agencia) Util
            .retonarObjetoDeColecao(colecaoEnderecos);
        	
           	agencia.setLogradouroCep(agenciaEndereco.getLogradouroCep());
        	agencia.setLogradouroBairro(agenciaEndereco.getLogradouroBairro());
        	agencia.setEnderecoReferencia(agenciaEndereco.getEnderecoReferencia());
        	agencia.setComplementoEndereco(agenciaEndereco.getComplementoEndereco());
        	agencia.setNumeroImovel(agenciaEndereco.getNumeroImovel());
        	
        } else {
        	throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null, "Endereço");
        }
        
        agencia.setCodigoAgencia(codigo);
        agencia.setNomeAgencia(nome);

		
		
		// AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = null;

		if (atualizarAgenciaBancariaActionForm.getBancoID() != null) {

			Integer idBanco = new Integer(atualizarAgenciaBancariaActionForm
					.getBancoID());

			if (idBanco.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				agencia.setBanco(null);
			} else {
				FiltroBanco filtroBanco = new FiltroBanco();
				filtroBanco.adicionarParametro(new ParametroSimples(
						FiltroBanco.ID, atualizarAgenciaBancariaActionForm
								.getBancoID().toString()));
				Collection colecaoBanco = (Collection) fachada.pesquisar(
						filtroBanco, Banco.class.getName());

				// setando
				agencia.setBanco((Banco) colecaoBanco.iterator().next());
			}
		}

		fachada.atualizarAgenciaBancaria(agencia);

		montarPaginaSucesso(httpServletRequest, "Agência Bancária de código "
				+ agencia.getCodigoAgencia().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Agência Bancaria",
				"exibirFiltrarAgenciaBancariaAction.do?menu=sim");
		return retorno;
	}
}
