import reactivemongo.api.MongoConnection.ParsedURI
import reactivemongo.api.{MongoConnection, MongoDriver}


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

/**
  * Created by william on 03/06/17.
  */
package object db {

  private lazy val mongoUri: String = "mongodb://localhost:27017/db-model-implicit"

  private lazy val driver: MongoDriver = MongoDriver()
  private lazy val parsedUri: Try[ParsedURI] = MongoConnection.parseURI(mongoUri)
  private lazy val connection: Try[MongoConnection] = parsedUri.map(driver.connection)

  private lazy val fututreConnection = Future.fromTry(connection)
  private lazy val futureDB = fututreConnection.flatMap(_.database("db-model-implicit"))
  lazy val peopleCollection = futureDB.map(_.collection("people"))

}
