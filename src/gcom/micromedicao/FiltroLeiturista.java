package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroLeiturista extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroLeiturista(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroLeiturista() {}

	public final static String ID = "id";
	public final static String INDICADOR_USO = "indicadorUso";
	public final static String CLIENTE = "cliente";
	public final static String CLIENTE_NOME = "cliente.nome";
	public final static String FUNCIONARIO = "funcionario";
	public final static String FUNCIONARIO_NOME = "funcionario.nome";
	public final static String DDD = "codigoDDD";
	public final static String TELEFONE = "numeroFone";
	public final static String EMPRESA = "empresa";
	public final static String EMPRESA_ID = "empresa.id";
	public final static String IMEI = "numeroImei";
	public final static String USUARIO_ID = "usuario.id";
	public final static String USUARIO = "usuario";
}