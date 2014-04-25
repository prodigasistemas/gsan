package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorHidrometroCommand extends ValidadorCommand {

	private ControladorUtilLocal controladorUtil;
	
	public ValidadorHidrometroCommand(
			AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha,
			ControladorUtilLocal controladorUtil) {
		super(cadastroImovel, linha);
		
		this.controladorUtil = controladorUtil;
	}

	@Override
	public void execute() throws Exception {
		validarValorNumeroHidrometro();
		validarTipoCaixaProtecaoHidrometro();
	}
	
	private void validarValorNumeroHidrometro() {
		String numeroHidrometro = linha.get("numeroHidrometro");
		
		if(StringUtils.isEmpty(numeroHidrometro)) {
			cadastroImovel.addMensagemErro("Número do hidrômetro não está preenchido");
		}
	}

	private void validarTipoCaixaProtecaoHidrometro() {
		String tipoCaixaProtecao = linha.get("tipoCaixaProtecaoHidrometro");
		
		if (StringUtils.isEmpty(tipoCaixaProtecao)){
			cadastroImovel.addMensagemErro("Tipo de caixa de proteção não está preenchida");
			
		} else {
			FiltroHidrometroProtecao filtro = new FiltroHidrometroProtecao();
			filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroProtecao.ID, Integer.parseInt(tipoCaixaProtecao)));
			
			try {
				Collection<HidrometroProtecao> colecaohidrometroProtecao = controladorUtil.pesquisar(
						filtro, HidrometroProtecao.class.getName());
				
				if (colecaohidrometroProtecao.isEmpty()) {
					cadastroImovel.addMensagemErro("Tipo de caixa de proteção inexistente");
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
	}
}
