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
public class RelatorioVisitaCobrancaBean implements RelatorioBean {
	
	private String inscricao;
	private String nomeClienteUsuario;
	private String grupo;
	private String matricula;
	private String dataEmissao;
	private String categoriaRES;
	private String categoriaPUB;
	private String categoriaIND;
	private String categoriaCOM;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String sequencial;
	private String ordemServico;
	private String dataValidade;
	private String rota;
	private String sequencialRota;
	private String valorTotal;
	private String perfilCliente;
	private String cpfCliente;
	private String telefoneCliente;
	private String mesAno;
	private String dataVencimento;
	private String valor;

	private JRBeanCollectionDataSource arrayJRSubrelatorioBean;
	private ArrayList arrayRelatorioVisitaCobrancaSubrelatorioBean;
	private String subRelatorio;

	private String sequencialDocumentoCobranca;
	private String enderecoImovel;
	
	
	public RelatorioVisitaCobrancaBean() {
		super();
	}

	public RelatorioVisitaCobrancaBean(String inscricao, String nomeClienteUsuario, String grupo,
			String matricula, String dataEmissao, String categoriaRES, String categoriaPUB,
			String categoriaIND, String categoriaCOM, String situacaoLigacaoAgua,
			String situacaoLigacaoEsgoto, String sequencial, String ordemServico, String dataValidade,
			String rota, String sequencialRota, String valorTotal, String perfilCliente,
			String cpfCliente, String telefoneCliente, String mesAno, String dataVencimento, String valor,
			RelatorioVisitaCobrancaSubBean relatorioVisitaCobrancaSubBean, String subRelatorio,
			String sequencialDocumentoCobranca, String enderecoImovel) {
		super();
		this.inscricao = inscricao;
		this.nomeClienteUsuario = nomeClienteUsuario;
		this.grupo = grupo;
		this.matricula = matricula;
		this.dataEmissao = dataEmissao;
		this.categoriaRES = categoriaRES;
		this.categoriaPUB = categoriaPUB;
		this.categoriaIND = categoriaIND;
		this.categoriaCOM = categoriaCOM;
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
		this.sequencial = sequencial;
		this.ordemServico = ordemServico;
		this.dataValidade = dataValidade;
		this.rota = rota;
		this.sequencialRota = sequencialRota;
		this.valorTotal = valorTotal;
		this.perfilCliente = perfilCliente;
		this.cpfCliente = cpfCliente;
		this.telefoneCliente = telefoneCliente;
		this.mesAno = mesAno;
		this.dataVencimento = dataVencimento;
		this.valor = valor;

		this.arrayRelatorioVisitaCobrancaSubrelatorioBean = new ArrayList();
		this.arrayRelatorioVisitaCobrancaSubrelatorioBean.add(relatorioVisitaCobrancaSubBean);
		this.arrayJRSubrelatorioBean = new JRBeanCollectionDataSource(this.arrayRelatorioVisitaCobrancaSubrelatorioBean);
		
		this.subRelatorio = subRelatorio;
		this.sequencialDocumentoCobranca = sequencialDocumentoCobranca;
		this.enderecoImovel = enderecoImovel;
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
	public String getTelefoneCliente() {
		return telefoneCliente;
	}
	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}
	public String getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
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
	public void setRelatorioVisitaCobrancaSubBean(RelatorioVisitaCobrancaSubBean relatorioVisitaCobrancaSubBean) {
		this.arrayRelatorioVisitaCobrancaSubrelatorioBean = new ArrayList();
		this.arrayRelatorioVisitaCobrancaSubrelatorioBean.add(relatorioVisitaCobrancaSubBean);
		this.arrayJRSubrelatorioBean = new JRBeanCollectionDataSource(this.arrayRelatorioVisitaCobrancaSubrelatorioBean);
	}
	public String getSubRelatorio() {
		return subRelatorio;
	}
	public void setSubRelatorio(String subRelatorio) {
		this.subRelatorio = subRelatorio;
	}
	public String getSequencialDocumentoCobranca() {
		return sequencialDocumentoCobranca;
	}
	public void setSequencialDocumentoCobranca(String sequencialDocumentoCobranca) {
		this.sequencialDocumentoCobranca = sequencialDocumentoCobranca;
	}
	public String getEnderecoImovel() {
		return enderecoImovel;
	}
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	
}
