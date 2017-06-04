package model

import db._
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocumentWriter, Macros}

import scala.concurrent.Future

/**
  * Created by william on 03/06/17.
  */
case class Person(name: String, age: Int)

object Person {

  implicit val writer: BSONDocumentWriter[Person] = Macros.writer[Person]

  implicit class PersonWritable(val entity: Person)(implicit val collection: Future[BSONCollection])
    extends Writable[Person]

}
