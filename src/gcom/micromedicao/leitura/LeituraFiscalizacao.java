package gcom.micromedicao.leitura;


import gcom.cadastro.funcionario.Funcionario;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LeituraFiscalizacao extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    //private int numeroLeituraCompesa;
    
    private int numeroLeituraEmpresa;

    /** persistent field */
    private Date dataLeituraEmpresa;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private MedicaoHistorico medicaoHistorico;

    /** persistent field */
    private gcom.micromedicao.leitura.LeituraAnormalidade leituraAnormalidade;

    /** persistent field */
    private Funcionario funcionario;

    /** full constructor */
    public LeituraFiscalizacao(int numeroLeituraEmpresa,
            Date dataLeituraEmpresa, Date ultimaAlteracao,
            MedicaoHistorico medicaoHistorico,
            gcom.micromedicao.leitura.LeituraAnormalidade leituraAnormalidade,
            Funcionario funcionario) {
    	this.numeroLeituraEmpresa = numeroLeituraEmpresa;
        this.dataLeituraEmpresa = dataLeituraEmpresa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.medicaoHistorico = medicaoHistorico;
        this.leituraAnormalidade = leituraAnormalidade;
        this.funcionario = funcionario;
    }

    /** default constructor */
    public LeituraFiscalizacao() {
    }

    /** minimal constructor */
    public LeituraFiscalizacao(int numeroLeituraEmpresa,
            Date dataLeituraEmpresa,
            gcom.micromedicao.leitura.LeituraAnormalidade leituraAnormalidade,
            Funcionario funcionario) {
    	this.numeroLeituraEmpresa = numeroLeituraEmpresa;
        this.dataLeituraEmpresa = dataLeituraEmpresa;
        this.leituraAnormalidade = leituraAnormalidade;
        this.funcionario = funcionario;
    }

    /**
	 * @return Retorna o campo numeroLeituraEmpresa.
	 */
	public int getNumeroLeituraEmpresa() {
		return numeroLeituraEmpresa;
	}

	/**
	 * @param numeroLeituraEmpresa O numeroLeituraEmpresa a ser setado.
	 */
	public void setNumeroLeituraEmpresa(int numeroLeituraEmpresa) {
		this.numeroLeituraEmpresa = numeroLeituraEmpresa;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public int getNumeroLeituraCompesa() {
//        return this.numeroLeituraCompesa;
//    }
//
//    public void setNumeroLeituraCompesa(int numeroLeituraCompesa) {
//        this.numeroLeituraCompesa = numeroLeituraCompesa;
//    }

    public Date getdataLeituraEmpresa() {
        return this.dataLeituraEmpresa;
    }

    public void setdataLeituraEmpresa(Date dataLeituraEmpresa) {
        this.dataLeituraEmpresa = dataLeituraEmpresa;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public MedicaoHistorico getMedicaoHistorico() {
        return this.medicaoHistorico;
    }

    public void setMedicaoHistorico(MedicaoHistorico medicaoHistorico) {
        this.medicaoHistorico = medicaoHistorico;
    }

    public gcom.micromedicao.leitura.LeituraAnormalidade getLeituraAnormalidade() {
        return this.leituraAnormalidade;
    }

    public void setLeituraAnormalidade(
            gcom.micromedicao.leitura.LeituraAnormalidade leituraAnormalidade) {
        this.leituraAnormalidade = leituraAnormalidade;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    
    public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
    
    
    public Filtro retornaFiltro() {
		FiltroLeituraFiscalizacao filtroLeituraFiscalizacao = new FiltroLeituraFiscalizacao();
		filtroLeituraFiscalizacao.adicionarParametro(new ParametroSimples(FiltroLeituraFiscalizacao.ID,
				this.getId()));
		filtroLeituraFiscalizacao.adicionarCaminhoParaCarregamentoEntidade("medicaoHistorico");
		
		filtroLeituraFiscalizacao.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		
		filtroLeituraFiscalizacao.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidade");

		return filtroLeituraFiscalizacao;
	}


}
