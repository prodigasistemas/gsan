package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0482] Emitir 2ª Via de Conta
 * @author Vivianne Sousa
 * @date 15/09/2006
 */
public class RelatorioContaBean implements RelatorioBean {
	
	private JRBeanCollectionDataSource arrayJRConsumosAnteriores;
	private ArrayList<RelatorioContaConsumosAnterioresBean> arrayRelatorioContaConsumosAnterioresBean;
	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList<RelatorioContaDetailBean> arrayRelatorioContaDetailBean;
	
	private Integer idConta;
	private String categoria;
	private String tarifa;
	private String aguaEsgoto;
	private String idImovel;
	private String nomeCliente;
	private String cpfCnpj;
	private BigDecimal percentualEsgoto;
	private String endereco;
	private String inscricao;
	private Short rota;
	private Integer sequencialRota;
	private Short quantidadeEconomias;
	private Date dataEmissao;
	private Date dataVencimento;
	private String mesAnoConta;
	private BigDecimal valorConta;
	
	private String numeroHidrometro;
	private Date dataInstalacao;
	private Integer leituraAnterior;
	private Integer leituraAtual;
	private Date dataLeituraAnterior;
	private Date dataLeituraAtual;
	private Integer diasConsumo;
	private Integer consumo;
	private String anormalidadeLeitura;
	private Integer leiturista;
	private String mensagem1;
	private String mensagem2;
	private String mensagem3;
	private BigDecimal multa;
	private BigDecimal encargos;
	private BigDecimal turbidez;
	private BigDecimal cloro;
	private BigDecimal cor;
	private BigDecimal ph;
	private BigDecimal alcalinidade;
	private String coliformesTotais;
	private String coliformesFecais;
	private String mediaConsumo;
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String indicadorDebitoConta;
	private String indicadorNaoGeraCodigoDeBarras;
	
	public RelatorioContaBean(Collection<RelatorioContaDetailBean> colecaoDetail, Collection<RelatorioContaConsumosAnterioresBean> colecaoConsumosAnteriores) {
		this.arrayRelatorioContaDetailBean = new ArrayList<RelatorioContaDetailBean>();
		this.arrayRelatorioContaDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(this.arrayRelatorioContaDetailBean);
		this.arrayRelatorioContaConsumosAnterioresBean = new ArrayList<RelatorioContaConsumosAnterioresBean>();
		this.arrayRelatorioContaConsumosAnterioresBean.addAll(colecaoConsumosAnteriores);
		this.arrayJRConsumosAnteriores = new JRBeanCollectionDataSource(this.arrayRelatorioContaConsumosAnterioresBean);
	}
	
