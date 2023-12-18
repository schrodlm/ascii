package Config.table

object TableNameMapper {

  def apply(tableName : String): Table ={
    tableName.toLowerCase match {
      case "default" => new PaulBorkesTable()
      case "mathematical" => new DefaultTable()
      case "nonlinear-default" => new DefaultNonLinearTable()
      case _ => throw new IllegalArgumentException("Unknown table type")
    }
  }
}
