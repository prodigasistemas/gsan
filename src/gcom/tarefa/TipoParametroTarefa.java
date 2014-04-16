package gcom.tarefa;


/**
 * Classe que o representa um Tipo Parametro Tarefa
 * 
 * @author thiago
 * @date 24/01/2006
 */
public class TipoParametroTarefa {
	
	public static final TipoParametroTarefa TIPO_SHORT = new TipoParametroTarefa(0,"Short"); 
	public static final TipoParametroTarefa TIPO_BYTE = new TipoParametroTarefa(1,"Byte");
	public static final TipoParametroTarefa TIPO_CHARACTER  = new TipoParametroTarefa(2,"Character");
	public static final TipoParametroTarefa TIPO_INTEGER = new TipoParametroTarefa(3,"Integer");
	public static final TipoParametroTarefa TIPO_LONG = new TipoParametroTarefa(4,"Long");
	public static final TipoParametroTarefa TIPO_DOUBLE = new TipoParametroTarefa(5,"Double");
	public static final TipoParametroTarefa TIPO_FLOAT = new TipoParametroTarefa(6,"Float");
	public static final TipoParametroTarefa TIPO_STRING = new TipoParametroTarefa(7,"String");
	public static final TipoParametroTarefa TIPO_BOOLEAN = new TipoParametroTarefa(8,"Boolean");
	public static final TipoParametroTarefa TIPO_BIGDECIMAL = new TipoParametroTarefa(9,"BigDecimal");
	public static final TipoParametroTarefa TIPO_DATE = new TipoParametroTarefa(10,"Date");
	public static final TipoParametroTarefa TIPO_ARRAY_SHORT = new TipoParametroTarefa(11,"ArrayShort"); 
	public static final TipoParametroTarefa TIPO_ARRAY_BYTE = new TipoParametroTarefa(12,"ArrayByte");
	public static final TipoParametroTarefa TIPO_ARRAY_CHARACTER  = new TipoParametroTarefa(13,"ArrayCharacter");
	public static final TipoParametroTarefa TIPO_ARRAY_INTEGER = new TipoParametroTarefa(14,"ArrayInteger");
	public static final TipoParametroTarefa TIPO_ARRAY_LONG = new TipoParametroTarefa(15,"ArrayLong");
	public static final TipoParametroTarefa TIPO_ARRAY_DOUBLE = new TipoParametroTarefa(16,"ArrayDouble");
	public static final TipoParametroTarefa TIPO_ARRAY_FLOAT = new TipoParametroTarefa(17,"ArrayFloat");
	public static final TipoParametroTarefa TIPO_ARRAY_STRING = new TipoParametroTarefa(18,"ArrayString");
	public static final TipoParametroTarefa TIPO_ARRAY_BOOLEAN = new TipoParametroTarefa(19,"ArrayBoolean");
	public static final TipoParametroTarefa TIPO_ARRAY_BIGDECIMAL = new TipoParametroTarefa(20,"ArrayBigDecimal");
	public static final TipoParametroTarefa TIPO_ARRAY_DATE = new TipoParametroTarefa(21,"ArrayDate");
	public static final String SEPARADOR_ARRAY = "#@&";

	private Integer id;
	private String descricao;
	
	public TipoParametroTarefa(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public TipoParametroTarefa() {
	
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
