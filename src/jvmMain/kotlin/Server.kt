import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.ResourceLoader
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.SunHttpLoom
import org.http4k.server.asServer


val app: HttpHandler = routes(

    "/" bind GET to static(ResourceLoader.Classpath("static")),

    "/js" bind GET to static(ResourceLoader.Directory("build/dist/js/productionExecutable/")),

    "favicon.ico" bind GET to { req ->
        static(ResourceLoader.Classpath("/static/"))(req)
    },

    "/img" bind GET to static(ResourceLoader.Classpath("static")),
)

fun main() {
    val printingApp: HttpHandler = PrintRequest()
        .then(app)

    val server = printingApp.asServer(SunHttpLoom(8080)).start()

    println("Server started on http://localhost:${server.port()}/")
}
