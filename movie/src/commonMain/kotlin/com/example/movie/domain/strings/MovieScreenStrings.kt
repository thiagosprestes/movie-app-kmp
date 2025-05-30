package com.example.movie.domain.strings

import com.example.core.utils.getFormattedDate

data class MovieScreenStrings(
    val description: String = "Descrição",
    val runtime: (runtime: Int) -> String = { "| $it minutos | " },
    val releaseDate: (releaseDate: String) -> String = { it.getFormattedDate() },
    val similarMovies: String = "Filmes semelhantes",
    val casting: String = "Elenco",
)