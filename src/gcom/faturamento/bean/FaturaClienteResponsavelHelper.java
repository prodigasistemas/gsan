package gcom.faturamento.bean;

import java.io.Serializable;
import java.util.Collection;

/** @author Hibernate CodeGenerator */
public class FaturaClienteResponsavelHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeCliente;
	private String endereco;
	private String enderecoLinha2;
	private String enderecoLinha3;
	private String tipoResponsavel;
	private String qtdeItens;
	private String dataEmissao;
	private String mesAno;
	private String primeiroVencimento;
	private String valorTotalAPagar;
	private String numeroFatura;
	
	private String valorBruto;
	private String valorImposto;

	private Collection colecaoFaturaItemClienteResponsavelHelper;
	
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String indicadorCodigoBarras;
	
	private String valorMedioTurbidez;
	private String valorMedioPh;
	private String valorMedioCor;
	private String valorMedioCloro;
	private String valorMedioFluor;
	private String valorMedioFerro;
	private String valorMedioColiformesTotais;
	private String valorMedioColiformesfecais;
	private String padraoTurbidez;
	private String padraoPh;
	private String padraoCor;
	private String padraoCloro;
	private String padraoFluor;
	private String padraoFerro;
	private String padraoColiformesTotais;
	private String padraoColiformesfecais;
	
	private String datasVencimentos;
	
	
	
	//Inicio - Usado somente no relatório RelatorioRelacaoAnaliticaFaturas
	
	private String codigoCliente;
	//Fim - Usado somente no relatório RelatorioRelacaoAnaliticaFaturas
	
	
	
	public FaturaClienteResponsavelHelper() {
	}
	
	public FaturaClienteResponsavelHelper(
			String nomeCliente,
			String endereco,
			String enderecoLinha2,
			String enderecoLinha3,
			String tipoResponsavel,
			String qtdeItens,
			String dataEmissao,
			String mesAno,
			String primeiroVencimento,
			String valorTotalAPagar,
			String datasVencimentos,
			String numeroFatura,
			Collection colecaoFaturaItemClienteResponsavelHelper,
			String representacaoNumericaCodBarraFormatada,
			String representacaoNumericaCodBarraSemDigito,
			String valorMedioTurbidez,
			String valorMedioPh,
			String valorMedioCor,
			String valorMedioCloro,
			String valorMedioFluor,
			String valorMedioFerro,
			String valorMedioColiformesTotais,
			String valorMedioColiformesfecais,
			String padraoTurbidez,
			String padraoPh,
			String padraoCor,
			String padraoCloro,
			String padraoFluor,
			String padraoFerro,
			String padraoColiformesTotais,
			String padraoColiformesfecais,
			String indicadorCodigoBarras) {
		
			this.nomeCliente = nomeCliente;
			this.endereco = endereco;
			this.enderecoLinha2 = enderecoLinha2;
			this.enderecoLinha3 = enderecoLinha3;
			this.tipoResponsavel = tipoResponsavel;
			this.qtdeItens = qtdeItens;
			this.dataEmissao = dataEmissao;
			this.mesAno = mesAno;
			this.primeiroVencimento = primeiroVencimento;
			this.valorTotalAPagar = valorTotalAPagar;
			this.numeroFatura = numeroFatura;
			this.colecaoFaturaItemClienteResponsavelHelper = colecaoFaturaItemClienteResponsavelHelper;
			this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
			this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
			this.valorMedioTurbidez = valorMedioTurbidez;
			this.valorMedioPh = valorMedioPh;
			this.valorMedioCor = valorMedioCor;
			this.valorMedioCloro = valorMedioCloro;
			this.valorMedioFluor = valorMedioFluor;
			this.valorMedioFerro = valorMedioFerro;
			this.valorMedioColiformesTotais = valorMedioColiformesTotais;
			this.valorMedioColiformesfecais = valorMedioColiformesfecais;
			this.padraoTurbidez = padraoTurbidez;
			this.padraoPh = padraoPh;
			this.padraoCor = padraoCor;
			this.padraoCloro = padraoCloro;
			this.padraoFluor = padraoFluor;
			this.padraoFerro = padraoFerro;
			this.padraoColiformesTotais = padraoColiformesTotais;
			this.padraoColiformesfecais = padraoColiformesfecais;
			this.indicadorCodigoBarras = indicadorCodigoBarras;
			this.datasVencimentos = datasVencimentos;
			
	}

	public String getDatasVencimentos() {
		return datasVencimentos;
	}

	public void setDatasVencimentos(String datasVencimentos) {
		this.datasVencimentos = datasVencimentos;
	}
	
	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEnderecoLinha2() {
		return enderecoLinha2;
	}

	public void setEnderecoLinha2(String enderecoLinha2) {
		this.enderecoLinha2 = enderecoLinha2;
	}

	public String getEnderecoLinha3() {
		return enderecoLinha3;
	}

	public void setEnderecoLinha3(String enderecoLinha3) {
		this.enderecoLinha3 = enderecoLinha3;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public String getPrimeiroVencimento() {
		return primeiroVencimento;
	}

	public void setPrimeiroVencimento(String primeiroVencimento) {
		this.primeiroVencimento = primeiroVencimento;
	}

	public String getQtdeItens() {
		return qtdeItens;
	}

	public void setQtdeItens(String qtdeItens) {
		this.qtdeItens = qtdeItens;
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

	public String getTipoResponsavel() {
		return tipoResponsavel;
	}

	public void setTipoResponsavel(String tipoResponsavel) {
		this.tipoResponsavel = tipoResponsavel;
	}

	public String getValorTotalAPagar() {
		return valorTotalAPagar;
	}

	public void setValorTotalAPagar(String valorTotalAPagar) {
		this.valorTotalAPagar = valorTotalAPagar;
	}

	
	public Collection getColecaoFaturaItemClienteResponsavelHelper() {
		return colecaoFaturaItemClienteResponsavelHelper;
	}

	public void setColecaoFaturaItemClienteResponsavelHelper(
			Collection colecaoFaturaItemClienteResponsavelHelper) {
		this.colecaoFaturaItemClienteResponsavelHelper = colecaoFaturaItemClienteResponsavelHelper;
	}

	public String getPadraoCloro() {
		return padraoCloro;
	}

	public void setPadraoCloro(String padraoCloro) {
		this.padraoCloro = padraoCloro;
	}

	public String getPadraoColiformesfecais() {
		return padraoColiformesfecais;
	}

	public void setPadraoColiformesfecais(String padraoColiformesfecais) {
		this.padraoColiformesfecais = padraoColiformesfecais;
	}

	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}

	public void setPadraoColiformesTotais(String padraoColiformesTotais) {
		this.padraoColiformesTotais = padraoColiformesTotais;
	}

	public String getPadraoCor() {
		return padraoCor;
	}

	public void setPadraoCor(String padraoCor) {
		this.padraoCor = padraoCor;
	}

	public String getPadraoFerro() {
		return padraoFerro;
	}

	public void setPadraoFerro(String padraoFerro) {
		this.padraoFerro = padraoFerro;
	}

	public String getPadraoFluor() {
		return padraoFluor;
	}

	public void setPadraoFluor(String padraoFluor) {
		this.padraoFluor = padraoFluor;
	}

	public String getPadraoPh() {
		return padraoPh;
	}

	public void setPadraoPh(String padraoPh) {
		this.padraoPh = padraoPh;
	}

	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}

	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}

	public String getValorMedioCloro() {
		return valorMedioCloro;
	}

	public void setValorMedioCloro(String valorMedioCloro) {
		this.valorMedioCloro = valorMedioCloro;
	}

	public String getValorMedioColiformesfecais() {
		return valorMedioColiformesfecais;
	}

	public void setValorMedioColiformesfecais(String valorMedioColiformesfecais) {
		this.valorMedioColiformesfecais = valorMedioColiformesfecais;
	}

	public String getValorMedioColiformesTotais() {
		return valorMedioColiformesTotais;
	}

	public void setValorMedioColiformesTotais(String valorMedioColiformesTotais) {
		this.valorMedioColiformesTotais = valorMedioColiformesTotais;
	}

	public String getValorMedioCor() {
		return valorMedioCor;
	}

	public void setValorMedioCor(String valorMedioCor) {
		this.valorMedioCor = valorMedioCor;
	}

	public String getValorMedioFerro() {
		return valorMedioFerro;
	}

	public void setValorMedioFerro(String valorMedioFerro) {
		this.valorMedioFerro = valorMedioFerro;
	}

	public String getValorMedioFluor() {
		return valorMedioFluor;
	}

	public void setValorMedioFluor(String valorMedioFluor) {
		this.valorMedioFluor = valorMedioFluor;
	}

	public String getValorMedioPh() {
		return valorMedioPh;
	}

	public void setValorMedioPh(String valorMedioPh) {
		this.valorMedioPh = valorMedioPh;
	}

	public String getValorMedioTurbidez() {
		return valorMedioTurbidez;
	}

	public void setValorMedioTurbidez(String valorMedioTurbidez) {
		this.valorMedioTurbidez = valorMedioTurbidez;
	}

	public String getIndicadorCodigoBarras() {
		return indicadorCodigoBarras;
	}

	public void setIndicadorCodigoBarras(String indicadorCodigoBarras) {
		this.indicadorCodigoBarras = indicadorCodigoBarras;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(String valorBruto) {
		this.valorBruto = valorBruto;
	}

	public String getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(String valorImposto) {
		this.valorImposto = valorImposto;
	}


	
}
