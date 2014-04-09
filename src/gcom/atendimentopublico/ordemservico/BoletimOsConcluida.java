package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.localidade.Localidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class BoletimOsConcluida implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferenciaBoletim;

    /** persistent field */
    private short codigoFiscalizacao;

    /** nullable persistent field */
    private Date dataFiscalizacao;

    /** nullable persistent field */
    private Date dataEncerramentoBoletim;

    /** nullable persistent field */
    private Short indicadorTrocaProtecaoHidrometro;

    /** nullable persistent field */
    private Short indicadorTrocaRegistroHidrometro;
    
    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private HidrometroLocalInstalacao hidrometroLocalInstalacao;

    /** nullable persistent field */
    private OrdemServico ordemServico;
    
    private Set dataFiscalizacaoOsSeletivas;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Usuario usuario;

    /** full constructor */
    public BoletimOsConcluida(Integer id, int anoMesReferenciaBoletim, short codigoFiscalizacao, Date dataFiscalizacao, Date dataEncerramentoBoletim, Short indicadorTrocaProtecaoHidrometro, Short indicadorTrocaRegistroHidrometro, HidrometroLocalInstalacao hidrometroLocalInstalacao, OrdemServico ordemServico, Localidade localidade, Usuario usuario) {
        this.id = id;
        this.anoMesReferenciaBoletim = anoMesReferenciaBoletim;
        this.codigoFiscalizacao = codigoFiscalizacao;
        this.dataFiscalizacao = dataFiscalizacao;
        this.dataEncerramentoBoletim = dataEncerramentoBoletim;
        this.indicadorTrocaProtecaoHidrometro = indicadorTrocaProtecaoHidrometro;
        this.indicadorTrocaRegistroHidrometro = indicadorTrocaRegistroHidrometro;
        this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
        this.ordemServico = ordemServico;
        this.localidade = localidade;
        this.usuario = usuario;
    }

    /** default constructor */
    public BoletimOsConcluida() {
    }

    /** minimal constructor */
    public BoletimOsConcluida(Integer id, int anoMesReferenciaBoletim, short codigoFiscalizacao, Localidade localidade, Usuario usuario) {
        this.id = id;
        this.anoMesReferenciaBoletim = anoMesReferenciaBoletim;
        this.codigoFiscalizacao = codigoFiscalizacao;
        this.localidade = localidade;
        this.usuario = usuario;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public int getAnoMesReferenciaBoletim() {
		return anoMesReferenciaBoletim;
	}

	public void setAnoMesReferenciaBoletim(int anoMesReferenciaBoletim) {
		this.anoMesReferenciaBoletim = anoMesReferenciaBoletim;
	}

	public short getCodigoFiscalizacao() {
		return codigoFiscalizacao;
	}

	public void setCodigoFiscalizacao(short codigoFiscalizacao) {
		this.codigoFiscalizacao = codigoFiscalizacao;
	}

	public Date getDataEncerramentoBoletim() {
		return dataEncerramentoBoletim;
	}

	public void setDataEncerramentoBoletim(Date dataEncerramentoBoletim) {
		this.dataEncerramentoBoletim = dataEncerramentoBoletim;
	}

	public Date getDataFiscalizacao() {
		return dataFiscalizacao;
	}

	public void setDataFiscalizacao(Date dataFiscalizacao) {
		this.dataFiscalizacao = dataFiscalizacao;
	}

	public Set getDataFiscalizacaoOsSeletivas() {
		return dataFiscalizacaoOsSeletivas;
	}

	public void setDataFiscalizacaoOsSeletivas(Set dataFiscalizacaoOsSeletivas) {
		this.dataFiscalizacaoOsSeletivas = dataFiscalizacaoOsSeletivas;
	}

	public HidrometroLocalInstalacao getHidrometroLocalInstalacao() {
		return hidrometroLocalInstalacao;
	}

	public void setHidrometroLocalInstalacao(
			HidrometroLocalInstalacao hidrometroLocalInstalacao) {
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorTrocaProtecaoHidrometro() {
		return indicadorTrocaProtecaoHidrometro;
	}

	public void setIndicadorTrocaProtecaoHidrometro(
			Short indicadorTrocaProtecaoHidrometro) {
		this.indicadorTrocaProtecaoHidrometro = indicadorTrocaProtecaoHidrometro;
	}

	public Short getIndicadorTrocaRegistroHidrometro() {
		return indicadorTrocaRegistroHidrometro;
	}

	public void setIndicadorTrocaRegistroHidrometro(
			Short indicadorTrocaRegistroHidrometro) {
		this.indicadorTrocaRegistroHidrometro = indicadorTrocaRegistroHidrometro;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
