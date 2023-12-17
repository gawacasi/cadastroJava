import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Usuario {
    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
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

    private boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Email: " + email;
    }
}

class Cadastro {
    private List<Usuario> usuarios;

    public Cadastro() {
        this.usuarios = new ArrayList<>();
    }

    public void cadastrarUsuario(String nome, String email) {
        try {
            Usuario usuario = new Usuario(nome, email);
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
            System.out.println("2. Exibir usuários cadastrados");
            System.out.println("3. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.next();
                    System.out.print("Digite o email do usuário: ");
                    String email = scanner.next();
                    cadastro.cadastrarUsuario(nome, email);
                    break;
                case 2:
                    cadastro.exibirUsuarios();
                    break;
                case 3:
                    System.out.println("Saindo do sistema.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
