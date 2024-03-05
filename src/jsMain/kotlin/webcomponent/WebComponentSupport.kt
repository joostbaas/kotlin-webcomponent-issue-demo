package webcomponent

import web.components.CustomElementRegistry
import web.components.customElements
import web.dom.DocumentReadyState.Companion.complete
import web.dom.document
import web.html.HtmlTagName
import webcomponent.doesnotwork.BrokenWebComponent

import webcomponent.works.WorkingWebComponent

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun <T : webcomponent.works.HTMLElement> CustomElementRegistry.register(name: String, clazz: JsClass<T>) {
    patchObservedAttributes(clazz)
    define(HtmlTagName(name), clazz.asDynamic() as JsClass<web.html.HTMLElement>)
}

fun <T : web.html.HTMLElement> CustomElementRegistry.register(name: String, clazz: JsClass<T>) {
    patchObservedAttributes(clazz)
    define(HtmlTagName(name), clazz)
}

private fun <T : Any> patchObservedAttributes(clazz: JsClass<T>) {
    clazz.asDynamic().observedAttributes = clazz.asDynamic().Companion.observedAttributes ?: arrayOf<String>()
}

private const val WORKING_WEBCOMPONENT = "working-webcomponent"
private const val BROKEN_COMPONENT = "broken-component"
const val ATTRIBUTE_NAME = "some-attribute"

@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("initializeKotlinWebComponents")
fun initializeKotlinWebComponents() {
    println("Initializing WebComponentSupport")
    customElements.register(WORKING_WEBCOMPONENT, WorkingWebComponent::class.js)
    customElements.register(BROKEN_COMPONENT, BrokenWebComponent::class.js)
    document.onreadystatechange = {
        if (document.readyState == complete) {
            println("Document ready, appending web components!")
            listOf(WORKING_WEBCOMPONENT, BROKEN_COMPONENT).forEach { webComponentName ->
                document.body.appendChild(document.createElement(webComponentName).apply {
                    setAttribute(ATTRIBUTE_NAME, "attribute of $webComponentName!")
                })
            }
        }
    }
}
