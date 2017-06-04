package db

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.BSONDocumentWriter

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by william on 04/06/17.
  */
trait Writable[E] {

  def entity: E

  def collection: Future[BSONCollection]

  def write(implicit writer: BSONDocumentWriter[E], ec: ExecutionContext): Future[Boolean] = {
    collection
      .flatMap(_.insert(entity).map(_ => true))
      .recover { case _ => false }
  }

}
