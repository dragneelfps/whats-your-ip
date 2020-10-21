package io.github.dragneelfps.whatsyourip

import io.kotless.dsl.app.http.RouteKey
import io.kotless.dsl.lang.http.Get
import io.kotless.dsl.lang.http.HttpRequestInterceptor
import io.kotless.dsl.lang.http.redirect
import io.kotless.dsl.model.HttpRequest
import io.kotless.dsl.model.HttpResponse


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
        val actualUrl = request.myQueryStringParameters?.get("next") ?: return redirect("https://www.google.com/")
        return redirect(actualUrl)
    }
}