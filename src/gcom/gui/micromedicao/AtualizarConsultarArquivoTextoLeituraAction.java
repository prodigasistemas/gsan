package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarConsultarArquivoTextoLeituraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarConsultarArquivoTextoLeituraActionForm atualizarConsultarArquivoTextoLeituraActionForm = (AtualizarConsultarArquivoTextoLeituraActionForm) actionForm;

		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = new ArquivoTextoRoteiroEmpresa();

		arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) sessao
				.getAttribute("atualizarConsultarArquivoTextoLeituraAction");

		// String mesAno = atualizarConsultarArquivoTextoLeituraActionForm
		// .getMesAno();
		
		arquivoTextoRoteiroEmpresa.setNomeArquivo(atualizarConsultarArquivoTextoLeituraActionForm.getNumeroArquivo());
		
		arquivoTextoRoteiroEmpresa.setQuantidadeImovel(new Integer(atualizarConsultarArquivoTextoLeituraActionForm.getQuantidadeImovel()));
		
		//arquivoTextoRoteiroEmpresa.getLocalidade().setLocalidade(atualizarConsultarArquivoTextoLeituraActionForm.getLocalidadeID());

		arquivoTextoRoteiroEmpresa.setNumeroFoneLeiturista(atualizarConsultarArquivoTextoLeituraActionForm.getNumeroFoneLeiturista());

		// Empresa
		Empresa empresa = null;

		if (atualizarConsultarArquivoTextoLeituraActionForm.getEmpresaID() != null
				&& !atualizarConsultarArquivoTextoLeituraActionForm
						.getEmpresaID().equals("")
				&& !atualizarConsultarArquivoTextoLeituraActionForm
						.getEmpresaID().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			empresa = new Empresa();

			arquivoTextoRoteiroEmpresa.setId(new Integer(
					atualizarConsultarArquivoTextoLeituraActionForm
							.getEmpresaID()));
		}

		arquivoTextoRoteiroEmpresa.setEmpresa(empresa);

		// Grupo Faturamento

		if (atualizarConsultarArquivoTextoLeituraActionForm
				.getGrupoFaturamentoID() != null) {

			Integer grupoFaturamentoID = new Integer(
					atualizarConsultarArquivoTextoLeituraActionForm
							.getGrupoFaturamentoID());

			if (grupoFaturamentoID
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				arquivoTextoRoteiroEmpresa.setFaturamentoGrupo(null);
			} else {
				FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoGrupo.ID,
						atualizarConsultarArquivoTextoLeituraActionForm
								.getGrupoFaturamentoID().toString()));
				Collection colecaoGrupoFaturamento = (Collection) fachada
						.pesquisar(filtroFaturamentoGrupo,
								FaturamentoGrupo.class.getName());

				// setando
				arquivoTextoRoteiroEmpresa
						.setFaturamentoGrupo((FaturamentoGrupo) colecaoGrupoFaturamento
								.iterator().next());
			}
		}

		// Date mesAnoFormatada = Util
		// .converteStringParaDate(mesAno);
		//		
		// arquivoTextoRoteiroEmpresa
		// .setDataTentativaEntrega(dataDevolucaoFormatada);


		arquivoTextoRoteiroEmpresa.setUltimaAlteracao(new Date());
		
//		fachada.atualizarConsultarArquivoTextoLeitura(arquivoTextoRoteiroEmpresa);

		montarPaginaSucesso(httpServletRequest, "Situação Usuario "
				+ arquivoTextoRoteiroEmpresa.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Situação Usuario",
				"exibirManterSituacaoUsuarioAction.do");

		return retorno;

	}

}
