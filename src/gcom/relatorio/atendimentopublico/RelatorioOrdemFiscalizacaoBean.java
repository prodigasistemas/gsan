package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Raphael Rossiter
 * @data 20/12/2007
 */
public class RelatorioOrdemFiscalizacaoBean implements RelatorioBean, Serializable {
	
	private static final long serialVersionUID = 1L;

	private JRBeanCollectionDataSource arrayJRFaturas;

	private ArrayList arrayRelatorioOrdemFiscalizacaoFaturasBean;

	private String inscricao;

	private String nomeCliente;

	private String matricula;

	private String endereco;

	private String ordemFiscalizacao;

	private String qtdeEconResidencial;

	private String qtdeEconComercial;

	private String qtdeEconIndustrial;

	private String qtdeEconPublica;

	private String qtdeEconTotal;

	private String dataEmissao;

	private String tipoConsumidor;

	private String ultimaAlteracao;

	private String grupo;

	private String sequencial;

	private String ligacaoAguaSituacao;

	private String consumoMedio;

	private String ligacaoEsgotoSituacao;

	private String consumoFixo;

	private String rotaSeqRota;

	private String dataCorte;

	private String dataSupressaoTotal;

	private String origem;

	private String ocorrencia;

	private String dataPosicaoDebito;

	private String valorDebito;
	
	private String numeroHidrometro;
	
	public RelatorioOrdemFiscalizacaoBean(){}

	public RelatorioOrdemFiscalizacaoBean(String inscricao, String nomeCliente,
			String matricula, String endereco, String ordemFiscalizacao,
			String qtdeEconResidencial, String qtdeEconComercial,
			String qtdeEconIndustrial, String qtdeEconPublica,
			String qtdeEconTotal, String dataEmissao, String tipoConsumidor,
			String ultimaAlteracao, String grupo, String sequencial,
			String ligacaoAguaSituacao, String consumoMedio,
			String ligacaoEsgotoSituacao, String consumoFixo,
			String rotaSeqRota, String dataCorte, 
			String dataSupressaoTotal, String origem, String ocorrencia,
			String dataPosicaoDebito, String valorDebito,
			Collection colecaoFaturas, String numeroHidrometro) {

		this.arrayRelatorioOrdemFiscalizacaoFaturasBean = new ArrayList();
		this.arrayRelatorioOrdemFiscalizacaoFaturasBean.addAll(colecaoFaturas);
		this.arrayJRFaturas = new JRBeanCollectionDataSource(
				this.arrayRelatorioOrdemFiscalizacaoFaturasBean);

		this.inscricao = inscricao;
		this.nomeCliente = nomeCliente;
		this.matricula = matricula;
		this.endereco = endereco;
		this.ordemFiscalizacao = ordemFiscalizacao;
		this.qtdeEconResidencial = qtdeEconResidencial;
		this.qtdeEconComercial = qtdeEconComercial;
		this.qtdeEconIndustrial = qtdeEconIndustrial;
		this.qtdeEconPublica = qtdeEconPublica;
		this.qtdeEconTotal = qtdeEconTotal;
		this.dataEmissao = dataEmissao;
		this.tipoConsumidor = tipoConsumidor;
		this.ultimaAlteracao = ultimaAlteracao;
		this.grupo = grupo;
		this.sequencial = sequencial;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.consumoMedio = consumoMedio;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.consumoFixo = consumoFixo;
		this.rotaSeqRota = rotaSeqRota;
		this.dataCorte = dataCorte;
		this.dataSupressaoTotal = dataSupressaoTotal;
		this.origem = origem;
		this.ocorrencia = ocorrencia;
		this.dataPosicaoDebito = dataPosicaoDebito;
		this.valorDebito = valorDebito;
		this.numeroHidrometro = numeroHidrometro;
	}

	public JRBeanCollectionDataSource getArrayJRFaturas() {
		return arrayJRFaturas;
	}

	public void setArrayJRFaturas(JRBeanCollectionDataSource arrayJRFaturas) {
		this.arrayJRFaturas = arrayJRFaturas;
	}

	public ArrayList getArrayRelatorioOrdemFiscalizacaoFaturasBean() {
		return arrayRelatorioOrdemFiscalizacaoFaturasBean;
	}

	public void setArrayRelatorioOrdemFiscalizacaoFaturasBean(
			ArrayList arrayRelatorioOrdemFiscalizacaoFaturasBean) {
		this.arrayRelatorioOrdemFiscalizacaoFaturasBean = arrayRelatorioOrdemFiscalizacaoFaturasBean;
	}

	public String getConsumoFixo() {
		return consumoFixo;
	}

	public void setConsumoFixo(String consumoFixo) {
		this.consumoFixo = consumoFixo;
	}

	public String getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public String getDataCorte() {
		return dataCorte;
	}

	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDataPosicaoDebito() {
		return dataPosicaoDebito;
	}

	public void setDataPosicaoDebito(String dataPosicaoDebito) {
		this.dataPosicaoDebito = dataPosicaoDebito;
	}

	public String getDataSupressaoTotal() {
		return dataSupressaoTotal;
	}

	public void setDataSupressaoTotal(String dataSupressaoTotal) {
		this.dataSupressaoTotal = dataSupressaoTotal;
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

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
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

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getOrdemFiscalizacao() {
		return ordemFiscalizacao;
	}

	public void setOrdemFiscalizacao(String ordemFiscalizacao) {
		this.ordemFiscalizacao = ordemFiscalizacao;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getQtdeEconComercial() {
		return qtdeEconComercial;
	}

	public void setQtdeEconComercial(String qtdeEconComercial) {
		this.qtdeEconComercial = qtdeEconComercial;
	}

	public String getQtdeEconIndustrial() {
		return qtdeEconIndustrial;
	}

	public void setQtdeEconIndustrial(String qtdeEconIndustrial) {
		this.qtdeEconIndustrial = qtdeEconIndustrial;
	}

	

	public String getQtdeEconPublica() {
		return qtdeEconPublica;
	}

	public void setQtdeEconPublica(String qtdeEconPublica) {
		this.qtdeEconPublica = qtdeEconPublica;
	}

	public String getQtdeEconResidencial() {
		return qtdeEconResidencial;
	}

	public void setQtdeEconResidencial(String qtdeEconResidencial) {
		this.qtdeEconResidencial = qtdeEconResidencial;
	}

	public String getQtdeEconTotal() {
		return qtdeEconTotal;
	}

	public void setQtdeEconTotal(String qtdeEconTotal) {
		this.qtdeEconTotal = qtdeEconTotal;
	}

	public String getRotaSeqRota() {
		return rotaSeqRota;
	}

	public void setRotaSeqRota(String rotaSeqRota) {
		this.rotaSeqRota = rotaSeqRota;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public String getTipoConsumidor() {
		return tipoConsumidor;
	}

	public void setTipoConsumidor(String tipoConsumidor) {
		this.tipoConsumidor = tipoConsumidor;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	
	

}
