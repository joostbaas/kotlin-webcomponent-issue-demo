@file:OptIn(ExperimentalJsExport::class)
@file:Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")

package webcomponent.works

import web.components.CustomElement
import web.components.ShadowRootInit
import web.dom.document
import web.html.HTMLElement
import webcomponent.ATTRIBUTE_NAME

@JsExport
@ExperimentalJsExport
@JsName("WorkingWebComponent")
class WorkingWebComponent : HTMLElement(), CustomElement.WithCallbacks {

    init {
        console.log("Hello from $WHO_AM_I.constructor()!")
    }

    companion object {
        private const val WHO_AM_I = "WorkingWebComponent"

        @Suppress("unused") // might get a @JsStatic annotation in the future
        val observedAttributes = arrayOf(ATTRIBUTE_NAME)

        init {
            println("$WHO_AM_I.init()")
        }
    }

    override fun connectedCallback() {
            println("$WHO_AM_I connectedCallback")

            val shadowP = document.createElement("p")

            shadowP.textContent =
                """${WHO_AM_I}: living in the shadow root"""

            val shadow = this.attachShadow(js("({'mode' : 'open'})") as ShadowRootInit)

            shadow.appendChild(shadowP)
        }


    override fun disconnectedCallback() {
            console.log("$WHO_AM_I disconnected")
        }

    override fun adoptedCallback() {
            console.log("$WHO_AM_I adopted")
        }

    override fun attributeChangedCallback(name: String, oldValue: Any?, newValue: Any?) {
            console.log("$WHO_AM_I Attribute changed: $name from $oldValue to $newValue")
        }
}
