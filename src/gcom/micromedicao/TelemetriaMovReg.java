package gcom.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.micromedicao.medicao.MedicaoTipo;

import java.io.Serializable;
import java.util.Date;

public class TelemetriaMovReg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Date dataLeitura;
	
	private String inscricao;
	
	private Integer leitura;
	
	private Integer numeroHidrometro;
	
	private Short indicadorProcessado;
	
	private TelemetriaMov telemetriaMov;
	
	private Imovel imovel;
	
	private MedicaoTipo medicaoTipo;
	
	private Date ultimaAlteracao;
	
	private byte[] descricaoErro;
	
	public TelemetriaMovReg(){}

	public TelemetriaMovReg(Integer id, Date dataLeitura, String inscricao, Integer leitura, Integer numeroHidrometro, Short indicadorProcessado, TelemetriaMov telemetriaMov, Imovel imovel, MedicaoTipo medicaoTipo, Date ultimaAlteracao) {
		super();
		
		this.id = id;
		this.dataLeitura = dataLeitura;
		this.inscricao = inscricao;
		this.leitura = leitura;
		this.numeroHidrometro = numeroHidrometro;
		this.indicadorProcessado = indicadorProcessado;
		this.telemetriaMov = telemetriaMov;
		this.imovel = imovel;
		this.medicaoTipo = medicaoTipo;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Date getDataLeitura() {
		return dataLeitura;
	}

	public void setDataLeitura(Date dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Short getIndicadorProcessado() {
		return indicadorProcessado;
	}

	public void setIndicadorProcessado(Short indicadorProcessado) {
		this.indicadorProcessado = indicadorProcessado;
	}

	public Integer getLeitura() {
		return leitura;
	}

	public void setLeitura(Integer leitura) {
		this.leitura = leitura;
	}

	public MedicaoTipo getMedicaoTipo() {
		return medicaoTipo;
	}

	public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}

	public Integer getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(Integer numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public TelemetriaMov getTelemetriaMov() {
		return telemetriaMov;
	}

	public void setTelemetriaMov(TelemetriaMov telemetriaMov) {
		this.telemetriaMov = telemetriaMov;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public byte[] getDescricaoErro() {
		return descricaoErro;
	}

	public void setDescricaoErro(byte[] descricaoErro) {
		this.descricaoErro = descricaoErro;
	}


}
