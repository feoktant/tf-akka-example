name := "tf-akka-http"

version := "0.1"

scalaVersion := "2.13.3"

val akkaHttpV = "10.1.11"
val akkaV = "2.6.5"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core"    % "0.13.0",
  "io.circe" %% "circe-generic" % "0.13.0",

  "de.heikoseeberger" %% "akka-http-circe" % "1.32.0",

  "com.typesafe.akka" %% "akka-http"           % akkaHttpV,
  "com.typesafe.akka" %% "akka-http-testkit"   % akkaHttpV % Test,
  "com.typesafe.akka" %% "akka-stream"         % akkaV,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaV % Test,
)
