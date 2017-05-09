package gcom.cobranca.controladores;

import gcom.util.ControladorException;

public interface IControladorCobrancaPorResultado {
	
	public void gerarNegociacoesCobrancaEmpresa(int idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException;
}
