package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorForm;

/**
 * @see gcom.gui.micromedicao.InformarSubdivisoesDeRotaAction
 * @see gcom.gui.micromedicao.InformarSubdivisoesDeRotaActionForm
 * @see gcom.gui.micromedicao.ExibirInformarSubdivisoesDeRotaAction
 * 
 * @author Victor Cisneiros
 * @date 28/09/2008
 */
public class InformarSubdivisoesDeRotaActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	
	private String idRota;
	private String codigoRota;
	private String descricaoRota;
	 
	private String quadraInicial;
	private String quadraFinal;
	private String quantidadeQuadras;
	private String idLeiturista;
	private String nomeLeiturista;
	
	private String idGrupoFaturamento;
	private String idCobrancaGrupo;

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public InformarSubdivisoesDeRotaActionForm() { }
	
	public String getIdRota() {
		return idRota;
	}

	public void setIdRota(String idRota) {
		this.idRota = idRota;
	}

	public String getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(String intervaloFinal) {
		this.quadraFinal = intervaloFinal;
	}

	public String getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(String intervaloInicial) {
		this.quadraInicial = intervaloInicial;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String leiturista) {
		this.idLeiturista = leiturista;
	}

	public String getDescricaoRota() {
		return descricaoRota;
	}

	public void setDescricaoRota(String descricaoRota) {
		this.descricaoRota = descricaoRota;
	}
	
	
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getQuantidadeQuadras() {
		return quantidadeQuadras;
	}

	public void setQuantidadeQuadras(String quantidadeQuadras) {
		this.quantidadeQuadras = quantidadeQuadras;
	}

	public String getIdCobrancaGrupo() {
		return idCobrancaGrupo;
	}

	public void setIdCobrancaGrupo(String idCobrancaGrupo) {
		this.idCobrancaGrupo = idCobrancaGrupo;
	}

}
