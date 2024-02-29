package data.api

import com.example.project.BuildKonfig.API_KEY
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import utils.BASE_URL

val httpClient = HttpClient {
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v(tag = "HTTP Client", message = message)
            }
        }
        level = LogLevel.HEADERS
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }
    defaultRequest {
        url {
            url(BASE_URL)
            parameters.append("language", "pt-BR")
            // parameters.append("page", "1")
        }
        header("Authorization", "Bearer $API_KEY")
    }
}.also {
    Napier.base(DebugAntilog())
}