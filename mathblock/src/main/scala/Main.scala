import zio.{ZIO, ZEnv, ExitCode, App}
import zhttp.http.*
import zio.console.{putStrLn}
import zhttp.service.Server
import zio.stream.ZStream
import scala.util.Try
import zhttp.service.server.ServerChannelFactory
import zhttp.service.EventLoopGroup

object Mathblock extends App {
  private val PORT = 8080

  private val graphiql = Http.succeed(
    Response.http(content =
      HttpData.fromStream(ZStream.fromResource("graphiql.html"))
    )
  )

  private val server =
    Server.port(PORT) ++ // Setup port
      Server.app(
        Http.route { case _ -> Root / "graphiql" =>
          graphiql
        }
      ) // Setup the Http app

  override def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] =
    val nThreads: Int =
      args.headOption.flatMap(x => Try(x.toInt).toOption).getOrElse(0)

    // Create a new server
    server.make
      .use(_ =>
        // Waiting for the server to start
        putStrLn(s"Server started on port $PORT") *> ZIO.never,
      )
      .provideCustomLayer(
        ServerChannelFactory.auto ++ EventLoopGroup.auto(nThreads)
      )
      .exitCode
}
