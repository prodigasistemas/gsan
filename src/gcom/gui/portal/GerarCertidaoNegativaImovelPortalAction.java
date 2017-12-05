package gcom.gui.portal;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.gui.atendimentopublico.GerarCertidaoNegativaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarCertidaoNegativaImovelPortalAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("emitirCertidaoNegativa");

		GerarCertidaoNegativaActionForm form = (GerarCertidaoNegativaActionForm) actionForm;
		ActionErrors errors = form.validate(mapping, request);

		Imovel imovel = null;
		if (form.getIdImovel() != null && !form.getIdImovel().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			if ("".equals(form.getIdImovel())) {
				errors.add("idImovel", new ActionError("atencao.pesquisa_inexistente", "Matrícula"));
			} else {
				imovel = new Imovel();
				try {
					Integer matriculaExistente = getFachada().verificarExistenciaImovel(Integer.valueOf(form.getIdImovel()));
					if (matriculaExistente != 1) {
						errors.add("idImovel", new ActionError("atencao.imovel.inexistente"));
					}
					imovel.setId(Integer.valueOf(form.getIdImovel()));

					Collection<ClienteImovel> colecaoClienteImovel = pesquisarClienteImovel(imovel);
					if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
						ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);

						if (clienteImovel.getCliente().getClienteTipo().getEsferaPoder().getIndicadorPermiteCertidaoNegativaDebitosParaImovel().equals(ConstantesSistema.NAO)) {
							errors.add("idImovel", new ActionError("atencao.esfera_poder_responsavel_nao_permite_geracao_certidao_negativa"));
						}
					}
				} catch (NumberFormatException nfe) {
					errors.add("idImovel", new ActionError("atencao.imovel.inexistente"));
				}
			}
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("exibir");
		}

		String tipoRelatorio = request.getParameter("tipoRelatorio");
		Usuario usuarioLogado = this.getUsuarioLogado(request);

		TarefaRelatorio relatorio = new RelatorioCertidaoNegativa(usuarioLogado);

		relatorio.addParametro("imovel", imovel);
		relatorio.addParametro("usuarioLogado", usuarioLogado);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, request, response, mapping);

		return retorno;
	}

	@SuppressWarnings("unchecked")
	private Collection<ClienteImovel> pesquisarClienteImovel(Imovel imovel) {
		Filtro filtro = new FiltroClienteImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.RESPONSAVEL));
		filtro.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo.esferaPoder");

		return getFachada().pesquisar(filtro, ClienteImovel.class.getName());
	}
}
