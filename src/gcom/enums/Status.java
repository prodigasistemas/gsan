package gcom.enums;

public enum Status {
	ATIVO( (short) 1), INATIVO( (short) 2);
	
	private short id;
	
	Status(short s){
		this.id = s;
	}
	
	public short getId(){
		return id;
	}
	
	public static Status parse(short id) {
        Status status = null;
        for (Status item : Status.values()) {
            if (item.getId() == id) {
                status = item;
                break;
            }
        }
        return status;
    }	
}