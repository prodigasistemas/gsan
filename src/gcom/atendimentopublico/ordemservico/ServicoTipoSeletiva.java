package gcom.atendimentopublico.ordemservico;

import java.util.Date;

public class ServicoTipoSeletiva {
	
	public final static int CORTE_SELETIVO = 1005;
	
	private ServicoTipo servicoTipo;

	private String codigoConstante;

	private String descricao;

	private Date ultimaAlteracao;

	public String getCodigoConstante() {
		return codigoConstante;
	}

	public void setCodigoConstante(String codigoConstante) {
		this.codigoConstante = codigoConstante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	
}
