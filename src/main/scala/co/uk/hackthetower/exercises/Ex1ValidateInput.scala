package co.uk.hackthetower.exercises

import cats.data.Validated._
import cats.data.{OneAnd, ValidatedNel}
import co.uk.hackthetower.commands.server.{React, Welcome, Goodbye, ServerCommand}

/**
  * First exercise: Implement method 'parseInput'.
  *
  * This method must validate the input received from the server and return an object indicating
  * if the input was correct or not.
  * As per specification the input, if valid, will contain a single command, and will match one of the
  * 'ServerCommands' defined in the codebase. Incorrect scenarios may include invalid commands,
  * multiple commands, etc.
  *
  * Aims:
  * - Learn to work with Validated and OneAnd instances (NonEmptyList in this case)
  * - Understand the differences between Validated and Xor
  * - Understand the differences between OneAnd and NonEmptyList
  *
  * See:
  * - http://typelevel.org/cats/tut/validated.html
  * - http://typelevel.org/cats/tut/oneand.html
  */
object Ex1ValidateInput {

  val goodbyeRegex = """^Goodbye\(energy=(-?\d+)\)$""".r
  val welcomeRegex = """^Welcome\(name=(\w+),apocalypse=(-?\d+),round=(-?\d+),maxslaves=(-?\d+)\)$""".r
  val reactRegex = """^React\(generation=(-?\d+),name=(\w+),time=\d,view=(\w+),energy=(\w+),master=(-?\d+):(-?\d+),collision=(-?\d+):(-?\d+),slaves=(-?\d+)\)$""".r

  /**
    * This method parses the input of the server and validates it to ensure we got a valid command
    *
    * @param input the input sent by the server. As per specification it will only have 1 command.
    * @return a ValidatedNel[String, ServerCommand], equivalent to Validated[NonEmptyList[String], ServerCommand]
    */
  def parseInput(input: String): ValidatedNel[String, ServerCommand] = input match {
    case goodbyeRegex(energy) => Valid(Goodbye(energy.toInt))
    case welcomeRegex(name, apocalypse, round, maxSlaves) => Valid(Welcome(name, apocalypse.toInt, round.toInt, maxSlaves.toInt))
    case reactRegex(generation, name, time, view, energy, master1, master2, collision1, collision2, slaves) => Valid(React(generation.toInt, name, time.toInt, view, energy, (master1.toInt, master2.toInt), (collision1.toInt, collision2.toInt), slaves.toInt, Map()))
    case _ => Invalid(OneAnd("What are you talking about?", List()))
  }
}
