package gcom.relatorio.faturamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class ValorAFaturarHelper {
	
	private Integer idGrupo;
	private Integer imovel;
	private String nomeCliente;	
	private BigDecimal valorAgua;
	private BigDecimal valorEsgoto;
	
	public ValorAFaturarHelper() {
		super();
	}

	public ValorAFaturarHelper(Integer idGrupo, BigDecimal valorAgua, BigDecimal valorEsgoto) {
		super();
		this.idGrupo = idGrupo;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
	}
	
	public ValorAFaturarHelper(Integer idGrupo, Integer imovel, String nomeCliente, BigDecimal valorAgua, BigDecimal valorEsgoto) {
		super();
		this.idGrupo = idGrupo;
		this.imovel = imovel;
		this.nomeCliente = nomeCliente;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Integer getImovel() {
		return imovel;
	}

	public void setImovel(Integer imovel) {
		this.imovel = imovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public static ArrayList<ValorAFaturarHelper> getListaValoresAFaturarHelper(Collection colecao) {
		ArrayList<ValorAFaturarHelper> lista = new ArrayList<ValorAFaturarHelper>();
		if (colecao != null && !colecao.isEmpty()) {
			Iterator iterator = colecao.iterator();

			while (iterator.hasNext()) {
				Object[] objeto = (Object[]) iterator.next();
				ValorAFaturarHelper helper = new ValorAFaturarHelper((Integer) objeto[0], (BigDecimal) objeto[1], (BigDecimal) objeto[2]);
				lista.add(helper);
			}
		}
		return lista;
	}
	
	public static ArrayList<ValorAFaturarHelper> getListaValoresAFaturarHelperPorGrupo(Integer idGrupo, Collection colecao) {
		ArrayList<ValorAFaturarHelper> lista = new ArrayList<ValorAFaturarHelper>();
		if (colecao != null && !colecao.isEmpty()) {
			Iterator iterator = colecao.iterator();

			while (iterator.hasNext()) {
				Object[] objeto = (Object[]) iterator.next();
				ValorAFaturarHelper helper = new ValorAFaturarHelper(idGrupo, (Integer) objeto[0], (String) objeto[1],
						(BigDecimal) objeto[2], (BigDecimal) objeto[3]);
				lista.add(helper);
			}
		}
		return lista;
	}
	
}