package gcom.relatorio.faturamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class DataLeituraPrevistaHelper {
	
	private Integer idGrupo;
	private Date dataPrevista;
	
	public DataLeituraPrevistaHelper() {
		super();
	}
	
	public DataLeituraPrevistaHelper(Integer idGrupo, Date dataPrevista) {
		super();
		this.idGrupo = idGrupo;
		this.dataPrevista = dataPrevista;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Date getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
	public static ArrayList<DataLeituraPrevistaHelper> getListaDatasLeituraPrevistaHelper(Collection colecao) {
		ArrayList<DataLeituraPrevistaHelper> lista = new ArrayList<DataLeituraPrevistaHelper>();
		if (colecao != null && !colecao.isEmpty()) {
			Iterator iterator = colecao.iterator();

			while (iterator.hasNext()) {
				Object[] objeto = (Object[]) iterator.next();
				DataLeituraPrevistaHelper helper = new DataLeituraPrevistaHelper((Integer) objeto[0], (Date) objeto[1]);
				lista.add(helper);
			}
		}
		return lista;		
	}
<<<<<<< HEAD
=======

	public static DataLeituraPrevistaHelper getListaDatasLeituraPrevistaHelperPorGrupo(Collection colecao) {
		DataLeituraPrevistaHelper helper = new DataLeituraPrevistaHelper();
		if (colecao != null && !colecao.isEmpty()) {
			Object[] objeto = (Object[]) colecao.iterator().next();
			helper = new DataLeituraPrevistaHelper((Integer) objeto[0], (Date) objeto[1]);
		}
		return helper;
	}
>>>>>>> prodigasistemas-master
}