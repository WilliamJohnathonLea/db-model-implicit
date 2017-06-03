package model

import db._
import reactivemongo.bson.{BSONDocumentWriter, Macros}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by william on 03/06/17.
  */
case class Person(name: String, age: Int)

object Person {

  implicit class PersonWritable(person: Person) extends MongoWritable[Person](peopleCollection) {

    override implicit protected def personWriter: BSONDocumentWriter[Person] = Macros.writer[Person]

    def write(implicit ec: ExecutionContext): Future[Boolean] = write(person)
  }

}
