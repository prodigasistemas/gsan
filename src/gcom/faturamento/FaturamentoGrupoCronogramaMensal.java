package gcom.faturamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FaturamentoGrupoCronogramaMensal extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

    private Integer id;
    private int anoMesReferencia;
    private Date ultimaAlteracao;
    private FaturamentoGrupo faturamentoGrupo;
    private Usuario usuario;
    
    @SuppressWarnings("rawtypes")
	private Set faturamentoAtividadeCronogramas;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroFaturamentoGrupoCronogramaMensal filtroFaturamentoGrupoCronogramaMensal = new FiltroFaturamentoGrupoCronogramaMensal();
		filtroFaturamentoGrupoCronogramaMensal. adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroFaturamentoGrupoCronogramaMensal. adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroFaturamentoGrupoCronogramaMensal. adicionarParametro(
		new ParametroSimples(FiltroFaturamentoGrupoCronogramaMensal.ID, this.getId()));
		
		return filtroFaturamentoGrupoCronogramaMensal; 
	}
    
    public FaturamentoGrupoCronogramaMensal(Integer anoMesReferencia, Date ultimaAlteracao, gcom.faturamento.FaturamentoGrupo faturamentoGrupo, Usuario usuario) {
        this.anoMesReferencia = anoMesReferencia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoGrupo = faturamentoGrupo;
        this.usuario = usuario;
    }

    public FaturamentoGrupoCronogramaMensal() {
    }

    public FaturamentoGrupoCronogramaMensal(Integer anoMesReferencia, gcom.faturamento.FaturamentoGrupo faturamentoGrupo) {
        this.anoMesReferencia = anoMesReferencia;
        this.faturamentoGrupo = faturamentoGrupo;
    }
 
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(gcom.faturamento.FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }
    
	@SuppressWarnings("rawtypes")
	public Set getFaturamentoAtividadeCronogramas() {
		return faturamentoAtividadeCronogramas;
	}

	@SuppressWarnings("rawtypes")
	public void setFaturamentoAtividadeCronogramas(
			Set faturamentoAtividadeCronogramas) {
		if (faturamentoAtividadeCronogramas != null && !faturamentoAtividadeCronogramas.isEmpty()) {
			Iterator it = faturamentoAtividadeCronogramas.iterator();
			while (it.hasNext()) {
				FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) it.next();
				if ( faturamentoAtividadeCronograma.getFaturamentoAtividade() != null) 
					 faturamentoAtividadeCronograma.getFaturamentoAtividade().getFaturamentoAtividadePrecedente();
			}
		}
		this.faturamentoAtividadeCronogramas = faturamentoAtividadeCronogramas;
	}

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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

}
