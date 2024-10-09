package br.edu.fatecpg.loginfatec.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.loginfatec.databinding.ActivityLoginBinding
import br.edu.fatecpg.loginfatec.model.Usuario
import br.edu.fatecpg.loginfatec.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            val user = obterUsuario()
            user?.let {
                if (it.login == "admin" && it.senha == "1234") {
                    // Redireciona para a AdminActivity
                    val intent = Intent(this, AdminActivity::class.java)
                    startActivity(intent)
                } else {
                    val retorno = viewModel.logar(it)
                    Toast.makeText(this, retorno, Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.btnCadastrar.setOnClickListener {
            val user = obterUsuario()
            user?.let {
                val retorno = viewModel.cadastrar(it)
                Toast.makeText(this, retorno, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun obterUsuario(): Usuario? {
        val login = binding.edtLogin.text.toString()
        val senha = binding.edtSenha.text.toString()
        if (login.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return null
        }
        return Usuario(login, senha)
    }

}
