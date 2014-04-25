package gcom.cadastro.atualizacaocadastralsimplificado;

import java.util.Collection;
import java.util.Date;

public class AtualizacaoCadastralSimplificadoCritica {
	private Integer id;
	private String descricao;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	private AtualizacaoCadastralSimplificadoCriticaTipo tipo;
	private Collection<AtualizacaoCadastralSimplificadoLinha> linhas;

	public final static Integer HIDROMETRO_FORA_TAMANHO_PADRAO_ABNT = new Integer(
			0);
	public final static Integer HIDROMETRO_FORA_PADRAO_ABNT = new Integer(1);
	public final static Integer IMOVEL_SEM_HIDROMETRO = new Integer(2);
	public final static Integer IMOVEL_COM_HIDROMETRO = new Integer(3);
	public final static Integer HIDROMETRO_INSTALADO_OUTRO_IMOVEL = new Integer(
			4);
	public final static Integer HIDROMETRO_CAPACIDADE_INEXISTENTE = new Integer(
			5);
	public final static Integer HIDROMETRO_ANO_FABRICACAO_INVALIDO = new Integer(
			6);
	public final static Integer HIDROMETRO_FABRICANTE_INEXISTENTE = new Integer(
			7);
	public final static Integer HIDROMETRO_SEQUENCIAIS_FABRICANTE_INVALIDOS = new Integer(
			8);
	public final static Integer IMOVEL_SUBCATEGORIA_INEXISTENTE = new Integer(
			9);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public AtualizacaoCadastralSimplificadoCriticaTipo getTipo() {
		return tipo;
	}

	public void setTipo(AtualizacaoCadastralSimplificadoCriticaTipo tipo) {
		this.tipo = tipo;
	}

	public Collection<AtualizacaoCadastralSimplificadoLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(Collection<AtualizacaoCadastralSimplificadoLinha> linhas) {
		this.linhas = linhas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtualizacaoCadastralSimplificadoCritica other = (AtualizacaoCadastralSimplificadoCritica) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	

}
