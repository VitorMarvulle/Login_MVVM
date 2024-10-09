package br.edu.fatecpg.loginfatec.model

data class Usuario(
    val login: String,
    val senha: String,
    var bloq: Boolean = false,
    var tentativasFalhas: Int = 0
)