package example

import kotlin.browser.window

data class Message(val action: String, val message: String)

fun main(args: Array<String>) {
    console.log("### run-in-tab.js is loaded, and listen on messages")

    chrome.runtime.onMessage.addListener { request, messageSender, sendResponse ->
        console.log(request)
        console.log(messageSender)
        
        val data: Message = request.unsafeCast<Message>()
        if (data.action == "ALERT") {
            window.alert(data.message)
        }
        sendResponse("DONE!")
    }
}