package model

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.BSONDocumentWriter

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by william on 03/06/17.
  */
case class Person(name: String, age: Int)

object Person {

  implicit class PersonWritable(person: Person)(implicit collection: Future[BSONCollection]) {

    def write(implicit writer: BSONDocumentWriter[Person], ec: ExecutionContext): Future[Boolean] = {
      collection
        .flatMap(_.insert(person).map(_ => true))
        .recover { case _ => false }
    }
  }

}
