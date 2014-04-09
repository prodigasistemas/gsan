package gcom.gerencial.micromedicao;

/**
 * Classe responsável por ajudar o caso de uso [UC0343] Resumo de Anormalidades 
 *
 * @author Thiago Toscano
 * @date 20/04/2006
 */
public class ResumoAnormalidadeConsultaHelper {

	private String idImovel;
	private String descricaoGerenciaRegional;
	private String descricaoLocalidade;
	private String codigoSetorComercial;
	private String descricaoSetorComercial;
	private String idMedicaoTipo;
	private String numeroQuadra;
	private String descricaoLeituraAnormalidadeFaturada;
	private String descricaoConsumoAnormalidade;
	private String idLocalidade;
	private String idElo;
	private String descricaoElo;
	private String idGerenciaRegional;

	private String quantidadeMedicao;
	
	public ResumoAnormalidadeConsultaHelper(){
		
	}

	public String getDescricaoGerenciaRegional() {
		return descricaoGerenciaRegional;
	}

	public void setDescricaoGerenciaRegional(String descricaoGerenciaRegional) {
		this.descricaoGerenciaRegional = descricaoGerenciaRegional;
	}

	public String getDescricaoLeituraAnormalidadeFaturada() {
		return descricaoLeituraAnormalidadeFaturada;
	}

	public void setDescricaoLeituraAnormalidadeFaturada(
			String descricaoLeituraAnormalidadeFaturada) {
		this.descricaoLeituraAnormalidadeFaturada = descricaoLeituraAnormalidadeFaturada;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getQuantidadeMedicao() {
		return quantidadeMedicao;
	}

	public void setQuantidadeMedicao(String quantidadeMedicao) {
		this.quantidadeMedicao = quantidadeMedicao;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getIdMedicaoTipo() {
		return idMedicaoTipo;
	}

	public void setIdMedicaoTipo(String idMedicaoTipo) {
		this.idMedicaoTipo = idMedicaoTipo;
	}

	public String getDescricaoConsumoAnormalidade() {
		return descricaoConsumoAnormalidade;
	}

	public void setDescricaoConsumoAnormalidade(String descricaoConsumoAnormalidade) {
		this.descricaoConsumoAnormalidade = descricaoConsumoAnormalidade;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoElo() {
		return descricaoElo;
	}

	public void setDescricaoElo(String descricaoElo) {
		this.descricaoElo = descricaoElo;
	}

	public String getIdElo() {
		return idElo;
	}

	public void setIdElo(String idElo) {
		this.idElo = idElo;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
}
