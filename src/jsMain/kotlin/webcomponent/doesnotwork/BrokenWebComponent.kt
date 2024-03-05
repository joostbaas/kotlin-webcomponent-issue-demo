@file:OptIn(ExperimentalJsExport::class)
@file:Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")

package webcomponent.doesnotwork

import web.components.AttributeChangedCallback
import web.components.ShadowRootInit
import web.dom.document
import web.html.HTMLElement
import webcomponent.ATTRIBUTE_NAME

@JsExport
@ExperimentalJsExport
@JsName("BrokenWebComponent")
class BrokenWebComponent : HTMLElement() {

    init {
        console.log("Hello from $WHO_AM_I.constructor()!")
    }

    companion object {
        private const val WHO_AM_I = "BrokenWebComponent"

        @Suppress("unused") // might get a @JsStatic annotation in the future
        val observedAttributes = arrayOf(ATTRIBUTE_NAME)

        init {
            println("$WHO_AM_I.init()")
        }
    }

    override val connectedCallback: () -> Unit
        get() = {
            println("$WHO_AM_I connectedCallback")

            val shadowP = document.createElement("p")

            shadowP.textContent =
                """${WHO_AM_I}: living in the shadow root"""

            val shadow = this.attachShadow(js("({'mode' : 'open'})") as ShadowRootInit)

            shadow.appendChild(shadowP)
        }

    override val disconnectedCallback: () -> Unit
        get() = {
            console.log("$WHO_AM_I disconnected")
        }

    override val adoptedCallback: () -> Unit
        get() = {
            console.log("$WHO_AM_I adopted")
        }

    override val attributeChangedCallback: AttributeChangedCallback
        get() = { name, oldValue, newValue ->
            console.log("$WHO_AM_I Attribute changed: $name from $oldValue to $newValue")
        }
}


