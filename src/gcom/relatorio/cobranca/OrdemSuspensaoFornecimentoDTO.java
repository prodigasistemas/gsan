package gcom.relatorio.cobranca;

import java.util.List;

public class OrdemSuspensaoFornecimentoDTO {

	private String cliente;
	private String endereco;

	private String grupo;
	private String rota;
	private String rotaSequencial;

	private Integer matricula;
	private String inscricao;

	private Integer documento;
	private String dataEmissao;
	private String valorTotal;

	private String categoria;
	private String ligacaoAgua;
	private String ligacaoEsgoto;

	private Integer ordemServico;

	private String hidrometro;
	private Integer hidrometroLeitura;
	private String hidrometroLocalizacao;

	private String codigoBarras;
	private String codigoBarrasFormatado;

	private List<CobrancaDocumentoContaDTO> contas;

	public OrdemSuspensaoFornecimentoDTO() {
		super();
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getRotaSequencial() {
		return rotaSequencial;
	}

	public void setRotaSequencial(String rotaSequencial) {
		this.rotaSequencial = rotaSequencial;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLigacaoAgua() {
		return ligacaoAgua;
	}

	public void setLigacaoAgua(String ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public String getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}

	public void setLigacaoEsgoto(String ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	public Integer getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(Integer ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}

	public Integer getHidrometroLeitura() {
		return hidrometroLeitura;
	}

	public void setHidrometroLeitura(Integer hidrometroLeitura) {
		this.hidrometroLeitura = hidrometroLeitura;
	}

	public String getHidrometroLocalizacao() {
		return hidrometroLocalizacao;
	}

	public void setHidrometroLocalizacao(String hidrometroLocalizacao) {
		this.hidrometroLocalizacao = hidrometroLocalizacao;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCodigoBarrasFormatado() {
		return codigoBarrasFormatado;
	}

	public void setCodigoBarrasFormatado(String codigoBarrasFormatado) {
		this.codigoBarrasFormatado = codigoBarrasFormatado;
	}

	public List<CobrancaDocumentoContaDTO> getContas() {
		return contas;
	}

	public void setContas(List<CobrancaDocumentoContaDTO> contas) {
		this.contas = contas;
	}
}