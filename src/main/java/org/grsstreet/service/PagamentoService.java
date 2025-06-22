package org.grsstreet.service;

public class PagamentoService {

    private double valorBase;
    private double valorFrete;

    public PagamentoService(double valorBase, double valorFrete) {
        this.valorBase = valorBase;
        this.valorFrete = valorFrete;
    }

    /**
     * Calcula o valor final aplicando um desconto..
     * Desconto deve ser negativo para reduzir (ex: -0.05 para 5% de desconto).
     */
    public double calcularValorComDesconto(double desconto) {
        return (valorBase + valorFrete) * (1 + desconto);
    }

    /**
     * Calcula o valor final para pagamento no cartão de crédito, considerando parcelas.
     * Até 3 parcelas: sem juros.
     * Acima de 3 parcelas: 1.5% de juros por parcela extra.
     */
    public double calcularValorComJuros(int parcelas) {
        double juros = 0;
        if (parcelas > 3) {
            juros = 0.015 * (parcelas - 3);
        }
        return (valorBase + valorFrete) * (1 + juros);
    }
}
