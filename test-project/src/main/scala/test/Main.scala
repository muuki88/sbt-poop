package test

object Main extends App {

  println("Hello, World")


}

class ComponentRegistry extends ComponentBastiImpl with ComponentMukiImpl

trait Basti
trait ComponentBasti { self : ComponentMuki =>
  val basti: Basti
}
trait ComponentBastiImpl { self : ComponentMuki =>
  val basti = new Basti { 
  
  }
}


trait Muki
trait ComponentMuki {
  val basti: Muki
}
trait ComponentMukiImpl {
  val basti = new Muki { 
  
  }
}

