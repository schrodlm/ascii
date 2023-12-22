package Config.Table

import Config.Tables.{LinearTable, NonLinearTable}
import org.scalatest.{FlatSpec, Matchers}


class LinearTableTest extends FlatSpec with Matchers {

  "LinearTable" should "correctly map values to characters" in {
    val chars = Array('a', 'b', 'c', 'd')
    val table = new LinearTable(chars)

    table.getChar(0) shouldBe 'a'         // Test lower boundary
    table.getChar(127) shouldBe 'b'       // Test middle value
    table.getChar(255) shouldBe 'd'       // Test upper boundary
  }
}

class NonLinearTableTest extends FlatSpec with Matchers {

  "NonLinearTable" should "correctly map values to characters" in {
    val chars = Array((85, 'a'), (170, 'b'), (255, 'c'))
    val table = new NonLinearTable(chars)

    table.getChar(0) shouldBe 'a'         // Test lower boundary
    table.getChar(85) shouldBe 'a'        // Test first border
    table.getChar(86) shouldBe 'b'        // Test just above first border
    table.getChar(170) shouldBe 'b'       // Test second border
    table.getChar(255) shouldBe 'c'       // Test upper boundary
  }
}
