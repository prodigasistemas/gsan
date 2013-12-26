package gcom.gui.cadastro.atualizacaocadastral;

import gcom.util.filtro.DescriptorEntity;
import gcom.util.filtro.Filtro;

public class FilterClassParameters {
	
	private Filtro filter;
	
	private DescriptorEntity entity;
	
	private String invalidMessage;
	
	private String fieldName;
	
	public FilterClassParameters(Filtro filter, DescriptorEntity entity, String invalidMessage, String fieldName) {
		this.filter = filter;
		this.entity = entity;
		this.invalidMessage = invalidMessage;
		this.fieldName = fieldName;
	}
	public Filtro getFilter() {
		return filter;
	}
	public DescriptorEntity getEntity() {
		return entity;
	}
	public String getInvalidMessage() {
		return invalidMessage;
	}
	public String getFieldName() {
		return fieldName;
	}
}
