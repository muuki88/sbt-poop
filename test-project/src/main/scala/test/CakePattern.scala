package test

class ComponentRegistry extends ComponentBastiImpl with ComponentMukiImpl

trait Basti
trait ComponentBasti { self: ComponentMuki =>
  val basti: Basti
}
trait ComponentBastiImpl { self: ComponentMuki with FloComponent =>
  val basti = new Basti {

  }
}

trait Muki
trait ComponentMuki {
  val muki: Muki
}
trait ComponentMukiImpl {
  val muki = new Muki {

  }
}

trait Flo
trait ComponentFlo {
  val flo: Flo
}
trait ComponentFloImpl {
  val flo = new Flo {

  }
}

