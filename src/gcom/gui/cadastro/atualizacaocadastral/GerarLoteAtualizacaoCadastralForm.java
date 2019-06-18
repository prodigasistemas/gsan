package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

import gcom.util.ConstantesSistema;

public class GerarLoteAtualizacaoCadastralForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private static final String TODOS = "-1";

	private String idEmpresa;

	private String nomeEmpresa;

	private String idLeiturista;

	private String nomeLeiturista;

	private String periodoInicial;

	private String periodoFinal;

	private String idLocalidadeInicial;

	private String nomeLocalidadeInicial;

	private String cdSetorComercialInicial;

	private String nomeSetorComercialInicial;

	private String imoveisNovos = TODOS;

	private String grandesConsumidores = TODOS;

	private String ocorrenciaCadastro = TODOS;

	private String ocorrenciaCadastroSelecionada = TODOS;

	private String lote;

	private String qtdImoveisLote;

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

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
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

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public void setCdSetorComercialInicial(String cdSetorComercialInicial) {
		this.cdSetorComercialInicial = cdSetorComercialInicial;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	public String getImoveisNovos() {
		return imoveisNovos;
	}

	public void setImoveisNovos(String imoveisNovos) {
		this.imoveisNovos = imoveisNovos;
	}

	public String getGrandesConsumidores() {
		return grandesConsumidores;
	}

	public void setGrandesConsumidores(String grandesConsumidores) {
		this.grandesConsumidores = grandesConsumidores;
	}

	public String getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public void setOcorrenciaCadastro(String ocorrenciaCadastro) {
		this.ocorrenciaCadastro = ocorrenciaCadastro;
	}

	public String getOcorrenciaCadastroSelecionada() {
		return ocorrenciaCadastroSelecionada;
	}

	public void setOcorrenciaCadastroSelecionada(String ocorrenciaCadastroSelecionada) {
		this.ocorrenciaCadastroSelecionada = ocorrenciaCadastroSelecionada;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getQtdImoveisLote() {
		return qtdImoveisLote;
	}

	public void setQtdImoveisLote(String qtdImoveisLote) {
		this.qtdImoveisLote = qtdImoveisLote;
	}

	public boolean existeParametroInformado() {
		boolean peloMenosUmParametroInformado = false;

		if (idEmpresa != null && !idEmpresa.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(idEmpresa))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(periodoInicial) && isParametroInformado(periodoFinal))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getIdLocalidadeInicial()))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getCdSetorComercialInicial()))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(imoveisNovos))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(grandesConsumidores))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(ocorrenciaCadastro))
			peloMenosUmParametroInformado = true;

		if (isParametroInformado(getLote()))
			peloMenosUmParametroInformado = true;

		return peloMenosUmParametroInformado;
	}

	private boolean isParametroInformado(String parametro) {
		return parametro != null && !parametro.trim().equals("");
	}
}
