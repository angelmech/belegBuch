name := "BelegBuchScala3Aufgabe"
version := "0.1"

scalaVersion := "3.3.7"
run := Defaults.runTask(fullClasspath in Runtime, mainClass in run in Compile, runner in run).evaluated

libraryDependencies ++=Seq(
"org.scalactic" %% "scalactic" % "3.2.19",
			   "org.scalatest" %% "scalatest" % "3.2.19" % "test",
  	"org.jfree" % "jfreechart" % "1.0.19")


