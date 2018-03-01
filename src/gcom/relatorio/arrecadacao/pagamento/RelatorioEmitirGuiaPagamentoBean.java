package gcom.relatorio.arrecadacao.pagamento;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 * [UC0379] Emitir Guia de Pagamento
 * @author Vivianne Sousa, Mariana Victor
 * @date 22/09/2006, 02/03/2011
 */
public class RelatorioEmitirGuiaPagamentoBean implements RelatorioBean {

	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorioEmitirGuiaPagamentoDetailBean;
	
	private String matricula ;
	private String nomeCliente ;
	private String dataVencimento ;
	private String inscricao ; 
	private String enderecoImovel ; 
	private String enderecoClienteResponsavel ;
	private String representacaoNumericaCodBarraFormatada ;
	private String representacaoNumericaCodBarraSemDigito ;
	private String dataValidade ;
	private String valorDebito;
	private String idGuiaPagamento;
	private String observacao;
	private String cpfCnpjCliente;
	
	private String mensagemParcelamento;
	private Boolean exibirDetalhesParcelamento;
	private Boolean exibirSemDetalhesParcelamento;
	
	/* Ficha de Compensação */
	private String idImovel;
	private String nossoNumero;
	private String sacadoParte01;
	private String sacadoParte02;
	private String enderecoImovelSacado;
	private JRBeanCollectionDataSource arrayJRDetailSub;
	
	private String subRelatorio;
	
	
	public RelatorioEmitirGuiaPagamentoBean( Collection colecaoDetail,
											 String matricula,
											 String nomeCliente,
											 String dataVencimento,
											 String inscricao,
											 String enderecoImovel, 
											 String enderecoClienteResponsavel,
											 String representacaoNumericaCodBarraFormatada,
											 String representacaoNumericaCodBarraSemDigito,
											 String dataValidade,
											 String valorDebito,
											 String idGuiaPagamento,
											 String observacao,
											 String cpfCnpjCliente,
											 String subRelatorio) {
		
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = new ArrayList();
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioEmitirGuiaPagamentoDetailBean);
		
		this.matricula = matricula;
		this.nomeCliente = nomeCliente;
		this.dataVencimento = dataVencimento;
		this.inscricao = inscricao;
		this.enderecoImovel = enderecoImovel;
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.dataValidade = dataValidade;
		this.valorDebito = valorDebito;
		this.idGuiaPagamento = idGuiaPagamento;
		this.observacao = observacao;
		this.cpfCnpjCliente = cpfCnpjCliente;
		this.subRelatorio = subRelatorio;
		
	}
	

	public RelatorioEmitirGuiaPagamentoBean( Collection colecaoDetail,
											 String matricula,
											 String nomeCliente,
											 String dataVencimento,
											 String inscricao,
											 String enderecoImovel, 
											 String enderecoClienteResponsavel,
											 String representacaoNumericaCodBarraFormatada,
											 String representacaoNumericaCodBarraSemDigito,
											 String dataValidade,
											 String valorDebito,
											 String idGuiaPagamento,
											 String observacao,
											 String cpfCnpjCliente,
											 String idImovel,
											 String nossoNumero,
											 String sacadoParte01,
											 String sacadoParte02,
											 String subRelatorio,
											 Collection colecaoDetailSub,
											 String mensagemParcelamento, 
											 Boolean ehParcelamento) {
		
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = new ArrayList();
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioEmitirGuiaPagamentoDetailBean);
		
		this.matricula = matricula;
		this.nomeCliente = nomeCliente;
		this.dataVencimento = dataVencimento;
		this.inscricao = inscricao;
		this.enderecoImovel = enderecoImovel;
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.dataValidade = dataValidade;
		this.valorDebito = valorDebito;
		this.idGuiaPagamento = idGuiaPagamento;
		this.observacao = observacao;
		this.cpfCnpjCliente = cpfCnpjCliente;
		this.idImovel = idImovel;
		this.nossoNumero = nossoNumero;
		this.sacadoParte01 = sacadoParte01;
		this.sacadoParte02 = sacadoParte02;
		this.subRelatorio = subRelatorio;
		this.mensagemParcelamento = mensagemParcelamento;
		this.exibirDetalhesParcelamento = ehParcelamento;
		this.exibirSemDetalhesParcelamento = !ehParcelamento;

		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = new ArrayList();
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean.addAll(colecaoDetailSub);
		this.arrayJRDetailSub = new JRBeanCollectionDataSource(
				this.arrayRelatorioEmitirGuiaPagamentoDetailBean);
	}

	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	public ArrayList getArrayRelatorioEmitirGuiaPagamentoDetailBean() {
		return arrayRelatorioEmitirGuiaPagamentoDetailBean;
	}

	public void setArrayRelatorioEmitirGuiaPagamentoDetailBean(
			ArrayList arrayRelatorioEmitirGuiaPagamentoDetailBean) {
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = arrayRelatorioEmitirGuiaPagamentoDetailBean;
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

	public String getEnderecoClienteResponsavel() {
		return enderecoClienteResponsavel;
	}

	public void setEnderecoClienteResponsavel(String enderecoClienteResponsavel) {
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getEnderecoImovelSacado() {
		return enderecoImovelSacado;
	}

	public void setEnderecoImovelSacado(String enderecoImovelSacado) {
		this.enderecoImovelSacado = enderecoImovelSacado;
	}

	public String getMensagemParcelamento() {
		return mensagemParcelamento;
	}

	public void setMensagemParcelamento(String mensagemParcelamento) {
		this.mensagemParcelamento = mensagemParcelamento;
	}

	public Boolean getExibirDetalhesParcelamento() {
		return exibirDetalhesParcelamento;
	}

	public void setExibirDetalhesParcelamento(Boolean exibirDetalhesParcelamento) {
		this.exibirDetalhesParcelamento = exibirDetalhesParcelamento;
	}

	public Boolean getExibirSemDetalhesParcelamento() {
		return exibirSemDetalhesParcelamento;
	}

	public void setExibirSemDetalhesParcelamento(Boolean exibirSemDetalhesParcelamento) {
		this.exibirSemDetalhesParcelamento = exibirSemDetalhesParcelamento;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getSacadoParte01() {
		return sacadoParte01;
	}

	public void setSacadoParte01(String sacadoParte01) {
		this.sacadoParte01 = sacadoParte01;
	}

	public String getSacadoParte02() {
		return sacadoParte02;
	}

	public void setSacadoParte02(String sacadoParte02) {
		this.sacadoParte02 = sacadoParte02;
	}

	public String getSubRelatorio() {
		return subRelatorio;
	}

	public void setSubRelatorio(String subRelatorio) {
		this.subRelatorio = subRelatorio;
	}

	public JRBeanCollectionDataSource getArrayJRDetailSub() {
		return arrayJRDetailSub;
	}

	public void setArrayJRDetailSub(JRBeanCollectionDataSource arrayJRDetailSub) {
		this.arrayJRDetailSub = arrayJRDetailSub;
	}

}
