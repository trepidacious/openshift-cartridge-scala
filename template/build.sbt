name := "http4s-demo"

version := "0.1-SNAPSHOT"

organization := "org.rebeam"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "jcenter" at "http://jcenter.bintray.com",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "tpolecat" at "http://dl.bintray.com/tpolecat/maven",
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
)

lazy val doobieVersion = "0.2.3"

//lazy val http4sVersion = "0.11.1" //Note this cannot be used at the moment, causes exception in test. This seems to be due to mismatch in scalaz-stream. http4s 0.11 uses scalaz-stream 0.8, and doobie uses 0.7.2a. 0.8 is documented to have breaking changes. doobie 0.3.0 should have scalaz-stream 0.8 support.

lazy val http4sVersion = "0.9.3"

lazy val argonautVersion = "6.1"

libraryDependencies ++= Seq(
  "org.scalatest"   %  "scalatest_2.11"             % "2.2.4"   % "test",
  "org.scalacheck"  %% "scalacheck"                 % "1.11.3"  % "test",   //Note that this is NOT the most recent version of scalacheck,
                                                                            //but IS the one referenced by scalatest on github
  "org.clapper"     %% "grizzled-slf4j"             % "1.0.2",
  "joda-time"       %  "joda-time"                  % "2.9",
  "org.joda"        %  "joda-convert"               % "1.8",

  "org.tpolecat"    %% "doobie-core"                % doobieVersion,
  "org.tpolecat"    %% "doobie-contrib-h2"          % doobieVersion,
  "org.tpolecat"    %% "doobie-contrib-postgresql"  % doobieVersion,
  "org.tpolecat"    %% "doobie-contrib-specs2"      % doobieVersion,

  "org.http4s"      %% "http4s-dsl"                 % http4sVersion,
  "org.http4s"      %% "http4s-blaze-server"        % http4sVersion,
  "org.http4s"      %% "http4s-argonaut"            % http4sVersion,
//  "org.http4s"      %% "http4s-websocket"    % "0.1.1",

  "io.argonaut"     %% "argonaut"                   % argonautVersion
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint"
)

testOptions in Test += Tests.Argument("-oDF")
