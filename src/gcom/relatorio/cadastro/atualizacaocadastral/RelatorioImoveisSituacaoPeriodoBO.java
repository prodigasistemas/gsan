package gcom.relatorio.cadastro.atualizacaocadastral;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.cadastro.atualizacaocadastral.RelatorioImoveisSituacaoPeriodoActionForm;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

public class RelatorioImoveisSituacaoPeriodoBO {

	private RelatorioImoveisSituacaoPeriodoActionForm form;
	private String dataInicial;
	private String dataFinal;
	private String idSituacaoCadastral;

	private Usuario usuario;
	private String tipoRelatorio;

	public RelatorioImoveisSituacaoPeriodoBO(ActionForm actionForm, HttpServletRequest request) {
		this.form = (RelatorioImoveisSituacaoPeriodoActionForm) actionForm;
		this.dataInicial = (String) form.getDataInicial();
		this.dataFinal = (String) form.getDataFinal();
		this.idSituacaoCadastral = (String) form.getIdSituacaoCadastral();

		this.usuario = (Usuario) (request.getSession(false)).getAttribute("usuarioLogado");
		this.tipoRelatorio = request.getParameter("tipoRelatorio");
	}

	public RelatorioImoveisSituacaoPeriodo getRelatorioImoveisSituacaoPeriodo() {
		if (idSituacaoCadastral.equals("-1"))
			throw new ActionServletException("atencao.campo_texto.obrigatorio", "Situação");

		if (temPeriodo()) {
			if (dataInicial != null && dataInicial.equals(""))
				throw new ActionServletException("atencao.data.inicial.invalida");

			if (dataFinal != null && dataFinal.equals(""))
				throw new ActionServletException("atencao.data.final.invalida");
		}

		RelatorioImoveisSituacaoPeriodo relatorio = new RelatorioImoveisSituacaoPeriodo(usuario);
		relatorio.addParametro("descricaoSituacaoCadastral", getSituacaoAtualizacaoCadastral());
		relatorio.addParametro("dataInicial", dataInicial);
		relatorio.addParametro("dataFinal", dataFinal);
		relatorio.addParametro("tipoFormatoRelatorio", getTipoFormatoRelatorio());
		relatorio.addParametro("colecaoImoveis", getImoveis(
				Util.converteStringParaDate(dataInicial), Util.converteStringParaDate(dataFinal), Integer.valueOf(idSituacaoCadastral)));
		return relatorio;
	}

	private boolean temPeriodo() {
		return idSituacaoCadastral.equals(SituacaoAtualizacaoCadastral.DISPONIVEL.toString())
				|| idSituacaoCadastral.equals(SituacaoAtualizacaoCadastral.TRANSMITIDO.toString())
				|| idSituacaoCadastral.equals(SituacaoAtualizacaoCadastral.APROVADO.toString())
				|| idSituacaoCadastral.equals(SituacaoAtualizacaoCadastral.ATUALIZADO.toString());
	}

	private String getSituacaoAtualizacaoCadastral() {
		SituacaoAtualizacaoCadastral situacao = Fachada.getInstancia().pesquisarSituacaoAtualizacaoCadastralPorId(Integer.valueOf(idSituacaoCadastral));
		return situacao.getDescricao();
	}

	private Collection<Integer> getImoveis(Date dataInicial, Date dataFinal, Integer idSituacaoCadastral) {
		return Fachada.getInstancia().pesquisarImoveisPorSituacaoPeriodo(dataInicial, dataFinal, idSituacaoCadastral);
	}

	public RelatorioImoveisSituacaoPeriodoActionForm getForm() {
		return form;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public String getIdSituacaoCadastral() {
		return idSituacaoCadastral;
	}

	private int getTipoFormatoRelatorio() {
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		return Integer.parseInt(tipoRelatorio);
	}
}
