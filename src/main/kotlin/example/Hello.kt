package example

import chrome.tabs.InjectDetails
import chrome.tabs.QueryInfo
import kotlinjs.common.jsonAs

data class Message(val action: String, val message: String)

fun main(args: Array<String>) {
    chrome.tabs.query(jsonAs<QueryInfo>().apply {
        this.active = true
    }) { tabs ->
        tabs.firstOrNull()?.let { tab ->
            console.log("current tab: ${tab.id}")
            chrome.tabs.executeScript(tab.id!!, jsonAs<InjectDetails>().apply {
                file = "run-in-tab.js"
            }) { _ ->
                console.log("### send message to tab: ${tab.id}")
                chrome.tabs.sendMessage(tab.id!!, Message("ALERT", "message-from-extension"), responseCallback = { response ->
                    console.log("Response from remote: $response")
                })
            }
        }
    }

}

