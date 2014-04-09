package gcom.cadastro.atualizacaocadastralsimplificado;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class AtualizacaoCadastralSimplificado {

	private Integer id;
	private String nome;
	private String comentario;
	private AtualizacaoCadastralSimplificadoBinario binario;
	private Usuario usuario;
	private Integer qtdeTotalImoveis;
	private Integer qtdeImoveisComHidrometro;
	private Integer qtdeImoveisComHidrometroAtualizados;
	private Integer qtdeImoveisSemHidrometro;
	private Integer qtdeImoveisComEconomiasAtualizados;
	private Integer qtdeImoveisComMedidorEnergiaAtualizados;
	private Date ultimaAlteracao;
	private Collection<AtualizacaoCadastralSimplificadoLinha> linhas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public AtualizacaoCadastralSimplificadoBinario getBinario() {
		return binario;
	}

	public void setBinario(AtualizacaoCadastralSimplificadoBinario binario) {
		this.binario = binario;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getQtdeTotalImoveis() {
		return qtdeTotalImoveis;
	}

	public void setQtdeTotalImoveis(Integer qtdeTotalImoveis) {
		this.qtdeTotalImoveis = qtdeTotalImoveis;
	}

	public Integer getQtdeImoveisComHidrometro() {
		return qtdeImoveisComHidrometro;
	}

	public void setQtdeImoveisComHidrometro(Integer qtdeImoveisComHidrometro) {
		this.qtdeImoveisComHidrometro = qtdeImoveisComHidrometro;
	}

	public Integer getQtdeImoveisComHidrometroAtualizados() {
		return qtdeImoveisComHidrometroAtualizados;
	}

	public void setQtdeImoveisComHidrometroAtualizados(
			Integer qtdeImoveisComHidrometroAtualizados) {
		this.qtdeImoveisComHidrometroAtualizados = qtdeImoveisComHidrometroAtualizados;
	}

	public Integer getQtdeImoveisSemHidrometro() {
		return qtdeImoveisSemHidrometro;
	}

	public void setQtdeImoveisSemHidrometro(Integer qtdeImoveisSemHidrometro) {
		this.qtdeImoveisSemHidrometro = qtdeImoveisSemHidrometro;
	}

	public Integer getQtdeImoveisComEconomiasAtualizados() {
		return qtdeImoveisComEconomiasAtualizados;
	}

	public void setQtdeImoveisComEconomiasAtualizados(
			Integer qtdeImoveisComEconomiasAtualizados) {
		this.qtdeImoveisComEconomiasAtualizados = qtdeImoveisComEconomiasAtualizados;
	}

	public Integer getQtdeImoveisComMedidorEnergiaAtualizados() {
		return qtdeImoveisComMedidorEnergiaAtualizados;
	}

	public void setQtdeImoveisComMedidorEnergiaAtualizados(
			Integer qtdeImoveisComMedidorEnergiaAtualizados) {
		this.qtdeImoveisComMedidorEnergiaAtualizados = qtdeImoveisComMedidorEnergiaAtualizados;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Collection<AtualizacaoCadastralSimplificadoLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(Collection<AtualizacaoCadastralSimplificadoLinha> linhas) {
		this.linhas = linhas;
	}

	public String getIndiceAtualizacaoHidrometro() {
		if (qtdeImoveisComHidrometro != null && qtdeImoveisComHidrometro > 0) {
		BigDecimal indice = new BigDecimal(
				this.qtdeImoveisComHidrometroAtualizados).divide(
				new BigDecimal(this.qtdeImoveisComHidrometro), 4,
				BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		return Util.formataBigDecimal(indice, 2, true);
		} else {
			return Util.formataBigDecimal(BigDecimal.ZERO, 2, true);
		}
	}

	public String getIndiceAtualizacaoEconomias() {
		BigDecimal indice = new BigDecimal(
				this.qtdeImoveisComEconomiasAtualizados).divide(
				new BigDecimal(this.qtdeTotalImoveis), 4,
				BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		return Util.formataBigDecimal(indice, 2, true);
	}
	
	public String getIndiceAtualizacaoMedidoresEnergia() {
		BigDecimal indice = new BigDecimal(0);
		if (qtdeImoveisComMedidorEnergiaAtualizados != null){
			indice = new BigDecimal(
					this.qtdeImoveisComMedidorEnergiaAtualizados).divide(
							new BigDecimal(this.qtdeTotalImoveis), 4,
							BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		}
		
		return Util.formataBigDecimal(indice, 2, true);
	}

}
