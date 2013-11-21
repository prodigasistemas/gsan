package gcom.gui.relatorio.cobranca;

public class OpcaoTotalizacaoHelper {
	private Integer id;
	private String descricao;
	
	public OpcaoTotalizacaoHelper(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
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
	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		
		if (obj instanceof OpcaoTotalizacaoHelper) {
			OpcaoTotalizacaoHelper helper = (OpcaoTotalizacaoHelper) obj;
			
			retorno = this.getId().compareTo(helper.getId())==0;
			
		}
		return retorno;
	}
}
