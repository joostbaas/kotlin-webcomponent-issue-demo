package webcomponent

import web.components.CustomElementRegistry
import web.components.customElements
import web.dom.DocumentReadyState.Companion.complete
import web.dom.document
import web.html.HtmlTagName

import webcomponent.works.WorkingWebComponent

fun <T : web.html.HTMLElement> CustomElementRegistry.register(name: String, clazz: JsClass<T>) {
    patchObservedAttributes(clazz)
    define(HtmlTagName(name), clazz)
}

private fun <T : Any> patchObservedAttributes(clazz: JsClass<T>) {
    clazz.asDynamic().observedAttributes = clazz.asDynamic().Companion.observedAttributes ?: arrayOf<String>()
}

private const val WORKING_WEBCOMPONENT = "working-webcomponent"
const val ATTRIBUTE_NAME = "some-attribute"

@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("initializeKotlinWebComponents")
fun initializeKotlinWebComponents() {
    println("Initializing WebComponentSupport")
    customElements.register(WORKING_WEBCOMPONENT, WorkingWebComponent::class.js)
    document.onreadystatechange = {
        if (document.readyState == complete) {
            println("Document ready, appending web components!")
            document.body.appendChild(document.createElement(WORKING_WEBCOMPONENT).apply {
                setAttribute(ATTRIBUTE_NAME, "attribute of WORKING_WEBCOMPONENT!")
            })
        }
    }
}

