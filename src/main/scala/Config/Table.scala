package Config

class Table(val chars: Array[Char])

class DefaultTable() extends Table(Array('#', '*', 'a'))
class MathematicalTable() extends Table(Array('+', '*', '-', '/'))

class CustomTable(chars: Array[Char]) extends Table(chars)