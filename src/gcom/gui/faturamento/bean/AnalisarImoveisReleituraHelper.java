package gcom.gui.faturamento.bean;

import gcom.micromedicao.leitura.LeituraAnormalidade;

public class AnalisarImoveisReleituraHelper {

	public static final short RELEITURA_NAO_REALIZADA = 1;

	public static final short RELEITURA_PENDENTE = 2;

	public static final short RELEITURA_REALIZADA = 3;

	private int idQuadra;

	private int idLocalidade;

	private int codigoSetorComercial;

	private int codigoRota;

	private Integer empresa;

	private String matriculaImovel;

	private String recebeuMensagem;

	private Integer leituraAtualAgua;

	private Integer leituraAnteriorAgua;

	private Integer leituraAtualPoco;

	private Integer leituraAnteriorPoco;

	private LeituraAnormalidade leituraAnormalidadeAnteriorAgua;

	private LeituraAnormalidade leituraAnormalidadeAtualAgua;

	private LeituraAnormalidade leituraAnormalidadeAnteriorPoco;

	private LeituraAnormalidade leituraAnormalidadeAtualPoco;

	private short idSituacaoReleitura;

	public int getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(int codigoRota) {
		this.codigoRota = codigoRota;
	}

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public int getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(int idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public int getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(int idQuadra) {
		this.idQuadra = idQuadra;
	}

	public short getIdSituacaoReleitura() {
		return idSituacaoReleitura;
	}

	public void setIdSituacaoReleitura(short idSituacaoReleitura) {
		this.idSituacaoReleitura = idSituacaoReleitura;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getRecebeuMensagem() {
		return recebeuMensagem;
	}

	public void setRecebeuMensagem(String recebeuMensagem) {
		this.recebeuMensagem = recebeuMensagem;
	}

	public String getHint() {
		return "--------------------- ÁGUA ---------------------"
				+ "<br>"
				+ "Leitura Anterior: "
				+ (this.getLeituraAnteriorAgua() != null ? this
						.getLeituraAnteriorAgua() : "---")
				+ "<br>"
				+ "Leitura Atual : "
				+ (this.getLeituraAtualAgua() != null ? this
						.getLeituraAtualAgua() : "---")
				+ "<br>"
				+ "Anormalidade Anterior: "
				+ (this.getLeituraAnormalidadeAnteriorAgua() != null ? this
						.getLeituraAnormalidadeAnteriorAgua().getDescricao()
						: "---")
				+ "<br>"
				+ "Anormalidade Atual: "
				+ (this.getLeituraAnormalidadeAtualAgua() != null ? this
						.getLeituraAnormalidadeAtualAgua().getDescricao()
						: "---")
				+ "<br>"
				+ "--------------------- POÇO ---------------------"
				+ "<br>"
				+ "Leitura Anterior: "
				+ (this.getLeituraAnteriorPoco() != null ? this
						.getLeituraAnteriorPoco() : "---")
				+ "<br>"
				+ "Leitura Atual : "
				+ (this.getLeituraAtualPoco() != null ? this
						.getLeituraAtualPoco() : "---")
				+ "<br>"
				+ "Anormalidade Anterior: "
				+ (this.getLeituraAnormalidadeAnteriorPoco() != null ? this
						.getLeituraAnormalidadeAnteriorPoco().getDescricao()
						: "---")
				+ "<br>"
				+ "Anormalidade Atual: "
				+ (this.getLeituraAnormalidadeAtualPoco() != null ? this
						.getLeituraAnormalidadeAtualPoco().getDescricao()
						: "---") + "<br>";
	}

	public LeituraAnormalidade getLeituraAnormalidadeAnteriorAgua() {
		return leituraAnormalidadeAnteriorAgua;
	}

	public void setLeituraAnormalidadeAnteriorAgua(
			LeituraAnormalidade leituraAnormalidadeAnteriorAgua) {
		this.leituraAnormalidadeAnteriorAgua = leituraAnormalidadeAnteriorAgua;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAnteriorPoco() {
		return leituraAnormalidadeAnteriorPoco;
	}

	public void setLeituraAnormalidadeAnteriorPoco(
			LeituraAnormalidade leituraAnormalidadeAnteriorPoco) {
		this.leituraAnormalidadeAnteriorPoco = leituraAnormalidadeAnteriorPoco;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAtualAgua() {
		return leituraAnormalidadeAtualAgua;
	}

	public void setLeituraAnormalidadeAtualAgua(
			LeituraAnormalidade leituraAnormalidadeAtualAgua) {
		this.leituraAnormalidadeAtualAgua = leituraAnormalidadeAtualAgua;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAtualPoco() {
		return leituraAnormalidadeAtualPoco;
	}

	public void setLeituraAnormalidadeAtualPoco(
			LeituraAnormalidade leituraAnormalidadeAtualPoco) {
		this.leituraAnormalidadeAtualPoco = leituraAnormalidadeAtualPoco;
	}

	public Integer getLeituraAnteriorAgua() {
		return leituraAnteriorAgua;
	}

	public void setLeituraAnteriorAgua(Integer leituraAnteriorAgua) {
		this.leituraAnteriorAgua = leituraAnteriorAgua;
	}

	public Integer getLeituraAnteriorPoco() {
		return leituraAnteriorPoco;
	}

	public void setLeituraAnteriorPoco(Integer leituraAnteriorPoco) {
		this.leituraAnteriorPoco = leituraAnteriorPoco;
	}

	public Integer getLeituraAtualAgua() {
		return leituraAtualAgua;
	}

	public void setLeituraAtualAgua(Integer leituraAtualAgua) {
		this.leituraAtualAgua = leituraAtualAgua;
	}

	public Integer getLeituraAtualPoco() {
		return leituraAtualPoco;
	}

	public void setLeituraAtualPoco(Integer leituraAtualPoco) {
		this.leituraAtualPoco = leituraAtualPoco;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

}
