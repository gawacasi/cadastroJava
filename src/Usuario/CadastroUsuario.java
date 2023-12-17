package Usuario;

import java.util.ArrayList;
import java.util.List;

public class CadastroUsuario {
    public static List<Usuario> usuariosCadastrados = new ArrayList<>();

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