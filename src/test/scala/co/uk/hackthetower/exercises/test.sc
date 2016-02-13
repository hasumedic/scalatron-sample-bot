object Test {
  val input = Array(("generation", 0), ("time", 0))

  val map = input.foldLeft(Map[String, String]()) { case (acc, (k, v)) =>
    acc.updated(k, v.toString)
  }
  map.get("generation")
}