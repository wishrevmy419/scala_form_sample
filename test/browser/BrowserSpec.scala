package browser

import org.scalatestplus.play._
import org.scalatestplus.play.guice._

/**
  * To get the full round trip experience, you can use ScalaTest with Selenium.
  *
  * For browser testing, you need both a server (here "GuiceOneServerPerSuite") and a
  * browser driver (here "HtmlUnitFactory") to get running.
  *
  * http://www.scalatest.org/user_guice/using_selenium
  */
class BrowserSpec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {

  "The browser should" must {
    "successfully process a form" in {
      val listWidgetsURL = controllers.routes.WidgetController.listWidgets().absoluteURL(false, s"localhost:$port")

      go to listWidgetsURL

      // Enter in the form fields...
      textField("name").value = "Foo"
      textField("price").value = "100"

      // Press enter button...
      submit()

      // Wait for servet to process...
      eventually {
        // Check to see that the vaue made into Flash message!
        pageSource contains "Foo"
      }
    }
  }
}