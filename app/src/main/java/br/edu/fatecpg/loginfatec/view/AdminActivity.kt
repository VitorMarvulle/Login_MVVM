package br.edu.fatecpg.loginfatec.view

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.loginfatec.databinding.ActivityAdminBinding
import br.edu.fatecpg.loginfatec.viewmodel.LoginViewModel

class AdminActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityAdminBinding
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("AdminActivity", "Activity criada") // Log para verificar

        listView = binding.listViewUsuarios
        val usuarios = viewModel.getUsuarios().map { "${it.login} - ${if (it.bloq) "Bloqueado" else "Ativo"}" }
        Log.d("AdminActivity", "Usuários obtidos: $usuarios") // Log para verificar

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, usuarios)
        listView.adapter = adapter


    }
}