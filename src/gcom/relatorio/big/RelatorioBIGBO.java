package gcom.relatorio.big;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.big.RelatorioBIGActionForm;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

public class RelatorioBIGBO {
	
	private RelatorioBIGActionForm form;
	private String mesAno;

	private Usuario usuario;

	public RelatorioBIGBO(ActionForm actionForm, HttpServletRequest httpServletRequest){
		this.form = (RelatorioBIGActionForm) actionForm;
		this.usuario = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");
		this.mesAno = (String) form.getMesAno();
	}
	
	public RelatorioBIG getRelatorioBIG() {
		if(getAnoMesReferencia().equals("")){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		RelatorioBIG relatorioBIG = new RelatorioBIG(usuario);
		
		relatorioBIG.addParametro("colecaoDadosBIG", getColecaoDadosBIG());
		relatorioBIG.addParametro("mesAno", Util.formatarAnoMesParaMesAno(getAnoMesReferencia()));
		
		return relatorioBIG;
	}
	
	public String getAnoMesReferencia() {
		String anoMes = "";

		if (mesAno != null && !mesAno.trim().equals("")) {
			anoMes = mesAno.substring(3, 7) + mesAno.substring(0, 2);
		}
		
		return anoMes;
	}
	
	@SuppressWarnings("rawtypes")
	private Collection getColecaoDadosBIG() {
		
		Collection colecaoDadosBIG = Fachada.getInstancia().pesquisarDadosRelatorioBIG(
				Integer.parseInt(getAnoMesReferencia()));
		
		if(colecaoDadosBIG.isEmpty()){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		return colecaoDadosBIG;
	}
}
