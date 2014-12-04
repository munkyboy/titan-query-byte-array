name := "Titan Byte Array Test"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq("com.thinkaurelius.titan" % "titan-core" % "0.5.2",
                            "com.thinkaurelius.titan" % "titan-berkeleyje" % "0.5.2")

fork := true
