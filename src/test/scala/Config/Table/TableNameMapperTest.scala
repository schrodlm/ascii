package Config.Table

import Config.Tables.{DefaultNonLinearTable, DefaultTable, PaulBorkesTable, TableNameMapper}
import org.scalatest.{FlatSpec, Matchers}

class TableNameMapperTest extends FlatSpec with Matchers {

  "TableNameMapper" should "return a PaulBorkesTable for 'paulborkes'" in {
    val table = TableNameMapper("paulborkes")
    table shouldBe a[PaulBorkesTable]
  }

  it should "return a DefaultTable for 'default'" in {
    val table = TableNameMapper("default")
    table shouldBe a[DefaultTable]
  }

  it should "return a DefaultNonLinearTable for 'nonlinear-default'" in {
    val table = TableNameMapper("nonlinear-default")
    table shouldBe a[DefaultNonLinearTable]
  }

  it should "throw IllegalArgumentException for unknown table names" in {
    an[IllegalArgumentException] should be thrownBy TableNameMapper("unknown")
  }
}
