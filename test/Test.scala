import org.scalatest.funsuite.AnyFunSuite
import org.scalactic.source.Position
import java.io.FileReader
import java.io.File
import java.io.Reader
import java.io.ByteArrayOutputStream

class Test extends AnyFunSuite {

  val testCaseDir  = "cases"

  testFile(1)

  def readerIterator(r: Reader): Iterator[Char] = new Iterator[Char] {
    private var x: Int   = r.read()
    def hasNext: Boolean = x != -1
    def next(): Char = {
      val res = x
      x = r.read()
      res.toChar
    }
  }

  def readerString(r: Reader): String = {
    val b = new StringBuilder()
    b.addAll(readerIterator(r))
    b.mkString
  }

  def testFile(n: Int)(implicit pos: Position): Unit = {
    val input    = new FileReader(new File(s"test/case$n"))
    val expected = new FileReader(new File(s"test/answer$n"))
    val actual   = new ByteArrayOutputStream()
    Console.withIn(input) {
      Console.withOut(actual) {
        (new Main).main(Array())
      }
    }
    test(s"case ${n}") {
      assert(actual.toString() == readerString(expected))
    }
  }

}
