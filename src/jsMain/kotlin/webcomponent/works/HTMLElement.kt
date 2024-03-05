@file:Suppress("unused")

package webcomponent.works

import web.components.ElementInternals
import web.cssom.ElementCSSInlineStyle
import web.dom.Element
import web.dom.GlobalEventHandlers
import web.dom.HTMLOrSVGElement
import web.html.EnterKeyHint
import web.html.InputMode

/**
 * Duplicate of [web.html.HTMLElement], except [CustomElementCallbacks] is patched
 */
open external class HTMLElement
protected constructor() :
    Element,
    ElementCSSInlineStyle,
    ElementContentEditable,
    GlobalEventHandlers,
    HTMLOrSVGElement,
    CustomElementCallbacks {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/accessKey)
     */
    var accessKey: String

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/accessKeyLabel)
     */
    val accessKeyLabel: String
    var autocapitalize: String

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/dir)
     */
    var dir: String

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/draggable)
     */
    var draggable: Boolean

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/hidden)
     */
    var hidden: Boolean

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/inert)
     */
    var inert: Boolean

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/innerText)
     */
    var innerText: String

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/lang)
     */
    var lang: String

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/offsetHeight)
     */
    val offsetHeight: Int

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/offsetLeft)
     */
    val offsetLeft: Int

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/offsetParent)
     */
    val offsetParent: Element?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/offsetTop)
     */
    val offsetTop: Int

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/offsetWidth)
     */
    val offsetWidth: Int

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/outerText)
     */
    var outerText: String

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/popover)
     */
    var popover: String?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/spellcheck)
     */
    var spellcheck: Boolean

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/title)
     */
    var title: String

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/translate)
     */
    var translate: Boolean

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/attachInternals)
     */
    fun attachInternals(): ElementInternals

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/click)
     */
    fun click()

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/hidePopover)
     */
    fun hidePopover()

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/showPopover)
     */
    fun showPopover()

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/togglePopover)
     */
    fun togglePopover(force: Boolean = definedExternally): Boolean

    override fun connectedCallback()

    override fun disconnectedCallback()

    override fun adoptedCallback()

    override fun attributeChangedCallback(name: String, oldValue: Any?, newValue: Any?)
}


/**
 *  duplicated from [web.html.ElementContentEditable] because otherwise HTMLElement cannot inherit from it
 */
sealed external interface ElementContentEditable {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/contentEditable)
     */
    var contentEditable: String?
        get() = definedExternally
        set(value) = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/enterKeyHint)
     */
    var enterKeyHint: EnterKeyHint?
        get() = definedExternally
        set(value) = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/inputMode)
     */
    var inputMode: InputMode?
        get() = definedExternally
        set(value) = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLElement/isContentEditable)
     */
    val isContentEditable: Boolean?
        get() = definedExternally
}

external interface CustomElementCallbacks {
    fun connectedCallback()
    fun disconnectedCallback()
    fun adoptedCallback()
    fun attributeChangedCallback(name: String, oldValue: Any?, newValue: Any?)
}