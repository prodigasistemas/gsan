package gcom.relatorio.cadastro.atualizacaocadastral;

import gcom.gui.cadastro.CarregarDadosAtualizacaoCadastralActionForm;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RelatorioInconsistenciasRetornoBO {
	
	private Usuario usuario;
	
	private Map<String, List<String>> colecaoErrosCadastro;
	private String nomeArquivo;
	private String totalImoveis;
	private String totalImoveisComErro;

	public RelatorioInconsistenciasRetornoBO(HttpServletRequest httpServletRequest, CarregarDadosAtualizacaoCadastralActionForm form) {
		this.usuario = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");
		this.colecaoErrosCadastro = form.getColecaoErrosCadastro();
		this.nomeArquivo = form.getNomeArquivo();
		this.totalImoveis = form.getTotalImoveis();
		this.totalImoveisComErro = form.getTotalImoveisComErro();
	}
	
	public RelatorioInconsistenciasRetorno getRelatorioInconsistenciasRetorno() {
		RelatorioInconsistenciasRetorno relatorioInconsistenciasRetorno = new RelatorioInconsistenciasRetorno(usuario);
		
		relatorioInconsistenciasRetorno.addParametro("colecaoErrosCadastro", getColecaoErrosCadastro());
		relatorioInconsistenciasRetorno.addParametro("nomeArquivo", nomeArquivo);
		relatorioInconsistenciasRetorno.addParametro("totalImoveis", totalImoveis);
		relatorioInconsistenciasRetorno.addParametro("totalImoveisComErro", totalImoveisComErro);
		
		return relatorioInconsistenciasRetorno;
	}
	
	@SuppressWarnings("rawtypes")
	private Map<String, List<String>> getColecaoErrosCadastro() {
		return colecaoErrosCadastro;
	}
}
