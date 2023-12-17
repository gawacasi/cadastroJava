import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Usuario {
    private String nome;
    private String email;
    private String senha;
    private double saldo;

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        this.saldo = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        } else {
            throw new IllegalArgumentException("Nome inválido");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validarEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.trim().isEmpty()) {
            this.senha = senha;
        } else {
            throw new IllegalArgumentException("Senha inválida");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso. Novo saldo: R$" + saldo);
        } else {
            System.out.println("Valor de depósito inválido. O valor deve ser maior que zero.");
        }
    }

    private boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Email: " + email + ", Saldo: R$" + saldo;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}

class Cadastro {
    private List<Usuario> usuarios;

    public Cadastro() {
        this.usuarios = new ArrayList<>();
    }

    public void cadastrarUsuario(String nome, String email, String senha) {
        try {
            Usuario usuario = new Usuario(nome, email, senha);
            if (!usuarioJaCadastrado(usuario.getEmail())) {
                usuarios.add(usuario);
                System.out.println("Usuário cadastrado com sucesso!");
            } else {
                System.out.println("Este e-mail já está cadastrado. Utilize outro e-mail.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Cadastro não realizado. " + e.getMessage());
        }
    }

    private boolean usuarioJaCadastrado(String email) {
        return usuarios.stream().anyMatch(u -> u.getEmail().equals(email));
    }

    public Usuario autenticarUsuario(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.autenticar(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public void depositarDinheiro(Usuario usuario, double valor) {
        if (usuario != null) {
            usuario.depositar(valor);
        } else {
            System.out.println("Usuário não encontrado. Verifique o e-mail e a senha.");
        }
    }

    public void exibirUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("Lista de usuários cadastrados:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }
}

public class SistemaCadastro {
    public static void main(String[] args) {
        Cadastro cadastro = new Cadastro();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Autenticar usuário");
            System.out.println("3. Depositar dinheiro");
            System.out.println("4. Exibir usuários cadastrados");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do usuário: ");
                    String nomeCadastro = scanner.next();
                    System.out.print("Digite o email do usuário: ");
                    String emailCadastro = scanner.next();
                    System.out.print("Digite a senha do usuário: ");
                    String senhaCadastro = scanner.next();
                    cadastro.cadastrarUsuario(nomeCadastro, emailCadastro, senhaCadastro);
                    break;
                case 2:
                    System.out.print("Digite o email do usuário: ");
                    String emailAutenticacao = scanner.next();
                    System.out.print("Digite a senha do usuário: ");
                    String senhaAutenticacao = scanner.next();
                    Usuario usuarioAutenticado = cadastro.autenticarUsuario(emailAutenticacao, senhaAutenticacao);
                    if (usuarioAutenticado != null) {
                        System.out.println("Usuário autenticado: " + usuarioAutenticado.getNome());
                    } else {
                        System.out.println("Autenticação falhou. Verifique o e-mail e a senha.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o email do usuário: ");
                    String emailDeposito = scanner.next();
                    System.out.print("Digite a senha do usuário: ");
                    String senhaDeposito = scanner.next();
                    Usuario usuarioDeposito = cadastro.autenticarUsuario(emailDeposito, senhaDeposito);
                    if (usuarioDeposito != null) {
                        System.out.print("Digite o valor a depositar: R$");
                        double valorDeposito = scanner.nextDouble();
                        cadastro.depositarDinheiro(usuarioDeposito, valorDeposito);
                    } else {
                        System.out.println("Depósito falhou. Verifique o e-mail e a senha.");
                    }
                    break;
                case 4:
                    cadastro.exibirUsuarios();
                    break;
                case 5:
                    System.out.println("Saindo do sistema.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
