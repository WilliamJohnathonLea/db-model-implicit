
import db._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by william on 27/06/17.
  */
object Main {

  def main(args: Array[String]): Unit = {
    peopleCollection onComplete {
      _ => println("Done")
    }
  }

}
