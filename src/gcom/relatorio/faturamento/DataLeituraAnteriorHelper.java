package gcom.relatorio.faturamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class DataLeituraAnteriorHelper {
	
	private Integer idGrupo;
	private Date dataAnterior;
	
	public DataLeituraAnteriorHelper() {
		super();
	}
	
	public DataLeituraAnteriorHelper(Integer idGrupo, Date dataAnterior) {
		super();
		this.idGrupo = idGrupo;
		this.dataAnterior = dataAnterior;
	}
	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Date getDataAnterior() {
		return dataAnterior;
	}
	public void setDataAnterior(Date dataAnterior) {
		this.dataAnterior = dataAnterior;
	}
	
	public static ArrayList<DataLeituraAnteriorHelper> getListaDatasLeituraAnteriorHelper(Collection colecao) {
		ArrayList<DataLeituraAnteriorHelper> lista = new ArrayList<DataLeituraAnteriorHelper>();
		if (colecao != null && !colecao.isEmpty()) {
			Iterator iterator = colecao.iterator();

			while (iterator.hasNext()) {
				Object[] objeto = (Object[]) iterator.next();
				DataLeituraAnteriorHelper helper = new DataLeituraAnteriorHelper((Integer) objeto[0], (Date) objeto[1]);
				lista.add(helper);
			}
		}
		return lista;		
	}
<<<<<<< HEAD
=======

	public static DataLeituraAnteriorHelper getListaDatasLeituraAnteriorHelperPorGrupo(Collection colecao) {
		DataLeituraAnteriorHelper helper = new DataLeituraAnteriorHelper();
		if (colecao != null && !colecao.isEmpty()) {
			Object[] objeto = (Object[]) colecao.iterator().next();
			helper = new DataLeituraAnteriorHelper((Integer) objeto[0], (Date) objeto[1]);
		}
		return helper;
	}
>>>>>>> prodigasistemas-master
}