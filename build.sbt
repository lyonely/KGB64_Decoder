name := "kgb64-decoder"
version := "0.1"
scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "com.esotericsoftware" % "kryo" % "5.5.0",
  "io.circe" %% "circe-parser" % "0.14.6"
)

Compile / mainClass := Some("KGB64Decoder")

enablePlugins(GraalVMNativeImagePlugin)

graalVMNativeImageOptions ++= Seq(
  "--no-fallback",
  "--initialize-at-build-time=scala",
  "--initialize-at-run-time=com.esotericsoftware.kryo.util.Util,com.esotericsoftware.kryo.unsafe.UnsafeUtil",
  "-H:+ReportExceptionStackTraces",
  "--verbose",
  "-J-Xmx2G",
  "--gc=serial",
  "-march=compatibility"
)