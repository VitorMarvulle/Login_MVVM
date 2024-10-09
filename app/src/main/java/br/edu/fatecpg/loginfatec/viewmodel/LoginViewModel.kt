package br.edu.fatecpg.loginfatec.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.fatecpg.loginfatec.model.Usuario

class LoginViewModel : ViewModel() {
    private val usuarios = mutableListOf<Usuario>()

    fun logar(user: Usuario): String {
        val usuarioExistente = usuarios.find { it.login == user.login }

        usuarioExistente?.let {
            if (it.bloq) {
                return "Usuário bloqueado. Contate o administrador."
            }

            if (it.senha == user.senha) {
                it.tentativasFalhas = 0
                return "Login efetuado com sucesso!"
            } else {
                it.tentativasFalhas++
                if (it.tentativasFalhas >= 3) {
                    it.bloq = true
                    return "Usuário bloqueado após 3 tentativas."
                }
                return "Senha incorreta. Tentativas: ${it.tentativasFalhas}"
            }
        }
        return "Usuário não encontrado."
    }

    fun cadastrar(user: Usuario): String {
        if (usuarios.any { it.login == user.login }) {
            return "Usuário já cadastrado."
        }
        usuarios.add(user)
        return "Cadastrado com sucesso!"
    }

    // Função para listar todos os usuários
    fun listarUsuarios(): List<Usuario> {
        return usuarios
    }

    fun getUsuarios(): List<Usuario> {
        return usuarios // Retorna a lista de usuários
    }
}