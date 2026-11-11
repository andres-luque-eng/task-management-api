name := "task-management-api"
version := "0.1.0"
scalaVersion := "3.10.0"

resolvers += "GitHub Packages" at "https://maven.pkg.github.com/andres-luque-eng/pekko-grpc-starter"

lazy val root = (project in file("."))
  .enablePlugins(PekkoGrpcPlugin)
  .settings(
    Compile / run / mainClass := Some("io.github.aluque.taskmanagement.Server"),
    Compile / run / fork := true,
    libraryDependencies ++= Seq(
      "io.github.andres-luque-eng" %% "pekko-grpc-starter" % "0.1.3",
      "org.scalameta"             %% "munit"              % "1.1.0" % Test
    )
  )
