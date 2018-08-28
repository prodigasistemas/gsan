package gcom.gui.cadastro;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarArquivoTextoAtualizacaoCadastralActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String idLocalidade;

	private String nomeLocalidade;

	private String codigoSetorComercial;

	private String nomeSetorComercial;

	private String idEmpresa;

	private String leituristaID;

	private String idSituacaoTransmissao;

	private String sitTransmissao;

	private String exibicao;

	private String[] idsRegistros;

	private String percentualAleatorios;
	
	private String lote;

	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		super.reset(mapping, request);
		this.sitTransmissao = "";
		this.idsRegistros = new String[0];
		this.leituristaID = "";
		this.idSituacaoTransmissao = "";
		this.exibicao = "1";
		this.percentualAleatorios = "0";
		this.lote = "0";
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getLeituristaID() {
		return leituristaID;
	}

	public void setLeituristaID(String leituristaID) {
		this.leituristaID = leituristaID;
	}

	public String getIdSituacaoTransmissao() {
		return idSituacaoTransmissao;
	}

	public void setIdSituacaoTransmissao(String idSituacaoTransmissao) {
		this.idSituacaoTransmissao = idSituacaoTransmissao;
	}

	public String getSitTransmissao() {
		return sitTransmissao;
	}

	public void setSitTransmissao(String sitTransmissao) {
		this.sitTransmissao = sitTransmissao;
	}

	public String getExibicao() {
		return exibicao;
	}

	public void setExibicao(String exibicao) {
		this.exibicao = exibicao;
	}

	public String[] getIdsRegistros() {
		return idsRegistros;
	}

	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}

	public String getPercentualAleatorios() {
		return percentualAleatorios;
	}

	public void setPercentualAleatorios(String percentualAleatorios) {
		this.percentualAleatorios = percentualAleatorios;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}
}