import org.apache.commons.configuration.BaseConfiguration
import com.thinkaurelius.titan.core.{TitanFactory, TitanGraph, TitanGraphTransaction}

object GraphETL extends App {
  val config = new BaseConfiguration
  config.addProperty("storage.backend", "berkeleyje")
  config.addProperty("storage.directory", "tmp/graph")
  val g = TitanFactory.open(config)

  if (!g.containsPropertyKey("ip")) {
    val ms = g.getManagementSystem()
    ms.makePropertyKey("ip").dataType(classOf[Array[Byte]]).make()
    ms.commit()
  }

  val ip = Array[Byte](127,0,0,1)
  val v = g.addVertex(null)
  v.setProperty("ip", ip)

  println("searching by exact instance")
  if (g.query.has("ip", ip).vertices.iterator.hasNext)
    println("found a matching record")
  else
    println("did not find any records")

  println("searching by same value")
  if (g.query.has("ip", Array[Byte](127,0,0,1)).vertices.iterator.hasNext)
    println("found a matching record")
  else
    println("did not find any records")

  g.rollback
  g.shutdown
}
