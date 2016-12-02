package gcom.relatorio.cliente;

import java.io.Serializable;

public class ReportField implements Serializable {

	private static final long serialVersionUID = 4322745170791148124L;

	private String name;

	private String description;

	private String align;
	
	private String type;

	public ReportField(String name, String description, String align, String type) {
		super();
		this.name = name;
		this.description = description;
		this.align = align;
		this.type  = type;
	}
	
	public ReportField(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.align = null;
	}
	
	public ReportField(String name) {
		super();
		this.name = name;
		this.description = null;
		this.align = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
