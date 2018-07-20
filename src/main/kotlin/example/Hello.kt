package example

import chrome.tabs.InjectDetails
import chrome.tabs.QueryInfo
import chrome.tabs.Tab
import chrome.tabs.sendMessage
import kotlinjs.common.jsonAs

data class Message(val action: String, val message: String)

fun main(args: Array<String>) {
    listenOnMessages()

    chrome.tabs.query(jsonAs<QueryInfo>().apply {
        this.active = true
    }) { tabs ->
        tabs.firstOrNull()?.let { tab ->
            console.log("current tab: ${tab.id}")
            chrome.tabs.executeScript(tab.id!!, jsonAs<InjectDetails>().apply {
                file = "run-in-tab.js"
            }) { _ ->
                sendMessageToTab(tab)
            }
        }
    }

}

private fun listenOnMessages() {
    console.log("### listen on PRINT messages")
    chrome.runtime.onMessage.addListener { request, messageSender, sendResponse ->
        console.log("----- received message -----")
        console.log(request)
        console.log(messageSender)
        val message = request.unsafeCast<Message>()
        if (message.action == "PRINT") {
            console.log(message.message)
            sendResponse("PRINT_DONE!")
        }
    }
}

private fun sendMessageToTab(tab: Tab) {
    console.log("### send ALERT message to tab: ${tab.id}")
    sendMessage(tab.id!!, Message("ALERT", "message-from-extension")) { response ->
        console.log("----- received response -----")
        console.log(response)
    }
}

