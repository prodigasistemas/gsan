package gcom.relatorio.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RelatorioFichaFiscalizacaoCadastralBO {

	private Usuario usuario;
	private Collection<ConsultarMovimentoAtualizacaoCadastralHelper> helper;
	public boolean dadosOriginais;
	
	@SuppressWarnings("unchecked")
	public RelatorioFichaFiscalizacaoCadastralBO(HttpServletRequest httpServletRequest, Boolean dadosOriginais) {
		HttpSession sessao = httpServletRequest.getSession(false);
		
		this.dadosOriginais = dadosOriginais;
		this.usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		this.helper = (Collection<ConsultarMovimentoAtualizacaoCadastralHelper>) sessao.getAttribute(
				"colecaoConsultarMovimentoAtualizacaoCadastralHelper");
	}
	
	public RelatorioFichaFiscalizacaoCadastral getRelatorioFichaFiscalizacaoCadastral() {
		RelatorioFichaFiscalizacaoCadastral relatorioFichaFiscalizacaoCadastral = new RelatorioFichaFiscalizacaoCadastral(usuario);
		
		relatorioFichaFiscalizacaoCadastral.addParametro("colecaoDadosFicha", getColecaoDadosFicha());
		
		return relatorioFichaFiscalizacaoCadastral;
	}
	
	@SuppressWarnings("rawtypes")
	private Collection getColecaoDadosFicha() {
		
		List<Integer> listaIdImoveis = new ArrayList<Integer>();
		
		for (ConsultarMovimentoAtualizacaoCadastralHelper imovel : helper) {
			listaIdImoveis.add(imovel.getIdImovel());
		}
		
		Collection colecaoDadosFicha = Fachada.getInstancia().pesquisarDadosFichaFiscalizacaoCadastral(listaIdImoveis, this.dadosOriginais);
		
		if(colecaoDadosFicha.isEmpty()){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		return colecaoDadosFicha;
	}

	public void setDadosOriginais(boolean isOriginal) {
		this.dadosOriginais = isOriginal;
	}
	
}
