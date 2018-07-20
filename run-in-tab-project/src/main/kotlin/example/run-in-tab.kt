package example

import chrome.runtime.onMessage
import kotlin.browser.window

data class Message(val action: String, val message: String)

fun main(args: Array<String>) {
    console.log("### run-in-tab.js is loaded, and listen on messages")

    listenOnMessages()
    sendToExtension()

}

private fun listenOnMessages() {
    console.log("### listen on ALERT messages")
    onMessage.addListener { request, messageSender, sendResponse ->
        console.log("----- received message -----")
        console.log(request)
        console.log(messageSender)
        val message: Message = request.unsafeCast<Message>()
        if (message.action == "ALERT") {
            window.alert(message.message)
            sendResponse("ALERT_DONE!")
        }
    }
}

fun sendToExtension() {
    console.log("### send PRINT message")
    chrome.runtime.sendMessage(Message("PRINT", "message-from-tab")) { response ->
        console.log("----- received response -----")
        console.log(response)
    }
}
