package gcom.micromedicao.hidrometro;


/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioHidrometroMarcaHBM implements IRepositorioHidrometroMarca {

	private static IRepositorioHidrometroMarca instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	private RepositorioHidrometroMarcaHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioHidrometroMarca getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioHidrometroMarcaHBM();
		}

		return instancia;
	}	
}
