package gcom.relatorio.cobranca;

import java.util.ArrayList;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gcom.relatorio.RelatorioBean;

/**
 * 
 * @author Mariana Vcitor
 * @date 17/01/2011
 * 
 */
public class RelatorioDocumentoCobrancaOrdemCorteBean implements RelatorioBean {
	
	private String sequencial;
	private String ordemServico;
	private String dataEmissao;
	private String dataValidade;
	private String grupo;
	private String matricula;
	private String inscricao;
	private String rota;
	private String sequencialRota;
	private String tipoCorte;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String nomeClienteUsuario;
	private String perfilCliente;
	private String cpfCliente;
	private String telefoneCliente;
	private String valorTotal;
	private String mesAno;
	private String dataVencimento;
	private String valor;

	private JRBeanCollectionDataSource arrayJRSubrelatorioBean;
	private ArrayList arrayRelatorioVisitaCobrancaSubrelatorioBean;
	private String subRelatorio;

	private String sequencialDocumentoCobranca;
	private String enderecoImovel;
	private String categoriaRES;
	private String categoriaPUB;
	private String categoriaIND;
	private String categoriaCOM;
	
	
	public RelatorioDocumentoCobrancaOrdemCorteBean() {
		super();
	}

	public RelatorioDocumentoCobrancaOrdemCorteBean(String sequencial, String ordemServico,
			String dataEmissao, String dataValidade, String grupo,
			String matricula, String inscricao, String rota, String sequencialRota,
			String tipoCorte, String situacaoLigacaoAgua, String situacaoLigacaoEsgoto,
			String nomeClienteUsuario, String perfilCliente, String cpfCliente,
			String telefoneCliente, String valorTotal, String mesAno, String dataVencimento,
			String valor, String subRelatorio, String sequencialDocumentoCobranca,
			String enderecoImovel, RelatorioVisitaCobrancaSubBean relatorioVisitaCobrancaSubBean,
			String categoriaRES, String categoriaPUB, String categoriaIND, String categoriaCOM) {
		super();
		this.sequencial = sequencial;
		this.ordemServico = ordemServico;
		this.dataEmissao = dataEmissao;
		this.dataValidade = dataValidade;
		this.grupo = grupo;
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.rota = rota;
		this.sequencialRota = sequencialRota;
		this.tipoCorte = tipoCorte;
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
		this.nomeClienteUsuario = nomeClienteUsuario;
		this.perfilCliente = perfilCliente;
		this.cpfCliente = cpfCliente;
		this.telefoneCliente = telefoneCliente;
		this.valorTotal = valorTotal;
		this.mesAno = mesAno;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.subRelatorio = subRelatorio;
		this.sequencialDocumentoCobranca = sequencialDocumentoCobranca;
		this.enderecoImovel = enderecoImovel;
		this.categoriaCOM = categoriaCOM;
		this.categoriaIND = categoriaIND;
		this.categoriaPUB = categoriaPUB;
		this.categoriaRES = categoriaRES;

		this.setRelatorioVisitaCobrancaSubBean(relatorioVisitaCobrancaSubBean);
	}
	
	public JRBeanCollectionDataSource getArrayJRSubrelatorioBean() {
		return arrayJRSubrelatorioBean;
	}

	public void setArrayJRSubrelatorioBean(
			JRBeanCollectionDataSource arrayJRSubrelatorioBean) {
		this.arrayJRSubrelatorioBean = arrayJRSubrelatorioBean;
	}

	public ArrayList getArrayRelatorioVisitaCobrancaSubrelatorioBean() {
		return arrayRelatorioVisitaCobrancaSubrelatorioBean;
	}

	public void setArrayRelatorioVisitaCobrancaSubrelatorioBean(
			ArrayList arrayRelatorioVisitaCobrancaSubrelatorioBean) {
		this.arrayRelatorioVisitaCobrancaSubrelatorioBean = arrayRelatorioVisitaCobrancaSubrelatorioBean;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getPerfilCliente() {
		return perfilCliente;
	}

	public void setPerfilCliente(String perfilCliente) {
		this.perfilCliente = perfilCliente;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public String getSequencialDocumentoCobranca() {
		return sequencialDocumentoCobranca;
	}

	public void setSequencialDocumentoCobranca(String sequencialDocumentoCobranca) {
		this.sequencialDocumentoCobranca = sequencialDocumentoCobranca;
	}

	public String getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getSubRelatorio() {
		return subRelatorio;
	}

	public void setSubRelatorio(String subRelatorio) {
		this.subRelatorio = subRelatorio;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getTipoCorte() {
		return tipoCorte;
	}

	public void setTipoCorte(String tipoCorte) {
		this.tipoCorte = tipoCorte;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setRelatorioVisitaCobrancaSubBean(RelatorioVisitaCobrancaSubBean relatorioVisitaCobrancaSubBean) {
		this.arrayRelatorioVisitaCobrancaSubrelatorioBean = new ArrayList();
		this.arrayRelatorioVisitaCobrancaSubrelatorioBean.add(relatorioVisitaCobrancaSubBean);
		this.arrayJRSubrelatorioBean = new JRBeanCollectionDataSource(this.arrayRelatorioVisitaCobrancaSubrelatorioBean);
	}

	public String getCategoriaCOM() {
		return categoriaCOM;
	}

	public void setCategoriaCOM(String categoriaCOM) {
		this.categoriaCOM = categoriaCOM;
	}

	public String getCategoriaIND() {
		return categoriaIND;
	}

	public void setCategoriaIND(String categoriaIND) {
		this.categoriaIND = categoriaIND;
	}

	public String getCategoriaPUB() {
		return categoriaPUB;
	}

	public void setCategoriaPUB(String categoriaPUB) {
		this.categoriaPUB = categoriaPUB;
	}

	public String getCategoriaRES() {
		return categoriaRES;
	}

	public void setCategoriaRES(String categoriaRES) {
		this.categoriaRES = categoriaRES;
	}
	
}
