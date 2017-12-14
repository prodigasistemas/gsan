package gcom.gui.portal;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.gui.atendimentopublico.GerarCertidaoNegativaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativa;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarCertidaoNegativaImovelPortalAction extends ExibidorProcessamentoTarefaRelatorio {

	private GerarCertidaoNegativaActionForm form;
	private ActionErrors errors;

	private Imovel imovel;
	private SistemaParametro sistemaParametro;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.form = (GerarCertidaoNegativaActionForm) actionForm;
		this.errors = new ActionErrors();
		this.sistemaParametro = getFachada().pesquisarParametrosDoSistema();

		if (form.getIdImovel() != null) {
			if (form.getIdImovel().equals("")) {
				adicionarErro(new ActionError("errors.portal.obrigatorio", "a Matrícula do Imóvel"));
			} else {
				imovel = new Imovel();
				try {
					if (getFachada().verificarExistenciaImovel(Integer.valueOf(form.getIdImovel())) != 1) {
						adicionarErro(new ActionError("errors.portal.invalida", "Matrícula do Imóvel"));
					} else {
						imovel.setId(Integer.valueOf(form.getIdImovel()));
						verificarEsferaPoder();
						verificarDebitosImovel();
					}
				} catch (NumberFormatException nfe) {
					adicionarErro(new ActionError("errors.portal.invalida", "Matrícula do Imóvel"));
				}
			}
		}

		form.setIdImovel(null);

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("erro-certidao-imovel", true);
			return mapping.findForward("exibir");
		} else {
			request.removeAttribute("erro-certidao-imovel");
		}

		return processar(mapping, request, response);
	}

	private void verificarDebitosImovel() {
		ObterDebitoImovelOuClienteHelper debitos = getFachada().obterDebitoImovelOuCliente(1, imovel.getId().toString(), null, null, 
				"190001", "299901", Util.criarData(1, 1, 1900), getDataFimVencimento(), 1, 1, 1, 1, 1, 1, 1, true);

		if (indicadorEfeitoPositivo()) {
			if (debitos != null) {
				if (possuiContas(debitos) || possuiGuias(debitos) || possuiDebitos(debitos)) {
					adicionarErro(new ActionError("errors.portal.possui_debitos", "Imóvel"));
				}
			}
		}
	}

	private boolean indicadorEfeitoPositivo() {
		return sistemaParametro.getIndicadorCertidaoNegativaEfeitoPositivo() == ConstantesSistema.NAO;
	}

	private boolean possuiDebitos(ObterDebitoImovelOuClienteHelper debitos) {
		return indicadorDebitoACobrarValido() && debitos.getColecaoDebitoACobrar() != null && !debitos.getColecaoDebitoACobrar().isEmpty();
	}

	private boolean indicadorDebitoACobrarValido() {
		return sistemaParametro.getIndicadorDebitoACobrarValidoCertidaoNegativa().equals(ConstantesSistema.SIM);
	}

	private boolean possuiGuias(ObterDebitoImovelOuClienteHelper debitos) {
		return debitos.getColecaoGuiasPagamentoValores() != null && !debitos.getColecaoGuiasPagamentoValores().isEmpty();
	}

	private boolean possuiContas(ObterDebitoImovelOuClienteHelper debitos) {
		return debitos.getColecaoContasValores() != null && !debitos.getColecaoContasValores().isEmpty();
	}

	private Date getDataFimVencimento() {
		Calendar dataFimVencimentoDebito = new GregorianCalendar();
		dataFimVencimentoDebito.add(Calendar.DATE, -sistemaParametro.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());
		return dataFimVencimentoDebito.getTime();
	}

	private ActionForward processar(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) {
		TarefaRelatorio dados = new RelatorioCertidaoNegativa(getUsuarioLogado(request));
		dados.addParametro("imovel", imovel);
		dados.addParametro("usuarioLogado", getUsuarioLogado(request));
		dados.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);

		return processarExibicaoRelatorio(dados, String.valueOf(TarefaRelatorio.TIPO_PDF), request, response, mapping);
	}

	private void verificarEsferaPoder() {
		Collection<ClienteImovel> colecaoClienteImovel = pesquisarClienteImovel();
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
			ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);

			if (clienteImovel.getCliente().getClienteTipo().getEsferaPoder().getIndicadorPermiteCertidaoNegativaDebitosParaImovel().equals(ConstantesSistema.NAO)) {
				adicionarErro(new ActionError("atencao.esfera_poder_responsavel_nao_permite_geracao_certidao_negativa"));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Collection<ClienteImovel> pesquisarClienteImovel() {
		Filtro filtro = new FiltroClienteImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.RESPONSAVEL));
		filtro.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo.esferaPoder");

		return getFachada().pesquisar(filtro, ClienteImovel.class.getName());
	}

	private void adicionarErro(ActionError erro) {
		if (errors.isEmpty())
			errors.add("erro-certidao-imovel", erro);
	}
}
