package gcom.gui.faturamento.consumotarifa.bean;

import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Classe que irá auxiliar para fazer a contagem do numero de faixa de
 * categorias
 * 
 * @author Tiago Moreno
 * @date 27/03/2006
 */
public class CategoriaFaixaConsumoTarifaHelper {

	private Integer quantidadesFaixa;

	private ConsumoTarifaCategoria consumoTarifaCategoria;

	private Collection colecaoFaixas = new ArrayList();

	public Collection<ConsumoTarifaFaixa> getColecaoFaixas() {
		return colecaoFaixas;
	}

	public void setColecaoFaixas(Collection colecaoFaixas) {
		this.colecaoFaixas = colecaoFaixas;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof CategoriaFaixaConsumoTarifaHelper)) {
			return false;
		}
		CategoriaFaixaConsumoTarifaHelper castOther = (CategoriaFaixaConsumoTarifaHelper) other;

		return ((this.getConsumoTarifaCategoria().getCategoria().getId().equals(castOther
				.getConsumoTarifaCategoria().getCategoria().getId())));
	}

	public CategoriaFaixaConsumoTarifaHelper(Integer quantidadesFaixa,
			ConsumoTarifaCategoria consumoTarifaCategoria) {
		super();

		this.quantidadesFaixa = quantidadesFaixa;
		this.consumoTarifaCategoria = consumoTarifaCategoria;
	}

	public CategoriaFaixaConsumoTarifaHelper(Integer quantidadesFaixa,
			ConsumoTarifaCategoria consumoTarifaCategoria,
			Collection colecaoFaixas) {
		super();

		this.quantidadesFaixa = quantidadesFaixa;
		this.consumoTarifaCategoria = consumoTarifaCategoria;
		this.colecaoFaixas = colecaoFaixas;
	}

	public ConsumoTarifaCategoria getConsumoTarifaCategoria() {
		return consumoTarifaCategoria;
	}

	public void setConsumoTarifaCategoria(
			ConsumoTarifaCategoria consumoTarifaCategoria) {
		this.consumoTarifaCategoria = consumoTarifaCategoria;
	}

	public Integer getQuantidadesFaixa() {
		return quantidadesFaixa;
	}

	public void setQuantidadesFaixa(Integer quantidadesFaixa) {
		this.quantidadesFaixa = quantidadesFaixa;
	}

	public Date getUltimaAlteracao() {
		return this.consumoTarifaCategoria.getUltimaAlteracao();
	}

	public Integer getId() {
		return this.consumoTarifaCategoria.getId();

	}
}
