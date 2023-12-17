package ContasBancarias;

import Interface.OperacoesBancarias;

public class ContaCorrente extends Conta implements OperacoesBancarias {
    public double taxaManutencao;

    public ContaCorrente(String titular, double taxaManutencao) {
        super(titular);
        this.taxaManutencao = taxaManutencao;
    }

    @Override
    public void depositar(double valor) {
        super.getSaldo();
        super.exibirInformacoes();
        System.out.println("Depositando R$" + valor + " na conta corrente de " + super.getTitular());
        super.saldo += valor;
        super.exibirInformacoes();
    }

    @Override
    public void sacar(double valor) {
        super.getSaldo();
        super.exibirInformacoes();
        if (super.getSaldo() - valor >= 0) {
            System.out.println("Sacando R$" + valor + " da conta corrente de " + super.getTitular());
            super.saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
        super.exibirInformacoes();
    }

    @Override
    public double consultarSaldo() {
        System.out.println("Saldo atual da conta corrente de " + super.getTitular() + ": R$" + super.getSaldo());
        return super.getSaldo();
    }
}
