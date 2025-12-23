import java.io.{ByteArrayInputStream, IOException}
import java.util.Base64
import java.util.zip.GZIPInputStream
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input

import io.circe._
import io.circe.parser._

object KGB64Decoder {
  private val dec = Base64.getDecoder
  
  private def createKryo(): Kryo = {
    val kryo = new Kryo()
    kryo.setRegistrationRequired(false)
    kryo.setReferences(true)
    kryo
  }
  
  private val kryo = createKryo()

  def decode(encoded: String): String = {
    val decoded = dec.decode(encoded)
    val input = new Input(new GZIPInputStream(new ByteArrayInputStream(decoded)))
    val output = kryo.readObject(input, classOf[String])
    input.close()
    output
  }

  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      System.err.println("Usage: kgb64-decoder <base64-encoded-string>")
      System.exit(1)
    }
    
    val encoded_text: String = args(0)
    
    // If it doesn't look like KGB64, just print it as-is
    if (!encoded_text.startsWith("H4sIAAA")) {
      println(encoded_text)
      System.exit(0)
    }
    
    try {
      val result = decode(encoded_text)
      val parsed: Either[ParsingFailure, Json] = parse(result)
      
      parsed match {
        case Right(json) => println(json.spaces2)
        case Left(_) => println(result)
      }
    } catch {
      case e: Exception =>
        System.err.println(s"Error: ${e.getMessage}")
        System.exit(1)
    }
  }
}