	/**
	 * @return Retorna o campo aguaEsgoto.
	 */
	public String getAguaEsgoto() {
		return aguaEsgoto;
	}
	/**
	 * @param aguaEsgoto O aguaEsgoto a ser setado.
	 */
	public void setAguaEsgoto(String aguaEsgoto) {
		this.aguaEsgoto = aguaEsgoto;
	}
	/**
	 * @return Retorna o campo arrayJRConsumosAnteriores.
	 */
	public JRBeanCollectionDataSource getArrayJRConsumosAnteriores() {
		return arrayJRConsumosAnteriores;
	}
	/**
	 * @param arrayJRConsumosAnteriores O arrayJRConsumosAnteriores a ser setado.
	 */
	public void setArrayJRConsumosAnteriores(
			JRBeanCollectionDataSource arrayJRConsumosAnteriores) {
		this.arrayJRConsumosAnteriores = arrayJRConsumosAnteriores;
	}
	/**
	 * @return Retorna o campo arrayJRDetail.
	 */
	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}
	/**
	 * @param arrayJRDetail O arrayJRDetail a ser setado.
	 */
	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}
	/**
	 * @return Retorna o campo arrayRelatorioContaConsumosAnterioresBean.
	 */
	public ArrayList<RelatorioContaConsumosAnterioresBean> getArrayRelatorioContaConsumosAnterioresBean() {
		return arrayRelatorioContaConsumosAnterioresBean;
	}
	/**
	 * @param arrayRelatorioContaConsumosAnterioresBean O arrayRelatorioContaConsumosAnterioresBean a ser setado.
	 */
	public void setArrayRelatorioContaConsumosAnterioresBean(
			ArrayList<RelatorioContaConsumosAnterioresBean> arrayRelatorioContaConsumosAnterioresBean) {
		this.arrayRelatorioContaConsumosAnterioresBean = arrayRelatorioContaConsumosAnterioresBean;
	}
	/**
	 * @return Retorna o campo arrayRelatorioContaDetailBean.
	 */
	public ArrayList<RelatorioContaDetailBean> getArrayRelatorioContaDetailBean() {
		return arrayRelatorioContaDetailBean;
	}
	/**
	 * @param arrayRelatorioContaDetailBean O arrayRelatorioContaDetailBean a ser setado.
	 */
	public void setArrayRelatorioContaDetailBean(ArrayList<RelatorioContaDetailBean> arrayRelatorioContaDetailBean) {
		this.arrayRelatorioContaDetailBean = arrayRelatorioContaDetailBean;
	}
	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return Retorna o campo cpfCnpj.
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	/**
	 * @param cpfCnpj O cpfCnpj a ser setado.
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	/**
	 * @return Retorna o campo dataEmissao.
	 */
	public Date getDataEmissao() {
		return dataEmissao;
	}
	/**
	 * @param dataEmissao O dataEmissao a ser setado.
	 */
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	/**
	 * @return Retorna o campo dataInstalacao.
	 */
	public Date getDataInstalacao() {
		return dataInstalacao;
	}
	/**
	 * @param dataInstalacao O dataInstalacao a ser setado.
	 */
	public void setDataInstalacao(Date dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}
	/**
	 * @return Retorna o campo dataLeituraAnterior.
	 */
	public Date getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}
	/**
	 * @param dataLeituraAnterior O dataLeituraAnterior a ser setado.
	 */
	public void setDataLeituraAnterior(Date dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}
	/**
	 * @return Retorna o campo dataLeituraAtual.
	 */
	public Date getDataLeituraAtual() {
		return dataLeituraAtual;
	}
	/**
	 * @param dataLeituraAtual O dataLeituraAtual a ser setado.
	 */
	public void setDataLeituraAtual(Date dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}
	/**
	 * @return Retorna o campo dataVencimento.
	 */
	public Date getDataVencimento() {
		return dataVencimento;
	}
	/**
	 * @param dataVencimento O dataVencimento a ser setado.
	 */
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	/**
	 * @return Retorna o campo diasConsumo.
	 */
	public Integer getDiasConsumo() {
		return diasConsumo;
	}
	/**
	 * @param diasConsumo O diasConsumo a ser setado.
	 */
	public void setDiasConsumo(Integer diasConsumo) {
		this.diasConsumo = diasConsumo;
	}
	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * @return Retorna o campo anormalidadeLeitura.
	 */
	public String getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}
	/**
	 * @param anormalidadeLeitura O anormalidadeLeitura a ser setado.
	 */
	public void setAnormalidadeLeitura(String anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}
	/**
	 * @return Retorna o campo cloro.
	 */
	public BigDecimal getCloro() {
		return cloro;
	}
	/**
	 * @param cloro O cloro a ser setado.
	 */
	public void setCloro(BigDecimal cloro) {
		this.cloro = cloro;
	}
	/**
	 * @return Retorna o campo consumo.
	 */
	public Integer getConsumo() {
		return consumo;
	}
	/**
	 * @param consumo O consumo a ser setado.
	 */
	public void setConsumo(Integer consumo) {
		this.consumo = consumo;
	}
	/**
	 * @return Retorna o campo encargos.
	 */
	public BigDecimal getEncargos() {
		return encargos;
	}
	/**
	 * @param encargos O encargos a ser setado.
	 */
	public void setEncargos(BigDecimal encargos) {
		this.encargos = encargos;
	}
	/**
	 * @return Retorna o campo leiturista.
	 */
	public Integer getLeiturista() {
		return leiturista;
	}
	/**
	 * @param leiturista O leiturista a ser setado.
	 */
	public void setLeiturista(Integer leiturista) {
		this.leiturista = leiturista;
	}
	/**
	 * @return Retorna o campo mediaConsumo.
	 */
	public String getMediaConsumo() {
		return mediaConsumo;
	}
	/**
	 * @param mediaConsumo O mediaConsumo a ser setado.
	 */
	public void setMediaConsumo(String mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}
	/**
	 * @return Retorna o campo mensagem1.
	 */
	public String getMensagem1() {
		return mensagem1;
	}
	/**
	 * @param mensagem1 O mensagem1 a ser setado.
	 */
	public void setMensagem1(String mensagem1) {
		this.mensagem1 = mensagem1;
	}
	/**
	 * @return Retorna o campo mensagem2.
	 */
	public String getMensagem2() {
		return mensagem2;
	}
	/**
	 * @param mensagem2 O mensagem2 a ser setado.
	 */
	public void setMensagem2(String mensagem2) {
		this.mensagem2 = mensagem2;
	}
	/**
	 * @return Retorna o campo mensagem3.
	 */
	public String getMensagem3() {
		return mensagem3;
	}
	/**
	 * @param mensagem3 O mensagem3 a ser setado.
	 */
	public void setMensagem3(String mensagem3) {
		this.mensagem3 = mensagem3;
	}
	/**
	 * @return Retorna o campo multa.
	 */
	public BigDecimal getMulta() {
		return multa;
	}
	/**
	 * @param multa O multa a ser setado.
	 */
	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}
	/**
	 * @return Retorna o campo turbidez.
	 */
	public BigDecimal getTurbidez() {
		return turbidez;
	}
	/**
	 * @param turbidez O turbidez a ser setado.
	 */
	public void setTurbidez(BigDecimal turbidez) {
		this.turbidez = turbidez;
	}
	/**
	 * @return Retorna o campo idConta.
	 */
	public Integer getIdConta() {
		return idConta;
	}
	/**
	 * @param idConta O idConta a ser setado.
	 */
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}
	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	/**
	 * @return Retorna o campo leituraAnterior.
	 */
	public Integer getLeituraAnterior() {
		return leituraAnterior;
	}
	/**
	 * @param leituraAnterior O leituraAnterior a ser setado.
	 */
	public void setLeituraAnterior(Integer leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}
	/**
	 * @return Retorna o campo leituraAtual.
	 */
	public Integer getLeituraAtual() {
		return leituraAtual;
	}
	/**
	 * @param leituraAtual O leituraAtual a ser setado.
	 */
	public void setLeituraAtual(Integer leituraAtual) {
		this.leituraAtual = leituraAtual;
	}
	/**
	 * @return Retorna o campo mesAnoConta.
	 */
	public String getMesAnoConta() {
		return mesAnoConta;
	}
	/**
	 * @param mesAnoConta O mesAnoConta a ser setado.
	 */
	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}
	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Retorna o campo numeroHidrometro.
	 */
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	/**
	 * @param numeroHidrometro O numeroHidrometro a ser setado.
	 */
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	/**
	 * @return Retorna o campo percentualEsgoto.
	 */
	public BigDecimal getPercentualEsgoto() {
		return percentualEsgoto;
	}
	/**
	 * @param percentualEsgoto O percentualEsgoto a ser setado.
	 */
	public void setPercentualEsgoto(BigDecimal percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}
	/**
	 * @return Retorna o campo quantidadeEconomias.
	 */
	public Short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	/**
	 * @param quantidadeEconomias O quantidadeEconomias a ser setado.
	 */
	public void setQuantidadeEconomias(Short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}
	/**
	 * @return Retorna o campo rota.
	 */
	public Short getRota() {
		return rota;
	}
	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(Short rota) {
		this.rota = rota;
	}
	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public Integer getSequencialRota() {
		return sequencialRota;
	}
	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	/**
	 * @return Retorna o campo tarifa.
	 */
	public String getTarifa() {
		return tarifa;
	}
	/**
	 * @param tarifa O tarifa a ser setado.
	 */
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}
	/**
	 * @return Retorna o campo valorConta.
	 */
	public BigDecimal getValorConta() {
		return valorConta;
	}
	/**
	 * @param valorConta O valorConta a ser setado.
	 */
	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}
	
	

	public String getColiformesFecais() {
		return coliformesFecais;
	}

	public void setColiformesFecais(String coliformesFecais) {
		this.coliformesFecais = coliformesFecais;
	}

	public String getColiformesTotais() {
		return coliformesTotais;
	}

	public void setColiformesTotais(String coliformesTotais) {
		this.coliformesTotais = coliformesTotais;
	}

	/**
	 * @return Retorna o campo cor.
	 */
	public BigDecimal getCor() {
		return cor;
	}

	/**
	 * @param cor O cor a ser setado.
	 */
	public void setCor(BigDecimal cor) {
		this.cor = cor;
	}

	/**
	 * @return Retorna o campo ph.
	 */
	public BigDecimal getPh() {
		return ph;
	}

	/**
	 * @param ph O ph a ser setado.
	 */
	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}

	/**
	 * @return Retorna o campo representacaoNumericaCodBarraFormatada.
	 */
	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	/**
	 * @param representacaoNumericaCodBarraFormatada O representacaoNumericaCodBarraFormatada a ser setado.
	 */
	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	/**
	 * @return Retorna o campo representacaoNumericaCodBarraSemDigito.
	 */
	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	/**
	 * @param representacaoNumericaCodBarraSemDigito O representacaoNumericaCodBarraSemDigito a ser setado.
	 */
	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public BigDecimal getAlcalinidade() {
		return alcalinidade;
	}

	public void setAlcalinidade(BigDecimal alcalinidade) {
		this.alcalinidade = alcalinidade;
	}

	public String getIndicadorDebitoConta() {
		return indicadorDebitoConta;
	}

	public void setIndicadorDebitoConta(String indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}

	public String getIndicadorNaoGeraCodigoDeBarras() {
		return indicadorNaoGeraCodigoDeBarras;
	}

	public void setIndicadorNaoGeraCodigoDeBarras(
			String indicadorNaoGeraCodigoDeBarras) {
		this.indicadorNaoGeraCodigoDeBarras = indicadorNaoGeraCodigoDeBarras;
	}
	
	
	
}
