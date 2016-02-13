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

  /**
    * This method parses the input of the server and validates it to ensure we got a valid command
    *
    * @param input the input sent by the server. As per specification it will only have 1 command.
    * @return a ValidatedNel[String, ServerCommand], equivalent to Validated[NonEmptyList[String], ServerCommand]
    */
  def parseInput(input: String): ValidatedNel[String, ServerCommand] = {
    val (operation, params) = ServerCommandParser(input)
    val serverCommand = buildServerCommand(operation, params)
    if (serverCommand.isDefined) valid(serverCommand.get)
    else invalid(OneAnd("Don't understand!", List()))
  }

  def buildServerCommand(operation: String, params: Map[String, String]): Option[ServerCommand] = {
    operation match {
      case "Welcome" => buildWelcome(params)
      case "React" => buildReact(params)
      case "Goodbye" => buildGoodbye(params)
      case _ => None
    }
  }

  private def buildWelcome(params: Map[String, String]): Option[Welcome] = {
    for {
      name <- params.get("name")
      apocalypse <- params.get("apocalypse")
      round <- params.get("round")
      maxSlaves <- params.get("maxslaves")
    } yield Welcome(name, Integer.parseInt(apocalypse), Integer.parseInt(round), Integer.parseInt(maxSlaves))
  }

  private def buildReact(params: Map[String, String]): Option[React] = {
    for {
      generation <- params.get("generation")
      name <- params.get("name")
      time <- params.get("time")
      view <- params.get("view")
      energy <- params.get("energy")
      master <- params.get("master")
      collision <- params.get("collision")
      slaves <- params.get("slaves")
    } yield React(
      Integer.parseInt(generation), name, Integer.parseInt(time), view, energy, Some(master.toInt, master.toInt), Some(collision.toInt, collision.toInt), Integer.parseInt(slaves), Map[String, String]()
    )
  }

  def buildGoodbye(params: Map[String, String]): Option[Goodbye] = {
    for {
      energy <- params.get("energy")
    } yield Goodbye(Integer.parseInt(energy))
  }
}

object ServerCommandParser {

  val invalidCommand = ("", Map[String, String]())

  def apply(command: String): (String, Map[String, String]) = {

    def splitParameters(parameters: String) = {
      val segments = parameters.split('=')
      if (!hasExactlyTwoParts(segments)) throw new IllegalStateException("invalid key/value pair: " + parameters)
      (segments(0), segments(1))
    }

    def hasExactlyTwoParts(parts: Array[String]) = parts.length == 2

    def splitByComma(stringToSplit: String): Array[String] = {
      splitBy(',', stringToSplit)
    }

    def splitBy(splitter: Char, stringToSplit: String): Array[String] = {
      stringToSplit.split(splitter)
    }

    val parts = command.split('(')
    if (!hasExactlyTwoParts(parts)) invalidCommand
    else {
      val parameters = splitByComma(parts(1).dropRight(1))
      val keyValuePairs = parameters.map(splitParameters).toMap
      (parts.head, keyValuePairs)
    }
  }
}
