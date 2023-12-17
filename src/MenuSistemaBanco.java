import java.util.Scanner;
import Usuario.*;
import ContasBancarias.*;
public class MenuSistemaBanco {
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

    public static void cadastrarNovoUsuario(String nomeUsuario, String senha) {
        CadastroUsuario.cadastrarNovoUsuario(nomeUsuario, senha);
    }

    public static Usuario realizarLogin(String nomeUsuario, String senha) {
        return CadastroUsuario.realizarLogin(nomeUsuario, senha);
    }

    public static void realizarOperacoesBancarias(Usuario usuario, Scanner scanner) {
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