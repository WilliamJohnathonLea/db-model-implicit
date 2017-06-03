package db

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by william on 03/06/17.
  */
trait Writable[E] {

  protected def write(entity: E)(implicit ec: ExecutionContext): Future[Boolean]

}
