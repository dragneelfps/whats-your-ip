package io.github.dragneelfps.whatsyourip

import io.kotless.dsl.app.http.RouteKey
import io.kotless.dsl.lang.http.Get
import io.kotless.dsl.lang.http.HttpRequestInterceptor
import io.kotless.dsl.lang.http.redirect
import io.kotless.dsl.model.HttpRequest
import io.kotless.dsl.model.HttpResponse
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking


@Get("/")
fun foo() = "bar"

object RequestInterceptor : HttpRequestInterceptor {
    override val priority = 0

    override fun intercept(
        request: HttpRequest,
        key: RouteKey,
        next: (HttpRequest, RouteKey) -> HttpResponse
    ): HttpResponse {
        println(request)
        val sourceIp = request.requestContext.identity.sourceIp
        runBlocking {
            val res = httpClient.get<String>("http://ip-api.com/json/$sourceIp")
            print(res)
        }
        val actualUrl = request.myQueryStringParameters?.get("next") ?: return redirect("https://www.google.com/")
        return redirect(actualUrl)
    }
}

private val httpClient = HttpClient(OkHttp)
