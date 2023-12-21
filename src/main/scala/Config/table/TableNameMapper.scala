package Config.table

object TableNameMapper {

  def apply(tableName : String): Table ={
    tableName.toLowerCase match {
      case "paulborkes" => new PaulBorkesTable()
      case "default" => new DefaultTable()
      case "nonlinear-default" => new DefaultNonLinearTable()
      case _ => throw new IllegalArgumentException("Unknown table type")
    }
  }
}
