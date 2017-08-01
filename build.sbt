name := "Dotty中文文档"

version := "1.0"

sourceDirectories in Compile += file(".")

val push = taskKey[Unit]("git push")

push := {
	import scala.sys.process._
	"./push".!
}
