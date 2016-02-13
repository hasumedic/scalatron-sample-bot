package co.uk.hackthetower.exercises

import cats.data.Xor
import co.uk.hackthetower.commands.bot.{Spawn, Move, BotCommands}
import co.uk.hackthetower.commands.server.ServerCommand

import scala.util.Random

/**
  * Second exercise: Implement method 'processServerCommand'
  *
  * This method receives an Xor[String, ServerCommand] instance and will return an Xor answer.
  *
  * Input:
  * - we receive a Left of String if the input is invalid
  * - we receive a Right of ServerCommand if we got the right command
  *
  * Note that receiving a Left doesn't mean we can't still send BotCommands to the server with instructions.
  *
  * Output:
  * - if the processing fails, we will send a Left with an error message.
  * This will be automatically converted to Say and Log commands for the server.
  * - if the processing succeeds, we will send a Right with a list of BotCommands to send to the server
  *
  * Aims:
  * - Learn to work with Xor to propagate error states
  * - Learn to use both right/left sides as well as mapping over them
  *
  * See:
  * - http://typelevel.org/cats/tut/xor.html
  */
object Ex2BotLogic {

  def processServerCommand(command: Xor[String, ServerCommand]): Xor[String, List[BotCommands]] = {
    Xor.Right(
      List(
        Move(randomMove(), randomMove()),
        Move(randomMove(), randomMove()),
        Move(randomMove(), randomMove()),
        Spawn((randomMove(), randomMove()), "DIEEEE", 10, Map())
      )
    )
  }

  def randomMove(): Int = {
    val random = new Random().nextInt()
    if (random == 0) random
    else if (random < 0) -1
    else 1
  }
}
