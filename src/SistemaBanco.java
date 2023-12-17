import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface OperacoesBancarias {
    void depositar(double valor);
    void sacar(double valor);
    double consultarSaldo();
}

class Conta {
    private String titular;
    public double saldo;

    public Conta(String titular) {
        this.titular = titular;
        this.saldo = 0.0;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void exibirInformacoes() {
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: R$" + saldo);
    }
}

class ContaCorrente extends Conta implements OperacoesBancarias {
    private double taxaManutencao;

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

class ContaPoupanca extends Conta implements OperacoesBancarias {
    private double taxaRendimento;

    public ContaPoupanca(String titular, double taxaRendimento) {
        super(titular);
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void depositar(double valor) {
        super.getSaldo();
        super.exibirInformacoes();
        System.out.println("Depositando R$" + valor + " na conta poupança de " + super.getTitular());
        super.saldo += valor;
        super.exibirInformacoes();
    }

    @Override
    public void sacar(double valor) {
        super.getSaldo();
        super.exibirInformacoes();
        if (super.getSaldo() - valor >= 0) {
            System.out.println("Sacando R$" + valor + " da conta poupança de " + super.getTitular());
            super.saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
        super.exibirInformacoes();
    }

    @Override
    public double consultarSaldo() {
        System.out.println("Saldo atual da conta poupança de " + super.getTitular() + ": R$" + super.getSaldo());
        return super.getSaldo();
    }

    public void calcularRendimento() {
        double rendimento = super.getSaldo() * taxaRendimento;
        super.saldo += rendimento;
        System.out.println("Rendimento da poupança de " + super.getTitular() + ": R$" + rendimento);
        super.exibirInformacoes();
    }
}

class Usuario {
    private String nomeUsuario;
    private String senha;
    private Conta conta;

    public Usuario(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}

class CadastroUsuario {
    private static List<Usuario> usuariosCadastrados = new ArrayList<>();

    public static void cadastrarNovoUsuario(String nomeUsuario, String senha) {
        Usuario novoUsuario = new Usuario(nomeUsuario, senha);
        usuariosCadastrados.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public static Usuario realizarLogin(String nomeUsuario, String senha) {
        for (Usuario usuario : usuariosCadastrados) {
            if (usuario.getNomeUsuario().equals(nomeUsuario) && usuario.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido. Bem-vindo, " + nomeUsuario + "!");
                return usuario;
            }
        }

        System.out.println("Falha no login. Verifique seu nome de usuário e senha.");
        return null;
    }
}

public class SistemaBanco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome de usuário: ");
        String novoUsuario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String novaSenha = scanner.nextLine();
        cadastrarNovoUsuario(novoUsuario, novaSenha);

        System.out.print("Digite seu nome de usuário para login: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Digite sua senha para login: ");
        String senha = scanner.nextLine();
        Usuario usuarioLogado = realizarLogin(nomeUsuario, senha);

        if (usuarioLogado != null) {
            realizarOperacoesBancarias(usuarioLogado, scanner);
        }
    }

    private static void cadastrarNovoUsuario(String nomeUsuario, String senha) {
        CadastroUsuario.cadastrarNovoUsuario(nomeUsuario, senha);
    }

    private static Usuario realizarLogin(String nomeUsuario, String senha) {
        return CadastroUsuario.realizarLogin(nomeUsuario, senha);
    }

    private static void realizarOperacoesBancarias(Usuario usuario, Scanner scanner) {
        System.out.println("Operações bancárias disponíveis para " + usuario.getNomeUsuario());

        ContaCorrente contaCorrente = new ContaCorrente(usuario.getNomeUsuario(), 10.0);
        usuario.setConta(contaCorrente);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Consultar Saldo");
            System.out.println("4 - Sair");

            int escolha = scanner.nextInt();
            double valor;

            switch (escolha) {
                case 1:
                    System.out.print("Digite o valor a ser depositado: ");
                    valor = scanner.nextDouble();
                    contaCorrente.depositar(valor);
                    break;
                case 2:
                    System.out.print("Digite o valor a ser sacado: ");
                    valor = scanner.nextDouble();
                    contaCorrente.sacar(valor);
                    break;
                case 3:
                    contaCorrente.consultarSaldo();
                    break;
                case 4:
                    System.out.println("Sessão encerrada. Obrigado por utilizar nosso sistema.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
