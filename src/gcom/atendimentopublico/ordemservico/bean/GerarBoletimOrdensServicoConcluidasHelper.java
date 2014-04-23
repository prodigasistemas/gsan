package gcom.atendimentopublico.ordemservico.bean;

import java.util.Date;



/**
 * [UC0765] Gerar Boletim Ordens Servico Concluidas
 * 
 * @author Ivan Sérgio
 * @created 29/04/2008
 * 
 */
public class GerarBoletimOrdensServicoConcluidasHelper {
	private String anoMesReferenciaBoletim;
	private Integer idOrdemServico;
	private short codigoFiscalizacao;
	private short indicadorTrocaProtecao;
	private short indicadorTrocaRegistro;
	private Integer idLocalInstalacaoHidrometro;
	private Integer idTipoServico;
	private Date dataFiscalizacao1;
	private Date dataFiscalizacao2;
	private Date dataFiscalizacao3;	
	
	public Date getDataFiscalizacao1() {
		return dataFiscalizacao1;
	}
	public void setDataFiscalizacao1(Date dataFiscalizacao1) {
		this.dataFiscalizacao1 = dataFiscalizacao1;
	}
	public Date getDataFiscalizacao2() {
		return dataFiscalizacao2;
	}
	public void setDataFiscalizacao2(Date dataFiscalizacao2) {
		this.dataFiscalizacao2 = dataFiscalizacao2;
	}
	public Date getDataFiscalizacao3() {
		return dataFiscalizacao3;
	}
	public void setDataFiscalizacao3(Date dataFiscalizacao3) {
		this.dataFiscalizacao3 = dataFiscalizacao3;
	}
	public String getAnoMesReferenciaBoletim() {
		return anoMesReferenciaBoletim;
	}
	public void setAnoMesReferenciaBoletim(String anoMesReferenciaBoletim) {
		this.anoMesReferenciaBoletim = anoMesReferenciaBoletim;
	}
	public short getCodigoFiscalizacao() {
		return codigoFiscalizacao;
	}
	public void setCodigoFiscalizacao(short codigoFiscalizacao) {
		this.codigoFiscalizacao = codigoFiscalizacao;
	}
	public Integer getIdLocalInstalacaoHidrometro() {
		return idLocalInstalacaoHidrometro;
	}
	public void setIdLocalInstalacaoHidrometro(Integer idLocalInstalacaoHidrometro) {
		this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
	}
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public Integer getIdTipoServico() {
		return idTipoServico;
	}
	public void setIdTipoServico(Integer idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	public short getIndicadorTrocaProtecao() {
		return indicadorTrocaProtecao;
	}
	public void setIndicadorTrocaProtecao(short indicadorTrocaProtecao) {
		this.indicadorTrocaProtecao = indicadorTrocaProtecao;
	}
	public short getIndicadorTrocaRegistro() {
		return indicadorTrocaRegistro;
	}
	public void setIndicadorTrocaRegistro(short indicadorTrocaRegistro) {
		this.indicadorTrocaRegistro = indicadorTrocaRegistro;
	}
}
