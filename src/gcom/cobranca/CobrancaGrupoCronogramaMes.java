package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaGrupoCronogramaMes extends ObjetoTransacao{
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.CobrancaGrupo cobrancaGrupo;
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroCobrancaGrupoCronogramaMes filtroCobrancaGrupoCronogramaMes = new FiltroCobrancaGrupoCronogramaMes();
		filtroCobrancaGrupoCronogramaMes. adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
		filtroCobrancaGrupoCronogramaMes. adicionarParametro(
		new ParametroSimples(FiltroCobrancaGrupoCronogramaMes.ID, this.getId()));
		
		return filtroCobrancaGrupoCronogramaMes; 
	}

    /** full constructor */
    public CobrancaGrupoCronogramaMes(int anoMesReferencia, Date ultimaAlteracao, gcom.cobranca.CobrancaGrupo cobrancaGrupo) {
        this.anoMesReferencia = anoMesReferencia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cobrancaGrupo = cobrancaGrupo;
    }

    /** default constructor */
    public CobrancaGrupoCronogramaMes() {
    }

    /** minimal constructor */
    public CobrancaGrupoCronogramaMes(int anoMesReferencia, gcom.cobranca.CobrancaGrupo cobrancaGrupo) {
        this.anoMesReferencia = anoMesReferencia;
        this.cobrancaGrupo = cobrancaGrupo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.CobrancaGrupo getCobrancaGrupo() {
        return this.cobrancaGrupo;
    }

    public void setCobrancaGrupo(gcom.cobranca.CobrancaGrupo cobrancaGrupo) {
        this.cobrancaGrupo = cobrancaGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    /**
     * Retorna o valor de mesAno
     * 
     * @return O valor de mesAno
     */
    public String getMesAno() {
        //o metodo serve para transformar o AnoMesReferencia do banco
        //em mes/Ano para demonstraçao para o usuario.
        //Ex.: 200508 para 08/2005
        String mesAno = null;

        String mes = null;
        String ano = null;

        if (this.anoMesReferencia != 0) {
            String anoMes = this.anoMesReferencia + "";

            mes = anoMes.substring(4, 6);
            ano = anoMes.substring(0, 4);
            mesAno = mes + "/" + ano;
        }
        return mesAno;
    }

}
