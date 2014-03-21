package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Arrays;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.util.ConstantesSistema;

import org.apache.struts.action.ActionForm;

public class FiltrarAlteracaoAtualizacaoCadastralActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

    private String idEmpresa;
    
    private String nomeEmpresa;
    
    private String idLeiturista;
    
    private String nomeLeiturista;
    
    private String periodoRealizacaoInicial;
    
    private String periodoRealizacaoFinal;
    
	private String idRegistrosNaoAutorizados;
	
	private String idRegistrosAutorizados;
	
	private String exibirCampos = SituacaoAtualizacaoCadastral.TRANSMITIDO.toString(); 
	
	private String[] colunaImoveisSelecionados;
	
	private String idLocalidadeInicial;	

	private String idLocalidadeFinal;

	private String nomeLocalidadeInicial;	

	private String nomeLocalidadeFinal;
	
	private String cdSetorComercialInicial;	
	
	private String cdSetorComercialFinal;
	
	private String nomeSetorComercialInicial;	
	
	private String nomeSetorComercialFinal;
	
	private String cdRotaInicial;

	private String cdRotaFinal;
	
	private String alteracaoSituacaoImovel;
	
	private String alteracaoSituacaoAgua;
	
	private String alteracaoSituacaoEsgoto;
	
	private String alteracaoCategoria;

	public String getIdRegistrosAutorizados() {
		return idRegistrosAutorizados;
	}

	public void setIdRegistrosAutorizados(String idRegistrosAutorizados) {
		this.idRegistrosAutorizados = idRegistrosAutorizados;
	}

	public String getIdRegistrosNaoAutorizados() {
		return idRegistrosNaoAutorizados;
	}

	public void setIdRegistrosNaoAutorizados(String idRegistrosNaoAutorizados) {
		this.idRegistrosNaoAutorizados = idRegistrosNaoAutorizados;
	}

	public String getPeriodoRealizacaoFinal() {
		return periodoRealizacaoFinal;
	}

	public void setPeriodoRealizacaoFinal(String periodoRealizacaoFinal) {
		this.periodoRealizacaoFinal = periodoRealizacaoFinal;
	}

	public String getPeriodoRealizacaoInicial() {
		return periodoRealizacaoInicial;
	}

	public void setPeriodoRealizacaoInicial(String periodoRealizacaoInicial) {
		this.periodoRealizacaoInicial = periodoRealizacaoInicial;
	}

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String[] getColunaImoveisSelecionados() {
		return colunaImoveisSelecionados;
	}

	public void setColunaImoveisSelecionados(String[] colunaImoveisSelecionados) {
		this.colunaImoveisSelecionados = colunaImoveisSelecionados;
	}

	public String getExibirCampos() {
		return exibirCampos;
	}

	public void setExibirCampos(String exibirCampos) {
		this.exibirCampos = exibirCampos;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public void setCdSetorComercialInicial(String setorInicial) {
		this.cdSetorComercialInicial = setorInicial;
	}

	public String getCdSetorComercialFinal() {
		return cdSetorComercialFinal;
	}

	public void setCdSetorComercialFinal(String setorFinal) {
		this.cdSetorComercialFinal = setorFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorInicial) {
		this.nomeSetorComercialInicial = nomeSetorInicial;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComercialFinal(String nomeSetorFinal) {
		this.nomeSetorComercialFinal = nomeSetorFinal;
	}

	public String getCdRotaInicial() {
		return cdRotaInicial;
	}

	public void setCdRotaInicial(String codRotaInicial) {
		this.cdRotaInicial = codRotaInicial;
	}

	public String getCdRotaFinal() {
		return cdRotaFinal;
	}

	public void setCdRotaFinal(String codRotaFinal) {
		this.cdRotaFinal = codRotaFinal;
	}
	
	public String getAlteracaoSituacaoImovel() {
		return alteracaoSituacaoImovel;
	}

	public void setAlteracaoSituacaoImovel(String alteracaoSituacaoImovel) {
		this.alteracaoSituacaoImovel = alteracaoSituacaoImovel;
	}

	public String getAlteracaoSituacaoAgua() {
		return alteracaoSituacaoAgua;
	}

	public void setAlteracaoSituacaoAgua(String alteracaoSituacaoAgua) {
		this.alteracaoSituacaoAgua = alteracaoSituacaoAgua;
	}

	public String getAlteracaoSituacaoEsgoto() {
		return alteracaoSituacaoEsgoto;
	}

	public void setAlteracaoSituacaoEsgoto(String alteracaoSituacaoEsgoto) {
		this.alteracaoSituacaoEsgoto = alteracaoSituacaoEsgoto;
	}

	public String getAlteracaoCategoria() {
		return alteracaoCategoria;
	}

	public void setAlteracaoCategoria(String alteracaoCategoria) {
		this.alteracaoCategoria = alteracaoCategoria;
	}

	public boolean existeParametroInformado() {
		boolean peloMenosUmParametroInformado = false;

		if (this.getIdEmpresa() != null
				&& !this.getIdEmpresa().trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
		}

		if (this.getIdLeiturista() != null
				&& !this.getIdLeiturista().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (this.getIdLocalidadeInicial() != null
				&& !this.getIdLocalidadeInicial().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (this.getCdSetorComercialInicial() != null
				&& !this.getCdSetorComercialInicial().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (this.getCdRotaInicial() != null
				&& !this.getCdRotaInicial().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (this.getIdLocalidadeFinal() != null
				&& !this.getIdLocalidadeFinal().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (this.getCdSetorComercialFinal() != null
				&& !this.getCdSetorComercialFinal().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (this.getCdRotaFinal() != null && !this.getCdRotaFinal().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (this.getExibirCampos() != null
				&& !this.getExibirCampos().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (this.getColunaImoveisSelecionados() != null
				&& !this.getColunaImoveisSelecionados().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		return peloMenosUmParametroInformado;
	}

	public String toString() {
		return "FiltrarAlteracaoAtualizacaoCadastralActionForm [idEmpresa=" + idEmpresa + ", nomeEmpresa=" + nomeEmpresa + ", idLeiturista=" + idLeiturista
				+ ", nomeLeiturista=" + nomeLeiturista + ", periodoRealizacaoInicial=" + periodoRealizacaoInicial + ", periodoRealizacaoFinal="
				+ periodoRealizacaoFinal + ", idRegistrosNaoAutorizados=" + idRegistrosNaoAutorizados + ", idRegistrosAutorizados=" + idRegistrosAutorizados
				+ ", exibirCampos=" + exibirCampos + ", colunaImoveisSelecionados=" + Arrays.toString(colunaImoveisSelecionados) + ", idLocalidadeInicial="
				+ idLocalidadeInicial + ", idLocalidadeFinal=" + idLocalidadeFinal + ", nomeLocalidadeInicial=" + nomeLocalidadeInicial
				+ ", nomeLocalidadeFinal=" + nomeLocalidadeFinal + ", cdSetorComercialInicial=" + cdSetorComercialInicial + ", cdSetorComercialFinal="
				+ cdSetorComercialFinal + ", nomeSetorComercialInicial=" + nomeSetorComercialInicial + ", nomeSetorComercialFinal=" + nomeSetorComercialFinal
				+ ", cdRotaInicial=" + cdRotaInicial + ", cdRotaFinal=" + cdRotaFinal + "]";
	}
}