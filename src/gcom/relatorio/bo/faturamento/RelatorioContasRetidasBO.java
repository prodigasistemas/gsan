package gcom.relatorio.bo.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.faturamento.RelatorioContasRetidasActionForm;
import gcom.relatorio.faturamento.RelatorioContasRetidas;
import gcom.relatorio.faturamento.RelatorioContasRetidasHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

public class RelatorioContasRetidasBO {
	
	private RelatorioContasRetidasActionForm form;
	private String idFaturamentoGrupo;
	private String mesAno;

	private Usuario usuario;
	private String tipoRelatorio;

	public RelatorioContasRetidasBO(ActionForm actionForm, HttpServletRequest httpServletRequest){
		this.form = (RelatorioContasRetidasActionForm) actionForm;
		this.idFaturamentoGrupo = (String) form.getIdGrupoFaturamento();
		this.usuario = (Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado");
		this.tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		this.mesAno = (String)form.getMesAno();
	}
	
	public RelatorioContasRetidas getRelatorioContasRetidas() {
		
		if(getAnoMesReferencia().equals("")){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		RelatorioContasRetidas relatorioContasRetidas = new RelatorioContasRetidas(usuario);
		
		relatorioContasRetidas.addParametro("colecaoContasRetidas", getColecaoContasRetidas());
		relatorioContasRetidas.addParametro("mesAno", Util.formatarAnoMesParaMesAno(getAnoMesReferencia()));
		relatorioContasRetidas.addParametro("idGrupoFaturamento", String.valueOf(idFaturamentoGrupo));
		relatorioContasRetidas.addParametro("tipoFormatoRelatorio", getTipoFormatoRelatorio());
		
		return relatorioContasRetidas;
	}

	public String getAnoMesReferencia() {
		String anoMes = "";

		if (possuiIdFaturamento() && possuiMesAno()) {
			anoMes = mesAno.substring(3, 7) + mesAno.substring(0, 2);
		}
		
		return anoMes;
	}

	public String getMesAno() {
		return mesAno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}
	
	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}
	
	private Collection<RelatorioContasRetidasHelper> getColecaoContasRetidas() {
		Collection<RelatorioContasRetidasHelper> colecaoContasRetidas = getContasRetidas();
		
		if(colecaoContasRetidas.isEmpty()){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		return colecaoContasRetidas;
	}
	
	private int getTipoFormatoRelatorio(){
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		return Integer.parseInt(tipoRelatorio);
	}

	private Collection<RelatorioContasRetidasHelper> getContasRetidas() {
		return Fachada.getInstancia().pesquisarDadosRelatorioContasRetidas(Integer.parseInt(getAnoMesReferencia()), 
				Integer.valueOf(getIdFaturamentoGrupo()));
	}

	private boolean possuiIdFaturamento() {
		return idFaturamentoGrupo != null && !idFaturamentoGrupo.trim().equals("") && !idFaturamentoGrupo.trim().equals("-1");
	}
	
	private boolean possuiMesAno(){
		return mesAno != null && !mesAno.trim().equals("");
	}
}
