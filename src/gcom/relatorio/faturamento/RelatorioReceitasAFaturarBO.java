package gcom.relatorio.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.faturamento.RelatorioReceitasAFaturarActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

public class RelatorioReceitasAFaturarBO {

	private RelatorioReceitasAFaturarActionForm form;
	private Usuario usuario;
	private String mesAno;
	private Integer grupoFaturamentoID;
	private String nomeRelatorio;

	public RelatorioReceitasAFaturarBO(ActionForm actionForm, HttpServletRequest httpServletRequest) {
		this.form = (RelatorioReceitasAFaturarActionForm) actionForm;
		this.usuario = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");
		this.mesAno = form.getMesAno();
		if(form.getGrupoFaturamentoID() <= 0) {
			this.grupoFaturamentoID = null;
			nomeRelatorio = ConstantesRelatorios.RELATORIO_RECEITAS_A_FATURAR_SINTETICO;
		}
		else {
			this.grupoFaturamentoID = form.getGrupoFaturamentoID();
			nomeRelatorio = ConstantesRelatorios.RELATORIO_RECEITAS_A_FATURAR_ANALITICO;
		}
	}

	public RelatorioReceitasAFaturar getRelatorioRelacaoImoveisRota() {
		if(getAnoMesReferencia().equals("")){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		RelatorioReceitasAFaturar relatorioReceitasAFaturar = new RelatorioReceitasAFaturar(usuario, nomeRelatorio);

		Collection<RelatorioReceitasAFaturarHelper> colecaoDadosRelatorio = getColecaoDadosRelatorio();
		String totalResgistros = String.valueOf(colecaoDadosRelatorio.size());

		relatorioReceitasAFaturar.addParametro("colecaoDadosRelacaoImoveisRota", colecaoDadosRelatorio);
		relatorioReceitasAFaturar.addParametro("idGrupo", this.grupoFaturamentoID);
		relatorioReceitasAFaturar.addParametro("ano", this.mesAno.substring(3));
		relatorioReceitasAFaturar.addParametro("mes", this.mesAno.substring(0, 2));
		
		if(this.grupoFaturamentoID != null) {
			getDataLeituraAnteriorAtual(relatorioReceitasAFaturar, colecaoDadosRelatorio);
		}

		return relatorioReceitasAFaturar;
	}

	private void getDataLeituraAnteriorAtual(RelatorioReceitasAFaturar relatorioReceitasAFaturar, Collection<RelatorioReceitasAFaturarHelper> colecaoDadosRelatorio) {
		ArrayList<RelatorioReceitasAFaturarHelper> lista = (ArrayList) colecaoDadosRelatorio;
		Date dataLeituraAnterior = lista.get(0).getDataLeituraAnterior();
		Date dataLeituraAtual = lista.get(0).getDataLeituraPrevista();
		relatorioReceitasAFaturar.addParametro("dataLeituraAnterior", dataLeituraAnterior);
		relatorioReceitasAFaturar.addParametro("dataLeituraAtual", dataLeituraAtual);
	}

	// parametros: grupo(um ou todos) e ano/mes de referencia
	private Collection<RelatorioReceitasAFaturarHelper> getColecaoDadosRelatorio() {
		Collection colecaoDadosRelatorio = Fachada.getInstancia().pesquisarDadosRelatorioReceitasAFaturar(this.grupoFaturamentoID,
				Integer.parseInt(getAnoMesReferencia()));

		if (colecaoDadosRelatorio.isEmpty()) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		return colecaoDadosRelatorio;
	}

	public String getAnoMesReferencia() {
		String anoMes = "";

		if (mesAno != null && !mesAno.trim().equals("")) {
			anoMes = mesAno.substring(3, 7) + mesAno.substring(0, 2);
		}

		return anoMes;
	}
}