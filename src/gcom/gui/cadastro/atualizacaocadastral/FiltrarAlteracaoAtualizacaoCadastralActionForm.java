package gcom.gui.cadastro.atualizacaocadastral;

import gcom.util.ConstantesSistema;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

public class FiltrarAlteracaoAtualizacaoCadastralActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	public static int FILTRO_PENDENTES = -1;
	public static int FILTRO_PARA_APROVACAO_EM_LOTE = -2;
	public static int FILTRO_TODOS = -3;

	private String idEmpresa;

	private String nomeEmpresa;

	private String idLeiturista;

	private String nomeLeiturista;

	private String periodoInicial;

	private String periodoFinal;

	private String idRegistrosNaoAutorizados;

	private String idRegistrosAutorizados;

	private String situacaoImoveis;

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

	private String alteracaoHidrometro = "-1";

	private String alteracaoSituacaoAgua = "-1";

	private String alteracaoSituacaoEsgoto = "-1";

	private String alteracaoCategoria = "-1";

	private String ocorrenciaCadastro = "-1";
	
	private String lote;
	
	private String matricula;
	
	private String inscricao;
	
	private String quantidadeVisitas = "-1";

	public FiltrarAlteracaoAtualizacaoCadastralActionForm() {
	}

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

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
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

	public String getAlteracaoHidrometro() {
		return alteracaoHidrometro;
	}

	public void setAlteracaoHidrometro(String alteracaoHidrometro) {
		this.alteracaoHidrometro = alteracaoHidrometro;
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

	public String getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public void setOcorrenciaCadastro(String ocorrenciaCadastro) {
		this.ocorrenciaCadastro = ocorrenciaCadastro;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getSituacaoImoveis() {
		return situacaoImoveis;
	}

	public void setSituacaoImoveis(String situacaoImoveis) {
		this.situacaoImoveis = situacaoImoveis;
	}

	public String getQuantidadeVisitas() {
		return quantidadeVisitas;
	}

	public void setQuantidadeVisitas(String quantidadeVisitas) {
		this.quantidadeVisitas = quantidadeVisitas;
	}

	public boolean existeParametroInformado() {
		boolean peloMenosUmParametroInformado = false;

		if (this.getIdEmpresa() != null && !this.getIdEmpresa().trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getIdLeiturista()))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getIdLocalidadeInicial()))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getCdSetorComercialInicial()))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getCdRotaInicial()))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getIdLocalidadeFinal()))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getCdSetorComercialFinal()))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getCdRotaFinal()))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getSituacaoImoveis()))
			peloMenosUmParametroInformado = true;

		if (this.getColunaImoveisSelecionados() != null && !this.getColunaImoveisSelecionados().equals(""))
			peloMenosUmParametroInformado = true;
		
		if (isParametroInformado(getLote()))
			peloMenosUmParametroInformado = true;
		
		if (isParametroInformado(getMatricula()))
			peloMenosUmParametroInformado = true;

		return peloMenosUmParametroInformado;
	}

	public String toString() {
		return "FiltrarAlteracaoAtualizacaoCadastralActionForm ["
				+ "idEmpresa=" + idEmpresa + ","
				+ "nomeEmpresa=" + nomeEmpresa + ","
				+ "idLeiturista=" + idLeiturista + ","
				+ "nomeLeiturista=" + nomeLeiturista + ","
				+ "periodoRealizacaoInicial=" + periodoInicial + ","
				+ "periodoRealizacaoFinal=" + periodoFinal + ","
				+ "idRegistrosNaoAutorizados=" + idRegistrosNaoAutorizados + ","
				+ "idRegistrosAutorizados=" + idRegistrosAutorizados + ","
				+ "exibirCampos=" + situacaoImoveis + ","
				+ "colunaImoveisSelecionados=" + Arrays.toString(colunaImoveisSelecionados) + ","
				+ "idLocalidadeInicial=" + idLocalidadeInicial + ","
				+ "idLocalidadeFinal=" + idLocalidadeFinal + ","
				+ "nomeLocalidadeInicial=" + nomeLocalidadeInicial + ","
				+ "nomeLocalidadeFinal=" + nomeLocalidadeFinal + ","
				+ "cdSetorComercialInicial=" + cdSetorComercialInicial + ","
				+ "cdSetorComercialFinal=" + cdSetorComercialFinal + ","
				+ "nomeSetorComercialInicial=" + nomeSetorComercialInicial + ","
				+ "nomeSetorComercialFinal=" + nomeSetorComercialFinal + ","
				+ "cdRotaInicial=" + cdRotaInicial + ","
				+ "cdRotaFinal=" + cdRotaFinal + "]";
	}
	
	private boolean isParametroInformado(String parametro) {
		return parametro != null && !parametro.trim().equals("");
	}
}
