package gcom.relatorio.cadastro.atualizacaocadastral;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.big.RelatorioBIGActionForm;
import gcom.gui.relatorio.cadastro.atualizacaocadastral.RelatorioRelacaoImoveisRotaActionForm;
import gcom.relatorio.big.RelatorioBIG;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

public class RelatorioRelacaoImoveisRotaBO {
	
	private RelatorioRelacaoImoveisRotaActionForm form;
	private Usuario usuario;
	private String idLocalidade;	
	private String cdSetorComercial;	
	private String cdRota;
	
	public RelatorioRelacaoImoveisRotaBO(ActionForm actionForm, HttpServletRequest httpServletRequest){
		this.form = (RelatorioRelacaoImoveisRotaActionForm) actionForm;
		this.usuario = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");
		this.idLocalidade = ((RelatorioRelacaoImoveisRotaActionForm) actionForm).getIdLocalidadeInicial();
		this.cdSetorComercial = ((RelatorioRelacaoImoveisRotaActionForm) actionForm).getCdSetorComercialInicial();
		this.cdRota = ((RelatorioRelacaoImoveisRotaActionForm) actionForm).getCdRotaInicial();
	}
	
	public RelatorioRelacaoImoveisRota getRelatorioRelacaoImoveisRota() {
		RelatorioRelacaoImoveisRota relatorioRelacaoImoveisRota = new RelatorioRelacaoImoveisRota(usuario);
		
		Collection colecaoDadosRelacaoImoveisRota = getColecaoDadosRelacaoImoveisRota();
		String totalResgistros = String.valueOf(colecaoDadosRelacaoImoveisRota.size());
		
		relatorioRelacaoImoveisRota.addParametro("colecaoDadosRelacaoImoveisRota", colecaoDadosRelacaoImoveisRota);
		relatorioRelacaoImoveisRota.addParametro("idLocalidade", this.idLocalidade);
		relatorioRelacaoImoveisRota.addParametro("cdSetorComercial", this.cdSetorComercial);
		relatorioRelacaoImoveisRota.addParametro("cdRota", this.cdRota);
		relatorioRelacaoImoveisRota.addParametro("totalRegistros", totalResgistros);
		
		return relatorioRelacaoImoveisRota;
	}
	
	private Collection getColecaoDadosRelacaoImoveisRota() {
		String idLocalidade = form.getIdLocalidadeInicial();
		String cdSetorComercial = form.getCdSetorComercialInicial();
		String cdRota = form.getCdRotaInicial();
		
		Collection colecaoDadosRelacaoImoveisRota = Fachada.getInstancia().pesquisarDadosRelatorioRelacaoImoveisRota(idLocalidade, cdSetorComercial, cdRota);
		
		if(colecaoDadosRelacaoImoveisRota.isEmpty()){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		return colecaoDadosRelacaoImoveisRota;
	}

}
