package com.lunaryorn.sbt.poop

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object PoopPlugin extends AutoPlugin {
  
  override def requires = JvmPlugin
  
  override def projectSettings: Seq[Setting[_]] = Seq(
    compile in Compile := {
      val log = streams.value.log
      val results = (compile in Compile).value
      
      val infos = results.infos.allInfos
      
      log.info(" CUSTOM COMPILE")
      
      infos foreach {
        case (f, info) => 
          log.info(s"------ ${f.getName}")
          log.warn("# REPORTED #")
          info.reportedProblems foreach { i =>
          log.warn(s"  [${i.category()}] ${i.message()}")
          }
          log.warn("# UNREPORTED #")
          info.unreportedProblems foreach { i =>
            log.warn(s"  [${i.category()}] ${i.message()}")
          }
      }
      
      results
    }    
  ) 
  
}