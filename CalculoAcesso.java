
public class CalculoAcesso {
	/**
	 * Movi as variáveis globais pois estão relacionadas com o calculo do acesso,
	 * então fazem mais sentido estarem dentro da classe CalculoAcesso.
	 */
	public static final float VALOR_FRACAO = (float) 2.0;
	public static final float VALOR_HORA = (float) 7.0;
	public static final float VALOR_DIARIA = (float) 30.0;
	
	private final Acesso acesso;
	
	public CalculoAcesso(Acesso acesso){
		this.acesso = acesso;
	}
	public float calcular() { 
		final int quantidadeHoras;
		final int quantidadeMinutos;
		
		if (entradaMaiorQueSaida()){
			quantidadeMinutos = acesso.minutosSaida + (60 - acesso.minutosEntrada);
			quantidadeHoras = acesso.horaSaida - acesso.horaEntrada - 1;
		}else{
			quantidadeHoras = calculaNormalmente(acesso.horaSaida,acesso.horaEntrada); 
			quantidadeMinutos = calculaNormalmente(acesso.minutosSaida,acesso.minutosEntrada);
		}

		final float valorTotalHoras = quantidadeHoras * VALOR_HORA;
		final float valorTotalMinutos = (float) (Math.ceil(quantidadeMinutos / 15.0) * VALOR_FRACAO);		
		final float valorTotal = valorTotalHoras + valorTotalMinutos;
		
		if (quantidadeHoras >=9)
			return VALOR_DIARIA;
		else 
			return valorTotal;
	}
	private int calculaNormalmente(int Saida, int Entrada){
		return Saida - Entrada;
	}
	private boolean entradaMaiorQueSaida(){
		return acesso.horaSaida > acesso.horaEntrada && acesso.minutosSaida < acesso.minutosEntrada;
	}
}
