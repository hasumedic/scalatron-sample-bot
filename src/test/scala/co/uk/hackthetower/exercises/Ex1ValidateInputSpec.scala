package co.uk.hackthetower.exercises

import org.scalatest.{Matchers, FlatSpec}

import scala.util.Random


class Ex1ValidateInputSpec extends FlatSpec with Matchers {

//  "parseInput" should "return Valid if input contains a valid server command" in {
//    val input = "Goodbye(energy=-1)"
//    Ex1ValidateInput.parseInput(input).isValid should be(true)
//  }
//
//  it should "return Invalid if input is empty" in {
//    val input = ""
//    Ex1ValidateInput.parseInput(input).isInvalid should be(true)
//  }
//
//  it should "return Invalid if input is nonsensical garbage" in {
//    val input = Random.nextString(40)
//    Ex1ValidateInput.parseInput(input).isInvalid should be(true)
//  }
//
//  it should "return Invalid if input doesn't match a server command" in {
//    val input = "Log(text=this is a bot command not a server command!)"
//    Ex1ValidateInput.parseInput(input).isInvalid should be(true)
//  }
//
//  it should "return Invalid if input contains multiple server commands" in {
//    val input = "Goodbye(energy=1)|Goodbye(energy=4)"
//    Ex1ValidateInput.parseInput(input).isInvalid should be(true)
//  }
//
//  it should "recognise other server commands like Welcome" in {
//    val input = "Welcome(name=player,apocalypse=10,round=0,maxslaves=5)"
//    Ex1ValidateInput.parseInput(input).isValid should be (true)
//  }
//
//  it should "recognise 'Welcome' when parameters are out of order" in {
//    val input = "Welcome(apocalypse=1,name=player,maxslaves=13,round=6)"
//    Ex1ValidateInput.parseInput(input).isValid should be (true)
//  }
//
//  it should "return Invalid if 'Goodbye' server command send illegal arguments" in {
//    val input = "Goodbye(name=player)"
//    Ex1ValidateInput.parseInput(input).isInvalid should be (true)
//  }

  it should "validate a React message from the server" in {
    val input = "React(generation=0,time=1118,view=W??????????????????????????????WWW????????????????????????????____??????????????????????????______?????????????????W??????_______????????????????WW?????_________???????????????WW????___________?????????????_WW???_____________????????????_WW??_______________??????????__WW?_________________?????????____________??_________WWWWWWW_____________?????__________________________WWWWWWW_____________________________________________________________________________________________________M_______B________________________________________________________________________________P________________________________________________________________________________________________________________________________________________________________________P_____B_________________________________________________________________________________________________________________WW_____________________________WW?_______P____________________WW?_____________________p______WW??______,energy=-692,slaves=0,name=Alex)"
    Ex1ValidateInput.parseInput(input).isValid should be (true)
  }
}
