package gcom.persistence.util;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;


public class QueryUpdateBuilder {

	private Session session;

	private StringBuffer sqlStatement;
	private StringBuffer sqlConditions;
	private Query query;
	
	private Map<String, Object> parameters;
	
	public QueryUpdateBuilder(Session session, Class<?> klass){
		this.session = session;
		
		parameters = new HashMap<String, Object>();
		sqlStatement = new StringBuffer();
		sqlConditions = new StringBuffer();
		
		sqlStatement.append("UPDATE ");
		sqlStatement.append(klass.getSimpleName());
		sqlStatement.append(" SET");
	}
	
	public StringBuffer appendIfNotNull(String field, Object value) {
		if (value != null) {
			sqlStatement.append(" ");
			sqlStatement.append(field);
			sqlStatement.append(" = ");
			sqlStatement.append(":");
			sqlStatement.append(field);
			sqlStatement.append(",");
			
			parameters.put(field, value);
		}
		
		return sqlStatement;
	}
	
	public StringBuffer appendCondition(String condition, String operator, Object value) {
		if (sqlConditions.toString().isEmpty()) {
			sqlConditions.append(" WHERE");
		}
		
		sqlConditions.append(" ");
		sqlConditions.append(condition);
		sqlConditions.append(" ");
		sqlConditions.append(operator);
		sqlConditions.append(" :");
		sqlConditions.append(condition);
		
		parameters.put(condition, value);
		
		return sqlConditions;
	}
	
	public StringBuffer appendAndCondition(String condition, String operator, Object value) {
		sqlConditions.append(" AND");
		
		return appendCondition(condition, operator, value);
	}
	
	public StringBuffer appendOrCondition(String condition, String operator, Object value) {
		sqlConditions.append(" OR");
		
		return appendCondition(condition, operator, value);
	}
	
	public Query createUpdateQuery() {
		query = (Query) session.createQuery(sql());
		
		Set<String> idsValue = parameters.keySet();
		for (String idValue : idsValue) {
			setParameterIfNotNull(idValue, parameters.get(idValue));
		}
		
		return query;
	}
	
	public StringBuffer getSqlStatement() {
		return sqlStatement;
	}

	public void setSqlStatement(StringBuffer sqlStatement) {
		this.sqlStatement = sqlStatement;
	}

	public StringBuffer getSqlConditions() {
		return sqlConditions;
	}

	public void setSqlConditions(StringBuffer sqlConditions) {
		this.sqlConditions = sqlConditions;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String sql() {
		return sqlStatement.substring(0, sqlStatement.length() - 1).concat(sqlConditions.toString());
	}
	
	private Query setParameterIfNotNull(String name, Object value) {
		if (value != null){
			query.setParameter(name, value);
		}
		
		return query;
	}
}
