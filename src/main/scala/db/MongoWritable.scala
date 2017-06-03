package db

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.BSONDocumentWriter

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by william on 03/06/17.
  */
abstract class MongoWritable[E](fColl: Future[BSONCollection]) extends Writable[E] {

  protected implicit def personWriter: BSONDocumentWriter[E]

  override protected def write(entity: E)(implicit ec: ExecutionContext): Future[Boolean] = {
    fColl
      .flatMap(_.insert(entity).map(_ => true))
      .recover { case _ => false }
  }
}
